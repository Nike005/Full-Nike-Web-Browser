package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.internal.cast.zzcu;
import com.startapp.android.publish.common.model.AdPreferences;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public final class MediaTrack extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<MediaTrack> CREATOR = new zzao();
    public static final int SUBTYPE_CAPTIONS = 2;
    public static final int SUBTYPE_CHAPTERS = 4;
    public static final int SUBTYPE_DESCRIPTIONS = 3;
    public static final int SUBTYPE_METADATA = 5;
    public static final int SUBTYPE_NONE = 0;
    public static final int SUBTYPE_SUBTITLES = 1;
    public static final int SUBTYPE_UNKNOWN = -1;
    public static final int TYPE_AUDIO = 2;
    public static final int TYPE_TEXT = 1;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_VIDEO = 3;

    /* renamed from: id */
    private long f19id;
    private String name;
    private int type;
    private String zzcy;
    private String zzda;
    private int zzes;
    private String zzj;
    private String zzk;
    private JSONObject zzp;

    public static class Builder {
        private final MediaTrack zzet;

        public Builder(long j, int i) throws IllegalArgumentException {
            this.zzet = new MediaTrack(j, i);
        }

        public MediaTrack build() {
            return this.zzet;
        }

        public Builder setContentId(String str) {
            this.zzet.setContentId(str);
            return this;
        }

        public Builder setContentType(String str) {
            this.zzet.setContentType(str);
            return this;
        }

        public Builder setCustomData(JSONObject jSONObject) {
            this.zzet.setCustomData(jSONObject);
            return this;
        }

        public Builder setLanguage(String str) {
            this.zzet.setLanguage(str);
            return this;
        }

        public Builder setLanguage(Locale locale) {
            this.zzet.setLanguage(zzcu.zza(locale));
            return this;
        }

        public Builder setName(String str) {
            this.zzet.setName(str);
            return this;
        }

        public Builder setSubtype(int i) throws IllegalArgumentException {
            this.zzet.zzb(i);
            return this;
        }
    }

    MediaTrack(long j, int i) throws IllegalArgumentException {
        this(0, 0, (String) null, (String) null, (String) null, (String) null, -1, (String) null);
        this.f19id = j;
        if (i <= 0 || i > 3) {
            StringBuilder sb = new StringBuilder(24);
            sb.append("invalid type ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }
        this.type = i;
    }

    MediaTrack(long j, int i, String str, String str2, String str3, String str4, int i2, String str5) {
        this.f19id = j;
        this.type = i;
        this.zzk = str;
        this.zzda = str2;
        this.name = str3;
        this.zzcy = str4;
        this.zzes = i2;
        this.zzj = str5;
        if (str5 != null) {
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

    MediaTrack(JSONObject jSONObject) throws JSONException {
        this(0, 0, (String) null, (String) null, (String) null, (String) null, -1, (String) null);
        int i;
        this.f19id = jSONObject.getLong("trackId");
        String string = jSONObject.getString("type");
        if (AdPreferences.TYPE_TEXT.equals(string)) {
            this.type = 1;
        } else if ("AUDIO".equals(string)) {
            this.type = 2;
        } else if ("VIDEO".equals(string)) {
            this.type = 3;
        } else {
            String valueOf = String.valueOf(string);
            throw new JSONException(valueOf.length() != 0 ? "invalid type: ".concat(valueOf) : new String("invalid type: "));
        }
        this.zzk = jSONObject.optString("trackContentId", (String) null);
        this.zzda = jSONObject.optString("trackContentType", (String) null);
        this.name = jSONObject.optString("name", (String) null);
        this.zzcy = jSONObject.optString("language", (String) null);
        if (jSONObject.has("subtype")) {
            String string2 = jSONObject.getString("subtype");
            if ("SUBTITLES".equals(string2)) {
                this.zzes = 1;
            } else if ("CAPTIONS".equals(string2)) {
                this.zzes = 2;
            } else if ("DESCRIPTIONS".equals(string2)) {
                this.zzes = 3;
            } else if ("CHAPTERS".equals(string2)) {
                i = 4;
            } else if ("METADATA".equals(string2)) {
                i = 5;
            } else {
                String valueOf2 = String.valueOf(string2);
                throw new JSONException(valueOf2.length() != 0 ? "invalid subtype: ".concat(valueOf2) : new String("invalid subtype: "));
            }
            this.zzp = jSONObject.optJSONObject("customData");
        }
        i = 0;
        this.zzes = i;
        this.zzp = jSONObject.optJSONObject("customData");
    }

    public final boolean equals(Object obj) {
        JSONObject jSONObject;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaTrack)) {
            return false;
        }
        MediaTrack mediaTrack = (MediaTrack) obj;
        if ((this.zzp == null) != (mediaTrack.zzp == null)) {
            return false;
        }
        JSONObject jSONObject2 = this.zzp;
        return (jSONObject2 == null || (jSONObject = mediaTrack.zzp) == null || JsonUtils.areJsonValuesEquivalent(jSONObject2, jSONObject)) && this.f19id == mediaTrack.f19id && this.type == mediaTrack.type && zzcu.zza(this.zzk, mediaTrack.zzk) && zzcu.zza(this.zzda, mediaTrack.zzda) && zzcu.zza(this.name, mediaTrack.name) && zzcu.zza(this.zzcy, mediaTrack.zzcy) && this.zzes == mediaTrack.zzes;
    }

    public final String getContentId() {
        return this.zzk;
    }

    public final String getContentType() {
        return this.zzda;
    }

    public final JSONObject getCustomData() {
        return this.zzp;
    }

    public final long getId() {
        return this.f19id;
    }

    public final String getLanguage() {
        return this.zzcy;
    }

    public final String getName() {
        return this.name;
    }

    public final int getSubtype() {
        return this.zzes;
    }

    public final int getType() {
        return this.type;
    }

    public final int hashCode() {
        return Objects.hashCode(Long.valueOf(this.f19id), Integer.valueOf(this.type), this.zzk, this.zzda, this.name, this.zzcy, Integer.valueOf(this.zzes), String.valueOf(this.zzp));
    }

    public final void setContentId(String str) {
        this.zzk = str;
    }

    public final void setContentType(String str) {
        this.zzda = str;
    }

    /* access modifiers changed from: package-private */
    public final void setCustomData(JSONObject jSONObject) {
        this.zzp = jSONObject;
    }

    /* access modifiers changed from: package-private */
    public final void setLanguage(String str) {
        this.zzcy = str;
    }

    /* access modifiers changed from: package-private */
    public final void setName(String str) {
        this.name = str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x002a A[Catch:{ JSONException -> 0x0084 }] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0035 A[Catch:{ JSONException -> 0x0084 }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0040 A[Catch:{ JSONException -> 0x0084 }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004f A[Catch:{ JSONException -> 0x0084 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0076 A[Catch:{ JSONException -> 0x0084 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x007d A[Catch:{ JSONException -> 0x0084 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final org.json.JSONObject toJson() {
        /*
            r6 = this;
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r1 = "trackId"
            long r2 = r6.f19id     // Catch:{ JSONException -> 0x0084 }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x0084 }
            int r1 = r6.type     // Catch:{ JSONException -> 0x0084 }
            r2 = 3
            r3 = 2
            r4 = 1
            java.lang.String r5 = "type"
            if (r1 == r4) goto L_0x0023
            if (r1 == r3) goto L_0x0020
            if (r1 == r2) goto L_0x001a
            goto L_0x0026
        L_0x001a:
            java.lang.String r1 = "VIDEO"
        L_0x001c:
            r0.put(r5, r1)     // Catch:{ JSONException -> 0x0084 }
            goto L_0x0026
        L_0x0020:
            java.lang.String r1 = "AUDIO"
            goto L_0x001c
        L_0x0023:
            java.lang.String r1 = "TEXT"
            goto L_0x001c
        L_0x0026:
            java.lang.String r1 = r6.zzk     // Catch:{ JSONException -> 0x0084 }
            if (r1 == 0) goto L_0x0031
            java.lang.String r1 = "trackContentId"
            java.lang.String r5 = r6.zzk     // Catch:{ JSONException -> 0x0084 }
            r0.put(r1, r5)     // Catch:{ JSONException -> 0x0084 }
        L_0x0031:
            java.lang.String r1 = r6.zzda     // Catch:{ JSONException -> 0x0084 }
            if (r1 == 0) goto L_0x003c
            java.lang.String r1 = "trackContentType"
            java.lang.String r5 = r6.zzda     // Catch:{ JSONException -> 0x0084 }
            r0.put(r1, r5)     // Catch:{ JSONException -> 0x0084 }
        L_0x003c:
            java.lang.String r1 = r6.name     // Catch:{ JSONException -> 0x0084 }
            if (r1 == 0) goto L_0x0047
            java.lang.String r1 = "name"
            java.lang.String r5 = r6.name     // Catch:{ JSONException -> 0x0084 }
            r0.put(r1, r5)     // Catch:{ JSONException -> 0x0084 }
        L_0x0047:
            java.lang.String r1 = r6.zzcy     // Catch:{ JSONException -> 0x0084 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ JSONException -> 0x0084 }
            if (r1 != 0) goto L_0x0056
            java.lang.String r1 = "language"
            java.lang.String r5 = r6.zzcy     // Catch:{ JSONException -> 0x0084 }
            r0.put(r1, r5)     // Catch:{ JSONException -> 0x0084 }
        L_0x0056:
            int r1 = r6.zzes     // Catch:{ JSONException -> 0x0084 }
            java.lang.String r5 = "subtype"
            if (r1 == r4) goto L_0x0076
            if (r1 == r3) goto L_0x0073
            if (r1 == r2) goto L_0x0070
            r2 = 4
            if (r1 == r2) goto L_0x006d
            r2 = 5
            if (r1 == r2) goto L_0x0067
            goto L_0x0079
        L_0x0067:
            java.lang.String r1 = "METADATA"
        L_0x0069:
            r0.put(r5, r1)     // Catch:{ JSONException -> 0x0084 }
            goto L_0x0079
        L_0x006d:
            java.lang.String r1 = "CHAPTERS"
            goto L_0x0069
        L_0x0070:
            java.lang.String r1 = "DESCRIPTIONS"
            goto L_0x0069
        L_0x0073:
            java.lang.String r1 = "CAPTIONS"
            goto L_0x0069
        L_0x0076:
            java.lang.String r1 = "SUBTITLES"
            goto L_0x0069
        L_0x0079:
            org.json.JSONObject r1 = r6.zzp     // Catch:{ JSONException -> 0x0084 }
            if (r1 == 0) goto L_0x0084
            java.lang.String r1 = "customData"
            org.json.JSONObject r2 = r6.zzp     // Catch:{ JSONException -> 0x0084 }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x0084 }
        L_0x0084:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.MediaTrack.toJson():org.json.JSONObject");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        JSONObject jSONObject = this.zzp;
        this.zzj = jSONObject == null ? null : jSONObject.toString();
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 2, getId());
        SafeParcelWriter.writeInt(parcel, 3, getType());
        SafeParcelWriter.writeString(parcel, 4, getContentId(), false);
        SafeParcelWriter.writeString(parcel, 5, getContentType(), false);
        SafeParcelWriter.writeString(parcel, 6, getName(), false);
        SafeParcelWriter.writeString(parcel, 7, getLanguage(), false);
        SafeParcelWriter.writeInt(parcel, 8, getSubtype());
        SafeParcelWriter.writeString(parcel, 9, this.zzj, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* access modifiers changed from: package-private */
    public final void zzb(int i) throws IllegalArgumentException {
        if (i < 0 || i > 5) {
            StringBuilder sb = new StringBuilder(27);
            sb.append("invalid subtype ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        } else if (i == 0 || this.type == 1) {
            this.zzes = i;
        } else {
            throw new IllegalArgumentException("subtypes are only valid for text tracks");
        }
    }
}
