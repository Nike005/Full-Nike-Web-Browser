package com.startapp.android.publish.ads.video.p024c.p027b;

import android.text.TextUtils;
import com.startapp.android.publish.ads.video.p024c.p025a.C0996c;
import com.startapp.android.publish.ads.video.p024c.p025a.C1001e;
import com.startapp.android.publish.ads.video.p024c.p025a.p026a.C0991b;
import com.startapp.common.p043a.C1270g;
import java.util.List;

/* renamed from: com.startapp.android.publish.ads.video.c.b.a */
/* compiled from: StartAppSDK */
public class C1002a {
    /* renamed from: a */
    public static C0991b m992a(C1001e eVar, C0996c cVar) {
        C1270g.m2078a("VASTModelPostValidator", 3, "validate");
        C0991b bVar = null;
        if (!m993a(eVar)) {
            C1270g.m2078a("VASTModelPostValidator", 3, "Validator returns: not valid (invalid model)");
            return null;
        }
        if (cVar != null) {
            C0991b a = cVar.mo14344a(eVar.mo14354b());
            if (a != null) {
                String a2 = a.mo14317a();
                if (!TextUtils.isEmpty(a2)) {
                    C1270g.m2078a("VASTModelPostValidator", 3, "mediaPicker selected mediaFile with URL " + a2);
                    bVar = a;
                }
            }
        } else {
            C1270g.m2078a("VASTModelPostValidator", 5, "mediaPicker: We don't have a compatible media file to play.");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Validator returns: ");
        sb.append(bVar != null ? "valid" : "not valid (no media file)");
        C1270g.m2078a("VASTModelPostValidator", 3, sb.toString());
        return bVar;
    }

    /* renamed from: a */
    public static boolean m993a(C1001e eVar) {
        C1270g.m2078a("VASTModelPostValidator", 3, "validateModel");
        List<String> d = eVar.mo14356d();
        boolean z = (d == null || d.size() == 0) ? false : true;
        List<C0991b> b = eVar.mo14354b();
        if (b != null && b.size() != 0) {
            return z;
        }
        C1270g.m2078a("VASTModelPostValidator", 3, "Validator error: mediaFile list invalid");
        return false;
    }
}
