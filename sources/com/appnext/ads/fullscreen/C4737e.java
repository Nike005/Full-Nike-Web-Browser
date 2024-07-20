package com.appnext.ads.fullscreen;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.appnext.C4703R;
import com.appnext.ads.C4708a;
import com.appnext.base.p082b.C4899d;
import com.appnext.core.AppnextAd;
import com.appnext.core.C4967f;
import com.appnext.core.C4977k;
import java.util.ArrayList;

/* renamed from: com.appnext.ads.fullscreen.e */
public final class C4737e extends Fragment {
    /* access modifiers changed from: private */

    /* renamed from: bc */
    public C4763i f4277bc;
    /* access modifiers changed from: private */

    /* renamed from: bd */
    public TextView f4278bd;
    /* access modifiers changed from: private */

    /* renamed from: be */
    public int f4279be = 0;
    /* access modifiers changed from: private */

    /* renamed from: bf */
    public ArrayList<AppnextAd> f4280bf;
    /* access modifiers changed from: private */
    public Handler handler;
    Runnable tick = new Runnable() {
        public final void run() {
            C4737e.this.handler.removeCallbacks(this);
            if (C4737e.m6310c(C4737e.this) == 0) {
                C4737e.this.f4277bc.videoSelected((AppnextAd) C4737e.this.f4280bf.get(0));
                C4737e.this.report(C4708a.f4155E);
                return;
            }
            if (C4737e.this.f4278bd != null) {
                TextView e = C4737e.this.f4278bd;
                e.setText(C4737e.this.f4279be + " sec");
            }
            C4737e.this.handler.postDelayed(C4737e.this.tick, 1000);
        }
    };

    /* renamed from: c */
    static /* synthetic */ int m6310c(C4737e eVar) {
        int i = eVar.f4279be - 1;
        eVar.f4279be = i;
        return i;
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null && getArguments().containsKey(C4899d.f4625fl)) {
            this.f4279be = getArguments().getInt(C4899d.f4625fl);
        }
        if (bundle != null) {
            this.f4279be = bundle.getInt(C4899d.f4625fl);
        }
    }

    public final void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f4277bc = (C4763i) activity;
        this.handler = new Handler();
    }

    public final void onAttach(Context context) {
        super.onAttach(context);
        this.f4277bc = (C4763i) context;
        this.handler = new Handler();
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            RelativeLayout relativeLayout = (RelativeLayout) layoutInflater.inflate(this.f4277bc.getTemplate("S1"), viewGroup, false);
            View findViewById = relativeLayout.findViewById(C4703R.C4706id.item1);
            View findViewById2 = relativeLayout.findViewById(C4703R.C4706id.item2);
            ((TextView) relativeLayout.findViewById(C4703R.C4706id.title)).setText(this.f4277bc.getConfigManager().get("pre_title_string1"));
            ((TextView) relativeLayout.findViewById(C4703R.C4706id.secondTile)).setText(this.f4277bc.getConfigManager().get("pre_title_string2"));
            this.f4278bd = (TextView) relativeLayout.findViewById(C4703R.C4706id.timer);
            ArrayList<AppnextAd> preRollAds = this.f4277bc.getPreRollAds();
            this.f4280bf = preRollAds;
            if (preRollAds.size() < 2) {
                this.f4277bc.videoSelected(this.f4280bf.get(0));
                return null;
            }
            m6307a((ViewGroup) findViewById, this.f4280bf.get(0), 0);
            m6307a((ViewGroup) findViewById2, this.f4280bf.get(1), 1);
            relativeLayout.findViewById(C4703R.C4706id.privacy).setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    C4737e.this.f4277bc.privacyClicked();
                }
            });
            if (C4977k.m6867a(this.f4280bf.get(0), this.f4277bc.getConfigManager())) {
                C4977k.m6866a((Context) this.f4277bc, (ImageView) relativeLayout.findViewById(C4703R.C4706id.privacy));
            }
            TextView textView = this.f4278bd;
            textView.setText(this.f4279be + " sec");
            report(C4708a.f4152B);
            return relativeLayout;
        } catch (Throwable unused) {
            this.f4277bc.closeClicked();
            return null;
        }
    }

    /* renamed from: a */
    private void m6307a(ViewGroup viewGroup, final AppnextAd appnextAd, final int i) {
        TextView textView = (TextView) viewGroup.findViewById(C4703R.C4706id.title);
        final ImageView imageView = (ImageView) viewGroup.findViewById(C4703R.C4706id.icon);
        final ImageView imageView2 = (ImageView) viewGroup.findViewById(C4703R.C4706id.background_image);
        RatingBar ratingBar = (RatingBar) viewGroup.findViewById(C4703R.C4706id.ratingBar);
        if (viewGroup.findViewById(C4703R.C4706id.playGameTextView) != null) {
            ((TextView) viewGroup.findViewById(C4703R.C4706id.playGameTextView)).setText(this.f4277bc.getConfigManager().get("pre_cta_string"));
        }
        textView.setText(appnextAd.getAdTitle());
        ratingBar.setRating(Float.parseFloat(appnextAd.getStoreRating()));
        viewGroup.findViewById(C4703R.C4706id.click).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                C4737e.this.f4277bc.videoSelected(appnextAd);
                int i = i;
                if (i == 0) {
                    C4737e.this.report(C4708a.f4153C);
                } else if (i == 1) {
                    C4737e.this.report(C4708a.f4154D);
                }
            }
        });
        if (imageView2 != null) {
            new Thread(new Runnable() {
                public final void run() {
                    final Bitmap Y = C4967f.m6809Y(appnextAd.getWideImageURL());
                    final Bitmap Y2 = C4967f.m6809Y(appnextAd.getImageURL());
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public final void run() {
                            imageView2.setImageBitmap(Y);
                            imageView.setImageBitmap(Y2);
                        }
                    });
                }
            }).start();
        }
        new Thread(new Runnable() {
            public final void run() {
                final Bitmap Y = imageView2 != null ? C4967f.m6809Y(appnextAd.getWideImageURL()) : null;
                final Bitmap Y2 = C4967f.m6809Y(appnextAd.getImageURL());
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public final void run() {
                        if (imageView2 != null) {
                            imageView2.setImageBitmap(Y);
                        }
                        imageView.setImageBitmap(Y2);
                    }
                });
            }
        }).start();
    }

    public final void onDestroyView() {
        super.onDestroyView();
        this.handler.removeCallbacksAndMessages((Object) null);
    }

    public final void onSaveInstanceState(Bundle bundle) {
        bundle.putInt(C4899d.f4625fl, this.f4279be);
        super.onSaveInstanceState(bundle);
    }

    public final void onPause() {
        super.onPause();
        this.handler.removeCallbacks(this.tick);
    }

    public final void onResume() {
        super.onResume();
        this.handler.postDelayed(this.tick, 1000);
    }

    /* access modifiers changed from: private */
    public void report(String str) {
        this.f4277bc.report(str, "S1");
    }
}
