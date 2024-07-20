package com.mopub.mobileads;

import android.content.Context;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler;
import com.mopub.network.TrackingRequest;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Mockable
@Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0017\u0018\u0000 '2\u00020\u0001:\u0001'BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\u000fJ$\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\r2\b\u0010#\u001a\u0004\u0018\u00010\rH\u0016J \u0010$\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010%\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\rH\u0016R\u0018\u0010\f\u001a\u0004\u0018\u00010\r8\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0016X\u0004¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0004\u001a\u00020\u00038\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0005\u001a\u00020\u00038\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0016\u0010\u0007\u001a\u00020\b8\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0013R\u0016\u0010\u0002\u001a\u00020\u00038\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018¨\u0006("}, mo45501d2 = {"Lcom/mopub/mobileads/VastIconConfigTwo;", "Ljava/io/Serializable;", "width", "", "height", "offsetMS", "durationMS", "vastResource", "Lcom/mopub/mobileads/VastResourceTwo;", "clickTrackingUris", "", "Lcom/mopub/mobileads/VastTrackerTwo;", "clickThroughUri", "", "viewTrackingUris", "(IILjava/lang/Integer;Ljava/lang/Integer;Lcom/mopub/mobileads/VastResourceTwo;Ljava/util/List;Ljava/lang/String;Ljava/util/List;)V", "getClickThroughUri", "()Ljava/lang/String;", "getClickTrackingUris", "()Ljava/util/List;", "getDurationMS", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getHeight", "()I", "getOffsetMS", "getVastResource", "()Lcom/mopub/mobileads/VastResourceTwo;", "getViewTrackingUris", "getWidth", "handleClick", "", "context", "Landroid/content/Context;", "webViewClickThroughUri", "dspCreativeId", "handleImpression", "contentPlayHead", "assetUri", "Companion", "mopub-sdk-base_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
/* compiled from: VastIconConfigTwo.kt */
public class VastIconConfigTwo implements Serializable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final long serialVersionUID = 1;
    @SerializedName("clickthrough_url")
    @Expose
    private final String clickThroughUri;
    @SerializedName("click_trackers")
    @Expose
    private final List<VastTrackerTwo> clickTrackingUris;
    @SerializedName("duration_ms")
    @Expose
    private final Integer durationMS;
    @SerializedName("height")
    @Expose
    private final int height;
    @SerializedName("skip_offset_ms")
    @Expose
    private final int offsetMS;
    @SerializedName("resource")
    @Expose
    private final VastResourceTwo vastResource;
    @SerializedName("video_viewability_tracker")
    @Expose
    private final List<VastTrackerTwo> viewTrackingUris;
    @SerializedName("width")
    @Expose
    private final int width;

    public VastIconConfigTwo(int i, int i2, Integer num, Integer num2, VastResourceTwo vastResourceTwo, List<? extends VastTrackerTwo> list, String str, List<? extends VastTrackerTwo> list2) {
        Intrinsics.checkParameterIsNotNull(vastResourceTwo, "vastResource");
        Intrinsics.checkParameterIsNotNull(list, "clickTrackingUris");
        Intrinsics.checkParameterIsNotNull(list2, "viewTrackingUris");
        this.width = i;
        this.height = i2;
        this.durationMS = num2;
        this.vastResource = vastResourceTwo;
        this.clickTrackingUris = list;
        this.clickThroughUri = str;
        this.viewTrackingUris = list2;
        this.offsetMS = num != null ? num.intValue() : 0;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Integer getDurationMS() {
        return this.durationMS;
    }

    public VastResourceTwo getVastResource() {
        return this.vastResource;
    }

    public List<VastTrackerTwo> getClickTrackingUris() {
        return this.clickTrackingUris;
    }

    public String getClickThroughUri() {
        return this.clickThroughUri;
    }

    public List<VastTrackerTwo> getViewTrackingUris() {
        return this.viewTrackingUris;
    }

    public int getOffsetMS() {
        return this.offsetMS;
    }

    public void handleImpression(Context context, int i, String str) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(str, "assetUri");
        TrackingRequest.makeVastTrackingTwoHttpRequest(getViewTrackingUris(), (VastErrorCode) null, Integer.valueOf(i), str, context);
    }

    public void handleClick(Context context, String str, String str2) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        String correctClickThroughUrl = getVastResource().getCorrectClickThroughUrl(getClickThroughUri(), str);
        if (correctClickThroughUrl != null) {
            if (!(correctClickThroughUrl.length() > 0)) {
                correctClickThroughUrl = null;
            }
            if (correctClickThroughUrl != null) {
                new UrlHandler.Builder().withSupportedUrlActions(UrlAction.IGNORE_ABOUT_SCHEME, UrlAction.OPEN_NATIVE_BROWSER, UrlAction.OPEN_IN_APP_BROWSER).withResultActions(new VastIconConfigTwo$handleClick$$inlined$let$lambda$1(str2, context)).withoutMoPubBrowser().build().handleUrl(context, correctClickThroughUrl);
            }
        }
    }

    @Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo45501d2 = {"Lcom/mopub/mobileads/VastIconConfigTwo$Companion;", "", "()V", "serialVersionUID", "", "mopub-sdk-base_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
    /* compiled from: VastIconConfigTwo.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
