package info.guardianproject.netcipher.proxy;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public class OrbotHelper implements ProxyHelper {
    public static final String ACTION_REQUEST_HS = "org.torproject.android.REQUEST_HS_PORT";
    public static final String ACTION_START = "org.torproject.android.intent.action.START";
    public static final String ACTION_START_TOR = "org.torproject.android.START_TOR";
    public static final String ACTION_STATUS = "org.torproject.android.intent.action.STATUS";
    public static final String EXTRA_PACKAGE_NAME = "org.torproject.android.intent.extra.PACKAGE_NAME";
    public static final String EXTRA_PROXY_PORT_HTTP = "org.torproject.android.intent.extra.HTTP_PROXY_PORT";
    public static final String EXTRA_PROXY_PORT_SOCKS = "org.torproject.android.intent.extra.SOCKS_PROXY_PORT";
    public static final String EXTRA_STATUS = "org.torproject.android.intent.extra.STATUS";
    public static final int HS_REQUEST_CODE = 9999;
    private static volatile OrbotHelper INSTANCE = null;
    public static final String ORBOT_FDROID_URI = "https://f-droid.org/repository/browse/?fdid=org.torproject.android";
    public static final String ORBOT_MARKET_URI = "market://details?id=org.torproject.android";
    public static final String ORBOT_PACKAGE_NAME = "org.torproject.android";
    public static final String ORBOT_PLAY_URI = "https://play.google.com/store/apps/details?id=org.torproject.android";
    private static final int REQUEST_CODE_STATUS = 100;
    public static final int START_TOR_RESULT = 37428;
    public static final String STATUS_OFF = "OFF";
    public static final String STATUS_ON = "ON";
    public static final String STATUS_STARTING = "STARTING";
    public static final String STATUS_STARTS_DISABLED = "STARTS_DISABLED";
    public static final String STATUS_STOPPING = "STOPPING";
    /* access modifiers changed from: private */
    public final Context ctxt;
    /* access modifiers changed from: private */
    public final Handler handler;
    /* access modifiers changed from: private */
    public Set<InstallCallback> installCallbacks = newSetFromMap(new WeakHashMap());
    private long installTimeoutMs = 60000;
    /* access modifiers changed from: private */
    public boolean isInstalled = false;
    /* access modifiers changed from: private */
    public Intent lastStatusIntent = null;
    /* access modifiers changed from: private */
    public Runnable onInstallTimeout = new Runnable() {
        public void run() {
            OrbotHelper.this.ctxt.unregisterReceiver(OrbotHelper.this.orbotInstallReceiver);
            for (InstallCallback onInstallTimeout : OrbotHelper.this.installCallbacks) {
                onInstallTimeout.onInstallTimeout();
            }
        }
    };
    /* access modifiers changed from: private */
    public Runnable onStatusTimeout = new Runnable() {
        public void run() {
            OrbotHelper.this.ctxt.unregisterReceiver(OrbotHelper.this.orbotStatusReceiver);
            for (StatusCallback onStatusTimeout : OrbotHelper.this.statusCallbacks) {
                onStatusTimeout.onStatusTimeout();
            }
        }
    };
    /* access modifiers changed from: private */
    public BroadcastReceiver orbotInstallReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), "android.intent.action.PACKAGE_ADDED") && OrbotHelper.ORBOT_PACKAGE_NAME.equals(intent.getData().getEncodedSchemeSpecificPart())) {
                boolean unused = OrbotHelper.this.isInstalled = true;
                OrbotHelper.this.handler.removeCallbacks(OrbotHelper.this.onInstallTimeout);
                context.unregisterReceiver(OrbotHelper.this.orbotInstallReceiver);
                for (InstallCallback onInstalled : OrbotHelper.this.installCallbacks) {
                    onInstalled.onInstalled();
                }
                OrbotHelper.this.init();
            }
        }
    };
    /* access modifiers changed from: private */
    public BroadcastReceiver orbotStatusReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), OrbotHelper.ACTION_STATUS)) {
                String stringExtra = intent.getStringExtra(OrbotHelper.EXTRA_STATUS);
                if (stringExtra.equals("ON")) {
                    Intent unused = OrbotHelper.this.lastStatusIntent = intent;
                    OrbotHelper.this.handler.removeCallbacks(OrbotHelper.this.onStatusTimeout);
                    for (StatusCallback onEnabled : OrbotHelper.this.statusCallbacks) {
                        onEnabled.onEnabled(intent);
                    }
                } else if (stringExtra.equals("OFF")) {
                    for (StatusCallback onDisabled : OrbotHelper.this.statusCallbacks) {
                        onDisabled.onDisabled();
                    }
                } else if (stringExtra.equals("STARTING")) {
                    for (StatusCallback onStarting : OrbotHelper.this.statusCallbacks) {
                        onStarting.onStarting();
                    }
                } else if (stringExtra.equals("STOPPING")) {
                    for (StatusCallback onStopping : OrbotHelper.this.statusCallbacks) {
                        onStopping.onStopping();
                    }
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public Set<StatusCallback> statusCallbacks = newSetFromMap(new WeakHashMap());
    private long statusTimeoutMs = 30000;
    private boolean validateOrbot = true;

    public interface InstallCallback {
        void onInstallTimeout();

        void onInstalled();
    }

    public static abstract class SimpleStatusCallback implements StatusCallback {
        public void onDisabled() {
        }

        public void onEnabled(Intent intent) {
        }

        public void onNotYetInstalled() {
        }

        public void onStarting() {
        }

        public void onStopping() {
        }
    }

    public String getName() {
        return "Orbot";
    }

    public static boolean isOnionAddress(URL url) {
        return url.getHost().endsWith(".onion");
    }

    public static boolean isOnionAddress(String str) {
        try {
            return isOnionAddress(new URL(str));
        } catch (MalformedURLException unused) {
            return false;
        }
    }

    public static boolean isOnionAddress(Uri uri) {
        return uri.getHost().endsWith(".onion");
    }

    @Deprecated
    public static boolean isOrbotRunning(Context context) {
        return TorServiceUtils.findProcessId(context) != -1;
    }

    public static boolean isOrbotInstalled(Context context) {
        return isAppInstalled(context, ORBOT_PACKAGE_NAME);
    }

    private static boolean isAppInstalled(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static void requestHiddenServiceOnPort(Activity activity, int i) {
        Intent intent = new Intent(ACTION_REQUEST_HS);
        intent.setPackage(ORBOT_PACKAGE_NAME);
        intent.putExtra("hs_port", i);
        activity.startActivityForResult(intent, HS_REQUEST_CODE);
    }

    public static boolean requestStartTor(Context context) {
        if (!isOrbotInstalled(context)) {
            return false;
        }
        Log.i("OrbotHelper", "requestStartTor " + context.getPackageName());
        context.sendBroadcast(getOrbotStartIntent(context));
        return true;
    }

    public static Intent getOrbotStartIntent(Context context) {
        Intent intent = new Intent(ACTION_START);
        intent.setPackage(ORBOT_PACKAGE_NAME);
        intent.putExtra(EXTRA_PACKAGE_NAME, context.getPackageName());
        return intent;
    }

    @Deprecated
    public static Intent getOrbotStartIntent() {
        Intent intent = new Intent(ACTION_START);
        intent.setPackage(ORBOT_PACKAGE_NAME);
        return intent;
    }

    public static boolean requestShowOrbotStart(Activity activity) {
        if (!isOrbotInstalled(activity) || isOrbotRunning(activity)) {
            return false;
        }
        activity.startActivityForResult(getShowOrbotStartIntent(), START_TOR_RESULT);
        return true;
    }

    public static Intent getShowOrbotStartIntent() {
        Intent intent = new Intent(ACTION_START_TOR);
        intent.setPackage(ORBOT_PACKAGE_NAME);
        intent.addFlags(268435456);
        return intent;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0060 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.content.Intent getOrbotInstallIntent(android.content.Context r4) {
        /*
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "android.intent.action.VIEW"
            r0.<init>(r1)
            java.lang.String r1 = "market://details?id=org.torproject.android"
            android.net.Uri r1 = android.net.Uri.parse(r1)
            r0.setData(r1)
            android.content.pm.PackageManager r4 = r4.getPackageManager()
            r1 = 0
            java.util.List r4 = r4.queryIntentActivities(r0, r1)
            java.util.Iterator r4 = r4.iterator()
        L_0x001d:
            boolean r1 = r4.hasNext()
            if (r1 == 0) goto L_0x0060
            java.lang.Object r1 = r4.next()
            android.content.pm.ResolveInfo r1 = (android.content.pm.ResolveInfo) r1
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "market: "
            r2.append(r3)
            android.content.pm.ActivityInfo r3 = r1.activityInfo
            java.lang.String r3 = r3.packageName
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = "OrbotHelper"
            android.util.Log.i(r3, r2)
            android.content.pm.ActivityInfo r2 = r1.activityInfo
            java.lang.String r2 = r2.packageName
            java.lang.String r3 = "org.fdroid.fdroid"
            boolean r2 = android.text.TextUtils.equals(r2, r3)
            if (r2 != 0) goto L_0x005b
            android.content.pm.ActivityInfo r2 = r1.activityInfo
            java.lang.String r2 = r2.packageName
            java.lang.String r3 = "com.android.vending"
            boolean r2 = android.text.TextUtils.equals(r2, r3)
            if (r2 == 0) goto L_0x001d
        L_0x005b:
            android.content.pm.ActivityInfo r4 = r1.activityInfo
            java.lang.String r4 = r4.packageName
            goto L_0x0061
        L_0x0060:
            r4 = 0
        L_0x0061:
            if (r4 != 0) goto L_0x006d
            java.lang.String r4 = "https://f-droid.org/repository/browse/?fdid=org.torproject.android"
            android.net.Uri r4 = android.net.Uri.parse(r4)
            r0.setData(r4)
            goto L_0x0070
        L_0x006d:
            r0.setPackage(r4)
        L_0x0070:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: info.guardianproject.netcipher.proxy.OrbotHelper.getOrbotInstallIntent(android.content.Context):android.content.Intent");
    }

    public boolean isInstalled(Context context) {
        return isOrbotInstalled(context);
    }

    public void requestStatus(Context context) {
        isOrbotRunning(context);
    }

    public boolean requestStart(Context context) {
        return requestStartTor(context);
    }

    public Intent getInstallIntent(Context context) {
        return getOrbotInstallIntent(context);
    }

    public Intent getStartIntent(Context context) {
        return getOrbotStartIntent();
    }

    public static synchronized OrbotHelper get(Context context) {
        OrbotHelper orbotHelper;
        synchronized (OrbotHelper.class) {
            if (INSTANCE == null) {
                INSTANCE = new OrbotHelper(context);
            }
            orbotHelper = INSTANCE;
        }
        return orbotHelper;
    }

    private OrbotHelper(Context context) {
        this.ctxt = context.getApplicationContext();
        this.handler = new Handler(Looper.getMainLooper());
    }

    public OrbotHelper addStatusCallback(StatusCallback statusCallback) {
        this.statusCallbacks.add(statusCallback);
        Intent intent = this.lastStatusIntent;
        if (intent != null && intent.getStringExtra(EXTRA_STATUS).equals("ON")) {
            statusCallback.onEnabled(this.lastStatusIntent);
        }
        return this;
    }

    public OrbotHelper removeStatusCallback(StatusCallback statusCallback) {
        this.statusCallbacks.remove(statusCallback);
        return this;
    }

    public OrbotHelper addInstallCallback(InstallCallback installCallback) {
        this.installCallbacks.add(installCallback);
        return this;
    }

    public OrbotHelper removeInstallCallback(InstallCallback installCallback) {
        this.installCallbacks.remove(installCallback);
        return this;
    }

    public OrbotHelper statusTimeout(long j) {
        this.statusTimeoutMs = j;
        return this;
    }

    public OrbotHelper installTimeout(long j) {
        this.installTimeoutMs = j;
        return this;
    }

    public OrbotHelper skipOrbotValidation() {
        this.validateOrbot = false;
        return this;
    }

    public boolean isInstalled() {
        return this.isInstalled;
    }

    public boolean init() {
        Intent orbotStartIntent = getOrbotStartIntent(this.ctxt);
        if (this.validateOrbot) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("A4:54:B8:7A:18:47:A8:9E:D7:F5:E7:0F:BA:6B:BA:96:F3:EF:29:C2:6E:09:81:20:4F:E3:47:BF:23:1D:FD:5B");
            arrayList.add("A7:02:07:92:4F:61:FF:09:37:1D:54:84:14:5C:4B:EE:77:2C:55:C1:9E:EE:23:2F:57:70:E1:82:71:F7:CB:AE");
            orbotStartIntent = SignatureUtils.validateBroadcastIntent(this.ctxt, orbotStartIntent, (List<String>) arrayList, false);
        }
        if (orbotStartIntent != null) {
            this.isInstalled = true;
            this.handler.postDelayed(this.onStatusTimeout, this.statusTimeoutMs);
            this.ctxt.registerReceiver(this.orbotStatusReceiver, new IntentFilter(ACTION_STATUS));
            this.ctxt.sendBroadcast(orbotStartIntent);
        } else {
            this.isInstalled = false;
            for (StatusCallback onNotYetInstalled : this.statusCallbacks) {
                onNotYetInstalled.onNotYetInstalled();
            }
        }
        return this.isInstalled;
    }

    public void installOrbot(Activity activity) {
        this.handler.postDelayed(this.onInstallTimeout, this.installTimeoutMs);
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        this.ctxt.registerReceiver(this.orbotInstallReceiver, intentFilter);
        activity.startActivity(getOrbotInstallIntent(this.ctxt));
    }

    static <E> Set<E> newSetFromMap(Map<E, Boolean> map) {
        if (map.isEmpty()) {
            return new SetFromMap(map);
        }
        throw new IllegalArgumentException("map not empty");
    }
}
