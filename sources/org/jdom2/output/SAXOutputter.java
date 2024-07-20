package org.jdom2.output;

import java.util.List;
import org.jdom2.CDATA;
import org.jdom2.Comment;
import org.jdom2.Content;
import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.EntityRef;
import org.jdom2.JDOMConstants;
import org.jdom2.JDOMException;
import org.jdom2.ProcessingInstruction;
import org.jdom2.Text;
import org.jdom2.output.support.AbstractSAXOutputProcessor;
import org.jdom2.output.support.SAXOutputProcessor;
import org.jdom2.output.support.SAXTarget;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;

public class SAXOutputter {
    private static final SAXOutputProcessor DEFAULT_PROCESSOR = new DefaultSAXOutputProcessor((C24071) null);
    private ContentHandler contentHandler;
    private DeclHandler declHandler;
    private boolean declareNamespaces;
    private DTDHandler dtdHandler;
    private EntityResolver entityResolver;
    private ErrorHandler errorHandler;
    private Format format;
    private LexicalHandler lexicalHandler;
    private SAXOutputProcessor processor;
    private boolean reportDtdEvents;

    @Deprecated
    public JDOMLocator getLocator() {
        return null;
    }

    private static final class DefaultSAXOutputProcessor extends AbstractSAXOutputProcessor {
        private DefaultSAXOutputProcessor() {
        }

        /* synthetic */ DefaultSAXOutputProcessor(C24071 r1) {
            this();
        }
    }

    public SAXOutputter() {
        this.declareNamespaces = false;
        this.reportDtdEvents = true;
        this.processor = DEFAULT_PROCESSOR;
        this.format = Format.getRawFormat();
    }

    public SAXOutputter(ContentHandler contentHandler2) {
        this(contentHandler2, (ErrorHandler) null, (DTDHandler) null, (EntityResolver) null, (LexicalHandler) null);
    }

    public SAXOutputter(ContentHandler contentHandler2, ErrorHandler errorHandler2, DTDHandler dTDHandler, EntityResolver entityResolver2) {
        this(contentHandler2, errorHandler2, dTDHandler, entityResolver2, (LexicalHandler) null);
    }

    public SAXOutputter(ContentHandler contentHandler2, ErrorHandler errorHandler2, DTDHandler dTDHandler, EntityResolver entityResolver2, LexicalHandler lexicalHandler2) {
        this.declareNamespaces = false;
        this.reportDtdEvents = true;
        this.processor = DEFAULT_PROCESSOR;
        this.format = Format.getRawFormat();
        this.contentHandler = contentHandler2;
        this.errorHandler = errorHandler2;
        this.dtdHandler = dTDHandler;
        this.entityResolver = entityResolver2;
        this.lexicalHandler = lexicalHandler2;
    }

    public SAXOutputter(SAXOutputProcessor sAXOutputProcessor, Format format2, ContentHandler contentHandler2, ErrorHandler errorHandler2, DTDHandler dTDHandler, EntityResolver entityResolver2, LexicalHandler lexicalHandler2) {
        this.declareNamespaces = false;
        this.reportDtdEvents = true;
        this.processor = DEFAULT_PROCESSOR;
        this.format = Format.getRawFormat();
        this.processor = sAXOutputProcessor == null ? DEFAULT_PROCESSOR : sAXOutputProcessor;
        this.format = format2 == null ? Format.getRawFormat() : format2;
        this.contentHandler = contentHandler2;
        this.errorHandler = errorHandler2;
        this.dtdHandler = dTDHandler;
        this.entityResolver = entityResolver2;
        this.lexicalHandler = lexicalHandler2;
    }

    public void setContentHandler(ContentHandler contentHandler2) {
        this.contentHandler = contentHandler2;
    }

    public ContentHandler getContentHandler() {
        return this.contentHandler;
    }

    public void setErrorHandler(ErrorHandler errorHandler2) {
        this.errorHandler = errorHandler2;
    }

    public ErrorHandler getErrorHandler() {
        return this.errorHandler;
    }

    public void setDTDHandler(DTDHandler dTDHandler) {
        this.dtdHandler = dTDHandler;
    }

    public DTDHandler getDTDHandler() {
        return this.dtdHandler;
    }

    public void setEntityResolver(EntityResolver entityResolver2) {
        this.entityResolver = entityResolver2;
    }

    public EntityResolver getEntityResolver() {
        return this.entityResolver;
    }

    public void setLexicalHandler(LexicalHandler lexicalHandler2) {
        this.lexicalHandler = lexicalHandler2;
    }

    public LexicalHandler getLexicalHandler() {
        return this.lexicalHandler;
    }

    public void setDeclHandler(DeclHandler declHandler2) {
        this.declHandler = declHandler2;
    }

    public DeclHandler getDeclHandler() {
        return this.declHandler;
    }

    public boolean getReportNamespaceDeclarations() {
        return this.declareNamespaces;
    }

    public void setReportNamespaceDeclarations(boolean z) {
        this.declareNamespaces = z;
    }

    public boolean getReportDTDEvents() {
        return this.reportDtdEvents;
    }

    public void setReportDTDEvents(boolean z) {
        this.reportDtdEvents = z;
    }

    public void setFeature(String str, boolean z) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (JDOMConstants.SAX_FEATURE_NAMESPACE_PREFIXES.equals(str)) {
            setReportNamespaceDeclarations(z);
        } else if (JDOMConstants.SAX_FEATURE_NAMESPACES.equals(str)) {
            if (!z) {
                throw new SAXNotSupportedException(str);
            }
        } else if (JDOMConstants.SAX_FEATURE_VALIDATION.equals(str)) {
            setReportDTDEvents(z);
        } else {
            throw new SAXNotRecognizedException(str);
        }
    }

    public boolean getFeature(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (JDOMConstants.SAX_FEATURE_NAMESPACE_PREFIXES.equals(str)) {
            return this.declareNamespaces;
        }
        if (JDOMConstants.SAX_FEATURE_NAMESPACES.equals(str)) {
            return true;
        }
        if (JDOMConstants.SAX_FEATURE_VALIDATION.equals(str)) {
            return this.reportDtdEvents;
        }
        throw new SAXNotRecognizedException(str);
    }

    public void setProperty(String str, Object obj) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (JDOMConstants.SAX_PROPERTY_LEXICAL_HANDLER.equals(str) || JDOMConstants.SAX_PROPERTY_LEXICAL_HANDLER_ALT.equals(str)) {
            setLexicalHandler((LexicalHandler) obj);
        } else if (JDOMConstants.SAX_PROPERTY_DECLARATION_HANDLER.equals(str) || JDOMConstants.SAX_PROPERTY_DECLARATION_HANDLER_ALT.equals(str)) {
            setDeclHandler((DeclHandler) obj);
        } else {
            throw new SAXNotRecognizedException(str);
        }
    }

    public Object getProperty(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (JDOMConstants.SAX_PROPERTY_LEXICAL_HANDLER.equals(str) || JDOMConstants.SAX_PROPERTY_LEXICAL_HANDLER_ALT.equals(str)) {
            return getLexicalHandler();
        }
        if (JDOMConstants.SAX_PROPERTY_DECLARATION_HANDLER.equals(str) || JDOMConstants.SAX_PROPERTY_DECLARATION_HANDLER_ALT.equals(str)) {
            return getDeclHandler();
        }
        throw new SAXNotRecognizedException(str);
    }

    public SAXOutputProcessor getSAXOutputProcessor() {
        return this.processor;
    }

    public void setSAXOutputProcessor(SAXOutputProcessor sAXOutputProcessor) {
        if (sAXOutputProcessor == null) {
            sAXOutputProcessor = DEFAULT_PROCESSOR;
        }
        this.processor = sAXOutputProcessor;
    }

    public Format getFormat() {
        return this.format;
    }

    public void setFormat(Format format2) {
        if (format2 == null) {
            format2 = Format.getRawFormat();
        }
        this.format = format2;
    }

    private final SAXTarget buildTarget(Document document) {
        String str;
        String str2;
        DocType docType;
        if (document == null || (docType = document.getDocType()) == null) {
            str2 = null;
            str = null;
        } else {
            String publicID = docType.getPublicID();
            str = docType.getSystemID();
            str2 = publicID;
        }
        return new SAXTarget(this.contentHandler, this.errorHandler, this.dtdHandler, this.entityResolver, this.lexicalHandler, this.declHandler, this.declareNamespaces, this.reportDtdEvents, str2, str);
    }

    public void output(Document document) throws JDOMException {
        this.processor.process(buildTarget(document), this.format, document);
    }

    public void output(List<? extends Content> list) throws JDOMException {
        this.processor.processAsDocument(buildTarget((Document) null), this.format, list);
    }

    public void output(Element element) throws JDOMException {
        this.processor.processAsDocument(buildTarget((Document) null), this.format, element);
    }

    public void outputFragment(List<? extends Content> list) throws JDOMException {
        if (list != null) {
            this.processor.process(buildTarget((Document) null), this.format, list);
        }
    }

    public void outputFragment(Content content) throws JDOMException {
        if (content != null) {
            SAXTarget buildTarget = buildTarget((Document) null);
            switch (C24071.$SwitchMap$org$jdom2$Content$CType[content.getCType().ordinal()]) {
                case 1:
                    this.processor.process(buildTarget, this.format, (CDATA) content);
                    return;
                case 2:
                    this.processor.process(buildTarget, this.format, (Comment) content);
                    return;
                case 3:
                    this.processor.process(buildTarget, this.format, (Element) content);
                    return;
                case 4:
                    this.processor.process(buildTarget, this.format, (EntityRef) content);
                    return;
                case 5:
                    this.processor.process(buildTarget, this.format, (ProcessingInstruction) content);
                    return;
                case 6:
                    this.processor.process(buildTarget, this.format, (Text) content);
                    return;
                default:
                    handleError(new JDOMException("Invalid element content: " + content));
                    return;
            }
        }
    }

    /* renamed from: org.jdom2.output.SAXOutputter$1 */
    static /* synthetic */ class C24071 {
        static final /* synthetic */ int[] $SwitchMap$org$jdom2$Content$CType;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
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
                org.jdom2.Content$CType r1 = org.jdom2.Content.CType.CDATA     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$jdom2$Content$CType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.jdom2.Content$CType r1 = org.jdom2.Content.CType.Comment     // Catch:{ NoSuchFieldError -> 0x001d }
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
                org.jdom2.Content$CType r1 = org.jdom2.Content.CType.EntityRef     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$jdom2$Content$CType     // Catch:{ NoSuchFieldError -> 0x003e }
                org.jdom2.Content$CType r1 = org.jdom2.Content.CType.ProcessingInstruction     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$jdom2$Content$CType     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.jdom2.Content$CType r1 = org.jdom2.Content.CType.Text     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jdom2.output.SAXOutputter.C24071.<clinit>():void");
        }
    }

    private void handleError(JDOMException jDOMException) throws JDOMException {
        ErrorHandler errorHandler2 = this.errorHandler;
        if (errorHandler2 != null) {
            try {
                errorHandler2.error(new SAXParseException(jDOMException.getMessage(), (Locator) null, jDOMException));
            } catch (SAXException e) {
                if (e.getException() instanceof JDOMException) {
                    throw ((JDOMException) e.getException());
                }
                throw new JDOMException(e.getMessage(), e);
            }
        } else {
            throw jDOMException;
        }
    }
}
