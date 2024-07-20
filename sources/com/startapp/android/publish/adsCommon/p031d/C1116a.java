package com.startapp.android.publish.adsCommon.p031d;

/* renamed from: com.startapp.android.publish.adsCommon.d.a */
/* compiled from: StartAppSDK */
public class C1116a extends C1117b {
    private static final long serialVersionUID = 1;
    private final String DURATION_PARAM_NAME = "&displayDuration=";
    private String duration;

    public C1116a(String str, String str2) {
        super(str2);
        this.duration = str;
    }

    public String getQueryString() {
        return super.getQueryString() + "&displayDuration=" + encode(this.duration);
    }
}
