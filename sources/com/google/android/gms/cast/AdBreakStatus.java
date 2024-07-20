package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.cast.zzcu;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class AdBreakStatus extends AbstractSafeParcelable {
    public static final int AD_BREAK_CLIP_NOT_SKIPPABLE = -1;
    public static final Parcelable.Creator<AdBreakStatus> CREATOR = new zzc();
    private final long zzm;
    private final long zzu;
    private final long zzv;
    private final String zzw;
    private final String zzx;

    AdBreakStatus(long j, long j2, String str, String str2, long j3) {
        this.zzu = j;
        this.zzv = j2;
        this.zzw = str;
        this.zzx = str2;
        this.zzm = j3;
    }

    static AdBreakStatus zzc(JSONObject jSONObject) {
        long j;
        JSONObject jSONObject2 = jSONObject;
        if (jSONObject2 == null || !jSONObject2.has("currentBreakTime") || !jSONObject2.has("currentBreakClipTime")) {
            return null;
        }
        try {
            double d = (double) jSONObject2.getLong("currentBreakTime");
            Double.isNaN(d);
            long j2 = (long) (d * 1000.0d);
            double d2 = (double) jSONObject2.getLong("currentBreakClipTime");
            Double.isNaN(d2);
            long j3 = (long) (d2 * 1000.0d);
            String optString = jSONObject2.optString("breakId", (String) null);
            String optString2 = jSONObject2.optString("breakClipId", (String) null);
            long optLong = jSONObject2.optLong("whenSkippable", -1);
            if (optLong != -1) {
                double d3 = (double) optLong;
                Double.isNaN(d3);
                j = (long) (d3 * 1000.0d);
            } else {
                j = optLong;
            }
            return new AdBreakStatus(j2, j3, optString, optString2, j);
        } catch (JSONException e) {
            Log.d("AdBreakInfo", String.format(Locale.ROOT, "Error while creating an AdBreakClipInfo from JSON: %s", new Object[]{e.getMessage()}));
            return null;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AdBreakStatus)) {
            return false;
        }
        AdBreakStatus adBreakStatus = (AdBreakStatus) obj;
        return this.zzu == adBreakStatus.zzu && this.zzv == adBreakStatus.zzv && zzcu.zza(this.zzw, adBreakStatus.zzw) && zzcu.zza(this.zzx, adBreakStatus.zzx) && this.zzm == adBreakStatus.zzm;
    }

    public String getBreakClipId() {
        return this.zzx;
    }

    public String getBreakId() {
        return this.zzw;
    }

    public long getCurrentBreakClipTimeInMs() {
        return this.zzv;
    }

    public long getCurrentBreakTimeInMs() {
        return this.zzu;
    }

    public long getWhenSkippableInMs() {
        return this.zzm;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzu), Long.valueOf(this.zzv), this.zzw, this.zzx, Long.valueOf(this.zzm));
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 2, getCurrentBreakTimeInMs());
        SafeParcelWriter.writeLong(parcel, 3, getCurrentBreakClipTimeInMs());
        SafeParcelWriter.writeString(parcel, 4, getBreakId(), false);
        SafeParcelWriter.writeString(parcel, 5, getBreakClipId(), false);
        SafeParcelWriter.writeLong(parcel, 6, getWhenSkippableInMs());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
