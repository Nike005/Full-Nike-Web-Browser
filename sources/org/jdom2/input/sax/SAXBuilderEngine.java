package org.jdom2.input.sax;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.JDOMFactory;
import org.jdom2.input.JDOMParseException;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

public class SAXBuilderEngine implements SAXEngine {
    private final SAXHandler saxHandler;
    private final XMLReader saxParser;
    private final boolean validating;

    public SAXBuilderEngine(XMLReader xMLReader, SAXHandler sAXHandler, boolean z) {
        this.saxParser = xMLReader;
        this.saxHandler = sAXHandler;
        this.validating = z;
    }

    public JDOMFactory getJDOMFactory() {
        return this.saxHandler.getFactory();
    }

    public boolean isValidating() {
        return this.validating;
    }

    public ErrorHandler getErrorHandler() {
        return this.saxParser.getErrorHandler();
    }

    public EntityResolver getEntityResolver() {
        return this.saxParser.getEntityResolver();
    }

    public DTDHandler getDTDHandler() {
        return this.saxParser.getDTDHandler();
    }

    public boolean getIgnoringElementContentWhitespace() {
        return this.saxHandler.getIgnoringElementContentWhitespace();
    }

    public boolean getIgnoringBoundaryWhitespace() {
        return this.saxHandler.getIgnoringBoundaryWhitespace();
    }

    public boolean getExpandEntities() {
        return this.saxHandler.getExpandEntities();
    }

    public Document build(InputSource inputSource) throws JDOMException, IOException {
        try {
            this.saxParser.parse(inputSource);
            Document document = this.saxHandler.getDocument();
            this.saxHandler.reset();
            return document;
        } catch (SAXParseException e) {
            Document document2 = this.saxHandler.getDocument();
            if (!document2.hasRootElement()) {
                document2 = null;
            }
            String systemId = e.getSystemId();
            if (systemId != null) {
                throw new JDOMParseException("Error on line " + e.getLineNumber() + " of document " + systemId + ": " + e.getMessage(), e, document2);
            }
            throw new JDOMParseException("Error on line " + e.getLineNumber() + ": " + e.getMessage(), e, document2);
        } catch (SAXException e2) {
            throw new JDOMParseException("Error in building: " + e2.getMessage(), e2, this.saxHandler.getDocument());
        } catch (Throwable th) {
            this.saxHandler.reset();
            throw th;
        }
    }

    public Document build(InputStream inputStream) throws JDOMException, IOException {
        return build(new InputSource(inputStream));
    }

    public Document build(File file) throws JDOMException, IOException {
        try {
            return build(fileToURL(file));
        } catch (MalformedURLException e) {
            throw new JDOMException("Error in building", e);
        }
    }

    public Document build(URL url) throws JDOMException, IOException {
        return build(new InputSource(url.toExternalForm()));
    }

    public Document build(InputStream inputStream, String str) throws JDOMException, IOException {
        InputSource inputSource = new InputSource(inputStream);
        inputSource.setSystemId(str);
        return build(inputSource);
    }

    public Document build(Reader reader) throws JDOMException, IOException {
        return build(new InputSource(reader));
    }

    public Document build(Reader reader, String str) throws JDOMException, IOException {
        InputSource inputSource = new InputSource(reader);
        inputSource.setSystemId(str);
        return build(inputSource);
    }

    public Document build(String str) throws JDOMException, IOException {
        return build(new InputSource(str));
    }

    private static URL fileToURL(File file) throws MalformedURLException {
        return file.getAbsoluteFile().toURI().toURL();
    }
}
