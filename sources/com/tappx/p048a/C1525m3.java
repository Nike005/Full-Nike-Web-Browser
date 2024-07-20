package com.tappx.p048a;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import com.google.android.gms.common.ConnectionResult;
import java.util.Random;

/* renamed from: com.tappx.a.m3 */
public class C1525m3 {

    /* renamed from: a */
    private static final C1492k3[] f2080a = {C1492k3.FROM_LEFT, C1492k3.FROM_RIGHT, C1492k3.FROM_LEFT_BOUNCE, C1492k3.FROM_RIGHT_BOUNCE};

    /* renamed from: b */
    private static final Random f2081b = new Random();

    /* renamed from: com.tappx.a.m3$a */
    static /* synthetic */ class C1526a {

        /* renamed from: a */
        static final /* synthetic */ int[] f2082a;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.tappx.a.k3[] r0 = com.tappx.p048a.C1492k3.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f2082a = r0
                com.tappx.a.k3 r1 = com.tappx.p048a.C1492k3.FROM_LEFT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f2082a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.tappx.a.k3 r1 = com.tappx.p048a.C1492k3.FROM_LEFT_BOUNCE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f2082a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.tappx.a.k3 r1 = com.tappx.p048a.C1492k3.FROM_RIGHT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f2082a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.tappx.a.k3 r1 = com.tappx.p048a.C1492k3.FROM_RIGHT_BOUNCE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tappx.p048a.C1525m3.C1526a.<clinit>():void");
        }
    }

    /* renamed from: a */
    public static Animation m3034a(C1492k3 k3Var) {
        if (k3Var == C1492k3.RANDOM) {
            k3Var = m3035a();
        }
        int i = C1526a.f2082a[k3Var.ordinal()];
        if (i == 1) {
            return m3033a(new AccelerateInterpolator(), 800);
        }
        if (i == 2) {
            return m3033a(new C1308a(), ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
        }
        if (i == 3) {
            return m3036b(new AccelerateInterpolator(), 800);
        }
        if (i != 4) {
            return null;
        }
        return m3036b(new C1308a(), ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
    }

    /* renamed from: b */
    private static Animation m3036b(Interpolator interpolator, int i) {
        TranslateAnimation translateAnimation = new TranslateAnimation(2, 1.0f, 2, 0.0f, 2, 0.0f, 2, 0.0f);
        translateAnimation.setDuration((long) i);
        translateAnimation.setInterpolator(interpolator);
        return translateAnimation;
    }

    /* renamed from: a */
    static C1492k3 m3035a() {
        C1492k3[] k3VarArr = f2080a;
        return k3VarArr[f2081b.nextInt(k3VarArr.length)];
    }

    /* renamed from: a */
    private static Animation m3033a(Interpolator interpolator, int i) {
        TranslateAnimation translateAnimation = new TranslateAnimation(2, -1.0f, 2, 0.0f, 2, 0.0f, 2, 0.0f);
        translateAnimation.setDuration((long) i);
        translateAnimation.setInterpolator(interpolator);
        return translateAnimation;
    }
}
