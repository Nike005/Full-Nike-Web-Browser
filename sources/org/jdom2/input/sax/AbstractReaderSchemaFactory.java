package org.jdom2.input.sax;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import org.jdom2.JDOMException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public abstract class AbstractReaderSchemaFactory implements XMLReaderJDOMFactory {
    private final SAXParserFactory saxfac;

    public boolean isValidating() {
        return true;
    }

    public AbstractReaderSchemaFactory(SAXParserFactory sAXParserFactory, Schema schema) {
        if (schema != null) {
            this.saxfac = sAXParserFactory;
            if (sAXParserFactory != null) {
                sAXParserFactory.setNamespaceAware(true);
                this.saxfac.setValidating(false);
                this.saxfac.setSchema(schema);
                return;
            }
            return;
        }
        throw new NullPointerException("Cannot create a SchemaXMLReaderFactory with a null schema");
    }

    public XMLReader createXMLReader() throws JDOMException {
        SAXParserFactory sAXParserFactory = this.saxfac;
        if (sAXParserFactory != null) {
            try {
                return sAXParserFactory.newSAXParser().getXMLReader();
            } catch (SAXException e) {
                throw new JDOMException("Could not create a new Schema-Validating XMLReader.", e);
            } catch (ParserConfigurationException e2) {
                throw new JDOMException("Could not create a new Schema-Validating XMLReader.", e2);
            }
        } else {
            throw new JDOMException("It was not possible to configure a suitable XMLReader to support " + this);
        }
    }
}
