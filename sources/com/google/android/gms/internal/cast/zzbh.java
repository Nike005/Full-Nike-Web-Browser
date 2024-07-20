package com.google.android.gms.internal.cast;

import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzbh extends UIController implements RemoteMediaClient.ProgressListener {
    private final TextView zzqw;
    private final String zzqx;
    private final View zzqy;

    public zzbh(TextView textView, String str, View view) {
        this.zzqw = textView;
        this.zzqx = str;
        this.zzqy = view;
    }

    private final void zza(long j, boolean z) {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            this.zzqw.setVisibility(0);
            this.zzqw.setText(this.zzqx);
            View view = this.zzqy;
            if (view != null) {
                view.setVisibility(4);
            }
        } else if (!remoteMediaClient.isLiveStream()) {
            if (z) {
                j = remoteMediaClient.getStreamDuration();
            }
            this.zzqw.setVisibility(0);
            this.zzqw.setText(DateUtils.formatElapsedTime(j / 1000));
            View view2 = this.zzqy;
            if (view2 != null) {
                view2.setVisibility(4);
            }
        } else {
            this.zzqw.setText(this.zzqx);
            if (this.zzqy != null) {
                this.zzqw.setVisibility(4);
                this.zzqy.setVisibility(0);
            }
        }
    }

    public final void onMediaStatusUpdated() {
        zza(-1, true);
    }

    public final void onProgressUpdated(long j, long j2) {
        zza(j2, false);
    }

    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        if (getRemoteMediaClient() != null) {
            getRemoteMediaClient().addProgressListener(this, 1000);
        }
        zza(-1, true);
    }

    public final void onSessionEnded() {
        this.zzqw.setText(this.zzqx);
        if (getRemoteMediaClient() != null) {
            getRemoteMediaClient().removeProgressListener(this);
        }
        super.onSessionEnded();
    }
}
