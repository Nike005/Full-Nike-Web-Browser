package com.mopub.mobileads;

import android.content.Context;
import com.mopub.mobileads.VastWebView;
import com.mopub.network.TrackingRequest;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0004"}, mo45501d2 = {"<anonymous>", "", "onVastWebViewClick", "com/mopub/mobileads/VastVideoViewControllerTwo$10$1$1", "com/mopub/mobileads/VastVideoViewControllerTwo$$special$$inlined$also$lambda$1"}, mo45502k = 3, mo45503mv = {1, 1, 16})
/* compiled from: VastVideoViewControllerTwo.kt */
final class VastVideoViewControllerTwo$$special$$inlined$let$lambda$1 implements VastWebView.VastWebViewClickListener {
    final /* synthetic */ VastIconConfigTwo $iconConfig$inlined;
    final /* synthetic */ VastVideoViewControllerTwo this$0;

    VastVideoViewControllerTwo$$special$$inlined$let$lambda$1(VastIconConfigTwo vastIconConfigTwo, VastVideoViewControllerTwo vastVideoViewControllerTwo) {
        this.$iconConfig$inlined = vastIconConfigTwo;
        this.this$0 = vastVideoViewControllerTwo;
    }

    public final void onVastWebViewClick() {
        TrackingRequest.makeVastTrackingTwoHttpRequest(this.$iconConfig$inlined.getClickTrackingUris(), (VastErrorCode) null, Integer.valueOf(this.this$0.getCurrentPosition()), this.this$0.getNetworkMediaFileUrl(), this.this$0.getContext());
        VastIconConfigTwo vastIconConfig = this.this$0.getVastIconConfig();
        if (vastIconConfig != null) {
            Context context = this.this$0.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            vastIconConfig.handleClick(context, (String) null, this.this$0.getVastVideoConfig().getDspCreativeId());
        }
    }
}
