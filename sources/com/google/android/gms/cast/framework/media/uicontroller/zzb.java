package com.google.android.gms.cast.framework.media.uicontroller;

import android.view.View;

final class zzb implements View.OnClickListener {
    private final /* synthetic */ UIMediaController zzpt;

    zzb(UIMediaController uIMediaController) {
        this.zzpt = uIMediaController;
    }

    public final void onClick(View view) {
        this.zzpt.onSkipNextClicked(view);
    }
}
