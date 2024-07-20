package acr.browser.lightning.database.bookmark;

import acr.browser.lightning.database.HistoryItem;
import acr.browser.lightning.utils.C3245Utils;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.anthonycr.bonsai.Single;
import com.anthonycr.bonsai.SingleAction;
import com.anthonycr.bonsai.SingleSubscriber;
import java.util.ArrayList;
import java.util.List;

public class BookmarkLocalSync {
    private static final String CHROME_BETA_BOOKMARKS_CONTENT = "content://com.chrome.beta.browser/bookmarks";
    private static final String CHROME_BOOKMARKS_CONTENT = "content://com.android.chrome.browser/bookmarks";
    private static final String CHROME_DEV_BOOKMARKS_CONTENT = "content://com.chrome.dev.browser/bookmarks";
    private static final String COLUMN_BOOKMARK = "bookmark";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_URL = "url";
    private static final String STOCK_BOOKMARKS_CONTENT = "content://browser/bookmarks";
    private static final String TAG = "BookmarkLocalSync";
    private final Context mContext;

    public enum Source {
        STOCK,
        CHROME_STABLE,
        CHROME_BETA,
        CHROME_DEV
    }

    public BookmarkLocalSync(Context context) {
        this.mContext = context;
    }

    private List<HistoryItem> getBookmarksFromContentUri(String str) {
        ArrayList arrayList = new ArrayList();
        Cursor browserCursor = getBrowserCursor(str);
        if (browserCursor != null) {
            int i = 0;
            while (i < browserCursor.getColumnCount()) {
                try {
                    Log.d(TAG, browserCursor.getColumnName(i));
                    i++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            while (browserCursor.moveToNext()) {
                if (browserCursor.getInt(2) == 1) {
                    String string = browserCursor.getString(0);
                    String string2 = browserCursor.getString(1);
                    if (!string.isEmpty()) {
                        if (string2 == null || string2.isEmpty()) {
                            string2 = C3245Utils.getDomainName(string);
                        }
                        if (string2 != null) {
                            arrayList.add(new HistoryItem(string, string2));
                        }
                    }
                }
            }
        }
        C3245Utils.close(browserCursor);
        return arrayList;
    }

    private Cursor getBrowserCursor(String str) {
        try {
            return this.mContext.getContentResolver().query(Uri.parse(str), new String[]{"url", "title", COLUMN_BOOKMARK}, (String) null, (String[]) null, (String) null);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public Single<List<Source>> getSupportedBrowsers() {
        return Single.create(new SingleAction<List<Source>>() {
            public void onSubscribe(SingleSubscriber<List<Source>> singleSubscriber) {
                ArrayList arrayList = new ArrayList(1);
                if (BookmarkLocalSync.this.isBrowserSupported(BookmarkLocalSync.STOCK_BOOKMARKS_CONTENT)) {
                    arrayList.add(Source.STOCK);
                }
                if (BookmarkLocalSync.this.isBrowserSupported(BookmarkLocalSync.CHROME_BOOKMARKS_CONTENT)) {
                    arrayList.add(Source.CHROME_STABLE);
                }
                if (BookmarkLocalSync.this.isBrowserSupported(BookmarkLocalSync.CHROME_BETA_BOOKMARKS_CONTENT)) {
                    arrayList.add(Source.CHROME_BETA);
                }
                if (BookmarkLocalSync.this.isBrowserSupported(BookmarkLocalSync.CHROME_DEV_BOOKMARKS_CONTENT)) {
                    arrayList.add(Source.CHROME_DEV);
                }
                singleSubscriber.onItem(arrayList);
                singleSubscriber.onComplete();
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean isBrowserSupported(String str) {
        Cursor browserCursor = getBrowserCursor(str);
        boolean z = browserCursor != null;
        C3245Utils.close(browserCursor);
        return z;
    }

    public List<HistoryItem> getBookmarksFromStockBrowser() {
        return getBookmarksFromContentUri(STOCK_BOOKMARKS_CONTENT);
    }

    public List<HistoryItem> getBookmarksFromChrome() {
        return getBookmarksFromContentUri(CHROME_BOOKMARKS_CONTENT);
    }

    public List<HistoryItem> getBookmarksFromChromeBeta() {
        return getBookmarksFromContentUri(CHROME_BETA_BOOKMARKS_CONTENT);
    }

    public List<HistoryItem> getBookmarksFromChromeDev() {
        return getBookmarksFromContentUri(CHROME_DEV_BOOKMARKS_CONTENT);
    }

    public Single<Boolean> isBrowserImportSupported() {
        return Single.create(new SingleAction<Boolean>() {
            public void onSubscribe(SingleSubscriber<Boolean> singleSubscriber) {
                Cursor access$100 = BookmarkLocalSync.this.getChromeCursor();
                C3245Utils.close(access$100);
                Cursor access$200 = BookmarkLocalSync.this.getChromeDevCursor();
                C3245Utils.close(access$200);
                Cursor access$300 = BookmarkLocalSync.this.getChromeBetaCursor();
                Cursor access$400 = BookmarkLocalSync.this.getStockCursor();
                C3245Utils.close(access$400);
                singleSubscriber.onItem(Boolean.valueOf((access$100 == null && access$200 == null && access$300 == null && access$400 == null) ? false : true));
                singleSubscriber.onComplete();
            }
        });
    }

    /* access modifiers changed from: private */
    public Cursor getChromeBetaCursor() {
        return getBrowserCursor(CHROME_BETA_BOOKMARKS_CONTENT);
    }

    /* access modifiers changed from: private */
    public Cursor getChromeDevCursor() {
        return getBrowserCursor(CHROME_DEV_BOOKMARKS_CONTENT);
    }

    /* access modifiers changed from: private */
    public Cursor getChromeCursor() {
        return getBrowserCursor(CHROME_BOOKMARKS_CONTENT);
    }

    /* access modifiers changed from: private */
    public Cursor getStockCursor() {
        return getBrowserCursor(STOCK_BOOKMARKS_CONTENT);
    }

    public void printAllColumns() {
        printColumns(CHROME_BETA_BOOKMARKS_CONTENT);
        printColumns(CHROME_BOOKMARKS_CONTENT);
        printColumns(CHROME_DEV_BOOKMARKS_CONTENT);
        printColumns(STOCK_BOOKMARKS_CONTENT);
    }

    private void printColumns(String str) {
        Cursor cursor;
        Log.e(TAG, str);
        try {
            cursor = this.mContext.getContentResolver().query(Uri.parse(str), (String[]) null, (String) null, (String[]) null, (String) null);
        } catch (Exception e) {
            Log.e(TAG, "Error Occurred", e);
            cursor = null;
        }
        if (cursor != null) {
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                Log.d(TAG, cursor.getColumnName(i));
            }
            cursor.close();
        }
    }
}
