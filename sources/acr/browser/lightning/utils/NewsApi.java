package acr.browser.lightning.utils;

import acr.browser.lightning.domain.News;
import acr.browser.lightning.domain.NewsCategory;
import android.os.AsyncTask;
import android.util.LruCache;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.xmlpull.v1.XmlPullParserException;

public class NewsApi {
    private static NewsApi INSTANCE = null;
    private static final long NEWS_UPDATE_TIME = 300000;
    /* access modifiers changed from: private */
    public static String[] categories = new String[0];
    /* access modifiers changed from: private */
    public long lastUpdateTime;
    /* access modifiers changed from: private */
    public LruCache<String, List<NewsCategory>> newsCache = new LruCache<>(4194304);
    private Random random = new Random();

    public interface NewsCallback {
        void onNewsResult(NewsCategory newsCategory);
    }

    private NewsApi() {
    }

    public static NewsApi getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NewsApi();
        }
        return INSTANCE;
    }

    public void getNews(final String str, final String str2, final NewsCallback newsCallback) {
        new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            public Void doInBackground(Void... voidArr) {
                try {
                    if (NewsApi.this.newsCache.get("news") == null || new Date().getTime() - NewsApi.this.lastUpdateTime >= 300000) {
                        ArrayList arrayList = new ArrayList();
                        NewsApi.this.loadTopStories(str, str2, arrayList, newsCallback);
                        for (String access$400 : NewsApi.categories) {
                            NewsApi.this.loadCategory(str, str2, access$400, arrayList, newsCallback);
                        }
                        NewsApi.this.newsCache.put("news", arrayList);
                        long unused = NewsApi.this.lastUpdateTime = new Date().getTime();
                        return null;
                    }
                    for (NewsCategory onNewsResult : (List) NewsApi.this.newsCache.get("news")) {
                        newsCallback.onNewsResult(onNewsResult);
                    }
                    return null;
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException | ParseException | XmlPullParserException e2) {
                    e2.printStackTrace();
                    return null;
                }
            }
        }.execute(new Void[0]);
    }

    public void getTopStoriesNews(final String str, final String str2, final NewsCallback newsCallback) {
        new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            public Void doInBackground(Void... voidArr) {
                try {
                    NewsApi.this.loadTopStories(str, str2, new ArrayList(), newsCallback);
                    return null;
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException | ParseException | XmlPullParserException e2) {
                    e2.printStackTrace();
                    return null;
                }
            }
        }.execute(new Void[0]);
    }

    /* access modifiers changed from: private */
    public void loadTopStories(String str, String str2, List<NewsCategory> list, NewsCallback newsCallback) throws IOException, ParseException, XmlPullParserException {
        NewsCategory parse = parse(new URL("https://news.google.com/news?cf=all&hl=" + str2 + "&pz=1&output=rss").openStream());
        StringBuilder sb = new StringBuilder();
        sb.append("http://frame.appsgeyser.com/api/news/rss.php?");
        sb.append(str);
        NewsCategory parse2 = parse(new URL(sb.toString()).openStream());
        if ((parse == null || parse.getNewsList().size() == 0) && parse2 != null) {
            parse = parse2;
        } else if (parse2 != null) {
            for (News next : parse2.getNewsList()) {
                if (next.isAds()) {
                    parse.getNewsList().add(Math.abs(this.random.nextInt()) % parse.getNewsList().size(), next);
                } else {
                    parse.getNewsList().add(next);
                }
            }
        }
        list.add(parse);
        newsCallback.onNewsResult(parse);
    }

    /* access modifiers changed from: private */
    public void loadCategory(String str, String str2, String str3, List<NewsCategory> list, NewsCallback newsCallback) throws IOException, ParseException, XmlPullParserException {
        NewsCategory parse = parse(new URL("https://news.google.com/news?cf=all&hl=" + str2 + "&pz=1&output=rss&topic=" + str3).openStream());
        NewsCategory parse2 = parse(new URL("http://frame.appsgeyser.com/api/news/" + str3 + "/rss.php?" + str).openStream());
        if ((parse == null || parse.getNewsList().size() == 0) && parse2 != null) {
            parse = parse2;
        } else if (parse2 != null) {
            for (News next : parse2.getNewsList()) {
                if (next.isAds()) {
                    parse.getNewsList().add(Math.abs(this.random.nextInt()) % parse.getNewsList().size(), next);
                } else {
                    parse.getNewsList().add(next);
                }
            }
        }
        list.add(parse);
        newsCallback.onNewsResult(parse);
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0140, code lost:
        r5 = new java.lang.StringBuilder();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0145, code lost:
        r17 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:?, code lost:
        r5.append("https:");
        r5.append(r6.attr("src"));
        r15 = r5.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0165, code lost:
        r0 = th;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public acr.browser.lightning.domain.NewsCategory parse(java.io.InputStream r25) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException, java.text.ParseException {
        /*
            r24 = this;
            java.lang.String r1 = "src"
            r2 = 0
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Exception -> 0x0205 }
            r3.<init>()     // Catch:{ Exception -> 0x0205 }
            org.xmlpull.v1.XmlPullParser r4 = android.util.Xml.newPullParser()     // Catch:{ Exception -> 0x0205 }
            r5 = r25
            r4.setInput(r5, r2)     // Catch:{ Exception -> 0x0205 }
            int r0 = r4.getEventType()     // Catch:{ Exception -> 0x0205 }
            java.lang.String r7 = ""
            r8 = r7
            r9 = r8
            r11 = r9
            r12 = r11
            r13 = r12
            r14 = r13
            r15 = r14
            r2 = 0
            r10 = 0
        L_0x0020:
            r6 = 1
            if (r0 == r6) goto L_0x01f4
            r6 = 2
            java.lang.String r5 = "item"
            if (r0 != r6) goto L_0x0175
            java.lang.String r0 = r4.getName()     // Catch:{ Exception -> 0x0205 }
            boolean r0 = r0.equalsIgnoreCase(r5)     // Catch:{ Exception -> 0x0205 }
            if (r0 == 0) goto L_0x0037
            r17 = r7
            r10 = 1
            goto L_0x01ea
        L_0x0037:
            java.lang.String r0 = r4.getName()     // Catch:{ Exception -> 0x0205 }
            java.lang.String r5 = "title"
            boolean r0 = r0.equalsIgnoreCase(r5)     // Catch:{ Exception -> 0x0205 }
            if (r0 == 0) goto L_0x008f
            java.lang.String r0 = " – "
            java.lang.String r5 = " - "
            if (r10 == 0) goto L_0x006f
            java.lang.String r9 = r4.nextText()     // Catch:{ Exception -> 0x0205 }
            boolean r0 = r9.contains(r0)     // Catch:{ Exception -> 0x0205 }
            if (r0 != 0) goto L_0x0059
            boolean r0 = r9.contains(r5)     // Catch:{ Exception -> 0x0205 }
            if (r0 == 0) goto L_0x00a2
        L_0x0059:
            java.lang.String r0 = " – | - "
            java.lang.String[] r0 = r9.split(r0)     // Catch:{ Exception -> 0x0205 }
            r5 = 0
            r9 = r0[r5]     // Catch:{ Exception -> 0x0205 }
            int r5 = r0.length     // Catch:{ Exception -> 0x0205 }
            r6 = 1
            if (r5 <= r6) goto L_0x00a2
            boolean r5 = r13.equals(r7)     // Catch:{ Exception -> 0x0205 }
            if (r5 == 0) goto L_0x00a2
            r13 = r0[r6]     // Catch:{ Exception -> 0x0205 }
            goto L_0x00a2
        L_0x006f:
            java.lang.String r8 = r4.nextText()     // Catch:{ Exception -> 0x0205 }
            boolean r6 = r8.contains(r5)     // Catch:{ Exception -> 0x0205 }
            if (r6 == 0) goto L_0x0081
            java.lang.String[] r0 = r8.split(r5)     // Catch:{ Exception -> 0x0205 }
            r5 = 0
            r8 = r0[r5]     // Catch:{ Exception -> 0x0205 }
            goto L_0x00a2
        L_0x0081:
            boolean r5 = r8.contains(r0)     // Catch:{ Exception -> 0x0205 }
            if (r5 == 0) goto L_0x00a2
            java.lang.String[] r0 = r8.split(r0)     // Catch:{ Exception -> 0x0205 }
            r6 = 0
            r8 = r0[r6]     // Catch:{ Exception -> 0x0205 }
            goto L_0x00a2
        L_0x008f:
            r6 = 0
            java.lang.String r0 = r4.getName()     // Catch:{ Exception -> 0x0205 }
            java.lang.String r5 = "link"
            boolean r0 = r0.equalsIgnoreCase(r5)     // Catch:{ Exception -> 0x0205 }
            if (r0 == 0) goto L_0x00a6
            if (r10 == 0) goto L_0x00a2
            java.lang.String r11 = r4.nextText()     // Catch:{ Exception -> 0x0205 }
        L_0x00a2:
            r17 = r7
            goto L_0x01ea
        L_0x00a6:
            java.lang.String r0 = r4.getName()     // Catch:{ Exception -> 0x0205 }
            java.lang.String r5 = "imageLink"
            boolean r0 = r0.equalsIgnoreCase(r5)     // Catch:{ Exception -> 0x0205 }
            if (r0 == 0) goto L_0x00b9
            if (r10 == 0) goto L_0x00a2
            java.lang.String r15 = r4.nextText()     // Catch:{ Exception -> 0x0205 }
            goto L_0x00a2
        L_0x00b9:
            java.lang.String r0 = r4.getName()     // Catch:{ Exception -> 0x0205 }
            java.lang.String r5 = "isAds"
            boolean r0 = r0.equalsIgnoreCase(r5)     // Catch:{ Exception -> 0x0205 }
            if (r0 == 0) goto L_0x00d2
            if (r10 == 0) goto L_0x00a2
            java.lang.String r0 = r4.nextText()     // Catch:{ Exception -> 0x0205 }
            java.lang.String r2 = "true"
            boolean r2 = r0.equals(r2)     // Catch:{ Exception -> 0x0205 }
            goto L_0x00a2
        L_0x00d2:
            java.lang.String r0 = r4.getName()     // Catch:{ Exception -> 0x0205 }
            java.lang.String r5 = "source"
            boolean r0 = r0.equalsIgnoreCase(r5)     // Catch:{ Exception -> 0x0205 }
            if (r0 == 0) goto L_0x00e5
            if (r10 == 0) goto L_0x00a2
            java.lang.String r13 = r4.nextText()     // Catch:{ Exception -> 0x0205 }
            goto L_0x00a2
        L_0x00e5:
            java.lang.String r0 = r4.getName()     // Catch:{ Exception -> 0x0205 }
            java.lang.String r5 = "pubDate"
            boolean r0 = r0.equalsIgnoreCase(r5)     // Catch:{ Exception -> 0x0205 }
            if (r0 == 0) goto L_0x00f8
            if (r10 == 0) goto L_0x00a2
            java.lang.String r12 = r4.nextText()     // Catch:{ Exception -> 0x0205 }
            goto L_0x00a2
        L_0x00f8:
            java.lang.String r0 = r4.getName()     // Catch:{ Exception -> 0x0205 }
            java.lang.String r5 = "description"
            boolean r0 = r0.equalsIgnoreCase(r5)     // Catch:{ Exception -> 0x0205 }
            if (r0 == 0) goto L_0x00a2
            if (r10 == 0) goto L_0x00a2
            java.lang.String r0 = r4.nextText()     // Catch:{ Exception -> 0x0205 }
            java.lang.String r0 = org.apache.commons.lang3.StringEscapeUtils.unescapeHtml4(r0)     // Catch:{ Exception -> 0x0205 }
            java.lang.String r14 = org.apache.commons.lang3.StringEscapeUtils.unescapeHtml3(r0)     // Catch:{ Exception -> 0x0205 }
            org.jsoup.nodes.Document r0 = org.jsoup.Jsoup.parse(r14)     // Catch:{ all -> 0x0167 }
            boolean r5 = r15.equals(r7)     // Catch:{ all -> 0x0167 }
            if (r5 == 0) goto L_0x015d
            java.lang.String r5 = "img"
            org.jsoup.select.Elements r5 = r0.select(r5)     // Catch:{ all -> 0x0167 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x0167 }
        L_0x0126:
            boolean r16 = r5.hasNext()     // Catch:{ all -> 0x0167 }
            if (r16 == 0) goto L_0x015d
            java.lang.Object r16 = r5.next()     // Catch:{ all -> 0x0167 }
            r6 = r16
            org.jsoup.nodes.Element r6 = (org.jsoup.nodes.Element) r6     // Catch:{ all -> 0x0167 }
            r16 = r5
            java.lang.String r5 = r6.attr(r1)     // Catch:{ all -> 0x0167 }
            boolean r5 = r5.equals(r7)     // Catch:{ all -> 0x0167 }
            if (r5 != 0) goto L_0x0159
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0167 }
            r5.<init>()     // Catch:{ all -> 0x0167 }
            r17 = r7
            java.lang.String r7 = "https:"
            r5.append(r7)     // Catch:{ all -> 0x0165 }
            java.lang.String r6 = r6.attr(r1)     // Catch:{ all -> 0x0165 }
            r5.append(r6)     // Catch:{ all -> 0x0165 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0165 }
            r15 = r5
            goto L_0x015f
        L_0x0159:
            r5 = r16
            r6 = 0
            goto L_0x0126
        L_0x015d:
            r17 = r7
        L_0x015f:
            java.lang.String r14 = r0.text()     // Catch:{ all -> 0x0165 }
            goto L_0x01ea
        L_0x0165:
            r0 = move-exception
            goto L_0x016a
        L_0x0167:
            r0 = move-exception
            r17 = r7
        L_0x016a:
            java.lang.String r5 = "jsoup"
            java.lang.String r0 = r0.getMessage()     // Catch:{ Exception -> 0x0205 }
            android.util.Log.w(r5, r0)     // Catch:{ Exception -> 0x0205 }
            goto L_0x01ea
        L_0x0175:
            r17 = r7
            r6 = 3
            if (r0 != r6) goto L_0x01ea
            java.lang.String r0 = r4.getName()     // Catch:{ Exception -> 0x0205 }
            boolean r0 = r0.equalsIgnoreCase(r5)     // Catch:{ Exception -> 0x0205 }
            if (r0 == 0) goto L_0x01ea
            acr.browser.lightning.domain.News r0 = new acr.browser.lightning.domain.News     // Catch:{ Exception -> 0x0205 }
            r0.<init>()     // Catch:{ Exception -> 0x0205 }
            r0.setTitle(r9)     // Catch:{ Exception -> 0x0205 }
            java.lang.String r5 = "url=(https*.*)"
            java.util.regex.Pattern r5 = java.util.regex.Pattern.compile(r5)     // Catch:{ Exception -> 0x0205 }
            java.util.regex.Matcher r5 = r5.matcher(r11)     // Catch:{ Exception -> 0x0205 }
            boolean r6 = r5.find()     // Catch:{ Exception -> 0x0205 }
            if (r6 == 0) goto L_0x01a1
            r6 = 1
            java.lang.String r11 = r5.group(r6)     // Catch:{ Exception -> 0x0205 }
        L_0x01a1:
            r0.setLink(r11)     // Catch:{ Exception -> 0x0205 }
            java.text.SimpleDateFormat r5 = new java.text.SimpleDateFormat     // Catch:{ Exception -> 0x0205 }
            java.lang.String r6 = "EEE, dd MMM yyyy HH:mm:ss zzz"
            java.util.Locale r7 = java.util.Locale.US     // Catch:{ Exception -> 0x0205 }
            r5.<init>(r6, r7)     // Catch:{ Exception -> 0x0205 }
            java.util.Date r5 = r5.parse(r12)     // Catch:{ Exception -> 0x0205 }
            long r18 = r5.getTime()     // Catch:{ Exception -> 0x0205 }
            long r20 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0205 }
            r22 = 1000(0x3e8, double:4.94E-321)
            java.lang.CharSequence r5 = android.text.format.DateUtils.getRelativeTimeSpanString(r18, r20, r22)     // Catch:{ Exception -> 0x0205 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x0205 }
            r0.setDate(r5)     // Catch:{ Exception -> 0x0205 }
            r0.setText(r14)     // Catch:{ Exception -> 0x0205 }
            r0.setSource(r13)     // Catch:{ Exception -> 0x0205 }
            r0.setImageLink(r15)     // Catch:{ Exception -> 0x0205 }
            r0.setAds(r2)     // Catch:{ Exception -> 0x0205 }
            java.lang.String r2 = r0.getTitle()     // Catch:{ Exception -> 0x0205 }
            java.lang.String r5 = "This RSS feed URL is deprecated"
            boolean r2 = r2.equals(r5)     // Catch:{ Exception -> 0x0205 }
            if (r2 != 0) goto L_0x01e1
            r3.add(r0)     // Catch:{ Exception -> 0x0205 }
        L_0x01e1:
            r9 = r17
            r11 = r9
            r12 = r11
            r13 = r12
            r14 = r13
            r15 = r14
            r2 = 0
            r10 = 0
        L_0x01ea:
            int r0 = r4.next()     // Catch:{ Exception -> 0x0205 }
            r5 = r25
            r7 = r17
            goto L_0x0020
        L_0x01f4:
            acr.browser.lightning.domain.NewsCategory r0 = new acr.browser.lightning.domain.NewsCategory     // Catch:{ Exception -> 0x0205 }
            r0.<init>()     // Catch:{ Exception -> 0x0205 }
            r0.setName(r8)     // Catch:{ Exception -> 0x0205 }
            r0.setNewsList(r3)     // Catch:{ Exception -> 0x0205 }
            r25.close()
            return r0
        L_0x0203:
            r0 = move-exception
            goto L_0x020e
        L_0x0205:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0203 }
            r25.close()
            r1 = 0
            return r1
        L_0x020e:
            r25.close()
            goto L_0x0213
        L_0x0212:
            throw r0
        L_0x0213:
            goto L_0x0212
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.utils.NewsApi.parse(java.io.InputStream):acr.browser.lightning.domain.NewsCategory");
    }
}
