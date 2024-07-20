package com.startapp.android.publish.adsCommon.p033f;

import android.content.Context;
import com.startapp.android.publish.adsCommon.p039h.C1149a;
import com.startapp.android.publish.adsCommon.p039h.C1151b;
import com.startapp.android.publish.adsCommon.p039h.C1153c;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.common.C1298d;
import com.startapp.common.p043a.C1270g;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.startapp.android.publish.adsCommon.f.c */
/* compiled from: StartAppSDK */
public class C1128c {

    /* renamed from: f */
    static AtomicBoolean f1159f = new AtomicBoolean(false);

    /* renamed from: a */
    Context f1160a;

    /* renamed from: b */
    C1298d f1161b;

    /* renamed from: c */
    ArrayList<C1149a> f1162c;

    /* renamed from: d */
    int f1163d;

    /* renamed from: e */
    C1127b f1164e;

    /* renamed from: g */
    private Runnable f1165g;

    public C1128c(Context context, boolean z) {
        this(context, z, (C1298d) null);
    }

    public C1128c(Context context, boolean z, C1298d dVar) {
        this.f1165g = new Runnable() {
            public void run() {
                synchronized (this) {
                    C1128c cVar = C1128c.this;
                    int i = cVar.f1163d - 1;
                    cVar.f1163d = i;
                    if (i == 0) {
                        C1270g.m2078a("DataEventTask", 3, "sending DataEvent");
                        C1132f.m1529a(C1128c.this.f1160a, C1128c.this.f1164e, "");
                        C1128c.f1159f.set(false);
                        C1128c.this.mo14881b();
                    }
                }
            }
        };
        this.f1160a = context;
        this.f1161b = dVar;
        this.f1162c = new ArrayList<>();
        C1127b bVar = new C1127b(C1130d.PERIODIC);
        this.f1164e = bVar;
        bVar.mo14874a(z);
        if (MetaData.getInstance().getSensorsConfig().mo15252b()) {
            this.f1162c.add(new C1153c(context, this.f1165g, this.f1164e));
        }
        if (MetaData.getInstance().getBluetoothConfig().mo15243b()) {
            this.f1162c.add(new C1151b(context, this.f1165g, this.f1164e));
        }
        this.f1163d = this.f1162c.size();
    }

    /* renamed from: a */
    public void mo14880a() {
        if (this.f1163d > 0) {
            if (f1159f.compareAndSet(false, true)) {
                for (int i = 0; i < this.f1163d; i++) {
                    this.f1162c.get(i).mo14938a();
                }
                return;
            }
        }
        mo14881b();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo14881b() {
        C1298d dVar = this.f1161b;
        if (dVar != null) {
            dVar.mo14939a((Object) null);
        }
    }

    /* renamed from: c */
    public C1127b mo14882c() {
        return this.f1164e;
    }
}
