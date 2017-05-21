package se.chalmers.projektgrupplp4.studentlivinggbg.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;

/**
 * Created by Erik on 2017-05-15.
 */

public class NameDialog {
    private View dialogView;
    private AlertDialog.Builder builder;

    public NameDialog(AlertDialog.Builder builder, Activity activity) {
        this.builder = builder;
        builder.setMessage("Välj ett namn för din bevakning.").setTitle("Skapa bevaking");
        LayoutInflater inflater = activity.getLayoutInflater();
        dialogView = inflater.inflate(R.layout.dialog_search_watcher_name, null);
        builder.setView(dialogView);
    }

    public View getDialogView() {
        return dialogView;
    }
}


