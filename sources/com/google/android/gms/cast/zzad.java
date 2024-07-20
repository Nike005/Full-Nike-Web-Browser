package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.cast.zzcu;

public final class zzad extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzad> CREATOR = new zzae();
    private final zzab zzcu;
    private final zzab zzcv;

    public zzad(zzab zzab, zzab zzab2) {
        this.zzcu = zzab;
        this.zzcv = zzab2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzad)) {
            return false;
        }
        zzad zzad = (zzad) obj;
        return zzcu.zza(this.zzcu, zzad.zzcu) && zzcu.zza(this.zzcv, zzad.zzcv);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzcu, this.zzcv);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzcu, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzcv, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
