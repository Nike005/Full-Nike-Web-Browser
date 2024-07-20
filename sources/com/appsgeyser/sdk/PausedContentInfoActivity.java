package com.appsgeyser.sdk;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.appsgeyser.sdk.configuration.Configuration;
import com.appsgeyser.sdk.configuration.Constants;
import com.appsgeyser.sdk.server.network.NetworkManager;

public class PausedContentInfoActivity extends Activity {
    private static final String CUSTOM_HTML_ABOUT_KEY = "CustomHtmlAboutKey";

    public static void startPausedContentInfoActivity(Context context, boolean z) {
        Intent intent = new Intent(context, PausedContentInfoActivity.class);
        intent.putExtra(CUSTOM_HTML_ABOUT_KEY, z);
        intent.setFlags(67108864);
        context.startActivity(intent);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        setContentView(C5051R.layout.appsgeysersdk_paused_content_activity);
        Log.d("PausedContentInfo", "created pausedActivity");
        WebView webView = (WebView) findViewById(C5051R.C5054id.webView);
        final boolean booleanExtra = getIntent().getBooleanExtra(CUSTOM_HTML_ABOUT_KEY, false);
        final String str2 = Constants.CUSTOM_HTML_ABOUT_URL + Configuration.getInstance(this).getApplicationId();
        if (booleanExtra) {
            str = str2;
        } else {
            str = Constants.PAUSED_CONTENT_INFO_URL + Configuration.getInstance(this).getApplicationId();
        }
        if (booleanExtra) {
            ImageView imageView = (ImageView) findViewById(C5051R.C5054id.close_screen);
            imageView.setVisibility(0);
            imageView.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    PausedContentInfoActivity.this.lambda$onCreate$0$PausedContentInfoActivity(view);
                }
            });
            imageView.bringToFront();
        }
        if (NetworkManager.isOnline(this)) {
            webView.setWebViewClient(new WebViewClient() {
                public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                    if (Uri.parse(str).getScheme().equals("market")) {
                        try {
                            webView.stopLoading();
                            webView.goBack();
                            Intent intent = new Intent("android.intent.action.VIEW");
                            intent.setData(Uri.parse(str));
                            ((Activity) webView.getContext()).startActivity(intent);
                            return false;
                        } catch (ActivityNotFoundException unused) {
                            Uri parse = Uri.parse(str);
                            webView.loadUrl("http://play.google.com/store/apps/" + parse.getHost() + "?" + parse.getQuery());
                            return false;
                        }
                    } else if (!booleanExtra || str.contains("appsgeyser.com/branding/")) {
                        webView.loadUrl(str);
                        return false;
                    } else {
                        PausedContentInfoActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                        return true;
                    }
                }

                public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                    if (!booleanExtra || webResourceRequest.getUrl().toString().equals(str2)) {
                        webView.loadUrl(webResourceRequest.getUrl().toString());
                    }
                    return super.shouldOverrideUrlLoading(webView, webResourceRequest);
                }
            });
            webView.loadUrl(str);
        } else if (!booleanExtra) {
            webView.setVisibility(8);
            ((FrameLayout) findViewById(C5051R.C5054id.ban_view)).setVisibility(0);
        }
    }

    public /* synthetic */ void lambda$onCreate$0$PausedContentInfoActivity(View view) {
        finish();
    }

    public void onBackPressed() {
        if (getIntent().getBooleanExtra(CUSTOM_HTML_ABOUT_KEY, false)) {
            super.onBackPressed();
        }
    }
}
