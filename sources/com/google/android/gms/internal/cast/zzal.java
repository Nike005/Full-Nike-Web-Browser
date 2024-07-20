package com.google.android.gms.internal.cast;

import android.content.Intent;
import android.support.p064v4.media.session.MediaSessionCompat;
import android.view.KeyEvent;

final class zzal extends MediaSessionCompat.Callback {
    private final /* synthetic */ zzai zzpm;

    zzal(zzai zzai) {
        this.zzpm = zzai;
    }

    public final boolean onMediaButtonEvent(Intent intent) {
        KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
        if (keyEvent == null) {
            return true;
        }
        if (keyEvent.getKeyCode() != 127 && keyEvent.getKeyCode() != 126) {
            return true;
        }
        this.zzpm.zzhp.togglePlayback();
        return true;
    }

    public final void onPause() {
        this.zzpm.zzhp.togglePlayback();
    }

    public final void onPlay() {
        this.zzpm.zzhp.togglePlayback();
    }

    public final void onStop() {
        if (this.zzpm.zzhp.isLiveStream()) {
            this.zzpm.zzhp.togglePlayback();
        }
    }
}
