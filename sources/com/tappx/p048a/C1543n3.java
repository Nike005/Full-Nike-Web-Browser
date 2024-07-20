package com.tappx.p048a;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.VideoView;

/* renamed from: com.tappx.a.n3 */
public abstract class C1543n3 {

    /* renamed from: a */
    private final Context f2122a;

    /* renamed from: b */
    private final RelativeLayout f2123b = new RelativeLayout(this.f2122a);

    /* renamed from: c */
    private final C1544a f2124c;

    /* renamed from: d */
    private final C1536n2 f2125d;

    /* renamed from: com.tappx.a.n3$a */
    public interface C1544a {
        void onFinish();

        void onSetContentView(View view);
    }

    protected C1543n3(Context context, C1544a aVar) {
        this.f2122a = context;
        this.f2125d = C1552o2.m3165a(context).mo16036c();
        this.f2124c = aVar;
    }

    /* renamed from: k */
    private void m3108k() {
        if (this.f2125d.mo15995j()) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2, 83);
            this.f2123b.addView(m3109l(), layoutParams);
        }
    }

    /* renamed from: l */
    private View m3109l() {
        return C1460i3.m2854c(this.f2122a);
    }

    /* renamed from: a */
    public void mo16000a(int i, int i2, Intent intent) {
    }

    /* renamed from: a */
    public abstract void mo16001a(Configuration configuration);

    /* renamed from: a */
    public abstract void mo16002a(Bundle bundle);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo16003a(boolean z) {
        if (z) {
            this.f2124c.onFinish();
        }
    }

    /* renamed from: a */
    public boolean mo16004a() {
        return true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public C1544a mo16005b() {
        return this.f2124c;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public Context mo16007c() {
        return this.f2122a;
    }

    /* renamed from: d */
    public ViewGroup mo16008d() {
        return this.f2123b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public abstract VideoView mo16009e();

    /* renamed from: f */
    public abstract void mo16010f();

    /* renamed from: g */
    public void mo16011g() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(13);
        this.f2123b.addView(mo16009e(), 0, layoutParams);
        m3108k();
        this.f2124c.onSetContentView(this.f2123b);
    }

    /* renamed from: h */
    public abstract void mo16012h();

    /* renamed from: i */
    public abstract void mo16013i();

    /* renamed from: j */
    public abstract void mo16014j();

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo16006b(boolean z) {
        C1475j4.m2888b("Video cannot be played.");
        if (z) {
            this.f2124c.onFinish();
        }
    }
}
