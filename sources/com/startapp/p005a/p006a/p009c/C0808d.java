package com.startapp.p005a.p006a.p009c;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/* renamed from: com.startapp.a.a.c.d */
/* compiled from: StartAppSDK */
public class C0808d {

    /* renamed from: a */
    public static final char f341a = File.separatorChar;

    /* renamed from: b */
    public static final String f342b;

    static {
        C0809e eVar = new C0809e(4);
        PrintWriter printWriter = new PrintWriter(eVar);
        printWriter.println();
        f342b = eVar.toString();
        printWriter.close();
    }

    /* renamed from: a */
    public static void m379a(OutputStream outputStream) {
        m378a((Closeable) outputStream);
    }

    /* renamed from: a */
    public static void m378a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
