package com.startapp.android.publish.ads.banner.banner3d;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowManager;
import com.mopub.mobileads.MoPubView;
import com.startapp.android.publish.ads.banner.Banner;
import com.startapp.android.publish.ads.banner.BannerOptions;
import com.startapp.android.publish.ads.banner.C0902d;
import com.startapp.android.publish.adsCommon.Utils.C1060h;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.common.p043a.C1270g;

/* compiled from: StartAppSDK */
public class Banner3DSize {

    /* compiled from: StartAppSDK */
    public enum Size {
        XXSMALL(new C0902d(MoPubView.MoPubAdSizeInt.HEIGHT_280_INT, 50)),
        XSMALL(new C0902d(300, 50)),
        SMALL(new C0902d(320, 50)),
        MEDIUM(new C0902d(468, 60)),
        LARGE(new C0902d(728, 90)),
        XLARGE(new C0902d(1024, 90));
        
        private C0902d size;

        private Size(C0902d dVar) {
            this.size = dVar;
        }

        public C0902d getSize() {
            return this.size;
        }
    }

    /* renamed from: a */
    public static boolean m608a(Context context, ViewParent viewParent, BannerOptions bannerOptions, Banner3D banner3D, C0902d dVar) {
        C1270g.m2078a("Banner3DSize", 3, "============== Optimize Size ==========");
        C0902d a = m606a(context, viewParent, bannerOptions, banner3D);
        dVar.mo13977a(a.mo13975a(), a.mo13978b());
        boolean z = false;
        for (Size size : Size.values()) {
            if (size.getSize().mo13975a() <= a.mo13975a() && size.getSize().mo13978b() <= a.mo13978b()) {
                C1270g.m2078a("Banner3DSize", 3, "BannerSize [" + size.getSize().mo13975a() + "," + size.getSize().mo13978b() + "]");
                bannerOptions.mo13878a(size.getSize().mo13975a(), size.getSize().mo13978b());
                z = true;
            }
        }
        if (!z) {
            bannerOptions.mo13878a(0, 0);
        }
        C1270g.m2078a("Banner3DSize", 3, "============== Optimize Size [" + z + "] ==========");
        return z;
    }

    /* renamed from: a */
    private static C0902d m606a(Context context, ViewParent viewParent, BannerOptions bannerOptions, Banner3D banner3D) {
        Point point = new Point();
        point.x = bannerOptions.mo13881d();
        point.y = bannerOptions.mo13882e();
        C1270g.m2078a("Banner3DSize", 3, "=============== set Application Size ===========");
        if (banner3D.getLayoutParams() != null && banner3D.getLayoutParams().width > 0) {
            point.x = C1060h.m1171b(context, banner3D.getLayoutParams().width + 1);
        }
        if (banner3D.getLayoutParams() != null && banner3D.getLayoutParams().height > 0) {
            point.y = C1060h.m1171b(context, banner3D.getLayoutParams().height + 1);
        }
        if (banner3D.getLayoutParams() == null || banner3D.getLayoutParams().width <= 0 || banner3D.getLayoutParams().height <= 0) {
            if (context instanceof Activity) {
                C1270g.m2078a("Banner3DSize", 3, "Context is Activity");
                View decorView = ((Activity) context).getWindow().getDecorView();
                try {
                    View view = (View) viewParent;
                    if (view instanceof Banner) {
                        C1270g.m2078a("Banner3DSize", 3, "Parent is instance of Wrapper Banner");
                        view = (View) view.getParent();
                    }
                    boolean z = false;
                    boolean z2 = false;
                    while (view != null && (view.getMeasuredWidth() <= 0 || view.getMeasuredHeight() <= 0)) {
                        if (view.getMeasuredWidth() > 0 && !z) {
                            m609b(context, point, view);
                            z = true;
                        }
                        if (view.getMeasuredHeight() > 0 && !z2) {
                            m607a(context, point, view);
                            z2 = true;
                        }
                        view = (View) view.getParent();
                    }
                    if (view == null) {
                        m610c(context, point, decorView);
                    } else {
                        if (!z) {
                            m609b(context, point, view);
                        }
                        if (!z2) {
                            m607a(context, point, view);
                        }
                    }
                } catch (Exception unused) {
                    m610c(context, point, decorView);
                    C1270g.m2078a("Banner3DSize", 3, "Exception occoured");
                }
            } else {
                C1270g.m2078a("Banner3DSize", 3, "Context not Activity, get max win size");
                try {
                    WindowManager windowManager = (WindowManager) context.getSystemService("window");
                    if (windowManager != null) {
                        C1060h.m1168a(context, windowManager, point);
                    }
                } catch (Exception e) {
                    C1132f.m1527a(context, C1130d.EXCEPTION, "Banner3DSize.getApplicationSize - system service failed", e.getMessage(), "");
                }
            }
        }
        C1270g.m2078a("Banner3DSize", 3, "============ exit Application Size [" + point.x + "," + point.y + "] =========");
        return new C0902d(point.x, point.y);
    }

    /* renamed from: a */
    private static void m607a(Context context, Point point, View view) {
        point.y = C1060h.m1171b(context, (view.getMeasuredHeight() - view.getPaddingBottom()) - view.getPaddingTop());
    }

    /* renamed from: b */
    private static void m609b(Context context, Point point, View view) {
        point.x = C1060h.m1171b(context, (view.getMeasuredWidth() - view.getPaddingLeft()) - view.getPaddingRight());
    }

    /* renamed from: c */
    private static void m610c(Context context, Point point, View view) {
        point.x = C1060h.m1171b(context, view.getMeasuredWidth());
        point.y = C1060h.m1171b(context, view.getMeasuredHeight());
    }
}
