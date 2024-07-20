package org.jdom2.output.support;

import com.appnext.ads.fullscreen.RewardedVideo;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
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

public abstract class AbstractStAXStreamProcessor extends AbstractOutputProcessor implements StAXStreamProcessor {
    public void process(XMLStreamWriter xMLStreamWriter, Format format, Document document) throws XMLStreamException {
        printDocument(xMLStreamWriter, new FormatStack(format), new NamespaceStack(), document);
        xMLStreamWriter.flush();
    }

    public void process(XMLStreamWriter xMLStreamWriter, Format format, DocType docType) throws XMLStreamException {
        printDocType(xMLStreamWriter, new FormatStack(format), docType);
        xMLStreamWriter.flush();
    }

    public void process(XMLStreamWriter xMLStreamWriter, Format format, Element element) throws XMLStreamException {
        printElement(xMLStreamWriter, new FormatStack(format), new NamespaceStack(), element);
        xMLStreamWriter.flush();
    }

    public void process(XMLStreamWriter xMLStreamWriter, Format format, List<? extends Content> list) throws XMLStreamException {
        FormatStack formatStack = new FormatStack(format);
        printContent(xMLStreamWriter, formatStack, new NamespaceStack(), buildWalker(formatStack, list, false));
        xMLStreamWriter.flush();
    }

    public void process(XMLStreamWriter xMLStreamWriter, Format format, CDATA cdata) throws XMLStreamException {
        List singletonList = Collections.singletonList(cdata);
        FormatStack formatStack = new FormatStack(format);
        Walker buildWalker = buildWalker(formatStack, singletonList, false);
        if (buildWalker.hasNext()) {
            Content next = buildWalker.next();
            if (next == null) {
                printCDATA(xMLStreamWriter, formatStack, new CDATA(buildWalker.text()));
            } else if (next.getCType() == Content.CType.CDATA) {
                printCDATA(xMLStreamWriter, formatStack, (CDATA) next);
            }
        }
        xMLStreamWriter.flush();
    }

    public void process(XMLStreamWriter xMLStreamWriter, Format format, Text text) throws XMLStreamException {
        List singletonList = Collections.singletonList(text);
        FormatStack formatStack = new FormatStack(format);
        Walker buildWalker = buildWalker(formatStack, singletonList, false);
        if (buildWalker.hasNext()) {
            Content next = buildWalker.next();
            if (next == null) {
                printText(xMLStreamWriter, formatStack, new Text(buildWalker.text()));
            } else if (next.getCType() == Content.CType.Text) {
                printText(xMLStreamWriter, formatStack, (Text) next);
            }
        }
        xMLStreamWriter.flush();
    }

    public void process(XMLStreamWriter xMLStreamWriter, Format format, Comment comment) throws XMLStreamException {
        printComment(xMLStreamWriter, new FormatStack(format), comment);
        xMLStreamWriter.flush();
    }

    public void process(XMLStreamWriter xMLStreamWriter, Format format, ProcessingInstruction processingInstruction) throws XMLStreamException {
        FormatStack formatStack = new FormatStack(format);
        formatStack.setIgnoreTrAXEscapingPIs(true);
        printProcessingInstruction(xMLStreamWriter, formatStack, processingInstruction);
        xMLStreamWriter.flush();
    }

    public void process(XMLStreamWriter xMLStreamWriter, Format format, EntityRef entityRef) throws XMLStreamException {
        printEntityRef(xMLStreamWriter, new FormatStack(format), entityRef);
        xMLStreamWriter.flush();
    }

    /* access modifiers changed from: protected */
    public void printDocument(XMLStreamWriter xMLStreamWriter, FormatStack formatStack, NamespaceStack namespaceStack, Document document) throws XMLStreamException {
        String text;
        if (formatStack.isOmitDeclaration()) {
            xMLStreamWriter.writeStartDocument();
            if (formatStack.getLineSeparator() != null) {
                xMLStreamWriter.writeCharacters(formatStack.getLineSeparator());
            }
        } else if (formatStack.isOmitEncoding()) {
            xMLStreamWriter.writeStartDocument("1.0");
            if (formatStack.getLineSeparator() != null) {
                xMLStreamWriter.writeCharacters(formatStack.getLineSeparator());
            }
        } else {
            xMLStreamWriter.writeStartDocument(formatStack.getEncoding(), "1.0");
            if (formatStack.getLineSeparator() != null) {
                xMLStreamWriter.writeCharacters(formatStack.getLineSeparator());
            }
        }
        List content = document.hasRootElement() ? document.getContent() : new ArrayList(document.getContentSize());
        if (content.isEmpty()) {
            int contentSize = document.getContentSize();
            for (int i = 0; i < contentSize; i++) {
                content.add(document.getContent(i));
            }
        }
        Walker buildWalker = buildWalker(formatStack, content, false);
        if (buildWalker.hasNext()) {
            while (buildWalker.hasNext()) {
                Content next = buildWalker.next();
                if (next == null) {
                    String text2 = buildWalker.text();
                    if (text2 != null && Verifier.isAllXMLWhitespace(text2) && !buildWalker.isCDATA()) {
                        xMLStreamWriter.writeCharacters(text2);
                    }
                } else {
                    int i2 = C24171.$SwitchMap$org$jdom2$Content$CType[next.getCType().ordinal()];
                    if (i2 == 1) {
                        printComment(xMLStreamWriter, formatStack, (Comment) next);
                    } else if (i2 == 2) {
                        printDocType(xMLStreamWriter, formatStack, (DocType) next);
                    } else if (i2 == 3) {
                        printElement(xMLStreamWriter, formatStack, namespaceStack, (Element) next);
                    } else if (i2 == 4) {
                        printProcessingInstruction(xMLStreamWriter, formatStack, (ProcessingInstruction) next);
                    } else if (i2 == 5 && (text = ((Text) next).getText()) != null && Verifier.isAllXMLWhitespace(text)) {
                        xMLStreamWriter.writeCharacters(text);
                    }
                }
            }
            if (formatStack.getLineSeparator() != null) {
                xMLStreamWriter.writeCharacters(formatStack.getLineSeparator());
            }
        }
        xMLStreamWriter.writeEndDocument();
    }

    /* renamed from: org.jdom2.output.support.AbstractStAXStreamProcessor$1 */
    static /* synthetic */ class C24171 {
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
            throw new UnsupportedOperationException("Method not decompiled: org.jdom2.output.support.AbstractStAXStreamProcessor.C24171.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public void printDocType(XMLStreamWriter xMLStreamWriter, FormatStack formatStack, DocType docType) throws XMLStreamException {
        boolean z;
        String publicID = docType.getPublicID();
        String systemID = docType.getSystemID();
        String internalSubset = docType.getInternalSubset();
        StringWriter stringWriter = new StringWriter();
        stringWriter.write("<!DOCTYPE ");
        stringWriter.write(docType.getElementName());
        if (publicID != null) {
            stringWriter.write(" PUBLIC \"");
            stringWriter.write(publicID);
            stringWriter.write("\"");
            z = true;
        } else {
            z = false;
        }
        if (systemID != null) {
            if (!z) {
                stringWriter.write(" SYSTEM");
            }
            stringWriter.write(" \"");
            stringWriter.write(systemID);
            stringWriter.write("\"");
        }
        if (internalSubset != null && !internalSubset.equals("")) {
            stringWriter.write(" [");
            stringWriter.write(formatStack.getLineSeparator());
            stringWriter.write(docType.getInternalSubset());
            stringWriter.write("]");
        }
        stringWriter.write(">");
        xMLStreamWriter.writeDTD(stringWriter.toString());
    }

    /* access modifiers changed from: protected */
    public void printProcessingInstruction(XMLStreamWriter xMLStreamWriter, FormatStack formatStack, ProcessingInstruction processingInstruction) throws XMLStreamException {
        String target = processingInstruction.getTarget();
        String data = processingInstruction.getData();
        if (data == null || data.trim().length() <= 0) {
            xMLStreamWriter.writeProcessingInstruction(target);
        } else {
            xMLStreamWriter.writeProcessingInstruction(target, data);
        }
    }

    /* access modifiers changed from: protected */
    public void printComment(XMLStreamWriter xMLStreamWriter, FormatStack formatStack, Comment comment) throws XMLStreamException {
        xMLStreamWriter.writeComment(comment.getText());
    }

    /* access modifiers changed from: protected */
    public void printEntityRef(XMLStreamWriter xMLStreamWriter, FormatStack formatStack, EntityRef entityRef) throws XMLStreamException {
        xMLStreamWriter.writeEntityRef(entityRef.getName());
    }

    /* access modifiers changed from: protected */
    public void printCDATA(XMLStreamWriter xMLStreamWriter, FormatStack formatStack, CDATA cdata) throws XMLStreamException {
        xMLStreamWriter.writeCData(cdata.getText());
    }

    /* access modifiers changed from: protected */
    public void printText(XMLStreamWriter xMLStreamWriter, FormatStack formatStack, Text text) throws XMLStreamException {
        xMLStreamWriter.writeCharacters(text.getText());
    }

    /* access modifiers changed from: protected */
    public void printElement(XMLStreamWriter xMLStreamWriter, FormatStack formatStack, NamespaceStack namespaceStack, Element element) throws XMLStreamException {
        ArrayList arrayList = new ArrayList();
        namespaceStack.push(element);
        for (Namespace next : namespaceStack.addedForward()) {
            arrayList.add(next.getPrefix());
            if ("".equals(next.getPrefix())) {
                xMLStreamWriter.setDefaultNamespace(next.getURI());
            } else {
                xMLStreamWriter.setPrefix(next.getPrefix(), next.getURI());
            }
        }
        List<Content> content = element.getContent();
        Format.TextMode textMode = formatStack.getTextMode();
        boolean z = false;
        Walker walker = null;
        if (!content.isEmpty()) {
            String attributeValue = element.getAttributeValue("space", Namespace.XML_NAMESPACE);
            if (RewardedVideo.VIDEO_MODE_DEFAULT.equals(attributeValue)) {
                textMode = formatStack.getDefaultMode();
            } else if ("preserve".equals(attributeValue)) {
                textMode = Format.TextMode.PRESERVE;
            }
            formatStack.push();
            try {
                formatStack.setTextMode(textMode);
                Walker buildWalker = buildWalker(formatStack, content, false);
                if (buildWalker.hasNext()) {
                    walker = buildWalker;
                }
                formatStack.pop();
            } catch (Throwable th) {
                namespaceStack.pop();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    String str = (String) it.next();
                    Iterator<Namespace> it2 = namespaceStack.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        Namespace next2 = it2.next();
                        if (next2.getPrefix().equals(str)) {
                            if ("".equals(next2.getPrefix())) {
                                xMLStreamWriter.setDefaultNamespace(next2.getURI());
                            } else {
                                xMLStreamWriter.setPrefix(next2.getPrefix(), next2.getURI());
                            }
                        }
                    }
                }
                throw th;
            }
        }
        if (walker != null || formatStack.isExpandEmptyElements()) {
            z = true;
        }
        Namespace namespace = element.getNamespace();
        if (z) {
            xMLStreamWriter.writeStartElement(namespace.getPrefix(), element.getName(), namespace.getURI());
            for (Namespace printNamespace : namespaceStack.addedForward()) {
                printNamespace(xMLStreamWriter, formatStack, printNamespace);
            }
            if (element.hasAttributes()) {
                for (Attribute printAttribute : element.getAttributes()) {
                    printAttribute(xMLStreamWriter, formatStack, printAttribute);
                }
            }
            xMLStreamWriter.writeCharacters("");
            if (walker != null) {
                formatStack.push();
                formatStack.setTextMode(textMode);
                if (!walker.isAllText() && formatStack.getPadBetween() != null) {
                    printText(xMLStreamWriter, formatStack, new Text(formatStack.getPadBetween()));
                }
                printContent(xMLStreamWriter, formatStack, namespaceStack, walker);
                if (!walker.isAllText() && formatStack.getPadLast() != null) {
                    printText(xMLStreamWriter, formatStack, new Text(formatStack.getPadLast()));
                }
                formatStack.pop();
            }
            xMLStreamWriter.writeEndElement();
        } else {
            xMLStreamWriter.writeEmptyElement(namespace.getPrefix(), element.getName(), namespace.getURI());
            for (Namespace printNamespace2 : namespaceStack.addedForward()) {
                printNamespace(xMLStreamWriter, formatStack, printNamespace2);
            }
            for (Attribute printAttribute2 : element.getAttributes()) {
                printAttribute(xMLStreamWriter, formatStack, printAttribute2);
            }
            xMLStreamWriter.writeCharacters("");
        }
        namespaceStack.pop();
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            String str2 = (String) it3.next();
            Iterator<Namespace> it4 = namespaceStack.iterator();
            while (true) {
                if (!it4.hasNext()) {
                    break;
                }
                Namespace next3 = it4.next();
                if (next3.getPrefix().equals(str2)) {
                    if ("".equals(next3.getPrefix())) {
                        xMLStreamWriter.setDefaultNamespace(next3.getURI());
                    } else {
                        xMLStreamWriter.setPrefix(next3.getPrefix(), next3.getURI());
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void printContent(XMLStreamWriter xMLStreamWriter, FormatStack formatStack, NamespaceStack namespaceStack, Walker walker) throws XMLStreamException {
        while (walker.hasNext()) {
            Content next = walker.next();
            if (next != null) {
                switch (C24171.$SwitchMap$org$jdom2$Content$CType[next.getCType().ordinal()]) {
                    case 1:
                        printComment(xMLStreamWriter, formatStack, (Comment) next);
                        break;
                    case 2:
                        printDocType(xMLStreamWriter, formatStack, (DocType) next);
                        break;
                    case 3:
                        printElement(xMLStreamWriter, formatStack, namespaceStack, (Element) next);
                        break;
                    case 4:
                        printProcessingInstruction(xMLStreamWriter, formatStack, (ProcessingInstruction) next);
                        break;
                    case 5:
                        printText(xMLStreamWriter, formatStack, (Text) next);
                        break;
                    case 6:
                        printCDATA(xMLStreamWriter, formatStack, (CDATA) next);
                        break;
                    case 7:
                        printEntityRef(xMLStreamWriter, formatStack, (EntityRef) next);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected Content " + next.getCType());
                }
            } else if (walker.isCDATA()) {
                printCDATA(xMLStreamWriter, formatStack, new CDATA(walker.text()));
            } else {
                printText(xMLStreamWriter, formatStack, new Text(walker.text()));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void printNamespace(XMLStreamWriter xMLStreamWriter, FormatStack formatStack, Namespace namespace) throws XMLStreamException {
        xMLStreamWriter.writeNamespace(namespace.getPrefix(), namespace.getURI());
    }

    /* access modifiers changed from: protected */
    public void printAttribute(XMLStreamWriter xMLStreamWriter, FormatStack formatStack, Attribute attribute) throws XMLStreamException {
        if (attribute.isSpecified() || !formatStack.isSpecifiedAttributesOnly()) {
            Namespace namespace = attribute.getNamespace();
            if (namespace == Namespace.NO_NAMESPACE) {
                xMLStreamWriter.writeAttribute(attribute.getName(), attribute.getValue());
            } else {
                xMLStreamWriter.writeAttribute(namespace.getPrefix(), namespace.getURI(), attribute.getName(), attribute.getValue());
            }
        }
    }
}
