package com.yandex.metrica.impl;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* renamed from: com.yandex.metrica.impl.af */
public class C1817af implements C1816ae {

    /* renamed from: a */
    private Executor f2896a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public C1816ae f2897b;

    /* renamed from: com.yandex.metrica.impl.af$a */
    abstract class C1826a implements Runnable {
        /* renamed from: a */
        public abstract void mo16791a() throws Exception;

        C1826a() {
        }

        public void run() {
            try {
                mo16791a();
            } catch (Exception unused) {
            }
        }
    }

    public C1817af(C1816ae aeVar) {
        this(Executors.newSingleThreadExecutor(), aeVar);
    }

    /* renamed from: a */
    public void mo16782a() {
        this.f2896a.execute(new C1826a() {
            /* renamed from: a */
            public void mo16791a() {
                C1817af.this.f2897b.mo16782a();
            }
        });
    }

    /* renamed from: a */
    public void mo16786a(final Intent intent, final int i) {
        this.f2896a.execute(new C1826a() {
            /* renamed from: a */
            public void mo16791a() {
                C1817af.this.f2897b.mo16786a(intent, i);
            }
        });
    }

    /* renamed from: a */
    public void mo16787a(final Intent intent, final int i, final int i2) {
        this.f2896a.execute(new C1826a() {
            /* renamed from: a */
            public void mo16791a() {
                C1817af.this.f2897b.mo16787a(intent, i, i2);
            }
        });
    }

    /* renamed from: a */
    public void mo16785a(final Intent intent) {
        this.f2896a.execute(new C1826a() {
            /* renamed from: a */
            public void mo16791a() {
                C1817af.this.f2897b.mo16785a(intent);
            }
        });
    }

    /* renamed from: b */
    public void mo16789b(final Intent intent) {
        this.f2896a.execute(new C1826a() {
            /* renamed from: a */
            public void mo16791a() {
                C1817af.this.f2897b.mo16789b(intent);
            }
        });
    }

    /* renamed from: c */
    public void mo16790c(final Intent intent) {
        this.f2896a.execute(new C1826a() {
            /* renamed from: a */
            public void mo16791a() {
                C1817af.this.f2897b.mo16790c(intent);
            }
        });
    }

    /* renamed from: b */
    public void mo16788b() {
        this.f2897b.mo16788b();
    }

    /* renamed from: a */
    public void mo16784a(int i, String str, int i2, String str2, Bundle bundle) throws RemoteException {
        final int i3 = i;
        final String str3 = str;
        final int i4 = i2;
        final String str4 = str2;
        final Bundle bundle2 = bundle;
        this.f2896a.execute(new C1826a() {
            /* renamed from: a */
            public void mo16791a() throws RemoteException {
                C1817af.this.f2897b.mo16784a(i3, str3, i4, str4, bundle2);
            }
        });
    }

    /* renamed from: a */
    public void mo16783a(final int i, final Bundle bundle) throws RemoteException {
        this.f2896a.execute(new C1826a() {
            /* renamed from: a */
            public void mo16791a() throws Exception {
                C1817af.this.f2897b.mo16783a(i, bundle);
            }
        });
    }

    C1817af(Executor executor, C1816ae aeVar) {
        this.f2896a = executor;
        this.f2897b = aeVar;
    }
}
