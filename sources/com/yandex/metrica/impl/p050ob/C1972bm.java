package com.yandex.metrica.impl.p050ob;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;
import com.appnext.base.p082b.C4899d;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.C1897bk;
import com.yandex.metrica.impl.utils.C2220e;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.yandex.metrica.impl.ob.bm */
public final class C1972bm {

    /* renamed from: a */
    public static final Boolean f3252a = false;

    /* renamed from: b */
    public static final int f3253b = YandexMetrica.getLibraryApiLevel();

    /* renamed from: c */
    static final SparseArray<C1985l> f3254c;

    /* renamed from: d */
    static final SparseArray<C1985l> f3255d;

    /* renamed from: e */
    static final HashMap<String, String[]> f3256e;

    /* renamed from: com.yandex.metrica.impl.ob.bm$aa */
    public static final class C1974aa implements C1995v {
    }

    /* renamed from: com.yandex.metrica.impl.ob.bm$v */
    interface C1995v {

        /* renamed from: a */
        public static final String[] f3258a = {"key", "value", "type"};
    }

    /* renamed from: com.yandex.metrica.impl.ob.bm$w */
    public static final class C1996w {

        /* renamed from: a */
        public static final String[] f3259a = {"name", "granted"};
    }

    /* renamed from: com.yandex.metrica.impl.ob.bm$x */
    public static final class C1997x implements C1995v {
    }

    /* renamed from: com.yandex.metrica.impl.ob.bm$y */
    public static final class C1998y {

        /* renamed from: a */
        public static final String[] f3260a = {"id", "number", "name", "value", "type", C4899d.f4625fl, "session_id", "wifi_network_info", "cell_info", "location_info", "error_environment", "user_info", "session_type", "app_environment", "app_environment_revision", "truncated", "connection_type", "cellular_connection_type", "custom_type", "wifi_access_point"};

        /* renamed from: b */
        static final String f3261b = ("CREATE TABLE IF NOT EXISTS reports (id INTEGER PRIMARY KEY,name TEXT,value TEXT,number INTEGER,type INTEGER,time INTEGER,session_id TEXT,wifi_network_info TEXT DEFAULT '',cell_info TEXT DEFAULT '',location_info TEXT DEFAULT '',error_environment TEXT,user_info TEXT,session_type INTEGER DEFAULT " + C1971bl.FOREGROUND.mo17260a() + ",app_environment TEXT DEFAULT '{}',app_environment_revision INTEGER DEFAULT 0,truncated INTEGER DEFAULT 0,connection_type INTEGER DEFAULT 2,cellular_connection_type TEXT,custom_type INTEGER DEFAULT 0, wifi_access_point TEXT )");
    }

    /* renamed from: com.yandex.metrica.impl.ob.bm$z */
    public static final class C1999z {

        /* renamed from: a */
        public static final String[] f3262a = {"id", "start_time", "network_info", "report_request_parameters", "server_time_offset", "type"};

        /* renamed from: b */
        static final String f3263b = ("CREATE TABLE IF NOT EXISTS sessions (id INTEGER,start_time INTEGER,network_info TEXT,report_request_parameters TEXT,server_time_offset INTEGER,type INTEGER DEFAULT " + C1971bl.FOREGROUND.mo17260a() + " )");

        /* renamed from: c */
        public static final String f3264c = String.format(Locale.US, "(select count(%s.%s) from %s where %s.%s = %s.%s) = 0 and %s NOT IN (%s)", new Object[]{"reports", "id", "reports", "reports", "session_id", "sessions", "id", "id", C2220e.m5934a(2)});
    }

    static {
        SparseArray<C1985l> sparseArray = new SparseArray<>();
        f3254c = sparseArray;
        sparseArray.put(6, new C1993t((byte) 0));
        f3254c.put(7, new C1994u((byte) 0));
        f3254c.put(14, new C1986m((byte) 0));
        f3254c.put(29, new C1987n((byte) 0));
        f3254c.put(37, new C1988o((byte) 0));
        f3254c.put(39, new C1989p((byte) 0));
        f3254c.put(45, new C1990q((byte) 0));
        f3254c.put(47, new C1991r((byte) 0));
        f3254c.put(50, new C1992s((byte) 0));
        SparseArray<C1985l> sparseArray2 = new SparseArray<>();
        f3255d = sparseArray2;
        sparseArray2.put(12, new C1980g((byte) 0));
        f3255d.put(29, new C1981h((byte) 0));
        f3255d.put(47, new C1982i((byte) 0));
        f3255d.put(50, new C1983j((byte) 0));
        f3255d.put(55, new C1984k((byte) 0));
        HashMap<String, String[]> hashMap = new HashMap<>();
        f3256e = hashMap;
        hashMap.put("reports", C1998y.f3260a);
        f3256e.put("sessions", C1999z.f3262a);
        f3256e.put("preferences", C1997x.f3258a);
    }

    /* renamed from: a */
    public static C2007bs m4966a() {
        return new C2007bs(new C1976c(), new C1977d(), f3254c, new C2009bu(f3256e));
    }

    /* renamed from: b */
    public static C2007bs m4967b() {
        HashMap hashMap = new HashMap();
        hashMap.put("preferences", C1997x.f3258a);
        hashMap.put("startup", C1974aa.f3258a);
        hashMap.put("permissions", C1996w.f3259a);
        return new C2007bs(new C1978e(), new C1979f(), f3255d, new C2009bu(hashMap));
    }

    /* renamed from: c */
    public static C2007bs m4968c() {
        HashMap hashMap = new HashMap();
        hashMap.put("preferences", C1997x.f3258a);
        return new C2007bs(new C1973a((byte) 0), new C1975b((byte) 0), new SparseArray(), new C2009bu(hashMap));
    }

    /* renamed from: com.yandex.metrica.impl.ob.bm$l */
    static abstract class C1985l {
        /* access modifiers changed from: protected */
        /* renamed from: a */
        public abstract void mo17261a(SQLiteDatabase sQLiteDatabase) throws SQLException, JSONException;

        C1985l() {
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.bm$t */
    private static class C1993t extends C1985l {
        private C1993t() {
        }

        /* synthetic */ C1993t(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo17261a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("ALTER TABLE sessions" + " ADD COLUMN wifi_network_info" + " TEXT DEFAULT ''");
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.bm$u */
    private static class C1994u extends C1985l {
        private C1994u() {
        }

        /* synthetic */ C1994u(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo17261a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("ALTER TABLE sessions" + " ADD COLUMN report_request_parameters" + " TEXT DEFAULT ''");
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.bm$m */
    private static class C1986m extends C1985l {
        private C1986m() {
        }

        /* synthetic */ C1986m(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo17261a(SQLiteDatabase sQLiteDatabase) throws SQLException, JSONException {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS sessions_BACKUP (" + "id INTEGER," + "start_time INTEGER," + "connection_type INTEGER," + "network_type TEXT," + "country_code INTEGER," + "operator_id INTEGER," + "lac INTEGER," + "report_request_parameters TEXT );");
            StringBuilder sb = new StringBuilder();
            sb.append("id,");
            sb.append("start_time,");
            sb.append("connection_type,");
            sb.append("network_type,");
            sb.append("country_code,");
            sb.append("operator_id,");
            sb.append("lac,");
            sb.append("report_request_parameters");
            sQLiteDatabase.execSQL("INSERT INTO sessions_BACKUP" + " SELECT " + sb + " FROM sessions;");
            sQLiteDatabase.execSQL("DROP TABLE sessions;");
            sQLiteDatabase.execSQL(C1999z.f3263b);
            Cursor cursor = null;
            try {
                Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM sessions_BACKUP", (String[]) null);
                while (rawQuery.moveToNext()) {
                    try {
                        ContentValues contentValues = new ContentValues();
                        DatabaseUtils.cursorRowToContentValues(rawQuery, contentValues);
                        ArrayList<String> arrayList = new ArrayList<>();
                        arrayList.add("id");
                        arrayList.add("start_time");
                        arrayList.add("report_request_parameters");
                        ContentValues contentValues2 = new ContentValues(contentValues);
                        for (Map.Entry next : contentValues.valueSet()) {
                            if (!arrayList.contains(next.getKey())) {
                                contentValues2.remove((String) next.getKey());
                            }
                        }
                        for (String remove : arrayList) {
                            contentValues.remove(remove);
                        }
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("conn_type", contentValues.getAsInteger("connection_type"));
                        jSONObject.putOpt("net_type", contentValues.get("network_type"));
                        jSONObject.putOpt("operator_id", contentValues.get("operator_id"));
                        jSONObject.putOpt("lac", contentValues.get("lac"));
                        jSONObject.putOpt("country_code", contentValues.get("country_code"));
                        contentValues2.put("network_info", jSONObject.toString());
                        sQLiteDatabase.insertOrThrow("sessions", (String) null, contentValues2);
                    } catch (Throwable th) {
                        th = th;
                        cursor = rawQuery;
                        C1897bk.m4643a(cursor);
                        throw th;
                    }
                }
                C1897bk.m4643a(rawQuery);
                sQLiteDatabase.execSQL("DROP TABLE sessions_BACKUP;");
                sQLiteDatabase.execSQL("ALTER TABLE reports" + " ADD COLUMN wifi_network_info" + " TEXT DEFAULT ''");
                sQLiteDatabase.execSQL("ALTER TABLE reports" + " ADD COLUMN cell_info" + " TEXT DEFAULT ''");
                sQLiteDatabase.execSQL("ALTER TABLE reports" + " ADD COLUMN location_info" + " TEXT DEFAULT ''");
            } catch (Throwable th2) {
                th = th2;
                C1897bk.m4643a(cursor);
                throw th;
            }
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.bm$n */
    private static class C1987n extends C1985l {
        private C1987n() {
        }

        /* synthetic */ C1987n(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo17261a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("ALTER TABLE reports" + " ADD COLUMN environment" + " TEXT ");
            sQLiteDatabase.execSQL("ALTER TABLE reports" + " ADD COLUMN user_info" + " TEXT ");
            sQLiteDatabase.execSQL("ALTER TABLE reports" + " ADD COLUMN session_type" + " INTEGER DEFAULT " + C1971bl.FOREGROUND.mo17260a());
            sQLiteDatabase.execSQL("UPDATE reports" + " SET session_type = " + C1971bl.BACKGROUND.mo17260a() + " WHERE session_id" + " = -2");
            StringBuilder sb = new StringBuilder();
            sb.append("ALTER TABLE sessions");
            sb.append(" ADD COLUMN server_time_offset");
            sb.append(" INTEGER ");
            sQLiteDatabase.execSQL(sb.toString());
            sQLiteDatabase.execSQL("ALTER TABLE sessions" + " ADD COLUMN type" + " INTEGER DEFAULT " + C1971bl.FOREGROUND.mo17260a());
            sQLiteDatabase.execSQL("UPDATE sessions" + " SET type = " + C1971bl.BACKGROUND.mo17260a() + " WHERE id" + " = -2");
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.bm$g */
    private static class C1980g extends C1985l {
        private C1980g() {
        }

        /* synthetic */ C1980g(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo17261a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS api_level_info (API_LEVEL INT )");
            ContentValues contentValues = new ContentValues();
            contentValues.put("API_LEVEL", Integer.valueOf(YandexMetrica.getLibraryApiLevel()));
            sQLiteDatabase.insert("api_level_info", "API_LEVEL", contentValues);
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.bm$h */
    private static class C1981h extends C1985l {
        private C1981h() {
        }

        /* synthetic */ C1981h(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo17261a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS api_level_info");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS device_id_info");
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.bm$i */
    private static class C1982i extends C1985l {
        private C1982i() {
        }

        /* synthetic */ C1982i(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo17261a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS preferences (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS startup (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.bm$j */
    private static class C1983j extends C1985l {
        private C1983j() {
        }

        /* synthetic */ C1983j(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo17261a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS GeoLocationInfo");
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.bm$k */
    private static final class C1984k extends C1985l {
        private C1984k() {
        }

        /* synthetic */ C1984k(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo17261a(SQLiteDatabase sQLiteDatabase) throws SQLException, JSONException {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS permissions (name TEXT PRIMARY KEY,granted INTEGER)");
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.bm$o */
    private static class C1988o extends C1985l {

        /* renamed from: a */
        private static final String f3257a = ("CREATE TABLE IF NOT EXISTS reports (id INTEGER PRIMARY KEY,name TEXT,value TEXT,number INTEGER,type INTEGER,time INTEGER,session_id TEXT,wifi_network_info TEXT DEFAULT '',cell_info TEXT DEFAULT '',location_info TEXT DEFAULT '',error_environment TEXT,user_info TEXT,session_type INTEGER DEFAULT " + C1971bl.FOREGROUND.mo17260a() + ",app_environment TEXT DEFAULT '{}',app_environment_revision INTEGER DEFAULT 0 )");

        private C1988o() {
        }

        /* synthetic */ C1988o(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo17261a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("ALTER TABLE reports" + " ADD COLUMN app_environment" + " TEXT DEFAULT '{}'");
            sQLiteDatabase.execSQL("ALTER TABLE reports" + " ADD COLUMN app_environment_revision" + " INTEGER DEFAULT 0");
            sQLiteDatabase.execSQL("ALTER TABLE reports RENAME TO reports_backup");
            sQLiteDatabase.execSQL(f3257a);
            Cursor cursor = null;
            try {
                Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM reports_backup", (String[]) null);
                while (rawQuery.moveToNext()) {
                    try {
                        ContentValues contentValues = new ContentValues();
                        DatabaseUtils.cursorRowToContentValues(rawQuery, contentValues);
                        String asString = contentValues.getAsString("environment");
                        contentValues.remove("environment");
                        contentValues.put("error_environment", asString);
                        sQLiteDatabase.insert("reports", (String) null, contentValues);
                    } catch (Throwable th) {
                        th = th;
                        cursor = rawQuery;
                        C1897bk.m4643a(cursor);
                        throw th;
                    }
                }
                C1897bk.m4643a(rawQuery);
                sQLiteDatabase.execSQL("DROP TABLE reports_backup");
            } catch (Throwable th2) {
                th = th2;
                C1897bk.m4643a(cursor);
                throw th;
            }
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.bm$p */
    private static class C1989p extends C1985l {
        private C1989p() {
        }

        /* synthetic */ C1989p(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo17261a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("ALTER TABLE reports" + " ADD COLUMN truncated" + " INTEGER DEFAULT 0");
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.bm$q */
    private static class C1990q extends C1985l {
        private C1990q() {
        }

        /* synthetic */ C1990q(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo17261a(SQLiteDatabase sQLiteDatabase) throws SQLException, JSONException {
            sQLiteDatabase.execSQL("ALTER TABLE reports" + " ADD COLUMN connection_type" + " INTEGER DEFAULT 2");
            sQLiteDatabase.execSQL("ALTER TABLE reports" + " ADD COLUMN cellular_connection_type" + " TEXT ");
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.bm$r */
    private static class C1991r extends C1985l {
        private C1991r() {
        }

        /* synthetic */ C1991r(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo17261a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS preferences (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
            sQLiteDatabase.execSQL("ALTER TABLE reports" + " ADD COLUMN custom_type" + " INTEGER DEFAULT 0");
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.bm$s */
    private static class C1992s extends C1985l {
        private C1992s() {
        }

        /* synthetic */ C1992s(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo17261a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS preferences (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
            sQLiteDatabase.execSQL("ALTER TABLE reports" + " ADD COLUMN wifi_access_point" + " TEXT ");
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.bm$c */
    static class C1976c extends C1985l {
        C1976c() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo17261a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL(C1998y.f3261b);
            sQLiteDatabase.execSQL(C1999z.f3263b);
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS preferences (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.bm$d */
    static class C1977d extends C1985l {
        C1977d() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo17261a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS reports");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS sessions");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS preferences");
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.bm$e */
    static class C1978e extends C1985l {
        C1978e() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo17261a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS preferences (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS startup (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS permissions (name TEXT PRIMARY KEY,granted INTEGER)");
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.bm$f */
    static class C1979f extends C1985l {
        C1979f() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo17261a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS device_id_info");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS api_level_info");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS preferences");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS startup");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS permissions");
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.bm$a */
    private static class C1973a extends C1985l {
        private C1973a() {
        }

        /* synthetic */ C1973a(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo17261a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS preferences (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.bm$b */
    private static class C1975b extends C1985l {
        private C1975b() {
        }

        /* synthetic */ C1975b(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo17261a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS preferences");
        }
    }
}
