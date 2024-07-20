package com.tappx.p048a;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.widget.FrameLayout;
import com.tappx.p048a.C1345c4;
import com.tappx.p048a.C1568p3;
import java.lang.ref.WeakReference;
import java.net.URI;

/* renamed from: com.tappx.a.d4 */
public class C1367d4 {

    /* renamed from: a */
    private final WeakReference<Activity> f1713a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public final Context f1714b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public final C1328b4 f1715c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public final FrameLayout f1716d;

    /* renamed from: e */
    private final C1568p3 f1717e;

    /* renamed from: f */
    private ViewGroup f1718f;

    /* renamed from: g */
    private final C1377j f1719g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public final C1545n4 f1720h;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public C1659v4 f1721i;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public C1375h f1722j;

    /* renamed from: k */
    private C1381k f1723k;

    /* renamed from: l */
    private C1602r4 f1724l;

    /* renamed from: m */
    private C1589q4 f1725m;

    /* renamed from: n */
    private C1589q4 f1726n;
    /* access modifiers changed from: private */

    /* renamed from: o */
    public final C1345c4 f1727o;
    /* access modifiers changed from: private */

    /* renamed from: p */
    public final C1345c4 f1728p;

    /* renamed from: q */
    private C1376i f1729q;

    /* renamed from: r */
    private Integer f1730r;

    /* renamed from: s */
    private boolean f1731s;

    /* renamed from: t */
    private C1512l4 f1732t;
    /* access modifiers changed from: private */

    /* renamed from: u */
    public final C1493k4 f1733u;

    /* renamed from: v */
    private boolean f1734v;
    /* access modifiers changed from: private */

    /* renamed from: w */
    public boolean f1735w;

    /* renamed from: x */
    private final C1345c4.C1353h f1736x;

    /* renamed from: y */
    private final C1345c4.C1353h f1737y;

    /* renamed from: com.tappx.a.d4$a */
    class C1368a implements C1568p3.C1574f {
        C1368a() {
        }

        /* renamed from: a */
        public void mo15700a() {
            C1367d4.this.mo15695d();
        }
    }

    /* renamed from: com.tappx.a.d4$b */
    class C1369b implements View.OnTouchListener {
        C1369b(C1367d4 d4Var) {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

    /* renamed from: com.tappx.a.d4$c */
    class C1370c implements C1345c4.C1353h {
        C1370c() {
        }

        /* renamed from: a */
        public boolean mo15641a(String str, JsResult jsResult) {
            return C1367d4.this.mo15690a(str, jsResult);
        }

        /* renamed from: b */
        public void mo15642b() {
            if (C1367d4.this.f1722j != null) {
                C1367d4.this.f1722j.mo15709d();
            }
        }

        /* renamed from: c */
        public void mo15644c() {
            C1367d4.this.mo15696e();
        }

        /* renamed from: a */
        public boolean mo15640a(ConsoleMessage consoleMessage) {
            return C1367d4.this.mo15688a(consoleMessage);
        }

        /* renamed from: a */
        public void mo15634a() {
            C1367d4.this.mo15695d();
        }

        /* renamed from: b */
        public void mo15643b(boolean z) {
            if (z) {
                C1367d4.this.m2468i();
            }
            if (!C1367d4.this.f1728p.mo15618b()) {
                C1367d4.this.f1727o.mo15614a(z);
            }
        }

        /* renamed from: a */
        public void mo15635a(int i, int i2, int i3, int i4, C1568p3.C1572d dVar, boolean z) {
            C1367d4.this.mo15681a(i, i2, i3, i4, dVar, z);
        }

        /* renamed from: a */
        public void mo15637a(URI uri, boolean z) {
            C1367d4.this.mo15685a(uri, z);
        }

        /* renamed from: a */
        public void mo15638a(boolean z) {
            C1367d4.this.mo15686a(z);
        }

        /* renamed from: a */
        public void mo15639a(boolean z, C1512l4 l4Var) {
            C1367d4.this.mo15687a(z, l4Var);
        }

        /* renamed from: a */
        public void mo15636a(URI uri) {
            if (C1367d4.this.f1722j != null) {
                C1367d4.this.f1722j.mo15707b();
            }
        }
    }

    /* renamed from: com.tappx.a.d4$d */
    class C1371d implements C1345c4.C1353h {
        C1371d() {
        }

        /* renamed from: a */
        public void mo15637a(URI uri, boolean z) {
        }

        /* renamed from: a */
        public boolean mo15641a(String str, JsResult jsResult) {
            return C1367d4.this.mo15690a(str, jsResult);
        }

        /* renamed from: b */
        public void mo15642b() {
        }

        /* renamed from: b */
        public void mo15643b(boolean z) {
            C1367d4.this.f1727o.mo15614a(z);
            C1367d4.this.f1728p.mo15614a(z);
        }

        /* renamed from: c */
        public void mo15644c() {
            C1367d4.this.mo15697f();
        }

        /* renamed from: a */
        public boolean mo15640a(ConsoleMessage consoleMessage) {
            return C1367d4.this.mo15688a(consoleMessage);
        }

        /* renamed from: a */
        public void mo15635a(int i, int i2, int i3, int i4, C1568p3.C1572d dVar, boolean z) {
            throw new C1413f4("Invalid state");
        }

        /* renamed from: a */
        public void mo15634a() {
            C1367d4.this.mo15695d();
        }

        /* renamed from: a */
        public void mo15638a(boolean z) {
            C1367d4.this.mo15686a(z);
        }

        /* renamed from: a */
        public void mo15639a(boolean z, C1512l4 l4Var) {
            C1367d4.this.mo15687a(z, l4Var);
        }

        /* renamed from: a */
        public void mo15636a(URI uri) {
            if (C1367d4.this.f1722j != null) {
                C1367d4.this.f1722j.mo15707b();
            }
        }
    }

    /* renamed from: com.tappx.a.d4$e */
    class C1372e implements Runnable {
        C1372e() {
        }

        public void run() {
            C1367d4.this.f1727o.mo15615a(C1367d4.this.f1733u.mo15916b(C1367d4.this.f1714b), C1367d4.this.f1733u.mo15918d(C1367d4.this.f1714b), C1367d4.this.f1733u.mo15914a(C1367d4.this.f1714b), C1367d4.this.f1733u.mo15917c(C1367d4.this.f1714b), C1367d4.this.m2475m());
            C1367d4.this.f1727o.mo15608a(C1367d4.this.f1715c);
            C1367d4.this.f1727o.mo15614a(C1367d4.this.f1727o.mo15622d());
            C1367d4.this.f1727o.mo15623e();
        }
    }

    /* renamed from: com.tappx.a.d4$f */
    class C1373f implements Runnable {
        C1373f() {
        }

        public void run() {
            C1367d4.this.f1728p.mo15615a(C1367d4.this.f1733u.mo15916b(C1367d4.this.f1714b), C1367d4.this.f1733u.mo15918d(C1367d4.this.f1714b), C1367d4.this.f1733u.mo15914a(C1367d4.this.f1714b), C1367d4.this.f1733u.mo15917c(C1367d4.this.f1714b), C1367d4.this.m2475m());
            C1367d4.this.f1728p.mo15613a(C1367d4.this.f1721i);
            C1367d4.this.f1728p.mo15608a(C1367d4.this.f1715c);
            C1367d4.this.f1728p.mo15614a(C1367d4.this.f1728p.mo15622d());
            C1367d4.this.f1728p.mo15623e();
        }
    }

    /* renamed from: com.tappx.a.d4$g */
    class C1374g implements Runnable {

        /* renamed from: a */
        final /* synthetic */ View f1743a;

        /* renamed from: b */
        final /* synthetic */ Runnable f1744b;

        C1374g(View view, Runnable runnable) {
            this.f1743a = view;
            this.f1744b = runnable;
        }

        public void run() {
            DisplayMetrics displayMetrics = C1367d4.this.f1714b.getResources().getDisplayMetrics();
            C1367d4.this.f1720h.mo16018a(displayMetrics.widthPixels, displayMetrics.heightPixels);
            int[] iArr = new int[2];
            ViewGroup f = C1367d4.this.m2473l();
            f.getLocationOnScreen(iArr);
            C1367d4.this.f1720h.mo16023c(iArr[0], iArr[1], f.getWidth(), f.getHeight());
            C1367d4.this.f1716d.getLocationOnScreen(iArr);
            C1367d4.this.f1720h.mo16021b(iArr[0], iArr[1], C1367d4.this.f1716d.getWidth(), C1367d4.this.f1716d.getHeight());
            this.f1743a.getLocationOnScreen(iArr);
            C1367d4.this.f1720h.mo16019a(iArr[0], iArr[1], this.f1743a.getWidth(), this.f1743a.getHeight());
            C1367d4.this.f1727o.mo15611a(C1367d4.this.f1720h);
            if (C1367d4.this.f1728p.mo15618b()) {
                C1367d4.this.f1728p.mo15611a(C1367d4.this.f1720h);
            }
            Runnable runnable = this.f1744b;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    /* renamed from: com.tappx.a.d4$h */
    public interface C1375h {
        /* renamed from: a */
        void mo15705a();

        /* renamed from: a */
        void mo15706a(View view);

        /* renamed from: b */
        void mo15707b();

        /* renamed from: c */
        void mo15708c();

        /* renamed from: d */
        void mo15709d();
    }

    /* renamed from: com.tappx.a.d4$j */
    static class C1377j {

        /* renamed from: a */
        private final Handler f1749a = new Handler(Looper.getMainLooper());

        /* renamed from: b */
        private C1378a f1750b;

        /* renamed from: com.tappx.a.d4$j$a */
        static class C1378a {
            /* access modifiers changed from: private */

            /* renamed from: a */
            public final View[] f1751a;

            /* renamed from: b */
            private final Handler f1752b;

            /* renamed from: c */
            private Runnable f1753c;

            /* renamed from: d */
            int f1754d;

            /* renamed from: e */
            private final Runnable f1755e;

            /* renamed from: com.tappx.a.d4$j$a$a */
            class C1379a implements Runnable {

                /* renamed from: com.tappx.a.d4$j$a$a$a */
                class C1380a implements ViewTreeObserver.OnPreDrawListener {

                    /* renamed from: a */
                    final /* synthetic */ View f1757a;

                    C1380a(View view) {
                        this.f1757a = view;
                    }

                    public boolean onPreDraw() {
                        this.f1757a.getViewTreeObserver().removeOnPreDrawListener(this);
                        C1378a.this.m2532b();
                        return true;
                    }
                }

                C1379a() {
                }

                public void run() {
                    for (View view : C1378a.this.f1751a) {
                        if (view.getHeight() > 0 || view.getWidth() > 0) {
                            C1378a.this.m2532b();
                        } else {
                            view.getViewTreeObserver().addOnPreDrawListener(new C1380a(view));
                        }
                    }
                }
            }

            /* synthetic */ C1378a(Handler handler, View[] viewArr, C1368a aVar) {
                this(handler, viewArr);
            }

            private C1378a(Handler handler, View[] viewArr) {
                this.f1755e = new C1379a();
                this.f1752b = handler;
                this.f1751a = viewArr;
            }

            /* access modifiers changed from: private */
            /* renamed from: b */
            public void m2532b() {
                Runnable runnable;
                int i = this.f1754d - 1;
                this.f1754d = i;
                if (i == 0 && (runnable = this.f1753c) != null) {
                    runnable.run();
                    this.f1753c = null;
                }
            }

            /* access modifiers changed from: package-private */
            /* renamed from: a */
            public void mo15716a(Runnable runnable) {
                this.f1753c = runnable;
                this.f1754d = this.f1751a.length;
                this.f1752b.post(this.f1755e);
            }

            /* access modifiers changed from: package-private */
            /* renamed from: a */
            public void mo15715a() {
                this.f1752b.removeCallbacks(this.f1755e);
                this.f1753c = null;
            }
        }

        C1377j() {
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public C1378a mo15713a(View... viewArr) {
            C1378a aVar = new C1378a(this.f1749a, viewArr, (C1368a) null);
            this.f1750b = aVar;
            return aVar;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo15714a() {
            C1378a aVar = this.f1750b;
            if (aVar != null) {
                aVar.mo15715a();
                this.f1750b = null;
            }
        }
    }

    /* renamed from: com.tappx.a.d4$k */
    public interface C1381k {
        /* renamed from: a */
        void mo15719a(boolean z);
    }

    public C1367d4(Context context, C1328b4 b4Var) {
        this(context, b4Var, new C1345c4(b4Var), new C1345c4(C1328b4.INTERSTITIAL), new C1377j());
    }

    C1367d4(Context context, C1328b4 b4Var, C1345c4 c4Var, C1345c4 c4Var2, C1377j jVar) {
        this.f1721i = C1659v4.LOADING;
        this.f1729q = new C1376i();
        this.f1731s = true;
        this.f1732t = C1512l4.NONE;
        this.f1735w = false;
        this.f1736x = new C1370c();
        this.f1737y = new C1371d();
        this.f1714b = context.getApplicationContext();
        if (context instanceof Activity) {
            this.f1713a = new WeakReference<>((Activity) context);
        } else {
            this.f1713a = new WeakReference<>((Object) null);
        }
        this.f1715c = b4Var;
        this.f1727o = c4Var;
        this.f1728p = c4Var2;
        this.f1719g = jVar;
        this.f1721i = C1659v4.LOADING;
        this.f1720h = new C1545n4(this.f1714b, this.f1714b.getResources().getDisplayMetrics().density);
        this.f1716d = new FrameLayout(this.f1714b);
        C1568p3 p3Var = new C1568p3(this.f1714b);
        this.f1717e = p3Var;
        p3Var.setCloseListener(new C1368a());
        View view = new View(this.f1714b);
        view.setOnTouchListener(new C1369b(this));
        this.f1717e.addView(view, new FrameLayout.LayoutParams(-1, -1));
        this.f1729q.mo15711a(this.f1714b);
        this.f1727o.mo15609a(this.f1736x);
        this.f1728p.mo15609a(this.f1737y);
        this.f1733u = new C1493k4();
    }

    /* access modifiers changed from: private */
    /* renamed from: i */
    public void m2468i() {
        if (this.f1735w) {
            this.f1735w = false;
            m2458a((Runnable) null);
        }
    }

    /* renamed from: j */
    private View m2469j() {
        return this.f1728p.mo15618b() ? this.f1726n : this.f1725m;
    }

    /* access modifiers changed from: private */
    /* renamed from: k */
    public int m2471k() {
        return ((WindowManager) this.f1714b.getSystemService("window")).getDefaultDisplay().getRotation();
    }

    /* access modifiers changed from: private */
    /* renamed from: l */
    public ViewGroup m2473l() {
        ViewGroup viewGroup = this.f1718f;
        if (viewGroup != null) {
            return viewGroup;
        }
        View a = C1676w4.m3549a((Context) this.f1713a.get(), this.f1716d);
        return a instanceof ViewGroup ? (ViewGroup) a : this.f1716d;
    }

    /* access modifiers changed from: private */
    /* renamed from: m */
    public boolean m2475m() {
        Activity activity = (Activity) this.f1713a.get();
        if (activity == null || m2469j() == null) {
            return false;
        }
        return this.f1733u.mo15913a(activity, m2469j());
    }

    /* renamed from: b */
    public void mo15693b(boolean z) {
        this.f1734v = true;
        C1589q4 q4Var = this.f1725m;
        if (q4Var != null) {
            C1692x4.m3611a(q4Var, z);
        }
        C1589q4 q4Var2 = this.f1726n;
        if (q4Var2 != null) {
            C1692x4.m3611a(q4Var2, z);
        }
    }

    /* renamed from: c */
    public FrameLayout mo15694c() {
        return this.f1716d;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo15695d() {
        C1659v4 v4Var;
        C1589q4 q4Var;
        if (this.f1725m != null && (v4Var = this.f1721i) != C1659v4.LOADING && v4Var != C1659v4.HIDDEN) {
            if (v4Var == C1659v4.EXPANDED || this.f1715c == C1328b4.INTERSTITIAL) {
                mo15699h();
            }
            C1659v4 v4Var2 = this.f1721i;
            if (v4Var2 == C1659v4.RESIZED || v4Var2 == C1659v4.EXPANDED) {
                if (!this.f1728p.mo15618b() || (q4Var = this.f1726n) == null) {
                    this.f1717e.removeView(this.f1725m);
                    this.f1716d.addView(this.f1725m, new FrameLayout.LayoutParams(-1, -1));
                    this.f1716d.setVisibility(0);
                } else {
                    this.f1717e.removeView(q4Var);
                    this.f1728p.mo15607a();
                }
                m2473l().removeView(this.f1717e);
                m2456a(C1659v4.DEFAULT);
            } else if (v4Var2 == C1659v4.DEFAULT) {
                this.f1716d.setVisibility(4);
                m2456a(C1659v4.HIDDEN);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public void mo15696e() {
        m2457a(C1659v4.DEFAULT, (Runnable) new C1372e());
        C1375h hVar = this.f1722j;
        if (hVar != null) {
            hVar.mo15706a(this.f1716d);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public void mo15697f() {
        m2458a((Runnable) new C1373f());
    }

    /* renamed from: g */
    public void mo15698g() {
        this.f1734v = false;
        C1589q4 q4Var = this.f1725m;
        if (q4Var != null) {
            C1692x4.m3610a(q4Var);
        }
        C1589q4 q4Var2 = this.f1726n;
        if (q4Var2 != null) {
            C1692x4.m3610a(q4Var2);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: h */
    public void mo15699h() {
        Integer num;
        Activity activity = (Activity) this.f1713a.get();
        if (!(activity == null || (num = this.f1730r) == null)) {
            try {
                activity.setRequestedOrientation(num.intValue());
            } catch (Exception unused) {
            }
        }
        this.f1730r = null;
    }

    /* renamed from: com.tappx.a.d4$i */
    class C1376i extends BroadcastReceiver {

        /* renamed from: a */
        private Context f1746a;

        /* renamed from: b */
        private int f1747b = -1;

        C1376i() {
        }

        /* renamed from: a */
        public void mo15711a(Context context) {
            Context applicationContext = context.getApplicationContext();
            this.f1746a = applicationContext;
            if (applicationContext != null) {
                applicationContext.registerReceiver(this, new IntentFilter("android.intent.action.CONFIGURATION_CHANGED"));
            }
        }

        public void onReceive(Context context, Intent intent) {
            int h;
            if (this.f1746a != null && "android.intent.action.CONFIGURATION_CHANGED".equals(intent.getAction()) && (h = C1367d4.this.m2471k()) != this.f1747b) {
                boolean unused = C1367d4.this.f1735w = true;
                this.f1747b = h;
                C1367d4.this.mo15680a(h);
            }
        }

        /* renamed from: a */
        public void mo15710a() {
            Context context = this.f1746a;
            if (context != null) {
                context.unregisterReceiver(this);
                this.f1746a = null;
            }
        }
    }

    /* renamed from: a */
    public void mo15682a(C1375h hVar) {
        this.f1722j = hVar;
    }

    /* renamed from: a */
    public void mo15683a(C1381k kVar) {
        this.f1723k = kVar;
    }

    /* renamed from: a */
    public void mo15684a(String str) {
        C1589q4 q4Var = new C1589q4(this.f1714b);
        this.f1725m = q4Var;
        this.f1727o.mo15612a(q4Var);
        this.f1716d.addView(this.f1725m, new FrameLayout.LayoutParams(-1, -1));
        this.f1727o.mo15619c(str);
    }

    /* renamed from: b */
    public void mo15691b() {
        this.f1719g.mo15714a();
        try {
            this.f1729q.mo15710a();
        } catch (IllegalArgumentException e) {
            if (!e.getMessage().contains("Receiver not registered")) {
                throw e;
            }
        }
        if (!this.f1734v) {
            mo15693b(true);
        }
        C1676w4.m3552b(this.f1717e);
        this.f1727o.mo15607a();
        C1589q4 q4Var = this.f1725m;
        if (q4Var != null) {
            q4Var.destroy();
            this.f1725m = null;
        }
        this.f1728p.mo15607a();
        C1589q4 q4Var2 = this.f1726n;
        if (q4Var2 != null) {
            q4Var2.destroy();
            this.f1726n = null;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo15688a(ConsoleMessage consoleMessage) {
        C1602r4 r4Var = this.f1724l;
        if (r4Var != null) {
            return r4Var.mo16118a(consoleMessage);
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo15690a(String str, JsResult jsResult) {
        C1602r4 r4Var = this.f1724l;
        if (r4Var != null) {
            return r4Var.mo16119a(str, jsResult);
        }
        jsResult.confirm();
        return true;
    }

    /* renamed from: a */
    private void m2458a(Runnable runnable) {
        this.f1719g.mo15714a();
        View j = m2469j();
        if (j != null) {
            this.f1719g.mo15713a(this.f1716d, j).mo15716a((Runnable) new C1374g(j, runnable));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo15680a(int i) {
        m2458a((Runnable) null);
    }

    /* renamed from: a */
    private void m2456a(C1659v4 v4Var) {
        m2457a(v4Var, (Runnable) null);
    }

    /* renamed from: a */
    private void m2457a(C1659v4 v4Var, Runnable runnable) {
        C1475j4.m2885a("MRAID state set to " + v4Var);
        C1659v4 v4Var2 = this.f1721i;
        this.f1721i = v4Var;
        this.f1727o.mo15613a(v4Var);
        if (this.f1728p.mo15620c()) {
            this.f1728p.mo15613a(v4Var);
        }
        C1375h hVar = this.f1722j;
        if (hVar != null) {
            C1659v4 v4Var3 = C1659v4.EXPANDED;
            if (v4Var == v4Var3) {
                hVar.mo15708c();
            } else if (v4Var2 == v4Var3 && v4Var == C1659v4.DEFAULT) {
                hVar.mo15705a();
            } else if (v4Var == C1659v4.HIDDEN) {
                this.f1722j.mo15705a();
            }
        }
        m2458a(runnable);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo15692b(int i) {
        Activity activity = (Activity) this.f1713a.get();
        if (activity == null || !mo15689a(this.f1732t)) {
            throw new C1413f4("Invalid vale: " + this.f1732t.name());
        }
        if (this.f1730r == null) {
            this.f1730r = Integer.valueOf(activity.getRequestedOrientation());
        }
        try {
            activity.setRequestedOrientation(i);
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public int mo15678a(int i, int i2, int i3) {
        return Math.max(i, Math.min(i2, i3));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo15681a(int i, int i2, int i3, int i4, C1568p3.C1572d dVar, boolean z) {
        if (this.f1725m != null) {
            C1659v4 v4Var = this.f1721i;
            if (v4Var != C1659v4.LOADING && v4Var != C1659v4.HIDDEN) {
                if (v4Var != C1659v4.EXPANDED) {
                    C1328b4 b4Var = C1328b4.INTERSTITIAL;
                    int d = C1588q3.m3288d((float) i, this.f1714b);
                    int d2 = C1588q3.m3288d((float) i2, this.f1714b);
                    int d3 = C1588q3.m3288d((float) i3, this.f1714b);
                    int d4 = C1588q3.m3288d((float) i4, this.f1714b);
                    int i5 = this.f1720h.mo16022c().left + d3;
                    int i6 = this.f1720h.mo16022c().top + d4;
                    Rect rect = new Rect(i5, i6, d + i5, d2 + i6);
                    if (!z) {
                        Rect e = this.f1720h.mo16025e();
                        if (rect.width() > e.width() || rect.height() > e.height()) {
                            throw new C1413f4("Resize invalid)");
                        }
                        rect.offsetTo(mo15678a(e.left, rect.left, e.right - rect.width()), mo15678a(e.top, rect.top, e.bottom - rect.height()));
                    }
                    this.f1717e.setInvisibleClose(true);
                    this.f1717e.setClosePosition(dVar);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(rect.width(), rect.height());
                    layoutParams.leftMargin = rect.left - this.f1720h.mo16025e().left;
                    layoutParams.topMargin = rect.top - this.f1720h.mo16025e().top;
                    C1659v4 v4Var2 = this.f1721i;
                    if (v4Var2 == C1659v4.DEFAULT) {
                        this.f1716d.removeView(this.f1725m);
                        this.f1716d.setVisibility(4);
                        this.f1717e.mo16055a((View) this.f1725m, new FrameLayout.LayoutParams(-1, -1));
                        m2473l().addView(this.f1717e, layoutParams);
                    } else if (v4Var2 == C1659v4.RESIZED) {
                        this.f1717e.setLayoutParams(layoutParams);
                    }
                    this.f1717e.setClosePosition(dVar);
                    m2456a(C1659v4.RESIZED);
                    return;
                }
                throw new C1413f4("Invalid status change");
            }
            return;
        }
        throw new C1413f4("View destroyed, ignoring");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo15685a(URI uri, boolean z) {
        if (this.f1725m != null) {
            C1328b4 b4Var = C1328b4.INTERSTITIAL;
            C1659v4 v4Var = this.f1721i;
            if (v4Var == C1659v4.DEFAULT || v4Var == C1659v4.RESIZED) {
                mo15679a();
                boolean z2 = uri != null;
                if (z2) {
                    C1589q4 q4Var = new C1589q4(this.f1714b);
                    this.f1726n = q4Var;
                    this.f1728p.mo15612a(q4Var);
                    this.f1728p.mo15621d(uri.toString());
                }
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
                C1659v4 v4Var2 = this.f1721i;
                if (v4Var2 == C1659v4.DEFAULT) {
                    if (z2) {
                        this.f1717e.mo16055a((View) this.f1726n, layoutParams);
                    } else {
                        this.f1716d.removeView(this.f1725m);
                        this.f1716d.setVisibility(4);
                        this.f1717e.mo16055a((View) this.f1725m, layoutParams);
                    }
                    m2473l().addView(this.f1717e, new FrameLayout.LayoutParams(-1, -1));
                } else if (v4Var2 == C1659v4.RESIZED && z2) {
                    this.f1717e.removeView(this.f1725m);
                    this.f1716d.addView(this.f1725m, layoutParams);
                    this.f1716d.setVisibility(4);
                    this.f1717e.mo16055a((View) this.f1726n, layoutParams);
                }
                this.f1717e.setLayoutParams(layoutParams);
                mo15686a(z);
                m2456a(C1659v4.EXPANDED);
                return;
            }
            return;
        }
        throw new C1413f4("View destroyed, ignoring");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo15679a() {
        C1512l4 l4Var = this.f1732t;
        if (l4Var != C1512l4.NONE) {
            mo15692b(l4Var.mo15945a());
        } else if (this.f1731s) {
            mo15699h();
        } else {
            Activity activity = (Activity) this.f1713a.get();
            if (activity != null) {
                mo15692b(m2454a(activity));
                return;
            }
            throw new C1413f4("Context is not an Activity");
        }
    }

    /* renamed from: a */
    public static int m2454a(Activity activity) {
        return C1588q3.m3283a(activity.getWindowManager().getDefaultDisplay().getRotation(), activity.getResources().getConfiguration().orientation);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo15689a(C1512l4 l4Var) {
        if (l4Var == C1512l4.NONE) {
            return true;
        }
        Activity activity = (Activity) this.f1713a.get();
        if (activity == null) {
            return false;
        }
        try {
            ActivityInfo activityInfo = activity.getPackageManager().getActivityInfo(new ComponentName(activity, activity.getClass()), 0);
            int i = activityInfo.screenOrientation;
            if (i == -1) {
                boolean a = C1646u4.m3481a(activityInfo.configChanges, 128);
                if (Build.VERSION.SDK_INT < 13) {
                    return a;
                }
                if (!a || !C1646u4.m3481a(activityInfo.configChanges, 1024)) {
                    return false;
                }
                return true;
            } else if (i == l4Var.mo15945a()) {
                return true;
            } else {
                return false;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo15686a(boolean z) {
        if (z != (!this.f1717e.mo16057b())) {
            this.f1717e.setCloseEnabled(!z);
            C1381k kVar = this.f1723k;
            if (kVar != null) {
                kVar.mo15719a(z);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo15687a(boolean z, C1512l4 l4Var) {
        if (mo15689a(l4Var)) {
            this.f1731s = z;
            this.f1732t = l4Var;
            if (this.f1721i == C1659v4.EXPANDED || this.f1715c == C1328b4.INTERSTITIAL) {
                mo15679a();
                return;
            }
            return;
        }
        throw new C1413f4("Unable to force orientation to " + l4Var);
    }
}
