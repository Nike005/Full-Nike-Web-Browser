package org.htmlcleaner;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.StringTokenizer;
import org.apache.commons.lang3.StringUtils;

public class BrowserCompactXmlSerializer extends XmlSerializer {
    private static final String BR_TAG = "<br />";
    private static final String LINE_BREAK = "\n";
    private static final String PRE_TAG = "pre";

    public BrowserCompactXmlSerializer(CleanerProperties cleanerProperties) {
        super(cleanerProperties);
    }

    /* access modifiers changed from: protected */
    public void serialize(TagNode tagNode, Writer writer) throws IOException {
        boolean z;
        boolean z2 = false;
        serializeOpenTag(tagNode, writer, false);
        TagInfo tagInfo = this.props.getTagInfoProvider().getTagInfo(tagNode.getName());
        String name = tagInfo != null ? tagInfo.getName() : null;
        ArrayList arrayList = new ArrayList(tagNode.getAllChildren());
        if (!isMinimizedTagSyntax(tagNode)) {
            ListIterator listIterator = arrayList.listIterator();
            while (listIterator.hasNext()) {
                Object next = listIterator.next();
                if (next != null) {
                    boolean z3 = next instanceof ContentNode;
                    if (z3 && !PRE_TAG.equals(name)) {
                        String content = ((ContentNode) next).getContent();
                        String replaceAll = (dontEscape(tagNode) ? content.replaceAll(XmlSerializer.END_CDATA, "]]&gt;") : escapeXml(content)).replaceAll("^ +", StringUtils.SPACE).replaceAll(" +$", StringUtils.SPACE);
                        boolean z4 = tagInfo != null && tagInfo.getDisplay().isLeadingAndEndWhitespacesAllowed();
                        boolean z5 = replaceAll.length() > 0 && Character.isWhitespace(replaceAll.charAt(0)) && z4;
                        boolean z6 = replaceAll.length() > 1 && Character.isWhitespace(replaceAll.charAt(replaceAll.length() - 1)) && z4;
                        String trim = replaceAll.trim();
                        if (trim.length() != 0) {
                            int indexOf = arrayList.indexOf(next);
                            boolean isContentOrInline = (indexOf < 2 || !listIterator.hasNext()) ? false : isContentOrInline(arrayList.get(indexOf - 1));
                            if (z5 || isContentOrInline) {
                                writer.write(32);
                            }
                            StringTokenizer stringTokenizer = new StringTokenizer(trim, "\n", true);
                            String str = "";
                            while (stringTokenizer.hasMoreTokens()) {
                                String nextToken = stringTokenizer.nextToken();
                                if (str.equals(nextToken) && str.equals("\n")) {
                                    writer.write(BR_TAG);
                                } else if ("\n".equals(nextToken)) {
                                    writer.write(32);
                                } else {
                                    writer.write(nextToken.trim());
                                }
                                str = nextToken;
                            }
                            if (listIterator.hasNext()) {
                                z = isContentOrInline(listIterator.next());
                                listIterator.previous();
                            } else {
                                z = false;
                            }
                            if (z6 || z) {
                                writer.write(32);
                            }
                        } else {
                            listIterator.remove();
                        }
                    } else if (z3) {
                        writer.write(((ContentNode) next).getContent());
                    } else if (next instanceof CommentNode) {
                        writer.write(((CommentNode) next).getCommentedContent().trim());
                    } else {
                        ((BaseToken) next).serialize(this, writer);
                    }
                }
            }
            if (tagInfo != null && tagInfo.getDisplay().isAfterTagLineBreakNeeded()) {
                z2 = true;
            }
            serializeEndTag(tagNode, writer, z2);
        }
    }

    private boolean isContentOrInline(Object obj) {
        TagInfo tagInfo;
        if (obj instanceof ContentNode) {
            return true;
        }
        if (!(obj instanceof TagNode) || (tagInfo = this.props.getTagInfoProvider().getTagInfo(((TagNode) obj).getName())) == null || tagInfo.getDisplay() != Display.inline) {
            return false;
        }
        return true;
    }
}
