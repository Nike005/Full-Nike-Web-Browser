package com.yandex.metrica.impl.p050ob;

/* renamed from: com.yandex.metrica.impl.ob.bl */
public enum C1971bl {
    FOREGROUND(0),
    BACKGROUND(1);
    

    /* renamed from: c */
    private final int f3251c;

    private C1971bl(int i) {
        this.f3251c = i;
    }

    /* renamed from: a */
    public int mo17260a() {
        return this.f3251c;
    }

    /* renamed from: a */
    public static C1971bl m4964a(Integer num) {
        C1971bl blVar = FOREGROUND;
        if (num == null) {
            return blVar;
        }
        int intValue = num.intValue();
        if (intValue == 0) {
            return FOREGROUND;
        }
        if (intValue != 1) {
            return blVar;
        }
        return BACKGROUND;
    }
}
