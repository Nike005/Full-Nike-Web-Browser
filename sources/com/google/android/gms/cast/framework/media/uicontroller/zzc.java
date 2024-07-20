package com.google.android.gms.cast.framework.media.uicontroller;

import android.view.View;

final class zzc implements View.OnClickListener {
    private final /* synthetic */ UIMediaController zzpt;

    zzc(UIMediaController uIMediaController) {
        this.zzpt = uIMediaController;
    }

    public final void onClick(View view) {
        this.zzpt.onSkipPrevClicked(view);
    }
}
