package acr.browser.lightning.utils;

import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.constant.BookmarkPage;
import acr.browser.lightning.constant.Constants;
import acr.browser.lightning.constant.DownloadsPage;
import acr.browser.lightning.constant.HistoryPage;
import acr.browser.lightning.constant.StartPage;
import android.util.Patterns;
import android.webkit.URLUtil;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public class UrlUtils {
    private static final Pattern ACCEPTED_URI_SCHEMA = Pattern.compile("(?i)((?:http|https|file)://|(?:inline|data|about|javascript):|(?:.*:.*@))(.*)");
    public static final String QUERY_PLACE_HOLDER = "%s";

    private UrlUtils() {
    }

    public static String smartUrlFilter(String str, boolean z, String str2) {
        String trim = str.trim();
        boolean z2 = trim.indexOf(32) != -1;
        Matcher matcher = ACCEPTED_URI_SCHEMA.matcher(trim);
        if (matcher.matches()) {
            String group = matcher.group(1);
            String lowerCase = group.toLowerCase();
            if (!lowerCase.equals(group)) {
                trim = lowerCase + matcher.group(2);
            }
            return (!z2 || !Patterns.WEB_URL.matcher(trim).matches()) ? trim : trim.replace(StringUtils.SPACE, "%20");
        } else if (z2 || !Patterns.WEB_URL.matcher(trim).matches()) {
            return z ? URLUtil.composeSearchUrl(trim, str2, QUERY_PLACE_HOLDER) : "";
        } else {
            return URLUtil.guessUrl(trim);
        }
    }

    public static boolean isSpecialUrl(String str) {
        return str != null && str.startsWith(Constants.FILE) && (str.endsWith(BookmarkPage.FILENAME) || str.endsWith(DownloadsPage.FILENAME) || str.endsWith(HistoryPage.FILENAME) || str.endsWith(StartPage.FILENAME) || (!BrowserApp.getConfig().getHomePageUrl().equals("") && str.endsWith(BrowserApp.getConfig().getHomePageUrl())));
    }

    public static boolean isBookmarkUrl(String str) {
        return str != null && str.startsWith(Constants.FILE) && str.endsWith(BookmarkPage.FILENAME);
    }

    public static boolean isDownloadsUrl(String str) {
        return str != null && str.startsWith(Constants.FILE) && str.endsWith(DownloadsPage.FILENAME);
    }

    public static boolean isHistoryUrl(String str) {
        return str != null && str.startsWith(Constants.FILE) && str.endsWith(HistoryPage.FILENAME);
    }

    public static boolean isStartPageUrl(String str) {
        return str != null && str.startsWith(Constants.FILE) && (str.endsWith(StartPage.FILENAME) || (!BrowserApp.getConfig().getHomePageUrl().equals("") && str.endsWith(BrowserApp.getConfig().getHomePageUrl())));
    }
}
