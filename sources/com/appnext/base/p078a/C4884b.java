package com.appnext.base.p078a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.appnext.base.p078a.p081c.C4890b;

/* renamed from: com.appnext.base.a.b */
public class C4884b extends SQLiteOpenHelper {

    /* renamed from: dx */
    private static final String f4564dx = "appnext_dbs472";

    /* renamed from: dy */
    private static final int f4565dy = 12;

    /* renamed from: dz */
    private static volatile C4884b f4566dz;

    /* renamed from: c */
    public static C4884b m6482c(Context context) {
        if (f4566dz == null) {
            synchronized (C4884b.class) {
                if (f4566dz == null) {
                    f4566dz = new C4884b(context.getApplicationContext());
                }
            }
        }
        return f4566dz;
    }

    private C4884b(Context context) {
        super(context, f4564dx, (SQLiteDatabase.CursorFactory) null, 12);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("create table ct ( p text, c integer)");
            sQLiteDatabase.execSQL(C4890b.m6510ar());
            sQLiteDatabase.execSQL("create table config_table ( key text primary key, status text not null default 'off', sample text not null default '1', sample_type text not null default '',cycle text not null default '1', cycle_type text not null default 'once', service_key text not null default '', data text not null default '')");
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    public final void mo40945a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ct");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS collected_data_table");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS config_table");
            onCreate(sQLiteDatabase);
        } catch (Throwable unused) {
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        try {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ct");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS collected_data_table");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS config_table");
            onCreate(sQLiteDatabase);
        } catch (Throwable unused) {
        }
    }
}
