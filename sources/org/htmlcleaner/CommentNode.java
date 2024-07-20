package org.htmlcleaner;

import java.io.IOException;
import java.io.Writer;

public class CommentNode extends BaseTokenImpl implements HtmlNode {
    private String content;

    public CommentNode(String str) {
        this.content = str;
    }

    public String getCommentedContent() {
        return "<!--" + this.content + "-->";
    }

    public String getContent() {
        return this.content;
    }

    public String toString() {
        return getCommentedContent();
    }

    public void serialize(Serializer serializer, Writer writer) throws IOException {
        writer.write(getCommentedContent());
    }
}
