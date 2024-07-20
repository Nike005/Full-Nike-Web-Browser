package com.google.android.gms.internal.cast;

import android.view.View;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzav extends UIController {
    private final View view;

    public zzav(View view2) {
        this.view = view2;
    }

    private final void zzby() {
        View view2;
        int i;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || remoteMediaClient.isBuffering()) {
            view2 = this.view;
            i = 0;
        } else {
            view2 = this.view;
            i = 8;
        }
        view2.setVisibility(i);
    }

    public final void onMediaStatusUpdated() {
        zzby();
    }

    public final void onSendingRemoteMediaRequest() {
        this.view.setVisibility(0);
    }

    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        zzby();
    }

    public final void onSessionEnded() {
        this.view.setVisibility(8);
        super.onSessionEnded();
    }
}
