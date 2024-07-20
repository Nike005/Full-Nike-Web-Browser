package com.google.android.gms.internal.cast;

import android.view.View;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzbk extends UIController {
    private final View view;
    private final int zzqu;

    public zzbk(View view2, int i) {
        this.view = view2;
        this.zzqu = i;
    }

    private final void zzby() {
        View view2;
        int i;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            view2 = this.view;
            i = this.zzqu;
        } else {
            view2 = this.view;
            i = 0;
        }
        view2.setVisibility(i);
    }

    public final void onMediaStatusUpdated() {
        zzby();
    }

    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        zzby();
    }

    public final void onSessionEnded() {
        this.view.setVisibility(this.zzqu);
        super.onSessionEnded();
    }
}
