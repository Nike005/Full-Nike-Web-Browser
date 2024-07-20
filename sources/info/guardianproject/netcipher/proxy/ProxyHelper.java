package info.guardianproject.netcipher.proxy;

import android.content.Context;
import android.content.Intent;

public interface ProxyHelper {
    public static final String ACTION_START = "android.intent.action.PROXY_START";
    public static final String ACTION_STATUS = "android.intent.action.PROXY_STATUS";
    public static final String EXTRA_PACKAGE_NAME = "android.intent.extra.PROXY_PACKAGE_NAME";
    public static final String EXTRA_PROXY_PORT_HTTP = "android.intent.extra.PROXY_PORT_HTTP";
    public static final String EXTRA_PROXY_PORT_SOCKS = "android.intent.extra.PROXY_PORT_SOCKS";
    public static final String EXTRA_STATUS = "android.intent.extra.PROXY_STATUS";
    public static final String FDROID_PACKAGE_NAME = "org.fdroid.fdroid";
    public static final String PLAY_PACKAGE_NAME = "com.android.vending";
    public static final String STATUS_OFF = "OFF";
    public static final String STATUS_ON = "ON";
    public static final String STATUS_STARTING = "STARTING";
    public static final String STATUS_STARTS_DISABLED = "STARTS_DISABLED";
    public static final String STATUS_STOPPING = "STOPPING";

    Intent getInstallIntent(Context context);

    String getName();

    Intent getStartIntent(Context context);

    boolean isInstalled(Context context);

    boolean requestStart(Context context);

    void requestStatus(Context context);
}
