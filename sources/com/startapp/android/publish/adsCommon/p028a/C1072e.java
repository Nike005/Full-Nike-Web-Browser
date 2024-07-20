package com.startapp.android.publish.adsCommon.p028a;

import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.Constants;
import com.startapp.common.p043a.C1270g;
import com.startapp.common.p046c.C5303f;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.startapp.android.publish.adsCommon.a.e */
/* compiled from: StartAppSDK */
public class C1072e implements Serializable {
    private static final long serialVersionUID = 1;

    /* renamed from: a */
    private transient Set<Class<? extends C1070c>> f1015a = new HashSet();
    private boolean applyOnBannerRefresh = true;
    @C5303f(mo45478b = HashMap.class, mo45479c = ArrayList.class, mo45480d = AdPreferences.Placement.class, mo45481e = C1070c.class)
    private Map<AdPreferences.Placement, List<C1070c>> placements = new HashMap();
    @C5303f(mo45478b = ArrayList.class, mo45479c = C1070c.class)
    private List<C1070c> session = new ArrayList();
    @C5303f(mo45478b = HashMap.class, mo45479c = ArrayList.class, mo45481e = C1070c.class)
    private Map<String, List<C1070c>> tags = new HashMap();

    /* renamed from: a */
    public boolean mo14662a() {
        return this.applyOnBannerRefresh;
    }

    /* renamed from: a */
    public synchronized C1073f mo14661a(AdPreferences.Placement placement, String str) {
        C1073f a;
        String str2;
        this.f1015a.clear();
        a = m1226a(this.tags.get(str), C1069b.m1217a().mo14654a(str), C1071d.TAG, str);
        if (a.mo14664a()) {
            a = m1226a(this.placements.get(placement), C1069b.m1217a().mo14653a(placement), C1071d.PLACEMENT, placement.toString());
            if (a.mo14664a()) {
                a = m1226a(this.session, C1069b.m1217a().mo14657c(), C1071d.SESSION, "session");
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("shouldDisplayAd result: ");
        sb.append(a.mo14664a());
        if (a.mo14664a()) {
            str2 = "";
        } else {
            str2 = " because of rule " + a.mo14665b();
        }
        sb.append(str2);
        C1270g.m2078a("AdRules", 3, sb.toString());
        return a;
    }

    /* renamed from: a */
    private C1073f m1226a(List<C1070c> list, List<C1068a> list2, C1071d dVar, String str) {
        String str2;
        if (list == null) {
            return new C1073f(true);
        }
        for (C1070c next : list) {
            if (next.mo14659a() || !this.f1015a.contains(next.getClass())) {
                if (!next.mo14660a(list2)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(next.getClass().getSimpleName());
                    sb.append("_");
                    sb.append(dVar);
                    if (Constants.m1978a().booleanValue()) {
                        str2 = StringUtils.SPACE + str + ":" + next;
                    } else {
                        str2 = "";
                    }
                    sb.append(str2);
                    return new C1073f(false, sb.toString());
                }
                this.f1015a.add(next.getClass());
            }
        }
        return new C1073f(true);
    }

    /* renamed from: b */
    public void mo14663b() {
        this.f1015a = new HashSet();
    }
}
