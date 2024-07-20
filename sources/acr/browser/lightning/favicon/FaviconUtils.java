package acr.browser.lightning.favicon;

import android.net.Uri;
import android.text.TextUtils;

class FaviconUtils {
    FaviconUtils() {
    }

    static Uri safeUri(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Uri parse = Uri.parse(str);
        if (TextUtils.isEmpty(parse.getScheme()) || TextUtils.isEmpty(parse.getHost())) {
            return null;
        }
        return parse;
    }

    static void assertUriSafe(Uri uri) {
        if (uri == null || TextUtils.isEmpty(uri.getScheme()) || TextUtils.isEmpty(uri.getHost())) {
            throw new RuntimeException("Unsafe uri provided");
        }
    }
}
