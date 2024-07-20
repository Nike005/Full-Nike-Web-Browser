package com.google.android.gms.cast.framework;

import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.cast.zzb;
import com.google.android.gms.internal.cast.zzc;

public abstract class zzo extends zzb implements zzn {
    public zzo() {
        super("com.google.android.gms.cast.framework.ICastStateListener");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            IObjectWrapper zzn = zzn();
            parcel2.writeNoException();
            zzc.zza(parcel2, (IInterface) zzn);
        } else if (i == 2) {
            onCastStateChanged(parcel.readInt());
            parcel2.writeNoException();
        } else if (i != 3) {
            return false;
        } else {
            zzm();
            parcel2.writeNoException();
            parcel2.writeInt(12451009);
        }
        return true;
    }
}
