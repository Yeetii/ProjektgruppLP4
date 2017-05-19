package se.chalmers.projektgrupplp4.studentlivinggbg.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.ApplyActivityController;

public class ApplyActivity extends ActivityWithNavigation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new ApplyActivityController(this);

        //Should be in view but ridiculous to make a class for one line?
        setContentView(R.layout.activity_apply);
        initializeNavigationListener();
    }



}
