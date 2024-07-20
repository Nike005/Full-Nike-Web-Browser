package org.htmlcleaner.conditional;

import org.htmlcleaner.TagNode;

public class TagNodeAttValueCondition implements ITagNodeCondition {
    private String attName;
    private String attValue;
    private boolean isCaseSensitive;

    public TagNodeAttValueCondition(String str, String str2, boolean z) {
        this.attName = str;
        this.attValue = str2;
        this.isCaseSensitive = z;
    }

    public boolean satisfy(TagNode tagNode) {
        String str;
        String str2;
        if (tagNode == null || (str = this.attName) == null || (str2 = this.attValue) == null) {
            return false;
        }
        boolean z = this.isCaseSensitive;
        String attributeByName = tagNode.getAttributeByName(str);
        return z ? str2.equals(attributeByName) : str2.equalsIgnoreCase(attributeByName);
    }
}
