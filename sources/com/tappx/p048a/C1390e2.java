package com.tappx.p048a;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: com.tappx.a.e2 */
public class C1390e2 {

    /* renamed from: a */
    private final Rect f1776a = new Rect();

    /* renamed from: com.tappx.a.e2$a */
    public static final class C1391a {

        /* renamed from: a */
        private final int f1777a;

        /* renamed from: b */
        private final boolean f1778b;

        C1391a(int i, boolean z) {
            if (i < 0) {
                i = 0;
            } else if (i > 100) {
                i = 100;
            }
            this.f1777a = i;
            this.f1778b = z;
        }

        /* renamed from: a */
        public int mo15739a() {
            return this.f1777a;
        }

        /* renamed from: b */
        public boolean mo15740b() {
            return this.f1778b;
        }
    }

    /* renamed from: b */
    private boolean m2559b(ViewGroup viewGroup) {
        return viewGroup.getChildCount() == 0 && viewGroup.getWidth() == 0 && viewGroup.getHeight() == 0;
    }

    /* renamed from: a */
    public C1391a mo15738a(ViewGroup viewGroup) {
        int i;
        boolean localVisibleRect = viewGroup.getLocalVisibleRect(this.f1776a);
        if (!localVisibleRect) {
            if (m2559b(viewGroup)) {
                localVisibleRect = true;
            }
            i = 0;
        } else {
            int a = m2557a((View) viewGroup, this.f1776a);
            i = viewGroup.getChildCount() > 0 ? Math.min(a, m2558a(viewGroup, this.f1776a)) : a;
        }
        return new C1391a(i, localVisibleRect);
    }

    /* renamed from: a */
    private int m2557a(View view, Rect rect) {
        float height = (float) (rect.height() * rect.width());
        float height2 = (float) (view.getHeight() * view.getWidth());
        if (height2 <= 0.0f) {
            return 0;
        }
        return (int) ((height * 100.0f) / height2);
    }

    /* renamed from: a */
    private int m2558a(ViewGroup viewGroup, Rect rect) {
        int childCount = viewGroup.getChildCount();
        int i = 100;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt.getVisibility() == 0) {
                i = Math.min(m2557a(childAt, rect), i);
            }
        }
        return i;
    }
}
