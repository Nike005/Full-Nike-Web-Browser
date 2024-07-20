package com.startapp.p005a.p006a.p011e;

import com.startapp.p005a.p006a.p007a.C0802c;
import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.PrintStream;

/* renamed from: com.startapp.a.a.e.d */
/* compiled from: StartAppSDK */
public abstract class C0819d {

    /* renamed from: a */
    private final C0818c f355a = new C0818c();

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract C0802c mo13701a(DataInput dataInput);

    /* renamed from: a */
    public C0802c mo13705a(String str) {
        if (str == null) {
            return null;
        }
        try {
            byte[] a = this.f355a.mo13704a(str);
            if (a == null) {
                return null;
            }
            return mo13701a(mo13706a(a));
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains("HighPageCountException")) {
                PrintStream printStream = System.err;
                printStream.println("HighPageCountException (PLM-2573) " + e.getMessage() + ", bad bloom token: " + str);
            }
            return null;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo13707a(DataInput dataInput, C0802c cVar, long j) {
        int c = cVar.mo13678c();
        for (int i = 0; i < c; i++) {
            long[] a = cVar.mo13676a(i);
            int i2 = 0;
            while (true) {
                if (i2 >= 4096) {
                    break;
                }
                long j2 = j - 1;
                if (j <= 0) {
                    j = j2;
                    break;
                }
                a[i2] = dataInput.readLong();
                i2++;
                j = j2;
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public DataInput mo13706a(byte[] bArr) {
        return new DataInputStream(new ByteArrayInputStream(bArr));
    }
}
