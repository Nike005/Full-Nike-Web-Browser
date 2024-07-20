package acr.browser.lightning.activity;

import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.constant.BookmarkPage;
import acr.browser.lightning.constant.Constants;
import acr.browser.lightning.constant.DownloadsPage;
import acr.browser.lightning.constant.HistoryPage;
import acr.browser.lightning.dialog.BrowserDialog;
import acr.browser.lightning.preference.PreferenceManager;
import acr.browser.lightning.utils.FileUtils;
import acr.browser.lightning.utils.Preconditions;
import acr.browser.lightning.utils.UrlUtils;
import acr.browser.lightning.view.LightningView;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import com.anthonycr.bonsai.Completable;
import com.anthonycr.bonsai.CompletableAction;
import com.anthonycr.bonsai.CompletableSubscriber;
import com.anthonycr.bonsai.Schedulers;
import com.anthonycr.bonsai.SingleOnSubscribe;
import com.anthonycr.bonsai.Stream;
import com.anthonycr.bonsai.StreamAction;
import com.anthonycr.bonsai.StreamOnSubscribe;
import com.anthonycr.bonsai.StreamSubscriber;
import com.wnikebrow_13999769.R;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class TabsManager {
    private static final String BUNDLE_KEY = "WEBVIEW_";
    private static final String BUNDLE_STORAGE = "SAVED_TABS.parcel";
    private static final String TAG = "TabsManager";
    private static final String URL_KEY = "URL_KEY";
    @Inject
    Application mApp;
    /* access modifiers changed from: private */
    public LightningView mCurrentTab;
    private boolean mIsInitialized = false;
    private final List<Runnable> mPostInitializationWorkList = new ArrayList();
    @Inject
    PreferenceManager mPreferenceManager;
    /* access modifiers changed from: private */
    public final List<LightningView> mTabList = new ArrayList(1);
    private TabNumberChangedListener mTabNumberListener;

    @Deprecated
    public interface TabNumberChangedListener {
        void tabNumberChanged(int i);
    }

    public TabsManager() {
        BrowserApp.getAppComponent().inject(this);
    }

    public void setTabNumberChangedListener(TabNumberChangedListener tabNumberChangedListener) {
        this.mTabNumberListener = tabNumberChangedListener;
    }

    public void cancelPendingWork() {
        this.mPostInitializationWorkList.clear();
    }

    public synchronized void doAfterInitialization(Runnable runnable) {
        if (this.mIsInitialized) {
            runnable.run();
        } else {
            this.mPostInitializationWorkList.add(runnable);
        }
    }

    /* access modifiers changed from: private */
    public synchronized void finishInitialization() {
        this.mIsInitialized = true;
        for (Runnable run : this.mPostInitializationWorkList) {
            run.run();
        }
    }

    public synchronized Completable initializeTabs(final FragmentActivity fragmentActivity, final Intent intent, final boolean z) {
        return Completable.create(new CompletableAction() {
            public void onSubscribe(CompletableSubscriber completableSubscriber) {
                TabsManager.this.shutdown();
                Intent intent = intent;
                String dataString = intent != null ? intent.getDataString() : null;
                if (z) {
                    TabsManager.this.newTab(fragmentActivity, dataString, true);
                    completableSubscriber.onComplete();
                    return;
                }
                Log.d(TabsManager.TAG, "URL from intent: " + dataString);
                LightningView unused = TabsManager.this.mCurrentTab = null;
                if (TabsManager.this.mPreferenceManager.getRestoreLostTabsEnabled()) {
                    TabsManager.this.restoreLostTabs(dataString, fragmentActivity, completableSubscriber);
                    return;
                }
                if (!TextUtils.isEmpty(dataString)) {
                    TabsManager.this.newTab(fragmentActivity, dataString, false);
                } else {
                    TabsManager.this.newTab(fragmentActivity, (String) null, false);
                }
                TabsManager.this.finishInitialization();
                completableSubscriber.onComplete();
            }
        });
    }

    /* access modifiers changed from: private */
    public void restoreLostTabs(final String str, final FragmentActivity fragmentActivity, final CompletableSubscriber completableSubscriber) {
        restoreState().subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new StreamOnSubscribe<Bundle>() {
            public void onNext(Bundle bundle) {
                final LightningView newTab = TabsManager.this.newTab(fragmentActivity, "", false);
                Preconditions.checkNonNull(bundle);
                String string = bundle.getString(TabsManager.URL_KEY);
                if (string == null || newTab.getWebView() == null) {
                    if (newTab.getWebView() != null) {
                        newTab.getWebView().restoreState(bundle);
                    }
                } else if (UrlUtils.isBookmarkUrl(string)) {
                    new BookmarkPage(fragmentActivity).getBookmarkPage().subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<String>() {
                        public void onItem(String str) {
                            Preconditions.checkNonNull(str);
                            newTab.loadUrl(str);
                        }
                    });
                } else if (UrlUtils.isDownloadsUrl(string)) {
                    new DownloadsPage().getDownloadsPage().subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<String>() {
                        public void onItem(String str) {
                            Preconditions.checkNonNull(str);
                            newTab.loadUrl(str);
                        }
                    });
                } else if (UrlUtils.isStartPageUrl(string)) {
                    newTab.loadStartpage();
                } else if (UrlUtils.isHistoryUrl(string)) {
                    new HistoryPage().getHistoryPage().subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<String>() {
                        public void onItem(String str) {
                            Preconditions.checkNonNull(str);
                            newTab.loadUrl(str);
                        }
                    });
                }
            }

            public void onComplete() {
                String str = str;
                if (str == null) {
                    if (TabsManager.this.mTabList.isEmpty()) {
                        TabsManager.this.newTab(fragmentActivity, (String) null, false);
                    }
                    TabsManager.this.finishInitialization();
                    completableSubscriber.onComplete();
                } else if (str.startsWith(Constants.FILE)) {
                    BrowserDialog.setDialogSize(fragmentActivity, new AlertDialog.Builder(fragmentActivity).setCancelable(true).setTitle((int) R.string.title_warning).setMessage((int) R.string.message_blocked_local).setOnDismissListener(new DialogInterface.OnDismissListener() {
                        public void onDismiss(DialogInterface dialogInterface) {
                            if (TabsManager.this.mTabList.isEmpty()) {
                                TabsManager.this.newTab(fragmentActivity, (String) null, false);
                            }
                            TabsManager.this.finishInitialization();
                            completableSubscriber.onComplete();
                        }
                    }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).setPositiveButton((int) R.string.action_open, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            TabsManager.this.newTab(fragmentActivity, str, false);
                        }
                    }).show());
                } else {
                    TabsManager.this.newTab(fragmentActivity, str, false);
                    if (TabsManager.this.mTabList.isEmpty()) {
                        TabsManager.this.newTab(fragmentActivity, (String) null, false);
                    }
                    TabsManager.this.finishInitialization();
                    completableSubscriber.onComplete();
                }
            }
        });
    }

    public void resumeAll(Context context) {
        LightningView currentTab = getCurrentTab();
        if (currentTab != null) {
            currentTab.resumeTimers();
        }
        for (LightningView next : this.mTabList) {
            if (next != null) {
                next.onResume();
                next.initializePreferences(context);
            }
        }
    }

    public void pauseAll() {
        LightningView currentTab = getCurrentTab();
        if (currentTab != null) {
            currentTab.pauseTimers();
        }
        for (LightningView next : this.mTabList) {
            if (next != null) {
                next.onPause();
            }
        }
    }

    public synchronized LightningView getTabAtPosition(int i) {
        if (i >= 0) {
            if (i < this.mTabList.size()) {
                return this.mTabList.get(i);
            }
        }
        return null;
    }

    public synchronized void freeMemory() {
        for (LightningView freeMemory : this.mTabList) {
            freeMemory.freeMemory();
        }
    }

    public synchronized void shutdown() {
        for (LightningView onDestroy : this.mTabList) {
            onDestroy.onDestroy();
        }
        this.mTabList.clear();
        this.mIsInitialized = false;
        this.mCurrentTab = null;
    }

    public synchronized void notifyConnectionStatus(boolean z) {
        for (LightningView webView : this.mTabList) {
            WebView webView2 = webView.getWebView();
            if (webView2 != null) {
                webView2.setNetworkAvailable(z);
            }
        }
    }

    public synchronized int size() {
        return this.mTabList.size();
    }

    public synchronized int last() {
        return this.mTabList.size() - 1;
    }

    public synchronized LightningView lastTab() {
        if (last() < 0) {
            return null;
        }
        return this.mTabList.get(last());
    }

    public synchronized LightningView newTab(FragmentActivity fragmentActivity, String str, boolean z) {
        LightningView lightningView;
        Log.d(TAG, "New tab");
        lightningView = new LightningView(fragmentActivity, str, z);
        this.mTabList.add(lightningView);
        if (this.mTabNumberListener != null) {
            this.mTabNumberListener.tabNumberChanged(size());
        }
        return lightningView;
    }

    private synchronized void removeTab(int i) {
        if (i < this.mTabList.size()) {
            LightningView remove = this.mTabList.remove(i);
            if (this.mCurrentTab == remove) {
                this.mCurrentTab = null;
            }
            remove.onDestroy();
        }
    }

    public synchronized boolean deleteTab(int i) {
        boolean z;
        Log.d(TAG, "Delete tab: " + i);
        int positionOf = positionOf(getCurrentTab());
        z = true;
        if (positionOf == i) {
            if (size() == 1) {
                this.mCurrentTab = null;
            } else if (positionOf < size() - 1) {
                switchToTab(positionOf + 1);
            } else {
                switchToTab(positionOf - 1);
            }
        }
        removeTab(i);
        if (this.mTabNumberListener != null) {
            this.mTabNumberListener.tabNumberChanged(size());
        }
        if (positionOf != i) {
            z = false;
        }
        return z;
    }

    public synchronized int positionOf(LightningView lightningView) {
        return this.mTabList.indexOf(lightningView);
    }

    public void saveState() {
        Bundle bundle = new Bundle(ClassLoader.getSystemClassLoader());
        Log.d(TAG, "Saving tab state");
        for (int i = 0; i < this.mTabList.size(); i++) {
            LightningView lightningView = this.mTabList.get(i);
            if (!TextUtils.isEmpty(lightningView.getUrl())) {
                Bundle bundle2 = new Bundle(ClassLoader.getSystemClassLoader());
                if (lightningView.getWebView() != null && !UrlUtils.isSpecialUrl(lightningView.getUrl())) {
                    lightningView.getWebView().saveState(bundle2);
                    bundle.putBundle(BUNDLE_KEY + i, bundle2);
                } else if (lightningView.getWebView() != null) {
                    bundle2.putString(URL_KEY, lightningView.getUrl());
                    bundle.putBundle(BUNDLE_KEY + i, bundle2);
                }
            }
        }
        FileUtils.writeBundleToStorage(this.mApp, bundle, BUNDLE_STORAGE);
    }

    public void clearSavedState() {
        FileUtils.deleteBundleInStorage(this.mApp, BUNDLE_STORAGE);
    }

    private Stream<Bundle> restoreState() {
        return Stream.create(new StreamAction<Bundle>() {
            public void onSubscribe(StreamSubscriber<Bundle> streamSubscriber) {
                Bundle readBundleFromStorage = FileUtils.readBundleFromStorage(TabsManager.this.mApp, TabsManager.BUNDLE_STORAGE);
                if (readBundleFromStorage != null) {
                    Log.d(TabsManager.TAG, "Restoring previous WebView state now");
                    for (String str : readBundleFromStorage.keySet()) {
                        if (str.startsWith(TabsManager.BUNDLE_KEY)) {
                            streamSubscriber.onNext(readBundleFromStorage.getBundle(str));
                        }
                    }
                }
                FileUtils.deleteBundleInStorage(TabsManager.this.mApp, TabsManager.BUNDLE_STORAGE);
                streamSubscriber.onComplete();
            }
        });
    }

    public synchronized int indexOfCurrentTab() {
        return this.mTabList.indexOf(this.mCurrentTab);
    }

    public synchronized int indexOfTab(LightningView lightningView) {
        return this.mTabList.indexOf(lightningView);
    }

    public synchronized LightningView getCurrentTab() {
        return this.mCurrentTab;
    }

    public synchronized LightningView getTabForHashCode(int i) {
        for (LightningView next : this.mTabList) {
            if (next.getWebView() != null && next.getWebView().hashCode() == i) {
                return next;
            }
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002f, code lost:
        return r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized acr.browser.lightning.view.LightningView switchToTab(int r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            java.lang.String r0 = "TabsManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0049 }
            r1.<init>()     // Catch:{ all -> 0x0049 }
            java.lang.String r2 = "switch to tab: "
            r1.append(r2)     // Catch:{ all -> 0x0049 }
            r1.append(r4)     // Catch:{ all -> 0x0049 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0049 }
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x0049 }
            if (r4 < 0) goto L_0x0030
            java.util.List<acr.browser.lightning.view.LightningView> r0 = r3.mTabList     // Catch:{ all -> 0x0049 }
            int r0 = r0.size()     // Catch:{ all -> 0x0049 }
            if (r4 < r0) goto L_0x0022
            goto L_0x0030
        L_0x0022:
            java.util.List<acr.browser.lightning.view.LightningView> r0 = r3.mTabList     // Catch:{ all -> 0x0049 }
            java.lang.Object r4 = r0.get(r4)     // Catch:{ all -> 0x0049 }
            acr.browser.lightning.view.LightningView r4 = (acr.browser.lightning.view.LightningView) r4     // Catch:{ all -> 0x0049 }
            if (r4 == 0) goto L_0x002e
            r3.mCurrentTab = r4     // Catch:{ all -> 0x0049 }
        L_0x002e:
            monitor-exit(r3)
            return r4
        L_0x0030:
            java.lang.String r0 = "TabsManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0049 }
            r1.<init>()     // Catch:{ all -> 0x0049 }
            java.lang.String r2 = "Returning a null LightningView requested for position: "
            r1.append(r2)     // Catch:{ all -> 0x0049 }
            r1.append(r4)     // Catch:{ all -> 0x0049 }
            java.lang.String r4 = r1.toString()     // Catch:{ all -> 0x0049 }
            android.util.Log.e(r0, r4)     // Catch:{ all -> 0x0049 }
            r4 = 0
            monitor-exit(r3)
            return r4
        L_0x0049:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.activity.TabsManager.switchToTab(int):acr.browser.lightning.view.LightningView");
    }
}
