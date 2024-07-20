package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.internal.cast.zzb;
import com.google.android.gms.internal.cast.zzc;

public abstract class zzi extends zzb implements zzh {
    public zzi() {
        super("com.google.android.gms.cast.framework.ICastConnectionController");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zza(parcel.readString(), parcel.readString());
        } else if (i == 2) {
            zza(parcel.readString(), (LaunchOptions) zzc.zza(parcel, LaunchOptions.CREATOR));
        } else if (i == 3) {
            zzi(parcel.readString());
        } else if (i == 4) {
            zze(parcel.readInt());
        } else if (i != 5) {
            return false;
        } else {
            zzm();
            parcel2.writeNoException();
            parcel2.writeInt(12451009);
            return true;
        }
        parcel2.writeNoException();
        return true;
    }
}
