package com.google.android.finsky.externalreferrer;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.p102a.C5189a;
import com.google.android.p102a.C5190b;
import com.google.android.p102a.C5191c;

public interface IGetInstallReferrerService extends IInterface {

    public static abstract class Stub extends C5190b implements IGetInstallReferrerService {

        public static class Proxy extends C5189a implements IGetInstallReferrerService {
            Proxy(IBinder iBinder) {
                super(iBinder, "com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
            }

            /* renamed from: a */
            public final Bundle mo1443a(Bundle bundle) throws RemoteException {
                Parcel a = mo42672a();
                C5191c.m7230a(a, (Parcelable) bundle);
                Parcel a2 = mo42673a(a);
                Bundle bundle2 = (Bundle) C5191c.m7229a(a2, Bundle.CREATOR);
                a2.recycle();
                return bundle2;
            }
        }

        public Stub() {
            super("com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
        }

        /* renamed from: a */
        public static IGetInstallReferrerService m17a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
            if (queryLocalInterface instanceof IGetInstallReferrerService) {
                return (IGetInstallReferrerService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }
    }

    /* renamed from: a */
    Bundle mo1443a(Bundle bundle) throws RemoteException;
}
