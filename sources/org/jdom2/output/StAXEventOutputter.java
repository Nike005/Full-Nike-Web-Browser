package org.jdom2.output;

import java.util.List;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLStreamException;
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
import org.jdom2.output.support.AbstractStAXEventProcessor;
import org.jdom2.output.support.StAXEventProcessor;

public final class StAXEventOutputter implements Cloneable {
    private static final XMLEventFactory DEFAULTEVENTFACTORY = XMLEventFactory.newInstance();
    private static final DefaultStAXEventProcessor DEFAULTPROCESSOR = new DefaultStAXEventProcessor();
    private XMLEventFactory myEventFactory;
    private Format myFormat;
    private StAXEventProcessor myProcessor;

    private static final class DefaultStAXEventProcessor extends AbstractStAXEventProcessor {
        private DefaultStAXEventProcessor() {
        }
    }

    public StAXEventOutputter(Format format, StAXEventProcessor stAXEventProcessor, XMLEventFactory xMLEventFactory) {
        this.myFormat = null;
        this.myProcessor = null;
        this.myEventFactory = null;
        this.myFormat = format == null ? Format.getRawFormat() : format.clone();
        this.myProcessor = stAXEventProcessor == null ? DEFAULTPROCESSOR : stAXEventProcessor;
        this.myEventFactory = xMLEventFactory == null ? DEFAULTEVENTFACTORY : xMLEventFactory;
    }

    public StAXEventOutputter() {
        this((Format) null, (StAXEventProcessor) null, (XMLEventFactory) null);
    }

    public StAXEventOutputter(Format format) {
        this(format, (StAXEventProcessor) null, (XMLEventFactory) null);
    }

    public StAXEventOutputter(StAXEventProcessor stAXEventProcessor) {
        this((Format) null, stAXEventProcessor, (XMLEventFactory) null);
    }

    public StAXEventOutputter(XMLEventFactory xMLEventFactory) {
        this((Format) null, (StAXEventProcessor) null, xMLEventFactory);
    }

    public void setFormat(Format format) {
        this.myFormat = format.clone();
    }

    public Format getFormat() {
        return this.myFormat;
    }

    public StAXEventProcessor getStAXStream() {
        return this.myProcessor;
    }

    public void setStAXEventProcessor(StAXEventProcessor stAXEventProcessor) {
        this.myProcessor = stAXEventProcessor;
    }

    public XMLEventFactory getEventFactory() {
        return this.myEventFactory;
    }

    public void setEventFactory(XMLEventFactory xMLEventFactory) {
        this.myEventFactory = xMLEventFactory;
    }

    public final void output(Document document, XMLEventConsumer xMLEventConsumer) throws XMLStreamException {
        this.myProcessor.process(xMLEventConsumer, this.myFormat, this.myEventFactory, document);
    }

    public final void output(DocType docType, XMLEventConsumer xMLEventConsumer) throws XMLStreamException {
        this.myProcessor.process(xMLEventConsumer, this.myFormat, this.myEventFactory, docType);
    }

    public final void output(Element element, XMLEventConsumer xMLEventConsumer) throws XMLStreamException {
        this.myProcessor.process(xMLEventConsumer, this.myFormat, this.myEventFactory, element);
    }

    public final void outputElementContent(Element element, XMLEventConsumer xMLEventConsumer) throws XMLStreamException {
        this.myProcessor.process(xMLEventConsumer, this.myFormat, this.myEventFactory, (List<? extends Content>) element.getContent());
    }

    public final void output(List<? extends Content> list, XMLEventConsumer xMLEventConsumer) throws XMLStreamException {
        this.myProcessor.process(xMLEventConsumer, this.myFormat, this.myEventFactory, list);
    }

    public final void output(CDATA cdata, XMLEventConsumer xMLEventConsumer) throws XMLStreamException {
        this.myProcessor.process(xMLEventConsumer, this.myFormat, this.myEventFactory, cdata);
    }

    public final void output(Text text, XMLEventConsumer xMLEventConsumer) throws XMLStreamException {
        this.myProcessor.process(xMLEventConsumer, this.myFormat, this.myEventFactory, text);
    }

    public final void output(Comment comment, XMLEventConsumer xMLEventConsumer) throws XMLStreamException {
        this.myProcessor.process(xMLEventConsumer, this.myFormat, this.myEventFactory, comment);
    }

    public final void output(ProcessingInstruction processingInstruction, XMLEventConsumer xMLEventConsumer) throws XMLStreamException {
        this.myProcessor.process(xMLEventConsumer, this.myFormat, this.myEventFactory, processingInstruction);
    }

    public final void output(EntityRef entityRef, XMLEventConsumer xMLEventConsumer) throws XMLStreamException {
        this.myProcessor.process(xMLEventConsumer, this.myFormat, this.myEventFactory, entityRef);
    }

    public StAXEventOutputter clone() {
        try {
            return (StAXEventOutputter) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e.toString());
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("StAXStreamOutputter[omitDeclaration = ");
        sb.append(this.myFormat.omitDeclaration);
        sb.append(", ");
        sb.append("encoding = ");
        sb.append(this.myFormat.encoding);
        sb.append(", ");
        sb.append("omitEncoding = ");
        sb.append(this.myFormat.omitEncoding);
        sb.append(", ");
        sb.append("indent = '");
        sb.append(this.myFormat.indent);
        sb.append("'");
        sb.append(", ");
        sb.append("expandEmptyElements = ");
        sb.append(this.myFormat.expandEmptyElements);
        sb.append(", ");
        sb.append("lineSeparator = '");
        for (char c : this.myFormat.lineSeparator.toCharArray()) {
            if (c == 9) {
                sb.append("\\t");
            } else if (c == 10) {
                sb.append("\\n");
            } else if (c != 13) {
                sb.append("[" + c + "]");
            } else {
                sb.append("\\r");
            }
        }
        sb.append("', ");
        sb.append("textMode = ");
        sb.append(this.myFormat.mode + "]");
        return sb.toString();
    }
}
