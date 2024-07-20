package com.tappx.p048a;

import android.content.Context;
import com.mopub.common.GpsHelper;
import java.lang.reflect.Method;

/* renamed from: com.tappx.a.y0 */
public class C1697y0 {

    /* renamed from: a */
    private final Context f2550a;

    /* renamed from: b */
    private final C1357d<C1698a> f2551b;

    /* renamed from: com.tappx.a.y0$a */
    public static class C1698a {

        /* renamed from: a */
        private final String f2552a;

        /* renamed from: b */
        private final boolean f2553b;

        public C1698a(String str, boolean z) {
            this.f2552a = str;
            this.f2553b = z;
        }

        /* renamed from: a */
        public String mo16271a() {
            return this.f2552a;
        }

        /* renamed from: b */
        public boolean mo16272b() {
            return this.f2553b;
        }
    }

    public C1697y0(Context context) {
        this(context, new C1466j(C1529n.f2088c));
    }

    /* renamed from: b */
    private C1698a m3682b() {
        Object invoke = m3681a(Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient"), "getAdvertisingIdInfo", new Class[]{Context.class}).invoke((Object) null, new Object[]{this.f2550a});
        return new C1698a(m3680a(invoke), m3683b(invoke));
    }

    /* renamed from: a */
    public C1698a mo16270a() {
        C1698a a = this.f2551b.mo15646a();
        if (a != null) {
            return a;
        }
        try {
            C1698a b = m3682b();
            this.f2551b.mo15647a(b);
            return b;
        } catch (Exception unused) {
            return null;
        }
    }

    C1697y0(Context context, C1357d<C1698a> dVar) {
        this.f2550a = context;
        this.f2551b = dVar;
    }

    /* renamed from: a */
    private String m3680a(Object obj) {
        try {
            return (String) m3681a(obj.getClass(), "getId", (Class<?>[]) null).invoke(obj, new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: b */
    private boolean m3683b(Object obj) {
        try {
            Boolean bool = (Boolean) m3681a(obj.getClass(), GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY, (Class<?>[]) null).invoke(obj, new Object[0]);
            if (bool == null) {
                return false;
            }
            return bool.booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: a */
    private static Method m3681a(Class<?> cls, String str, Class<?>[] clsArr) {
        Class<? super Object> cls2;
        while (cls2 != null) {
            try {
                return cls2.getDeclaredMethod(str, clsArr);
            } catch (NoSuchMethodException unused) {
                Class<? super Object> superclass = cls2.getSuperclass();
                cls2 = cls;
                cls2 = superclass;
            }
        }
        throw new NoSuchMethodException();
    }
}
