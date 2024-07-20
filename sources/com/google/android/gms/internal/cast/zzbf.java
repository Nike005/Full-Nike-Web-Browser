package com.google.android.gms.internal.cast;

import android.view.View;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzbf extends UIController {
    private final View view;
    private final int zzqu;

    public zzbf(View view2, int i) {
        this.view = view2;
        this.zzqu = i;
        view2.setEnabled(false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0019, code lost:
        r2 = r2.getIndexById(r2.getCurrentItemId());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzcb() {
        /*
            r5 = this;
            com.google.android.gms.cast.framework.media.RemoteMediaClient r0 = r5.getRemoteMediaClient()
            r1 = 0
            if (r0 == 0) goto L_0x004d
            boolean r2 = r0.hasMediaSession()
            if (r2 != 0) goto L_0x000e
            goto L_0x004d
        L_0x000e:
            com.google.android.gms.cast.MediaStatus r2 = r0.getMediaStatus()
            int r3 = r2.getQueueRepeatMode()
            r4 = 1
            if (r3 != 0) goto L_0x002c
            int r3 = r2.getCurrentItemId()
            java.lang.Integer r2 = r2.getIndexById(r3)
            if (r2 == 0) goto L_0x002a
            int r2 = r2.intValue()
            if (r2 <= 0) goto L_0x002a
            goto L_0x002c
        L_0x002a:
            r2 = 0
            goto L_0x002d
        L_0x002c:
            r2 = 1
        L_0x002d:
            if (r2 == 0) goto L_0x0040
            boolean r0 = r0.isPlayingAd()
            if (r0 != 0) goto L_0x0040
            android.view.View r0 = r5.view
            r0.setVisibility(r1)
            android.view.View r0 = r5.view
            r0.setEnabled(r4)
            return
        L_0x0040:
            android.view.View r0 = r5.view
            int r2 = r5.zzqu
            r0.setVisibility(r2)
            android.view.View r0 = r5.view
            r0.setEnabled(r1)
            return
        L_0x004d:
            android.view.View r0 = r5.view
            r0.setEnabled(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.zzbf.zzcb():void");
    }

    public final void onMediaStatusUpdated() {
        zzcb();
    }

    public final void onSendingRemoteMediaRequest() {
        this.view.setEnabled(false);
    }

    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        zzcb();
    }

    public final void onSessionEnded() {
        this.view.setEnabled(false);
        super.onSessionEnded();
    }
}
