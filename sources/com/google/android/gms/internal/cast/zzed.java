package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;

public abstract class zzed extends zzb implements zzec {
    public zzed() {
        super("com.google.android.gms.cast.remote_display.ICastRemoteDisplayCallbacks");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zza(parcel.readInt(), parcel.readInt(), (Surface) zzc.zza(parcel, Surface.CREATOR));
        } else if (i == 2) {
            onError(parcel.readInt());
        } else if (i == 3) {
            onDisconnected();
        } else if (i != 4) {
            return false;
        } else {
            zzc();
        }
        parcel2.writeNoException();
        return true;
    }
}
