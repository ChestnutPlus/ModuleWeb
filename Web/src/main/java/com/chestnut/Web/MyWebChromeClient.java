package com.chestnut.Web;

import android.graphics.Bitmap;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.chestnut.Common.utils.LogUtils;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
 *     time  : 2017/5/30 20:02
 *     desc  :  WebChromeClient封装+暴露必要的参数
 *     thanks To:
 *          1.  http://www.jianshu.com/p/6a7c91f1d804
 *     dependent on:
 *     update log:
 * </pre>
 */

public class MyWebChromeClient extends WebChromeClient{

    private String TAG = "MyWebChromeClient";
    private boolean OpenLog = false;
    private WebChromeClient webChromeClient;
    private int progress = -1;

    public WebChromeClient getWebChromeClient() {
        return webChromeClient;
    }

    /**
     * 构造器，内部重写部分重要方法，对外
     * 暴露重要的回调接口
     */
    public MyWebChromeClient() {
        webChromeClient = new WebChromeClient() {
            /**
             * 回调网页的标题
             * @param view  webview
             * @param title 标题
             */
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (callback!=null)
                    callback.onReceivedTitle(title);
            }

            /**
             * 回调网页的Icon
             * @param view  webView
             * @param icon  bitmap-icon
             */
            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                if (callback!=null)
                    callback.onReceivedIcon(icon);
            }

            /**
             * 回调当前页面的加载进度
             * @param view  webview
             * @param newProgress progress
             */
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                LogUtils.i(OpenLog,TAG,"onProgressChanged:"+newProgress);
                if (progress!=newProgress && callback!=null) {
                    callback.onProgressChanged(newProgress);
                    progress = newProgress;
                }
            }
        };
    }

    /**
     * 回调接口
     */
    public interface Callback {
        void onProgressChanged(int progress);
        void onReceivedTitle(String title);
        void onReceivedIcon(Bitmap icon);
    }
    private Callback callback;
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
