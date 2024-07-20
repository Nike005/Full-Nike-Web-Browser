package com.moat.analytics.mobile.mpub;

import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import com.moat.analytics.mobile.mpub.C0310g;
import com.mopub.mobileads.VastIconXmlManager;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.mpub.c */
abstract class C0305c extends C0304b {

    /* renamed from: g */
    static final MoatAdEventType[] f72g = {MoatAdEventType.AD_EVT_FIRST_QUARTILE, MoatAdEventType.AD_EVT_MID_POINT, MoatAdEventType.AD_EVT_THIRD_QUARTILE};

    /* renamed from: h */
    final Map<MoatAdEventType, Integer> f73h;

    /* renamed from: i */
    final Handler f74i;

    /* renamed from: j */
    Map<String, String> f75j;

    /* renamed from: k */
    WeakReference<View> f76k;

    /* renamed from: l */
    private final Set<MoatAdEventType> f77l;
    /* access modifiers changed from: private */

    /* renamed from: m */
    public VideoTrackerListener f78m;

    /* renamed from: n */
    private boolean f79n;

    /* renamed from: o */
    private Double f80o;
    /* access modifiers changed from: private */

    /* renamed from: p */
    public final C0310g f81p;

    /* renamed from: q */
    private final String f82q;

    C0305c(String str) {
        super((View) null, false, true);
        C0336p.m228a(3, "BaseVideoTracker", (Object) this, "Initializing.");
        this.f82q = str;
        C0310g gVar = new C0310g(C0298a.m71a(), C0310g.C0315a.VIDEO);
        this.f81p = gVar;
        super.mo10382a(gVar.f87b);
        try {
            super.mo10381a(this.f81p.f86a);
        } catch (C0330n e) {
            this.f61a = e;
        }
        this.f73h = new HashMap();
        this.f77l = new HashSet();
        this.f74i = new Handler();
        this.f79n = false;
        this.f80o = Double.valueOf(1.0d);
    }

    /* renamed from: a */
    private static boolean m107a(MoatAdEventType moatAdEventType) {
        return moatAdEventType == MoatAdEventType.AD_EVT_COMPLETE || moatAdEventType == MoatAdEventType.AD_EVT_STOPPED || moatAdEventType == MoatAdEventType.AD_EVT_SKIPPED;
    }

    /* renamed from: b */
    private void m108b(MoatAdEvent moatAdEvent) {
        JSONObject a = mo10398a(moatAdEvent);
        C0336p.m228a(3, "BaseVideoTracker", (Object) this, String.format("Received event: %s", new Object[]{a.toString()}));
        C0336p.m231a("[SUCCESS] ", mo10380a() + String.format(" Received event: %s", new Object[]{a.toString()}));
        if (mo10389e() && this.f63c != null) {
            this.f63c.mo10438a(this.f81p.f88c, a);
            if (!this.f77l.contains(moatAdEvent.f45d)) {
                this.f77l.add(moatAdEvent.f45d);
                VideoTrackerListener videoTrackerListener = this.f78m;
                if (videoTrackerListener != null) {
                    videoTrackerListener.onVideoEventReported(moatAdEvent.f45d);
                }
            }
        }
        MoatAdEventType moatAdEventType = moatAdEvent.f45d;
        if (m107a(moatAdEventType)) {
            this.f73h.put(moatAdEventType, 1);
            if (this.f63c != null) {
                this.f63c.mo10442c((C0304b) this);
            }
            mo10405l();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public JSONObject mo10398a(MoatAdEvent moatAdEvent) {
        if (Double.isNaN(moatAdEvent.f44c.doubleValue())) {
            moatAdEvent.f44c = this.f80o;
        }
        return new JSONObject(moatAdEvent.mo10317a());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo10384a(List<String> list) {
        if (this.f75j == null) {
            list.add("Null adIds object");
        }
        if (list.isEmpty()) {
            super.mo10384a(list);
            return;
        }
        throw new C0330n(TextUtils.join(" and ", list));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo10399a(Integer num, Integer num2) {
        int abs = Math.abs(num2.intValue() - num.intValue());
        double intValue = (double) num2.intValue();
        Double.isNaN(intValue);
        return ((double) abs) <= Math.min(750.0d, intValue * 0.05d);
    }

    /* renamed from: a */
    public boolean mo10400a(Map<String, String> map, View view) {
        try {
            mo10386c();
            mo10388d();
            if (view == null) {
                C0336p.m228a(3, "BaseVideoTracker", (Object) this, "trackVideoAd received null video view instance");
            }
            this.f75j = map;
            this.f76k = new WeakReference<>(view);
            mo10385b();
            String format = String.format("trackVideoAd tracking ids: %s | view: %s", new Object[]{new JSONObject(map).toString(), C0336p.m226a(view)});
            C0336p.m228a(3, "BaseVideoTracker", (Object) this, format);
            C0336p.m231a("[SUCCESS] ", mo10380a() + StringUtils.SPACE + format);
            if (this.f64d != null) {
                this.f64d.onTrackingStarted(mo10391g());
            }
            return true;
        } catch (Exception e) {
            mo10383a("trackVideoAd", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo10385b() {
        super.changeTargetView((View) this.f76k.get());
        super.mo10385b();
        Map<String, Object> i = mo10402i();
        Integer num = (Integer) i.get("width");
        Integer num2 = (Integer) i.get("height");
        Integer num3 = (Integer) i.get(VastIconXmlManager.DURATION);
        C0336p.m228a(3, "BaseVideoTracker", (Object) this, String.format(Locale.ROOT, "Player metadata: height = %d, width = %d, duration = %d", new Object[]{num2, num, num3}));
        this.f81p.mo10413a(this.f82q, this.f75j, num, num2, num3);
    }

    public void changeTargetView(View view) {
        C0336p.m228a(3, "BaseVideoTracker", (Object) this, "changing view to " + C0336p.m226a(view));
        this.f76k = new WeakReference<>(view);
        try {
            super.changeTargetView(view);
        } catch (Exception e) {
            C0330n.m214a(e);
        }
    }

    public void dispatchEvent(MoatAdEvent moatAdEvent) {
        try {
            m108b(moatAdEvent);
        } catch (Exception e) {
            C0330n.m214a(e);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: i */
    public abstract Map<String, Object> mo10402i();

    /* access modifiers changed from: package-private */
    /* renamed from: j */
    public Double mo10403j() {
        return Double.valueOf(mo10404k().doubleValue() * C0328l.m199a().mo10452b());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: k */
    public Double mo10404k() {
        return this.f80o;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: l */
    public void mo10405l() {
        if (!this.f79n) {
            this.f79n = true;
            this.f74i.postDelayed(new Runnable() {
                public void run() {
                    try {
                        C0336p.m228a(3, "BaseVideoTracker", (Object) this, "Shutting down.");
                        C0305c.this.f81p.mo10411a();
                        VideoTrackerListener unused = C0305c.this.f78m = null;
                    } catch (Exception e) {
                        C0330n.m214a(e);
                    }
                }
            }, 500);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: m */
    public boolean mo10406m() {
        return this.f73h.containsKey(MoatAdEventType.AD_EVT_COMPLETE) || this.f73h.containsKey(MoatAdEventType.AD_EVT_STOPPED) || this.f73h.containsKey(MoatAdEventType.AD_EVT_SKIPPED);
    }

    public void removeVideoListener() {
        this.f78m = null;
    }

    public void setPlayerVolume(Double d) {
        Double j = mo10403j();
        if (!d.equals(this.f80o)) {
            C0336p.m228a(3, "BaseVideoTracker", (Object) this, String.format(Locale.ROOT, "player volume changed to %f ", new Object[]{d}));
            this.f80o = d;
            if (!j.equals(mo10403j())) {
                dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_VOLUME_CHANGE, MoatAdEvent.f41a, this.f80o));
            }
        }
    }

    public void setVideoListener(VideoTrackerListener videoTrackerListener) {
        this.f78m = videoTrackerListener;
    }

    public void stopTracking() {
        try {
            super.stopTracking();
            mo10405l();
            if (this.f78m != null) {
                this.f78m = null;
            }
        } catch (Exception e) {
            C0330n.m214a(e);
        }
    }
}
