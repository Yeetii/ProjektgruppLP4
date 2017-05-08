package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ApplyActivity extends AppCompatActivity {

    private WebView browser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
        browser = (WebView) findViewById(R.id.browser);
        String URL = getIntent().getStringExtra("URL");
        browser.setWebViewClient(new myWebViewClient());
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
