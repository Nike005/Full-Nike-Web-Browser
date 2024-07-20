package com.google.android.gms.cast;

final class zzv implements Runnable {
    private final /* synthetic */ CastRemoteDisplayLocalService zzcg;

    zzv(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        this.zzcg = castRemoteDisplayLocalService;
    }

    public final void run() {
        CastRemoteDisplayLocalService castRemoteDisplayLocalService = this.zzcg;
        boolean zzb = castRemoteDisplayLocalService.zzcb;
        StringBuilder sb = new StringBuilder(59);
        sb.append("onCreate after delay. The local service been started: ");
        sb.append(zzb);
        castRemoteDisplayLocalService.zzb(sb.toString());
        if (!this.zzcg.zzcb) {
            this.zzcg.zzc("The local service has not been been started, stopping it");
            this.zzcg.stopSelf();
        }
    }
}
