package com.startapp.common;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: com.startapp.common.c */
/* compiled from: StartAppSDK */
public class C1292c {

    /* renamed from: b */
    private static C1292c f1552b;

    /* renamed from: a */
    protected String f1553a = "e106";

    /* renamed from: c */
    private Context f1554c;

    /* renamed from: d */
    private PhoneStateListener f1555d = m2160c();

    private C1292c(Context context) {
        this.f1554c = context.getApplicationContext();
    }

    /* renamed from: a */
    public void mo15499a(Context context) {
        m2159a(context, 256);
    }

    /* renamed from: b */
    public void mo15501b(Context context) {
        m2159a(context, 0);
    }

    /* renamed from: a */
    private void m2159a(Context context, int i) {
        try {
            ((TelephonyManager) context.getSystemService("phone")).listen(this.f1555d, i);
        } catch (Exception unused) {
        }
    }

    /* renamed from: c */
    public static void m2161c(Context context) {
        if (f1552b == null) {
            f1552b = new C1292c(context);
            m2158a().mo15499a(context);
        }
    }

    /* renamed from: a */
    public static C1292c m2158a() {
        return f1552b;
    }

    /* renamed from: b */
    public String mo15500b() {
        return this.f1553a;
    }

    /* renamed from: c */
    private PhoneStateListener m2160c() {
        try {
            return new PhoneStateListener() {
                public void onSignalStrengthsChanged(SignalStrength signalStrength) {
                    try {
                        Method method = SignalStrength.class.getMethod("getLevel", (Class[]) null);
                        C1292c.this.f1553a = Integer.toString(((Integer) method.invoke(signalStrength, (Object[]) null)).intValue());
                    } catch (NoSuchMethodException unused) {
                        C1292c.this.f1553a = "e104";
                    } catch (IllegalAccessException unused2) {
                        C1292c.this.f1553a = "e105";
                    } catch (IllegalArgumentException unused3) {
                        C1292c.this.f1553a = "e105";
                    } catch (InvocationTargetException unused4) {
                        C1292c.this.f1553a = "e105";
                    }
                }
            };
        } catch (Exception unused) {
            return null;
        }
    }
}
