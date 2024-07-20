package org.jdom2.output.support;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.jdom2.Content;
import org.jdom2.Verifier;
import org.jdom2.output.support.AbstractFormattedWalker;

public class WalkerNORMALIZE extends AbstractFormattedWalker {
    public WalkerNORMALIZE(List<? extends Content> list, FormatStack formatStack, boolean z) {
        super(list, formatStack, z);
    }

    private boolean isSpaceFirst(String str) {
        if (str.length() > 0) {
            return Verifier.isXMLWhitespace(str.charAt(0));
        }
        return false;
    }

    private boolean isSpaceLast(String str) {
        int length = str.length();
        if (length <= 0 || !Verifier.isXMLWhitespace(str.charAt(length - 1))) {
            return false;
        }
        return true;
    }

    /* renamed from: org.jdom2.output.support.WalkerNORMALIZE$1 */
    static /* synthetic */ class C24201 {
        static final /* synthetic */ int[] $SwitchMap$org$jdom2$Content$CType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.jdom2.Content$CType[] r0 = org.jdom2.Content.CType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$jdom2$Content$CType = r0
                org.jdom2.Content$CType r1 = org.jdom2.Content.CType.Text     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$jdom2$Content$CType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.jdom2.Content$CType r1 = org.jdom2.Content.CType.CDATA     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$jdom2$Content$CType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.jdom2.Content$CType r1 = org.jdom2.Content.CType.EntityRef     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jdom2.output.support.WalkerNORMALIZE.C24201.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public void analyzeMultiText(AbstractFormattedWalker.MultiText multiText, int i, int i2) {
        boolean z = false;
        boolean z2 = false;
        for (int i3 = 0; i3 < i2; i3++) {
            Content content = get(i + i3);
            int i4 = C24201.$SwitchMap$org$jdom2$Content$CType[content.getCType().ordinal()];
            if (i4 != 1) {
                if (i4 != 2) {
                    if (z && z2) {
                        multiText.appendText(AbstractFormattedWalker.Trim.NONE, StringUtils.SPACE);
                    }
                    multiText.appendRaw(content);
                    z = true;
                    z2 = false;
                } else {
                    String value = content.getValue();
                    if (!Verifier.isAllXMLWhitespace(value)) {
                        if (z && (z2 || isSpaceFirst(value))) {
                            multiText.appendText(AbstractFormattedWalker.Trim.NONE, StringUtils.SPACE);
                        }
                        multiText.appendCDATA(AbstractFormattedWalker.Trim.COMPACT, value);
                        z2 = isSpaceLast(value);
                        z = true;
                    } else if (z) {
                        if (value.length() <= 0) {
                        }
                    }
                }
            } else {
                String value2 = content.getValue();
                if (Verifier.isAllXMLWhitespace(value2)) {
                    if (z) {
                        if (value2.length() <= 0) {
                        }
                    }
                } else {
                    if (z && (z2 || isSpaceFirst(value2))) {
                        multiText.appendText(AbstractFormattedWalker.Trim.NONE, StringUtils.SPACE);
                    }
                    multiText.appendText(AbstractFormattedWalker.Trim.COMPACT, value2);
                    z2 = isSpaceLast(value2);
                    z = true;
                }
            }
            z2 = true;
        }
    }
}
