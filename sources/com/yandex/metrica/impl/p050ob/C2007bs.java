package com.yandex.metrica.impl.p050ob;

import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;
import com.yandex.metrica.impl.p050ob.C1972bm;

/* renamed from: com.yandex.metrica.impl.ob.bs */
public class C2007bs {

    /* renamed from: a */
    private final C1972bm.C1985l f3298a;

    /* renamed from: b */
    private final C1972bm.C1985l f3299b;

    /* renamed from: c */
    private final SparseArray<C1972bm.C1985l> f3300c;

    /* renamed from: d */
    private final C2008bt f3301d;

    public C2007bs(C1972bm.C1985l lVar, C1972bm.C1985l lVar2, SparseArray<C1972bm.C1985l> sparseArray, C2008bt btVar) {
        this.f3298a = lVar;
        this.f3299b = lVar2;
        this.f3300c = sparseArray;
        this.f3301d = btVar;
    }

    /* renamed from: a */
    public void mo17306a(SQLiteDatabase sQLiteDatabase) {
        try {
            if (this.f3301d != null && !this.f3301d.mo17311a(sQLiteDatabase)) {
                mo17308a(sQLiteDatabase, this.f3298a, this.f3299b);
            }
        } catch (Exception unused) {
        }
    }

    /* renamed from: b */
    public void mo17310b(SQLiteDatabase sQLiteDatabase) {
        mo17309a(this.f3298a, sQLiteDatabase);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17309a(C1972bm.C1985l lVar, SQLiteDatabase sQLiteDatabase) {
        try {
            lVar.mo17261a(sQLiteDatabase);
        } catch (Exception unused) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0022  */
    /* JADX WARNING: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo17307a(android.database.sqlite.SQLiteDatabase r4, int r5, int r6) {
        /*
            r3 = this;
            r0 = 1
            if (r6 <= r5) goto L_0x0017
            r1 = 0
            int r5 = r5 + r0
        L_0x0005:
            if (r5 > r6) goto L_0x0018
            android.util.SparseArray<com.yandex.metrica.impl.ob.bm$l> r2 = r3.f3300c     // Catch:{ Exception -> 0x0017 }
            java.lang.Object r2 = r2.get(r5)     // Catch:{ Exception -> 0x0017 }
            com.yandex.metrica.impl.ob.bm$l r2 = (com.yandex.metrica.impl.p050ob.C1972bm.C1985l) r2     // Catch:{ Exception -> 0x0017 }
            if (r2 == 0) goto L_0x0014
            r2.mo17261a(r4)     // Catch:{ Exception -> 0x0017 }
        L_0x0014:
            int r5 = r5 + 1
            goto L_0x0005
        L_0x0017:
            r1 = 1
        L_0x0018:
            com.yandex.metrica.impl.ob.bt r5 = r3.f3301d
            boolean r5 = r5.mo17311a(r4)
            r5 = r5 ^ r0
            r5 = r5 | r1
            if (r5 == 0) goto L_0x0029
            com.yandex.metrica.impl.ob.bm$l r5 = r3.f3298a
            com.yandex.metrica.impl.ob.bm$l r6 = r3.f3299b
            r3.mo17308a((android.database.sqlite.SQLiteDatabase) r4, (com.yandex.metrica.impl.p050ob.C1972bm.C1985l) r5, (com.yandex.metrica.impl.p050ob.C1972bm.C1985l) r6)
        L_0x0029:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.p050ob.C2007bs.mo17307a(android.database.sqlite.SQLiteDatabase, int, int):void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17308a(SQLiteDatabase sQLiteDatabase, C1972bm.C1985l lVar, C1972bm.C1985l lVar2) {
        try {
            lVar2.mo17261a(sQLiteDatabase);
        } catch (Exception unused) {
        }
        mo17309a(lVar, sQLiteDatabase);
    }
}
