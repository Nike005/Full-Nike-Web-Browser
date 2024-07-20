package org.jdom2.input;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.jdom2.DefaultJDOMFactory;
import org.jdom2.Document;
import org.jdom2.JDOMConstants;
import org.jdom2.JDOMException;
import org.jdom2.JDOMFactory;
import org.jdom2.Verifier;
import org.jdom2.input.sax.BuilderErrorHandler;
import org.jdom2.input.sax.DefaultSAXHandlerFactory;
import org.jdom2.input.sax.SAXBuilderEngine;
import org.jdom2.input.sax.SAXEngine;
import org.jdom2.input.sax.SAXHandler;
import org.jdom2.input.sax.SAXHandlerFactory;
import org.jdom2.input.sax.XMLReaderJDOMFactory;
import org.jdom2.input.sax.XMLReaderSAX2Factory;
import org.jdom2.input.sax.XMLReaders;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;

public class SAXBuilder implements SAXEngine {
    private static final JDOMFactory DEFAULTJDOMFAC = new DefaultJDOMFactory();
    private static final SAXHandlerFactory DEFAULTSAXHANDLERFAC = new DefaultSAXHandlerFactory();
    private SAXEngine engine;
    private boolean expand;
    private final HashMap<String, Boolean> features;
    private SAXHandlerFactory handlerfac;
    private boolean ignoringBoundaryWhite;
    private boolean ignoringWhite;
    private JDOMFactory jdomfac;
    private final HashMap<String, Object> properties;
    private XMLReaderJDOMFactory readerfac;
    private boolean reuseParser;
    private DTDHandler saxDTDHandler;
    private EntityResolver saxEntityResolver;
    private ErrorHandler saxErrorHandler;
    private XMLFilter saxXMLFilter;

    @Deprecated
    public void setFastReconfigure(boolean z) {
    }

    public SAXBuilder() {
        this((XMLReaderJDOMFactory) null, (SAXHandlerFactory) null, (JDOMFactory) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public SAXBuilder(boolean z) {
        this(z ? XMLReaders.DTDVALIDATING : XMLReaders.NONVALIDATING, (SAXHandlerFactory) null, (JDOMFactory) null);
    }

    @Deprecated
    public SAXBuilder(String str) {
        this(str, false);
    }

    @Deprecated
    public SAXBuilder(String str, boolean z) {
        this(new XMLReaderSAX2Factory(z, str), (SAXHandlerFactory) null, (JDOMFactory) null);
    }

    public SAXBuilder(XMLReaderJDOMFactory xMLReaderJDOMFactory) {
        this(xMLReaderJDOMFactory, (SAXHandlerFactory) null, (JDOMFactory) null);
    }

    public SAXBuilder(XMLReaderJDOMFactory xMLReaderJDOMFactory, SAXHandlerFactory sAXHandlerFactory, JDOMFactory jDOMFactory) {
        this.readerfac = null;
        this.handlerfac = null;
        this.jdomfac = null;
        this.features = new HashMap<>(5);
        this.properties = new HashMap<>(5);
        this.saxErrorHandler = null;
        this.saxEntityResolver = null;
        this.saxDTDHandler = null;
        this.saxXMLFilter = null;
        this.expand = true;
        this.ignoringWhite = false;
        this.ignoringBoundaryWhite = false;
        this.reuseParser = true;
        this.engine = null;
        this.readerfac = xMLReaderJDOMFactory == null ? XMLReaders.NONVALIDATING : xMLReaderJDOMFactory;
        this.handlerfac = sAXHandlerFactory == null ? DEFAULTSAXHANDLERFAC : sAXHandlerFactory;
        this.jdomfac = jDOMFactory == null ? DEFAULTJDOMFAC : jDOMFactory;
    }

    @Deprecated
    public String getDriverClass() {
        XMLReaderJDOMFactory xMLReaderJDOMFactory = this.readerfac;
        if (xMLReaderJDOMFactory instanceof XMLReaderSAX2Factory) {
            return ((XMLReaderSAX2Factory) xMLReaderJDOMFactory).getDriverClassName();
        }
        return null;
    }

    @Deprecated
    public JDOMFactory getFactory() {
        return getJDOMFactory();
    }

    public JDOMFactory getJDOMFactory() {
        return this.jdomfac;
    }

    @Deprecated
    public void setFactory(JDOMFactory jDOMFactory) {
        setJDOMFactory(jDOMFactory);
    }

    public void setJDOMFactory(JDOMFactory jDOMFactory) {
        this.jdomfac = jDOMFactory;
        this.engine = null;
    }

    public XMLReaderJDOMFactory getXMLReaderFactory() {
        return this.readerfac;
    }

    public void setXMLReaderFactory(XMLReaderJDOMFactory xMLReaderJDOMFactory) {
        if (xMLReaderJDOMFactory == null) {
            xMLReaderJDOMFactory = XMLReaders.NONVALIDATING;
        }
        this.readerfac = xMLReaderJDOMFactory;
        this.engine = null;
    }

    public SAXHandlerFactory getSAXHandlerFactory() {
        return this.handlerfac;
    }

    public void setSAXHandlerFactory(SAXHandlerFactory sAXHandlerFactory) {
        if (sAXHandlerFactory == null) {
            sAXHandlerFactory = DEFAULTSAXHANDLERFAC;
        }
        this.handlerfac = sAXHandlerFactory;
        this.engine = null;
    }

    @Deprecated
    public boolean getValidation() {
        return isValidating();
    }

    public boolean isValidating() {
        return this.readerfac.isValidating();
    }

    @Deprecated
    public void setValidation(boolean z) {
        setXMLReaderFactory(z ? XMLReaders.DTDVALIDATING : XMLReaders.NONVALIDATING);
    }

    public ErrorHandler getErrorHandler() {
        return this.saxErrorHandler;
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.saxErrorHandler = errorHandler;
        this.engine = null;
    }

    public EntityResolver getEntityResolver() {
        return this.saxEntityResolver;
    }

    public void setEntityResolver(EntityResolver entityResolver) {
        this.saxEntityResolver = entityResolver;
        this.engine = null;
    }

    public DTDHandler getDTDHandler() {
        return this.saxDTDHandler;
    }

    public void setDTDHandler(DTDHandler dTDHandler) {
        this.saxDTDHandler = dTDHandler;
        this.engine = null;
    }

    public XMLFilter getXMLFilter() {
        return this.saxXMLFilter;
    }

    public void setXMLFilter(XMLFilter xMLFilter) {
        this.saxXMLFilter = xMLFilter;
        this.engine = null;
    }

    public boolean getIgnoringElementContentWhitespace() {
        return this.ignoringWhite;
    }

    public void setIgnoringElementContentWhitespace(boolean z) {
        this.ignoringWhite = z;
        this.engine = null;
    }

    public boolean getIgnoringBoundaryWhitespace() {
        return this.ignoringBoundaryWhite;
    }

    public void setIgnoringBoundaryWhitespace(boolean z) {
        this.ignoringBoundaryWhite = z;
        this.engine = null;
    }

    public boolean getExpandEntities() {
        return this.expand;
    }

    public void setExpandEntities(boolean z) {
        this.expand = z;
        this.engine = null;
    }

    public boolean getReuseParser() {
        return this.reuseParser;
    }

    public void setReuseParser(boolean z) {
        this.reuseParser = z;
        if (!z) {
            this.engine = null;
        }
    }

    public void setFeature(String str, boolean z) {
        this.features.put(str, z ? Boolean.TRUE : Boolean.FALSE);
        this.engine = null;
    }

    public void setProperty(String str, Object obj) {
        this.properties.put(str, obj);
        this.engine = null;
    }

    public SAXEngine buildEngine() throws JDOMException {
        SAXHandler createSAXHandler = this.handlerfac.createSAXHandler(this.jdomfac);
        createSAXHandler.setExpandEntities(this.expand);
        createSAXHandler.setIgnoringElementContentWhitespace(this.ignoringWhite);
        createSAXHandler.setIgnoringBoundaryWhitespace(this.ignoringBoundaryWhite);
        XMLReader createParser = createParser();
        configureParser(createParser, createSAXHandler);
        return new SAXBuilderEngine(createParser, createSAXHandler, this.readerfac.isValidating());
    }

    /* access modifiers changed from: protected */
    public XMLReader createParser() throws JDOMException {
        XMLReader createXMLReader = this.readerfac.createXMLReader();
        XMLFilter xMLFilter = this.saxXMLFilter;
        if (xMLFilter == null) {
            return createXMLReader;
        }
        while (xMLFilter.getParent() instanceof XMLFilter) {
            xMLFilter = (XMLFilter) xMLFilter.getParent();
        }
        xMLFilter.setParent(createXMLReader);
        return this.saxXMLFilter;
    }

    private SAXEngine getEngine() throws JDOMException {
        SAXEngine sAXEngine = this.engine;
        if (sAXEngine != null) {
            return sAXEngine;
        }
        SAXEngine buildEngine = buildEngine();
        this.engine = buildEngine;
        return buildEngine;
    }

    /* access modifiers changed from: protected */
    public void configureParser(XMLReader xMLReader, SAXHandler sAXHandler) throws JDOMException {
        xMLReader.setContentHandler(sAXHandler);
        EntityResolver entityResolver = this.saxEntityResolver;
        if (entityResolver != null) {
            xMLReader.setEntityResolver(entityResolver);
        }
        DTDHandler dTDHandler = this.saxDTDHandler;
        if (dTDHandler != null) {
            xMLReader.setDTDHandler(dTDHandler);
        } else {
            xMLReader.setDTDHandler(sAXHandler);
        }
        ErrorHandler errorHandler = this.saxErrorHandler;
        if (errorHandler != null) {
            xMLReader.setErrorHandler(errorHandler);
        } else {
            xMLReader.setErrorHandler(new BuilderErrorHandler());
        }
        boolean z = false;
        try {
            xMLReader.setProperty(JDOMConstants.SAX_PROPERTY_LEXICAL_HANDLER, sAXHandler);
            z = true;
        } catch (SAXNotRecognizedException | SAXNotSupportedException unused) {
        }
        if (!z) {
            try {
                xMLReader.setProperty(JDOMConstants.SAX_PROPERTY_LEXICAL_HANDLER_ALT, sAXHandler);
            } catch (SAXNotRecognizedException | SAXNotSupportedException unused2) {
            }
        }
        for (Map.Entry next : this.features.entrySet()) {
            internalSetFeature(xMLReader, (String) next.getKey(), ((Boolean) next.getValue()).booleanValue(), (String) next.getKey());
        }
        for (Map.Entry next2 : this.properties.entrySet()) {
            internalSetProperty(xMLReader, (String) next2.getKey(), next2.getValue(), (String) next2.getKey());
        }
        try {
            if (xMLReader.getFeature(JDOMConstants.SAX_FEATURE_EXTERNAL_ENT) != this.expand) {
                xMLReader.setFeature(JDOMConstants.SAX_FEATURE_EXTERNAL_ENT, this.expand);
            }
        } catch (SAXException unused3) {
        }
        if (!this.expand) {
            try {
                xMLReader.setProperty(JDOMConstants.SAX_PROPERTY_DECLARATION_HANDLER, sAXHandler);
            } catch (SAXNotRecognizedException | SAXNotSupportedException unused4) {
            }
        }
    }

    private void internalSetFeature(XMLReader xMLReader, String str, boolean z, String str2) throws JDOMException {
        try {
            xMLReader.setFeature(str, z);
        } catch (SAXNotSupportedException unused) {
            throw new JDOMException(str2 + " feature not supported for SAX driver " + xMLReader.getClass().getName());
        } catch (SAXNotRecognizedException unused2) {
            throw new JDOMException(str2 + " feature not recognized for SAX driver " + xMLReader.getClass().getName());
        }
    }

    private void internalSetProperty(XMLReader xMLReader, String str, Object obj, String str2) throws JDOMException {
        try {
            xMLReader.setProperty(str, obj);
        } catch (SAXNotSupportedException unused) {
            throw new JDOMException(str2 + " property not supported for SAX driver " + xMLReader.getClass().getName());
        } catch (SAXNotRecognizedException unused2) {
            throw new JDOMException(str2 + " property not recognized for SAX driver " + xMLReader.getClass().getName());
        }
    }

    public Document build(InputSource inputSource) throws JDOMException, IOException {
        try {
            return getEngine().build(inputSource);
        } finally {
            if (!this.reuseParser) {
                this.engine = null;
            }
        }
    }

    public Document build(InputStream inputStream) throws JDOMException, IOException {
        try {
            return getEngine().build(inputStream);
        } finally {
            if (!this.reuseParser) {
                this.engine = null;
            }
        }
    }

    public Document build(File file) throws JDOMException, IOException {
        try {
            return getEngine().build(file);
        } finally {
            if (!this.reuseParser) {
                this.engine = null;
            }
        }
    }

    public Document build(URL url) throws JDOMException, IOException {
        try {
            return getEngine().build(url);
        } finally {
            if (!this.reuseParser) {
                this.engine = null;
            }
        }
    }

    public Document build(InputStream inputStream, String str) throws JDOMException, IOException {
        try {
            return getEngine().build(inputStream, str);
        } finally {
            if (!this.reuseParser) {
                this.engine = null;
            }
        }
    }

    public Document build(Reader reader) throws JDOMException, IOException {
        try {
            return getEngine().build(reader);
        } finally {
            if (!this.reuseParser) {
                this.engine = null;
            }
        }
    }

    public Document build(Reader reader, String str) throws JDOMException, IOException {
        try {
            return getEngine().build(reader, str);
        } finally {
            if (!this.reuseParser) {
                this.engine = null;
            }
        }
    }

    public Document build(String str) throws JDOMException, IOException {
        if (str != null) {
            try {
                Document build = getEngine().build(str);
                if (!this.reuseParser) {
                    this.engine = null;
                }
                return build;
            } catch (IOException e) {
                int length = str.length();
                int i = 0;
                while (i < length && Verifier.isXMLWhitespace(str.charAt(i))) {
                    i++;
                }
                if (i >= length || '<' != str.charAt(i)) {
                    throw e;
                }
                MalformedURLException malformedURLException = new MalformedURLException("SAXBuilder.build(String) expects the String to be a systemID, but in this instance it appears to be actual XML data.");
                malformedURLException.initCause(e);
                throw malformedURLException;
            } catch (Throwable th) {
                if (!this.reuseParser) {
                    this.engine = null;
                }
                throw th;
            }
        } else {
            throw new NullPointerException("Unable to build a URI from a null systemID.");
        }
    }
}
