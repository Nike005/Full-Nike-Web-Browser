package com.mopub.mobileads;

import com.google.firebase.messaging.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mopub.common.Constants;
import com.mopub.mobileads.VastTrackerTwo;
import java.util.List;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\u0016\u0018\u0000 \u00122\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002:\u0002\u0011\u0012B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0011\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0000H\u0002J\b\u0010\u0010\u001a\u00020\u0006H\u0016R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0013"}, mo45501d2 = {"Lcom/mopub/mobileads/VastAbsoluteProgressTrackerTwo;", "Lcom/mopub/mobileads/VastTrackerTwo;", "", "trackingMilliseconds", "", "content", "", "messageType", "Lcom/mopub/mobileads/VastTrackerTwo$MessageType;", "isRepeatable", "", "(ILjava/lang/String;Lcom/mopub/mobileads/VastTrackerTwo$MessageType;Z)V", "getTrackingMilliseconds", "()I", "compareTo", "other", "toString", "Builder", "Companion", "mopub-sdk-base_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
/* compiled from: VastAbsoluteProgressTrackerTwo.kt */
public class VastAbsoluteProgressTrackerTwo extends VastTrackerTwo implements Comparable<VastAbsoluteProgressTrackerTwo> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Pattern absolutePattern = Pattern.compile("\\d{2}:\\d{2}:\\d{2}(.\\d{3})?");
    private static final long serialVersionUID = 1;
    @SerializedName("tracking_ms")
    @Expose
    private final int trackingMilliseconds;

    public final int getTrackingMilliseconds() {
        return this.trackingMilliseconds;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VastAbsoluteProgressTrackerTwo(int i, String str, VastTrackerTwo.MessageType messageType, boolean z) {
        super(str, messageType, z);
        Intrinsics.checkParameterIsNotNull(str, Constants.VAST_TRACKER_CONTENT);
        Intrinsics.checkParameterIsNotNull(messageType, Constants.FirelogAnalytics.PARAM_MESSAGE_TYPE);
        this.trackingMilliseconds = i;
    }

    @Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000b\u001a\u00020\fJ\t\u0010\r\u001a\u00020\u0003HÂ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÂ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nJ\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, mo45501d2 = {"Lcom/mopub/mobileads/VastAbsoluteProgressTrackerTwo$Builder;", "", "content", "", "trackingMilliseconds", "", "(Ljava/lang/String;I)V", "isRepeatable", "", "messageType", "Lcom/mopub/mobileads/VastTrackerTwo$MessageType;", "build", "Lcom/mopub/mobileads/VastAbsoluteProgressTrackerTwo;", "component1", "component2", "copy", "equals", "other", "hashCode", "toString", "mopub-sdk-base_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
    /* compiled from: VastAbsoluteProgressTrackerTwo.kt */
    public static final class Builder {
        private final String content;
        private boolean isRepeatable;
        private VastTrackerTwo.MessageType messageType = VastTrackerTwo.MessageType.TRACKING_URL;
        private final int trackingMilliseconds;

        private final String component1() {
            return this.content;
        }

        private final int component2() {
            return this.trackingMilliseconds;
        }

        public static /* synthetic */ Builder copy$default(Builder builder, String str, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = builder.content;
            }
            if ((i2 & 2) != 0) {
                i = builder.trackingMilliseconds;
            }
            return builder.copy(str, i);
        }

        public final Builder copy(String str, int i) {
            Intrinsics.checkParameterIsNotNull(str, com.mopub.common.Constants.VAST_TRACKER_CONTENT);
            return new Builder(str, i);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Builder)) {
                return false;
            }
            Builder builder = (Builder) obj;
            return Intrinsics.areEqual((Object) this.content, (Object) builder.content) && this.trackingMilliseconds == builder.trackingMilliseconds;
        }

        public int hashCode() {
            String str = this.content;
            return ((str != null ? str.hashCode() : 0) * 31) + this.trackingMilliseconds;
        }

        public String toString() {
            return "Builder(content=" + this.content + ", trackingMilliseconds=" + this.trackingMilliseconds + ")";
        }

        public Builder(String str, int i) {
            Intrinsics.checkParameterIsNotNull(str, com.mopub.common.Constants.VAST_TRACKER_CONTENT);
            this.content = str;
            this.trackingMilliseconds = i;
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

        public final VastAbsoluteProgressTrackerTwo build() {
            return new VastAbsoluteProgressTrackerTwo(this.trackingMilliseconds, this.content, this.messageType, this.isRepeatable);
        }
    }

    @Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u0017\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\u000eR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\u000f"}, mo45501d2 = {"Lcom/mopub/mobileads/VastAbsoluteProgressTrackerTwo$Companion;", "", "()V", "absolutePattern", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "serialVersionUID", "", "isAbsoluteTracker", "", "progressValue", "", "parseAbsoluteOffset", "", "(Ljava/lang/String;)Ljava/lang/Integer;", "mopub-sdk-base_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
    /* compiled from: VastAbsoluteProgressTrackerTwo.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isAbsoluteTracker(String str) {
            CharSequence charSequence = str;
            if ((charSequence == null || charSequence.length() == 0) || !VastAbsoluteProgressTrackerTwo.absolutePattern.matcher(charSequence).matches()) {
                return false;
            }
            return true;
        }

        public final Integer parseAbsoluteOffset(String str) {
            List split$default;
            if (str == null || (split$default = StringsKt.split$default((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null)) == null) {
                return null;
            }
            if (!(split$default.size() == 3)) {
                split$default = null;
            }
            if (split$default == null) {
                return null;
            }
            Integer.parseInt((String) split$default.get(0));
            Integer.parseInt((String) split$default.get(1));
            return Integer.valueOf((int) (Float.parseFloat((String) split$default.get(2)) * ((float) 1000)));
        }
    }

    public int compareTo(VastAbsoluteProgressTrackerTwo vastAbsoluteProgressTrackerTwo) {
        Intrinsics.checkParameterIsNotNull(vastAbsoluteProgressTrackerTwo, "other");
        return Intrinsics.compare(this.trackingMilliseconds, vastAbsoluteProgressTrackerTwo.trackingMilliseconds);
    }

    public String toString() {
        return this.trackingMilliseconds + "ms: " + getContent();
    }
}
