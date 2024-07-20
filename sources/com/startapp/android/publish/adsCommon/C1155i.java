package com.startapp.android.publish.adsCommon;

import android.content.Context;
import android.os.Handler;
import com.appnext.base.services.OperationJobService;
import com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener;
import com.startapp.android.publish.adsCommon.p031d.C1117b;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.common.p043a.C1270g;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.startapp.android.publish.adsCommon.i */
/* compiled from: StartAppSDK */
public class C1155i {

    /* renamed from: a */
    private static final boolean f1219a = MetaData.getInstance().isSupportIABViewability();

    /* renamed from: b */
    private Handler f1220b = new Handler();

    /* renamed from: c */
    private long f1221c;

    /* renamed from: d */
    private Context f1222d;

    /* renamed from: e */
    private long f1223e = -1;

    /* renamed from: f */
    private long f1224f;

    /* renamed from: g */
    private boolean f1225g;

    /* renamed from: h */
    private boolean f1226h;

    /* renamed from: i */
    private String[] f1227i;

    /* renamed from: j */
    private C1117b f1228j;

    /* renamed from: k */
    private AtomicBoolean f1229k = new AtomicBoolean(false);

    public C1155i(Context context, String[] strArr, C1117b bVar, long j) {
        this.f1222d = context.getApplicationContext();
        this.f1227i = strArr;
        this.f1228j = bVar;
        this.f1221c = j;
    }

    /* renamed from: a */
    public void mo14945a() {
        C1270g.m2078a("ScheduledImpression", 4, OperationJobService.SCHEDULE);
        if (mo14949c()) {
            C1270g.m2078a("ScheduledImpression", 3, "Already sent impression. Must call cancel(true/false) to reschedule");
        } else if (f1219a) {
            m1581a(this.f1221c);
        } else {
            C1270g.m2078a("ScheduledImpression", 3, "Delay feature disabled, sending impression now!");
            mo14948b(true);
        }
    }

    /* renamed from: b */
    public void mo14947b() {
        if (this.f1225g && this.f1226h) {
            C1270g.m2078a("ScheduledImpression", 4, "pause");
            this.f1220b.removeCallbacksAndMessages((Object) null);
            long currentTimeMillis = System.currentTimeMillis();
            this.f1223e = currentTimeMillis;
            this.f1221c -= currentTimeMillis - this.f1224f;
            this.f1226h = false;
        }
    }

    /* renamed from: a */
    public void mo14946a(boolean z) {
        if (this.f1225g) {
            C1270g.m2078a("ScheduledImpression", 4, "cancel(" + z + ")");
            mo14948b(z);
            m1582d();
        }
    }

    /* renamed from: c */
    public boolean mo14949c() {
        return this.f1229k.get();
    }

    /* renamed from: a */
    private void m1581a(long j) {
        if (!this.f1226h) {
            this.f1226h = true;
            if (!this.f1225g) {
                this.f1225g = true;
            }
            C1270g.m2078a("ScheduledImpression", 3, "Scheduling timer to: " + j + " millis, Num urls = " + this.f1227i.length);
            this.f1224f = System.currentTimeMillis();
            this.f1220b.postDelayed(new Runnable() {
                public void run() {
                    C1270g.m2078a("ScheduledImpression", 4, "Timer elapsed");
                    C1155i.this.mo14948b(true);
                }
            }, j);
            return;
        }
        C1270g.m2078a("ScheduledImpression", 3, "Already running");
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo14948b(boolean z) {
        if (!this.f1229k.compareAndSet(false, true)) {
            C1270g.m2078a("ScheduledImpression", 4, "Already sent");
        } else if (z) {
            C1270g.m2078a("ScheduledImpression", 3, "Sending impression");
            C1103c.m1381a(this.f1222d, this.f1227i, this.f1228j);
        } else {
            C1270g.m2078a("ScheduledImpression", 3, "Sending non-impression");
            C1103c.m1383a(this.f1222d, this.f1227i, this.f1228j.getAdTag(), AdDisplayListener.NotDisplayedReason.AD_CLOSED_TOO_QUICKLY.toString());
        }
    }

    /* renamed from: d */
    private void m1582d() {
        C1270g.m2078a("ScheduledImpression", 4, "reset");
        this.f1225g = false;
        this.f1220b.removeCallbacksAndMessages((Object) null);
        this.f1226h = false;
        this.f1223e = -1;
        this.f1224f = 0;
    }
}
