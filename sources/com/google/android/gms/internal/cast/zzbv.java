package com.google.android.gms.internal.cast;

import acr.browser.lightning.BuildConfig;
import android.app.PendingIntent;
import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.common.api.Status;
import java.util.Locale;

final class zzbv implements zzdm {
    private final /* synthetic */ zzbl zztn;
    private final /* synthetic */ zzbu zztr;

    zzbv(zzbu zzbu, zzbl zzbl) {
        this.zztr = zzbu;
        this.zztn = zzbl;
    }

    public final void zza(long j, int i, Object obj) {
        if (obj == null) {
            try {
                this.zztr.setResult(new zzbw(new Status(i, (String) null, (PendingIntent) null), this.zztr.zztq));
            } catch (ClassCastException unused) {
                this.zztr.setResult(zzbu.zzc(new Status(13)));
            }
        } else {
            zzbz zzbz = (zzbz) obj;
            zzby zzby = zzbz.zzuj;
            if (zzby == null || zzcu.zza(BuildConfig.VERSION_NAME, zzby.getVersion())) {
                this.zztr.setResult(new zzbw(new Status(i, zzbz.zztz, (PendingIntent) null), this.zztr.zztq));
                return;
            }
            zzby unused2 = this.zztr.zzth.zzsx = null;
            this.zztr.setResult(zzbu.zzc(new Status(GameManagerClient.STATUS_INCORRECT_VERSION, String.format(Locale.ROOT, "Incorrect Game Manager SDK version. Receiver: %s Sender: %s", new Object[]{zzby.getVersion(), BuildConfig.VERSION_NAME}))));
        }
    }

    public final void zzb(long j) {
        this.zztr.setResult(zzbu.zzc(new Status(2103)));
    }
}
