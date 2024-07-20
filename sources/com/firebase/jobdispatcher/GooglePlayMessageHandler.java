package com.firebase.jobdispatcher;

import android.app.AppOpsManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.firebase.jobdispatcher.JobInvocation;

class GooglePlayMessageHandler extends Handler {
    private static final int MSG_INIT = 4;
    static final int MSG_RESULT = 3;
    static final int MSG_START_EXEC = 1;
    static final int MSG_STOP_EXEC = 2;
    private final GooglePlayReceiver googlePlayReceiver;

    public GooglePlayMessageHandler(Looper looper, GooglePlayReceiver googlePlayReceiver2) {
        super(looper);
        this.googlePlayReceiver = googlePlayReceiver2;
    }

    public void handleMessage(Message message) {
        if (message != null) {
            try {
                ((AppOpsManager) this.googlePlayReceiver.getApplicationContext().getSystemService("appops")).checkPackage(message.sendingUid, "com.google.android.gms");
                int i = message.what;
                if (i == 1) {
                    handleStartMessage(message);
                } else if (i == 2) {
                    handleStopMessage(message);
                } else if (i != 4) {
                    Log.e("FJD.GooglePlayReceiver", "Unrecognized message received: " + message);
                }
            } catch (SecurityException unused) {
                Log.e("FJD.GooglePlayReceiver", "Message was not sent from GCM.");
            }
        }
    }

    private void handleStartMessage(Message message) {
        Bundle data = message.getData();
        Messenger messenger = message.replyTo;
        String string = data.getString("tag");
        if (messenger != null && string != null) {
            this.googlePlayReceiver.getExecutionDelegator().executeJob(this.googlePlayReceiver.prepareJob(new GooglePlayMessengerCallback(messenger, string), data));
        } else if (Log.isLoggable("FJD.GooglePlayReceiver", 3)) {
            Log.d("FJD.GooglePlayReceiver", "Invalid start execution message.");
        }
    }

    private void handleStopMessage(Message message) {
        JobInvocation.Builder decode = GooglePlayReceiver.getJobCoder().decode(message.getData());
        if (decode != null) {
            this.googlePlayReceiver.getExecutionDelegator().stopJob(decode.build());
        } else if (Log.isLoggable("FJD.GooglePlayReceiver", 3)) {
            Log.d("FJD.GooglePlayReceiver", "Invalid stop execution message.");
        }
    }
}
