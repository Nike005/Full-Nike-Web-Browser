package org.jdom2.output;

import java.util.List;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.jdom2.CDATA;
import org.jdom2.Comment;
import org.jdom2.Content;
import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.EntityRef;
import org.jdom2.ProcessingInstruction;
import org.jdom2.Text;
import org.jdom2.output.support.AbstractStAXStreamProcessor;
import org.jdom2.output.support.StAXStreamProcessor;

public final class StAXStreamOutputter implements Cloneable {
    private static final DefaultStAXStreamProcessor DEFAULTPROCESSOR = new DefaultStAXStreamProcessor();
    private Format myFormat;
    private StAXStreamProcessor myProcessor;

    private static final class DefaultStAXStreamProcessor extends AbstractStAXStreamProcessor {
        private DefaultStAXStreamProcessor() {
        }
    }

    public StAXStreamOutputter(Format format, StAXStreamProcessor stAXStreamProcessor) {
        this.myFormat = null;
        this.myProcessor = null;
        this.myFormat = format == null ? Format.getRawFormat() : format.clone();
        this.myProcessor = stAXStreamProcessor == null ? DEFAULTPROCESSOR : stAXStreamProcessor;
    }

    public StAXStreamOutputter() {
        this((Format) null, (StAXStreamProcessor) null);
    }

    public StAXStreamOutputter(Format format) {
        this(format, (StAXStreamProcessor) null);
    }

    public StAXStreamOutputter(StAXStreamProcessor stAXStreamProcessor) {
        this((Format) null, stAXStreamProcessor);
    }

    public void setFormat(Format format) {
        this.myFormat = format.clone();
    }

    public Format getFormat() {
        return this.myFormat;
    }

    public StAXStreamProcessor getStAXStream() {
        return this.myProcessor;
    }

    public void setStAXStreamProcessor(StAXStreamProcessor stAXStreamProcessor) {
        this.myProcessor = stAXStreamProcessor;
    }

    public final void output(Document document, XMLStreamWriter xMLStreamWriter) throws XMLStreamException {
        this.myProcessor.process(xMLStreamWriter, this.myFormat, document);
        xMLStreamWriter.flush();
    }

    public final void output(DocType docType, XMLStreamWriter xMLStreamWriter) throws XMLStreamException {
        this.myProcessor.process(xMLStreamWriter, this.myFormat, docType);
        xMLStreamWriter.flush();
    }

    public final void output(Element element, XMLStreamWriter xMLStreamWriter) throws XMLStreamException {
        this.myProcessor.process(xMLStreamWriter, this.myFormat, element);
        xMLStreamWriter.flush();
    }

    public final void outputElementContent(Element element, XMLStreamWriter xMLStreamWriter) throws XMLStreamException {
        this.myProcessor.process(xMLStreamWriter, this.myFormat, (List<? extends Content>) element.getContent());
        xMLStreamWriter.flush();
    }

    public final void output(List<? extends Content> list, XMLStreamWriter xMLStreamWriter) throws XMLStreamException {
        this.myProcessor.process(xMLStreamWriter, this.myFormat, list);
        xMLStreamWriter.flush();
    }

    public final void output(CDATA cdata, XMLStreamWriter xMLStreamWriter) throws XMLStreamException {
        this.myProcessor.process(xMLStreamWriter, this.myFormat, cdata);
        xMLStreamWriter.flush();
    }

    public final void output(Text text, XMLStreamWriter xMLStreamWriter) throws XMLStreamException {
        this.myProcessor.process(xMLStreamWriter, this.myFormat, text);
        xMLStreamWriter.flush();
    }

    public final void output(Comment comment, XMLStreamWriter xMLStreamWriter) throws XMLStreamException {
        this.myProcessor.process(xMLStreamWriter, this.myFormat, comment);
        xMLStreamWriter.flush();
    }

    public final void output(ProcessingInstruction processingInstruction, XMLStreamWriter xMLStreamWriter) throws XMLStreamException {
        this.myProcessor.process(xMLStreamWriter, this.myFormat, processingInstruction);
        xMLStreamWriter.flush();
    }

    public final void output(EntityRef entityRef, XMLStreamWriter xMLStreamWriter) throws XMLStreamException {
        this.myProcessor.process(xMLStreamWriter, this.myFormat, entityRef);
        xMLStreamWriter.flush();
    }

    public StAXStreamOutputter clone() {
        try {
            return (StAXStreamOutputter) super.clone();
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
