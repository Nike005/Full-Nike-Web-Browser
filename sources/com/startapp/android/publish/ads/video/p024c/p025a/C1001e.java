package com.startapp.android.publish.ads.video.p024c.p025a;

import android.text.TextUtils;
import com.mopub.mobileads.VastExtensionXmlManager;
import com.mopub.mobileads.VastIconXmlManager;
import com.mopub.mobileads.VastResourceXmlManager;
import com.startapp.android.publish.ads.video.p024c.p025a.p026a.C0990a;
import com.startapp.android.publish.ads.video.p024c.p025a.p026a.C0991b;
import com.startapp.android.publish.ads.video.p024c.p025a.p026a.C0992c;
import com.startapp.android.publish.ads.video.p024c.p025a.p026a.C0993d;
import com.startapp.android.publish.ads.video.p024c.p025a.p026a.C0994e;
import com.startapp.android.publish.ads.video.p024c.p027b.C1002a;
import com.startapp.android.publish.adsCommon.C1185p;
import com.startapp.android.publish.omsdk.AdVerification;
import com.startapp.android.publish.omsdk.VerificationDetails;
import com.startapp.common.p043a.C1270g;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* renamed from: com.startapp.android.publish.ads.video.c.a.e */
/* compiled from: StartAppSDK */
public class C1001e {

    /* renamed from: a */
    private static String f831a = "VASTModel";

    /* renamed from: b */
    private HashMap<C0995b, List<C0992c>> f832b;

    /* renamed from: c */
    private List<C0991b> f833c;

    /* renamed from: d */
    private int f834d;

    /* renamed from: e */
    private C0994e f835e;

    /* renamed from: f */
    private List<String> f836f;

    /* renamed from: g */
    private List<String> f837g;

    /* renamed from: h */
    private int f838h;

    /* renamed from: i */
    private C0991b f839i = null;

    /* renamed from: j */
    private List<C0990a> f840j;

    /* renamed from: k */
    private AdVerification f841k;

    public C1001e(Document document) {
        this.f834d = m976c(document);
        this.f832b = m973a(document);
        this.f833c = m975b(document);
        this.f835e = m977d(document);
        this.f836f = m978e(document);
        this.f837g = m979f(document);
        this.f838h = m980g(document);
        this.f840j = m981h(document);
        this.f841k = m982i(document);
    }

    /* renamed from: a */
    public boolean mo14353a(C0996c cVar) {
        C0991b a = C1002a.m992a(this, cVar);
        this.f839i = a;
        return a != null;
    }

    /* renamed from: a */
    public HashMap<C0995b, List<C0992c>> mo14352a() {
        return this.f832b;
    }

    /* renamed from: b */
    public List<C0991b> mo14354b() {
        return this.f833c;
    }

    /* renamed from: c */
    public C0994e mo14355c() {
        return this.f835e;
    }

    /* renamed from: d */
    public List<String> mo14356d() {
        return this.f836f;
    }

    /* renamed from: e */
    public List<String> mo14357e() {
        return this.f837g;
    }

    /* renamed from: f */
    public int mo14358f() {
        return this.f838h;
    }

    /* renamed from: g */
    public C0991b mo14359g() {
        return this.f839i;
    }

    /* renamed from: h */
    public AdVerification mo14360h() {
        return this.f841k;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(10:8|9|10|11|(3:15|16|(1:18)(2:19|20))|21|22|(1:24)(1:25)|26|36) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0072 */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0078 A[Catch:{ Exception -> 0x00b1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007f A[Catch:{ Exception -> 0x00b1 }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.HashMap<com.startapp.android.publish.ads.video.p024c.p025a.C0995b, java.util.List<com.startapp.android.publish.ads.video.p024c.p025a.p026a.C0992c>> m973a(org.w3c.dom.Document r9) {
        /*
            r8 = this;
            java.lang.String r0 = "%"
            java.lang.String r1 = f831a
            r2 = 3
            java.lang.String r3 = "getTrackingUrls"
            com.startapp.common.p043a.C1270g.m2078a((java.lang.String) r1, (int) r2, (java.lang.String) r3)
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            javax.xml.xpath.XPathFactory r2 = javax.xml.xpath.XPathFactory.newInstance()
            javax.xml.xpath.XPath r2 = r2.newXPath()
            java.lang.String r3 = "/VASTS/VAST/Ad/InLine/Creatives/Creative/Linear/TrackingEvents/Tracking|/VASTS/VAST/Ad/InLine/Creatives/Creative/NonLinearAds/TrackingEvents/Tracking|/VASTS/VAST/Ad/Wrapper/Creatives/Creative/Linear/TrackingEvents/Tracking|/VASTS/VAST/Ad/Wrapper/Creatives/Creative/NonLinearAds/TrackingEvents/Tracking"
            javax.xml.namespace.QName r4 = javax.xml.xpath.XPathConstants.NODESET     // Catch:{ Exception -> 0x00b1 }
            java.lang.Object r9 = r2.evaluate(r3, r9, r4)     // Catch:{ Exception -> 0x00b1 }
            org.w3c.dom.NodeList r9 = (org.w3c.dom.NodeList) r9     // Catch:{ Exception -> 0x00b1 }
            if (r9 == 0) goto L_0x00b0
            r2 = 0
        L_0x0024:
            int r3 = r9.getLength()     // Catch:{ Exception -> 0x00b1 }
            if (r2 >= r3) goto L_0x00b0
            org.w3c.dom.Node r3 = r9.item(r2)     // Catch:{ Exception -> 0x00b1 }
            org.w3c.dom.NamedNodeMap r4 = r3.getAttributes()     // Catch:{ Exception -> 0x00b1 }
            java.lang.String r5 = "event"
            org.w3c.dom.Node r5 = r4.getNamedItem(r5)     // Catch:{ Exception -> 0x00b1 }
            java.lang.String r5 = r5.getNodeValue()     // Catch:{ Exception -> 0x00b1 }
            com.startapp.android.publish.ads.video.c.a.b r5 = com.startapp.android.publish.ads.video.p024c.p025a.C0995b.valueOf(r5)     // Catch:{ IllegalArgumentException -> 0x0090 }
            java.lang.String r3 = com.startapp.android.publish.adsCommon.C1185p.m1755b(r3)     // Catch:{ Exception -> 0x00b1 }
            r6 = -1
            java.lang.String r7 = "offset"
            org.w3c.dom.Node r4 = r4.getNamedItem(r7)     // Catch:{ Exception -> 0x00b1 }
            if (r4 == 0) goto L_0x0072
            java.lang.String r4 = r4.getNodeValue()     // Catch:{ Exception -> 0x00b1 }
            if (r4 == 0) goto L_0x0072
            boolean r7 = r4.contains(r0)     // Catch:{ Exception -> 0x0072 }
            if (r7 == 0) goto L_0x006b
            java.lang.String r7 = ""
            java.lang.String r4 = r4.replace(r0, r7)     // Catch:{ Exception -> 0x0072 }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x0072 }
            int r7 = r8.f834d     // Catch:{ Exception -> 0x0072 }
            int r7 = r7 / 100
            int r7 = r7 * r4
            r6 = r7
            goto L_0x0072
        L_0x006b:
            int r4 = m972a((java.lang.String) r4)     // Catch:{ Exception -> 0x0072 }
            int r4 = r4 * 1000
            r6 = r4
        L_0x0072:
            boolean r4 = r1.containsKey(r5)     // Catch:{ Exception -> 0x00b1 }
            if (r4 == 0) goto L_0x007f
            java.lang.Object r4 = r1.get(r5)     // Catch:{ Exception -> 0x00b1 }
            java.util.List r4 = (java.util.List) r4     // Catch:{ Exception -> 0x00b1 }
            goto L_0x0087
        L_0x007f:
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ Exception -> 0x00b1 }
            r4.<init>()     // Catch:{ Exception -> 0x00b1 }
            r1.put(r5, r4)     // Catch:{ Exception -> 0x00b1 }
        L_0x0087:
            com.startapp.android.publish.ads.video.c.a.a.c r5 = new com.startapp.android.publish.ads.video.c.a.a.c     // Catch:{ Exception -> 0x00b1 }
            r5.<init>(r3, r6)     // Catch:{ Exception -> 0x00b1 }
            r4.add(r5)     // Catch:{ Exception -> 0x00b1 }
            goto L_0x00ac
        L_0x0090:
            java.lang.String r3 = f831a     // Catch:{ Exception -> 0x00b1 }
            r4 = 5
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00b1 }
            r6.<init>()     // Catch:{ Exception -> 0x00b1 }
            java.lang.String r7 = "Event:"
            r6.append(r7)     // Catch:{ Exception -> 0x00b1 }
            r6.append(r5)     // Catch:{ Exception -> 0x00b1 }
            java.lang.String r5 = " is not valid. Skipping it."
            r6.append(r5)     // Catch:{ Exception -> 0x00b1 }
            java.lang.String r5 = r6.toString()     // Catch:{ Exception -> 0x00b1 }
            com.startapp.common.p043a.C1270g.m2078a((java.lang.String) r3, (int) r4, (java.lang.String) r5)     // Catch:{ Exception -> 0x00b1 }
        L_0x00ac:
            int r2 = r2 + 1
            goto L_0x0024
        L_0x00b0:
            return r1
        L_0x00b1:
            r9 = move-exception
            java.lang.String r0 = f831a
            r1 = 6
            java.lang.String r2 = r9.getMessage()
            com.startapp.common.p043a.C1270g.m2079a(r0, r1, r2, r9)
            r9 = 0
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.ads.video.p024c.p025a.C1001e.m973a(org.w3c.dom.Document):java.util.HashMap");
    }

    /* renamed from: b */
    private List<C0991b> m975b(Document document) {
        String str;
        Integer num;
        String str2;
        Integer num2;
        Integer num3;
        String str3;
        Boolean bool;
        Boolean bool2;
        String str4;
        C1270g.m2078a(f831a, 3, "getMediaFiles");
        ArrayList arrayList = new ArrayList();
        try {
            NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().evaluate("//MediaFile", document, XPathConstants.NODESET);
            if (nodeList != null) {
                for (int i = 0; i < nodeList.getLength(); i++) {
                    C0991b bVar = new C0991b();
                    Node item = nodeList.item(i);
                    NamedNodeMap attributes = item.getAttributes();
                    Node namedItem = attributes.getNamedItem("apiFramework");
                    if (namedItem == null) {
                        str = null;
                    } else {
                        str = namedItem.getNodeValue();
                    }
                    bVar.mo14331e(str);
                    Node namedItem2 = attributes.getNamedItem("bitrate");
                    if (namedItem2 == null) {
                        num = null;
                    } else {
                        num = Integer.valueOf(namedItem2.getNodeValue());
                    }
                    bVar.mo14319a(num);
                    Node namedItem3 = attributes.getNamedItem("delivery");
                    if (namedItem3 == null) {
                        str2 = null;
                    } else {
                        str2 = namedItem3.getNodeValue();
                    }
                    bVar.mo14327c(str2);
                    Node namedItem4 = attributes.getNamedItem("height");
                    if (namedItem4 == null) {
                        num2 = null;
                    } else {
                        num2 = Integer.valueOf(namedItem4.getNodeValue());
                    }
                    bVar.mo14326c(num2);
                    Node namedItem5 = attributes.getNamedItem("width");
                    if (namedItem5 == null) {
                        num3 = null;
                    } else {
                        num3 = Integer.valueOf(namedItem5.getNodeValue());
                    }
                    bVar.mo14323b(num3);
                    Node namedItem6 = attributes.getNamedItem("id");
                    if (namedItem6 == null) {
                        str3 = null;
                    } else {
                        str3 = namedItem6.getNodeValue();
                    }
                    bVar.mo14324b(str3);
                    Node namedItem7 = attributes.getNamedItem("maintainAspectRatio");
                    if (namedItem7 == null) {
                        bool = null;
                    } else {
                        bool = Boolean.valueOf(namedItem7.getNodeValue());
                    }
                    bVar.mo14322b(bool);
                    Node namedItem8 = attributes.getNamedItem("scalable");
                    if (namedItem8 == null) {
                        bool2 = null;
                    } else {
                        bool2 = Boolean.valueOf(namedItem8.getNodeValue());
                    }
                    bVar.mo14318a(bool2);
                    Node namedItem9 = attributes.getNamedItem("type");
                    if (namedItem9 == null) {
                        str4 = null;
                    } else {
                        str4 = namedItem9.getNodeValue();
                    }
                    bVar.mo14329d(str4);
                    bVar.mo14320a(C1185p.m1755b(item));
                    if (bVar.mo14332f()) {
                        arrayList.add(bVar);
                    }
                }
            }
            return arrayList;
        } catch (Exception e) {
            C1270g.m2079a(f831a, 6, e.getMessage(), e);
            return null;
        }
    }

    /* renamed from: c */
    private int m976c(Document document) {
        C1270g.m2078a(f831a, 3, "getDuration");
        try {
            NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().evaluate("//Duration", document, XPathConstants.NODESET);
            if (nodeList == null || nodeList.getLength() <= 0) {
                return Integer.MAX_VALUE;
            }
            return m972a(C1185p.m1755b(nodeList.item(0)));
        } catch (Exception e) {
            C1270g.m2079a(f831a, 6, e.getMessage(), e);
            return Integer.MAX_VALUE;
        }
    }

    /* renamed from: d */
    private C0994e m977d(Document document) {
        C1270g.m2078a(f831a, 3, "getVideoClicks");
        C0994e eVar = new C0994e();
        try {
            NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().evaluate("//VideoClicks", document, XPathConstants.NODESET);
            if (nodeList != null) {
                for (int i = 0; i < nodeList.getLength(); i++) {
                    NodeList childNodes = nodeList.item(i).getChildNodes();
                    for (int i2 = 0; i2 < childNodes.getLength(); i2++) {
                        Node item = childNodes.item(i2);
                        String nodeName = item.getNodeName();
                        String b = C1185p.m1755b(item);
                        if (nodeName.equalsIgnoreCase("ClickTracking")) {
                            eVar.mo14341b().add(b);
                        } else if (nodeName.equalsIgnoreCase("ClickThrough")) {
                            eVar.mo14340a(b);
                        } else if (nodeName.equalsIgnoreCase("CustomClick")) {
                            eVar.mo14342c().add(b);
                        }
                    }
                }
            }
            return eVar;
        } catch (Exception e) {
            C1270g.m2079a(f831a, 6, e.getMessage(), e);
            return null;
        }
    }

    /* renamed from: e */
    private List<String> m978e(Document document) {
        C1270g.m2078a(f831a, 3, "getImpressions");
        return m974a(document, "//Impression");
    }

    /* renamed from: f */
    private List<String> m979f(Document document) {
        C1270g.m2078a(f831a, 3, "getErrorUrl");
        return m974a(document, "//Error");
    }

    /* renamed from: g */
    private int m980g(Document document) {
        C1270g.m2078a(f831a, 3, "getSkipOffset");
        try {
            NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().evaluate("//Linear", document, XPathConstants.NODESET);
            if (nodeList == null) {
                return Integer.MAX_VALUE;
            }
            int i = 0;
            while (i < nodeList.getLength()) {
                try {
                    if (nodeList.item(i).getAttributes().getNamedItem("skipoffset") != null) {
                        return m972a(nodeList.item(i).getAttributes().getNamedItem("skipoffset").getNodeValue());
                    }
                    i++;
                } catch (Exception e) {
                    C1270g.m2079a(f831a, 6, e.getMessage(), e);
                }
            }
            return Integer.MAX_VALUE;
        } catch (Exception e2) {
            C1270g.m2079a(f831a, 6, e2.getMessage(), e2);
            return Integer.MAX_VALUE;
        }
    }

    /* renamed from: h */
    private List<C0990a> m981h(Document document) {
        String str;
        Integer num;
        Integer num2;
        Integer num3;
        Integer num4;
        Integer num5;
        Integer num6;
        String str2;
        Integer num7;
        String str3;
        C1270g.m2078a(f831a, 3, "getIcons");
        ArrayList arrayList = new ArrayList();
        try {
            NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().evaluate("//Icon", document, XPathConstants.NODESET);
            if (nodeList != null) {
                for (int i = 0; i < nodeList.getLength(); i++) {
                    C0990a aVar = new C0990a();
                    Node item = nodeList.item(i);
                    NamedNodeMap attributes = item.getAttributes();
                    Node namedItem = attributes.getNamedItem("program");
                    if (namedItem == null) {
                        str = null;
                    } else {
                        str = namedItem.getNodeValue();
                    }
                    aVar.mo14301a(str);
                    Node namedItem2 = attributes.getNamedItem("width");
                    if (namedItem2 == null) {
                        num = null;
                    } else {
                        num = Integer.valueOf(namedItem2.getNodeValue());
                    }
                    aVar.mo14300a(num);
                    Node namedItem3 = attributes.getNamedItem("height");
                    if (namedItem3 == null) {
                        num2 = null;
                    } else {
                        num2 = Integer.valueOf(namedItem3.getNodeValue());
                    }
                    aVar.mo14303b(num2);
                    Node namedItem4 = attributes.getNamedItem("xPosition");
                    if (namedItem4 == null) {
                        num3 = null;
                    } else {
                        num3 = Integer.valueOf(namedItem4.getNodeValue());
                    }
                    aVar.mo14306c(num3);
                    Node namedItem5 = attributes.getNamedItem("yPosition");
                    if (namedItem5 == null) {
                        num4 = null;
                    } else {
                        num4 = Integer.valueOf(namedItem5.getNodeValue());
                    }
                    aVar.mo14309d(num4);
                    Node namedItem6 = attributes.getNamedItem(VastIconXmlManager.DURATION);
                    if (namedItem6 == null) {
                        num5 = null;
                    } else {
                        num5 = Integer.valueOf(namedItem6.getNodeValue());
                    }
                    aVar.mo14311e(num5);
                    Node namedItem7 = attributes.getNamedItem(VastIconXmlManager.OFFSET);
                    if (namedItem7 == null) {
                        num6 = null;
                    } else {
                        num6 = Integer.valueOf(namedItem7.getNodeValue());
                    }
                    aVar.mo14313f(num6);
                    Node namedItem8 = attributes.getNamedItem("apiFramework");
                    if (namedItem8 == null) {
                        str2 = null;
                    } else {
                        str2 = namedItem8.getNodeValue();
                    }
                    aVar.mo14304b(str2);
                    Node namedItem9 = attributes.getNamedItem("pxratio");
                    if (namedItem9 == null) {
                        num7 = null;
                    } else {
                        num7 = Integer.valueOf(namedItem9.getNodeValue());
                    }
                    aVar.mo14315g(num7);
                    NodeList childNodes = item.getChildNodes();
                    for (int i2 = 0; i2 < childNodes.getLength(); i2++) {
                        Node item2 = childNodes.item(i2);
                        String nodeName = item2.getNodeName();
                        String b = C1185p.m1755b(item2);
                        if (nodeName.equalsIgnoreCase(VastIconXmlManager.ICON_CLICKS)) {
                            NodeList childNodes2 = item.getChildNodes();
                            for (int i3 = 0; i3 < childNodes2.getLength(); i3++) {
                                Node item3 = childNodes.item(i2);
                                String nodeName2 = item3.getNodeName();
                                String b2 = C1185p.m1755b(item3);
                                if (nodeName2.equalsIgnoreCase("ClickThrough")) {
                                    aVar.mo14307c(b2);
                                } else if (nodeName2.equalsIgnoreCase(VastIconXmlManager.ICON_VIEW_TRACKING)) {
                                    aVar.mo14314g().add(b2);
                                }
                            }
                        } else if (nodeName.equalsIgnoreCase("ClickTracking")) {
                            aVar.mo14312f().add(b);
                        } else if (nodeName.equalsIgnoreCase(VastResourceXmlManager.STATIC_RESOURCE)) {
                            C0993d dVar = new C0993d();
                            dVar.mo14338b(b);
                            Node namedItem10 = item.getAttributes().getNamedItem(VastResourceXmlManager.CREATIVE_TYPE);
                            if (namedItem10 == null) {
                                str3 = null;
                            } else {
                                str3 = namedItem10.getNodeValue();
                            }
                            dVar.mo14336a(str3);
                            if (dVar.mo14337a()) {
                                aVar.mo14310e().add(dVar);
                            }
                        }
                    }
                    if (aVar.mo14316h()) {
                        arrayList.add(aVar);
                    }
                }
            }
            return arrayList;
        } catch (Exception e) {
            C1270g.m2079a(f831a, 6, e.getMessage(), e);
            return null;
        }
    }

    /* renamed from: a */
    private List<String> m974a(Document document, String str) {
        C1270g.m2078a(f831a, 3, "getListFromXPath");
        ArrayList arrayList = new ArrayList();
        try {
            NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().evaluate(str, document, XPathConstants.NODESET);
            if (nodeList != null) {
                for (int i = 0; i < nodeList.getLength(); i++) {
                    arrayList.add(C1185p.m1755b(nodeList.item(i)));
                }
            }
            return arrayList;
        } catch (Exception e) {
            C1270g.m2079a(f831a, 6, e.getMessage(), e);
            return null;
        }
    }

    /* renamed from: i */
    private AdVerification m982i(Document document) {
        try {
            NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().evaluate("//AdVerifications", document, XPathConstants.NODESET);
            if (nodeList == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < nodeList.getLength(); i++) {
                NodeList childNodes = nodeList.item(i).getChildNodes();
                for (int i2 = 0; i2 < childNodes.getLength(); i2++) {
                    Node item = childNodes.item(i2);
                    if (item.getNodeName().equalsIgnoreCase(VastExtensionXmlManager.VERIFICATION)) {
                        String str = "";
                        NamedNodeMap attributes = item.getAttributes();
                        String nodeValue = (attributes == null || attributes.getNamedItem(VastExtensionXmlManager.VENDOR) == null) ? null : attributes.getNamedItem(VastExtensionXmlManager.VENDOR).getNodeValue();
                        NodeList childNodes2 = item.getChildNodes();
                        String str2 = null;
                        String str3 = null;
                        for (int i3 = 0; i3 < childNodes2.getLength(); i3++) {
                            Node item2 = childNodes2.item(i3);
                            if (item2.getNodeName().equalsIgnoreCase("JavaScriptResource")) {
                                Node namedItem = item2.getAttributes().getNamedItem("apiFramework");
                                if (namedItem != null) {
                                    str = namedItem.getNodeValue();
                                }
                                str2 = C1185p.m1755b(item2);
                            } else if (item2.getNodeName().equalsIgnoreCase("VerificationParameters")) {
                                str3 = C1185p.m1755b(item2);
                            }
                        }
                        if (!TextUtils.isEmpty(nodeValue) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3) && "omid".equalsIgnoreCase(str)) {
                            arrayList.add(new VerificationDetails(nodeValue, str2, str3));
                        }
                    }
                }
            }
            if (!arrayList.isEmpty()) {
                return new AdVerification((VerificationDetails[]) arrayList.toArray(new VerificationDetails[arrayList.size()]));
            }
            return null;
        } catch (Exception e) {
            C1270g.m2079a(f831a, 6, e.getMessage(), e);
            return null;
        }
    }

    /* renamed from: a */
    private static int m972a(String str) {
        String[] split = str.split(":");
        return (Integer.parseInt(split[0]) * 3600) + (Integer.parseInt(split[1]) * 60) + Integer.parseInt(split[2]);
    }
}
