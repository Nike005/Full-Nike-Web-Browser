package com.google.android.gms.internal.cast;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import androidx.collection.SimpleArrayMap;

public class zzei extends AnimatorListenerAdapter {
    private SimpleArrayMap<Animator, Boolean> zzyc = new SimpleArrayMap<>();

    public void onAnimationCancel(Animator animator) {
        this.zzyc.put(animator, true);
    }

    public void onAnimationStart(Animator animator) {
        this.zzyc.put(animator, false);
    }

    /* access modifiers changed from: protected */
    public final boolean zzb(Animator animator) {
        return this.zzyc.containsKey(animator) && this.zzyc.get(animator).booleanValue();
    }
}
