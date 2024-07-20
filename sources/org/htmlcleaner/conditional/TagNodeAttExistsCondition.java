package org.htmlcleaner.conditional;

import org.htmlcleaner.TagNode;

public class TagNodeAttExistsCondition implements ITagNodeCondition {
    private String attName;

    public TagNodeAttExistsCondition(String str) {
        this.attName = str;
    }

    public boolean satisfy(TagNode tagNode) {
        if (tagNode == null) {
            return false;
        }
        return tagNode.getAttributes().containsKey(this.attName.toLowerCase());
    }
}
