package com.yandex.metrica.impl.p050ob;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.Closeable;

/* renamed from: com.yandex.metrica.impl.ob.bo */
public class C2002bo extends SQLiteOpenHelper implements Closeable {

    /* renamed from: a */
    protected final C2007bs f3279a;

    public C2002bo(Context context, String str, C2007bs bsVar) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, C1972bm.f3253b);
        this.f3279a = bsVar;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.f3279a.mo17310b(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.f3279a.mo17307a(sQLiteDatabase, i, i2);
    }

    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
        this.f3279a.mo17306a(sQLiteDatabase);
    }
}
