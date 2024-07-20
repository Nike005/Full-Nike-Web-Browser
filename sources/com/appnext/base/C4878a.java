package com.appnext.base;

/* renamed from: com.appnext.base.a */
public final class C4878a {

    /* renamed from: ds */
    private int f4554ds;

    /* renamed from: com.appnext.base.a$a */
    public enum C4879a {
        ;
        
        public static final int Internal$1d8b5b4a = 1;
        public static final int NoInternet$1d8b5b4a = 2;
        public static final int NoPermission$1d8b5b4a = 3;

        static {
            $VALUES$47a19cef = new int[]{1, 2, 3};
        }

        /* renamed from: W */
        public static int[] m6471W() {
            return (int[]) $VALUES$47a19cef.clone();
        }
    }

    public C4878a(int i) {
        this.f4554ds = i;
    }

    /* renamed from: V */
    public final int mo40939V() {
        return this.f4554ds;
    }
}
