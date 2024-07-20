package com.firebase.jobdispatcher;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import android.util.Log;
import android.util.Pair;
import androidx.collection.SimpleArrayMap;
import com.firebase.jobdispatcher.ExecutionDelegator;

public class GooglePlayReceiver extends Service implements ExecutionDelegator.JobFinishedCallback {
    static final String ACTION_EXECUTE = "com.google.android.gms.gcm.ACTION_TASK_READY";
    static final String ACTION_INITIALIZE = "com.google.android.gms.gcm.SERVICE_ACTION_INITIALIZE";
    private static final String ERROR_NO_DATA = "No data provided, terminating";
    private static final String ERROR_NULL_INTENT = "Null Intent passed, terminating";
    private static final String ERROR_UNKNOWN_ACTION = "Unknown action received, terminating";
    static final String TAG = "FJD.GooglePlayReceiver";
    private static final JobCoder prefixedCoder = new JobCoder("com.firebase.jobdispatcher.", true);
    private final GooglePlayCallbackExtractor callbackExtractor = new GooglePlayCallbackExtractor();
    private SimpleArrayMap<String, SimpleArrayMap<String, JobCallback>> callbacks = new SimpleArrayMap<>(1);
    private ExecutionDelegator executionDelegator;
    private int latestStartId;
    private final Object lock = new Object();
    Messenger serviceMessenger;

    private static void sendResultSafely(JobCallback jobCallback, int i) {
        try {
            jobCallback.jobFinished(i);
        } catch (Throwable th) {
            Log.e(TAG, "Encountered error running callback", th.getCause());
        }
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        try {
            super.onStartCommand(intent, i, i2);
            if (intent == null) {
                Log.w(TAG, ERROR_NULL_INTENT);
                synchronized (this) {
                    this.latestStartId = i2;
                    if (this.callbacks.isEmpty()) {
                        stopSelf(this.latestStartId);
                    }
                }
                return 2;
            }
            String action = intent.getAction();
            if (ACTION_EXECUTE.equals(action)) {
                getExecutionDelegator().executeJob(prepareJob(intent));
                synchronized (this) {
                    this.latestStartId = i2;
                    if (this.callbacks.isEmpty()) {
                        stopSelf(this.latestStartId);
                    }
                }
                return 2;
            } else if (ACTION_INITIALIZE.equals(action)) {
                synchronized (this) {
                    this.latestStartId = i2;
                    if (this.callbacks.isEmpty()) {
                        stopSelf(this.latestStartId);
                    }
                }
                return 2;
            } else {
                Log.e(TAG, ERROR_UNKNOWN_ACTION);
                synchronized (this) {
                    this.latestStartId = i2;
                    if (this.callbacks.isEmpty()) {
                        stopSelf(this.latestStartId);
                    }
                }
                return 2;
            }
        } catch (Throwable th) {
            synchronized (this) {
                this.latestStartId = i2;
                if (this.callbacks.isEmpty()) {
                    stopSelf(this.latestStartId);
                }
                throw th;
            }
        }
    }

    public IBinder onBind(Intent intent) {
        if (intent == null || Build.VERSION.SDK_INT < 21 || !ACTION_EXECUTE.equals(intent.getAction())) {
            return null;
        }
        return getServiceMessenger().getBinder();
    }

    private Messenger getServiceMessenger() {
        Messenger messenger;
        synchronized (this.lock) {
            if (this.serviceMessenger == null) {
                this.serviceMessenger = new Messenger(new GooglePlayMessageHandler(Looper.getMainLooper(), this));
            }
            messenger = this.serviceMessenger;
        }
        return messenger;
    }

    /* access modifiers changed from: package-private */
    public ExecutionDelegator getExecutionDelegator() {
        ExecutionDelegator executionDelegator2;
        synchronized (this.lock) {
            if (this.executionDelegator == null) {
                this.executionDelegator = new ExecutionDelegator(this, this);
            }
            executionDelegator2 = this.executionDelegator;
        }
        return executionDelegator2;
    }

    /* access modifiers changed from: package-private */
    public JobInvocation prepareJob(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            Log.e(TAG, ERROR_NO_DATA);
            return null;
        }
        Pair<JobCallback, Bundle> extractCallback = this.callbackExtractor.extractCallback(extras);
        if (extractCallback != null) {
            return prepareJob((JobCallback) extractCallback.first, (Bundle) extractCallback.second);
        }
        Log.i(TAG, "no callback found");
        return null;
    }

    /* access modifiers changed from: package-private */
    public JobInvocation prepareJob(JobCallback jobCallback, Bundle bundle) {
        JobInvocation decodeIntentBundle = prefixedCoder.decodeIntentBundle(bundle);
        if (decodeIntentBundle == null) {
            Log.e(TAG, "unable to decode job");
            sendResultSafely(jobCallback, 2);
            return null;
        }
        synchronized (this.lock) {
            SimpleArrayMap simpleArrayMap = this.callbacks.get(decodeIntentBundle.getService());
            if (simpleArrayMap == null) {
                simpleArrayMap = new SimpleArrayMap(1);
                this.callbacks.put(decodeIntentBundle.getService(), simpleArrayMap);
            }
            simpleArrayMap.put(decodeIntentBundle.getTag(), jobCallback);
        }
        return decodeIntentBundle;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001f, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0077, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onJobFinished(com.firebase.jobdispatcher.JobInvocation r7, int r8) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.lock
            monitor-enter(r0)
            androidx.collection.SimpleArrayMap<java.lang.String, androidx.collection.SimpleArrayMap<java.lang.String, com.firebase.jobdispatcher.JobCallback>> r1 = r6.callbacks     // Catch:{ all -> 0x0078 }
            java.lang.String r2 = r7.getService()     // Catch:{ all -> 0x0078 }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x0078 }
            androidx.collection.SimpleArrayMap r1 = (androidx.collection.SimpleArrayMap) r1     // Catch:{ all -> 0x0078 }
            if (r1 != 0) goto L_0x0020
            androidx.collection.SimpleArrayMap<java.lang.String, androidx.collection.SimpleArrayMap<java.lang.String, com.firebase.jobdispatcher.JobCallback>> r7 = r6.callbacks     // Catch:{ all -> 0x0087 }
            boolean r7 = r7.isEmpty()     // Catch:{ all -> 0x0087 }
            if (r7 == 0) goto L_0x001e
            int r7 = r6.latestStartId     // Catch:{ all -> 0x0087 }
            r6.stopSelf(r7)     // Catch:{ all -> 0x0087 }
        L_0x001e:
            monitor-exit(r0)     // Catch:{ all -> 0x0087 }
            return
        L_0x0020:
            java.lang.String r2 = r7.getTag()     // Catch:{ all -> 0x0078 }
            java.lang.Object r2 = r1.remove(r2)     // Catch:{ all -> 0x0078 }
            com.firebase.jobdispatcher.JobCallback r2 = (com.firebase.jobdispatcher.JobCallback) r2     // Catch:{ all -> 0x0078 }
            if (r2 == 0) goto L_0x005a
            java.lang.String r3 = "FJD.GooglePlayReceiver"
            r4 = 2
            boolean r3 = android.util.Log.isLoggable(r3, r4)     // Catch:{ all -> 0x0078 }
            if (r3 == 0) goto L_0x0057
            java.lang.String r3 = "FJD.GooglePlayReceiver"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0078 }
            r4.<init>()     // Catch:{ all -> 0x0078 }
            java.lang.String r5 = "sending jobFinished for "
            r4.append(r5)     // Catch:{ all -> 0x0078 }
            java.lang.String r5 = r7.getTag()     // Catch:{ all -> 0x0078 }
            r4.append(r5)     // Catch:{ all -> 0x0078 }
            java.lang.String r5 = " = "
            r4.append(r5)     // Catch:{ all -> 0x0078 }
            r4.append(r8)     // Catch:{ all -> 0x0078 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0078 }
            android.util.Log.v(r3, r4)     // Catch:{ all -> 0x0078 }
        L_0x0057:
            sendResultSafely(r2, r8)     // Catch:{ all -> 0x0078 }
        L_0x005a:
            boolean r8 = r1.isEmpty()     // Catch:{ all -> 0x0078 }
            if (r8 == 0) goto L_0x0069
            androidx.collection.SimpleArrayMap<java.lang.String, androidx.collection.SimpleArrayMap<java.lang.String, com.firebase.jobdispatcher.JobCallback>> r8 = r6.callbacks     // Catch:{ all -> 0x0078 }
            java.lang.String r7 = r7.getService()     // Catch:{ all -> 0x0078 }
            r8.remove(r7)     // Catch:{ all -> 0x0078 }
        L_0x0069:
            androidx.collection.SimpleArrayMap<java.lang.String, androidx.collection.SimpleArrayMap<java.lang.String, com.firebase.jobdispatcher.JobCallback>> r7 = r6.callbacks     // Catch:{ all -> 0x0087 }
            boolean r7 = r7.isEmpty()     // Catch:{ all -> 0x0087 }
            if (r7 == 0) goto L_0x0076
            int r7 = r6.latestStartId     // Catch:{ all -> 0x0087 }
            r6.stopSelf(r7)     // Catch:{ all -> 0x0087 }
        L_0x0076:
            monitor-exit(r0)     // Catch:{ all -> 0x0087 }
            return
        L_0x0078:
            r7 = move-exception
            androidx.collection.SimpleArrayMap<java.lang.String, androidx.collection.SimpleArrayMap<java.lang.String, com.firebase.jobdispatcher.JobCallback>> r8 = r6.callbacks     // Catch:{ all -> 0x0087 }
            boolean r8 = r8.isEmpty()     // Catch:{ all -> 0x0087 }
            if (r8 == 0) goto L_0x0086
            int r8 = r6.latestStartId     // Catch:{ all -> 0x0087 }
            r6.stopSelf(r8)     // Catch:{ all -> 0x0087 }
        L_0x0086:
            throw r7     // Catch:{ all -> 0x0087 }
        L_0x0087:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0087 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.jobdispatcher.GooglePlayReceiver.onJobFinished(com.firebase.jobdispatcher.JobInvocation, int):void");
    }

    static JobCoder getJobCoder() {
        return prefixedCoder;
    }
}
