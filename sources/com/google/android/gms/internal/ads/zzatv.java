package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzatv extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzatv> CREATOR = new zzatw();
    private final int versionCode;
    private zzba zzdhe = null;
    private byte[] zzdhf;

    zzatv(int i, byte[] bArr) {
        this.versionCode = i;
        this.zzdhf = bArr;
        zzwf();
    }

    private final void zzwf() {
        if (this.zzdhe == null && this.zzdhf != null) {
            return;
        }
        if (this.zzdhe != null && this.zzdhf == null) {
            return;
        }
        if (this.zzdhe != null && this.zzdhf != null) {
            throw new IllegalStateException("Invalid internal representation - full");
        } else if (this.zzdhe == null && this.zzdhf == null) {
            throw new IllegalStateException("Invalid internal representation - empty");
        } else {
            throw new IllegalStateException("Impossible");
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        byte[] bArr = this.zzdhf;
        if (bArr == null) {
            bArr = zzbfi.zzb(this.zzdhe);
        }
        SafeParcelWriter.writeByteArray(parcel, 2, bArr, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final zzba zzwe() {
        if (!(this.zzdhe != null)) {
            try {
                this.zzdhe = (zzba) zzbfi.zza(new zzba(), this.zzdhf);
                this.zzdhf = null;
            } catch (zzbfh e) {
                throw new IllegalStateException(e);
            }
        }
        zzwf();
        return this.zzdhe;
    }
}
