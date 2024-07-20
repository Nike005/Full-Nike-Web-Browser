package com.startapp.android.publish.ads.video;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.startapp.android.publish.ads.video.C0984c;
import com.startapp.android.publish.adsCommon.C1098b;
import java.net.URL;

/* renamed from: com.startapp.android.publish.ads.video.g */
/* compiled from: StartAppSDK */
public class C1035g {

    /* renamed from: a */
    protected Context f937a;

    /* renamed from: b */
    protected URL f938b;

    /* renamed from: c */
    protected String f939c;

    /* renamed from: d */
    protected C1037a f940d;

    /* renamed from: e */
    protected C0984c.C0987a f941e;

    /* renamed from: com.startapp.android.publish.ads.video.g$a */
    /* compiled from: StartAppSDK */
    public interface C1037a {
        /* renamed from: a */
        void mo14262a(String str);
    }

    public C1035g(Context context, URL url, String str, C1037a aVar, C0984c.C0987a aVar2) {
        this.f937a = context;
        this.f938b = url;
        this.f939c = str;
        this.f940d = aVar;
        this.f941e = aVar2;
    }

    /* renamed from: a */
    public void mo14415a() {
        final String str;
        try {
            str = C1098b.m1303a().mo14754H().mo15031i() ? C0984c.m898a().mo14291a(this.f937a, this.f938b, this.f939c, this.f941e) : C1038h.m1116a(this.f937a, this.f938b, this.f939c);
        } catch (Exception unused) {
            str = null;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                if (C1035g.this.f940d != null) {
                    C1035g.this.f940d.mo14262a(str);
                }
            }
        });
    }
}
