package com.mopub.mobileads;

import com.google.firebase.messaging.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mopub.common.Constants;
import com.mopub.mobileads.VastTrackerTwo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0016\u0018\u0000 \u00102\u00020\u0001:\u0002\u000f\u0010B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u0011"}, mo45501d2 = {"Lcom/mopub/mobileads/VideoViewabilityTrackerTwo;", "Lcom/mopub/mobileads/VastTrackerTwo;", "viewablePlaytimeMS", "", "percentViewable", "content", "", "messageType", "Lcom/mopub/mobileads/VastTrackerTwo$MessageType;", "isRepeatable", "", "(IILjava/lang/String;Lcom/mopub/mobileads/VastTrackerTwo$MessageType;Z)V", "getPercentViewable", "()I", "getViewablePlaytimeMS", "Builder", "Companion", "mopub-sdk-base_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
/* compiled from: VideoViewabilityTrackerTwo.kt */
public class VideoViewabilityTrackerTwo extends VastTrackerTwo {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final long serialVersionUID = 1;
    @SerializedName("percent_viewable")
    @Expose
    private final int percentViewable;
    @SerializedName("playtime_ms")
    @Expose
    private final int viewablePlaytimeMS;

    public final int getViewablePlaytimeMS() {
        return this.viewablePlaytimeMS;
    }

    public final int getPercentViewable() {
        return this.percentViewable;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VideoViewabilityTrackerTwo(int i, int i2, String str, VastTrackerTwo.MessageType messageType, boolean z) {
        super(str, messageType, z);
        Intrinsics.checkParameterIsNotNull(str, Constants.VAST_TRACKER_CONTENT);
        Intrinsics.checkParameterIsNotNull(messageType, Constants.FirelogAnalytics.PARAM_MESSAGE_TYPE);
        this.viewablePlaytimeMS = i;
        this.percentViewable = i2;
    }

    @Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u0006\u0010\u000f\u001a\u00020\u0010J\t\u0010\u0011\u001a\u00020\u0003HÂ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J'\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\t2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bJ\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u0019"}, mo45501d2 = {"Lcom/mopub/mobileads/VideoViewabilityTrackerTwo$Builder;", "", "content", "", "viewablePlaytimeMS", "", "percentViewable", "(Ljava/lang/String;II)V", "isRepeatable", "", "messageType", "Lcom/mopub/mobileads/VastTrackerTwo$MessageType;", "getPercentViewable", "()I", "getViewablePlaytimeMS", "build", "Lcom/mopub/mobileads/VideoViewabilityTrackerTwo;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "mopub-sdk-base_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
    /* compiled from: VideoViewabilityTrackerTwo.kt */
    public static final class Builder {
        private final String content;
        private boolean isRepeatable;
        private VastTrackerTwo.MessageType messageType = VastTrackerTwo.MessageType.TRACKING_URL;
        private final int percentViewable;
        private final int viewablePlaytimeMS;

        private final String component1() {
            return this.content;
        }

        public static /* synthetic */ Builder copy$default(Builder builder, String str, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = builder.content;
            }
            if ((i3 & 2) != 0) {
                i = builder.viewablePlaytimeMS;
            }
            if ((i3 & 4) != 0) {
                i2 = builder.percentViewable;
            }
            return builder.copy(str, i, i2);
        }

        public final int component2() {
            return this.viewablePlaytimeMS;
        }

        public final int component3() {
            return this.percentViewable;
        }

        public final Builder copy(String str, int i, int i2) {
            Intrinsics.checkParameterIsNotNull(str, com.mopub.common.Constants.VAST_TRACKER_CONTENT);
            return new Builder(str, i, i2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Builder)) {
                return false;
            }
            Builder builder = (Builder) obj;
            return Intrinsics.areEqual((Object) this.content, (Object) builder.content) && this.viewablePlaytimeMS == builder.viewablePlaytimeMS && this.percentViewable == builder.percentViewable;
        }

        public int hashCode() {
            String str = this.content;
            return ((((str != null ? str.hashCode() : 0) * 31) + this.viewablePlaytimeMS) * 31) + this.percentViewable;
        }

        public String toString() {
            return "Builder(content=" + this.content + ", viewablePlaytimeMS=" + this.viewablePlaytimeMS + ", percentViewable=" + this.percentViewable + ")";
        }

        public Builder(String str, int i, int i2) {
            Intrinsics.checkParameterIsNotNull(str, com.mopub.common.Constants.VAST_TRACKER_CONTENT);
            this.content = str;
            this.viewablePlaytimeMS = i;
            this.percentViewable = i2;
        }

        public final int getViewablePlaytimeMS() {
            return this.viewablePlaytimeMS;
        }

        public final int getPercentViewable() {
            return this.percentViewable;
        }

        public final Builder messageType(VastTrackerTwo.MessageType messageType2) {
            Intrinsics.checkParameterIsNotNull(messageType2, Constants.FirelogAnalytics.PARAM_MESSAGE_TYPE);
            Builder builder = this;
            builder.messageType = messageType2;
            return builder;
        }

        public final Builder isRepeatable(boolean z) {
            Builder builder = this;
            builder.isRepeatable = z;
            return builder;
        }

        public final VideoViewabilityTrackerTwo build() {
            return new VideoViewabilityTrackerTwo(this.viewablePlaytimeMS, this.percentViewable, this.content, this.messageType, this.isRepeatable);
        }
    }

    @Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo45501d2 = {"Lcom/mopub/mobileads/VideoViewabilityTrackerTwo$Companion;", "", "()V", "serialVersionUID", "", "mopub-sdk-base_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
    /* compiled from: VideoViewabilityTrackerTwo.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
