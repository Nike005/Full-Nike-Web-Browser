package org.htmlcleaner.conditional;

import java.util.Map;
import java.util.regex.Pattern;
import org.htmlcleaner.TagNode;

public class TagNodeAttNameValueRegexCondition implements ITagNodeCondition {
    private Pattern attNameRegex;
    private Pattern attValueRegex;

    public TagNodeAttNameValueRegexCondition(Pattern pattern, Pattern pattern2) {
        this.attNameRegex = pattern;
        this.attValueRegex = pattern2;
    }

    public boolean satisfy(TagNode tagNode) {
        Pattern pattern;
        if (tagNode == null) {
            return false;
        }
        for (Map.Entry next : tagNode.getAttributes().entrySet()) {
            Pattern pattern2 = this.attNameRegex;
            if ((pattern2 == null || pattern2.matcher((CharSequence) next.getKey()).find()) && ((pattern = this.attValueRegex) == null || pattern.matcher((CharSequence) next.getValue()).find())) {
                return true;
            }
        }
        return false;
    }
}
