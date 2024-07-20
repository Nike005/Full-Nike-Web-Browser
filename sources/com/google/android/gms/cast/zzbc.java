package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzcn;
import org.json.JSONObject;

final class zzbc extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzfa;
    private final /* synthetic */ GoogleApiClient zzfb;
    private final /* synthetic */ JSONObject zzfi;
    private final /* synthetic */ int zzfs;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbc(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, int i, GoogleApiClient googleApiClient2, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzfa = remoteMediaPlayer;
        this.zzfs = i;
        this.zzfb = googleApiClient2;
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
        r1 = r6.zzfa.zzev;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0068, code lost:
        r6.zzfa.zzev.zza((com.google.android.gms.common.api.GoogleApiClient) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0071, code lost:
        throw r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x004f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.cast.zzcn r7) {
        /*
            r6 = this;
            com.google.android.gms.cast.RemoteMediaPlayer r7 = r6.zzfa
            java.lang.Object r7 = r7.lock
            monitor-enter(r7)
            com.google.android.gms.cast.RemoteMediaPlayer r0 = r6.zzfa     // Catch:{ all -> 0x0072 }
            int r1 = r6.zzfs     // Catch:{ all -> 0x0072 }
            int r0 = r0.zzc((int) r1)     // Catch:{ all -> 0x0072 }
            r1 = -1
            r2 = 0
            if (r0 != r1) goto L_0x0023
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x0072 }
            r0.<init>(r2)     // Catch:{ all -> 0x0072 }
            com.google.android.gms.common.api.Result r0 = r6.createFailedResult(r0)     // Catch:{ all -> 0x0072 }
            com.google.android.gms.cast.RemoteMediaPlayer$MediaChannelResult r0 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r0     // Catch:{ all -> 0x0072 }
            r6.setResult(r0)     // Catch:{ all -> 0x0072 }
            monitor-exit(r7)     // Catch:{ all -> 0x0072 }
            return
        L_0x0023:
            com.google.android.gms.cast.RemoteMediaPlayer r0 = r6.zzfa     // Catch:{ all -> 0x0072 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r0 = r0.zzev     // Catch:{ all -> 0x0072 }
            com.google.android.gms.common.api.GoogleApiClient r1 = r6.zzfb     // Catch:{ all -> 0x0072 }
            r0.zza(r1)     // Catch:{ all -> 0x0072 }
            r0 = 0
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r6.zzfa     // Catch:{ zzdk | IllegalStateException -> 0x004f }
            com.google.android.gms.internal.cast.zzdh r1 = r1.zzeu     // Catch:{ zzdk | IllegalStateException -> 0x004f }
            com.google.android.gms.internal.cast.zzdm r3 = r6.zzgc     // Catch:{ zzdk | IllegalStateException -> 0x004f }
            r4 = 1
            int[] r4 = new int[r4]     // Catch:{ zzdk | IllegalStateException -> 0x004f }
            int r5 = r6.zzfs     // Catch:{ zzdk | IllegalStateException -> 0x004f }
            r4[r2] = r5     // Catch:{ zzdk | IllegalStateException -> 0x004f }
            org.json.JSONObject r2 = r6.zzfi     // Catch:{ zzdk | IllegalStateException -> 0x004f }
            r1.zza((com.google.android.gms.internal.cast.zzdm) r3, (int[]) r4, (org.json.JSONObject) r2)     // Catch:{ zzdk | IllegalStateException -> 0x004f }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r6.zzfa     // Catch:{ all -> 0x0072 }
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
            com.google.android.gms.common.api.Result r1 = r6.createFailedResult(r1)     // Catch:{ all -> 0x004d }
            com.google.android.gms.cast.RemoteMediaPlayer$MediaChannelResult r1 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r1     // Catch:{ all -> 0x004d }
            r6.setResult(r1)     // Catch:{ all -> 0x004d }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r6.zzfa     // Catch:{ all -> 0x0072 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r1 = r1.zzev     // Catch:{ all -> 0x0072 }
            goto L_0x0049
        L_0x0066:
            monitor-exit(r7)     // Catch:{ all -> 0x0072 }
            return
        L_0x0068:
            com.google.android.gms.cast.RemoteMediaPlayer r2 = r6.zzfa     // Catch:{ all -> 0x0072 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r2 = r2.zzev     // Catch:{ all -> 0x0072 }
            r2.zza(r0)     // Catch:{ all -> 0x0072 }
            throw r1     // Catch:{ all -> 0x0072 }
        L_0x0072:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x0072 }
            goto L_0x0076
        L_0x0075:
            throw r0
        L_0x0076:
            goto L_0x0075
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.zzbc.zza(com.google.android.gms.internal.cast.zzcn):void");
    }
}
