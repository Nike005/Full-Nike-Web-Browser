package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzaf extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaf> CREATOR = new zzag();
    private int zzcw;

    public zzaf() {
        this(0);
    }

    zzaf(int i) {
        this.zzcw = i;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof zzaf) && this.zzcw == ((zzaf) obj).zzcw;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzcw));
    }

    public final String toString() {
        int i = this.zzcw;
        return String.format("joinOptions(connectionType=%s)", new Object[]{i != 0 ? i != 2 ? "UNKNOWN" : "INVISIBLE" : "STRONG"});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zzcw);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
