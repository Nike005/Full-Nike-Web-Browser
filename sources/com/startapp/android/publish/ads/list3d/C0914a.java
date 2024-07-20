package com.startapp.android.publish.ads.list3d;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import com.startapp.android.publish.adsCommon.C1155i;
import com.startapp.android.publish.adsCommon.p031d.C1117b;
import com.startapp.common.p043a.C1265d;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

/* renamed from: com.startapp.android.publish.ads.list3d.a */
/* compiled from: StartAppSDK */
public class C0914a {

    /* renamed from: a */
    HashMap<String, C1155i> f540a = new HashMap<>();

    /* renamed from: b */
    Hashtable<String, Bitmap> f541b = new Hashtable<>();

    /* renamed from: c */
    Set<String> f542c = new HashSet();

    /* renamed from: d */
    C0927g f543d;

    /* renamed from: e */
    int f544e = 0;

    /* renamed from: f */
    ConcurrentLinkedQueue<C0916b> f545f = new ConcurrentLinkedQueue<>();

    /* renamed from: a */
    public void mo14040a(C0927g gVar, boolean z) {
        this.f543d = gVar;
        if (z) {
            mo14038a();
        }
    }

    /* renamed from: a */
    public void mo14038a() {
        this.f542c.clear();
        this.f544e = 0;
        this.f545f.clear();
        HashMap<String, C1155i> hashMap = this.f540a;
        if (hashMap != null) {
            for (String str : hashMap.keySet()) {
                this.f540a.get(str).mo14946a(false);
            }
            this.f540a.clear();
        }
    }

    /* renamed from: a */
    public void mo14039a(Context context, String str, String str2, C1117b bVar, long j) {
        if (!this.f540a.containsKey(str2)) {
            C1155i iVar = new C1155i(context, new String[]{str2}, bVar, j);
            this.f540a.put(str2, iVar);
            iVar.mo14945a();
        }
    }

    /* renamed from: a */
    public void mo14041a(String str) {
        HashMap<String, C1155i> hashMap = this.f540a;
        if (hashMap != null && hashMap.containsKey(str) && this.f540a.get(str) != null) {
            this.f540a.get(str).mo14946a(true);
        }
    }

    /* renamed from: b */
    public void mo14042b() {
        for (String next : this.f540a.keySet()) {
            if (this.f540a.get(next) != null) {
                this.f540a.get(next).mo14947b();
            }
        }
    }

    /* renamed from: c */
    public void mo14043c() {
        for (String next : this.f540a.keySet()) {
            if (this.f540a.get(next) != null) {
                this.f540a.get(next).mo14945a();
            }
        }
    }

    /* renamed from: d */
    public void mo14044d() {
        for (String next : this.f540a.keySet()) {
            if (this.f540a.get(next) != null) {
                this.f540a.get(next).mo14946a(false);
            }
        }
    }

    /* renamed from: a */
    public Bitmap mo14037a(int i, String str, String str2) {
        Bitmap bitmap = this.f541b.get(str);
        if (bitmap != null) {
            return bitmap;
        }
        if (this.f542c.contains(str)) {
            return null;
        }
        this.f542c.add(str);
        int i2 = this.f544e;
        if (i2 >= 15) {
            this.f545f.add(new C0916b(i, str, str2));
            return null;
        }
        this.f544e = i2 + 1;
        new C0915a(i, str, str2).execute(new Void[0]);
        return null;
    }

    /* renamed from: e */
    public void mo14045e() {
        if (!this.f545f.isEmpty()) {
            C0916b poll = this.f545f.poll();
            new C0915a(poll.f550a, poll.f551b, poll.f552c).execute(new Void[0]);
        }
    }

    /* renamed from: com.startapp.android.publish.ads.list3d.a$a */
    /* compiled from: StartAppSDK */
    private class C0915a extends AsyncTask<Void, Void, Bitmap> {

        /* renamed from: a */
        int f546a = -1;

        /* renamed from: b */
        String f547b;

        /* renamed from: c */
        String f548c;

        public C0915a(int i, String str, String str2) {
            this.f546a = i;
            this.f547b = str;
            this.f548c = str2;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Bitmap doInBackground(Void... voidArr) {
            return C1265d.m2056b(this.f548c);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Bitmap bitmap) {
            C0914a aVar = C0914a.this;
            aVar.f544e--;
            if (bitmap != null) {
                C0914a.this.f541b.put(this.f547b, bitmap);
                if (C0914a.this.f543d != null) {
                    C0914a.this.f543d.mo13994a(this.f546a);
                }
                C0914a.this.mo14045e();
            }
        }
    }

    /* renamed from: com.startapp.android.publish.ads.list3d.a$b */
    /* compiled from: StartAppSDK */
    class C0916b {

        /* renamed from: a */
        int f550a;

        /* renamed from: b */
        String f551b;

        /* renamed from: c */
        String f552c;

        public C0916b(int i, String str, String str2) {
            this.f550a = i;
            this.f551b = str;
            this.f552c = str2;
        }
    }
}
