package acr.browser.lightning.activity;

import acr.browser.lightning.constant.Constants;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.anthonycr.bonsai.Completable;
import com.anthonycr.bonsai.CompletableAction;
import com.anthonycr.bonsai.CompletableSubscriber;
import com.appsgeyser.sdk.AppsgeyserSDK;
import com.wnikebrow_13999769.R;

public class MainActivity extends BrowserActivity {
    public static final int SEARCH_BAR_NOTIFICATION_ID = 8242017;
    public boolean firstLaunch = true;
    private boolean inBackground = true;

    public boolean isIncognito() {
        return false;
    }

    public Completable updateCookiePreference() {
        return Completable.create(new CompletableAction() {
            public void onSubscribe(CompletableSubscriber completableSubscriber) {
                CookieManager instance = CookieManager.getInstance();
                if (Build.VERSION.SDK_INT < 21) {
                    CookieSyncManager.createInstance(MainActivity.this);
                }
                instance.setAcceptCookie(MainActivity.this.mPreferences.getCookiesEnabled());
                completableSubscriber.onComplete();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        AppsgeyserSDK.takeOff(this, getResources().getString(R.string.widgetID), getString(R.string.app_metrica_on_start_event), getString(R.string.template_version));
        super.onCreate(bundle);
        showInterstitialAd(Constants.TAG_FULLSCREEN_ONSTART);
        if (this.mPreferences.getNotificationSearchBarEnabled()) {
            createNotice();
        }
        this.mPreferences.getNotificationSearchBarEnabled();
    }

    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        AppsgeyserSDK.isAboutDialogEnabled(this, new AppsgeyserSDK.OnAboutDialogEnableListener() {
            public void onDialogEnableReceived(boolean z) {
                if (!z) {
                    menu.removeItem(R.id.main_menu_about_dialog);
                }
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        if (intent.getBooleanExtra("focus", false)) {
            this.mSearch.requestFocusFromTouch();
            ((InputMethodManager) getSystemService("input_method")).showSoftInput(this.mSearch, 1);
        }
        if (isPanicTrigger(intent)) {
            panicClean();
            return;
        }
        handleNewIntent(intent);
        super.onNewIntent(intent);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        saveOpenTabs();
        AppsgeyserSDK.onPause(this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        AppsgeyserSDK.onResume(this);
    }

    public void updateHistory(String str, String str2) {
        addItemToHistory(str, str2);
    }

    public void closeActivity() {
        closeDrawers(new Runnable() {
            public void run() {
                MainActivity.this.performExitCleanUp();
                MainActivity.this.moveTaskToBack(true);
            }
        });
    }
}
