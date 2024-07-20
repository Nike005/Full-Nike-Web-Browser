package com.yandex.metrica.impl.p050ob;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.yandex.metrica.impl.ob.el */
public class C2123el extends CertificateException {
    public C2123el(C2147ff ffVar) {
        super("There is not pinned certificates among chain " + m5582a(ffVar.mo17752a()));
    }

    /* renamed from: a */
    private static String m5582a(X509Certificate[] x509CertificateArr) {
        StringBuilder sb = new StringBuilder();
        for (X509Certificate x509Certificate : x509CertificateArr) {
            sb.append("ISSUER=" + x509Certificate.getIssuerDN().toString() + StringUtils.f3949LF);
        }
        return sb.toString();
    }
}
