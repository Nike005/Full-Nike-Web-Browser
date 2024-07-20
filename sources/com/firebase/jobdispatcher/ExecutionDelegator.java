package com.firebase.jobdispatcher;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import java.lang.ref.WeakReference;

class ExecutionDelegator {
    static final int JOB_FINISHED = 1;
    static final String TAG = "FJD.ExternalReceiver";
    private final Context context;
    private final JobFinishedCallback jobFinishedCallback;
    private final ResponseHandler responseHandler = new ResponseHandler(Looper.getMainLooper(), new WeakReference(this));
    private final SimpleArrayMap<JobInvocation, JobServiceConnection> serviceConnections = new SimpleArrayMap<>();

    interface JobFinishedCallback {
        void onJobFinished(JobInvocation jobInvocation, int i);
    }

    ExecutionDelegator(Context context2, JobFinishedCallback jobFinishedCallback2) {
        this.context = context2;
        this.jobFinishedCallback = jobFinishedCallback2;
    }

    /* access modifiers changed from: package-private */
    public boolean executeJob(JobInvocation jobInvocation) {
        boolean bindService;
        if (jobInvocation == null) {
            return false;
        }
        JobServiceConnection jobServiceConnection = new JobServiceConnection(jobInvocation, this.responseHandler.obtainMessage(1));
        synchronized (this.serviceConnections) {
            if (this.serviceConnections.put(jobInvocation, jobServiceConnection) != null) {
                Log.e(TAG, "Received execution request for already running job");
            }
            bindService = this.context.bindService(createBindIntent(jobInvocation), jobServiceConnection, 1);
        }
        return bindService;
    }

    private Intent createBindIntent(JobParameters jobParameters) {
        Intent intent = new Intent("com.firebase.jobdispatcher.ACTION_EXECUTE");
        intent.setClassName(this.context, jobParameters.getService());
        return intent;
    }

    /* access modifiers changed from: package-private */
    public void stopJob(JobInvocation jobInvocation) {
        synchronized (this.serviceConnections) {
            JobServiceConnection remove = this.serviceConnections.remove(jobInvocation);
            if (remove != null) {
                remove.onStop();
                safeUnbindService(remove);
            }
        }
    }

    private void safeUnbindService(JobServiceConnection jobServiceConnection) {
        if (jobServiceConnection != null && jobServiceConnection.isBound()) {
            try {
                this.context.unbindService(jobServiceConnection);
            } catch (IllegalArgumentException e) {
                Log.w(TAG, "Error unbinding service: " + e.getMessage());
            }
        }
    }

    /* access modifiers changed from: private */
    public void onJobFinishedMessage(JobInvocation jobInvocation, int i) {
        synchronized (this.serviceConnections) {
            safeUnbindService(this.serviceConnections.remove(jobInvocation));
        }
        this.jobFinishedCallback.onJobFinished(jobInvocation, i);
    }

    private static class ResponseHandler extends Handler {
        private final WeakReference<ExecutionDelegator> executionDelegatorReference;

        ResponseHandler(Looper looper, WeakReference<ExecutionDelegator> weakReference) {
            super(looper);
            this.executionDelegatorReference = weakReference;
        }

        public void handleMessage(Message message) {
            if (message.what != 1) {
                Log.wtf(ExecutionDelegator.TAG, "handleMessage: unknown message type received: " + message.what);
            } else if (message.obj instanceof JobInvocation) {
                ExecutionDelegator executionDelegator = (ExecutionDelegator) this.executionDelegatorReference.get();
                if (executionDelegator == null) {
                    Log.wtf(ExecutionDelegator.TAG, "handleMessage: service was unexpectedly GC'd, can't send job result");
                } else {
                    executionDelegator.onJobFinishedMessage((JobInvocation) message.obj, message.arg1);
                }
            } else {
                Log.wtf(ExecutionDelegator.TAG, "handleMessage: unknown obj returned");
            }
        }
    }
}
