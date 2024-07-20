package com.p088b.p089a.p090a.p091a.p092a;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import android.provider.Settings;

/* renamed from: com.b.a.a.a.a.d */
public final class C5106d extends ContentObserver {

    /* renamed from: a */
    private final Context f4928a;

    /* renamed from: b */
    private final AudioManager f4929b;

    /* renamed from: c */
    private final C5103a f4930c;

    /* renamed from: d */
    private final C5105c f4931d;

    /* renamed from: e */
    private float f4932e;

    public C5106d(Handler handler, Context context, C5103a aVar, C5105c cVar) {
        super(handler);
        this.f4928a = context;
        this.f4929b = (AudioManager) context.getSystemService("audio");
        this.f4930c = aVar;
        this.f4931d = cVar;
    }

    /* renamed from: a */
    private boolean m6976a(float f) {
        return f != this.f4932e;
    }

    /* renamed from: c */
    private float m6977c() {
        return this.f4930c.mo41810a(this.f4929b.getStreamVolume(3), this.f4929b.getStreamMaxVolume(3));
    }

    /* renamed from: d */
    private void m6978d() {
        this.f4931d.mo41812a(this.f4932e);
    }

    /* renamed from: a */
    public void mo41813a() {
        this.f4932e = m6977c();
        m6978d();
        this.f4928a.getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, this);
    }

    /* renamed from: b */
    public void mo41814b() {
        this.f4928a.getContentResolver().unregisterContentObserver(this);
    }

    public void onChange(boolean z) {
        super.onChange(z);
        float c = m6977c();
        if (m6976a(c)) {
            this.f4932e = c;
            m6978d();
        }
    }
}
