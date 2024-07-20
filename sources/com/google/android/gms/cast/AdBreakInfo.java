package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.cast.zzcu;
import com.mopub.mobileads.VastIconXmlManager;
import java.util.Arrays;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdBreakInfo extends AbstractSafeParcelable {
    public static final Parcelable.Creator<AdBreakInfo> CREATOR = new zzb();
    private final String zze;
    private final long zzg;
    private final long zzq;
    private final boolean zzr;
    private String[] zzs;
    private final boolean zzt;

    public static class Builder {
        private String zze = null;
        private long zzg = 0;
        private long zzq = 0;
        private boolean zzr = false;
        private String[] zzs = null;
        private boolean zzt = false;

        public Builder(long j) {
            this.zzq = j;
        }

        public AdBreakInfo build() {
            return new AdBreakInfo(this.zzq, this.zze, this.zzg, this.zzr, this.zzs, this.zzt);
        }

        public Builder setBreakClipIds(String[] strArr) {
            this.zzs = strArr;
            return this;
        }

        public Builder setDurationInMs(long j) {
            this.zzg = j;
            return this;
        }

        public Builder setId(String str) {
            this.zze = str;
            return this;
        }

        public Builder setIsEmbedded(boolean z) {
            this.zzt = z;
            return this;
        }

        public Builder setIsWatched(boolean z) {
            this.zzr = z;
            return this;
        }
    }

    public AdBreakInfo(long j, String str, long j2, boolean z, String[] strArr, boolean z2) {
        this.zzq = j;
        this.zze = str;
        this.zzg = j2;
        this.zzr = z;
        this.zzs = strArr;
        this.zzt = z2;
    }

    static AdBreakInfo zzb(JSONObject jSONObject) {
        String[] strArr;
        if (jSONObject != null && jSONObject.has("id") && jSONObject.has("position")) {
            try {
                String string = jSONObject.getString("id");
                double d = (double) jSONObject.getLong("position");
                Double.isNaN(d);
                long j = (long) (d * 1000.0d);
                boolean optBoolean = jSONObject.optBoolean("isWatched");
                double optLong = (double) jSONObject.optLong(VastIconXmlManager.DURATION);
                Double.isNaN(optLong);
                long j2 = (long) (optLong * 1000.0d);
                JSONArray optJSONArray = jSONObject.optJSONArray("breakClipIds");
                if (optJSONArray != null) {
                    String[] strArr2 = new String[optJSONArray.length()];
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        strArr2[i] = optJSONArray.getString(i);
                    }
                    strArr = strArr2;
                } else {
                    strArr = null;
                }
                return new AdBreakInfo(j, string, j2, optBoolean, strArr, jSONObject.optBoolean("isEmbedded"));
            } catch (JSONException e) {
                Log.d("AdBreakInfo", String.format(Locale.ROOT, "Error while creating an AdBreakInfo from JSON: %s", new Object[]{e.getMessage()}));
            }
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AdBreakInfo)) {
            return false;
        }
        AdBreakInfo adBreakInfo = (AdBreakInfo) obj;
        return zzcu.zza(this.zze, adBreakInfo.zze) && this.zzq == adBreakInfo.zzq && this.zzg == adBreakInfo.zzg && this.zzr == adBreakInfo.zzr && Arrays.equals(this.zzs, adBreakInfo.zzs) && this.zzt == adBreakInfo.zzt;
    }

    public String[] getBreakClipIds() {
        return this.zzs;
    }

    public long getDurationInMs() {
        return this.zzg;
    }

    public String getId() {
        return this.zze;
    }

    public long getPlaybackPositionInMs() {
        return this.zzq;
    }

    public int hashCode() {
        return this.zze.hashCode();
    }

    public boolean isEmbedded() {
        return this.zzt;
    }

    public boolean isWatched() {
        return this.zzr;
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.zze);
            double d = (double) this.zzq;
            Double.isNaN(d);
            jSONObject.put("position", d / 1000.0d);
            jSONObject.put("isWatched", this.zzr);
            jSONObject.put("isEmbedded", this.zzt);
            double d2 = (double) this.zzg;
            Double.isNaN(d2);
            jSONObject.put(VastIconXmlManager.DURATION, d2 / 1000.0d);
            if (this.zzs != null) {
                JSONArray jSONArray = new JSONArray();
                for (String put : this.zzs) {
                    jSONArray.put(put);
                }
                jSONObject.put("breakClipIds", jSONArray);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 2, getPlaybackPositionInMs());
        SafeParcelWriter.writeString(parcel, 3, getId(), false);
        SafeParcelWriter.writeLong(parcel, 4, getDurationInMs());
        SafeParcelWriter.writeBoolean(parcel, 5, isWatched());
        SafeParcelWriter.writeStringArray(parcel, 6, getBreakClipIds(), false);
        SafeParcelWriter.writeBoolean(parcel, 7, isEmbedded());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
