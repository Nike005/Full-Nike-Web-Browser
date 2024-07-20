package com.startapp.p005a.p006a.p007a;

import java.nio.ByteBuffer;
import java.util.List;

/* renamed from: com.startapp.a.a.a.a */
/* compiled from: StartAppSDK */
public class C0800a {

    /* renamed from: a */
    private final int f303a;

    /* renamed from: b */
    private final int f304b;

    public C0800a(int i, int i2) {
        this.f303a = i;
        this.f304b = i2;
    }

    /* renamed from: a */
    public C0802c mo13672a(List<String> list) {
        C0802c cVar = new C0802c((long) (this.f303a * this.f304b));
        mo13673a(list, cVar);
        return cVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo13673a(List<String> list, C0802c cVar) {
        for (String bytes : list) {
            m346a(ByteBuffer.wrap(bytes.getBytes()), cVar);
        }
    }

    /* renamed from: a */
    private void m346a(ByteBuffer byteBuffer, C0802c cVar) {
        for (long a : m347a(byteBuffer, cVar.mo13674a())) {
            cVar.mo13675a(a);
        }
    }

    /* renamed from: a */
    private long[] m347a(ByteBuffer byteBuffer, long j) {
        int i = this.f303a;
        long[] jArr = new long[i];
        long j2 = j / ((long) i);
        long a = C0801b.m350a(byteBuffer, byteBuffer.position(), byteBuffer.remaining(), 0);
        long a2 = C0801b.m350a(byteBuffer, byteBuffer.position(), byteBuffer.remaining(), a);
        for (int i2 = 0; i2 < this.f303a; i2++) {
            long j3 = (long) i2;
            jArr[i2] = (j3 * j2) + Math.abs(((j3 * a2) + a) % j2);
        }
        return jArr;
    }
}
