package com.appnext.base.p078a.p081c;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteFullException;
import com.appnext.base.p078a.p079a.C4881a;
import com.appnext.base.p078a.p080b.C4888d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* renamed from: com.appnext.base.a.c.e */
public abstract class C4893e<MODEL extends C4888d> {

    /* renamed from: eh */
    private static final int f4599eh = -1;

    /* renamed from: ei */
    private static final String f4600ei = " DESC";

    /* access modifiers changed from: protected */
    /* renamed from: at */
    public abstract String[] mo40970at();

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract MODEL mo40971b(Cursor cursor);

    /* renamed from: com.appnext.base.a.c.e$a */
    protected enum C4894a {
        Equals(" = ? "),
        GreaterThan(" >= ? "),
        LessThan(" <= ? ");
        
        private String mOperator;

        private C4894a(String str) {
            this.mOperator = str;
        }

        /* renamed from: au */
        public final String mo41000au() {
            return this.mOperator;
        }
    }

    /* renamed from: a */
    protected static long m6538a(String str, ContentValues contentValues) {
        try {
            long insert = C4881a.m6478ac().mo40943ad().insert(str, (String) null, contentValues);
            C4881a.m6478ac().mo40944ae();
            return insert;
        } catch (SQLiteFullException e) {
            C4881a.m6478ac();
            C4881a.m6477a(C4881a.C4883a.DatabaseOrDiskFull$53629b42, new Exception(e.getMessage()));
            return -1;
        } catch (Throwable th) {
            C4881a.m6478ac();
            C4881a.m6477a(C4881a.C4883a.Global$53629b42, th);
            return -1;
        }
    }

    /* renamed from: b */
    protected static long m6541b(String str, ContentValues contentValues) {
        try {
            long insertWithOnConflict = C4881a.m6478ac().mo40943ad().insertWithOnConflict(str, (String) null, contentValues, 5);
            C4881a.m6478ac().mo40944ae();
            return insertWithOnConflict;
        } catch (SQLiteFullException e) {
            C4881a.m6478ac();
            C4881a.m6477a(C4881a.C4883a.DatabaseOrDiskFull$53629b42, new Exception(e.getMessage()));
            return -1;
        } catch (Throwable th) {
            C4881a.m6478ac();
            C4881a.m6477a(C4881a.C4883a.Global$53629b42, th);
            return -1;
        }
    }

    /* renamed from: a */
    protected static long m6537a(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues) {
        try {
            return sQLiteDatabase.insertWithOnConflict(str, (String) null, contentValues, 5);
        } catch (SQLiteFullException e) {
            C4881a.m6478ac();
            C4881a.m6477a(C4881a.C4883a.DatabaseOrDiskFull$53629b42, new Exception(e.getMessage()));
            return -1;
        } catch (Throwable th) {
            C4881a.m6478ac();
            C4881a.m6477a(C4881a.C4883a.Global$53629b42, th);
            return -1;
        }
    }

    /* renamed from: b */
    protected static long m6540b(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues) {
        try {
            return sQLiteDatabase.insert(str, (String) null, contentValues);
        } catch (SQLiteFullException e) {
            C4881a.m6478ac();
            C4881a.m6477a(C4881a.C4883a.DatabaseOrDiskFull$53629b42, new Exception(e.getMessage()));
            return -1;
        } catch (Throwable th) {
            C4881a.m6478ac();
            C4881a.m6477a(C4881a.C4883a.Global$53629b42, th);
            return -1;
        }
    }

    /* renamed from: b */
    private static ContentValues m6542b(JSONObject jSONObject) {
        try {
            ContentValues contentValues = new ContentValues();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                contentValues.put(next, jSONObject.getString(next));
            }
            return contentValues;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final long mo40995a(String str, JSONObject jSONObject) {
        try {
            long insertWithOnConflict = C4881a.m6478ac().mo40943ad().insertWithOnConflict(str, (String) null, m6542b(jSONObject), 5);
            C4881a.m6478ac().mo40944ae();
            return insertWithOnConflict;
        } catch (SQLiteFullException e) {
            C4881a.m6478ac();
            C4881a.m6477a(C4881a.C4883a.DatabaseOrDiskFull$53629b42, new Exception(e.getMessage()));
            return -1;
        } catch (Throwable th) {
            C4881a.m6478ac();
            C4881a.m6477a(C4881a.C4883a.Global$53629b42, th);
            return -1;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0037, code lost:
        if (0 == 0) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0029, code lost:
        if (r2 != null) goto L_0x002b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002b, code lost:
        r2.endTransaction();
        com.appnext.base.p078a.p079a.C4881a.m6478ac().mo40944ae();
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long mo40994a(java.lang.String r7, org.json.JSONArray r8) {
        /*
            r6 = this;
            r0 = -1
            if (r8 == 0) goto L_0x003a
            r2 = 0
            int r3 = r8.length()     // Catch:{ all -> 0x0036 }
            com.appnext.base.a.a.a r4 = com.appnext.base.p078a.p079a.C4881a.m6478ac()     // Catch:{ all -> 0x0036 }
            android.database.sqlite.SQLiteDatabase r2 = r4.mo40943ad()     // Catch:{ all -> 0x0036 }
            r2.beginTransaction()     // Catch:{ all -> 0x0036 }
            r4 = 0
        L_0x0015:
            if (r4 >= r3) goto L_0x0026
            org.json.JSONObject r5 = r8.getJSONObject(r4)     // Catch:{ all -> 0x0036 }
            android.content.ContentValues r5 = m6542b((org.json.JSONObject) r5)     // Catch:{ all -> 0x0036 }
            long r0 = m6537a(r2, r7, r5)     // Catch:{ all -> 0x0036 }
            int r4 = r4 + 1
            goto L_0x0015
        L_0x0026:
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x0036 }
            if (r2 == 0) goto L_0x003a
        L_0x002b:
            r2.endTransaction()
            com.appnext.base.a.a.a r7 = com.appnext.base.p078a.p079a.C4881a.m6478ac()
            r7.mo40944ae()
            goto L_0x003a
        L_0x0036:
            if (r2 == 0) goto L_0x003a
            goto L_0x002b
        L_0x003a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.base.p078a.p081c.C4893e.mo40994a(java.lang.String, org.json.JSONArray):long");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0037, code lost:
        if (0 == 0) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0029, code lost:
        if (r2 != null) goto L_0x002b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002b, code lost:
        r2.endTransaction();
        com.appnext.base.p078a.p079a.C4881a.m6478ac().mo40944ae();
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long mo40997b(java.lang.String r7, org.json.JSONArray r8) {
        /*
            r6 = this;
            r0 = -1
            if (r8 == 0) goto L_0x003a
            r2 = 0
            int r3 = r8.length()     // Catch:{ all -> 0x0036 }
            com.appnext.base.a.a.a r4 = com.appnext.base.p078a.p079a.C4881a.m6478ac()     // Catch:{ all -> 0x0036 }
            android.database.sqlite.SQLiteDatabase r2 = r4.mo40943ad()     // Catch:{ all -> 0x0036 }
            r2.beginTransaction()     // Catch:{ all -> 0x0036 }
            r4 = 0
        L_0x0015:
            if (r4 >= r3) goto L_0x0026
            org.json.JSONObject r5 = r8.getJSONObject(r4)     // Catch:{ all -> 0x0036 }
            android.content.ContentValues r5 = m6542b((org.json.JSONObject) r5)     // Catch:{ all -> 0x0036 }
            long r0 = m6540b(r2, r7, r5)     // Catch:{ all -> 0x0036 }
            int r4 = r4 + 1
            goto L_0x0015
        L_0x0026:
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x0036 }
            if (r2 == 0) goto L_0x003a
        L_0x002b:
            r2.endTransaction()
            com.appnext.base.a.a.a r7 = com.appnext.base.p078a.p079a.C4881a.m6478ac()
            r7.mo40944ae()
            goto L_0x003a
        L_0x0036:
            if (r2 == 0) goto L_0x003a
            goto L_0x002b
        L_0x003a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.base.p078a.p081c.C4893e.mo40997b(java.lang.String, org.json.JSONArray):long");
    }

    /* access modifiers changed from: protected */
    public final void delete(String str) {
        mo40993a(str, (String[]) null, (String[]) null, (List<C4894a>) null);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final int mo40993a(String str, String[] strArr, String[] strArr2, List<C4894a> list) {
        try {
            SQLiteDatabase ad = C4881a.m6478ac().mo40943ad();
            String str2 = null;
            if (strArr != null) {
                str2 = m6539a(strArr, list);
            }
            int delete = ad.delete(str, str2, strArr2);
            C4881a.m6478ac().mo40944ae();
            return delete;
        } catch (SQLiteFullException e) {
            C4881a.m6478ac();
            C4881a.m6477a(C4881a.C4883a.DatabaseOrDiskFull$53629b42, new Exception(e.getMessage()));
            return 0;
        } catch (Throwable th) {
            C4881a.m6478ac();
            C4881a.m6477a(C4881a.C4883a.Global$53629b42, new Exception(th.getMessage()));
            return 0;
        }
    }

    /* renamed from: e */
    protected static void m6544e(String str, String str2) {
        try {
            C4881a.m6478ac().mo40943ad().delete(str, str2, (String[]) null);
            C4881a.m6478ac().mo40944ae();
        } catch (SQLiteFullException e) {
            C4881a.m6478ac();
            C4881a.m6477a(C4881a.C4883a.DatabaseOrDiskFull$53629b42, new Exception(e.getMessage()));
        } catch (Throwable th) {
            C4881a.m6478ac();
            C4881a.m6477a(C4881a.C4883a.Global$53629b42, new Exception(th.getMessage()));
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: y */
    public final List<MODEL> mo40999y(String str) {
        try {
            List<MODEL> e = m6543e(C4881a.m6478ac().mo40943ad().query(str, mo40970at(), (String) null, (String[]) null, (String) null, (String) null, (String) null));
            C4881a.m6478ac().mo40944ae();
            return e;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final List<MODEL> mo40996a(String str, String[] strArr, String[] strArr2, String str2, List<C4894a> list) {
        try {
            List<MODEL> e = m6543e(C4881a.m6478ac().mo40943ad().query(str, mo40970at(), m6539a(strArr, list), strArr2, (String) null, (String) null, str2));
            C4881a.m6478ac().mo40944ae();
            return e;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: z */
    protected static String m6545z(String str) {
        return str + f4600ei;
    }

    /* renamed from: e */
    private List<MODEL> m6543e(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                arrayList.add(mo40971b(cursor));
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Throwable unused) {
        }
        return arrayList;
    }

    /* renamed from: a */
    private static String m6539a(String[] strArr, List<C4894a> list) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
            int length = strArr.length;
            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    sb.append(" AND ");
                }
                sb.append(strArr[i]);
                sb.append(list.get(i).mo41000au());
            }
        } catch (Throwable unused) {
        }
        return sb.toString();
    }
}
