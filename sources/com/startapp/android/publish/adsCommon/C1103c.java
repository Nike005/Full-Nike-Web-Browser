package com.startapp.android.publish.adsCommon;

import acr.browser.lightning.constant.Constants;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.browser.customtabs.CustomTabsIntent;
import com.appnext.base.p082b.C4899d;
import com.mopub.common.AdType;
import com.startapp.android.publish.adsCommon.C1040Ad;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.adsCommon.activities.OverlayActivity;
import com.startapp.android.publish.adsCommon.p031d.C1117b;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.android.publish.adsCommon.p042k.C1167a;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.model.AdDetails;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.C1275b;
import com.startapp.common.C1301e;
import com.startapp.common.C1303g;
import com.startapp.common.p043a.C1253a;
import com.startapp.common.p043a.C1254b;
import com.startapp.common.p043a.C1261c;
import com.startapp.common.p043a.C1270g;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.startapp.android.publish.adsCommon.c */
/* compiled from: StartAppSDK */
public class C1103c {

    /* renamed from: a */
    private static ProgressDialog f1097a;

    /* renamed from: a */
    public static C1040Ad.AdType m1365a(AdPreferences adPreferences, String str) {
        Object a = C1061i.m1175a((Class) adPreferences.getClass(), str, (Object) adPreferences);
        if (a == null || !(a instanceof C1040Ad.AdType)) {
            return null;
        }
        return (C1040Ad.AdType) a;
    }

    /* renamed from: a */
    public static void m1384a(AdPreferences adPreferences, String str, C1040Ad.AdType adType) {
        C1061i.m1191a((Class) adPreferences.getClass(), str, (Object) adPreferences, (Object) adType);
    }

    /* JADX WARNING: type inference failed for: r4v4, types: [java.lang.CharSequence] */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m1367a(android.content.Context r3, java.lang.String r4) {
        /*
            android.content.res.Resources r0 = r3.getResources()     // Catch:{ NotFoundException -> 0x000f }
            android.content.pm.ApplicationInfo r1 = r3.getApplicationInfo()     // Catch:{ NotFoundException -> 0x000f }
            int r1 = r1.labelRes     // Catch:{ NotFoundException -> 0x000f }
            java.lang.String r3 = r0.getString(r1)     // Catch:{ NotFoundException -> 0x000f }
            return r3
        L_0x000f:
            android.content.pm.PackageManager r0 = r3.getPackageManager()
            r1 = 0
            android.content.pm.ApplicationInfo r3 = r3.getApplicationInfo()     // Catch:{ NameNotFoundException -> 0x0020 }
            java.lang.String r3 = r3.packageName     // Catch:{ NameNotFoundException -> 0x0020 }
            r2 = 0
            android.content.pm.ApplicationInfo r1 = r0.getApplicationInfo(r3, r2)     // Catch:{ NameNotFoundException -> 0x0020 }
            goto L_0x0021
        L_0x0020:
        L_0x0021:
            if (r1 == 0) goto L_0x0027
            java.lang.CharSequence r4 = r0.getApplicationLabel(r1)
        L_0x0027:
            java.lang.String r4 = (java.lang.String) r4
            java.lang.String r4 = (java.lang.String) r4
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.adsCommon.C1103c.m1367a(android.content.Context, java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    public static boolean m1387a(Activity activity) {
        boolean z = activity.getTheme().obtainStyledAttributes(new int[]{16843277}).getBoolean(0, false);
        if ((activity.getWindow().getAttributes().flags & 1024) != 0) {
            return true;
        }
        return z;
    }

    /* renamed from: a */
    public static int m1364a(String str) {
        String[] split = str.split("&");
        return Integer.parseInt(split[split.length - 1].split("=")[1]);
    }

    /* renamed from: a */
    public static void m1383a(Context context, String[] strArr, String str, String str2) {
        m1382a(context, strArr, str, 0, str2);
    }

    /* renamed from: a */
    public static void m1382a(Context context, String[] strArr, String str, int i, String str2) {
        C1117b nonImpressionReason = new C1117b(str).setOffset(i).setNonImpressionReason(str2);
        if (strArr == null || strArr.length == 0) {
            C1132f.m1527a(context, C1130d.NON_IMPRESSION_NO_DPARAM, str2, nonImpressionReason.getProfileId(), "");
            return;
        }
        for (String str3 : strArr) {
            if (str3 != null && !str3.equalsIgnoreCase("")) {
                C1270g.m2076a(3, "Sending Impression: [" + str3 + "]");
                m1373a(context, str3, nonImpressionReason, false);
            }
        }
    }

    /* renamed from: a */
    public static void m1372a(Context context, String str, C1117b bVar) {
        if (str != null && !str.equalsIgnoreCase("")) {
            C1270g.m2076a(3, "Sending Impression: [" + str + "]");
            if (bVar != null) {
                bVar.setLocation(context);
            }
            m1373a(context, str, bVar, true);
        }
    }

    /* renamed from: a */
    public static void m1381a(Context context, String[] strArr, C1117b bVar) {
        if (strArr != null) {
            for (String a : strArr) {
                m1372a(context, a, bVar);
            }
        }
    }

    /* renamed from: a */
    public static List<String> m1369a(List<String> list, String str, String str2) {
        String str3;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < list.size()) {
            int i2 = i + 5;
            List<String> subList = list.subList(i, Math.min(i2, list.size()));
            StringBuilder sb = new StringBuilder();
            sb.append(AdsConstants.f959f);
            sb.append("?");
            sb.append(TextUtils.join("&", subList));
            sb.append("&isShown=");
            sb.append(str);
            if (str2 != null) {
                str3 = "&appPresence=" + str2;
            } else {
                str3 = "";
            }
            sb.append(str3);
            arrayList.add(sb.toString());
            i = i2;
        }
        C1270g.m2076a(3, "newUrlList size = " + arrayList.size());
        return arrayList;
    }

    /* renamed from: a */
    public static final void m1376a(Context context, String str, String str2, C1117b bVar, boolean z, boolean z2) {
        if (!TextUtils.isEmpty(str2)) {
            m1395b(context, str2, bVar);
        }
        C1174m.m1649a().mo14980b();
        String str3 = null;
        if (!z2) {
            try {
                str3 = m1368a(str, str2);
            } catch (Exception e) {
                C1130d dVar = C1130d.FAILED_EXTRACTING_DPARAMS;
                C1132f.m1527a(context, dVar, "Util.clickWithoutSmartRedirect(): Couldn't extract dparams with clickUrl " + str + " and tacking click url " + str2, e.getMessage(), (String) null);
                StringBuilder sb = new StringBuilder();
                sb.append("Cannot start activity to handle url: [");
                sb.append(str);
                sb.append("]");
                C1270g.m2076a(6, sb.toString());
            }
        }
        try {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(m1407f(str2) ? C1253a.m1982a(str3) : "");
            String sb3 = sb2.toString();
            if (MetaData.getInstance().isInAppBrowser() && z) {
                m1396b(context, sb3, str3);
            } else if (!TextUtils.isEmpty(str2) || !m1406f(context)) {
                m1398c(context, sb3);
            } else {
                m1393b(context);
                m1398c(context, m1408g(sb3));
                C1270g.m2076a(6, "forceExternal - click without - External");
                C1270g.m2076a(6, "forceExternal - click without - trackingClickUrl : " + str2);
            }
        } catch (Exception e2) {
            C1130d dVar2 = C1130d.EXCEPTION;
            C1132f.m1527a(context, dVar2, "Util.clickWithoutSmartRedirect - Couldn't start activity for " + "InAppBrowser", e2.getMessage(), str3);
            C1270g.m2076a(6, "Cannot start activity to handle url: [" + str + "]");
        }
    }

    /* renamed from: f */
    private static boolean m1407f(String str) {
        return C1098b.m1303a().mo14750D() || TextUtils.isEmpty(str);
    }

    /* renamed from: g */
    private static String m1408g(String str) {
        return str + "&cki=1";
    }

    /* renamed from: b */
    private static void m1393b(Context context) {
        C1166k.m1616b(context, "shared_prefs_CookieFeatureTS", Long.valueOf(System.currentTimeMillis()));
        C1270g.m2076a(6, "forceExternal - write to sp - TS : " + SimpleDateFormat.getDateInstance().format(new Date()));
    }

    /* renamed from: a */
    public static final void m1378a(Context context, String str, String str2, String str3, C1117b bVar, long j, long j2, boolean z, Boolean bool, boolean z2) {
        m1379a(context, str, str2, str3, bVar, j, j2, z, bool, z2, (Runnable) null);
    }

    /* renamed from: a */
    public static final void m1379a(Context context, String str, String str2, String str3, C1117b bVar, long j, long j2, boolean z, Boolean bool, boolean z2, Runnable runnable) {
        Context context2 = context;
        String str4 = str;
        String str5 = str2;
        if (C1098b.m1303a().mo14749C()) {
            C1174m.m1649a().mo14980b();
            String str6 = null;
            if (!z2) {
                try {
                    str6 = m1368a(str, str2);
                } catch (Exception e) {
                    Exception exc = e;
                    C1130d dVar = C1130d.FAILED_EXTRACTING_DPARAMS;
                    C1132f.m1527a(context, dVar, "Util.clickWithSmartRedirect(): Couldn't extract dparams with clickUrl " + str + " and tacking click url " + str2, exc.getMessage(), (String) null);
                    StringBuilder sb = new StringBuilder();
                    sb.append("Cannot start activity to handle url: [");
                    sb.append(str);
                    sb.append("]");
                    C1270g.m2076a(6, sb.toString());
                }
            }
            String str7 = "";
            if (str5 != null && !str2.equals(str7)) {
                m1395b(context, str2, bVar);
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            if (m1407f(str2)) {
                str7 = C1253a.m1982a(str6);
            }
            sb2.append(str7);
            m1377a(context, sb2.toString(), str3, str6, j, j2, z, bool, runnable);
            return;
        }
        C1117b bVar2 = bVar;
        m1376a(context, str, str2, bVar, z, z2);
    }

    /* renamed from: b */
    public static void m1395b(Context context, String str, C1117b bVar) {
        m1373a(context, str, bVar, true);
    }

    /* renamed from: a */
    public static void m1373a(final Context context, final String str, final C1117b bVar, final boolean z) {
        if (!str.equals("")) {
            C1303g.m2206a(C1303g.C1307a.HIGH, (Runnable) new Runnable() {
                public void run() {
                    try {
                        if (z) {
                            Context context = context;
                            C1167a.m1622a(context, str + C1253a.m1982a(C1103c.m1404e(str)) + bVar.getQueryString(), (Map<String, String>) null);
                            return;
                        }
                        Context context2 = context;
                        C1167a.m1622a(context2, str + bVar.getQueryString(), (Map<String, String>) null);
                    } catch (C1301e e) {
                        C1132f.m1527a(context, C1130d.EXCEPTION, "Util.sendTrackingMessage - Error sending tracking message", e.getMessage(), C1103c.m1404e(str));
                        C1270g.m2077a(6, "Error sending tracking message", (Throwable) e);
                    }
                }
            });
        }
    }

    /* renamed from: b */
    public static void m1394b(final Context context, final String str) {
        C1303g.m2206a(C1303g.C1307a.HIGH, (Runnable) new Runnable() {
            public void run() {
                try {
                    C1167a.m1622a(context, str, (Map<String, String>) null);
                } catch (C1301e e) {
                    C1132f.m1527a(context, C1130d.EXCEPTION, "Util.sendTrackingMessage - Error sending tracking message", e.getMessage(), "");
                    C1270g.m2077a(6, "Error sending tracking message", (Throwable) e);
                }
            }
        });
    }

    /* renamed from: a */
    private static final void m1377a(Context context, String str, String str2, String str3, long j, long j2, boolean z, Boolean bool, Runnable runnable) {
        Context context2 = context;
        String str4 = str;
        String str5 = str2;
        String str6 = str3;
        C1275b.m2102a(context).mo15481a(new Intent("com.startapp.android.OnClickCallback"));
        if (m1397b(str)) {
            if (str5 != null && !str5.equals("") && !str.toLowerCase().contains(str2.toLowerCase())) {
                C1130d dVar = C1130d.WRONG_PACKAGE_REACHED;
                C1132f.m1527a(context2, dVar, "Wrong package name reached", "Expected: " + str5 + " Link: " + str4, str6);
            }
            m1374a(context2, str4, str6);
            if (runnable != null) {
                runnable.run();
                return;
            }
            return;
        }
        if (context2 instanceof Activity) {
            C1061i.m1182a((Activity) context2, true);
        }
        try {
            final WebView webView = new WebView(context2);
            if (f1097a == null) {
                if (Build.VERSION.SDK_INT >= 22) {
                    f1097a = new ProgressDialog(context2, 16974545);
                } else {
                    f1097a = new ProgressDialog(context2);
                }
                f1097a.setTitle((CharSequence) null);
                f1097a.setMessage("Loading....");
                f1097a.setIndeterminate(false);
                f1097a.setCancelable(false);
                f1097a.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialogInterface) {
                        webView.stopLoading();
                    }
                });
                if ((context2 instanceof Activity) && !((Activity) context2).isFinishing()) {
                    f1097a.show();
                } else if (!(context2 instanceof Activity) && m1399c(context) && f1097a.getWindow() != null) {
                    if (Build.VERSION.SDK_INT >= 26) {
                        f1097a.getWindow().setType(2038);
                    } else {
                        f1097a.getWindow().setType(2003);
                    }
                    f1097a.show();
                }
            }
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebChromeClient(new WebChromeClient());
            C1107a aVar = r2;
            WebView webView2 = webView;
            C1107a aVar2 = new C1107a(j, j2, z, bool, f1097a, str, str2, str3, runnable);
            webView2.setWebViewClient(aVar);
            webView2.loadUrl(str4);
        } catch (Exception e) {
            Context context3 = context;
            C1132f.m1527a(context3, C1130d.EXCEPTION, "Util.smartRedirect - Webview failed", e.getMessage(), str6);
            m1374a(context3, str4, str6);
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    /* renamed from: c */
    private static boolean m1399c(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            return Settings.canDrawOverlays(context);
        }
        return C1261c.m2031a(context, "android.permission.SYSTEM_ALERT_WINDOW");
    }

    /* renamed from: b */
    public static boolean m1397b(String str) {
        return str.startsWith("market") || str.startsWith("http://play.google.com") || str.startsWith("https://play.google.com");
    }

    /* renamed from: c */
    public static boolean m1400c(String str) {
        return str.startsWith("intent://");
    }

    /* renamed from: d */
    public static boolean m1403d(String str) {
        return str != null && (str.startsWith(Constants.HTTP) || str.startsWith(Constants.HTTPS));
    }

    /* renamed from: a */
    public static final void m1370a(Context context) {
        if (context != null && (context instanceof Activity)) {
            C1061i.m1182a((Activity) context, false);
        }
        m1401d(context);
    }

    /* renamed from: d */
    private static void m1401d(Context context) {
        ProgressDialog progressDialog = f1097a;
        if (progressDialog != null) {
            synchronized (progressDialog) {
                if (f1097a != null && f1097a.isShowing()) {
                    try {
                        f1097a.cancel();
                    } catch (Exception e) {
                        C1270g.m2077a(6, "Error while cancelling progress", (Throwable) e);
                        C1132f.m1527a(context, C1130d.EXCEPTION, "AdsCommonUtils.cancelProgress - progress.cancel() failed", e.getMessage(), "");
                    }
                    f1097a = null;
                }
            }
        }
    }

    /* renamed from: com.startapp.android.publish.adsCommon.c$a */
    /* compiled from: StartAppSDK */
    private static class C1107a extends WebViewClient {

        /* renamed from: a */
        protected String f1105a = "";

        /* renamed from: b */
        protected String f1106b;

        /* renamed from: c */
        protected boolean f1107c = false;

        /* renamed from: d */
        protected boolean f1108d = false;

        /* renamed from: e */
        protected long f1109e;

        /* renamed from: f */
        protected boolean f1110f = true;

        /* renamed from: g */
        protected Boolean f1111g = null;

        /* renamed from: h */
        protected String f1112h;

        /* renamed from: i */
        protected ProgressDialog f1113i;

        /* renamed from: j */
        protected Runnable f1114j;

        /* renamed from: k */
        protected boolean f1115k = false;

        /* renamed from: l */
        protected boolean f1116l = false;

        /* renamed from: m */
        private long f1117m;

        /* renamed from: n */
        private LinkedHashMap<String, Float> f1118n = new LinkedHashMap<>();

        /* renamed from: o */
        private long f1119o;

        /* renamed from: p */
        private Timer f1120p;

        public C1107a(long j, long j2, boolean z, Boolean bool, ProgressDialog progressDialog, String str, String str2, String str3, Runnable runnable) {
            this.f1109e = j;
            this.f1117m = j2;
            this.f1110f = z;
            this.f1111g = bool;
            this.f1113i = progressDialog;
            this.f1105a = str;
            this.f1112h = str2;
            this.f1106b = str3;
            this.f1114j = runnable;
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            C1270g.m2076a(2, "MyWebViewClientSmartRedirect::onPageStarted - [" + str + "]");
            super.onPageStarted(webView, str, bitmap);
            if (!this.f1108d) {
                this.f1119o = System.currentTimeMillis();
                this.f1118n.put(str, Float.valueOf(-1.0f));
                m1409a(webView.getContext());
                this.f1108d = true;
            }
            this.f1116l = false;
            m1412b();
        }

        /* JADX WARNING: Removed duplicated region for block: B:29:0x00e9 A[Catch:{ Exception -> 0x013c }] */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x00f6 A[Catch:{ Exception -> 0x013c }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean shouldOverrideUrlLoading(android.webkit.WebView r9, java.lang.String r10) {
            /*
                r8 = this;
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "MyWebViewClientSmartRedirect::shouldOverrideUrlLoading - ["
                r0.append(r1)
                r0.append(r10)
                java.lang.String r1 = "]"
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                r1 = 2
                com.startapp.common.p043a.C1270g.m2076a(r1, r0)
                r0 = 1
                long r1 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x013c }
                long r3 = r8.f1119o     // Catch:{ Exception -> 0x013c }
                long r3 = r1 - r3
                float r3 = (float) r3     // Catch:{ Exception -> 0x013c }
                r4 = 1148846080(0x447a0000, float:1000.0)
                float r3 = r3 / r4
                java.lang.Float r3 = java.lang.Float.valueOf(r3)     // Catch:{ Exception -> 0x013c }
                r8.f1119o = r1     // Catch:{ Exception -> 0x013c }
                java.util.LinkedHashMap<java.lang.String, java.lang.Float> r1 = r8.f1118n     // Catch:{ Exception -> 0x013c }
                java.lang.String r2 = r8.f1105a     // Catch:{ Exception -> 0x013c }
                r1.put(r2, r3)     // Catch:{ Exception -> 0x013c }
                java.util.LinkedHashMap<java.lang.String, java.lang.Float> r1 = r8.f1118n     // Catch:{ Exception -> 0x013c }
                r2 = -1082130432(0xffffffffbf800000, float:-1.0)
                java.lang.Float r2 = java.lang.Float.valueOf(r2)     // Catch:{ Exception -> 0x013c }
                r1.put(r10, r2)     // Catch:{ Exception -> 0x013c }
                r8.f1105a = r10     // Catch:{ Exception -> 0x013c }
                java.lang.String r1 = r10.toLowerCase()     // Catch:{ Exception -> 0x013c }
                boolean r2 = com.startapp.android.publish.adsCommon.C1103c.m1397b((java.lang.String) r1)     // Catch:{ Exception -> 0x013c }
                r3 = 0
                if (r2 != 0) goto L_0x0053
                boolean r2 = com.startapp.android.publish.adsCommon.C1103c.m1400c((java.lang.String) r1)     // Catch:{ Exception -> 0x013c }
                if (r2 != 0) goto L_0x0053
                return r3
            L_0x0053:
                boolean r2 = r8.f1115k     // Catch:{ Exception -> 0x013c }
                if (r2 != 0) goto L_0x013c
                r8.f1107c = r0     // Catch:{ Exception -> 0x013c }
                android.content.Context r2 = r9.getContext()     // Catch:{ Exception -> 0x013c }
                com.startapp.android.publish.adsCommon.C1103c.m1370a((android.content.Context) r2)     // Catch:{ Exception -> 0x013c }
                r8.m1412b()     // Catch:{ Exception -> 0x013c }
                android.content.Context r2 = r9.getContext()     // Catch:{ Exception -> 0x013c }
                boolean r1 = com.startapp.android.publish.adsCommon.C1103c.m1400c((java.lang.String) r1)     // Catch:{ Exception -> 0x013c }
                if (r1 == 0) goto L_0x0071
                java.lang.String r10 = r9.getUrl()     // Catch:{ Exception -> 0x013c }
            L_0x0071:
                com.startapp.android.publish.adsCommon.C1103c.m1398c(r2, r10)     // Catch:{ Exception -> 0x013c }
                java.lang.String r10 = r8.f1112h     // Catch:{ Exception -> 0x013c }
                if (r10 == 0) goto L_0x00c0
                java.lang.String r10 = r8.f1112h     // Catch:{ Exception -> 0x013c }
                java.lang.String r1 = ""
                boolean r10 = r10.equals(r1)     // Catch:{ Exception -> 0x013c }
                if (r10 != 0) goto L_0x00c0
                java.lang.String r10 = r8.f1105a     // Catch:{ Exception -> 0x013c }
                java.lang.String r10 = r10.toLowerCase()     // Catch:{ Exception -> 0x013c }
                java.lang.String r1 = r8.f1112h     // Catch:{ Exception -> 0x013c }
                java.lang.String r1 = r1.toLowerCase()     // Catch:{ Exception -> 0x013c }
                boolean r10 = r10.contains(r1)     // Catch:{ Exception -> 0x013c }
                if (r10 != 0) goto L_0x00c0
                android.content.Context r9 = r9.getContext()     // Catch:{ Exception -> 0x013c }
                com.startapp.android.publish.adsCommon.f.d r10 = com.startapp.android.publish.adsCommon.p033f.C1130d.WRONG_PACKAGE_REACHED     // Catch:{ Exception -> 0x013c }
                java.lang.String r1 = "Wrong package name reached"
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x013c }
                r2.<init>()     // Catch:{ Exception -> 0x013c }
                java.lang.String r3 = "Expected: "
                r2.append(r3)     // Catch:{ Exception -> 0x013c }
                java.lang.String r3 = r8.f1112h     // Catch:{ Exception -> 0x013c }
                r2.append(r3)     // Catch:{ Exception -> 0x013c }
                java.lang.String r3 = " Link: "
                r2.append(r3)     // Catch:{ Exception -> 0x013c }
                java.lang.String r3 = r8.f1105a     // Catch:{ Exception -> 0x013c }
                r2.append(r3)     // Catch:{ Exception -> 0x013c }
                java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x013c }
                java.lang.String r3 = r8.f1106b     // Catch:{ Exception -> 0x013c }
                com.startapp.android.publish.adsCommon.p033f.C1132f.m1527a(r9, r10, r1, r2, r3)     // Catch:{ Exception -> 0x013c }
                goto L_0x0133
            L_0x00c0:
                com.startapp.android.publish.common.metaData.MetaData r10 = com.startapp.android.publish.common.metaData.MetaData.getInstance()     // Catch:{ Exception -> 0x013c }
                com.startapp.android.publish.adsCommon.f.a r10 = r10.getAnalyticsConfig()     // Catch:{ Exception -> 0x013c }
                boolean r10 = r10.mo14871g()     // Catch:{ Exception -> 0x013c }
                java.lang.String r1 = "firstSucceededSmartRedirect"
                if (r10 == 0) goto L_0x00e4
                android.content.Context r10 = r9.getContext()     // Catch:{ Exception -> 0x013c }
                java.lang.Boolean r2 = java.lang.Boolean.valueOf(r0)     // Catch:{ Exception -> 0x013c }
                java.lang.Boolean r10 = com.startapp.android.publish.adsCommon.C1166k.m1606a((android.content.Context) r10, (java.lang.String) r1, (java.lang.Boolean) r2)     // Catch:{ Exception -> 0x013c }
                boolean r10 = r10.booleanValue()     // Catch:{ Exception -> 0x013c }
                if (r10 == 0) goto L_0x00e4
                r10 = 1
                goto L_0x00e5
            L_0x00e4:
                r10 = 0
            L_0x00e5:
                java.lang.Boolean r2 = r8.f1111g     // Catch:{ Exception -> 0x013c }
                if (r2 != 0) goto L_0x00f6
                com.startapp.android.publish.common.metaData.MetaData r2 = com.startapp.android.publish.common.metaData.MetaData.getInstance()     // Catch:{ Exception -> 0x013c }
                com.startapp.android.publish.adsCommon.f.a r2 = r2.getAnalyticsConfig()     // Catch:{ Exception -> 0x013c }
                float r2 = r2.mo14870f()     // Catch:{ Exception -> 0x013c }
                goto L_0x0102
            L_0x00f6:
                java.lang.Boolean r2 = r8.f1111g     // Catch:{ Exception -> 0x013c }
                boolean r2 = r2.booleanValue()     // Catch:{ Exception -> 0x013c }
                if (r2 == 0) goto L_0x0101
                r2 = 1120403456(0x42c80000, float:100.0)
                goto L_0x0102
            L_0x0101:
                r2 = 0
            L_0x0102:
                if (r10 != 0) goto L_0x0111
                double r4 = java.lang.Math.random()     // Catch:{ Exception -> 0x013c }
                r6 = 4636737291354636288(0x4059000000000000, double:100.0)
                double r4 = r4 * r6
                double r6 = (double) r2     // Catch:{ Exception -> 0x013c }
                int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r10 >= 0) goto L_0x0133
            L_0x0111:
                com.startapp.android.publish.adsCommon.f.e r10 = new com.startapp.android.publish.adsCommon.f.e     // Catch:{ Exception -> 0x013c }
                com.startapp.android.publish.adsCommon.f.d r2 = com.startapp.android.publish.adsCommon.p033f.C1130d.SUCCESS_SMART_REDIRECT_HOP_INFO     // Catch:{ Exception -> 0x013c }
                r10.<init>(r2)     // Catch:{ Exception -> 0x013c }
                org.json.JSONArray r2 = r8.mo14801a()     // Catch:{ Exception -> 0x013c }
                r10.mo14886a((org.json.JSONArray) r2)     // Catch:{ Exception -> 0x013c }
                android.content.Context r2 = r9.getContext()     // Catch:{ Exception -> 0x013c }
                java.lang.String r4 = r8.f1106b     // Catch:{ Exception -> 0x013c }
                com.startapp.android.publish.adsCommon.p033f.C1132f.m1529a(r2, r10, r4)     // Catch:{ Exception -> 0x013c }
                android.content.Context r9 = r9.getContext()     // Catch:{ Exception -> 0x013c }
                java.lang.Boolean r10 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x013c }
                com.startapp.android.publish.adsCommon.C1166k.m1613b((android.content.Context) r9, (java.lang.String) r1, (java.lang.Boolean) r10)     // Catch:{ Exception -> 0x013c }
            L_0x0133:
                java.lang.Runnable r9 = r8.f1114j     // Catch:{ Exception -> 0x013c }
                if (r9 == 0) goto L_0x013c
                java.lang.Runnable r9 = r8.f1114j     // Catch:{ Exception -> 0x013c }
                r9.run()     // Catch:{ Exception -> 0x013c }
            L_0x013c:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.adsCommon.C1103c.C1107a.shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String):boolean");
        }

        public void onPageFinished(WebView webView, String str) {
            C1270g.m2076a(2, "MyWebViewClientSmartRedirect::onPageFinished - [" + str + "]");
            if (!this.f1107c && !this.f1115k && this.f1105a.equals(str) && str != null && !C1103c.m1397b(str) && (str.startsWith(Constants.HTTP) || str.startsWith(Constants.HTTPS))) {
                this.f1116l = true;
                try {
                    m1411a(str);
                } catch (Exception unused) {
                }
                m1413b(webView.getContext());
            }
            super.onPageFinished(webView, str);
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            C1270g.m2076a(2, "MyWebViewClientSmartRedirect::onReceivedError - [" + str + "], [" + str2 + "]");
            m1412b();
            if (str2 != null && !C1103c.m1397b(str2) && C1103c.m1403d(str2)) {
                C1132f.m1527a(webView.getContext(), C1130d.FAILED_SMART_REDIRECT, Integer.toString(i), str2, this.f1106b);
            }
            super.onReceivedError(webView, i, str, str2);
        }

        /* renamed from: a */
        private void m1409a(final Context context) {
            C1303g.m2205a((Runnable) new Runnable() {
                /* JADX WARNING: Failed to process nested try/catch */
                /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0030 */
                /* JADX WARNING: Removed duplicated region for block: B:13:0x004f A[Catch:{ Exception -> 0x0078 }] */
                /* JADX WARNING: Removed duplicated region for block: B:14:0x005d A[Catch:{ Exception -> 0x0078 }] */
                /* JADX WARNING: Removed duplicated region for block: B:17:0x0070 A[Catch:{ Exception -> 0x0078 }] */
                /* JADX WARNING: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r5 = this;
                        com.startapp.android.publish.adsCommon.c$a r0 = com.startapp.android.publish.adsCommon.C1103c.C1107a.this
                        boolean r0 = r0.f1107c
                        if (r0 != 0) goto L_0x008a
                        com.startapp.android.publish.adsCommon.f.e r0 = new com.startapp.android.publish.adsCommon.f.e     // Catch:{ Exception -> 0x0030 }
                        com.startapp.android.publish.adsCommon.f.d r1 = com.startapp.android.publish.adsCommon.p033f.C1130d.FAILED_SMART_REDIRECT_HOP_INFO     // Catch:{ Exception -> 0x0030 }
                        r0.<init>(r1)     // Catch:{ Exception -> 0x0030 }
                        com.startapp.android.publish.adsCommon.c$a r1 = com.startapp.android.publish.adsCommon.C1103c.C1107a.this     // Catch:{ Exception -> 0x0030 }
                        org.json.JSONArray r1 = r1.mo14801a()     // Catch:{ Exception -> 0x0030 }
                        r0.mo14886a((org.json.JSONArray) r1)     // Catch:{ Exception -> 0x0030 }
                        com.startapp.android.publish.adsCommon.c$a r1 = com.startapp.android.publish.adsCommon.C1103c.C1107a.this     // Catch:{ Exception -> 0x0030 }
                        boolean r1 = r1.f1116l     // Catch:{ Exception -> 0x0030 }
                        if (r1 == 0) goto L_0x0022
                        java.lang.String r1 = "Page Finished"
                        r0.mo14887d(r1)     // Catch:{ Exception -> 0x0030 }
                        goto L_0x0027
                    L_0x0022:
                        java.lang.String r1 = "Timeout"
                        r0.mo14887d(r1)     // Catch:{ Exception -> 0x0030 }
                    L_0x0027:
                        android.content.Context r1 = r4     // Catch:{ Exception -> 0x0030 }
                        com.startapp.android.publish.adsCommon.c$a r2 = com.startapp.android.publish.adsCommon.C1103c.C1107a.this     // Catch:{ Exception -> 0x0030 }
                        java.lang.String r2 = r2.f1106b     // Catch:{ Exception -> 0x0030 }
                        com.startapp.android.publish.adsCommon.p033f.C1132f.m1529a(r1, r0, r2)     // Catch:{ Exception -> 0x0030 }
                    L_0x0030:
                        com.startapp.android.publish.adsCommon.c$a r0 = com.startapp.android.publish.adsCommon.C1103c.C1107a.this     // Catch:{ Exception -> 0x0078 }
                        r1 = 1
                        r0.f1115k = r1     // Catch:{ Exception -> 0x0078 }
                        android.content.Context r0 = r4     // Catch:{ Exception -> 0x0078 }
                        com.startapp.android.publish.adsCommon.C1103c.m1370a((android.content.Context) r0)     // Catch:{ Exception -> 0x0078 }
                        com.startapp.android.publish.adsCommon.c$a r0 = com.startapp.android.publish.adsCommon.C1103c.C1107a.this     // Catch:{ Exception -> 0x0078 }
                        r0.m1412b()     // Catch:{ Exception -> 0x0078 }
                        com.startapp.android.publish.adsCommon.c$a r0 = com.startapp.android.publish.adsCommon.C1103c.C1107a.this     // Catch:{ Exception -> 0x0078 }
                        boolean r0 = r0.f1110f     // Catch:{ Exception -> 0x0078 }
                        if (r0 == 0) goto L_0x005d
                        com.startapp.android.publish.common.metaData.MetaData r0 = com.startapp.android.publish.common.metaData.MetaData.getInstance()     // Catch:{ Exception -> 0x0078 }
                        boolean r0 = r0.isInAppBrowser()     // Catch:{ Exception -> 0x0078 }
                        if (r0 == 0) goto L_0x005d
                        android.content.Context r0 = r4     // Catch:{ Exception -> 0x0078 }
                        com.startapp.android.publish.adsCommon.c$a r1 = com.startapp.android.publish.adsCommon.C1103c.C1107a.this     // Catch:{ Exception -> 0x0078 }
                        java.lang.String r1 = r1.f1105a     // Catch:{ Exception -> 0x0078 }
                        com.startapp.android.publish.adsCommon.c$a r2 = com.startapp.android.publish.adsCommon.C1103c.C1107a.this     // Catch:{ Exception -> 0x0078 }
                        java.lang.String r2 = r2.f1106b     // Catch:{ Exception -> 0x0078 }
                        com.startapp.android.publish.adsCommon.C1103c.m1396b((android.content.Context) r0, (java.lang.String) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x0078 }
                        goto L_0x006a
                    L_0x005d:
                        android.content.Context r0 = r4     // Catch:{ Exception -> 0x0078 }
                        com.startapp.android.publish.adsCommon.c$a r1 = com.startapp.android.publish.adsCommon.C1103c.C1107a.this     // Catch:{ Exception -> 0x0078 }
                        java.lang.String r1 = r1.f1105a     // Catch:{ Exception -> 0x0078 }
                        com.startapp.android.publish.adsCommon.c$a r2 = com.startapp.android.publish.adsCommon.C1103c.C1107a.this     // Catch:{ Exception -> 0x0078 }
                        java.lang.String r2 = r2.f1106b     // Catch:{ Exception -> 0x0078 }
                        com.startapp.android.publish.adsCommon.C1103c.m1374a((android.content.Context) r0, (java.lang.String) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x0078 }
                    L_0x006a:
                        com.startapp.android.publish.adsCommon.c$a r0 = com.startapp.android.publish.adsCommon.C1103c.C1107a.this     // Catch:{ Exception -> 0x0078 }
                        java.lang.Runnable r0 = r0.f1114j     // Catch:{ Exception -> 0x0078 }
                        if (r0 == 0) goto L_0x008a
                        com.startapp.android.publish.adsCommon.c$a r0 = com.startapp.android.publish.adsCommon.C1103c.C1107a.this     // Catch:{ Exception -> 0x0078 }
                        java.lang.Runnable r0 = r0.f1114j     // Catch:{ Exception -> 0x0078 }
                        r0.run()     // Catch:{ Exception -> 0x0078 }
                        goto L_0x008a
                    L_0x0078:
                        r0 = move-exception
                        android.content.Context r1 = r4
                        com.startapp.android.publish.adsCommon.f.d r2 = com.startapp.android.publish.adsCommon.p033f.C1130d.EXCEPTION
                        java.lang.String r0 = r0.getMessage()
                        com.startapp.android.publish.adsCommon.c$a r3 = com.startapp.android.publish.adsCommon.C1103c.C1107a.this
                        java.lang.String r3 = r3.f1106b
                        java.lang.String r4 = "AdsCommonUtils.startTimeout - error after time elapsed"
                        com.startapp.android.publish.adsCommon.p033f.C1132f.m1527a(r1, r2, r4, r0, r3)
                    L_0x008a:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.adsCommon.C1103c.C1107a.C11081.run():void");
                }
            }, this.f1109e);
        }

        /* renamed from: b */
        private void m1413b(final Context context) {
            m1412b();
            try {
                Timer timer = new Timer();
                this.f1120p = timer;
                timer.schedule(new TimerTask() {
                    public void run() {
                        if (!C1107a.this.f1115k && !C1107a.this.f1107c) {
                            try {
                                C1107a.this.f1107c = true;
                                C1103c.m1370a(context);
                                if (!C1107a.this.f1110f || !MetaData.getInstance().isInAppBrowser()) {
                                    C1103c.m1374a(context, C1107a.this.f1105a, C1107a.this.f1106b);
                                } else {
                                    C1103c.m1396b(context, C1107a.this.f1105a, C1107a.this.f1106b);
                                }
                                if (C1107a.this.f1114j != null) {
                                    C1107a.this.f1114j.run();
                                }
                            } catch (Exception e) {
                                C1132f.m1527a(context, C1130d.EXCEPTION, "AdsCommonUtils.startLoadedTimer - error after time elapsed", e.getMessage(), C1107a.this.f1106b);
                            }
                        }
                    }
                }, this.f1117m);
            } catch (Exception e) {
                this.f1120p = null;
                C1270g.m2079a("AdsCommonUtils", 6, "startLoadedTimer", e);
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: b */
        public void m1412b() {
            Timer timer = this.f1120p;
            if (timer != null) {
                timer.cancel();
                this.f1120p = null;
            }
        }

        /* renamed from: a */
        private void m1411a(String str) {
            if (this.f1118n.get(str).floatValue() < 0.0f) {
                this.f1118n.put(str, Float.valueOf(((float) (System.currentTimeMillis() - this.f1119o)) / 1000.0f));
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public JSONArray mo14801a() {
            JSONArray jSONArray = new JSONArray();
            for (String next : this.f1118n.keySet()) {
                JSONObject jSONObject = new JSONObject();
                try {
                    m1411a(next);
                    jSONObject.put(C4899d.f4625fl, this.f1118n.get(next).toString());
                    jSONObject.put("url", next);
                    jSONArray.put(jSONObject);
                } catch (JSONException unused) {
                    C1270g.m2076a(6, "error puting url into json [" + next + "]");
                }
            }
            return jSONArray;
        }
    }

    /* renamed from: c */
    public static void m1398c(Context context, String str) {
        m1374a(context, str, (String) null);
    }

    /* renamed from: a */
    public static void m1374a(Context context, String str, String str2) {
        m1380a(context, str, str2, m1403d(str));
    }

    /* renamed from: a */
    public static void m1380a(Context context, String str, String str2, boolean z) {
        if (context != null) {
            int i = 76021760;
            if (C1098b.m1303a().mo14753G() || !(context instanceof Activity)) {
                i = 344457216;
            }
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.addFlags(i);
            boolean a = m1388a(context, intent);
            if (!a) {
                try {
                    if (Build.VERSION.SDK_INT >= 18 && MetaData.getInstance().getChromeCustomeTabsExternal() && m1405e(context)) {
                        m1402d(context, str);
                        return;
                    }
                } catch (Exception unused) {
                    m1375a(context, str, str2, i);
                    return;
                }
            }
            if (z && !a) {
                m1371a(context, intent, i);
            }
            context.startActivity(intent);
        }
    }

    /* renamed from: a */
    private static void m1371a(Context context, Intent intent, int i) {
        String[] strArr = {"com.android.chrome", "com.android.browser", "com.opera.mini.native", "org.mozilla.firefox", "com.opera.browser"};
        try {
            List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, i);
            if (queryIntentActivities != null && queryIntentActivities.size() > 1) {
                for (int i2 = 0; i2 < 5; i2++) {
                    String str = strArr[i2];
                    if (C1261c.m2032a(context, str, 0)) {
                        intent.setPackage(str);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            C1132f.m1527a(context, C1130d.EXCEPTION, "AdsCommonUtils.chooseDefaultBrowser", e.getMessage(), "");
        }
    }

    /* renamed from: a */
    private static void m1375a(Context context, String str, String str2, int i) {
        try {
            Intent parseUri = Intent.parseUri(str, i);
            m1388a(context, parseUri);
            if (!(context instanceof Activity)) {
                parseUri.addFlags(268435456);
            }
            context.startActivity(parseUri);
        } catch (Exception e) {
            C1132f.m1527a(context, C1130d.EXCEPTION, "Util.openUrlExternally - Couldn't start activity", e.getMessage(), str2);
            C1270g.m2076a(6, "Cannot find activity to handle url: [" + str + "]");
        }
    }

    /* renamed from: b */
    public static void m1396b(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            C1132f.m1527a(context, C1130d.EXCEPTION, "Util.OpenAsInAppBrowser - Couldn't start activity", "Parameter clickUrl is null", str2);
            C1270g.m2076a(6, "Cannot start activity, because url is null");
        } else if (m1397b(str) || !C1061i.m1194a(256)) {
            m1374a(context, str, str2);
        } else {
            try {
                if (Build.VERSION.SDK_INT >= 18 && MetaData.getInstance().getChromeCustomeTabsInternal() && m1405e(context)) {
                    m1402d(context, str);
                    return;
                }
            } catch (Exception e) {
                C1132f.m1527a(context, C1130d.EXCEPTION, "Util.OpenAsInAppBrowser - Couldn't openUrlChromeTabs", e.getMessage(), str2);
            }
            Intent intent = new Intent(context, OverlayActivity.class);
            if (Build.VERSION.SDK_INT >= 21) {
                intent.addFlags(524288);
            }
            if (Build.VERSION.SDK_INT >= 11) {
                intent.addFlags(32768);
            }
            if (!(context instanceof Activity)) {
                intent.addFlags(268435456);
            }
            intent.setData(Uri.parse(str));
            intent.putExtra("placement", AdPreferences.Placement.INAPP_BROWSER.getIndex());
            intent.putExtra("activityShouldLockOrientation", false);
            try {
                context.startActivity(intent);
            } catch (Exception e2) {
                C1132f.m1527a(context, C1130d.EXCEPTION, "Util.OpenAsInAppBrowser - Couldn't start activity", e2.getMessage(), str2);
                C1270g.m2076a(6, "Cannot find activity to handle url: [" + str + "]");
            }
        }
    }

    /* renamed from: d */
    private static void m1402d(Context context, String str) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        Bundle bundle = new Bundle();
        bundle.putBinder(CustomTabsIntent.EXTRA_SESSION, (IBinder) null);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /* renamed from: e */
    private static boolean m1405e(Context context) {
        return C1166k.m1606a(context, "chromeTabs", (Boolean) false).booleanValue();
    }

    /* renamed from: a */
    public static void m1385a(String str, String str2, String str3, Context context, C1117b bVar) {
        m1373a(context, str3, bVar, true);
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
        if (str2 != null) {
            try {
                JSONObject jSONObject = new JSONObject(str2);
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
            context.startActivity(launchIntentForPackage);
        } catch (Exception e2) {
            C1132f.m1527a(context, C1130d.EXCEPTION, "Util.handleCPEClick - Couldn't start activity", e2.getMessage(), m1368a(str3, (String) null));
            C1270g.m2076a(6, "Cannot find activity to handle url: [" + str3 + "]");
        }
    }

    /* renamed from: e */
    public static String m1404e(String str) {
        return m1368a(str, (String) null);
    }

    /* renamed from: a */
    public static String m1368a(String str, String str2) {
        if (str2 != null) {
            try {
                if (!str2.equals("")) {
                    str = str2;
                }
            } catch (Exception unused) {
                return "";
            }
        }
        String[] split = str.split("[?&]d=");
        if (split.length >= 2) {
            return split[1].split("[?&]")[0];
        }
        return "";
    }

    /* renamed from: a */
    public static boolean m1388a(Context context, Intent intent) {
        for (ResolveInfo next : context.getPackageManager().queryIntentActivities(intent, 0)) {
            if (next.activityInfo.packageName.equalsIgnoreCase(com.startapp.common.Constants.f1469a)) {
                intent.setComponent(new ComponentName(next.activityInfo.packageName, next.activityInfo.name));
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public static String m1366a() {
        return "&position=" + m1392b();
    }

    /* renamed from: b */
    public static String m1392b() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int i = 0;
        while (i < 8) {
            if (stackTrace[i].getMethodName().compareTo("doHome") == 0) {
                return "home";
            }
            if (stackTrace[i].getMethodName().compareTo("onBackPressed") != 0) {
                i++;
            } else if (!C1174m.m1649a().mo14999f() && !C1174m.m1677p()) {
                return AdType.INTERSTITIAL;
            } else {
                C1174m.m1649a().mo15008m();
                return "back";
            }
        }
        return AdType.INTERSTITIAL;
    }

    /* renamed from: a */
    public static String[] m1390a(C1136g gVar) {
        if (gVar instanceof C1118e) {
            return ((C1118e) gVar).mo14849l();
        }
        return gVar instanceof C1148h ? m1391a(((C1148h) gVar).mo14937d()) : new String[0];
    }

    /* renamed from: a */
    public static String[] m1391a(List<AdDetails> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (AdDetails trackingUrl : list) {
                arrayList.add(trackingUrl.getTrackingUrl());
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    /* renamed from: a */
    public static boolean m1389a(Context context, AdPreferences.Placement placement) {
        C1270g.m2078a("AdsCommonUtils", 6, "forceExternal - check -placement is : " + placement);
        if (placement.equals(AdPreferences.Placement.INAPP_SPLASH) || !C1098b.m1303a().mo14759M()) {
            return false;
        }
        return m1406f(context);
    }

    /* renamed from: f */
    private static boolean m1406f(Context context) {
        if (C1254b.m1990a().mo15441a(context).mo15454b() || !m1386a(C1166k.m1609a(context, "shared_prefs_CookieFeatureTS", (Long) 0L).longValue(), System.currentTimeMillis())) {
            return false;
        }
        C1270g.m2078a("AdsCommonUtils", 6, "forceExternal - check - true ");
        return true;
    }

    /* renamed from: a */
    private static boolean m1386a(long j, long j2) {
        return j == 0 || j + (((long) C1098b.m1303a().mo14758L()) * DateUtils.MILLIS_PER_DAY) <= j2;
    }
}
