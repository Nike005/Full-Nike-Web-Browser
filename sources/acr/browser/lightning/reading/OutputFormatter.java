package acr.browser.lightning.reading;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

public class OutputFormatter {
    private static final int MIN_FIRST_PARAGRAPH_TEXT = 50;
    private static final int MIN_PARAGRAPH_TEXT = 30;
    private static final List<String> NODES_TO_REPLACE = Arrays.asList(new String[]{"strong", "b", "i"});
    private final int minFirstParagraphText;
    private final int minParagraphText;
    private String nodesToKeepCssSelector;
    private final List<String> nodesToReplace;
    private Pattern unlikelyPattern;

    public OutputFormatter() {
        this(50, 30, NODES_TO_REPLACE);
    }

    public OutputFormatter(int i) {
        this(i, i, NODES_TO_REPLACE);
    }

    public OutputFormatter(int i, int i2) {
        this(i, i2, NODES_TO_REPLACE);
    }

    private OutputFormatter(int i, int i2, List<String> list) {
        this.unlikelyPattern = Pattern.compile("display:none|visibility:hidden");
        this.nodesToKeepCssSelector = "p, ol";
        this.minFirstParagraphText = i;
        this.minParagraphText = i2;
        this.nodesToReplace = list;
    }

    public void setNodesToKeepCssSelector(String str) {
        this.nodesToKeepCssSelector = str;
    }

    public String getFormattedText(Element element) {
        setParagraphIndex(element, this.nodesToKeepCssSelector);
        removeNodesWithNegativeScores(element);
        StringBuilder sb = new StringBuilder();
        int append = append(element, sb, this.nodesToKeepCssSelector);
        String innerTrim = SHelper.innerTrim(sb.toString());
        int length = element.text().length();
        boolean z = true;
        if (length == 0) {
            length = 1;
        }
        double length2 = (double) innerTrim.length();
        double d = (double) length;
        Double.isNaN(d);
        Double.isNaN(length2);
        if (length2 / (d * 1.0d) >= 0.25d) {
            z = false;
        }
        if (innerTrim.length() > 100 && append > 0 && !z) {
            return innerTrim;
        }
        if (innerTrim.isEmpty() || ((!element.text().isEmpty() && innerTrim.length() <= element.ownText().length()) || append == 0 || z)) {
            innerTrim = element.text();
        }
        return Jsoup.parse(innerTrim).text();
    }

    private void removeNodesWithNegativeScores(Element element) {
        Iterator it = element.select("*[gravityScore]").iterator();
        while (it.hasNext()) {
            Element element2 = (Element) it.next();
            int score = getScore(element2);
            int paragraphIndex = getParagraphIndex(element2);
            if (score < 0 || element2.text().length() < getMinParagraph(paragraphIndex)) {
                element2.remove();
            }
        }
    }

    private int append(Element element, StringBuilder sb, String str) {
        Iterator it = element.select(str).iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            Element element2 = (Element) it.next();
            Element element3 = element2;
            while (true) {
                if (element3 == null || element3.equals(element)) {
                    String node2Text = node2Text(element2);
                } else if (unlikely(element3)) {
                    break;
                } else {
                    element3 = element3.parent();
                }
            }
            String node2Text2 = node2Text(element2);
            if (!node2Text2.isEmpty() && node2Text2.length() >= getMinParagraph(i2) && node2Text2.length() <= SHelper.countLetters(node2Text2) * 2) {
                if (element2.tagName().equals("p")) {
                    i++;
                }
                sb.append(node2Text2);
                sb.append("\n\n");
                i2++;
            }
        }
        return i;
    }

    private static void setParagraphIndex(Element element, String str) {
        Iterator it = element.select(str).iterator();
        int i = 0;
        while (it.hasNext()) {
            ((Element) it.next()).attr("paragraphIndex", Integer.toString(i));
            i++;
        }
    }

    private int getMinParagraph(int i) {
        if (i < 1) {
            return this.minFirstParagraphText;
        }
        return this.minParagraphText;
    }

    private static int getParagraphIndex(Element element) {
        try {
            return Integer.parseInt(element.attr("paragraphIndex"));
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    private static int getScore(Element element) {
        try {
            return Integer.parseInt(element.attr("gravityScore"));
        } catch (Exception unused) {
            return 0;
        }
    }

    private boolean unlikely(Node node) {
        if (node.attr("class") != null && node.attr("class").toLowerCase().contains("caption")) {
            return true;
        }
        String attr = node.attr("style");
        String attr2 = node.attr("class");
        if (this.unlikelyPattern.matcher(attr).find() || this.unlikelyPattern.matcher(attr2).find()) {
            return true;
        }
        return false;
    }

    private void appendTextSkipHidden(Element element, StringBuilder sb, int i) {
        for (Node next : element.childNodes()) {
            if (!unlikely(next)) {
                if (next instanceof TextNode) {
                    sb.append(((TextNode) next).text());
                } else if (next instanceof Element) {
                    Element element2 = (Element) next;
                    if (sb.length() > 0 && element2.isBlock() && !lastCharIsWhitespace(sb)) {
                        sb.append(' ');
                    } else if (element2.tagName().equals("br")) {
                        sb.append(' ');
                    }
                    appendTextSkipHidden(element2, sb, i + 1);
                }
            }
        }
    }

    private static boolean lastCharIsWhitespace(StringBuilder sb) {
        return sb.length() != 0 && Character.isWhitespace(sb.charAt(sb.length() - 1));
    }

    private String node2Text(Element element) {
        StringBuilder sb = new StringBuilder(200);
        appendTextSkipHidden(element, sb, 0);
        return sb.toString();
    }

    private OutputFormatter setUnlikelyPattern(String str) {
        this.unlikelyPattern = Pattern.compile(str);
        return this;
    }

    public OutputFormatter appendUnlikelyPattern(String str) {
        return setUnlikelyPattern(this.unlikelyPattern.toString() + '|' + str);
    }
}
