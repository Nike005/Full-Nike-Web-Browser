package androidx.core.graphics;

import android.graphics.ImageDecoder;
import com.google.firebase.messaging.Constants;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u00062\u000e\u0010\u0007\u001a\n \u0004*\u0004\u0018\u00010\b0\bH\n¢\u0006\u0002\b\t"}, mo45501d2 = {"<anonymous>", "", "decoder", "Landroid/graphics/ImageDecoder;", "kotlin.jvm.PlatformType", "info", "Landroid/graphics/ImageDecoder$ImageInfo;", "source", "Landroid/graphics/ImageDecoder$Source;", "onHeaderDecoded"}, mo45502k = 3, mo45503mv = {1, 1, 15})
/* compiled from: ImageDecoder.kt */
public final class ImageDecoderKt$decodeDrawable$1 implements ImageDecoder.OnHeaderDecodedListener {
    final /* synthetic */ Function3 $action;

    public ImageDecoderKt$decodeDrawable$1(Function3 function3) {
        this.$action = function3;
    }

    public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
        Function3 function3 = this.$action;
        Intrinsics.checkExpressionValueIsNotNull(imageDecoder, "decoder");
        Intrinsics.checkExpressionValueIsNotNull(imageInfo, "info");
        Intrinsics.checkExpressionValueIsNotNull(source, Constants.ScionAnalytics.PARAM_SOURCE);
        function3.invoke(imageDecoder, imageInfo, source);
    }
}
