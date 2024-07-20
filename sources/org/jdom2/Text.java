package org.jdom2;

import org.jdom2.Content;
import org.jdom2.output.Format;

public class Text extends Content {
    static final String EMPTY_STRING = "";
    private static final long serialVersionUID = 200;
    protected String value;

    protected Text(Content.CType cType) {
        super(cType);
    }

    protected Text() {
        this(Content.CType.Text);
    }

    public Text(String str) {
        this(Content.CType.Text);
        setText(str);
    }

    public String getText() {
        return this.value;
    }

    public String getTextTrim() {
        return Format.trimBoth(getText());
    }

    public String getTextNormalize() {
        return normalizeString(getText());
    }

    public static String normalizeString(String str) {
        return str == null ? "" : Format.compact(str);
    }

    public Text setText(String str) {
        if (str == null) {
            this.value = "";
            return this;
        }
        String checkCharacterData = Verifier.checkCharacterData(str);
        if (checkCharacterData == null) {
            this.value = str;
            return this;
        }
        throw new IllegalDataException(str, "character content", checkCharacterData);
    }

    public void append(String str) {
        if (str != null) {
            String checkCharacterData = Verifier.checkCharacterData(str);
            if (checkCharacterData != null) {
                throw new IllegalDataException(str, "character content", checkCharacterData);
            } else if (str.length() > 0) {
                this.value += str;
            }
        }
    }

    public void append(Text text) {
        if (text != null) {
            this.value += text.getText();
        }
    }

    public String getValue() {
        return this.value;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append("[Text: ");
        sb.append(getText());
        sb.append("]");
        return sb.toString();
    }

    public Text clone() {
        Text text = (Text) super.clone();
        text.value = this.value;
        return text;
    }

    public Text detach() {
        return (Text) super.detach();
    }

    /* access modifiers changed from: protected */
    public Text setParent(Parent parent) {
        return (Text) super.setParent(parent);
    }

    public Element getParent() {
        return (Element) super.getParent();
    }
}
