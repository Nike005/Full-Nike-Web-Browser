package com.google.android.gms.internal.cast;

import android.content.Context;
import android.widget.ImageView;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.framework.C0069R;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzay extends UIController {
    private Cast.Listener zzaj;
    private final Context zzgs;
    private final ImageView zzpw;
    private final String zzqh;
    private final String zzqi = this.zzgs.getString(C0069R.string.cast_unmute);

    public zzay(ImageView imageView, Context context) {
        this.zzpw = imageView;
        Context applicationContext = context.getApplicationContext();
        this.zzgs = applicationContext;
        this.zzqh = applicationContext.getString(C0069R.string.cast_mute);
        this.zzpw.setEnabled(false);
        this.zzaj = null;
    }

    private final void zzh(boolean z) {
        this.zzpw.setSelected(z);
        this.zzpw.setContentDescription(z ? this.zzqh : this.zzqi);
    }

    public final void onMediaStatusUpdated() {
        zzby();
    }

    public final void onSendingRemoteMediaRequest() {
        this.zzpw.setEnabled(false);
    }

    public final void onSessionConnected(CastSession castSession) {
        if (this.zzaj == null) {
            this.zzaj = new zzaz(this);
        }
        super.onSessionConnected(castSession);
        castSession.addCastListener(this.zzaj);
        zzby();
    }

    public final void onSessionEnded() {
        Cast.Listener listener;
        this.zzpw.setEnabled(false);
        CastSession currentCastSession = CastContext.getSharedInstance(this.zzgs).getSessionManager().getCurrentCastSession();
        if (!(currentCastSession == null || (listener = this.zzaj) == null)) {
            currentCastSession.removeCastListener(listener);
        }
        super.onSessionEnded();
    }

    /* access modifiers changed from: protected */
    public final void zzby() {
        CastSession currentCastSession = CastContext.getSharedInstance(this.zzgs).getSessionManager().getCurrentCastSession();
        if (currentCastSession == null || !currentCastSession.isConnected()) {
            this.zzpw.setEnabled(false);
            return;
        }
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            this.zzpw.setEnabled(false);
        } else {
            this.zzpw.setEnabled(true);
        }
        if (currentCastSession.isMute()) {
            zzh(true);
        } else {
            zzh(false);
        }
    }
}
