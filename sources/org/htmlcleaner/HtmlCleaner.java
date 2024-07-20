package org.htmlcleaner;

import com.mopub.common.AdType;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import org.htmlcleaner.audit.ErrorType;
import org.htmlcleaner.conditional.ITagNodeCondition;
import org.jdom2.JDOMConstants;

public class HtmlCleaner {
    private static final String MARKER_ATTRIBUTE = "htmlcleaner_marker";
    private CleanerProperties properties;
    private CleanerTransformations transformations;

    private class TagPos {
        /* access modifiers changed from: private */

        /* renamed from: info  reason: collision with root package name */
        public TagInfo f5107info;
        /* access modifiers changed from: private */
        public String name;
        /* access modifiers changed from: private */
        public int position;

        TagPos(int i, String str) {
            this.position = i;
            this.name = str;
            this.f5107info = HtmlCleaner.this.getTagInfoProvider().getTagInfo(str);
        }
    }

    private class ChildBreaks {
        private Stack<TagPos> breakingTags;
        /* access modifiers changed from: private */
        public Stack<TagPos> closedByChildBreak;

        private ChildBreaks() {
            this.closedByChildBreak = new Stack<>();
            this.breakingTags = new Stack<>();
        }

        public void addBreak(TagPos tagPos, TagPos tagPos2) {
            this.closedByChildBreak.add(tagPos);
            this.breakingTags.add(tagPos2);
        }

        public boolean isEmpty() {
            return this.closedByChildBreak.isEmpty();
        }

        public String getLastBreakingTag() {
            return this.breakingTags.peek().name;
        }

        public TagPos pop() {
            this.breakingTags.pop();
            return this.closedByChildBreak.pop();
        }

        public int getLastBreakingTagPosition() {
            if (this.breakingTags.isEmpty()) {
                return -1;
            }
            return this.breakingTags.peek().position;
        }
    }

    protected class NestingState {
        private ChildBreaks childBreaks = new ChildBreaks();
        private OpenTags openTags = new OpenTags();

        protected NestingState() {
        }

        public OpenTags getOpenTags() {
            return this.openTags;
        }

        public ChildBreaks getChildBreaks() {
            return this.childBreaks;
        }
    }

    class OpenTags {
        private TagPos last;
        /* access modifiers changed from: private */
        public List<TagPos> list = new ArrayList();
        private Set<String> set = new HashSet();

        OpenTags() {
        }

        /* access modifiers changed from: private */
        public boolean isEmpty() {
            return this.list.isEmpty();
        }

        /* access modifiers changed from: private */
        public void addTag(String str, int i) {
            TagPos tagPos = new TagPos(i, str);
            this.last = tagPos;
            this.list.add(tagPos);
            this.set.add(str);
        }

        /* access modifiers changed from: private */
        public void removeTag(String str) {
            TagPos tagPos;
            List<TagPos> list2 = this.list;
            ListIterator<TagPos> listIterator = list2.listIterator(list2.size());
            while (true) {
                if (listIterator.hasPrevious()) {
                    if (str.equals(listIterator.previous().name)) {
                        listIterator.remove();
                        break;
                    }
                } else {
                    break;
                }
            }
            if (this.list.isEmpty()) {
                tagPos = null;
            } else {
                List<TagPos> list3 = this.list;
                tagPos = list3.get(list3.size() - 1);
            }
            this.last = tagPos;
        }

        /* access modifiers changed from: private */
        public TagPos findFirstTagPos() {
            if (this.list.isEmpty()) {
                return null;
            }
            return this.list.get(0);
        }

        /* access modifiers changed from: private */
        public TagPos getLastTagPos() {
            return this.last;
        }

        /* access modifiers changed from: private */
        public TagPos findTag(String str) {
            if (str != null) {
                List<TagPos> list2 = this.list;
                ListIterator<TagPos> listIterator = list2.listIterator(list2.size());
                TagInfo tagInfo = HtmlCleaner.this.getTagInfoProvider().getTagInfo(str);
                String fatalTag = tagInfo != null ? tagInfo.getFatalTag() : null;
                while (listIterator.hasPrevious()) {
                    TagPos previous = listIterator.previous();
                    if (!str.equals(previous.name)) {
                        if (fatalTag != null && fatalTag.equals(previous.name)) {
                            break;
                        }
                    } else {
                        return previous;
                    }
                }
            }
            return null;
        }

        /* access modifiers changed from: private */
        public boolean tagExists(String str) {
            return findTag(str) != null;
        }

        /* access modifiers changed from: private */
        public TagPos findTagToPlaceRubbish() {
            TagPos tagPos = null;
            if (!isEmpty()) {
                List<TagPos> list2 = this.list;
                ListIterator<TagPos> listIterator = list2.listIterator(list2.size());
                while (true) {
                    TagPos tagPos2 = tagPos;
                    if (!listIterator.hasPrevious()) {
                        break;
                    }
                    tagPos = listIterator.previous();
                    if ((tagPos.f5107info == null || tagPos.f5107info.allowsAnything()) && tagPos2 != null) {
                        return tagPos2;
                    }
                }
            }
            return tagPos;
        }

        /* access modifiers changed from: private */
        public boolean tagEncountered(String str) {
            return this.set.contains(str);
        }

        /* access modifiers changed from: private */
        public boolean someAlreadyOpen(Set<String> set2) {
            for (TagPos access$000 : this.list) {
                if (set2.contains(access$000.name)) {
                    return true;
                }
            }
            return false;
        }
    }

    public HtmlCleaner() {
        this((ITagInfoProvider) null, (CleanerProperties) null);
    }

    public HtmlCleaner(ITagInfoProvider iTagInfoProvider) {
        this(iTagInfoProvider, (CleanerProperties) null);
    }

    public HtmlCleaner(CleanerProperties cleanerProperties) {
        this((ITagInfoProvider) null, cleanerProperties);
    }

    public HtmlCleaner(ITagInfoProvider iTagInfoProvider, CleanerProperties cleanerProperties) {
        cleanerProperties = cleanerProperties == null ? new CleanerProperties() : cleanerProperties;
        this.properties = cleanerProperties;
        cleanerProperties.setTagInfoProvider(iTagInfoProvider == null ? DefaultTagProvider.INSTANCE : iTagInfoProvider);
    }

    public TagNode clean(String str) {
        try {
            return clean((Reader) new StringReader(str), new CleanTimeValues());
        } catch (IOException e) {
            throw new HtmlCleanerException((Throwable) e);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:11|(2:15|16)|17|18|19) */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:3|4|5|6|7|8|9) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0025 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0017 */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0022 A[SYNTHETIC, Splitter:B:15:0x0022] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.htmlcleaner.TagNode clean(java.io.File r3, java.lang.String r4) throws java.io.IOException {
        /*
            r2 = this;
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r3)
            r3 = 0
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch:{ all -> 0x001d }
            r1.<init>(r0, r4)     // Catch:{ all -> 0x001d }
            org.htmlcleaner.CleanTimeValues r3 = new org.htmlcleaner.CleanTimeValues     // Catch:{ all -> 0x001b }
            r3.<init>()     // Catch:{ all -> 0x001b }
            org.htmlcleaner.TagNode r3 = r2.clean((java.io.Reader) r1, (org.htmlcleaner.CleanTimeValues) r3)     // Catch:{ all -> 0x001b }
            r1.close()     // Catch:{ IOException -> 0x0017 }
        L_0x0017:
            r0.close()     // Catch:{ IOException -> 0x001a }
        L_0x001a:
            return r3
        L_0x001b:
            r3 = move-exception
            goto L_0x0020
        L_0x001d:
            r4 = move-exception
            r1 = r3
            r3 = r4
        L_0x0020:
            if (r1 == 0) goto L_0x0025
            r1.close()     // Catch:{ IOException -> 0x0025 }
        L_0x0025:
            r0.close()     // Catch:{ IOException -> 0x0028 }
        L_0x0028:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.htmlcleaner.HtmlCleaner.clean(java.io.File, java.lang.String):org.htmlcleaner.TagNode");
    }

    public TagNode clean(File file) throws IOException {
        return clean(file, this.properties.getCharset());
    }

    @Deprecated
    public TagNode clean(URL url, String str) throws IOException {
        return clean((Reader) new StringReader(C2401Utils.readUrl(url, str).toString()), new CleanTimeValues());
    }

    public TagNode clean(URL url) throws IOException {
        return clean(url, this.properties.getCharset());
    }

    public TagNode clean(InputStream inputStream, String str) throws IOException {
        return clean((Reader) new InputStreamReader(inputStream, str), new CleanTimeValues());
    }

    public TagNode clean(InputStream inputStream) throws IOException {
        return clean(inputStream, this.properties.getCharset());
    }

    public TagNode clean(Reader reader, CleanTimeValues cleanTimeValues) throws IOException {
        pushNesting(cleanTimeValues);
        cleanTimeValues._headOpened = false;
        cleanTimeValues._bodyOpened = false;
        cleanTimeValues._headTags.clear();
        cleanTimeValues.allTags.clear();
        cleanTimeValues.pruneTagSet = new HashSet(this.properties.getPruneTagSet());
        cleanTimeValues.allowTagSet = new HashSet(this.properties.getAllowTagSet());
        this.transformations = this.properties.getCleanerTransformations();
        cleanTimeValues.pruneNodeSet.clear();
        cleanTimeValues.htmlNode = newTagNode(AdType.HTML);
        cleanTimeValues.bodyNode = newTagNode("body");
        cleanTimeValues.headNode = newTagNode("head");
        cleanTimeValues.rootNode = null;
        cleanTimeValues.htmlNode.addChild(cleanTimeValues.headNode);
        cleanTimeValues.htmlNode.addChild(cleanTimeValues.bodyNode);
        HtmlTokenizer htmlTokenizer = new HtmlTokenizer(this, reader, cleanTimeValues);
        htmlTokenizer.start();
        List<BaseToken> tokenList = htmlTokenizer.getTokenList();
        closeAll(tokenList, cleanTimeValues);
        createDocumentNodes(tokenList, cleanTimeValues);
        calculateRootNode(cleanTimeValues, htmlTokenizer.getNamespacePrefixes());
        do {
        } while (markNodesToPrune(tokenList, cleanTimeValues));
        if (cleanTimeValues.pruneNodeSet != null && !cleanTimeValues.pruneNodeSet.isEmpty()) {
            for (TagNode next : cleanTimeValues.pruneNodeSet) {
                TagNode parent = next.getParent();
                if (parent != null) {
                    parent.removeChild(next);
                }
            }
        }
        cleanTimeValues.rootNode.setDocType(htmlTokenizer.getDocType());
        popNesting(cleanTimeValues);
        return cleanTimeValues.rootNode;
    }

    private boolean markNodesToPrune(List list, CleanTimeValues cleanTimeValues) {
        boolean z = false;
        for (Object next : list) {
            if ((next instanceof TagNode) && !cleanTimeValues.pruneNodeSet.contains(next)) {
                TagNode tagNode = (TagNode) next;
                if (addIfNeededToPruneSet(tagNode, cleanTimeValues)) {
                    z = true;
                } else if (!tagNode.isEmpty()) {
                    z |= markNodesToPrune(tagNode.getAllChildren(), cleanTimeValues);
                }
            }
        }
        return z;
    }

    private void calculateRootNode(CleanTimeValues cleanTimeValues, Set<String> set) {
        cleanTimeValues.rootNode = cleanTimeValues.htmlNode;
        if (this.properties.isOmitHtmlEnvelope()) {
            List<Object> allChildren = cleanTimeValues.bodyNode.getAllChildren();
            cleanTimeValues.rootNode = new TagNode((String) null);
            if (allChildren != null) {
                for (Object addChild : allChildren) {
                    cleanTimeValues.rootNode.addChild(addChild);
                }
            }
        }
        Map<String, String> attributes = cleanTimeValues.rootNode.getAttributes();
        if (this.properties.isNamespacesAware() && set != null) {
            for (String next : set) {
                String str = "xmlns:" + next;
                if (!attributes.containsKey(str) && !next.equals(JDOMConstants.NS_PREFIX_XML)) {
                    cleanTimeValues.rootNode.addAttribute(str, next);
                }
            }
        }
    }

    private void addAttributesToTag(TagNode tagNode, Map<String, String> map) {
        if (map != null) {
            Map<String, String> attributes = tagNode.getAttributes();
            for (Map.Entry next : map.entrySet()) {
                String str = (String) next.getKey();
                if (!attributes.containsKey(str)) {
                    tagNode.addAttribute(str, (String) next.getValue());
                }
            }
        }
    }

    private boolean isFatalTagSatisfied(TagInfo tagInfo, CleanTimeValues cleanTimeValues) {
        String fatalTag;
        if (tagInfo == null || (fatalTag = tagInfo.getFatalTag()) == null) {
            return true;
        }
        return getOpenTags(cleanTimeValues).tagExists(fatalTag);
    }

    private boolean mustAddRequiredParent(TagInfo tagInfo, CleanTimeValues cleanTimeValues) {
        TagPos access$500;
        if (tagInfo == null || tagInfo.getRequiredParent() == null) {
            return false;
        }
        String fatalTag = tagInfo.getFatalTag();
        int i = -1;
        if (!(fatalTag == null || (access$500 = getOpenTags(cleanTimeValues).findTag(fatalTag)) == null)) {
            i = access$500.position;
        }
        ListIterator listIterator = getOpenTags(cleanTimeValues).list.listIterator(getOpenTags(cleanTimeValues).list.size());
        while (listIterator.hasPrevious()) {
            TagPos tagPos = (TagPos) listIterator.previous();
            if (tagInfo.isHigher(tagPos.name)) {
                if (tagPos.position <= i) {
                    return true;
                }
                return false;
            }
        }
        return true;
    }

    private TagNode newTagNode(String str) {
        return new TagNode(str);
    }

    private TagNode createTagNode(TagNode tagNode) {
        tagNode.setFormed();
        return tagNode;
    }

    private boolean isAllowedInLastOpenTag(BaseToken baseToken, CleanTimeValues cleanTimeValues) {
        TagPos access$700 = getOpenTags(cleanTimeValues).getLastTagPos();
        if (access$700 == null || access$700.f5107info == null) {
            return true;
        }
        return access$700.f5107info.allowsItem(baseToken);
    }

    private void saveToLastOpenTag(List list, Object obj, CleanTimeValues cleanTimeValues) {
        TagPos access$800;
        TagPos access$700 = getOpenTags(cleanTimeValues).getLastTagPos();
        if ((access$700 == null || access$700.f5107info == null || !access$700.f5107info.isIgnorePermitted()) && (access$800 = getOpenTags(cleanTimeValues).findTagToPlaceRubbish()) != null) {
            ((TagNode) list.get(access$800.position)).addItemForMoving(obj);
        }
    }

    private boolean isStartToken(Object obj) {
        return (obj instanceof TagNode) && !((TagNode) obj).isFormed();
    }

    /* access modifiers changed from: package-private */
    public void makeTree(List list, ListIterator<BaseToken> listIterator, CleanTimeValues cleanTimeValues) {
        TagInfo tagInfo;
        int i;
        while (listIterator.hasNext()) {
            BaseToken next = listIterator.next();
            if (next instanceof EndTagToken) {
                EndTagToken endTagToken = (EndTagToken) next;
                String name = endTagToken.getName();
                TagInfo tagInfo2 = getTagInfoProvider().getTagInfo(name);
                if ((tagInfo2 == null && this.properties.isOmitUnknownTags()) || (tagInfo2 != null && tagInfo2.isDeprecated() && this.properties.isOmitDeprecatedTags())) {
                    listIterator.set((Object) null);
                } else if (tagInfo2 == null || tagInfo2.allowsBody()) {
                    TagPos access$500 = getOpenTags(cleanTimeValues).findTag(name);
                    if (access$500 != null) {
                        List<TagNode> closeSnippet = closeSnippet(list, access$500, endTagToken, cleanTimeValues);
                        listIterator.set((Object) null);
                        for (int size = closeSnippet.size() - 1; size >= 0; size--) {
                            TagNode tagNode = closeSnippet.get(size);
                            if (size > 0 && tagInfo2 != null && tagInfo2.isContinueAfter(tagNode.getName())) {
                                TagNode makeCopy = tagNode.makeCopy();
                                makeCopy.setAutoGenerated(true);
                                listIterator.add(makeCopy);
                                listIterator.previous();
                            }
                        }
                        if (!getChildBreaks(cleanTimeValues).isEmpty()) {
                            while (access$500.position < getChildBreaks(cleanTimeValues).getLastBreakingTagPosition()) {
                                getChildBreaks(cleanTimeValues).pop();
                            }
                        }
                        while (!getChildBreaks(cleanTimeValues).isEmpty() && name.equals(getChildBreaks(cleanTimeValues).getLastBreakingTag()) && access$500.position == getChildBreaks(cleanTimeValues).getLastBreakingTagPosition()) {
                            if (list.get(((TagPos) getChildBreaks(cleanTimeValues).closedByChildBreak.peek()).position) != null) {
                                int access$100 = getChildBreaks(cleanTimeValues).pop().position;
                                Object obj = list.get(access$100);
                                if (obj instanceof TagNode) {
                                    reopenBrokenNode(listIterator, (TagNode) obj, cleanTimeValues);
                                } else if (obj instanceof List) {
                                    for (TagNode add : (List) obj) {
                                        listIterator.add(add);
                                        makeTree(list, list.listIterator(list.size() - 1), cleanTimeValues);
                                    }
                                    list.set(access$100, (Object) null);
                                }
                            } else {
                                getChildBreaks(cleanTimeValues).pop();
                            }
                        }
                    }
                } else {
                    listIterator.set((Object) null);
                }
            } else if (isStartToken(next)) {
                TagNode tagNode2 = (TagNode) next;
                String name2 = tagNode2.getName();
                TagInfo tagInfo3 = getTagInfoProvider().getTagInfo(name2);
                TagPos access$700 = getOpenTags(cleanTimeValues).isEmpty() ? null : getOpenTags(cleanTimeValues).getLastTagPos();
                if (access$700 == null) {
                    tagInfo = null;
                } else {
                    tagInfo = getTagInfoProvider().getTagInfo(access$700.name);
                }
                cleanTimeValues.allTags.add(name2);
                if (AdType.HTML.equals(name2)) {
                    addAttributesToTag(cleanTimeValues.htmlNode, tagNode2.getAttributes());
                    listIterator.set((Object) null);
                } else if ("body".equals(name2)) {
                    cleanTimeValues._bodyOpened = true;
                    addAttributesToTag(cleanTimeValues.bodyNode, tagNode2.getAttributes());
                    listIterator.set((Object) null);
                } else if ("head".equals(name2)) {
                    cleanTimeValues._headOpened = true;
                    addAttributesToTag(cleanTimeValues.headNode, tagNode2.getAttributes());
                    listIterator.set((Object) null);
                } else if (tagInfo3 == null && this.properties.isOmitUnknownTags()) {
                    listIterator.set((Object) null);
                    this.properties.fireUglyHtml(true, tagNode2, ErrorType.Unknown);
                } else if (tagInfo3 != null && tagInfo3.isDeprecated() && this.properties.isOmitDeprecatedTags()) {
                    listIterator.set((Object) null);
                    this.properties.fireUglyHtml(true, tagNode2, ErrorType.Deprecated);
                } else if (tagInfo3 == null && tagInfo != null && !tagInfo.allowsAnything()) {
                    closeSnippet(list, access$700, tagNode2, cleanTimeValues);
                    listIterator.previous();
                } else if (tagInfo3 != null && tagInfo3.hasPermittedTags() && getOpenTags(cleanTimeValues).someAlreadyOpen(tagInfo3.getPermittedTags())) {
                    listIterator.set((Object) null);
                } else if (tagInfo3 != null && tagInfo3.isUnique() && getOpenTags(cleanTimeValues).tagEncountered(name2)) {
                    listIterator.set((Object) null);
                    this.properties.fireHtmlError(true, tagNode2, ErrorType.UniqueTagDuplicated);
                } else if (!isFatalTagSatisfied(tagInfo3, cleanTimeValues)) {
                    listIterator.set((Object) null);
                    this.properties.fireHtmlError(true, tagNode2, ErrorType.FatalTagMissing);
                } else if (mustAddRequiredParent(tagInfo3, cleanTimeValues)) {
                    TagNode newTagNode = newTagNode(tagInfo3.getRequiredParent());
                    newTagNode.setAutoGenerated(true);
                    listIterator.previous();
                    listIterator.add(newTagNode);
                    listIterator.previous();
                    this.properties.fireHtmlError(true, tagNode2, ErrorType.RequiredParentMissing);
                } else if (tagInfo3 != null && access$700 != null && tagInfo3.isMustCloseTag(tagInfo)) {
                    getChildBreaks(cleanTimeValues).addBreak(access$700, new TagPos(listIterator.previousIndex(), tagInfo3.getName()));
                    this.properties.fireHtmlError(!tagNode2.hasAttribute("id"), (TagNode) list.get(access$700.position), ErrorType.UnpermittedChild);
                    List<TagNode> closeSnippet2 = closeSnippet(list, access$700, tagNode2, cleanTimeValues);
                    int size2 = closeSnippet2.size();
                    if (tagInfo3.hasCopyTags() && size2 > 0) {
                        ListIterator<TagNode> listIterator2 = closeSnippet2.listIterator(size2);
                        ArrayList<TagNode> arrayList = new ArrayList<>();
                        while (true) {
                            if (!listIterator2.hasPrevious()) {
                                break;
                            }
                            TagNode previous = listIterator2.previous();
                            if (!tagInfo3.isCopy(previous.getName())) {
                                break;
                            }
                            arrayList.add(0, previous);
                        }
                        if (arrayList.size() > 0) {
                            for (TagNode makeCopy2 : arrayList) {
                                listIterator.add(makeCopy2.makeCopy());
                            }
                            for (i = 0; i < arrayList.size(); i++) {
                                listIterator.previous();
                            }
                        }
                    }
                    listIterator.previous();
                } else if (!isAllowedInLastOpenTag(next, cleanTimeValues)) {
                    saveToLastOpenTag(list, next, cleanTimeValues);
                    listIterator.set((Object) null);
                } else if (tagInfo3 == null || tagInfo3.allowsBody()) {
                    getOpenTags(cleanTimeValues).addTag(name2, listIterator.previousIndex());
                } else {
                    TagNode createTagNode = createTagNode(tagNode2);
                    addPossibleHeadCandidate(tagInfo3, createTagNode, cleanTimeValues);
                    listIterator.set(createTagNode);
                }
            } else {
                if (cleanTimeValues._headOpened && !cleanTimeValues._bodyOpened && this.properties.isKeepWhitespaceAndCommentsInHead()) {
                    if (next instanceof CommentNode) {
                        if (getOpenTags(cleanTimeValues).getLastTagPos() == null) {
                            cleanTimeValues._headTags.add(new ProxyTagNode((CommentNode) next, cleanTimeValues.bodyNode));
                        }
                    } else if (next instanceof ContentNode) {
                        ContentNode contentNode = (ContentNode) next;
                        if (contentNode.isBlank() && ((BaseToken) list.get(list.size() - 1)) == next) {
                            cleanTimeValues._headTags.add(new ProxyTagNode(contentNode, cleanTimeValues.bodyNode));
                        }
                    }
                }
                if (!isAllowedInLastOpenTag(next, cleanTimeValues)) {
                    saveToLastOpenTag(list, next, cleanTimeValues);
                    listIterator.set((Object) null);
                }
            }
        }
    }

    private void reopenBrokenNode(ListIterator<BaseToken> listIterator, TagNode tagNode, CleanTimeValues cleanTimeValues) {
        TagNode makeCopy = tagNode.makeCopy();
        makeCopy.setAutoGenerated(true);
        makeCopy.removeAttribute("id");
        listIterator.add(makeCopy);
        getOpenTags(cleanTimeValues).addTag(tagNode.getName(), listIterator.previousIndex());
    }

    /* access modifiers changed from: protected */
    public boolean isRemovingNodeReasonablySafe(TagNode tagNode) {
        return !tagNode.hasAttribute("id") && !tagNode.hasAttribute("name") && !tagNode.hasAttribute("class");
    }

    private void createDocumentNodes(List list, CleanTimeValues cleanTimeValues) {
        boolean z;
        Iterator it = list.iterator();
        while (true) {
            boolean z2 = true;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (next != null) {
                if (next instanceof TagNode) {
                    TagNode tagNode = (TagNode) next;
                    addPossibleHeadCandidate(getTagInfoProvider().getTagInfo(tagNode.getName()), tagNode, cleanTimeValues);
                } else if (next instanceof ContentNode) {
                    z2 = true ^ "".equals(next.toString());
                }
                if (z2) {
                    cleanTimeValues.bodyNode.addChild(next);
                }
            }
        }
        for (TagNode tagNode2 : cleanTimeValues._headTags) {
            TagNode parent = tagNode2.getParent();
            while (true) {
                if (parent == null) {
                    z = true;
                    break;
                } else if (cleanTimeValues._headTags.contains(parent)) {
                    z = false;
                    break;
                } else {
                    parent = parent.getParent();
                }
            }
            if (z) {
                tagNode2.removeFromTree();
                cleanTimeValues.headNode.addChild(tagNode2);
            }
        }
    }

    private List<TagNode> closeSnippet(List list, TagPos tagPos, Object obj, CleanTimeValues cleanTimeValues) {
        ArrayList arrayList = new ArrayList();
        ListIterator listIterator = list.listIterator(tagPos.position);
        Object next = listIterator.next();
        TagNode tagNode = null;
        boolean z = false;
        while (true) {
            if ((obj != null || z) && (obj == null || next == obj)) {
                return arrayList;
            }
            if (isStartToken(next)) {
                TagNode tagNode2 = (TagNode) next;
                arrayList.add(tagNode2);
                List itemsToMove = tagNode2.getItemsToMove();
                if (itemsToMove != null) {
                    pushNesting(cleanTimeValues);
                    makeTree(itemsToMove, itemsToMove.listIterator(0), cleanTimeValues);
                    closeAll(itemsToMove, cleanTimeValues);
                    tagNode2.setItemsToMove((List) null);
                    popNesting(cleanTimeValues);
                }
                TagNode createTagNode = createTagNode(tagNode2);
                addPossibleHeadCandidate(getTagInfoProvider().getTagInfo(createTagNode.getName()), createTagNode, cleanTimeValues);
                if (tagNode != null) {
                    tagNode.addChildren(itemsToMove);
                    tagNode.addChild(createTagNode);
                    listIterator.set((Object) null);
                } else if (itemsToMove != null) {
                    itemsToMove.add(createTagNode);
                    listIterator.set(itemsToMove);
                } else {
                    listIterator.set(createTagNode);
                }
                getOpenTags(cleanTimeValues).removeTag(createTagNode.getName());
                tagNode = createTagNode;
            } else if (tagNode != null) {
                listIterator.set((Object) null);
                if (next != null) {
                    tagNode.addChild(next);
                }
            }
            if (listIterator.hasNext()) {
                next = listIterator.next();
            } else {
                z = true;
            }
        }
        return arrayList;
    }

    private void closeAll(List list, CleanTimeValues cleanTimeValues) {
        TagPos access$1500 = getOpenTags(cleanTimeValues).findFirstTagPos();
        for (TagPos access$100 : getOpenTags(cleanTimeValues).list) {
            this.properties.fireHtmlError(true, (TagNode) list.get(access$100.position), ErrorType.UnclosedTag);
        }
        if (access$1500 != null) {
            closeSnippet(list, access$1500, (Object) null, cleanTimeValues);
        }
    }

    private void addPossibleHeadCandidate(TagInfo tagInfo, TagNode tagNode, CleanTimeValues cleanTimeValues) {
        if (tagInfo != null && tagNode != null) {
            if (tagInfo.isHeadTag() || (tagInfo.isHeadAndBodyTag() && cleanTimeValues._headOpened && !cleanTimeValues._bodyOpened)) {
                cleanTimeValues._headTags.add(tagNode);
            }
        }
    }

    public CleanerProperties getProperties() {
        return this.properties;
    }

    public Set<ITagNodeCondition> getPruneTagSet(CleanTimeValues cleanTimeValues) {
        return cleanTimeValues.pruneTagSet;
    }

    public Set<ITagNodeCondition> getAllowTagSet(CleanTimeValues cleanTimeValues) {
        return cleanTimeValues.allowTagSet;
    }

    public void addPruneNode(TagNode tagNode, CleanTimeValues cleanTimeValues) {
        tagNode.setPruned(true);
        cleanTimeValues.pruneNodeSet.add(tagNode);
    }

    private boolean addIfNeededToPruneSet(TagNode tagNode, CleanTimeValues cleanTimeValues) {
        if (cleanTimeValues.pruneTagSet != null) {
            for (ITagNodeCondition next : cleanTimeValues.pruneTagSet) {
                if (next.satisfy(tagNode)) {
                    addPruneNode(tagNode, cleanTimeValues);
                    this.properties.fireConditionModification(next, tagNode);
                    return true;
                }
            }
        }
        if (cleanTimeValues.allowTagSet == null || cleanTimeValues.allowTagSet.isEmpty()) {
            return false;
        }
        for (ITagNodeCondition satisfy : cleanTimeValues.allowTagSet) {
            if (satisfy.satisfy(tagNode)) {
                return false;
            }
        }
        if (!tagNode.isAutoGenerated()) {
            this.properties.fireUserDefinedModification(true, tagNode, ErrorType.NotAllowedTag);
        }
        addPruneNode(tagNode, cleanTimeValues);
        return true;
    }

    public Set<String> getAllTags(CleanTimeValues cleanTimeValues) {
        return cleanTimeValues.allTags;
    }

    public ITagInfoProvider getTagInfoProvider() {
        return this.properties.getTagInfoProvider();
    }

    public CleanerTransformations getTransformations() {
        return this.transformations;
    }

    public String getInnerHtml(TagNode tagNode) {
        if (tagNode != null) {
            String asString = new SimpleXmlSerializer(this.properties).getAsString(tagNode);
            int indexOf = asString.indexOf(62, asString.indexOf("<" + tagNode.getName()) + 1);
            int lastIndexOf = asString.lastIndexOf(60);
            if (indexOf < 0 || indexOf > lastIndexOf) {
                return null;
            }
            return asString.substring(indexOf + 1, lastIndexOf);
        }
        throw new HtmlCleanerException("Cannot return inner html of the null node!");
    }

    public void setInnerHtml(TagNode tagNode, String str) {
        if (tagNode != null) {
            String name = tagNode.getName();
            StringBuilder sb = new StringBuilder();
            sb.append("<");
            sb.append(name);
            sb.append(" htmlcleaner_marker=''>");
            sb.append(str);
            sb.append("</");
            sb.append(name);
            sb.append(">");
            for (TagNode parent = tagNode.getParent(); parent != null; parent = parent.getParent()) {
                String name2 = parent.getName();
                sb.insert(0, "<" + name2 + ">");
                sb.append("</");
                sb.append(name2);
                sb.append(">");
            }
            TagNode findElementHavingAttribute = clean(sb.toString()).findElementHavingAttribute(MARKER_ATTRIBUTE, true);
            if (findElementHavingAttribute != null) {
                tagNode.setChildren(findElementHavingAttribute.getAllChildren());
            }
        }
    }

    public void initCleanerTransformations(Map map) {
        this.transformations = new CleanerTransformations(map);
    }

    private OpenTags getOpenTags(CleanTimeValues cleanTimeValues) {
        return cleanTimeValues.nestingStates.peek().getOpenTags();
    }

    private ChildBreaks getChildBreaks(CleanTimeValues cleanTimeValues) {
        return cleanTimeValues.nestingStates.peek().getChildBreaks();
    }

    private NestingState pushNesting(CleanTimeValues cleanTimeValues) {
        return cleanTimeValues.nestingStates.push(new NestingState());
    }

    private NestingState popNesting(CleanTimeValues cleanTimeValues) {
        return cleanTimeValues.nestingStates.pop();
    }
}
