package com.yandex.metrica.impl;

import android.net.Uri;
import android.text.TextUtils;
import android.util.SparseArray;
import com.yandex.metrica.impl.utils.C2223g;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.yandex.metrica.impl.p */
public final class C2208p {

    /* renamed from: a */
    public static final EnumSet<C2209a> f3839a = EnumSet.of(C2209a.EVENT_TYPE_INIT, new C2209a[]{C2209a.EVENT_TYPE_INIT_BACKGROUND, C2209a.EVENT_TYPE_CUSTOM_EVENT, C2209a.EVENT_TYPE_FIRST_ACTIVATION, C2209a.EVENT_TYPE_REFERRER_RECEIVED, C2209a.EVENT_TYPE_REFERRER_DEPRECATED, C2209a.EVENT_TYPE_APP_UPDATE});

    /* renamed from: b */
    private static final EnumSet<C2209a> f3840b = EnumSet.of(C2209a.EVENT_TYPE_UNDEFINED, new C2209a[]{C2209a.EVENT_TYPE_PURGE_BUFFER, C2209a.EVENT_TYPE_UPDATE_COLLECT_INSTALLED_APPS, C2209a.EVENT_TYPE_REFERRER_RECEIVED, C2209a.EVENT_TYPE_MIGRATE_EVENT_FORMAT_DEPRECATED, C2209a.EVENT_TYPE_MIGRATE_TO_UUID_API_KEY_DEPRECATED, C2209a.EVENT_TYPE_REFERRER_DEPRECATED, C2209a.EVENT_TYPE_APP_ENVIRONMENT_UPDATED, C2209a.EVENT_TYPE_APP_ENVIRONMENT_CLEARED, C2209a.EVENT_TYPE_ACTIVATION});

    /* renamed from: c */
    private static final EnumSet<C2209a> f3841c = EnumSet.of(C2209a.EVENT_TYPE_SET_USER_INFO, new C2209a[]{C2209a.EVENT_TYPE_REPORT_USER_INFO, C2209a.EVENT_TYPE_IDENTITY, C2209a.EVENT_TYPE_UPDATE_COLLECT_INSTALLED_APPS, C2209a.EVENT_TYPE_UNDEFINED, C2209a.EVENT_TYPE_INIT, C2209a.EVENT_TYPE_APP_UPDATE, C2209a.EVENT_TYPE_REFERRER_DEPRECATED, C2209a.EVENT_TYPE_ALIVE, C2209a.EVENT_TYPE_INIT_BACKGROUND, C2209a.EVENT_TYPE_STARTUP, C2209a.EVENT_TYPE_APP_ENVIRONMENT_UPDATED, C2209a.EVENT_TYPE_APP_ENVIRONMENT_CLEARED, C2209a.EVENT_TYPE_ACTIVATION});

    /* renamed from: d */
    private static final EnumSet<C2209a> f3842d = EnumSet.of(C2209a.EVENT_TYPE_ACTIVITY_END, C2209a.EVENT_TYPE_SET_USER_INFO, C2209a.EVENT_TYPE_REPORT_USER_INFO);

    /* renamed from: e */
    private static final EnumSet<C2209a> f3843e = EnumSet.of(C2209a.EVENT_TYPE_STARTUP, new C2209a[]{C2209a.EVENT_TYPE_UPDATE_COLLECT_INSTALLED_APPS, C2209a.EVENT_TYPE_REFERRER_RECEIVED, C2209a.EVENT_TYPE_REFERRER_DEPRECATED, C2209a.EVENT_TYPE_MIGRATE_EVENT_FORMAT_DEPRECATED, C2209a.EVENT_TYPE_MIGRATE_TO_UUID_API_KEY_DEPRECATED, C2209a.EVENT_TYPE_UNDEFINED, C2209a.EVENT_TYPE_ALIVE, C2209a.EVENT_TYPE_INIT_BACKGROUND, C2209a.EVENT_TYPE_APP_ENVIRONMENT_UPDATED, C2209a.EVENT_TYPE_APP_ENVIRONMENT_CLEARED});

    /* renamed from: f */
    private static final EnumSet<C2209a> f3844f = EnumSet.of(C2209a.EVENT_TYPE_EXCEPTION_UNHANDLED_DEPRECATED, C2209a.EVENT_TYPE_EXCEPTION_UNHANDLED, C2209a.EVENT_TYPE_EXCEPTION_USER, C2209a.EVENT_TYPE_NATIVE_CRASH, C2209a.EVENT_TYPE_REGULAR);

    /* renamed from: g */
    private static final EnumSet<C2209a> f3845g = EnumSet.of(C2209a.EVENT_TYPE_REGULAR);

    /* renamed from: com.yandex.metrica.impl.p$a */
    public enum C2209a {
        EVENT_TYPE_UNDEFINED(-1, "Unrecognized action"),
        EVENT_TYPE_INIT(0, "First initialization event"),
        EVENT_TYPE_REGULAR(1, "Regular event"),
        EVENT_TYPE_ACTIVITY_START_DEPRECATED(2, "Start of interaction with UI"),
        EVENT_TYPE_ACTIVITY_END(3, "End of interaction with UI"),
        EVENT_TYPE_EXCEPTION_UNHANDLED_DEPRECATED(4, "Deprecated crash of App"),
        EVENT_TYPE_EXCEPTION_USER(5, "Error from developer"),
        EVENT_TYPE_REFERRER_DEPRECATED(6, "Deprecated sending referrer"),
        EVENT_TYPE_ALIVE(7, "App is still alive"),
        EVENT_TYPE_UPDATE_COLLECT_INSTALLED_APPS(8, "Update collect apps"),
        EVENT_TYPE_SET_USER_INFO(9, "User info"),
        EVENT_TYPE_REPORT_USER_INFO(10, "Report user info"),
        EVENT_TYPE_PURGE_BUFFER(256, "Forcible buffer clearing"),
        EVENT_TYPE_SESSION_START_MANUALLY(512, "Manual start of session"),
        EVENT_TYPE_NATIVE_CRASH(768, "Native crash of App"),
        EVENT_TYPE_INIT_BACKGROUND(1280, "First initialization event in background mode"),
        EVENT_TYPE_STARTUP(1536, "Sending the startup due to lack of data"),
        EVENT_TYPE_IDENTITY(1792, "System identification"),
        EVENT_TYPE_STATBOX(2304, "Event with statistical data"),
        EVENT_TYPE_REFERRER_RECEIVED(4096, "Referrer received"),
        EVENT_TYPE_MIGRATE_EVENT_FORMAT_DEPRECATED(4352, "Migrate event format"),
        EVENT_TYPE_MIGRATE_TO_UUID_API_KEY_DEPRECATED(4608, "Migrate to uuid api key"),
        EVENT_TYPE_APP_ENVIRONMENT_UPDATED(5376, "App Environment Updated"),
        EVENT_TYPE_APP_ENVIRONMENT_CLEARED(5632, "App Environment Cleared"),
        EVENT_TYPE_EXCEPTION_UNHANDLED(5888, "Crash of App"),
        EVENT_TYPE_ACTIVATION(6144, "Activation of metrica"),
        EVENT_TYPE_FIRST_ACTIVATION(6145, "First activation of metrica"),
        EVENT_TYPE_START(6400, "Start of new session"),
        EVENT_TYPE_CUSTOM_EVENT(8192, "Custom event"),
        EVENT_TYPE_APP_OPEN(8208, "App open event"),
        EVENT_TYPE_APP_UPDATE(8224, "App update event"),
        EVENT_TYPE_PERMISSIONS(12288, "Permissions changed event"),
        EVENT_TYPE_APP_FEATURES(12289, "Features, required by application");
        

        /* renamed from: H */
        static final SparseArray<C2209a> f3853H = null;

        /* renamed from: I */
        private final int f3881I;

        /* renamed from: J */
        private final String f3882J;

        static {
            int i;
            f3853H = new SparseArray<>();
            for (C2209a aVar : values()) {
                f3853H.put(aVar.mo17884a(), aVar);
            }
        }

        private C2209a(int i, String str) {
            this.f3881I = i;
            this.f3882J = str;
        }

        /* renamed from: a */
        public int mo17884a() {
            return this.f3881I;
        }

        /* renamed from: b */
        public String mo17885b() {
            return this.f3882J;
        }

        /* renamed from: a */
        public static C2209a m5885a(int i) {
            C2209a aVar = f3853H.get(i);
            return aVar == null ? EVENT_TYPE_UNDEFINED : aVar;
        }
    }

    /* renamed from: a */
    public static boolean m5873a(C2209a aVar) {
        return !f3840b.contains(aVar);
    }

    /* renamed from: b */
    public static boolean m5877b(C2209a aVar) {
        return !f3841c.contains(aVar);
    }

    /* renamed from: a */
    public static boolean m5871a(int i) {
        return f3842d.contains(C2209a.m5885a(i));
    }

    /* renamed from: a */
    public static boolean m5872a(C1915h hVar) {
        return (hVar.mo17118c() == C2209a.EVENT_TYPE_SET_USER_INFO.mo17884a() || hVar.mo17118c() == C2209a.EVENT_TYPE_REPORT_USER_INFO.mo17884a()) && !TextUtils.isEmpty(hVar.mo17133l());
    }

    /* renamed from: c */
    public static boolean m5881c(C2209a aVar) {
        return !f3843e.contains(aVar);
    }

    /* renamed from: b */
    public static boolean m5876b(int i) {
        return f3844f.contains(C2209a.m5885a(i));
    }

    /* renamed from: c */
    public static boolean m5880c(int i) {
        return f3845g.contains(C2209a.m5885a(i));
    }

    /* renamed from: a */
    static C1915h m5868a(C2209a aVar, String str) {
        return new C1908e(str, aVar.mo17885b(), aVar.mo17884a());
    }

    /* renamed from: d */
    public static C1915h m5882d(C2209a aVar) {
        return new C1908e(aVar.mo17885b(), aVar.mo17884a());
    }

    /* renamed from: a */
    public static C1915h m5869a(String str) {
        return new C1908e(str, C2209a.EVENT_TYPE_REGULAR.mo17884a());
    }

    /* renamed from: a */
    static C1915h m5870a(String str, String str2) {
        return new C1908e(str2, str, C2209a.EVENT_TYPE_REGULAR.mo17884a());
    }

    /* renamed from: b */
    static C1915h m5875b(String str, String str2) {
        return new C1908e(str2, str, C2209a.EVENT_TYPE_EXCEPTION_USER.mo17884a());
    }

    /* renamed from: b */
    static C1915h m5874b(String str) {
        return new C1908e(str, C2209a.EVENT_TYPE_START.mo17884a());
    }

    /* renamed from: c */
    static C1915h m5878c(String str) {
        return new C1908e(str, C2209a.EVENT_TYPE_ACTIVITY_END.mo17884a());
    }

    /* renamed from: c */
    static C1915h m5879c(String str, String str2) {
        return new C1908e(str2, str, C2209a.EVENT_TYPE_EXCEPTION_UNHANDLED.mo17884a());
    }

    /* renamed from: d */
    public static C1915h m5883d(String str) {
        return new C1908e("", str, C2209a.EVENT_TYPE_REFERRER_RECEIVED.mo17884a());
    }

    /* renamed from: a */
    static C1915h m5866a(Uri uri) {
        return m5884e(uri.toString());
    }

    /* renamed from: e */
    static C1915h m5884e(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("link", str);
        return new C1908e(C2223g.m5948a((Map) hashMap), "", C2209a.EVENT_TYPE_APP_OPEN.mo17884a());
    }

    /* renamed from: a */
    public static C1915h m5867a(C1840an anVar) {
        return new C1908e(anVar == null ? "" : anVar.mo16838a(), C2209a.EVENT_TYPE_ACTIVATION.mo17885b(), C2209a.EVENT_TYPE_ACTIVATION.mo17884a());
    }

    /* renamed from: a */
    static C1915h m5864a() {
        return new C1908e(C2209a.EVENT_TYPE_UPDATE_COLLECT_INSTALLED_APPS.mo17885b(), C2209a.EVENT_TYPE_UPDATE_COLLECT_INSTALLED_APPS.mo17884a());
    }

    /* renamed from: a */
    static C1915h m5865a(int i, String str, String str2, Map<String, Object> map) {
        return new C1908e(str2, str, C2209a.EVENT_TYPE_CUSTOM_EVENT.mo17884a(), i).mo17124e(C2223g.m5948a((Map) map));
    }
}
