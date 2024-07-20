package com.firebase.jobdispatcher;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import com.firebase.jobdispatcher.JobService;

class JobServiceConnection implements ServiceConnection {
    private JobService.LocalBinder binder;
    private final Message jobFinishedMessage;
    private final JobInvocation jobInvocation;
    private boolean wasMessageUsed = false;

    JobServiceConnection(JobInvocation jobInvocation2, Message message) {
        this.jobFinishedMessage = message;
        this.jobInvocation = jobInvocation2;
        message.obj = jobInvocation2;
    }

    public synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (!(iBinder instanceof JobService.LocalBinder)) {
            Log.w("FJD.ExternalReceiver", "Unknown service connected");
        } else if (this.wasMessageUsed) {
            Log.w("FJD.ExternalReceiver", "onServiceConnected Duplicate calls. Ignored.");
        } else {
            this.wasMessageUsed = true;
            JobService.LocalBinder localBinder = (JobService.LocalBinder) iBinder;
            this.binder = localBinder;
            localBinder.getService().start(this.jobInvocation, this.jobFinishedMessage);
        }
    }

    public synchronized void onServiceDisconnected(ComponentName componentName) {
        this.binder = null;
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean isBound() {
        return this.binder != null;
    }

    /* access modifiers changed from: package-private */
    public synchronized void onStop() {
        if (isBound()) {
            this.binder.getService().stop(this.jobInvocation);
        }
    }
}
