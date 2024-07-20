package com.appnext.base.p078a.p081c;

import android.content.ContentValues;
import android.database.Cursor;
import com.appnext.base.p078a.p080b.C4885a;
import com.appnext.base.p078a.p080b.C4888d;
import com.appnext.base.p078a.p081c.C4893e;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

/* renamed from: com.appnext.base.a.c.a */
public final class C4889a extends C4893e<C4885a> {
    private static final String COLUMN_PACKAGE_NAME = "p";

    /* renamed from: dS */
    public static final String f4580dS = "ct";

    /* renamed from: dT */
    private static final String f4581dT = "c";

    /* renamed from: dU */
    private String[] f4582dU = {"p", f4581dT};

    /* renamed from: ar */
    public static String m6501ar() {
        return "create table ct ( p text, c integer)";
    }

    /* renamed from: a */
    public final long mo40968a(JSONArray jSONArray) {
        return super.mo40994a(f4580dS, jSONArray);
    }

    public final void delete() {
        super.delete(f4580dS);
    }

    /* renamed from: q */
    public final void mo40973q(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(C4893e.C4894a.Equals);
        super.mo40993a(f4580dS, new String[]{"p"}, new String[]{str}, arrayList);
    }

    /* renamed from: as */
    public final List<C4885a> mo40969as() {
        return super.mo40999y(f4580dS);
    }

    /* renamed from: r */
    public final List<C4885a> mo40974r(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(C4893e.C4894a.Equals);
        return super.mo40996a(f4580dS, new String[]{"p"}, new String[]{str}, (String) null, arrayList);
    }

    /* renamed from: b */
    private static ContentValues m6502b(C4885a aVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("p", aVar.getPackageName());
        contentValues.put(f4581dT, aVar.mo40948ag());
        return contentValues;
    }

    /* access modifiers changed from: protected */
    /* renamed from: at */
    public final String[] mo40970at() {
        return this.f4582dU;
    }

    /* renamed from: a */
    protected static C4885a m6500a(Cursor cursor) {
        return new C4885a(cursor.getString(cursor.getColumnIndex("p")), Integer.valueOf(cursor.getInt(cursor.getColumnIndex(f4581dT))));
    }

    /* renamed from: a */
    public final long mo40967a(C4885a aVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("p", aVar.getPackageName());
        contentValues.put(f4581dT, aVar.mo40948ag());
        return C4893e.m6538a(f4580dS, contentValues);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final /* synthetic */ C4888d mo40971b(Cursor cursor) {
        return new C4885a(cursor.getString(cursor.getColumnIndex("p")), Integer.valueOf(cursor.getInt(cursor.getColumnIndex(f4581dT))));
    }
}
