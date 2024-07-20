package com.startapp.android.publish.ads.list3d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.startapp.android.publish.adsCommon.C1098b;
import com.startapp.android.publish.adsCommon.Utils.C1060h;
import com.startapp.android.publish.common.metaData.MetaDataStyle;
import com.startapp.android.publish.p016a.C0848b;
import com.startapp.common.p043a.C1261c;

/* renamed from: com.startapp.android.publish.ads.list3d.d */
/* compiled from: StartAppSDK */
public class C0923d {

    /* renamed from: a */
    private RelativeLayout f590a;

    /* renamed from: b */
    private ImageView f591b;

    /* renamed from: c */
    private TextView f592c;

    /* renamed from: d */
    private TextView f593d;

    /* renamed from: e */
    private TextView f594e;

    /* renamed from: f */
    private C0848b f595f;

    /* renamed from: g */
    private MetaDataStyle f596g = null;

    public C0923d(Context context) {
        Context context2 = context;
        context2.setTheme(16973829);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -2);
        this.f590a = new RelativeLayout(context2);
        this.f590a.setBackgroundDrawable(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{C1098b.m1303a().mo14774n(), C1098b.m1303a().mo14775o()}));
        this.f590a.setLayoutParams(layoutParams);
        int a = C1060h.m1162a(context2, 3);
        int a2 = C1060h.m1162a(context2, 4);
        int a3 = C1060h.m1162a(context2, 5);
        int a4 = C1060h.m1162a(context2, 6);
        int a5 = C1060h.m1162a(context2, 10);
        int a6 = C1060h.m1162a(context2, 84);
        this.f590a.setPadding(a5, a, a5, a);
        this.f590a.setTag(this);
        ImageView imageView = new ImageView(context2);
        this.f591b = imageView;
        imageView.setId(1);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(a6, a6);
        layoutParams2.addRule(15);
        this.f591b.setLayoutParams(layoutParams2);
        this.f591b.setPadding(0, 0, a4, 0);
        TextView textView = new TextView(context2);
        this.f592c = textView;
        textView.setId(2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(C1261c.m2011a(17), 1);
        layoutParams3.addRule(6, 1);
        this.f592c.setLayoutParams(layoutParams3);
        this.f592c.setPadding(0, 0, 0, a3);
        this.f592c.setTextColor(C1098b.m1303a().mo14777q().intValue());
        this.f592c.setTextSize((float) C1098b.m1303a().mo14776p().intValue());
        this.f592c.setSingleLine(true);
        this.f592c.setEllipsize(TextUtils.TruncateAt.END);
        C1060h.m1170a(this.f592c, C1098b.m1303a().mo14778r());
        TextView textView2 = new TextView(context2);
        this.f593d = textView2;
        textView2.setId(3);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams4.addRule(C1261c.m2011a(17), 1);
        layoutParams4.addRule(3, 2);
        layoutParams4.setMargins(0, 0, 0, a3);
        this.f593d.setLayoutParams(layoutParams4);
        this.f593d.setTextColor(C1098b.m1303a().mo14780t().intValue());
        this.f593d.setTextSize((float) C1098b.m1303a().mo14779s().intValue());
        this.f593d.setSingleLine(true);
        this.f593d.setEllipsize(TextUtils.TruncateAt.END);
        C1060h.m1170a(this.f593d, C1098b.m1303a().mo14781u());
        this.f595f = new C0848b(context2);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams5.addRule(C1261c.m2011a(17), 1);
        layoutParams5.addRule(8, 1);
        layoutParams5.setMargins(0, 0, 0, -a3);
        this.f595f.setLayoutParams(layoutParams5);
        this.f595f.setPadding(0, 0, 0, a2);
        this.f595f.setId(5);
        this.f594e = new TextView(context2);
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams6.addRule(C1261c.m2011a(21));
        layoutParams6.addRule(8, 1);
        this.f594e.setLayoutParams(layoutParams6);
        mo14080a(false);
        this.f594e.setTextColor(-1);
        this.f594e.setTextSize(12.0f);
        this.f594e.setTypeface((Typeface) null, 1);
        this.f594e.setPadding(a5, a4, a5, a4);
        this.f594e.setId(4);
        this.f594e.setShadowLayer(2.5f, -3.0f, 3.0f, -9013642);
        this.f594e.setBackgroundDrawable(new ShapeDrawable(new RoundRectShape(new float[]{10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f}, (RectF) null, (float[]) null)) {
            /* access modifiers changed from: protected */
            public void onDraw(Shape shape, Canvas canvas, Paint paint) {
                paint.setColor(-11363070);
                paint.setMaskFilter(new EmbossMaskFilter(new float[]{1.0f, 1.0f, 1.0f}, 0.4f, 5.0f, 3.0f));
                super.onDraw(shape, canvas, paint);
            }
        });
        this.f590a.addView(this.f591b);
        this.f590a.addView(this.f592c);
        this.f590a.addView(this.f593d);
        this.f590a.addView(this.f595f);
        this.f590a.addView(this.f594e);
    }

    /* renamed from: a */
    public RelativeLayout mo14078a() {
        return this.f590a;
    }

    /* renamed from: b */
    public ImageView mo14081b() {
        return this.f591b;
    }

    /* renamed from: c */
    public TextView mo14082c() {
        return this.f592c;
    }

    /* renamed from: d */
    public TextView mo14083d() {
        return this.f593d;
    }

    /* renamed from: e */
    public C0848b mo14084e() {
        return this.f595f;
    }

    /* renamed from: a */
    public void mo14080a(boolean z) {
        if (z) {
            this.f594e.setText("Open");
        } else {
            this.f594e.setText("Download");
        }
    }

    /* renamed from: a */
    public void mo14079a(MetaDataStyle metaDataStyle) {
        if (this.f596g != metaDataStyle) {
            this.f596g = metaDataStyle;
            this.f590a.setBackgroundDrawable(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{metaDataStyle.getItemGradientTop().intValue(), metaDataStyle.getItemGradientBottom().intValue()}));
            this.f592c.setTextSize((float) metaDataStyle.getItemTitleTextSize().intValue());
            this.f592c.setTextColor(metaDataStyle.getItemTitleTextColor().intValue());
            C1060h.m1170a(this.f592c, metaDataStyle.getItemTitleTextDecoration());
            this.f593d.setTextSize((float) metaDataStyle.getItemDescriptionTextSize().intValue());
            this.f593d.setTextColor(metaDataStyle.getItemDescriptionTextColor().intValue());
            C1060h.m1170a(this.f593d, metaDataStyle.getItemDescriptionTextDecoration());
        }
    }
}
