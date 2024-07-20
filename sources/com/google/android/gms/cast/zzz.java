package com.google.android.gms.cast;

import android.view.Display;
import com.google.android.gms.tasks.OnCompleteListener;

final class zzz implements OnCompleteListener<Display> {
    private final /* synthetic */ CastRemoteDisplayLocalService zzcg;

    zzz(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        this.zzcg = castRemoteDisplayLocalService;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0041, code lost:
        r5 = r5.getResult();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0047, code lost:
        if (r5 == null) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0049, code lost:
        r4.zzcg.zza(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004f, code lost:
        com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbd.mo6871e("Cast Remote Display session created without display", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005a, code lost:
        com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbp.set(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0067, code lost:
        if (r4.zzcg.zzby == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x006f, code lost:
        if (r4.zzcg.zzbz == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r4.zzcg.zzby.unbindService(r4.zzcg.zzbz);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0081, code lost:
        com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbd.mo6870d("No need to unbind service, already unbound", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onComplete(com.google.android.gms.tasks.Task<android.view.Display> r5) {
        /*
            r4 = this;
            boolean r0 = r5.isSuccessful()
            r1 = 0
            if (r0 != 0) goto L_0x0018
            com.google.android.gms.internal.cast.zzdg r5 = com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbd
            java.lang.String r0 = "Connection was not successful"
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r5.mo6871e(r0, r1)
            com.google.android.gms.cast.CastRemoteDisplayLocalService r5 = r4.zzcg
            r5.zzd()
            return
        L_0x0018:
            com.google.android.gms.internal.cast.zzdg r0 = com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbd
            java.lang.String r2 = "startRemoteDisplay successful"
            java.lang.Object[] r3 = new java.lang.Object[r1]
            r0.mo6870d(r2, r3)
            java.lang.Object r0 = com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbo
            monitor-enter(r0)
            com.google.android.gms.cast.CastRemoteDisplayLocalService r2 = com.google.android.gms.cast.CastRemoteDisplayLocalService.zzce     // Catch:{ all -> 0x0098 }
            if (r2 != 0) goto L_0x0040
            com.google.android.gms.internal.cast.zzdg r5 = com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbd     // Catch:{ all -> 0x0098 }
            java.lang.String r2 = "Remote Display started but session already cancelled"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0098 }
            r5.mo6870d(r2, r1)     // Catch:{ all -> 0x0098 }
            com.google.android.gms.cast.CastRemoteDisplayLocalService r5 = r4.zzcg     // Catch:{ all -> 0x0098 }
            r5.zzd()     // Catch:{ all -> 0x0098 }
            monitor-exit(r0)     // Catch:{ all -> 0x0098 }
            return
        L_0x0040:
            monitor-exit(r0)     // Catch:{ all -> 0x0098 }
            java.lang.Object r5 = r5.getResult()
            android.view.Display r5 = (android.view.Display) r5
            if (r5 == 0) goto L_0x004f
            com.google.android.gms.cast.CastRemoteDisplayLocalService r0 = r4.zzcg
            r0.zza((android.view.Display) r5)
            goto L_0x005a
        L_0x004f:
            com.google.android.gms.internal.cast.zzdg r5 = com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbd
            java.lang.String r0 = "Cast Remote Display session created without display"
            java.lang.Object[] r2 = new java.lang.Object[r1]
            r5.mo6871e(r0, r2)
        L_0x005a:
            java.util.concurrent.atomic.AtomicBoolean r5 = com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbp
            r5.set(r1)
            com.google.android.gms.cast.CastRemoteDisplayLocalService r5 = r4.zzcg
            android.content.Context r5 = r5.zzby
            if (r5 == 0) goto L_0x0097
            com.google.android.gms.cast.CastRemoteDisplayLocalService r5 = r4.zzcg
            android.content.ServiceConnection r5 = r5.zzbz
            if (r5 == 0) goto L_0x0097
            com.google.android.gms.cast.CastRemoteDisplayLocalService r5 = r4.zzcg     // Catch:{ IllegalArgumentException -> 0x0081 }
            android.content.Context r5 = r5.zzby     // Catch:{ IllegalArgumentException -> 0x0081 }
            com.google.android.gms.cast.CastRemoteDisplayLocalService r0 = r4.zzcg     // Catch:{ IllegalArgumentException -> 0x0081 }
            android.content.ServiceConnection r0 = r0.zzbz     // Catch:{ IllegalArgumentException -> 0x0081 }
            r5.unbindService(r0)     // Catch:{ IllegalArgumentException -> 0x0081 }
            goto L_0x008c
        L_0x0081:
            com.google.android.gms.internal.cast.zzdg r5 = com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbd
            java.lang.String r0 = "No need to unbind service, already unbound"
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r5.mo6870d(r0, r1)
        L_0x008c:
            com.google.android.gms.cast.CastRemoteDisplayLocalService r5 = r4.zzcg
            r0 = 0
            android.content.ServiceConnection unused = r5.zzbz = null
            com.google.android.gms.cast.CastRemoteDisplayLocalService r5 = r4.zzcg
            android.content.Context unused = r5.zzby = null
        L_0x0097:
            return
        L_0x0098:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0098 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.zzz.onComplete(com.google.android.gms.tasks.Task):void");
    }
}
