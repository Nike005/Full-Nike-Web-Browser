package com.mopub.mobileads;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mopub.common.Constants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;

@Mockable
@Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0017\u0018\u0000 \u001c2\u00020\u0001:\u0003\u001c\u001d\u001eB-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u00032\b\u0010\u0016\u001a\u0004\u0018\u00010\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016R\u0016\u0010\u0006\u001a\u00020\u00078\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\n\u001a\u00020\t8\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0002\u001a\u00020\u00038\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0004\u001a\u00020\u00058\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\b\u001a\u00020\t8\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000f¨\u0006\u001f"}, mo45501d2 = {"Lcom/mopub/mobileads/VastResourceTwo;", "Ljava/io/Serializable;", "resource", "", "type", "Lcom/mopub/mobileads/VastResourceTwo$Type;", "creativeType", "Lcom/mopub/mobileads/VastResourceTwo$CreativeType;", "width", "", "height", "(Ljava/lang/String;Lcom/mopub/mobileads/VastResourceTwo$Type;Lcom/mopub/mobileads/VastResourceTwo$CreativeType;II)V", "getCreativeType", "()Lcom/mopub/mobileads/VastResourceTwo$CreativeType;", "getHeight", "()I", "getResource", "()Ljava/lang/String;", "getType", "()Lcom/mopub/mobileads/VastResourceTwo$Type;", "getWidth", "getCorrectClickThroughUrl", "vastClickThroughUrl", "webViewClickThroughUrl", "initializeWebView", "", "webView", "Lcom/mopub/mobileads/VastWebView;", "Companion", "CreativeType", "Type", "mopub-sdk-base_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
/* compiled from: VastResourceTwo.kt */
public class VastResourceTwo implements Serializable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final List<String> VALID_APPLICATION_TYPES = CollectionsKt.listOf("application/x-javascript");
    /* access modifiers changed from: private */
    public static final List<String> VALID_IMAGE_TYPES = CollectionsKt.listOf("image/jpeg", "image/png", "image/bmp", "image/gif");
    private static final long serialVersionUID = 1;
    @SerializedName("creative_type")
    @Expose
    private final CreativeType creativeType;
    @SerializedName("height")
    @Expose
    private final int height;
    @SerializedName("resource")
    @Expose
    private final String resource;
    @SerializedName("type")
    @Expose
    private final Type type;
    @SerializedName("width")
    @Expose
    private final int width;

    @Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, mo45501d2 = {"Lcom/mopub/mobileads/VastResourceTwo$CreativeType;", "", "(Ljava/lang/String;I)V", "NONE", "IMAGE", "JAVASCRIPT", "mopub-sdk-base_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
    /* compiled from: VastResourceTwo.kt */
    public enum CreativeType {
        NONE,
        IMAGE,
        JAVASCRIPT
    }

    @Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, mo45501d2 = {"Lcom/mopub/mobileads/VastResourceTwo$Type;", "", "(Ljava/lang/String;I)V", "STATIC_RESOURCE", "HTML_RESOURCE", "IFRAME_RESOURCE", "mopub-sdk-base_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
    /* compiled from: VastResourceTwo.kt */
    public enum Type {
        STATIC_RESOURCE,
        HTML_RESOURCE,
        IFRAME_RESOURCE
    }

    @JvmStatic
    public static final VastResourceTwo fromVastResourceXmlManager(VastResourceXmlManager vastResourceXmlManager, int i, int i2) {
        return Companion.fromVastResourceXmlManager(vastResourceXmlManager, i, i2);
    }

    @JvmStatic
    public static final VastResourceTwo fromVastResourceXmlManager(VastResourceXmlManager vastResourceXmlManager, Type type2, int i, int i2) {
        return Companion.fromVastResourceXmlManager(vastResourceXmlManager, type2, i, i2);
    }

    public VastResourceTwo(String str, Type type2, CreativeType creativeType2, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(str, Constants.VAST_RESOURCE);
        Intrinsics.checkParameterIsNotNull(type2, "type");
        Intrinsics.checkParameterIsNotNull(creativeType2, VastResourceXmlManager.CREATIVE_TYPE);
        this.resource = str;
        this.type = type2;
        this.creativeType = creativeType2;
        this.width = i;
        this.height = i2;
    }

    public String getResource() {
        return this.resource;
    }

    public Type getType() {
        return this.type;
    }

    public CreativeType getCreativeType() {
        return this.creativeType;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void initializeWebView(VastWebView vastWebView) {
        String str;
        Intrinsics.checkParameterIsNotNull(vastWebView, "webView");
        if (getType() == Type.HTML_RESOURCE) {
            str = getResource();
        } else if (getType() == Type.IFRAME_RESOURCE) {
            str = "<iframe frameborder=\"0\" scrolling=\"no\" marginheight=\"0\" marginwidth=\"0\" style=\"border: 0px; margin: 0px;\"" + " width=\"" + getWidth() + Typography.quote + " height=\"" + getHeight() + Typography.quote + " src=\"" + getResource() + "\"></iframe>";
        } else if (getType() == Type.STATIC_RESOURCE && getCreativeType() == CreativeType.IMAGE) {
            str = "<html>" + "<head></head><body style=\"margin:0;padding:0\"><img src=\"" + getResource() + Typography.quote + " width=\"100%\" style=\"max-width:100%;max-height:100%;\" />" + "</body></html>";
        } else if (getType() == Type.STATIC_RESOURCE && getCreativeType() == CreativeType.JAVASCRIPT) {
            str = "<script src=\"" + getResource() + "\"></script>";
        } else {
            str = null;
        }
        if (str != null) {
            vastWebView.loadData(str);
        }
    }

    public String getCorrectClickThroughUrl(String str, String str2) {
        if (!(getType() == Type.HTML_RESOURCE || getType() == Type.IFRAME_RESOURCE)) {
            if (getType() == Type.STATIC_RESOURCE && getCreativeType() == CreativeType.IMAGE) {
                return str;
            }
            if (!(getType() == Type.STATIC_RESOURCE && getCreativeType() == CreativeType.JAVASCRIPT)) {
                return null;
            }
        }
        return str2;
    }

    @Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J*\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0007J\"\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0007R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000¨\u0006\u0012"}, mo45501d2 = {"Lcom/mopub/mobileads/VastResourceTwo$Companion;", "", "()V", "VALID_APPLICATION_TYPES", "", "", "VALID_IMAGE_TYPES", "serialVersionUID", "", "fromVastResourceXmlManager", "Lcom/mopub/mobileads/VastResourceTwo;", "resourceXmlManager", "Lcom/mopub/mobileads/VastResourceXmlManager;", "type", "Lcom/mopub/mobileads/VastResourceTwo$Type;", "width", "", "height", "mopub-sdk-base_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
    /* compiled from: VastResourceTwo.kt */
    public static final class Companion {

        @Metadata(mo45499bv = {1, 0, 3}, mo45502k = 3, mo45503mv = {1, 1, 16})
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Type.values().length];
                $EnumSwitchMapping$0 = iArr;
                iArr[Type.STATIC_RESOURCE.ordinal()] = 1;
                $EnumSwitchMapping$0[Type.HTML_RESOURCE.ordinal()] = 2;
                $EnumSwitchMapping$0[Type.IFRAME_RESOURCE.ordinal()] = 3;
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final VastResourceTwo fromVastResourceXmlManager(VastResourceXmlManager vastResourceXmlManager, int i, int i2) {
            Intrinsics.checkParameterIsNotNull(vastResourceXmlManager, "resourceXmlManager");
            Type[] values = Type.values();
            Collection arrayList = new ArrayList();
            for (Type fromVastResourceXmlManager : values) {
                VastResourceTwo fromVastResourceXmlManager2 = VastResourceTwo.Companion.fromVastResourceXmlManager(vastResourceXmlManager, fromVastResourceXmlManager, i, i2);
                if (fromVastResourceXmlManager2 != null) {
                    arrayList.add(fromVastResourceXmlManager2);
                }
            }
            return (VastResourceTwo) CollectionsKt.firstOrNull((List) arrayList);
        }

        @JvmStatic
        public final VastResourceTwo fromVastResourceXmlManager(VastResourceXmlManager vastResourceXmlManager, Type type, int i, int i2) {
            Intrinsics.checkParameterIsNotNull(vastResourceXmlManager, "resourceXmlManager");
            Intrinsics.checkParameterIsNotNull(type, "type");
            String staticResourceType = vastResourceXmlManager.getStaticResourceType();
            String str = null;
            CreativeType creativeType = CreativeType.NONE;
            int i3 = WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
            boolean z = true;
            if (i3 == 1) {
                String staticResource = vastResourceXmlManager.getStaticResource();
                if (!CollectionsKt.contains(VastResourceTwo.VALID_IMAGE_TYPES, staticResourceType) && !CollectionsKt.contains(VastResourceTwo.VALID_APPLICATION_TYPES, staticResourceType)) {
                    z = false;
                }
                str = z ? staticResource : null;
                CreativeType creativeType2 = CreativeType.IMAGE;
                if (!CollectionsKt.contains(VastResourceTwo.VALID_IMAGE_TYPES, staticResourceType)) {
                    creativeType2 = null;
                }
                if (creativeType2 == null) {
                    creativeType2 = CreativeType.JAVASCRIPT;
                }
                creativeType = creativeType2;
            } else if (i3 == 2) {
                str = vastResourceXmlManager.getHTMLResource();
            } else if (i3 == 3) {
                str = vastResourceXmlManager.getIFrameResource();
            }
            String str2 = str;
            CreativeType creativeType3 = creativeType;
            if (str2 != null) {
                return new VastResourceTwo(str2, type, creativeType3, i, i2);
            }
            return null;
        }
    }
}
