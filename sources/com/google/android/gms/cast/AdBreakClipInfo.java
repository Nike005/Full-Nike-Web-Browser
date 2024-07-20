package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.cast.zzcu;
import com.mopub.mobileads.VastIconXmlManager;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class AdBreakClipInfo extends AbstractSafeParcelable {
    public static final long AD_BREAK_CLIP_NOT_SKIPPABLE = -1;
    public static final Parcelable.Creator<AdBreakClipInfo> CREATOR = new zza();
    private final String mimeType;
    private final String zze;
    private final String zzf;
    private final long zzg;
    private final String zzh;
    private final String zzi;
    private String zzj;
    private String zzk;
    private String zzl;
    private final long zzm;
    private final String zzn;
    private final VastAdsRequest zzo;
    private JSONObject zzp;

    public static class Builder {
        private String mimeType = null;
        private String zze = null;
        private String zzf = null;
        private long zzg = 0;
        private String zzh = null;
        private String zzi = null;
        private String zzj = null;
        private String zzk = null;
        private String zzl = null;
        private long zzm = -1;
        private String zzn;
        private VastAdsRequest zzo = null;

        public Builder(String str) {
            this.zze = str;
        }

        public AdBreakClipInfo build() {
            return new AdBreakClipInfo(this.zze, this.zzf, this.zzg, this.zzh, this.mimeType, this.zzi, this.zzj, this.zzk, this.zzl, this.zzm, this.zzn, this.zzo);
        }

        public Builder setClickThroughUrl(String str) {
            this.zzi = str;
            return this;
        }

        public Builder setContentId(String str) {
            this.zzk = str;
            return this;
        }

        public Builder setContentUrl(String str) {
            this.zzh = str;
            return this;
        }

        public Builder setCustomDataJsonString(String str) {
            this.zzj = str;
            return this;
        }

        public Builder setDurationInMs(long j) {
            this.zzg = j;
            return this;
        }

        public Builder setHlsSegmentFormat(String str) {
            this.zzn = str;
            return this;
        }

        public Builder setImageUrl(String str) {
            this.zzl = str;
            return this;
        }

        public Builder setMimeType(String str) {
            this.mimeType = str;
            return this;
        }

        public Builder setTitle(String str) {
            this.zzf = str;
            return this;
        }

        public Builder setVastAdsRequest(VastAdsRequest vastAdsRequest) {
            this.zzo = vastAdsRequest;
            return this;
        }

        public Builder setWhenSkippableInMs(long j) {
            this.zzm = j;
            return this;
        }
    }

    AdBreakClipInfo(String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, String str8, long j2, String str9, VastAdsRequest vastAdsRequest) {
        JSONObject jSONObject;
        this.zze = str;
        this.zzf = str2;
        this.zzg = j;
        this.zzh = str3;
        this.mimeType = str4;
        this.zzi = str5;
        this.zzj = str6;
        this.zzk = str7;
        this.zzl = str8;
        this.zzm = j2;
        this.zzn = str9;
        this.zzo = vastAdsRequest;
        if (!TextUtils.isEmpty(str6)) {
            try {
                this.zzp = new JSONObject(str6);
            } catch (JSONException e) {
                Log.w("AdBreakClipInfo", String.format(Locale.ROOT, "Error creating AdBreakClipInfo: %s", new Object[]{e.getMessage()}));
                this.zzj = null;
                jSONObject = new JSONObject();
            }
        } else {
            jSONObject = new JSONObject();
            this.zzp = jSONObject;
        }
    }

    static AdBreakClipInfo zza(JSONObject jSONObject) {
        long j;
        String str;
        JSONObject jSONObject2 = jSONObject;
        if (jSONObject2 == null || !jSONObject2.has("id")) {
            return null;
        }
        try {
            String string = jSONObject2.getString("id");
            double optLong = (double) jSONObject2.optLong(VastIconXmlManager.DURATION);
            Double.isNaN(optLong);
            long j2 = (long) (optLong * 1000.0d);
            String optString = jSONObject2.optString("clickThroughUrl", (String) null);
            String optString2 = jSONObject2.optString("contentUrl", (String) null);
            String optString3 = jSONObject2.optString("mimeType", (String) null);
            if (optString3 == null) {
                optString3 = jSONObject2.optString("contentType", (String) null);
            }
            String str2 = optString3;
            String optString4 = jSONObject2.optString("title", (String) null);
            JSONObject optJSONObject = jSONObject2.optJSONObject("customData");
            String optString5 = jSONObject2.optString("contentId", (String) null);
            String optString6 = jSONObject2.optString("posterUrl", (String) null);
            long j3 = -1;
            if (jSONObject2.has("whenSkippable")) {
                j = j2;
                double intValue = (double) ((Integer) jSONObject2.get("whenSkippable")).intValue();
                Double.isNaN(intValue);
                j3 = (long) (intValue * 1000.0d);
            } else {
                j = j2;
            }
            String optString7 = jSONObject2.optString("hlsSegmentFormat", (String) null);
            VastAdsRequest fromJson = VastAdsRequest.fromJson(jSONObject2.optJSONObject("vastAdsRequest"));
            if (optJSONObject != null) {
                if (optJSONObject.length() != 0) {
                    str = optJSONObject.toString();
                    return new AdBreakClipInfo(string, optString4, j, optString2, str2, optString, str, optString5, optString6, j3, optString7, fromJson);
                }
            }
            str = null;
            return new AdBreakClipInfo(string, optString4, j, optString2, str2, optString, str, optString5, optString6, j3, optString7, fromJson);
        } catch (JSONException e) {
            Log.d("AdBreakClipInfo", String.format(Locale.ROOT, "Error while creating an AdBreakClipInfo from JSON: %s", new Object[]{e.getMessage()}));
            return null;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AdBreakClipInfo)) {
            return false;
        }
        AdBreakClipInfo adBreakClipInfo = (AdBreakClipInfo) obj;
        return zzcu.zza(this.zze, adBreakClipInfo.zze) && zzcu.zza(this.zzf, adBreakClipInfo.zzf) && this.zzg == adBreakClipInfo.zzg && zzcu.zza(this.zzh, adBreakClipInfo.zzh) && zzcu.zza(this.mimeType, adBreakClipInfo.mimeType) && zzcu.zza(this.zzi, adBreakClipInfo.zzi) && zzcu.zza(this.zzj, adBreakClipInfo.zzj) && zzcu.zza(this.zzk, adBreakClipInfo.zzk) && zzcu.zza(this.zzl, adBreakClipInfo.zzl) && this.zzm == adBreakClipInfo.zzm && zzcu.zza(this.zzn, adBreakClipInfo.zzn) && zzcu.zza(this.zzo, adBreakClipInfo.zzo);
    }

    public String getClickThroughUrl() {
        return this.zzi;
    }

    public String getContentId() {
        return this.zzk;
    }

    public String getContentUrl() {
        return this.zzh;
    }

    public JSONObject getCustomData() {
        return this.zzp;
    }

    public long getDurationInMs() {
        return this.zzg;
    }

    public String getHlsSegmentFormat() {
        return this.zzn;
    }

    public String getId() {
        return this.zze;
    }

    public String getImageUrl() {
        return this.zzl;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public String getTitle() {
        return this.zzf;
    }

    public VastAdsRequest getVastAdsRequest() {
        return this.zzo;
    }

    public long getWhenSkippableInMs() {
        return this.zzm;
    }

    public int hashCode() {
        return Objects.hashCode(this.zze, this.zzf, Long.valueOf(this.zzg), this.zzh, this.mimeType, this.zzi, this.zzj, this.zzk, this.zzl, Long.valueOf(this.zzm), this.zzn, this.zzo);
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.zze);
            double d = (double) this.zzg;
            Double.isNaN(d);
            jSONObject.put(VastIconXmlManager.DURATION, d / 1000.0d);
            if (this.zzm != -1) {
                double d2 = (double) this.zzm;
                Double.isNaN(d2);
                jSONObject.put("whenSkippable", d2 / 1000.0d);
            }
            if (this.zzk != null) {
                jSONObject.put("contentId", this.zzk);
            }
            if (this.mimeType != null) {
                jSONObject.put("contentType", this.mimeType);
            }
            if (this.zzf != null) {
                jSONObject.put("title", this.zzf);
            }
            if (this.zzh != null) {
                jSONObject.put("contentUrl", this.zzh);
            }
            if (this.zzi != null) {
                jSONObject.put("clickThroughUrl", this.zzi);
            }
            if (this.zzp != null) {
                jSONObject.put("customData", this.zzp);
            }
            if (this.zzl != null) {
                jSONObject.put("posterUrl", this.zzl);
            }
            if (this.zzn != null) {
                jSONObject.put("hlsSegmentFormat", this.zzn);
            }
            if (this.zzo != null) {
                jSONObject.put("vastAdsRequest", this.zzo.toJson());
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getId(), false);
        SafeParcelWriter.writeString(parcel, 3, getTitle(), false);
        SafeParcelWriter.writeLong(parcel, 4, getDurationInMs());
        SafeParcelWriter.writeString(parcel, 5, getContentUrl(), false);
        SafeParcelWriter.writeString(parcel, 6, getMimeType(), false);
        SafeParcelWriter.writeString(parcel, 7, getClickThroughUrl(), false);
        SafeParcelWriter.writeString(parcel, 8, this.zzj, false);
        SafeParcelWriter.writeString(parcel, 9, getContentId(), false);
        SafeParcelWriter.writeString(parcel, 10, getImageUrl(), false);
        SafeParcelWriter.writeLong(parcel, 11, getWhenSkippableInMs());
        SafeParcelWriter.writeString(parcel, 12, getHlsSegmentFormat(), false);
        SafeParcelWriter.writeParcelable(parcel, 13, getVastAdsRequest(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
