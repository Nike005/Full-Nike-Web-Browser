package org.htmlcleaner;

import org.apache.tools.ant.Task;

public class HtmlCleanerForAnt extends Task {
    private boolean advancedxmlescape = true;
    private boolean allowhtmlinsideattributes = false;
    private boolean allowmultiwordattributes = true;
    private String booleanatts = CleanerProperties.BOOL_ATT_SELF;
    private String dest;
    private String hyphenreplacement = "=";
    private boolean ignoreqe = false;
    private String incharset = "UTF-8";
    private boolean namespacesaware = true;
    private String nodebyxpath = null;
    private boolean omitcomments = false;
    private boolean omitdeprtags = false;
    private boolean omitdoctypedecl = true;
    private boolean omithtmlenvelope = false;
    private boolean omitunknowntags = false;
    private boolean omitxmldecl = false;
    private String outcharset = "UTF-8";
    private String outputtype = "simple";
    private String prunetags = "";
    private boolean specialentities = true;
    private String src;
    private String taginfofile = null;
    private String text;
    private String transform = null;
    private boolean treatdeprtagsascontent = false;
    private boolean treatunknowntagsascontent = false;
    private boolean unicodechars = true;
    private boolean usecdata = true;
    private boolean useemptyelementtags = true;

    public void setText(String str) {
        this.text = str;
    }

    public void setSrc(String str) {
        this.src = str;
    }

    public void setDest(String str) {
        this.dest = str;
    }

    public void setIncharset(String str) {
        this.incharset = str;
    }

    public void setOutcharset(String str) {
        this.outcharset = str;
    }

    public void setTaginfofile(String str) {
        this.taginfofile = str;
    }

    public void setOutputtype(String str) {
        this.outputtype = str;
    }

    public void setAdvancedxmlescape(boolean z) {
        this.advancedxmlescape = z;
    }

    public void setUsecdata(boolean z) {
        this.usecdata = z;
    }

    public void setSpecialentities(boolean z) {
        this.specialentities = z;
    }

    public void setUnicodechars(boolean z) {
        this.unicodechars = z;
    }

    public void setOmitunknowntags(boolean z) {
        this.omitunknowntags = z;
    }

    public void setTreatunknowntagsascontent(boolean z) {
        this.treatunknowntagsascontent = z;
    }

    public void setOmitdeprtags(boolean z) {
        this.omitdeprtags = z;
    }

    public void setTreatdeprtagsascontent(boolean z) {
        this.treatdeprtagsascontent = z;
    }

    public void setOmitcomments(boolean z) {
        this.omitcomments = z;
    }

    public void setOmitxmldecl(boolean z) {
        this.omitxmldecl = z;
    }

    public void setOmitdoctypedecl(boolean z) {
        this.omitdoctypedecl = z;
    }

    public void setOmithtmlenvelope(boolean z) {
        this.omithtmlenvelope = z;
    }

    public void setUseemptyelementtags(boolean z) {
        this.useemptyelementtags = z;
    }

    public void setAllowmultiwordattributes(boolean z) {
        this.allowmultiwordattributes = z;
    }

    public void setAllowhtmlinsideattributes(boolean z) {
        this.allowhtmlinsideattributes = z;
    }

    public void setIgnoreqe(boolean z) {
        this.ignoreqe = z;
    }

    public void setNamespacesaware(boolean z) {
        this.namespacesaware = z;
    }

    public void setHyphenreplacement(String str) {
        this.hyphenreplacement = str;
    }

    public void setPrunetags(String str) {
        this.prunetags = str;
    }

    public void setBooleanatts(String str) {
        this.booleanatts = str;
    }

    public void setNodebyxpath(String str) {
        this.nodebyxpath = str;
    }

    public void setTransform(String str) {
        this.transform = str;
    }

    public void addText(String str) {
        this.text = str;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v31, resolved type: java.lang.Object[]} */
    /* JADX WARNING: type inference failed for: r5v0 */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0190, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x019e, code lost:
        throw new org.apache.tools.ant.BuildException(r0);
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0150 A[Catch:{ IOException -> 0x0192, XPatherException -> 0x0190, IOException -> 0x019f, XPatherException -> 0x0190 }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x015b A[Catch:{ IOException -> 0x0192, XPatherException -> 0x0190, IOException -> 0x019f, XPatherException -> 0x0190 }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0190 A[Catch:{ IOException -> 0x0192, XPatherException -> 0x0190, IOException -> 0x019f, XPatherException -> 0x0190 }, ExcHandler: XPatherException (r0v4 'e' org.htmlcleaner.XPatherException A[CUSTOM_DECLARE, Catch:{  }]), Splitter:B:23:0x00d1] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void execute() throws org.apache.tools.ant.BuildException {
        /*
            r10 = this;
            java.lang.String r0 = r10.taginfofile
            if (r0 == 0) goto L_0x0016
            org.htmlcleaner.HtmlCleaner r0 = new org.htmlcleaner.HtmlCleaner
            org.htmlcleaner.ConfigFileTagProvider r1 = new org.htmlcleaner.ConfigFileTagProvider
            java.io.File r2 = new java.io.File
            java.lang.String r3 = r10.taginfofile
            r2.<init>(r3)
            r1.<init>((java.io.File) r2)
            r0.<init>((org.htmlcleaner.ITagInfoProvider) r1)
            goto L_0x001b
        L_0x0016:
            org.htmlcleaner.HtmlCleaner r0 = new org.htmlcleaner.HtmlCleaner
            r0.<init>()
        L_0x001b:
            java.lang.String r1 = r10.text
            if (r1 != 0) goto L_0x002c
            java.lang.String r1 = r10.src
            if (r1 == 0) goto L_0x0024
            goto L_0x002c
        L_0x0024:
            org.apache.tools.ant.BuildException r0 = new org.apache.tools.ant.BuildException
            java.lang.String r1 = "Eather attribute 'src' or text body containing HTML must be specified!"
            r0.<init>(r1)
            throw r0
        L_0x002c:
            org.htmlcleaner.CleanerProperties r1 = r0.getProperties()
            boolean r2 = r10.advancedxmlescape
            r1.setAdvancedXmlEscape(r2)
            boolean r2 = r10.usecdata
            r1.setUseCdataForScriptAndStyle(r2)
            boolean r2 = r10.specialentities
            r1.setTranslateSpecialEntities(r2)
            boolean r2 = r10.unicodechars
            r1.setRecognizeUnicodeChars(r2)
            boolean r2 = r10.omitunknowntags
            r1.setOmitUnknownTags(r2)
            boolean r2 = r10.treatunknowntagsascontent
            r1.setTreatUnknownTagsAsContent(r2)
            boolean r2 = r10.omitdeprtags
            r1.setOmitDeprecatedTags(r2)
            boolean r2 = r10.treatdeprtagsascontent
            r1.setTreatDeprecatedTagsAsContent(r2)
            boolean r2 = r10.omitcomments
            r1.setOmitComments(r2)
            boolean r2 = r10.omitxmldecl
            r1.setOmitXmlDeclaration(r2)
            boolean r2 = r10.omitdoctypedecl
            r1.setOmitDoctypeDeclaration(r2)
            boolean r2 = r10.omithtmlenvelope
            r1.setOmitHtmlEnvelope(r2)
            boolean r2 = r10.useemptyelementtags
            r1.setUseEmptyElementTags(r2)
            boolean r2 = r10.allowmultiwordattributes
            r1.setAllowMultiWordAttributes(r2)
            boolean r2 = r10.allowhtmlinsideattributes
            r1.setAllowHtmlInsideAttributes(r2)
            boolean r2 = r10.ignoreqe
            r1.setIgnoreQuestAndExclam(r2)
            boolean r2 = r10.namespacesaware
            r1.setNamespacesAware(r2)
            java.lang.String r2 = r10.hyphenreplacement
            r1.setHyphenReplacementInComment(r2)
            java.lang.String r2 = r10.prunetags
            r1.setPruneTags(r2)
            java.lang.String r2 = r10.booleanatts
            r1.setBooleanAttributeValues(r2)
            java.lang.String r2 = r10.transform
            boolean r2 = org.htmlcleaner.C2401Utils.isEmptyString(r2)
            r3 = 0
            if (r2 != 0) goto L_0x00d1
            java.lang.String r2 = r10.transform
            java.lang.String r4 = "|"
            java.lang.String[] r2 = org.htmlcleaner.C2401Utils.tokenize(r2, r4)
            java.util.TreeMap r4 = new java.util.TreeMap
            r4.<init>()
            int r5 = r2.length
            r6 = 0
        L_0x00ac:
            if (r6 >= r5) goto L_0x00ce
            r7 = r2[r6]
            r8 = 61
            int r8 = r7.indexOf(r8)
            if (r8 > 0) goto L_0x00ba
            r9 = r7
            goto L_0x00be
        L_0x00ba:
            java.lang.String r9 = r7.substring(r3, r8)
        L_0x00be:
            if (r8 > 0) goto L_0x00c2
            r7 = 0
            goto L_0x00c8
        L_0x00c2:
            int r8 = r8 + 1
            java.lang.String r7 = r7.substring(r8)
        L_0x00c8:
            r4.put(r9, r7)
            int r6 = r6 + 1
            goto L_0x00ac
        L_0x00ce:
            r0.initCleanerTransformations(r4)
        L_0x00d1:
            java.lang.String r2 = r10.src     // Catch:{ IOException -> 0x0192, XPatherException -> 0x0190 }
            if (r2 == 0) goto L_0x00f7
            java.lang.String r2 = r10.src     // Catch:{ IOException -> 0x0192, XPatherException -> 0x0190 }
            java.lang.String r4 = "http://"
            boolean r2 = r2.startsWith(r4)     // Catch:{ IOException -> 0x0192, XPatherException -> 0x0190 }
            if (r2 != 0) goto L_0x00e9
            java.lang.String r2 = r10.src     // Catch:{ IOException -> 0x0192, XPatherException -> 0x0190 }
            java.lang.String r4 = "https://"
            boolean r2 = r2.startsWith(r4)     // Catch:{ IOException -> 0x0192, XPatherException -> 0x0190 }
            if (r2 == 0) goto L_0x00f7
        L_0x00e9:
            java.net.URL r2 = new java.net.URL     // Catch:{ IOException -> 0x0192, XPatherException -> 0x0190 }
            java.lang.String r4 = r10.src     // Catch:{ IOException -> 0x0192, XPatherException -> 0x0190 }
            r2.<init>(r4)     // Catch:{ IOException -> 0x0192, XPatherException -> 0x0190 }
            java.lang.String r4 = r10.incharset     // Catch:{ IOException -> 0x0192, XPatherException -> 0x0190 }
            org.htmlcleaner.TagNode r0 = r0.clean((java.net.URL) r2, (java.lang.String) r4)     // Catch:{ IOException -> 0x0192, XPatherException -> 0x0190 }
            goto L_0x010f
        L_0x00f7:
            java.lang.String r2 = r10.src     // Catch:{ IOException -> 0x0192, XPatherException -> 0x0190 }
            if (r2 == 0) goto L_0x0109
            java.io.File r2 = new java.io.File     // Catch:{ IOException -> 0x0192, XPatherException -> 0x0190 }
            java.lang.String r4 = r10.src     // Catch:{ IOException -> 0x0192, XPatherException -> 0x0190 }
            r2.<init>(r4)     // Catch:{ IOException -> 0x0192, XPatherException -> 0x0190 }
            java.lang.String r4 = r10.incharset     // Catch:{ IOException -> 0x0192, XPatherException -> 0x0190 }
            org.htmlcleaner.TagNode r0 = r0.clean((java.io.File) r2, (java.lang.String) r4)     // Catch:{ IOException -> 0x0192, XPatherException -> 0x0190 }
            goto L_0x010f
        L_0x0109:
            java.lang.String r2 = r10.text     // Catch:{ IOException -> 0x0192, XPatherException -> 0x0190 }
            org.htmlcleaner.TagNode r0 = r0.clean((java.lang.String) r2)     // Catch:{ IOException -> 0x0192, XPatherException -> 0x0190 }
        L_0x010f:
            java.lang.String r2 = r10.nodebyxpath     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            if (r2 == 0) goto L_0x0129
            java.lang.String r2 = r10.nodebyxpath     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            java.lang.Object[] r2 = r0.evaluateXPath(r2)     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            int r4 = r2.length     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
        L_0x011a:
            if (r3 >= r4) goto L_0x0129
            r5 = r2[r3]     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            boolean r6 = r5 instanceof org.htmlcleaner.TagNode     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            if (r6 == 0) goto L_0x0126
            r0 = r5
            org.htmlcleaner.TagNode r0 = (org.htmlcleaner.TagNode) r0     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            goto L_0x0129
        L_0x0126:
            int r3 = r3 + 1
            goto L_0x011a
        L_0x0129:
            java.lang.String r2 = r10.dest     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            if (r2 == 0) goto L_0x0144
            java.lang.String r2 = ""
            java.lang.String r3 = r10.dest     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            java.lang.String r3 = r3.trim()     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            boolean r2 = r2.equals(r3)     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            if (r2 == 0) goto L_0x013c
            goto L_0x0144
        L_0x013c:
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            java.lang.String r3 = r10.dest     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            goto L_0x0146
        L_0x0144:
            java.io.PrintStream r2 = java.lang.System.out     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
        L_0x0146:
            java.lang.String r3 = "compact"
            java.lang.String r4 = r10.outputtype     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            boolean r3 = r3.equals(r4)     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            if (r3 == 0) goto L_0x015b
            org.htmlcleaner.CompactXmlSerializer r3 = new org.htmlcleaner.CompactXmlSerializer     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            r3.<init>(r1)     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            java.lang.String r1 = r10.outcharset     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            r3.writeXmlToStream(r0, r2, r1)     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            goto L_0x018f
        L_0x015b:
            java.lang.String r3 = "browser-compact"
            java.lang.String r4 = r10.outputtype     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            boolean r3 = r3.equals(r4)     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            if (r3 == 0) goto L_0x0170
            org.htmlcleaner.BrowserCompactXmlSerializer r3 = new org.htmlcleaner.BrowserCompactXmlSerializer     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            r3.<init>(r1)     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            java.lang.String r1 = r10.outcharset     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            r3.writeXmlToStream(r0, r2, r1)     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            goto L_0x018f
        L_0x0170:
            java.lang.String r3 = "pretty"
            java.lang.String r4 = r10.outputtype     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            boolean r3 = r3.equals(r4)     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            if (r3 == 0) goto L_0x0185
            org.htmlcleaner.PrettyXmlSerializer r3 = new org.htmlcleaner.PrettyXmlSerializer     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            r3.<init>(r1)     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            java.lang.String r1 = r10.outcharset     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            r3.writeXmlToStream(r0, r2, r1)     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            goto L_0x018f
        L_0x0185:
            org.htmlcleaner.SimpleXmlSerializer r3 = new org.htmlcleaner.SimpleXmlSerializer     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            r3.<init>(r1)     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            java.lang.String r1 = r10.outcharset     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            r3.writeXmlToStream(r0, r2, r1)     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
        L_0x018f:
            return
        L_0x0190:
            r0 = move-exception
            goto L_0x0199
        L_0x0192:
            r0 = move-exception
            org.apache.tools.ant.BuildException r1 = new org.apache.tools.ant.BuildException     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            r1.<init>(r0)     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
            throw r1     // Catch:{ IOException -> 0x019f, XPatherException -> 0x0190 }
        L_0x0199:
            org.apache.tools.ant.BuildException r1 = new org.apache.tools.ant.BuildException
            r1.<init>(r0)
            throw r1
        L_0x019f:
            r0 = move-exception
            org.apache.tools.ant.BuildException r1 = new org.apache.tools.ant.BuildException
            r1.<init>(r0)
            goto L_0x01a7
        L_0x01a6:
            throw r1
        L_0x01a7:
            goto L_0x01a6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.htmlcleaner.HtmlCleanerForAnt.execute():void");
    }
}
