package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzcn;
import org.json.JSONObject;

final class zzbj extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzfa;
    private final /* synthetic */ GoogleApiClient zzfb;
    private final /* synthetic */ JSONObject zzfi;
    private final /* synthetic */ double zzfw;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbj(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, GoogleApiClient googleApiClient2, double d, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzfa = remoteMediaPlayer;
        this.zzfb = googleApiClient2;
        this.zzfw = d;
        this.zzfi = jSONObject;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzcn) anyClient);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002c, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        setResult((com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) createFailedResult(new com.google.android.gms.common.api.Status(2100)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1 = r6.zzfa.zzev;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0047, code lost:
        r6.zzfa.zzev.zza((com.google.android.gms.common.api.GoogleApiClient) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0050, code lost:
        throw r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.cast.zzcn r7) {
        /*
            r6 = this;
            com.google.android.gms.cast.RemoteMediaPlayer r7 = r6.zzfa
            java.lang.Object r7 = r7.lock
            monitor-enter(r7)
            com.google.android.gms.cast.RemoteMediaPlayer r0 = r6.zzfa     // Catch:{ all -> 0x0051 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r0 = r0.zzev     // Catch:{ all -> 0x0051 }
            com.google.android.gms.common.api.GoogleApiClient r1 = r6.zzfb     // Catch:{ all -> 0x0051 }
            r0.zza(r1)     // Catch:{ all -> 0x0051 }
            r0 = 0
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r6.zzfa     // Catch:{ zzdk | IllegalArgumentException | IllegalStateException -> 0x002e }
            com.google.android.gms.internal.cast.zzdh r1 = r1.zzeu     // Catch:{ zzdk | IllegalArgumentException | IllegalStateException -> 0x002e }
            com.google.android.gms.internal.cast.zzdm r2 = r6.zzgc     // Catch:{ zzdk | IllegalArgumentException | IllegalStateException -> 0x002e }
            double r3 = r6.zzfw     // Catch:{ zzdk | IllegalArgumentException | IllegalStateException -> 0x002e }
            org.json.JSONObject r5 = r6.zzfi     // Catch:{ zzdk | IllegalArgumentException | IllegalStateException -> 0x002e }
            r1.zza((com.google.android.gms.internal.cast.zzdm) r2, (double) r3, (org.json.JSONObject) r5)     // Catch:{ zzdk | IllegalArgumentException | IllegalStateException -> 0x002e }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r6.zzfa     // Catch:{ all -> 0x0051 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r1 = r1.zzev     // Catch:{ all -> 0x0051 }
        L_0x0028:
            r1.zza(r0)     // Catch:{ all -> 0x0051 }
            goto L_0x0045
        L_0x002c:
            r1 = move-exception
            goto L_0x0047
        L_0x002e:
            com.google.android.gms.common.api.Status r1 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x002c }
            r2 = 2100(0x834, float:2.943E-42)
            r1.<init>(r2)     // Catch:{ all -> 0x002c }
            com.google.android.gms.common.api.Result r1 = r6.createFailedResult(r1)     // Catch:{ all -> 0x002c }
            com.google.android.gms.cast.RemoteMediaPlayer$MediaChannelResult r1 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r1     // Catch:{ all -> 0x002c }
            r6.setResult(r1)     // Catch:{ all -> 0x002c }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r6.zzfa     // Catch:{ all -> 0x0051 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r1 = r1.zzev     // Catch:{ all -> 0x0051 }
            goto L_0x0028
        L_0x0045:
            monitor-exit(r7)     // Catch:{ all -> 0x0051 }
            return
        L_0x0047:
            com.google.android.gms.cast.RemoteMediaPlayer r2 = r6.zzfa     // Catch:{ all -> 0x0051 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r2 = r2.zzev     // Catch:{ all -> 0x0051 }
            r2.zza(r0)     // Catch:{ all -> 0x0051 }
            throw r1     // Catch:{ all -> 0x0051 }
        L_0x0051:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x0051 }
            goto L_0x0055
        L_0x0054:
            throw r0
        L_0x0055:
            goto L_0x0054
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.zzbj.zza(com.google.android.gms.internal.cast.zzcn):void");
    }
}
