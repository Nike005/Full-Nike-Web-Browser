package com.tappx.p048a;

import android.net.Uri;
import com.tappx.p048a.C1687x3;

/* renamed from: com.tappx.a.t4 */
public class C1628t4 {
    /* renamed from: a */
    public boolean mo16184a(String str, C1687x3.C1691d dVar) {
        Uri parse = Uri.parse(str);
        if (!"tappx".equalsIgnoreCase(parse.getScheme())) {
            return false;
        }
        String host = parse.getHost();
        if ("noFillAd".equalsIgnoreCase(host)) {
            dVar.mo16214a();
            return true;
        } else if (!"loadFinished".equalsIgnoreCase(host)) {
            return true;
        } else {
            dVar.mo16216c();
            return true;
        }
    }
}
