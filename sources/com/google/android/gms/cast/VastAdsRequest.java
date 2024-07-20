package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.cast.zzcu;
import org.json.JSONException;
import org.json.JSONObject;

public class VastAdsRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<VastAdsRequest> CREATOR = new zzbs();
    private final String zzgl;
    private final String zzgm;

    public static class Builder {
        private String zzgl = null;
        private String zzgm = null;

        public VastAdsRequest build() {
            return new VastAdsRequest(this.zzgl, this.zzgm);
        }

        public Builder setAdTagUrl(String str) {
            this.zzgl = str;
            return this;
        }

        public Builder setAdsResponse(String str) {
            this.zzgm = str;
            return this;
        }
    }

    VastAdsRequest(String str, String str2) {
        this.zzgl = str;
        this.zzgm = str2;
    }

    public static VastAdsRequest fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return new VastAdsRequest(jSONObject.optString("adTagUrl", (String) null), jSONObject.optString("adsResponse", (String) null));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VastAdsRequest)) {
            return false;
        }
        VastAdsRequest vastAdsRequest = (VastAdsRequest) obj;
        return zzcu.zza(this.zzgl, vastAdsRequest.zzgl) && zzcu.zza(this.zzgm, vastAdsRequest.zzgm);
    }

    public String getAdTagUrl() {
        return this.zzgl;
    }

    public String getAdsResponse() {
        return this.zzgm;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzgl, this.zzgm);
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.zzgl != null) {
                jSONObject.put("adTagUrl", this.zzgl);
            }
            if (this.zzgm != null) {
                jSONObject.put("adsResponse", this.zzgm);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getAdTagUrl(), false);
        SafeParcelWriter.writeString(parcel, 3, getAdsResponse(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
