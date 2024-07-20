package acr.browser.lightning.database.history;

import acr.browser.lightning.database.HistoryItem;
import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.wnikebrow_13999769.R;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class HistoryDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "historyManager";
    private static final int DATABASE_VERSION = 2;
    private static final String KEY_ID = "id";
    private static final String KEY_TIME_VISITED = "time";
    private static final String KEY_TITLE = "title";
    private static final String KEY_URL = "url";
    private static final String TABLE_HISTORY = "history";
    private SQLiteDatabase mDatabase = getWritableDatabase();

    @Inject
    HistoryDatabase(Application application) {
        super(application, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 2);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE history(id INTEGER PRIMARY KEY,url TEXT,title TEXT,time INTEGER)");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS history");
        onCreate(sQLiteDatabase);
    }

    private static HistoryItem fromCursor(Cursor cursor) {
        HistoryItem historyItem = new HistoryItem();
        historyItem.setUrl(cursor.getString(1));
        historyItem.setTitle(cursor.getString(2));
        historyItem.setImageId(R.drawable.ic_history);
        return historyItem;
    }

    private SQLiteDatabase lazyDatabase() {
        SQLiteDatabase sQLiteDatabase = this.mDatabase;
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            this.mDatabase = getWritableDatabase();
        }
        return this.mDatabase;
    }

    /* access modifiers changed from: package-private */
    public synchronized void deleteHistory() {
        lazyDatabase().delete(TABLE_HISTORY, (String) null, (String[]) null);
        lazyDatabase().close();
    }

    /* access modifiers changed from: package-private */
    public synchronized void deleteHistoryItem(String str) {
        lazyDatabase().delete(TABLE_HISTORY, "url = ?", new String[]{str});
    }

    /* access modifiers changed from: package-private */
    public synchronized void visitHistoryItem(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", str2 == null ? "" : str2);
        contentValues.put("time", Long.valueOf(System.currentTimeMillis()));
        Cursor query = lazyDatabase().query(false, TABLE_HISTORY, new String[]{"url"}, "url = ?", new String[]{str}, (String) null, (String) null, (String) null, "1");
        if (query.getCount() > 0) {
            lazyDatabase().update(TABLE_HISTORY, contentValues, "url = ?", new String[]{str});
        } else {
            if (str2 == null) {
                str2 = "";
            }
            addHistoryItem(new HistoryItem(str, str2));
        }
        query.close();
    }

    private synchronized void addHistoryItem(HistoryItem historyItem) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("url", historyItem.getUrl());
        contentValues.put("title", historyItem.getTitle());
        contentValues.put("time", Long.valueOf(System.currentTimeMillis()));
        lazyDatabase().insert(TABLE_HISTORY, (String) null, contentValues);
    }

    /* access modifiers changed from: package-private */
    public synchronized String getHistoryItem(String str) {
        String str2;
        Cursor query = lazyDatabase().query(TABLE_HISTORY, new String[]{"id", "url", "title"}, "url = ?", new String[]{str}, (String) null, (String) null, (String) null, "1");
        str2 = null;
        if (query != null) {
            query.moveToFirst();
            str2 = query.getString(0);
            query.close();
        }
        return str2;
    }

    /* access modifiers changed from: package-private */
    public synchronized List<HistoryItem> findItemsContaining(String str) {
        ArrayList arrayList = new ArrayList(5);
        if (str == null) {
            return arrayList;
        }
        String str2 = '%' + str + '%';
        Cursor query = lazyDatabase().query(TABLE_HISTORY, (String[]) null, "title LIKE ? OR url LIKE ?", new String[]{str2, str2}, (String) null, (String) null, "time DESC", "5");
        while (query.moveToNext()) {
            arrayList.add(fromCursor(query));
        }
        query.close();
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public synchronized List<HistoryItem> getLastHundredItems() {
        ArrayList arrayList;
        arrayList = new ArrayList(100);
        Cursor query = lazyDatabase().query(TABLE_HISTORY, (String[]) null, (String) null, (String[]) null, (String) null, (String) null, "time DESC", "100");
        while (query.moveToNext()) {
            arrayList.add(fromCursor(query));
        }
        query.close();
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public synchronized List<HistoryItem> getAllHistoryItems() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Cursor query = lazyDatabase().query(TABLE_HISTORY, (String[]) null, (String) null, (String[]) null, (String) null, (String) null, "time DESC");
        while (query.moveToNext()) {
            arrayList.add(fromCursor(query));
        }
        query.close();
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public synchronized long getHistoryItemsCount() {
        return DatabaseUtils.queryNumEntries(this.mDatabase, TABLE_HISTORY);
    }
}
