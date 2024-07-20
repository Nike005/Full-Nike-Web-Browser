package com.moat.analytics.mobile.mpub;

import android.media.MediaPlayer;
import android.view.View;
import com.mopub.mobileads.VastIconXmlManager;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.htmlcleaner.CleanerProperties;

/* renamed from: com.moat.analytics.mobile.mpub.u */
class C0344u extends C0316h implements NativeVideoTracker {

    /* renamed from: m */
    private WeakReference<MediaPlayer> f194m;

    C0344u(String str) {
        super(str);
        C0336p.m228a(3, "NativeVideoTracker", (Object) this, "In initialization method.");
        if (str == null || str.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("PartnerCode is ");
            sb.append(str == null ? "null" : CleanerProperties.BOOL_ATT_EMPTY);
            String sb2 = sb.toString();
            C0336p.m229a("[ERROR] ", 3, "NativeVideoTracker", this, "NativeDisplayTracker creation problem, " + sb2);
            this.f61a = new C0330n(sb2);
        }
        C0336p.m231a("[SUCCESS] ", mo10380a() + " created");
    }

    /* renamed from: a */
    private void m255a(MediaPlayer mediaPlayer) {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.getCurrentPosition();
            } catch (Exception unused) {
                throw new C0330n("Playback has already completed");
            }
        } else {
            throw new C0330n("Null player instance");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public String mo10380a() {
        return "NativeVideoTracker";
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo10384a(List<String> list) {
        if (!mo10419n()) {
            list.add("Player is null");
        }
        super.mo10384a(list);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: i */
    public Map<String, Object> mo10402i() {
        MediaPlayer mediaPlayer = (MediaPlayer) this.f194m.get();
        HashMap hashMap = new HashMap();
        hashMap.put("width", Integer.valueOf(mediaPlayer.getVideoWidth()));
        hashMap.put("height", Integer.valueOf(mediaPlayer.getVideoHeight()));
        hashMap.put(VastIconXmlManager.DURATION, Integer.valueOf(mediaPlayer.getDuration()));
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: n */
    public boolean mo10419n() {
        WeakReference<MediaPlayer> weakReference = this.f194m;
        return (weakReference == null || weakReference.get() == null) ? false : true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: o */
    public Integer mo10420o() {
        return Integer.valueOf(((MediaPlayer) this.f194m.get()).getCurrentPosition());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: q */
    public boolean mo10422q() {
        return ((MediaPlayer) this.f194m.get()).isPlaying();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: r */
    public Integer mo10423r() {
        return Integer.valueOf(((MediaPlayer) this.f194m.get()).getDuration());
    }

    public boolean trackVideoAd(Map<String, String> map, MediaPlayer mediaPlayer, View view) {
        try {
            mo10386c();
            mo10388d();
            m255a(mediaPlayer);
            this.f194m = new WeakReference<>(mediaPlayer);
            return super.mo10400a(map, view);
        } catch (Exception e) {
            C0330n.m214a(e);
            String a = C0330n.m212a("trackVideoAd", e);
            if (this.f64d != null) {
                this.f64d.onTrackingFailedToStart(a);
            }
            C0336p.m228a(3, "NativeVideoTracker", (Object) this, a);
            C0336p.m231a("[ERROR] ", mo10380a() + StringUtils.SPACE + a);
            return false;
        }
    }
}
