package com.moat.analytics.mobile.mpub;

import java.util.HashMap;
import java.util.Map;

public class MoatAdEvent {
    public static final Double VOLUME_MUTED = Double.valueOf(0.0d);
    public static final Double VOLUME_UNMUTED = Double.valueOf(1.0d);

    /* renamed from: a */
    static final Integer f41a = Integer.MIN_VALUE;

    /* renamed from: e */
    private static final Double f42e = Double.valueOf(Double.NaN);

    /* renamed from: b */
    Integer f43b;

    /* renamed from: c */
    Double f44c;

    /* renamed from: d */
    MoatAdEventType f45d;

    /* renamed from: f */
    private final Double f46f;

    /* renamed from: g */
    private final Long f47g;

    public MoatAdEvent(MoatAdEventType moatAdEventType) {
        this(moatAdEventType, f41a, f42e);
    }

    public MoatAdEvent(MoatAdEventType moatAdEventType, Integer num) {
        this(moatAdEventType, num, f42e);
    }

    public MoatAdEvent(MoatAdEventType moatAdEventType, Integer num, Double d) {
        this.f47g = Long.valueOf(System.currentTimeMillis());
        this.f45d = moatAdEventType;
        this.f44c = d;
        this.f43b = num;
        this.f46f = Double.valueOf(C0328l.m199a().mo10452b());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public Map<String, Object> mo10317a() {
        HashMap hashMap = new HashMap();
        hashMap.put("adVolume", this.f44c);
        hashMap.put("playhead", this.f43b);
        hashMap.put("aTimeStamp", this.f47g);
        hashMap.put("type", this.f45d.toString());
        hashMap.put("deviceVolume", this.f46f);
        return hashMap;
    }
}
