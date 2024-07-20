package acr.browser.lightning.database.bookmark;

import acr.browser.lightning.constant.Constants;
import acr.browser.lightning.database.HistoryItem;
import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.anthonycr.bonsai.Completable;
import com.anthonycr.bonsai.CompletableAction;
import com.anthonycr.bonsai.CompletableOnSubscribe;
import com.anthonycr.bonsai.CompletableSubscriber;
import com.anthonycr.bonsai.Schedulers;
import com.anthonycr.bonsai.Single;
import com.anthonycr.bonsai.SingleAction;
import com.anthonycr.bonsai.SingleSubscriber;
import com.wnikebrow_13999769.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BookmarkDatabase extends SQLiteOpenHelper implements BookmarkModel {
    private static final String DATABASE_NAME = "bookmarkManager";
    private static final int DATABASE_VERSION = 1;
    private static final String KEY_DELETED = "deleted";
    private static final String KEY_FOLDER = "folder";
    private static final String KEY_ID = "id";
    private static final String KEY_IMAGE_URL = "image_url";
    private static final String KEY_MAIN_SCREEN = "main_screen";
    private static final String KEY_POSITION = "position";
    private static final String KEY_TITLE = "title";
    private static final String KEY_URL = "url";
    private static final String TABLE_BOOKMARK = "bookmark";
    private static final String TAG = "BookmarkDatabase";
    /* access modifiers changed from: private */
    public final String DEFAULT_BOOKMARK_TITLE;
    private SQLiteDatabase mDatabase;

    @Inject
    public BookmarkDatabase(Application application) {
        super(application, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
        this.DEFAULT_BOOKMARK_TITLE = application.getString(R.string.untitled);
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
        sQLiteDatabase.execSQL("CREATE TABLE " + DatabaseUtils.sqlEscapeString(TABLE_BOOKMARK) + '(' + DatabaseUtils.sqlEscapeString("id") + " INTEGER PRIMARY KEY," + DatabaseUtils.sqlEscapeString("url") + " TEXT," + DatabaseUtils.sqlEscapeString("title") + " TEXT," + DatabaseUtils.sqlEscapeString(KEY_FOLDER) + " TEXT," + DatabaseUtils.sqlEscapeString(KEY_MAIN_SCREEN) + " INTEGER," + DatabaseUtils.sqlEscapeString(KEY_DELETED) + " INTEGER," + DatabaseUtils.sqlEscapeString(KEY_IMAGE_URL) + " TEXT," + DatabaseUtils.sqlEscapeString(KEY_POSITION) + " INTEGER" + ')');
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseUtils.sqlEscapeString(TABLE_BOOKMARK));
        onCreate(sQLiteDatabase);
    }

    /* access modifiers changed from: private */
    public static ContentValues bindBookmarkToContentValues(HistoryItem historyItem) {
        ContentValues contentValues = new ContentValues(5);
        contentValues.put("title", historyItem.getTitle());
        contentValues.put("url", historyItem.getUrl());
        contentValues.put(KEY_FOLDER, historyItem.getFolder());
        contentValues.put(KEY_POSITION, Integer.valueOf(historyItem.getPosition()));
        contentValues.put(KEY_MAIN_SCREEN, Integer.valueOf(historyItem.isShowOnMainScreen() ? 1 : 0));
        contentValues.put(KEY_DELETED, Integer.valueOf(historyItem.isDeleted() ? 1 : 0));
        contentValues.put(KEY_IMAGE_URL, historyItem.getImageUrl());
        return contentValues;
    }

    /* access modifiers changed from: private */
    public static HistoryItem bindCursorToHistoryItem(Cursor cursor) {
        HistoryItem historyItem = new HistoryItem();
        historyItem.setImageId(R.drawable.ic_bookmark);
        historyItem.setUrl(cursor.getString(cursor.getColumnIndex("url")));
        historyItem.setTitle(cursor.getString(cursor.getColumnIndex("title")));
        historyItem.setFolder(cursor.getString(cursor.getColumnIndex(KEY_FOLDER)));
        historyItem.setPosition(cursor.getInt(cursor.getColumnIndex(KEY_POSITION)));
        boolean z = false;
        historyItem.setShowOnMainScreen(cursor.getInt(cursor.getColumnIndex(KEY_MAIN_SCREEN)) == 1);
        if (cursor.getInt(cursor.getColumnIndex(KEY_DELETED)) == 1) {
            z = true;
        }
        historyItem.setDeleted(z);
        historyItem.setImageUrl(cursor.getString(cursor.getColumnIndex(KEY_IMAGE_URL)));
        return historyItem;
    }

    /* access modifiers changed from: private */
    public static List<HistoryItem> bindCursorToHistoryItemList(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            arrayList.add(bindCursorToHistoryItem(cursor));
        }
        cursor.close();
        return arrayList;
    }

    public Single<HistoryItem> findBookmarkForUrl(final String str) {
        return Single.create(new SingleAction<HistoryItem>() {
            public void onSubscribe(SingleSubscriber<HistoryItem> singleSubscriber) {
                Cursor query = BookmarkDatabase.this.lazyDatabase().query(BookmarkDatabase.TABLE_BOOKMARK, (String[]) null, "url=?", new String[]{str}, (String) null, (String) null, (String) null, "1");
                if (query.moveToFirst()) {
                    singleSubscriber.onItem(BookmarkDatabase.bindCursorToHistoryItem(query));
                } else {
                    singleSubscriber.onItem(null);
                }
                query.close();
                singleSubscriber.onComplete();
            }
        });
    }

    public Single<Boolean> isBookmark(final String str) {
        return Single.create(new SingleAction<Boolean>() {
            public void onSubscribe(SingleSubscriber<Boolean> singleSubscriber) {
                Cursor query = BookmarkDatabase.this.lazyDatabase().query(BookmarkDatabase.TABLE_BOOKMARK, (String[]) null, "url=? and deleted=?", new String[]{str, "0"}, (String) null, (String) null, (String) null, "1");
                singleSubscriber.onItem(Boolean.valueOf(query.moveToFirst()));
                query.close();
                singleSubscriber.onComplete();
            }
        });
    }

    public Single<Boolean> addBookmarkIfNotExists(final HistoryItem historyItem, final boolean z) {
        return Single.create(new SingleAction<Boolean>() {
            public void onSubscribe(final SingleSubscriber<Boolean> singleSubscriber) {
                boolean z = true;
                Cursor query = BookmarkDatabase.this.lazyDatabase().query(BookmarkDatabase.TABLE_BOOKMARK, (String[]) null, "url=?", new String[]{historyItem.getUrl()}, (String) null, (String) null, (String) null, "1");
                if (query.moveToFirst()) {
                    if (z) {
                        HistoryItem access$100 = BookmarkDatabase.bindCursorToHistoryItem(query);
                        access$100.setDeleted(false);
                        BookmarkDatabase.this.editBookmark(access$100, access$100).observeOn(Schedulers.m6232io()).subscribe(new CompletableOnSubscribe() {
                            public void onComplete() {
                                singleSubscriber.onItem(true);
                                singleSubscriber.onComplete();
                            }
                        });
                    } else {
                        singleSubscriber.onItem(false);
                        singleSubscriber.onComplete();
                    }
                    query.close();
                    return;
                }
                query.close();
                if (BookmarkDatabase.this.lazyDatabase().insert(BookmarkDatabase.TABLE_BOOKMARK, (String) null, BookmarkDatabase.bindBookmarkToContentValues(historyItem)) == -1) {
                    z = false;
                }
                singleSubscriber.onItem(Boolean.valueOf(z));
                singleSubscriber.onComplete();
            }
        });
    }

    public Completable addBookmarkList(final List<HistoryItem> list) {
        return Completable.create(new CompletableAction() {
            public void onSubscribe(CompletableSubscriber completableSubscriber) {
                BookmarkDatabase.this.lazyDatabase().beginTransaction();
                for (HistoryItem addBookmarkIfNotExists : list) {
                    BookmarkDatabase.this.addBookmarkIfNotExists(addBookmarkIfNotExists, true).subscribe();
                }
                BookmarkDatabase.this.lazyDatabase().setTransactionSuccessful();
                BookmarkDatabase.this.lazyDatabase().endTransaction();
                completableSubscriber.onComplete();
            }
        });
    }

    public Single<Boolean> deleteBookmark(final HistoryItem historyItem) {
        return Single.create(new SingleAction<Boolean>() {
            public void onSubscribe(final SingleSubscriber<Boolean> singleSubscriber) {
                historyItem.setDeleted(true);
                BookmarkDatabase bookmarkDatabase = BookmarkDatabase.this;
                HistoryItem historyItem = historyItem;
                bookmarkDatabase.editBookmark(historyItem, historyItem).observeOn(Schedulers.m6232io()).subscribe(new CompletableOnSubscribe() {
                    public void onComplete() {
                        singleSubscriber.onItem(true);
                        singleSubscriber.onComplete();
                    }
                });
            }
        });
    }

    public Completable renameFolder(final String str, final String str2) {
        return Completable.create(new CompletableAction() {
            public void onSubscribe(CompletableSubscriber completableSubscriber) {
                ContentValues contentValues = new ContentValues(1);
                contentValues.put(BookmarkDatabase.KEY_FOLDER, str2);
                BookmarkDatabase.this.lazyDatabase().update(BookmarkDatabase.TABLE_BOOKMARK, contentValues, "folder=?", new String[]{str});
                completableSubscriber.onComplete();
            }
        });
    }

    public Completable deleteFolder(final String str) {
        return Completable.create(new CompletableAction() {
            public void onSubscribe(CompletableSubscriber completableSubscriber) {
                BookmarkDatabase.this.renameFolder(str, "").subscribe();
                completableSubscriber.onComplete();
            }
        });
    }

    public Completable deleteAllBookmarks() {
        return Completable.create(new CompletableAction() {
            public void onSubscribe(CompletableSubscriber completableSubscriber) {
                BookmarkDatabase.this.lazyDatabase().delete(BookmarkDatabase.TABLE_BOOKMARK, (String) null, (String[]) null);
                completableSubscriber.onComplete();
            }
        });
    }

    public Completable editBookmark(final HistoryItem historyItem, final HistoryItem historyItem2) {
        return Completable.create(new CompletableAction() {
            public void onSubscribe(CompletableSubscriber completableSubscriber) {
                if (historyItem2.getTitle().isEmpty()) {
                    historyItem2.setTitle(BookmarkDatabase.this.DEFAULT_BOOKMARK_TITLE);
                }
                ContentValues access$200 = BookmarkDatabase.bindBookmarkToContentValues(historyItem2);
                BookmarkDatabase.this.lazyDatabase().update(BookmarkDatabase.TABLE_BOOKMARK, access$200, "url=?", new String[]{historyItem.getUrl()});
                completableSubscriber.onComplete();
            }
        });
    }

    public Single<List<HistoryItem>> getAllBookmarks() {
        return Single.create(new SingleAction<List<HistoryItem>>() {
            public void onSubscribe(SingleSubscriber<List<HistoryItem>> singleSubscriber) {
                singleSubscriber.onItem(BookmarkDatabase.bindCursorToHistoryItemList(BookmarkDatabase.this.lazyDatabase().query(BookmarkDatabase.TABLE_BOOKMARK, (String[]) null, "deleted=?", new String[]{"0"}, (String) null, (String) null, (String) null)));
                singleSubscriber.onComplete();
            }
        });
    }

    public Single<List<HistoryItem>> getBookmarksFromFolderSorted(final String str) {
        return Single.create(new SingleAction<List<HistoryItem>>() {
            public void onSubscribe(SingleSubscriber<List<HistoryItem>> singleSubscriber) {
                String str = str;
                if (str == null) {
                    str = "";
                }
                List access$400 = BookmarkDatabase.bindCursorToHistoryItemList(BookmarkDatabase.this.lazyDatabase().query(BookmarkDatabase.TABLE_BOOKMARK, (String[]) null, "folder=? and deleted=?", new String[]{str, "0"}, (String) null, (String) null, (String) null));
                Collections.sort(access$400);
                singleSubscriber.onItem(access$400);
                singleSubscriber.onComplete();
            }
        });
    }

    public Single<List<HistoryItem>> getBookmarksForMainScreen() {
        return Single.create(new SingleAction<List<HistoryItem>>() {
            public void onSubscribe(SingleSubscriber<List<HistoryItem>> singleSubscriber) {
                List access$400 = BookmarkDatabase.bindCursorToHistoryItemList(BookmarkDatabase.this.lazyDatabase().query(BookmarkDatabase.TABLE_BOOKMARK, (String[]) null, "main_screen=? and deleted=?", new String[]{"1", "0"}, (String) null, (String) null, (String) null));
                Collections.sort(access$400, new Comparator<HistoryItem>() {
                    public int compare(HistoryItem historyItem, HistoryItem historyItem2) {
                        return Integer.valueOf(historyItem.getPosition()).compareTo(Integer.valueOf(historyItem2.getPosition()));
                    }
                });
                singleSubscriber.onItem(access$400);
                singleSubscriber.onComplete();
            }
        });
    }

    public Single<List<HistoryItem>> getFoldersSorted() {
        return Single.create(new SingleAction<List<HistoryItem>>() {
            public void onSubscribe(SingleSubscriber<List<HistoryItem>> singleSubscriber) {
                Cursor query = BookmarkDatabase.this.lazyDatabase().query(true, BookmarkDatabase.TABLE_BOOKMARK, new String[]{BookmarkDatabase.KEY_FOLDER}, (String) null, (String[]) null, (String) null, (String) null, (String) null, (String) null);
                ArrayList arrayList = new ArrayList();
                while (query.moveToNext()) {
                    String string = query.getString(query.getColumnIndex(BookmarkDatabase.KEY_FOLDER));
                    if (!TextUtils.isEmpty(string)) {
                        HistoryItem historyItem = new HistoryItem();
                        historyItem.setIsFolder(true);
                        historyItem.setTitle(string);
                        historyItem.setImageId(R.drawable.ic_folder);
                        historyItem.setUrl(Constants.FOLDER + string);
                        arrayList.add(historyItem);
                    }
                }
                query.close();
                Collections.sort(arrayList);
                singleSubscriber.onItem(arrayList);
                singleSubscriber.onComplete();
            }
        });
    }

    public Single<List<String>> getFolderNames() {
        return Single.create(new SingleAction<List<String>>() {
            public void onSubscribe(SingleSubscriber<List<String>> singleSubscriber) {
                Cursor query = BookmarkDatabase.this.lazyDatabase().query(true, BookmarkDatabase.TABLE_BOOKMARK, new String[]{BookmarkDatabase.KEY_FOLDER}, (String) null, (String[]) null, (String) null, (String) null, (String) null, (String) null);
                ArrayList arrayList = new ArrayList();
                while (query.moveToNext()) {
                    String string = query.getString(query.getColumnIndex(BookmarkDatabase.KEY_FOLDER));
                    if (!TextUtils.isEmpty(string)) {
                        arrayList.add(string);
                    }
                }
                query.close();
                singleSubscriber.onItem(arrayList);
                singleSubscriber.onComplete();
            }
        });
    }

    public long count() {
        return DatabaseUtils.queryNumEntries(lazyDatabase(), TABLE_BOOKMARK);
    }
}
