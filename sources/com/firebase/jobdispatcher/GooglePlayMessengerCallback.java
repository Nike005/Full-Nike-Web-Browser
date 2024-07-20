package com.firebase.jobdispatcher;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

class GooglePlayMessengerCallback implements JobCallback {
    private final Messenger messenger;
    private final String tag;

    GooglePlayMessengerCallback(Messenger messenger2, String str) {
        this.messenger = messenger2;
        this.tag = str;
    }

    public void jobFinished(int i) {
        try {
            this.messenger.send(createResultMessage(i));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    private Message createResultMessage(int i) {
        Message obtain = Message.obtain();
        obtain.what = 3;
        obtain.arg1 = i;
        Bundle bundle = new Bundle();
        bundle.putString("tag", this.tag);
        obtain.setData(bundle);
        return obtain;
    }
}
