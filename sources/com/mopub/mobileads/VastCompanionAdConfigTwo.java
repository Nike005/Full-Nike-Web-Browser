package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler;
import com.mopub.network.TrackingRequest;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 $2\u00020\u0001:\u0001$BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\rJ\u0014\u0010\u0018\u001a\u00020\u00192\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001aJ\u0014\u0010\u001b\u001a\u00020\u00192\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001aJ*\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00032\b\u0010 \u001a\u0004\u0018\u00010\b2\b\u0010!\u001a\u0004\u0018\u00010\bJ\u0016\u0010\"\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020\u0003R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014¨\u0006%"}, mo45501d2 = {"Lcom/mopub/mobileads/VastCompanionAdConfigTwo;", "Ljava/io/Serializable;", "width", "", "height", "vastResource", "Lcom/mopub/mobileads/VastResourceTwo;", "clickThroughUrl", "", "clickTrackers", "", "Lcom/mopub/mobileads/VastTrackerTwo;", "creativeViewTrackers", "(IILcom/mopub/mobileads/VastResourceTwo;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getClickThroughUrl", "()Ljava/lang/String;", "getClickTrackers", "()Ljava/util/List;", "getCreativeViewTrackers", "getHeight", "()I", "getVastResource", "()Lcom/mopub/mobileads/VastResourceTwo;", "getWidth", "addClickTrackers", "", "", "addCreativeViewTrackers", "handleClick", "context", "Landroid/content/Context;", "requestCode", "webViewClickThroughUrl", "dspCreativeId", "handleImpression", "contentPlayHead", "Companion", "mopub-sdk-base_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
/* compiled from: VastCompanionAdConfigTwo.kt */
public final class VastCompanionAdConfigTwo implements Serializable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final long serialVersionUID = 3;
    @SerializedName("clickthrough_url")
    @Expose
    private final String clickThroughUrl;
    @SerializedName("click_trackers")
    @Expose
    private final List<VastTrackerTwo> clickTrackers;
    @SerializedName("impression_trackers")
    @Expose
    private final List<VastTrackerTwo> creativeViewTrackers;
    @SerializedName("height")
    @Expose
    private final int height;
    @SerializedName("resource")
    @Expose
    private final VastResourceTwo vastResource;
    @SerializedName("width")
    @Expose
    private final int width;

    public VastCompanionAdConfigTwo(int i, int i2, VastResourceTwo vastResourceTwo, String str, List<VastTrackerTwo> list, List<VastTrackerTwo> list2) {
        Intrinsics.checkParameterIsNotNull(vastResourceTwo, "vastResource");
        Intrinsics.checkParameterIsNotNull(list, "clickTrackers");
        Intrinsics.checkParameterIsNotNull(list2, "creativeViewTrackers");
        this.width = i;
        this.height = i2;
        this.vastResource = vastResourceTwo;
        this.clickThroughUrl = str;
        this.clickTrackers = list;
        this.creativeViewTrackers = list2;
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    public final VastResourceTwo getVastResource() {
        return this.vastResource;
    }

    public final String getClickThroughUrl() {
        return this.clickThroughUrl;
    }

    public final List<VastTrackerTwo> getClickTrackers() {
        return this.clickTrackers;
    }

    public final List<VastTrackerTwo> getCreativeViewTrackers() {
        return this.creativeViewTrackers;
    }

    @Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo45501d2 = {"Lcom/mopub/mobileads/VastCompanionAdConfigTwo$Companion;", "", "()V", "serialVersionUID", "", "mopub-sdk-base_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
    /* compiled from: VastCompanionAdConfigTwo.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final void addClickTrackers(Collection<? extends VastTrackerTwo> collection) {
        Intrinsics.checkParameterIsNotNull(collection, "clickTrackers");
        this.clickTrackers.addAll(collection);
    }

    public final void addCreativeViewTrackers(Collection<? extends VastTrackerTwo> collection) {
        Intrinsics.checkParameterIsNotNull(collection, "creativeViewTrackers");
        this.creativeViewTrackers.addAll(collection);
    }

    public final void handleImpression(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        TrackingRequest.makeVastTrackingTwoHttpRequest(this.creativeViewTrackers, (VastErrorCode) null, Integer.valueOf(i), (String) null, context);
    }

    public final void handleClick(Context context, int i, String str, String str2) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (context instanceof Activity) {
            String correctClickThroughUrl = this.vastResource.getCorrectClickThroughUrl(this.clickThroughUrl, str);
            if (correctClickThroughUrl != null) {
                if (!(correctClickThroughUrl.length() > 0)) {
                    correctClickThroughUrl = null;
                }
                if (correctClickThroughUrl != null) {
                    new UrlHandler.Builder().withSupportedUrlActions(UrlAction.IGNORE_ABOUT_SCHEME, UrlAction.OPEN_APP_MARKET, UrlAction.OPEN_NATIVE_BROWSER, UrlAction.OPEN_IN_APP_BROWSER, UrlAction.HANDLE_SHARE_TWEET, UrlAction.FOLLOW_DEEP_LINK_WITH_FALLBACK, UrlAction.FOLLOW_DEEP_LINK).withResultActions(new VastCompanionAdConfigTwo$handleClick$$inlined$let$lambda$1(str2, context, i)).withDspCreativeId(str2).withoutMoPubBrowser().build().handleUrl(context, correctClickThroughUrl);
                    return;
                }
                return;
            }
            return;
        }
        throw new IllegalArgumentException("context must be an activity".toString());
    }
}
