package com.tappx.p048a;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import com.tappx.p048a.C1419g1;
import com.tappx.p048a.C1454i1;
import com.tappx.p048a.C1500l0;
import com.tappx.p048a.C1522m1;
import com.tappx.p048a.C1559p0;

/* renamed from: com.tappx.a.p */
public class C1557p {

    /* renamed from: a */
    private final Context f2157a;

    /* renamed from: com.tappx.a.p$a */
    private static final class C1558a extends AsyncTask<Void, Void, Void> {

        /* renamed from: a */
        private final Context f2158a;

        public C1558a(Context context) {
            this.f2158a = context;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            C1559p0.C1561b.m3186a(this.f2158a).mo16045a();
            C1522m1.C1523a.m3023a(this.f2158a).mo15965b();
            C1454i1.C1455a.m2837a(this.f2158a).mo15864a();
            C1500l0.C1501a.m2949a(this.f2158a).mo15927a();
            C1419g1.C1421b.m2693a(this.f2158a).mo15820a();
            return null;
        }
    }

    public C1557p(Context context) {
        this.f2157a = context.getApplicationContext();
    }

    /* renamed from: a */
    public void mo16042a() {
        C1558a aVar = new C1558a(this.f2157a);
        if (Build.VERSION.SDK_INT >= 11) {
            aVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            aVar.execute(new Void[0]);
        }
    }
}
