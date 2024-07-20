package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzcn;
import org.json.JSONObject;

final class zzbd extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzfa;
    private final /* synthetic */ GoogleApiClient zzfb;
    private final /* synthetic */ long zzfh;
    private final /* synthetic */ JSONObject zzfi;
    private final /* synthetic */ int zzfs;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbd(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, int i, GoogleApiClient googleApiClient2, long j, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzfa = remoteMediaPlayer;
        this.zzfs = i;
        this.zzfb = googleApiClient2;
        this.zzfh = j;
        this.zzfi = jSONObject;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzcn) anyClient);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004d, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        setResult((com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) createFailedResult(new com.google.android.gms.common.api.Status(2100)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r1 = r11.zzfa.zzev;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0068, code lost:
        r11.zzfa.zzev.zza((com.google.android.gms.common.api.GoogleApiClient) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0071, code lost:
        throw r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x004f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.cast.zzcn r12) {
        /*
            r11 = this;
            com.google.android.gms.cast.RemoteMediaPlayer r12 = r11.zzfa
            java.lang.Object r12 = r12.lock
            monitor-enter(r12)
            com.google.android.gms.cast.RemoteMediaPlayer r0 = r11.zzfa     // Catch:{ all -> 0x0072 }
            int r1 = r11.zzfs     // Catch:{ all -> 0x0072 }
            int r0 = r0.zzc((int) r1)     // Catch:{ all -> 0x0072 }
            r1 = -1
            if (r0 != r1) goto L_0x0023
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x0072 }
            r1 = 0
            r0.<init>(r1)     // Catch:{ all -> 0x0072 }
            com.google.android.gms.common.api.Result r0 = r11.createFailedResult(r0)     // Catch:{ all -> 0x0072 }
            com.google.android.gms.cast.RemoteMediaPlayer$MediaChannelResult r0 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r0     // Catch:{ all -> 0x0072 }
            r11.setResult(r0)     // Catch:{ all -> 0x0072 }
            monitor-exit(r12)     // Catch:{ all -> 0x0072 }
            return
        L_0x0023:
            com.google.android.gms.cast.RemoteMediaPlayer r0 = r11.zzfa     // Catch:{ all -> 0x0072 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r0 = r0.zzev     // Catch:{ all -> 0x0072 }
            com.google.android.gms.common.api.GoogleApiClient r1 = r11.zzfb     // Catch:{ all -> 0x0072 }
            r0.zza(r1)     // Catch:{ all -> 0x0072 }
            r0 = 0
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r11.zzfa     // Catch:{ zzdk | IllegalStateException -> 0x004f }
            com.google.android.gms.internal.cast.zzdh r2 = r1.zzeu     // Catch:{ zzdk | IllegalStateException -> 0x004f }
            com.google.android.gms.internal.cast.zzdm r3 = r11.zzgc     // Catch:{ zzdk | IllegalStateException -> 0x004f }
            int r4 = r11.zzfs     // Catch:{ zzdk | IllegalStateException -> 0x004f }
            long r5 = r11.zzfh     // Catch:{ zzdk | IllegalStateException -> 0x004f }
            r7 = 0
            r8 = 0
            r9 = 0
            org.json.JSONObject r10 = r11.zzfi     // Catch:{ zzdk | IllegalStateException -> 0x004f }
            r2.zza((com.google.android.gms.internal.cast.zzdm) r3, (int) r4, (long) r5, (com.google.android.gms.cast.MediaQueueItem[]) r7, (int) r8, (java.lang.Integer) r9, (org.json.JSONObject) r10)     // Catch:{ zzdk | IllegalStateException -> 0x004f }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r11.zzfa     // Catch:{ all -> 0x0072 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r1 = r1.zzev     // Catch:{ all -> 0x0072 }
        L_0x0049:
            r1.zza(r0)     // Catch:{ all -> 0x0072 }
            goto L_0x0066
        L_0x004d:
            r1 = move-exception
            goto L_0x0068
        L_0x004f:
            com.google.android.gms.common.api.Status r1 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x004d }
            r2 = 2100(0x834, float:2.943E-42)
            r1.<init>(r2)     // Catch:{ all -> 0x004d }
            com.google.android.gms.common.api.Result r1 = r11.createFailedResult(r1)     // Catch:{ all -> 0x004d }
            com.google.android.gms.cast.RemoteMediaPlayer$MediaChannelResult r1 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r1     // Catch:{ all -> 0x004d }
            r11.setResult(r1)     // Catch:{ all -> 0x004d }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r11.zzfa     // Catch:{ all -> 0x0072 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r1 = r1.zzev     // Catch:{ all -> 0x0072 }
            goto L_0x0049
        L_0x0066:
            monitor-exit(r12)     // Catch:{ all -> 0x0072 }
            return
        L_0x0068:
            com.google.android.gms.cast.RemoteMediaPlayer r2 = r11.zzfa     // Catch:{ all -> 0x0072 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r2 = r2.zzev     // Catch:{ all -> 0x0072 }
            r2.zza(r0)     // Catch:{ all -> 0x0072 }
            throw r1     // Catch:{ all -> 0x0072 }
        L_0x0072:
            r0 = move-exception
            monitor-exit(r12)     // Catch:{ all -> 0x0072 }
            goto L_0x0076
        L_0x0075:
            throw r0
        L_0x0076:
            goto L_0x0075
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.zzbd.zza(com.google.android.gms.internal.cast.zzcn):void");
    }
}
