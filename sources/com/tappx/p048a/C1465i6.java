package com.tappx.p048a;

import java.io.ByteArrayOutputStream;

/* renamed from: com.tappx.a.i6 */
public class C1465i6 extends ByteArrayOutputStream {

    /* renamed from: a */
    private final C1383d6 f1958a;

    public C1465i6(C1383d6 d6Var, int i) {
        this.f1958a = d6Var;
        this.buf = d6Var.mo15725a(Math.max(i, 256));
    }

    /* renamed from: a */
    private void m2864a(int i) {
        int i2 = this.count + i;
        if (i2 > this.buf.length) {
            byte[] a = this.f1958a.mo15725a(i2 * 2);
            System.arraycopy(this.buf, 0, a, 0, this.count);
            this.f1958a.mo15724a(this.buf);
            this.buf = a;
        }
    }

    public void close() {
        this.f1958a.mo15724a(this.buf);
        this.buf = null;
        super.close();
    }

    public void finalize() {
        this.f1958a.mo15724a(this.buf);
    }

    public synchronized void write(byte[] bArr, int i, int i2) {
        m2864a(i2);
        super.write(bArr, i, i2);
    }

    public synchronized void write(int i) {
        m2864a(1);
        super.write(i);
    }
}
