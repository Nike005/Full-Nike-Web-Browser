package com.appnext.base.operations;

import android.app.job.JobParameters;
import android.app.job.JobService;
import java.util.HashMap;
import java.util.Map;

public abstract class AppnextOperationJobService extends JobService {

    /* renamed from: ej */
    private final Map<JobParameters, AsyncJobTask> f4649ej = new HashMap();

    /* renamed from: ek */
    private AsyncJobTask f4650ek;

    public abstract int onRunJob(JobParameters jobParameters);

    public boolean onStartJob(JobParameters jobParameters) {
        this.f4650ek = new AsyncJobTask(this, jobParameters);
        synchronized (this.f4649ej) {
            this.f4649ej.put(jobParameters, this.f4650ek);
        }
        this.f4650ek.execute(new Void[0]);
        return true;
    }

    public boolean onStopJob(JobParameters jobParameters) {
        synchronized (this.f4649ej) {
            AsyncJobTask remove = this.f4649ej.remove(jobParameters);
            if (remove == null) {
                return false;
            }
            remove.cancel(true);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo41023a(JobParameters jobParameters) {
        synchronized (this.f4649ej) {
            this.f4649ej.remove(jobParameters);
        }
        this.f4650ek.finishJob();
    }
}
