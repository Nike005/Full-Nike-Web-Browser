package com.startapp.android.publish.adsCommon.Utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Base64;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.appnext.core.C4924Ad;
import com.startapp.android.publish.GeneratedConstants;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.C1166k;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.C1301e;
import com.startapp.common.p043a.C1261c;
import com.startapp.common.p043a.C1270g;
import com.startapp.common.p046c.C1295b;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.WeakHashMap;
import org.htmlcleaner.CleanerProperties;

/* renamed from: com.startapp.android.publish.adsCommon.Utils.i */
/* compiled from: StartAppSDK */
public class C1061i {

    /* renamed from: a */
    protected static int f991a;

    /* renamed from: b */
    private static Map<Activity, Integer> f992b = new WeakHashMap();

    /* renamed from: c */
    private static boolean f993c = false;

    /* renamed from: com.startapp.android.publish.adsCommon.Utils.i$a */
    /* compiled from: StartAppSDK */
    public interface C1066a {
        /* renamed from: a */
        void mo14645a();

        /* renamed from: a */
        void mo14646a(String str);
    }

    /* renamed from: d */
    public static String m1204d() {
        return GeneratedConstants.INAPP_PACKAGING;
    }

    /* renamed from: a */
    public static boolean m1193a() {
        return new BigInteger(AdsConstants.f962i, 10).intValue() == 0;
    }

    /* renamed from: b */
    public static String m1199b() {
        C1270g.m2076a(3, "SDK version: [" + GeneratedConstants.INAPP_VERSION + "]");
        return GeneratedConstants.INAPP_VERSION;
    }

    /* renamed from: c */
    public static String m1202c() {
        C1270g.m2076a(3, "SDK Flavor: [" + GeneratedConstants.INAPP_FLAVOR + "]");
        return GeneratedConstants.INAPP_FLAVOR;
    }

    /* renamed from: a */
    public static boolean m1194a(long j) {
        String str = AdsConstants.f962i;
        if (str.equals("${flavor}") || (j & new BigInteger(str, 2).longValue()) != 0) {
            return true;
        }
        return false;
    }

    /* renamed from: e */
    public static boolean m1206e() {
        return m1194a(2) || m1194a(16) || m1194a(32) || m1194a(4);
    }

    /* renamed from: a */
    public static String m1178a(Double d) {
        if (d == null) {
            return null;
        }
        return String.format(Locale.US, "%.2f", new Object[]{d});
    }

    /* renamed from: a */
    public static boolean m1195a(Context context) {
        if (AdsConstants.OVERRIDE_HOST != null || AdsConstants.OVERRIDE_NETWORK.booleanValue()) {
            return true;
        }
        if (C1261c.m2031a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            try {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                    return false;
                }
                return true;
            } catch (Exception e) {
                C1132f.m1527a(context, C1130d.EXCEPTION, "Util.isNetworkAvailable - system service failed", e.getMessage(), "");
            }
        }
        return false;
    }

    /* renamed from: a */
    public static void m1187a(SharedPreferences.Editor editor) {
        C1261c.m2022a(editor);
    }

    /* renamed from: a */
    public static String m1179a(String str, String str2, String str3) {
        int indexOf;
        int indexOf2;
        if (str == null || str2 == null || str3 == null || (indexOf = str.indexOf(str2)) == -1 || (indexOf2 = str.indexOf(str3, str2.length() + indexOf)) == -1) {
            return null;
        }
        return str.substring(indexOf + str2.length(), indexOf2);
    }

    /* renamed from: b */
    public static String m1200b(Context context) {
        if (context.getResources().getConfiguration().orientation == 2) {
            return C4924Ad.ORIENTATION_LANDSCAPE;
        }
        return context.getResources().getConfiguration().orientation == 1 ? C4924Ad.ORIENTATION_PORTRAIT : "undefined";
    }

    /* renamed from: a */
    public static int m1172a(Activity activity, int i, boolean z) {
        if (z) {
            if (!f992b.containsKey(activity)) {
                f992b.put(activity, Integer.valueOf(activity.getRequestedOrientation()));
            }
            if (i == activity.getResources().getConfiguration().orientation) {
                return C1261c.m2013a(activity, i, false);
            }
            return C1261c.m2013a(activity, i, true);
        } else if (!f992b.containsKey(activity)) {
            return -1;
        } else {
            int intValue = f992b.get(activity).intValue();
            C1261c.m2021a(activity, intValue);
            f992b.remove(activity);
            return intValue;
        }
    }

    /* renamed from: a */
    public static void m1182a(Activity activity, boolean z) {
        m1172a(activity, activity.getResources().getConfiguration().orientation, z);
    }

    /* renamed from: a */
    private static List<Field> m1180a(Class cls) {
        return m1181a((List<Field>) new LinkedList(), (Class<?>) cls);
    }

    /* renamed from: a */
    private static List<Field> m1181a(List<Field> list, Class<?> cls) {
        list.addAll(Arrays.asList(cls.getDeclaredFields()));
        if (cls.getSuperclass() != null) {
            m1181a(list, (Class<?>) cls.getSuperclass());
        }
        return list;
    }

    /* renamed from: a */
    public static <T> boolean m1198a(T t, T t2) {
        Object obj;
        boolean z = false;
        try {
            for (Field next : m1180a((Class) t2.getClass())) {
                int modifiers = next.getModifiers();
                if (!Modifier.isTransient(modifiers)) {
                    if (!Modifier.isStatic(modifiers)) {
                        next.setAccessible(true);
                        if (next.get(t) == null && (obj = next.get(t2)) != null) {
                            next.set(t, obj);
                            z = true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            C1270g.m2076a(3, "Util.mergeDefaultValues failed: " + e.getMessage());
        }
        return z;
    }

    /* renamed from: a */
    public static void m1184a(Context context, AdPreferences adPreferences) {
        String a = C1166k.m1610a(context, "shared_prefs_devId", (String) null);
        String a2 = C1166k.m1610a(context, "shared_prefs_appId", (String) null);
        if (adPreferences.getPublisherId() == null) {
            adPreferences.setPublisherId(a);
        }
        if (adPreferences.getProductId() == null) {
            adPreferences.setProductId(a2);
        }
        if (adPreferences.getProductId() == null && !f993c) {
            f993c = true;
            Log.e("StartApp", "Integration Error - App ID is missing");
        }
    }

    /* renamed from: a */
    public static void m1186a(Context context, String str, String str2) {
        if (str != null) {
            C1166k.m1617b(context, "shared_prefs_devId", str.trim());
        } else {
            C1166k.m1617b(context, "shared_prefs_devId", (String) null);
        }
        C1166k.m1617b(context, "shared_prefs_appId", str2.trim());
    }

    /* renamed from: a */
    public static void m1185a(Context context, String str, final C1066a aVar) {
        if (CleanerProperties.BOOL_ATT_TRUE.equals(m1179a(str, "@doNotRender@", "@doNotRender@"))) {
            aVar.mo14645a();
            return;
        }
        try {
            final WebView webView = new WebView(context);
            final Handler handler = new Handler();
            if (AdsConstants.OVERRIDE_NETWORK.booleanValue()) {
                f991a = 25000;
                webView.getSettings().setBlockNetworkImage(false);
                webView.getSettings().setLoadsImagesAutomatically(true);
                webView.getSettings().setJavaScriptEnabled(true);
            } else {
                f991a = 0;
            }
            webView.setWebChromeClient(new WebChromeClient());
            webView.setWebViewClient(new WebViewClient() {
                public void onPageFinished(WebView webView, String str) {
                    super.onPageFinished(webView, str);
                    C1270g.m2078a("StartAppWall.Util", 4, "onPageFinished url=[" + str + "]");
                    handler.removeCallbacksAndMessages((Object) null);
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            webView.destroy();
                            C1270g.m2078a("StartAppWall.Util", 4, "webview destroyed");
                            aVar.mo14645a();
                        }
                    }, (long) C1061i.f991a);
                }

                public void onReceivedError(final WebView webView, int i, final String str, String str2) {
                    super.onReceivedError(webView, i, str, str2);
                    C1270g.m2078a("StartAppWall.Util", 6, "onReceivedError failingUrl=[" + str2 + "], description=[" + str + "]");
                    handler.removeCallbacksAndMessages((Object) null);
                    handler.post(new Runnable() {
                        public void run() {
                            webView.destroy();
                            aVar.mo14646a(str);
                        }
                    });
                }

                public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                    C1270g.m2078a("StartAppWall.Util", 4, "shouldOverrideUrlLoading url=[" + str + "]");
                    return super.shouldOverrideUrlLoading(webView, str);
                }
            });
            m1183a(context, webView, str);
            C1270g.m2078a("StartAppWall.Util", 4, "Data loaded to webview");
            handler.postDelayed(new Runnable() {
                public void run() {
                    webView.destroy();
                    aVar.mo14645a();
                    C1270g.m2078a("StartAppWall.Util", 4, "webview destroyed pos 2");
                }
            }, 25000);
        } catch (Exception e) {
            C1132f.m1527a(context, C1130d.EXCEPTION, "Util.loadHtmlToCacheWebView - webview failed", e.getMessage(), "");
            aVar.mo14646a("WebView instantiation Error");
        }
    }

    /* renamed from: a */
    public static void m1183a(Context context, WebView webView, String str) {
        try {
            webView.loadDataWithBaseURL(MetaData.getInstance().getHostForWebview(), str, "text/html", "utf-8", (String) null);
        } catch (Exception e) {
            if (context != null) {
                C1132f.m1527a(context, C1130d.EXCEPTION, "Util.loadDataToWebview failed", e.getMessage(), "");
            }
            C1270g.m2076a(6, "StartAppWall.UtilError while encoding html");
        }
    }

    /* renamed from: c */
    public static String m1203c(Context context) {
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
            if (resolveActivity == null || resolveActivity.activityInfo == null) {
                return "";
            }
            String str = resolveActivity.activityInfo.packageName;
            return str != null ? str.toLowerCase() : str;
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: a */
    public static boolean m1197a(AdPreferences adPreferences, String str) {
        Object a = m1175a((Class) adPreferences.getClass(), str, (Object) adPreferences);
        if (a == null || !(a instanceof Boolean)) {
            return false;
        }
        return ((Boolean) a).booleanValue();
    }

    /* renamed from: b */
    public static String m1201b(AdPreferences adPreferences, String str) {
        Object a = m1175a((Class) adPreferences.getClass(), str, (Object) adPreferences);
        if (a == null || !(a instanceof String)) {
            return null;
        }
        return (String) a;
    }

    /* renamed from: a */
    public static void m1190a(AdPreferences adPreferences, String str, boolean z) {
        m1191a((Class) adPreferences.getClass(), str, (Object) adPreferences, (Object) Boolean.valueOf(z));
    }

    /* renamed from: a */
    public static Object m1175a(Class cls, String str, Object obj) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.get(obj);
        } catch (NoSuchFieldException e) {
            C1270g.m2078a("StartAppWall.Util", 6, e.getLocalizedMessage());
            return null;
        } catch (IllegalArgumentException e2) {
            C1270g.m2078a("StartAppWall.Util", 6, e2.getLocalizedMessage());
            return null;
        } catch (IllegalAccessException e3) {
            C1270g.m2078a("StartAppWall.Util", 6, e3.getLocalizedMessage());
            return null;
        }
    }

    /* renamed from: a */
    public static void m1191a(Class cls, String str, Object obj, Object obj2) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(obj, obj2);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        }
    }

    /* renamed from: d */
    public static String m1205d(Context context) {
        return context.getPackageManager().getInstallerPackageName(context.getPackageName());
    }

    /* renamed from: a */
    public static void m1188a(WebView webView, String str, Object... objArr) {
        m1189a(webView, true, str, objArr);
    }

    /* renamed from: a */
    public static void m1189a(WebView webView, boolean z, String str, Object... objArr) {
        if (webView != null) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append("(");
                if (objArr != null) {
                    for (int i = 0; i < objArr.length; i++) {
                        if (!z || !(objArr[i] instanceof String)) {
                            sb.append(objArr[i]);
                        } else {
                            sb.append("\"");
                            sb.append(objArr[i]);
                            sb.append("\"");
                        }
                        if (i < objArr.length - 1) {
                            sb.append(",");
                        }
                    }
                }
                sb.append(")");
                C1270g.m2078a("StartAppWall.Util", 3, "runJavascript: " + sb.toString());
                webView.loadUrl("javascript:" + sb.toString());
            } catch (Exception e) {
                C1270g.m2078a("StartAppWall.Util", 6, "runJavascript Exception: " + e.getMessage());
            }
        }
    }

    /* renamed from: a */
    public static Class<?> m1174a(Context context, Class<? extends Activity> cls, Class<? extends Activity> cls2) {
        if (m1196a(context, cls) || !m1196a(context, cls2)) {
            return cls;
        }
        Log.w("StartAppWall.Util", "Expected activity " + cls.getName() + " is missing from AndroidManifest.xml");
        return cls2;
    }

    /* renamed from: a */
    public static boolean m1196a(Context context, Class<? extends Activity> cls) {
        try {
            for (ActivityInfo activityInfo : context.getPackageManager().getPackageInfo(context.getPackageName(), 1).activities) {
                if (activityInfo.name.equals(cls.getName())) {
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    /* renamed from: e */
    public static boolean m1207e(Context context) {
        for (ActivityManager.RunningAppProcessInfo next : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (next.importance == 100 && next.processName.equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: f */
    public static boolean m1208f(Context context) {
        boolean z = false;
        try {
            ActivityInfo[] activityInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 1).activities;
            boolean z2 = false;
            int i = 0;
            while (!z2) {
                try {
                    if (i >= activityInfoArr.length) {
                        return z2;
                    }
                    int i2 = i + 1;
                    ActivityInfo activityInfo = activityInfoArr[i];
                    if (activityInfo.name.equals("com.startapp.android.publish.AppWallActivity") || activityInfo.name.equals("com.startapp.android.publish.adsCommon.OverlayActivity") || activityInfo.name.equals("com.startapp.android.publish.FullScreenActivity")) {
                        z2 = (activityInfo.flags & 512) == 0;
                    }
                    i = i2;
                } catch (PackageManager.NameNotFoundException | Exception unused) {
                    z = z2;
                    return z;
                }
            }
            return z2;
        } catch (PackageManager.NameNotFoundException | Exception unused2) {
            return z;
        }
    }

    /* renamed from: a */
    public String mo14638a(String str, Context context) {
        try {
            return new C1261c().mo15460a(str, context);
        } catch (Exception e) {
            C1132f.m1527a(context, C1130d.EXCEPTION, "Util.getApkHash - system service failed", e.getMessage(), "");
            return null;
        }
    }

    /* renamed from: a */
    public static long m1173a(File file, long j) {
        return C1261c.m2015a(file, j);
    }

    /* renamed from: a */
    public static String m1177a(Context context, int i) {
        try {
            Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), i);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            decodeResource.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: a */
    public static <T> T m1176a(String str, Class<T> cls) {
        T a = C1295b.m2179a(str, cls);
        if (a != null) {
            return a;
        }
        throw new C1301e();
    }

    /* renamed from: a */
    public static void m1192a(Object obj, long j) {
        new Handler(Looper.getMainLooper()).postAtTime((Runnable) null, obj, SystemClock.uptimeMillis() + j);
    }
}
