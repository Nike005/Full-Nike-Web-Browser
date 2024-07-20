package kotlin.text;

import kotlin.Metadata;
import kotlin.collections.CharIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo45501d2 = {"<anonymous>", "Lkotlin/collections/CharIterator;", "invoke"}, mo45502k = 3, mo45503mv = {1, 1, 15})
/* compiled from: _Strings.kt */
final class StringsKt___StringsKt$withIndex$1 extends Lambda implements Function0<CharIterator> {
    final /* synthetic */ CharSequence $this_withIndex;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StringsKt___StringsKt$withIndex$1(CharSequence charSequence) {
        super(0);
        this.$this_withIndex = charSequence;
    }

    public final CharIterator invoke() {
        return StringsKt.iterator(this.$this_withIndex);
    }
}
