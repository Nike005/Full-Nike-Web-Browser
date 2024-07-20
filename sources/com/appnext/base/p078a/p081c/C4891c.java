package com.appnext.base.p078a.p081c;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.appnext.base.p078a.p080b.C4887c;
import com.appnext.base.p078a.p080b.C4888d;
import com.appnext.base.p078a.p081c.C4893e;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.appnext.base.a.c.c */
public final class C4891c extends C4893e<C4887c> {
    private static final String COLUMN_STATUS = "status";

    /* renamed from: dS */
    public static final String f4584dS = "config_table";

    /* renamed from: dV */
    public static final String f4585dV = "key";

    /* renamed from: dW */
    private static final String f4586dW = "sample";

    /* renamed from: dX */
    private static final String f4587dX = "sample_type";

    /* renamed from: dY */
    private static final String f4588dY = "cycle";

    /* renamed from: dZ */
    private static final String f4589dZ = "cycle_type";

    /* renamed from: ea */
    private static final String f4590ea = "service_key";

    /* renamed from: eb */
    private static final String f4591eb = "data";

    /* renamed from: dU */
    private String[] f4592dU = {"key", "status", "sample", "sample_type", "cycle", "cycle_type", "service_key", "data"};

    /* renamed from: ar */
    public static String m6511ar() {
        return "create table config_table ( key text primary key, status text not null default 'off', sample text not null default '1', sample_type text not null default '',cycle text not null default '1', cycle_type text not null default 'once', service_key text not null default '', data text not null default '')";
    }

    /* renamed from: b */
    public final long mo40978b(JSONArray jSONArray) {
        return super.mo40994a(f4584dS, jSONArray);
    }

    /* renamed from: a */
    public final long mo40976a(JSONObject jSONObject) {
        return super.mo40995a(f4584dS, jSONObject);
    }

    /* renamed from: a */
    public final long mo40975a(C4887c cVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", cVar.mo40958ak());
        contentValues.put("sample", cVar.mo40959al());
        contentValues.put("sample_type", cVar.mo40960am());
        contentValues.put("cycle", cVar.mo40961an());
        contentValues.put("cycle_type", cVar.mo40962ao());
        contentValues.put("key", cVar.getKey());
        contentValues.put("service_key", cVar.mo40963ap());
        JSONObject aq = cVar.mo40964aq();
        if (aq != null) {
            contentValues.put("data", aq.toString());
        }
        return C4893e.m6541b(f4584dS, contentValues);
    }

    public final void delete() {
        super.delete(f4584dS);
    }

    /* renamed from: s */
    public final void mo40980s(String str) {
        if (!TextUtils.isEmpty(str)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(C4893e.C4894a.Equals);
            super.mo40993a(f4584dS, new String[]{"key"}, new String[]{str}, arrayList);
        }
    }

    /* renamed from: as */
    public final List<C4887c> mo40977as() {
        return super.mo40999y(f4584dS);
    }

    /* renamed from: t */
    public final C4887c mo40981t(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(C4893e.C4894a.Equals);
        List a = super.mo40996a(f4584dS, new String[]{"key"}, new String[]{str}, (String) null, arrayList);
        if (a == null || a.size() <= 0) {
            return null;
        }
        return (C4887c) a.get(0);
    }

    /* access modifiers changed from: protected */
    /* renamed from: at */
    public final String[] mo40970at() {
        return this.f4592dU;
    }

    /* renamed from: c */
    protected static C4887c m6512c(Cursor cursor) {
        return new C4887c(cursor.getString(cursor.getColumnIndex("status")), cursor.getString(cursor.getColumnIndex("sample")), cursor.getString(cursor.getColumnIndex("sample_type")), cursor.getString(cursor.getColumnIndex("cycle")), cursor.getString(cursor.getColumnIndex("cycle_type")), cursor.getString(cursor.getColumnIndex("key")), cursor.getString(cursor.getColumnIndex("service_key")), cursor.getString(cursor.getColumnIndex("data")));
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final /* synthetic */ C4888d mo40971b(Cursor cursor) {
        return new C4887c(cursor.getString(cursor.getColumnIndex("status")), cursor.getString(cursor.getColumnIndex("sample")), cursor.getString(cursor.getColumnIndex("sample_type")), cursor.getString(cursor.getColumnIndex("cycle")), cursor.getString(cursor.getColumnIndex("cycle_type")), cursor.getString(cursor.getColumnIndex("key")), cursor.getString(cursor.getColumnIndex("service_key")), cursor.getString(cursor.getColumnIndex("data")));
    }
}
