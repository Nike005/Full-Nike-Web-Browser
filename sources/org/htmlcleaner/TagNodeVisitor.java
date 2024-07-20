package org.htmlcleaner;

public interface TagNodeVisitor {
    boolean visit(TagNode tagNode, HtmlNode htmlNode);
}
