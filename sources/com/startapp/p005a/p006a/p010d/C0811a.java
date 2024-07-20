package com.startapp.p005a.p006a.p010d;

import com.startapp.p005a.p006a.p009c.C0804a;
import com.startapp.p005a.p006a.p009c.C0808d;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

/* renamed from: com.startapp.a.a.d.a */
/* compiled from: StartAppSDK */
public class C0811a implements C0815e {

    /* renamed from: a */
    private final C0813c f344a;

    public C0811a(C0813c cVar) {
        this.f344a = cVar;
    }

    /* renamed from: a */
    public String mo13699a(String str) {
        GZIPOutputStream gZIPOutputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(byteArrayOutputStream);
            try {
                gZIPOutputStream2.write(str.getBytes());
                C0808d.m379a((OutputStream) gZIPOutputStream2);
                String a = this.f344a.mo13700a(C0804a.m362a(byteArrayOutputStream.toByteArray()));
                C0808d.m379a((OutputStream) gZIPOutputStream2);
                return a;
            } catch (Exception unused) {
                gZIPOutputStream = gZIPOutputStream2;
                C0808d.m379a((OutputStream) gZIPOutputStream);
                return "";
            } catch (Throwable th) {
                C0808d.m379a((OutputStream) gZIPOutputStream2);
                throw th;
            }
        } catch (Exception unused2) {
            C0808d.m379a((OutputStream) gZIPOutputStream);
            return "";
        }
    }
}
