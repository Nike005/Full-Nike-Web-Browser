package org.htmlcleaner.conditional;

import java.util.List;
import org.htmlcleaner.TagNode;

public class TagNodeInsignificantBrCondition implements ITagNodeCondition {
    private static final String BR_TAG = "br";

    public boolean satisfy(TagNode tagNode) {
        if (!isBrNode(tagNode)) {
            return false;
        }
        List allChildren = tagNode.getParent().getAllChildren();
        int indexOf = allChildren.indexOf(tagNode);
        if (checkSublist(0, indexOf, allChildren) || checkSublist(indexOf, allChildren.size(), allChildren)) {
            return true;
        }
        return false;
    }

    private boolean isBrNode(TagNode tagNode) {
        return tagNode != null && "br".equals(tagNode.getName());
    }

    private boolean checkSublist(int i, int i2, List list) {
        for (Object next : list.subList(i, i2)) {
            if (!(next instanceof TagNode)) {
                return false;
            }
            TagNode tagNode = (TagNode) next;
            if (!isBrNode(tagNode) && !tagNode.isPruned()) {
                return false;
            }
        }
        return true;
    }
}
