package com.google.android.gms.cast.framework;

import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.cast.zzb;
import com.google.android.gms.internal.cast.zzc;

public abstract class zzy extends zzb implements zzx {
    public zzy() {
        super("com.google.android.gms.cast.framework.ISessionManagerListener");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                IObjectWrapper zzn = zzn();
                parcel2.writeNoException();
                zzc.zza(parcel2, (IInterface) zzn);
                return true;
            case 2:
                zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                break;
            case 3:
                zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                break;
            case 4:
                zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                break;
            case 5:
                zzb(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                break;
            case 6:
                zzb(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                break;
            case 7:
                zzb(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                break;
            case 8:
                zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), zzc.zza(parcel));
                break;
            case 9:
                zzc(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                break;
            case 10:
                zzd(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                break;
            case 11:
                zzm();
                parcel2.writeNoException();
                parcel2.writeInt(12451009);
                return true;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
