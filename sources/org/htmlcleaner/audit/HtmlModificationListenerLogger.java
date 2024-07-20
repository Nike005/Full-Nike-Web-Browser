package org.htmlcleaner.audit;

import java.util.logging.Logger;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.conditional.ITagNodeCondition;

public class HtmlModificationListenerLogger implements HtmlModificationListener {
    private Logger log;

    public HtmlModificationListenerLogger(Logger logger) {
        this.log = logger;
    }

    public void fireConditionModification(ITagNodeCondition iTagNodeCondition, TagNode tagNode) {
        Logger logger = this.log;
        logger.info("fireConditionModification:" + iTagNodeCondition + " at " + tagNode);
    }

    public void fireHtmlError(boolean z, TagNode tagNode, ErrorType errorType) {
        Logger logger = this.log;
        logger.info("fireHtmlError:" + errorType + "(" + z + ") at " + tagNode);
    }

    public void fireUglyHtml(boolean z, TagNode tagNode, ErrorType errorType) {
        Logger logger = this.log;
        logger.info("fireConditionModification:" + errorType + "(" + z + ") at " + tagNode);
    }

    public void fireUserDefinedModification(boolean z, TagNode tagNode, ErrorType errorType) {
        Logger logger = this.log;
        logger.info("fireConditionModification" + errorType + "(" + z + ") at " + tagNode);
    }
}
