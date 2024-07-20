package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mopub.common.Constants;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.VastAbsoluteProgressTrackerTwo;
import com.mopub.mobileads.VastFractionalProgressTrackerTwo;
import com.mopub.mobileads.VastTrackerTwo;
import com.mopub.network.TrackingRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONObject;

@Mockable
@Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000²\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\u0010 \n\u0002\b\u000e\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0017\u0018\u0000 ±\u00012\u00020\u0001:\u0002±\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010t\u001a\u00020u2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050vH\u0016J\u0018\u0010w\u001a\u00020u2\u000e\u0010x\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u001cH\u0016J\u0016\u0010y\u001a\u00020u2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\n0vH\u0016J\u0016\u0010z\u001a\u00020u2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\n0vH\u0016J\u0016\u0010{\u001a\u00020u2\f\u0010|\u001a\b\u0012\u0004\u0012\u00020\b0vH\u0012J\u0016\u0010}\u001a\u00020u2\f\u0010|\u001a\b\u0012\u0004\u0012\u00020\b0vH\u0012J\u0016\u0010~\u001a\u00020u2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\n0vH\u0016J\u0016\u0010\u001a\u00020u2\f\u0010|\u001a\b\u0012\u0004\u0012\u00020\b0vH\u0012J\u0017\u0010\u0001\u001a\u00020u2\f\u0010@\u001a\b\u0012\u0004\u0012\u00020\n0vH\u0016J\u001f\u0010\u0001\u001a\u00020u2\u0014\u0010B\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010CH\u0016J\u0017\u0010\u0001\u001a\u00020u2\f\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00110vH\u0016J!\u0010\u0001\u001a\u00020u2\f\u0010|\u001a\b\u0012\u0004\u0012\u00020\b0v2\b\u0010\u0001\u001a\u00030\u0001H\u0012J\u0017\u0010\u0001\u001a\u00020u2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\n0vH\u0016J\u001a\u0010\u0001\u001a\u00020u2\u000f\u0010\u0001\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u001cH\u0016J\u0017\u0010\u0001\u001a\u00020u2\f\u0010T\u001a\b\u0012\u0004\u0012\u00020\n0vH\u0016J\u0017\u0010\u0001\u001a\u00020u2\f\u0010a\u001a\b\u0012\u0004\u0012\u00020\n0vH\u0016J\u0017\u0010\u0001\u001a\u00020u2\f\u0010f\u001a\b\u0012\u0004\u0012\u00020\n0vH\u0016J\u0017\u0010\u0001\u001a\u00020u2\f\u0010|\u001a\b\u0012\u0004\u0012\u00020\b0vH\u0012J\u0015\u0010\u0001\u001a\u00020u2\n\u0010\u0001\u001a\u0005\u0018\u00010\u0001H\u0016J\u001d\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\n0v2\f\u0010|\u001a\b\u0012\u0004\u0012\u00020\b0vH\u0012J\u001a\u0010\u0001\u001a\u0004\u0018\u00010^2\u0007\u0010\u0001\u001a\u00020^H\u0016¢\u0006\u0003\u0010\u0001J!\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\n0v2\u0007\u0010\u0001\u001a\u00020^2\u0007\u0010\u0001\u001a\u00020^H\u0016J\u0014\u0010\u0001\u001a\u0004\u0018\u00010N2\u0007\u0010\u0001\u001a\u00020^H\u0016J-\u0010\u0001\u001a\u00020u2\b\u0010\u0001\u001a\u00030\u00012\u0007\u0010\u0001\u001a\u00020^2\t\u0010\u0001\u001a\u0004\u0018\u00010^H\u0012¢\u0006\u0003\u0010\u0001J%\u0010\u0001\u001a\u00020u2\b\u0010 \u0001\u001a\u00030¡\u00012\u0007\u0010\u0001\u001a\u00020^2\u0007\u0010\u0001\u001a\u00020^H\u0016J\u001c\u0010¢\u0001\u001a\u00020u2\b\u0010\u0001\u001a\u00030\u00012\u0007\u0010\u0001\u001a\u00020^H\u0016J\u001c\u0010£\u0001\u001a\u00020u2\b\u0010\u0001\u001a\u00030\u00012\u0007\u0010\u0001\u001a\u00020^H\u0016J\u001c\u0010¤\u0001\u001a\u00020u2\b\u0010\u0001\u001a\u00030\u00012\u0007\u0010\u0001\u001a\u00020^H\u0016J(\u0010¥\u0001\u001a\u00020u2\b\u0010\u0001\u001a\u00030\u00012\n\u0010¦\u0001\u001a\u0005\u0018\u00010§\u00012\u0007\u0010\u0001\u001a\u00020^H\u0016J\u001c\u0010¨\u0001\u001a\u00020u2\b\u0010\u0001\u001a\u00030\u00012\u0007\u0010\u0001\u001a\u00020^H\u0016J\u001c\u0010©\u0001\u001a\u00020u2\b\u0010\u0001\u001a\u00030\u00012\u0007\u0010\u0001\u001a\u00020^H\u0016J\u001c\u0010ª\u0001\u001a\u00020u2\b\u0010\u0001\u001a\u00030\u00012\u0007\u0010\u0001\u001a\u00020^H\u0016J\u001c\u0010«\u0001\u001a\u00020u2\b\u0010\u0001\u001a\u00030\u00012\u0007\u0010\u0001\u001a\u00020^H\u0016J\t\u0010¬\u0001\u001a\u00020;H\u0016J%\u0010­\u0001\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010v2\t\u0010®\u0001\u001a\u0004\u0018\u00010\b2\u0007\u0010|\u001a\u00030¯\u0001H\u0012J\u001d\u0010°\u0001\u001a\u00020u2\b\u0010M\u001a\u0004\u0018\u00010N2\b\u0010V\u001a\u0004\u0018\u00010NH\u0016R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0012X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0012X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00048\u0012X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u00048\u0012X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\u00048\u0012X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\u00048\u0012X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u000f8\u0012X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00048\u0012X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\n0\u00048\u0012X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0012X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\n0\u00048\u0012X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\n0\u00048\u0012X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\n0\u00048\u0012X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u00188VX\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\b0\u001c8VX\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR \u0010\u001f\u001a\u0004\u0018\u00010\b8\u0016@\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\b\u0012\u0004\u0012\u00020\n0\u00188VX\u0004¢\u0006\u0006\u001a\u0004\b%\u0010\u001aR\u001a\u0010&\u001a\b\u0012\u0004\u0012\u00020\n0\u00188VX\u0004¢\u0006\u0006\u001a\u0004\b'\u0010\u001aR\u001a\u0010(\u001a\b\u0012\u0004\u0012\u00020\n0\u00188VX\u0004¢\u0006\u0006\u001a\u0004\b)\u0010\u001aR*\u0010+\u001a\u0004\u0018\u00010\b2\b\u0010*\u001a\u0004\u0018\u00010\b8\u0016@VX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010!\"\u0004\b-\u0010#R*\u0010.\u001a\u0004\u0018\u00010\b2\b\u0010*\u001a\u0004\u0018\u00010\b8\u0016@VX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010!\"\u0004\b0\u0010#R*\u00101\u001a\u0004\u0018\u00010\b2\b\u0010*\u001a\u0004\u0018\u00010\b8\u0016@VX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010!\"\u0004\b3\u0010#R \u00104\u001a\u0004\u0018\u00010\b8\u0016@\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010!\"\u0004\b6\u0010#R*\u00107\u001a\u0004\u0018\u00010\b2\b\u0010*\u001a\u0004\u0018\u00010\b8\u0016@VX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010!\"\u0004\b9\u0010#R\u001e\u0010:\u001a\u00020;8\u0016@\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u001a\u0010@\u001a\b\u0012\u0004\u0012\u00020\n0\u00188VX\u0004¢\u0006\u0006\u001a\u0004\bA\u0010\u001aR \u0010B\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0C8VX\u0004¢\u0006\u0006\u001a\u0004\bD\u0010ER\u001a\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00110\u00188VX\u0004¢\u0006\u0006\u001a\u0004\bG\u0010\u001aR\u001a\u0010H\u001a\b\u0012\u0004\u0012\u00020\n0\u00188VX\u0004¢\u0006\u0006\u001a\u0004\bI\u0010\u001aR&\u0010K\u001a\u00020;2\u0006\u0010J\u001a\u00020;8\u0016@PX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010=\"\u0004\bL\u0010?R\u0014\u0010M\u001a\u0004\u0018\u00010N8\u0012@\u0012X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010O\u001a\b\u0012\u0004\u0012\u00020\b0\u001c8VX\u0004¢\u0006\u0006\u001a\u0004\bP\u0010\u001eR \u0010Q\u001a\u0004\u0018\u00010\b8\u0016@\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010!\"\u0004\bS\u0010#R\u001a\u0010T\u001a\b\u0012\u0004\u0012\u00020\n0\u00188VX\u0004¢\u0006\u0006\u001a\u0004\bU\u0010\u001aR\u0014\u0010V\u001a\u0004\u0018\u00010N8\u0012@\u0012X\u000e¢\u0006\u0002\n\u0000R \u0010W\u001a\u0004\u0018\u00010\b8\u0016@\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010!\"\u0004\bY\u0010#R*\u0010Z\u001a\u0004\u0018\u00010\b2\b\u0010*\u001a\u0004\u0018\u00010\b8\u0016@VX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010!\"\u0004\b\\\u0010#R\u0014\u0010]\u001a\u00020^8VX\u0004¢\u0006\u0006\u001a\u0004\b_\u0010`R\u001a\u0010a\u001a\b\u0012\u0004\u0012\u00020\n0\u00188VX\u0004¢\u0006\u0006\u001a\u0004\bb\u0010\u001aR*\u0010c\u001a\u0004\u0018\u00010\b2\b\u0010*\u001a\u0004\u0018\u00010\b8\u0016@PX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u0010!\"\u0004\be\u0010#R\u001a\u0010f\u001a\b\u0012\u0004\u0012\u00020\n0\u00188VX\u0004¢\u0006\u0006\u001a\u0004\bg\u0010\u001aR \u0010h\u001a\u0004\u0018\u00010i8\u0016@\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bj\u0010k\"\u0004\bl\u0010mR*\u0010o\u001a\u0004\u0018\u00010n2\b\u0010*\u001a\u0004\u0018\u00010n8\u0016@VX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bp\u0010q\"\u0004\br\u0010s¨\u0006²\u0001"}, mo45501d2 = {"Lcom/mopub/mobileads/VastVideoConfigTwo;", "Ljava/io/Serializable;", "()V", "_absoluteTrackers", "", "Lcom/mopub/mobileads/VastAbsoluteProgressTrackerTwo;", "_avidJavascriptResources", "", "", "_clickTrackers", "Lcom/mopub/mobileads/VastTrackerTwo;", "_closeTrackers", "_completeTrackers", "_errorTrackers", "_externalViewabilityTrackers", "", "_fractionalTrackers", "Lcom/mopub/mobileads/VastFractionalProgressTrackerTwo;", "_impressionTrackers", "_moatImpressionPixels", "_pauseTrackers", "_resumeTrackers", "_skipTrackers", "absoluteTrackers", "Ljava/util/ArrayList;", "getAbsoluteTrackers", "()Ljava/util/ArrayList;", "avidJavascriptResources", "", "getAvidJavascriptResources", "()Ljava/util/Set;", "clickThroughUrl", "getClickThroughUrl", "()Ljava/lang/String;", "setClickThroughUrl", "(Ljava/lang/String;)V", "clickTrackers", "getClickTrackers", "closeTrackers", "getCloseTrackers", "completeTrackers", "getCompleteTrackers", "value", "customCloseIconUrl", "getCustomCloseIconUrl", "setCustomCloseIconUrl", "customCtaText", "getCustomCtaText", "setCustomCtaText", "customSkipText", "getCustomSkipText", "setCustomSkipText", "diskMediaFileUrl", "getDiskMediaFileUrl", "setDiskMediaFileUrl", "dspCreativeId", "getDspCreativeId", "setDspCreativeId", "enableClickExperiment", "", "getEnableClickExperiment", "()Z", "setEnableClickExperiment", "(Z)V", "errorTrackers", "getErrorTrackers", "externalViewabilityTrackers", "", "getExternalViewabilityTrackers", "()Ljava/util/Map;", "fractionalTrackers", "getFractionalTrackers", "impressionTrackers", "getImpressionTrackers", "<set-?>", "isRewarded", "setRewarded$mopub_sdk_base_release", "landscapeVastCompanionAdConfig", "Lcom/mopub/mobileads/VastCompanionAdConfigTwo;", "moatImpressionPixels", "getMoatImpressionPixels", "networkMediaFileUrl", "getNetworkMediaFileUrl", "setNetworkMediaFileUrl", "pauseTrackers", "getPauseTrackers", "portraitVastCompanionAdConfig", "privacyInformationIconClickthroughUrl", "getPrivacyInformationIconClickthroughUrl", "setPrivacyInformationIconClickthroughUrl", "privacyInformationIconImageUrl", "getPrivacyInformationIconImageUrl", "setPrivacyInformationIconImageUrl", "remainingProgressTrackerCount", "", "getRemainingProgressTrackerCount", "()I", "resumeTrackers", "getResumeTrackers", "skipOffset", "getSkipOffset", "setSkipOffset$mopub_sdk_base_release", "skipTrackers", "getSkipTrackers", "vastIconConfig", "Lcom/mopub/mobileads/VastIconConfigTwo;", "getVastIconConfig", "()Lcom/mopub/mobileads/VastIconConfigTwo;", "setVastIconConfig", "(Lcom/mopub/mobileads/VastIconConfigTwo;)V", "Lcom/mopub/mobileads/VideoViewabilityTracker;", "videoViewabilityTracker", "getVideoViewabilityTracker", "()Lcom/mopub/mobileads/VideoViewabilityTracker;", "setVideoViewabilityTracker", "(Lcom/mopub/mobileads/VideoViewabilityTracker;)V", "addAbsoluteTrackers", "", "", "addAvidJavascriptResources", "javascriptResources", "addClickTrackers", "addCloseTrackers", "addCompanionAdClickTrackersForUrls", "urls", "addCompanionAdViewTrackersForUrls", "addCompleteTrackers", "addCompleteTrackersForUrls", "addErrorTrackers", "addExternalViewabilityTrackers", "addFractionalTrackers", "addFractionalTrackersForUrls", "fraction", "", "addImpressionTrackers", "addMoatImpressionPixels", "impressionPixels", "addPauseTrackers", "addResumeTrackers", "addSkipTrackers", "addStartTrackersForUrls", "addVideoTrackers", "videoTrackers", "Lorg/json/JSONObject;", "createVastTrackersForUrls", "getSkipOffsetMillis", "videoDuration", "(I)Ljava/lang/Integer;", "getUntriggeredTrackersBefore", "currentPositionMillis", "videoLengthMillis", "getVastCompanionAd", "orientation", "handleClick", "context", "Landroid/content/Context;", "contentPlayHead", "requestCode", "(Landroid/content/Context;ILjava/lang/Integer;)V", "handleClickForResult", "activity", "Landroid/app/Activity;", "handleClickWithoutResult", "handleClose", "handleComplete", "handleError", "errorCode", "Lcom/mopub/mobileads/VastErrorCode;", "handleImpression", "handlePause", "handleResume", "handleSkip", "hasCompanionAd", "hydrateUrls", "event", "Lorg/json/JSONArray;", "setVastCompanionAd", "Companion", "mopub-sdk-base_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
/* compiled from: VastVideoConfigTwo.kt */
public class VastVideoConfigTwo implements Serializable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final long serialVersionUID = 3;
    @SerializedName("absolute_trackers")
    @Expose
    private final List<VastAbsoluteProgressTrackerTwo> _absoluteTrackers = new ArrayList();
    @SerializedName("avid_javascript_resources")
    @Expose
    private final Set<String> _avidJavascriptResources = new LinkedHashSet();
    @SerializedName("click_trackers")
    @Expose
    private final List<VastTrackerTwo> _clickTrackers = new ArrayList();
    @SerializedName("close_trackers")
    @Expose
    private final List<VastTrackerTwo> _closeTrackers = new ArrayList();
    @SerializedName("complete_trackers")
    @Expose
    private final List<VastTrackerTwo> _completeTrackers = new ArrayList();
    @SerializedName("error_trackers")
    @Expose
    private final List<VastTrackerTwo> _errorTrackers = new ArrayList();
    @SerializedName("external_viewability_trackers")
    @Expose
    private final Map<String, String> _externalViewabilityTrackers = new LinkedHashMap();
    @SerializedName("fractional_trackers")
    @Expose
    private final List<VastFractionalProgressTrackerTwo> _fractionalTrackers = new ArrayList();
    @SerializedName("impression_trackers")
    @Expose
    private final List<VastTrackerTwo> _impressionTrackers = new ArrayList();
    @SerializedName("moat_impression_pixels")
    @Expose
    private final Set<String> _moatImpressionPixels = new LinkedHashSet();
    @SerializedName("pause_trackers")
    @Expose
    private final List<VastTrackerTwo> _pauseTrackers = new ArrayList();
    @SerializedName("resume_trackers")
    @Expose
    private final List<VastTrackerTwo> _resumeTrackers = new ArrayList();
    @SerializedName("skip_trackers")
    @Expose
    private final List<VastTrackerTwo> _skipTrackers = new ArrayList();
    @SerializedName("clickthrough_url")
    @Expose
    private String clickThroughUrl;
    @SerializedName("custom_close_icon_url")
    @Expose
    private String customCloseIconUrl;
    @SerializedName("custom_cta_text")
    @Expose
    private String customCtaText;
    @SerializedName("custom_skip_text")
    @Expose
    private String customSkipText;
    @SerializedName("disk_media_file_url")
    @Expose
    private String diskMediaFileUrl;
    @SerializedName("dsp_creative_id")
    @Expose
    private String dspCreativeId;
    @SerializedName("enable_click_exp")
    @Expose
    private boolean enableClickExperiment;
    @SerializedName("is_rewarded")
    @Expose
    private boolean isRewarded;
    @SerializedName("landscape_companion_ad")
    @Expose
    private VastCompanionAdConfigTwo landscapeVastCompanionAdConfig;
    @SerializedName("network_media_file_url")
    @Expose
    private String networkMediaFileUrl;
    @SerializedName("portrait_companion_ad")
    @Expose
    private VastCompanionAdConfigTwo portraitVastCompanionAdConfig;
    @SerializedName("privacy_icon_click_url")
    @Expose
    private String privacyInformationIconClickthroughUrl;
    @SerializedName("privacy_icon_image_url")
    @Expose
    private String privacyInformationIconImageUrl;
    @SerializedName("skip_offset")
    @Expose
    private String skipOffset;
    @SerializedName("icon_config")
    @Expose
    private VastIconConfigTwo vastIconConfig;
    @SerializedName("video_viewability_tracker")
    @Expose
    private VideoViewabilityTracker videoViewabilityTracker;

    @Metadata(mo45499bv = {1, 0, 3}, mo45502k = 3, mo45503mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[VideoTrackingEvent.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[VideoTrackingEvent.START.ordinal()] = 1;
            $EnumSwitchMapping$0[VideoTrackingEvent.FIRST_QUARTILE.ordinal()] = 2;
            $EnumSwitchMapping$0[VideoTrackingEvent.MIDPOINT.ordinal()] = 3;
            $EnumSwitchMapping$0[VideoTrackingEvent.THIRD_QUARTILE.ordinal()] = 4;
            $EnumSwitchMapping$0[VideoTrackingEvent.COMPLETE.ordinal()] = 5;
            $EnumSwitchMapping$0[VideoTrackingEvent.COMPANION_AD_VIEW.ordinal()] = 6;
            $EnumSwitchMapping$0[VideoTrackingEvent.COMPANION_AD_CLICK.ordinal()] = 7;
        }
    }

    @Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo45501d2 = {"Lcom/mopub/mobileads/VastVideoConfigTwo$Companion;", "", "()V", "serialVersionUID", "", "mopub-sdk-base_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
    /* compiled from: VastVideoConfigTwo.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public ArrayList<VastTrackerTwo> getImpressionTrackers() {
        return new ArrayList<>(this._impressionTrackers);
    }

    public ArrayList<VastTrackerTwo> getPauseTrackers() {
        return new ArrayList<>(this._pauseTrackers);
    }

    public ArrayList<VastTrackerTwo> getResumeTrackers() {
        return new ArrayList<>(this._resumeTrackers);
    }

    public ArrayList<VastTrackerTwo> getCompleteTrackers() {
        return new ArrayList<>(this._completeTrackers);
    }

    public ArrayList<VastTrackerTwo> getCloseTrackers() {
        return new ArrayList<>(this._closeTrackers);
    }

    public ArrayList<VastTrackerTwo> getSkipTrackers() {
        return new ArrayList<>(this._skipTrackers);
    }

    public ArrayList<VastTrackerTwo> getClickTrackers() {
        return new ArrayList<>(this._clickTrackers);
    }

    public ArrayList<VastTrackerTwo> getErrorTrackers() {
        return new ArrayList<>(this._errorTrackers);
    }

    public ArrayList<VastFractionalProgressTrackerTwo> getFractionalTrackers() {
        return new ArrayList<>(this._fractionalTrackers);
    }

    public ArrayList<VastAbsoluteProgressTrackerTwo> getAbsoluteTrackers() {
        return new ArrayList<>(this._absoluteTrackers);
    }

    public Map<String, String> getExternalViewabilityTrackers() {
        return new HashMap<>(this._externalViewabilityTrackers);
    }

    public Set<String> getAvidJavascriptResources() {
        return new HashSet<>(this._avidJavascriptResources);
    }

    public Set<String> getMoatImpressionPixels() {
        return new HashSet<>(this._moatImpressionPixels);
    }

    public String getClickThroughUrl() {
        return this.clickThroughUrl;
    }

    public void setClickThroughUrl(String str) {
        this.clickThroughUrl = str;
    }

    public String getNetworkMediaFileUrl() {
        return this.networkMediaFileUrl;
    }

    public void setNetworkMediaFileUrl(String str) {
        this.networkMediaFileUrl = str;
    }

    public String getDiskMediaFileUrl() {
        return this.diskMediaFileUrl;
    }

    public void setDiskMediaFileUrl(String str) {
        this.diskMediaFileUrl = str;
    }

    public String getSkipOffset() {
        return this.skipOffset;
    }

    public void setSkipOffset$mopub_sdk_base_release(String str) {
        if (str == null) {
            str = this.skipOffset;
        }
        this.skipOffset = str;
    }

    public VastIconConfigTwo getVastIconConfig() {
        return this.vastIconConfig;
    }

    public void setVastIconConfig(VastIconConfigTwo vastIconConfigTwo) {
        this.vastIconConfig = vastIconConfigTwo;
    }

    public boolean isRewarded() {
        return this.isRewarded;
    }

    public void setRewarded$mopub_sdk_base_release(boolean z) {
        this.isRewarded = z;
    }

    public boolean getEnableClickExperiment() {
        return this.enableClickExperiment;
    }

    public void setEnableClickExperiment(boolean z) {
        this.enableClickExperiment = z;
    }

    public String getCustomCtaText() {
        return this.customCtaText;
    }

    public void setCustomCtaText(String str) {
        if (str == null) {
            str = this.customCtaText;
        }
        this.customCtaText = str;
    }

    public String getCustomSkipText() {
        return this.customSkipText;
    }

    public void setCustomSkipText(String str) {
        if (str == null) {
            str = this.customSkipText;
        }
        this.customSkipText = str;
    }

    public String getCustomCloseIconUrl() {
        return this.customCloseIconUrl;
    }

    public void setCustomCloseIconUrl(String str) {
        if (str == null) {
            str = this.customCloseIconUrl;
        }
        this.customCloseIconUrl = str;
    }

    public VideoViewabilityTracker getVideoViewabilityTracker() {
        return this.videoViewabilityTracker;
    }

    public void setVideoViewabilityTracker(VideoViewabilityTracker videoViewabilityTracker2) {
        if (videoViewabilityTracker2 == null) {
            videoViewabilityTracker2 = this.videoViewabilityTracker;
        }
        this.videoViewabilityTracker = videoViewabilityTracker2;
    }

    public String getDspCreativeId() {
        return this.dspCreativeId;
    }

    public void setDspCreativeId(String str) {
        if (str == null) {
            str = this.dspCreativeId;
        }
        this.dspCreativeId = str;
    }

    public String getPrivacyInformationIconImageUrl() {
        return this.privacyInformationIconImageUrl;
    }

    public void setPrivacyInformationIconImageUrl(String str) {
        if (str == null) {
            str = this.privacyInformationIconImageUrl;
        }
        this.privacyInformationIconImageUrl = str;
    }

    public String getPrivacyInformationIconClickthroughUrl() {
        return this.privacyInformationIconClickthroughUrl;
    }

    public void setPrivacyInformationIconClickthroughUrl(String str) {
        this.privacyInformationIconClickthroughUrl = str;
    }

    public void addImpressionTrackers(List<? extends VastTrackerTwo> list) {
        Intrinsics.checkParameterIsNotNull(list, "impressionTrackers");
        this._impressionTrackers.addAll(list);
    }

    public void addResumeTrackers(List<? extends VastTrackerTwo> list) {
        Intrinsics.checkParameterIsNotNull(list, "resumeTrackers");
        this._resumeTrackers.addAll(list);
    }

    public void addFractionalTrackers(List<VastFractionalProgressTrackerTwo> list) {
        Intrinsics.checkParameterIsNotNull(list, "fractionalTrackers");
        this._fractionalTrackers.addAll(list);
        CollectionsKt.sort(this._fractionalTrackers);
    }

    public void addAbsoluteTrackers(List<? extends VastAbsoluteProgressTrackerTwo> list) {
        Intrinsics.checkParameterIsNotNull(list, "absoluteTrackers");
        this._absoluteTrackers.addAll(list);
        CollectionsKt.sort(this._absoluteTrackers);
    }

    public void addCompleteTrackers(List<? extends VastTrackerTwo> list) {
        Intrinsics.checkParameterIsNotNull(list, "completeTrackers");
        this._completeTrackers.addAll(list);
    }

    public void addPauseTrackers(List<? extends VastTrackerTwo> list) {
        Intrinsics.checkParameterIsNotNull(list, "pauseTrackers");
        this._pauseTrackers.addAll(list);
    }

    public void addCloseTrackers(List<? extends VastTrackerTwo> list) {
        Intrinsics.checkParameterIsNotNull(list, "closeTrackers");
        this._closeTrackers.addAll(list);
    }

    public void addSkipTrackers(List<? extends VastTrackerTwo> list) {
        Intrinsics.checkParameterIsNotNull(list, "skipTrackers");
        this._skipTrackers.addAll(list);
    }

    public void addClickTrackers(List<? extends VastTrackerTwo> list) {
        Intrinsics.checkParameterIsNotNull(list, "clickTrackers");
        this._clickTrackers.addAll(list);
    }

    public void addErrorTrackers(List<? extends VastTrackerTwo> list) {
        Intrinsics.checkParameterIsNotNull(list, "errorTrackers");
        this._errorTrackers.addAll(list);
    }

    public void addExternalViewabilityTrackers(Map<String, String> map) {
        if (map != null) {
            this._externalViewabilityTrackers.putAll(map);
        }
    }

    public void addAvidJavascriptResources(Set<String> set) {
        if (set != null) {
            this._avidJavascriptResources.addAll(set);
        }
    }

    public void addMoatImpressionPixels(Set<String> set) {
        if (set != null) {
            this._moatImpressionPixels.addAll(set);
        }
    }

    public void addVideoTrackers(JSONObject jSONObject) {
        if (jSONObject != null) {
            JSONArray optJSONArray = jSONObject.optJSONArray(Constants.VIDEO_TRACKING_URLS_KEY);
            JSONArray optJSONArray2 = jSONObject.optJSONArray(Constants.VIDEO_TRACKING_EVENTS_KEY);
            if (optJSONArray != null && optJSONArray2 != null) {
                int length = optJSONArray2.length();
                for (int i = 0; i < length; i++) {
                    String optString = optJSONArray2.optString(i);
                    List<String> hydrateUrls = hydrateUrls(optString, optJSONArray);
                    VideoTrackingEvent fromString = VideoTrackingEvent.Companion.fromString(optString);
                    if (!(optString == null || hydrateUrls == null)) {
                        switch (WhenMappings.$EnumSwitchMapping$0[fromString.ordinal()]) {
                            case 1:
                                addStartTrackersForUrls(hydrateUrls);
                                break;
                            case 2:
                            case 3:
                            case 4:
                                addFractionalTrackersForUrls(hydrateUrls, fromString.toFloat());
                                break;
                            case 5:
                                addCompleteTrackersForUrls(hydrateUrls);
                                break;
                            case 6:
                                addCompanionAdViewTrackersForUrls(hydrateUrls);
                                break;
                            case 7:
                                addCompanionAdClickTrackersForUrls(hydrateUrls);
                                break;
                            default:
                                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Encountered unknown video tracking event: " + optString);
                                break;
                        }
                    }
                }
            }
        }
    }

    public void setVastCompanionAd(VastCompanionAdConfigTwo vastCompanionAdConfigTwo, VastCompanionAdConfigTwo vastCompanionAdConfigTwo2) {
        this.landscapeVastCompanionAdConfig = vastCompanionAdConfigTwo;
        this.portraitVastCompanionAdConfig = vastCompanionAdConfigTwo2;
    }

    public VastCompanionAdConfigTwo getVastCompanionAd(int i) {
        if (i == 1) {
            return this.portraitVastCompanionAdConfig;
        }
        if (i != 2) {
            return this.landscapeVastCompanionAdConfig;
        }
        return this.landscapeVastCompanionAdConfig;
    }

    public boolean hasCompanionAd() {
        return (this.landscapeVastCompanionAdConfig == null || this.portraitVastCompanionAdConfig == null) ? false : true;
    }

    public void handleImpression(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        TrackingRequest.makeVastTrackingTwoHttpRequest(this._impressionTrackers, (VastErrorCode) null, Integer.valueOf(i), getNetworkMediaFileUrl(), context);
    }

    public void handleClickForResult(Activity activity, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        handleClick(activity, i, Integer.valueOf(i2));
    }

    public void handleClickWithoutResult(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "context.applicationContext");
        handleClick(applicationContext, i, (Integer) null);
    }

    private void handleClick(Context context, int i, Integer num) {
        TrackingRequest.makeVastTrackingTwoHttpRequest(this._clickTrackers, (VastErrorCode) null, Integer.valueOf(i), getNetworkMediaFileUrl(), context);
        CharSequence clickThroughUrl2 = getClickThroughUrl();
        if (!(clickThroughUrl2 == null || clickThroughUrl2.length() == 0)) {
            UrlHandler build = new UrlHandler.Builder().withDspCreativeId(getDspCreativeId()).withoutMoPubBrowser().withSupportedUrlActions(UrlAction.IGNORE_ABOUT_SCHEME, UrlAction.OPEN_APP_MARKET, UrlAction.OPEN_NATIVE_BROWSER, UrlAction.OPEN_IN_APP_BROWSER, UrlAction.HANDLE_SHARE_TWEET, UrlAction.FOLLOW_DEEP_LINK_WITH_FALLBACK, UrlAction.FOLLOW_DEEP_LINK).withResultActions(new VastVideoConfigTwo$handleClick$urlHandler$1(this, context, num)).build();
            String clickThroughUrl3 = getClickThroughUrl();
            if (clickThroughUrl3 != null) {
                build.handleUrl(context, clickThroughUrl3);
            }
        }
    }

    public void handleResume(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        TrackingRequest.makeVastTrackingTwoHttpRequest(this._resumeTrackers, (VastErrorCode) null, Integer.valueOf(i), getNetworkMediaFileUrl(), context);
    }

    public void handlePause(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        TrackingRequest.makeVastTrackingTwoHttpRequest(this._pauseTrackers, (VastErrorCode) null, Integer.valueOf(i), getNetworkMediaFileUrl(), context);
    }

    public void handleClose(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        TrackingRequest.makeVastTrackingTwoHttpRequest(this._closeTrackers, (VastErrorCode) null, Integer.valueOf(i), getNetworkMediaFileUrl(), context);
    }

    public void handleSkip(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        TrackingRequest.makeVastTrackingTwoHttpRequest(this._skipTrackers, (VastErrorCode) null, Integer.valueOf(i), getNetworkMediaFileUrl(), context);
    }

    public void handleComplete(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        TrackingRequest.makeVastTrackingTwoHttpRequest(this._completeTrackers, (VastErrorCode) null, Integer.valueOf(i), getNetworkMediaFileUrl(), context);
    }

    public void handleError(Context context, VastErrorCode vastErrorCode, int i) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        TrackingRequest.makeVastTrackingTwoHttpRequest(this._errorTrackers, vastErrorCode, Integer.valueOf(i), getNetworkMediaFileUrl(), context);
    }

    public List<VastTrackerTwo> getUntriggeredTrackersBefore(int i, int i2) {
        if (i2 <= 0 || i < 0) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        VastAbsoluteProgressTrackerTwo build = new VastAbsoluteProgressTrackerTwo.Builder("", i).build();
        for (VastAbsoluteProgressTrackerTwo vastAbsoluteProgressTrackerTwo : this._absoluteTrackers) {
            if (vastAbsoluteProgressTrackerTwo.compareTo(build) <= 0 && !vastAbsoluteProgressTrackerTwo.isTracked()) {
                arrayList.add(vastAbsoluteProgressTrackerTwo);
            }
        }
        VastFractionalProgressTrackerTwo build2 = new VastFractionalProgressTrackerTwo.Builder("", ((float) i) / ((float) i2)).build();
        for (VastFractionalProgressTrackerTwo vastFractionalProgressTrackerTwo : this._fractionalTrackers) {
            if (vastFractionalProgressTrackerTwo.compareTo(build2) <= 0 && !vastFractionalProgressTrackerTwo.isTracked()) {
                arrayList.add(vastFractionalProgressTrackerTwo);
            }
        }
        return arrayList;
    }

    public int getRemainingProgressTrackerCount() {
        return getUntriggeredTrackersBefore(Integer.MAX_VALUE, Integer.MAX_VALUE).size();
    }

    public Integer getSkipOffsetMillis(int i) throws NumberFormatException {
        Integer num;
        String skipOffset2 = getSkipOffset();
        if (skipOffset2 == null) {
            return null;
        }
        if (VastAbsoluteProgressTrackerTwo.Companion.isAbsoluteTracker(skipOffset2)) {
            num = VastAbsoluteProgressTrackerTwo.Companion.parseAbsoluteOffset(skipOffset2);
        } else if (VastFractionalProgressTrackerTwo.Companion.isPercentageTracker(skipOffset2)) {
            num = VastFractionalProgressTrackerTwo.Companion.parsePercentageOffset(skipOffset2, i);
        } else {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Invalid VAST skipoffset format: " + skipOffset2);
            num = null;
        }
        if (num != null) {
            return Integer.valueOf(Math.min(num.intValue(), i));
        }
        return null;
    }

    private List<String> hydrateUrls(String str, JSONArray jSONArray) {
        if (str == null) {
            return null;
        }
        List<String> arrayList = new ArrayList<>();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            String optString = jSONArray.optString(i);
            if (optString != null) {
                arrayList.add(StringsKt.replace$default(optString, Constants.VIDEO_TRACKING_URL_MACRO, str, false, 4, (Object) null));
            }
        }
        return arrayList;
    }

    private List<VastTrackerTwo> createVastTrackersForUrls(List<String> list) {
        Iterable<String> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (String builder : iterable) {
            arrayList.add(new VastTrackerTwo.Builder(builder).build());
        }
        return (List) arrayList;
    }

    private void addCompleteTrackersForUrls(List<String> list) {
        addCompleteTrackers(createVastTrackersForUrls(list));
    }

    private void addStartTrackersForUrls(List<String> list) {
        Iterable<String> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (String builder : iterable) {
            arrayList.add(new VastAbsoluteProgressTrackerTwo.Builder(builder, 0).build());
        }
        addAbsoluteTrackers((List) arrayList);
    }

    private void addFractionalTrackersForUrls(List<String> list, float f) {
        Iterable<String> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (String builder : iterable) {
            arrayList.add(new VastFractionalProgressTrackerTwo.Builder(builder, f).build());
        }
        addFractionalTrackers((List) arrayList);
    }

    private void addCompanionAdViewTrackersForUrls(List<String> list) {
        List<VastTrackerTwo> createVastTrackersForUrls = createVastTrackersForUrls(list);
        VastCompanionAdConfigTwo vastCompanionAdConfigTwo = this.landscapeVastCompanionAdConfig;
        if (vastCompanionAdConfigTwo != null) {
            vastCompanionAdConfigTwo.addCreativeViewTrackers(createVastTrackersForUrls);
        }
        VastCompanionAdConfigTwo vastCompanionAdConfigTwo2 = this.portraitVastCompanionAdConfig;
        if (vastCompanionAdConfigTwo2 != null) {
            vastCompanionAdConfigTwo2.addCreativeViewTrackers(createVastTrackersForUrls);
        }
    }

    private void addCompanionAdClickTrackersForUrls(List<String> list) {
        List<VastTrackerTwo> createVastTrackersForUrls = createVastTrackersForUrls(list);
        VastCompanionAdConfigTwo vastCompanionAdConfigTwo = this.landscapeVastCompanionAdConfig;
        if (vastCompanionAdConfigTwo != null) {
            vastCompanionAdConfigTwo.addClickTrackers(createVastTrackersForUrls);
        }
        VastCompanionAdConfigTwo vastCompanionAdConfigTwo2 = this.portraitVastCompanionAdConfig;
        if (vastCompanionAdConfigTwo2 != null) {
            vastCompanionAdConfigTwo2.addClickTrackers(createVastTrackersForUrls);
        }
    }
}
