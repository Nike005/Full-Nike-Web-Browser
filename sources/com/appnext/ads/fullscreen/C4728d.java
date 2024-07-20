package com.appnext.ads.fullscreen;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.appnext.C4703R;
import com.appnext.ads.C4708a;
import com.appnext.core.AppnextAd;
import com.appnext.core.C4967f;
import com.appnext.core.C4977k;
import com.appnext.core.p086a.C4944b;
import java.util.ArrayList;

/* renamed from: com.appnext.ads.fullscreen.d */
public final class C4728d extends Fragment {
    /* access modifiers changed from: private */

    /* renamed from: aS */
    public ImageView f4260aS;

    /* renamed from: aT */
    private TextView f4261aT;
    /* access modifiers changed from: private */

    /* renamed from: aU */
    public C4762h f4262aU;
    /* access modifiers changed from: private */
    public ArrayList<AppnextAd> ads;
    /* access modifiers changed from: private */
    public boolean clicked = false;

    public final void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f4262aU = (C4762h) activity;
    }

    public final void onAttach(Context context) {
        super.onAttach(context);
        this.f4262aU = (C4762h) context;
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            RelativeLayout relativeLayout = (RelativeLayout) layoutInflater.inflate(this.f4262aU.getTemplate("S3"), viewGroup, false);
            this.ads = this.f4262aU.getPostRollAds();
            ImageView imageView = (ImageView) relativeLayout.findViewById(C4703R.C4706id.privacy);
            ImageView imageView2 = (ImageView) relativeLayout.findViewById(C4703R.C4706id.close);
            View findViewById = relativeLayout.findViewById(C4703R.C4706id.click);
            this.f4260aS = (ImageView) relativeLayout.findViewById(C4703R.C4706id.media);
            this.f4261aT = (TextView) relativeLayout.findViewById(C4703R.C4706id.install);
            imageView.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    C4728d.this.f4262aU.privacyClicked();
                }
            });
            if (C4977k.m6867a(this.ads.get(0), this.f4262aU.getConfigManager())) {
                C4977k.m6866a((Context) this.f4262aU, imageView);
            }
            imageView2.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    C4728d.this.f4262aU.closeClicked();
                }
            });
            findViewById.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    C4728d.this.f4262aU.installClicked((AppnextAd) C4728d.this.ads.get(0));
                }
            });
            String ctaText = this.f4262aU.getCtaText();
            int parseInt = Integer.parseInt(C4944b.m6738bp().mo41225b(this.f4262aU.getLanguage(), C4944b.f4720hW, "len"));
            if (!TextUtils.isEmpty(ctaText) && ctaText.length() > parseInt) {
                ctaText = ctaText.substring(0, parseInt);
            }
            this.f4261aT.setText(ctaText);
            this.f4261aT.setTextSize(2, (float) Integer.parseInt(C4944b.m6738bp().mo41225b(this.f4262aU.getLanguage(), C4944b.f4720hW, "font_size_sp")));
            new Thread(new Runnable() {
                public final void run() {
                    final Bitmap Y = C4967f.m6809Y(((AppnextAd) C4728d.this.ads.get(0)).getWideImageURL());
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public final void run() {
                            C4728d.this.f4260aS.setImageBitmap(Y);
                        }
                    });
                }
            }).start();
            m6301a(relativeLayout, this.ads.get(0), true);
            View findViewById2 = relativeLayout.findViewById(C4703R.C4706id.extra);
            if (findViewById2 != null) {
                if (this.ads.size() > 1) {
                    m6301a((RelativeLayout) findViewById2.findViewById(C4703R.C4706id.item1), this.ads.get(1), false);
                } else {
                    findViewById2.findViewById(C4703R.C4706id.item1).setVisibility(4);
                }
                if (this.ads.size() > 2) {
                    m6301a((RelativeLayout) findViewById2.findViewById(C4703R.C4706id.item2), this.ads.get(2), false);
                } else {
                    findViewById2.findViewById(C4703R.C4706id.item2).setVisibility(4);
                }
                if (findViewById2.findViewById(C4703R.C4706id.item3) != null) {
                    if (this.ads.size() > 3) {
                        m6301a((RelativeLayout) findViewById2.findViewById(C4703R.C4706id.item3), this.ads.get(3), false);
                    } else {
                        findViewById2.findViewById(C4703R.C4706id.item3).setVisibility(4);
                    }
                }
            }
            report(C4708a.f4168R);
            return relativeLayout;
        } catch (Throwable unused) {
            this.f4262aU.closeClicked();
            return null;
        }
    }

    /* renamed from: a */
    private void m6301a(final RelativeLayout relativeLayout, final AppnextAd appnextAd, final boolean z) {
        new Thread(new Runnable() {
            public final void run() {
                final Bitmap Y = C4967f.m6809Y(appnextAd.getImageURL());
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public final void run() {
                        ((ImageView) relativeLayout.findViewById(C4703R.C4706id.icon)).setImageBitmap(Y);
                    }
                });
            }
        }).start();
        ((TextView) relativeLayout.findViewById(C4703R.C4706id.title)).setText(appnextAd.getAdTitle());
        ((RatingBar) relativeLayout.findViewById(C4703R.C4706id.rating)).setRating(Float.parseFloat(appnextAd.getStoreRating()));
        relativeLayout.findViewById(C4703R.C4706id.click).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                FullscreenAd fullscreenAd = new FullscreenAd(appnextAd);
                if (z) {
                    C4728d.this.report(C4708a.f4170T);
                } else {
                    C4728d.this.report(C4708a.f4171U);
                    StringBuilder sb = new StringBuilder();
                    sb.append(fullscreenAd.getAppURL());
                    sb.append("&tem_id=");
                    sb.append(C4728d.this.f4262aU.isRewarded() ? "8" : "7");
                    sb.append("05");
                    fullscreenAd.setAppURL(sb.toString());
                }
                C4728d.this.f4262aU.installClicked(fullscreenAd);
                boolean unused = C4728d.this.clicked = true;
            }
        });
    }

    public final void onDestroyView() {
        report(C4708a.f4169S);
        super.onDestroyView();
    }

    /* access modifiers changed from: private */
    public void report(String str) {
        this.f4262aU.report(str, "S3");
    }
}
