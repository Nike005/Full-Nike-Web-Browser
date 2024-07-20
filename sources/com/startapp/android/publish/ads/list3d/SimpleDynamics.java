package com.startapp.android.publish.ads.list3d;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: StartAppSDK */
class SimpleDynamics extends Dynamics implements Parcelable {
    public static final Parcelable.Creator<SimpleDynamics> CREATOR = new Parcelable.Creator<SimpleDynamics>() {
        /* renamed from: a */
        public SimpleDynamics createFromParcel(Parcel parcel) {
            return new SimpleDynamics(parcel);
        }

        /* renamed from: a */
        public SimpleDynamics[] newArray(int i) {
            return new SimpleDynamics[i];
        }
    };

    /* renamed from: f */
    private float f538f;

    /* renamed from: g */
    private float f539g;

    public int describeContents() {
        return 0;
    }

    public SimpleDynamics(float f, float f2) {
        this.f538f = f;
        this.f539g = f2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo13984a(int i) {
        this.f497b += mo13989c() * this.f539g;
        this.f496a += (this.f497b * ((float) i)) / 1000.0f;
        this.f497b *= this.f538f;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeFloat(this.f538f);
        parcel.writeFloat(this.f539g);
    }

    public SimpleDynamics(Parcel parcel) {
        super(parcel);
        this.f538f = parcel.readFloat();
        this.f539g = parcel.readFloat();
    }

    /* renamed from: a */
    public void mo13981a(double d) {
        super.mo13981a(d);
    }

    public String toString() {
        return super.toString() + ", Friction: [" + this.f538f + "], Snap:[" + this.f539g + "]";
    }
}
