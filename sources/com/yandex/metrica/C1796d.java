package com.yandex.metrica;

import android.text.TextUtils;
import java.util.Map;

/* renamed from: com.yandex.metrica.d */
public class C1796d {

    /* renamed from: a */
    private String f2837a;

    /* renamed from: b */
    private String f2838b;

    /* renamed from: c */
    private Map<String, String> f2839c;

    /* renamed from: a */
    public String mo16691a() {
        return this.f2837a;
    }

    /* renamed from: a */
    public void mo16692a(String str) {
        this.f2837a = str;
    }

    /* renamed from: b */
    public String mo16694b() {
        return this.f2838b;
    }

    /* renamed from: b */
    public void mo16695b(String str) {
        this.f2838b = str;
    }

    /* renamed from: c */
    public Map<String, String> mo16696c() {
        return this.f2839c;
    }

    /* renamed from: a */
    public void mo16693a(Map<String, String> map) {
        this.f2839c = map;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C1796d)) {
            return false;
        }
        C1796d dVar = (C1796d) obj;
        if (!TextUtils.equals(this.f2837a, dVar.f2837a) || !TextUtils.equals(this.f2838b, dVar.f2838b)) {
            return false;
        }
        Map<String, String> map = this.f2839c;
        Map<String, String> map2 = dVar.f2839c;
        if (map == map2 || map == null || map.equals(map2)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        String str = this.f2837a;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.f2838b;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        Map<String, String> map = this.f2839c;
        if (map != null) {
            i = map.hashCode();
        }
        return hashCode2 + i;
    }
}
