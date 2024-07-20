package com.google.android.gms.cast.framework.media.uicontroller;

import android.view.View;

final class zzg implements View.OnClickListener {
    private final /* synthetic */ UIMediaController zzpt;

    zzg(UIMediaController uIMediaController) {
        this.zzpt = uIMediaController;
    }

    public final void onClick(View view) {
        this.zzpt.onLaunchExpandedControllerClicked(view);
    }
}
