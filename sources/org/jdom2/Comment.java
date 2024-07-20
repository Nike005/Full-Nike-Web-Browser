package org.jdom2;

import org.jdom2.Content;
import org.jdom2.output.XMLOutputter;

public class Comment extends Content {
    private static final long serialVersionUID = 200;
    protected String text;

    protected Comment() {
        super(Content.CType.Comment);
    }

    public Comment(String str) {
        super(Content.CType.Comment);
        setText(str);
    }

    public String getValue() {
        return this.text;
    }

    public String getText() {
        return this.text;
    }

    public Comment setText(String str) {
        String checkCommentData = Verifier.checkCommentData(str);
        if (checkCommentData == null) {
            this.text = str;
            return this;
        }
        throw new IllegalDataException(str, "comment", checkCommentData);
    }

    public Comment clone() {
        return (Comment) super.clone();
    }

    public Comment detach() {
        return (Comment) super.detach();
    }

    /* access modifiers changed from: protected */
    public Comment setParent(Parent parent) {
        return (Comment) super.setParent(parent);
    }

    public String toString() {
        return "[Comment: " + new XMLOutputter().outputString(this) + "]";
    }
}
