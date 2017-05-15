package se.chalmers.projektgrupplp4.studentlivinggbg.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.AdvancedSearchActivityController;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.AdvancedSearchActivityView;

public class AdvancedSearchActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AdvancedSearchActivityView view = new AdvancedSearchActivityView(this);
//        AdvancedSearchActivityModel model = new AdvancedSearchActivityModel();
        new AdvancedSearchActivityController(this);
    }
}


