package com.startapp.android.publish.common.metaData;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import com.startapp.common.p044b.C1279a;
import com.startapp.common.p044b.p045a.C1285b;

/* compiled from: StartAppSDK */
public class PeriodicJobService extends JobService {

    /* renamed from: a */
    private static final String f1394a = "PeriodicJobService";

    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }

    public boolean onStartJob(final JobParameters jobParameters) {
        C1279a.m2109a((Context) this);
        boolean a = C1279a.m2120a(jobParameters, (C1285b.C1287b) new C1285b.C1287b() {
            /* renamed from: a */
            public void mo15139a(C1285b.C1286a aVar) {
                PeriodicJobService.this.jobFinished(jobParameters, false);
            }
        });
        C1279a.m2114a(3, f1394a, "onStartJob: RunnerManager.runJob" + a, (Throwable) null);
        return a;
    }
}
