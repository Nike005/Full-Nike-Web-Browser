package com.moat.analytics.mobile.mpub;

import android.app.Application;
import android.media.AudioManager;

/* renamed from: com.moat.analytics.mobile.mpub.l */
public class C0328l {

    /* renamed from: a */
    private static final Long f155a = 200L;

    /* renamed from: b */
    private static final C0328l f156b = new C0328l();

    /* renamed from: c */
    private AudioManager f157c;

    /* renamed from: d */
    private double f158d = 0.0d;

    /* renamed from: e */
    private Long f159e;

    private C0328l() {
        m200c();
    }

    /* renamed from: a */
    public static C0328l m199a() {
        return f156b;
    }

    /* renamed from: c */
    private void m200c() {
        Application a = C0298a.m71a();
        if (a != null) {
            this.f157c = (AudioManager) a.getSystemService("audio");
        }
    }

    /* renamed from: d */
    private AudioManager m201d() {
        if (this.f157c == null) {
            m200c();
        }
        return this.f157c;
    }

    /* renamed from: e */
    private void m202e() {
        try {
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            if (this.f159e == null || valueOf.longValue() - this.f159e.longValue() > f155a.longValue()) {
                this.f159e = valueOf;
                AudioManager d = m201d();
                if (d != null) {
                    double streamVolume = (double) d.getStreamVolume(3);
                    double streamMaxVolume = (double) d.getStreamMaxVolume(3);
                    Double.isNaN(streamVolume);
                    Double.isNaN(streamMaxVolume);
                    this.f158d = streamVolume / streamMaxVolume;
                }
            }
        } catch (Exception e) {
            C0330n.m214a(e);
            this.f158d = 0.0d;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public double mo10452b() {
        try {
            m202e();
            return this.f158d;
        } catch (Exception e) {
            C0330n.m214a(e);
            return 0.0d;
        }
    }
}
