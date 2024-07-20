package com.yandex.metrica.impl.p050ob;

import android.content.Context;
import com.yandex.metrica.impl.C1897bk;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.yandex.metrica.impl.ob.bp */
public class C2003bp {

    /* renamed from: a */
    private static volatile C2003bp f3280a;

    /* renamed from: b */
    private final Map<String, C2002bo> f3281b = new HashMap();

    /* renamed from: c */
    private final Map<String, C2004bq> f3282c = new HashMap();

    /* renamed from: d */
    private final Context f3283d;

    /* renamed from: e */
    private C2002bo f3284e;

    /* renamed from: f */
    private C2004bq f3285f;

    /* renamed from: g */
    private C2004bq f3286g;

    /* renamed from: h */
    private C2004bq f3287h;

    /* renamed from: i */
    private C2006br f3288i;

    /* renamed from: a */
    public static C2003bp m5024a(Context context) {
        if (f3280a == null) {
            synchronized (C2003bp.class) {
                if (f3280a == null) {
                    f3280a = new C2003bp(context);
                }
            }
        }
        return f3280a;
    }

    public C2003bp(Context context) {
        this.f3283d = context;
    }

    /* renamed from: a */
    public synchronized C2002bo mo17284a() {
        if (this.f3284e == null) {
            this.f3284e = mo17286a("metrica_data.db", C1972bm.m4967b());
        }
        return this.f3284e;
    }

    /* renamed from: b */
    public synchronized C2004bq mo17288b(C2190r rVar) {
        C2004bq bqVar;
        String rVar2 = rVar.toString();
        bqVar = this.f3282c.get(rVar2);
        if (bqVar == null) {
            bqVar = new C2004bq(mo17285a(rVar), "preferences");
            this.f3282c.put(rVar2, bqVar);
        }
        return bqVar;
    }

    /* renamed from: b */
    public synchronized C2004bq mo17287b() {
        if (this.f3285f == null) {
            this.f3285f = new C2004bq(mo17284a(), "preferences");
        }
        return this.f3285f;
    }

    /* renamed from: c */
    public synchronized C2006br mo17289c() {
        if (this.f3288i == null) {
            this.f3288i = new C2006br(mo17284a(), "permissions");
        }
        return this.f3288i;
    }

    /* renamed from: d */
    public synchronized C2004bq mo17290d() {
        if (this.f3286g == null) {
            this.f3286g = new C2004bq(mo17284a(), "startup");
        }
        return this.f3286g;
    }

    /* renamed from: e */
    public synchronized C2004bq mo17291e() {
        if (this.f3287h == null) {
            this.f3287h = new C2004bq("preferences", (C2010bv) new C2011bw(this.f3283d, m5025a("metrica_client_data.db")));
        }
        return this.f3287h;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C2002bo mo17286a(String str, C2007bs bsVar) {
        return new C2002bo(this.f3283d, m5025a(str), bsVar);
    }

    /* renamed from: a */
    private String m5025a(String str) {
        return C1897bk.m4650a(21) ? m5026b(str) : str;
    }

    /* renamed from: b */
    private String m5026b(String str) {
        try {
            File noBackupFilesDir = this.f3283d.getNoBackupFilesDir();
            File file = new File(noBackupFilesDir, str);
            if (!file.exists()) {
                File databasePath = this.f3283d.getDatabasePath(str);
                if (databasePath.exists() && databasePath.renameTo(file)) {
                    String str2 = str + "-journal";
                    this.f3283d.getDatabasePath(str2).renameTo(new File(noBackupFilesDir, str2));
                }
            }
            return file.getAbsolutePath();
        } catch (Exception unused) {
            return str;
        }
    }

    /* renamed from: a */
    public synchronized C2002bo mo17285a(C2190r rVar) {
        C2002bo boVar;
        String str = "db_metrica_" + rVar;
        boVar = this.f3281b.get(str);
        if (boVar == null) {
            boVar = mo17286a(str, C1972bm.m4966a());
            this.f3281b.put(str, boVar);
        }
        return boVar;
    }
}
