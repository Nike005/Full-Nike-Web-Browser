package com.mopub.mobileads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.mopub.common.MoPubBrowser;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Intents;
import com.mopub.exceptions.IntentNotResolvableException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0007H\u0016¨\u0006\n"}, mo45501d2 = {"com/mopub/mobileads/VastVideoConfigTwo$handleClick$urlHandler$1", "Lcom/mopub/common/UrlHandler$ResultActions;", "urlHandlingFailed", "", "url", "", "lastFailedUrlAction", "Lcom/mopub/common/UrlAction;", "urlHandlingSucceeded", "urlAction", "mopub-sdk-base_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
/* compiled from: VastVideoConfigTwo.kt */
public final class VastVideoConfigTwo$handleClick$urlHandler$1 implements UrlHandler.ResultActions {
    final /* synthetic */ Context $context;
    final /* synthetic */ Integer $requestCode;
    final /* synthetic */ VastVideoConfigTwo this$0;

    public void urlHandlingFailed(String str, UrlAction urlAction) {
        Intrinsics.checkParameterIsNotNull(str, "url");
        Intrinsics.checkParameterIsNotNull(urlAction, "lastFailedUrlAction");
    }

    VastVideoConfigTwo$handleClick$urlHandler$1(VastVideoConfigTwo vastVideoConfigTwo, Context context, Integer num) {
        this.this$0 = vastVideoConfigTwo;
        this.$context = context;
        this.$requestCode = num;
    }

    public void urlHandlingSucceeded(String str, UrlAction urlAction) {
        Intrinsics.checkParameterIsNotNull(str, "url");
        Intrinsics.checkParameterIsNotNull(urlAction, "urlAction");
        if (urlAction == UrlAction.OPEN_IN_APP_BROWSER) {
            Bundle bundle = new Bundle();
            bundle.putString(MoPubBrowser.DESTINATION_URL_KEY, str);
            bundle.putString(MoPubBrowser.DSP_CREATIVE_ID, this.this$0.getDspCreativeId());
            Class<MoPubBrowser> cls = MoPubBrowser.class;
            Intent startActivityIntent = Intents.getStartActivityIntent(this.$context, cls, bundle);
            try {
                if (!(this.$context instanceof Activity)) {
                    Intents.startActivity(this.$context, startActivityIntent);
                } else if (this.$requestCode != null) {
                    ((Activity) this.$context).startActivityForResult(startActivityIntent, this.$requestCode.intValue());
                } else {
                    throw new IllegalArgumentException("Activity context requires a requestCode".toString());
                }
            } catch (ActivityNotFoundException unused) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Activity " + cls.getName() + " not found. Did you declare " + "it in your AndroidManifest.xml?");
            } catch (IntentNotResolvableException unused2) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Activity " + cls.getName() + " not found. Did you declare " + "it in your AndroidManifest.xml?");
            }
        }
    }
}
