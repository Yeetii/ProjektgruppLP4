package se.chalmers.projektgrupplp4.studentlivinggbg.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.service.Observer;

/**
 * Inspired by: http://stackoverflow.com/a/6022474/1521064
 * @author Erik Magnusson
 * Revised by John, Peter Gärdenäs
 * Used by: AdvancedSearchFragmentController, AdvancedSearchFragmentView, MultiSpinnerController, advanced_search_fragment, styles
 * Uses: Observer
 * Responsibility: View for SearchWatcherItems
 */
public class MultiSpinner extends android.support.v7.widget.AppCompatSpinner {

    private List<String> items;
    private boolean[] selected;
    private String defaultText;
    private MultiSpinnerListener listener;
    private AlertDialog.Builder builder;
    private Observer observer;
    private DialogInterface.OnMultiChoiceClickListener onMultiChoiceListener;

    public MultiSpinner(Context context) {
        super(context);
    }

    public MultiSpinner(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
    }

    public MultiSpinner(Context arg0, AttributeSet arg1, int arg2) {
        super(arg0, arg1, arg2);
    }

    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
        selected[which] = isChecked;
    }

    public void setObserver (Observer observer) {
        this.observer = observer;
    }

    public void onCancel(DialogInterface dialog) {
        int amountOfUnselected = 0;
        // refresh text on spinner
        StringBuffer spinnerBuffer = new StringBuffer();
        for (int i = 0; i < items.size(); i++) {
            if (selected[i]) {
                spinnerBuffer.append(items.get(i));
                spinnerBuffer.append(", ");
            } else {
                amountOfUnselected++;
            }
        }
        String spinnerText;
        if (amountOfUnselected > 0 && amountOfUnselected != selected.length) {
            spinnerText = spinnerBuffer.toString();
            if (spinnerText.length() > 2)
                spinnerText = spinnerText.substring(0, spinnerText.length() - 2);
        } else {
            spinnerText = defaultText;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item,
                new String[]{spinnerText});
        setAdapter(adapter);
        listener.onItemsSelected(selected);
    }

    @Override
    public boolean performClick() {
        builder = new AlertDialog.Builder(getContext());
        observer.update("Builder added");
        //This one is really unclear since it does both view and controller stuff in the same method.
        builder.setMultiChoiceItems(items.toArray(new CharSequence[items.size()]), selected, onMultiChoiceListener);
        builder.show();
        return true;
    }

    public void setItems(List<String> items, String allText) {
        this.items = items;
        this.defaultText = allText;

        // all selected by default
        selected = new boolean[items.size()];
        for (int i = 0; i < selected.length; i++){
            selected[i] = true;
        }

        // all text on the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, new String[] {allText});
        setAdapter(adapter);
    }

    public List<String> getSelectedItems(){
        List<String> result = new ArrayList<>();
        for(int i=0; i<items.size(); i++){
            if(selected[i]){
                result.add(items.get(i));
            }
        }
        return result;
    }

    public void setListener(MultiSpinnerListener listener){
        this.listener = listener;
    }

    public void select(int position) {
        selected[position] = true;
    }

    public void selectAll(){
        for(int i = 0; i < selected.length; i ++){
            selected[i] = true;
        }
    }

    public void deselect(int position){
        selected[position] = false;
    }

    public void clear(){
        for(int i=0; i<selected.length; i++){
            selected[i] = false;
        }
    }

    public void setOnMultiChoiceListener(DialogInterface.OnMultiChoiceClickListener onMultiChoiceListener) {
        this.onMultiChoiceListener = onMultiChoiceListener;
    }

    public interface MultiSpinnerListener {
        void onItemsSelected(boolean[] selected);
    }

    public AlertDialog.Builder getBuilder () {
        return builder;
    }
}