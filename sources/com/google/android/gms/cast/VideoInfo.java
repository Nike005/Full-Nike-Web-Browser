package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public final class VideoInfo extends AbstractSafeParcelable {
    public static final Parcelable.Creator<VideoInfo> CREATOR = new zzbt();
    public static final int HDR_TYPE_DV = 3;
    public static final int HDR_TYPE_HDR = 4;
    public static final int HDR_TYPE_HDR10 = 2;
    public static final int HDR_TYPE_SDR = 1;
    public static final int HDR_TYPE_UNKNOWN = 0;
    private int height;
    private int width;
    private int zzgn;

    VideoInfo(int i, int i2, int i3) {
        this.width = i;
        this.height = i2;
        this.zzgn = i3;
    }

    static VideoInfo zzg(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            String string = jSONObject.getString("hdrType");
            char c = 65535;
            int hashCode = string.hashCode();
            int i = 3;
            if (hashCode != 3218) {
                if (hashCode != 103158) {
                    if (hashCode != 113729) {
                        if (hashCode == 99136405) {
                            if (string.equals("hdr10")) {
                                c = 1;
                            }
                        }
                    } else if (string.equals("sdr")) {
                        c = 3;
                    }
                } else if (string.equals("hdr")) {
                    c = 2;
                }
            } else if (string.equals("dv")) {
                c = 0;
            }
            if (c != 0) {
                if (c == 1) {
                    i = 2;
                } else if (c == 2) {
                    i = 4;
                } else if (c != 3) {
                    Log.d("VideoInfo", String.format(Locale.ROOT, "Unknown HDR type: %s", new Object[]{string}));
                    i = 0;
                } else {
                    i = 1;
                }
            }
            return new VideoInfo(jSONObject.getInt("width"), jSONObject.getInt("height"), i);
        } catch (JSONException e) {
            Log.d("VideoInfo", String.format(Locale.ROOT, "Error while creating a VideoInfo instance from JSON: %s", new Object[]{e.getMessage()}));
            return null;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VideoInfo)) {
            return false;
        }
        VideoInfo videoInfo = (VideoInfo) obj;
        return this.height == videoInfo.getHeight() && this.width == videoInfo.getWidth() && this.zzgn == videoInfo.getHdrType();
    }

    public final int getHdrType() {
        return this.zzgn;
    }

    public final int getHeight() {
        return this.height;
    }

    public final int getWidth() {
        return this.width;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.height), Integer.valueOf(this.width), Integer.valueOf(this.zzgn));
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, getWidth());
        SafeParcelWriter.writeInt(parcel, 3, getHeight());
        SafeParcelWriter.writeInt(parcel, 4, getHdrType());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
