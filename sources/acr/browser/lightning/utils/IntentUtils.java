package acr.browser.lightning.utils;

import acr.browser.lightning.constant.Constants;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.webkit.WebView;
import com.wnikebrow_13999769.R;
import java.net.URISyntaxException;
import java.util.List;
import java.util.regex.Pattern;

public class IntentUtils {
    private static final Pattern ACCEPTED_URI_SCHEMA = Pattern.compile("(?i)((?:http|https|file)://|(?:inline|data|about|javascript):|(?:.*:.*@))(.*)");
    private final Activity mActivity;

    public IntentUtils(Activity activity) {
        this.mActivity = activity;
    }

    public boolean startActivityForUrl(WebView webView, String str) {
        try {
            Intent parseUri = Intent.parseUri(str, 1);
            parseUri.addCategory("android.intent.category.BROWSABLE");
            parseUri.setComponent((ComponentName) null);
            if (Build.VERSION.SDK_INT >= 15) {
                parseUri.setSelector((Intent) null);
            }
            if (this.mActivity.getPackageManager().resolveActivity(parseUri, 0) == null) {
                String str2 = parseUri.getPackage();
                if (str2 == null) {
                    return false;
                }
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pname:" + str2));
                intent.addCategory("android.intent.category.BROWSABLE");
                this.mActivity.startActivity(intent);
                return true;
            }
            if (webView != null) {
                parseUri.putExtra(Constants.INTENT_ORIGIN, webView.hashCode());
            }
            if (ACCEPTED_URI_SCHEMA.matcher(str).matches() && !isSpecializedHandlerAvailable(parseUri)) {
                return false;
            }
            try {
                return this.mActivity.startActivityIfNeeded(parseUri, -1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException e2) {
            Log.w("Browser", "Bad URI " + str + ": " + e2.getMessage());
            return false;
        }
    }

    private boolean isSpecializedHandlerAvailable(Intent intent) {
        List<ResolveInfo> queryIntentActivities = this.mActivity.getPackageManager().queryIntentActivities(intent, 64);
        if (queryIntentActivities != null && !queryIntentActivities.isEmpty()) {
            for (ResolveInfo resolveInfo : queryIntentActivities) {
                IntentFilter intentFilter = resolveInfo.filter;
                if (intentFilter != null && intentFilter.countDataAuthorities() != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public void shareUrl(String str, String str2) {
        if (str != null && !UrlUtils.isSpecialUrl(str)) {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            if (str2 != null) {
                intent.putExtra("android.intent.extra.SUBJECT", str2);
            }
            intent.putExtra("android.intent.extra.TEXT", str);
            Activity activity = this.mActivity;
            activity.startActivity(Intent.createChooser(intent, activity.getString(R.string.dialog_title_share)));
        }
    }
}
