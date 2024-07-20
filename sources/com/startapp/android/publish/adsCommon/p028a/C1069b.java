package com.startapp.android.publish.adsCommon.p028a;

import com.startapp.android.publish.common.model.AdPreferences;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.startapp.android.publish.adsCommon.a.b */
/* compiled from: StartAppSDK */
public class C1069b {

    /* renamed from: a */
    private static C1069b f1006a = new C1069b();

    /* renamed from: b */
    private List<C1068a> f1007b = new ArrayList();

    /* renamed from: c */
    private Map<AdPreferences.Placement, List<C1068a>> f1008c = new HashMap();

    /* renamed from: d */
    private Map<String, List<C1068a>> f1009d = new HashMap();

    /* renamed from: a */
    public static C1069b m1217a() {
        return f1006a;
    }

    /* renamed from: b */
    public void mo14656b() {
        this.f1007b.clear();
        this.f1008c.clear();
        this.f1009d.clear();
    }

    /* renamed from: c */
    public List<C1068a> mo14657c() {
        return this.f1007b;
    }

    /* renamed from: a */
    public List<C1068a> mo14653a(AdPreferences.Placement placement) {
        return this.f1008c.get(placement);
    }

    /* renamed from: a */
    public List<C1068a> mo14654a(String str) {
        return this.f1009d.get(str);
    }

    /* renamed from: a */
    public synchronized void mo14655a(C1068a aVar) {
        this.f1007b.add(0, aVar);
        List list = this.f1008c.get(aVar.mo14649a());
        if (list == null) {
            list = new ArrayList();
            this.f1008c.put(aVar.mo14649a(), list);
        }
        list.add(0, aVar);
        List list2 = this.f1009d.get(aVar.mo14650b());
        if (list2 == null) {
            list2 = new ArrayList();
            this.f1009d.put(aVar.mo14650b(), list2);
        }
        list2.add(0, aVar);
    }

    /* renamed from: d */
    public int mo14658d() {
        return this.f1007b.size();
    }
}
