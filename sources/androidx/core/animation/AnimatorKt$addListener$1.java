package androidx.core.animation;

import android.animation.Animator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, mo45501d2 = {"<anonymous>", "", "it", "Landroid/animation/Animator;", "invoke"}, mo45502k = 3, mo45503mv = {1, 1, 15})
/* compiled from: Animator.kt */
public final class AnimatorKt$addListener$1 extends Lambda implements Function1<Animator, Unit> {
    public static final AnimatorKt$addListener$1 INSTANCE = new AnimatorKt$addListener$1();

    public AnimatorKt$addListener$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Animator) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Animator animator) {
        Intrinsics.checkParameterIsNotNull(animator, "it");
    }
}
