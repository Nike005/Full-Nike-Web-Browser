package org.htmlcleaner;

import java.io.IOException;
import java.io.Writer;

public class SimpleHtmlSerializer extends HtmlSerializer {
    public SimpleHtmlSerializer(CleanerProperties cleanerProperties) {
        super(cleanerProperties);
    }

    /* access modifiers changed from: protected */
    public void serialize(TagNode tagNode, Writer writer) throws IOException {
        serializeOpenTag(tagNode, writer, false);
        if (!isMinimizedTagSyntax(tagNode)) {
            for (Object next : tagNode.getAllChildren()) {
                if (next instanceof ContentNode) {
                    String obj = next.toString();
                    if (!dontEscape(tagNode)) {
                        obj = escapeText(obj);
                    }
                    writer.write(obj);
                } else if (next instanceof BaseToken) {
                    ((BaseToken) next).serialize(this, writer);
                }
            }
            serializeEndTag(tagNode, writer, false);
        }
    }
}
