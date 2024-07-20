package com.firebase.jobdispatcher;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

final class GooglePlayJobCallback implements JobCallback {
    private static final String DESCRIPTOR = "com.google.android.gms.gcm.INetworkTaskCallback";
    private static final int TRANSACTION_TASK_FINISHED = 2;
    private final IBinder mRemote;

    public GooglePlayJobCallback(IBinder iBinder) {
        this.mRemote = iBinder;
    }

    public void jobFinished(int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(DESCRIPTOR);
            obtain.writeInt(i);
            this.mRemote.transact(2, obtain, obtain2, 0);
            obtain2.readException();
            obtain.recycle();
            obtain2.recycle();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            obtain.recycle();
            obtain2.recycle();
            throw th;
        }
    }
}
