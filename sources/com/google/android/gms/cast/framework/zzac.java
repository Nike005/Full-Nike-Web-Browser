package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.cast.zzb;
import com.google.android.gms.internal.cast.zzc;

public abstract class zzac extends zzb implements zzab {
    public zzac() {
        super("com.google.android.gms.cast.framework.ISessionProxy");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                IObjectWrapper zzaa = zzaa();
                parcel2.writeNoException();
                zzc.zza(parcel2, (IInterface) zzaa);
                return true;
            case 2:
                start((Bundle) zzc.zza(parcel, Bundle.CREATOR));
                break;
            case 3:
                resume((Bundle) zzc.zza(parcel, Bundle.CREATOR));
                break;
            case 4:
                end(zzc.zza(parcel));
                break;
            case 5:
                long sessionRemainingTimeMs = getSessionRemainingTimeMs();
                parcel2.writeNoException();
                parcel2.writeLong(sessionRemainingTimeMs);
                return true;
            case 6:
                zzm();
                parcel2.writeNoException();
                parcel2.writeInt(12451009);
                return true;
            case 7:
                onStarting((Bundle) zzc.zza(parcel, Bundle.CREATOR));
                break;
            case 8:
                onResuming((Bundle) zzc.zza(parcel, Bundle.CREATOR));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
