package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import se.chalmers.projektgrupplp4.studentlivinggbg.service.Observer;

/**
 * Created by Erik on 2017-05-15.
 */

public class NameDialog {

    public NameDialog(final View view, final Observer observer){
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

        builder.setMessage("Välj ett namn för din bevakning.").setTitle("Skapa bevaking");
        LayoutInflater inflater = (LayoutInflater)view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.dialog_search_watcher_name, null);
        builder.setView(dialogView);
        builder.setPositiveButton(R.string.dialogSearchWatcherOk, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                EditText text = (EditText) dialogView.findViewById(R.id.dialogSearchWatcherName);
                observer.update(text.getText().toString());
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
