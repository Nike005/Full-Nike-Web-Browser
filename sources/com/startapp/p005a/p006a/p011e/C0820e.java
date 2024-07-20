package com.startapp.p005a.p006a.p011e;

import com.startapp.p005a.p006a.p007a.C0802c;
import java.io.DataInput;
import java.io.IOException;

/* renamed from: com.startapp.a.a.e.e */
/* compiled from: StartAppSDK */
public class C0820e extends C0819d {
    /* access modifiers changed from: protected */
    /* renamed from: a */
    public DataInput mo13706a(byte[] bArr) {
        DataInput a = super.mo13706a(bArr);
        m396b(a);
        return a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0802c mo13701a(DataInput dataInput) {
        long readInt = (long) dataInput.readInt();
        C0802c cVar = new C0802c(readInt << 6);
        mo13707a(dataInput, cVar, readInt);
        return cVar;
    }

    /* renamed from: b */
    private void m396b(DataInput dataInput) {
        try {
            dataInput.readInt();
        } catch (IOException e) {
            throw new RuntimeException("problem incrementInputStreamForBackwordCompatability", e);
        }
    }
}
