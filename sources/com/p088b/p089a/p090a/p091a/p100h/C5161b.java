package com.p088b.p089a.p090a.p091a.p100h;

import android.view.View;
import android.view.ViewParent;
import com.p088b.p089a.p090a.p091a.p093b.C5118i;
import com.p088b.p089a.p090a.p091a.p095c.C5120a;
import com.p088b.p089a.p090a.p091a.p097e.C5140f;
import com.p088b.p089a.p090a.p091a.p098f.C5141a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

/* renamed from: com.b.a.a.a.h.b */
public class C5161b {

    /* renamed from: a */
    private final HashMap<View, String> f5038a = new HashMap<>();

    /* renamed from: b */
    private final HashMap<View, ArrayList<String>> f5039b = new HashMap<>();

    /* renamed from: c */
    private final HashSet<View> f5040c = new HashSet<>();

    /* renamed from: d */
    private final HashSet<String> f5041d = new HashSet<>();

    /* renamed from: e */
    private final HashSet<String> f5042e = new HashSet<>();

    /* renamed from: f */
    private boolean f5043f;

    /* renamed from: a */
    private void m7211a(View view, C5118i iVar) {
        ArrayList arrayList = this.f5039b.get(view);
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.f5039b.put(view, arrayList);
        }
        arrayList.add(iVar.mo41852g());
    }

    /* renamed from: a */
    private void m7212a(C5118i iVar) {
        for (C5141a aVar : iVar.mo41849d()) {
            View view = (View) aVar.get();
            if (view != null) {
                m7211a(view, iVar);
            }
        }
    }

    /* renamed from: d */
    private boolean m7213d(View view) {
        if (!view.hasWindowFocus()) {
            return false;
        }
        HashSet hashSet = new HashSet();
        while (view != null) {
            if (!C5140f.m7149d(view)) {
                return false;
            }
            hashSet.add(view);
            ViewParent parent = view.getParent();
            view = parent instanceof View ? (View) parent : null;
        }
        this.f5040c.addAll(hashSet);
        return true;
    }

    /* renamed from: a */
    public String mo41947a(View view) {
        if (this.f5038a.size() == 0) {
            return null;
        }
        String str = this.f5038a.get(view);
        if (str != null) {
            this.f5038a.remove(view);
        }
        return str;
    }

    /* renamed from: a */
    public HashSet<String> mo41948a() {
        return this.f5041d;
    }

    /* renamed from: b */
    public ArrayList<String> mo41949b(View view) {
        if (this.f5039b.size() == 0) {
            return null;
        }
        ArrayList<String> arrayList = this.f5039b.get(view);
        if (arrayList != null) {
            this.f5039b.remove(view);
            Collections.sort(arrayList);
        }
        return arrayList;
    }

    /* renamed from: b */
    public HashSet<String> mo41950b() {
        return this.f5042e;
    }

    /* renamed from: c */
    public C5163d mo41951c(View view) {
        return this.f5040c.contains(view) ? C5163d.PARENT_VIEW : this.f5043f ? C5163d.OBSTRUCTION_VIEW : C5163d.UNDERLYING_VIEW;
    }

    /* renamed from: c */
    public void mo41952c() {
        C5120a a = C5120a.m7050a();
        if (a != null) {
            for (C5118i next : a.mo41868c()) {
                View h = next.mo41853h();
                if (next.mo41854i()) {
                    if (h == null || !m7213d(h)) {
                        this.f5042e.add(next.mo41852g());
                    } else {
                        this.f5041d.add(next.mo41852g());
                        this.f5038a.put(h, next.mo41852g());
                        m7212a(next);
                    }
                }
            }
        }
    }

    /* renamed from: d */
    public void mo41953d() {
        this.f5038a.clear();
        this.f5039b.clear();
        this.f5040c.clear();
        this.f5041d.clear();
        this.f5042e.clear();
        this.f5043f = false;
    }

    /* renamed from: e */
    public void mo41954e() {
        this.f5043f = true;
    }
}
