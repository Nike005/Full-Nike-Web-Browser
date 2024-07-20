package com.startapp.android.publish.html;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.startapp.android.publish.adsCommon.C1103c;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.adsCommon.p031d.C1117b;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.common.p043a.C1270g;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
public class JsInterface {
    private Runnable clickCallback;
    private Runnable closeCallback;
    private Runnable enableScrollCallback;
    protected boolean inAppBrowserEnabled;
    protected Context mContext;
    private C1117b params;
    private boolean processed;

    public JsInterface(Context context, Runnable runnable, C1117b bVar, boolean z) {
        this(context, runnable, bVar);
        this.inAppBrowserEnabled = z;
    }

    public JsInterface(Context context, Runnable runnable, C1117b bVar) {
        this.processed = false;
        this.inAppBrowserEnabled = true;
        this.closeCallback = null;
        this.clickCallback = null;
        this.enableScrollCallback = null;
        this.closeCallback = runnable;
        this.mContext = context;
        this.params = bVar;
    }

    public JsInterface(Context context, Runnable runnable, Runnable runnable2, Runnable runnable3, C1117b bVar, boolean z) {
        this(context, runnable, bVar, z);
        this.clickCallback = runnable2;
        this.enableScrollCallback = runnable3;
    }

    public JsInterface(Context context, Runnable runnable, Runnable runnable2, C1117b bVar) {
        this(context, runnable, bVar);
        this.clickCallback = runnable2;
    }

    @JavascriptInterface
    public void closeAd() {
        if (!this.processed) {
            this.processed = true;
            this.closeCallback.run();
        }
    }

    @JavascriptInterface
    public void openApp(String str, String str2, String str3) {
        if (str != null && !TextUtils.isEmpty(str)) {
            C1103c.m1395b(this.mContext, str, this.params);
        }
        Intent launchIntentForPackage = this.mContext.getPackageManager().getLaunchIntentForPackage(str2);
        if (str3 != null) {
            try {
                JSONObject jSONObject = new JSONObject(str3);
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String valueOf = String.valueOf(keys.next());
                    launchIntentForPackage.putExtra(valueOf, String.valueOf(jSONObject.get(valueOf)));
                }
            } catch (JSONException e) {
                C1270g.m2077a(6, "Couldn't parse intent details json!", (Throwable) e);
            }
        }
        try {
            this.mContext.startActivity(launchIntentForPackage);
        } catch (Exception e2) {
            C1132f.m1527a(this.mContext, C1130d.EXCEPTION, "JsInterface.openApp - Couldn't start activity", e2.getMessage(), C1103c.m1368a(str, (String) null));
            C1270g.m2076a(6, "Cannot find activity to handle url: [" + str + "]");
        }
        Runnable runnable = this.clickCallback;
        if (runnable != null) {
            runnable.run();
        }
    }

    @JavascriptInterface
    public void externalLinks(String str) {
        if (!this.inAppBrowserEnabled || !C1061i.m1194a(256)) {
            C1103c.m1398c(this.mContext, str);
        } else {
            C1103c.m1396b(this.mContext, str, (String) null);
        }
    }

    @JavascriptInterface
    public void enableScroll(String str) {
        Runnable runnable = this.enableScrollCallback;
        if (runnable != null) {
            runnable.run();
        }
    }
}
