package org.jsoup.examples;

import java.io.IOException;
import java.util.Iterator;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

public class HtmlToPlainText {
    private static final int timeout = 5000;
    private static final String userAgent = "Mozilla/5.0 (jsoup)";

    public static void main(String... strArr) throws IOException {
        Validate.isTrue(strArr.length == 1 || strArr.length == 2, "usage: java -cp jsoup.jar org.jsoup.examples.HtmlToPlainText url [selector]");
        String str = strArr[0];
        String str2 = strArr.length == 2 ? strArr[1] : null;
        Document document = Jsoup.connect(str).userAgent(userAgent).timeout(5000).get();
        HtmlToPlainText htmlToPlainText = new HtmlToPlainText();
        if (str2 != null) {
            Iterator it = document.select(str2).iterator();
            while (it.hasNext()) {
                System.out.println(htmlToPlainText.getPlainText((Element) it.next()));
            }
            return;
        }
        System.out.println(htmlToPlainText.getPlainText(document));
    }

    public String getPlainText(Element element) {
        FormattingVisitor formattingVisitor = new FormattingVisitor();
        new NodeTraversor(formattingVisitor).traverse(element);
        return formattingVisitor.toString();
    }

    private class FormattingVisitor implements NodeVisitor {
        private static final int maxWidth = 80;
        private StringBuilder accum;
        private int width;

        private FormattingVisitor() {
            this.width = 0;
            this.accum = new StringBuilder();
        }

        public void head(Node node, int i) {
            String nodeName = node.nodeName();
            if (node instanceof TextNode) {
                append(((TextNode) node).text());
            } else if (nodeName.equals("li")) {
                append("\n * ");
            } else if (nodeName.equals("dt")) {
                append("  ");
            } else if (StringUtil.m6103in(nodeName, "p", "h1", "h2", "h3", "h4", "h5", "tr")) {
                append(StringUtils.f3949LF);
            }
        }

        public void tail(Node node, int i) {
            String nodeName = node.nodeName();
            if (StringUtil.m6103in(nodeName, "br", "dd", "dt", "p", "h1", "h2", "h3", "h4", "h5")) {
                append(StringUtils.f3949LF);
            } else if (nodeName.equals(NotificationBundleProcessor.PUSH_ADDITIONAL_DATA_KEY)) {
                append(String.format(" <%s>", new Object[]{node.absUrl("href")}));
            }
        }

        private void append(String str) {
            if (str.startsWith(StringUtils.f3949LF)) {
                this.width = 0;
            }
            if (str.equals(StringUtils.SPACE)) {
                if (this.accum.length() != 0) {
                    StringBuilder sb = this.accum;
                    if (StringUtil.m6103in(sb.substring(sb.length() - 1), StringUtils.SPACE, StringUtils.f3949LF)) {
                        return;
                    }
                } else {
                    return;
                }
            }
            if (str.length() + this.width > 80) {
                String[] split = str.split("\\s+");
                int i = 0;
                while (i < split.length) {
                    String str2 = split[i];
                    if (!(i == split.length - 1)) {
                        str2 = str2 + StringUtils.SPACE;
                    }
                    if (str2.length() + this.width > 80) {
                        StringBuilder sb2 = this.accum;
                        sb2.append(StringUtils.f3949LF);
                        sb2.append(str2);
                        this.width = str2.length();
                    } else {
                        this.accum.append(str2);
                        this.width += str2.length();
                    }
                    i++;
                }
                return;
            }
            this.accum.append(str);
            this.width += str.length();
        }

        public String toString() {
            return this.accum.toString();
        }
    }
}