package com.mopub.mobileads;

import kotlin.Metadata;
import kotlin.text.StringsKt;

@Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\n\b\u0001\u0018\u0000 \u00112\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0011B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0012"}, mo45501d2 = {"Lcom/mopub/mobileads/VideoTrackingEvent;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "toFloat", "", "START", "FIRST_QUARTILE", "MIDPOINT", "THIRD_QUARTILE", "COMPLETE", "COMPANION_AD_VIEW", "COMPANION_AD_CLICK", "UNKNOWN", "Companion", "mopub-sdk-base_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
/* compiled from: VideoTrackingEvent.kt */
public enum VideoTrackingEvent {
    START("start"),
    FIRST_QUARTILE("firstQuartile"),
    MIDPOINT("midpoint"),
    THIRD_QUARTILE("thirdQuartile"),
    COMPLETE("complete"),
    COMPANION_AD_VIEW("companionAdView"),
    COMPANION_AD_CLICK("companionAdClick"),
    UNKNOWN("");
    
    public static final Companion Companion = null;
    private final String value;

    @Metadata(mo45499bv = {1, 0, 3}, mo45502k = 3, mo45503mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] iArr = new int[VideoTrackingEvent.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[VideoTrackingEvent.FIRST_QUARTILE.ordinal()] = 1;
            $EnumSwitchMapping$0[VideoTrackingEvent.MIDPOINT.ordinal()] = 2;
            $EnumSwitchMapping$0[VideoTrackingEvent.THIRD_QUARTILE.ordinal()] = 3;
            $EnumSwitchMapping$0[VideoTrackingEvent.COMPLETE.ordinal()] = 4;
        }
    }

    private VideoTrackingEvent(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }

    static {
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    @Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, mo45501d2 = {"Lcom/mopub/mobileads/VideoTrackingEvent$Companion;", "", "()V", "fromString", "Lcom/mopub/mobileads/VideoTrackingEvent;", "name", "", "mopub-sdk-base_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
    /* compiled from: VideoTrackingEvent.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final VideoTrackingEvent fromString(String str) {
            VideoTrackingEvent videoTrackingEvent;
            VideoTrackingEvent[] values = VideoTrackingEvent.values();
            int length = values.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    videoTrackingEvent = null;
                    break;
                }
                videoTrackingEvent = values[i];
                if (StringsKt.equals(videoTrackingEvent.getValue(), str, true)) {
                    break;
                }
                i++;
            }
            return videoTrackingEvent != null ? videoTrackingEvent : VideoTrackingEvent.UNKNOWN;
        }
    }

    public final float toFloat() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return 0.25f;
        }
        if (i == 2) {
            return 0.5f;
        }
        if (i != 3) {
            return i != 4 ? 0.0f : 1.0f;
        }
        return 0.75f;
    }
}
