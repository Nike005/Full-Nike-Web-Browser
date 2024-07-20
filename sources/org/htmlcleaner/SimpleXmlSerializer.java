package org.htmlcleaner;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class SimpleXmlSerializer extends XmlSerializer {
    public SimpleXmlSerializer(CleanerProperties cleanerProperties) {
        super(cleanerProperties);
    }

    /* access modifiers changed from: protected */
    public void serializeContentToken(ContentNode contentNode, TagNode tagNode, Writer writer) throws IOException {
        String content = contentNode.getContent();
        String trim = content.trim();
        boolean dontEscape = dontEscape(tagNode);
        if (trim.endsWith(XmlSerializer.SAFE_END_CDATA)) {
            int lastIndexOf = content.lastIndexOf(XmlSerializer.SAFE_END_CDATA);
            String substring = content.substring(lastIndexOf);
            if (dontEscape) {
                writer.write(content.substring(0, lastIndexOf).replaceAll(XmlSerializer.END_CDATA, "]]&gt;"));
            } else if (trim.startsWith(XmlSerializer.BEGIN_CDATA)) {
                int indexOf = content.indexOf(XmlSerializer.BEGIN_CDATA) + 9;
                writer.write(content.substring(0, indexOf));
                writer.write(escapeXml(content.substring(indexOf, lastIndexOf)));
            } else {
                writer.write(escapeXml(content.substring(0, lastIndexOf)));
            }
            writer.write(substring);
        } else if (dontEscape) {
            writer.write(content.replaceAll(XmlSerializer.END_CDATA, "]]&gt;"));
        } else {
            writer.write(escapeXml(content));
        }
    }

    /* access modifiers changed from: protected */
    public void serialize(TagNode tagNode, Writer writer) throws IOException {
        serializeOpenTag(tagNode, writer, false);
        List allChildren = tagNode.getAllChildren();
        if (!isMinimizedTagSyntax(tagNode)) {
            for (Object next : allChildren) {
                if (next != null) {
                    if (next instanceof ContentNode) {
                        serializeContentToken((ContentNode) next, tagNode, writer);
                    } else {
                        ((BaseToken) next).serialize(this, writer);
                    }
                }
            }
            serializeEndTag(tagNode, writer, false);
        }
    }
}
