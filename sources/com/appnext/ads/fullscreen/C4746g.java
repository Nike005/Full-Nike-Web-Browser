package com.appnext.ads.fullscreen;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import com.appnext.C4703R;
import com.appnext.ads.C4708a;

/* renamed from: com.appnext.ads.fullscreen.g */
public final class C4746g extends Fragment {
    private final int TICK = 330;
    /* access modifiers changed from: private */

    /* renamed from: ay */
    public Circle f4303ay;
    /* access modifiers changed from: private */

    /* renamed from: bA */
    public boolean f4304bA = false;
    /* access modifiers changed from: private */

    /* renamed from: bB */
    public C4764j f4305bB;

    /* renamed from: bC */
    Runnable f4306bC = new Runnable() {
        public final void run() {
            C4746g.this.f4309bw.setAlpha(1.0f);
            C4746g.this.f4309bw.animate().alpha(0.0f).setDuration(1000);
        }
    };
    /* access modifiers changed from: private */

    /* renamed from: bu */
    public ImageView f4307bu;
    /* access modifiers changed from: private */

    /* renamed from: bv */
    public Button f4308bv;
    /* access modifiers changed from: private */

    /* renamed from: bw */
    public TextView f4309bw;
    /* access modifiers changed from: private */

    /* renamed from: bx */
    public ImageView f4310bx;
    /* access modifiers changed from: private */

    /* renamed from: by */
    public Animation f4311by;
    /* access modifiers changed from: private */

    /* renamed from: bz */
    public ImageView f4312bz;
    /* access modifiers changed from: private */
    public int currentPosition = 0;
    /* access modifiers changed from: private */
    public boolean finished = false;
    private int lastProgress = 0;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler();
    /* access modifiers changed from: private */
    public MediaPlayer mediaPlayer;
    /* access modifiers changed from: private */
    public boolean started = false;
    Runnable tick = new Runnable() {
        public final void run() {
            if (C4746g.this.videoView != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(C4746g.this.videoView.getCurrentPosition());
                sb.append(" of ");
                sb.append(C4746g.this.videoView.getDuration());
                if (C4746g.this.videoView.getDuration() == -1) {
                    C4746g.m6333l(C4746g.this);
                    return;
                }
                C4746g.m6335n(C4746g.this);
                if (C4746g.this.f4303ay.getVisibility() == 0) {
                    C4724a aVar = new C4724a(C4746g.this.f4303ay, 360.0f - ((((float) (C4746g.this.videoView.getCurrentPosition() + 1)) / ((float) C4746g.this.videoView.getDuration())) * 360.0f));
                    aVar.setDuration(330);
                    C4746g.this.f4303ay.startAnimation(aVar);
                }
                if (C4746g.this.videoView.getCurrentPosition() < C4746g.this.videoView.getDuration() && !C4746g.this.finished) {
                    C4746g.this.mHandler.postDelayed(C4746g.this.tick, 330);
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public VideoView videoView;

    public final void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f4305bB = (C4764j) activity;
        m6339s();
    }

    public final void onAttach(Context context) {
        super.onAttach(context);
        this.f4305bB = (C4764j) context;
        m6339s();
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0151 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.view.View onCreateView(android.view.LayoutInflater r8, android.view.ViewGroup r9, final android.os.Bundle r10) {
        /*
            r7 = this;
            java.lang.String r0 = "settings"
            java.lang.String r1 = "showCta"
            com.appnext.ads.fullscreen.j r2 = r7.f4305bB     // Catch:{ all -> 0x01a4 }
            java.lang.String r3 = "S2"
            int r2 = r2.getTemplate(r3)     // Catch:{ all -> 0x01a4 }
            r3 = 0
            android.view.View r8 = r8.inflate(r2, r9, r3)     // Catch:{ all -> 0x01a4 }
            android.widget.RelativeLayout r8 = (android.widget.RelativeLayout) r8     // Catch:{ all -> 0x01a4 }
            int r9 = com.appnext.C4703R.C4706id.privacy     // Catch:{ all -> 0x01a4 }
            android.view.View r9 = r8.findViewById(r9)     // Catch:{ all -> 0x01a4 }
            android.widget.ImageView r9 = (android.widget.ImageView) r9     // Catch:{ all -> 0x01a4 }
            int r2 = com.appnext.C4703R.C4706id.close     // Catch:{ all -> 0x01a4 }
            android.view.View r2 = r8.findViewById(r2)     // Catch:{ all -> 0x01a4 }
            android.widget.ImageView r2 = (android.widget.ImageView) r2     // Catch:{ all -> 0x01a4 }
            r7.f4312bz = r2     // Catch:{ all -> 0x01a4 }
            int r2 = com.appnext.C4703R.C4706id.v_view     // Catch:{ all -> 0x01a4 }
            android.view.View r2 = r8.findViewById(r2)     // Catch:{ all -> 0x01a4 }
            android.widget.ImageView r2 = (android.widget.ImageView) r2     // Catch:{ all -> 0x01a4 }
            r7.f4307bu = r2     // Catch:{ all -> 0x01a4 }
            int r2 = com.appnext.C4703R.C4706id.install     // Catch:{ all -> 0x01a4 }
            android.view.View r2 = r8.findViewById(r2)     // Catch:{ all -> 0x01a4 }
            android.widget.Button r2 = (android.widget.Button) r2     // Catch:{ all -> 0x01a4 }
            r7.f4308bv = r2     // Catch:{ all -> 0x01a4 }
            int r2 = com.appnext.C4703R.C4706id.circle     // Catch:{ all -> 0x01a4 }
            android.view.View r2 = r8.findViewById(r2)     // Catch:{ all -> 0x01a4 }
            com.appnext.ads.fullscreen.Circle r2 = (com.appnext.ads.fullscreen.Circle) r2     // Catch:{ all -> 0x01a4 }
            r7.f4303ay = r2     // Catch:{ all -> 0x01a4 }
            int r2 = com.appnext.C4703R.C4706id.click_txt     // Catch:{ all -> 0x01a4 }
            android.view.View r2 = r8.findViewById(r2)     // Catch:{ all -> 0x01a4 }
            android.widget.TextView r2 = (android.widget.TextView) r2     // Catch:{ all -> 0x01a4 }
            r7.f4309bw = r2     // Catch:{ all -> 0x01a4 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x01a4 }
            java.lang.String r5 = "You will be redirected to "
            r4.<init>(r5)     // Catch:{ all -> 0x01a4 }
            com.appnext.ads.fullscreen.j r5 = r7.f4305bB     // Catch:{ all -> 0x01a4 }
            boolean r5 = r5.isInstalled()     // Catch:{ all -> 0x01a4 }
            if (r5 == 0) goto L_0x005f
            java.lang.String r5 = "app"
            goto L_0x0061
        L_0x005f:
            java.lang.String r5 = "Google Play"
        L_0x0061:
            r4.append(r5)     // Catch:{ all -> 0x01a4 }
            java.lang.String r5 = " once the ad will finish"
            r4.append(r5)     // Catch:{ all -> 0x01a4 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x01a4 }
            r2.setText(r4)     // Catch:{ all -> 0x01a4 }
            int r2 = com.appnext.C4703R.C4706id.loader     // Catch:{ all -> 0x01a4 }
            android.view.View r2 = r8.findViewById(r2)     // Catch:{ all -> 0x01a4 }
            android.widget.ImageView r2 = (android.widget.ImageView) r2     // Catch:{ all -> 0x01a4 }
            r7.f4310bx = r2     // Catch:{ all -> 0x01a4 }
            com.appnext.ads.fullscreen.g$1 r2 = new com.appnext.ads.fullscreen.g$1     // Catch:{ all -> 0x01a4 }
            r2.<init>()     // Catch:{ all -> 0x01a4 }
            r9.setOnClickListener(r2)     // Catch:{ all -> 0x01a4 }
            com.appnext.ads.fullscreen.j r2 = r7.f4305bB     // Catch:{ all -> 0x01a4 }
            com.appnext.core.AppnextAd r2 = r2.getSelectedAd()     // Catch:{ all -> 0x01a4 }
            com.appnext.ads.fullscreen.j r4 = r7.f4305bB     // Catch:{ all -> 0x01a4 }
            com.appnext.core.p r4 = r4.getConfigManager()     // Catch:{ all -> 0x01a4 }
            boolean r2 = com.appnext.core.C4977k.m6867a((com.appnext.core.AppnextAd) r2, (com.appnext.core.C4986p) r4)     // Catch:{ all -> 0x01a4 }
            if (r2 == 0) goto L_0x009b
            com.appnext.ads.fullscreen.j r2 = r7.f4305bB     // Catch:{ all -> 0x01a4 }
            android.content.Context r2 = (android.content.Context) r2     // Catch:{ all -> 0x01a4 }
            com.appnext.core.C4977k.m6866a((android.content.Context) r2, (android.widget.ImageView) r9)     // Catch:{ all -> 0x01a4 }
        L_0x009b:
            android.widget.ImageView r9 = r7.f4312bz     // Catch:{ all -> 0x01a4 }
            r2 = 8
            r9.setVisibility(r2)     // Catch:{ all -> 0x01a4 }
            com.appnext.ads.fullscreen.j r9 = r7.f4305bB     // Catch:{ all -> 0x01a4 }
            boolean r9 = r9.showClose()     // Catch:{ all -> 0x01a4 }
            if (r9 == 0) goto L_0x00ba
            android.os.Handler r9 = r7.mHandler     // Catch:{ all -> 0x01a4 }
            com.appnext.ads.fullscreen.g$4 r4 = new com.appnext.ads.fullscreen.g$4     // Catch:{ all -> 0x01a4 }
            r4.<init>()     // Catch:{ all -> 0x01a4 }
            com.appnext.ads.fullscreen.j r5 = r7.f4305bB     // Catch:{ all -> 0x01a4 }
            long r5 = r5.closeDelay()     // Catch:{ all -> 0x01a4 }
            r9.postDelayed(r4, r5)     // Catch:{ all -> 0x01a4 }
        L_0x00ba:
            android.widget.ImageView r9 = r7.f4312bz     // Catch:{ all -> 0x01a4 }
            com.appnext.ads.fullscreen.g$5 r4 = new com.appnext.ads.fullscreen.g$5     // Catch:{ all -> 0x01a4 }
            r4.<init>()     // Catch:{ all -> 0x01a4 }
            r9.setOnClickListener(r4)     // Catch:{ all -> 0x01a4 }
            android.os.Bundle r9 = r7.getArguments()     // Catch:{ all -> 0x01a4 }
            if (r9 == 0) goto L_0x00e3
            android.os.Bundle r9 = r7.getArguments()     // Catch:{ all -> 0x01a4 }
            boolean r9 = r9.containsKey(r1)     // Catch:{ all -> 0x01a4 }
            if (r9 == 0) goto L_0x00e3
            android.os.Bundle r9 = r7.getArguments()     // Catch:{ all -> 0x01a4 }
            boolean r9 = r9.getBoolean(r1)     // Catch:{ all -> 0x01a4 }
            if (r9 != 0) goto L_0x00e3
            android.widget.Button r9 = r7.f4308bv     // Catch:{ all -> 0x01a4 }
            r9.setVisibility(r2)     // Catch:{ all -> 0x01a4 }
        L_0x00e3:
            com.appnext.ads.fullscreen.j r9 = r7.f4305bB     // Catch:{ all -> 0x01a4 }
            java.lang.String r9 = r9.getCtaText()     // Catch:{ all -> 0x01a4 }
            com.appnext.core.a.b r1 = com.appnext.core.p086a.C4944b.m6738bp()     // Catch:{ all -> 0x01a4 }
            com.appnext.ads.fullscreen.j r2 = r7.f4305bB     // Catch:{ all -> 0x01a4 }
            java.lang.String r2 = r2.getLanguage()     // Catch:{ all -> 0x01a4 }
            java.lang.String r4 = "len"
            java.lang.String r1 = r1.mo41225b(r2, r0, r4)     // Catch:{ all -> 0x01a4 }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ all -> 0x01a4 }
            boolean r2 = android.text.TextUtils.isEmpty(r9)     // Catch:{ all -> 0x01a4 }
            if (r2 != 0) goto L_0x010d
            int r2 = r9.length()     // Catch:{ all -> 0x01a4 }
            if (r2 <= r1) goto L_0x010d
            java.lang.String r9 = r9.substring(r3, r1)     // Catch:{ all -> 0x01a4 }
        L_0x010d:
            android.widget.Button r1 = r7.f4308bv     // Catch:{ all -> 0x01a4 }
            r1.setText(r9)     // Catch:{ all -> 0x01a4 }
            android.widget.Button r9 = r7.f4308bv     // Catch:{ all -> 0x01a4 }
            r1 = 2
            com.appnext.core.a.b r2 = com.appnext.core.p086a.C4944b.m6738bp()     // Catch:{ all -> 0x01a4 }
            com.appnext.ads.fullscreen.j r4 = r7.f4305bB     // Catch:{ all -> 0x01a4 }
            java.lang.String r4 = r4.getLanguage()     // Catch:{ all -> 0x01a4 }
            java.lang.String r5 = "font_size_sp"
            java.lang.String r0 = r2.mo41225b(r4, r0, r5)     // Catch:{ all -> 0x01a4 }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ all -> 0x01a4 }
            float r0 = (float) r0     // Catch:{ all -> 0x01a4 }
            r9.setTextSize(r1, r0)     // Catch:{ all -> 0x01a4 }
            android.widget.Button r9 = r7.f4308bv     // Catch:{ all -> 0x01a4 }
            com.appnext.ads.fullscreen.g$6 r0 = new com.appnext.ads.fullscreen.g$6     // Catch:{ all -> 0x01a4 }
            r0.<init>()     // Catch:{ all -> 0x01a4 }
            r9.setOnClickListener(r0)     // Catch:{ all -> 0x01a4 }
            android.widget.ImageView r9 = r7.f4307bu     // Catch:{ all -> 0x01a4 }
            com.appnext.ads.fullscreen.g$7 r0 = new com.appnext.ads.fullscreen.g$7     // Catch:{ all -> 0x01a4 }
            r0.<init>()     // Catch:{ all -> 0x01a4 }
            r9.setOnClickListener(r0)     // Catch:{ all -> 0x01a4 }
            android.widget.VideoView r9 = new android.widget.VideoView     // Catch:{ all -> 0x0151 }
            android.app.Activity r0 = r7.getActivity()     // Catch:{ all -> 0x0151 }
            android.content.Context r0 = r0.getApplicationContext()     // Catch:{ all -> 0x0151 }
            r9.<init>(r0)     // Catch:{ all -> 0x0151 }
            r7.videoView = r9     // Catch:{ all -> 0x0151 }
            goto L_0x015c
        L_0x0151:
            android.widget.VideoView r9 = new android.widget.VideoView     // Catch:{ all -> 0x019e }
            android.app.Activity r0 = r7.getActivity()     // Catch:{ all -> 0x019e }
            r9.<init>(r0)     // Catch:{ all -> 0x019e }
            r7.videoView = r9     // Catch:{ all -> 0x019e }
        L_0x015c:
            android.widget.VideoView r9 = r7.videoView     // Catch:{ all -> 0x019e }
            android.view.ViewGroup$LayoutParams r0 = new android.view.ViewGroup$LayoutParams     // Catch:{ all -> 0x019e }
            r1 = -1
            r2 = -2
            r0.<init>(r1, r2)     // Catch:{ all -> 0x019e }
            r9.setLayoutParams(r0)     // Catch:{ all -> 0x019e }
            int r9 = com.appnext.C4703R.C4706id.media     // Catch:{ all -> 0x019e }
            android.view.View r9 = r8.findViewById(r9)     // Catch:{ all -> 0x019e }
            android.view.ViewGroup r9 = (android.view.ViewGroup) r9     // Catch:{ all -> 0x019e }
            android.widget.VideoView r0 = r7.videoView     // Catch:{ all -> 0x019e }
            r9.addView(r0, r3)     // Catch:{ all -> 0x019e }
            android.widget.VideoView r9 = r7.videoView     // Catch:{ all -> 0x019e }
            com.appnext.ads.fullscreen.g$8 r0 = new com.appnext.ads.fullscreen.g$8     // Catch:{ all -> 0x019e }
            r0.<init>(r10)     // Catch:{ all -> 0x019e }
            r9.setOnPreparedListener(r0)     // Catch:{ all -> 0x019e }
            android.widget.VideoView r9 = r7.videoView     // Catch:{ all -> 0x019e }
            com.appnext.ads.fullscreen.g$9 r10 = new com.appnext.ads.fullscreen.g$9     // Catch:{ all -> 0x019e }
            r10.<init>()     // Catch:{ all -> 0x019e }
            r9.setOnCompletionListener(r10)     // Catch:{ all -> 0x019e }
            android.widget.VideoView r9 = r7.videoView     // Catch:{ all -> 0x019e }
            com.appnext.ads.fullscreen.g$10 r10 = new com.appnext.ads.fullscreen.g$10     // Catch:{ all -> 0x019e }
            r10.<init>()     // Catch:{ all -> 0x019e }
            r9.setOnErrorListener(r10)     // Catch:{ all -> 0x019e }
            android.widget.VideoView r9 = r7.videoView     // Catch:{ all -> 0x019e }
            com.appnext.ads.fullscreen.j r10 = r7.f4305bB     // Catch:{ all -> 0x019e }
            android.net.Uri r10 = r10.getSelectedVideoUri()     // Catch:{ all -> 0x019e }
            r9.setVideoURI(r10)     // Catch:{ all -> 0x019e }
        L_0x019e:
            java.lang.String r9 = "roll_loaded"
            r7.report(r9)     // Catch:{ all -> 0x01a4 }
            return r8
        L_0x01a4:
            com.appnext.ads.fullscreen.j r8 = r7.f4305bB
            r8.closeClicked()
            r8 = 0
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.ads.fullscreen.C4746g.onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle):android.view.View");
    }

    /* renamed from: r */
    private void m6338r() {
        try {
            if (this.mediaPlayer != null && this.mediaPlayer.getCurrentPosition() != 0 && this.mediaPlayer.getDuration() != 0 && !this.finished) {
                StringBuilder sb = new StringBuilder("onCompletion. ");
                sb.append(this.mediaPlayer.getCurrentPosition());
                sb.append("/");
                sb.append(this.mediaPlayer.getDuration());
                this.finished = true;
                C4764j jVar = this.f4305bB;
                if (jVar != null) {
                    jVar.videoEnded();
                }
                report(C4708a.f4163M);
            }
        } catch (Throwable unused) {
        }
    }

    public final void onPause() {
        super.onPause();
        this.mHandler.removeCallbacks(this.tick);
        VideoView videoView2 = this.videoView;
        if (videoView2 != null) {
            videoView2.pause();
            this.currentPosition = this.videoView.getCurrentPosition();
        }
    }

    public final void onResume() {
        super.onResume();
        if (this.videoView != null && !this.finished) {
            try {
                this.mediaPlayer.seekTo(this.currentPosition);
                this.mediaPlayer.start();
                this.mHandler.postDelayed(this.tick, 33);
            } catch (Throwable unused) {
            }
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("currentPosition", this.currentPosition);
        bundle.putBoolean("started", this.started);
        super.onSaveInstanceState(bundle);
    }

    public final void onViewStateRestored(Bundle bundle) {
        super.onViewStateRestored(bundle);
        if (bundle != null) {
            this.currentPosition = bundle.getInt("currentPosition");
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:0|1|2|(1:4)|5|6|(2:8|10)(1:12)) */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001e */
    /* JADX WARNING: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0022 A[Catch:{ all -> 0x0029 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onDestroyView() {
        /*
            r2 = this;
            super.onDestroyView()
            r0 = 0
            android.widget.VideoView r1 = r2.videoView     // Catch:{ all -> 0x001e }
            if (r1 == 0) goto L_0x001e
            android.widget.VideoView r1 = r2.videoView     // Catch:{ all -> 0x001e }
            r1.setOnCompletionListener(r0)     // Catch:{ all -> 0x001e }
            android.widget.VideoView r1 = r2.videoView     // Catch:{ all -> 0x001e }
            r1.setOnErrorListener(r0)     // Catch:{ all -> 0x001e }
            android.widget.VideoView r1 = r2.videoView     // Catch:{ all -> 0x001e }
            r1.setOnPreparedListener(r0)     // Catch:{ all -> 0x001e }
            android.widget.VideoView r1 = r2.videoView     // Catch:{ all -> 0x001e }
            r1.suspend()     // Catch:{ all -> 0x001e }
            r2.videoView = r0     // Catch:{ all -> 0x001e }
        L_0x001e:
            android.media.MediaPlayer r1 = r2.mediaPlayer     // Catch:{ all -> 0x0029 }
            if (r1 == 0) goto L_0x0029
            android.media.MediaPlayer r1 = r2.mediaPlayer     // Catch:{ all -> 0x0029 }
            r1.release()     // Catch:{ all -> 0x0029 }
            r2.mediaPlayer = r0     // Catch:{ all -> 0x0029 }
        L_0x0029:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.ads.fullscreen.C4746g.onDestroyView():void");
    }

    /* renamed from: com.appnext.ads.fullscreen.g$a */
    private class C4761a extends Animation {

        /* renamed from: bH */
        final int f4329bH;

        /* renamed from: bI */
        int f4330bI;
        View view;

        public final boolean willChangeBounds() {
            return true;
        }

        C4761a(View view2, int i, int i2) {
            this.view = view2;
            this.f4329bH = i;
            this.f4330bI = i2;
        }

        /* access modifiers changed from: protected */
        public final void applyTransformation(float f, Transformation transformation) {
            ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
            int i = this.f4330bI;
            layoutParams.width = (int) (((float) i) + (((float) (this.f4329bH - i)) * f));
            this.view.requestLayout();
        }
    }

    private void checkProgress() {
        int currentPosition2 = (int) ((((float) this.mediaPlayer.getCurrentPosition()) / ((float) this.mediaPlayer.getDuration())) * 100.0f);
        if (currentPosition2 > 25 && this.lastProgress == 0) {
            this.lastProgress = 25;
            report(C4708a.f4160J);
        } else if (currentPosition2 > 50 && this.lastProgress == 25) {
            this.lastProgress = 50;
            report(C4708a.f4161K);
        } else if (currentPosition2 > 75 && this.lastProgress == 50) {
            this.lastProgress = 75;
            report(C4708a.f4162L);
        }
    }

    /* renamed from: s */
    private void m6339s() {
        Animation loadAnimation = AnimationUtils.loadAnimation(getActivity(), C4703R.C4704anim.apnxt_stream_loader);
        this.f4311by = loadAnimation;
        loadAnimation.setAnimationListener(new Animation.AnimationListener() {
            public final void onAnimationRepeat(Animation animation) {
            }

            public final void onAnimationStart(Animation animation) {
            }

            public final void onAnimationEnd(Animation animation) {
                if (C4746g.this.f4310bx.getVisibility() != 8) {
                    C4746g.this.f4310bx.startAnimation(C4746g.this.f4311by);
                }
            }
        });
        this.f4311by.setRepeatCount(-1);
        this.f4311by.setRepeatMode(1);
    }

    /* access modifiers changed from: private */
    public void report(String str) {
        this.f4305bB.report(str, "S2");
    }

    /* renamed from: l */
    static /* synthetic */ void m6333l(C4746g gVar) {
        try {
            if (gVar.mediaPlayer != null && gVar.mediaPlayer.getCurrentPosition() != 0 && gVar.mediaPlayer.getDuration() != 0 && !gVar.finished) {
                StringBuilder sb = new StringBuilder("onCompletion. ");
                sb.append(gVar.mediaPlayer.getCurrentPosition());
                sb.append("/");
                sb.append(gVar.mediaPlayer.getDuration());
                gVar.finished = true;
                C4764j jVar = gVar.f4305bB;
                if (jVar != null) {
                    jVar.videoEnded();
                }
                gVar.report(C4708a.f4163M);
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: n */
    static /* synthetic */ void m6335n(C4746g gVar) {
        int currentPosition2 = (int) ((((float) gVar.mediaPlayer.getCurrentPosition()) / ((float) gVar.mediaPlayer.getDuration())) * 100.0f);
        if (currentPosition2 > 25 && gVar.lastProgress == 0) {
            gVar.lastProgress = 25;
            gVar.report(C4708a.f4160J);
        } else if (currentPosition2 > 50 && gVar.lastProgress == 25) {
            gVar.lastProgress = 50;
            gVar.report(C4708a.f4161K);
        } else if (currentPosition2 > 75 && gVar.lastProgress == 50) {
            gVar.lastProgress = 75;
            gVar.report(C4708a.f4162L);
        }
    }
}
