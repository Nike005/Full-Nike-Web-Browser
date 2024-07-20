package org.htmlcleaner;

import java.io.IOException;
import java.io.Writer;

public class ContentNode extends BaseTokenImpl implements HtmlNode {
    private final boolean blank;
    private final String content;

    public ContentNode(String str) {
        this.content = str;
        this.blank = C2401Utils.isEmptyString(str);
    }

    public String getContent() {
        return this.content;
    }

    public String toString() {
        return getContent();
    }

    public void serialize(Serializer serializer, Writer writer) throws IOException {
        writer.write(this.content);
    }

    public boolean isBlank() {
        return this.blank;
    }
}
