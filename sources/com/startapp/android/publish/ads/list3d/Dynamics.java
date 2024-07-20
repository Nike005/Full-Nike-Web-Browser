package com.startapp.android.publish.ads.list3d;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.animation.AnimationUtils;

/* compiled from: StartAppSDK */
public abstract class Dynamics implements Parcelable {

    /* renamed from: a */
    protected float f496a;

    /* renamed from: b */
    protected float f497b;

    /* renamed from: c */
    protected float f498c = Float.MAX_VALUE;

    /* renamed from: d */
    protected float f499d = -3.4028235E38f;

    /* renamed from: e */
    protected long f500e = 0;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo13984a(int i);

    public int describeContents() {
        return 0;
    }

    public Dynamics() {
    }

    public Dynamics(Parcel parcel) {
        this.f496a = parcel.readFloat();
        this.f497b = parcel.readFloat();
        this.f498c = parcel.readFloat();
        this.f499d = parcel.readFloat();
        this.f500e = AnimationUtils.currentAnimationTimeMillis();
    }

    /* renamed from: a */
    public void mo13983a(float f, float f2, long j) {
        this.f497b = f2;
        this.f496a = f;
        this.f500e = j;
    }

    /* renamed from: a */
    public float mo13980a() {
        return this.f496a;
    }

    /* renamed from: b */
    public float mo13987b() {
        return this.f497b;
    }

    /* renamed from: a */
    public boolean mo13986a(float f, float f2) {
        boolean z = Math.abs(this.f497b) < f;
        float f3 = this.f496a;
        boolean z2 = f3 - f2 < this.f498c && f3 + f2 > this.f499d;
        if (!z || !z2) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public void mo13982a(float f) {
        this.f498c = f;
    }

    /* renamed from: b */
    public void mo13988b(float f) {
        this.f499d = f;
    }

    /* renamed from: a */
    public void mo13985a(long j) {
        long j2 = this.f500e;
        if (j2 != 0) {
            int i = (int) (j - j2);
            if (i > 50) {
                i = 50;
            }
            mo13984a(i);
        }
        this.f500e = j;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public float mo13989c() {
        float f = this.f496a;
        float f2 = this.f498c;
        if (f <= f2) {
            f2 = this.f499d;
            if (f >= f2) {
                return 0.0f;
            }
        }
        return f2 - f;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.f496a);
        parcel.writeFloat(this.f497b);
        parcel.writeFloat(this.f498c);
        parcel.writeFloat(this.f499d);
    }

    /* renamed from: a */
    public void mo13981a(double d) {
        double d2 = (double) this.f496a;
        Double.isNaN(d2);
        this.f496a = (float) (d2 * d);
    }

    public String toString() {
        return "Position: [" + this.f496a + "], Velocity:[" + this.f497b + "], MaxPos: [" + this.f498c + "], mMinPos: [" + this.f499d + "] LastTime:[" + this.f500e + "]";
    }
}
