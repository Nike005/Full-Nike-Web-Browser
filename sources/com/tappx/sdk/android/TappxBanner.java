package com.tappx.sdk.android;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.tappx.p048a.C1341c3;
import com.tappx.p048a.C1390e2;
import com.tappx.p048a.C1422g2;

public final class TappxBanner extends FrameLayout implements ITappxBanner, C1341c3.C1344c {

    /* renamed from: a */
    private C1341c3 f2628a;

    /* renamed from: b */
    private C1422g2 f2629b;

    /* renamed from: c */
    private final C1390e2 f2630c;

    /* renamed from: d */
    boolean f2631d;

    /* renamed from: e */
    boolean f2632e;

    /* renamed from: f */
    boolean f2633f;

    /* renamed from: g */
    private boolean f2634g;

    public enum AdSize {
        SMART_BANNER(-1, -1),
        BANNER_320x50(320, 50),
        BANNER_728x90(728, 90),
        BANNER_300x250(300, 250);
        

        /* renamed from: a */
        private final int f2636a;

        /* renamed from: b */
        private final int f2637b;

        private AdSize(int i, int i2) {
            this.f2636a = i;
            this.f2637b = i2;
        }

        public int getHeight() {
            return this.f2637b;
        }

        public int getWidth() {
            return this.f2636a;
        }
    }

    public TappxBanner(Context context, String str) {
        super(context);
        this.f2630c = new C1390e2();
        m3799c();
        setAppKey(str);
    }

    /* renamed from: a */
    private void m3797a(Context context, AttributeSet attributeSet) {
        boolean z = false;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1725R.styleable.TappxParams, 0, 0);
        try {
            String string = obtainStyledAttributes.getString(C1725R.styleable.TappxParams_txAppKey);
            String string2 = obtainStyledAttributes.getString(C1725R.styleable.TappxParams_txAdSize);
            if (obtainStyledAttributes.getBoolean(C1725R.styleable.TappxParams_txAutoPrivacyDisclaimer, false)) {
                Tappx.getPrivacyManager(context).setAutoPrivacyDisclaimerEnabled(true);
            }
            int i = obtainStyledAttributes.getInt(C1725R.styleable.TappxParams_txRefreshTime, -1);
            if (string != null) {
                setAppKey(string);
            }
            if (string2 != null) {
                setAdSizeFromString(string2);
            }
            if (i > 0) {
                setRefreshTimeSeconds(i);
            }
            if (string != null) {
                z = true;
            }
            this.f2631d = z;
        } catch (Exception unused) {
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    /* renamed from: b */
    private void m3798b() {
        boolean z = true;
        boolean z2 = getVisibility() == 0;
        boolean z3 = getWindowVisibility() == 0;
        boolean b = this.f2628a.mo15603b();
        boolean z4 = this.f2633f && this.f2634g && z2 && z3 && b;
        if (!this.f2633f || !z2 || !z3 || !b) {
            z = false;
        }
        if (z4 != this.f2632e) {
            this.f2632e = z4;
            this.f2629b.mo15826b(z4);
        }
        if (z) {
            m3796a();
        }
    }

    /* renamed from: c */
    private void m3799c() {
        setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        this.f2629b = C1422g2.C1425c.m2741a().mo15832a(this);
        C1341c3 a = C1341c3.C1343b.m2349a().mo15605a(getContext());
        this.f2628a = a;
        a.mo15602a((C1341c3.C1344c) this);
        setAdSize(AdSize.BANNER_320x50);
    }

    private void setAdSizeFromString(String str) {
        setAdSize(m3795a(str));
    }

    public void destroy() {
        this.f2629b.mo15770a();
        this.f2628a.mo15601a();
    }

    public void loadAd(AdRequest adRequest) {
        this.f2631d = false;
        this.f2629b.mo15773a(adRequest);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setAttachedToWindow(true);
        m3798b();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setAttachedToWindow(false);
        m3798b();
    }

    public void onDeviceScreenStateChanged(boolean z) {
        m3798b();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        C1390e2.C1391a a = this.f2630c.mo15738a(this);
        this.f2629b.mo15821a(a.mo15739a());
        setVisibleOnScreen(a.mo15740b());
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        m3798b();
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        m3798b();
    }

    public void setAdSize(AdSize adSize) {
        this.f2629b.mo15822a(adSize);
    }

    public void setAppKey(String str) {
        this.f2629b.mo15780b(str);
    }

    public void setAttachedToWindow(boolean z) {
        this.f2633f = z;
    }

    public void setEnableAutoRefresh(boolean z) {
        this.f2629b.mo15824a(z);
    }

    public void setListener(TappxBannerListener tappxBannerListener) {
        this.f2629b.mo15823a(tappxBannerListener);
    }

    public void setRefreshTimeSeconds(int i) {
        this.f2629b.mo15825b(i * 1000);
    }

    /* access modifiers changed from: package-private */
    public void setVisibleOnScreen(boolean z) {
        if (z != this.f2634g) {
            this.f2634g = z;
            m3798b();
        }
    }

    public void loadAd() {
        loadAd((AdRequest) null);
    }

    public TappxBanner(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet);
        m3799c();
        m3797a(context, attributeSet);
    }

    public TappxBanner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2630c = new C1390e2();
        m3799c();
        m3797a(context, attributeSet);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0057  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.tappx.sdk.android.TappxBanner.AdSize m3795a(java.lang.String r5) {
        /*
            r4 = this;
            java.lang.String r5 = r5.toLowerCase()
            int r0 = r5.hashCode()
            r1 = 1
            r2 = 2
            r3 = -559799608(0xffffffffdea222c8, float:-5.8415601E18)
            if (r0 == r3) goto L_0x003d
            r3 = 109549001(0x68795c9, float:5.1001445E-35)
            if (r0 == r3) goto L_0x0033
            r3 = 1507809730(0x59df59c2, float:7.8584512E15)
            if (r0 == r3) goto L_0x0029
            r3 = 1622564786(0x60b65fb2, float:1.0513134E20)
            if (r0 == r3) goto L_0x001f
            goto L_0x0047
        L_0x001f:
            java.lang.String r0 = "728x90"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0047
            r5 = 1
            goto L_0x0048
        L_0x0029:
            java.lang.String r0 = "320x50"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0047
            r5 = 0
            goto L_0x0048
        L_0x0033:
            java.lang.String r0 = "smart"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0047
            r5 = 3
            goto L_0x0048
        L_0x003d:
            java.lang.String r0 = "300x250"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0047
            r5 = 2
            goto L_0x0048
        L_0x0047:
            r5 = -1
        L_0x0048:
            if (r5 == 0) goto L_0x0057
            if (r5 == r1) goto L_0x0054
            if (r5 == r2) goto L_0x0051
            com.tappx.sdk.android.TappxBanner$AdSize r5 = com.tappx.sdk.android.TappxBanner.AdSize.SMART_BANNER
            return r5
        L_0x0051:
            com.tappx.sdk.android.TappxBanner$AdSize r5 = com.tappx.sdk.android.TappxBanner.AdSize.BANNER_300x250
            return r5
        L_0x0054:
            com.tappx.sdk.android.TappxBanner$AdSize r5 = com.tappx.sdk.android.TappxBanner.AdSize.BANNER_728x90
            return r5
        L_0x0057:
            com.tappx.sdk.android.TappxBanner$AdSize r5 = com.tappx.sdk.android.TappxBanner.AdSize.BANNER_320x50
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tappx.sdk.android.TappxBanner.m3795a(java.lang.String):com.tappx.sdk.android.TappxBanner$AdSize");
    }

    /* renamed from: a */
    private void m3796a() {
        if (this.f2631d) {
            this.f2631d = false;
            loadAd();
        }
    }
}
