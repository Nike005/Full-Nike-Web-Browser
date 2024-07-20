package com.startapp.android.publish.ads.video.p023b;

import android.media.MediaPlayer;
import android.widget.VideoView;
import com.startapp.android.publish.ads.video.C0984c;
import com.startapp.android.publish.ads.video.p023b.C0975c;
import com.startapp.android.publish.adsCommon.C1103c;
import com.startapp.common.p043a.C1270g;

/* renamed from: com.startapp.android.publish.ads.video.b.b */
/* compiled from: StartAppSDK */
public class C0971b extends C0970a implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {

    /* renamed from: h */
    private MediaPlayer f699h;

    /* renamed from: i */
    private VideoView f700i;

    /* renamed from: com.startapp.android.publish.ads.video.b.b$b */
    /* compiled from: StartAppSDK */
    public enum C0974b {
        MEDIA_ERROR_UNKNOWN,
        MEDIA_ERROR_SERVER_DIED;

        /* renamed from: a */
        public static C0974b m873a(int i) {
            if (i == 100) {
                return MEDIA_ERROR_SERVER_DIED;
            }
            return MEDIA_ERROR_UNKNOWN;
        }
    }

    /* renamed from: com.startapp.android.publish.ads.video.b.b$a */
    /* compiled from: StartAppSDK */
    public enum C0973a {
        MEDIA_ERROR_IO,
        MEDIA_ERROR_MALFORMED,
        MEDIA_ERROR_UNSUPPORTED,
        MEDIA_ERROR_TIMED_OUT;

        /* renamed from: a */
        public static C0973a m872a(int i) {
            if (i == -1010) {
                return MEDIA_ERROR_UNSUPPORTED;
            }
            if (i == -1007) {
                return MEDIA_ERROR_MALFORMED;
            }
            if (i == -1004) {
                return MEDIA_ERROR_IO;
            }
            if (i != -110) {
                return MEDIA_ERROR_IO;
            }
            return MEDIA_ERROR_TIMED_OUT;
        }
    }

    public C0971b(VideoView videoView) {
        C1270g.m2078a("NativeVideoPlayer", 4, "Ctor");
        this.f700i = videoView;
        videoView.setOnPreparedListener(this);
        this.f700i.setOnCompletionListener(this);
        this.f700i.setOnErrorListener(this);
    }

    /* renamed from: a */
    public void mo14271a() {
        C1270g.m2078a("NativeVideoPlayer", 4, "start");
        this.f700i.start();
    }

    /* renamed from: a */
    public void mo14272a(int i) {
        C1270g.m2078a("NativeVideoPlayer", 4, "seekTo(" + i + ")");
        this.f700i.seekTo(i);
    }

    /* renamed from: b */
    public void mo14274b() {
        C1270g.m2078a("NativeVideoPlayer", 4, "pause");
        this.f700i.pause();
    }

    /* renamed from: c */
    public void mo14275c() {
        C1270g.m2078a("NativeVideoPlayer", 4, "stop");
        this.f700i.stopPlayback();
    }

    /* renamed from: a */
    public void mo14273a(boolean z) {
        C1270g.m2078a("NativeVideoPlayer", 4, "setMute(" + z + ")");
        MediaPlayer mediaPlayer = this.f699h;
        if (mediaPlayer == null) {
            return;
        }
        if (z) {
            mediaPlayer.setVolume(0.0f, 0.0f);
        } else {
            mediaPlayer.setVolume(1.0f, 1.0f);
        }
    }

    /* renamed from: d */
    public int mo14276d() {
        return this.f700i.getCurrentPosition();
    }

    /* renamed from: e */
    public int mo14277e() {
        return this.f700i.getDuration();
    }

    /* renamed from: f */
    public boolean mo14278f() {
        return this.f699h != null;
    }

    /* renamed from: a */
    public void mo14270a(String str) {
        C1270g.m2078a("NativeVideoPlayer", 4, "setVideoLocation(" + str + ")");
        super.mo14270a(str);
        this.f700i.setVideoPath(this.f692a);
    }

    /* renamed from: g */
    public void mo14279g() {
        if (this.f699h != null) {
            this.f699h = null;
        }
        C0984c.m898a().mo14292a((C0975c.C0978c) null);
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        MediaPlayer mediaPlayer2;
        C1270g.m2078a("NativeVideoPlayer", 4, "onPrepared");
        this.f699h = mediaPlayer;
        if (this.f693b != null) {
            C1270g.m2078a("NativeVideoPlayer", 3, "Dispatching onPrepared");
            this.f693b.mo14287a();
        }
        if (C1103c.m1403d(this.f692a) && (mediaPlayer2 = this.f699h) != null) {
            mediaPlayer2.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                    if (C0971b.this.f697f != null) {
                        C1270g.m2078a("NativeVideoPlayer", 4, "onBufferingUpdate");
                        C0971b.this.f697f.mo14284a(i);
                    }
                }
            });
        } else if (!C1103c.m1403d(this.f692a)) {
            C0984c.m898a().mo14292a(this.f697f);
        }
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        C1270g.m2078a("NativeVideoPlayer", 4, "onCompletion");
        if (this.f695d != null) {
            C1270g.m2078a("NativeVideoPlayer", 3, "Dispatching onCompletion");
            this.f695d.mo14285a();
        }
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        C1270g.m2078a("NativeVideoPlayer", 6, "onError(" + i + ", " + i2 + ")");
        if (this.f694c == null) {
            return false;
        }
        C1270g.m2078a("NativeVideoPlayer", 3, "Dispatching onError");
        return this.f694c.mo14286a(m861a(i, i2, mediaPlayer != null ? mediaPlayer.getCurrentPosition() : -1));
    }

    /* renamed from: a */
    private C0975c.C0982g m861a(int i, int i2, int i3) {
        return new C0975c.C0982g(C0974b.m873a(i) == C0974b.MEDIA_ERROR_SERVER_DIED ? C0975c.C0983h.SERVER_DIED : C0975c.C0983h.UNKNOWN, C0973a.m872a(i2).toString(), i3);
    }
}
