package org.htmlcleaner;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.conditional.ITagNodeCondition;

class CleanTimeValues {
    boolean _bodyOpened = false;
    boolean _headOpened = false;
    Set _headTags = new LinkedHashSet();
    Set allTags = new TreeSet();
    Set<ITagNodeCondition> allowTagSet;
    TagNode bodyNode;
    TagNode headNode;
    TagNode htmlNode;
    transient Stack<HtmlCleaner.NestingState> nestingStates = new Stack<>();
    Set<TagNode> pruneNodeSet = new HashSet();
    Set<ITagNodeCondition> pruneTagSet = new HashSet();
    TagNode rootNode;

    CleanTimeValues() {
    }
}
