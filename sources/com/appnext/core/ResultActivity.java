package com.appnext.core;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import com.mopub.common.Constants;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ResultActivity extends Activity {

    /* renamed from: hA */
    private Intent f4708hA;
    private WebView webView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Intent selector;
        requestWindowFeature(1);
        super.onCreate(bundle);
        LinearLayout linearLayout = new LinearLayout(this);
        setContentView(linearLayout);
        linearLayout.setOrientation(1);
        WebView webView2 = new WebView(getApplicationContext());
        this.webView = webView2;
        webView2.getSettings().setTextZoom(100);
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.getSettings().setAppCacheEnabled(true);
        this.webView.getSettings().setDomStorageEnabled(true);
        this.webView.getSettings().setDatabaseEnabled(true);
        if (Build.VERSION.SDK_INT >= 21) {
            this.webView.getSettings().setMixedContentMode(0);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            this.webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            this.webView.setLayerType(2, (Paint) null);
        } else {
            this.webView.setLayerType(1, (Paint) null);
        }
        this.webView.setWebViewClient(new WebViewClient() {
            public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
                String str2;
                if (str == null) {
                    return false;
                }
                new StringBuilder("result page url ").append(str);
                if (str.startsWith(Constants.HTTP)) {
                    ResultActivity resultActivity = ResultActivity.this;
                    if (resultActivity.hasNewResolver(ResultActivity.m6716U(str).setComponent((ComponentName) null))) {
                        ResultActivity.m6719b(ResultActivity.this, str);
                    } else {
                        webView.loadUrl(str);
                    }
                    return true;
                } else if (str.startsWith("intent://")) {
                    try {
                        Intent parseUri = Intent.parseUri(str, 1);
                        if (ResultActivity.this.getPackageManager().resolveActivity(parseUri, 65536) != null) {
                            ResultActivity.m6719b(ResultActivity.this, parseUri.getData().toString());
                            return true;
                        }
                        if (parseUri.getExtras() != null) {
                            if (parseUri.getExtras().containsKey("browser_fallback_url") && !parseUri.getExtras().getString("browser_fallback_url").equals("")) {
                                str2 = parseUri.getExtras().getString("browser_fallback_url");
                                ResultActivity.m6719b(ResultActivity.this, str2);
                                return true;
                            }
                        }
                        if (parseUri.getExtras().containsKey("market_referrer") && !parseUri.getExtras().getString("market_referrer").equals("")) {
                            str2 = "market://details?id=" + parseUri.getPackage() + "&referrer=" + parseUri.getExtras().getString("market_referrer");
                            ResultActivity.m6719b(ResultActivity.this, str2);
                        }
                        return true;
                    } catch (Throwable unused) {
                        return false;
                    }
                } else {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse(str));
                    try {
                        if (ResultActivity.this.getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
                            ResultActivity.m6719b(ResultActivity.this, str);
                            return true;
                        }
                    } catch (Throwable unused2) {
                    }
                    return false;
                }
            }
        });
        linearLayout.addView(this.webView);
        this.webView.setLayoutParams(new LinearLayout.LayoutParams(-1, 0));
        ((LinearLayout.LayoutParams) this.webView.getLayoutParams()).weight = 1.0f;
        try {
            String string = getIntent().getExtras().getString("url");
            getIntent().getExtras().getString("title");
            new StringBuilder("loading result page ").append(string);
            this.f4708hA = new Intent(m6716U(string)).setComponent((ComponentName) null);
            if (Build.VERSION.SDK_INT >= 15 && (selector = this.f4708hA.getSelector()) != null) {
                selector.setComponent((ComponentName) null);
            }
            this.webView.loadUrl(string);
        } catch (Throwable unused) {
            finish();
        }
    }

    /* renamed from: a */
    private static List m6718a(Context context, Intent intent) {
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo next : queryIntentActivities) {
            arrayList.add(new ComponentName(next.activityInfo.packageName, next.activityInfo.name));
        }
        return arrayList;
    }

    public final boolean hasNewResolver(Intent intent) {
        if (this.f4708hA == null) {
            return intent != null;
        }
        if (intent == null) {
            return false;
        }
        List<ComponentName> a = m6718a((Context) this, intent);
        HashSet hashSet = new HashSet();
        hashSet.addAll(m6718a((Context) this, this.f4708hA));
        for (ComponentName contains : a) {
            if (!hashSet.contains(contains)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* renamed from: U */
    public static Intent m6716U(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        return intent;
    }

    /* renamed from: bl */
    private void m6720bl() {
        onBackPressed();
    }

    private void openLink(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        intent.addFlags(268435456);
        startActivity(intent);
    }

    public void onBackPressed() {
        try {
            if (this.webView == null || !this.webView.canGoBack()) {
                super.onBackPressed();
            } else {
                this.webView.goBack();
            }
        } catch (Throwable unused) {
            finish();
        }
    }

    /* renamed from: b */
    static /* synthetic */ void m6719b(ResultActivity resultActivity, String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        intent.addFlags(268435456);
        resultActivity.startActivity(intent);
    }
}
