package acr.browser.lightning.browser;

import acr.browser.lightning.BuildConfig;
import acr.browser.lightning.activity.TabsManager;
import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.constant.Constants;
import acr.browser.lightning.controller.UIController;
import acr.browser.lightning.preference.PreferenceManager;
import acr.browser.lightning.utils.UrlUtils;
import acr.browser.lightning.view.LightningView;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.anthonycr.bonsai.CompletableOnSubscribe;
import com.anthonycr.bonsai.Schedulers;
import com.wnikebrow_13999769.R;
import javax.inject.Inject;

public class BrowserPresenter {
    private static final String TAG = "BrowserPresenter";
    private LightningView mCurrentTab;
    /* access modifiers changed from: private */
    public final boolean mIsIncognito;
    @Inject
    PreferenceManager mPreferences;
    /* access modifiers changed from: private */
    public boolean mShouldClose;
    /* access modifiers changed from: private */
    public final TabsManager mTabsModel;
    /* access modifiers changed from: private */
    public final BrowserView mView;

    public BrowserPresenter(BrowserView browserView, boolean z) {
        BrowserApp.getAppComponent().inject(this);
        TabsManager tabModel = ((UIController) browserView).getTabModel();
        this.mTabsModel = tabModel;
        this.mView = browserView;
        this.mIsIncognito = z;
        tabModel.setTabNumberChangedListener(new TabsManager.TabNumberChangedListener() {
            public void tabNumberChanged(int i) {
                BrowserPresenter.this.mView.updateTabNumber(i);
            }
        });
    }

    public void setupTabs(Intent intent) {
        this.mTabsModel.initializeTabs((FragmentActivity) this.mView, intent, this.mIsIncognito).subscribeOn(Schedulers.main()).subscribe(new CompletableOnSubscribe() {
            public void onComplete() {
                BrowserPresenter.this.mView.notifyTabViewInitialized();
                BrowserPresenter.this.mView.updateTabNumber(BrowserPresenter.this.mTabsModel.size());
                BrowserPresenter browserPresenter = BrowserPresenter.this;
                browserPresenter.tabChanged(browserPresenter.mTabsModel.last());
            }
        });
    }

    public void tabChangeOccurred(LightningView lightningView) {
        this.mView.notifyTabViewChanged(this.mTabsModel.indexOfTab(lightningView));
    }

    private void onTabChanged(LightningView lightningView) {
        Log.d(TAG, "On tab changed");
        if (lightningView == null) {
            this.mView.removeTabView();
            LightningView lightningView2 = this.mCurrentTab;
            if (lightningView2 != null) {
                lightningView2.pauseTimers();
                this.mCurrentTab.onDestroy();
            }
        } else if (lightningView.getWebView() == null) {
            this.mView.removeTabView();
            LightningView lightningView3 = this.mCurrentTab;
            if (lightningView3 != null) {
                lightningView3.pauseTimers();
                this.mCurrentTab.onDestroy();
            }
        } else {
            LightningView lightningView4 = this.mCurrentTab;
            if (lightningView4 != null) {
                lightningView4.setIsForegroundTab(false);
            }
            lightningView.resumeTimers();
            lightningView.onResume();
            lightningView.setIsForegroundTab(true);
            this.mView.updateProgress(lightningView.getProgress());
            this.mView.setBackButtonEnabled(lightningView.canGoBack());
            this.mView.setForwardButtonEnabled(lightningView.canGoForward());
            this.mView.updateUrl(lightningView.getUrl(), true);
            this.mView.setTabView(lightningView);
            if (this.mTabsModel.indexOfTab(lightningView) >= 0) {
                this.mView.notifyTabViewChanged(this.mTabsModel.indexOfTab(lightningView));
            }
        }
        this.mCurrentTab = lightningView;
    }

    public void closeAllOtherTabs() {
        while (this.mTabsModel.last() != this.mTabsModel.indexOfCurrentTab()) {
            deleteTab(this.mTabsModel.last());
        }
        while (this.mTabsModel.indexOfCurrentTab() != 0) {
            deleteTab(0);
        }
    }

    public void deleteTab(int i) {
        Log.d(TAG, "delete Tab");
        LightningView tabAtPosition = this.mTabsModel.getTabAtPosition(i);
        if (tabAtPosition != null) {
            C30453 r2 = new View.OnClickListener() {
                public void onClick(View view) {
                    if (BrowserPresenter.this.mPreferences.getSavedUrl() == null) {
                        BrowserPresenter browserPresenter = BrowserPresenter.this;
                        browserPresenter.newTab((String) null, browserPresenter.mIsIncognito);
                    } else {
                        ((UIController) BrowserPresenter.this.mView).newTabButtonLongClicked();
                    }
                    BrowserPresenter.this.mView.notifyTabViewAdded();
                }
            };
            if (!UrlUtils.isSpecialUrl(tabAtPosition.getUrl()) && !this.mIsIncognito) {
                this.mPreferences.setSavedUrl(tabAtPosition.getUrl());
            }
            LightningView tabAtPosition2 = this.mTabsModel.getTabAtPosition(i);
            boolean isShown = tabAtPosition.isShown();
            boolean z = this.mShouldClose && isShown && tabAtPosition.isNewTab();
            LightningView currentTab = this.mTabsModel.getCurrentTab();
            if (this.mTabsModel.size() != 1 || currentTab == null || (!UrlUtils.isStartPageUrl(currentTab.getUrl()) && !currentTab.getUrl().equals(this.mPreferences.getHomepage()))) {
                if (isShown) {
                    this.mView.removeTabView();
                }
                if (this.mTabsModel.deleteTab(i)) {
                    tabChanged(this.mTabsModel.indexOfCurrentTab());
                }
                LightningView currentTab2 = this.mTabsModel.getCurrentTab();
                this.mView.notifyTabViewRemoved(i);
                if (tabAtPosition2 != null) {
                    this.mView.notifyTabClosed(tabAtPosition2.getTitle(), r2);
                }
                if (currentTab2 == null) {
                    this.mView.closeBrowser();
                    return;
                }
                if (currentTab2 != currentTab) {
                    this.mView.notifyTabViewChanged(this.mTabsModel.indexOfCurrentTab());
                }
                if (z) {
                    this.mShouldClose = false;
                    this.mView.closeActivity();
                }
                this.mView.updateTabNumber(this.mTabsModel.size());
                Log.d(TAG, "deleted tab");
                return;
            }
            this.mView.closeAllTabs();
        }
    }

    public void onNewIntent(final Intent intent) {
        this.mTabsModel.doAfterInitialization(new Runnable() {
            public void run() {
                Intent intent = intent;
                final String dataString = intent != null ? intent.getDataString() : null;
                int i = 0;
                Intent intent2 = intent;
                if (!(intent2 == null || intent2.getExtras() == null)) {
                    i = intent.getExtras().getInt(Constants.INTENT_ORIGIN);
                }
                if (i != 0) {
                    LightningView tabForHashCode = BrowserPresenter.this.mTabsModel.getTabForHashCode(i);
                    if (tabForHashCode != null) {
                        tabForHashCode.loadUrl(dataString);
                    }
                } else if (dataString == null) {
                } else {
                    if (dataString.startsWith(Constants.FILE)) {
                        BrowserPresenter.this.mView.showBlockedLocalFileDialog(new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                BrowserPresenter.this.newTab(dataString, true);
                                boolean unused = BrowserPresenter.this.mShouldClose = true;
                                LightningView lastTab = BrowserPresenter.this.mTabsModel.lastTab();
                                if (lastTab != null) {
                                    lastTab.setIsNewTab(true);
                                }
                            }
                        });
                        return;
                    }
                    BrowserPresenter.this.newTab(dataString, true);
                    boolean unused = BrowserPresenter.this.mShouldClose = true;
                    LightningView lastTab = BrowserPresenter.this.mTabsModel.lastTab();
                    if (lastTab != null && intent.getData() != null && !intent.getData().getHost().equals("www.googleadservices.com")) {
                        lastTab.setIsNewTab(true);
                    }
                }
            }
        });
    }

    public void loadUrlInCurrentView(String str) {
        LightningView currentTab = this.mTabsModel.getCurrentTab();
        if (currentTab != null) {
            currentTab.loadUrl(str);
        }
    }

    public void shutdown() {
        onTabChanged((LightningView) null);
        this.mTabsModel.setTabNumberChangedListener((TabsManager.TabNumberChangedListener) null);
        this.mTabsModel.cancelPendingWork();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void tabChanged(int r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            java.lang.String r0 = "BrowserPresenter"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x002f }
            r1.<init>()     // Catch:{ all -> 0x002f }
            java.lang.String r2 = "tabChanged: "
            r1.append(r2)     // Catch:{ all -> 0x002f }
            r1.append(r4)     // Catch:{ all -> 0x002f }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x002f }
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x002f }
            if (r4 < 0) goto L_0x002d
            acr.browser.lightning.activity.TabsManager r0 = r3.mTabsModel     // Catch:{ all -> 0x002f }
            int r0 = r0.size()     // Catch:{ all -> 0x002f }
            if (r4 < r0) goto L_0x0022
            goto L_0x002d
        L_0x0022:
            acr.browser.lightning.activity.TabsManager r0 = r3.mTabsModel     // Catch:{ all -> 0x002f }
            acr.browser.lightning.view.LightningView r4 = r0.switchToTab(r4)     // Catch:{ all -> 0x002f }
            r3.onTabChanged(r4)     // Catch:{ all -> 0x002f }
            monitor-exit(r3)
            return
        L_0x002d:
            monitor-exit(r3)
            return
        L_0x002f:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.browser.BrowserPresenter.tabChanged(int):void");
    }

    public synchronized boolean newTab(String str, boolean z) {
        if (BuildConfig.FULL_VERSION || this.mTabsModel.size() < 10) {
            this.mView.showInterstitialAd(Constants.TAG_FULLSCREEN_NEWTAB);
            Log.d(TAG, "New tab, show: " + z);
            LightningView newTab = this.mTabsModel.newTab((FragmentActivity) this.mView, str, this.mIsIncognito);
            if (this.mTabsModel.size() == 1) {
                newTab.resumeTimers();
            }
            this.mView.notifyTabViewAdded();
            if (z) {
                onTabChanged(this.mTabsModel.switchToTab(this.mTabsModel.last()));
            }
            this.mView.updateTabNumber(this.mTabsModel.size());
            return true;
        }
        this.mView.showSnackbar(R.string.max_tabs);
        return false;
    }

    public void onAutoCompleteItemPressed() {
        LightningView currentTab = this.mTabsModel.getCurrentTab();
        if (currentTab != null) {
            currentTab.requestFocus();
        }
    }

    public void findInPage(String str) {
        LightningView currentTab = this.mTabsModel.getCurrentTab();
        if (currentTab != null) {
            currentTab.find(str);
        }
    }

    public void onAppLowMemory() {
        this.mTabsModel.freeMemory();
    }
}
