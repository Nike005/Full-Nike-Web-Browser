package com.firebase.jobdispatcher;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class JobService extends Service {
    static final String ACTION_EXECUTE = "com.firebase.jobdispatcher.ACTION_EXECUTE";
    public static final int RESULT_FAIL_NORETRY = 2;
    public static final int RESULT_FAIL_RETRY = 1;
    public static final int RESULT_SUCCESS = 0;
    static final String TAG = "FJD.JobService";
    private LocalBinder binder = new LocalBinder();
    private final SimpleArrayMap<String, JobCallback> runningJobs = new SimpleArrayMap<>(1);

    @Retention(RetentionPolicy.SOURCE)
    public @interface JobResult {
    }

    public final void onStart(Intent intent, int i) {
    }

    public abstract boolean onStartJob(JobParameters jobParameters);

    public abstract boolean onStopJob(JobParameters jobParameters);

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void start(com.firebase.jobdispatcher.JobParameters r7, android.os.Message r8) {
        /*
            r6 = this;
            androidx.collection.SimpleArrayMap<java.lang.String, com.firebase.jobdispatcher.JobService$JobCallback> r0 = r6.runningJobs
            monitor-enter(r0)
            androidx.collection.SimpleArrayMap<java.lang.String, com.firebase.jobdispatcher.JobService$JobCallback> r1 = r6.runningJobs     // Catch:{ all -> 0x0050 }
            java.lang.String r2 = r7.getTag()     // Catch:{ all -> 0x0050 }
            boolean r1 = r1.containsKey(r2)     // Catch:{ all -> 0x0050 }
            r2 = 0
            if (r1 == 0) goto L_0x0028
            java.lang.String r8 = "FJD.JobService"
            java.util.Locale r1 = java.util.Locale.US     // Catch:{ all -> 0x0050 }
            java.lang.String r3 = "Job with tag = %s was already running."
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x0050 }
            java.lang.String r7 = r7.getTag()     // Catch:{ all -> 0x0050 }
            r4[r2] = r7     // Catch:{ all -> 0x0050 }
            java.lang.String r7 = java.lang.String.format(r1, r3, r4)     // Catch:{ all -> 0x0050 }
            android.util.Log.w(r8, r7)     // Catch:{ all -> 0x0050 }
            monitor-exit(r0)     // Catch:{ all -> 0x0050 }
            return
        L_0x0028:
            androidx.collection.SimpleArrayMap<java.lang.String, com.firebase.jobdispatcher.JobService$JobCallback> r1 = r6.runningJobs     // Catch:{ all -> 0x0050 }
            java.lang.String r3 = r7.getTag()     // Catch:{ all -> 0x0050 }
            com.firebase.jobdispatcher.JobService$JobCallback r4 = new com.firebase.jobdispatcher.JobService$JobCallback     // Catch:{ all -> 0x0050 }
            r5 = 0
            r4.<init>(r8)     // Catch:{ all -> 0x0050 }
            r1.put(r3, r4)     // Catch:{ all -> 0x0050 }
            boolean r8 = r6.onStartJob(r7)     // Catch:{ all -> 0x0050 }
            if (r8 != 0) goto L_0x004e
            androidx.collection.SimpleArrayMap<java.lang.String, com.firebase.jobdispatcher.JobService$JobCallback> r8 = r6.runningJobs     // Catch:{ all -> 0x0050 }
            java.lang.String r7 = r7.getTag()     // Catch:{ all -> 0x0050 }
            java.lang.Object r7 = r8.remove(r7)     // Catch:{ all -> 0x0050 }
            com.firebase.jobdispatcher.JobService$JobCallback r7 = (com.firebase.jobdispatcher.JobService.JobCallback) r7     // Catch:{ all -> 0x0050 }
            if (r7 == 0) goto L_0x004e
            r7.sendResult(r2)     // Catch:{ all -> 0x0050 }
        L_0x004e:
            monitor-exit(r0)     // Catch:{ all -> 0x0050 }
            return
        L_0x0050:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0050 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.jobdispatcher.JobService.start(com.firebase.jobdispatcher.JobParameters, android.os.Message):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0022, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void stop(com.firebase.jobdispatcher.JobInvocation r4) {
        /*
            r3 = this;
            androidx.collection.SimpleArrayMap<java.lang.String, com.firebase.jobdispatcher.JobService$JobCallback> r0 = r3.runningJobs
            monitor-enter(r0)
            androidx.collection.SimpleArrayMap<java.lang.String, com.firebase.jobdispatcher.JobService$JobCallback> r1 = r3.runningJobs     // Catch:{ all -> 0x0031 }
            java.lang.String r2 = r4.getTag()     // Catch:{ all -> 0x0031 }
            java.lang.Object r1 = r1.remove(r2)     // Catch:{ all -> 0x0031 }
            com.firebase.jobdispatcher.JobService$JobCallback r1 = (com.firebase.jobdispatcher.JobService.JobCallback) r1     // Catch:{ all -> 0x0031 }
            if (r1 != 0) goto L_0x0023
            java.lang.String r4 = "FJD.JobService"
            r1 = 3
            boolean r4 = android.util.Log.isLoggable(r4, r1)     // Catch:{ all -> 0x0031 }
            if (r4 == 0) goto L_0x0021
            java.lang.String r4 = "FJD.JobService"
            java.lang.String r1 = "Provided job has already been executed."
            android.util.Log.d(r4, r1)     // Catch:{ all -> 0x0031 }
        L_0x0021:
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
            return
        L_0x0023:
            boolean r4 = r3.onStopJob(r4)     // Catch:{ all -> 0x0031 }
            if (r4 == 0) goto L_0x002b
            r4 = 1
            goto L_0x002c
        L_0x002b:
            r4 = 0
        L_0x002c:
            r1.sendResult(r4)     // Catch:{ all -> 0x0031 }
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
            return
        L_0x0031:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.jobdispatcher.JobService.stop(com.firebase.jobdispatcher.JobInvocation):void");
    }

    public final void jobFinished(JobParameters jobParameters, boolean z) {
        if (jobParameters == null) {
            Log.e(TAG, "jobFinished called with a null JobParameters");
            return;
        }
        synchronized (this.runningJobs) {
            JobCallback remove = this.runningJobs.remove(jobParameters.getTag());
            if (remove != null) {
                remove.sendResult(z ? 1 : 0);
            }
        }
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        stopSelf(i2);
        return 2;
    }

    public final IBinder onBind(Intent intent) {
        return this.binder;
    }

    public final boolean onUnbind(Intent intent) {
        synchronized (this.runningJobs) {
            for (int size = this.runningJobs.size() - 1; size >= 0; size--) {
                JobCallback jobCallback = this.runningJobs.get(this.runningJobs.keyAt(size));
                if (jobCallback != null) {
                    jobCallback.sendResult(onStopJob((JobParameters) jobCallback.message.obj) ? 1 : 2);
                }
            }
        }
        return super.onUnbind(intent);
    }

    public final void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    /* access modifiers changed from: protected */
    public final void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(fileDescriptor, printWriter, strArr);
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public final void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
    }

    private static final class JobCallback {
        public final Message message;

        private JobCallback(Message message2) {
            this.message = message2;
        }

        /* access modifiers changed from: package-private */
        public void sendResult(int i) {
            this.message.arg1 = i;
            this.message.sendToTarget();
        }
    }

    class LocalBinder extends Binder {
        LocalBinder() {
        }

        /* access modifiers changed from: package-private */
        public JobService getService() {
            return JobService.this;
        }
    }
}
