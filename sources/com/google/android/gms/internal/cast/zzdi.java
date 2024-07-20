package com.google.android.gms.internal.cast;

final class zzdi implements zzdm {
    private final /* synthetic */ zzdm zzxk;
    private final /* synthetic */ zzdh zzxl;

    zzdi(zzdh zzdh, zzdm zzdm) {
        this.zzxl = zzdh;
        this.zzxk = zzdm;
    }

    public final void zza(long j, int i, Object obj) {
        if (i == 15) {
            this.zzxl.zzda();
        }
        zzdm zzdm = this.zzxk;
        if (zzdm != null) {
            zzdm.zza(j, i, obj);
        }
    }

    public final void zzb(long j) {
        zzdm zzdm = this.zzxk;
        if (zzdm != null) {
            zzdm.zzb(j);
        }
    }
}
