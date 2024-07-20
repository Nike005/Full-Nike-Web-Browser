package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzcn;
import org.json.JSONObject;

final class zzbf extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzfa;
    private final /* synthetic */ GoogleApiClient zzfb;
    private final /* synthetic */ JSONObject zzfi;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbf(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, GoogleApiClient googleApiClient2, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzfa = remoteMediaPlayer;
        this.zzfb = googleApiClient2;
        this.zzfi = jSONObject;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzcn) anyClient);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002a, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        setResult((com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) createFailedResult(new com.google.android.gms.common.api.Status(2100)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1 = r4.zzfa.zzev;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0045, code lost:
        r4.zzfa.zzev.zza((com.google.android.gms.common.api.GoogleApiClient) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004e, code lost:
        throw r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.cast.zzcn r5) {
        /*
            r4 = this;
            com.google.android.gms.cast.RemoteMediaPlayer r5 = r4.zzfa
            java.lang.Object r5 = r5.lock
            monitor-enter(r5)
            com.google.android.gms.cast.RemoteMediaPlayer r0 = r4.zzfa     // Catch:{ all -> 0x004f }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r0 = r0.zzev     // Catch:{ all -> 0x004f }
            com.google.android.gms.common.api.GoogleApiClient r1 = r4.zzfb     // Catch:{ all -> 0x004f }
            r0.zza(r1)     // Catch:{ all -> 0x004f }
            r0 = 0
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r4.zzfa     // Catch:{ zzdk | IllegalStateException -> 0x002c }
            com.google.android.gms.internal.cast.zzdh r1 = r1.zzeu     // Catch:{ zzdk | IllegalStateException -> 0x002c }
            com.google.android.gms.internal.cast.zzdm r2 = r4.zzgc     // Catch:{ zzdk | IllegalStateException -> 0x002c }
            org.json.JSONObject r3 = r4.zzfi     // Catch:{ zzdk | IllegalStateException -> 0x002c }
            r1.zza((com.google.android.gms.internal.cast.zzdm) r2, (org.json.JSONObject) r3)     // Catch:{ zzdk | IllegalStateException -> 0x002c }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r4.zzfa     // Catch:{ all -> 0x004f }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r1 = r1.zzev     // Catch:{ all -> 0x004f }
        L_0x0026:
            r1.zza(r0)     // Catch:{ all -> 0x004f }
            goto L_0x0043
        L_0x002a:
            r1 = move-exception
            goto L_0x0045
        L_0x002c:
            com.google.android.gms.common.api.Status r1 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x002a }
            r2 = 2100(0x834, float:2.943E-42)
            r1.<init>(r2)     // Catch:{ all -> 0x002a }
            com.google.android.gms.common.api.Result r1 = r4.createFailedResult(r1)     // Catch:{ all -> 0x002a }
            com.google.android.gms.cast.RemoteMediaPlayer$MediaChannelResult r1 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r1     // Catch:{ all -> 0x002a }
            r4.setResult(r1)     // Catch:{ all -> 0x002a }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r4.zzfa     // Catch:{ all -> 0x004f }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r1 = r1.zzev     // Catch:{ all -> 0x004f }
            goto L_0x0026
        L_0x0043:
            monitor-exit(r5)     // Catch:{ all -> 0x004f }
            return
        L_0x0045:
            com.google.android.gms.cast.RemoteMediaPlayer r2 = r4.zzfa     // Catch:{ all -> 0x004f }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r2 = r2.zzev     // Catch:{ all -> 0x004f }
            r2.zza(r0)     // Catch:{ all -> 0x004f }
            throw r1     // Catch:{ all -> 0x004f }
        L_0x004f:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x004f }
            goto L_0x0053
        L_0x0052:
            throw r0
        L_0x0053:
            goto L_0x0052
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.zzbf.zza(com.google.android.gms.internal.cast.zzcn):void");
    }
}
