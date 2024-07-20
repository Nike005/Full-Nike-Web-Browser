package com.google.android.gms.cast.framework.media;

import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.cast.zzc;

public interface zzb extends IInterface {

    public static abstract class zza extends com.google.android.gms.internal.cast.zzb implements zzb {
        public zza() {
            super("com.google.android.gms.cast.framework.media.IImagePicker");
        }

        /* access modifiers changed from: protected */
        public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            WebImage webImage;
            if (i != 1) {
                if (i == 2) {
                    IObjectWrapper zzaw = zzaw();
                    parcel2.writeNoException();
                    zzc.zza(parcel2, (IInterface) zzaw);
                } else if (i == 3) {
                    int zzm = zzm();
                    parcel2.writeNoException();
                    parcel2.writeInt(zzm);
                } else if (i != 4) {
                    return false;
                } else {
                    webImage = zza((MediaMetadata) zzc.zza(parcel, MediaMetadata.CREATOR), (ImageHints) zzc.zza(parcel, ImageHints.CREATOR));
                }
                return true;
            }
            webImage = onPickImage((MediaMetadata) zzc.zza(parcel, MediaMetadata.CREATOR), parcel.readInt());
            parcel2.writeNoException();
            zzc.zzb(parcel2, webImage);
            return true;
        }
    }

    WebImage onPickImage(MediaMetadata mediaMetadata, int i) throws RemoteException;

    WebImage zza(MediaMetadata mediaMetadata, ImageHints imageHints) throws RemoteException;

    IObjectWrapper zzaw() throws RemoteException;

    int zzm() throws RemoteException;
}
