package org.jdom2.output.support;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.jdom2.CDATA;
import org.jdom2.Content;
import org.jdom2.internal.ArrayCopy;
import org.jdom2.output.EscapeStrategy;
import org.jdom2.output.Format;

public abstract class AbstractFormattedWalker implements Walker {
    /* access modifiers changed from: private */
    public static final CDATA CDATATOKEN = new CDATA("");
    private static final Iterator<Content> EMPTYIT = new Iterator<Content>() {
        public boolean hasNext() {
            return false;
        }

        public Content next() {
            throw new NoSuchElementException("Cannot call next() on an empty iterator.");
        }

        public void remove() {
            throw new UnsupportedOperationException("Cannot remove from an empty iterator.");
        }
    };
    private final boolean alltext;
    private final boolean allwhite;
    private final Iterator<? extends Content> content;
    /* access modifiers changed from: private */
    public final String endofline;
    /* access modifiers changed from: private */
    public final EscapeStrategy escape;
    /* access modifiers changed from: private */
    public final FormatStack fstack;
    private boolean hasnext;
    private final MultiText holdingmt;
    /* access modifiers changed from: private */
    public final StringBuilder mtbuffer;
    /* access modifiers changed from: private */
    public Content[] mtdata;
    /* access modifiers changed from: private */
    public boolean mtgottext;
    private int mtpos;
    /* access modifiers changed from: private */
    public boolean mtpostpad;
    /* access modifiers changed from: private */
    public int mtsize;
    private Content[] mtsource;
    private int mtsourcesize;
    /* access modifiers changed from: private */
    public String[] mttext;
    private Boolean mtwasescape;
    private MultiText multitext;
    /* access modifiers changed from: private */
    public final String newlineindent;
    private Content pending = null;
    private MultiText pendingmt;

    protected enum Trim {
        LEFT,
        RIGHT,
        BOTH,
        COMPACT,
        NONE
    }

    /* access modifiers changed from: protected */
    public abstract void analyzeMultiText(MultiText multiText, int i, int i2);

    static /* synthetic */ int access$008(AbstractFormattedWalker abstractFormattedWalker) {
        int i = abstractFormattedWalker.mtsize;
        abstractFormattedWalker.mtsize = i + 1;
        return i;
    }

    protected final class MultiText {
        private MultiText() {
        }

        private void ensurespace() {
            if (AbstractFormattedWalker.this.mtsize >= AbstractFormattedWalker.this.mtdata.length) {
                AbstractFormattedWalker abstractFormattedWalker = AbstractFormattedWalker.this;
                Content[] unused = abstractFormattedWalker.mtdata = (Content[]) ArrayCopy.copyOf((E[]) abstractFormattedWalker.mtdata, AbstractFormattedWalker.this.mtsize + 1 + (AbstractFormattedWalker.this.mtsize / 2));
                AbstractFormattedWalker abstractFormattedWalker2 = AbstractFormattedWalker.this;
                String[] unused2 = abstractFormattedWalker2.mttext = (String[]) ArrayCopy.copyOf((E[]) abstractFormattedWalker2.mttext, AbstractFormattedWalker.this.mtdata.length);
            }
        }

        private void closeText() {
            if (AbstractFormattedWalker.this.mtbuffer.length() != 0) {
                ensurespace();
                AbstractFormattedWalker.this.mtdata[AbstractFormattedWalker.this.mtsize] = null;
                AbstractFormattedWalker.this.mttext[AbstractFormattedWalker.access$008(AbstractFormattedWalker.this)] = AbstractFormattedWalker.this.mtbuffer.toString();
                AbstractFormattedWalker.this.mtbuffer.setLength(0);
            }
        }

        public void appendText(Trim trim, String str) {
            if (str.length() != 0) {
                int i = C24132.$SwitchMap$org$jdom2$output$support$AbstractFormattedWalker$Trim[trim.ordinal()];
                if (i != 1) {
                    if (i == 2) {
                        str = Format.trimBoth(str);
                    } else if (i == 3) {
                        str = Format.trimLeft(str);
                    } else if (i != 4) {
                        str = i != 5 ? null : Format.compact(str);
                    } else {
                        str = Format.trimRight(str);
                    }
                }
                if (str != null) {
                    AbstractFormattedWalker.this.mtbuffer.append(escapeText(str));
                    boolean unused = AbstractFormattedWalker.this.mtgottext = true;
                }
            }
        }

        private String escapeText(String str) {
            return (AbstractFormattedWalker.this.escape == null || !AbstractFormattedWalker.this.fstack.getEscapeOutput()) ? str : Format.escapeText(AbstractFormattedWalker.this.escape, AbstractFormattedWalker.this.endofline, str);
        }

        private String escapeCDATA(String str) {
            if (AbstractFormattedWalker.this.escape == null) {
            }
            return str;
        }

        public void appendCDATA(Trim trim, String str) {
            closeText();
            int i = C24132.$SwitchMap$org$jdom2$output$support$AbstractFormattedWalker$Trim[trim.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    str = Format.trimBoth(str);
                } else if (i == 3) {
                    str = Format.trimLeft(str);
                } else if (i != 4) {
                    str = i != 5 ? null : Format.compact(str);
                } else {
                    str = Format.trimRight(str);
                }
            }
            String escapeCDATA = escapeCDATA(str);
            ensurespace();
            AbstractFormattedWalker.this.mtdata[AbstractFormattedWalker.this.mtsize] = AbstractFormattedWalker.CDATATOKEN;
            AbstractFormattedWalker.this.mttext[AbstractFormattedWalker.access$008(AbstractFormattedWalker.this)] = escapeCDATA;
            boolean unused = AbstractFormattedWalker.this.mtgottext = true;
        }

        /* access modifiers changed from: private */
        public void forceAppend(String str) {
            boolean unused = AbstractFormattedWalker.this.mtgottext = true;
            AbstractFormattedWalker.this.mtbuffer.append(str);
        }

        public void appendRaw(Content content) {
            closeText();
            ensurespace();
            AbstractFormattedWalker.this.mttext[AbstractFormattedWalker.this.mtsize] = null;
            AbstractFormattedWalker.this.mtdata[AbstractFormattedWalker.access$008(AbstractFormattedWalker.this)] = content;
            AbstractFormattedWalker.this.mtbuffer.setLength(0);
        }

        public void done() {
            if (AbstractFormattedWalker.this.mtpostpad && AbstractFormattedWalker.this.newlineindent != null) {
                AbstractFormattedWalker.this.mtbuffer.append(AbstractFormattedWalker.this.newlineindent);
            }
            if (AbstractFormattedWalker.this.mtgottext) {
                closeText();
            }
            AbstractFormattedWalker.this.mtbuffer.setLength(0);
        }
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [java.util.List, java.util.List<? extends org.jdom2.Content>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AbstractFormattedWalker(java.util.List<? extends org.jdom2.Content> r6, org.jdom2.output.support.FormatStack r7, boolean r8) {
        /*
            r5 = this;
            r5.<init>()
            r0 = 0
            r5.pending = r0
            r1 = 1
            r5.hasnext = r1
            r5.multitext = r0
            r5.pendingmt = r0
            org.jdom2.output.support.AbstractFormattedWalker$MultiText r2 = new org.jdom2.output.support.AbstractFormattedWalker$MultiText
            r2.<init>()
            r5.holdingmt = r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r5.mtbuffer = r2
            r2 = 0
            r5.mtgottext = r2
            r5.mtsize = r2
            r5.mtsourcesize = r2
            r3 = 8
            org.jdom2.Content[] r4 = new org.jdom2.Content[r3]
            r5.mtsource = r4
            org.jdom2.Content[] r4 = new org.jdom2.Content[r3]
            r5.mtdata = r4
            java.lang.String[] r3 = new java.lang.String[r3]
            r5.mttext = r3
            r3 = -1
            r5.mtpos = r3
            r5.fstack = r7
            boolean r3 = r6.isEmpty()
            if (r3 == 0) goto L_0x003e
            java.util.Iterator<org.jdom2.Content> r6 = EMPTYIT
            goto L_0x0042
        L_0x003e:
            java.util.Iterator r6 = r6.iterator()
        L_0x0042:
            r5.content = r6
            if (r8 == 0) goto L_0x004b
            org.jdom2.output.EscapeStrategy r6 = r7.getEscapeStrategy()
            goto L_0x004c
        L_0x004b:
            r6 = r0
        L_0x004c:
            r5.escape = r6
            java.lang.String r6 = r7.getPadBetween()
            r5.newlineindent = r6
            java.lang.String r6 = r7.getLevelEOL()
            r5.endofline = r6
            java.util.Iterator<? extends org.jdom2.Content> r6 = r5.content
            boolean r6 = r6.hasNext()
            if (r6 != 0) goto L_0x0067
            r5.alltext = r1
            r5.allwhite = r1
            goto L_0x00a3
        L_0x0067:
            java.util.Iterator<? extends org.jdom2.Content> r6 = r5.content
            java.lang.Object r6 = r6.next()
            org.jdom2.Content r6 = (org.jdom2.Content) r6
            r5.pending = r6
            boolean r6 = r5.isTextLike(r6)
            if (r6 == 0) goto L_0x009d
            org.jdom2.output.support.AbstractFormattedWalker$MultiText r6 = r5.buildMultiText(r1)
            r5.pendingmt = r6
            int r7 = r5.mtsourcesize
            r5.analyzeMultiText(r6, r2, r7)
            org.jdom2.output.support.AbstractFormattedWalker$MultiText r6 = r5.pendingmt
            r6.done()
            org.jdom2.Content r6 = r5.pending
            if (r6 != 0) goto L_0x0094
            int r6 = r5.mtsize
            if (r6 != 0) goto L_0x0091
            r6 = 1
            goto L_0x0092
        L_0x0091:
            r6 = 0
        L_0x0092:
            r7 = 1
            goto L_0x0096
        L_0x0094:
            r6 = 0
            r7 = 0
        L_0x0096:
            int r8 = r5.mtsize
            if (r8 != 0) goto L_0x009f
            r5.pendingmt = r0
            goto L_0x009f
        L_0x009d:
            r6 = 0
            r7 = 0
        L_0x009f:
            r5.alltext = r7
            r5.allwhite = r6
        L_0x00a3:
            org.jdom2.output.support.AbstractFormattedWalker$MultiText r6 = r5.pendingmt
            if (r6 != 0) goto L_0x00ad
            org.jdom2.Content r6 = r5.pending
            if (r6 == 0) goto L_0x00ac
            goto L_0x00ad
        L_0x00ac:
            r1 = 0
        L_0x00ad:
            r5.hasnext = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jdom2.output.support.AbstractFormattedWalker.<init>(java.util.List, org.jdom2.output.support.FormatStack, boolean):void");
    }

    public final Content next() {
        if (this.hasnext) {
            Content content2 = null;
            boolean z = true;
            if (this.multitext != null && this.mtpos + 1 >= this.mtsize) {
                this.multitext = null;
                resetMultiText();
            }
            if (this.pendingmt != null) {
                if (!(this.mtwasescape == null || this.fstack.getEscapeOutput() == this.mtwasescape.booleanValue())) {
                    this.mtsize = 0;
                    this.mtwasescape = Boolean.valueOf(this.fstack.getEscapeOutput());
                    analyzeMultiText(this.pendingmt, 0, this.mtsourcesize);
                    this.pendingmt.done();
                }
                this.multitext = this.pendingmt;
                this.pendingmt = null;
            }
            if (this.multitext != null) {
                int i = this.mtpos + 1;
                this.mtpos = i;
                if (this.mttext[i] == null) {
                    content2 = this.mtdata[i];
                }
                if (this.mtpos + 1 >= this.mtsize && this.pending == null) {
                    z = false;
                }
                this.hasnext = z;
                return content2;
            }
            Content content3 = this.pending;
            Content content4 = this.content.hasNext() ? (Content) this.content.next() : null;
            this.pending = content4;
            if (content4 == null) {
                this.hasnext = false;
            } else if (isTextLike(content4)) {
                MultiText buildMultiText = buildMultiText(false);
                this.pendingmt = buildMultiText;
                analyzeMultiText(buildMultiText, 0, this.mtsourcesize);
                this.pendingmt.done();
                if (this.mtsize > 0) {
                    this.hasnext = true;
                } else if (this.pending == null || this.newlineindent == null) {
                    this.pendingmt = null;
                    if (this.pending == null) {
                        z = false;
                    }
                    this.hasnext = z;
                } else {
                    resetMultiText();
                    MultiText multiText = this.holdingmt;
                    this.pendingmt = multiText;
                    multiText.forceAppend(this.newlineindent);
                    this.pendingmt.done();
                    this.hasnext = true;
                }
            } else {
                if (this.newlineindent != null) {
                    resetMultiText();
                    MultiText multiText2 = this.holdingmt;
                    this.pendingmt = multiText2;
                    multiText2.forceAppend(this.newlineindent);
                    this.pendingmt.done();
                }
                this.hasnext = true;
            }
            return content3;
        }
        throw new NoSuchElementException("Cannot walk off end of Content");
    }

    private void resetMultiText() {
        this.mtsourcesize = 0;
        this.mtpos = -1;
        this.mtsize = 0;
        this.mtgottext = false;
        this.mtpostpad = false;
        this.mtwasescape = null;
        this.mtbuffer.setLength(0);
    }

    /* access modifiers changed from: protected */
    public final Content get(int i) {
        return this.mtsource[i];
    }

    public final boolean isAllText() {
        return this.alltext;
    }

    public final boolean hasNext() {
        return this.hasnext;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0015  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final org.jdom2.output.support.AbstractFormattedWalker.MultiText buildMultiText(boolean r4) {
        /*
            r3 = this;
            if (r4 != 0) goto L_0x000b
            java.lang.String r4 = r3.newlineindent
            if (r4 == 0) goto L_0x000b
            java.lang.StringBuilder r0 = r3.mtbuffer
            r0.append(r4)
        L_0x000b:
            r4 = 0
            r3.mtsourcesize = r4
        L_0x000e:
            int r0 = r3.mtsourcesize
            org.jdom2.Content[] r1 = r3.mtsource
            int r2 = r1.length
            if (r0 < r2) goto L_0x0020
            int r0 = r1.length
            int r0 = r0 * 2
            java.lang.Object[] r0 = org.jdom2.internal.ArrayCopy.copyOf((E[]) r1, (int) r0)
            org.jdom2.Content[] r0 = (org.jdom2.Content[]) r0
            r3.mtsource = r0
        L_0x0020:
            org.jdom2.Content[] r0 = r3.mtsource
            int r1 = r3.mtsourcesize
            int r2 = r1 + 1
            r3.mtsourcesize = r2
            org.jdom2.Content r2 = r3.pending
            r0[r1] = r2
            java.util.Iterator<? extends org.jdom2.Content> r0 = r3.content
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto L_0x003d
            java.util.Iterator<? extends org.jdom2.Content> r0 = r3.content
            java.lang.Object r0 = r0.next()
            org.jdom2.Content r0 = (org.jdom2.Content) r0
            goto L_0x003e
        L_0x003d:
            r0 = 0
        L_0x003e:
            r3.pending = r0
            if (r0 == 0) goto L_0x0048
            boolean r0 = r3.isTextLike(r0)
            if (r0 != 0) goto L_0x000e
        L_0x0048:
            org.jdom2.Content r0 = r3.pending
            if (r0 == 0) goto L_0x004d
            r4 = 1
        L_0x004d:
            r3.mtpostpad = r4
            org.jdom2.output.support.FormatStack r4 = r3.fstack
            boolean r4 = r4.getEscapeOutput()
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            r3.mtwasescape = r4
            org.jdom2.output.support.AbstractFormattedWalker$MultiText r4 = r3.holdingmt
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jdom2.output.support.AbstractFormattedWalker.buildMultiText(boolean):org.jdom2.output.support.AbstractFormattedWalker$MultiText");
    }

    public final String text() {
        int i;
        if (this.multitext == null || (i = this.mtpos) >= this.mtsize) {
            return null;
        }
        return this.mttext[i];
    }

    public final boolean isCDATA() {
        int i;
        if (this.multitext == null || (i = this.mtpos) >= this.mtsize || this.mttext[i] == null || this.mtdata[i] != CDATATOKEN) {
            return false;
        }
        return true;
    }

    public final boolean isAllWhitespace() {
        return this.allwhite;
    }

    /* renamed from: org.jdom2.output.support.AbstractFormattedWalker$2 */
    static /* synthetic */ class C24132 {
        static final /* synthetic */ int[] $SwitchMap$org$jdom2$Content$CType;
        static final /* synthetic */ int[] $SwitchMap$org$jdom2$output$support$AbstractFormattedWalker$Trim;

        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|20|(3:21|22|24)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|20|(3:21|22|24)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|20|21|22|24) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0039 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0043 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0058 */
        static {
            /*
                org.jdom2.Content$CType[] r0 = org.jdom2.Content.CType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$jdom2$Content$CType = r0
                r1 = 1
                org.jdom2.Content$CType r2 = org.jdom2.Content.CType.Text     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$org$jdom2$Content$CType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.jdom2.Content$CType r3 = org.jdom2.Content.CType.CDATA     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$org$jdom2$Content$CType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.jdom2.Content$CType r4 = org.jdom2.Content.CType.EntityRef     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                org.jdom2.output.support.AbstractFormattedWalker$Trim[] r3 = org.jdom2.output.support.AbstractFormattedWalker.Trim.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$org$jdom2$output$support$AbstractFormattedWalker$Trim = r3
                org.jdom2.output.support.AbstractFormattedWalker$Trim r4 = org.jdom2.output.support.AbstractFormattedWalker.Trim.NONE     // Catch:{ NoSuchFieldError -> 0x0039 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0039 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0039 }
            L_0x0039:
                int[] r1 = $SwitchMap$org$jdom2$output$support$AbstractFormattedWalker$Trim     // Catch:{ NoSuchFieldError -> 0x0043 }
                org.jdom2.output.support.AbstractFormattedWalker$Trim r3 = org.jdom2.output.support.AbstractFormattedWalker.Trim.BOTH     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                int[] r0 = $SwitchMap$org$jdom2$output$support$AbstractFormattedWalker$Trim     // Catch:{ NoSuchFieldError -> 0x004d }
                org.jdom2.output.support.AbstractFormattedWalker$Trim r1 = org.jdom2.output.support.AbstractFormattedWalker.Trim.LEFT     // Catch:{ NoSuchFieldError -> 0x004d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004d }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004d }
            L_0x004d:
                int[] r0 = $SwitchMap$org$jdom2$output$support$AbstractFormattedWalker$Trim     // Catch:{ NoSuchFieldError -> 0x0058 }
                org.jdom2.output.support.AbstractFormattedWalker$Trim r1 = org.jdom2.output.support.AbstractFormattedWalker.Trim.RIGHT     // Catch:{ NoSuchFieldError -> 0x0058 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0058 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0058 }
            L_0x0058:
                int[] r0 = $SwitchMap$org$jdom2$output$support$AbstractFormattedWalker$Trim     // Catch:{ NoSuchFieldError -> 0x0063 }
                org.jdom2.output.support.AbstractFormattedWalker$Trim r1 = org.jdom2.output.support.AbstractFormattedWalker.Trim.COMPACT     // Catch:{ NoSuchFieldError -> 0x0063 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0063 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0063 }
            L_0x0063:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jdom2.output.support.AbstractFormattedWalker.C24132.<clinit>():void");
        }
    }

    private final boolean isTextLike(Content content2) {
        int i = C24132.$SwitchMap$org$jdom2$Content$CType[content2.getCType().ordinal()];
        return i == 1 || i == 2 || i == 3;
    }
}
