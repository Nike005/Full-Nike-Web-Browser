package com.truenet.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.mopub.common.AdType;
import com.startapp.common.p044b.C1279a;
import com.startapp.common.p044b.C1289b;
import com.startapp.common.p044b.p045a.C1284a;
import com.startapp.common.p044b.p045a.C1285b;
import com.startapp.common.p046c.C1295b;
import com.truenet.android.C1758b;
import com.truenet.android.p049a.C1745a;
import com.truenet.android.p049a.C1751g;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.ClassUtils;
import p055a.p056a.C2971j;
import p055a.p056a.p057a.C2903g;
import p055a.p056a.p058b.p059a.C2918a;
import p055a.p056a.p058b.p059a.C2919b;
import p055a.p056a.p058b.p060b.C2928h;
import p055a.p056a.p058b.p060b.C2929i;
import p055a.p056a.p058b.p060b.C2933m;
import p055a.p056a.p058b.p060b.C2935n;
import p055a.p056a.p061c.C2938a;
import p055a.p056a.p062d.C2945c;

/* compiled from: StartAppSDK */
public final class TrueNetSDK implements C1284a {
    private static final String BASE_INIT_URL = new String(new char[]{'h', 't', 't', 'p', 's', ':', '/', '/', 'v', 'a', 'l', 'i', 'd', 'a', 't', 'i', 'o', 'n', '-', 'e', 'n', 'g', 'i', 'n', 'e', ClassUtils.PACKAGE_SEPARATOR_CHAR, 't', 'r', 'u', 'e', 'n', 'e', 't', ClassUtils.PACKAGE_SEPARATOR_CHAR, 'a', 'i'});
    private static final String BASE_RESULT_URL = new String(new char[]{'h', 't', 't', 'p', 's', ':', '/', '/', 'r', 'e', 's', 'u', 'l', 't', '-', 'a', 'p', 'i', ClassUtils.PACKAGE_SEPARATOR_CHAR, 't', 'r', 'u', 'e', 'n', 'e', 't', ClassUtils.PACKAGE_SEPARATOR_CHAR, 'a', 'i'});
    public static final C1733a Companion = new C1733a((C2925e) null);
    private static final String INIT_URL = (BASE_INIT_URL + "/api/initial");
    private static final int JOB_ID = 97764;
    public static final String JOB_TAG = "TruenetCheckLinksJob";
    private static final String PREFS_ENABLED = "PrefsEnabled";
    private static final String PREFS_PUBLISHER_ID = "PrefsPublisherId";
    public static final String PREFS_TAG = "TruenetJobKey";
    private static final String RESULT_URL = (BASE_RESULT_URL + "/api/result");
    /* access modifiers changed from: private */
    public static final URL initUrl = new URL(INIT_URL);
    /* access modifiers changed from: private */
    public static int intervalPosition;
    /* access modifiers changed from: private */
    public static final List<Long> intervals = C2903g.m6119a((T[]) new Long[]{15L, 30L, 60L, 120L, 240L, 480L});
    /* access modifiers changed from: private */
    public static long requestDelay;
    /* access modifiers changed from: private */
    public static final URL resultUrl = new URL(RESULT_URL);
    /* access modifiers changed from: private */
    public static ThreadFactory threadFactory = C1740b.f2657a;
    /* access modifiers changed from: private */
    public static boolean wasInitCalled;

    public static final void enable(Context context, boolean z) {
        Companion.mo16474a(context, z);
    }

    public static final void with(Context context, String str) {
        Companion.mo16473a(context, str);
    }

    /* renamed from: com.truenet.android.TrueNetSDK$c */
    /* compiled from: StartAppSDK */
    static final class C1742c implements C1285b {

        /* renamed from: a */
        final /* synthetic */ TrueNetSDK f2658a;

        C1742c(TrueNetSDK trueNetSDK) {
            this.f2658a = trueNetSDK;
        }

        /* renamed from: com.truenet.android.TrueNetSDK$c$a */
        /* compiled from: StartAppSDK */
        static final class C1743a extends C2929i implements C2918a<C2971j> {
            final /* synthetic */ Context $context$inlined;
            final /* synthetic */ Map $extras$inlined;
            final /* synthetic */ int $jobId$inlined;
            final /* synthetic */ C1285b.C1287b $runnerListener$inlined;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            C1743a(Map map, Context context, int i, C1285b.C1287b bVar) {
                super(0);
                this.$extras$inlined = map;
                this.$context$inlined = context;
                this.$jobId$inlined = i;
                this.$runnerListener$inlined = bVar;
            }

            /* renamed from: a */
            public /* synthetic */ Object mo16475a() {
                mo16488b();
                return C2971j.f4034a;
            }

            /* renamed from: b */
            public final void mo16488b() {
                Log.d("JobManager", "finished " + String.valueOf(this.$jobId$inlined));
                this.$runnerListener$inlined.mo15139a(C1285b.C1286a.SUCCESS);
            }
        }

        /* renamed from: a */
        public final void mo14857a(Context context, int i, Map<String, String> map, C1285b.C1287b bVar) {
            synchronized (this.f2658a) {
                if (C2928h.m6156a((Object) map.get(TrueNetSDK.JOB_TAG), (Object) TrueNetSDK.PREFS_TAG)) {
                    C1733a aVar = TrueNetSDK.Companion;
                    C2928h.m6154a((Object) context, "context");
                    aVar.mo16472a(context, (C2918a<C2971j>) new C1743a(map, context, i, bVar));
                }
                C2971j jVar = C2971j.f4034a;
            }
        }
    }

    /* renamed from: com.truenet.android.TrueNetSDK$a */
    /* compiled from: StartAppSDK */
    public static final class C1733a {

        /* renamed from: com.truenet.android.TrueNetSDK$a$a */
        /* compiled from: StartAppSDK */
        static final class C1734a extends C2929i implements C2918a<C2971j> {
            final /* synthetic */ ConcurrentLinkedQueue $bulkQueue;
            final /* synthetic */ Context $context;
            final /* synthetic */ C2918a $finish;
            final /* synthetic */ LinksData $links;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            C1734a(LinksData linksData, ConcurrentLinkedQueue concurrentLinkedQueue, Context context, C2918a aVar) {
                super(0);
                this.$links = linksData;
                this.$bulkQueue = concurrentLinkedQueue;
                this.$context = context;
                this.$finish = aVar;
            }

            /* renamed from: a */
            public /* synthetic */ Object mo16475a() {
                mo16476b();
                return C2971j.f4034a;
            }

            /* renamed from: b */
            public final void mo16476b() {
                if (this.$links.getBulkResponse()) {
                    String a = C1295b.m2180a(new ValidationResults(C2903g.m6123a(this.$bulkQueue)));
                    URL access$getResultUrl$cp = TrueNetSDK.resultUrl;
                    C2928h.m6154a((Object) a, AdType.STATIC_NATIVE);
                    C1751g.m3859b(access$getResultUrl$cp, a, this.$context);
                }
                TrueNetSDK.Companion.m3814a(this.$context, this.$links.getSleep());
                if (this.$links.getSleep() != 0) {
                    this.$finish.mo16475a();
                }
            }
        }

        /* renamed from: com.truenet.android.TrueNetSDK$a$b */
        /* compiled from: StartAppSDK */
        static final class C1735b extends C2929i implements C2919b<C1758b, Integer, C2971j> {
            final /* synthetic */ ConcurrentLinkedQueue $bulkQueue;
            final /* synthetic */ Context $context;
            final /* synthetic */ LinksData $links;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            C1735b(LinksData linksData, Context context, ConcurrentLinkedQueue concurrentLinkedQueue) {
                super(2);
                this.$links = linksData;
                this.$context = context;
                this.$bulkQueue = concurrentLinkedQueue;
            }

            /* renamed from: a */
            public /* synthetic */ Object mo16477a(Object obj, Object obj2) {
                mo16478a((C1758b) obj, ((Number) obj2).intValue());
                return C2971j.f4034a;
            }

            /* renamed from: a */
            public final void mo16478a(C1758b bVar, int i) {
                String str;
                C2928h.m6157b(bVar, "info");
                Iterable<C1758b.C1760b> d = bVar.mo16525d();
                Collection arrayList = new ArrayList(C2903g.m6121a(d, 10));
                for (C1758b.C1760b bVar2 : d) {
                    String a = bVar2.mo16530a();
                    long b = bVar2.mo16531b();
                    int c = bVar2.mo16532c();
                    List<String> d2 = bVar2.mo16533d();
                    if (d2 == null) {
                        d2 = C2903g.m6118a();
                    }
                    arrayList.add(new RedirectsResult(a, b, c, d2));
                }
                List list = (List) arrayList;
                Link link = this.$links.getValidation().get(i);
                String instanceId = link.getInstanceId();
                int b2 = bVar.mo16523b();
                long c2 = bVar.mo16524c();
                String e = bVar.mo16526e();
                String f = bVar.mo16527f();
                String htmlStorage = (f == null || !C1751g.m3858a(new URL(link.getHtmlStorage()), f, this.$context)) ? "" : link.getHtmlStorage();
                Bitmap a2 = bVar.mo16522a();
                if (a2 == null || !C1745a.m3847a(a2, link.getImageStorage())) {
                    str = "";
                } else {
                    str = link.getImageStorage();
                }
                ValidationResult validationResult = new ValidationResult(instanceId, b2, c2, list, e, htmlStorage, str, TrueNetSDK.Companion.m3825c(this.$context), link.getMetaData());
                if (this.$links.getBulkResponse()) {
                    this.$bulkQueue.add(validationResult);
                    return;
                }
                String a3 = C1295b.m2180a(new ValidationResults(C2903g.m6116a(validationResult)));
                URL access$getResultUrl$cp = TrueNetSDK.resultUrl;
                C2928h.m6154a((Object) a3, AdType.STATIC_NATIVE);
                C1751g.m3859b(access$getResultUrl$cp, a3, this.$context);
            }
        }

        private C1733a() {
        }

        public /* synthetic */ C1733a(C2925e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        /* renamed from: a */
        public final void m3822a(Thread thread, Throwable th) {
            Log.e("TrueNetSDK", "Something went wrong in thread: " + String.valueOf(thread.getId()), th);
        }

        /* renamed from: a */
        public final void mo16473a(Context context, String str) {
            C2928h.m6157b(context, "context");
            C2928h.m6157b(str, "publisherID");
            try {
                SharedPreferences sharedPreferences = context.getSharedPreferences(TrueNetSDK.PREFS_TAG, 0);
                sharedPreferences.edit().putString(TrueNetSDK.PREFS_PUBLISHER_ID, str).apply();
                if (sharedPreferences.getBoolean(TrueNetSDK.PREFS_ENABLED, true) && !TrueNetSDK.wasInitCalled) {
                    m3813a(context);
                    TrueNetSDK.wasInitCalled = true;
                }
            } catch (Throwable th) {
                Thread currentThread = Thread.currentThread();
                C2928h.m6154a((Object) currentThread, "Thread.currentThread()");
                m3822a(currentThread, th);
            }
        }

        /* renamed from: a */
        public final void mo16474a(Context context, boolean z) {
            C2928h.m6157b(context, "context");
            try {
                context.getSharedPreferences(TrueNetSDK.PREFS_TAG, 0).edit().putBoolean(TrueNetSDK.PREFS_ENABLED, z).apply();
                if (z && !TrueNetSDK.wasInitCalled) {
                    m3813a(context);
                    TrueNetSDK.wasInitCalled = true;
                }
            } catch (Throwable th) {
                Thread currentThread = Thread.currentThread();
                C2928h.m6154a((Object) currentThread, "Thread.currentThread()");
                m3822a(currentThread, th);
            }
        }

        /* renamed from: a */
        private final void m3813a(Context context) {
            C1279a.m2109a(context);
            C1279a.m2117a((C1284a) new TrueNetSDK());
            m3819a(this, context, 0, 2, (Object) null);
        }

        /* renamed from: a */
        static /* bridge */ /* synthetic */ void m3819a(C1733a aVar, Context context, long j, int i, Object obj) {
            if ((i & 2) != 0) {
                j = 0;
            }
            aVar.m3814a(context, j);
        }

        /* renamed from: com.truenet.android.TrueNetSDK$a$d */
        /* compiled from: StartAppSDK */
        static final class C1737d implements Runnable {

            /* renamed from: a */
            final /* synthetic */ long f2654a;

            /* renamed from: b */
            final /* synthetic */ Context f2655b;

            C1737d(long j, Context context) {
                this.f2654a = j;
                this.f2655b = context;
            }

            public final void run() {
                final C2933m.C2934a aVar = new C2933m.C2934a();
                aVar.element = (String) null;
                if (this.f2654a != 0 || new C2918a<String>(this) {
                    final /* synthetic */ C1737d this$0;

                    {
                        this.this$0 = r1;
                    }

                    /* renamed from: b */
                    public final String mo16475a() {
                        aVar.element = C1751g.m3859b(TrueNetSDK.initUrl, TrueNetSDK.Companion.m3823b(this.this$0.f2655b), this.this$0.f2655b);
                        return (String) aVar.element;
                    }
                }.mo16475a() == null) {
                    TrueNetSDK.Companion.m3812a(0, this.f2654a);
                    return;
                }
                C1733a aVar2 = TrueNetSDK.Companion;
                Context context = this.f2655b;
                String str = (String) aVar.element;
                if (str == null) {
                    C2928h.m6153a();
                }
                aVar2.m3816a(context, str, (C2918a<C2971j>) C17392.f2656a);
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: a */
        public final void m3814a(Context context, long j) {
            Executors.newSingleThreadExecutor(TrueNetSDK.threadFactory).execute(new C1737d(j, context));
        }

        /* access modifiers changed from: private */
        /* renamed from: a */
        public final void m3812a(int i, long j) {
            TrueNetSDK.requestDelay = j;
            TrueNetSDK.intervalPosition = C2938a.m6171a(i, TrueNetSDK.intervals.size() - 1);
            if (!(j != 0)) {
                j = TimeUnit.MINUTES.toMillis(((Number) TrueNetSDK.intervals.get(TrueNetSDK.intervalPosition)).longValue());
            }
            Log.d("JobManager", "scheduled millis: " + String.valueOf(j));
            C1279a.m2115a((int) TrueNetSDK.JOB_ID, false);
            C1279a.m2122a(new C1289b.C1291a(TrueNetSDK.JOB_ID).mo15493a(j).mo15496a(false).mo15494a(TrueNetSDK.JOB_TAG, TrueNetSDK.PREFS_TAG).mo15498b(true).mo15497a());
        }

        /* renamed from: a */
        public final void mo16472a(Context context, C2918a<C2971j> aVar) {
            C2928h.m6157b(context, "context");
            C2928h.m6157b(aVar, "finish");
            try {
                if (!context.getSharedPreferences(TrueNetSDK.PREFS_TAG, 0).getBoolean(TrueNetSDK.PREFS_ENABLED, true)) {
                    C1279a.m2115a((int) TrueNetSDK.JOB_ID, false);
                    aVar.mo16475a();
                    return;
                }
                Executors.newSingleThreadExecutor(TrueNetSDK.threadFactory).execute(new C1736c(context, aVar));
            } catch (Throwable th) {
                Thread currentThread = Thread.currentThread();
                C2928h.m6154a((Object) currentThread, "Thread.currentThread()");
                m3822a(currentThread, th);
            }
        }

        /* renamed from: com.truenet.android.TrueNetSDK$a$c */
        /* compiled from: StartAppSDK */
        static final class C1736c implements Runnable {

            /* renamed from: a */
            final /* synthetic */ Context f2652a;

            /* renamed from: b */
            final /* synthetic */ C2918a f2653b;

            C1736c(Context context, C2918a aVar) {
                this.f2652a = context;
                this.f2653b = aVar;
            }

            public final void run() {
                boolean z = TrueNetSDK.requestDelay != 0;
                Log.d("JobManager", "sending initial request");
                String b = C1751g.m3859b(TrueNetSDK.initUrl, TrueNetSDK.Companion.m3823b(this.f2652a), this.f2652a);
                if (b != null) {
                    TrueNetSDK.Companion.m3816a(this.f2652a, b, (C2918a<C2971j>) this.f2653b);
                    return;
                }
                TrueNetSDK.Companion.m3812a(z ? TrueNetSDK.intervalPosition : TrueNetSDK.intervalPosition + 1, 0);
                this.f2653b.mo16475a();
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: b */
        public final String m3823b(Context context) {
            DeviceInfo a = new C1744a(context, (TelephonyManager) null, 2, (C2925e) null).mo16517a();
            a.setPublisherId(m3825c(context));
            String a2 = C1295b.m2180a(a);
            C2928h.m6154a((Object) a2, "JSONParser.toJson(info)");
            return a2;
        }

        /* access modifiers changed from: private */
        /* renamed from: c */
        public final String m3825c(Context context) {
            String string = context.getSharedPreferences(TrueNetSDK.PREFS_TAG, 0).getString(TrueNetSDK.PREFS_PUBLISHER_ID, "Undefined");
            C2928h.m6154a((Object) string, "prefs.getString(PREFS_PUBLISHER_ID, \"Undefined\")");
            return string;
        }

        /* access modifiers changed from: private */
        /* renamed from: a */
        public final void m3816a(Context context, String str, C2918a<C2971j> aVar) {
            TrueNetSDK.intervalPosition = 0;
            TrueNetSDK.requestDelay = 0;
            LinksData linksData = (LinksData) C1295b.m2179a(str, LinksData.class);
            if (linksData.getValidation().size() != 0) {
                C2928h.m6154a((Object) linksData, "response");
                m3815a(context, linksData, aVar);
                return;
            }
            m3814a(context, linksData.getSleep());
            if (linksData.getSleep() != 0) {
                aVar.mo16475a();
            }
        }

        /* renamed from: a */
        private final void m3815a(Context context, LinksData linksData, C2918a<C2971j> aVar) {
            Iterable<Link> validation = linksData.getValidation();
            Collection arrayList = new ArrayList(C2903g.m6121a(validation, 10));
            for (Link validationUrl : validation) {
                arrayList.add(validationUrl.getValidationUrl());
            }
            C1767c cVar = new C1767c(context, (List) arrayList, TrueNetSDK.threadFactory, linksData.getMaxRedirectTime(), linksData.getNumOfRedirect(), linksData.getValidateParallel());
            ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
            cVar.mo16547a((C2918a<C2971j>) new C1734a(linksData, concurrentLinkedQueue, context, aVar));
            cVar.mo16548a((C2919b<? super C1758b, ? super Integer, C2971j>) new C1735b(linksData, context, concurrentLinkedQueue));
        }
    }

    /* renamed from: com.truenet.android.TrueNetSDK$b */
    /* compiled from: StartAppSDK */
    static final class C1740b implements ThreadFactory {

        /* renamed from: a */
        public static final C1740b f2657a = new C1740b();

        C1740b() {
        }

        public final Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setUncaughtExceptionHandler(new C1771d(new C2919b<Thread, Throwable, C2971j>(TrueNetSDK.Companion) {
                /* renamed from: a */
                public final C2945c mo16484a() {
                    return C2935n.m6162a(C1733a.class);
                }

                /* renamed from: b */
                public final String mo16486b() {
                    return "uncaughtExceptionHandler";
                }

                /* renamed from: c */
                public final String mo16487c() {
                    return "uncaughtExceptionHandler(Ljava/lang/Thread;Ljava/lang/Throwable;)V";
                }

                /* renamed from: a */
                public /* bridge */ /* synthetic */ Object mo16477a(Object obj, Object obj2) {
                    mo16485a((Thread) obj, (Throwable) obj2);
                    return C2971j.f4034a;
                }

                /* renamed from: a */
                public final void mo16485a(Thread thread, Throwable th) {
                    C2928h.m6157b(thread, "p1");
                    C2928h.m6157b(th, "p2");
                    ((C1733a) this.receiver).m3822a(thread, th);
                }
            }));
            return thread;
        }
    }

    public C1285b create(int i) {
        if (i != JOB_ID) {
            return null;
        }
        Log.d("JobManager", "addJobCreator");
        return new C1742c(this);
    }
}
