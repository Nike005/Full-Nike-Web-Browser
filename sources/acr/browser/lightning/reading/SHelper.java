package acr.browser.lightning.reading;

import acr.browser.lightning.constant.Constants;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Pattern;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;

class SHelper {
    private static final Pattern SPACE = Pattern.compile(StringUtils.SPACE);
    private static final String UTF8 = "UTF-8";

    SHelper() {
    }

    public static String replaceSpaces(String str) {
        if (str.isEmpty()) {
            return str;
        }
        String trim = str.trim();
        return trim.contains(StringUtils.SPACE) ? SPACE.matcher(trim).replaceAll("%20") : trim;
    }

    public static int count(String str, String str2) {
        int indexOf = str.indexOf(str2);
        if (indexOf >= 0) {
            return 1 + count(str.substring(indexOf + str2.length()), str2);
        }
        return 0;
    }

    public static String innerTrim(String str) {
        if (str.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder(str.length());
        int length = str.length();
        boolean z = false;
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == ' ' || charAt == 9 || charAt == 10) {
                z = true;
            } else {
                if (z) {
                    sb.append(' ');
                }
                sb.append(charAt);
                z = false;
            }
        }
        return sb.toString().trim();
    }

    public static String encodingCleanup(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        boolean z = false;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isDigit(charAt) || Character.isLetter(charAt) || charAt == '-' || charAt == '_') {
                sb.append(charAt);
                z = true;
            } else if (z) {
                break;
            }
        }
        return sb.toString().trim();
    }

    public static String getLongestSubstring(String str, String str2) {
        int[] longestSubstring = longestSubstring(str, str2);
        return (longestSubstring == null || longestSubstring[0] >= longestSubstring[1]) ? "" : str.substring(longestSubstring[0], longestSubstring[1]);
    }

    private static int[] longestSubstring(String str, String str2) {
        if (str == null || str.isEmpty() || str2 == null || str2.isEmpty()) {
            return null;
        }
        int length = str.length();
        int[] iArr = new int[2];
        iArr[1] = str2.length();
        iArr[0] = length;
        int[][] iArr2 = (int[][]) Array.newInstance(int.class, iArr);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < str.length(); i4++) {
            for (int i5 = 0; i5 < str2.length(); i5++) {
                if (str.charAt(i4) == str2.charAt(i5)) {
                    if (i4 == 0 || i5 == 0) {
                        iArr2[i4][i5] = 1;
                    } else {
                        iArr2[i4][i5] = iArr2[i4 - 1][i5 - 1] + 1;
                    }
                    if (iArr2[i4][i5] > i3) {
                        i3 = iArr2[i4][i5];
                        i = (i4 - iArr2[i4][i5]) + 1;
                        i2 = i4 + 1;
                    }
                }
            }
        }
        return new int[]{i, i2};
    }

    public static String getDefaultFavicon(String str) {
        return useDomainOfFirstArg4Second(str, "/favicon.ico");
    }

    public static String useDomainOfFirstArg4Second(String str, String str2) {
        try {
            return new URL(new URL(str), str2).toString();
        } catch (MalformedURLException unused) {
            return str2;
        }
    }

    public static String extractHost(String str) {
        return extractDomain(str, false);
    }

    public static String extractDomain(String str, boolean z) {
        if (str.startsWith(Constants.HTTP)) {
            str = str.substring(7);
        } else if (str.startsWith(Constants.HTTPS)) {
            str = str.substring(8);
        }
        if (z) {
            if (str.startsWith("www.")) {
                str = str.substring(4);
            }
            if (str.startsWith("m.")) {
                str = str.substring(2);
            }
        }
        int indexOf = str.indexOf(47);
        return indexOf > 0 ? str.substring(0, indexOf) : str;
    }

    public static boolean isVideoLink(String str) {
        String extractDomain = extractDomain(str, true);
        if (extractDomain.startsWith("youtube.com") || extractDomain.startsWith("video.yahoo.com") || extractDomain.startsWith("vimeo.com") || extractDomain.startsWith("blip.tv")) {
            return true;
        }
        return false;
    }

    public static boolean isVideo(String str) {
        return str.endsWith(".mpeg") || str.endsWith(".mpg") || str.endsWith(".avi") || str.endsWith(".mov") || str.endsWith(".mpg4") || str.endsWith(".mp4") || str.endsWith(".flv") || str.endsWith(".wmv");
    }

    public static boolean isAudio(String str) {
        return str.endsWith(".mp3") || str.endsWith(".ogg") || str.endsWith(".m3u") || str.endsWith(".wav");
    }

    public static boolean isDoc(String str) {
        return str.endsWith(".pdf") || str.endsWith(".ppt") || str.endsWith(".doc") || str.endsWith(".swf") || str.endsWith(".rtf") || str.endsWith(".xls");
    }

    public static boolean isPackage(String str) {
        return str.endsWith(".gz") || str.endsWith(".tgz") || str.endsWith(".zip") || str.endsWith(".rar") || str.endsWith(".deb") || str.endsWith(".rpm") || str.endsWith(".7z");
    }

    public static boolean isApp(String str) {
        return str.endsWith(".exe") || str.endsWith(".bin") || str.endsWith(".bat") || str.endsWith(".dmg");
    }

    public static boolean isImage(String str) {
        return str.endsWith(".png") || str.endsWith(".jpeg") || str.endsWith(".gif") || str.endsWith(".jpg") || str.endsWith(".bmp") || str.endsWith(".ico") || str.endsWith(".eps");
    }

    public static void enableCookieMgmt() {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);
    }

    public static void enableUserAgentOverwrite() {
        System.setProperty("http.agent", "");
    }

    public static String getUrlFromUglyGoogleRedirect(String str) {
        if (!str.startsWith("https://www.google.com/url?")) {
            return null;
        }
        for (String str2 : urlDecode(str.substring(27)).split("&")) {
            if (str2.startsWith("q=")) {
                return str2.substring(2);
            }
        }
        return null;
    }

    public static String getUrlFromUglyFacebookRedirect(String str) {
        if (str.startsWith("https://www.facebook.com/l.php?u=")) {
            return urlDecode(str.substring(33));
        }
        return null;
    }

    public static String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }

    private static String urlDecode(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }

    public static String removeHashbang(String str) {
        return str.replaceFirst("#!", "");
    }

    public static String printNode(Element element) {
        return printNode(element, 0);
    }

    private static String printNode(Element element, int i) {
        StringBuilder sb = new StringBuilder(i);
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(' ');
        }
        sb.append(element.tagName());
        sb.append(':');
        sb.append(element.ownText());
        sb.append(10);
        Iterator it = element.children().iterator();
        while (it.hasNext()) {
            sb.append(printNode((Element) it.next(), i + 1));
            sb.append(10);
        }
        return sb.toString();
    }

    public static String estimateDate(String str) {
        int i;
        int indexOf = str.indexOf("://");
        if (indexOf > 0) {
            str = str.substring(indexOf + 3);
        }
        String[] split = str.split("/");
        int i2 = 0;
        int i3 = -1;
        int i4 = -1;
        int i5 = -1;
        int i6 = -1;
        int i7 = -1;
        while (true) {
            if (i2 >= split.length) {
                break;
            }
            String str2 = split[i2];
            if (str2.length() == 4) {
                try {
                    i6 = Integer.parseInt(str2);
                    if (i6 < 1970 || i6 > 3000) {
                        i6 = -1;
                    } else {
                        i5 = i2;
                    }
                } catch (Exception unused) {
                }
            } else if (str2.length() != 2) {
                continue;
            } else if (i4 < 0 && i2 == i5 + 1) {
                i7 = Integer.parseInt(str2);
                if (i7 < 1 || i7 > 12) {
                    i7 = -1;
                } else {
                    i4 = i2;
                }
            } else if (i2 == i4 + 1) {
                try {
                    i = Integer.parseInt(str2);
                } catch (Exception unused2) {
                    i = -1;
                }
                if (i >= 1 && i <= 31) {
                    i3 = i;
                    break;
                }
            } else {
                continue;
            }
            i2++;
        }
        if (i6 < 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder(i6);
        if (i7 < 1) {
            return sb.toString();
        }
        sb.append('/');
        if (i7 < 10) {
            sb.append('0');
        }
        sb.append(i7);
        if (i3 < 1) {
            return sb.toString();
        }
        sb.append('/');
        if (i3 < 10) {
            sb.append('0');
        }
        sb.append(i3);
        return sb.toString();
    }

    public static String completeDate(String str) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(47);
        if (indexOf <= 0) {
            return str + "/01/01";
        } else if (str.indexOf(47, indexOf + 1) > 0) {
            return str;
        } else {
            return str + "/01";
        }
    }

    public static void enableAnySSL() {
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(new KeyManager[0], new TrustManager[]{new DefaultTrustManager()}, new SecureRandom());
            SSLContext.setDefault(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class DefaultTrustManager implements X509TrustManager {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        private DefaultTrustManager() {
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            Date date = new Date();
            for (X509Certificate checkValidity : x509CertificateArr) {
                checkValidity.checkValidity(date);
            }
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            Date date = new Date();
            for (X509Certificate checkValidity : x509CertificateArr) {
                checkValidity.checkValidity(date);
            }
        }
    }

    public static int countLetters(String str) {
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (Character.isLetter(str.charAt(i2))) {
                i++;
            }
        }
        return i;
    }
}
