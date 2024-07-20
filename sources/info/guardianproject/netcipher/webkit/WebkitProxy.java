package info.guardianproject.netcipher.webkit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.util.ArrayMap;
import android.util.Log;
import android.webkit.WebView;
import com.mopub.common.Constants;
import info.guardianproject.netcipher.proxy.OrbotHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import org.apache.http.HttpHost;

public class WebkitProxy {
    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 8118;
    private static final int DEFAULT_SOCKS_PORT = 9050;
    private static final int REQUEST_CODE = 0;
    private static final String TAG = "OrbotHelpher";

    public static boolean setProxy(String str, Context context, WebView webView, String str2, int i) throws Exception {
        setSystemProperties(str2, i);
        if (Build.VERSION.SDK_INT < 13) {
            setProxyUpToHC(webView, str2, i);
        } else if (Build.VERSION.SDK_INT < 19) {
            return setWebkitProxyICS(context, str2, i);
        } else {
            if (Build.VERSION.SDK_INT < 20) {
                boolean kitKatProxy = setKitKatProxy(str, context, str2, i);
                if (!kitKatProxy) {
                    return setWebkitProxyICS(context, str2, i);
                }
                return kitKatProxy;
            } else if (Build.VERSION.SDK_INT >= 21) {
                return setWebkitProxyLollipop(context, str2, i);
            }
        }
        return false;
    }

    private static void setSystemProperties(String str, int i) {
        System.setProperty("proxyHost", str);
        System.setProperty("proxyPort", Integer.toString(i));
        System.setProperty("http.proxyHost", str);
        System.setProperty("http.proxyPort", Integer.toString(i));
        System.setProperty("https.proxyHost", str);
        System.setProperty("https.proxyPort", Integer.toString(i));
        System.setProperty("socks.proxyHost", str);
        System.setProperty("socks.proxyPort", Integer.toString(DEFAULT_SOCKS_PORT));
        System.setProperty("socksProxyHost", str);
        System.setProperty("socksProxyPort", Integer.toString(DEFAULT_SOCKS_PORT));
    }

    private static void resetSystemProperties() {
        System.setProperty("proxyHost", "");
        System.setProperty("proxyPort", "");
        System.setProperty("http.proxyHost", "");
        System.setProperty("http.proxyPort", "");
        System.setProperty("https.proxyHost", "");
        System.setProperty("https.proxyPort", "");
        System.setProperty("socks.proxyHost", "");
        System.setProperty("socks.proxyPort", Integer.toString(DEFAULT_SOCKS_PORT));
        System.setProperty("socksProxyHost", "");
        System.setProperty("socksProxyPort", Integer.toString(DEFAULT_SOCKS_PORT));
    }

    private static boolean setWebkitProxyGingerbread(Context context, String str, int i) throws Exception {
        Object requestQueue = getRequestQueue(context);
        if (requestQueue == null) {
            return false;
        }
        setDeclaredField(requestQueue, "mProxyHost", new HttpHost(str, i, Constants.HTTP));
        return true;
    }

    private static boolean setProxyUpToHC(WebView webView, String str, int i) {
        Log.d(TAG, "Setting proxy with <= 3.2 API.");
        HttpHost httpHost = new HttpHost(str, i);
        try {
            Class<?> cls = Class.forName("android.webkit.Network");
            if (cls == null) {
                Log.e(TAG, "failed to get class for android.webkit.Network");
                return false;
            }
            Method method = cls.getMethod("getInstance", new Class[]{Context.class});
            if (method == null) {
                Log.e(TAG, "failed to get getInstance method");
            }
            Object invoke = method.invoke(cls, new Object[]{webView.getContext()});
            if (invoke == null) {
                Log.e(TAG, "error getting network: network is null");
                return false;
            }
            try {
                Object fieldValueSafely = getFieldValueSafely(cls.getDeclaredField("mRequestQueue"), invoke);
                if (fieldValueSafely == null) {
                    Log.e(TAG, "Request queue is null");
                    return false;
                }
                try {
                    Field declaredField = Class.forName("android.net.http.RequestQueue").getDeclaredField("mProxyHost");
                    boolean isAccessible = declaredField.isAccessible();
                    try {
                        declaredField.setAccessible(true);
                        declaredField.set(fieldValueSafely, httpHost);
                    } catch (Exception unused) {
                        Log.e(TAG, "error setting proxy host");
                    } catch (Throwable th) {
                        declaredField.setAccessible(isAccessible);
                        throw th;
                    }
                    declaredField.setAccessible(isAccessible);
                    Log.d(TAG, "Setting proxy with <= 3.2 API successful!");
                    return true;
                } catch (Exception unused2) {
                    Log.e(TAG, "error getting proxy host field");
                    return false;
                }
            } catch (Exception unused3) {
                Log.e(TAG, "error getting field value");
                return false;
            }
        } catch (Exception e) {
            Log.e(TAG, "error getting network: " + e);
            return false;
        }
    }

    private static Object getFieldValueSafely(Field field, Object obj) throws IllegalArgumentException, IllegalAccessException {
        boolean isAccessible = field.isAccessible();
        field.setAccessible(true);
        Object obj2 = field.get(obj);
        field.setAccessible(isAccessible);
        return obj2;
    }

    private static boolean setWebkitProxyICS(Context context, String str, int i) {
        try {
            Class<?> cls = Class.forName("android.webkit.WebViewCore");
            Class<?> cls2 = Class.forName("android.net.ProxyProperties");
            if (!(cls == null || cls2 == null)) {
                Method declaredMethod = cls.getDeclaredMethod("sendStaticMessage", new Class[]{Integer.TYPE, Object.class});
                Constructor<?> constructor = cls2.getConstructor(new Class[]{String.class, Integer.TYPE, String.class});
                if (!(declaredMethod == null || constructor == null)) {
                    declaredMethod.setAccessible(true);
                    constructor.setAccessible(true);
                    declaredMethod.invoke((Object) null, new Object[]{193, constructor.newInstance(new Object[]{str, Integer.valueOf(i), null})});
                    return true;
                }
            }
        } catch (Exception e) {
            Log.e("ProxySettings", "Exception setting WebKit proxy through android.net.ProxyProperties: " + e.toString());
        } catch (Error e2) {
            Log.e("ProxySettings", "Exception setting WebKit proxy through android.webkit.Network: " + e2.toString());
        }
        return false;
    }

    public static boolean resetKitKatProxy(String str, Context context) {
        return setKitKatProxy(str, context, (String) null, 0);
    }

    private static boolean setKitKatProxy(String str, Context context, String str2, int i) {
        if (str2 != null) {
            System.setProperty("http.proxyHost", str2);
            System.setProperty("http.proxyPort", Integer.toString(i));
            System.setProperty("https.proxyHost", str2);
            System.setProperty("https.proxyPort", Integer.toString(i));
        }
        try {
            Field field = Class.forName(str).getField("mLoadedApk");
            field.setAccessible(true);
            Object obj = field.get(context);
            Field declaredField = Class.forName("android.app.LoadedApk").getDeclaredField("mReceivers");
            declaredField.setAccessible(true);
            for (ArrayMap keySet : ((ArrayMap) declaredField.get(obj)).values()) {
                for (Object next : keySet.keySet()) {
                    Class<?> cls = next.getClass();
                    if (cls.getName().contains("ProxyChangeListener")) {
                        Method declaredMethod = cls.getDeclaredMethod("onReceive", new Class[]{Context.class, Intent.class});
                        Intent intent = new Intent("android.intent.action.PROXY_CHANGE");
                        if (str2 != null) {
                            Constructor<?> constructor = Class.forName("android.net.ProxyProperties").getConstructor(new Class[]{String.class, Integer.TYPE, String.class});
                            constructor.setAccessible(true);
                            intent.putExtra("proxy", (Parcelable) constructor.newInstance(new Object[]{str2, Integer.valueOf(i), null}));
                        }
                        declaredMethod.invoke(next, new Object[]{context, intent});
                    }
                }
            }
            return true;
        } catch (ClassNotFoundException e) {
            StringWriter stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));
            String stringWriter2 = stringWriter.toString();
            Log.v(TAG, e.getMessage());
            Log.v(TAG, stringWriter2);
            return false;
        } catch (NoSuchFieldException e2) {
            StringWriter stringWriter3 = new StringWriter();
            e2.printStackTrace(new PrintWriter(stringWriter3));
            String stringWriter4 = stringWriter3.toString();
            Log.v(TAG, e2.getMessage());
            Log.v(TAG, stringWriter4);
            return false;
        } catch (IllegalAccessException e3) {
            StringWriter stringWriter5 = new StringWriter();
            e3.printStackTrace(new PrintWriter(stringWriter5));
            String stringWriter6 = stringWriter5.toString();
            Log.v(TAG, e3.getMessage());
            Log.v(TAG, stringWriter6);
            return false;
        } catch (IllegalArgumentException e4) {
            StringWriter stringWriter7 = new StringWriter();
            e4.printStackTrace(new PrintWriter(stringWriter7));
            String stringWriter8 = stringWriter7.toString();
            Log.v(TAG, e4.getMessage());
            Log.v(TAG, stringWriter8);
            return false;
        } catch (NoSuchMethodException e5) {
            StringWriter stringWriter9 = new StringWriter();
            e5.printStackTrace(new PrintWriter(stringWriter9));
            String stringWriter10 = stringWriter9.toString();
            Log.v(TAG, e5.getMessage());
            Log.v(TAG, stringWriter10);
            return false;
        } catch (InvocationTargetException e6) {
            StringWriter stringWriter11 = new StringWriter();
            e6.printStackTrace(new PrintWriter(stringWriter11));
            String stringWriter12 = stringWriter11.toString();
            Log.v(TAG, e6.getMessage());
            Log.v(TAG, stringWriter12);
            return false;
        } catch (InstantiationException e7) {
            StringWriter stringWriter13 = new StringWriter();
            e7.printStackTrace(new PrintWriter(stringWriter13));
            String stringWriter14 = stringWriter13.toString();
            Log.v(TAG, e7.getMessage());
            Log.v(TAG, stringWriter14);
            return false;
        }
    }

    public static boolean resetLollipopProxy(String str, Context context) {
        return setWebkitProxyLollipop(context, (String) null, 0);
    }

    private static boolean setWebkitProxyLollipop(Context context, String str, int i) {
        System.setProperty("http.proxyHost", str);
        System.setProperty("http.proxyPort", Integer.toString(i));
        System.setProperty("https.proxyHost", str);
        System.setProperty("https.proxyPort", Integer.toString(i));
        try {
            Field declaredField = Class.forName("android.app.Application").getDeclaredField("mLoadedApk");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(context);
            Field declaredField2 = Class.forName("android.app.LoadedApk").getDeclaredField("mReceivers");
            declaredField2.setAccessible(true);
            for (ArrayMap keySet : ((ArrayMap) declaredField2.get(obj)).values()) {
                for (Object next : keySet.keySet()) {
                    Class<?> cls = next.getClass();
                    if (cls.getName().contains("ProxyChangeListener")) {
                        cls.getDeclaredMethod("onReceive", new Class[]{Context.class, Intent.class}).invoke(next, new Object[]{context, new Intent("android.intent.action.PROXY_CHANGE")});
                    }
                }
            }
            return true;
        } catch (ClassNotFoundException e) {
            Log.d("ProxySettings", "Exception setting WebKit proxy on Lollipop through ProxyChangeListener: " + e.toString());
            return false;
        } catch (NoSuchFieldException e2) {
            Log.d("ProxySettings", "Exception setting WebKit proxy on Lollipop through ProxyChangeListener: " + e2.toString());
            return false;
        } catch (IllegalAccessException e3) {
            Log.d("ProxySettings", "Exception setting WebKit proxy on Lollipop through ProxyChangeListener: " + e3.toString());
            return false;
        } catch (NoSuchMethodException e4) {
            Log.d("ProxySettings", "Exception setting WebKit proxy on Lollipop through ProxyChangeListener: " + e4.toString());
            return false;
        } catch (InvocationTargetException e5) {
            Log.d("ProxySettings", "Exception setting WebKit proxy on Lollipop through ProxyChangeListener: " + e5.toString());
            return false;
        }
    }

    private static boolean sendProxyChangedIntent(Context context, String str, int i) {
        Constructor<?> constructor;
        try {
            Class<?> cls = Class.forName("android.net.ProxyProperties");
            if (!(cls == null || (constructor = cls.getConstructor(new Class[]{String.class, Integer.TYPE, String.class})) == null)) {
                constructor.setAccessible(true);
                Object newInstance = constructor.newInstance(new Object[]{str, Integer.valueOf(i), null});
                Intent intent = new Intent("android.intent.action.PROXY_CHANGE");
                intent.putExtra("proxy", (Parcelable) newInstance);
                context.sendBroadcast(intent);
            }
        } catch (Exception e) {
            Log.e("ProxySettings", "Exception sending Intent ", e);
        } catch (Error e2) {
            Log.e("ProxySettings", "Exception sending Intent ", e2);
        }
        return false;
    }

    public static void resetProxy(String str, Context context) throws Exception {
        resetSystemProperties();
        if (Build.VERSION.SDK_INT < 14) {
            resetProxyForGingerBread(context);
        } else if (Build.VERSION.SDK_INT < 19) {
            resetProxyForICS();
        } else {
            resetKitKatProxy(str, context);
        }
    }

    private static void resetProxyForICS() throws Exception {
        Method declaredMethod;
        try {
            Class<?> cls = Class.forName("android.webkit.WebViewCore");
            Class<?> cls2 = Class.forName("android.net.ProxyProperties");
            if (cls != null && cls2 != null && (declaredMethod = cls.getDeclaredMethod("sendStaticMessage", new Class[]{Integer.TYPE, Object.class})) != null) {
                declaredMethod.setAccessible(true);
                declaredMethod.invoke((Object) null, new Object[]{193, null});
            }
        } catch (Exception e) {
            Log.e("ProxySettings", "Exception setting WebKit proxy through android.net.ProxyProperties: " + e.toString());
            throw e;
        } catch (Error e2) {
            Log.e("ProxySettings", "Exception setting WebKit proxy through android.webkit.Network: " + e2.toString());
            throw e2;
        }
    }

    private static void resetProxyForGingerBread(Context context) throws Exception {
        Object requestQueue = getRequestQueue(context);
        if (requestQueue != null) {
            setDeclaredField(requestQueue, "mProxyHost", (Object) null);
        }
    }

    public static Object getRequestQueue(Context context) throws Exception {
        Object invokeMethod;
        Class<?> cls = Class.forName("android.webkit.Network");
        if (cls == null || (invokeMethod = invokeMethod(cls, "getInstance", new Object[]{context}, Context.class)) == null) {
            return null;
        }
        return getDeclaredField(invokeMethod, "mRequestQueue");
    }

    private static Object getDeclaredField(Object obj, String str) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field declaredField = obj.getClass().getDeclaredField(str);
        declaredField.setAccessible(true);
        return declaredField.get(obj);
    }

    private static void setDeclaredField(Object obj, String str, Object obj2) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field declaredField = obj.getClass().getDeclaredField(str);
        declaredField.setAccessible(true);
        declaredField.set(obj, obj2);
    }

    private static Object invokeMethod(Object obj, String str, Object[] objArr, Class... clsArr) throws Exception {
        Class<?> cls = obj instanceof Class ? (Class) obj : obj.getClass();
        if (clsArr != null) {
            return cls.getMethod(str, clsArr).invoke(obj, objArr);
        }
        return cls.getMethod(str, new Class[0]).invoke(obj, new Object[0]);
    }

    public static Socket getSocket(Context context, String str, int i) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(str, i), 10000);
        return socket;
    }

    public static Socket getSocket(Context context) throws IOException {
        return getSocket(context, DEFAULT_HOST, DEFAULT_SOCKS_PORT);
    }

    public static AlertDialog initOrbot(Activity activity, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4, CharSequence charSequence5) {
        Intent intent = new Intent(OrbotHelper.ACTION_START_TOR);
        intent.addCategory("android.intent.category.DEFAULT");
        try {
            activity.startActivityForResult(intent, 0);
            return null;
        } catch (ActivityNotFoundException unused) {
            return showDownloadDialog(activity, charSequence, charSequence2, charSequence3, charSequence4);
        }
    }

    private static AlertDialog showDownloadDialog(final Activity activity, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(charSequence);
        builder.setMessage(charSequence2);
        builder.setPositiveButton(charSequence3, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pname:org.torproject.android")));
            }
        });
        builder.setNegativeButton(charSequence4, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return builder.show();
    }
}
