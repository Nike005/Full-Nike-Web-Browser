package com.tappx.p048a;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.tappx.p048a.C1553o3;

/* renamed from: com.tappx.a.x3 */
public class C1687x3 extends C1510l3 {
    /* access modifiers changed from: private */

    /* renamed from: d */
    public boolean f2474d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public final C1553o3 f2475e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public C1691d f2476f;

    /* renamed from: g */
    private final C1553o3.C1554a f2477g = new C1689b();

    /* renamed from: com.tappx.a.x3$a */
    class C1688a implements View.OnTouchListener {
        C1688a() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            C1687x3.this.f2475e.mo16037a(motionEvent);
            int action = motionEvent.getAction();
            if ((action != 0 && action != 1) || view.hasFocus()) {
                return false;
            }
            view.requestFocus();
            return false;
        }
    }

    /* renamed from: com.tappx.a.x3$b */
    class C1689b implements C1553o3.C1554a {
        C1689b() {
        }

        /* renamed from: a */
        public void mo15627a() {
            boolean unused = C1687x3.this.f2474d = true;
        }
    }

    /* renamed from: com.tappx.a.x3$d */
    public interface C1691d {
        /* renamed from: a */
        void mo16214a();

        /* renamed from: b */
        void mo16215b();

        /* renamed from: c */
        void mo16216c();
    }

    public C1687x3(Context context, boolean z) {
        super(context);
        if (!z) {
            mo16264c();
        }
        getSettings().setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= 14) {
            mo15940a(true);
        }
        setBackgroundColor(0);
        C1553o3 o3Var = new C1553o3();
        this.f2475e = o3Var;
        o3Var.mo16038a(this.f2477g);
        setWebViewClient(new C1690c(this, (C1688a) null));
        setOnTouchListener(new C1688a());
    }

    public void setListener(C1691d dVar) {
        this.f2476f = dVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo16264c() {
        setHorizontalScrollBarEnabled(false);
        setHorizontalScrollbarOverlay(false);
        setVerticalScrollBarEnabled(false);
        setVerticalScrollbarOverlay(false);
        getSettings().setSupportZoom(false);
    }

    /* renamed from: com.tappx.a.x3$c */
    private final class C1690c extends WebViewClient {

        /* renamed from: a */
        private final C1628t4 f2480a;

        private C1690c() {
            this.f2480a = new C1628t4();
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (this.f2480a.mo16184a(str, C1687x3.this.f2476f)) {
                return true;
            }
            if (C1687x3.this.f2474d) {
                boolean unused = C1687x3.this.f2474d = false;
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                intent.addFlags(268435456);
                try {
                    C1687x3.this.getContext().startActivity(intent);
                    if (C1687x3.this.f2476f != null) {
                        C1687x3.this.f2476f.mo16215b();
                    }
                    return true;
                } catch (ActivityNotFoundException unused2) {
                    C1475j4.m2885a("No activity found to handle this URL " + str);
                }
            }
            return false;
        }

        /* synthetic */ C1690c(C1687x3 x3Var, C1688a aVar) {
            this();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16263a(String str) {
        loadDataWithBaseURL((String) null, str, "text/html", "utf-8", (String) null);
    }
}
