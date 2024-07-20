package com.yandex.metrica.impl.interact;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import com.yandex.metrica.C1779a;
import com.yandex.metrica.impl.C1838am;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class DeviceInfo {

    /* renamed from: a */
    private static volatile DeviceInfo f3186a;
    public final String appPlatform = SystemMediaRouteProvider.PACKAGE_NAME;

    /* renamed from: b */
    private final Context f3187b;
    public final String deviceRootStatus;
    public final List<String> deviceRootStatusMarkers;
    public final String deviceType;
    public String locale;
    public final String manufacturer;
    public final String model;
    public final String osVersion;
    public final String platform = SystemMediaRouteProvider.PACKAGE_NAME;
    public final String platformDeviceId;
    public final float scaleFactor;
    public final int screenDpi;
    public final int screenHeight;
    public final int screenWidth;

    public static DeviceInfo getInstance(Context context) {
        if (f3186a == null) {
            synchronized (DeviceInfo.class) {
                if (f3186a == null) {
                    f3186a = new DeviceInfo(context.getApplicationContext());
                }
            }
        }
        return f3186a;
    }

    private DeviceInfo(Context context) {
        C1779a aVar;
        this.f3187b = context;
        this.platformDeviceId = Settings.Secure.getString(context.getContentResolver(), "android_id");
        this.manufacturer = Build.MANUFACTURER;
        this.model = Build.MODEL;
        this.osVersion = Build.VERSION.RELEASE;
        this.screenWidth = C1838am.m4250a(this.f3187b).x;
        this.screenHeight = C1838am.m4250a(this.f3187b).y;
        this.screenDpi = this.f3187b.getResources().getDisplayMetrics().densityDpi;
        this.scaleFactor = this.f3187b.getResources().getDisplayMetrics().density;
        this.locale = C1838am.m4252b(this.f3187b);
        Context context2 = this.f3187b;
        DisplayMetrics displayMetrics = context2.getResources().getDisplayMetrics();
        Point a = C1838am.m4250a(context2);
        int i = a.x;
        int i2 = a.y;
        float f = displayMetrics.density;
        float f2 = (float) i;
        float f3 = (float) i2;
        float min = Math.min(f2 / f, f3 / f);
        float f4 = f * 160.0f;
        float f5 = f2 / f4;
        float f6 = f3 / f4;
        double sqrt = Math.sqrt((double) ((f5 * f5) + (f6 * f6)));
        if (sqrt >= 15.0d && !context2.getPackageManager().hasSystemFeature("android.hardware.touchscreen")) {
            aVar = C1779a.TV;
        } else if (sqrt >= 7.0d || min >= 600.0f) {
            aVar = C1779a.TABLET;
        } else {
            aVar = C1779a.PHONE;
        }
        this.deviceType = aVar.name().toLowerCase(Locale.US);
        this.deviceRootStatus = String.valueOf(C1838am.C1839a.m4256c());
        this.deviceRootStatusMarkers = Collections.unmodifiableList(new ArrayList<String>() {
            {
                if (C1838am.C1839a.m4254a()) {
                    add("Superuser.apk");
                }
                if (C1838am.C1839a.m4255b()) {
                    add("su.so");
                }
            }
        });
    }

    public String getLocale() {
        String b = C1838am.m4252b(this.f3187b);
        this.locale = b;
        return b;
    }
}
