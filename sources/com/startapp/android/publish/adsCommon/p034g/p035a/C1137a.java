package com.startapp.android.publish.adsCommon.p034g.p035a;

import android.app.Activity;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.android.publish.adsCommon.p034g.p037c.C1145a;
import com.startapp.common.p043a.C1261c;
import com.startapp.common.p043a.C1270g;
import java.net.URLDecoder;
import java.util.Map;

/* renamed from: com.startapp.android.publish.adsCommon.g.a.a */
/* compiled from: StartAppSDK */
public abstract class C1137a implements C1139b {
    private static final String TAG = "BaseMraidController";
    protected C1138a openListener;

    /* renamed from: com.startapp.android.publish.adsCommon.g.a.a$a */
    /* compiled from: StartAppSDK */
    public interface C1138a {
        /* renamed from: a */
        boolean mo13819a(String str);
    }

    public abstract void close();

    public abstract boolean isFeatureSupported(String str);

    public abstract void setOrientationProperties(Map<String, String> map);

    public abstract void useCustomClose(String str);

    public C1137a(C1138a aVar) {
        this.openListener = aVar;
    }

    public void resize() {
        C1270g.m2078a(TAG, 3, "resize");
    }

    public void expand(String str) {
        C1270g.m2078a(TAG, 3, "expand");
    }

    public boolean open(String str) {
        C1270g.m2078a(TAG, 3, "open " + str);
        try {
            String trim = URLDecoder.decode(str, "UTF-8").trim();
            if (trim.startsWith("sms")) {
                return openSMS(trim);
            }
            if (trim.startsWith("tel")) {
                return openTel(trim);
            }
            return this.openListener.mo13819a(trim);
        } catch (Exception e) {
            C1270g.m2078a(TAG, 6, e.getMessage());
            return this.openListener.mo13819a(str);
        }
    }

    /* access modifiers changed from: protected */
    public void applyOrientationProperties(Activity activity, C1145a aVar) {
        try {
            int i = 0;
            int i2 = activity.getResources().getConfiguration().orientation == 1 ? 1 : 0;
            if (aVar.f1208b == 0) {
                i = 1;
            } else if (aVar.f1208b != 1) {
                i = aVar.f1207a ? -1 : i2;
            }
            C1261c.m2021a(activity, i);
        } catch (Exception e) {
            C1132f.m1527a(activity, C1130d.EXCEPTION, "BaseMraidController.applyOrientationProperties", e.getMessage(), "");
        }
    }

    public void setResizeProperties(Map<String, String> map) {
        C1270g.m2078a(TAG, 3, "setResizeProperties " + map);
    }

    public void createCalendarEvent(String str) {
        C1270g.m2078a(TAG, 3, "createCalendarEvent " + str);
        isFeatureSupported("calendar");
    }

    public void playVideo(String str) {
        C1270g.m2078a(TAG, 3, "playVideo " + str);
        isFeatureSupported("inlineVideo");
    }

    public void storePicture(String str) {
        C1270g.m2078a(TAG, 3, "storePicture " + str);
        isFeatureSupported("storePicture");
    }

    public boolean openSMS(String str) {
        C1270g.m2078a(TAG, 3, "openSMS " + str);
        isFeatureSupported("sms");
        return true;
    }

    public boolean openTel(String str) {
        C1270g.m2078a(TAG, 3, "openTel " + str);
        isFeatureSupported("tel");
        return true;
    }
}
