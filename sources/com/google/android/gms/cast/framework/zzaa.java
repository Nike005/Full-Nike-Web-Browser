package com.google.android.gms.cast.framework;

import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.cast.zzb;
import com.google.android.gms.internal.cast.zzc;

public abstract class zzaa extends zzb implements zzz {
    public zzaa() {
        super("com.google.android.gms.cast.framework.ISessionProvider");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            IObjectWrapper zzj = zzj(parcel.readString());
            parcel2.writeNoException();
            zzc.zza(parcel2, (IInterface) zzj);
        } else if (i == 2) {
            boolean isSessionRecoverable = isSessionRecoverable();
            parcel2.writeNoException();
            zzc.zza(parcel2, isSessionRecoverable);
        } else if (i == 3) {
            String category = getCategory();
            parcel2.writeNoException();
            parcel2.writeString(category);
        } else if (i != 4) {
            return false;
        } else {
            zzm();
            parcel2.writeNoException();
            parcel2.writeInt(12451009);
        }
        return true;
    }
}
