package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CastOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<CastOptions> CREATOR = new zzb();
    private final LaunchOptions zzcz;
    private String zzhc;
    private final List<String> zzhd;
    private final boolean zzhe;
    private final boolean zzhf;
    private final CastMediaOptions zzhg;
    private final boolean zzhh;
    private final double zzhi;

    public static final class Builder {
        private LaunchOptions zzcz = new LaunchOptions();
        private String zzhc;
        private List<String> zzhd = new ArrayList();
        private boolean zzhe;
        private boolean zzhf = true;
        private CastMediaOptions zzhg = new CastMediaOptions.Builder().build();
        private boolean zzhh = true;
        private double zzhi = 0.05000000074505806d;

        public final CastOptions build() {
            return new CastOptions(this.zzhc, this.zzhd, this.zzhe, this.zzcz, this.zzhf, this.zzhg, this.zzhh, this.zzhi);
        }

        public final Builder setCastMediaOptions(CastMediaOptions castMediaOptions) {
            this.zzhg = castMediaOptions;
            return this;
        }

        public final Builder setEnableReconnectionService(boolean z) {
            this.zzhh = z;
            return this;
        }

        public final Builder setLaunchOptions(LaunchOptions launchOptions) {
            this.zzcz = launchOptions;
            return this;
        }

        public final Builder setReceiverApplicationId(String str) {
            this.zzhc = str;
            return this;
        }

        public final Builder setResumeSavedSession(boolean z) {
            this.zzhf = z;
            return this;
        }

        public final Builder setStopReceiverApplicationWhenEndingSession(boolean z) {
            this.zzhe = z;
            return this;
        }

        public final Builder setSupportedNamespaces(List<String> list) {
            this.zzhd = list;
            return this;
        }

        public final Builder setVolumeDeltaBeforeIceCreamSandwich(double d) throws IllegalArgumentException {
            if (d <= 0.0d || d > 0.5d) {
                throw new IllegalArgumentException("volumeDelta must be greater than 0 and less or equal to 0.5");
            }
            this.zzhi = d;
            return this;
        }
    }

    CastOptions(String str, List<String> list, boolean z, LaunchOptions launchOptions, boolean z2, CastMediaOptions castMediaOptions, boolean z3, double d) {
        this.zzhc = TextUtils.isEmpty(str) ? "" : str;
        int size = list == null ? 0 : list.size();
        ArrayList arrayList = new ArrayList(size);
        this.zzhd = arrayList;
        if (size > 0) {
            arrayList.addAll(list);
        }
        this.zzhe = z;
        this.zzcz = launchOptions == null ? new LaunchOptions() : launchOptions;
        this.zzhf = z2;
        this.zzhg = castMediaOptions;
        this.zzhh = z3;
        this.zzhi = d;
    }

    public CastMediaOptions getCastMediaOptions() {
        return this.zzhg;
    }

    public boolean getEnableReconnectionService() {
        return this.zzhh;
    }

    public LaunchOptions getLaunchOptions() {
        return this.zzcz;
    }

    public String getReceiverApplicationId() {
        return this.zzhc;
    }

    public boolean getResumeSavedSession() {
        return this.zzhf;
    }

    public boolean getStopReceiverApplicationWhenEndingSession() {
        return this.zzhe;
    }

    public List<String> getSupportedNamespaces() {
        return Collections.unmodifiableList(this.zzhd);
    }

    public double getVolumeDeltaBeforeIceCreamSandwich() {
        return this.zzhi;
    }

    public final void setReceiverApplicationId(String str) {
        this.zzhc = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getReceiverApplicationId(), false);
        SafeParcelWriter.writeStringList(parcel, 3, getSupportedNamespaces(), false);
        SafeParcelWriter.writeBoolean(parcel, 4, getStopReceiverApplicationWhenEndingSession());
        SafeParcelWriter.writeParcelable(parcel, 5, getLaunchOptions(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 6, getResumeSavedSession());
        SafeParcelWriter.writeParcelable(parcel, 7, getCastMediaOptions(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 8, getEnableReconnectionService());
        SafeParcelWriter.writeDouble(parcel, 9, getVolumeDeltaBeforeIceCreamSandwich());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
