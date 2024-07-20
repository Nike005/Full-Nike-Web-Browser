package com.yandex.metrica.impl;

import android.database.Cursor;
import com.yandex.metrica.C1781c;
import com.yandex.metrica.impl.C1857at;
import com.yandex.metrica.impl.p050ob.C1971bl;
import com.yandex.metrica.impl.p050ob.C2198t;
import java.util.Map;

/* renamed from: com.yandex.metrica.impl.au */
class C1862au extends C1857at {
    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo16873a(long j) {
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: s */
    public long mo16866s() {
        return Long.MIN_VALUE;
    }

    /* access modifiers changed from: protected */
    /* renamed from: t */
    public long mo16867t() {
        return Long.MIN_VALUE;
    }

    public C1862au(C2198t tVar) {
        super(tVar);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0011, code lost:
        if (r1.getCount() != 0) goto L_0x0038;
     */
    /* renamed from: x */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.yandex.metrica.impl.C1857at.C1861c mo16876x() {
        /*
            r7 = this;
            r0 = 0
            android.database.Cursor r1 = r7.mo16878z()     // Catch:{ Exception -> 0x0051, all -> 0x0048 }
            if (r1 == 0) goto L_0x0013
            boolean r2 = r1.moveToFirst()     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            if (r2 == 0) goto L_0x0013
            int r2 = r1.getCount()     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            if (r2 != 0) goto L_0x0038
        L_0x0013:
            com.yandex.metrica.impl.ob.bn r2 = r7.f2986o     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            long r3 = r7.mo16866s()     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            com.yandex.metrica.impl.ob.bl r5 = com.yandex.metrica.impl.p050ob.C1971bl.BACKGROUND     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            android.database.Cursor r0 = r2.mo17273b((long) r3, (com.yandex.metrica.impl.p050ob.C1971bl) r5)     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            if (r0 == 0) goto L_0x0038
            boolean r2 = r0.moveToFirst()     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            if (r2 == 0) goto L_0x0038
            int r2 = r0.getCount()     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            if (r2 <= 0) goto L_0x0038
            com.yandex.metrica.impl.ob.bn r2 = r7.f2986o     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            long r3 = r7.mo16866s()     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            com.yandex.metrica.impl.ob.bl r5 = com.yandex.metrica.impl.p050ob.C1971bl.BACKGROUND     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            r2.mo17269a((long) r3, (com.yandex.metrica.impl.p050ob.C1971bl) r5)     // Catch:{ Exception -> 0x0044, all -> 0x003f }
        L_0x0038:
            com.yandex.metrica.impl.C1897bk.m4643a((android.database.Cursor) r1)
            com.yandex.metrica.impl.C1897bk.m4643a((android.database.Cursor) r0)
            goto L_0x0058
        L_0x003f:
            r2 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L_0x004a
        L_0x0044:
            r6 = r1
            r1 = r0
            r0 = r6
            goto L_0x0052
        L_0x0048:
            r2 = move-exception
            r1 = r0
        L_0x004a:
            com.yandex.metrica.impl.C1897bk.m4643a((android.database.Cursor) r0)
            com.yandex.metrica.impl.C1897bk.m4643a((android.database.Cursor) r1)
            throw r2
        L_0x0051:
            r1 = r0
        L_0x0052:
            com.yandex.metrica.impl.C1897bk.m4643a((android.database.Cursor) r0)
            com.yandex.metrica.impl.C1897bk.m4643a((android.database.Cursor) r1)
        L_0x0058:
            com.yandex.metrica.impl.at$c r0 = super.mo16876x()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.C1862au.mo16876x():com.yandex.metrica.impl.at$c");
    }

    /* access modifiers changed from: protected */
    /* renamed from: z */
    public Cursor mo16878z() {
        return this.f2986o.mo17265a(mo16866s(), (Map<String, String>) this.f3195b);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Cursor mo16869a(long j, C1971bl blVar) {
        return this.f2986o.mo17273b(mo16866s(), blVar);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C1857at.C1860b mo16871a(long j, C1781c.C1782a.C1786d.C1792b bVar) {
        return super.mo16871a(mo16867t(), bVar);
    }

    /* renamed from: a */
    public String mo16814a() {
        return super.mo16814a() + " [" + mo16866s() + "]";
    }
}
