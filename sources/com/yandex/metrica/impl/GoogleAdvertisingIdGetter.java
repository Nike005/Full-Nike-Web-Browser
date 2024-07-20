package com.yandex.metrica.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Pair;
import com.mopub.common.GpsHelper;
import com.yandex.metrica.impl.p050ob.C2174g;
import com.yandex.metrica.impl.p050ob.C2180i;
import com.yandex.metrica.impl.p050ob.C2184l;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;

public class GoogleAdvertisingIdGetter {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public volatile String f2864a = null;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public volatile Boolean f2865b = null;

    /* renamed from: c */
    private final Object f2866c = new Object();

    /* renamed from: d */
    private volatile FutureTask<Pair<String, Boolean>> f2867d;

    /* renamed from: com.yandex.metrica.impl.GoogleAdvertisingIdGetter$b */
    private static class C1803b {

        /* renamed from: a */
        static final GoogleAdvertisingIdGetter f2873a = new GoogleAdvertisingIdGetter();
    }

    /* renamed from: com.yandex.metrica.impl.GoogleAdvertisingIdGetter$c */
    private interface C1804c<T> {
        /* renamed from: b */
        T mo16741b(Future<Pair<String, Boolean>> future) throws InterruptedException, ExecutionException;
    }

    /* renamed from: a */
    public void mo16734a(final Context context) {
        if (this.f2867d == null) {
            synchronized (this.f2866c) {
                if (this.f2867d == null) {
                    this.f2867d = new FutureTask<>(new Callable<Pair<String, Boolean>>() {
                        /* renamed from: a */
                        public Pair<String, Boolean> call() {
                            Context applicationContext = context.getApplicationContext();
                            if (GoogleAdvertisingIdGetter.m4105d(applicationContext)) {
                                GoogleAdvertisingIdGetter.m4100a(C1803b.f2873a, applicationContext);
                            }
                            if (!GoogleAdvertisingIdGetter.this.mo16737c()) {
                                GoogleAdvertisingIdGetter.m4103b(C1803b.f2873a, applicationContext);
                            }
                            return new Pair<>(GoogleAdvertisingIdGetter.this.f2864a, GoogleAdvertisingIdGetter.this.f2865b);
                        }
                    });
                    new Thread(this.f2867d).start();
                }
            }
        }
    }

    /* renamed from: a */
    private void m4101a(String str) {
        C2174g.m5753a().mo17806b((C2180i) new C2184l(str));
        this.f2864a = str;
    }

    /* renamed from: a */
    private <T> T m4098a(Context context, C1804c<T> cVar) {
        mo16734a(context);
        try {
            return cVar.mo16741b(this.f2867d);
        } catch (InterruptedException | ExecutionException unused) {
            return null;
        }
    }

    /* renamed from: b */
    public String mo16736b(Context context) {
        return (String) m4098a(context, new C1804c<String>() {
            /* renamed from: a */
            public String mo16741b(Future<Pair<String, Boolean>> future) throws InterruptedException, ExecutionException {
                return (String) future.get().first;
            }
        });
    }

    /* renamed from: a */
    public String mo16733a() {
        return this.f2864a;
    }

    /* renamed from: b */
    public Boolean mo16735b() {
        return this.f2865b;
    }

    /* renamed from: c */
    public synchronized boolean mo16737c() {
        return (this.f2864a == null || this.f2865b == null) ? false : true;
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public static boolean m4105d(Context context) {
        try {
            return Class.forName("com.google.android.gms.common.GooglePlayServicesUtil").getMethod("isGooglePlayServicesAvailable", new Class[]{Context.class}).invoke((Object) null, new Object[]{context}).equals(0);
        } catch (Exception unused) {
            return false;
        }
    }

    private interface GoogleAdvertisingInfo extends IInterface {
        boolean getEnabled(boolean z) throws RemoteException;

        String getId() throws RemoteException;

        public static abstract class GoogleAdvertisingInfoBinder extends Binder implements GoogleAdvertisingInfo {
            public static GoogleAdvertisingInfo Create(IBinder iBinder) {
                if (iBinder == null) {
                    return null;
                }
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                if (queryLocalInterface == null || !(queryLocalInterface instanceof GoogleAdvertisingInfo)) {
                    return new GoogleAdvertisingInfoImplementation(iBinder);
                }
                return (GoogleAdvertisingInfo) queryLocalInterface;
            }

            public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
                if (i == 1) {
                    parcel.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                    String id = getId();
                    parcel2.writeNoException();
                    parcel2.writeString(id);
                    return true;
                } else if (i != 2) {
                    return super.onTransact(i, parcel, parcel2, i2);
                } else {
                    parcel.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                    boolean enabled = getEnabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(enabled ? 1 : 0);
                    return true;
                }
            }

            private static class GoogleAdvertisingInfoImplementation implements GoogleAdvertisingInfo {

                /* renamed from: a */
                private IBinder f2870a;

                GoogleAdvertisingInfoImplementation(IBinder iBinder) {
                    this.f2870a = iBinder;
                }

                public IBinder asBinder() {
                    return this.f2870a;
                }

                public String getId() throws RemoteException {
                    Parcel obtain = Parcel.obtain();
                    Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        this.f2870a.transact(1, obtain, obtain2, 0);
                        obtain2.readException();
                        return obtain2.readString();
                    } finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                }

                public boolean getEnabled(boolean z) throws RemoteException {
                    Parcel obtain = Parcel.obtain();
                    Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        boolean z2 = true;
                        obtain.writeInt(z ? 1 : 0);
                        this.f2870a.transact(2, obtain, obtain2, 0);
                        obtain2.readException();
                        if (obtain2.readInt() == 0) {
                            z2 = false;
                        }
                        return z2;
                    } finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                }
            }
        }
    }

    /* renamed from: com.yandex.metrica.impl.GoogleAdvertisingIdGetter$a */
    private class C1802a implements ServiceConnection {

        /* renamed from: a */
        private boolean f2871a;

        /* renamed from: b */
        private final BlockingQueue<IBinder> f2872b;

        public void onServiceDisconnected(ComponentName componentName) {
        }

        private C1802a() {
            this.f2871a = false;
            this.f2872b = new LinkedBlockingQueue();
        }

        /* synthetic */ C1802a(byte b) {
            this();
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f2872b.put(iBinder);
            } catch (InterruptedException unused) {
            }
        }

        /* renamed from: a */
        public IBinder mo16746a() throws InterruptedException {
            if (!this.f2871a) {
                this.f2871a = true;
                return this.f2872b.take();
            }
            throw new IllegalStateException();
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m4100a(GoogleAdvertisingIdGetter googleAdvertisingIdGetter, Context context) {
        try {
            Object invoke = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke((Object) null, new Object[]{context});
            Class<?> cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
            String str = (String) cls.getMethod("getId", new Class[0]).invoke(invoke, new Object[0]);
            Boolean bool = (Boolean) cls.getMethod(GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY, new Class[0]).invoke(invoke, new Object[0]);
            synchronized (googleAdvertisingIdGetter) {
                googleAdvertisingIdGetter.m4101a(str);
                googleAdvertisingIdGetter.f2865b = bool;
            }
        } catch (Exception unused) {
        }
    }

    /* renamed from: b */
    static /* synthetic */ void m4103b(GoogleAdvertisingIdGetter googleAdvertisingIdGetter, Context context) {
        C1802a aVar = new C1802a((byte) 0);
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        if (context.bindService(intent, aVar, 1)) {
            try {
                GoogleAdvertisingInfo Create = GoogleAdvertisingInfo.GoogleAdvertisingInfoBinder.Create(aVar.mo16746a());
                String id = Create.getId();
                Boolean valueOf = Boolean.valueOf(Create.getEnabled(true));
                synchronized (googleAdvertisingIdGetter) {
                    googleAdvertisingIdGetter.m4101a(id);
                    googleAdvertisingIdGetter.f2865b = valueOf;
                }
                context.unbindService(aVar);
            } catch (Exception unused) {
                context.unbindService(aVar);
            } catch (Throwable th) {
                context.unbindService(aVar);
                throw th;
            }
        }
    }
}
