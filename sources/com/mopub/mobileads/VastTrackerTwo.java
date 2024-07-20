package com.mopub.mobileads;

import com.google.firebase.messaging.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mopub.common.Constants;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 \u00132\u00020\u0001:\u0003\u0012\u0013\u0014B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u0010\u001a\u00020\u0011R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000bR\u001e\u0010\r\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0015"}, mo45501d2 = {"Lcom/mopub/mobileads/VastTrackerTwo;", "Ljava/io/Serializable;", "content", "", "messageType", "Lcom/mopub/mobileads/VastTrackerTwo$MessageType;", "isRepeatable", "", "(Ljava/lang/String;Lcom/mopub/mobileads/VastTrackerTwo$MessageType;Z)V", "getContent", "()Ljava/lang/String;", "()Z", "<set-?>", "isTracked", "getMessageType", "()Lcom/mopub/mobileads/VastTrackerTwo$MessageType;", "setTracked", "", "Builder", "Companion", "MessageType", "mopub-sdk-base_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
/* compiled from: VastTrackerTwo.kt */
public class VastTrackerTwo implements Serializable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final long serialVersionUID = 3;
    @SerializedName("content")
    @Expose
    private final String content;
    @SerializedName("is_repeatable")
    @Expose
    private final boolean isRepeatable;
    private boolean isTracked;
    @SerializedName("message_type")
    @Expose
    private final MessageType messageType;

    @Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, mo45501d2 = {"Lcom/mopub/mobileads/VastTrackerTwo$MessageType;", "", "(Ljava/lang/String;I)V", "TRACKING_URL", "QUARTILE_EVENT", "mopub-sdk-base_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
    /* compiled from: VastTrackerTwo.kt */
    public enum MessageType {
        TRACKING_URL,
        QUARTILE_EVENT
    }

    public VastTrackerTwo(String str, MessageType messageType2, boolean z) {
        Intrinsics.checkParameterIsNotNull(str, Constants.VAST_TRACKER_CONTENT);
        Intrinsics.checkParameterIsNotNull(messageType2, Constants.FirelogAnalytics.PARAM_MESSAGE_TYPE);
        this.content = str;
        this.messageType = messageType2;
        this.isRepeatable = z;
    }

    public final String getContent() {
        return this.content;
    }

    public final MessageType getMessageType() {
        return this.messageType;
    }

    public final boolean isRepeatable() {
        return this.isRepeatable;
    }

    @Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\nJ\t\u0010\u000b\u001a\u00020\u0003HÂ\u0003J\u0013\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u000e\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, mo45501d2 = {"Lcom/mopub/mobileads/VastTrackerTwo$Builder;", "", "content", "", "(Ljava/lang/String;)V", "isRepeatable", "", "messageType", "Lcom/mopub/mobileads/VastTrackerTwo$MessageType;", "build", "Lcom/mopub/mobileads/VastTrackerTwo;", "component1", "copy", "equals", "other", "hashCode", "", "toString", "mopub-sdk-base_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
    /* compiled from: VastTrackerTwo.kt */
    public static final class Builder {
        private final String content;
        private boolean isRepeatable;
        private MessageType messageType = MessageType.TRACKING_URL;

        private final String component1() {
            return this.content;
        }

        public static /* synthetic */ Builder copy$default(Builder builder, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = builder.content;
            }
            return builder.copy(str);
        }

        public final Builder copy(String str) {
            Intrinsics.checkParameterIsNotNull(str, com.mopub.common.Constants.VAST_TRACKER_CONTENT);
            return new Builder(str);
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                return (obj instanceof Builder) && Intrinsics.areEqual((Object) this.content, (Object) ((Builder) obj).content);
            }
            return true;
        }

        public int hashCode() {
            String str = this.content;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "Builder(content=" + this.content + ")";
        }

        public Builder(String str) {
            Intrinsics.checkParameterIsNotNull(str, com.mopub.common.Constants.VAST_TRACKER_CONTENT);
            this.content = str;
        }

        public final Builder messageType(MessageType messageType2) {
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

        public final VastTrackerTwo build() {
            return new VastTrackerTwo(this.content, this.messageType, this.isRepeatable);
        }
    }

    @Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo45501d2 = {"Lcom/mopub/mobileads/VastTrackerTwo$Companion;", "", "()V", "serialVersionUID", "", "mopub-sdk-base_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
    /* compiled from: VastTrackerTwo.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final boolean isTracked() {
        return this.isTracked;
    }

    public final void setTracked() {
        this.isTracked = true;
    }
}
