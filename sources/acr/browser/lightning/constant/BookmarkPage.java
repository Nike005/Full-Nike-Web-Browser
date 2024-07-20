package acr.browser.lightning.constant;

import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.database.HistoryItem;
import acr.browser.lightning.database.bookmark.BookmarkModel;
import acr.browser.lightning.utils.C3245Utils;
import acr.browser.lightning.utils.Preconditions;
import acr.browser.lightning.utils.ThemeUtils;
import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;
import com.anthonycr.bonsai.Single;
import com.anthonycr.bonsai.SingleAction;
import com.anthonycr.bonsai.SingleOnSubscribe;
import com.anthonycr.bonsai.SingleSubscriber;
import com.wnikebrow_13999769.R;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;

public final class BookmarkPage {
    private static final String END = "</div></body></html>";
    public static final String FILENAME = "bookmarks.html";
    private static final String FOLDER_ICON = "folder.png";
    private static final String HEADING_1 = "<!DOCTYPE html><html xmlns=http://www.w3.org/1999/xhtml>\n<head>\n<meta content=en-us http-equiv=Content-Language />\n<meta content='text/html; charset=utf-8' http-equiv=Content-Type />\n<meta name=viewport content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no'>\n<title>";
    private static final String HEADING_2 = "</title>\n</head>\n<style>body{background: #E5E5E5; padding-top: 5px;max-width:100%;min-height:100%}#content{width:100%;max-width:800px;margin:0 auto;text-align:center}.box{vertical-align:middle;text-align:center;position:relative;display:inline-block;height:45px;width:150px;margin:6px;padding:4px;background-color:#fff;border: 1px solid #d2d2d2;border-top-width: 0;border-bottom-width: 2px;font-family:Arial;color:#444;font-size:12px;-moz-border-radius:2px;-webkit-border-radius:2px;border-radius:2px}.box-content{height:25px;width:100%;vertical-align:middle;text-align:center;display:table-cell}p.ellipses{width:130px;font-size: small;font-family: Arial, Helvetica, 'sans-serif';white-space:nowrap;overflow:hidden;text-align:left;vertical-align:middle;margin:auto;text-overflow:ellipsis;-o-text-overflow:ellipsis;-ms-text-overflow:ellipsis}.box a{width:100%;height:100%;position:absolute;left:0;top:0}img{vertical-align:middle;margin-right:10px;width:20px;height:20px;}.margin{margin:10px}</style>\n<body><div id=content>";
    private static final String PART1 = "<div class=box><a href='";
    private static final String PART2 = "'></a>\n<div class=margin>\n<div class=box-content>\n<p class=ellipses>\n<img src='";
    private static final String PART3 = "https://www.google.com/s2/favicons?domain=";
    private static final String PART4 = "' />";
    private static final String PART5 = "</p></div></div></div>";
    @Inject
    Application mApp;
    /* access modifiers changed from: private */
    public File mCacheDir;
    /* access modifiers changed from: private */
    public File mFilesDir;
    private final Bitmap mFolderIcon;
    @Inject
    BookmarkModel mManager;
    /* access modifiers changed from: private */
    public final String mTitle = this.mApp.getString(R.string.action_bookmarks);

    public BookmarkPage(Activity activity) {
        BrowserApp.getAppComponent().inject(this);
        this.mFolderIcon = ThemeUtils.getThemedBitmap(activity, R.drawable.ic_folder, false);
    }

    public Single<String> getBookmarkPage() {
        return Single.create(new SingleAction<String>() {
            public void onSubscribe(SingleSubscriber<String> singleSubscriber) {
                BookmarkPage bookmarkPage = BookmarkPage.this;
                File unused = bookmarkPage.mCacheDir = bookmarkPage.mApp.getCacheDir();
                BookmarkPage bookmarkPage2 = BookmarkPage.this;
                File unused2 = bookmarkPage2.mFilesDir = bookmarkPage2.mApp.getFilesDir();
                BookmarkPage.this.cacheDefaultFolderIcon();
                BookmarkPage.this.buildBookmarkPage((String) null);
                File file = new File(BookmarkPage.this.mFilesDir, BookmarkPage.FILENAME);
                singleSubscriber.onItem(Constants.FILE + file);
                singleSubscriber.onComplete();
            }
        });
    }

    /* access modifiers changed from: private */
    public void cacheDefaultFolderIcon() {
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(new File(this.mCacheDir, FOLDER_ICON));
            try {
                this.mFolderIcon.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream2);
                this.mFolderIcon.recycle();
                C3245Utils.close((Closeable) fileOutputStream2);
            } catch (FileNotFoundException e) {
                e = e;
                fileOutputStream = fileOutputStream2;
                try {
                    e.printStackTrace();
                    C3245Utils.close((Closeable) fileOutputStream);
                } catch (Throwable th) {
                    th = th;
                    C3245Utils.close((Closeable) fileOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = fileOutputStream2;
                C3245Utils.close((Closeable) fileOutputStream);
                throw th;
            }
        } catch (FileNotFoundException e2) {
            e = e2;
            e.printStackTrace();
            C3245Utils.close((Closeable) fileOutputStream);
        }
    }

    /* access modifiers changed from: private */
    public void buildBookmarkPage(final String str) {
        this.mManager.getBookmarksFromFolderSorted(str).subscribe(new SingleOnSubscribe<List<HistoryItem>>() {
            public void onItem(List<HistoryItem> list) {
                File file;
                Preconditions.checkNonNull(list);
                String str = str;
                if (str == null || str.isEmpty()) {
                    file = new File(BookmarkPage.this.mFilesDir, BookmarkPage.FILENAME);
                } else {
                    file = new File(BookmarkPage.this.mFilesDir, str + '-' + BookmarkPage.FILENAME);
                }
                StringBuilder sb = new StringBuilder(BookmarkPage.HEADING_1 + BookmarkPage.this.mTitle + BookmarkPage.HEADING_2);
                String str2 = Constants.FILE + BookmarkPage.this.mCacheDir + '/' + BookmarkPage.FOLDER_ICON;
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    HistoryItem historyItem = list.get(i);
                    sb.append(BookmarkPage.PART1);
                    if (historyItem.isFolder()) {
                        File file2 = new File(BookmarkPage.this.mFilesDir, historyItem.getTitle() + '-' + BookmarkPage.FILENAME);
                        sb.append(Constants.FILE);
                        sb.append(file2);
                        sb.append(BookmarkPage.PART2);
                        sb.append(str2);
                        BookmarkPage.this.buildBookmarkPage(historyItem.getTitle());
                    } else {
                        sb.append(historyItem.getUrl());
                        sb.append(BookmarkPage.PART2);
                        sb.append(BookmarkPage.PART3);
                        sb.append(historyItem.getUrl());
                    }
                    sb.append(BookmarkPage.PART4);
                    sb.append(historyItem.getTitle());
                    sb.append(BookmarkPage.PART5);
                }
                sb.append(BookmarkPage.END);
                FileWriter fileWriter = null;
                try {
                    FileWriter fileWriter2 = new FileWriter(file, false);
                    try {
                        fileWriter2.write(sb.toString());
                        C3245Utils.close((Closeable) fileWriter2);
                    } catch (IOException e) {
                        e = e;
                        fileWriter = fileWriter2;
                        try {
                            e.printStackTrace();
                            C3245Utils.close((Closeable) fileWriter);
                        } catch (Throwable th) {
                            th = th;
                            C3245Utils.close((Closeable) fileWriter);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileWriter = fileWriter2;
                        C3245Utils.close((Closeable) fileWriter);
                        throw th;
                    }
                } catch (IOException e2) {
                    e = e2;
                    e.printStackTrace();
                    C3245Utils.close((Closeable) fileWriter);
                }
            }
        });
    }
}
