package org.htmlcleaner.conditional;

import org.htmlcleaner.TagNode;

public class TagNodeNameCondition implements ITagNodeCondition {
    private String name;

    public TagNodeNameCondition(String str) {
        this.name = str;
    }

    public boolean satisfy(TagNode tagNode) {
        if (tagNode == null) {
            return false;
        }
        return tagNode.getName().equalsIgnoreCase(this.name);
    }
}
