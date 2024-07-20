package com.tappx.p048a;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.tappx.a.f5 */
public class C1414f5 implements Parcelable {
    public static final Parcelable.Creator<C1414f5> CREATOR = new C1415a();

    /* renamed from: a */
    private int f1843a;

    /* renamed from: b */
    private boolean f1844b;

    /* renamed from: c */
    private C1492k3 f1845c;

    /* renamed from: d */
    private int f1846d;

    /* renamed from: e */
    private int f1847e;

    /* renamed from: f */
    private boolean f1848f;

    /* renamed from: g */
    private boolean f1849g;

    /* renamed from: h */
    private C1474j3 f1850h;

    /* renamed from: com.tappx.a.f5$a */
    static class C1415a implements Parcelable.Creator<C1414f5> {
        C1415a() {
        }

        public C1414f5 createFromParcel(Parcel parcel) {
            return new C1414f5(parcel, (C1415a) null);
        }

        public C1414f5[] newArray(int i) {
            return new C1414f5[i];
        }
    }

    /* synthetic */ C1414f5(Parcel parcel, C1415a aVar) {
        this(parcel);
    }

    /* renamed from: a */
    public C1414f5 mo15795a(int i) {
        this.f1843a = i;
        return this;
    }

    /* renamed from: b */
    public C1492k3 mo15802b() {
        return this.f1845c;
    }

    /* renamed from: c */
    public int mo15803c() {
        return this.f1843a;
    }

    /* renamed from: d */
    public int mo15806d() {
        return this.f1847e;
    }

    public int describeContents() {
        return 0;
    }

    /* renamed from: e */
    public int mo15808e() {
        return this.f1846d;
    }

    /* renamed from: f */
    public boolean mo15809f() {
        return this.f1848f;
    }

    /* renamed from: g */
    public boolean mo15810g() {
        return this.f1849g;
    }

    /* renamed from: h */
    public boolean mo15811h() {
        return this.f1844b;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f1843a);
        parcel.writeByte(this.f1844b ? (byte) 1 : 0);
        parcel.writeString(C1492k3.m2929a(this.f1845c));
        parcel.writeInt(this.f1846d);
        parcel.writeInt(this.f1847e);
        parcel.writeByte(this.f1848f ? (byte) 1 : 0);
        parcel.writeByte(this.f1849g ? (byte) 1 : 0);
        parcel.writeString(C1474j3.m2884a(this.f1850h));
    }

    private C1414f5(Parcel parcel) {
        this.f1845c = C1492k3.NONE;
        this.f1843a = parcel.readInt();
        boolean z = true;
        this.f1844b = parcel.readByte() != 0;
        this.f1845c = C1492k3.m2928a(parcel.readString());
        this.f1846d = parcel.readInt();
        this.f1847e = parcel.readInt();
        this.f1848f = parcel.readByte() != 0;
        this.f1849g = parcel.readByte() == 0 ? false : z;
        this.f1850h = C1474j3.m2883a(parcel.readString());
    }

    /* renamed from: a */
    public C1414f5 mo15797a(C1492k3 k3Var) {
        if (k3Var == null) {
            k3Var = C1492k3.NONE;
        }
        this.f1845c = k3Var;
        return this;
    }

    /* renamed from: b */
    public C1414f5 mo15800b(int i) {
        this.f1847e = i;
        return this;
    }

    /* renamed from: c */
    public C1414f5 mo15805c(boolean z) {
        this.f1844b = z;
        return this;
    }

    /* renamed from: b */
    public C1414f5 mo15801b(boolean z) {
        this.f1849g = z;
        return this;
    }

    /* renamed from: c */
    public C1414f5 mo15804c(int i) {
        this.f1846d = i;
        return this;
    }

    /* renamed from: a */
    public C1414f5 mo15798a(boolean z) {
        this.f1848f = z;
        return this;
    }

    /* renamed from: a */
    public C1474j3 mo15799a() {
        return this.f1850h;
    }

    /* renamed from: a */
    public C1414f5 mo15796a(C1474j3 j3Var) {
        this.f1850h = j3Var;
        return this;
    }

    public C1414f5() {
        this.f1845c = C1492k3.NONE;
    }
}
