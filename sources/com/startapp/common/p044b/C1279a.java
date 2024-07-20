package com.startapp.common.p044b;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.PersistableBundle;
import android.os.SystemClock;
import androidx.core.app.NotificationCompat;
import com.startapp.android.publish.common.metaData.InfoEventService;
import com.startapp.android.publish.common.metaData.PeriodicJobService;
import com.startapp.common.p044b.C1289b;
import com.startapp.common.p044b.p045a.C1284a;
import com.startapp.common.p044b.p045a.C1285b;
import com.startapp.common.p044b.p045a.C1288c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.startapp.common.b.a */
/* compiled from: StartAppSDK */
public class C1279a {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static volatile C1279a f1521a = null;

    /* renamed from: b */
    private static volatile C1288c f1522b = null;

    /* renamed from: c */
    private static volatile Integer f1523c = null;

    /* renamed from: d */
    private static volatile int f1524d = 60000;

    /* renamed from: j */
    private static final ExecutorService f1525j = Executors.newSingleThreadExecutor();

    /* renamed from: k */
    private static final ScheduledExecutorService f1526k = Executors.newScheduledThreadPool(1);
    /* access modifiers changed from: private */

    /* renamed from: e */
    public Context f1527e;

    /* renamed from: f */
    private List<C1284a> f1528f = Collections.synchronizedList(new ArrayList());
    /* access modifiers changed from: private */

    /* renamed from: g */
    public Map<Integer, Integer> f1529g = new ConcurrentHashMap();

    /* renamed from: h */
    private AtomicInteger f1530h = new AtomicInteger(0);

    /* renamed from: i */
    private boolean f1531i;

    /* renamed from: b */
    private static int m2124b(int i) {
        return i & Integer.MAX_VALUE;
    }

    /* renamed from: b */
    private static int m2125b(int i, boolean z) {
        return z ? i | Integer.MIN_VALUE : i;
    }

    private C1279a(Context context) {
        this.f1527e = context.getApplicationContext();
        this.f1531i = m2134d(context);
    }

    /* renamed from: a */
    public static C1279a m2109a(Context context) {
        if (f1521a == null) {
            synchronized (C1279a.class) {
                if (f1521a == null) {
                    if (context.getApplicationContext() != null) {
                        context = context.getApplicationContext();
                    }
                    f1521a = new C1279a(context);
                    try {
                        SharedPreferences sharedPreferences = context.getSharedPreferences("RunnerManager", 0);
                        String str = null;
                        String string = sharedPreferences.getString("RegisteredClassesNames", (String) null);
                        if (string != null) {
                            String[] split = string.split(",");
                            StringBuilder sb = new StringBuilder(string.length());
                            for (String str2 : split) {
                                try {
                                    m2113a(3, "RunnerManager", "create CLS: " + str2);
                                    Class<?> cls = Class.forName(str2);
                                    if (C1284a.class.isAssignableFrom(cls)) {
                                        f1521a.f1528f.add((C1284a) cls.newInstance());
                                        if (sb.length() > 0) {
                                            sb.append(',');
                                        }
                                        sb.append(str2);
                                    }
                                } catch (ClassNotFoundException unused) {
                                } catch (Throwable th) {
                                    m2114a(6, "RunnerManager", "create :" + str2, th);
                                }
                            }
                            if (!sb.toString().equals(string)) {
                                SharedPreferences.Editor edit = sharedPreferences.edit();
                                if (sb.length() > 0) {
                                    str = sb.toString();
                                }
                                edit.putString("RegisteredClassesNames", str).commit();
                            }
                        }
                    } catch (Exception e) {
                        m2114a(6, "RunnerManager", "create", (Throwable) e);
                    }
                }
            }
        }
        return f1521a;
    }

    /* renamed from: a */
    public static void m2117a(C1284a aVar) {
        f1521a.f1528f.add(aVar);
        String name = aVar.getClass().getName();
        SharedPreferences sharedPreferences = f1521a.f1527e.getSharedPreferences("RunnerManager", 0);
        String string = sharedPreferences.getString("RegisteredClassesNames", (String) null);
        if (string == null) {
            sharedPreferences.edit().putString("RegisteredClassesNames", name).commit();
        } else if (!string.contains(name)) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("RegisteredClassesNames", string + "," + name).commit();
        }
    }

    /* renamed from: a */
    public static void m2118a(C1288c cVar) {
        f1522b = cVar;
    }

    /* renamed from: a */
    public static boolean m2122a(C1289b bVar) {
        try {
            int b = m2125b(bVar.mo15486a(), bVar.mo15490e());
            m2113a(3, "RunnerManager", "schedule " + b + StringUtils.SPACE + bVar);
            if (!m2132c()) {
                return m2133c(b, bVar);
            }
            if (m2128b()) {
                return m2119a(b, bVar);
            }
            return m2129b(b, bVar);
        } catch (Exception e) {
            m2114a(6, "RunnerManager", "schedule error", (Throwable) e);
            return false;
        }
    }

    /* renamed from: a */
    private static boolean m2119a(int i, C1289b bVar) {
        JobScheduler c = m2131c(f1521a.f1527e);
        if (c == null) {
            return false;
        }
        PersistableBundle persistableBundle = new PersistableBundle();
        Map<String, String> b = bVar.mo15487b();
        for (String next : b.keySet()) {
            persistableBundle.putString(next, b.get(next));
        }
        persistableBundle.putInt("__RUNNER_RECURRING_ID__", bVar.mo15490e() ? 1 : 0);
        persistableBundle.putLong("__RUNNER_TRIGGER_ID__", bVar.mo15488c());
        JobInfo.Builder builder = new JobInfo.Builder(i, new ComponentName(f1521a.f1527e, PeriodicJobService.class));
        builder.setExtras(persistableBundle);
        if (bVar.mo15490e()) {
            builder.setPeriodic(bVar.mo15488c());
        } else {
            builder.setMinimumLatency(bVar.mo15488c()).setOverrideDeadline(bVar.mo15488c() + ((long) f1524d));
        }
        builder.setRequiredNetworkType(bVar.mo15491f() ? 1 : 0);
        int schedule = c.schedule(builder.build());
        m2113a(3, "RunnerManager", "jobScheduler.schedule " + schedule);
        if (schedule == 1) {
            return true;
        }
        return false;
    }

    /* renamed from: b */
    private static boolean m2129b(int i, C1289b bVar) {
        AlarmManager b = m2126b(f1521a.f1527e);
        if (b == null) {
            return false;
        }
        Intent intent = new Intent(f1521a.f1527e, InfoEventService.class);
        Map<String, String> b2 = bVar.mo15487b();
        for (String next : b2.keySet()) {
            intent.putExtra(next, b2.get(next));
        }
        intent.putExtra("__RUNNER_TASK_ID__", i);
        intent.putExtra("__RUNNER_RECURRING_ID__", bVar.mo15490e());
        intent.putExtra("__RUNNER_TRIGGER_ID__", bVar.mo15488c());
        PendingIntent service = PendingIntent.getService(f1521a.f1527e, i, intent, 134217728);
        b.cancel(service);
        if (bVar.mo15490e()) {
            b.setRepeating(0, System.currentTimeMillis() + bVar.mo15489d(), bVar.mo15488c(), service);
            return true;
        }
        b.set(3, SystemClock.elapsedRealtime() + bVar.mo15488c(), service);
        return true;
    }

    /* renamed from: c */
    private static boolean m2133c(final int i, final C1289b bVar) {
        final int incrementAndGet = f1521a.f1530h.incrementAndGet();
        C12801 r2 = new Runnable() {
            public void run() {
                Integer num = (Integer) C1279a.f1521a.f1529g.get(Integer.valueOf(i));
                if (num != null && num.intValue() == incrementAndGet) {
                    if (!bVar.mo15490e()) {
                        C1279a.f1521a.f1529g.remove(Integer.valueOf(i));
                    }
                    boolean unused = C1279a.m2130b(bVar, (C1285b.C1287b) new C1285b.C1287b() {
                        /* renamed from: a */
                        public void mo15139a(C1285b.C1286a aVar) {
                        }
                    });
                }
            }
        };
        if (bVar.mo15490e()) {
            f1526k.scheduleAtFixedRate(r2, bVar.mo15489d(), bVar.mo15489d(), TimeUnit.MILLISECONDS);
        } else {
            f1526k.schedule(r2, bVar.mo15488c(), TimeUnit.MILLISECONDS);
        }
        f1521a.f1529g.put(Integer.valueOf(i), Integer.valueOf(incrementAndGet));
        return true;
    }

    /* renamed from: a */
    public static void m2115a(int i, boolean z) {
        m2113a(3, "RunnerManager", "cancelAlarm " + i);
        try {
            int b = m2125b(i, z);
            if (!f1521a.f1531i) {
                f1521a.f1529g.remove(Integer.valueOf(b));
            } else if (m2128b()) {
                JobScheduler c = m2131c(f1521a.f1527e);
                if (c != null) {
                    c.cancel(b);
                }
            } else {
                AlarmManager b2 = m2126b(f1521a.f1527e);
                if (b2 != null) {
                    m2116a(f1521a.f1527e, new Intent(f1521a.f1527e, InfoEventService.class), b2, b);
                }
            }
        } catch (Exception e) {
            m2114a(6, "RunnerManager", "cancelAlarm " + i, (Throwable) e);
        }
    }

    /* renamed from: a */
    public static void m2113a(int i, String str, String str2) {
        m2114a(i, str, str2, (Throwable) null);
    }

    /* renamed from: a */
    public static void m2114a(int i, String str, String str2, Throwable th) {
        if (f1522b != null) {
            f1522b.mo14625a(i, str, str2, th);
        }
    }

    /* renamed from: a */
    public static boolean m2121a(Intent intent, C1285b.C1287b bVar) {
        StringBuilder sb = new StringBuilder();
        sb.append("runJob ");
        sb.append(intent != null ? intent : "NULL");
        m2113a(3, "RunnerManager", sb.toString());
        return intent != null && m2130b(m2111a(intent), bVar);
    }

    /* renamed from: a */
    public static boolean m2120a(JobParameters jobParameters, C1285b.C1287b bVar) {
        m2113a(3, "RunnerManager", "runJob " + jobParameters);
        return m2130b(m2110a(jobParameters), bVar);
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static boolean m2130b(final C1289b bVar, final C1285b.C1287b bVar2) {
        m2113a(3, "RunnerManager", "RunnerJob " + bVar.mo15486a() + StringUtils.SPACE + m2124b(bVar.mo15486a()));
        final int b = m2124b(bVar.mo15486a());
        final C1285b a = m2107a(b);
        if (a != null) {
            f1525j.execute(new Runnable() {
                public void run() {
                    a.mo14857a(C1279a.f1521a.f1527e, b, bVar.mo15487b(), new C1285b.C1287b() {
                        /* renamed from: a */
                        public void mo15139a(C1285b.C1286a aVar) {
                            C1279a.m2113a(3, "RunnerManager", "job.execute " + bVar.mo15486a() + StringUtils.SPACE + aVar);
                            if (aVar == C1285b.C1286a.RESCHEDULE && !bVar.mo15490e()) {
                                C1279a.m2122a(bVar);
                            }
                            bVar2.mo15139a(aVar);
                        }
                    });
                }
            });
            return true;
        }
        m2113a(5, "RunnerManager", "runJob: failed to get job for ID " + bVar.mo15486a());
        bVar2.mo15139a(C1285b.C1286a.FAILED);
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:1:0x0009 A[LOOP:0: B:1:0x0009->B:4:0x0019, LOOP_START, PHI: r1 
      PHI: (r1v1 com.startapp.common.b.a.b) = (r1v0 com.startapp.common.b.a.b), (r1v5 com.startapp.common.b.a.b) binds: [B:0:0x0000, B:4:0x0019] A[DONT_GENERATE, DONT_INLINE]] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.startapp.common.p044b.p045a.C1285b m2107a(int r3) {
        /*
            com.startapp.common.b.a r0 = f1521a
            java.util.List<com.startapp.common.b.a.a> r0 = r0.f1528f
            java.util.Iterator r0 = r0.iterator()
            r1 = 0
        L_0x0009:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x001b
            java.lang.Object r1 = r0.next()
            com.startapp.common.b.a.a r1 = (com.startapp.common.p044b.p045a.C1284a) r1
            com.startapp.common.b.a.b r1 = r1.create(r3)
            if (r1 == 0) goto L_0x0009
        L_0x001b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.common.p044b.C1279a.m2107a(int):com.startapp.common.b.a.b");
    }

    /* renamed from: a */
    private static void m2116a(Context context, Intent intent, AlarmManager alarmManager, int i) {
        PendingIntent service = PendingIntent.getService(context, i, intent, 134217728);
        if (PendingIntent.getService(context, 0, intent, 268435456) != null) {
            alarmManager.cancel(service);
            service.cancel();
        }
    }

    /* renamed from: a */
    private static C1289b m2111a(Intent intent) {
        HashMap hashMap;
        int intExtra = intent.getIntExtra("__RUNNER_TASK_ID__", -1);
        boolean booleanExtra = intent.getBooleanExtra("__RUNNER_RECURRING_ID__", false);
        long longExtra = intent.getLongExtra("__RUNNER_TRIGGER_ID__", 0);
        if (intent.getExtras() != null) {
            hashMap = new HashMap(intent.getExtras().keySet().size());
            for (String str : intent.getExtras().keySet()) {
                Object obj = intent.getExtras().get(str);
                if (obj instanceof String) {
                    hashMap.put(str, (String) obj);
                }
            }
        } else {
            hashMap = null;
        }
        return new C1289b.C1291a(intExtra).mo15495a((Map<String, String>) hashMap).mo15496a(booleanExtra).mo15493a(longExtra).mo15497a();
    }

    /* renamed from: a */
    private static C1289b m2110a(JobParameters jobParameters) {
        PersistableBundle extras = jobParameters.getExtras();
        boolean z = true;
        if (extras.getInt("__RUNNER_RECURRING_ID__") != 1) {
            z = false;
        }
        long j = extras.getLong("__RUNNER_TRIGGER_ID__", 0);
        HashMap hashMap = new HashMap(extras.keySet().size());
        for (String str : extras.keySet()) {
            Object obj = extras.get(str);
            if (obj instanceof String) {
                hashMap.put(str, (String) obj);
            }
        }
        return new C1289b.C1291a(jobParameters.getJobId()).mo15495a((Map<String, String>) hashMap).mo15496a(z).mo15493a(j).mo15497a();
    }

    /* renamed from: b */
    private static AlarmManager m2126b(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (alarmManager == null) {
            m2113a(6, "RunnerManager", "failed to get AlarmManager");
        }
        return alarmManager;
    }

    /* renamed from: c */
    private static JobScheduler m2131c(Context context) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        if (jobScheduler == null) {
            m2113a(6, "RunnerManager", "failed to get JobScheduler");
        }
        return jobScheduler;
    }

    /* renamed from: b */
    private static boolean m2128b() {
        int i = Build.VERSION.SDK_INT;
        if (f1523c != null) {
            i = f1523c.intValue();
        }
        return i >= 21;
    }

    /* renamed from: d */
    private boolean m2134d(Context context) {
        try {
            for (ServiceInfo serviceInfo : context.getPackageManager().getPackageInfo(context.getPackageName(), 4).services) {
                if (serviceInfo.name.equals(InfoEventService.class.getName())) {
                    return true;
                }
            }
        } catch (Throwable th) {
            m2114a(6, "RunnerManager", "servicesDefined", th);
        }
        return false;
    }

    /* renamed from: c */
    private static boolean m2132c() {
        return f1521a.f1531i;
    }
}
