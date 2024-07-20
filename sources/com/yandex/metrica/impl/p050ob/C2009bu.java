package com.yandex.metrica.impl.p050ob;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.yandex.metrica.impl.C1897bk;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.yandex.metrica.impl.ob.bu */
public class C2009bu implements C2008bt {

    /* renamed from: a */
    private final HashMap<String, String[]> f3302a;

    public C2009bu(HashMap<String, String[]> hashMap) {
        this.f3302a = hashMap;
    }

    /* renamed from: a */
    public boolean mo17311a(SQLiteDatabase sQLiteDatabase) {
        Cursor cursor;
        boolean z = true;
        try {
            for (Map.Entry next : this.f3302a.entrySet()) {
                cursor = null;
                cursor = sQLiteDatabase.query((String) next.getKey(), (String[]) null, (String) null, (String[]) null, (String) null, (String) null, (String) null);
                if (cursor == null) {
                    C1897bk.m4643a(cursor);
                    return false;
                }
                z &= mo17312a(cursor, (String[]) next.getValue());
                C1897bk.m4643a(cursor);
            }
            return z;
        } catch (Exception unused) {
            return false;
        } catch (Throwable th) {
            C1897bk.m4643a(cursor);
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo17312a(Cursor cursor, String[] strArr) {
        String[] columnNames = cursor.getColumnNames();
        Arrays.sort(columnNames);
        Arrays.sort(strArr);
        return Arrays.equals(columnNames, strArr);
    }
}
