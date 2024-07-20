package com.google.android.gms.cast.framework.media;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public class NotificationAction extends AbstractSafeParcelable {
    public static final Parcelable.Creator<NotificationAction> CREATOR = new zzo();
    private final String zzlx;
    private final int zzly;
    private final String zzlz;

    public static final class Builder {
        private String zzlx;
        private int zzly;
        private String zzlz;

        public final NotificationAction build() {
            return new NotificationAction(this.zzlx, this.zzly, this.zzlz);
        }

        public final Builder setAction(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.zzlx = str;
                return this;
            }
            throw new IllegalArgumentException("action cannot be null or an empty string.");
        }

        public final Builder setContentDescription(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.zzlz = str;
                return this;
            }
            throw new IllegalArgumentException("contentDescription cannot be null  or an empty string.");
        }

        public final Builder setIconResId(int i) {
            this.zzly = i;
            return this;
        }
    }

    NotificationAction(String str, int i, String str2) {
        this.zzlx = str;
        this.zzly = i;
        this.zzlz = str2;
    }

    public String getAction() {
        return this.zzlx;
    }

    public String getContentDescription() {
        return this.zzlz;
    }

    public int getIconResId() {
        return this.zzly;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getAction(), false);
        SafeParcelWriter.writeInt(parcel, 3, getIconResId());
        SafeParcelWriter.writeString(parcel, 4, getContentDescription(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
