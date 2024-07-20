package acr.browser.lightning.browser;

import acr.browser.lightning.view.LightningView;
import android.content.DialogInterface;
import android.view.View;

public interface BrowserView {
    void closeActivity();

    void closeAllTabs();

    void closeBrowser();

    void notifyTabClosed(String str, View.OnClickListener onClickListener);

    void notifyTabViewAdded();

    void notifyTabViewChanged(int i);

    void notifyTabViewInitialized();

    void notifyTabViewRemoved(int i);

    void removeTabView();

    void setBackButtonEnabled(boolean z);

    void setForwardButtonEnabled(boolean z);

    void setTabView(LightningView lightningView);

    void showBlockedLocalFileDialog(DialogInterface.OnClickListener onClickListener);

    void showInterstitialAd(String str);

    void showSnackbar(int i);

    void updateProgress(int i);

    void updateTabNumber(int i);

    void updateUrl(String str, boolean z);
}
