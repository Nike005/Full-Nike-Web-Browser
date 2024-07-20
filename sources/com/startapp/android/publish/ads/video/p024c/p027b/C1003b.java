package com.startapp.android.publish.ads.video.p024c.p027b;

import android.content.Context;
import com.google.firebase.iid.ServiceStarter;
import com.startapp.android.publish.ads.video.p024c.p025a.C0989a;
import com.startapp.android.publish.ads.video.p024c.p025a.C0996c;
import com.startapp.android.publish.ads.video.p024c.p025a.C1001e;
import com.startapp.android.publish.adsCommon.C1166k;
import com.startapp.android.publish.adsCommon.C1185p;
import com.startapp.common.p043a.C1270g;
import com.startapp.common.p043a.C1271h;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/* renamed from: com.startapp.android.publish.ads.video.c.b.b */
/* compiled from: StartAppSDK */
public final class C1003b {

    /* renamed from: a */
    private final int f842a;

    /* renamed from: b */
    private final int f843b;

    /* renamed from: c */
    private C1001e f844c;

    /* renamed from: d */
    private StringBuilder f845d = new StringBuilder(ServiceStarter.ERROR_UNKNOWN);

    /* renamed from: e */
    private long f846e = -1;

    public C1003b(int i, int i2) {
        this.f842a = i;
        this.f843b = i2;
    }

    /* renamed from: a */
    public C1001e mo14363a() {
        return this.f844c;
    }

    /* renamed from: a */
    public C0989a mo14362a(Context context, String str, C0996c cVar) {
        this.f844c = null;
        this.f846e = System.currentTimeMillis();
        C0989a a = mo14361a(context, str, 0);
        if (a == C0989a.XMLParsingError) {
            C1270g.m2078a("VASTProcessor", 3, "processXml error " + a);
            return C0989a.XMLParsingError;
        }
        Document a2 = m995a(this.f845d.toString());
        if (a2 == null) {
            C1270g.m2078a("VASTProcessor", 3, "wrapMergedVastDocWithVasts error " + a);
            return C0989a.XMLParsingError;
        }
        C1001e eVar = new C1001e(a2);
        this.f844c = eVar;
        if (eVar.mo14353a(cVar)) {
            return a;
        }
        C1270g.m2078a("VASTProcessor", 3, "validate error " + a);
        return a == C0989a.ErrorNone ? C0989a.MediaNotSupported : a;
    }

    /* renamed from: a */
    public C0989a mo14361a(Context context, String str, int i) {
        if (i >= this.f842a) {
            C1270g.m2078a("VASTProcessor", 6, "VAST wrapping exceeded max limit of " + this.f842a);
            return C0989a.WrapperLimitReached;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j = this.f846e;
        long j2 = currentTimeMillis - j;
        if (j2 <= ((long) this.f843b) || j <= 0) {
            Document b = m996b(str);
            if (b == null) {
                return C0989a.XMLParsingError;
            }
            String a = m994a(b);
            if (b.getChildNodes().getLength() == 0 || b.getChildNodes().item(0).getChildNodes().getLength() == 0 || a == null) {
                return C0989a.WrapperNoReponse;
            }
            this.f845d.append(a);
            NodeList elementsByTagName = b.getElementsByTagName("VASTAdTagURI");
            if (elementsByTagName == null || elementsByTagName.getLength() == 0) {
                return C0989a.ErrorNone;
            }
            String b2 = C1185p.m1755b(elementsByTagName.item(0));
            C1270g.m2078a("VASTProcessor", 3, "Wrapper URL: " + b2);
            try {
                C1271h.C1272a a2 = C1271h.m2081a(context, b2.replace(StringUtils.SPACE, "%20"), (Map<String, String>) null, C1166k.m1610a(context, "User-Agent", "-1"), false);
                if (a2 == null || a2.mo15466a() == null) {
                    return C0989a.WrapperNoReponse;
                }
                return mo14361a(context, a2.mo15466a(), i + 1);
            } catch (Exception e) {
                C1270g.m2079a("VASTProcessor", 6, "processXml network", e);
                return C0989a.GeneralWrapperError;
            }
        } else {
            C1270g.m2078a("VASTProcessor", 6, "VAST wrapping exceeded timeout " + j2);
            return C0989a.WrapperTimeout;
        }
    }

    /* renamed from: a */
    public static Document m995a(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        return C1185p.m1754a("<VASTS>" + str + "</VASTS>");
    }

    /* renamed from: b */
    public static Document m996b(String str) {
        Document a = C1185p.m1754a(str);
        if (a != null) {
            a.getDocumentElement().normalize();
        }
        return a;
    }

    /* renamed from: a */
    public static String m994a(Document document) {
        NodeList elementsByTagName;
        if (document == null || (elementsByTagName = document.getElementsByTagName("VAST")) == null || elementsByTagName.getLength() <= 0) {
            return null;
        }
        return C1185p.m1753a(elementsByTagName.item(0));
    }
}
