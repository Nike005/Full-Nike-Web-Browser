package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzab extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzab> CREATOR = new zzac();
    private final float zzcr;
    private final float zzcs;
    private final float zzct;

    public zzab(float f, float f2, float f3) {
        this.zzcr = f;
        this.zzcs = f2;
        this.zzct = f3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzab)) {
            return false;
        }
        zzab zzab = (zzab) obj;
        return this.zzcr == zzab.zzcr && this.zzcs == zzab.zzcs && this.zzct == zzab.zzct;
    }

    public final int hashCode() {
        return Objects.hashCode(Float.valueOf(this.zzcr), Float.valueOf(this.zzcs), Float.valueOf(this.zzct));
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeFloat(parcel, 2, this.zzcr);
        SafeParcelWriter.writeFloat(parcel, 3, this.zzcs);
        SafeParcelWriter.writeFloat(parcel, 4, this.zzct);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
