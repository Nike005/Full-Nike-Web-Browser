package com.google.android.gms.internal.cast;

import android.os.Handler;
import android.os.Looper;
import java.util.Locale;

public final class zzdn {
    private static final zzdg zzbd = new zzdg("RequestTracker");
    private static final Object zzxo = new Object();
    private final Handler handler = new Handler(Looper.getMainLooper());
    private Runnable zzoh;
    private long zztt = -1;
    private long zzxm;
    private zzdm zzxn;

    public zzdn(long j) {
        this.zzxm = j;
    }

    private final void zza(int i, Object obj, String str) {
        zzbd.mo6870d(str, new Object[0]);
        synchronized (zzxo) {
            if (this.zzxn != null) {
                this.zzxn.zza(this.zztt, i, obj);
            }
            this.zztt = -1;
            this.zzxn = null;
            synchronized (zzxo) {
                if (this.zzoh != null) {
                    this.handler.removeCallbacks(this.zzoh);
                    this.zzoh = null;
                }
            }
        }
    }

    private final boolean zza(int i, Object obj) {
        synchronized (zzxo) {
            if (this.zztt == -1) {
                return false;
            }
            zza(i, (Object) null, String.format(Locale.ROOT, "clearing request %d", new Object[]{Long.valueOf(this.zztt)}));
            return true;
        }
    }

    public final boolean test(long j) {
        boolean z;
        synchronized (zzxo) {
            z = this.zztt != -1 && this.zztt == j;
        }
        return z;
    }

    public final void zza(long j, zzdm zzdm) {
        zzdm zzdm2;
        long j2;
        synchronized (zzxo) {
            zzdm2 = this.zzxn;
            j2 = this.zztt;
            this.zztt = j;
            this.zzxn = zzdm;
        }
        if (zzdm2 != null) {
            zzdm2.zzb(j2);
        }
        synchronized (zzxo) {
            if (this.zzoh != null) {
                this.handler.removeCallbacks(this.zzoh);
            }
            zzdo zzdo = new zzdo(this);
            this.zzoh = zzdo;
            this.handler.postDelayed(zzdo, this.zzxm);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0029, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzc(long r8, int r10, java.lang.Object r11) {
        /*
            r7 = this;
            java.lang.Object r0 = zzxo
            monitor-enter(r0)
            long r1 = r7.zztt     // Catch:{ all -> 0x002a }
            r3 = -1
            r5 = 0
            int r6 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r6 == 0) goto L_0x0028
            long r1 = r7.zztt     // Catch:{ all -> 0x002a }
            int r3 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            if (r3 != 0) goto L_0x0028
            java.util.Locale r1 = java.util.Locale.ROOT     // Catch:{ all -> 0x002a }
            java.lang.String r2 = "request %d completed"
            r3 = 1
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x002a }
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x002a }
            r4[r5] = r8     // Catch:{ all -> 0x002a }
            java.lang.String r8 = java.lang.String.format(r1, r2, r4)     // Catch:{ all -> 0x002a }
            r7.zza(r10, r11, r8)     // Catch:{ all -> 0x002a }
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            return r3
        L_0x0028:
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            return r5
        L_0x002a:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.zzdn.zzc(long, int, java.lang.Object):boolean");
    }

    public final boolean zzdb() {
        boolean z;
        synchronized (zzxo) {
            z = this.zztt != -1;
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzdc() {
        synchronized (zzxo) {
            if (this.zztt != -1) {
                zza(15, (Object) null);
            }
        }
    }

    public final boolean zzq(int i) {
        return zza(2002, (Object) null);
    }
}
