package info.guardianproject.netcipher.proxy;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class PsiphonHelper implements ProxyHelper {
    public static final String COMPONENT_NAME = "com.psiphon3.StatusActivity";
    public static final int DEFAULT_HTTP_PORT = 8080;
    public static final int DEFAULT_SOCKS_PORT = 1080;
    public static final String FDROID_URI = "https://f-droid.org/repository/browse/?fdid=com.psiphon3";
    public static final String MARKET_URI = "market://details?id=com.psiphon3";
    public static final String ORBOT_PLAY_URI = "https://play.google.com/store/apps/details?id=com.psiphon3";
    public static final String PACKAGE_NAME = "com.psiphon3";

    public String getName() {
        return PACKAGE_NAME;
    }

    public boolean isInstalled(Context context) {
        return isAppInstalled(context, PACKAGE_NAME);
    }

    private static boolean isAppInstalled(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public void requestStatus(final Context context) {
        new Thread() {
            public void run() {
                int i = 0;
                int i2 = PsiphonHelper.DEFAULT_SOCKS_PORT;
                int i3 = 0;
                boolean z = false;
                while (i3 < 10 && !z) {
                    i3++;
                    int i4 = i2 + 1;
                    z = PsiphonHelper.isPortOpen("127.0.0.1", i2, 100);
                    i2 = i4;
                }
                boolean z2 = false;
                int i5 = PsiphonHelper.DEFAULT_HTTP_PORT;
                while (i < 10 && !z2) {
                    i++;
                    boolean isPortOpen = PsiphonHelper.isPortOpen("127.0.0.1", i5, 100);
                    i5++;
                    z2 = isPortOpen;
                }
                Intent intent = new Intent(ProxyHelper.ACTION_STATUS);
                intent.putExtra(ProxyHelper.EXTRA_PACKAGE_NAME, PsiphonHelper.PACKAGE_NAME);
                if (!z || !z2) {
                    intent.putExtra(ProxyHelper.EXTRA_STATUS, "OFF");
                } else {
                    intent.putExtra(ProxyHelper.EXTRA_STATUS, "ON");
                    intent.putExtra(ProxyHelper.EXTRA_PROXY_PORT_HTTP, i5 - 1);
                    intent.putExtra(ProxyHelper.EXTRA_PROXY_PORT_SOCKS, i2 - 1);
                }
                context.sendBroadcast(intent);
            }
        }.start();
    }

    public boolean requestStart(Context context) {
        context.startActivity(getStartIntent(context));
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0046 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.content.Intent getInstallIntent(android.content.Context r5) {
        /*
            r4 = this;
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "android.intent.action.VIEW"
            r0.<init>(r1)
            java.lang.String r1 = "market://details?id=com.psiphon3"
            android.net.Uri r1 = android.net.Uri.parse(r1)
            r0.setData(r1)
            android.content.pm.PackageManager r5 = r5.getPackageManager()
            r1 = 0
            java.util.List r5 = r5.queryIntentActivities(r0, r1)
            java.util.Iterator r5 = r5.iterator()
        L_0x001d:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L_0x0046
            java.lang.Object r1 = r5.next()
            android.content.pm.ResolveInfo r1 = (android.content.pm.ResolveInfo) r1
            android.content.pm.ActivityInfo r2 = r1.activityInfo
            java.lang.String r2 = r2.packageName
            java.lang.String r3 = "org.fdroid.fdroid"
            boolean r2 = android.text.TextUtils.equals(r2, r3)
            if (r2 != 0) goto L_0x0041
            android.content.pm.ActivityInfo r2 = r1.activityInfo
            java.lang.String r2 = r2.packageName
            java.lang.String r3 = "com.android.vending"
            boolean r2 = android.text.TextUtils.equals(r2, r3)
            if (r2 == 0) goto L_0x001d
        L_0x0041:
            android.content.pm.ActivityInfo r5 = r1.activityInfo
            java.lang.String r5 = r5.packageName
            goto L_0x0047
        L_0x0046:
            r5 = 0
        L_0x0047:
            if (r5 != 0) goto L_0x0053
            java.lang.String r5 = "https://f-droid.org/repository/browse/?fdid=com.psiphon3"
            android.net.Uri r5 = android.net.Uri.parse(r5)
            r0.setData(r5)
            goto L_0x0056
        L_0x0053:
            r0.setPackage(r5)
        L_0x0056:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: info.guardianproject.netcipher.proxy.PsiphonHelper.getInstallIntent(android.content.Context):android.content.Intent");
    }

    public Intent getStartIntent(Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(PACKAGE_NAME, COMPONENT_NAME));
        return intent;
    }

    public static boolean isPortOpen(String str, int i, int i2) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(str, i), i2);
            socket.close();
            return true;
        } catch (ConnectException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
