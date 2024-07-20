package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.cast.zzcu;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzbp extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbp> CREATOR = new zzbq();
    private final String url;
    private final int zzgf;
    private final int zzgg;
    private final String zzn;

    public zzbp(String str, int i, int i2, String str2) {
        this.url = str;
        this.zzgf = i;
        this.zzgg = i2;
        this.zzn = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbp)) {
            return false;
        }
        zzbp zzbp = (zzbp) obj;
        return zzcu.zza(this.url, zzbp.url) && zzcu.zza(Integer.valueOf(this.zzgf), Integer.valueOf(zzbp.zzgf)) && zzcu.zza(Integer.valueOf(this.zzgg), Integer.valueOf(zzbp.zzgg)) && zzcu.zza(zzbp.zzn, this.zzn);
    }

    public final int hashCode() {
        return Objects.hashCode(this.url, Integer.valueOf(this.zzgf), Integer.valueOf(this.zzgg), this.zzn);
    }

    public final JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("url", this.url);
        jSONObject.put("protocolType", this.zzgf);
        jSONObject.put("initialTime", this.zzgg);
        jSONObject.put("hlsSegmentFormat", this.zzn);
        return jSONObject;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.url, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzgf);
        SafeParcelWriter.writeInt(parcel, 4, this.zzgg);
        SafeParcelWriter.writeString(parcel, 5, this.zzn, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
