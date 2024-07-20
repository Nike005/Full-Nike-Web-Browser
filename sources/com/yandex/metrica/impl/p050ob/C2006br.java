package com.yandex.metrica.impl.p050ob;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.yandex.metrica.impl.C1897bk;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.yandex.metrica.impl.ob.br */
public class C2006br {

    /* renamed from: a */
    private final C2010bv f3296a;

    /* renamed from: b */
    private String f3297b;

    public C2006br(C2002bo boVar, String str) {
        this.f3296a = new C2012bx(boVar);
        this.f3297b = str;
    }

    /* renamed from: a */
    public List<C2052cw> mo17304a() {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor;
        Throwable th;
        try {
            sQLiteDatabase = this.f3296a.mo17313a();
            try {
                cursor = sQLiteDatabase.query(this.f3297b, (String[]) null, (String) null, (String[]) null, (String) null, (String) null, (String) null);
                if (cursor != null) {
                    try {
                        if (cursor.moveToFirst()) {
                            ArrayList arrayList = new ArrayList();
                            do {
                                arrayList.add(new C2052cw(cursor.getString(cursor.getColumnIndex("name")), cursor.getLong(cursor.getColumnIndex("granted")) == 1));
                            } while (cursor.moveToNext());
                            this.f3296a.mo17314a(sQLiteDatabase);
                            C1897bk.m4643a(cursor);
                            return arrayList;
                        }
                    } catch (Exception unused) {
                    } catch (Throwable th2) {
                        th = th2;
                        this.f3296a.mo17314a(sQLiteDatabase);
                        C1897bk.m4643a(cursor);
                        throw th;
                    }
                }
            } catch (Exception unused2) {
                cursor = null;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                cursor = null;
                th = th4;
                this.f3296a.mo17314a(sQLiteDatabase);
                C1897bk.m4643a(cursor);
                throw th;
            }
        } catch (Exception unused3) {
            sQLiteDatabase = null;
            cursor = null;
        } catch (Throwable th5) {
            cursor = null;
            th = th5;
            sQLiteDatabase = null;
            this.f3296a.mo17314a(sQLiteDatabase);
            C1897bk.m4643a(cursor);
            throw th;
        }
        this.f3296a.mo17314a(sQLiteDatabase);
        C1897bk.m4643a(cursor);
        return null;
    }

    /* renamed from: a */
    public void mo17305a(List<C2052cw> list) {
        SQLiteDatabase a = this.f3296a.mo17313a();
        try {
            a.beginTransaction();
            a.execSQL("delete from permissions");
            for (C2052cw next : list) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", next.mo17513b());
                contentValues.put("granted", Integer.valueOf(next.mo17512a() ? 1 : 0));
                a.insert("permissions", (String) null, contentValues);
            }
            a.setTransactionSuccessful();
        } catch (SQLException unused) {
        } catch (Throwable th) {
            a.endTransaction();
            this.f3296a.mo17314a(a);
            throw th;
        }
        a.endTransaction();
        this.f3296a.mo17314a(a);
    }
}
