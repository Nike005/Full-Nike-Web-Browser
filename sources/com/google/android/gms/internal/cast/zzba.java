package com.google.android.gms.internal.cast;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.google.android.gms.cast.framework.C0069R;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzba extends UIController {
    private final ImageView zzpw;
    private final View zzqk;
    private final boolean zzql;
    private final Drawable zzqm;
    private final String zzqn;
    private final Drawable zzqo;
    private final String zzqp;
    private final Drawable zzqq;
    private final String zzqr;

    public zzba(ImageView imageView, Context context, Drawable drawable, Drawable drawable2, Drawable drawable3, View view, boolean z) {
        this.zzpw = imageView;
        this.zzqm = drawable;
        this.zzqo = drawable2;
        this.zzqq = drawable3 != null ? drawable3 : drawable2;
        this.zzqn = context.getString(C0069R.string.cast_play);
        this.zzqp = context.getString(C0069R.string.cast_pause);
        this.zzqr = context.getString(C0069R.string.cast_stop);
        this.zzqk = view;
        this.zzql = z;
        this.zzpw.setEnabled(false);
    }

    private final void zza(Drawable drawable, String str) {
        this.zzpw.setImageDrawable(drawable);
        this.zzpw.setContentDescription(str);
        this.zzpw.setVisibility(0);
        this.zzpw.setEnabled(true);
        View view = this.zzqk;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    private final void zzby() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            this.zzpw.setEnabled(false);
        } else if (remoteMediaClient.isPaused()) {
            zza(this.zzqm, this.zzqn);
        } else if (remoteMediaClient.isPlaying()) {
            if (remoteMediaClient.isLiveStream()) {
                zza(this.zzqq, this.zzqr);
            } else {
                zza(this.zzqo, this.zzqp);
            }
        } else if (remoteMediaClient.isBuffering()) {
            zzi(false);
        } else if (remoteMediaClient.isLoadingNextItem()) {
            zzi(true);
        }
    }

    private final void zzi(boolean z) {
        View view = this.zzqk;
        int i = 0;
        if (view != null) {
            view.setVisibility(0);
        }
        ImageView imageView = this.zzpw;
        if (this.zzql) {
            i = 4;
        }
        imageView.setVisibility(i);
        this.zzpw.setEnabled(!z);
    }

    public final void onMediaStatusUpdated() {
        zzby();
    }

    public final void onSendingRemoteMediaRequest() {
        zzi(true);
    }

    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        zzby();
    }

    public final void onSessionEnded() {
        this.zzpw.setEnabled(false);
        super.onSessionEnded();
    }
}
