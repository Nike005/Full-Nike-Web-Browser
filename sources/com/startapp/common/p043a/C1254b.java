package com.startapp.common.p043a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import com.mopub.common.GpsHelper;
import com.startapp.common.C1303g;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

/* renamed from: com.startapp.common.a.b */
/* compiled from: StartAppSDK */
public final class C1254b {

    /* renamed from: a */
    private static volatile C1258c f1483a;

    /* renamed from: com.startapp.common.a.b$e */
    /* compiled from: StartAppSDK */
    private static class C1260e {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public static final C1254b f1495a = new C1254b();
    }

    /* renamed from: a */
    public static boolean m1991a(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    /* renamed from: a */
    public static C1254b m1990a() {
        return C1260e.f1495a;
    }

    /* renamed from: a */
    public synchronized C1258c mo15441a(final Context context) {
        if (f1483a == null) {
            f1483a = new C1258c();
            try {
                mo15442a(m1992b(context));
            } catch (Exception e) {
                for (int i = 0; i < e.getStackTrace().length; i++) {
                }
                f1483a.mo15451a("0");
            }
        } else {
            C1303g.m2206a(C1303g.C1307a.HIGH, (Runnable) new Runnable() {
                public void run() {
                    C1256a aVar;
                    try {
                        aVar = C1254b.m1992b(context);
                    } catch (Exception unused) {
                        aVar = new C1256a("0", false, "");
                    }
                    C1254b.this.mo15442a(aVar);
                }
            });
        }
        return f1483a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public synchronized void mo15442a(C1256a aVar) {
        f1483a.mo15451a(aVar.mo15444a());
        f1483a.mo15452a(aVar.mo15445b());
        f1483a.mo15453b(aVar.mo15446c());
    }

    /* renamed from: com.startapp.common.a.b$c */
    /* compiled from: StartAppSDK */
    public static class C1258c {

        /* renamed from: a */
        private String f1491a = "";

        /* renamed from: b */
        private String f1492b = "";

        /* renamed from: c */
        private boolean f1493c = false;

        /* renamed from: a */
        public synchronized String mo15450a() {
            return this.f1491a;
        }

        /* renamed from: a */
        public synchronized void mo15451a(String str) {
            this.f1491a = str;
        }

        /* renamed from: b */
        public synchronized boolean mo15454b() {
            return this.f1493c;
        }

        /* renamed from: a */
        public synchronized void mo15452a(boolean z) {
            this.f1493c = z;
        }

        /* renamed from: c */
        public synchronized String mo15455c() {
            return this.f1492b;
        }

        /* renamed from: b */
        public synchronized void mo15453b(String str) {
            this.f1492b = str;
        }
    }

    /* renamed from: com.startapp.common.a.b$a */
    /* compiled from: StartAppSDK */
    public static final class C1256a {

        /* renamed from: a */
        private final String f1486a;

        /* renamed from: b */
        private final boolean f1487b;

        /* renamed from: c */
        private final String f1488c;

        C1256a(String str, boolean z, String str2) {
            this.f1486a = str;
            this.f1487b = z;
            this.f1488c = str2;
        }

        /* renamed from: a */
        public String mo15444a() {
            return this.f1486a;
        }

        /* renamed from: b */
        public boolean mo15445b() {
            return this.f1487b;
        }

        /* renamed from: c */
        public String mo15446c() {
            return this.f1488c;
        }
    }

    /* renamed from: b */
    public static C1256a m1992b(Context context) {
        if (m1993b()) {
            return m1994c(context);
        }
        return m1995d(context);
    }

    /* renamed from: c */
    private static C1256a m1994c(Context context) {
        try {
            Object invoke = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke((Object) null, new Object[]{context.getApplicationContext()});
            return new C1256a((String) invoke.getClass().getMethod("getId", new Class[0]).invoke(invoke, new Object[0]), ((Boolean) invoke.getClass().getMethod(GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY, new Class[0]).invoke(invoke, new Object[0])).booleanValue(), "APP");
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }

    /* renamed from: d */
    private static C1256a m1995d(Context context) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            try {
                context.getPackageManager().getPackageInfo("com.android.vending", 0);
                C1257b bVar = new C1257b();
                Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
                intent.setPackage("com.google.android.gms");
                if (context.getApplicationContext().bindService(intent, bVar, 1)) {
                    try {
                        C1259d dVar = new C1259d(bVar.mo15447a());
                        C1256a aVar = new C1256a(dVar.mo15456a(), dVar.mo15457a(true), "DEVICE");
                        context.getApplicationContext().unbindService(bVar);
                        return aVar;
                    } catch (Exception e) {
                        throw e;
                    } catch (Throwable th) {
                        context.getApplicationContext().unbindService(bVar);
                        throw th;
                    }
                } else {
                    throw new IOException("Google Play connection failed");
                }
            } catch (Exception e2) {
                throw e2;
            }
        } else {
            throw new IllegalStateException("Cannot be called from the main thread");
        }
    }

    /* renamed from: b */
    public static boolean m1993b() {
        return m1991a("com.google.android.gms.ads.identifier.AdvertisingIdClient");
    }

    /* renamed from: com.startapp.common.a.b$b */
    /* compiled from: StartAppSDK */
    protected static final class C1257b implements ServiceConnection {

        /* renamed from: a */
        boolean f1489a = false;

        /* renamed from: b */
        private final LinkedBlockingQueue<IBinder> f1490b = new LinkedBlockingQueue<>(1);

        public void onServiceDisconnected(ComponentName componentName) {
        }

        protected C1257b() {
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f1490b.put(iBinder);
            } catch (InterruptedException unused) {
            }
        }

        /* renamed from: a */
        public IBinder mo15447a() {
            if (!this.f1489a) {
                this.f1489a = true;
                return this.f1490b.take();
            }
            throw new IllegalStateException();
        }
    }

    /* renamed from: com.startapp.common.a.b$d */
    /* compiled from: StartAppSDK */
    private static final class C1259d implements IInterface {

        /* renamed from: a */
        private IBinder f1494a;

        public C1259d(IBinder iBinder) {
            this.f1494a = iBinder;
        }

        public IBinder asBinder() {
            return this.f1494a;
        }

        /* renamed from: a */
        public String mo15456a() {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.f1494a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        /* renamed from: a */
        public boolean mo15457a(boolean z) {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                boolean z2 = true;
                obtain.writeInt(z ? 1 : 0);
                this.f1494a.transact(2, obtain, obtain2, 0);
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
