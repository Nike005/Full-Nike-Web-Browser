package org.htmlcleaner.conditional;

import org.htmlcleaner.TagNode;

public class TagAllCondition implements ITagNodeCondition {
    public boolean satisfy(TagNode tagNode) {
        return true;
    }
}
