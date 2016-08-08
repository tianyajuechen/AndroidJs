package com.tzy.AndroidJs;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MyActivity extends Activity {
    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.bt)
    Button bt;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webview.addJavascriptInterface(new JsInterface(), "jsObj");
        webview.setWebChromeClient(new MyWebChromeClient());
        webview.loadUrl("file:///android_asset/hello.html");


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.loadUrl("javascript: showFromHtml()");
            }
        });
    }

    private class JsInterface {
        public String HtmlcallJava() {
            return "Html call Java";
        }

        public String HtmlcallJava2(final String param) {
            return "Html call Java : " + param;
        }

        public void JavacallHtml() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    webview.loadUrl("javascript: showFromHtml()");
                }
            });
        }

        public void JavacallHtml2() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    webview.loadUrl("javascript: showFromHtml2('IT-homer blog')");
                }
            });
        }

    }

    public void JavacallHtml() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                webview.loadUrl("javascript: showFromHtml()");
                Toast.makeText(MyActivity.this, "clickBtn", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }
}
