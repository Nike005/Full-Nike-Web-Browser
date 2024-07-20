package com.mopub.mobileads;

import android.content.Context;
import android.os.Bundle;
import com.mopub.common.MoPubBrowser;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Intents;
import com.mopub.exceptions.IntentNotResolvableException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0007H\u0016¨\u0006\n¸\u0006\u0000"}, mo45501d2 = {"com/mopub/mobileads/VastIconConfigTwo$handleClick$2$1", "Lcom/mopub/common/UrlHandler$ResultActions;", "urlHandlingFailed", "", "url", "", "lastFailedUrlAction", "Lcom/mopub/common/UrlAction;", "urlHandlingSucceeded", "urlAction", "mopub-sdk-base_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
/* compiled from: VastIconConfigTwo.kt */
public final class VastIconConfigTwo$handleClick$$inlined$let$lambda$1 implements UrlHandler.ResultActions {
    final /* synthetic */ Context $context$inlined;
    final /* synthetic */ String $dspCreativeId$inlined;

    public void urlHandlingFailed(String str, UrlAction urlAction) {
        Intrinsics.checkParameterIsNotNull(str, "url");
        Intrinsics.checkParameterIsNotNull(urlAction, "lastFailedUrlAction");
    }

    VastIconConfigTwo$handleClick$$inlined$let$lambda$1(String str, Context context) {
        this.$dspCreativeId$inlined = str;
        this.$context$inlined = context;
    }

    public void urlHandlingSucceeded(String str, UrlAction urlAction) {
        Intrinsics.checkParameterIsNotNull(str, "url");
        Intrinsics.checkParameterIsNotNull(urlAction, "urlAction");
        if (urlAction == UrlAction.OPEN_IN_APP_BROWSER) {
            Bundle bundle = new Bundle();
            bundle.putString(MoPubBrowser.DESTINATION_URL_KEY, str);
            CharSequence charSequence = this.$dspCreativeId$inlined;
            if (!(charSequence == null || charSequence.length() == 0)) {
                bundle.putString(MoPubBrowser.DSP_CREATIVE_ID, this.$dspCreativeId$inlined);
            }
            try {
                Intents.startActivity(this.$context$inlined, Intents.getStartActivityIntent(this.$context$inlined, MoPubBrowser.class, bundle));
            } catch (IntentNotResolvableException e) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, e.getMessage());
            }
        }
    }
}
