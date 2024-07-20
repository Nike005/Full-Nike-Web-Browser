package com.appnext.base.p078a.p081c;

import android.content.ContentValues;
import android.database.Cursor;
import com.appnext.base.p078a.p080b.C4886b;
import com.appnext.base.p078a.p080b.C4888d;
import com.appnext.base.p078a.p081c.C4893e;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;
import org.json.JSONArray;

/* renamed from: com.appnext.base.a.c.d */
public class C4892d extends C4893e<C4886b> {
    public static final String COLUMN_TYPE = "t";

    /* renamed from: ec */
    public static final String f4593ec = "pk";

    /* renamed from: ed */
    public static final String f4594ed = "cd";

    /* renamed from: ee */
    public static final String f4595ee = "cdd";

    /* renamed from: ef */
    public static final String f4596ef = "cdt";

    /* renamed from: dU */
    private String[] f4597dU = {f4593ec, COLUMN_TYPE, f4594ed, f4595ee, f4596ef};

    /* renamed from: eg */
    private String f4598eg;

    /* renamed from: a */
    private static String m6521a(boolean z) {
        return z ? "primary key" : "";
    }

    public C4892d(String str) {
        this.f4598eg = str;
    }

    /* renamed from: a */
    public final long mo40982a(C4886b bVar) {
        return C4893e.m6538a(this.f4598eg, m6523c(bVar));
    }

    /* renamed from: a */
    public final long mo40983a(JSONArray jSONArray) {
        return super.mo40997b(this.f4598eg, jSONArray);
    }

    /* renamed from: b */
    public final long mo40986b(C4886b bVar) {
        mo40989u(bVar.getType());
        return C4893e.m6538a(this.f4598eg, m6523c(bVar));
    }

    /* renamed from: u */
    public final void mo40989u(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(C4893e.C4894a.Equals);
        super.mo40993a(this.f4598eg, new String[]{COLUMN_TYPE}, new String[]{str}, arrayList);
    }

    /* renamed from: b */
    public final int mo40985b(String str, long j) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(C4893e.C4894a.Equals);
        arrayList.add(C4893e.C4894a.LessThan);
        return super.mo40993a(this.f4598eg, new String[]{COLUMN_TYPE, f4595ee}, new String[]{str, String.valueOf(j)}, arrayList);
    }

    public final void delete() {
        super.delete(this.f4598eg);
    }

    /* renamed from: as */
    public final List<C4886b> mo40984as() {
        return super.mo40999y(this.f4598eg);
    }

    /* renamed from: v */
    public final List<C4886b> mo40990v(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(C4893e.C4894a.Equals);
        return super.mo40996a(this.f4598eg, new String[]{COLUMN_TYPE}, new String[]{str}, (String) null, arrayList);
    }

    /* renamed from: w */
    public final List<C4886b> mo40991w(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(C4893e.C4894a.Equals);
        return super.mo40996a(this.f4598eg, new String[]{f4593ec}, new String[]{str}, (String) null, arrayList);
    }

    /* renamed from: c */
    public final List<C4886b> mo40987c(String str, long j) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(C4893e.C4894a.Equals);
        arrayList.add(C4893e.C4894a.GreaterThan);
        return super.mo40996a(this.f4598eg, new String[]{COLUMN_TYPE, f4595ee}, new String[]{str, String.valueOf(j)}, (String) null, arrayList);
    }

    /* renamed from: x */
    public final List<C4886b> mo40992x(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(C4893e.C4894a.Equals);
        String str2 = this.f4598eg;
        String[] strArr = {COLUMN_TYPE};
        String[] strArr2 = {str};
        List<C4886b> a = super.mo40996a(str2, strArr, strArr2, f4595ee + " DESC", arrayList);
        if (a == null || a.isEmpty()) {
            return null;
        }
        ListIterator<C4886b> listIterator = a.listIterator();
        listIterator.next();
        while (listIterator.hasNext()) {
            listIterator.next();
            listIterator.remove();
        }
        return a;
    }

    /* renamed from: c */
    private static ContentValues m6523c(C4886b bVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(f4593ec, bVar.mo40950ah());
        contentValues.put(COLUMN_TYPE, bVar.getType());
        contentValues.put(f4594ed, bVar.mo40951ai());
        contentValues.put(f4596ef, bVar.getDataType());
        return contentValues;
    }

    /* access modifiers changed from: protected */
    /* renamed from: at */
    public final String[] mo40970at() {
        return this.f4597dU;
    }

    /* renamed from: d */
    protected static C4886b m6524d(Cursor cursor) {
        String string = cursor.getString(cursor.getColumnIndex(f4593ec));
        String string2 = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE));
        String string3 = cursor.getString(cursor.getColumnIndex(f4594ed));
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(cursor.getLong(cursor.getColumnIndex(f4595ee)) * 1000);
        return new C4886b(string, string2, string3, instance.getTime(), cursor.getString(cursor.getColumnIndex(f4596ef)));
    }

    /* renamed from: b */
    protected static String m6522b(String str, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("create table ");
        sb.append(str);
        sb.append(" ( pk text not null, t text not null " + "" + ", cd" + " text not null, cdd" + " text default (strftime('%s','now')), cdt" + " text)");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final /* synthetic */ C4888d mo40971b(Cursor cursor) {
        String string = cursor.getString(cursor.getColumnIndex(f4593ec));
        String string2 = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE));
        String string3 = cursor.getString(cursor.getColumnIndex(f4594ed));
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(cursor.getLong(cursor.getColumnIndex(f4595ee)) * 1000);
        return new C4886b(string, string2, string3, instance.getTime(), cursor.getString(cursor.getColumnIndex(f4596ef)));
    }
}
