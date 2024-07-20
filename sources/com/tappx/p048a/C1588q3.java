package com.tappx.p048a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.TypedValue;
import android.view.WindowManager;

/* renamed from: com.tappx.a.q3 */
public class C1588q3 {
    /* renamed from: a */
    public static boolean m3285a(Context context, Intent intent) {
        try {
            return !context.getPackageManager().queryIntentActivities(intent, 0).isEmpty();
        } catch (NullPointerException unused) {
            return false;
        }
    }

    /* renamed from: b */
    public static int m3286b(float f, Context context) {
        return (int) (m3281a(f, context) + 0.5f);
    }

    /* renamed from: c */
    public static float m3287c(float f, Context context) {
        return f * m3282a(context);
    }

    /* renamed from: d */
    public static int m3288d(float f, Context context) {
        return (int) (m3287c(f, context) + 0.5f);
    }

    /* renamed from: e */
    public static float m3289e(float f, Context context) {
        return f / m3282a(context);
    }

    /* renamed from: f */
    public static int m3290f(float f, Context context) {
        return (int) (m3289e(f, context) + 0.5f);
    }

    /* renamed from: a */
    private static float m3282a(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    /* renamed from: a */
    public static float m3281a(float f, Context context) {
        return TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    /* renamed from: a */
    public static void m3284a(Activity activity, C1474j3 j3Var) {
        int a = m3283a(((WindowManager) activity.getSystemService("window")).getDefaultDisplay().getRotation(), activity.getResources().getConfiguration().orientation);
        int i = 8;
        if (j3Var == C1474j3.PORTRAIT) {
            i = 9 == a ? 9 : 1;
        } else if (j3Var != C1474j3.LANDSCAPE) {
            return;
        } else {
            if (8 != a) {
                i = 0;
            }
        }
        try {
            activity.setRequestedOrientation(i);
        } catch (Exception unused) {
        }
    }

    /* renamed from: a */
    static int m3283a(int i, int i2) {
        if (1 == i2) {
            return (i == 1 || i == 2) ? 9 : 1;
        }
        if (2 == i2) {
            return (i == 2 || i == 3) ? 8 : 0;
        }
        C1475j4.m2885a("Unknown screen orientation. Defaulting to portrait.");
        return 9;
    }
}
