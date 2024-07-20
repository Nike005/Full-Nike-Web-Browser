package com.p088b.p089a.p090a.p091a;

import android.content.Context;
import com.p088b.p089a.p090a.p091a.p095c.C5121b;
import com.p088b.p089a.p090a.p091a.p095c.C5124c;
import com.p088b.p089a.p090a.p091a.p095c.C5127e;
import com.p088b.p089a.p090a.p091a.p097e.C5135b;
import com.p088b.p089a.p090a.p091a.p097e.C5139e;

/* renamed from: com.b.a.a.a.c */
public class C5119c {

    /* renamed from: a */
    private boolean f4965a;

    /* renamed from: b */
    private void m7042b(String str, Context context) {
        m7043c(str);
        C5139e.m7136a((Object) context, "Application Context cannot be null");
    }

    /* renamed from: c */
    private void m7043c(String str) {
        C5139e.m7136a((Object) str, "Version cannot be null");
        if (!str.matches("[0-9]+\\.[0-9]+\\.[0-9]+.*")) {
            throw new IllegalArgumentException("Invalid version format : " + str);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public String mo41859a() {
        return "1.2.0-Startapp";
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo41860a(boolean z) {
        this.f4965a = z;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo41861a(String str) {
        return mo41863b(mo41859a()) == mo41863b(str);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo41862a(String str, Context context) {
        m7042b(str, context);
        if (!mo41861a(str)) {
            return false;
        }
        if (!mo41864b()) {
            mo41860a(true);
            C5127e.m7086a().mo41894a(context);
            C5121b.m7057a().mo41871a(context);
            C5135b.m7117a(context);
            C5124c.m7069a().mo41878a(context);
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public int mo41863b(String str) {
        m7043c(str);
        return Integer.parseInt(str.split("\\.", 2)[0]);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public boolean mo41864b() {
        return this.f4965a;
    }
}
