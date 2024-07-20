package org.jdom2.input.sax;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.SchemaFactory;
import org.jdom2.JDOMException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public enum XMLReaders implements XMLReaderJDOMFactory {
    NONVALIDATING(0),
    DTDVALIDATING(1),
    XSDVALIDATING(2);
    
    private final Exception failcause;
    private final SAXParserFactory jaxpfactory;
    private final boolean validates;

    private XMLReaders(int i) {
        SAXParserFactory newInstance = SAXParserFactory.newInstance();
        boolean z = true;
        newInstance.setNamespaceAware(true);
        Exception exc = null;
        if (i != 0) {
            if (i == 1) {
                newInstance.setValidating(true);
            } else if (i == 2) {
                newInstance.setValidating(false);
                try {
                    newInstance.setSchema(SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema").newSchema());
                } catch (IllegalArgumentException | UnsupportedOperationException | SAXException e) {
                    z = false;
                    exc = e;
                    newInstance = null;
                }
            }
            this.jaxpfactory = newInstance;
            this.validates = z;
            this.failcause = exc;
        }
        newInstance.setValidating(false);
        z = false;
        this.jaxpfactory = newInstance;
        this.validates = z;
        this.failcause = exc;
    }

    public XMLReader createXMLReader() throws JDOMException {
        SAXParserFactory sAXParserFactory = this.jaxpfactory;
        if (sAXParserFactory != null) {
            try {
                return sAXParserFactory.newSAXParser().getXMLReader();
            } catch (SAXException e) {
                throw new JDOMException("Unable to create a new XMLReader instance", e);
            } catch (ParserConfigurationException e2) {
                throw new JDOMException("Unable to create a new XMLReader instance", e2);
            }
        } else {
            throw new JDOMException("It was not possible to configure a suitable XMLReader to support " + this, this.failcause);
        }
    }

    public boolean isValidating() {
        return this.validates;
    }
}
