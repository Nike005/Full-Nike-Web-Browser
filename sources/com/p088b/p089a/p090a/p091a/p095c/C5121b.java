package com.p088b.p089a.p090a.p091a.p095c;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.p088b.p089a.p090a.p091a.p093b.C5118i;

/* renamed from: com.b.a.a.a.c.b */
public class C5121b {

    /* renamed from: a */
    private static C5121b f4969a = new C5121b();

    /* renamed from: b */
    private Context f4970b;

    /* renamed from: c */
    private BroadcastReceiver f4971c;

    /* renamed from: d */
    private boolean f4972d;

    /* renamed from: e */
    private boolean f4973e;

    /* renamed from: f */
    private C5123a f4974f;

    /* renamed from: com.b.a.a.a.c.b$a */
    public interface C5123a {
        /* renamed from: a */
        void mo41877a(boolean z);
    }

    private C5121b() {
    }

    /* renamed from: a */
    public static C5121b m7057a() {
        return f4969a;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m7059a(boolean z) {
        if (this.f4973e != z) {
            this.f4973e = z;
            if (this.f4972d) {
                m7062g();
                C5123a aVar = this.f4974f;
                if (aVar != null) {
                    aVar.mo41877a(mo41875d());
                }
            }
        }
    }

    /* renamed from: e */
    private void m7060e() {
        this.f4971c = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                KeyguardManager keyguardManager;
                if (intent != null) {
                    if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                        C5121b.this.m7059a(true);
                    } else if ("android.intent.action.USER_PRESENT".equals(intent.getAction()) || ("android.intent.action.SCREEN_ON".equals(intent.getAction()) && (keyguardManager = (KeyguardManager) context.getSystemService("keyguard")) != null && !keyguardManager.inKeyguardRestrictedInputMode())) {
                        C5121b.this.m7059a(false);
                    }
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        this.f4970b.registerReceiver(this.f4971c, intentFilter);
    }

    /* renamed from: f */
    private void m7061f() {
        BroadcastReceiver broadcastReceiver;
        Context context = this.f4970b;
        if (context != null && (broadcastReceiver = this.f4971c) != null) {
            context.unregisterReceiver(broadcastReceiver);
            this.f4971c = null;
        }
    }

    /* renamed from: g */
    private void m7062g() {
        boolean z = !this.f4973e;
        for (C5118i f : C5120a.m7050a().mo41866b()) {
            f.mo41851f().mo41913a(z);
        }
    }

    /* renamed from: a */
    public void mo41871a(Context context) {
        this.f4970b = context.getApplicationContext();
    }

    /* renamed from: a */
    public void mo41872a(C5123a aVar) {
        this.f4974f = aVar;
    }

    /* renamed from: b */
    public void mo41873b() {
        m7060e();
        this.f4972d = true;
        m7062g();
    }

    /* renamed from: c */
    public void mo41874c() {
        m7061f();
        this.f4972d = false;
        this.f4973e = false;
        this.f4974f = null;
    }

    /* renamed from: d */
    public boolean mo41875d() {
        return !this.f4973e;
    }
}
