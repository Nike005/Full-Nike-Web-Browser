package com.mopub.mobileads;

import android.os.Handler;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, mo45501d2 = {"Lcom/mopub/mobileads/VastVideoViewCountdownRunnableTwo;", "Lcom/mopub/mobileads/RepeatingHandlerRunnable;", "videoViewController", "Lcom/mopub/mobileads/VastVideoViewControllerTwo;", "handler", "Landroid/os/Handler;", "(Lcom/mopub/mobileads/VastVideoViewControllerTwo;Landroid/os/Handler;)V", "doWork", "", "mopub-sdk-base_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
/* compiled from: VastVideoViewCountdownRunnableTwo.kt */
public final class VastVideoViewCountdownRunnableTwo extends RepeatingHandlerRunnable {
    private final VastVideoViewControllerTwo videoViewController;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VastVideoViewCountdownRunnableTwo(VastVideoViewControllerTwo vastVideoViewControllerTwo, Handler handler) {
        super(handler);
        Intrinsics.checkParameterIsNotNull(vastVideoViewControllerTwo, "videoViewController");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        this.videoViewController = vastVideoViewControllerTwo;
    }

    public void doWork() {
        VastVideoViewControllerTwo.updateCountdown$mopub_sdk_base_release$default(this.videoViewController, false, 1, (Object) null);
    }
}
