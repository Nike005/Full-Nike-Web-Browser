package com.google.android.gms.internal.cast;

import android.os.Handler;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import java.util.concurrent.atomic.AtomicReference;

final class zzcp extends zzdc {
    private final Handler handler;
    private final AtomicReference<zzcn> zzvv;

    public zzcp(zzcn zzcn) {
        this.zzvv = new AtomicReference<>(zzcn);
        this.handler = new Handler(zzcn.getLooper());
    }

    public final boolean isDisposed() {
        return this.zzvv.get() == null;
    }

    public final void onApplicationDisconnected(int i) {
        zzcn zzcn = this.zzvv.get();
        if (zzcn != null) {
            String unused = zzcn.zzvj = null;
            String unused2 = zzcn.zzvk = null;
            zzcn.zzm(i);
            if (zzcn.zzaj != null) {
                this.handler.post(new zzcq(this, zzcn, i));
            }
        }
    }

    public final void zza(ApplicationMetadata applicationMetadata, String str, String str2, boolean z) {
        zzcn zzcn = this.zzvv.get();
        if (zzcn != null) {
            ApplicationMetadata unused = zzcn.zzux = applicationMetadata;
            String unused2 = zzcn.zzvj = applicationMetadata.getApplicationId();
            String unused3 = zzcn.zzvk = str2;
            String unused4 = zzcn.zzvb = str;
            synchronized (zzcn.zzvp) {
                if (zzcn.zzvn != null) {
                    zzcn.zzvn.setResult(new zzco(new Status(0), applicationMetadata, str, str2, z));
                    BaseImplementation.ResultHolder unused5 = zzcn.zzvn = null;
                }
            }
        }
    }

    public final void zza(String str, double d, boolean z) {
        zzcn.zzbd.mo6870d("Deprecated callback: \"onStatusreceived\"", new Object[0]);
    }

    public final void zza(String str, long j) {
        zzcn zzcn = this.zzvv.get();
        if (zzcn != null) {
            zzcn.zzb(j, 0);
        }
    }

    public final void zza(String str, long j, int i) {
        zzcn zzcn = this.zzvv.get();
        if (zzcn != null) {
            zzcn.zzb(j, i);
        }
    }

    public final void zza(String str, byte[] bArr) {
        if (this.zzvv.get() != null) {
            zzcn.zzbd.mo6870d("IGNORING: Receive (type=binary, ns=%s) <%d bytes>", str, Integer.valueOf(bArr.length));
        }
    }

    public final void zzb(zzcd zzcd) {
        zzcn zzcn = this.zzvv.get();
        if (zzcn != null) {
            zzcn.zzbd.mo6870d("onApplicationStatusChanged", new Object[0]);
            this.handler.post(new zzcs(this, zzcn, zzcd));
        }
    }

    public final void zzb(zzcv zzcv) {
        zzcn zzcn = this.zzvv.get();
        if (zzcn != null) {
            zzcn.zzbd.mo6870d("onDeviceStatusChanged", new Object[0]);
            this.handler.post(new zzcr(this, zzcn, zzcv));
        }
    }

    public final void zzb(String str, String str2) {
        zzcn zzcn = this.zzvv.get();
        if (zzcn != null) {
            zzcn.zzbd.mo6870d("Receive (type=text, ns=%s) %s", str, str2);
            this.handler.post(new zzct(this, zzcn, str, str2));
        }
    }

    public final zzcn zzcu() {
        zzcn andSet = this.zzvv.getAndSet((Object) null);
        if (andSet == null) {
            return null;
        }
        andSet.zzcp();
        return andSet;
    }

    public final void zzf(int i) {
        zzcn zzcn = this.zzvv.get();
        if (zzcn != null) {
            zzcn.zzl(i);
        }
    }

    public final void zzn(int i) {
        zzcn zzcu = zzcu();
        if (zzcu != null) {
            zzcn.zzbd.mo6870d("ICastDeviceControllerListener.onDisconnected: %d", Integer.valueOf(i));
            if (i != 0) {
                zzcu.triggerConnectionSuspended(2);
            }
        }
    }

    public final void zzo(int i) {
        zzcn zzcn = this.zzvv.get();
        if (zzcn != null) {
            zzcn.zzm(i);
        }
    }

    public final void zzp(int i) {
        zzcn zzcn = this.zzvv.get();
        if (zzcn != null) {
            zzcn.zzm(i);
        }
    }
}
