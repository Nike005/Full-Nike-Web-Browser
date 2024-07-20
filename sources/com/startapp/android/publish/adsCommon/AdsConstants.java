package com.startapp.android.publish.adsCommon;

import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.model.AdPreferences;

/* compiled from: StartAppSDK */
public class AdsConstants {
    public static final int AD_INFORMATION_EXTENDED_ID = 1475346434;
    public static final int AD_INFORMATION_ID = 1475346433;
    public static final Boolean FORCE_NATIVE_VIDEO_PLAYER = false;
    public static final int LIST_3D_CLOSE_BUTTON_ID = 1475346435;
    public static final String OVERRIDE_HOST = null;
    public static final Boolean OVERRIDE_NETWORK = false;
    public static final int SPLASH_NATIVE_MAIN_LAYOUT_ID = 1475346437;
    public static final int STARTAPP_AD_MAIN_LAYOUT_ID = 1475346432;
    public static final Boolean VIDEO_DEBUG = false;

    /* renamed from: a */
    public static final String f954a = "get";

    /* renamed from: b */
    public static final String f955b;

    /* renamed from: c */
    public static final String f956c;

    /* renamed from: d */
    public static final String f957d = "trackdownload";

    /* renamed from: e */
    public static final String f958e;

    /* renamed from: f */
    public static final String f959f = "https://imp.startappservice.com/tracking/adImpression";

    /* renamed from: g */
    public static final Boolean f960g = false;

    /* renamed from: h */
    public static final String f961h = C1061i.m1199b();

    /* renamed from: i */
    public static final String f962i = C1061i.m1202c();

    /* renamed from: j */
    public static final String f963j = C1061i.m1204d();

    /* renamed from: k */
    public static final String[] f964k = {"back_", "back_dark", "browser_icon_dark", "forward_", "forward_dark", "x_dark"};

    /* renamed from: l */
    public static final String[] f965l = {"empty_star", "filled_star", "half_star"};

    /* compiled from: StartAppSDK */
    public enum AdApiType {
        HTML,
        JSON
    }

    /* compiled from: StartAppSDK */
    public enum ServiceApiType {
        METADATA,
        DOWNLOAD
    }

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(f954a);
        sb.append("ads");
        f955b = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(f954a);
        sb2.append("htmlad");
        f956c = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(f954a);
        sb3.append("adsmetadata");
        f958e = sb3.toString();
    }

    /* renamed from: a */
    public static String m1127a(ServiceApiType serviceApiType) {
        String str;
        String str2;
        String str3;
        int i = C10431.f966a[serviceApiType.ordinal()];
        String str4 = null;
        if (i == 1) {
            str3 = f958e;
            str2 = MetaData.getInstance().getMetaDataHost();
        } else if (i != 2) {
            str = null;
            return str4 + str;
        } else {
            str3 = f957d;
            str2 = MetaData.getInstance().getAdPlatformHost();
        }
        String str5 = str3;
        str4 = str2;
        str = str5;
        return str4 + str;
    }

    /* renamed from: com.startapp.android.publish.adsCommon.AdsConstants$1 */
    /* compiled from: StartAppSDK */
    static /* synthetic */ class C10431 {

        /* renamed from: a */
        static final /* synthetic */ int[] f966a;

        /* renamed from: b */
        static final /* synthetic */ int[] f967b;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
        static {
            /*
                com.startapp.android.publish.adsCommon.AdsConstants$AdApiType[] r0 = com.startapp.android.publish.adsCommon.AdsConstants.AdApiType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f967b = r0
                r1 = 1
                com.startapp.android.publish.adsCommon.AdsConstants$AdApiType r2 = com.startapp.android.publish.adsCommon.AdsConstants.AdApiType.HTML     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = f967b     // Catch:{ NoSuchFieldError -> 0x001d }
                com.startapp.android.publish.adsCommon.AdsConstants$AdApiType r3 = com.startapp.android.publish.adsCommon.AdsConstants.AdApiType.JSON     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                com.startapp.android.publish.adsCommon.AdsConstants$ServiceApiType[] r2 = com.startapp.android.publish.adsCommon.AdsConstants.ServiceApiType.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                f966a = r2
                com.startapp.android.publish.adsCommon.AdsConstants$ServiceApiType r3 = com.startapp.android.publish.adsCommon.AdsConstants.ServiceApiType.METADATA     // Catch:{ NoSuchFieldError -> 0x002e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002e }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x002e }
            L_0x002e:
                int[] r1 = f966a     // Catch:{ NoSuchFieldError -> 0x0038 }
                com.startapp.android.publish.adsCommon.AdsConstants$ServiceApiType r2 = com.startapp.android.publish.adsCommon.AdsConstants.ServiceApiType.DOWNLOAD     // Catch:{ NoSuchFieldError -> 0x0038 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0038 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0038 }
            L_0x0038:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.adsCommon.AdsConstants.C10431.<clinit>():void");
        }
    }

    /* renamed from: a */
    public static String m1126a(AdApiType adApiType, AdPreferences.Placement placement) {
        String str;
        String str2;
        String str3;
        int i = C10431.f967b[adApiType.ordinal()];
        String str4 = null;
        if (i == 1) {
            str3 = f956c;
            str2 = MetaData.getInstance().getAdPlatformHost(placement);
        } else if (i != 2) {
            str = null;
            return str4 + str;
        } else {
            str3 = f955b;
            str2 = MetaData.getInstance().getAdPlatformHost(placement);
        }
        String str5 = str3;
        str4 = str2;
        str = str5;
        return str4 + str;
    }

    /* renamed from: a */
    public static Boolean m1125a() {
        return VIDEO_DEBUG;
    }
}
