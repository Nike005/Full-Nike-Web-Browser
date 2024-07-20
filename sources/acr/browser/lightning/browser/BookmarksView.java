package acr.browser.lightning.browser;

import acr.browser.lightning.database.HistoryItem;

public interface BookmarksView {
    void handleBookmarkDeleted(HistoryItem historyItem);

    void handleUpdatedUrl(String str);

    void navigateBack();
}
