package com.truenet.android.p049a;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.util.concurrent.SynchronousQueue;
import p055a.p056a.p058b.p060b.C2928h;

/* renamed from: com.truenet.android.a.i */
/* compiled from: StartAppSDK */
public final class C1754i {

    /* renamed from: a */
    public static final C1755a f2667a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public static final String f2668b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public static String f2669c;

    /* renamed from: com.truenet.android.a.i$a */
    /* compiled from: StartAppSDK */
    public static final class C1755a {
        private C1755a() {
        }

        public /* synthetic */ C1755a(C2925e eVar) {
            this();
        }

        /* renamed from: a */
        public final String mo16520a(Context context) {
            C2928h.m6157b(context, "context");
            if (C1754i.f2669c != null) {
                String a = C1754i.f2669c;
                if (a == null) {
                    C2928h.m6153a();
                }
                return a;
            }
            SynchronousQueue synchronousQueue = new SynchronousQueue();
            new Handler(Looper.getMainLooper()).post(new C1756a(context, synchronousQueue));
            C1754i.f2669c = (String) synchronousQueue.take();
            String a2 = C1754i.f2669c;
            if (a2 == null) {
                C2928h.m6153a();
            }
            return a2;
        }

        /* renamed from: com.truenet.android.a.i$a$a */
        /* compiled from: StartAppSDK */
        static final class C1756a implements Runnable {

            /* renamed from: a */
            final /* synthetic */ Context f2670a;

            /* renamed from: b */
            final /* synthetic */ SynchronousQueue f2671b;

            C1756a(Context context, SynchronousQueue synchronousQueue) {
                this.f2670a = context;
                this.f2671b = synchronousQueue;
            }

            public final void run() {
                try {
                    WebSettings settings = new WebView(this.f2670a).getSettings();
                    C2928h.m6154a((Object) settings, "WebView(context).settings");
                    this.f2671b.offer(settings.getUserAgentString());
                } catch (Exception e) {
                    Log.e(C1754i.f2668b, e.getMessage());
                    this.f2671b.offer("undefined");
                }
            }
        }
    }

    static {
        C1755a aVar = new C1755a((C2925e) null);
        f2667a = aVar;
        f2668b = aVar.getClass().getSimpleName();
    }
}
