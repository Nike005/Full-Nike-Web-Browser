package com.tappx.sdk.android.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tappx.p048a.C1437h3;
import com.tappx.p048a.C1536n2;
import com.tappx.p048a.C1552o2;

public class PrivacyView extends FrameLayout {

    /* renamed from: a */
    private View f2642a;

    /* renamed from: b */
    private View f2643b;

    /* renamed from: c */
    private TextView f2644c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public boolean f2645d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public C1536n2 f2646e;

    /* renamed from: f */
    private TextView f2647f;

    /* renamed from: g */
    private TextView f2648g;

    public PrivacyView(Context context) {
        super(context);
        m3810d();
    }

    /* renamed from: c */
    private void m3809c() {
        removeAllViews();
        Context context = getContext();
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setGravity(83);
        linearLayout.setOrientation(0);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.width = -2;
        layoutParams.height = -2;
        linearLayout.setLayoutParams(layoutParams);
        LinearLayout linearLayout2 = new LinearLayout(context);
        linearLayout2.setBackgroundColor(Color.parseColor("#EEe7e7e7"));
        linearLayout2.setOrientation(1);
        linearLayout2.setGravity(17);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(m3803a(0), m3803a(50));
        layoutParams2.weight = 1.0f;
        linearLayout2.setLayoutParams(layoutParams2);
        this.f2642a = linearLayout2;
        TextView textView = new TextView(context);
        this.f2644c = textView;
        textView.setGravity(17);
        this.f2644c.setText("Do you want to change your ad experience?.");
        this.f2644c.setTextColor(Color.parseColor("#000000"));
        this.f2644c.setTextSize(14.0f);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(m3803a(285), -2);
        layoutParams3.gravity = 17;
        layoutParams3.leftMargin = m3803a(5);
        layoutParams3.rightMargin = m3803a(5);
        this.f2644c.setLayoutParams(layoutParams3);
        linearLayout2.addView(this.f2644c);
        LinearLayout linearLayout3 = new LinearLayout(context);
        linearLayout3.setOrientation(0);
        FrameLayout.LayoutParams layoutParams4 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams4.gravity = 17;
        layoutParams4.bottomMargin = m3803a(3);
        layoutParams4.topMargin = m3803a(3);
        linearLayout3.setLayoutParams(layoutParams4);
        int a = m3803a(3);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        float f = (float) a;
        gradientDrawable.setCornerRadii(new float[]{f, f, f, f, f, f, f, f});
        gradientDrawable.setColor(Color.parseColor("#116073"));
        TextView textView2 = new TextView(context);
        this.f2647f = textView2;
        textView2.setBackgroundDrawable(gradientDrawable);
        int a2 = m3803a(20);
        int a3 = m3803a(2);
        this.f2647f.setPadding(a2, a3, a2, a3);
        this.f2647f.setText("Yes");
        this.f2647f.setTextColor(Color.parseColor("#ffffff"));
        this.f2647f.setTextSize(14.0f);
        this.f2647f.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        linearLayout3.addView(this.f2647f);
        View view = new View(context);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(m3803a(1), -1);
        layoutParams5.leftMargin = a2;
        layoutParams5.rightMargin = a2;
        view.setLayoutParams(layoutParams5);
        view.setBackgroundColor(Color.parseColor("#cacaca"));
        linearLayout3.addView(view);
        TextView textView3 = new TextView(context);
        this.f2648g = textView3;
        textView3.setBackgroundDrawable(gradientDrawable);
        this.f2648g.setPadding(a2, a3, a2, a3);
        this.f2648g.setText("No");
        this.f2648g.setTextColor(Color.parseColor("#ffffff"));
        this.f2648g.setTextSize(14.0f);
        FrameLayout.LayoutParams layoutParams6 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams6.rightMargin = m3803a(20);
        this.f2648g.setLayoutParams(layoutParams6);
        linearLayout3.addView(this.f2648g);
        linearLayout2.addView(linearLayout3);
        linearLayout.addView(linearLayout2);
        LinearLayout linearLayout4 = new LinearLayout(context);
        linearLayout4.setOrientation(1);
        FrameLayout.LayoutParams layoutParams7 = new FrameLayout.LayoutParams(-2, -1);
        layoutParams7.gravity = 83;
        linearLayout4.setLayoutParams(layoutParams7);
        linearLayout.addView(linearLayout4);
        this.f2643b = linearLayout4;
        ImageView imageView = new ImageView(context);
        imageView.setImageDrawable(C1437h3.m2793a(context));
        linearLayout4.addView(imageView, new LinearLayout.LayoutParams(-2, -2));
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setBackgroundColor(-1);
        linearLayout4.addView(frameLayout, new LinearLayout.LayoutParams(-1, -1));
        addView(linearLayout);
    }

    /* renamed from: d */
    private void m3810d() {
        m3807b();
        this.f2646e = C1552o2.m3165a(getContext()).mo16036c();
        m3805a();
        this.f2648g.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PrivacyView.this.setCollapsed(true);
            }
        });
        this.f2647f.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PrivacyView.this.f2646e.mo15980a(view.getContext());
                PrivacyView.this.setCollapsed(true);
            }
        });
        this.f2643b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PrivacyView privacyView = PrivacyView.this;
                privacyView.setCollapsed(!privacyView.f2645d);
            }
        });
        setCollapsed(true);
    }

    /* access modifiers changed from: private */
    public void setCollapsed(boolean z) {
        this.f2645d = z;
        this.f2642a.setVisibility(z ? 8 : 0);
    }

    /* renamed from: b */
    private void m3807b() {
        try {
            throw new RuntimeException("");
        } catch (Throwable unused) {
            m3809c();
        }
    }

    public PrivacyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m3810d();
    }

    /* renamed from: a */
    private int m3803a(int i) {
        return (int) TypedValue.applyDimension(1, (float) i, getResources().getDisplayMetrics());
    }

    /* renamed from: a */
    private void m3805a() {
        String b = this.f2646e.mo15986b();
        if (b != null) {
            this.f2644c.setText(b);
        }
    }

    public PrivacyView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m3810d();
    }
}
