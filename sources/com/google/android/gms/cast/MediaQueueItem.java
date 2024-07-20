package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.internal.cast.zzcu;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MediaQueueItem extends AbstractSafeParcelable {
    public static final Parcelable.Creator<MediaQueueItem> CREATOR = new zzam();
    public static final double DEFAULT_PLAYBACK_DURATION = Double.POSITIVE_INFINITY;
    public static final int INVALID_ITEM_ID = 0;
    private boolean zzdj;
    private long[] zzdm;
    private MediaInfo zzdw;
    private int zzdx;
    private double zzdy;
    private double zzdz;
    private double zzea;
    private String zzj;
    private JSONObject zzp;

    public static class Builder {
        private final MediaQueueItem zzeb;

        public Builder(MediaInfo mediaInfo) throws IllegalArgumentException {
            this.zzeb = new MediaQueueItem(mediaInfo);
        }

        public Builder(MediaQueueItem mediaQueueItem) throws IllegalArgumentException {
            this.zzeb = new MediaQueueItem();
        }

        public Builder(JSONObject jSONObject) throws JSONException {
            this.zzeb = new MediaQueueItem(jSONObject);
        }

        public MediaQueueItem build() {
            this.zzeb.zzi();
            return this.zzeb;
        }

        public Builder clearItemId() {
            this.zzeb.zza(0);
            return this;
        }

        public Builder setActiveTrackIds(long[] jArr) {
            this.zzeb.zza(jArr);
            return this;
        }

        public Builder setAutoplay(boolean z) {
            this.zzeb.zze(z);
            return this;
        }

        public Builder setCustomData(JSONObject jSONObject) {
            this.zzeb.setCustomData(jSONObject);
            return this;
        }

        public Builder setPlaybackDuration(double d) {
            this.zzeb.zzb(d);
            return this;
        }

        public Builder setPreloadTime(double d) throws IllegalArgumentException {
            this.zzeb.zzc(d);
            return this;
        }

        public Builder setStartTime(double d) throws IllegalArgumentException {
            this.zzeb.zza(d);
            return this;
        }
    }

    private MediaQueueItem(MediaInfo mediaInfo) throws IllegalArgumentException {
        this(mediaInfo, 0, true, 0.0d, Double.POSITIVE_INFINITY, 0.0d, (long[]) null, (String) null);
        if (mediaInfo == null) {
            throw new IllegalArgumentException("media cannot be null.");
        }
    }

    MediaQueueItem(MediaInfo mediaInfo, int i, boolean z, double d, double d2, double d3, long[] jArr, String str) {
        this.zzdw = mediaInfo;
        this.zzdx = i;
        this.zzdj = z;
        this.zzdy = d;
        this.zzdz = d2;
        this.zzea = d3;
        this.zzdm = jArr;
        this.zzj = str;
        if (str != null) {
            try {
                this.zzp = new JSONObject(this.zzj);
            } catch (JSONException unused) {
                this.zzp = null;
                this.zzj = null;
            }
        } else {
            this.zzp = null;
        }
    }

    private MediaQueueItem(MediaQueueItem mediaQueueItem) throws IllegalArgumentException {
        this(mediaQueueItem.getMedia(), mediaQueueItem.getItemId(), mediaQueueItem.getAutoplay(), mediaQueueItem.getStartTime(), mediaQueueItem.getPlaybackDuration(), mediaQueueItem.getPreloadTime(), mediaQueueItem.getActiveTrackIds(), (String) null);
        if (this.zzdw != null) {
            this.zzp = mediaQueueItem.getCustomData();
            return;
        }
        throw new IllegalArgumentException("media cannot be null.");
    }

    MediaQueueItem(JSONObject jSONObject) throws JSONException {
        this((MediaInfo) null, 0, true, 0.0d, Double.POSITIVE_INFINITY, 0.0d, (long[]) null, (String) null);
        zzf(jSONObject);
    }

    public boolean equals(Object obj) {
        JSONObject jSONObject;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaQueueItem)) {
            return false;
        }
        MediaQueueItem mediaQueueItem = (MediaQueueItem) obj;
        if ((this.zzp == null) != (mediaQueueItem.zzp == null)) {
            return false;
        }
        JSONObject jSONObject2 = this.zzp;
        return (jSONObject2 == null || (jSONObject = mediaQueueItem.zzp) == null || JsonUtils.areJsonValuesEquivalent(jSONObject2, jSONObject)) && zzcu.zza(this.zzdw, mediaQueueItem.zzdw) && this.zzdx == mediaQueueItem.zzdx && this.zzdj == mediaQueueItem.zzdj && this.zzdy == mediaQueueItem.zzdy && this.zzdz == mediaQueueItem.zzdz && this.zzea == mediaQueueItem.zzea && Arrays.equals(this.zzdm, mediaQueueItem.zzdm);
    }

    public long[] getActiveTrackIds() {
        return this.zzdm;
    }

    public boolean getAutoplay() {
        return this.zzdj;
    }

    public JSONObject getCustomData() {
        return this.zzp;
    }

    public int getItemId() {
        return this.zzdx;
    }

    public MediaInfo getMedia() {
        return this.zzdw;
    }

    public double getPlaybackDuration() {
        return this.zzdz;
    }

    public double getPreloadTime() {
        return this.zzea;
    }

    public double getStartTime() {
        return this.zzdy;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzdw, Integer.valueOf(this.zzdx), Boolean.valueOf(this.zzdj), Double.valueOf(this.zzdy), Double.valueOf(this.zzdz), Double.valueOf(this.zzea), Integer.valueOf(Arrays.hashCode(this.zzdm)), String.valueOf(this.zzp));
    }

    /* access modifiers changed from: package-private */
    public final void setCustomData(JSONObject jSONObject) {
        this.zzp = jSONObject;
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("media", this.zzdw.toJson());
            if (this.zzdx != 0) {
                jSONObject.put("itemId", this.zzdx);
            }
            jSONObject.put("autoplay", this.zzdj);
            jSONObject.put("startTime", this.zzdy);
            if (this.zzdz != Double.POSITIVE_INFINITY) {
                jSONObject.put("playbackDuration", this.zzdz);
            }
            jSONObject.put("preloadTime", this.zzea);
            if (this.zzdm != null) {
                JSONArray jSONArray = new JSONArray();
                for (long put : this.zzdm) {
                    jSONArray.put(put);
                }
                jSONObject.put("activeTrackIds", jSONArray);
            }
            if (this.zzp != null) {
                jSONObject.put("customData", this.zzp);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public void writeToParcel(Parcel parcel, int i) {
        JSONObject jSONObject = this.zzp;
        this.zzj = jSONObject == null ? null : jSONObject.toString();
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getMedia(), i, false);
        SafeParcelWriter.writeInt(parcel, 3, getItemId());
        SafeParcelWriter.writeBoolean(parcel, 4, getAutoplay());
        SafeParcelWriter.writeDouble(parcel, 5, getStartTime());
        SafeParcelWriter.writeDouble(parcel, 6, getPlaybackDuration());
        SafeParcelWriter.writeDouble(parcel, 7, getPreloadTime());
        SafeParcelWriter.writeLongArray(parcel, 8, getActiveTrackIds(), false);
        SafeParcelWriter.writeString(parcel, 9, this.zzj, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* access modifiers changed from: package-private */
    public final void zza(double d) throws IllegalArgumentException {
        if (Double.isNaN(d) || d < 0.0d) {
            throw new IllegalArgumentException("startTime cannot be negative or NaN.");
        }
        this.zzdy = d;
    }

    /* access modifiers changed from: package-private */
    public final void zza(int i) {
        this.zzdx = 0;
    }

    /* access modifiers changed from: package-private */
    public final void zza(long[] jArr) {
        this.zzdm = jArr;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(double d) throws IllegalArgumentException {
        if (!Double.isNaN(d)) {
            this.zzdz = d;
            return;
        }
        throw new IllegalArgumentException("playbackDuration cannot be NaN.");
    }

    /* access modifiers changed from: package-private */
    public final void zzc(double d) throws IllegalArgumentException {
        if (Double.isNaN(d) || d < 0.0d) {
            throw new IllegalArgumentException("preloadTime cannot be negative or NaN.");
        }
        this.zzea = d;
    }

    /* access modifiers changed from: package-private */
    public final void zze(boolean z) {
        this.zzdj = z;
    }

    public final boolean zzf(JSONObject jSONObject) throws JSONException {
        boolean z;
        boolean z2;
        int i;
        boolean z3 = false;
        if (jSONObject.has("media")) {
            this.zzdw = new MediaInfo(jSONObject.getJSONObject("media"));
            z = true;
        } else {
            z = false;
        }
        if (jSONObject.has("itemId") && this.zzdx != (i = jSONObject.getInt("itemId"))) {
            this.zzdx = i;
            z = true;
        }
        if (jSONObject.has("autoplay") && this.zzdj != (z2 = jSONObject.getBoolean("autoplay"))) {
            this.zzdj = z2;
            z = true;
        }
        if (jSONObject.has("startTime")) {
            double d = jSONObject.getDouble("startTime");
            if (Math.abs(d - this.zzdy) > 1.0E-7d) {
                this.zzdy = d;
                z = true;
            }
        }
        if (jSONObject.has("playbackDuration")) {
            double d2 = jSONObject.getDouble("playbackDuration");
            if (Math.abs(d2 - this.zzdz) > 1.0E-7d) {
                this.zzdz = d2;
                z = true;
            }
        }
        if (jSONObject.has("preloadTime")) {
            double d3 = jSONObject.getDouble("preloadTime");
            if (Math.abs(d3 - this.zzea) > 1.0E-7d) {
                this.zzea = d3;
                z = true;
            }
        }
        long[] jArr = null;
        if (jSONObject.has("activeTrackIds")) {
            JSONArray jSONArray = jSONObject.getJSONArray("activeTrackIds");
            int length = jSONArray.length();
            long[] jArr2 = new long[length];
            for (int i2 = 0; i2 < length; i2++) {
                jArr2[i2] = jSONArray.getLong(i2);
            }
            long[] jArr3 = this.zzdm;
            if (jArr3 != null && jArr3.length == length) {
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        jArr = jArr2;
                        break;
                    } else if (this.zzdm[i3] != jArr2[i3]) {
                        break;
                    } else {
                        i3++;
                    }
                }
            }
            jArr = jArr2;
            z3 = true;
        }
        if (z3) {
            this.zzdm = jArr;
            z = true;
        }
        if (!jSONObject.has("customData")) {
            return z;
        }
        this.zzp = jSONObject.getJSONObject("customData");
        return true;
    }

    /* access modifiers changed from: package-private */
    public final void zzi() throws IllegalArgumentException {
        if (this.zzdw == null) {
            throw new IllegalArgumentException("media cannot be null.");
        } else if (Double.isNaN(this.zzdy) || this.zzdy < 0.0d) {
            throw new IllegalArgumentException("startTime cannot be negative or NaN.");
        } else if (Double.isNaN(this.zzdz)) {
            throw new IllegalArgumentException("playbackDuration cannot be NaN.");
        } else if (Double.isNaN(this.zzea) || this.zzea < 0.0d) {
            throw new IllegalArgumentException("preloadTime cannot be negative or Nan.");
        }
    }
}
