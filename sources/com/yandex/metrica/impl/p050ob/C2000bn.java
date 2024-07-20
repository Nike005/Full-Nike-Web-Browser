package com.yandex.metrica.impl.p050ob;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.appnext.base.p082b.C4899d;
import com.google.android.exoplayer2.upstream.cache.CacheDataSink;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.C1807a;
import com.yandex.metrica.impl.C1894bi;
import com.yandex.metrica.impl.C1897bk;
import com.yandex.metrica.impl.C1915h;
import com.yandex.metrica.impl.C1923k;
import com.yandex.metrica.impl.C2208p;
import com.yandex.metrica.impl.p050ob.C1972bm;
import com.yandex.metrica.impl.utils.C2232m;
import java.io.Closeable;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* renamed from: com.yandex.metrica.impl.ob.bn */
public class C2000bn implements Closeable {

    /* renamed from: a */
    private final ReentrantReadWriteLock f3265a;

    /* renamed from: b */
    private final Lock f3266b;

    /* renamed from: c */
    private final Lock f3267c = this.f3265a.writeLock();

    /* renamed from: d */
    private final C2002bo f3268d;

    /* renamed from: e */
    private final C2001a f3269e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public final Object f3270f = new Object();
    /* access modifiers changed from: private */

    /* renamed from: g */
    public final List<ContentValues> f3271g = new ArrayList(3);
    /* access modifiers changed from: private */

    /* renamed from: h */
    public ContentValues f3272h;

    /* renamed from: i */
    private final Context f3273i;

    /* renamed from: j */
    private final C2200u f3274j;

    /* renamed from: k */
    private final AtomicLong f3275k = new AtomicLong();

    public C2000bn(C2200u uVar, C2002bo boVar) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.f3265a = reentrantReadWriteLock;
        this.f3266b = reentrantReadWriteLock.readLock();
        this.f3268d = boVar;
        this.f3273i = uVar.mo17866m();
        this.f3274j = uVar;
        this.f3275k.set(m4999b());
        C2001a aVar = new C2001a();
        this.f3269e = aVar;
        aVar.setName("DatabaseWorker [" + uVar.mo17865l() + "]");
        this.f3269e.start();
        m5004c();
    }

    /* renamed from: a */
    public void mo17272a(C2200u uVar) {
        this.f3269e.mo17278a(uVar);
    }

    /* renamed from: a */
    public void mo17269a(long j, C1971bl blVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Long.valueOf(j));
        contentValues.put("start_time", Long.valueOf(System.currentTimeMillis() / 1000));
        contentValues.put("server_time_offset", Long.valueOf(C2232m.m5973a()));
        contentValues.put("type", Integer.valueOf(blVar.mo17260a()));
        new C1923k(this.f3273i).mo17146a(this.f3274j).mo17145a(contentValues).mo17147a();
        mo17270a(contentValues);
    }

    /* renamed from: a */
    public void mo17271a(C1915h hVar, C1969bj bjVar, C1807a.C1808a aVar) {
        ContentValues contentValues = new ContentValues(20);
        contentValues.put("number", Long.valueOf(bjVar.mo17256c()));
        contentValues.put(C4899d.f4625fl, Long.valueOf(bjVar.mo17258d()));
        contentValues.put("session_id", Long.valueOf(bjVar.mo17251a()));
        contentValues.put("session_type", Integer.valueOf(bjVar.mo17255b().mo17260a()));
        new C1923k(this.f3273i).mo17146a(this.f3274j).mo17145a(contentValues).mo17149a(hVar, aVar);
        mo17274b(contentValues);
    }

    /* renamed from: a */
    private static long m4990a(Cursor cursor) {
        try {
            if (cursor.moveToFirst()) {
                return cursor.getLong(0);
            }
            C1897bk.m4643a(cursor);
            return 0;
        } finally {
            C1897bk.m4643a(cursor);
        }
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: b */
    private long m4999b() {
        this.f3266b.lock();
        try {
            long a = m4990a(this.f3268d.getReadableDatabase().rawQuery("SELECT count() FROM reports", (String[]) null));
            this.f3266b.unlock();
            return a;
        } catch (Exception unused) {
            this.f3266b.unlock();
            return 0;
        } catch (Throwable th) {
            this.f3266b.unlock();
            throw th;
        }
    }

    /* renamed from: a */
    public void mo17270a(ContentValues contentValues) {
        synchronized (this.f3270f) {
            this.f3272h = contentValues;
        }
        synchronized (this.f3269e) {
            this.f3269e.notifyAll();
        }
    }

    /* renamed from: b */
    public void mo17274b(ContentValues contentValues) {
        synchronized (this.f3270f) {
            this.f3271g.add(contentValues);
        }
        synchronized (this.f3269e) {
            this.f3269e.notifyAll();
        }
    }

    /* renamed from: a */
    public int mo17263a(long[] jArr) {
        Cursor cursor;
        Cursor cursor2;
        this.f3267c.lock();
        int i = 0;
        try {
            if (C1972bm.f3252a.booleanValue()) {
                this.f3266b.lock();
                Cursor cursor3 = null;
                try {
                    SQLiteDatabase readableDatabase = this.f3268d.getReadableDatabase();
                    Cursor rawQuery = readableDatabase.rawQuery(" SELECT DISTINCT id From sessions order by id asc ", new String[0]);
                    try {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("All sessions in db: ");
                        while (rawQuery.moveToNext()) {
                            stringBuffer.append(rawQuery.getString(0));
                            stringBuffer.append(", ");
                        }
                        Cursor rawQuery2 = readableDatabase.rawQuery(" SELECT DISTINCT session_id From reports order by session_id asc ", new String[0]);
                        StringBuffer stringBuffer2 = new StringBuffer();
                        stringBuffer2.append("All sessions in reports db: ");
                        while (rawQuery2.moveToNext()) {
                            stringBuffer2.append(rawQuery2.getString(0));
                            stringBuffer2.append(", ");
                        }
                        this.f3266b.unlock();
                        C1897bk.m4643a(rawQuery);
                        C1897bk.m4643a(rawQuery2);
                    } catch (Exception unused) {
                        cursor = null;
                        cursor3 = rawQuery;
                        this.f3266b.unlock();
                        C1897bk.m4643a(cursor3);
                        C1897bk.m4643a(cursor);
                        i = this.f3268d.getWritableDatabase().delete("sessions", C1972bm.C1999z.f3264c, C1897bk.m4654a(jArr));
                        this.f3267c.unlock();
                        return i;
                    } catch (Throwable th) {
                        th = th;
                        cursor2 = null;
                        cursor3 = rawQuery;
                        this.f3266b.unlock();
                        C1897bk.m4643a(cursor3);
                        C1897bk.m4643a(cursor2);
                        throw th;
                    }
                } catch (Exception unused2) {
                    cursor = null;
                    this.f3266b.unlock();
                    C1897bk.m4643a(cursor3);
                    C1897bk.m4643a(cursor);
                    i = this.f3268d.getWritableDatabase().delete("sessions", C1972bm.C1999z.f3264c, C1897bk.m4654a(jArr));
                    this.f3267c.unlock();
                    return i;
                } catch (Throwable th2) {
                    th = th2;
                    cursor2 = null;
                    this.f3266b.unlock();
                    C1897bk.m4643a(cursor3);
                    C1897bk.m4643a(cursor2);
                    throw th;
                }
            }
            i = this.f3268d.getWritableDatabase().delete("sessions", C1972bm.C1999z.f3264c, C1897bk.m4654a(jArr));
        } catch (Exception unused3) {
        } catch (Throwable th3) {
            this.f3267c.unlock();
            throw th3;
        }
        this.f3267c.unlock();
        return i;
    }

    /* renamed from: c */
    private void m5004c() {
        try {
            this.f3267c.lock();
            SQLiteDatabase writableDatabase = this.f3268d.getWritableDatabase();
            if (new File(writableDatabase.getPath()).length() > CacheDataSink.DEFAULT_FRAGMENT_SIZE) {
                this.f3275k.addAndGet((long) (-mo17262a(writableDatabase)));
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            this.f3267c.unlock();
            throw th;
        }
        this.f3267c.unlock();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public int mo17262a(SQLiteDatabase sQLiteDatabase) {
        try {
            Integer[] numArr = new Integer[C2208p.f3839a.size()];
            Iterator it = C2208p.f3839a.iterator();
            int i = 0;
            while (it.hasNext()) {
                numArr[i] = Integer.valueOf(((C2208p.C2209a) it.next()).mo17884a());
                i++;
            }
            return sQLiteDatabase.delete("reports", String.format("%1$s NOT IN (%2$s) AND (%3$s IN (SELECT %3$s FROM %4$s ORDER BY %5$s, %6$s LIMIT (SELECT count() FROM %4$s) / %7$s ) OR %5$s < %8$s )", new Object[]{"type", TextUtils.join(",", numArr), "id", "reports", "session_id", "number", 10, Long.valueOf((System.currentTimeMillis() / 1000) - TimeUnit.DAYS.toSeconds(14))}), (String[]) null);
        } catch (Throwable th) {
            YandexMetrica.getReporter(this.f3273i, "20799a27-fa80-4b36-b2db-0f8141f24180").reportError("deleteExcessiveReports exception", th);
            return 0;
        }
    }

    /* renamed from: a */
    public void mo17268a(long j, int i, int i2) throws SQLiteException {
        ArrayList arrayList;
        Cursor cursor;
        if (i2 > 0) {
            this.f3267c.lock();
            Cursor cursor2 = null;
            try {
                SQLiteDatabase writableDatabase = this.f3268d.getWritableDatabase();
                String format = String.format(Locale.US, "%1$s = %2$s AND %3$s = %4$s AND %5$s <= (SELECT %5$s FROM %6$s WHERE %1$s = %2$s AND %3$s = %4$s ORDER BY %5$s ASC LIMIT %7$s, 1)", new Object[]{"session_id", Long.toString(j), "session_type", Integer.toString(i), "id", "reports", Integer.toString(i2 - 1)});
                if (this.f3274j.mo17869p().mo17906b()) {
                    cursor = m4991a(format);
                    if (cursor != null) {
                        try {
                            if (cursor.getCount() > 0) {
                                arrayList = new ArrayList(cursor.getCount());
                                while (cursor.moveToNext()) {
                                    ContentValues contentValues = new ContentValues();
                                    DatabaseUtils.cursorRowToContentValues(cursor, contentValues);
                                    arrayList.add(contentValues);
                                }
                            }
                        } catch (Exception unused) {
                            cursor2 = cursor;
                            C1897bk.m4643a(cursor2);
                            this.f3267c.unlock();
                        } catch (Throwable th) {
                            th = th;
                            cursor2 = cursor;
                            C1897bk.m4643a(cursor2);
                            this.f3267c.unlock();
                            throw th;
                        }
                    }
                    arrayList = null;
                } else {
                    cursor = null;
                    arrayList = null;
                }
                int delete = writableDatabase.delete("reports", format, (String[]) null);
                if (arrayList != null) {
                    m4996a((List<ContentValues>) arrayList, "Event removed from db");
                }
                this.f3275k.addAndGet((long) (-delete));
                C1897bk.m4643a(cursor);
            } catch (Exception unused2) {
                C1897bk.m4643a(cursor2);
                this.f3267c.unlock();
            } catch (Throwable th2) {
                th = th2;
                C1897bk.m4643a(cursor2);
                this.f3267c.unlock();
                throw th;
            }
            this.f3267c.unlock();
        }
    }

    /* renamed from: a */
    private Cursor m4991a(String str) {
        try {
            return this.f3268d.getReadableDatabase().query("reports", (String[]) null, str, (String[]) null, (String) null, (String) null, (String) null, (String) null);
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    public Cursor mo17266a(Map<String, String> map) {
        this.f3266b.lock();
        try {
            Cursor query = this.f3268d.getReadableDatabase().query("sessions", (String[]) null, m4992a("id >= ?", map), m4998a(new String[]{Long.toString(0)}, map), (String) null, (String) null, "id ASC", (String) null);
            this.f3266b.unlock();
            return query;
        } catch (Exception unused) {
            this.f3266b.unlock();
            return null;
        } catch (Throwable th) {
            this.f3266b.unlock();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    public Cursor mo17265a(long j, Map<String, String> map) {
        this.f3266b.lock();
        try {
            Cursor query = this.f3268d.getReadableDatabase().query("sessions", (String[]) null, m4992a("id = ?", map), m4998a(new String[]{Long.toString(j)}, map), (String) null, (String) null, (String) null, (String) null);
            this.f3266b.unlock();
            return query;
        } catch (Exception unused) {
            this.f3266b.unlock();
            return null;
        } catch (Throwable th) {
            this.f3266b.unlock();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: b */
    public Cursor mo17273b(long j, C1971bl blVar) throws SQLiteException {
        this.f3266b.lock();
        try {
            Cursor query = this.f3268d.getReadableDatabase().query("reports", (String[]) null, "session_id = ? AND session_type = ?", new String[]{Long.toString(j), Integer.toString(blVar.mo17260a())}, (String) null, (String) null, "number ASC", (String) null);
            this.f3266b.unlock();
            return query;
        } catch (Exception unused) {
            this.f3266b.unlock();
            return null;
        } catch (Throwable th) {
            this.f3266b.unlock();
            throw th;
        }
    }

    /* renamed from: a */
    private void m4996a(List<ContentValues> list, String str) {
        for (int i = 0; i < list.size(); i++) {
            m4993a(list.get(i), str);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r0 = new java.util.ArrayList();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0049, code lost:
        com.yandex.metrica.impl.C1897bk.m4643a((android.database.Cursor) null);
        r7.f3266b.unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0052, code lost:
        throw r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0039, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x003b */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<android.content.ContentValues> mo17267a(java.lang.Long r8) {
        /*
            r7 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.concurrent.locks.Lock r1 = r7.f3266b
            r1.lock()
            r1 = 0
            com.yandex.metrica.impl.ob.bo r2 = r7.f3268d     // Catch:{ Exception -> 0x003b }
            android.database.sqlite.SQLiteDatabase r2 = r2.getReadableDatabase()     // Catch:{ Exception -> 0x003b }
            java.lang.String r3 = "SELECT DISTINCT report_request_parameters FROM sessions WHERE id >= 0"
            if (r8 == 0) goto L_0x0023
            java.util.Locale r3 = java.util.Locale.US     // Catch:{ Exception -> 0x003b }
            java.lang.String r4 = "SELECT DISTINCT report_request_parameters FROM sessions WHERE id = %s"
            r5 = 1
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Exception -> 0x003b }
            r6 = 0
            r5[r6] = r8     // Catch:{ Exception -> 0x003b }
            java.lang.String r3 = java.lang.String.format(r3, r4, r5)     // Catch:{ Exception -> 0x003b }
        L_0x0023:
            android.database.Cursor r1 = r2.rawQuery(r3, r1)     // Catch:{ Exception -> 0x003b }
        L_0x0027:
            boolean r8 = r1.moveToNext()     // Catch:{ Exception -> 0x003b }
            if (r8 == 0) goto L_0x0040
            android.content.ContentValues r8 = new android.content.ContentValues     // Catch:{ Exception -> 0x003b }
            r8.<init>()     // Catch:{ Exception -> 0x003b }
            android.database.DatabaseUtils.cursorRowToContentValues(r1, r8)     // Catch:{ Exception -> 0x003b }
            r0.add(r8)     // Catch:{ Exception -> 0x003b }
            goto L_0x0027
        L_0x0039:
            r8 = move-exception
            goto L_0x0049
        L_0x003b:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0039 }
            r0.<init>()     // Catch:{ all -> 0x0039 }
        L_0x0040:
            com.yandex.metrica.impl.C1897bk.m4643a((android.database.Cursor) r1)
            java.util.concurrent.locks.Lock r8 = r7.f3266b
            r8.unlock()
            return r0
        L_0x0049:
            com.yandex.metrica.impl.C1897bk.m4643a((android.database.Cursor) r1)
            java.util.concurrent.locks.Lock r0 = r7.f3266b
            r0.unlock()
            goto L_0x0053
        L_0x0052:
            throw r8
        L_0x0053:
            goto L_0x0052
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.p050ob.C2000bn.mo17267a(java.lang.Long):java.util.List");
    }

    /* renamed from: c */
    public ContentValues mo17275c(long j, C1971bl blVar) {
        ContentValues contentValues = new ContentValues();
        this.f3266b.lock();
        Cursor cursor = null;
        try {
            cursor = this.f3268d.getReadableDatabase().rawQuery(String.format(Locale.US, "SELECT report_request_parameters FROM sessions WHERE id = %s AND type = %s ORDER BY id DESC LIMIT 1", new Object[]{Long.valueOf(j), Integer.valueOf(blVar.mo17260a())}), (String[]) null);
            if (cursor.moveToNext()) {
                ContentValues contentValues2 = new ContentValues();
                DatabaseUtils.cursorRowToContentValues(cursor, contentValues2);
                contentValues = contentValues2;
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            C1897bk.m4643a((Cursor) null);
            this.f3266b.unlock();
            throw th;
        }
        C1897bk.m4643a(cursor);
        this.f3266b.unlock();
        return contentValues;
    }

    /* renamed from: a */
    private static String m4992a(String str, Map<String, String> map) {
        StringBuilder sb = new StringBuilder(str);
        for (String next : map.keySet()) {
            sb.append(sb.length() > 0 ? " AND " : "");
            sb.append(next + " = ? ");
        }
        if (C1894bi.m4622a(sb.toString())) {
            return null;
        }
        return sb.toString();
    }

    /* renamed from: a */
    private static String[] m4998a(String[] strArr, Map<String, String> map) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(strArr));
        for (Map.Entry<String, String> value : map.entrySet()) {
            arrayList.add(value.getValue());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    /* renamed from: b */
    private static String m5002b(ContentValues contentValues, String str) {
        return C1894bi.m4626b(contentValues.getAsString(str), "");
    }

    /* renamed from: a */
    public long mo17264a() {
        this.f3266b.lock();
        try {
            return this.f3275k.get();
        } finally {
            this.f3266b.unlock();
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.bn$a */
    private class C2001a extends Thread {

        /* renamed from: b */
        private final List<ContentValues> f3277b = new ArrayList();

        /* renamed from: c */
        private C2200u f3278c;

        public C2001a() {
        }

        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    synchronized (this) {
                        if (C2000bn.this.m5006d()) {
                            wait();
                        }
                    }
                } catch (Exception unused) {
                    Thread.currentThread().interrupt();
                }
                synchronized (C2000bn.this.f3270f) {
                    this.f3277b.clear();
                    this.f3277b.addAll(C2000bn.this.f3271g);
                    C2000bn.this.f3271g.clear();
                    C2000bn.m4994a(C2000bn.this, C2000bn.this.f3272h);
                    C2000bn.m4995a(C2000bn.this, (List) this.f3277b);
                    ContentValues unused2 = C2000bn.this.f3272h = null;
                }
                mo17279b();
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public synchronized void mo17277a() {
            interrupt();
            this.f3278c = null;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public synchronized void mo17278a(C2200u uVar) {
            this.f3278c = uVar;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public synchronized void mo17279b() {
            if (this.f3278c != null && !this.f3278c.mo17868o()) {
                this.f3278c.mo17845b();
            }
        }
    }

    public void close() {
        this.f3271g.clear();
        this.f3269e.mo17277a();
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public boolean m5006d() {
        boolean z;
        synchronized (this.f3270f) {
            z = this.f3272h == null && this.f3271g.isEmpty();
        }
        return z;
    }

    /* renamed from: a */
    private void m4993a(ContentValues contentValues, String str) {
        int i;
        Integer asInteger = contentValues.getAsInteger("type");
        if (asInteger != null) {
            i = asInteger.intValue();
        } else {
            i = -1;
        }
        if (C2208p.m5876b(i)) {
            StringBuilder sb = new StringBuilder(str);
            sb.append(": ");
            sb.append(m5002b(contentValues, "name"));
            String b = m5002b(contentValues, "value");
            if (C2208p.m5880c(contentValues.getAsInteger("type").intValue()) && !TextUtils.isEmpty(b)) {
                sb.append(" with value ");
                sb.append(b);
            }
            this.f3274j.mo17869p().mo17902a(sb.toString());
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m4994a(C2000bn bnVar, ContentValues contentValues) {
        if (contentValues != null) {
            bnVar.f3267c.lock();
            try {
                bnVar.f3268d.getWritableDatabase().insertOrThrow("sessions", (String) null, contentValues);
            } catch (Exception unused) {
            } catch (Throwable th) {
                bnVar.f3267c.unlock();
                throw th;
            }
            bnVar.f3267c.unlock();
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m4995a(C2000bn bnVar, List list) {
        if (list != null && !list.isEmpty()) {
            bnVar.f3267c.lock();
            SQLiteDatabase sQLiteDatabase = null;
            try {
                SQLiteDatabase writableDatabase = bnVar.f3268d.getWritableDatabase();
                try {
                    if (bnVar.f3275k.get() % 10 == 0) {
                        bnVar.m5004c();
                    }
                    writableDatabase.beginTransaction();
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        ContentValues contentValues = (ContentValues) it.next();
                        writableDatabase.insertOrThrow("reports", (String) null, contentValues);
                        bnVar.m4993a(contentValues, "Event saved to db");
                    }
                    writableDatabase.setTransactionSuccessful();
                    bnVar.f3275k.incrementAndGet();
                    C1897bk.m4644a(writableDatabase);
                } catch (Exception unused) {
                    sQLiteDatabase = writableDatabase;
                    C1897bk.m4644a(sQLiteDatabase);
                    bnVar.f3267c.unlock();
                } catch (Throwable th) {
                    th = th;
                    sQLiteDatabase = writableDatabase;
                    C1897bk.m4644a(sQLiteDatabase);
                    bnVar.f3267c.unlock();
                    throw th;
                }
            } catch (Exception unused2) {
                C1897bk.m4644a(sQLiteDatabase);
                bnVar.f3267c.unlock();
            } catch (Throwable th2) {
                th = th2;
                C1897bk.m4644a(sQLiteDatabase);
                bnVar.f3267c.unlock();
                throw th;
            }
            bnVar.f3267c.unlock();
        }
    }
}
