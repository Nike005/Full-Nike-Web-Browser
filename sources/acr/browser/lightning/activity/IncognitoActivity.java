package acr.browser.lightning.activity;

import android.content.Intent;
import android.os.Build;
import android.view.Menu;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.anthonycr.bonsai.Completable;
import com.anthonycr.bonsai.CompletableAction;
import com.anthonycr.bonsai.CompletableSubscriber;
import com.appsgeyser.sdk.AppsgeyserSDK;
import com.wnikebrow_13999769.R;

public class IncognitoActivity extends BrowserActivity {
    public boolean isIncognito() {
        return true;
    }

    public void updateHistory(String str, String str2) {
    }

    public Completable updateCookiePreference() {
        return Completable.create(new CompletableAction() {
            public void onSubscribe(CompletableSubscriber completableSubscriber) {
                CookieManager instance = CookieManager.getInstance();
                if (Build.VERSION.SDK_INT < 21) {
                    CookieSyncManager.createInstance(IncognitoActivity.this);
                }
                instance.setAcceptCookie(IncognitoActivity.this.mPreferences.getIncognitoCookiesEnabled());
                completableSubscriber.onComplete();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.incognito, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        AppsgeyserSDK.onPause(this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        AppsgeyserSDK.onResume(this);
    }

    public void closeActivity() {
        closeDrawers(new Runnable() {
            public void run() {
                IncognitoActivity.this.closeBrowser();
            }
        });
    }
}
