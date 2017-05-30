package com.chestnut.Web;

import android.graphics.Bitmap;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.chestnut.Common.utils.LogUtils;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
 *     time  : 2017/5/30 19:31
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */

public class MyWebViewClient extends WebViewClient{

    private String TAG = "MyWebViewClient";
    private boolean OpenLog = true;

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LogUtils.w(OpenLog,TAG,"shouldOverrideUrlLoading:"+url);
        return true;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        LogUtils.w(OpenLog,TAG,"shouldOverrideUrlLoading2:"+request.toString());
        return true;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        LogUtils.w(OpenLog,TAG,"onPageStarted");
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        LogUtils.w(OpenLog,TAG,"onPageFinished");
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        LogUtils.w(OpenLog,TAG,"onReceivedError");
    }
}
