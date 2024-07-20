package com.p070a.p071a.p072a.p073a;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* renamed from: com.a.a.a.a.a */
/* compiled from: StartAppSDK */
public interface C4625a extends IInterface {
    /* renamed from: a */
    Bundle mo40051a(Bundle bundle);

    /* renamed from: com.a.a.a.a.a$a */
    /* compiled from: StartAppSDK */
    public static abstract class C4626a extends Binder implements C4625a {
        /* renamed from: a */
        public static C4625a m6226a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof C4625a)) {
                return new C4627a(iBinder);
            }
            return (C4625a) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface("com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
                Bundle a = mo40051a(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                if (a != null) {
                    parcel2.writeInt(1);
                    a.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
                return true;
            }
        }

        /* renamed from: com.a.a.a.a.a$a$a */
        /* compiled from: StartAppSDK */
        private static class C4627a implements C4625a {

            /* renamed from: a */
            private IBinder f4134a;

            C4627a(IBinder iBinder) {
                this.f4134a = iBinder;
            }

            public IBinder asBinder() {
                return this.f4134a;
            }

            /* renamed from: a */
            public Bundle mo40051a(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f4134a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
