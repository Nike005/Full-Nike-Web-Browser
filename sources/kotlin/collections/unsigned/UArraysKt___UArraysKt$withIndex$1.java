package kotlin.collections.unsigned;

import kotlin.Metadata;
import kotlin.UIntArray;
import kotlin.collections.UIntIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo45501d2 = {"<anonymous>", "Lkotlin/collections/UIntIterator;", "invoke"}, mo45502k = 3, mo45503mv = {1, 1, 15})
/* compiled from: _UArrays.kt */
final class UArraysKt___UArraysKt$withIndex$1 extends Lambda implements Function0<UIntIterator> {
    final /* synthetic */ int[] $this_withIndex;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    UArraysKt___UArraysKt$withIndex$1(int[] iArr) {
        super(0);
        this.$this_withIndex = iArr;
    }

    public final UIntIterator invoke() {
        return UIntArray.m7426iteratorimpl(this.$this_withIndex);
    }
}
