package com.mopub.mobileads;

import android.view.MotionEvent;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u0006H\n¢\u0006\u0002\b\u0007¨\u0006\b"}, mo45501d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/view/View;", "kotlin.jvm.PlatformType", "event", "Landroid/view/MotionEvent;", "onTouch", "com/mopub/mobileads/VastVideoViewControllerTwo$12$1"}, mo45502k = 3, mo45503mv = {1, 1, 16})
/* compiled from: VastVideoViewControllerTwo.kt */
final class VastVideoViewControllerTwo$$special$$inlined$also$lambda$3 implements View.OnTouchListener {
    final /* synthetic */ VastVideoViewControllerTwo this$0;

    VastVideoViewControllerTwo$$special$$inlined$also$lambda$3(VastVideoViewControllerTwo vastVideoViewControllerTwo) {
        this.this$0 = vastVideoViewControllerTwo;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        Intrinsics.checkExpressionValueIsNotNull(motionEvent, "event");
        if (motionEvent.getAction() != 1) {
            return true;
        }
        VastVideoViewControllerTwo vastVideoViewControllerTwo = this.this$0;
        vastVideoViewControllerTwo.setClosing(!vastVideoViewControllerTwo.isInClickExperiment || this.this$0.isComplete());
        this.this$0.handleExitTrackers();
        this.this$0.getBaseVideoViewControllerListener().onFinish();
        return true;
    }
}
