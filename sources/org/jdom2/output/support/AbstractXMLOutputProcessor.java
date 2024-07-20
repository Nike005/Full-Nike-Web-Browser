package org.jdom2.output.support;

import com.appnext.ads.fullscreen.RewardedVideo;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.text.Typography;
import org.apache.commons.lang3.StringUtils;
import org.jdom2.Attribute;
import org.jdom2.CDATA;
import org.jdom2.Comment;
import org.jdom2.Content;
import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.EntityRef;
import org.jdom2.Namespace;
import org.jdom2.ProcessingInstruction;
import org.jdom2.Text;
import org.jdom2.Verifier;
import org.jdom2.output.Format;
import org.jdom2.util.NamespaceStack;

public abstract class AbstractXMLOutputProcessor extends AbstractOutputProcessor implements XMLOutputProcessor {
    protected static final String CDATAPOST = "]]>";
    protected static final String CDATAPRE = "<![CDATA[";

    public void process(Writer writer, Format format, Document document) throws IOException {
        printDocument(writer, new FormatStack(format), new NamespaceStack(), document);
        writer.flush();
    }

    public void process(Writer writer, Format format, DocType docType) throws IOException {
        printDocType(writer, new FormatStack(format), docType);
        writer.flush();
    }

    public void process(Writer writer, Format format, Element element) throws IOException {
        printElement(writer, new FormatStack(format), new NamespaceStack(), element);
        writer.flush();
    }

    public void process(Writer writer, Format format, List<? extends Content> list) throws IOException {
        FormatStack formatStack = new FormatStack(format);
        printContent(writer, formatStack, new NamespaceStack(), buildWalker(formatStack, list, true));
        writer.flush();
    }

    public void process(Writer writer, Format format, CDATA cdata) throws IOException {
        List singletonList = Collections.singletonList(cdata);
        FormatStack formatStack = new FormatStack(format);
        Walker buildWalker = buildWalker(formatStack, singletonList, true);
        if (buildWalker.hasNext()) {
            printContent(writer, formatStack, new NamespaceStack(), buildWalker);
        }
        writer.flush();
    }

    public void process(Writer writer, Format format, Text text) throws IOException {
        List singletonList = Collections.singletonList(text);
        FormatStack formatStack = new FormatStack(format);
        Walker buildWalker = buildWalker(formatStack, singletonList, true);
        if (buildWalker.hasNext()) {
            printContent(writer, formatStack, new NamespaceStack(), buildWalker);
        }
        writer.flush();
    }

    public void process(Writer writer, Format format, Comment comment) throws IOException {
        printComment(writer, new FormatStack(format), comment);
        writer.flush();
    }

    public void process(Writer writer, Format format, ProcessingInstruction processingInstruction) throws IOException {
        FormatStack formatStack = new FormatStack(format);
        formatStack.setIgnoreTrAXEscapingPIs(true);
        printProcessingInstruction(writer, formatStack, processingInstruction);
        writer.flush();
    }

    public void process(Writer writer, Format format, EntityRef entityRef) throws IOException {
        printEntityRef(writer, new FormatStack(format), entityRef);
        writer.flush();
    }

    /* access modifiers changed from: protected */
    public void write(Writer writer, String str) throws IOException {
        if (str != null) {
            writer.write(str);
        }
    }

    /* access modifiers changed from: protected */
    public void write(Writer writer, char c) throws IOException {
        writer.write(c);
    }

    /* access modifiers changed from: protected */
    public void attributeEscapedEntitiesFilter(Writer writer, FormatStack formatStack, String str) throws IOException {
        if (!formatStack.getEscapeOutput()) {
            write(writer, str);
        } else {
            write(writer, Format.escapeAttribute(formatStack.getEscapeStrategy(), str));
        }
    }

    /* access modifiers changed from: protected */
    public void textRaw(Writer writer, String str) throws IOException {
        write(writer, str);
    }

    /* access modifiers changed from: protected */
    public void textRaw(Writer writer, char c) throws IOException {
        write(writer, c);
    }

    /* access modifiers changed from: protected */
    public void textEntityRef(Writer writer, String str) throws IOException {
        textRaw(writer, (char) Typography.amp);
        textRaw(writer, str);
        textRaw(writer, ';');
    }

    /* access modifiers changed from: protected */
    public void textCDATA(Writer writer, String str) throws IOException {
        textRaw(writer, "<![CDATA[");
        textRaw(writer, str);
        textRaw(writer, "]]>");
    }

    /* access modifiers changed from: protected */
    public void printDocument(Writer writer, FormatStack formatStack, NamespaceStack namespaceStack, Document document) throws IOException {
        String text;
        List content = document.hasRootElement() ? document.getContent() : new ArrayList(document.getContentSize());
        if (content.isEmpty()) {
            int contentSize = document.getContentSize();
            for (int i = 0; i < contentSize; i++) {
                content.add(document.getContent(i));
            }
        }
        printDeclaration(writer, formatStack);
        Walker buildWalker = buildWalker(formatStack, content, true);
        if (buildWalker.hasNext()) {
            while (buildWalker.hasNext()) {
                Content next = buildWalker.next();
                if (next == null) {
                    String text2 = buildWalker.text();
                    if (text2 != null && Verifier.isAllXMLWhitespace(text2) && !buildWalker.isCDATA()) {
                        write(writer, text2);
                    }
                } else {
                    int i2 = C24181.$SwitchMap$org$jdom2$Content$CType[next.getCType().ordinal()];
                    if (i2 == 1) {
                        printComment(writer, formatStack, (Comment) next);
                    } else if (i2 == 2) {
                        printDocType(writer, formatStack, (DocType) next);
                    } else if (i2 == 3) {
                        printElement(writer, formatStack, namespaceStack, (Element) next);
                    } else if (i2 == 4) {
                        printProcessingInstruction(writer, formatStack, (ProcessingInstruction) next);
                    } else if (i2 == 5 && (text = ((Text) next).getText()) != null && Verifier.isAllXMLWhitespace(text)) {
                        write(writer, text);
                    }
                }
            }
            if (formatStack.getLineSeparator() != null) {
                write(writer, formatStack.getLineSeparator());
            }
        }
    }

    /* renamed from: org.jdom2.output.support.AbstractXMLOutputProcessor$1 */
    static /* synthetic */ class C24181 {
        static final /* synthetic */ int[] $SwitchMap$org$jdom2$Content$CType;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.jdom2.Content$CType[] r0 = org.jdom2.Content.CType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$jdom2$Content$CType = r0
                org.jdom2.Content$CType r1 = org.jdom2.Content.CType.Comment     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$jdom2$Content$CType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.jdom2.Content$CType r1 = org.jdom2.Content.CType.DocType     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$jdom2$Content$CType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.jdom2.Content$CType r1 = org.jdom2.Content.CType.Element     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$jdom2$Content$CType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.jdom2.Content$CType r1 = org.jdom2.Content.CType.ProcessingInstruction     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$jdom2$Content$CType     // Catch:{ NoSuchFieldError -> 0x003e }
                org.jdom2.Content$CType r1 = org.jdom2.Content.CType.Text     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$jdom2$Content$CType     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.jdom2.Content$CType r1 = org.jdom2.Content.CType.CDATA     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$jdom2$Content$CType     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.jdom2.Content$CType r1 = org.jdom2.Content.CType.EntityRef     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jdom2.output.support.AbstractXMLOutputProcessor.C24181.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public void printDeclaration(Writer writer, FormatStack formatStack) throws IOException {
        if (!formatStack.isOmitDeclaration()) {
            if (formatStack.isOmitEncoding()) {
                write(writer, "<?xml version=\"1.0\"?>");
            } else {
                write(writer, "<?xml version=\"1.0\"");
                write(writer, " encoding=\"");
                write(writer, formatStack.getEncoding());
                write(writer, "\"?>");
            }
            write(writer, formatStack.getLineSeparator());
        }
    }

    /* access modifiers changed from: protected */
    public void printDocType(Writer writer, FormatStack formatStack, DocType docType) throws IOException {
        boolean z;
        String publicID = docType.getPublicID();
        String systemID = docType.getSystemID();
        String internalSubset = docType.getInternalSubset();
        write(writer, "<!DOCTYPE ");
        write(writer, docType.getElementName());
        if (publicID != null) {
            write(writer, " PUBLIC \"");
            write(writer, publicID);
            write(writer, "\"");
            z = true;
        } else {
            z = false;
        }
        if (systemID != null) {
            if (!z) {
                write(writer, " SYSTEM");
            }
            write(writer, " \"");
            write(writer, systemID);
            write(writer, "\"");
        }
        if (internalSubset != null && !internalSubset.equals("")) {
            write(writer, " [");
            write(writer, formatStack.getLineSeparator());
            write(writer, docType.getInternalSubset());
            write(writer, "]");
        }
        write(writer, ">");
    }

    /* access modifiers changed from: protected */
    public void printProcessingInstruction(Writer writer, FormatStack formatStack, ProcessingInstruction processingInstruction) throws IOException {
        String target = processingInstruction.getTarget();
        boolean z = false;
        if (!formatStack.isIgnoreTrAXEscapingPIs()) {
            if (target.equals("javax.xml.transform.disable-output-escaping")) {
                formatStack.setEscapeOutput(false);
            } else if (target.equals("javax.xml.transform.enable-output-escaping")) {
                formatStack.setEscapeOutput(true);
            }
            z = true;
        }
        if (!z) {
            String data = processingInstruction.getData();
            if (!"".equals(data)) {
                write(writer, "<?");
                write(writer, target);
                write(writer, StringUtils.SPACE);
                write(writer, data);
                write(writer, "?>");
                return;
            }
            write(writer, "<?");
            write(writer, target);
            write(writer, "?>");
        }
    }

    /* access modifiers changed from: protected */
    public void printComment(Writer writer, FormatStack formatStack, Comment comment) throws IOException {
        write(writer, "<!--");
        write(writer, comment.getText());
        write(writer, "-->");
    }

    /* access modifiers changed from: protected */
    public void printEntityRef(Writer writer, FormatStack formatStack, EntityRef entityRef) throws IOException {
        textEntityRef(writer, entityRef.getName());
    }

    /* access modifiers changed from: protected */
    public void printCDATA(Writer writer, FormatStack formatStack, CDATA cdata) throws IOException {
        textCDATA(writer, cdata.getText());
    }

    /* access modifiers changed from: protected */
    public void printText(Writer writer, FormatStack formatStack, Text text) throws IOException {
        if (formatStack.getEscapeOutput()) {
            textRaw(writer, Format.escapeText(formatStack.getEscapeStrategy(), formatStack.getLineSeparator(), text.getText()));
        } else {
            textRaw(writer, text.getText());
        }
    }

    /* access modifiers changed from: protected */
    public void printElement(Writer writer, FormatStack formatStack, NamespaceStack namespaceStack, Element element) throws IOException {
        namespaceStack.push(element);
        try {
            List<Content> content = element.getContent();
            write(writer, "<");
            write(writer, element.getQualifiedName());
            for (Namespace printNamespace : namespaceStack.addedForward()) {
                printNamespace(writer, formatStack, printNamespace);
            }
            if (element.hasAttributes()) {
                for (Attribute printAttribute : element.getAttributes()) {
                    printAttribute(writer, formatStack, printAttribute);
                }
            }
            if (content.isEmpty()) {
                if (formatStack.isExpandEmptyElements()) {
                    write(writer, "></");
                    write(writer, element.getQualifiedName());
                    write(writer, ">");
                } else {
                    write(writer, " />");
                }
                namespaceStack.pop();
                return;
            }
            formatStack.push();
            String attributeValue = element.getAttributeValue("space", Namespace.XML_NAMESPACE);
            if (RewardedVideo.VIDEO_MODE_DEFAULT.equals(attributeValue)) {
                formatStack.setTextMode(formatStack.getDefaultMode());
            } else if ("preserve".equals(attributeValue)) {
                formatStack.setTextMode(Format.TextMode.PRESERVE);
            }
            Walker buildWalker = buildWalker(formatStack, content, true);
            if (!buildWalker.hasNext()) {
                if (formatStack.isExpandEmptyElements()) {
                    write(writer, "></");
                    write(writer, element.getQualifiedName());
                    write(writer, ">");
                } else {
                    write(writer, " />");
                }
                formatStack.pop();
                namespaceStack.pop();
                return;
            }
            write(writer, ">");
            if (!buildWalker.isAllText()) {
                textRaw(writer, formatStack.getPadBetween());
            }
            printContent(writer, formatStack, namespaceStack, buildWalker);
            if (!buildWalker.isAllText()) {
                textRaw(writer, formatStack.getPadLast());
            }
            write(writer, "</");
            write(writer, element.getQualifiedName());
            write(writer, ">");
            formatStack.pop();
            namespaceStack.pop();
        } catch (Throwable th) {
            namespaceStack.pop();
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public void printContent(Writer writer, FormatStack formatStack, NamespaceStack namespaceStack, Walker walker) throws IOException {
        while (walker.hasNext()) {
            Content next = walker.next();
            if (next != null) {
                switch (C24181.$SwitchMap$org$jdom2$Content$CType[next.getCType().ordinal()]) {
                    case 1:
                        printComment(writer, formatStack, (Comment) next);
                        break;
                    case 2:
                        printDocType(writer, formatStack, (DocType) next);
                        break;
                    case 3:
                        printElement(writer, formatStack, namespaceStack, (Element) next);
                        break;
                    case 4:
                        printProcessingInstruction(writer, formatStack, (ProcessingInstruction) next);
                        break;
                    case 5:
                        printText(writer, formatStack, (Text) next);
                        break;
                    case 6:
                        printCDATA(writer, formatStack, (CDATA) next);
                        break;
                    case 7:
                        printEntityRef(writer, formatStack, (EntityRef) next);
                        break;
                }
            } else {
                String text = walker.text();
                if (walker.isCDATA()) {
                    textCDATA(writer, text);
                } else {
                    textRaw(writer, text);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void printNamespace(Writer writer, FormatStack formatStack, Namespace namespace) throws IOException {
        String prefix = namespace.getPrefix();
        String uri = namespace.getURI();
        write(writer, " xmlns");
        if (!prefix.equals("")) {
            write(writer, ":");
            write(writer, prefix);
        }
        write(writer, "=\"");
        attributeEscapedEntitiesFilter(writer, formatStack, uri);
        write(writer, "\"");
    }

    /* access modifiers changed from: protected */
    public void printAttribute(Writer writer, FormatStack formatStack, Attribute attribute) throws IOException {
        if (attribute.isSpecified() || !formatStack.isSpecifiedAttributesOnly()) {
            write(writer, StringUtils.SPACE);
            write(writer, attribute.getQualifiedName());
            write(writer, "=");
            write(writer, "\"");
            attributeEscapedEntitiesFilter(writer, formatStack, attribute.getValue());
            write(writer, "\"");
        }
    }
}
