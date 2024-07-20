package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.internal.cast.zzcu;
import com.mopub.mobileads.VastIconXmlManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MediaInfo extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<MediaInfo> CREATOR = new zzai();
    public static final int STREAM_TYPE_BUFFERED = 1;
    public static final int STREAM_TYPE_INVALID = -1;
    public static final int STREAM_TYPE_LIVE = 2;
    public static final int STREAM_TYPE_NONE = 0;
    public static final long UNKNOWN_DURATION = -1;
    private int streamType;
    private String zzda;
    private MediaMetadata zzdb;
    private long zzdc;
    private List<MediaTrack> zzdd;
    private TextTrackStyle zzde;
    private List<AdBreakInfo> zzdf;
    private List<AdBreakClipInfo> zzdg;
    private String zzdh;
    private String zzj;
    private final String zzk;
    private JSONObject zzp;

    public static class Builder {
        private final MediaInfo zzdi;

        public Builder(String str) throws IllegalArgumentException {
            this.zzdi = new MediaInfo(str);
        }

        public Builder(String str, String str2) throws IllegalArgumentException {
            this.zzdi = new MediaInfo(str, str2);
        }

        public MediaInfo build() {
            return this.zzdi;
        }

        public Builder setAdBreakClips(List<AdBreakClipInfo> list) {
            this.zzdi.zzc(list);
            return this;
        }

        public Builder setAdBreaks(List<AdBreakInfo> list) {
            this.zzdi.zzb(list);
            return this;
        }

        public Builder setContentType(String str) {
            this.zzdi.setContentType(str);
            return this;
        }

        public Builder setCustomData(JSONObject jSONObject) {
            this.zzdi.setCustomData(jSONObject);
            return this;
        }

        public Builder setEntity(String str) {
            this.zzdi.zzd(str);
            return this;
        }

        public Builder setMediaTracks(List<MediaTrack> list) {
            this.zzdi.zza(list);
            return this;
        }

        public Builder setMetadata(MediaMetadata mediaMetadata) {
            this.zzdi.zza(mediaMetadata);
            return this;
        }

        public Builder setStreamDuration(long j) throws IllegalArgumentException {
            this.zzdi.zza(j);
            return this;
        }

        public Builder setStreamType(int i) throws IllegalArgumentException {
            this.zzdi.setStreamType(i);
            return this;
        }

        public Builder setTextTrackStyle(TextTrackStyle textTrackStyle) {
            this.zzdi.setTextTrackStyle(textTrackStyle);
            return this;
        }
    }

    MediaInfo(String str) throws IllegalArgumentException {
        this(str, -1, (String) null, (MediaMetadata) null, -1, (List<MediaTrack>) null, (TextTrackStyle) null, (String) null, (List<AdBreakInfo>) null, (List<AdBreakClipInfo>) null, (String) null);
        if (str == null) {
            throw new IllegalArgumentException("contentID cannot be null");
        }
    }

    MediaInfo(String str, int i, String str2, MediaMetadata mediaMetadata, long j, List<MediaTrack> list, TextTrackStyle textTrackStyle, String str3, List<AdBreakInfo> list2, List<AdBreakClipInfo> list3, String str4) {
        this.zzk = str;
        this.streamType = i;
        this.zzda = str2;
        this.zzdb = mediaMetadata;
        this.zzdc = j;
        this.zzdd = list;
        this.zzde = textTrackStyle;
        this.zzj = str3;
        if (str3 != null) {
            try {
                this.zzp = new JSONObject(this.zzj);
            } catch (JSONException unused) {
                this.zzp = null;
                this.zzj = null;
            }
        } else {
            this.zzp = null;
        }
        this.zzdf = list2;
        this.zzdg = list3;
        this.zzdh = str4;
    }

    MediaInfo(String str, String str2) throws IllegalArgumentException {
        this(str, -1, (String) null, (MediaMetadata) null, -1, (List<MediaTrack>) null, (TextTrackStyle) null, (String) null, (List<AdBreakInfo>) null, (List<AdBreakClipInfo>) null, str2);
        if (str == null) {
            throw new IllegalArgumentException("contentID cannot be null");
        }
    }

    MediaInfo(JSONObject jSONObject) throws JSONException {
        this(jSONObject.getString("contentId"), -1, (String) null, (MediaMetadata) null, -1, (List<MediaTrack>) null, (TextTrackStyle) null, (String) null, (List<AdBreakInfo>) null, (List<AdBreakClipInfo>) null, (String) null);
        String string = jSONObject.getString("streamType");
        if ("NONE".equals(string)) {
            this.streamType = 0;
        } else {
            this.streamType = "BUFFERED".equals(string) ? 1 : "LIVE".equals(string) ? 2 : -1;
        }
        TextTrackStyle textTrackStyle = null;
        this.zzda = jSONObject.optString("contentType", (String) null);
        if (jSONObject.has("metadata")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("metadata");
            MediaMetadata mediaMetadata = new MediaMetadata(jSONObject2.getInt("metadataType"));
            this.zzdb = mediaMetadata;
            mediaMetadata.zze(jSONObject2);
        }
        this.zzdc = -1;
        if (jSONObject.has(VastIconXmlManager.DURATION) && !jSONObject.isNull(VastIconXmlManager.DURATION)) {
            double optDouble = jSONObject.optDouble(VastIconXmlManager.DURATION, 0.0d);
            if (!Double.isNaN(optDouble) && !Double.isInfinite(optDouble)) {
                this.zzdc = (long) (optDouble * 1000.0d);
            }
        }
        if (jSONObject.has("tracks")) {
            this.zzdd = new ArrayList();
            JSONArray jSONArray = jSONObject.getJSONArray("tracks");
            for (int i = 0; i < jSONArray.length(); i++) {
                this.zzdd.add(new MediaTrack(jSONArray.getJSONObject(i)));
            }
        } else {
            this.zzdd = null;
        }
        if (jSONObject.has("textTrackStyle")) {
            JSONObject jSONObject3 = jSONObject.getJSONObject("textTrackStyle");
            textTrackStyle = new TextTrackStyle();
            textTrackStyle.zze(jSONObject3);
        }
        this.zzde = textTrackStyle;
        zzd(jSONObject);
        this.zzp = jSONObject.optJSONObject("customData");
        if (jSONObject.has("entity")) {
            this.zzdh = jSONObject.getString("entity");
        }
    }

    public boolean equals(Object obj) {
        JSONObject jSONObject;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaInfo)) {
            return false;
        }
        MediaInfo mediaInfo = (MediaInfo) obj;
        if ((this.zzp == null) != (mediaInfo.zzp == null)) {
            return false;
        }
        JSONObject jSONObject2 = this.zzp;
        return (jSONObject2 == null || (jSONObject = mediaInfo.zzp) == null || JsonUtils.areJsonValuesEquivalent(jSONObject2, jSONObject)) && zzcu.zza(this.zzk, mediaInfo.zzk) && this.streamType == mediaInfo.streamType && zzcu.zza(this.zzda, mediaInfo.zzda) && zzcu.zza(this.zzdb, mediaInfo.zzdb) && this.zzdc == mediaInfo.zzdc && zzcu.zza(this.zzdd, mediaInfo.zzdd) && zzcu.zza(this.zzde, mediaInfo.zzde) && zzcu.zza(this.zzdf, mediaInfo.zzdf) && zzcu.zza(this.zzdg, mediaInfo.zzdg) && zzcu.zza(this.zzdh, mediaInfo.zzdh);
    }

    public List<AdBreakClipInfo> getAdBreakClips() {
        List<AdBreakClipInfo> list = this.zzdg;
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }

    public List<AdBreakInfo> getAdBreaks() {
        List<AdBreakInfo> list = this.zzdf;
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }

    public String getContentId() {
        return this.zzk;
    }

    public String getContentType() {
        return this.zzda;
    }

    public JSONObject getCustomData() {
        return this.zzp;
    }

    public String getEntity() {
        return this.zzdh;
    }

    public List<MediaTrack> getMediaTracks() {
        return this.zzdd;
    }

    public MediaMetadata getMetadata() {
        return this.zzdb;
    }

    public long getStreamDuration() {
        return this.zzdc;
    }

    public int getStreamType() {
        return this.streamType;
    }

    public TextTrackStyle getTextTrackStyle() {
        return this.zzde;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzk, Integer.valueOf(this.streamType), this.zzda, this.zzdb, Long.valueOf(this.zzdc), String.valueOf(this.zzp), this.zzdd, this.zzde, this.zzdf, this.zzdg, this.zzdh);
    }

    /* access modifiers changed from: package-private */
    public final void setContentType(String str) {
        this.zzda = str;
    }

    /* access modifiers changed from: package-private */
    public final void setCustomData(JSONObject jSONObject) {
        this.zzp = jSONObject;
    }

    /* access modifiers changed from: package-private */
    public final void setStreamType(int i) throws IllegalArgumentException {
        if (i < -1 || i > 2) {
            throw new IllegalArgumentException("invalid stream type");
        }
        this.streamType = i;
    }

    public void setTextTrackStyle(TextTrackStyle textTrackStyle) {
        this.zzde = textTrackStyle;
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("contentId", this.zzk);
            int i = this.streamType;
            jSONObject.put("streamType", i != 1 ? i != 2 ? "NONE" : "LIVE" : "BUFFERED");
            if (this.zzda != null) {
                jSONObject.put("contentType", this.zzda);
            }
            if (this.zzdb != null) {
                jSONObject.put("metadata", this.zzdb.toJson());
            }
            if (this.zzdc <= -1) {
                jSONObject.put(VastIconXmlManager.DURATION, JSONObject.NULL);
            } else {
                double d = (double) this.zzdc;
                Double.isNaN(d);
                jSONObject.put(VastIconXmlManager.DURATION, d / 1000.0d);
            }
            if (this.zzdd != null) {
                JSONArray jSONArray = new JSONArray();
                for (MediaTrack json : this.zzdd) {
                    jSONArray.put(json.toJson());
                }
                jSONObject.put("tracks", jSONArray);
            }
            if (this.zzde != null) {
                jSONObject.put("textTrackStyle", this.zzde.toJson());
            }
            if (this.zzp != null) {
                jSONObject.put("customData", this.zzp);
            }
            if (this.zzdh != null) {
                jSONObject.put("entity", this.zzdh);
            }
            if (this.zzdf != null) {
                JSONArray jSONArray2 = new JSONArray();
                for (AdBreakInfo json2 : this.zzdf) {
                    jSONArray2.put(json2.toJson());
                }
                jSONObject.put("breaks", jSONArray2);
            }
            if (this.zzdg != null) {
                JSONArray jSONArray3 = new JSONArray();
                for (AdBreakClipInfo json3 : this.zzdg) {
                    jSONArray3.put(json3.toJson());
                }
                jSONObject.put("breakClips", jSONArray3);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public void writeToParcel(Parcel parcel, int i) {
        JSONObject jSONObject = this.zzp;
        this.zzj = jSONObject == null ? null : jSONObject.toString();
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getContentId(), false);
        SafeParcelWriter.writeInt(parcel, 3, getStreamType());
        SafeParcelWriter.writeString(parcel, 4, getContentType(), false);
        SafeParcelWriter.writeParcelable(parcel, 5, getMetadata(), i, false);
        SafeParcelWriter.writeLong(parcel, 6, getStreamDuration());
        SafeParcelWriter.writeTypedList(parcel, 7, getMediaTracks(), false);
        SafeParcelWriter.writeParcelable(parcel, 8, getTextTrackStyle(), i, false);
        SafeParcelWriter.writeString(parcel, 9, this.zzj, false);
        SafeParcelWriter.writeTypedList(parcel, 10, getAdBreaks(), false);
        SafeParcelWriter.writeTypedList(parcel, 11, getAdBreakClips(), false);
        SafeParcelWriter.writeString(parcel, 12, getEntity(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* access modifiers changed from: package-private */
    public final void zza(long j) throws IllegalArgumentException {
        if (j >= 0 || j == -1) {
            this.zzdc = j;
            return;
        }
        throw new IllegalArgumentException("Invalid stream duration");
    }

    /* access modifiers changed from: package-private */
    public final void zza(MediaMetadata mediaMetadata) {
        this.zzdb = mediaMetadata;
    }

    /* access modifiers changed from: package-private */
    public final void zza(List<MediaTrack> list) {
        this.zzdd = list;
    }

    public final void zzb(List<AdBreakInfo> list) {
        this.zzdf = list;
    }

    /* access modifiers changed from: package-private */
    public final void zzc(List<AdBreakClipInfo> list) {
        this.zzdg = list;
    }

    public final void zzd(String str) {
        this.zzdh = str;
    }

    /* access modifiers changed from: package-private */
    public final void zzd(JSONObject jSONObject) throws JSONException {
        int i = 0;
        if (jSONObject.has("breaks")) {
            JSONArray jSONArray = jSONObject.getJSONArray("breaks");
            this.zzdf = new ArrayList(jSONArray.length());
            int i2 = 0;
            while (true) {
                if (i2 < jSONArray.length()) {
                    AdBreakInfo zzb = AdBreakInfo.zzb(jSONArray.getJSONObject(i2));
                    if (zzb == null) {
                        this.zzdf.clear();
                        break;
                    } else {
                        this.zzdf.add(zzb);
                        i2++;
                    }
                } else {
                    break;
                }
            }
        }
        if (jSONObject.has("breakClips")) {
            JSONArray jSONArray2 = jSONObject.getJSONArray("breakClips");
            this.zzdg = new ArrayList(jSONArray2.length());
            while (i < jSONArray2.length()) {
                AdBreakClipInfo zza = AdBreakClipInfo.zza(jSONArray2.getJSONObject(i));
                if (zza != null) {
                    this.zzdg.add(zza);
                    i++;
                } else {
                    this.zzdg.clear();
                    return;
                }
            }
        }
    }
}
