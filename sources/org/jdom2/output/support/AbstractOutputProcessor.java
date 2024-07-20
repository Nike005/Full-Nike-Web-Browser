package org.jdom2.output.support;

import java.util.List;
import org.jdom2.Content;

public abstract class AbstractOutputProcessor {

    /* renamed from: org.jdom2.output.support.AbstractOutputProcessor$1 */
    static /* synthetic */ class C24141 {
        static final /* synthetic */ int[] $SwitchMap$org$jdom2$output$Format$TextMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                org.jdom2.output.Format$TextMode[] r0 = org.jdom2.output.Format.TextMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$jdom2$output$Format$TextMode = r0
                org.jdom2.output.Format$TextMode r1 = org.jdom2.output.Format.TextMode.PRESERVE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$jdom2$output$Format$TextMode     // Catch:{ NoSuchFieldError -> 0x001d }
                org.jdom2.output.Format$TextMode r1 = org.jdom2.output.Format.TextMode.NORMALIZE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$jdom2$output$Format$TextMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.jdom2.output.Format$TextMode r1 = org.jdom2.output.Format.TextMode.TRIM     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$jdom2$output$Format$TextMode     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.jdom2.output.Format$TextMode r1 = org.jdom2.output.Format.TextMode.TRIM_FULL_WHITE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jdom2.output.support.AbstractOutputProcessor.C24141.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public Walker buildWalker(FormatStack formatStack, List<? extends Content> list, boolean z) {
        int i = C24141.$SwitchMap$org$jdom2$output$Format$TextMode[formatStack.getTextMode().ordinal()];
        if (i == 1) {
            return new WalkerPRESERVE(list);
        }
        if (i == 2) {
            return new WalkerNORMALIZE(list, formatStack, z);
        }
        if (i != 3) {
            return i != 4 ? new WalkerPRESERVE(list) : new WalkerTRIM_FULL_WHITE(list, formatStack, z);
        }
        return new WalkerTRIM(list, formatStack, z);
    }
}
