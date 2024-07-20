package acr.browser.lightning.reading;

import com.mopub.common.AdType;
import com.mopub.common.Constants;
import com.mopub.mobileads.facebookaudiencenetwork.BuildConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector;
import org.slf4j.Marker;

public class ArticleTextExtractor {
    private static final List<Pattern> CLEAN_AUTHOR_PATTERNS = Collections.singletonList(Pattern.compile("By\\S*(.*)[\\.,].*"));
    private static final boolean DEBUG_WEIGHTS = false;
    private static final OutputFormatter DEFAULT_FORMATTER = new OutputFormatter();
    private static final Set<String> IGNORED_TITLE_PARTS = new LinkedHashSet<String>() {
        {
            add("hacker news");
            add(BuildConfig.NETWORK_NAME);
            add("home");
            add("articles");
        }
    };
    private static final Pattern IGNORE_AUTHOR_PARTS = Pattern.compile("by|name|author|posted|twitter|handle|news", 2);
    private static final int MAX_AUTHOR_DESC_LENGHT = 1000;
    private static final int MAX_AUTHOR_NAME_LENGHT = 255;
    private static final int MAX_IMAGE_LENGHT = 255;
    private static final int MAX_LOG_LENGTH = 200;
    private static final int MIN_AUTHOR_NAME_LENGTH = 4;
    private static final Pattern NEGATIVE_STYLE = Pattern.compile("hidden|display: ?none|font-size: ?small");
    private static final Pattern NODES = Pattern.compile("p|div|td|h1|h2|article|section");
    private Pattern NEGATIVE;
    private Pattern POSITIVE;
    private Pattern UNLIKELY;
    private OutputFormatter formatter = DEFAULT_FORMATTER;
    private String negativeStr;
    private String positiveStr;
    private String unlikelyStr;

    private static String removeTitleFromText(String str, String str2) {
        return str;
    }

    public ArticleTextExtractor() {
        setUnlikely("com(bx|ment|munity)|dis(qus|cuss)|e(xtra|[-]?mail)|foot|header|menu|re(mark|ply)|rss|sh(are|outbox)|sponsora(d|ll|gegate|rchive|ttachment)|(pag(er|ination))|popup|print|login|si(debar|gn|ngle)");
        setPositive("(^(body|content|h?entry|main|page|post|text|blog|story|haupt))|arti(cle|kel)|instapaper_body");
        setNegative("nav($|igation)|user|com(ment|bx)|(^com-)|contact|foot|masthead|(me(dia|ta))|outbrain|promo|related|scroll|(sho(utbox|pping))|sidebar|sponsor|tags|tool|widget|player|disclaimer|toc|infobox|vcard");
    }

    private ArticleTextExtractor setUnlikely(String str) {
        this.unlikelyStr = str;
        this.UNLIKELY = Pattern.compile(str);
        return this;
    }

    public ArticleTextExtractor addUnlikely(String str) {
        return setUnlikely(this.unlikelyStr + '|' + str);
    }

    private ArticleTextExtractor setPositive(String str) {
        this.positiveStr = str;
        this.POSITIVE = Pattern.compile(str);
        return this;
    }

    public ArticleTextExtractor addPositive(String str) {
        return setPositive(this.positiveStr + '|' + str);
    }

    private ArticleTextExtractor setNegative(String str) {
        this.negativeStr = str;
        this.NEGATIVE = Pattern.compile(str);
        return this;
    }

    public ArticleTextExtractor addNegative(String str) {
        setNegative(this.negativeStr + '|' + str);
        return this;
    }

    public void setOutputFormatter(OutputFormatter outputFormatter) {
        this.formatter = outputFormatter;
    }

    public JResult extractContent(String str, int i) throws Exception {
        return extractContent(new JResult(), str, i);
    }

    public JResult extractContent(String str) throws Exception {
        return extractContent(new JResult(), str, 0);
    }

    public JResult extractContent(JResult jResult, String str, int i) throws Exception {
        return extractContent(jResult, str, this.formatter, (Boolean) true, i);
    }

    public JResult extractContent(JResult jResult, String str) throws Exception {
        return extractContent(jResult, str, this.formatter, (Boolean) true, 0);
    }

    private JResult extractContent(JResult jResult, String str, OutputFormatter outputFormatter, Boolean bool, int i) throws Exception {
        if (!str.isEmpty()) {
            return extractContent(jResult, Jsoup.parse(str), outputFormatter, bool, i);
        }
        throw new IllegalArgumentException("html string is empty!?");
    }

    private Element getBestMatchElement(Collection<Element> collection) {
        int i = -200;
        Element element = null;
        for (Element next : collection) {
            int weight = getWeight(next, false);
            if (weight > i) {
                element = next;
                i = weight;
            }
        }
        return element;
    }

    private JResult extractContent(JResult jResult, Document document, OutputFormatter outputFormatter, Boolean bool, int i) throws Exception {
        Document clone = document.clone();
        JResult extractContent = extractContent(jResult, document, outputFormatter, bool, i, true);
        return extractContent.getText().isEmpty() ? extractContent(jResult, clone, outputFormatter, bool, i, false) : extractContent;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x006c, code lost:
        r0 = new java.util.ArrayList();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private acr.browser.lightning.reading.JResult extractContent(acr.browser.lightning.reading.JResult r4, org.jsoup.nodes.Document r5, acr.browser.lightning.reading.OutputFormatter r6, java.lang.Boolean r7, int r8, boolean r9) {
        /*
            r3 = this;
            if (r5 == 0) goto L_0x0187
            java.lang.String r0 = extractTitle(r5)
            r4.setTitle(r0)
            java.lang.String r0 = extractDescription(r5)
            r4.setDescription(r0)
            java.lang.String r0 = extractCanonicalUrl(r5)
            r4.setCanonicalUrl(r0)
            java.lang.String r0 = extractType(r5)
            r4.setType(r0)
            java.lang.String r0 = extractSitename(r5)
            r4.setSitename(r0)
            java.lang.String r0 = extractLanguage(r5)
            r4.setLanguage(r0)
            java.lang.String r0 = r3.extractAuthorName(r5)
            r4.setAuthorName(r0)
            java.lang.String r0 = r4.getAuthorName()
            java.lang.String r0 = r3.extractAuthorDescription(r5, r0)
            r4.setAuthorDescription(r0)
            java.util.Date r0 = extractDate(r5)
            if (r0 != 0) goto L_0x0054
            java.lang.String r0 = r4.getUrl()
            java.lang.String r0 = acr.browser.lightning.reading.SHelper.estimateDate(r0)
            java.util.Date r0 = parseDate(r0)
            r4.setDate(r0)
            goto L_0x0057
        L_0x0054:
            r4.setDate(r0)
        L_0x0057:
            if (r9 == 0) goto L_0x005c
            prepareDocument(r5)
        L_0x005c:
            java.util.Collection r9 = getNodes(r5)
            org.jsoup.nodes.Element r9 = r3.getBestMatchElement(r9)
            if (r9 == 0) goto L_0x00ed
            boolean r0 = r7.booleanValue()
            if (r0 == 0) goto L_0x0087
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            org.jsoup.nodes.Element r1 = determineImageSource(r9, r0)
            if (r1 == 0) goto L_0x0087
            java.lang.String r2 = "src"
            java.lang.String r1 = r1.attr(r2)
            java.lang.String r1 = acr.browser.lightning.reading.SHelper.replaceSpaces(r1)
            r4.setImageUrl(r1)
            r4.setImages(r0)
        L_0x0087:
            java.lang.String r6 = r6.getFormattedText(r9)
            java.lang.String r0 = r4.getTitle()
            java.lang.String r6 = removeTitleFromText(r6, r0)
            int r0 = r6.length()
            java.lang.String r1 = r4.getTitle()
            int r1 = r1.length()
            if (r0 <= r1) goto L_0x00b0
            if (r8 <= 0) goto L_0x00ad
            int r0 = r6.length()
            if (r0 <= r8) goto L_0x00ad
            java.lang.String r6 = utf8truncate(r6, r8)
        L_0x00ad:
            r4.setText(r6)
        L_0x00b0:
            java.lang.String r6 = r9.toString()
            java.lang.String r8 = "a[href]"
            org.jsoup.select.Elements r8 = r9.select(r8)
            r9 = 0
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            java.util.Iterator r8 = r8.iterator()
        L_0x00c3:
            boolean r0 = r8.hasNext()
            if (r0 == 0) goto L_0x00ed
            java.lang.Object r0 = r8.next()
            org.jsoup.nodes.Element r0 = (org.jsoup.nodes.Element) r0
            java.lang.String r1 = r0.toString()
            int r9 = r9.intValue()
            int r9 = r6.indexOf(r1, r9)
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            java.lang.String r1 = "abs:href"
            java.lang.String r1 = r0.attr(r1)
            java.lang.String r0 = r0.text()
            r4.addLink(r1, r0, r9)
            goto L_0x00c3
        L_0x00ed:
            boolean r6 = r7.booleanValue()
            if (r6 == 0) goto L_0x0104
            java.lang.String r6 = r4.getImageUrl()
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto L_0x0104
            java.lang.String r6 = extractImageUrl(r5)
            r4.setImageUrl(r6)
        L_0x0104:
            java.lang.String r6 = extractRssUrl(r5)
            r4.setRssUrl(r6)
            java.lang.String r6 = extractVideoUrl(r5)
            r4.setVideoUrl(r6)
            java.lang.String r6 = extractFaviconUrl(r5)
            r4.setFaviconUrl(r6)
            java.util.Collection r5 = extractKeywords(r5)
            r4.setKeywords(r5)
            java.lang.String r5 = r4.getAuthorName()
            int r5 = r5.length()
            r6 = 255(0xff, float:3.57E-43)
            if (r5 <= r6) goto L_0x0137
            java.lang.String r5 = r4.getAuthorName()
            java.lang.String r5 = utf8truncate(r5, r6)
            r4.setAuthorName(r5)
        L_0x0137:
            java.lang.String r5 = r4.getAuthorDescription()
            java.lang.String r5 = getSnippet(r5)
            java.lang.String r7 = r4.getText()
            java.lang.String r7 = getSnippet(r7)
            boolean r7 = r7.equals(r5)
            java.lang.String r8 = ""
            if (r7 != 0) goto L_0x0176
            java.lang.String r7 = r4.getDescription()
            java.lang.String r7 = getSnippet(r7)
            boolean r5 = r7.equals(r5)
            if (r5 == 0) goto L_0x015e
            goto L_0x0176
        L_0x015e:
            java.lang.String r5 = r4.getAuthorDescription()
            int r5 = r5.length()
            r7 = 1000(0x3e8, float:1.401E-42)
            if (r5 <= r7) goto L_0x0179
            java.lang.String r5 = r4.getAuthorDescription()
            java.lang.String r5 = utf8truncate(r5, r7)
            r4.setAuthorDescription(r5)
            goto L_0x0179
        L_0x0176:
            r4.setAuthorDescription(r8)
        L_0x0179:
            java.lang.String r5 = r4.getImageUrl()
            int r5 = r5.length()
            if (r5 <= r6) goto L_0x0186
            r4.setImageUrl(r8)
        L_0x0186:
            return r4
        L_0x0187:
            java.lang.NullPointerException r4 = new java.lang.NullPointerException
            java.lang.String r5 = "missing document"
            r4.<init>(r5)
            goto L_0x0190
        L_0x018f:
            throw r4
        L_0x0190:
            goto L_0x018f
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.reading.ArticleTextExtractor.extractContent(acr.browser.lightning.reading.JResult, org.jsoup.nodes.Document, acr.browser.lightning.reading.OutputFormatter, java.lang.Boolean, int, boolean):acr.browser.lightning.reading.JResult");
    }

    private static String getSnippet(String str) {
        if (str.length() < 50) {
            return str;
        }
        return str.substring(0, 50);
    }

    private static String extractTitle(Document document) {
        String cleanTitle = cleanTitle(document.title());
        if (!cleanTitle.isEmpty()) {
            return cleanTitle;
        }
        String innerTrim = SHelper.innerTrim(document.select("head title").text());
        if (!innerTrim.isEmpty()) {
            return innerTrim;
        }
        String innerTrim2 = SHelper.innerTrim(document.select("head meta[name=title]").attr(Constants.VAST_TRACKER_CONTENT));
        if (!innerTrim2.isEmpty()) {
            return innerTrim2;
        }
        String innerTrim3 = SHelper.innerTrim(document.select("head meta[property=og:title]").attr(Constants.VAST_TRACKER_CONTENT));
        if (!innerTrim3.isEmpty()) {
            return innerTrim3;
        }
        String innerTrim4 = SHelper.innerTrim(document.select("head meta[name=twitter:title]").attr(Constants.VAST_TRACKER_CONTENT));
        return innerTrim4.isEmpty() ? SHelper.innerTrim(document.select("h1:first-of-type").text()) : innerTrim4;
    }

    private static String extractCanonicalUrl(Document document) {
        String replaceSpaces = SHelper.replaceSpaces(document.select("head link[rel=canonical]").attr("href"));
        if (!replaceSpaces.isEmpty()) {
            return replaceSpaces;
        }
        String replaceSpaces2 = SHelper.replaceSpaces(document.select("head meta[property=og:url]").attr(Constants.VAST_TRACKER_CONTENT));
        return replaceSpaces2.isEmpty() ? SHelper.replaceSpaces(document.select("head meta[name=twitter:url]").attr(Constants.VAST_TRACKER_CONTENT)) : replaceSpaces2;
    }

    private static String extractDescription(Document document) {
        String innerTrim = SHelper.innerTrim(document.select("head meta[name=description]").attr(Constants.VAST_TRACKER_CONTENT));
        if (!innerTrim.isEmpty()) {
            return innerTrim;
        }
        String innerTrim2 = SHelper.innerTrim(document.select("head meta[property=og:description]").attr(Constants.VAST_TRACKER_CONTENT));
        return innerTrim2.isEmpty() ? SHelper.innerTrim(document.select("head meta[name=twitter:description]").attr(Constants.VAST_TRACKER_CONTENT)) : innerTrim2;
    }

    private static Date extractDate(Document document) {
        Element first = document.select("meta[name=ptime]").first();
        String innerTrim = first != null ? SHelper.innerTrim(first.attr(Constants.VAST_TRACKER_CONTENT)) : "";
        if (innerTrim.isEmpty()) {
            innerTrim = SHelper.innerTrim(document.select("meta[name=utime]").attr(Constants.VAST_TRACKER_CONTENT));
        }
        if (innerTrim.isEmpty()) {
            innerTrim = SHelper.innerTrim(document.select("meta[name=pdate]").attr(Constants.VAST_TRACKER_CONTENT));
        }
        if (innerTrim.isEmpty()) {
            innerTrim = SHelper.innerTrim(document.select("meta[property=article:published]").attr(Constants.VAST_TRACKER_CONTENT));
        }
        if (innerTrim.isEmpty()) {
            return parseDate(innerTrim);
        }
        Elements select = document.select("meta[property=article:published_time]");
        if (!select.isEmpty()) {
            Element element = (Element) select.get(0);
            if (element.hasAttr(Constants.VAST_TRACKER_CONTENT)) {
                String attr = element.attr(Constants.VAST_TRACKER_CONTENT);
                try {
                    if (attr.endsWith("Z")) {
                        attr = attr.substring(0, attr.length() - 1) + "GMT-00:00";
                    } else {
                        attr = String.format(attr.substring(0, attr.length() - 6), new Object[]{attr.substring(attr.length() - 6, attr.length())});
                    }
                } catch (StringIndexOutOfBoundsException unused) {
                }
                return parseDate(attr);
            }
        }
        Elements select2 = document.select("meta[property=dateCreated], span[property=dateCreated]");
        if (!select2.isEmpty()) {
            Element element2 = (Element) select2.get(0);
            if (element2.hasAttr(Constants.VAST_TRACKER_CONTENT)) {
                return parseDate(element2.attr(Constants.VAST_TRACKER_CONTENT));
            }
            return parseDate(element2.text());
        }
        Elements select3 = document.select("meta[itemprop=datePublished], span[itemprop=datePublished]");
        if (!select3.isEmpty()) {
            Element element3 = (Element) select3.get(0);
            if (element3.hasAttr(Constants.VAST_TRACKER_CONTENT)) {
                return parseDate(element3.attr(Constants.VAST_TRACKER_CONTENT));
            }
            if (element3.hasAttr("value")) {
                return parseDate(element3.attr("value"));
            }
            return parseDate(element3.text());
        }
        Elements select4 = document.select("meta[name=OriginalPublicationDate]");
        if (!select4.isEmpty()) {
            Element element4 = (Element) select4.get(0);
            if (element4.hasAttr(Constants.VAST_TRACKER_CONTENT)) {
                return parseDate(element4.attr(Constants.VAST_TRACKER_CONTENT));
            }
        }
        Elements select5 = document.select("meta[name=DisplayDate]");
        if (!select5.isEmpty()) {
            Element element5 = (Element) select5.get(0);
            if (element5.hasAttr(Constants.VAST_TRACKER_CONTENT)) {
                return parseDate(element5.attr(Constants.VAST_TRACKER_CONTENT));
            }
        }
        Elements select6 = document.select("meta[name*=date]");
        if (!select6.isEmpty()) {
            Element element6 = (Element) select6.get(0);
            if (element6.hasAttr(Constants.VAST_TRACKER_CONTENT)) {
                return parseDate(element6.attr(Constants.VAST_TRACKER_CONTENT));
            }
        }
        Elements select7 = document.select(".date-header");
        if (!select7.isEmpty()) {
            return parseDate(((Element) select7.get(0)).text());
        }
        return null;
    }

    private static Date parseDate(String str) {
        return new Date(0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:58:0x0104  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String extractAuthorName(org.jsoup.nodes.Document r6) {
        /*
            r5 = this;
            java.lang.String r0 = ","
            java.lang.String r1 = "body [rel*=author]"
            org.jsoup.select.Elements r1 = r6.select(r1)
            org.jsoup.nodes.Element r1 = r1.first()
            java.lang.String r2 = ""
            if (r1 == 0) goto L_0x0019
            java.lang.String r1 = r1.ownText()
            java.lang.String r1 = acr.browser.lightning.reading.SHelper.innerTrim(r1)
            goto L_0x001a
        L_0x0019:
            r1 = r2
        L_0x001a:
            boolean r3 = r1.isEmpty()
            if (r3 == 0) goto L_0x00f8
            java.lang.String r3 = "head meta[name=author]"
            org.jsoup.select.Elements r3 = r6.select(r3)
            org.jsoup.nodes.Element r3 = r3.first()
            java.lang.String r4 = "content"
            if (r3 == 0) goto L_0x0036
            java.lang.String r1 = r3.attr(r4)
            java.lang.String r1 = acr.browser.lightning.reading.SHelper.innerTrim(r1)
        L_0x0036:
            boolean r3 = r1.isEmpty()
            if (r3 == 0) goto L_0x004a
            java.lang.String r1 = "head meta[property=article:author]"
            org.jsoup.select.Elements r1 = r6.select(r1)
            java.lang.String r1 = r1.attr(r4)
            java.lang.String r1 = acr.browser.lightning.reading.SHelper.innerTrim(r1)
        L_0x004a:
            boolean r3 = r1.isEmpty()
            if (r3 == 0) goto L_0x005e
            java.lang.String r1 = "head meta[property=twitter:creator]"
            org.jsoup.select.Elements r1 = r6.select(r1)
            java.lang.String r1 = r1.attr(r4)
            java.lang.String r1 = acr.browser.lightning.reading.SHelper.innerTrim(r1)
        L_0x005e:
            boolean r3 = r1.isEmpty()
            if (r3 == 0) goto L_0x0072
            java.lang.String r1 = "meta[itemprop=author], span[itemprop=author]"
            org.jsoup.select.Elements r1 = r6.select(r1)
            java.lang.String r1 = r1.attr(r4)
            java.lang.String r1 = acr.browser.lightning.reading.SHelper.innerTrim(r1)
        L_0x0072:
            boolean r3 = r1.isEmpty()
            if (r3 == 0) goto L_0x00f8
            java.lang.String r3 = "a[rel=author],.byline-name,.byLineTag,.byline,.author,.by,.writer,.address"
            org.jsoup.select.Elements r3 = r6.select(r3)     // Catch:{ Exception -> 0x00ee }
            if (r3 == 0) goto L_0x0086
            boolean r4 = r3.isEmpty()     // Catch:{ Exception -> 0x00ee }
            if (r4 == 0) goto L_0x008c
        L_0x0086:
            java.lang.String r3 = "body [class*=author]"
            org.jsoup.select.Elements r3 = r6.select(r3)     // Catch:{ Exception -> 0x00ee }
        L_0x008c:
            if (r3 == 0) goto L_0x0094
            boolean r4 = r3.isEmpty()     // Catch:{ Exception -> 0x00ee }
            if (r4 == 0) goto L_0x009a
        L_0x0094:
            java.lang.String r3 = "body [title*=author]"
            org.jsoup.select.Elements r3 = r6.select(r3)     // Catch:{ Exception -> 0x00ee }
        L_0x009a:
            if (r3 == 0) goto L_0x00a2
            boolean r4 = r3.isEmpty()     // Catch:{ Exception -> 0x00ee }
            if (r4 == 0) goto L_0x00a8
        L_0x00a2:
            java.lang.String r3 = ".staff_info dl a[href]"
            org.jsoup.select.Elements r3 = r6.select(r3)     // Catch:{ Exception -> 0x00ee }
        L_0x00a8:
            if (r3 == 0) goto L_0x00b0
            boolean r4 = r3.isEmpty()     // Catch:{ Exception -> 0x00ee }
            if (r4 == 0) goto L_0x00b6
        L_0x00b0:
            java.lang.String r3 = "cite[class*=source]"
            org.jsoup.select.Elements r3 = r6.select(r3)     // Catch:{ Exception -> 0x00ee }
        L_0x00b6:
            if (r3 == 0) goto L_0x00f8
            org.jsoup.nodes.Element r6 = r5.getBestMatchElement(r3)     // Catch:{ Exception -> 0x00ee }
            if (r6 == 0) goto L_0x00f8
            java.lang.String r1 = r6.text()     // Catch:{ Exception -> 0x00ee }
            int r3 = r1.length()     // Catch:{ Exception -> 0x00ee }
            r4 = 4
            if (r3 >= r4) goto L_0x00cd
            java.lang.String r1 = r6.text()     // Catch:{ Exception -> 0x00ee }
        L_0x00cd:
            java.util.regex.Pattern r6 = IGNORE_AUTHOR_PARTS     // Catch:{ Exception -> 0x00ee }
            java.util.regex.Matcher r6 = r6.matcher(r1)     // Catch:{ Exception -> 0x00ee }
            java.lang.String r6 = r6.replaceAll(r2)     // Catch:{ Exception -> 0x00ee }
            java.lang.String r6 = acr.browser.lightning.reading.SHelper.innerTrim(r6)     // Catch:{ Exception -> 0x00ee }
            boolean r1 = r6.contains(r0)     // Catch:{ Exception -> 0x00ea }
            if (r1 == 0) goto L_0x00e8
            java.lang.String[] r0 = r6.split(r0)     // Catch:{ Exception -> 0x00ea }
            r1 = 0
            r6 = r0[r1]     // Catch:{ Exception -> 0x00ea }
        L_0x00e8:
            r1 = r6
            goto L_0x00f8
        L_0x00ea:
            r0 = move-exception
            r1 = r6
            r6 = r0
            goto L_0x00ef
        L_0x00ee:
            r6 = move-exception
        L_0x00ef:
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.String r6 = r6.toString()
            r0.println(r6)
        L_0x00f8:
            java.util.List<java.util.regex.Pattern> r6 = CLEAN_AUTHOR_PATTERNS
            java.util.Iterator r6 = r6.iterator()
        L_0x00fe:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L_0x011d
            java.lang.Object r0 = r6.next()
            java.util.regex.Pattern r0 = (java.util.regex.Pattern) r0
            java.util.regex.Matcher r0 = r0.matcher(r1)
            boolean r2 = r0.matches()
            if (r2 == 0) goto L_0x00fe
            r6 = 1
            java.lang.String r6 = r0.group(r6)
            java.lang.String r1 = acr.browser.lightning.reading.SHelper.innerTrim(r6)
        L_0x011d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.reading.ArticleTextExtractor.extractAuthorName(org.jsoup.nodes.Document):java.lang.String");
    }

    private String extractAuthorDescription(Document document, String str) {
        if (str.isEmpty()) {
            return "";
        }
        Elements select = document.select(".byline > .bio");
        if (select != null && !select.isEmpty()) {
            return select.first().text();
        }
        Elements select2 = document.select(".byline span[class*=teaser]");
        if (select2 != null && !select2.isEmpty()) {
            return select2.first().text();
        }
        try {
            Element bestMatchElement = getBestMatchElement(document.select(":containsOwn(" + str + ')'));
            if (bestMatchElement != null) {
                return bestMatchElement.text();
            }
            return "";
        } catch (Selector.SelectorParseException unused) {
            return "";
        }
    }

    private static Collection<String> extractKeywords(Document document) {
        String innerTrim = SHelper.innerTrim(document.select("head meta[name=keywords]").attr(Constants.VAST_TRACKER_CONTENT));
        if (innerTrim.startsWith("[") && innerTrim.endsWith("]")) {
            innerTrim = innerTrim.substring(1, innerTrim.length() - 1);
        }
        String[] split = innerTrim.split("\\s*,\\s*");
        if (split.length > 1 || (split.length > 0 && split[0] != null && !split[0].isEmpty())) {
            return Arrays.asList(split);
        }
        return Collections.emptyList();
    }

    private static String extractImageUrl(Document document) {
        String replaceSpaces = SHelper.replaceSpaces(document.select("head meta[property=og:image]").attr(Constants.VAST_TRACKER_CONTENT));
        if (!replaceSpaces.isEmpty()) {
            return replaceSpaces;
        }
        String replaceSpaces2 = SHelper.replaceSpaces(document.select("head meta[name=twitter:image]").attr(Constants.VAST_TRACKER_CONTENT));
        if (!replaceSpaces2.isEmpty()) {
            return replaceSpaces2;
        }
        String replaceSpaces3 = SHelper.replaceSpaces(document.select("link[rel=image_src]").attr("href"));
        return replaceSpaces3.isEmpty() ? SHelper.replaceSpaces(document.select("head meta[name=thumbnail]").attr(Constants.VAST_TRACKER_CONTENT)) : replaceSpaces3;
    }

    private static String extractRssUrl(Document document) {
        return SHelper.replaceSpaces(document.select("link[rel=alternate]").select("link[type=application/rss+xml]").attr("href"));
    }

    private static String extractVideoUrl(Document document) {
        return SHelper.replaceSpaces(document.select("head meta[property=og:video]").attr(Constants.VAST_TRACKER_CONTENT));
    }

    private static String extractFaviconUrl(Document document) {
        String replaceSpaces = SHelper.replaceSpaces(document.select("head link[rel=icon]").attr("href"));
        return replaceSpaces.isEmpty() ? SHelper.replaceSpaces(document.select("head link[rel^=shortcut],link[rel$=icon]").attr("href")) : replaceSpaces;
    }

    private static String extractType(Document document) {
        return SHelper.innerTrim(document.select("head meta[property=og:type]").attr(Constants.VAST_TRACKER_CONTENT));
    }

    private static String extractSitename(Document document) {
        String innerTrim = SHelper.innerTrim(document.select("head meta[property=og:site_name]").attr(Constants.VAST_TRACKER_CONTENT));
        if (innerTrim.isEmpty()) {
            innerTrim = SHelper.innerTrim(document.select("head meta[name=twitter:site]").attr(Constants.VAST_TRACKER_CONTENT));
        }
        return innerTrim.isEmpty() ? SHelper.innerTrim(document.select("head meta[property=og:site_name]").attr(Constants.VAST_TRACKER_CONTENT)) : innerTrim;
    }

    private static String extractLanguage(Document document) {
        String innerTrim = SHelper.innerTrim(document.select("head meta[property=language]").attr(Constants.VAST_TRACKER_CONTENT));
        if (innerTrim.isEmpty()) {
            innerTrim = SHelper.innerTrim(document.select(AdType.HTML).attr("lang"));
            if (innerTrim.isEmpty()) {
                innerTrim = SHelper.innerTrim(document.select("head meta[property=og:locale]").attr(Constants.VAST_TRACKER_CONTENT));
            }
        }
        return (innerTrim.isEmpty() || innerTrim.length() <= 2) ? innerTrim : innerTrim.substring(0, 2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0024, code lost:
        r6 = r6.select("[extragravityscore]").first();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int getWeight(org.jsoup.nodes.Element r6, boolean r7) {
        /*
            r5 = this;
            int r0 = r5.calcWeight(r6)
            java.lang.String r1 = r6.ownText()
            int r1 = r1.length()
            double r1 = (double) r1
            r3 = 4636737291354636288(0x4059000000000000, double:100.0)
            java.lang.Double.isNaN(r1)
            double r1 = r1 / r3
            r3 = 4621819117588971520(0x4024000000000000, double:10.0)
            double r1 = r1 * r3
            long r1 = java.lang.Math.round(r1)
            int r2 = (int) r1
            int r0 = r0 + r2
            int r1 = r5.weightChildNodes(r6)
            int r0 = r0 + r1
            if (r7 == 0) goto L_0x003b
            java.lang.String r7 = "[extragravityscore]"
            org.jsoup.select.Elements r6 = r6.select(r7)
            org.jsoup.nodes.Element r6 = r6.first()
            if (r6 == 0) goto L_0x003b
            java.lang.String r7 = "extragravityscore"
            java.lang.String r6 = r6.attr(r7)
            int r6 = java.lang.Integer.parseInt(r6)
            int r0 = r0 + r6
        L_0x003b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.reading.ArticleTextExtractor.getWeight(org.jsoup.nodes.Element, boolean):int");
    }

    private int weightChildNodes(Element element) {
        int i;
        ArrayList arrayList = new ArrayList(5);
        Iterator it = element.children().iterator();
        Element element2 = null;
        int i2 = 0;
        while (true) {
            i = 200;
            if (!it.hasNext()) {
                break;
            }
            Element element3 = (Element) it.next();
            String ownText = element3.ownText();
            int length = ownText.length();
            if (length >= 20) {
                if (length > 200) {
                    i2 += Math.max(50, length / 10);
                }
                if (element3.tagName().equals("h1") || element3.tagName().equals("h2")) {
                    i2 += 30;
                } else if (element3.tagName().equals("div") || element3.tagName().equals("p")) {
                    i2 += calcWeightForChild(element3, ownText);
                    if (element3.tagName().equals("p") && length > 50) {
                        arrayList.add(element3);
                    }
                    if (element3.className().toLowerCase().equals("caption")) {
                        element2 = element3;
                    }
                }
            }
        }
        Iterator it2 = element.children().iterator();
        int i3 = 0;
        while (it2.hasNext()) {
            Element element4 = (Element) it2.next();
            if (this.NEGATIVE.matcher(element4.mo22818id()).find() || this.NEGATIVE.matcher(element4.className()).find()) {
                i3 -= 30;
                i = 200;
            } else {
                Iterator it3 = element4.children().iterator();
                while (it3.hasNext()) {
                    Element element5 = (Element) it3.next();
                    String ownText2 = element5.ownText();
                    int length2 = ownText2.length();
                    if (length2 >= 20) {
                        int max = length2 > i ? Math.max(50, length2 / 10) + 0 : 0;
                        if (element5.tagName().equals("h1") || element5.tagName().equals("h2")) {
                            max += 30;
                        } else if (element5.tagName().equals("div") || element5.tagName().equals("p")) {
                            max += calcWeightForChild(element5, ownText2);
                        }
                        i3 += max;
                        i = 200;
                    }
                }
            }
        }
        int i4 = i2 + (i3 / 3);
        if (element2 != null) {
            i4 += 30;
        }
        if (arrayList.size() >= 2) {
            Iterator it4 = element.children().iterator();
            while (it4.hasNext()) {
                Element element6 = (Element) it4.next();
                if ("h1;h2;h3;h4;h5;h6".contains(element6.tagName())) {
                    i4 += 20;
                } else if ("table;li;td;th".contains(element6.tagName())) {
                    addScore(element6, -30);
                }
                if ("p".contains(element6.tagName())) {
                    addScore(element6, 30);
                }
            }
        }
        return i4;
    }

    private static void addScore(Element element, int i) {
        setScore(element, i + getScore(element));
    }

    private static int getScore(Element element) {
        try {
            return Integer.parseInt(element.attr("gravityScore"));
        } catch (Exception unused) {
            return 0;
        }
    }

    private static void setScore(Element element, int i) {
        element.attr("gravityScore", Integer.toString(i));
    }

    private static int calcWeightForChild(Element element, String str) {
        int i;
        if (SHelper.count(str, "&quot;") + SHelper.count(str, "&lt;") + SHelper.count(str, "&gt;") + SHelper.count(str, "px") > 5) {
            i = -30;
        } else {
            double length = (double) str.length();
            Double.isNaN(length);
            i = (int) Math.round(length / 35.0d);
        }
        addScore(element, i);
        return i;
    }

    private int calcWeight(Element element) {
        int i = this.POSITIVE.matcher(element.className()).find() ? 35 : 0;
        if (this.POSITIVE.matcher(element.mo22818id()).find()) {
            i += 45;
        }
        if (this.UNLIKELY.matcher(element.className()).find()) {
            i -= 20;
        }
        if (this.UNLIKELY.matcher(element.mo22818id()).find()) {
            i -= 20;
        }
        if (this.NEGATIVE.matcher(element.className()).find()) {
            i -= 50;
        }
        if (this.NEGATIVE.matcher(element.mo22818id()).find()) {
            i -= 50;
        }
        String attr = element.attr("style");
        if (attr != null && !attr.isEmpty() && NEGATIVE_STYLE.matcher(attr).find()) {
            i -= 50;
        }
        String attr2 = element.attr("itemprop");
        return (attr2 == null || attr2.isEmpty() || !this.POSITIVE.matcher(attr2).find()) ? i : i + 100;
    }

    private static Element determineImageSource(Element element, List<ImageResult> list) {
        int i;
        int i2;
        int i3;
        boolean z;
        String attr;
        List<ImageResult> list2 = list;
        Elements select = element.select("img");
        if (select.isEmpty()) {
            select = element.parent().select("img");
        }
        double d = 1.0d;
        Iterator it = select.iterator();
        Element element2 = null;
        int i4 = 0;
        while (it.hasNext()) {
            Element element3 = (Element) it.next();
            String attr2 = element3.attr("src");
            if (!attr2.isEmpty() && !isAdImage(attr2)) {
                try {
                    int parseInt = Integer.parseInt(element3.attr("height"));
                    i2 = parseInt >= 50 ? 20 : -20;
                    i = parseInt;
                } catch (Exception unused) {
                    i2 = 0;
                    i = 0;
                }
                try {
                    int parseInt2 = Integer.parseInt(element3.attr("width"));
                    i2 = parseInt2 >= 50 ? i2 + 20 : i2 - 20;
                    i3 = parseInt2;
                } catch (Exception unused2) {
                    i3 = 0;
                }
                String attr3 = element3.attr("alt");
                if (attr3.length() > 35) {
                    i2 += 20;
                }
                String attr4 = element3.attr("title");
                if (attr4.length() > 35) {
                    i2 += 20;
                }
                if (element3.parent() == null || (attr = element3.parent().attr("rel")) == null || !attr.contains("nofollow")) {
                    z = false;
                } else {
                    i2 -= 40;
                    z = attr.contains("nofollow");
                }
                double d2 = (double) i2;
                Double.isNaN(d2);
                int i5 = (int) (d2 * d);
                if (i5 > i4) {
                    d /= 2.0d;
                    element2 = element3;
                    i4 = i5;
                }
                list2.add(new ImageResult(attr2, Integer.valueOf(i5), attr4, i, i3, attr3, z));
            }
        }
        Collections.sort(list2, new ImageComparator());
        return element2;
    }

    private static void prepareDocument(Document document) {
        removeScriptsAndStyles(document);
    }

    /* access modifiers changed from: protected */
    public void stripUnlikelyCandidates(Document document) {
        Iterator it = document.select("body").select(Marker.ANY_MARKER).iterator();
        while (it.hasNext()) {
            Element element = (Element) it.next();
            String lowerCase = element.className().toLowerCase();
            String lowerCase2 = element.mo22818id().toLowerCase();
            if (this.NEGATIVE.matcher(lowerCase).find() || this.NEGATIVE.matcher(lowerCase2).find()) {
                element.remove();
            }
        }
    }

    private static Document removeScriptsAndStyles(Document document) {
        Iterator it = document.getElementsByTag("script").iterator();
        while (it.hasNext()) {
            ((Element) it.next()).remove();
        }
        Iterator it2 = document.getElementsByTag("noscript").iterator();
        while (it2.hasNext()) {
            ((Element) it2.next()).remove();
        }
        Iterator it3 = document.getElementsByTag("style").iterator();
        while (it3.hasNext()) {
            ((Element) it3.next()).remove();
        }
        return document;
    }

    private static boolean isAdImage(String str) {
        return SHelper.count(str, "ad") >= 2;
    }

    private static String doTitleSplits(String str, String str2) {
        String str3 = "";
        int i = 0;
        for (String str4 : str.split(str2)) {
            if (str4.length() > i) {
                i = str4.length();
                str3 = str4;
            }
        }
        return str3.replace("&raquo;", StringUtils.SPACE).replace("»", StringUtils.SPACE).trim();
    }

    private static Collection<Element> getNodes(Document document) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(64);
        Iterator it = document.select("body").select(Marker.ANY_MARKER).iterator();
        int i = 100;
        while (it.hasNext()) {
            Element element = (Element) it.next();
            if (NODES.matcher(element.tagName()).matches()) {
                linkedHashMap.put(element, (Object) null);
                setScore(element, i);
                i /= 2;
            }
        }
        return linkedHashMap.keySet();
    }

    private static String cleanTitle(String str) {
        String[] split = str.split("\\|");
        StringBuilder sb = new StringBuilder(split.length);
        int i = 0;
        for (String str2 : split) {
            if (!IGNORED_TITLE_PARTS.contains(str2.toLowerCase().trim()) && (i != split.length - 1 || sb.length() <= str2.length())) {
                if (i > 0) {
                    sb.append('|');
                }
                sb.append(str2);
                i++;
            }
        }
        return SHelper.innerTrim(sb.toString());
    }

    private static String utf8truncate(String str, int i) {
        StringBuilder sb = new StringBuilder(i);
        int i2 = 0;
        for (int i3 = 0; i3 < str.length(); i3++) {
            char charAt = str.charAt(i3);
            int i4 = 3;
            if (charAt <= 127) {
                i4 = 1;
            } else if (charAt <= 2047) {
                i4 = 2;
            } else if (charAt > 55295) {
                if (charAt <= 56319) {
                    i4 = 4;
                } else if (charAt <= 57343) {
                    i4 = 0;
                }
            }
            i2 += i4;
            if (i2 > i) {
                break;
            }
            sb.append(charAt);
        }
        return sb.toString();
    }

    private static class ImageComparator implements Comparator<ImageResult> {
        private ImageComparator() {
        }

        public int compare(ImageResult imageResult, ImageResult imageResult2) {
            return imageResult2.weight.compareTo(imageResult.weight);
        }
    }
}
