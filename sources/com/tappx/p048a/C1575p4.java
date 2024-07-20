package com.tappx.p048a;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.StateListDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import com.tappx.p048a.C1543n3;

/* renamed from: com.tappx.a.p4 */
public class C1575p4 extends C1543n3 {

    /* renamed from: e */
    private final VideoView f2217e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public View f2218f;

    /* renamed from: com.tappx.a.p4$a */
    class C1576a implements MediaPlayer.OnCompletionListener {
        C1576a() {
        }

        public void onCompletion(MediaPlayer mediaPlayer) {
            if (C1575p4.this.f2218f != null) {
                C1575p4.this.f2218f.setVisibility(0);
            }
            C1575p4.this.mo16003a(true);
        }
    }

    /* renamed from: com.tappx.a.p4$b */
    class C1577b implements MediaPlayer.OnErrorListener {
        C1577b() {
        }

        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            if (C1575p4.this.f2218f != null) {
                C1575p4.this.f2218f.setVisibility(0);
            }
            C1575p4.this.mo16006b(false);
            return false;
        }
    }

    /* renamed from: com.tappx.a.p4$c */
    class C1578c implements View.OnClickListener {
        C1578c() {
        }

        public void onClick(View view) {
            C1575p4.this.mo16005b().onFinish();
        }
    }

    public C1575p4(Context context, Bundle bundle, Bundle bundle2, C1543n3.C1544a aVar) {
        super(context, aVar);
        VideoView videoView = new VideoView(context);
        this.f2217e = videoView;
        videoView.setOnCompletionListener(new C1576a());
        this.f2217e.setOnErrorListener(new C1577b());
        this.f2217e.setVideoPath(bundle.getString("video_url"));
    }

    /* renamed from: k */
    private void m3229k() {
        this.f2218f = new View(mo16007c());
        int b = C1588q3.m3286b(30.0f, mo16007c());
        int b2 = C1588q3.m3286b(10.0f, mo16007c());
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{-16842919}, C1393e4.INTERSTITIAL_CLOSE_BUTTON_NORMAL.mo15742a(mo16007c()));
        stateListDrawable.addState(new int[]{16842919}, C1393e4.INTERSTITIAL_CLOSE_BUTTON_PRESSED.mo15742a(mo16007c()));
        this.f2218f.setBackgroundDrawable(stateListDrawable);
        this.f2218f.setOnClickListener(new C1578c());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(b, b);
        layoutParams.addRule(11);
        layoutParams.setMargins(b2, b2, b2, b2);
        mo16008d().addView(this.f2218f, layoutParams);
    }

    /* renamed from: a */
    public void mo16001a(Configuration configuration) {
    }

    /* renamed from: a */
    public void mo16002a(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public VideoView mo16009e() {
        return this.f2217e;
    }

    /* renamed from: f */
    public void mo16010f() {
    }

    /* renamed from: g */
    public void mo16011g() {
        super.mo16011g();
        m3229k();
        this.f2218f.setVisibility(8);
        this.f2217e.start();
    }

    /* renamed from: h */
    public void mo16012h() {
    }

    /* renamed from: i */
    public void mo16013i() {
    }

    /* renamed from: j */
    public void mo16014j() {
    }
}
