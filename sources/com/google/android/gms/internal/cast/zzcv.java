package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.zzad;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzcv extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzcv> CREATOR = new zzcw();
    private double zzei;
    private boolean zzej;
    private zzad zzvf;
    private int zzvg;
    private int zzvh;
    private ApplicationMetadata zzvr;

    public zzcv() {
        this(Double.NaN, false, -1, (ApplicationMetadata) null, -1, (zzad) null);
    }

    zzcv(double d, boolean z, int i, ApplicationMetadata applicationMetadata, int i2, zzad zzad) {
        this.zzei = d;
        this.zzej = z;
        this.zzvg = i;
        this.zzvr = applicationMetadata;
        this.zzvh = i2;
        this.zzvf = zzad;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcv)) {
            return false;
        }
        zzcv zzcv = (zzcv) obj;
        if (this.zzei == zzcv.zzei && this.zzej == zzcv.zzej && this.zzvg == zzcv.zzvg && zzcu.zza(this.zzvr, zzcv.zzvr) && this.zzvh == zzcv.zzvh) {
            zzad zzad = this.zzvf;
            if (zzcu.zza(zzad, zzad)) {
                return true;
            }
        }
        return false;
    }

    public final int getActiveInputState() {
        return this.zzvg;
    }

    public final ApplicationMetadata getApplicationMetadata() {
        return this.zzvr;
    }

    public final int getStandbyState() {
        return this.zzvh;
    }

    public final double getVolume() {
        return this.zzei;
    }

    public final int hashCode() {
        return Objects.hashCode(Double.valueOf(this.zzei), Boolean.valueOf(this.zzej), Integer.valueOf(this.zzvg), this.zzvr, Integer.valueOf(this.zzvh), this.zzvf);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeDouble(parcel, 2, this.zzei);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzej);
        SafeParcelWriter.writeInt(parcel, 4, this.zzvg);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzvr, i, false);
        SafeParcelWriter.writeInt(parcel, 6, this.zzvh);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzvf, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final boolean zzcv() {
        return this.zzej;
    }

    public final zzad zzcw() {
        return this.zzvf;
    }
}
