package com.appnext.base.p082b;

import com.appnext.core.C4974i;

/* renamed from: com.appnext.base.b.d */
public final class C4899d {
    public static final String STATUS = "status";

    /* renamed from: eS */
    public static final String f4606eS = "service_key";

    /* renamed from: eT */
    public static final String f4607eT = "4.7.2";

    /* renamed from: eU */
    public static final String f4608eU = "config.json";

    /* renamed from: eV */
    public static final String f4609eV = "plist.json";

    /* renamed from: eW */
    public static final String f4610eW = "/data/appnext/";

    /* renamed from: eX */
    public static final String f4611eX = "videos/";

    /* renamed from: eY */
    public static final String f4612eY = ".tmp";

    /* renamed from: eZ */
    public static final String f4613eZ = "http://cdn.appnext.com/tools/services/4.7.2/config.json";

    /* renamed from: fa */
    public static final String f4614fa = "http://cdn.appnext.com/tools/services/4.7.2/plist.json";

    /* renamed from: fb */
    public static final int f4615fb = 1024;

    /* renamed from: fc */
    public static final long f4616fc = 1048576;

    /* renamed from: fd */
    public static final int f4617fd = 15000;

    /* renamed from: fe */
    public static final String f4618fe = "on";

    /* renamed from: ff */
    public static final String f4619ff = "off";

    /* renamed from: fg */
    public static final String f4620fg = "config_data_obj";

    /* renamed from: fh */
    public static final String f4621fh = "second";

    /* renamed from: fi */
    public static final String f4622fi = "minute";

    /* renamed from: fj */
    public static final String f4623fj = "hour";

    /* renamed from: fk */
    public static final String f4624fk = "day";

    /* renamed from: fl */
    public static final String f4625fl = "time";

    /* renamed from: fm */
    public static final String f4626fm = "once";

    /* renamed from: fn */
    public static final String f4627fn = "interval";

    /* renamed from: fo */
    public static final String f4628fo = "isAidDisabled";

    /* renamed from: fp */
    public static final String f4629fp = "aidForSend";

    /* renamed from: aL */
    public static final String m6571aL() {
        return C4974i.f4833hm;
    }

    /* renamed from: aM */
    public static final String m6572aM() {
        return C4974i.f4834hn;
    }

    /* renamed from: com.appnext.base.b.d$a */
    public enum C4900a {
        String("String"),
        Long("Long"),
        Double("Double"),
        Integer("Integer"),
        HashMap("HashMap"),
        ArrayList("ArrayList"),
        Boolean("Boolean"),
        JSONArray("JSONArray"),
        JSONObject("JSONObject"),
        Set("Set");
        
        private String mDataType;

        private C4900a(String str) {
            this.mDataType = str;
        }

        public final String getType() {
            return this.mDataType;
        }
    }
}
