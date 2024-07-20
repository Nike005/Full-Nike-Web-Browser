package com.google.android.gms.cast.framework;

import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.cast.zzb;
import com.google.android.gms.internal.cast.zzc;

public abstract class zzg extends zzb implements zzf {
    public zzg() {
        super("com.google.android.gms.cast.framework.IAppVisibilityListener");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            if (i == 2) {
                onAppEnteredForeground();
            } else if (i == 3) {
                onAppEnteredBackground();
            } else if (i != 4) {
                return false;
            } else {
                zzm();
                parcel2.writeNoException();
                parcel2.writeInt(12451009);
            }
            parcel2.writeNoException();
        } else {
            IObjectWrapper zzn = zzn();
            parcel2.writeNoException();
            zzc.zza(parcel2, (IInterface) zzn);
        }
        return true;
    }
}
