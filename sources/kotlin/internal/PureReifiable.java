package kotlin.internal;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.Retention;

@Target({})
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.TYPE_PARAMETER})
@Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, mo45501d2 = {"Lkotlin/internal/PureReifiable;", "", "kotlin-stdlib"}, mo45502k = 1, mo45503mv = {1, 1, 15})
@Retention(AnnotationRetention.BINARY)
@java.lang.annotation.Retention(RetentionPolicy.CLASS)
/* compiled from: InternalAnnotations.kt */
public @interface PureReifiable {
}
