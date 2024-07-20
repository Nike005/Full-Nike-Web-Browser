package com.appnext.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

/* renamed from: com.appnext.core.k */
public final class C4977k {
    /* renamed from: a */
    public static void m6866a(final Context context, final ImageView imageView) {
        new Thread(new Runnable() {
            public final void run() {
                try {
                    final Bitmap Y = C4967f.m6809Y("https://cdn.appnext.com/tools/sdk/adchoices/adchoices_big.png");
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public final void run() {
                            try {
                                if (Y != null) {
                                    imageView.setImageDrawable(new BitmapDrawable(context.getResources(), Y));
                                    return;
                                }
                                imageView.setImageResource(C4935R.C4937drawable.apnxt_adchoices);
                            } catch (Throwable unused) {
                            }
                        }
                    });
                } catch (Throwable unused) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public final void run() {
                            imageView.setImageResource(C4935R.C4937drawable.apnxt_adchoices);
                        }
                    });
                }
            }
        }).start();
    }

    /* renamed from: a */
    public static boolean m6867a(AppnextAd appnextAd, C4986p pVar) {
        return appnextAd.isGdpr() && Boolean.parseBoolean(pVar.get("gdpr"));
    }
}
