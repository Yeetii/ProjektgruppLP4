package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;

import se.chalmers.projektgrupplp4.studentlivinggbg.service.Observer;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.MultiSpinner;

/**
 * Created by PG on 23/05/2017.
 */

public class MultiSpinnerController implements Observer {
    private AlertDialog.Builder builder;
    private MultiSpinner multiSpinner;

    public MultiSpinnerController (MultiSpinner multiSpinner, Enum[] values) {
        this.multiSpinner = multiSpinner;
        multiSpinner.setObserver(this);
        multiSpinner.setListener(new MultiSpinnerListener(values));

    }


    @Override
    public void update(String updateString) {
        builder = multiSpinner.getBuilder();
        initAlertDialogListeners();
    }

    private void initAlertDialogListeners() {
        builder.setPositiveButton(android.R.string.ok, onOkListener);
        builder.setOnCancelListener(onCancelListener);
        multiSpinner.setOnMultiChoiceListener(multiChoiceListener);
    }

    private DialogInterface.OnCancelListener onCancelListener = new DialogInterface.OnCancelListener() {
        @Override
        public void onCancel(DialogInterface dialog) {
            multiSpinner.onCancel(dialog);
        }
    };

    private DialogInterface.OnClickListener onOkListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
        }
    };

    private DialogInterface.OnMultiChoiceClickListener multiChoiceListener = new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            multiSpinner.onClick(dialog, which, isChecked);
        }
    };

    private class MultiSpinnerListener implements MultiSpinner.MultiSpinnerListener{

        private Enum[] terms;

        MultiSpinnerListener(Enum[] terms) {
            this.terms = terms;
        }

        @Override
        public void onItemsSelected(boolean[] selected) {
            //TODO do something with the terms that match with selected
            int i = 0;
            for (boolean b : selected){
                if (b)
                    System.out.println(terms[i]);
                i++;
            }
        }
    }


}
