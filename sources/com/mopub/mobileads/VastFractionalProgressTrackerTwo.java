package com.mopub.mobileads;

import com.google.firebase.messaging.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mopub.common.Constants;
import com.mopub.mobileads.VastTrackerTwo;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u0000 \u00132\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002:\u0002\u0012\u0013B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0011\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0000H\u0002J\b\u0010\u0011\u001a\u00020\u0006H\u0016R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0014"}, mo45501d2 = {"Lcom/mopub/mobileads/VastFractionalProgressTrackerTwo;", "Lcom/mopub/mobileads/VastTrackerTwo;", "", "trackingFraction", "", "content", "", "messageType", "Lcom/mopub/mobileads/VastTrackerTwo$MessageType;", "isRepeatable", "", "(FLjava/lang/String;Lcom/mopub/mobileads/VastTrackerTwo$MessageType;Z)V", "getTrackingFraction", "()F", "compareTo", "", "other", "toString", "Builder", "Companion", "mopub-sdk-base_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
/* compiled from: VastFractionalProgressTrackerTwo.kt */
public final class VastFractionalProgressTrackerTwo extends VastTrackerTwo implements Comparable<VastFractionalProgressTrackerTwo> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Pattern percentagePattern = Pattern.compile("((\\d{1,2})|(100))%");
    private static final long serialVersionUID = 1;
    @SerializedName("tracking_fraction")
    @Expose
    private final float trackingFraction;

    public final float getTrackingFraction() {
        return this.trackingFraction;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VastFractionalProgressTrackerTwo(float f, String str, VastTrackerTwo.MessageType messageType, boolean z) {
        super(str, messageType, z);
        Intrinsics.checkParameterIsNotNull(str, Constants.VAST_TRACKER_CONTENT);
        Intrinsics.checkParameterIsNotNull(messageType, Constants.FirelogAnalytics.PARAM_MESSAGE_TYPE);
        this.trackingFraction = f;
    }

    @Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000b\u001a\u00020\fJ\t\u0010\r\u001a\u00020\u0003HÂ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÂ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nJ\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, mo45501d2 = {"Lcom/mopub/mobileads/VastFractionalProgressTrackerTwo$Builder;", "", "content", "", "trackingFraction", "", "(Ljava/lang/String;F)V", "isRepeatable", "", "messageType", "Lcom/mopub/mobileads/VastTrackerTwo$MessageType;", "build", "Lcom/mopub/mobileads/VastFractionalProgressTrackerTwo;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "mopub-sdk-base_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
    /* compiled from: VastFractionalProgressTrackerTwo.kt */
    public static final class Builder {
        private final String content;
        private boolean isRepeatable;
        private VastTrackerTwo.MessageType messageType = VastTrackerTwo.MessageType.TRACKING_URL;
        private final float trackingFraction;

        private final String component1() {
            return this.content;
        }

        private final float component2() {
            return this.trackingFraction;
        }

        public static /* synthetic */ Builder copy$default(Builder builder, String str, float f, int i, Object obj) {
            if ((i & 1) != 0) {
                str = builder.content;
            }
            if ((i & 2) != 0) {
                f = builder.trackingFraction;
            }
            return builder.copy(str, f);
        }

        public final Builder copy(String str, float f) {
            Intrinsics.checkParameterIsNotNull(str, com.mopub.common.Constants.VAST_TRACKER_CONTENT);
            return new Builder(str, f);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Builder)) {
                return false;
            }
            Builder builder = (Builder) obj;
            return Intrinsics.areEqual((Object) this.content, (Object) builder.content) && Float.compare(this.trackingFraction, builder.trackingFraction) == 0;
        }

        public int hashCode() {
            String str = this.content;
            return ((str != null ? str.hashCode() : 0) * 31) + Float.floatToIntBits(this.trackingFraction);
        }

        public String toString() {
            return "Builder(content=" + this.content + ", trackingFraction=" + this.trackingFraction + ")";
        }

        public Builder(String str, float f) {
            Intrinsics.checkParameterIsNotNull(str, com.mopub.common.Constants.VAST_TRACKER_CONTENT);
            this.content = str;
            this.trackingFraction = f;
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

        public final VastFractionalProgressTrackerTwo build() {
            return new VastFractionalProgressTrackerTwo(this.trackingFraction, this.content, this.messageType, this.isRepeatable);
        }
    }

    @Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u001f\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u000fR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\u0010"}, mo45501d2 = {"Lcom/mopub/mobileads/VastFractionalProgressTrackerTwo$Companion;", "", "()V", "percentagePattern", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "serialVersionUID", "", "isPercentageTracker", "", "progressValue", "", "parsePercentageOffset", "", "videoDuration", "(Ljava/lang/String;I)Ljava/lang/Integer;", "mopub-sdk-base_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
    /* compiled from: VastFractionalProgressTrackerTwo.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isPercentageTracker(String str) {
            CharSequence charSequence = str;
            if ((charSequence == null || charSequence.length() == 0) || !VastFractionalProgressTrackerTwo.percentagePattern.matcher(charSequence).matches()) {
                return false;
            }
            return true;
        }

        public final Integer parsePercentageOffset(String str, int i) {
            String replace$default;
            if (str == null || (replace$default = StringsKt.replace$default(str, "%", "", false, 4, (Object) null)) == null) {
                return null;
            }
            return Integer.valueOf((int) ((float) Math.rint((double) ((((float) i) * Float.parseFloat(replace$default)) / 100.0f))));
        }
    }

    public int compareTo(VastFractionalProgressTrackerTwo vastFractionalProgressTrackerTwo) {
        Intrinsics.checkParameterIsNotNull(vastFractionalProgressTrackerTwo, "other");
        return Float.compare(this.trackingFraction, vastFractionalProgressTrackerTwo.trackingFraction);
    }

    public String toString() {
        return this.trackingFraction + ": " + getContent();
    }
}
