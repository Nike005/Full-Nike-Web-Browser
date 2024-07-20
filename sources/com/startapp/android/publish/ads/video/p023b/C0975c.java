package com.startapp.android.publish.ads.video.p023b;

/* renamed from: com.startapp.android.publish.ads.video.b.c */
/* compiled from: StartAppSDK */
public interface C0975c {

    /* renamed from: com.startapp.android.publish.ads.video.b.c$a */
    /* compiled from: StartAppSDK */
    public interface C0976a {
    }

    /* renamed from: com.startapp.android.publish.ads.video.b.c$b */
    /* compiled from: StartAppSDK */
    public interface C0977b {
    }

    /* renamed from: com.startapp.android.publish.ads.video.b.c$c */
    /* compiled from: StartAppSDK */
    public interface C0978c {
        /* renamed from: a */
        void mo14284a(int i);
    }

    /* renamed from: com.startapp.android.publish.ads.video.b.c$d */
    /* compiled from: StartAppSDK */
    public interface C0979d {
        /* renamed from: a */
        void mo14285a();
    }

    /* renamed from: com.startapp.android.publish.ads.video.b.c$e */
    /* compiled from: StartAppSDK */
    public interface C0980e {
        /* renamed from: a */
        boolean mo14286a(C0982g gVar);
    }

    /* renamed from: com.startapp.android.publish.ads.video.b.c$f */
    /* compiled from: StartAppSDK */
    public interface C0981f {
        /* renamed from: a */
        void mo14287a();
    }

    /* renamed from: com.startapp.android.publish.ads.video.b.c$h */
    /* compiled from: StartAppSDK */
    public enum C0983h {
        UNKNOWN,
        SERVER_DIED,
        BUFFERING_TIMEOUT,
        PLAYER_CREATION
    }

    /* renamed from: a */
    void mo14271a();

    /* renamed from: a */
    void mo14272a(int i);

    /* renamed from: a */
    void mo14264a(C0976a aVar);

    /* renamed from: a */
    void mo14265a(C0977b bVar);

    /* renamed from: a */
    void mo14266a(C0978c cVar);

    /* renamed from: a */
    void mo14267a(C0979d dVar);

    /* renamed from: a */
    void mo14268a(C0980e eVar);

    /* renamed from: a */
    void mo14269a(C0981f fVar);

    /* renamed from: a */
    void mo14270a(String str);

    /* renamed from: a */
    void mo14273a(boolean z);

    /* renamed from: b */
    void mo14274b();

    /* renamed from: c */
    void mo14275c();

    /* renamed from: d */
    int mo14276d();

    /* renamed from: e */
    int mo14277e();

    /* renamed from: f */
    boolean mo14278f();

    /* renamed from: g */
    void mo14279g();

    /* renamed from: com.startapp.android.publish.ads.video.b.c$g */
    /* compiled from: StartAppSDK */
    public static class C0982g {

        /* renamed from: a */
        private C0983h f710a;

        /* renamed from: b */
        private String f711b;

        /* renamed from: c */
        private int f712c;

        public C0982g(C0983h hVar, String str, int i) {
            this.f710a = hVar;
            this.f711b = str;
            this.f712c = i;
        }

        /* renamed from: a */
        public C0983h mo14288a() {
            return this.f710a;
        }

        /* renamed from: b */
        public String mo14289b() {
            return this.f711b;
        }

        /* renamed from: c */
        public int mo14290c() {
            return this.f712c;
        }
    }
}
