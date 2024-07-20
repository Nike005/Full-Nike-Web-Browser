package com.appnext.base.services;

import android.app.job.JobParameters;
import android.os.Bundle;
import android.os.PersistableBundle;
import com.appnext.base.C4878a;
import com.appnext.base.operations.AppnextOperationJobService;
import com.appnext.base.operations.C4910a;
import com.appnext.base.p078a.p080b.C4887c;
import com.appnext.base.p082b.C4898c;
import com.appnext.base.p082b.C4901e;
import com.appnext.base.p082b.C4905i;
import com.appnext.base.services.p083a.C4920c;
import com.appnext.base.services.p084b.C4922a;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class OperationJobService extends AppnextOperationJobService {
    public static final String SCHEDULE = "schedule";

    public int onRunJob(JobParameters jobParameters) {
        C4887c cVar;
        PersistableBundle persistableBundle;
        try {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            C4901e.init(getApplicationContext());
            C4905i.m6591aR().init(getApplicationContext());
            PersistableBundle extras = jobParameters.getExtras();
            Bundle bundle = null;
            if (extras == null) {
                cVar = null;
            } else {
                String string = extras.getString("key", "");
                cVar = new C4887c(extras.getString("status", ""), extras.getString(C4898c.f4603eQ, ""), extras.getString(C4898c.f4604eR, ""), extras.getString(C4898c.f4601eO, ""), extras.getString(C4898c.f4602eP, ""), string, extras.getString("service_key", ""), extras.getString("data", (String) null));
            }
            if (cVar == null) {
                m6665b(jobParameters);
                return 0;
            } else if (m6664a(extras)) {
                m6665b(jobParameters);
                C4922a.m6693d(getApplicationContext()).mo41070a(cVar, true);
                return 0;
            } else {
                if (!(extras == null || (persistableBundle = extras.getPersistableBundle(C4920c.f4674eH)) == null)) {
                    bundle = new Bundle();
                    bundle.putAll(persistableBundle);
                }
                final JobParameters jobParameters2 = jobParameters;
                try {
                    new C4921b().mo41069a(getApplicationContext(), cVar.getKey(), (String) null, bundle, (Object) null, new C4910a.C4911a() {
                        /* renamed from: aH */
                        public final void mo41049aH() {
                            OperationJobService.this.m6665b(jobParameters2);
                            countDownLatch.countDown();
                        }

                        /* renamed from: b */
                        public final void mo41050b(C4878a aVar) {
                            OperationJobService.this.m6665b(jobParameters2);
                            countDownLatch.countDown();
                        }
                    });
                    countDownLatch.await(10, TimeUnit.SECONDS);
                } catch (Throwable unused) {
                }
                return 0;
            }
        } catch (Throwable unused2) {
        }
    }

    /* renamed from: a */
    private static boolean m6664a(PersistableBundle persistableBundle) {
        if (persistableBundle == null) {
            return false;
        }
        try {
            return Boolean.valueOf(persistableBundle.getString(SCHEDULE, "false")).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m6665b(JobParameters jobParameters) {
        try {
            mo41023a(jobParameters);
        } catch (Throwable unused) {
        }
    }
}
