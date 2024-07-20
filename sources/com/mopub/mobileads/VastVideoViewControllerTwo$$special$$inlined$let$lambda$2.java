package com.mopub.mobileads;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b¸\u0006\t"}, mo45501d2 = {"com/mopub/mobileads/VastVideoViewControllerTwo$10$1$2", "Landroid/webkit/WebViewClient;", "shouldOverrideUrlLoading", "", "view", "Landroid/webkit/WebView;", "url", "", "mopub-sdk-base_release", "com/mopub/mobileads/VastVideoViewControllerTwo$$special$$inlined$also$lambda$2"}, mo45502k = 1, mo45503mv = {1, 1, 16})
/* compiled from: VastVideoViewControllerTwo.kt */
public final class VastVideoViewControllerTwo$$special$$inlined$let$lambda$2 extends WebViewClient {
    final /* synthetic */ VastIconConfigTwo $iconConfig$inlined;
    final /* synthetic */ VastVideoViewControllerTwo this$0;

    VastVideoViewControllerTwo$$special$$inlined$let$lambda$2(VastIconConfigTwo vastIconConfigTwo, VastVideoViewControllerTwo vastVideoViewControllerTwo) {
        this.$iconConfig$inlined = vastIconConfigTwo;
        this.this$0 = vastVideoViewControllerTwo;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        Intrinsics.checkParameterIsNotNull(webView, "view");
        Intrinsics.checkParameterIsNotNull(str, "url");
        VastIconConfigTwo vastIconConfig = this.this$0.getVastIconConfig();
        if (vastIconConfig == null) {
            return true;
        }
        Context context = this.this$0.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        vastIconConfig.handleClick(context, str, this.this$0.getVastVideoConfig().getDspCreativeId());
        return true;
    }
}
