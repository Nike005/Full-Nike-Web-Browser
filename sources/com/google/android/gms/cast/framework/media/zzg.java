package com.google.android.gms.cast.framework.media;

import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.cast.zzb;
import com.google.android.gms.internal.cast.zzc;
import java.util.List;

public abstract class zzg extends zzb implements zzf {
    public zzg() {
        super("com.google.android.gms.cast.framework.media.INotificationActionsProvider");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            int zzm = zzm();
            parcel2.writeNoException();
            parcel2.writeInt(zzm);
        } else if (i == 2) {
            IObjectWrapper zzaw = zzaw();
            parcel2.writeNoException();
            zzc.zza(parcel2, (IInterface) zzaw);
        } else if (i == 3) {
            List<NotificationAction> notificationActions = getNotificationActions();
            parcel2.writeNoException();
            parcel2.writeTypedList(notificationActions);
        } else if (i != 4) {
            return false;
        } else {
            int[] compactViewActionIndices = getCompactViewActionIndices();
            parcel2.writeNoException();
            parcel2.writeIntArray(compactViewActionIndices);
        }
        return true;
    }
}
