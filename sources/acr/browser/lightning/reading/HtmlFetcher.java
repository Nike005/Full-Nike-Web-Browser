package acr.browser.lightning.reading;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Marker;

public class HtmlFetcher {
    private static final Pattern SPACE = Pattern.compile(StringUtils.SPACE);
    private String accept = "application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5";
    private SCache cache;
    private String cacheControl = "max-age=0";
    private final AtomicInteger cacheCounter = new AtomicInteger(0);
    private String charset = "UTF-8";
    private ArticleTextExtractor extractor = new ArticleTextExtractor();
    private Set<String> furtherResolveNecessary = new LinkedHashSet<String>() {
        {
            add("bit.ly");
            add("cli.gs");
            add("deck.ly");
            add("fb.me");
            add("feedproxy.google.com");
            add("flic.kr");
            add("fur.ly");
            add("goo.gl");
            add("is.gd");
            add("ink.co");
            add("j.mp");
            add("lnkd.in");
            add("on.fb.me");
            add("ow.ly");
            add("plurl.us");
            add("sns.mx");
            add("snurl.com");
            add("su.pr");
            add("t.co");
            add("tcrn.ch");
            add("tl.gd");
            add("tiny.cc");
            add("tinyurl.com");
            add("tmi.me");
            add("tr.im");
            add("twurl.nl");
        }
    };
    private String language = "en-us";
    private int maxTextLength = -1;
    private String referrer = "http://jetsli.de/crawler";
    private String userAgent = ("Mozilla/5.0 (compatible; Jetslide; +" + this.referrer + ')');

    static {
        SHelper.enableCookieMgmt();
        SHelper.enableUserAgentOverwrite();
        SHelper.enableAnySSL();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: java.io.BufferedWriter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: java.io.BufferedReader} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void main(java.lang.String[] r7) throws java.lang.Exception {
        /*
            r7 = 0
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ all -> 0x0075 }
            java.io.FileReader r1 = new java.io.FileReader     // Catch:{ all -> 0x0075 }
            java.lang.String r2 = "urls.txt"
            r1.<init>(r2)     // Catch:{ all -> 0x0075 }
            r0.<init>(r1)     // Catch:{ all -> 0x0075 }
            java.util.LinkedHashSet r1 = new java.util.LinkedHashSet     // Catch:{ all -> 0x0071 }
            r1.<init>()     // Catch:{ all -> 0x0071 }
        L_0x0012:
            java.lang.String r2 = r0.readLine()     // Catch:{ all -> 0x0071 }
            if (r2 == 0) goto L_0x006a
            r3 = 34
            int r4 = r2.indexOf(r3)     // Catch:{ all -> 0x0071 }
            r5 = 1
            int r4 = r4 + r5
            int r3 = r2.indexOf(r3, r4)     // Catch:{ all -> 0x0071 }
            java.lang.String r2 = r2.substring(r4, r3)     // Catch:{ all -> 0x0071 }
            java.lang.String r3 = acr.browser.lightning.reading.SHelper.extractDomain(r2, r5)     // Catch:{ all -> 0x0071 }
            java.lang.String r4 = ""
            boolean r5 = r1.contains(r3)     // Catch:{ all -> 0x0071 }
            if (r5 == 0) goto L_0x0037
            java.lang.String r4 = "2"
            goto L_0x003a
        L_0x0037:
            r1.add(r3)     // Catch:{ all -> 0x0071 }
        L_0x003a:
            acr.browser.lightning.reading.HtmlFetcher r5 = new acr.browser.lightning.reading.HtmlFetcher     // Catch:{ all -> 0x0071 }
            r5.<init>()     // Catch:{ all -> 0x0071 }
            r6 = 2000(0x7d0, float:2.803E-42)
            java.lang.String r2 = r5.fetchAsString(r2, r6)     // Catch:{ all -> 0x0071 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0071 }
            r5.<init>()     // Catch:{ all -> 0x0071 }
            r5.append(r3)     // Catch:{ all -> 0x0071 }
            r5.append(r4)     // Catch:{ all -> 0x0071 }
            java.lang.String r3 = ".html"
            r5.append(r3)     // Catch:{ all -> 0x0071 }
            java.lang.String r3 = r5.toString()     // Catch:{ all -> 0x0071 }
            java.io.BufferedWriter r4 = new java.io.BufferedWriter     // Catch:{ all -> 0x0071 }
            java.io.FileWriter r5 = new java.io.FileWriter     // Catch:{ all -> 0x0071 }
            r5.<init>(r3)     // Catch:{ all -> 0x0071 }
            r4.<init>(r5)     // Catch:{ all -> 0x0071 }
            r4.write(r2)     // Catch:{ all -> 0x0068 }
            r7 = r4
            goto L_0x0012
        L_0x0068:
            r1 = move-exception
            goto L_0x0073
        L_0x006a:
            acr.browser.lightning.utils.C3245Utils.close((java.io.Closeable) r0)
            acr.browser.lightning.utils.C3245Utils.close((java.io.Closeable) r7)
            return
        L_0x0071:
            r1 = move-exception
            r4 = r7
        L_0x0073:
            r7 = r0
            goto L_0x0077
        L_0x0075:
            r1 = move-exception
            r4 = r7
        L_0x0077:
            acr.browser.lightning.utils.C3245Utils.close((java.io.Closeable) r7)
            acr.browser.lightning.utils.C3245Utils.close((java.io.Closeable) r4)
            goto L_0x007f
        L_0x007e:
            throw r1
        L_0x007f:
            goto L_0x007e
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.reading.HtmlFetcher.main(java.lang.String[]):void");
    }

    public void setExtractor(ArticleTextExtractor articleTextExtractor) {
        this.extractor = articleTextExtractor;
    }

    public ArticleTextExtractor getExtractor() {
        return this.extractor;
    }

    public HtmlFetcher setCache(SCache sCache) {
        this.cache = sCache;
        return this;
    }

    public SCache getCache() {
        return this.cache;
    }

    public int getCacheCounter() {
        return this.cacheCounter.get();
    }

    public HtmlFetcher clearCacheCounter() {
        this.cacheCounter.set(0);
        return this;
    }

    public HtmlFetcher setMaxTextLength(int i) {
        this.maxTextLength = i;
        return this;
    }

    public int getMaxTextLength() {
        return this.maxTextLength;
    }

    public void setAccept(String str) {
        this.accept = str;
    }

    public void setCharset(String str) {
        this.charset = str;
    }

    public void setCacheControl(String str) {
        this.cacheControl = str;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public String getReferrer() {
        return this.referrer;
    }

    public HtmlFetcher setReferrer(String str) {
        this.referrer = str;
        return this;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setUserAgent(String str) {
        this.userAgent = str;
    }

    public String getAccept() {
        return this.accept;
    }

    public String getCacheControl() {
        return this.cacheControl;
    }

    public String getCharset() {
        return this.charset;
    }

    public JResult fetchAndExtract(String str, int i, boolean z) throws Exception {
        return fetchAndExtract(str, i, z, 0, false);
    }

    private JResult fetchAndExtract(String str, int i, boolean z, int i2, boolean z2) throws Exception {
        String str2;
        String removeHashbang = SHelper.removeHashbang(str);
        String urlFromUglyGoogleRedirect = SHelper.getUrlFromUglyGoogleRedirect(removeHashbang);
        if (!(urlFromUglyGoogleRedirect == null && (urlFromUglyGoogleRedirect = SHelper.getUrlFromUglyFacebookRedirect(removeHashbang)) == null)) {
            removeHashbang = urlFromUglyGoogleRedirect;
        }
        if (z) {
            JResult fromCache = getFromCache(removeHashbang, str);
            if (fromCache != null) {
                return fromCache;
            }
            String resolvedUrl = getResolvedUrl(removeHashbang, i, 0);
            if (resolvedUrl.isEmpty()) {
                JResult jResult = new JResult();
                SCache sCache = this.cache;
                if (sCache != null) {
                    sCache.put(removeHashbang, jResult);
                }
                return jResult.setUrl(removeHashbang);
            } else if (!resolvedUrl.equals(removeHashbang)) {
                removeHashbang = SHelper.useDomainOfFirstArg4Second(removeHashbang, resolvedUrl);
            }
        }
        JResult fromCache2 = getFromCache(removeHashbang, str);
        if (fromCache2 != null) {
            return fromCache2;
        }
        JResult jResult2 = new JResult();
        jResult2.setUrl(removeHashbang);
        jResult2.setOriginalUrl(str);
        SCache sCache2 = this.cache;
        if (sCache2 != null) {
            sCache2.put(str, jResult2);
            this.cache.put(removeHashbang, jResult2);
        }
        String lowerCase = removeHashbang.toLowerCase();
        if (!SHelper.isDoc(lowerCase) && !SHelper.isApp(lowerCase) && !SHelper.isPackage(lowerCase)) {
            if (SHelper.isVideo(lowerCase) || SHelper.isAudio(lowerCase)) {
                jResult2.setVideoUrl(removeHashbang);
            } else if (SHelper.isImage(lowerCase)) {
                jResult2.setImageUrl(removeHashbang);
            } else {
                if (z2) {
                    try {
                        str2 = getURLtoBreakCache(removeHashbang);
                    } catch (IOException unused) {
                    }
                } else {
                    str2 = removeHashbang;
                }
                this.extractor.extractContent(jResult2, fetchAsString(str2, i), i2);
                if (jResult2.getFaviconUrl().isEmpty()) {
                    jResult2.setFaviconUrl(SHelper.getDefaultFavicon(removeHashbang));
                }
                if (!jResult2.getFaviconUrl().isEmpty()) {
                    jResult2.setFaviconUrl(fixUrl(removeHashbang, jResult2.getFaviconUrl()));
                }
                if (!jResult2.getImageUrl().isEmpty()) {
                    jResult2.setImageUrl(fixUrl(removeHashbang, jResult2.getImageUrl()));
                }
                if (!jResult2.getVideoUrl().isEmpty()) {
                    jResult2.setVideoUrl(fixUrl(removeHashbang, jResult2.getVideoUrl()));
                }
                if (!jResult2.getRssUrl().isEmpty()) {
                    jResult2.setRssUrl(fixUrl(removeHashbang, jResult2.getRssUrl()));
                }
            }
        }
        jResult2.setText(lessText(jResult2.getText()));
        synchronized (jResult2) {
            jResult2.notifyAll();
        }
        return jResult2;
    }

    private static String getURLtoBreakCache(String str) {
        try {
            URL url = new URL(str);
            if (url.getQuery() == null || !url.getQuery().isEmpty()) {
                return str + "&1";
            }
            return str + "?1";
        } catch (MalformedURLException unused) {
            return str;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0009, code lost:
        r0 = r3.length();
        r1 = r2.maxTextLength;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String lessText(java.lang.String r3) {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x0005
            java.lang.String r3 = ""
            return r3
        L_0x0005:
            int r0 = r2.maxTextLength
            if (r0 < 0) goto L_0x0016
            int r0 = r3.length()
            int r1 = r2.maxTextLength
            if (r0 <= r1) goto L_0x0016
            r0 = 0
            java.lang.String r3 = r3.substring(r0, r1)
        L_0x0016:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.reading.HtmlFetcher.lessText(java.lang.String):java.lang.String");
    }

    private static String fixUrl(String str, String str2) {
        return SHelper.useDomainOfFirstArg4Second(str, str2);
    }

    private String fetchAsString(String str, int i) throws IOException {
        return fetchAsString(str, i, true);
    }

    private String fetchAsString(String str, int i, boolean z) throws IOException {
        InputStream inputStream;
        HttpURLConnection createUrlConnection = createUrlConnection(str, i, z);
        createUrlConnection.setInstanceFollowRedirects(true);
        String contentEncoding = createUrlConnection.getContentEncoding();
        if ("gzip".equalsIgnoreCase(contentEncoding)) {
            inputStream = new GZIPInputStream(createUrlConnection.getInputStream());
        } else if ("deflate".equalsIgnoreCase(contentEncoding)) {
            inputStream = new InflaterInputStream(createUrlConnection.getInputStream(), new Inflater(true));
        } else {
            inputStream = createUrlConnection.getInputStream();
        }
        return createConverter(str).streamToString(inputStream, Converter.extractEncoding(createUrlConnection.getContentType()));
    }

    private static Converter createConverter(String str) {
        return new Converter(str);
    }

    private String getResolvedUrl(String str, int i, int i2) {
        try {
            HttpURLConnection createUrlConnection = createUrlConnection(str, i, true);
            createUrlConnection.setInstanceFollowRedirects(false);
            createUrlConnection.setRequestMethod("HEAD");
            createUrlConnection.connect();
            int responseCode = createUrlConnection.getResponseCode();
            createUrlConnection.getInputStream().close();
            if (responseCode == 200) {
                return str;
            }
            String headerField = createUrlConnection.getHeaderField("Location");
            if (responseCode / 100 != 3 || headerField == null || i2 >= 5) {
                return str;
            }
            String replaceAll = SPACE.matcher(headerField).replaceAll(Marker.ANY_NON_NULL_MARKER);
            if (str.contains("://bit.ly") || str.contains("://is.gd")) {
                replaceAll = encodeUriFromHeader(replaceAll);
            }
            return getResolvedUrl(SHelper.useDomainOfFirstArg4Second(str, replaceAll), i, i2 + 1);
        } catch (Exception unused) {
            return "";
        }
    }

    private static String encodeUriFromHeader(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        for (char c : str.toCharArray()) {
            if (c < 128) {
                sb.append(c);
            } else {
                sb.append(String.format("%%%02X", new Object[]{Integer.valueOf(c)}));
            }
        }
        return sb.toString();
    }

    private HttpURLConnection createUrlConnection(String str, int i, boolean z) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection(Proxy.NO_PROXY);
        httpURLConnection.setRequestProperty("User-Agent", this.userAgent);
        httpURLConnection.setRequestProperty("Accept", this.accept);
        if (z) {
            httpURLConnection.setRequestProperty("Accept-Language", this.language);
            httpURLConnection.setRequestProperty("content-charset", this.charset);
            httpURLConnection.addRequestProperty("Referer", this.referrer);
            httpURLConnection.setRequestProperty("Cache-Control", this.cacheControl);
        }
        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
        httpURLConnection.setConnectTimeout(i);
        httpURLConnection.setReadTimeout(i);
        return httpURLConnection;
    }

    private JResult getFromCache(String str, String str2) {
        JResult jResult;
        SCache sCache = this.cache;
        if (sCache == null || (jResult = sCache.get(str)) == null) {
            return null;
        }
        jResult.setUrl(str);
        jResult.setOriginalUrl(str2);
        this.cacheCounter.addAndGet(1);
        return jResult;
    }
}
