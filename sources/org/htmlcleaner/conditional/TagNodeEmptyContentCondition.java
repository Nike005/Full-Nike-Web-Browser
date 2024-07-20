package org.htmlcleaner.conditional;

import java.util.HashSet;
import java.util.Set;
import org.htmlcleaner.C2401Utils;
import org.htmlcleaner.ContentNode;
import org.htmlcleaner.Display;
import org.htmlcleaner.ITagInfoProvider;
import org.htmlcleaner.TagInfo;
import org.htmlcleaner.TagNode;

public class TagNodeEmptyContentCondition implements ITagNodeCondition {
    private static final String ID_ATTRIBUTE_NAME = "id";
    private static final Set<String> unsafeBlockElements;
    private ITagInfoProvider tagInfoProvider;

    static {
        HashSet hashSet = new HashSet();
        unsafeBlockElements = hashSet;
        hashSet.add("td");
        unsafeBlockElements.add("th");
    }

    public TagNodeEmptyContentCondition(ITagInfoProvider iTagInfoProvider) {
        this.tagInfoProvider = iTagInfoProvider;
    }

    public boolean satisfy(TagNode tagNode) {
        return satisfy(tagNode, false);
    }

    private boolean satisfy(TagNode tagNode, boolean z) {
        String name = tagNode.getName();
        TagInfo tagInfo = this.tagInfoProvider.getTagInfo(name);
        if (tagInfo == null || hasIdAttributeSet(tagNode) || Display.none == tagInfo.getDisplay() || tagInfo.isEmptyTag() || ((!z && unsafeBlockElements.contains(name)) || !C2401Utils.isEmptyString(tagNode.getText()))) {
            return false;
        }
        if (tagNode.isEmpty()) {
            return true;
        }
        for (Object next : tagNode.getAllChildren()) {
            if (next instanceof TagNode) {
                if (!satisfy((TagNode) next, true)) {
                    return false;
                }
            } else if (!(next instanceof ContentNode) || !((ContentNode) next).isBlank()) {
                return false;
            }
        }
        return true;
    }

    private boolean hasIdAttributeSet(TagNode tagNode) {
        return !C2401Utils.isEmptyString(tagNode.getAttributes().get("id"));
    }
}
