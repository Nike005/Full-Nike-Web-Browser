package org.htmlcleaner.audit;

import org.htmlcleaner.TagNode;
import org.htmlcleaner.conditional.ITagNodeCondition;

public interface HtmlModificationListener {
    void fireConditionModification(ITagNodeCondition iTagNodeCondition, TagNode tagNode);

    void fireHtmlError(boolean z, TagNode tagNode, ErrorType errorType);

    void fireUglyHtml(boolean z, TagNode tagNode, ErrorType errorType);

    void fireUserDefinedModification(boolean z, TagNode tagNode, ErrorType errorType);
}
