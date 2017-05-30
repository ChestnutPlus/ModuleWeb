package com.chestnut.Web;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chestnut.Common.utils.LogUtils;

public class WebActivity extends AppCompatActivity {

    public static final String URL = "WebActivity-URL";

    private WebView webView;
    private TextView toolbar_title;
    private MyWebViewClient webViewClient;
    private MyWebChromeClient myWebChromeClient;
    private ProgressBar progressBar;
    private String TAG = "WebActivity";
    private boolean OpenLog = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = (WebView) findViewById(R.id.webView);
        progressBar = (ProgressBar) findViewById(R.id.progress_loading);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        findViewById(R.id.id_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.destroy();
                finish();
            }
        });
        webViewClient = new MyWebViewClient();
        myWebChromeClient = new MyWebChromeClient();
        initWeb(getIntent().getStringExtra(URL));
    }

    /**
     * 初始化webView
     * @param url URL
     */
    private void initWeb(String url) {
        if (url==null) {
            LogUtils.i(OpenLog,TAG,"null");
            url = "about:null";
        }
        webView.setWebViewClient(webViewClient);
        WebSettings settings = webView.getSettings();
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        webView.setWebChromeClient(myWebChromeClient.getWebChromeClient());
        myWebChromeClient.setCallback(new MyWebChromeClient.Callback() {
            @Override
            public void onProgressChanged(int progress) {
                progressBar.setProgress(progress);
                if (progress == 100) {
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onReceivedTitle(String title) {
                toolbar_title.setText(title==null?"":title);
            }

            @Override
            public void onReceivedIcon(Bitmap icon) {

            }
        });
        webView.loadUrl(url);
    }

    /**
     * 改写物理按键——返回的逻辑
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK) {
            if(webView.canGoBack()) {
                webView.goBack();//返回上一页面
                return true;
            }
            else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
