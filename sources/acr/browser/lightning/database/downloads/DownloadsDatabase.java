package acr.browser.lightning.database.downloads;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.anthonycr.bonsai.Completable;
import com.anthonycr.bonsai.CompletableAction;
import com.anthonycr.bonsai.CompletableSubscriber;
import com.anthonycr.bonsai.Single;
import com.anthonycr.bonsai.SingleAction;
import com.anthonycr.bonsai.SingleSubscriber;
import com.wnikebrow_13999769.R;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DownloadsDatabase extends SQLiteOpenHelper implements DownloadsModel {
    private static final String DATABASE_NAME = "downloadManager";
    private static final int DATABASE_VERSION = 1;
    private static final String KEY_ID = "id";
    private static final String KEY_SIZE = "size";
    private static final String KEY_TITLE = "title";
    private static final String KEY_URL = "url";
    private static final String TABLE_DOWNLOADS = "download";
    private static final String TAG = "DownloadsDatabase";
    private final String DEFAULT_DOWNLOADS_TITLE;
    private SQLiteDatabase mDatabase;

    @Inject
    public DownloadsDatabase(Application application) {
        super(application, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
        this.DEFAULT_DOWNLOADS_TITLE = application.getString(R.string.untitled);
    }

    /* access modifiers changed from: private */
    public SQLiteDatabase lazyDatabase() {
        SQLiteDatabase sQLiteDatabase = this.mDatabase;
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            this.mDatabase = getWritableDatabase();
        }
        return this.mDatabase;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE " + DatabaseUtils.sqlEscapeString(TABLE_DOWNLOADS) + '(' + DatabaseUtils.sqlEscapeString("id") + " INTEGER PRIMARY KEY," + DatabaseUtils.sqlEscapeString("url") + " TEXT," + DatabaseUtils.sqlEscapeString("title") + " TEXT," + DatabaseUtils.sqlEscapeString(KEY_SIZE) + " TEXT" + ')');
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseUtils.sqlEscapeString(TABLE_DOWNLOADS));
        onCreate(sQLiteDatabase);
    }

    /* access modifiers changed from: private */
    public static ContentValues bindBookmarkToContentValues(DownloadItem downloadItem) {
        ContentValues contentValues = new ContentValues(3);
        contentValues.put("title", downloadItem.getTitle());
        contentValues.put("url", downloadItem.getUrl());
        contentValues.put(KEY_SIZE, downloadItem.getContentSize());
        return contentValues;
    }

    /* access modifiers changed from: private */
    public static DownloadItem bindCursorToDownloadItem(Cursor cursor) {
        DownloadItem downloadItem = new DownloadItem();
        downloadItem.setUrl(cursor.getString(cursor.getColumnIndex("url")));
        downloadItem.setTitle(cursor.getString(cursor.getColumnIndex("title")));
        downloadItem.setContentSize(cursor.getString(cursor.getColumnIndex(KEY_SIZE)));
        return downloadItem;
    }

    /* access modifiers changed from: private */
    public static List<DownloadItem> bindCursorToDownloadItemList(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            arrayList.add(bindCursorToDownloadItem(cursor));
        }
        cursor.close();
        return arrayList;
    }

    public Single<DownloadItem> findDownloadForUrl(final String str) {
        return Single.create(new SingleAction<DownloadItem>() {
            public void onSubscribe(SingleSubscriber<DownloadItem> singleSubscriber) {
                Cursor query = DownloadsDatabase.this.lazyDatabase().query(DownloadsDatabase.TABLE_DOWNLOADS, (String[]) null, "url=?", new String[]{str}, (String) null, (String) null, "1");
                if (query.moveToFirst()) {
                    singleSubscriber.onItem(DownloadsDatabase.bindCursorToDownloadItem(query));
                } else {
                    singleSubscriber.onItem(null);
                }
                query.close();
                singleSubscriber.onComplete();
            }
        });
    }

    public Single<Boolean> isDownload(final String str) {
        return Single.create(new SingleAction<Boolean>() {
            public void onSubscribe(SingleSubscriber<Boolean> singleSubscriber) {
                Cursor query = DownloadsDatabase.this.lazyDatabase().query(DownloadsDatabase.TABLE_DOWNLOADS, (String[]) null, "url=?", new String[]{str}, (String) null, (String) null, (String) null, "1");
                singleSubscriber.onItem(Boolean.valueOf(query.moveToFirst()));
                query.close();
                singleSubscriber.onComplete();
            }
        });
    }

    public Single<Boolean> addDownloadIfNotExists(final DownloadItem downloadItem) {
        return Single.create(new SingleAction<Boolean>() {
            public void onSubscribe(SingleSubscriber<Boolean> singleSubscriber) {
                boolean z = true;
                Cursor query = DownloadsDatabase.this.lazyDatabase().query(DownloadsDatabase.TABLE_DOWNLOADS, (String[]) null, "url=?", new String[]{downloadItem.getUrl()}, (String) null, (String) null, "1");
                if (query.moveToFirst()) {
                    query.close();
                    singleSubscriber.onItem(false);
                    singleSubscriber.onComplete();
                    return;
                }
                query.close();
                if (DownloadsDatabase.this.lazyDatabase().insert(DownloadsDatabase.TABLE_DOWNLOADS, (String) null, DownloadsDatabase.bindBookmarkToContentValues(downloadItem)) == -1) {
                    z = false;
                }
                singleSubscriber.onItem(Boolean.valueOf(z));
                singleSubscriber.onComplete();
            }
        });
    }

    public Completable addDownloadsList(final List<DownloadItem> list) {
        return Completable.create(new CompletableAction() {
            public void onSubscribe(CompletableSubscriber completableSubscriber) {
                DownloadsDatabase.this.lazyDatabase().beginTransaction();
                for (DownloadItem addDownloadIfNotExists : list) {
                    DownloadsDatabase.this.addDownloadIfNotExists(addDownloadIfNotExists).subscribe();
                }
                DownloadsDatabase.this.lazyDatabase().setTransactionSuccessful();
                DownloadsDatabase.this.lazyDatabase().endTransaction();
                completableSubscriber.onComplete();
            }
        });
    }

    public Single<Boolean> deleteDownload(final String str) {
        return Single.create(new SingleAction<Boolean>() {
            public void onSubscribe(SingleSubscriber<Boolean> singleSubscriber) {
                boolean z = true;
                if (DownloadsDatabase.this.lazyDatabase().delete(DownloadsDatabase.TABLE_DOWNLOADS, "url=?", new String[]{str}) <= 0) {
                    z = false;
                }
                singleSubscriber.onItem(Boolean.valueOf(z));
                singleSubscriber.onComplete();
            }
        });
    }

    public Completable deleteAllDownloads() {
        return Completable.create(new CompletableAction() {
            public void onSubscribe(CompletableSubscriber completableSubscriber) {
                DownloadsDatabase.this.lazyDatabase().delete(DownloadsDatabase.TABLE_DOWNLOADS, (String) null, (String[]) null);
                completableSubscriber.onComplete();
            }
        });
    }

    public Single<List<DownloadItem>> getAllDownloads() {
        return Single.create(new SingleAction<List<DownloadItem>>() {
            public void onSubscribe(SingleSubscriber<List<DownloadItem>> singleSubscriber) {
                singleSubscriber.onItem(DownloadsDatabase.bindCursorToDownloadItemList(DownloadsDatabase.this.lazyDatabase().query(DownloadsDatabase.TABLE_DOWNLOADS, (String[]) null, (String) null, (String[]) null, (String) null, (String) null, (String) null)));
                singleSubscriber.onComplete();
            }
        });
    }

    public long count() {
        return DatabaseUtils.queryNumEntries(lazyDatabase(), TABLE_DOWNLOADS);
    }
}
