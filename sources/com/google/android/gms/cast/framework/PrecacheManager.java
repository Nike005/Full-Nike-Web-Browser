package com.google.android.gms.cast.framework;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.zzbp;
import com.google.android.gms.internal.cast.zzch;
import com.google.android.gms.internal.cast.zzdg;
import java.util.List;

public class PrecacheManager {
    private final zzdg zzbd = new zzdg("PrecacheManager");
    private final SessionManager zzgu;
    private final CastOptions zzgy;
    private final zzch zzid;

    public PrecacheManager(CastOptions castOptions, SessionManager sessionManager, zzch zzch) {
        this.zzgy = castOptions;
        this.zzgu = sessionManager;
        this.zzid = zzch;
    }

    public void precache(String str) {
        Session currentSession = this.zzgu.getCurrentSession();
        if (str == null) {
            throw new IllegalArgumentException("No precache data found to be precached");
        } else if (currentSession == null) {
            this.zzid.zza(new String[]{this.zzgy.getReceiverApplicationId()}, str, (List<zzbp>) null);
        } else if (currentSession instanceof CastSession) {
            RemoteMediaClient remoteMediaClient = ((CastSession) currentSession).getRemoteMediaClient();
            if (remoteMediaClient != null) {
                remoteMediaClient.zza(str, (List<zzbp>) null);
            } else {
                this.zzbd.mo6871e("Failed to get RemoteMediaClient from current cast session.", new Object[0]);
            }
        } else {
            this.zzbd.mo6871e("Current session is not a CastSession. Precache is not supported.", new Object[0]);
        }
    }
}
