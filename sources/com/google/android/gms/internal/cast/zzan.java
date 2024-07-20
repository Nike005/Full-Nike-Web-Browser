package com.google.android.gms.internal.cast;

import android.content.Context;
import android.view.View;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.cast.framework.C0069R;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import java.util.Iterator;
import java.util.List;

public final class zzan extends UIController {
    private final View view;
    private final String zzlz;
    private final String zzpv;

    public zzan(View view2, Context context) {
        this.view = view2;
        this.zzlz = context.getString(C0069R.string.cast_closed_captions);
        this.zzpv = context.getString(C0069R.string.cast_closed_captions_unavailable);
        this.view.setEnabled(false);
    }

    private final void zzby() {
        View view2;
        String str;
        boolean z;
        List<MediaTrack> mediaTracks;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession()) {
            MediaInfo mediaInfo = remoteMediaClient.getMediaInfo();
            if (mediaInfo != null && (mediaTracks = mediaInfo.getMediaTracks()) != null && !mediaTracks.isEmpty()) {
                Iterator<MediaTrack> it = mediaTracks.iterator();
                int i = 0;
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    MediaTrack next = it.next();
                    if (next.getType() == 2) {
                        i++;
                        if (i > 1) {
                            break;
                        }
                    } else if (next.getType() == 1) {
                        break;
                    }
                }
                z = true;
                if (z && !remoteMediaClient.isPlayingAd()) {
                    this.view.setEnabled(true);
                    view2 = this.view;
                    str = this.zzlz;
                    view2.setContentDescription(str);
                }
            }
            z = false;
            this.view.setEnabled(true);
            view2 = this.view;
            str = this.zzlz;
            view2.setContentDescription(str);
        }
        this.view.setEnabled(false);
        view2 = this.view;
        str = this.zzpv;
        view2.setContentDescription(str);
    }

    public final void onMediaStatusUpdated() {
        zzby();
    }

    public final void onSendingRemoteMediaRequest() {
        this.view.setEnabled(false);
    }

    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        this.view.setEnabled(true);
        zzby();
    }

    public final void onSessionEnded() {
        this.view.setEnabled(false);
        super.onSessionEnded();
    }
}
