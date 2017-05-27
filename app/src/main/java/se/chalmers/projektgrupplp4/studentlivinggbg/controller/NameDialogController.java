package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.Observer;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.NameDialog;

/**
 * @author Erik Magnusson
 * Revised by: Peter Gärdenäs
 * Used by: AdvancedSearchActivityContoller, ModalController
 * Uses: Observer, NameDialog
 * Responsibility: Sets the ok button on the NameDialog
 */

public class NameDialogController {
    public NameDialogController(final AlertDialog.Builder builder, final NameDialog nameDialog, final Observer observer) {
        builder.setPositiveButton(R.string.dialogSearchWatcherOk, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                EditText text = (EditText) nameDialog.getDialogView().findViewById(R.id.dialogSearchWatcherName);
                observer.update(text.getText().toString());
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
