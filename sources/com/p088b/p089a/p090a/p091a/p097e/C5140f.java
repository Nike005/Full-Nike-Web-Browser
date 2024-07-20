package com.p088b.p089a.p090a.p091a.p097e;

import android.os.Build;
import android.view.View;
import android.view.ViewParent;

/* renamed from: com.b.a.a.a.e.f */
public final class C5140f {
    /* renamed from: a */
    public static float m7146a(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getZ();
        }
        return 0.0f;
    }

    /* renamed from: b */
    public static View m7147b(View view) {
        ViewParent parent = view.getParent();
        if (parent instanceof View) {
            return (View) parent;
        }
        return null;
    }

    /* renamed from: c */
    public static boolean m7148c(View view) {
        if ((Build.VERSION.SDK_INT >= 19 && !view.isAttachedToWindow()) || !view.isShown()) {
            return false;
        }
        while (view != null) {
            if (view.getAlpha() == 0.0f) {
                return false;
            }
            view = m7147b(view);
        }
        return true;
    }

    /* renamed from: d */
    public static boolean m7149d(View view) {
        return (Build.VERSION.SDK_INT < 19 || view.isAttachedToWindow()) && view.getVisibility() == 0 && view.getAlpha() != 0.0f;
    }
}
