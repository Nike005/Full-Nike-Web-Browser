package com.startapp.android.publish.ads.video.p024c.p025a;

import android.content.Context;
import android.util.DisplayMetrics;
import com.startapp.android.publish.ads.video.p024c.p025a.p026a.C0991b;
import com.startapp.common.p043a.C1271h;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.startapp.android.publish.ads.video.c.a.c */
/* compiled from: StartAppSDK */
public class C0996c {

    /* renamed from: a */
    protected int f823a;

    /* renamed from: b */
    protected int f824b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public int f825c;

    public C0996c(Context context) {
        m956a(context);
    }

    /* renamed from: a */
    public C0991b mo14344a(List<C0991b> list) {
        if (list == null || mo14347c(list) == 0) {
            return null;
        }
        Collections.sort(list, mo14345a());
        return mo14346b(list);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Comparator<C0991b> mo14345a() {
        return new C0998a();
    }

    /* renamed from: a */
    private void m956a(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.f823a = displayMetrics.widthPixels;
        int i = displayMetrics.heightPixels;
        this.f824b = i;
        this.f825c = this.f823a * i;
        if (!C1271h.m2083a(context).equals("WIFI")) {
            this.f825c = (int) (((float) this.f825c) * 0.75f);
        }
    }

    /* renamed from: com.startapp.android.publish.ads.video.c.a.c$a */
    /* compiled from: StartAppSDK */
    private class C0998a implements Comparator<C0991b> {
        private C0998a() {
        }

        /* renamed from: a */
        public int compare(C0991b bVar, C0991b bVar2) {
            int intValue = bVar.mo14328d().intValue() * bVar.mo14330e().intValue();
            int intValue2 = bVar2.mo14328d().intValue() * bVar2.mo14330e().intValue();
            int abs = Math.abs(intValue - C0996c.this.f825c);
            int abs2 = Math.abs(intValue2 - C0996c.this.f825c);
            if (abs < abs2) {
                return -1;
            }
            return abs > abs2 ? 1 : 0;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public C0991b mo14346b(List<C0991b> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        return list.get(0);
    }

    /* renamed from: a */
    private boolean m957a(C0991b bVar) {
        return bVar.mo14321b().matches("video/.*(?i)(mp4|3gpp|mp2t|webm|matroska)");
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public int mo14347c(List<C0991b> list) {
        Iterator<C0991b> it = list.iterator();
        while (it.hasNext()) {
            C0991b next = it.next();
            if (!next.mo14332f() || !m957a(next)) {
                it.remove();
            }
        }
        return list.size();
    }
}
