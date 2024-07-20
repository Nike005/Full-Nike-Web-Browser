package acr.browser.lightning.utils;

import acr.browser.lightning.database.history.HistoryModel;
import android.content.Context;
import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import android.webkit.WebIconDatabase;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewDatabase;
import com.anthonycr.bonsai.Schedulers;

public class WebUtils {
    public static void clearCookies(Context context) {
        CookieManager instance = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT >= 21) {
            instance.removeAllCookies((ValueCallback) null);
            return;
        }
        CookieSyncManager.createInstance(context);
        instance.removeAllCookie();
    }

    public static void clearWebStorage() {
        WebStorage.getInstance().deleteAllData();
    }

    public static void clearHistory(Context context) {
        HistoryModel.deleteHistory().subscribeOn(Schedulers.m6232io()).subscribe();
        WebViewDatabase instance = WebViewDatabase.getInstance(context);
        instance.clearFormData();
        instance.clearHttpAuthUsernamePassword();
        if (Build.VERSION.SDK_INT < 18) {
            instance.clearUsernamePassword();
            WebIconDatabase.getInstance().removeAllIcons();
        }
        C3245Utils.trimCache(context);
    }

    public static void clearCache(WebView webView) {
        if (webView != null) {
            webView.clearCache(true);
        }
    }
}
