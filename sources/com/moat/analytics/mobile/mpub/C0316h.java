package com.moat.analytics.mobile.mpub;

import android.view.View;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.mpub.h */
abstract class C0316h extends C0305c {

    /* renamed from: l */
    int f100l = Integer.MIN_VALUE;

    /* renamed from: m */
    private C0318a f101m = C0318a.UNINITIALIZED;

    /* renamed from: n */
    private int f102n = Integer.MIN_VALUE;

    /* renamed from: o */
    private double f103o = Double.NaN;

    /* renamed from: p */
    private int f104p = Integer.MIN_VALUE;

    /* renamed from: q */
    private int f105q = 0;

    /* renamed from: com.moat.analytics.mobile.mpub.h$a */
    enum C0318a {
        UNINITIALIZED,
        PAUSED,
        PLAYING,
        STOPPED,
        COMPLETED
    }

    C0316h(String str) {
        super(str);
    }

    /* renamed from: t */
    private void m133t() {
        this.f74i.postDelayed(new Runnable() {
            public void run() {
                C0316h hVar;
                try {
                    if (!C0316h.this.mo10419n() || C0316h.this.mo10406m()) {
                        hVar = C0316h.this;
                    } else if (Boolean.valueOf(C0316h.this.mo10424s()).booleanValue()) {
                        C0316h.this.f74i.postDelayed(this, 200);
                        return;
                    } else {
                        hVar = C0316h.this;
                    }
                    hVar.mo10405l();
                } catch (Exception e) {
                    C0316h.this.mo10405l();
                    C0330n.m214a(e);
                }
            }
        }, 200);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public JSONObject mo10398a(MoatAdEvent moatAdEvent) {
        Integer num;
        int i;
        if (!moatAdEvent.f43b.equals(MoatAdEvent.f41a)) {
            num = moatAdEvent.f43b;
        } else {
            try {
                num = mo10420o();
            } catch (Exception unused) {
                num = Integer.valueOf(this.f102n);
            }
            moatAdEvent.f43b = num;
        }
        if (moatAdEvent.f43b.intValue() < 0 || (moatAdEvent.f43b.intValue() == 0 && moatAdEvent.f45d == MoatAdEventType.AD_EVT_COMPLETE && this.f102n > 0)) {
            num = Integer.valueOf(this.f102n);
            moatAdEvent.f43b = num;
        }
        if (moatAdEvent.f45d == MoatAdEventType.AD_EVT_COMPLETE) {
            if (num.intValue() == Integer.MIN_VALUE || (i = this.f100l) == Integer.MIN_VALUE || !mo10399a(num, Integer.valueOf(i))) {
                this.f101m = C0318a.STOPPED;
                moatAdEvent.f45d = MoatAdEventType.AD_EVT_STOPPED;
            } else {
                this.f101m = C0318a.COMPLETED;
            }
        }
        return super.mo10398a(moatAdEvent);
    }

    /* renamed from: a */
    public boolean mo10400a(Map<String, String> map, View view) {
        try {
            boolean a = super.mo10400a(map, view);
            if (!a || !mo10421p()) {
                return a;
            }
            m133t();
            return a;
        } catch (Exception e) {
            C0336p.m228a(3, "IntervalVideoTracker", (Object) this, "Problem with video loop");
            mo10383a("trackVideoAd", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: n */
    public abstract boolean mo10419n();

    /* access modifiers changed from: package-private */
    /* renamed from: o */
    public abstract Integer mo10420o();

    /* access modifiers changed from: protected */
    /* renamed from: p */
    public boolean mo10421p() {
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: q */
    public abstract boolean mo10422q();

    /* access modifiers changed from: package-private */
    /* renamed from: r */
    public abstract Integer mo10423r();

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x009d A[Catch:{ Exception -> 0x00d4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x009f A[Catch:{ Exception -> 0x00d4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00bf A[Catch:{ Exception -> 0x00d4 }] */
    /* renamed from: s */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo10424s() {
        /*
            r12 = this;
            boolean r0 = r12.mo10419n()
            r1 = 0
            if (r0 == 0) goto L_0x00df
            boolean r0 = r12.mo10406m()
            if (r0 == 0) goto L_0x000f
            goto L_0x00df
        L_0x000f:
            r0 = 1
            java.lang.Integer r2 = r12.mo10420o()     // Catch:{ Exception -> 0x00d4 }
            int r2 = r2.intValue()     // Catch:{ Exception -> 0x00d4 }
            int r3 = r12.f102n     // Catch:{ Exception -> 0x00d4 }
            if (r3 < 0) goto L_0x001f
            if (r2 >= 0) goto L_0x001f
            return r1
        L_0x001f:
            r12.f102n = r2     // Catch:{ Exception -> 0x00d4 }
            if (r2 != 0) goto L_0x0024
            return r0
        L_0x0024:
            java.lang.Integer r3 = r12.mo10423r()     // Catch:{ Exception -> 0x00d4 }
            int r3 = r3.intValue()     // Catch:{ Exception -> 0x00d4 }
            boolean r4 = r12.mo10422q()     // Catch:{ Exception -> 0x00d4 }
            double r5 = (double) r3
            r7 = 4616189618054758400(0x4010000000000000, double:4.0)
            java.lang.Double.isNaN(r5)
            double r5 = r5 / r7
            java.lang.Double r7 = r12.mo10403j()     // Catch:{ Exception -> 0x00d4 }
            double r7 = r7.doubleValue()     // Catch:{ Exception -> 0x00d4 }
            r9 = 0
            int r10 = r12.f104p     // Catch:{ Exception -> 0x00d4 }
            if (r2 <= r10) goto L_0x0046
            r12.f104p = r2     // Catch:{ Exception -> 0x00d4 }
        L_0x0046:
            int r10 = r12.f100l     // Catch:{ Exception -> 0x00d4 }
            r11 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r10 != r11) goto L_0x004e
            r12.f100l = r3     // Catch:{ Exception -> 0x00d4 }
        L_0x004e:
            if (r4 == 0) goto L_0x0090
            com.moat.analytics.mobile.mpub.h$a r3 = r12.f101m     // Catch:{ Exception -> 0x00d4 }
            com.moat.analytics.mobile.mpub.h$a r4 = com.moat.analytics.mobile.mpub.C0316h.C0318a.UNINITIALIZED     // Catch:{ Exception -> 0x00d4 }
            if (r3 != r4) goto L_0x005d
            com.moat.analytics.mobile.mpub.MoatAdEventType r9 = com.moat.analytics.mobile.mpub.MoatAdEventType.AD_EVT_START     // Catch:{ Exception -> 0x00d4 }
            com.moat.analytics.mobile.mpub.h$a r3 = com.moat.analytics.mobile.mpub.C0316h.C0318a.PLAYING     // Catch:{ Exception -> 0x00d4 }
        L_0x005a:
            r12.f101m = r3     // Catch:{ Exception -> 0x00d4 }
            goto L_0x009b
        L_0x005d:
            com.moat.analytics.mobile.mpub.h$a r3 = r12.f101m     // Catch:{ Exception -> 0x00d4 }
            com.moat.analytics.mobile.mpub.h$a r4 = com.moat.analytics.mobile.mpub.C0316h.C0318a.PAUSED     // Catch:{ Exception -> 0x00d4 }
            if (r3 != r4) goto L_0x0068
            com.moat.analytics.mobile.mpub.MoatAdEventType r9 = com.moat.analytics.mobile.mpub.MoatAdEventType.AD_EVT_PLAYING     // Catch:{ Exception -> 0x00d4 }
            com.moat.analytics.mobile.mpub.h$a r3 = com.moat.analytics.mobile.mpub.C0316h.C0318a.PLAYING     // Catch:{ Exception -> 0x00d4 }
            goto L_0x005a
        L_0x0068:
            double r3 = (double) r2
            java.lang.Double.isNaN(r3)
            double r3 = r3 / r5
            double r3 = java.lang.Math.floor(r3)     // Catch:{ Exception -> 0x00d4 }
            int r3 = (int) r3     // Catch:{ Exception -> 0x00d4 }
            int r3 = r3 - r0
            r4 = -1
            if (r3 <= r4) goto L_0x009b
            r4 = 3
            if (r3 >= r4) goto L_0x009b
            com.moat.analytics.mobile.mpub.MoatAdEventType[] r4 = f72g     // Catch:{ Exception -> 0x00d4 }
            r3 = r4[r3]     // Catch:{ Exception -> 0x00d4 }
            java.util.Map r4 = r12.f73h     // Catch:{ Exception -> 0x00d4 }
            boolean r4 = r4.containsKey(r3)     // Catch:{ Exception -> 0x00d4 }
            if (r4 != 0) goto L_0x009b
            java.util.Map r4 = r12.f73h     // Catch:{ Exception -> 0x00d4 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r0)     // Catch:{ Exception -> 0x00d4 }
            r4.put(r3, r5)     // Catch:{ Exception -> 0x00d4 }
            r9 = r3
            goto L_0x009b
        L_0x0090:
            com.moat.analytics.mobile.mpub.h$a r3 = r12.f101m     // Catch:{ Exception -> 0x00d4 }
            com.moat.analytics.mobile.mpub.h$a r4 = com.moat.analytics.mobile.mpub.C0316h.C0318a.PAUSED     // Catch:{ Exception -> 0x00d4 }
            if (r3 == r4) goto L_0x009b
            com.moat.analytics.mobile.mpub.MoatAdEventType r9 = com.moat.analytics.mobile.mpub.MoatAdEventType.AD_EVT_PAUSED     // Catch:{ Exception -> 0x00d4 }
            com.moat.analytics.mobile.mpub.h$a r3 = com.moat.analytics.mobile.mpub.C0316h.C0318a.PAUSED     // Catch:{ Exception -> 0x00d4 }
            goto L_0x005a
        L_0x009b:
            if (r9 == 0) goto L_0x009f
            r3 = 1
            goto L_0x00a0
        L_0x009f:
            r3 = 0
        L_0x00a0:
            if (r3 != 0) goto L_0x00bd
            double r4 = r12.f103o     // Catch:{ Exception -> 0x00d4 }
            boolean r4 = java.lang.Double.isNaN(r4)     // Catch:{ Exception -> 0x00d4 }
            if (r4 != 0) goto L_0x00bd
            double r4 = r12.f103o     // Catch:{ Exception -> 0x00d4 }
            double r4 = r4 - r7
            double r4 = java.lang.Math.abs(r4)     // Catch:{ Exception -> 0x00d4 }
            r10 = 4587366580439587226(0x3fa999999999999a, double:0.05)
            int r6 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r6 <= 0) goto L_0x00bd
            com.moat.analytics.mobile.mpub.MoatAdEventType r9 = com.moat.analytics.mobile.mpub.MoatAdEventType.AD_EVT_VOLUME_CHANGE     // Catch:{ Exception -> 0x00d4 }
            r3 = 1
        L_0x00bd:
            if (r3 == 0) goto L_0x00cf
            com.moat.analytics.mobile.mpub.MoatAdEvent r3 = new com.moat.analytics.mobile.mpub.MoatAdEvent     // Catch:{ Exception -> 0x00d4 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Exception -> 0x00d4 }
            java.lang.Double r4 = r12.mo10404k()     // Catch:{ Exception -> 0x00d4 }
            r3.<init>(r9, r2, r4)     // Catch:{ Exception -> 0x00d4 }
            r12.dispatchEvent(r3)     // Catch:{ Exception -> 0x00d4 }
        L_0x00cf:
            r12.f103o = r7     // Catch:{ Exception -> 0x00d4 }
            r12.f105q = r1     // Catch:{ Exception -> 0x00d4 }
            return r0
        L_0x00d4:
            int r2 = r12.f105q
            int r3 = r2 + 1
            r12.f105q = r3
            r3 = 5
            if (r2 >= r3) goto L_0x00df
            r1 = 1
        L_0x00df:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.mpub.C0316h.mo10424s():boolean");
    }

    public void setPlayerVolume(Double d) {
        super.setPlayerVolume(d);
        this.f103o = mo10403j().doubleValue();
    }

    public void stopTracking() {
        try {
            dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_COMPLETE));
            super.stopTracking();
        } catch (Exception e) {
            C0330n.m214a(e);
        }
    }
}
