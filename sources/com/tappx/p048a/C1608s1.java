package com.tappx.p048a;

import android.content.Context;
import android.webkit.WebView;

/* renamed from: com.tappx.a.s1 */
public class C1608s1 {

    /* renamed from: a */
    private final Context f2259a;

    public C1608s1(Context context) {
        this.f2259a = context;
    }

    /* renamed from: b */
    private void m3335b() {
        try {
            new WebView(this.f2259a).destroy();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /* renamed from: a */
    public void mo16121a() {
        m3335b();
    }
}
