package com.startapp.android.publish.ads.video;

import android.content.Context;
import android.util.Base64;
import com.startapp.android.publish.ads.video.C0984c;
import com.startapp.android.publish.ads.video.C1035g;
import com.startapp.android.publish.adsCommon.C1098b;
import com.startapp.android.publish.cache.C1206h;
import com.startapp.common.C1303g;
import com.startapp.common.p043a.C1267e;
import com.startapp.common.p043a.C1270g;
import java.io.File;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.LinkedList;

/* renamed from: com.startapp.android.publish.ads.video.d */
/* compiled from: StartAppSDK */
public class C1004d {

    /* renamed from: a */
    private static C1004d f847a = new C1004d();

    /* renamed from: b */
    private LinkedList<C1206h> f848b = new LinkedList<>();

    private C1004d() {
    }

    /* renamed from: a */
    public void mo14365a(Context context, String str, C1035g.C1037a aVar, C0984c.C0987a aVar2) {
        final Context context2 = context;
        final String str2 = str;
        final C1035g.C1037a aVar3 = aVar;
        final C0984c.C0987a aVar4 = aVar2;
        C1303g.m2206a(C1303g.C1307a.HIGH, (Runnable) new Runnable() {
            public void run() {
                C1004d.this.m1004b(context2, str2, aVar3, aVar4);
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m1004b(final Context context, String str, final C1035g.C1037a aVar, final C0984c.C0987a aVar2) {
        String str2;
        C1270g.m2078a("VideoAdCacheManager", 3, "Full cache: " + str);
        m1001a(context);
        try {
            URL url = new URL(str);
            String str3 = url.getHost() + url.getPath().replace("/", "_");
            try {
                String substring = str3.substring(0, str3.lastIndexOf(46));
                str2 = new String(Base64.encodeToString(MessageDigest.getInstance("MD5").digest(substring.getBytes()), 0)).replaceAll("[^a-zA-Z0-9]+", "_") + str3.substring(str3.lastIndexOf(46));
            } catch (NoSuchAlgorithmException e) {
                C1270g.m2079a("VideoAdCacheManager", 6, "Error applying MD5 digest to filename " + str3, e);
                str2 = str3;
            }
            final C1206h hVar = new C1206h(str2);
            new C1035g(context, url, str2, new C1035g.C1037a() {
                /* renamed from: a */
                public void mo14262a(String str) {
                    C1035g.C1037a aVar = aVar;
                    if (aVar != null) {
                        aVar.mo14262a(str);
                    }
                    if (str != null) {
                        hVar.mo15119a(System.currentTimeMillis());
                        hVar.mo15120a(str);
                        C1004d.this.mo14364a(context, hVar);
                    }
                }
            }, new C0984c.C0987a() {
                /* renamed from: a */
                public void mo14263a(String str) {
                    C0984c.C0987a aVar = aVar2;
                    if (aVar != null) {
                        aVar.mo14263a(str);
                    }
                }
            }).mo14415a();
        } catch (MalformedURLException e2) {
            C1270g.m2079a("VideoAdCacheManager", 6, "Malformed url " + str, e2);
            if (aVar != null) {
                aVar.mo14262a((String) null);
            }
        }
    }

    /* renamed from: a */
    public boolean mo14366a(int i) {
        Iterator it = this.f848b.iterator();
        boolean z = false;
        while (it.hasNext() && this.f848b.size() > i) {
            C1206h hVar = (C1206h) it.next();
            if (!C1038h.m1118a(hVar.mo15121b())) {
                z = true;
                it.remove();
                if (hVar.mo15121b() != null) {
                    new File(hVar.mo15121b()).delete();
                    C1270g.m2078a("VideoAdCacheManager", 3, "cachedVideoAds reached the maximum of " + i + " videos - removed " + hVar.mo15118a() + " Size = " + this.f848b.size());
                }
            }
        }
        return z;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo14364a(Context context, C1206h hVar) {
        if (this.f848b.contains(hVar)) {
            this.f848b.remove(hVar);
            C1270g.m2078a("VideoAdCacheManager", 3, "cachedVideoAds already contained " + hVar.mo15118a() + " - removed. Size = " + this.f848b.size());
        }
        mo14366a(C1098b.m1303a().mo14754H().mo15024b() - 1);
        this.f848b.add(hVar);
        m1003b(context);
        C1270g.m2078a("VideoAdCacheManager", 3, "Added " + hVar.mo15118a() + " to cachedVideoAds. Size = " + this.f848b.size());
    }

    /* renamed from: a */
    private void m1001a(Context context) {
        if (this.f848b == null) {
            LinkedList<C1206h> linkedList = (LinkedList) C1267e.m2057a(context, "CachedAds", LinkedList.class);
            this.f848b = linkedList;
            if (linkedList == null) {
                this.f848b = new LinkedList<>();
            }
            if (mo14366a(C1098b.m1303a().mo14754H().mo15024b())) {
                m1003b(context);
            }
        }
    }

    /* renamed from: b */
    private void m1003b(Context context) {
        C1267e.m2070b(context, "CachedAds", (Serializable) this.f848b);
    }

    /* renamed from: a */
    public static C1004d m1000a() {
        return f847a;
    }
}
