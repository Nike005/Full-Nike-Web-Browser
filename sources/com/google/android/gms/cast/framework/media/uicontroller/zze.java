package com.google.android.gms.cast.framework.media.uicontroller;

import android.view.View;

final class zze implements View.OnClickListener {
    private final /* synthetic */ UIMediaController zzpt;
    private final /* synthetic */ long zzpu;

    zze(UIMediaController uIMediaController, long j) {
        this.zzpt = uIMediaController;
        this.zzpu = j;
    }

    public final void onClick(View view) {
        this.zzpt.onRewindClicked(view, this.zzpu);
    }
}
