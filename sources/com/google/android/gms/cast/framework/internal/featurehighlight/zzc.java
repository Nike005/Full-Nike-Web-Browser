package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

final class zzc extends GestureDetector.SimpleOnGestureListener {
    private final /* synthetic */ View zzjr;
    private final /* synthetic */ boolean zzjs = true;
    private final /* synthetic */ zzh zzjt;

    zzc(zza zza, View view, boolean z, zzh zzh) {
        this.zzjr = view;
        this.zzjt = zzh;
    }

    public final boolean onSingleTapUp(MotionEvent motionEvent) {
        if (this.zzjr.getParent() != null) {
            this.zzjr.performClick();
        }
        if (!this.zzjs) {
            return true;
        }
        this.zzjt.zzan();
        return true;
    }
}
