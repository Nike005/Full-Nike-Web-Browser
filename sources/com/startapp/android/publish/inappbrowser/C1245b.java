package com.startapp.android.publish.inappbrowser;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.startapp.android.publish.adsCommon.Utils.C1060h;
import com.startapp.common.p043a.C1270g;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.startapp.android.publish.inappbrowser.b */
/* compiled from: StartAppSDK */
public class C1245b extends RelativeLayout {

    /* renamed from: p */
    private static final int f1447p = Color.rgb(78, 86, 101);

    /* renamed from: q */
    private static final int f1448q = Color.rgb(148, 155, 166);

    /* renamed from: a */
    private RelativeLayout f1449a;

    /* renamed from: b */
    private ImageView f1450b;

    /* renamed from: c */
    private ImageView f1451c;

    /* renamed from: d */
    private ImageView f1452d;

    /* renamed from: e */
    private ImageView f1453e;

    /* renamed from: f */
    private Bitmap f1454f;

    /* renamed from: g */
    private Bitmap f1455g;

    /* renamed from: h */
    private Bitmap f1456h;

    /* renamed from: i */
    private Bitmap f1457i;

    /* renamed from: j */
    private Bitmap f1458j;

    /* renamed from: k */
    private Bitmap f1459k;

    /* renamed from: l */
    private TextView f1460l;

    /* renamed from: m */
    private TextView f1461m;

    /* renamed from: n */
    private Boolean f1462n = false;

    /* renamed from: o */
    private Map<String, C1246c> f1463o;

    public C1245b(Context context) {
        super(context);
    }

    /* renamed from: a */
    public void mo15421a() {
        setDescendantFocusability(262144);
        setBackgroundColor(Color.parseColor("#e9e9e9"));
        setLayoutParams(new RelativeLayout.LayoutParams(-1, C1060h.m1162a(getContext(), 60)));
        setId(RemoteMediaPlayer.STATUS_CANCELED);
        this.f1463o = m1957d();
    }

    /* renamed from: d */
    private Map<String, C1246c> m1957d() {
        HashMap hashMap = new HashMap();
        hashMap.put("BACK", new C1246c(this.f1456h, 14, 22, "back_.png"));
        hashMap.put("BACK_DARK", new C1246c(this.f1458j, 14, 22, "back_dark.png"));
        hashMap.put("FORWARD", new C1246c(this.f1457i, 14, 22, "forward_.png"));
        hashMap.put("FORWARD_DARK", new C1246c(this.f1459k, 14, 22, "forward_dark.png"));
        hashMap.put("X", new C1246c(this.f1455g, 23, 23, "x_dark.png"));
        hashMap.put("BROWSER", new C1246c(this.f1454f, 28, 28, "browser_icon_dark.png"));
        return hashMap;
    }

    /* renamed from: b */
    public void mo15423b() {
        Typeface typeface = Typeface.DEFAULT;
        this.f1460l = C1060h.m1167a(getContext(), this.f1460l, typeface, 1, 16.46f, f1447p, RemoteMediaPlayer.STATUS_TIMED_OUT);
        this.f1461m = C1060h.m1167a(getContext(), this.f1460l, typeface, 1, 12.12f, f1448q, 2107);
        this.f1460l.setText("Loading...");
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        this.f1449a = relativeLayout;
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        this.f1449a.addView(this.f1460l, C1060h.m1165a(getContext(), new int[]{0, 0, 0, 0}, new int[0]));
        this.f1449a.addView(this.f1461m, C1060h.m1166a(getContext(), new int[]{0, 0, 0, 0}, new int[0], 3, RemoteMediaPlayer.STATUS_TIMED_OUT));
        m1958e();
        this.f1450b = C1060h.m1164a(getContext(), this.f1450b, this.f1463o.get("X").mo15433d(), 2103);
        this.f1452d = C1060h.m1164a(getContext(), this.f1452d, this.f1463o.get("BROWSER").mo15433d(), 2104);
        this.f1453e = C1060h.m1164a(getContext(), this.f1453e, this.f1463o.get("BACK").mo15433d(), 2105);
        this.f1451c = C1060h.m1164a(getContext(), this.f1451c, this.f1463o.get("FORWARD").mo15433d(), 2106);
        int a = C1060h.m1162a(getContext(), 10);
        this.f1451c.setPadding(a, a, a, a);
        this.f1453e.setPadding(a, a, a, a);
        addView(this.f1450b, C1060h.m1165a(getContext(), new int[]{0, 0, 16, 0}, new int[]{15, 11}));
        addView(this.f1452d, C1060h.m1166a(getContext(), new int[]{0, 0, 17, 0}, new int[]{15}, 0, 2103));
        addView(this.f1449a, C1060h.m1166a(getContext(), new int[]{16, 6, 16, 0}, new int[]{9}, 0, 2104));
    }

    /* renamed from: e */
    private void m1958e() {
        for (C1246c next : this.f1463o.values()) {
            Bitmap a = C1060h.m1163a(getContext(), next.mo15432c());
            if (a == null) {
                C1270g.m2078a("NavigationBarLayout", 6, "Error getting navigation bar bitmap - " + next.mo15432c() + " from resources ");
            } else {
                next.mo15430a(Bitmap.createScaledBitmap(a, C1060h.m1162a(getContext(), next.mo15429a()), C1060h.m1162a(getContext(), next.mo15431b()), true));
            }
        }
    }

    /* renamed from: a */
    public void mo15422a(WebView webView) {
        if (this.f1462n.booleanValue()) {
            mo15424b(webView);
        } else if (webView.canGoBack()) {
            m1959f();
            this.f1462n = true;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo15424b(WebView webView) {
        if (webView.canGoBack()) {
            this.f1453e.setImageBitmap(this.f1463o.get("BACK_DARK").mo15433d());
        } else {
            this.f1453e.setImageBitmap(this.f1463o.get("BACK").mo15433d());
        }
        if (webView.canGoForward()) {
            this.f1451c.setImageBitmap(this.f1463o.get("FORWARD_DARK").mo15433d());
        } else {
            this.f1451c.setImageBitmap(this.f1463o.get("FORWARD").mo15433d());
        }
        if (webView.getTitle() != null) {
            this.f1460l.setText(webView.getTitle());
        }
    }

    public TextView getUrlTxt() {
        return this.f1461m;
    }

    public TextView getTitleTxt() {
        return this.f1460l;
    }

    public void setButtonsListener(View.OnClickListener onClickListener) {
        this.f1450b.setOnClickListener(onClickListener);
        this.f1453e.setOnClickListener(onClickListener);
        this.f1451c.setOnClickListener(onClickListener);
        this.f1452d.setOnClickListener(onClickListener);
    }

    /* renamed from: f */
    private void m1959f() {
        this.f1453e.setImageBitmap(this.f1463o.get("BACK_DARK").mo15433d());
        addView(this.f1453e, C1060h.m1165a(getContext(), new int[]{6, 0, 0, 0}, new int[]{15, 9}));
        addView(this.f1451c, C1060h.m1166a(getContext(), new int[]{9, 0, 0, 0}, new int[]{15}, 1, 2105));
        removeView(this.f1449a);
        this.f1449a.removeView(this.f1461m);
        this.f1449a.removeView(this.f1460l);
        this.f1449a.addView(this.f1460l, C1060h.m1165a(getContext(), new int[]{0, 0, 0, 0}, new int[]{14}));
        this.f1449a.addView(this.f1461m, C1060h.m1166a(getContext(), new int[]{0, 0, 0, 0}, new int[]{14}, 3, RemoteMediaPlayer.STATUS_TIMED_OUT));
        RelativeLayout.LayoutParams a = C1060h.m1166a(getContext(), new int[]{16, 0, 16, 0}, new int[]{15}, 1, 2106);
        a.addRule(0, 2104);
        addView(this.f1449a, a);
    }

    /* renamed from: c */
    public void mo15425c() {
        if (Build.VERSION.SDK_INT < 11) {
            ((BitmapDrawable) this.f1450b.getDrawable()).getBitmap().recycle();
            ((BitmapDrawable) this.f1452d.getDrawable()).getBitmap().recycle();
            ((BitmapDrawable) this.f1453e.getDrawable()).getBitmap().recycle();
            ((BitmapDrawable) this.f1451c.getDrawable()).getBitmap().recycle();
        }
        this.f1463o = null;
        this.f1456h = null;
        this.f1458j = null;
        this.f1457i = null;
        this.f1459k = null;
    }
}
