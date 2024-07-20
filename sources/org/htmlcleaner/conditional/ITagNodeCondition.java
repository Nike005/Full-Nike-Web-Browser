package org.htmlcleaner.conditional;

import org.htmlcleaner.TagNode;

public interface ITagNodeCondition {
    boolean satisfy(TagNode tagNode);
}
