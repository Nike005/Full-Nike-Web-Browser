package com.startapp.android.publish.adsCommon.adinformation;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.Utils.C1060h;
import com.startapp.android.publish.adsCommon.adinformation.AdInformationPositions;
import com.startapp.android.publish.adsCommon.adinformation.C1084b;
import com.startapp.android.publish.common.model.AdPreferences;

/* renamed from: com.startapp.android.publish.adsCommon.adinformation.d */
/* compiled from: StartAppSDK */
public class C1094d extends RelativeLayout {

    /* renamed from: a */
    private ImageView f1072a;

    /* renamed from: b */
    private RelativeLayout f1073b;

    /* renamed from: c */
    private View.OnClickListener f1074c = null;

    /* renamed from: d */
    private AdInformationConfig f1075d;

    /* renamed from: e */
    private C1096e f1076e;

    /* renamed from: f */
    private AdPreferences.Placement f1077f;

    /* renamed from: g */
    private AdInformationPositions.Position f1078g;

    public C1094d(Context context, C1084b.C1092b bVar, AdPreferences.Placement placement, C1093c cVar, final View.OnClickListener onClickListener) {
        super(context);
        this.f1077f = placement;
        this.f1074c = new View.OnClickListener() {
            public void onClick(View view) {
                onClickListener.onClick(view);
            }
        };
        mo14731a(bVar, cVar);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo14731a(C1084b.C1092b bVar, C1093c cVar) {
        AdInformationConfig a = C1084b.m1262a(getContext());
        this.f1075d = a;
        if (a == null) {
            this.f1075d = AdInformationConfig.m1237a();
        }
        this.f1076e = this.f1075d.mo14686a(bVar.mo14724a());
        if (cVar == null || !cVar.mo14729d()) {
            this.f1078g = this.f1075d.mo14685a(this.f1077f);
        } else {
            this.f1078g = cVar.mo14728c();
        }
        ImageView imageView = new ImageView(getContext());
        this.f1072a = imageView;
        imageView.setContentDescription("info");
        this.f1072a.setId(AdsConstants.AD_INFORMATION_ID);
        this.f1072a.setImageBitmap(this.f1076e.mo14733a(getContext()));
        this.f1073b = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(C1060h.m1162a(getContext(), (int) (((float) this.f1076e.mo14738b()) * this.f1075d.mo14693e())), C1060h.m1162a(getContext(), (int) (((float) this.f1076e.mo14742c()) * this.f1075d.mo14693e())));
        this.f1073b.setBackgroundColor(0);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(C1060h.m1162a(getContext(), this.f1076e.mo14738b()), C1060h.m1162a(getContext(), this.f1076e.mo14742c()));
        layoutParams2.setMargins(0, 0, 0, 0);
        this.f1072a.setPadding(0, 0, 0, 0);
        this.f1078g.addRules(layoutParams2);
        this.f1073b.addView(this.f1072a, layoutParams2);
        this.f1073b.setOnClickListener(this.f1074c);
        addView(this.f1073b, layoutParams);
    }
}
