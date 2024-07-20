package com.google.android.gms.cast.framework.media;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.cast.zzdg;

public class CastMediaOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<CastMediaOptions> CREATOR = new zza();
    private static final zzdg zzbd = new zzdg("CastMediaOptions");
    private final String zzkr;
    private final String zzks;
    private final zzb zzkt;
    private final NotificationOptions zzku;
    private final boolean zzkv;

    public static final class Builder {
        private String zzkr = MediaIntentReceiver.class.getName();
        private String zzks;
        private NotificationOptions zzku = new NotificationOptions.Builder().build();
        private ImagePicker zzkw;

        public final CastMediaOptions build() {
            ImagePicker imagePicker = this.zzkw;
            return new CastMediaOptions(this.zzkr, this.zzks, imagePicker == null ? null : imagePicker.zzax().asBinder(), this.zzku, false);
        }

        public final Builder setExpandedControllerActivityClassName(String str) {
            this.zzks = str;
            return this;
        }

        public final Builder setImagePicker(ImagePicker imagePicker) {
            this.zzkw = imagePicker;
            return this;
        }

        public final Builder setMediaIntentReceiverClassName(String str) {
            this.zzkr = str;
            return this;
        }

        public final Builder setNotificationOptions(NotificationOptions notificationOptions) {
            this.zzku = notificationOptions;
            return this;
        }
    }

    CastMediaOptions(String str, String str2, IBinder iBinder, NotificationOptions notificationOptions, boolean z) {
        zzb zzb;
        this.zzkr = str;
        this.zzks = str2;
        if (iBinder == null) {
            zzb = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.framework.media.IImagePicker");
            zzb = queryLocalInterface instanceof zzb ? (zzb) queryLocalInterface : new zzc(iBinder);
        }
        this.zzkt = zzb;
        this.zzku = notificationOptions;
        this.zzkv = z;
    }

    public String getExpandedControllerActivityClassName() {
        return this.zzks;
    }

    public ImagePicker getImagePicker() {
        zzb zzb = this.zzkt;
        if (zzb == null) {
            return null;
        }
        try {
            return (ImagePicker) ObjectWrapper.unwrap(zzb.zzaw());
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "getWrappedClientObject", zzb.class.getSimpleName());
            return null;
        }
    }

    public String getMediaIntentReceiverClassName() {
        return this.zzkr;
    }

    public NotificationOptions getNotificationOptions() {
        return this.zzku;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getMediaIntentReceiverClassName(), false);
        SafeParcelWriter.writeString(parcel, 3, getExpandedControllerActivityClassName(), false);
        zzb zzb = this.zzkt;
        SafeParcelWriter.writeIBinder(parcel, 4, zzb == null ? null : zzb.asBinder(), false);
        SafeParcelWriter.writeParcelable(parcel, 5, getNotificationOptions(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzkv);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final boolean zzav() {
        return this.zzkv;
    }
}
