package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import se.chalmers.projektgrupplp4.studentlivinggbg.controller.AdvancedSearchActivityController;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherModel;

/**
 * Created by Erik on 2017-05-15.
 */

public class NameDialog {

    public NameDialog(final Activity activity, final AdvancedSearchActivityController controller){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setMessage("Välj ett namn för din bevakning.").setTitle("Skapa bevaking");
        LayoutInflater inflater = activity.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_search_watcher_name, null);
        builder.setView(dialogView);
        builder.setPositiveButton(R.string.dialogSearchWatcherOk, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                System.out.println("YOU PRESS OKAAAY");
                // User clicked OK button
                EditText text = (EditText) dialogView.findViewById(R.id.dialogSearchWatcherName);
                controller.createSearchWatcher(text.getText().toString());
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
