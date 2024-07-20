package com.appnext.core;

import android.accounts.AccountManager;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.util.Pair;
import com.google.android.gms.common.internal.AccountType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.appnext.core.d */
public abstract class C4948d {

    /* renamed from: fP */
    private static final int f4729fP = 50;

    /* renamed from: fQ */
    protected final int f4730fQ = 0;

    /* renamed from: fR */
    protected final int f4731fR = 1;

    /* renamed from: fS */
    protected final int f4732fS = 2;

    /* renamed from: fT */
    protected final int f4733fT = 3;
    /* access modifiers changed from: private */

    /* renamed from: fU */
    public final HashMap<C4946b, C4940a> f4734fU = new HashMap<>();

    /* renamed from: com.appnext.core.d$a */
    public interface C4955a {
        /* renamed from: a */
        <T> void mo40599a(T t);

        void error(String str);
    }

    /* renamed from: aV */
    protected static int m6748aV() {
        return 8000;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract int mo40610a(Context context, C4972g gVar);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract String mo40613a(Context context, C4924Ad ad, String str, ArrayList<Pair<String, String>> arrayList);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo40614a(Context context, C4924Ad ad, C4940a aVar) throws Exception;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo40615a(C4924Ad ad, String str, String str2);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract <T> void mo40617a(String str, C4924Ad ad, T t);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract boolean mo40618a(Context context, C4924Ad ad, ArrayList<?> arrayList);

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public abstract C4986p mo40622c(C4924Ad ad);

    /* renamed from: a */
    public final void mo41238a(Context context, C4924Ad ad, String str, C4955a aVar) {
        mo41239a(context, ad, str, aVar, true);
    }

    /* renamed from: a */
    public final void mo41239a(Context context, C4924Ad ad, String str, C4955a aVar, boolean z) {
        final C4924Ad ad2 = ad;
        final Context context2 = context;
        final C4955a aVar2 = aVar;
        final String str2 = str;
        final boolean z2 = z;
        new Thread(new Runnable() {
            public final void run() {
                try {
                    if (C4948d.this.mo40619a(ad2) || (C4948d.this.mo41246h(ad2) && C4948d.this.mo41247i(ad2))) {
                        C4948d.this.mo40614a(context2, ad2, C4948d.this.mo41249k(ad2));
                    }
                } catch (Throwable unused) {
                }
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public final void run() {
                        try {
                            if (C4948d.this.mo40619a(ad2) || (C4948d.this.mo41246h(ad2) && C4948d.this.mo41247i(ad2))) {
                                ArrayList<?> ads = C4948d.this.mo41249k(ad2).getAds();
                                if (ads.size() == 0) {
                                    aVar2.error(AppnextError.NO_ADS);
                                    return;
                                } else if (!C4948d.this.mo40618a(context2, ad2, ads)) {
                                    C4948d.this.mo41251l(str2);
                                } else if (aVar2 != null) {
                                    aVar2.mo40599a(ads);
                                    return;
                                } else {
                                    return;
                                }
                            }
                        } catch (Throwable unused) {
                            if (aVar2 != null) {
                                aVar2.error(AppnextError.NO_ADS);
                            }
                        }
                        try {
                            if (!C4967f.m6833bd().equals("")) {
                                C4948d.this.m6751b(context2, ad2, str2, aVar2, z2);
                                return;
                            }
                            C4967f.m6847m(context2);
                            C4948d.this.m6751b(context2, ad2, str2, aVar2, z2);
                        } catch (Throwable unused2) {
                            if (aVar2 != null) {
                                aVar2.error(AppnextError.NO_ADS);
                            }
                        }
                    }
                });
            }
        }).start();
    }

    /* renamed from: b */
    private String m6750b(Context context, C4924Ad ad, String str, ArrayList<Pair<String, String>> arrayList) {
        StringBuilder sb = new StringBuilder("https://global.appnext.com/offerWallApi.aspx?ext=t&pimp=1&igroup=sdk&m=1&osid=100&s2s=1&type=json&id=");
        sb.append(str);
        sb.append("&cnt=50");
        sb.append("&tid=");
        sb.append(ad != null ? ad.getTID() : "301");
        sb.append("&vid=");
        sb.append(ad != null ? ad.getVID() : "2.5.1.472");
        sb.append("&cat=");
        String str2 = "";
        sb.append(ad != null ? ad.getCategories() : str2);
        sb.append("&pbk=");
        sb.append(ad != null ? ad.getPostback() : str2);
        sb.append("&did=");
        sb.append(C4967f.m6827b(context, Boolean.parseBoolean(mo40622c(ad).get("didPrivacy"))));
        sb.append("&devn=");
        sb.append(C4967f.m6834be());
        sb.append("&dosv=");
        sb.append(Build.VERSION.SDK_INT);
        sb.append("&dct=");
        sb.append(C4967f.m6810Z(C4967f.m6849o(context)));
        sb.append("&lang=");
        sb.append(Locale.getDefault().getLanguage());
        sb.append("&dcc=");
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager.getSimState() == 5) {
            String simOperator = telephonyManager.getSimOperator();
            str2 = simOperator.substring(0, 3) + "_" + simOperator.substring(3);
        }
        sb.append(str2);
        sb.append("&dds=0");
        sb.append("&packageId=");
        sb.append(context.getPackageName());
        sb.append("&g=");
        sb.append(m6753j(context));
        sb.append("&rnd=");
        sb.append(new Random().nextInt());
        return sb.toString();
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m6751b(Context context, C4924Ad ad, String str, C4955a aVar, boolean z) {
        final C4924Ad ad2 = ad;
        final Context context2 = context;
        final C4955a aVar2 = aVar;
        final String str2 = str;
        final boolean z2 = z;
        new Thread() {
            /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
                java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
                	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
                	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
                	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
                	at java.base/java.util.Objects.checkIndex(Objects.java:372)
                	at java.base/java.util.ArrayList.get(ArrayList.java:458)
                	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
                	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
                	at jadx.core.dex.visitors.regions.RegionMaker.processExcHandler(RegionMaker.java:1043)
                	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:975)
                	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
                */
            public final void run() {
                /*
                    r7 = this;
                    super.run()
                    com.appnext.core.Ad r0 = r2     // Catch:{ all -> 0x000e }
                    android.content.Context r1 = r3     // Catch:{ all -> 0x000e }
                    java.lang.String r1 = com.appnext.core.C4967f.m6853r(r1)     // Catch:{ all -> 0x000e }
                    r0.setSessionId(r1)     // Catch:{ all -> 0x000e }
                L_0x000e:
                    com.appnext.core.d r0 = com.appnext.core.C4948d.this     // Catch:{ all -> 0x0179 }
                    java.util.HashMap r0 = r0.f4734fU     // Catch:{ all -> 0x0179 }
                    monitor-enter(r0)     // Catch:{ all -> 0x0179 }
                    com.appnext.core.d r1 = com.appnext.core.C4948d.this     // Catch:{ all -> 0x0176 }
                    com.appnext.core.Ad r2 = r2     // Catch:{ all -> 0x0176 }
                    com.appnext.core.a r1 = r1.mo41249k(r2)     // Catch:{ all -> 0x0176 }
                    r2 = 1
                    if (r1 == 0) goto L_0x0041
                    com.appnext.core.d r1 = com.appnext.core.C4948d.this     // Catch:{ all -> 0x0176 }
                    com.appnext.core.Ad r3 = r2     // Catch:{ all -> 0x0176 }
                    com.appnext.core.a r1 = r1.mo41249k(r3)     // Catch:{ all -> 0x0176 }
                    int r1 = r1.getState()     // Catch:{ all -> 0x0176 }
                    if (r1 != r2) goto L_0x0041
                    com.appnext.core.d$a r1 = r4     // Catch:{ all -> 0x0176 }
                    if (r1 == 0) goto L_0x003f
                    com.appnext.core.d r1 = com.appnext.core.C4948d.this     // Catch:{ all -> 0x0176 }
                    com.appnext.core.Ad r2 = r2     // Catch:{ all -> 0x0176 }
                    com.appnext.core.a r1 = r1.mo41249k(r2)     // Catch:{ all -> 0x0176 }
                    com.appnext.core.d$a r2 = r4     // Catch:{ all -> 0x0176 }
                    r1.mo41209a((com.appnext.core.C4948d.C4955a) r2)     // Catch:{ all -> 0x0176 }
                L_0x003f:
                    monitor-exit(r0)     // Catch:{ all -> 0x0176 }
                    return
                L_0x0041:
                    com.appnext.core.a r1 = new com.appnext.core.a     // Catch:{ all -> 0x0176 }
                    r1.<init>()     // Catch:{ all -> 0x0176 }
                    com.appnext.core.d$a r3 = r4     // Catch:{ all -> 0x0176 }
                    r1.mo41209a((com.appnext.core.C4948d.C4955a) r3)     // Catch:{ all -> 0x0176 }
                    java.lang.String r3 = r5     // Catch:{ all -> 0x0176 }
                    r1.setPlacementID(r3)     // Catch:{ all -> 0x0176 }
                    r1.setState(r2)     // Catch:{ all -> 0x0176 }
                    com.appnext.core.d r2 = com.appnext.core.C4948d.this     // Catch:{ all -> 0x0176 }
                    java.util.HashMap r2 = r2.f4734fU     // Catch:{ all -> 0x0176 }
                    com.appnext.core.b r3 = new com.appnext.core.b     // Catch:{ all -> 0x0176 }
                    com.appnext.core.Ad r4 = r2     // Catch:{ all -> 0x0176 }
                    r3.<init>(r4)     // Catch:{ all -> 0x0176 }
                    r2.remove(r3)     // Catch:{ all -> 0x0176 }
                    com.appnext.core.d r2 = com.appnext.core.C4948d.this     // Catch:{ all -> 0x0176 }
                    com.appnext.core.Ad r3 = r2     // Catch:{ all -> 0x0176 }
                    r2.mo41240a((com.appnext.core.C4924Ad) r3, (com.appnext.core.C4940a) r1)     // Catch:{ all -> 0x0176 }
                    monitor-exit(r0)     // Catch:{ all -> 0x0176 }
                    r0 = 0
                    java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    r1.<init>()     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    r2.<init>()     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    com.appnext.core.d r3 = com.appnext.core.C4948d.this     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    android.content.Context r4 = r3     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    com.appnext.core.Ad r5 = r2     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    java.lang.String r6 = r5     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    java.lang.String r3 = com.appnext.core.C4948d.m6744a((com.appnext.core.C4948d) r3, (android.content.Context) r4, (com.appnext.core.C4924Ad) r5, (java.lang.String) r6, (java.util.ArrayList) r1)     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    r2.append(r3)     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    com.appnext.core.d r3 = com.appnext.core.C4948d.this     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    android.content.Context r4 = r3     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    com.appnext.core.Ad r5 = r2     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    java.lang.String r6 = r5     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    java.lang.String r3 = r3.mo40613a((android.content.Context) r4, (com.appnext.core.C4924Ad) r5, (java.lang.String) r6, (java.util.ArrayList<android.util.Pair<java.lang.String, java.lang.String>>) r1)     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    r2.append(r3)     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    java.lang.String r2 = r2.toString()     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    java.lang.String r4 = "AdsManager request url: "
                    r3.<init>(r4)     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    r3.append(r2)     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    r3 = 8000(0x1f40, float:1.121E-41)
                    java.lang.String r1 = com.appnext.core.C4967f.m6814a((java.lang.String) r2, (java.util.ArrayList<android.util.Pair<java.lang.String, java.lang.String>>) r1, (int) r3)     // Catch:{ SocketTimeoutException -> 0x0167, UnknownHostException -> 0x0158, all -> 0x0149 }
                    java.lang.String r2 = "{}"
                    boolean r2 = r1.equals(r2)     // Catch:{ all -> 0x0179 }
                    if (r2 != 0) goto L_0x013f
                    boolean r2 = com.appnext.core.C4948d.m6741P(r1)     // Catch:{ all -> 0x0179 }
                    if (r2 == 0) goto L_0x00b8
                    goto L_0x013f
                L_0x00b8:
                    com.appnext.core.d r2 = com.appnext.core.C4948d.this     // Catch:{ all -> 0x0130 }
                    android.content.Context r3 = r3     // Catch:{ all -> 0x0130 }
                    com.appnext.core.Ad r4 = r2     // Catch:{ all -> 0x0130 }
                    r5 = 50
                    java.util.ArrayList r1 = r2.mo41237a((android.content.Context) r3, (com.appnext.core.C4924Ad) r4, (java.lang.String) r1, (int) r5)     // Catch:{ all -> 0x0130 }
                    int r2 = r1.size()     // Catch:{ all -> 0x0179 }
                    if (r2 != 0) goto L_0x00d4
                    com.appnext.core.d r0 = com.appnext.core.C4948d.this     // Catch:{ all -> 0x0179 }
                    java.lang.String r1 = "No Ads"
                    com.appnext.core.Ad r2 = r2     // Catch:{ all -> 0x0179 }
                    r0.mo41244b((java.lang.String) r1, (com.appnext.core.C4924Ad) r2)     // Catch:{ all -> 0x0179 }
                    return
                L_0x00d4:
                    com.appnext.core.d r2 = com.appnext.core.C4948d.this     // Catch:{ all -> 0x0179 }
                    com.appnext.core.Ad r3 = r2     // Catch:{ all -> 0x0179 }
                    com.appnext.core.a r2 = r2.mo41249k(r3)     // Catch:{ all -> 0x0179 }
                    r2.mo41214d(r1)     // Catch:{ all -> 0x0179 }
                    boolean r2 = r6     // Catch:{ all -> 0x0179 }
                    r3 = 2
                    if (r2 == 0) goto L_0x0113
                    com.appnext.core.d r2 = com.appnext.core.C4948d.this     // Catch:{ all -> 0x0179 }
                    android.content.Context r4 = r3     // Catch:{ all -> 0x0179 }
                    com.appnext.core.Ad r5 = r2     // Catch:{ all -> 0x0179 }
                    boolean r1 = r2.mo40618a((android.content.Context) r4, (com.appnext.core.C4924Ad) r5, (java.util.ArrayList<?>) r1)     // Catch:{ all -> 0x0179 }
                    if (r1 != 0) goto L_0x00f7
                    com.appnext.core.d r1 = com.appnext.core.C4948d.this     // Catch:{ all -> 0x0179 }
                    java.lang.String r2 = r5     // Catch:{ all -> 0x0179 }
                    r1.mo41251l((java.lang.String) r2)     // Catch:{ all -> 0x0179 }
                L_0x00f7:
                    r1 = 3
                    if (r0 >= r1) goto L_0x0113
                    com.appnext.core.d r1 = com.appnext.core.C4948d.this     // Catch:{ all -> 0x010c }
                    android.content.Context r2 = r3     // Catch:{ all -> 0x010c }
                    com.appnext.core.Ad r4 = r2     // Catch:{ all -> 0x010c }
                    com.appnext.core.d r5 = com.appnext.core.C4948d.this     // Catch:{ all -> 0x010c }
                    com.appnext.core.Ad r6 = r2     // Catch:{ all -> 0x010c }
                    com.appnext.core.a r5 = r5.mo41249k(r6)     // Catch:{ all -> 0x010c }
                    r1.mo40614a((android.content.Context) r2, (com.appnext.core.C4924Ad) r4, (com.appnext.core.C4940a) r5)     // Catch:{ all -> 0x010c }
                    goto L_0x0113
                L_0x010c:
                    r1 = move-exception
                    if (r0 == r3) goto L_0x0112
                    int r0 = r0 + 1
                    goto L_0x00f7
                L_0x0112:
                    throw r1     // Catch:{ all -> 0x0179 }
                L_0x0113:
                    com.appnext.core.d r0 = com.appnext.core.C4948d.this     // Catch:{ all -> 0x0179 }
                    com.appnext.core.Ad r1 = r2     // Catch:{ all -> 0x0179 }
                    com.appnext.core.a r0 = r0.mo41249k(r1)     // Catch:{ all -> 0x0179 }
                    r0.setState(r3)     // Catch:{ all -> 0x0179 }
                    android.os.Handler r0 = new android.os.Handler     // Catch:{ all -> 0x0179 }
                    android.os.Looper r1 = android.os.Looper.getMainLooper()     // Catch:{ all -> 0x0179 }
                    r0.<init>(r1)     // Catch:{ all -> 0x0179 }
                    com.appnext.core.d$2$1 r1 = new com.appnext.core.d$2$1     // Catch:{ all -> 0x0179 }
                    r1.<init>()     // Catch:{ all -> 0x0179 }
                    r0.post(r1)     // Catch:{ all -> 0x0179 }
                    return
                L_0x0130:
                    r0 = move-exception
                    com.appnext.core.d r1 = com.appnext.core.C4948d.this     // Catch:{ all -> 0x0179 }
                    java.lang.String r2 = "Internal error"
                    java.lang.String r0 = com.appnext.core.C4967f.m6829b((java.lang.Throwable) r0)     // Catch:{ all -> 0x0179 }
                    com.appnext.core.Ad r3 = r2     // Catch:{ all -> 0x0179 }
                    r1.mo41241a((java.lang.String) r2, (java.lang.String) r0, (com.appnext.core.C4924Ad) r3)     // Catch:{ all -> 0x0179 }
                    return
                L_0x013f:
                    com.appnext.core.d r0 = com.appnext.core.C4948d.this     // Catch:{ all -> 0x0179 }
                    java.lang.String r2 = "No Ads"
                    com.appnext.core.Ad r3 = r2     // Catch:{ all -> 0x0179 }
                    r0.mo41241a((java.lang.String) r2, (java.lang.String) r1, (com.appnext.core.C4924Ad) r3)     // Catch:{ all -> 0x0179 }
                    return
                L_0x0149:
                    r0 = move-exception
                    com.appnext.core.d r1 = com.appnext.core.C4948d.this     // Catch:{ all -> 0x0179 }
                    java.lang.String r2 = "Internal error"
                    java.lang.String r0 = com.appnext.core.C4967f.m6829b((java.lang.Throwable) r0)     // Catch:{ all -> 0x0179 }
                    com.appnext.core.Ad r3 = r2     // Catch:{ all -> 0x0179 }
                    r1.mo41241a((java.lang.String) r2, (java.lang.String) r0, (com.appnext.core.C4924Ad) r3)     // Catch:{ all -> 0x0179 }
                    return
                L_0x0158:
                    r1 = move-exception
                    com.appnext.core.d r2 = com.appnext.core.C4948d.this     // Catch:{ all -> 0x0179 }
                    java.lang.String r3 = "Connection Error"
                    java.lang.String r1 = com.appnext.core.C4967f.m6829b((java.lang.Throwable) r1)     // Catch:{ all -> 0x0179 }
                    com.appnext.core.Ad r4 = r2     // Catch:{ all -> 0x0179 }
                    r2.mo41242a((java.lang.String) r3, (java.lang.String) r1, (com.appnext.core.C4924Ad) r4, (int) r0)     // Catch:{ all -> 0x0179 }
                    return
                L_0x0167:
                    r1 = move-exception
                    com.appnext.core.d r2 = com.appnext.core.C4948d.this     // Catch:{ all -> 0x0179 }
                    java.lang.String r3 = "Timeout"
                    java.lang.String r1 = com.appnext.core.C4967f.m6829b((java.lang.Throwable) r1)     // Catch:{ all -> 0x0179 }
                    com.appnext.core.Ad r4 = r2     // Catch:{ all -> 0x0179 }
                    r2.mo41242a((java.lang.String) r3, (java.lang.String) r1, (com.appnext.core.C4924Ad) r4, (int) r0)     // Catch:{ all -> 0x0179 }
                    return
                L_0x0176:
                    r1 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x0176 }
                    throw r1     // Catch:{ all -> 0x0179 }
                L_0x0179:
                    r0 = move-exception
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder
                    java.lang.String r2 = "finished custom after load with error "
                    r1.<init>(r2)
                    java.lang.String r2 = com.appnext.core.C4967f.m6829b((java.lang.Throwable) r0)
                    r1.append(r2)
                    com.appnext.core.d r1 = com.appnext.core.C4948d.this
                    java.lang.String r0 = r0.getMessage()
                    com.appnext.core.Ad r2 = r2
                    r1.mo41244b((java.lang.String) r0, (com.appnext.core.C4924Ad) r2)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.appnext.core.C4948d.C49512.run():void");
            }
        }.start();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo40619a(C4924Ad ad) {
        try {
            return mo41246h(ad) && mo41247i(ad);
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public final boolean mo41246h(C4924Ad ad) {
        return (this.f4734fU == null || mo41249k(ad) == null || mo41249k(ad).getState() != 2 || mo41249k(ad).getAds() == null) ? false : true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: i */
    public final boolean mo41247i(C4924Ad ad) {
        return mo41248j(ad) == 0 ? this.f4734fU != null && mo41249k(ad) != null && mo41249k(ad).getAds().size() == 0 && mo41249k(ad).mo41212aU().longValue() + 60000 > System.currentTimeMillis() : (this.f4734fU == null || mo41249k(ad) == null || mo41249k(ad).mo41212aU().longValue() + mo41248j(ad) <= System.currentTimeMillis()) ? false : true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: j */
    public final long mo41248j(C4924Ad ad) {
        try {
            return mo40622c(ad).get("_cachingRequest") == null ? m6742a(ad, "ads_caching_time_minutes") * 60000 : m6742a(ad, "_cachingRequest") * 1000;
        } catch (Throwable unused) {
            return m6742a(ad, "ads_caching_time_minutes") * 60000;
        }
    }

    /* renamed from: a */
    private long m6742a(C4924Ad ad, String str) {
        return Long.valueOf(mo40622c(ad).get(str)).longValue();
    }

    /* renamed from: c */
    public final void mo41245c(Context context, C4924Ad ad, String str) {
        this.f4734fU.remove(new C4946b(ad));
        m6751b(context, ad, str, (C4955a) null, true);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x007f, code lost:
        if (r7.getRevenueType().equals("cpc") != false) goto L_0x0081;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.ArrayList<? extends com.appnext.core.C4972g> mo41237a(final android.content.Context r11, com.appnext.core.C4924Ad r12, java.lang.String r13, int r14) throws org.json.JSONException {
        /*
            r10 = this;
            com.appnext.core.a r14 = r10.mo41249k(r12)
            r14.mo41206N(r13)
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            org.json.JSONObject r1 = new org.json.JSONObject
            r1.<init>(r13)
            java.lang.String r13 = "apps"
            org.json.JSONArray r13 = r1.getJSONArray(r13)
            r1 = 0
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
        L_0x0021:
            int r6 = r13.length()
            if (r1 >= r6) goto L_0x00b6
            org.json.JSONObject r6 = r13.getJSONObject(r1)
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x00b2 }
            com.appnext.core.g r6 = parseAd(r6)     // Catch:{ all -> 0x00b2 }
            com.appnext.core.AppnextAd r6 = (com.appnext.core.AppnextAd) r6     // Catch:{ all -> 0x00b2 }
            int r7 = r14.size()     // Catch:{ all -> 0x00b2 }
            r6.setAdID(r7)     // Catch:{ all -> 0x00b2 }
            java.lang.String r7 = r12.getPlacementID()     // Catch:{ all -> 0x00b2 }
            r6.setPlacementID(r7)     // Catch:{ all -> 0x00b2 }
            int r7 = r10.mo40610a((android.content.Context) r11, (com.appnext.core.C4972g) r6)     // Catch:{ all -> 0x00b2 }
            if (r7 != 0) goto L_0x008b
            com.appnext.core.AppnextAd r7 = m6749b((java.util.ArrayList<com.appnext.core.AppnextAd>) r14, (com.appnext.core.AppnextAd) r6)     // Catch:{ all -> 0x00b2 }
            if (r7 == 0) goto L_0x0085
            r14.remove(r7)     // Catch:{ all -> 0x00b2 }
            java.lang.String r8 = r7.getRevenueType()     // Catch:{ all -> 0x00b2 }
            java.lang.String r9 = r6.getRevenueType()     // Catch:{ all -> 0x00b2 }
            boolean r8 = r8.equals(r9)     // Catch:{ all -> 0x00b2 }
            if (r8 == 0) goto L_0x0075
            java.lang.String r8 = r7.getRevenueRate()     // Catch:{ all -> 0x00b2 }
            float r8 = java.lang.Float.parseFloat(r8)     // Catch:{ all -> 0x00b2 }
            java.lang.String r9 = r6.getRevenueRate()     // Catch:{ all -> 0x00b2 }
            float r9 = java.lang.Float.parseFloat(r9)     // Catch:{ all -> 0x00b2 }
            int r8 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
            if (r8 < 0) goto L_0x0082
            goto L_0x0081
        L_0x0075:
            java.lang.String r8 = r7.getRevenueType()     // Catch:{ all -> 0x00b2 }
            java.lang.String r9 = "cpc"
            boolean r8 = r8.equals(r9)     // Catch:{ all -> 0x00b2 }
            if (r8 == 0) goto L_0x0082
        L_0x0081:
            r6 = r7
        L_0x0082:
            int r5 = r5 + 1
            goto L_0x0087
        L_0x0085:
            int r2 = r2 + 1
        L_0x0087:
            r14.add(r6)     // Catch:{ all -> 0x00b2 }
            goto L_0x00a9
        L_0x008b:
            java.lang.String r6 = r6.getBannerID()     // Catch:{ all -> 0x00b2 }
            r0.append(r6)     // Catch:{ all -> 0x00b2 }
            java.lang.String r6 = ","
            r0.append(r6)     // Catch:{ all -> 0x00b2 }
            r6 = 1
            if (r7 == r6) goto L_0x00a7
            r6 = 2
            if (r7 == r6) goto L_0x00a4
            r6 = 3
            if (r7 == r6) goto L_0x00a1
            goto L_0x00a9
        L_0x00a1:
            int r5 = r5 + 1
            goto L_0x00a9
        L_0x00a4:
            int r4 = r4 + 1
            goto L_0x00a9
        L_0x00a7:
            int r3 = r3 + 1
        L_0x00a9:
            int r6 = r14.size()     // Catch:{ all -> 0x00b2 }
            r7 = 50
            if (r6 != r7) goto L_0x00b2
            goto L_0x00b6
        L_0x00b2:
            int r1 = r1 + 1
            goto L_0x0021
        L_0x00b6:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r13 = "Filtering values {count = "
            r12.<init>(r13)
            r12.append(r2)
            java.lang.String r13 = ", new filtered = "
            r12.append(r13)
            r12.append(r3)
            java.lang.String r13 = ", existing  filtered = "
            r12.append(r13)
            r12.append(r4)
            java.lang.String r13 = ",  other = "
            r12.append(r13)
            r12.append(r5)
            java.lang.Thread r12 = new java.lang.Thread
            com.appnext.core.d$3 r13 = new com.appnext.core.d$3
            r13.<init>(r0, r11)
            r12.<init>(r13)
            r12.start()
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.core.C4948d.mo41237a(android.content.Context, com.appnext.core.Ad, java.lang.String, int):java.util.ArrayList");
    }

    /* renamed from: a */
    private static AppnextAd m6743a(AppnextAd appnextAd, AppnextAd appnextAd2) {
        return appnextAd.getRevenueType().equals(appnextAd2.getRevenueType()) ? Float.parseFloat(appnextAd.getRevenueRate()) < Float.parseFloat(appnextAd2.getRevenueRate()) ? appnextAd2 : appnextAd : appnextAd.getRevenueType().equals("cpc") ? appnextAd : appnextAd2;
    }

    /* renamed from: b */
    private static AppnextAd m6749b(ArrayList<AppnextAd> arrayList, AppnextAd appnextAd) {
        Iterator<AppnextAd> it = arrayList.iterator();
        while (it.hasNext()) {
            AppnextAd next = it.next();
            if (next.getAdPackage().equals(appnextAd.getAdPackage())) {
                return next;
            }
        }
        return null;
    }

    /* renamed from: P */
    protected static boolean m6741P(String str) {
        try {
            return new JSONObject(str).has("rnd");
        } catch (Throwable unused) {
            return true;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public String mo40776a(ArrayList<AppnextAd> arrayList) {
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator<AppnextAd> it = arrayList.iterator();
            while (it.hasNext()) {
                jSONArray.put(new JSONObject(it.next().getAdJSON()));
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("apps", jSONArray);
            return jSONObject.toString().replace(" ", "\\u2028").replace(" ", "\\u2029");
        } catch (Throwable unused) {
            return "";
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo41244b(String str, C4924Ad ad) {
        mo41241a(str, "", ad);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo41241a(String str, String str2, C4924Ad ad) {
        mo41242a(str, str2, ad, 2);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo41242a(String str, String str2, C4924Ad ad, int i) {
        final C4924Ad ad2 = ad;
        final int i2 = i;
        final String str3 = str;
        final String str4 = str2;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public final void run() {
                C4940a k = C4948d.this.mo41249k(ad2);
                if (k != null) {
                    if (k.getAds() == null) {
                        k.mo41214d(new ArrayList());
                    } else {
                        k.mo41214d(k.getAds());
                    }
                    k.setState(i2);
                    k.mo41207O(str3);
                    C4948d dVar = C4948d.this;
                    C4924Ad ad = ad2;
                    dVar.mo40615a(ad, str3 + StringUtils.SPACE + str4, k.getPlacementID());
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    /* renamed from: k */
    public final C4940a mo41249k(C4924Ad ad) {
        return this.f4734fU.get(new C4946b(ad));
    }

    /* access modifiers changed from: protected */
    /* renamed from: aW */
    public final HashMap<C4946b, C4940a> mo41243aW() {
        return this.f4734fU;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo41240a(C4924Ad ad, C4940a aVar) {
        this.f4734fU.put(new C4946b(ad), aVar);
    }

    /* renamed from: d */
    public static String m6752d(AppnextAd appnextAd) {
        return appnextAd.getAdJSON();
    }

    /* renamed from: l */
    public final String mo41250l(C4924Ad ad) {
        return mo41249k(ad).mo41205A();
    }

    public static C4972g parseAd(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            AppnextAd appnextAd = (AppnextAd) C4981l.m6868a((Class<?>) AppnextAd.class, jSONObject);
            if (appnextAd != null) {
                appnextAd.setAdJSON(jSONObject.toString());
                if (jSONObject.has("sid")) {
                    appnextAd.setSession(jSONObject.getString("sid"));
                }
                if (appnextAd.getStoreRating().equals("")) {
                    appnextAd.setStoreRating("0");
                }
            }
            return appnextAd;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: a */
    protected static boolean m6747a(String str, String str2) {
        return C4975j.m6859bj().mo41289o(str, str2);
    }

    /* access modifiers changed from: protected */
    /* renamed from: l */
    public void mo41251l(String str) {
        C4975j.m6859bj().mo41285ab(str);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo40616a(String str, C4924Ad ad) {
        C4975j.m6859bj().mo41288n(str, ad.getPlacementID());
    }

    /* renamed from: j */
    protected static int m6753j(Context context) {
        try {
            if (C4967f.m6823a(context, "android.permission.READ_CONTACTS") && C4967f.m6823a(context, "android.permission.GET_ACCOUNTS")) {
                return AccountManager.get(context).getAccountsByType(AccountType.GOOGLE).length > 0 ? 0 : 1;
            }
        } catch (Throwable unused) {
        }
        return 2;
    }

    /* renamed from: a */
    static /* synthetic */ String m6744a(C4948d dVar, Context context, C4924Ad ad, String str, ArrayList arrayList) {
        StringBuilder sb = new StringBuilder("https://global.appnext.com/offerWallApi.aspx?ext=t&pimp=1&igroup=sdk&m=1&osid=100&s2s=1&type=json&id=");
        sb.append(str);
        sb.append("&cnt=50");
        sb.append("&tid=");
        sb.append(ad != null ? ad.getTID() : "301");
        sb.append("&vid=");
        sb.append(ad != null ? ad.getVID() : "2.5.1.472");
        sb.append("&cat=");
        String str2 = "";
        sb.append(ad != null ? ad.getCategories() : str2);
        sb.append("&pbk=");
        sb.append(ad != null ? ad.getPostback() : str2);
        sb.append("&did=");
        sb.append(C4967f.m6827b(context, Boolean.parseBoolean(dVar.mo40622c(ad).get("didPrivacy"))));
        sb.append("&devn=");
        sb.append(C4967f.m6834be());
        sb.append("&dosv=");
        sb.append(Build.VERSION.SDK_INT);
        sb.append("&dct=");
        sb.append(C4967f.m6810Z(C4967f.m6849o(context)));
        sb.append("&lang=");
        sb.append(Locale.getDefault().getLanguage());
        sb.append("&dcc=");
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager.getSimState() == 5) {
            String simOperator = telephonyManager.getSimOperator();
            str2 = simOperator.substring(0, 3) + "_" + simOperator.substring(3);
        }
        sb.append(str2);
        sb.append("&dds=0");
        sb.append("&packageId=");
        sb.append(context.getPackageName());
        sb.append("&g=");
        sb.append(m6753j(context));
        sb.append("&rnd=");
        sb.append(new Random().nextInt());
        return sb.toString();
    }
}
