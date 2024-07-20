package com.tappx.p048a;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/* renamed from: com.tappx.a.g1 */
public final class C1419g1 {

    /* renamed from: a */
    public final String f1856a;

    /* renamed from: b */
    public final C1420a f1857b;

    /* renamed from: com.tappx.a.g1$a */
    public static class C1420a {

        /* renamed from: a */
        public final double f1858a;

        /* renamed from: b */
        public final double f1859b;

        /* renamed from: c */
        public final long f1860c;

        /* renamed from: d */
        public final long f1861d;

        public C1420a(double d, double d2, long j, long j2) {
            this.f1858a = d;
            this.f1859b = d2;
            this.f1860c = j;
            this.f1861d = j2;
        }
    }

    public C1419g1(String str, C1420a aVar) {
        this.f1856a = str;
        this.f1857b = aVar;
    }

    /* renamed from: com.tappx.a.g1$b */
    public static class C1421b {

        /* renamed from: b */
        private static volatile C1421b f1862b;

        /* renamed from: a */
        private final Context f1863a;

        public C1421b(Context context) {
            this.f1863a = context;
        }

        /* renamed from: a */
        public static final C1421b m2693a(Context context) {
            if (f1862b == null) {
                synchronized (C1421b.class) {
                    if (f1862b == null) {
                        f1862b = new C1421b(context);
                    }
                }
            }
            return f1862b;
        }

        /* renamed from: b */
        private C1420a m2694b() {
            Location d = m2696d();
            if (d == null) {
                return null;
            }
            return new C1420a(d.getLatitude(), d.getLongitude(), (long) d.getAccuracy(), System.currentTimeMillis() - d.getTime());
        }

        /* renamed from: c */
        private Location m2695c() {
            if (!C1366d3.m2453a(this.f1863a, "android.permission.ACCESS_FINE_LOCATION")) {
                return null;
            }
            return m2692a("gps");
        }

        /* renamed from: d */
        private Location m2696d() {
            return m2691a(m2697e(), m2695c());
        }

        /* renamed from: e */
        private Location m2697e() {
            if (!(C1366d3.m2453a(this.f1863a, "android.permission.ACCESS_FINE_LOCATION") || C1366d3.m2453a(this.f1863a, "android.permission.ACCESS_COARSE_LOCATION"))) {
                return null;
            }
            return m2692a("network");
        }

        /* renamed from: f */
        private String m2698f() {
            return new SimpleDateFormat("Z", Locale.US).format(Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault()).getTime());
        }

        /* renamed from: a */
        public C1419g1 mo15820a() {
            return new C1419g1(m2698f(), m2694b());
        }

        /* renamed from: a */
        private Location m2692a(String str) {
            try {
                return ((LocationManager) this.f1863a.getSystemService("location")).getLastKnownLocation(str);
            } catch (IllegalArgumentException | SecurityException e) {
                e.printStackTrace();
                return null;
            }
        }

        /* renamed from: a */
        private Location m2691a(Location location, Location location2) {
            if (location == null) {
                return location2;
            }
            return (location2 != null && location.getTime() <= location2.getTime()) ? location2 : location;
        }
    }
}
