package com.google.android.gms.cast.framework.media.uicontroller;

import android.widget.SeekBar;

final class zzf implements SeekBar.OnSeekBarChangeListener {
    private final /* synthetic */ UIMediaController zzpt;

    zzf(UIMediaController uIMediaController) {
        this.zzpt = uIMediaController;
    }

    public final void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        this.zzpt.onSeekBarProgressChanged(seekBar, i, z);
    }

    public final void onStartTrackingTouch(SeekBar seekBar) {
        this.zzpt.onSeekBarStartTrackingTouch(seekBar);
    }

    public final void onStopTrackingTouch(SeekBar seekBar) {
        this.zzpt.onSeekBarStopTrackingTouch(seekBar);
    }
}
