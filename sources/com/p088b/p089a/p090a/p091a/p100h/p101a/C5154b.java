package com.p088b.p089a.p090a.p091a.p100h.p101a;

import android.os.AsyncTask;
import java.util.concurrent.ThreadPoolExecutor;
import org.json.JSONObject;

/* renamed from: com.b.a.a.a.h.a.b */
public abstract class C5154b extends AsyncTask<Object, Void, String> {

    /* renamed from: a */
    private C5155a f5032a;

    /* renamed from: d */
    protected final C5156b f5033d;

    /* renamed from: com.b.a.a.a.h.a.b$a */
    public interface C5155a {
        /* renamed from: a */
        void mo41937a(C5154b bVar);
    }

    /* renamed from: com.b.a.a.a.h.a.b$b */
    public interface C5156b {
        /* renamed from: a */
        JSONObject mo41938a();

        /* renamed from: a */
        void mo41939a(JSONObject jSONObject);
    }

    public C5154b(C5156b bVar) {
        this.f5033d = bVar;
    }

    /* renamed from: a */
    public void mo41933a(C5155a aVar) {
        this.f5032a = aVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(String str) {
        C5155a aVar = this.f5032a;
        if (aVar != null) {
            aVar.mo41937a(this);
        }
    }

    /* renamed from: a */
    public void mo41935a(ThreadPoolExecutor threadPoolExecutor) {
        executeOnExecutor(threadPoolExecutor, new Object[0]);
    }
}
