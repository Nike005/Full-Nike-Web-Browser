package com.appnext.base.services.p083a;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import com.appnext.base.p078a.p080b.C4887c;
import com.appnext.base.p082b.C4896a;
import com.appnext.base.p082b.C4898c;
import com.appnext.base.services.OperationJobService;
import java.util.List;
import org.apache.commons.lang3.time.DateUtils;
import org.htmlcleaner.CleanerProperties;

/* renamed from: com.appnext.base.services.a.b */
public final class C4919b extends C4920c {

    /* renamed from: eF */
    private static final int f4672eF = 900000;

    /* renamed from: eG */
    private JobScheduler f4673eG;
    private Context mContext;

    public C4919b(Context context) {
        try {
            Context applicationContext = context.getApplicationContext();
            this.mContext = applicationContext;
            this.f4673eG = (JobScheduler) applicationContext.getSystemService("jobscheduler");
        } catch (Throwable unused) {
        }
    }

    /* renamed from: b */
    public final void mo41063b(C4887c cVar) {
        try {
            this.f4673eG.cancel(cVar.mo40963ap().hashCode());
        } catch (Throwable unused) {
        }
    }

    /* renamed from: g */
    public final void mo41065g(List<C4887c> list) {
        try {
            this.f4673eG.cancelAll();
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo41061a(C4887c cVar, long j, long j2) {
        m6675a(cVar, j, j2, (Bundle) null);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo41064b(C4887c cVar, long j, long j2) {
        m6675a(cVar, j, DateUtils.MILLIS_PER_DAY, (Bundle) null);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo41062a(C4887c cVar, long j, Bundle bundle) {
        m6675a(cVar, j, 0, bundle);
    }

    /* renamed from: a */
    private void m6675a(C4887c cVar, long j, long j2, Bundle bundle) {
        PersistableBundle a;
        try {
            JobInfo.Builder requiredNetworkType = new JobInfo.Builder(cVar.mo40963ap().hashCode(), new ComponentName(this.mContext, OperationJobService.class)).setPersisted(true).setRequiredNetworkType(1);
            PersistableBundle e = C4898c.m6570e(cVar);
            if (!(bundle == null || (a = C4896a.m6557a(bundle)) == null)) {
                e.putPersistableBundle(C4920c.f4674eH, a);
            }
            if (j2 > 0 && j2 < 900000) {
                j2 = 900000;
            }
            if (j > System.currentTimeMillis()) {
                requiredNetworkType.setMinimumLatency(Math.max(j - System.currentTimeMillis(), 60000));
                e.putString(OperationJobService.SCHEDULE, CleanerProperties.BOOL_ATT_TRUE);
            } else if (j2 > 0 && (Build.VERSION.SDK_INT < 24 || j2 >= 900000)) {
                requiredNetworkType.setPeriodic(j2);
            }
            requiredNetworkType.setExtras(e);
            this.f4673eG.schedule(requiredNetworkType.build());
        } catch (Throwable unused) {
        }
    }
}
