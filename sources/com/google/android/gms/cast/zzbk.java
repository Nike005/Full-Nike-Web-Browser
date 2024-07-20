package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzcn;
import org.json.JSONObject;

final class zzbk extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzfa;
    private final /* synthetic */ GoogleApiClient zzfb;
    private final /* synthetic */ JSONObject zzfi;
    private final /* synthetic */ boolean zzfx;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbk(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, GoogleApiClient googleApiClient2, boolean z, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzfa = remoteMediaPlayer;
        this.zzfb = googleApiClient2;
        this.zzfx = z;
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
        r1 = r5.zzfa.zzev;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0047, code lost:
        r5.zzfa.zzev.zza((com.google.android.gms.common.api.GoogleApiClient) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0050, code lost:
        throw r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.cast.zzcn r6) {
        /*
            r5 = this;
            com.google.android.gms.cast.RemoteMediaPlayer r6 = r5.zzfa
            java.lang.Object r6 = r6.lock
            monitor-enter(r6)
            com.google.android.gms.cast.RemoteMediaPlayer r0 = r5.zzfa     // Catch:{ all -> 0x0051 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r0 = r0.zzev     // Catch:{ all -> 0x0051 }
            com.google.android.gms.common.api.GoogleApiClient r1 = r5.zzfb     // Catch:{ all -> 0x0051 }
            r0.zza(r1)     // Catch:{ all -> 0x0051 }
            r0 = 0
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r5.zzfa     // Catch:{ zzdk | IllegalStateException -> 0x002e }
            com.google.android.gms.internal.cast.zzdh r1 = r1.zzeu     // Catch:{ zzdk | IllegalStateException -> 0x002e }
            com.google.android.gms.internal.cast.zzdm r2 = r5.zzgc     // Catch:{ zzdk | IllegalStateException -> 0x002e }
            boolean r3 = r5.zzfx     // Catch:{ zzdk | IllegalStateException -> 0x002e }
            org.json.JSONObject r4 = r5.zzfi     // Catch:{ zzdk | IllegalStateException -> 0x002e }
            r1.zza((com.google.android.gms.internal.cast.zzdm) r2, (boolean) r3, (org.json.JSONObject) r4)     // Catch:{ zzdk | IllegalStateException -> 0x002e }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r5.zzfa     // Catch:{ all -> 0x0051 }
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
            com.google.android.gms.common.api.Result r1 = r5.createFailedResult(r1)     // Catch:{ all -> 0x002c }
            com.google.android.gms.cast.RemoteMediaPlayer$MediaChannelResult r1 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r1     // Catch:{ all -> 0x002c }
            r5.setResult(r1)     // Catch:{ all -> 0x002c }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r5.zzfa     // Catch:{ all -> 0x0051 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r1 = r1.zzev     // Catch:{ all -> 0x0051 }
            goto L_0x0028
        L_0x0045:
            monitor-exit(r6)     // Catch:{ all -> 0x0051 }
            return
        L_0x0047:
            com.google.android.gms.cast.RemoteMediaPlayer r2 = r5.zzfa     // Catch:{ all -> 0x0051 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r2 = r2.zzev     // Catch:{ all -> 0x0051 }
            r2.zza(r0)     // Catch:{ all -> 0x0051 }
            throw r1     // Catch:{ all -> 0x0051 }
        L_0x0051:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0051 }
            goto L_0x0055
        L_0x0054:
            throw r0
        L_0x0055:
            goto L_0x0054
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.zzbk.zza(com.google.android.gms.internal.cast.zzcn):void");
    }
}
