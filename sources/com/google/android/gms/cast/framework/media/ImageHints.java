package com.google.android.gms.cast.framework.media;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public class ImageHints extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ImageHints> CREATOR = new zzi();
    private final int type;
    private final int zzkx;
    private final int zzky;

    public ImageHints(int i, int i2, int i3) {
        this.type = i;
        this.zzkx = i2;
        this.zzky = i3;
    }

    public int getHeightInPixels() {
        return this.zzky;
    }

    public int getType() {
        return this.type;
    }

    public int getWidthInPixels() {
        return this.zzkx;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, getType());
        SafeParcelWriter.writeInt(parcel, 3, getWidthInPixels());
        SafeParcelWriter.writeInt(parcel, 4, getHeightInPixels());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
