package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.cast.zzcu;
import java.util.Locale;

public class LaunchOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<LaunchOptions> CREATOR = new zzah();
    private boolean zzcx;
    private String zzcy;

    public static final class Builder {
        private LaunchOptions zzcz = new LaunchOptions();

        public final LaunchOptions build() {
            return this.zzcz;
        }

        public final Builder setLocale(Locale locale) {
            this.zzcz.setLanguage(zzcu.zza(locale));
            return this;
        }

        public final Builder setRelaunchIfRunning(boolean z) {
            this.zzcz.setRelaunchIfRunning(z);
            return this;
        }
    }

    public LaunchOptions() {
        this(false, zzcu.zza(Locale.getDefault()));
    }

    LaunchOptions(boolean z, String str) {
        this.zzcx = z;
        this.zzcy = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LaunchOptions)) {
            return false;
        }
        LaunchOptions launchOptions = (LaunchOptions) obj;
        return this.zzcx == launchOptions.zzcx && zzcu.zza(this.zzcy, launchOptions.zzcy);
    }

    public String getLanguage() {
        return this.zzcy;
    }

    public boolean getRelaunchIfRunning() {
        return this.zzcx;
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.zzcx), this.zzcy);
    }

    public void setLanguage(String str) {
        this.zzcy = str;
    }

    public void setRelaunchIfRunning(boolean z) {
        this.zzcx = z;
    }

    public String toString() {
        return String.format("LaunchOptions(relaunchIfRunning=%b, language=%s)", new Object[]{Boolean.valueOf(this.zzcx), this.zzcy});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 2, getRelaunchIfRunning());
        SafeParcelWriter.writeString(parcel, 3, getLanguage(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
