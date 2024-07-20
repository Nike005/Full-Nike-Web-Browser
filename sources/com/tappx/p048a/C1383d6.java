package com.tappx.p048a;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/* renamed from: com.tappx.a.d6 */
public class C1383d6 {

    /* renamed from: e */
    protected static final Comparator<byte[]> f1765e = new C1384a();

    /* renamed from: a */
    private final List<byte[]> f1766a = new ArrayList();

    /* renamed from: b */
    private final List<byte[]> f1767b = new ArrayList(64);

    /* renamed from: c */
    private int f1768c = 0;

    /* renamed from: d */
    private final int f1769d;

    /* renamed from: com.tappx.a.d6$a */
    static class C1384a implements Comparator<byte[]> {
        C1384a() {
        }

        /* renamed from: a */
        public int compare(byte[] bArr, byte[] bArr2) {
            return bArr.length - bArr2.length;
        }
    }

    public C1383d6(int i) {
        this.f1769d = i;
    }

    /* renamed from: a */
    public synchronized byte[] mo15725a(int i) {
        for (int i2 = 0; i2 < this.f1767b.size(); i2++) {
            byte[] bArr = this.f1767b.get(i2);
            if (bArr.length >= i) {
                this.f1768c -= bArr.length;
                this.f1767b.remove(i2);
                this.f1766a.remove(bArr);
                return bArr;
            }
        }
        return new byte[i];
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002f, code lost:
        return;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void mo15724a(byte[] r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            if (r3 == 0) goto L_0x002e
            int r0 = r3.length     // Catch:{ all -> 0x002b }
            int r1 = r2.f1769d     // Catch:{ all -> 0x002b }
            if (r0 <= r1) goto L_0x0009
            goto L_0x002e
        L_0x0009:
            java.util.List<byte[]> r0 = r2.f1766a     // Catch:{ all -> 0x002b }
            r0.add(r3)     // Catch:{ all -> 0x002b }
            java.util.List<byte[]> r0 = r2.f1767b     // Catch:{ all -> 0x002b }
            java.util.Comparator<byte[]> r1 = f1765e     // Catch:{ all -> 0x002b }
            int r0 = java.util.Collections.binarySearch(r0, r3, r1)     // Catch:{ all -> 0x002b }
            if (r0 >= 0) goto L_0x001b
            int r0 = -r0
            int r0 = r0 + -1
        L_0x001b:
            java.util.List<byte[]> r1 = r2.f1767b     // Catch:{ all -> 0x002b }
            r1.add(r0, r3)     // Catch:{ all -> 0x002b }
            int r0 = r2.f1768c     // Catch:{ all -> 0x002b }
            int r3 = r3.length     // Catch:{ all -> 0x002b }
            int r0 = r0 + r3
            r2.f1768c = r0     // Catch:{ all -> 0x002b }
            r2.m2541a()     // Catch:{ all -> 0x002b }
            monitor-exit(r2)
            return
        L_0x002b:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        L_0x002e:
            monitor-exit(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tappx.p048a.C1383d6.mo15724a(byte[]):void");
    }

    /* renamed from: a */
    private synchronized void m2541a() {
        while (this.f1768c > this.f1769d) {
            byte[] remove = this.f1766a.remove(0);
            this.f1767b.remove(remove);
            this.f1768c -= remove.length;
        }
    }
}
