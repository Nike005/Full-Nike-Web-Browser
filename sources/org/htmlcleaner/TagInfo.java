package org.htmlcleaner;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class TagInfo {
    private BelongsTo belongsTo = BelongsTo.BODY;
    private Set<String> childTags = new HashSet();
    private CloseTag closeTag;
    private ContentType contentType;
    private Set<String> continueAfterTags = new HashSet();
    private Set<String> copyTags = new HashSet();
    private boolean deprecated;
    private Display display;
    private String fatalTag;
    private Set<String> higherTags = new HashSet();
    private boolean ignorePermitted;
    private Set<String> mustCloseTags = new HashSet();
    private String name;
    private Set<String> permittedTags = new HashSet();
    private String requiredParent;
    private boolean unique;

    public TagInfo(String str, ContentType contentType2, BelongsTo belongsTo2, boolean z, boolean z2, boolean z3, CloseTag closeTag2, Display display2) {
        this.name = str;
        this.contentType = contentType2;
        this.belongsTo = belongsTo2;
        this.deprecated = z;
        this.unique = z2;
        this.ignorePermitted = z3;
        this.closeTag = closeTag2;
        this.display = display2;
    }

    public void defineFatalTags(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str.toLowerCase(), ",");
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            this.fatalTag = nextToken;
            this.higherTags.add(nextToken);
        }
    }

    public void defineRequiredEnclosingTags(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str.toLowerCase(), ",");
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            this.requiredParent = nextToken;
            this.higherTags.add(nextToken);
        }
    }

    public void defineForbiddenTags(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str.toLowerCase(), ",");
        while (stringTokenizer.hasMoreTokens()) {
            this.permittedTags.add(stringTokenizer.nextToken());
        }
    }

    public void defineAllowedChildrenTags(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str.toLowerCase(), ",");
        while (stringTokenizer.hasMoreTokens()) {
            this.childTags.add(stringTokenizer.nextToken());
        }
    }

    public void defineHigherLevelTags(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str.toLowerCase(), ",");
        while (stringTokenizer.hasMoreTokens()) {
            this.higherTags.add(stringTokenizer.nextToken());
        }
    }

    public void defineCloseBeforeCopyInsideTags(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str.toLowerCase(), ",");
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            this.copyTags.add(nextToken);
            this.mustCloseTags.add(nextToken);
        }
    }

    public void defineCloseInsideCopyAfterTags(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str.toLowerCase(), ",");
        while (stringTokenizer.hasMoreTokens()) {
            this.continueAfterTags.add(stringTokenizer.nextToken());
        }
    }

    public void defineCloseBeforeTags(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str.toLowerCase(), ",");
        while (stringTokenizer.hasMoreTokens()) {
            this.mustCloseTags.add(stringTokenizer.nextToken());
        }
    }

    public Display getDisplay() {
        return this.display;
    }

    public void setDisplay(Display display2) {
        this.display = display2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public ContentType getContentType() {
        return this.contentType;
    }

    public Set<String> getMustCloseTags() {
        return this.mustCloseTags;
    }

    public void setMustCloseTags(Set<String> set) {
        this.mustCloseTags = set;
    }

    public Set<String> getHigherTags() {
        return this.higherTags;
    }

    public void setHigherTags(Set<String> set) {
        this.higherTags = set;
    }

    public Set<String> getChildTags() {
        return this.childTags;
    }

    public void setChildTags(Set<String> set) {
        this.childTags = set;
    }

    public Set<String> getPermittedTags() {
        return this.permittedTags;
    }

    public void setPermittedTags(Set<String> set) {
        this.permittedTags = set;
    }

    public Set<String> getCopyTags() {
        return this.copyTags;
    }

    public void setCopyTags(Set<String> set) {
        this.copyTags = set;
    }

    public Set<String> getContinueAfterTags() {
        return this.continueAfterTags;
    }

    public void setContinueAfterTags(Set<String> set) {
        this.continueAfterTags = set;
    }

    public String getRequiredParent() {
        return this.requiredParent;
    }

    public void setRequiredParent(String str) {
        this.requiredParent = str;
    }

    public BelongsTo getBelongsTo() {
        return this.belongsTo;
    }

    public void setBelongsTo(BelongsTo belongsTo2) {
        this.belongsTo = belongsTo2;
    }

    public String getFatalTag() {
        return this.fatalTag;
    }

    public void setFatalTag(String str) {
        this.fatalTag = str;
    }

    public boolean isDeprecated() {
        return this.deprecated;
    }

    public void setDeprecated(boolean z) {
        this.deprecated = z;
    }

    public boolean isUnique() {
        return this.unique;
    }

    public void setUnique(boolean z) {
        this.unique = z;
    }

    public boolean isIgnorePermitted() {
        return this.ignorePermitted;
    }

    public boolean isEmptyTag() {
        return ContentType.none == this.contentType;
    }

    public void setIgnorePermitted(boolean z) {
        this.ignorePermitted = z;
    }

    /* access modifiers changed from: package-private */
    public boolean allowsBody() {
        return ContentType.none != this.contentType;
    }

    /* access modifiers changed from: package-private */
    public boolean isHigher(String str) {
        return this.higherTags.contains(str);
    }

    /* access modifiers changed from: package-private */
    public boolean isCopy(String str) {
        return this.copyTags.contains(str);
    }

    /* access modifiers changed from: package-private */
    public boolean hasCopyTags() {
        return !this.copyTags.isEmpty();
    }

    /* access modifiers changed from: package-private */
    public boolean isContinueAfter(String str) {
        return this.continueAfterTags.contains(str);
    }

    /* access modifiers changed from: package-private */
    public boolean hasPermittedTags() {
        return !this.permittedTags.isEmpty();
    }

    /* access modifiers changed from: package-private */
    public boolean isHeadTag() {
        return this.belongsTo == BelongsTo.HEAD;
    }

    /* access modifiers changed from: package-private */
    public boolean isHeadAndBodyTag() {
        return this.belongsTo == BelongsTo.HEAD || this.belongsTo == BelongsTo.HEAD_AND_BODY;
    }

    /* access modifiers changed from: package-private */
    public boolean isMustCloseTag(TagInfo tagInfo) {
        if (tagInfo != null) {
            return this.mustCloseTags.contains(tagInfo.getName()) || tagInfo.contentType == ContentType.text;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean allowsItem(BaseToken baseToken) {
        if (this.contentType != ContentType.none && (baseToken instanceof TagToken) && "script".equals(((TagToken) baseToken).getName())) {
            return true;
        }
        int i = C24001.$SwitchMap$org$htmlcleaner$ContentType[this.contentType.ordinal()];
        if (i == 1) {
            if (!this.childTags.isEmpty()) {
                if (baseToken instanceof TagToken) {
                    return this.childTags.contains(((TagToken) baseToken).getName());
                }
            } else if (!this.permittedTags.isEmpty() && (baseToken instanceof TagToken)) {
                return !this.permittedTags.contains(((TagToken) baseToken).getName());
            }
            return true;
        } else if (i == 2) {
            return !(baseToken instanceof TagToken);
        } else {
            if (i != 3) {
                return false;
            }
            if (baseToken instanceof ContentNode) {
                return ((ContentNode) baseToken).isBlank();
            }
            if (!(baseToken instanceof TagToken)) {
                return true;
            }
            return false;
        }
    }

    /* renamed from: org.htmlcleaner.TagInfo$1 */
    static /* synthetic */ class C24001 {
        static final /* synthetic */ int[] $SwitchMap$org$htmlcleaner$ContentType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.htmlcleaner.ContentType[] r0 = org.htmlcleaner.ContentType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$htmlcleaner$ContentType = r0
                org.htmlcleaner.ContentType r1 = org.htmlcleaner.ContentType.all     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$htmlcleaner$ContentType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.htmlcleaner.ContentType r1 = org.htmlcleaner.ContentType.text     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$htmlcleaner$ContentType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.htmlcleaner.ContentType r1 = org.htmlcleaner.ContentType.none     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.htmlcleaner.TagInfo.C24001.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public boolean allowsAnything() {
        return ContentType.all == this.contentType && this.childTags.isEmpty();
    }

    public boolean isMinimizedTagPermitted() {
        return this.closeTag.isMinimizedTagPermitted();
    }
}
