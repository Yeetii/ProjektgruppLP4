package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

import android.app.Activity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.activity.ApplyActivity;

/**
 * Created by Erik on 2017-05-17.
 */

public class ApplyActivityController {
    private WebView browser;

    public ApplyActivityController(Activity activity){
        browser = (WebView) activity.findViewById(R.id.browser);
        String URL = activity.getIntent().getStringExtra("URL");
        browser.setWebViewClient(new ApplyActivityController.myWebViewClient());
        browser.getSettings().setJavaScriptEnabled(true);

        browser.getSettings().setLoadWithOverviewMode(true);
        browser.getSettings().setUseWideViewPort(true);
        WebSettings webSettings = browser.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //browser.loadUrl(URL);
        //browser.loadUrl("https://www.chalmersstudentbostader.se/");
    }
    class myWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
