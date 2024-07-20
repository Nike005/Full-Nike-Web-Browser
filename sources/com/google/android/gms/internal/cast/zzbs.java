package com.google.android.gms.internal.cast;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import org.json.JSONObject;

final class zzbs implements zzdm {
    private final /* synthetic */ zzbl zztn;
    private final /* synthetic */ zzbr zzto;

    zzbs(zzbr zzbr, zzbl zzbl) {
        this.zzto = zzbr;
        this.zztn = zzbl;
    }

    public final void zza(long j, int i, Object obj) {
        if (obj == null) {
            try {
                this.zzto.setResult(new zzbx(new Status(i, (String) null, (PendingIntent) null), (String) null, j, (JSONObject) null));
            } catch (ClassCastException unused) {
                this.zzto.setResult(zzbr.zzb(new Status(13)));
            }
        } else {
            zzbz zzbz = (zzbz) obj;
            String str = zzbz.zzug;
            if (i == 0 && str != null) {
                String unused2 = this.zzto.zzth.zztg = str;
            }
            this.zzto.setResult(new zzbx(new Status(i, zzbz.zztz, (PendingIntent) null), str, zzbz.zzuh, zzbz.zzua));
        }
    }

    public final void zzb(long j) {
        this.zzto.setResult(zzbr.zzb(new Status(2103)));
    }
}
