package com.mopub.mobileads;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mopub.common.AdReport;
import com.mopub.common.CacheService;
import com.mopub.common.CreativeOrientation;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.CustomEventInterstitial;
import com.mopub.mobileads.VastManager;
import com.mopub.mobileads.VastVideoConfig;
import com.mopub.mobileads.factories.VastManagerFactory;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 ,2\u00020\u00012\u00020\u0002:\u0001,B\u0005¢\u0006\u0002\u0010\u0003J\u001c\u0010#\u001a\u00020$2\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tH\u0014J\b\u0010&\u001a\u00020$H\u0016J\u0012\u0010'\u001a\u00020$2\b\u0010\u001a\u001a\u0004\u0018\u00010(H\u0016J\u0010\u0010)\u001a\u00020$2\u0006\u0010*\u001a\u00020\u0005H\u0014J\b\u0010+\u001a\u00020$H\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R&\u0010\r\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u000f\u0010\u0003\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R&\u0010\u0014\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0015\u0010\u0003\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R&\u0010\u001a\u001a\u0004\u0018\u00010\u001b8\u0006@\u0006X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001c\u0010\u0003\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u000e¢\u0006\u0002\n\u0000¨\u0006-"}, mo45501d2 = {"Lcom/mopub/mobileads/VastVideoInterstitialTwo;", "Lcom/mopub/mobileads/ResponseBodyInterstitial;", "Lcom/mopub/mobileads/VastManager$VastManagerListener;", "()V", "customEventInterstitialListener", "Lcom/mopub/mobileads/CustomEventInterstitial$CustomEventInterstitialListener;", "enableClickExperiment", "", "externalViewabilityTrackers", "", "", "orientation", "Lcom/mopub/common/CreativeOrientation;", "vastManager", "Lcom/mopub/mobileads/VastManager;", "vastManager$annotations", "getVastManager", "()Lcom/mopub/mobileads/VastManager;", "setVastManager", "(Lcom/mopub/mobileads/VastManager;)V", "vastResponse", "vastResponse$annotations", "getVastResponse", "()Ljava/lang/String;", "setVastResponse", "(Ljava/lang/String;)V", "vastVideoConfig", "Lcom/mopub/mobileads/VastVideoConfigTwo;", "vastVideoConfig$annotations", "getVastVideoConfig", "()Lcom/mopub/mobileads/VastVideoConfigTwo;", "setVastVideoConfig", "(Lcom/mopub/mobileads/VastVideoConfigTwo;)V", "videoTrackers", "Lorg/json/JSONObject;", "extractExtras", "", "serverExtras", "onInvalidate", "onVastVideoConfigurationPrepared", "Lcom/mopub/mobileads/VastVideoConfig;", "preRenderHtml", "listener", "showInterstitial", "Companion", "mopub-sdk-interstitial_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
/* compiled from: VastVideoInterstitialTwo.kt */
public class VastVideoInterstitialTwo extends ResponseBodyInterstitial implements VastManager.VastManagerListener {
    /* access modifiers changed from: private */
    public static final String ADAPTER_NAME;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private CustomEventInterstitial.CustomEventInterstitialListener customEventInterstitialListener;
    private boolean enableClickExperiment;
    private Map<String, String> externalViewabilityTrackers;
    private CreativeOrientation orientation;
    private VastManager vastManager;
    private String vastResponse;
    private VastVideoConfigTwo vastVideoConfig;
    private JSONObject videoTrackers;

    @VisibleForTesting
    public static /* synthetic */ void vastManager$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void vastResponse$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void vastVideoConfig$annotations() {
    }

    public final String getVastResponse() {
        return this.vastResponse;
    }

    public final void setVastResponse(String str) {
        this.vastResponse = str;
    }

    public final VastManager getVastManager() {
        return this.vastManager;
    }

    public final void setVastManager(VastManager vastManager2) {
        this.vastManager = vastManager2;
    }

    public final VastVideoConfigTwo getVastVideoConfig() {
        return this.vastVideoConfig;
    }

    public final void setVastVideoConfig(VastVideoConfigTwo vastVideoConfigTwo) {
        this.vastVideoConfig = vastVideoConfigTwo;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x006a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void extractExtras(java.util.Map<java.lang.String, java.lang.String> r9) {
        /*
            r8 = this;
            java.lang.String r0 = "serverExtras"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r0)
            java.lang.String r0 = "html-response-body"
            java.lang.Object r0 = r9.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            r8.vastResponse = r0
            java.lang.String r0 = "com_mopub_orientation"
            java.lang.Object r0 = r9.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            com.mopub.common.CreativeOrientation r0 = com.mopub.common.CreativeOrientation.fromString(r0)
            r8.orientation = r0
            java.lang.String r0 = "com_mopub_vast_click_exp_enabled"
            java.lang.Object r0 = r9.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            r1 = 0
            if (r0 == 0) goto L_0x002d
            boolean r0 = java.lang.Boolean.parseBoolean(r0)
            goto L_0x002e
        L_0x002d:
            r0 = 0
        L_0x002e:
            r8.enableClickExperiment = r0
            java.lang.String r0 = "external-video-viewability-trackers"
            java.lang.Object r0 = r9.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L_0x005d
            java.util.Map r0 = com.mopub.common.util.Json.jsonStringToMap(r0)     // Catch:{ JSONException -> 0x0041 }
            goto L_0x005e
        L_0x0041:
            com.mopub.common.logging.MoPubLog$AdapterLogEvent r4 = com.mopub.common.logging.MoPubLog.AdapterLogEvent.CUSTOM
            com.mopub.common.logging.MoPubLog$MPLogEventType r4 = (com.mopub.common.logging.MoPubLog.MPLogEventType) r4
            java.lang.Object[] r5 = new java.lang.Object[r2]
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Failed to parse video viewability trackers to JSON: "
            r6.append(r7)
            r6.append(r0)
            java.lang.String r0 = r6.toString()
            r5[r1] = r0
            com.mopub.common.logging.MoPubLog.log(r4, r5)
        L_0x005d:
            r0 = r3
        L_0x005e:
            r8.externalViewabilityTrackers = r0
            java.lang.String r0 = "video-trackers"
            java.lang.Object r9 = r9.get(r0)
            java.lang.String r9 = (java.lang.String) r9
            if (r9 == 0) goto L_0x00a3
            r0 = r9
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            if (r0 <= 0) goto L_0x0075
            r0 = 1
            goto L_0x0076
        L_0x0075:
            r0 = 0
        L_0x0076:
            if (r0 == 0) goto L_0x0079
            goto L_0x007a
        L_0x0079:
            r9 = r3
        L_0x007a:
            if (r9 == 0) goto L_0x00a3
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0083 }
            r0.<init>(r9)     // Catch:{ JSONException -> 0x0083 }
            r3 = r0
            goto L_0x00a3
        L_0x0083:
            r0 = move-exception
            com.mopub.common.logging.MoPubLog$SdkLogEvent r4 = com.mopub.common.logging.MoPubLog.SdkLogEvent.ERROR_WITH_THROWABLE
            com.mopub.common.logging.MoPubLog$MPLogEventType r4 = (com.mopub.common.logging.MoPubLog.MPLogEventType) r4
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Failed to parse video trackers to JSON: "
            r6.append(r7)
            r6.append(r9)
            java.lang.String r9 = r6.toString()
            r5[r1] = r9
            r5[r2] = r0
            com.mopub.common.logging.MoPubLog.log(r4, r5)
        L_0x00a3:
            r8.videoTrackers = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mopub.mobileads.VastVideoInterstitialTwo.extractExtras(java.util.Map):void");
    }

    /* access modifiers changed from: protected */
    public void preRenderHtml(CustomEventInterstitial.CustomEventInterstitialListener customEventInterstitialListener2) {
        Intrinsics.checkParameterIsNotNull(customEventInterstitialListener2, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.customEventInterstitialListener = customEventInterstitialListener2;
        if (!CacheService.initializeDiskCache(this.mContext)) {
            MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_FAILED, ADAPTER_NAME, Integer.valueOf(MoPubErrorCode.VIDEO_CACHE_ERROR.getIntCode()), MoPubErrorCode.VIDEO_CACHE_ERROR);
            customEventInterstitialListener2.onInterstitialFailed(MoPubErrorCode.VIDEO_CACHE_ERROR);
            return;
        }
        VastManager create = VastManagerFactory.create(this.mContext);
        AdReport adReport = this.mAdReport;
        Intrinsics.checkExpressionValueIsNotNull(adReport, "mAdReport");
        create.prepareVastVideoConfiguration(this.vastResponse, this, adReport.getDspCreativeId(), this.mContext);
        this.vastManager = create;
        MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_SUCCESS, ADAPTER_NAME);
    }

    public void showInterstitial() {
        MoPubLog.log(MoPubLog.AdapterLogEvent.SHOW_ATTEMPTED, ADAPTER_NAME);
        MraidVideoPlayerActivity.startVast(this.mContext, this.vastVideoConfig, this.mBroadcastIdentifier, this.orientation);
    }

    public void onInvalidate() {
        VastManager vastManager2 = this.vastManager;
        if (vastManager2 != null) {
            vastManager2.cancel();
        }
        super.onInvalidate();
    }

    public void onVastVideoConfigurationPrepared(VastVideoConfig vastVideoConfig2) {
        if (vastVideoConfig2 != null) {
            Gson create = new GsonBuilder().registerTypeAdapterFactory(new VastVideoConfig.VastVideoConfigTypeAdapterFactory()).create();
            VastVideoConfigTwo vastVideoConfigTwo = (VastVideoConfigTwo) create.fromJson(create.toJson((Object) vastVideoConfig2), VastVideoConfigTwo.class);
            if (vastVideoConfigTwo != null) {
                this.vastVideoConfig = vastVideoConfigTwo;
                vastVideoConfigTwo.addVideoTrackers(this.videoTrackers);
                vastVideoConfigTwo.addExternalViewabilityTrackers(this.externalViewabilityTrackers);
                vastVideoConfigTwo.setEnableClickExperiment(this.enableClickExperiment);
                CustomEventInterstitial.CustomEventInterstitialListener customEventInterstitialListener2 = this.customEventInterstitialListener;
                if (customEventInterstitialListener2 != null) {
                    customEventInterstitialListener2.onInterstitialLoaded();
                }
                if (vastVideoConfigTwo != null) {
                    return;
                }
            }
        }
        CustomEventInterstitial.CustomEventInterstitialListener customEventInterstitialListener3 = this.customEventInterstitialListener;
        if (customEventInterstitialListener3 != null) {
            customEventInterstitialListener3.onInterstitialFailed(MoPubErrorCode.VIDEO_DOWNLOAD_ERROR);
            Unit unit = Unit.INSTANCE;
        }
    }

    @Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, mo45501d2 = {"Lcom/mopub/mobileads/VastVideoInterstitialTwo$Companion;", "", "()V", "ADAPTER_NAME", "", "getADAPTER_NAME", "()Ljava/lang/String;", "mopub-sdk-interstitial_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
    /* compiled from: VastVideoInterstitialTwo.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getADAPTER_NAME() {
            return VastVideoInterstitialTwo.ADAPTER_NAME;
        }
    }

    static {
        String simpleName = VastVideoInterstitialTwo.class.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "VastVideoInterstitialTwo::class.java.simpleName");
        ADAPTER_NAME = simpleName;
    }
}
