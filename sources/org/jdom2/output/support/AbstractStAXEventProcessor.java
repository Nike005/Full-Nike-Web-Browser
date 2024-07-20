package org.jdom2.output.support;

import com.appnext.ads.fullscreen.RewardedVideo;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EntityDeclaration;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.util.XMLEventConsumer;
import org.jdom2.CDATA;
import org.jdom2.Comment;
import org.jdom2.Content;
import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.EntityRef;
import org.jdom2.ProcessingInstruction;
import org.jdom2.Text;
import org.jdom2.Verifier;
import org.jdom2.output.Format;
import org.jdom2.util.NamespaceStack;

public abstract class AbstractStAXEventProcessor extends AbstractOutputProcessor implements StAXEventProcessor {

    private static final class NSIterator implements Iterator<Namespace> {
        private final XMLEventFactory fac;
        private final Iterator<org.jdom2.Namespace> source;

        public NSIterator(Iterator<org.jdom2.Namespace> it, XMLEventFactory xMLEventFactory) {
            this.source = it;
            this.fac = xMLEventFactory;
        }

        public boolean hasNext() {
            return this.source.hasNext();
        }

        public Namespace next() {
            org.jdom2.Namespace next = this.source.next();
            return this.fac.createNamespace(next.getPrefix(), next.getURI());
        }

        public void remove() {
            throw new UnsupportedOperationException("Cannot remove Namespaces");
        }
    }

    private static final class AttIterator implements Iterator<Attribute> {
        private final XMLEventFactory fac;
        private final Iterator<org.jdom2.Attribute> source;

        public AttIterator(Iterator<org.jdom2.Attribute> it, XMLEventFactory xMLEventFactory, boolean z) {
            this.source = z ? specified(it) : it;
            this.fac = xMLEventFactory;
        }

        private Iterator<org.jdom2.Attribute> specified(Iterator<org.jdom2.Attribute> it) {
            if (it == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            while (it.hasNext()) {
                org.jdom2.Attribute next = it.next();
                if (next.isSpecified()) {
                    arrayList.add(next);
                }
            }
            if (arrayList.isEmpty()) {
                return null;
            }
            return arrayList.iterator();
        }

        public boolean hasNext() {
            Iterator<org.jdom2.Attribute> it = this.source;
            return it != null && it.hasNext();
        }

        public Attribute next() {
            org.jdom2.Attribute next = this.source.next();
            org.jdom2.Namespace namespace = next.getNamespace();
            if (namespace == org.jdom2.Namespace.NO_NAMESPACE) {
                return this.fac.createAttribute(next.getName(), next.getValue());
            }
            return this.fac.createAttribute(namespace.getPrefix(), namespace.getURI(), next.getName(), next.getValue());
        }

        public void remove() {
            throw new UnsupportedOperationException("Cannot remove attributes");
        }
    }

    public void process(XMLEventConsumer xMLEventConsumer, Format format, XMLEventFactory xMLEventFactory, Document document) throws XMLStreamException {
        printDocument(xMLEventConsumer, new FormatStack(format), new NamespaceStack(), xMLEventFactory, document);
    }

    public void process(XMLEventConsumer xMLEventConsumer, Format format, XMLEventFactory xMLEventFactory, DocType docType) throws XMLStreamException {
        printDocType(xMLEventConsumer, new FormatStack(format), xMLEventFactory, docType);
    }

    public void process(XMLEventConsumer xMLEventConsumer, Format format, XMLEventFactory xMLEventFactory, Element element) throws XMLStreamException {
        printElement(xMLEventConsumer, new FormatStack(format), new NamespaceStack(), xMLEventFactory, element);
    }

    public void process(XMLEventConsumer xMLEventConsumer, Format format, XMLEventFactory xMLEventFactory, List<? extends Content> list) throws XMLStreamException {
        XMLEventConsumer xMLEventConsumer2 = xMLEventConsumer;
        printContent(xMLEventConsumer2, new FormatStack(format), new NamespaceStack(), xMLEventFactory, buildWalker(new FormatStack(format), list, false));
    }

    public void process(XMLEventConsumer xMLEventConsumer, Format format, XMLEventFactory xMLEventFactory, CDATA cdata) throws XMLStreamException {
        List singletonList = Collections.singletonList(cdata);
        FormatStack formatStack = new FormatStack(format);
        Walker buildWalker = buildWalker(formatStack, singletonList, false);
        if (buildWalker.hasNext()) {
            Content next = buildWalker.next();
            if (next == null) {
                printCDATA(xMLEventConsumer, formatStack, xMLEventFactory, new CDATA(buildWalker.text()));
            } else if (next.getCType() == Content.CType.CDATA) {
                printCDATA(xMLEventConsumer, formatStack, xMLEventFactory, (CDATA) next);
            }
        }
    }

    public void process(XMLEventConsumer xMLEventConsumer, Format format, XMLEventFactory xMLEventFactory, Text text) throws XMLStreamException {
        List singletonList = Collections.singletonList(text);
        FormatStack formatStack = new FormatStack(format);
        Walker buildWalker = buildWalker(formatStack, singletonList, false);
        if (buildWalker.hasNext()) {
            Content next = buildWalker.next();
            if (next == null) {
                printText(xMLEventConsumer, formatStack, xMLEventFactory, new Text(buildWalker.text()));
            } else if (next.getCType() == Content.CType.Text) {
                printText(xMLEventConsumer, formatStack, xMLEventFactory, (Text) next);
            }
        }
    }

    public void process(XMLEventConsumer xMLEventConsumer, Format format, XMLEventFactory xMLEventFactory, Comment comment) throws XMLStreamException {
        printComment(xMLEventConsumer, new FormatStack(format), xMLEventFactory, comment);
    }

    public void process(XMLEventConsumer xMLEventConsumer, Format format, XMLEventFactory xMLEventFactory, ProcessingInstruction processingInstruction) throws XMLStreamException {
        FormatStack formatStack = new FormatStack(format);
        formatStack.setIgnoreTrAXEscapingPIs(true);
        printProcessingInstruction(xMLEventConsumer, formatStack, xMLEventFactory, processingInstruction);
    }

    public void process(XMLEventConsumer xMLEventConsumer, Format format, XMLEventFactory xMLEventFactory, EntityRef entityRef) throws XMLStreamException {
        printEntityRef(xMLEventConsumer, new FormatStack(format), xMLEventFactory, entityRef);
    }

    /* access modifiers changed from: protected */
    public void printDocument(XMLEventConsumer xMLEventConsumer, FormatStack formatStack, NamespaceStack namespaceStack, XMLEventFactory xMLEventFactory, Document document) throws XMLStreamException {
        if (formatStack.isOmitDeclaration()) {
            xMLEventConsumer.add(xMLEventFactory.createStartDocument((String) null, (String) null));
        } else if (formatStack.isOmitEncoding()) {
            xMLEventConsumer.add(xMLEventFactory.createStartDocument((String) null, "1.0"));
            if (formatStack.getLineSeparator() != null) {
                xMLEventConsumer.add(xMLEventFactory.createCharacters(formatStack.getLineSeparator()));
            }
        } else {
            xMLEventConsumer.add(xMLEventFactory.createStartDocument(formatStack.getEncoding(), "1.0"));
            if (formatStack.getLineSeparator() != null) {
                xMLEventConsumer.add(xMLEventFactory.createCharacters(formatStack.getLineSeparator()));
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
                    String text = buildWalker.text();
                    if (text != null && Verifier.isAllXMLWhitespace(text) && !buildWalker.isCDATA()) {
                        xMLEventConsumer.add(xMLEventFactory.createCharacters(text));
                    }
                } else {
                    int i2 = C24161.$SwitchMap$org$jdom2$Content$CType[next.getCType().ordinal()];
                    if (i2 == 1) {
                        printComment(xMLEventConsumer, formatStack, xMLEventFactory, (Comment) next);
                    } else if (i2 == 2) {
                        printDocType(xMLEventConsumer, formatStack, xMLEventFactory, (DocType) next);
                    } else if (i2 == 3) {
                        printElement(xMLEventConsumer, formatStack, namespaceStack, xMLEventFactory, (Element) next);
                    } else if (i2 == 4) {
                        printProcessingInstruction(xMLEventConsumer, formatStack, xMLEventFactory, (ProcessingInstruction) next);
                    }
                }
            }
            if (formatStack.getLineSeparator() != null) {
                xMLEventConsumer.add(xMLEventFactory.createCharacters(formatStack.getLineSeparator()));
            }
        }
        xMLEventConsumer.add(xMLEventFactory.createEndDocument());
    }

    /* renamed from: org.jdom2.output.support.AbstractStAXEventProcessor$1 */
    static /* synthetic */ class C24161 {
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
                org.jdom2.Content$CType r1 = org.jdom2.Content.CType.CDATA     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$jdom2$Content$CType     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.jdom2.Content$CType r1 = org.jdom2.Content.CType.EntityRef     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$jdom2$Content$CType     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.jdom2.Content$CType r1 = org.jdom2.Content.CType.Text     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jdom2.output.support.AbstractStAXEventProcessor.C24161.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public void printDocType(XMLEventConsumer xMLEventConsumer, FormatStack formatStack, XMLEventFactory xMLEventFactory, DocType docType) throws XMLStreamException {
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
        xMLEventConsumer.add(xMLEventFactory.createDTD(stringWriter.toString()));
    }

    /* access modifiers changed from: protected */
    public void printProcessingInstruction(XMLEventConsumer xMLEventConsumer, FormatStack formatStack, XMLEventFactory xMLEventFactory, ProcessingInstruction processingInstruction) throws XMLStreamException {
        String target = processingInstruction.getTarget();
        String data = processingInstruction.getData();
        if (data == null || data.trim().length() <= 0) {
            xMLEventConsumer.add(xMLEventFactory.createProcessingInstruction(target, ""));
        } else {
            xMLEventConsumer.add(xMLEventFactory.createProcessingInstruction(target, data));
        }
    }

    /* access modifiers changed from: protected */
    public void printComment(XMLEventConsumer xMLEventConsumer, FormatStack formatStack, XMLEventFactory xMLEventFactory, Comment comment) throws XMLStreamException {
        xMLEventConsumer.add(xMLEventFactory.createComment(comment.getText()));
    }

    /* access modifiers changed from: protected */
    public void printEntityRef(XMLEventConsumer xMLEventConsumer, FormatStack formatStack, XMLEventFactory xMLEventFactory, EntityRef entityRef) throws XMLStreamException {
        xMLEventConsumer.add(xMLEventFactory.createEntityReference(entityRef.getName(), (EntityDeclaration) null));
    }

    /* access modifiers changed from: protected */
    public void printCDATA(XMLEventConsumer xMLEventConsumer, FormatStack formatStack, XMLEventFactory xMLEventFactory, CDATA cdata) throws XMLStreamException {
        xMLEventConsumer.add(xMLEventFactory.createCData(cdata.getText()));
    }

    /* access modifiers changed from: protected */
    public void printText(XMLEventConsumer xMLEventConsumer, FormatStack formatStack, XMLEventFactory xMLEventFactory, Text text) throws XMLStreamException {
        xMLEventConsumer.add(xMLEventFactory.createCharacters(text.getText()));
    }

    /* access modifiers changed from: protected */
    public void printElement(XMLEventConsumer xMLEventConsumer, FormatStack formatStack, NamespaceStack namespaceStack, XMLEventFactory xMLEventFactory, Element element) throws XMLStreamException {
        namespaceStack.push(element);
        try {
            org.jdom2.Namespace namespace = element.getNamespace();
            Iterator<org.jdom2.Attribute> it = element.hasAttributes() ? element.getAttributes().iterator() : null;
            if (namespace == org.jdom2.Namespace.NO_NAMESPACE) {
                xMLEventConsumer.add(xMLEventFactory.createStartElement("", "", element.getName(), new AttIterator(it, xMLEventFactory, formatStack.isSpecifiedAttributesOnly()), new NSIterator(namespaceStack.addedForward().iterator(), xMLEventFactory)));
            } else if ("".equals(namespace.getPrefix())) {
                xMLEventConsumer.add(xMLEventFactory.createStartElement("", namespace.getURI(), element.getName(), new AttIterator(it, xMLEventFactory, formatStack.isSpecifiedAttributesOnly()), new NSIterator(namespaceStack.addedForward().iterator(), xMLEventFactory)));
            } else {
                xMLEventConsumer.add(xMLEventFactory.createStartElement(namespace.getPrefix(), namespace.getURI(), element.getName(), new AttIterator(it, xMLEventFactory, formatStack.isSpecifiedAttributesOnly()), new NSIterator(namespaceStack.addedForward().iterator(), xMLEventFactory)));
            }
            List<Content> content = element.getContent();
            if (!content.isEmpty()) {
                Format.TextMode textMode = formatStack.getTextMode();
                String attributeValue = element.getAttributeValue("space", org.jdom2.Namespace.XML_NAMESPACE);
                if (RewardedVideo.VIDEO_MODE_DEFAULT.equals(attributeValue)) {
                    textMode = formatStack.getDefaultMode();
                } else if ("preserve".equals(attributeValue)) {
                    textMode = Format.TextMode.PRESERVE;
                }
                formatStack.push();
                formatStack.setTextMode(textMode);
                Walker buildWalker = buildWalker(formatStack, content, false);
                if (buildWalker.hasNext()) {
                    if (!buildWalker.isAllText() && formatStack.getPadBetween() != null) {
                        printText(xMLEventConsumer, formatStack, xMLEventFactory, new Text(formatStack.getPadBetween()));
                    }
                    printContent(xMLEventConsumer, formatStack, namespaceStack, xMLEventFactory, buildWalker);
                    if (!buildWalker.isAllText() && formatStack.getPadLast() != null) {
                        printText(xMLEventConsumer, formatStack, xMLEventFactory, new Text(formatStack.getPadLast()));
                    }
                }
                formatStack.pop();
            }
            xMLEventConsumer.add(xMLEventFactory.createEndElement(element.getNamespacePrefix(), element.getNamespaceURI(), element.getName(), new NSIterator(namespaceStack.addedReverse().iterator(), xMLEventFactory)));
            namespaceStack.pop();
        } catch (Throwable th) {
            namespaceStack.pop();
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public void printContent(XMLEventConsumer xMLEventConsumer, FormatStack formatStack, NamespaceStack namespaceStack, XMLEventFactory xMLEventFactory, Walker walker) throws XMLStreamException {
        while (walker.hasNext()) {
            Content next = walker.next();
            if (next != null) {
                switch (C24161.$SwitchMap$org$jdom2$Content$CType[next.getCType().ordinal()]) {
                    case 1:
                        printComment(xMLEventConsumer, formatStack, xMLEventFactory, (Comment) next);
                        break;
                    case 2:
                        printDocType(xMLEventConsumer, formatStack, xMLEventFactory, (DocType) next);
                        break;
                    case 3:
                        printElement(xMLEventConsumer, formatStack, namespaceStack, xMLEventFactory, (Element) next);
                        break;
                    case 4:
                        printProcessingInstruction(xMLEventConsumer, formatStack, xMLEventFactory, (ProcessingInstruction) next);
                        break;
                    case 5:
                        printCDATA(xMLEventConsumer, formatStack, xMLEventFactory, (CDATA) next);
                        break;
                    case 6:
                        printEntityRef(xMLEventConsumer, formatStack, xMLEventFactory, (EntityRef) next);
                        break;
                    case 7:
                        printText(xMLEventConsumer, formatStack, xMLEventFactory, (Text) next);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected Content " + next.getCType());
                }
            } else if (walker.isCDATA()) {
                printCDATA(xMLEventConsumer, formatStack, xMLEventFactory, new CDATA(walker.text()));
            } else {
                printText(xMLEventConsumer, formatStack, xMLEventFactory, new Text(walker.text()));
            }
        }
    }
}
