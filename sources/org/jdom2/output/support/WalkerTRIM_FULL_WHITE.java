package org.jdom2.output.support;

import java.util.List;
import org.jdom2.Content;
import org.jdom2.Text;
import org.jdom2.Verifier;
import org.jdom2.output.support.AbstractFormattedWalker;

public class WalkerTRIM_FULL_WHITE extends AbstractFormattedWalker {
    public WalkerTRIM_FULL_WHITE(List<? extends Content> list, FormatStack formatStack, boolean z) {
        super(list, formatStack, z);
    }

    /* access modifiers changed from: protected */
    public void analyzeMultiText(AbstractFormattedWalker.MultiText multiText, int i, int i2) {
        Content content;
        int i3 = i2;
        do {
            i3--;
            if (i3 < 0) {
                break;
            }
            content = get(i + i3);
            if (!(content instanceof Text)) {
                break;
            }
        } while (!Verifier.isAllXMLWhitespace(content.getValue()));
        if (i3 >= 0) {
            for (int i4 = 0; i4 < i2; i4++) {
                Content content2 = get(i + i4);
                int i5 = C24231.$SwitchMap$org$jdom2$Content$CType[content2.getCType().ordinal()];
                if (i5 == 1) {
                    multiText.appendText(AbstractFormattedWalker.Trim.NONE, content2.getValue());
                } else if (i5 != 2) {
                    multiText.appendRaw(content2);
                } else {
                    multiText.appendCDATA(AbstractFormattedWalker.Trim.NONE, content2.getValue());
                }
            }
        }
    }

    /* renamed from: org.jdom2.output.support.WalkerTRIM_FULL_WHITE$1 */
    static /* synthetic */ class C24231 {
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
            throw new UnsupportedOperationException("Method not decompiled: org.jdom2.output.support.WalkerTRIM_FULL_WHITE.C24231.<clinit>():void");
        }
    }
}
