package acr.browser.lightning.controller;

import acr.browser.lightning.activity.TabsManager;
import acr.browser.lightning.database.HistoryItem;
import acr.browser.lightning.dialog.LightningDialogBuilder;
import acr.browser.lightning.view.LightningView;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Message;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;

public interface UIController {
    void bookmarkButtonClicked();

    void bookmarkItemClicked(HistoryItem historyItem);

    void changeToolbarBackground(Bitmap bitmap, Drawable drawable);

    void closeBookmarksDrawer();

    void closeEmptyTab();

    TabsManager getTabModel();

    int getThemeNum();

    int getUiColor();

    void handleBookmarkDeleted(HistoryItem historyItem);

    void handleBookmarksChange();

    void handleDownloadDeleted();

    void handleHistoryChange();

    void handleNewTab(LightningDialogBuilder.NewTab newTab, String str);

    void hideActionBar();

    void loadUrl(String str);

    void newTabButtonClicked();

    void newTabButtonLongClicked();

    void onBackButtonPressed();

    void onCloseWindow(LightningView lightningView);

    void onCreateWindow(Message message);

    void onForwardButtonPressed();

    void onHideCustomView();

    void onHomeButtonPressed();

    void onPageLoadFinish();

    void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback);

    void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback, int i);

    void openFileChooser(ValueCallback<Uri> valueCallback);

    void setBackButtonEnabled(boolean z);

    void setForwardButtonEnabled(boolean z);

    void showActionBar();

    void showCloseDialog(int i);

    void showFileChooser(ValueCallback<Uri[]> valueCallback);

    void tabChanged(LightningView lightningView);

    void tabClicked(int i);

    void tabCloseClicked(int i);

    void updateBookmarks();

    void updateHistory(String str, String str2);

    void updateProgress(int i);

    void updateUrl(String str, boolean z);
}
