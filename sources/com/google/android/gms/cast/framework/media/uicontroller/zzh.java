package com.google.android.gms.cast.framework.media.uicontroller;

import android.view.View;

final class zzh implements View.OnClickListener {
    private final /* synthetic */ UIMediaController zzpt;

    zzh(UIMediaController uIMediaController) {
        this.zzpt = uIMediaController;
    }

    public final void onClick(View view) {
        this.zzpt.onClosedCaptionClicked(view);
    }
}
