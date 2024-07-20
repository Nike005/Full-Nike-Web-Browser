package com.yandex.metrica.impl;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Parcel;
import com.yandex.metrica.impl.C1906d;
import com.yandex.metrica.impl.p050ob.C2003bp;
import com.yandex.metrica.impl.p050ob.C2018cc;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

/* renamed from: com.yandex.metrica.impl.y */
public final class C2241y implements C1906d {

    /* renamed from: a */
    static final long f3915a = TimeUnit.SECONDS.toMillis(300);

    /* renamed from: b */
    static final long f3916b = TimeUnit.SECONDS.toMillis(120);

    /* renamed from: c */
    static final Set<String> f3917c = new HashSet(Arrays.asList(new String[]{"gps"}));

    /* renamed from: i */
    private static volatile C2241y f3918i;

    /* renamed from: j */
    private static final Object f3919j = new Object();

    /* renamed from: d */
    private final Context f3920d;

    /* renamed from: e */
    private final HandlerThread f3921e;

    /* renamed from: f */
    private final LocationManager f3922f;

    /* renamed from: g */
    private final WeakHashMap<Object, Object> f3923g;

    /* renamed from: h */
    private boolean f3924h;

    /* renamed from: k */
    private C1906d.C1907a<Location> f3925k = new C1906d.C1907a<>();

    /* renamed from: l */
    private boolean f3926l = false;

    /* renamed from: m */
    private C2018cc f3927m;

    /* renamed from: n */
    private LocationListener f3928n = new LocationListener() {
        public void onProviderDisabled(String str) {
        }

        public void onProviderEnabled(String str) {
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
        }

        public void onLocationChanged(Location location) {
            C2241y.this.mo17936a(location);
        }
    };

    private C2241y(Context context) {
        this.f3920d = context;
        this.f3923g = new WeakHashMap<>();
        this.f3924h = false;
        HandlerThread handlerThread = new HandlerThread("LHandlerThread");
        this.f3921e = handlerThread;
        handlerThread.start();
        this.f3922f = (LocationManager) context.getSystemService("location");
        C2018cc ccVar = new C2018cc(C2003bp.m5024a(this.f3920d).mo17287b());
        this.f3927m = ccVar;
        this.f3926l = ccVar.mo17411c();
    }

    /* renamed from: a */
    public static C2241y m5985a(Context context) {
        if (f3918i == null) {
            synchronized (f3919j) {
                if (f3918i == null) {
                    f3918i = new C2241y(context.getApplicationContext());
                }
            }
        }
        return f3918i;
    }

    /* renamed from: a */
    public synchronized void mo17937a(Object obj) {
        if (this.f3926l && C1837al.m4248a(this.f3920d, "android.permission.ACCESS_COARSE_LOCATION")) {
            this.f3923g.put(obj, (Object) null);
            if (!this.f3924h) {
                this.f3924h = true;
                m5986a("network", 0.0f, f3915a, this.f3928n, this.f3921e.getLooper());
                if (C1837al.m4248a(this.f3920d, "android.permission.ACCESS_FINE_LOCATION")) {
                    m5986a("passive", 0.0f, f3915a, this.f3928n, this.f3921e.getLooper());
                }
            }
        }
    }

    /* renamed from: a */
    private void m5986a(String str, float f, long j, LocationListener locationListener, Looper looper) {
        try {
            if (this.f3922f != null) {
                this.f3922f.requestLocationUpdates(str, j, f, locationListener, looper);
            }
        } catch (Exception unused) {
        }
    }

    /* renamed from: b */
    public synchronized void mo17940b(Object obj) {
        this.f3923g.remove(obj);
        mo17939b();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized void mo17935a() {
        this.f3923g.clear();
        mo17939b();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo17939b() {
        if (this.f3924h && this.f3923g.isEmpty()) {
            this.f3924h = false;
            try {
                if (this.f3922f != null) {
                    this.f3922f.removeUpdates(this.f3928n);
                }
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: a */
    public synchronized void mo17936a(Location location) {
        Location location2;
        if (this.f3925k.mo17103c() || m5987a(location, this.f3925k.mo17100a())) {
            if (location == null) {
                location2 = null;
            } else {
                location2 = new Location(location);
            }
            this.f3925k.mo17101a(location2);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public synchronized Location mo17941c() {
        return this.f3925k.mo17104d();
    }

    /* renamed from: d */
    public Location mo17942d() {
        List<String> allProviders;
        Location location;
        LocationManager locationManager = this.f3922f;
        if (locationManager == null || (allProviders = locationManager.getAllProviders()) == null) {
            return null;
        }
        boolean a = C1837al.m4248a(this.f3920d, "android.permission.ACCESS_COARSE_LOCATION");
        boolean a2 = C1837al.m4248a(this.f3920d, "android.permission.ACCESS_FINE_LOCATION");
        Location location2 = null;
        for (String next : allProviders) {
            if (!f3917c.contains(next)) {
                if (a) {
                    try {
                        if (!"passive".equals(next) || a2) {
                            location = this.f3922f.getLastKnownLocation(next);
                            if (location != null && m5987a(location, location2)) {
                                location2 = location;
                            }
                        }
                    } catch (Exception unused) {
                    }
                }
                location = null;
                location2 = location;
            }
        }
        return location2;
    }

    /* renamed from: a */
    static boolean m5987a(Location location, Location location2) {
        if (location2 == null) {
            return true;
        }
        if (location == null) {
            return false;
        }
        long time = location.getTime() - location2.getTime();
        boolean z = time > f3916b;
        boolean z2 = time < (-f3916b);
        boolean z3 = time > 0;
        if (z) {
            return true;
        }
        if (z2) {
            return false;
        }
        int accuracy = (int) (location.getAccuracy() - location2.getAccuracy());
        boolean z4 = accuracy > 0;
        boolean z5 = accuracy < 0;
        boolean z6 = ((long) accuracy) > 200;
        String provider = location.getProvider();
        String provider2 = location2.getProvider();
        boolean equals = provider == null ? provider2 == null : provider.equals(provider2);
        if (z5) {
            return true;
        }
        if (!z3 || z4) {
            return z3 && !z6 && equals;
        }
        return true;
    }

    /* renamed from: b */
    public static byte[] m5988b(Location location) {
        Parcel obtain = Parcel.obtain();
        byte[] bArr = new byte[0];
        try {
            obtain.writeValue(location);
            bArr = obtain.marshall();
        } catch (Exception unused) {
        } catch (Throwable th) {
            obtain.recycle();
            throw th;
        }
        obtain.recycle();
        return bArr;
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    public static Location m5984a(byte[] bArr) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.unmarshall(bArr, 0, bArr.length);
            obtain.setDataPosition(0);
            Location location = (Location) obtain.readValue(Location.class.getClassLoader());
            obtain.recycle();
            return location;
        } catch (Exception unused) {
            obtain.recycle();
            return null;
        } catch (Throwable th) {
            obtain.recycle();
            throw th;
        }
    }

    /* renamed from: a */
    public void mo17938a(Object obj, boolean z, boolean z2) {
        if (this.f3926l == z2) {
            return;
        }
        if (z) {
            this.f3926l = z2;
            this.f3927m.mo17406a(z2);
            if (this.f3926l) {
                mo17937a(obj);
            } else {
                mo17935a();
            }
        } else if (!z2) {
            mo17940b(obj);
        }
    }
}
