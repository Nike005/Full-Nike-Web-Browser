package com.startapp.common.p047d;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.startapp.common.p046c.C1295b;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* renamed from: com.startapp.common.d.b */
/* compiled from: StartAppSDK */
public class C1300b implements CookieStore {

    /* renamed from: a */
    private final CookieStore f1561a = new CookieManager().getCookieStore();

    /* renamed from: b */
    private final SharedPreferences f1562b;

    public C1300b(Context context) {
        HttpCookie httpCookie;
        this.f1562b = context.getApplicationContext().getSharedPreferences("com.startapp.android.publish.CookiePrefsFile", 0);
        String string = this.f1562b.getString("names", (String) null);
        if (string != null) {
            for (String str : TextUtils.split(string, ";")) {
                SharedPreferences sharedPreferences = this.f1562b;
                String string2 = sharedPreferences.getString("cookie_" + str, (String) null);
                if (!(string2 == null || (httpCookie = (HttpCookie) C1295b.m2179a(string2, HttpCookie.class)) == null)) {
                    if (httpCookie.hasExpired()) {
                        m2196a(httpCookie);
                        m2199b();
                    } else {
                        this.f1561a.add(URI.create(httpCookie.getDomain()), httpCookie);
                    }
                }
            }
        }
    }

    public void add(URI uri, HttpCookie httpCookie) {
        String b = m2198b(httpCookie);
        this.f1561a.add(uri, httpCookie);
        m2197a(httpCookie, b);
        m2199b();
    }

    public List<HttpCookie> get(URI uri) {
        return this.f1561a.get(uri);
    }

    public List<HttpCookie> getCookies() {
        return this.f1561a.getCookies();
    }

    public List<URI> getURIs() {
        return this.f1561a.getURIs();
    }

    public boolean remove(URI uri, HttpCookie httpCookie) {
        if (!this.f1561a.remove(uri, httpCookie)) {
            return false;
        }
        m2196a(httpCookie);
        m2199b();
        return true;
    }

    public boolean removeAll() {
        if (!this.f1561a.removeAll()) {
            return false;
        }
        m2195a();
        m2199b();
        return true;
    }

    /* renamed from: a */
    private void m2197a(HttpCookie httpCookie, String str) {
        SharedPreferences.Editor edit = this.f1562b.edit();
        edit.putString("cookie_" + str, C1295b.m2180a(httpCookie));
        edit.commit();
    }

    /* renamed from: a */
    private void m2195a() {
        SharedPreferences.Editor edit = this.f1562b.edit();
        edit.clear();
        edit.commit();
    }

    /* renamed from: a */
    private void m2196a(HttpCookie httpCookie) {
        SharedPreferences.Editor edit = this.f1562b.edit();
        edit.remove("cookie_" + m2198b(httpCookie));
        edit.commit();
    }

    /* renamed from: b */
    private void m2199b() {
        SharedPreferences.Editor edit = this.f1562b.edit();
        edit.putString("names", TextUtils.join(";", m2200c()));
        edit.commit();
    }

    /* renamed from: b */
    private String m2198b(HttpCookie httpCookie) {
        return httpCookie.getDomain() + "_" + httpCookie.getName();
    }

    /* renamed from: c */
    private Set<String> m2200c() {
        HashSet hashSet = new HashSet();
        for (HttpCookie b : this.f1561a.getCookies()) {
            hashSet.add(m2198b(b));
        }
        return hashSet;
    }
}
