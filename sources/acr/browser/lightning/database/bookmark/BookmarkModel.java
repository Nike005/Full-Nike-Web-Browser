package acr.browser.lightning.database.bookmark;

import acr.browser.lightning.database.HistoryItem;
import com.anthonycr.bonsai.Completable;
import com.anthonycr.bonsai.Single;
import java.util.List;

public interface BookmarkModel {
    Single<Boolean> addBookmarkIfNotExists(HistoryItem historyItem, boolean z);

    Completable addBookmarkList(List<HistoryItem> list);

    long count();

    Completable deleteAllBookmarks();

    Single<Boolean> deleteBookmark(HistoryItem historyItem);

    Completable deleteFolder(String str);

    Completable editBookmark(HistoryItem historyItem, HistoryItem historyItem2);

    Single<HistoryItem> findBookmarkForUrl(String str);

    Single<List<HistoryItem>> getAllBookmarks();

    Single<List<HistoryItem>> getBookmarksForMainScreen();

    Single<List<HistoryItem>> getBookmarksFromFolderSorted(String str);

    Single<List<String>> getFolderNames();

    Single<List<HistoryItem>> getFoldersSorted();

    Single<Boolean> isBookmark(String str);

    Completable renameFolder(String str, String str2);
}
