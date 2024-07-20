package com.startapp.android.publish.ads.list3d;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.C1098b;
import com.startapp.android.publish.adsCommon.C1103c;
import com.startapp.android.publish.adsCommon.C1174m;
import com.startapp.android.publish.adsCommon.Utils.C1049a;
import com.startapp.android.publish.adsCommon.Utils.C1060h;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.adsCommon.adinformation.C1084b;
import com.startapp.android.publish.adsCommon.adinformation.C1093c;
import com.startapp.android.publish.adsCommon.p031d.C1116a;
import com.startapp.android.publish.adsCommon.p031d.C1117b;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.C1275b;
import com.startapp.common.p043a.C1270g;
import java.util.List;

/* compiled from: StartAppSDK */
public class List3DActivity extends Activity implements C0927g {

    /* renamed from: a */
    String f501a;

    /* renamed from: b */
    String f502b;

    /* renamed from: c */
    List<ListItem> f503c;

    /* renamed from: d */
    private C0918c f504d;

    /* renamed from: e */
    private ProgressDialog f505e = null;

    /* renamed from: f */
    private WebView f506f = null;

    /* renamed from: g */
    private int f507g;

    /* renamed from: h */
    private C1084b f508h;

    /* renamed from: i */
    private Long f509i;

    /* renamed from: j */
    private Long f510j;

    /* renamed from: k */
    private String f511k;

    /* renamed from: l */
    private long f512l = 0;

    /* renamed from: m */
    private long f513m = 0;

    /* renamed from: n */
    private BroadcastReceiver f514n = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            List3DActivity.this.finish();
        }
    };

    public void onCreate(Bundle bundle) {
        String str;
        View view;
        Bundle bundle2 = bundle;
        try {
            overridePendingTransition(0, 0);
            super.onCreate(bundle);
            if (getIntent().getBooleanExtra("fullscreen", false)) {
                requestWindowFeature(1);
                getWindow().setFlags(1024, 1024);
            }
            if (bundle2 == null) {
                C1275b.m2102a((Context) this).mo15481a(new Intent("com.startapp.android.ShowDisplayBroadcastListener"));
                this.f509i = (Long) getIntent().getSerializableExtra("lastLoadTime");
                this.f510j = (Long) getIntent().getSerializableExtra("adCacheTtl");
            } else {
                if (bundle2.containsKey("lastLoadTime")) {
                    this.f509i = (Long) bundle2.getSerializable("lastLoadTime");
                }
                if (bundle2.containsKey("adCacheTtl")) {
                    this.f510j = (Long) bundle2.getSerializable("adCacheTtl");
                }
            }
            this.f511k = getIntent().getStringExtra("position");
            this.f501a = getIntent().getStringExtra("listModelUuid");
            C1275b.m2102a((Context) this).mo15480a(this.f514n, new IntentFilter("com.startapp.android.CloseAdActivity"));
            this.f507g = getResources().getConfiguration().orientation;
            C1061i.m1182a((Activity) this, true);
            boolean booleanExtra = getIntent().getBooleanExtra("overlay", false);
            requestWindowFeature(1);
            this.f502b = getIntent().getStringExtra("adTag");
            int e = C1098b.m1303a().mo14765e();
            int f = C1098b.m1303a().mo14766f();
            this.f504d = new C0918c(this, (AttributeSet) null, this.f502b, this.f501a);
            this.f504d.setBackgroundDrawable(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{e, f}));
            List<ListItem> e2 = C0926f.m744a().mo14097a(this.f501a).mo14096e();
            this.f503c = e2;
            if (e2 == null) {
                finish();
                return;
            }
            if (booleanExtra) {
                C1275b.m2102a((Context) this).mo15480a(this.f504d.f575p, new IntentFilter("com.startapp.android.Activity3DGetValues"));
                str = "";
            } else {
                this.f504d.mo14053a();
                this.f504d.setHint(true);
                this.f504d.setFade(true);
                str = "back";
            }
            C0917b bVar = new C0917b(this, this.f503c, str, this.f502b, this.f501a);
            C0926f.m744a().mo14097a(this.f501a).mo14089a(this, !booleanExtra);
            this.f504d.setAdapter(bVar);
            this.f504d.setDynamics(new SimpleDynamics(0.9f, 0.6f));
            this.f504d.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    int i2 = i;
                    String b = List3DActivity.this.f503c.get(i2).mo14009b();
                    String e = List3DActivity.this.f503c.get(i2).mo14013e();
                    String f = List3DActivity.this.f503c.get(i2).mo14014f();
                    boolean l = List3DActivity.this.f503c.get(i2).mo14020l();
                    boolean m = List3DActivity.this.f503c.get(i2).mo14021m();
                    String p = List3DActivity.this.f503c.get(i2).mo14024p();
                    String o = List3DActivity.this.f503c.get(i2).mo14023o();
                    Boolean s = List3DActivity.this.f503c.get(i2).mo14027s();
                    C0926f.m744a().mo14097a(List3DActivity.this.f501a).mo14091a(List3DActivity.this.f503c.get(i2).mo14010c());
                    if (p != null && !TextUtils.isEmpty(p)) {
                        m660a(p, o, b, e);
                    } else if (!TextUtils.isEmpty(b)) {
                        boolean a = C1103c.m1389a(List3DActivity.this.getApplicationContext(), AdPreferences.Placement.INAPP_OFFER_WALL);
                        if (!l || a) {
                            List3DActivity list3DActivity = List3DActivity.this;
                            C1103c.m1376a(list3DActivity, b, e, list3DActivity.mo13993a(), m && !a, false);
                            List3DActivity.this.finish();
                            return;
                        }
                        List3DActivity list3DActivity2 = List3DActivity.this;
                        C1103c.m1379a(list3DActivity2, b, e, f, list3DActivity2.mo13993a(), C1098b.m1303a().mo14747A(), C1098b.m1303a().mo14748B(), m, s, false, new Runnable() {
                            public void run() {
                                List3DActivity.this.finish();
                            }
                        });
                    }
                }

                /* renamed from: a */
                private void m660a(String str, String str2, String str3, String str4) {
                    List3DActivity list3DActivity = List3DActivity.this;
                    C1103c.m1385a(str, str2, str3, (Context) list3DActivity, new C1117b(list3DActivity.f502b));
                    List3DActivity.this.finish();
                }
            });
            RelativeLayout relativeLayout = new RelativeLayout(this);
            relativeLayout.setContentDescription("StartApp Ad");
            relativeLayout.setId(AdsConstants.STARTAPP_AD_MAIN_LAYOUT_ID);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(1);
            relativeLayout.addView(linearLayout, layoutParams2);
            RelativeLayout relativeLayout2 = new RelativeLayout(this);
            relativeLayout2.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
            relativeLayout2.setBackgroundColor(C1098b.m1303a().mo14768h().intValue());
            linearLayout.addView(relativeLayout2);
            TextView textView = new TextView(this);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams3.addRule(13);
            textView.setLayoutParams(layoutParams3);
            textView.setPadding(0, C1060h.m1162a((Context) this, 2), 0, C1060h.m1162a((Context) this, 5));
            textView.setTextColor(C1098b.m1303a().mo14771k().intValue());
            textView.setTextSize((float) C1098b.m1303a().mo14770j().intValue());
            textView.setSingleLine(true);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setText(C1098b.m1303a().mo14769i());
            textView.setShadowLayer(2.5f, -2.0f, 2.0f, -11513776);
            C1060h.m1170a(textView, C1098b.m1303a().mo14772l());
            relativeLayout2.addView(textView);
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams4.addRule(11);
            layoutParams4.addRule(15);
            Bitmap a = C1049a.m1129a(this, "close_button.png");
            if (a != null) {
                view = new ImageButton(this, (AttributeSet) null, 16973839);
                ((ImageButton) view).setImageBitmap(Bitmap.createScaledBitmap(a, C1060h.m1162a((Context) this, 36), C1060h.m1162a((Context) this, 36), true));
            } else {
                view = new TextView(this);
                ((TextView) view).setText("   x   ");
                ((TextView) view).setTextSize(20.0f);
            }
            view.setLayoutParams(layoutParams4);
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    List3DActivity list3DActivity = List3DActivity.this;
                    C1103c.m1395b((Context) list3DActivity, list3DActivity.mo13995b(), List3DActivity.this.mo13993a());
                    List3DActivity.this.finish();
                }
            });
            view.setContentDescription(AvidJSONUtil.KEY_X);
            view.setId(AdsConstants.LIST_3D_CLOSE_BUTTON_ID);
            relativeLayout2.addView(view);
            View view2 = new View(this);
            view2.setLayoutParams(new LinearLayout.LayoutParams(-1, C1060h.m1162a((Context) this, 2)));
            view2.setBackgroundColor(C1098b.m1303a().mo14773m().intValue());
            linearLayout.addView(view2);
            LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-1, 0);
            layoutParams5.weight = 1.0f;
            this.f504d.setLayoutParams(layoutParams5);
            linearLayout.addView(this.f504d);
            LinearLayout linearLayout2 = new LinearLayout(this);
            LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams6.gravity = 80;
            linearLayout2.setLayoutParams(layoutParams6);
            linearLayout2.setBackgroundColor(C1098b.m1303a().mo14782v().intValue());
            linearLayout2.setGravity(17);
            linearLayout.addView(linearLayout2);
            TextView textView2 = new TextView(this);
            textView2.setTextColor(C1098b.m1303a().mo14783w().intValue());
            textView2.setPadding(0, C1060h.m1162a((Context) this, 2), 0, C1060h.m1162a((Context) this, 3));
            textView2.setText("Powered By ");
            textView2.setTextSize(16.0f);
            linearLayout2.addView(textView2);
            ImageView imageView = new ImageView(this);
            imageView.setImageBitmap(Bitmap.createScaledBitmap(C1049a.m1129a(this, "logo.png"), C1060h.m1162a((Context) this, 56), C1060h.m1162a((Context) this, 12), true));
            linearLayout2.addView(imageView);
            C1084b bVar2 = new C1084b(this, C1084b.C1092b.LARGE, AdPreferences.Placement.INAPP_OFFER_WALL, (C1093c) getIntent().getSerializableExtra("adInfoOverride"));
            this.f508h = bVar2;
            bVar2.mo14713a(relativeLayout);
            setContentView(relativeLayout, layoutParams);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    List3DActivity.this.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
                }
            }, 500);
        } catch (Throwable th) {
            C1270g.m2079a("List3DActivity", 6, "List3DActivity.onCreate", th);
            C1132f.m1527a(this, C1130d.EXCEPTION, "List3DActivity.onCreate", th.getMessage(), "");
            finish();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C1117b mo13993a() {
        long currentTimeMillis = System.currentTimeMillis();
        this.f512l = currentTimeMillis;
        double d = (double) (currentTimeMillis - this.f513m);
        Double.isNaN(d);
        return new C1116a(String.valueOf(d / 1000.0d), this.f502b);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public String mo13995b() {
        List<ListItem> list = this.f503c;
        if (list == null || list.isEmpty() || this.f503c.get(0).mo14011d() == null) {
            return "";
        }
        return this.f503c.get(0).mo14011d();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (m656d()) {
            C1270g.m2078a("List3DActivity", 3, "Cache TTL passed, finishing");
            finish();
            return;
        }
        C1174m.m1649a().mo14978a(true);
        this.f513m = System.currentTimeMillis();
        C0926f.m744a().mo14097a(this.f501a).mo14094c();
    }

    public void onBackPressed() {
        C0926f.m744a().mo14097a(this.f501a).mo14095d();
        super.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        ProgressDialog progressDialog = this.f505e;
        if (progressDialog != null) {
            synchronized (progressDialog) {
                if (this.f505e != null) {
                    this.f505e.dismiss();
                    this.f505e = null;
                }
            }
        }
        WebView webView = this.f506f;
        if (webView != null) {
            webView.stopLoading();
        }
        C1061i.m1182a((Activity) this, false);
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        C0926f.m744a().mo14097a(this.f501a).mo14092b();
        C1084b bVar = this.f508h;
        if (bVar != null && bVar.mo14715b()) {
            this.f508h.mo14717d();
        }
        overridePendingTransition(0, 0);
        String str = this.f511k;
        if (str != null && str.equals("back")) {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Long l = this.f509i;
        if (l != null) {
            bundle.putSerializable("lastLoadTime", l);
        }
        Long l2 = this.f510j;
        if (l2 != null) {
            bundle.putSerializable("adCacheTtl", l2);
        }
    }

    /* renamed from: a */
    public void mo13994a(int i) {
        View childAt = this.f504d.getChildAt(i - this.f504d.getFirstItemPosition());
        if (childAt != null) {
            C0923d dVar = (C0923d) childAt.getTag();
            C0925e a = C0926f.m744a().mo14097a(this.f501a);
            if (a != null && a.mo14096e() != null && i < a.mo14096e().size()) {
                ListItem listItem = a.mo14096e().get(i);
                dVar.mo14081b().setImageBitmap(a.mo14086a(i, listItem.mo14008a(), listItem.mo14017i()));
                dVar.mo14081b().requestLayout();
                dVar.mo14080a(listItem.mo14025q());
            }
        }
    }

    public void finish() {
        C1270g.m2078a("List3DActivity", 2, "Finishing activity.");
        this.f512l = System.currentTimeMillis();
        C1103c.m1395b((Context) this, mo13995b(), mo13993a());
        C1174m.m1649a().mo14978a(false);
        m655c();
        synchronized (this) {
            if (this.f514n != null) {
                C1275b.m2102a((Context) this).mo15479a(this.f514n);
                this.f514n = null;
            }
        }
        C0926f.m744a().mo14097a(this.f501a).mo14095d();
        if (!AdsConstants.OVERRIDE_NETWORK.booleanValue()) {
            C0926f.m744a().mo14098b(this.f501a);
        }
        super.finish();
    }

    /* renamed from: c */
    private void m655c() {
        if (this.f507g == getResources().getConfiguration().orientation) {
            C1275b.m2102a((Context) this).mo15481a(new Intent("com.startapp.android.HideDisplayBroadcastListener"));
        }
    }

    /* renamed from: d */
    private boolean m656d() {
        if (this.f509i == null || this.f510j == null || System.currentTimeMillis() - this.f509i.longValue() <= this.f510j.longValue()) {
            return false;
        }
        return true;
    }
}
