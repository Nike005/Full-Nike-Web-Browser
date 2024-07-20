package org.jdom2.output.support;

import java.util.List;
import org.jdom2.Content;
import org.jdom2.Text;
import org.jdom2.Verifier;
import org.jdom2.output.support.AbstractFormattedWalker;

public class WalkerTRIM extends AbstractFormattedWalker {
    public WalkerTRIM(List<? extends Content> list, FormatStack formatStack, boolean z) {
        super(list, formatStack, z);
    }

    /* access modifiers changed from: protected */
    public void analyzeMultiText(AbstractFormattedWalker.MultiText multiText, int i, int i2) {
        while (i2 > 0) {
            Content content = get(i);
            if (!(content instanceof Text) || !Verifier.isAllXMLWhitespace(content.getValue())) {
                break;
            }
            i++;
            i2--;
        }
        while (i2 > 0) {
            Content content2 = get((i + i2) - 1);
            if (!(content2 instanceof Text) || !Verifier.isAllXMLWhitespace(content2.getValue())) {
                break;
            }
            i2--;
        }
        int i3 = 0;
        while (i3 < i2) {
            AbstractFormattedWalker.Trim trim = AbstractFormattedWalker.Trim.NONE;
            int i4 = i3 + 1;
            if (i4 == i2) {
                trim = AbstractFormattedWalker.Trim.RIGHT;
            }
            if (i3 == 0) {
                trim = AbstractFormattedWalker.Trim.LEFT;
            }
            if (i2 == 1) {
                trim = AbstractFormattedWalker.Trim.BOTH;
            }
            Content content3 = get(i3 + i);
            int i5 = C24221.$SwitchMap$org$jdom2$Content$CType[content3.getCType().ordinal()];
            if (i5 == 1) {
                multiText.appendText(trim, content3.getValue());
            } else if (i5 != 2) {
                multiText.appendRaw(content3);
            } else {
                multiText.appendCDATA(trim, content3.getValue());
            }
            i3 = i4;
        }
    }

    /* renamed from: org.jdom2.output.support.WalkerTRIM$1 */
    static /* synthetic */ class C24221 {
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
            throw new UnsupportedOperationException("Method not decompiled: org.jdom2.output.support.WalkerTRIM.C24221.<clinit>():void");
        }
    }
}
