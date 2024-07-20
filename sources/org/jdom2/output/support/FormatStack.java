package org.jdom2.output.support;

import org.jdom2.internal.ArrayCopy;
import org.jdom2.output.EscapeStrategy;
import org.jdom2.output.Format;

public final class FormatStack {
    private int capacity = 16;
    private final Format.TextMode defaultMode;
    private int depth = 0;
    private final String encoding;
    private boolean[] escapeOutput = new boolean[16];
    private final EscapeStrategy escapeStrategy;
    private final boolean expandEmptyElements;
    private boolean[] ignoreTrAXEscapingPIs = new boolean[16];
    private final String indent;
    private String[] levelEOL = new String[16];
    private String[] levelEOLIndent = new String[16];
    private String[] levelIndent = new String[16];
    private final String lineSeparator;
    private Format.TextMode[] mode = new Format.TextMode[16];
    private final boolean omitDeclaration;
    private final boolean omitEncoding;
    private final boolean specifiedAttributesOnly;
    private String[] termEOLIndent = new String[16];

    public FormatStack(Format format) {
        this.indent = format.getIndent();
        this.lineSeparator = format.getLineSeparator();
        this.encoding = format.getEncoding();
        this.omitDeclaration = format.getOmitDeclaration();
        this.omitEncoding = format.getOmitEncoding();
        this.expandEmptyElements = format.getExpandEmptyElements();
        this.escapeStrategy = format.getEscapeStrategy();
        this.defaultMode = format.getTextMode();
        this.specifiedAttributesOnly = format.isSpecifiedAttributesOnly();
        String str = null;
        this.levelIndent[this.depth] = format.getIndent() == null ? null : "";
        this.levelEOL[this.depth] = format.getLineSeparator();
        String[] strArr = this.levelEOLIndent;
        int i = this.depth;
        strArr[i] = this.levelIndent[i] != null ? this.levelEOL[i] : str;
        String[] strArr2 = this.termEOLIndent;
        int i2 = this.depth;
        strArr2[i2] = this.levelEOLIndent[i2];
        this.ignoreTrAXEscapingPIs[i2] = format.getIgnoreTrAXEscapingPIs();
        this.mode[this.depth] = format.getTextMode();
        this.escapeOutput[this.depth] = true;
    }

    private final void resetReusableIndents() {
        int i = this.depth;
        while (true) {
            i++;
            String[] strArr = this.levelIndent;
            if (i < strArr.length && strArr[i] != null) {
                strArr[i] = null;
            } else {
                return;
            }
        }
    }

    public String getIndent() {
        return this.indent;
    }

    public String getLineSeparator() {
        return this.lineSeparator;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public boolean isOmitDeclaration() {
        return this.omitDeclaration;
    }

    public boolean isSpecifiedAttributesOnly() {
        return this.specifiedAttributesOnly;
    }

    public boolean isOmitEncoding() {
        return this.omitEncoding;
    }

    public boolean isExpandEmptyElements() {
        return this.expandEmptyElements;
    }

    public EscapeStrategy getEscapeStrategy() {
        return this.escapeStrategy;
    }

    public boolean isIgnoreTrAXEscapingPIs() {
        return this.ignoreTrAXEscapingPIs[this.depth];
    }

    public void setIgnoreTrAXEscapingPIs(boolean z) {
        this.ignoreTrAXEscapingPIs[this.depth] = z;
    }

    public boolean getEscapeOutput() {
        return this.escapeOutput[this.depth];
    }

    public void setEscapeOutput(boolean z) {
        this.escapeOutput[this.depth] = z;
    }

    public Format.TextMode getDefaultMode() {
        return this.defaultMode;
    }

    public String getLevelIndent() {
        return this.levelIndent[this.depth];
    }

    public String getPadBetween() {
        return this.levelEOLIndent[this.depth];
    }

    public String getPadLast() {
        return this.termEOLIndent[this.depth];
    }

    public void setLevelIndent(String str) {
        String str2;
        String[] strArr = this.levelIndent;
        int i = this.depth;
        strArr[i] = str;
        String[] strArr2 = this.levelEOLIndent;
        if (str == null || this.levelEOL[i] == null) {
            str2 = null;
        } else {
            str2 = this.levelEOL[this.depth] + str;
        }
        strArr2[i] = str2;
        resetReusableIndents();
    }

    public String getLevelEOL() {
        return this.levelEOL[this.depth];
    }

    public void setLevelEOL(String str) {
        this.levelEOL[this.depth] = str;
        resetReusableIndents();
    }

    public Format.TextMode getTextMode() {
        return this.mode[this.depth];
    }

    public void setTextMode(Format.TextMode textMode) {
        int i;
        Format.TextMode[] textModeArr = this.mode;
        int i2 = this.depth;
        if (textModeArr[i2] != textMode) {
            textModeArr[i2] = textMode;
            int i3 = 1;
            if (C24191.$SwitchMap$org$jdom2$output$Format$TextMode[textMode.ordinal()] != 1) {
                String[] strArr = this.levelEOL;
                int i4 = this.depth;
                String str = this.lineSeparator;
                strArr[i4] = str;
                String str2 = this.indent;
                if (str2 == null || str == null) {
                    String[] strArr2 = this.levelEOLIndent;
                    int i5 = this.depth;
                    strArr2[i5] = null;
                    this.termEOLIndent[i5] = null;
                } else {
                    if (i4 > 0) {
                        StringBuilder sb = new StringBuilder(str2.length() * this.depth);
                        while (true) {
                            i = this.depth;
                            if (i3 >= i) {
                                break;
                            }
                            sb.append(this.indent);
                            i3++;
                        }
                        String[] strArr3 = this.termEOLIndent;
                        strArr3[i] = this.lineSeparator + sb.toString();
                        sb.append(this.indent);
                        this.levelIndent[this.depth] = sb.toString();
                    } else {
                        this.termEOLIndent[i4] = str;
                        this.levelIndent[i4] = "";
                    }
                    String[] strArr4 = this.levelEOLIndent;
                    int i6 = this.depth;
                    strArr4[i6] = this.lineSeparator + this.levelIndent[this.depth];
                }
            } else {
                String[] strArr5 = this.levelEOL;
                int i7 = this.depth;
                strArr5[i7] = null;
                this.levelIndent[i7] = null;
                this.levelEOLIndent[i7] = null;
                this.termEOLIndent[i7] = null;
            }
            resetReusableIndents();
        }
    }

    /* renamed from: org.jdom2.output.support.FormatStack$1 */
    static /* synthetic */ class C24191 {
        static final /* synthetic */ int[] $SwitchMap$org$jdom2$output$Format$TextMode;

        static {
            int[] iArr = new int[Format.TextMode.values().length];
            $SwitchMap$org$jdom2$output$Format$TextMode = iArr;
            try {
                iArr[Format.TextMode.PRESERVE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public void push() {
        int i = this.depth;
        int i2 = i + 1;
        this.depth = i2;
        int i3 = this.capacity;
        if (i2 >= i3) {
            int i4 = i3 * 2;
            this.capacity = i4;
            this.levelIndent = (String[]) ArrayCopy.copyOf((E[]) this.levelIndent, i4);
            this.levelEOL = (String[]) ArrayCopy.copyOf((E[]) this.levelEOL, this.capacity);
            this.levelEOLIndent = (String[]) ArrayCopy.copyOf((E[]) this.levelEOLIndent, this.capacity);
            this.termEOLIndent = (String[]) ArrayCopy.copyOf((E[]) this.termEOLIndent, this.capacity);
            this.ignoreTrAXEscapingPIs = ArrayCopy.copyOf(this.ignoreTrAXEscapingPIs, this.capacity);
            this.mode = (Format.TextMode[]) ArrayCopy.copyOf((E[]) this.mode, this.capacity);
            this.escapeOutput = ArrayCopy.copyOf(this.escapeOutput, this.capacity);
        }
        boolean[] zArr = this.ignoreTrAXEscapingPIs;
        int i5 = this.depth;
        zArr[i5] = zArr[i];
        Format.TextMode[] textModeArr = this.mode;
        textModeArr[i5] = textModeArr[i];
        boolean[] zArr2 = this.escapeOutput;
        zArr2[i5] = zArr2[i];
        String[] strArr = this.levelIndent;
        if (strArr[i] != null) {
            String[] strArr2 = this.levelEOL;
            if (strArr2[i] != null) {
                if (strArr[i5] == null) {
                    strArr2[i5] = strArr2[i];
                    String[] strArr3 = this.termEOLIndent;
                    strArr3[i5] = this.levelEOL[this.depth] + this.levelIndent[i];
                    String[] strArr4 = this.levelIndent;
                    int i6 = this.depth;
                    strArr4[i6] = this.levelIndent[i] + this.indent;
                    String[] strArr5 = this.levelEOLIndent;
                    int i7 = this.depth;
                    strArr5[i7] = this.levelEOL[this.depth] + this.levelIndent[this.depth];
                    return;
                }
                return;
            }
        }
        String[] strArr6 = this.levelIndent;
        int i8 = this.depth;
        strArr6[i8] = null;
        this.levelEOL[i8] = null;
        this.levelEOLIndent[i8] = null;
        this.termEOLIndent[i8] = null;
    }

    public void pop() {
        this.depth--;
    }
}
