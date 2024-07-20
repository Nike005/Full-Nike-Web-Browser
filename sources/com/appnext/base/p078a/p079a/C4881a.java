package com.appnext.base.p078a.p079a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.appnext.base.p078a.C4884b;
import com.appnext.base.p082b.C4901e;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.appnext.base.a.a.a */
public class C4881a {

    /* renamed from: dB */
    private static C4881a f4559dB;

    /* renamed from: dC */
    private static C4884b f4560dC;

    /* renamed from: dA */
    private AtomicInteger f4561dA = new AtomicInteger(0);

    /* renamed from: dD */
    private SQLiteDatabase f4562dD;

    /* renamed from: com.appnext.base.a.a.a$a */
    public enum C4883a {
        ;
        
        public static final int DatabaseOrDiskFull$53629b42 = 2;
        public static final int Global$53629b42 = 1;

        static {
            $VALUES$40a167d9 = new int[]{1, 2};
        }

        /* renamed from: af */
        public static int[] m6481af() {
            return (int[]) $VALUES$40a167d9.clone();
        }
    }

    private C4881a(Context context) {
        f4560dC = C4884b.m6482c(context);
    }

    /* renamed from: ac */
    public static C4881a m6478ac() {
        if (f4559dB == null) {
            synchronized (C4881a.class) {
                if (f4559dB == null) {
                    f4559dB = new C4881a(C4901e.getContext().getApplicationContext());
                }
            }
        }
        return f4559dB;
    }

    /* renamed from: ad */
    public final SQLiteDatabase mo40943ad() {
        if (this.f4561dA.incrementAndGet() == 1) {
            this.f4562dD = f4560dC.getWritableDatabase();
        }
        return this.f4562dD;
    }

    /* renamed from: ae */
    public final void mo40944ae() {
        if (this.f4561dA.decrementAndGet() == 0) {
            this.f4562dD.close();
        }
    }

    /* renamed from: com.appnext.base.a.a.a$1 */
    static /* synthetic */ class C48821 {

        /* renamed from: dE */
        static final /* synthetic */ int[] f4563dE;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x000f */
        static {
            /*
                int[] r0 = com.appnext.base.p078a.p079a.C4881a.C4883a.m6481af()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f4563dE = r0
                r1 = 1
                int r2 = com.appnext.base.p078a.p079a.C4881a.C4883a.DatabaseOrDiskFull$53629b42     // Catch:{ NoSuchFieldError -> 0x000f }
                int r2 = r2 - r1
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x000f }
            L_0x000f:
                int[] r0 = f4563dE     // Catch:{ NoSuchFieldError -> 0x0017 }
                int r2 = com.appnext.base.p078a.p079a.C4881a.C4883a.Global$53629b42     // Catch:{ NoSuchFieldError -> 0x0017 }
                int r2 = r2 - r1
                r1 = 2
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0017 }
            L_0x0017:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appnext.base.p078a.p079a.C4881a.C48821.<clinit>():void");
        }
    }

    /* renamed from: a */
    public static void m6477a(int i, Throwable th) {
        int[] iArr = C48821.f4563dE;
    }
}
