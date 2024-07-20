package com.google.android.p102a;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: com.google.android.a.a */
/* compiled from: BaseProxy */
public class C5189a implements IInterface {

    /* renamed from: a */
    private final IBinder f5052a;

    /* renamed from: b */
    private final String f5053b;

    protected C5189a(IBinder iBinder, String str) {
        this.f5052a = iBinder;
        this.f5053b = str;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Parcel mo42672a() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.f5053b);
        return obtain;
    }

    public final IBinder asBinder() {
        return this.f5052a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Parcel mo42673a(Parcel parcel) throws RemoteException {
        parcel = Parcel.obtain();
        try {
            this.f5052a.transact(1, parcel, parcel, 0);
            parcel.readException();
            return parcel;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            parcel.recycle();
        }
    }
}
