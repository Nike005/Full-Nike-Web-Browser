package com.appnext.base.operations;

import android.os.Bundle;
import com.appnext.base.operations.C4910a;
import com.appnext.base.p078a.p080b.C4887c;
import com.appnext.base.p082b.C4905i;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.appnext.base.operations.b */
public class C4912b {

    /* renamed from: eq */
    private static final String f4656eq = "com.appnext.base.operations.imp";

    /* renamed from: er */
    private static volatile C4912b f4657er;

    /* renamed from: es */
    private List<C4910a> f4658es = new ArrayList();

    /* renamed from: aI */
    public static C4912b m6644aI() {
        if (f4657er == null) {
            synchronized (C4912b.class) {
                if (f4657er == null) {
                    f4657er = new C4912b();
                }
            }
        }
        return f4657er;
    }

    private C4912b() {
    }

    /* renamed from: a */
    private static C4910a m6643a(String str, C4887c cVar, Bundle bundle, Object obj) {
        if (cVar == null) {
            return null;
        }
        try {
            Object newInstance = Class.forName(m6642B(str)).getConstructor(new Class[]{C4887c.class, Bundle.class, Object.class}).newInstance(new Object[]{cVar, bundle, obj});
            if (newInstance instanceof C4910a) {
                return (C4910a) newInstance;
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    /* renamed from: a */
    public final void mo41052a(String str, C4887c cVar, Bundle bundle, Object obj, C4910a.C4911a aVar) {
        if (cVar != null) {
            try {
                C4910a a = m6643a(str, cVar, bundle, obj);
                if (a != null) {
                    synchronized (this) {
                        this.f4658es.add(a);
                    }
                    a.mo41034a(aVar);
                    C4905i aR = C4905i.m6591aR();
                    aR.putLong(cVar.getKey() + C4905i.f4641fy, System.currentTimeMillis());
                    a.mo41036aC();
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: b */
    public final void mo41054b(String str, C4887c cVar, Bundle bundle, Object obj) {
        if (cVar != null) {
            try {
                m6643a(str, cVar, (Bundle) null, (Object) null);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: a */
    public final void mo41051a(C4910a aVar) {
        synchronized (this) {
            this.f4658es.remove(aVar);
        }
    }

    /* renamed from: aJ */
    public final void mo41053aJ() {
        synchronized (this) {
            Iterator<C4910a> it = this.f4658es.iterator();
            while (it.hasNext()) {
                it.next();
            }
            this.f4658es.clear();
        }
    }

    /* renamed from: B */
    public static String m6642B(String str) {
        return "com.appnext.base.operations.imp." + str;
    }
}
