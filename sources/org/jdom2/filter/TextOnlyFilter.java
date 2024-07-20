package org.jdom2.filter;

import org.jdom2.Content;
import org.jdom2.Text;

final class TextOnlyFilter extends AbstractFilter<Text> {
    private static final long serialVersionUID = 200;

    TextOnlyFilter() {
    }

    public Text filter(Object obj) {
        if (!(obj instanceof Text)) {
            return null;
        }
        Text text = (Text) obj;
        if (text.getCType() == Content.CType.Text) {
            return text;
        }
        return null;
    }

    public int hashCode() {
        return getClass().hashCode();
    }

    public boolean equals(Object obj) {
        return obj instanceof TextOnlyFilter;
    }
}
