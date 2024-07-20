package org.htmlcleaner;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.ListIterator;
import org.apache.commons.lang3.StringUtils;

public class CompactXmlSerializer extends XmlSerializer {
    public CompactXmlSerializer(CleanerProperties cleanerProperties) {
        super(cleanerProperties);
    }

    /* access modifiers changed from: protected */
    public void serialize(TagNode tagNode, Writer writer) throws IOException {
        serializeOpenTag(tagNode, writer, false);
        List allChildren = tagNode.getAllChildren();
        if (!isMinimizedTagSyntax(tagNode)) {
            ListIterator listIterator = allChildren.listIterator();
            while (listIterator.hasNext()) {
                Object next = listIterator.next();
                if (next != null) {
                    if (next instanceof ContentNode) {
                        String trim = ((ContentNode) next).getContent().trim();
                        writer.write(dontEscape(tagNode) ? trim.replaceAll(XmlSerializer.END_CDATA, "]]&gt;") : escapeXml(trim));
                        if (listIterator.hasNext()) {
                            if (!isWhitespaceString(listIterator.next())) {
                                writer.write(StringUtils.f3949LF);
                            }
                            listIterator.previous();
                        }
                    } else if (next instanceof CommentNode) {
                        writer.write(((CommentNode) next).getCommentedContent().trim());
                    } else {
                        ((BaseToken) next).serialize(this, writer);
                    }
                }
            }
            serializeEndTag(tagNode, writer, false);
        }
    }

    private boolean isWhitespaceString(Object obj) {
        String obj2;
        if (obj == null || (obj2 = obj.toString()) == null || !"".equals(obj2.trim())) {
            return false;
        }
        return true;
    }
}
