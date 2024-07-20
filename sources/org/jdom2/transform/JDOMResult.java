package org.jdom2.transform;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.xml.transform.sax.SAXResult;
import org.jdom2.Content;
import org.jdom2.DefaultJDOMFactory;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMFactory;
import org.jdom2.input.sax.SAXHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.XMLFilterImpl;

public class JDOMResult extends SAXResult {
    public static final String JDOM_FEATURE = "http://jdom.org/jdom2/transform/JDOMResult/feature";
    private JDOMFactory factory = null;
    private boolean queried = false;
    private Document resultdoc = null;
    private List<Content> resultlist = null;

    public void setHandler(ContentHandler contentHandler) {
    }

    public void setLexicalHandler(LexicalHandler lexicalHandler) {
    }

    public JDOMResult() {
        DocumentBuilder documentBuilder = new DocumentBuilder();
        super.setHandler(documentBuilder);
        super.setLexicalHandler(documentBuilder);
    }

    public void setResult(List<Content> list) {
        this.resultlist = list;
        this.queried = false;
    }

    public List<Content> getResult() {
        List<Content> emptyList = Collections.emptyList();
        retrieveResult();
        List<Content> list = this.resultlist;
        if (list == null) {
            Document document = this.resultdoc;
            if (document != null && !this.queried) {
                List<Content> content = document.getContent();
                list = new ArrayList<>(content.size());
                while (content.size() != 0) {
                    list.add(content.remove(0));
                }
                this.resultlist = list;
                this.resultdoc = null;
            }
            this.queried = true;
            return emptyList;
        }
        emptyList = list;
        this.queried = true;
        return emptyList;
    }

    public void setDocument(Document document) {
        this.resultdoc = document;
        this.resultlist = null;
        this.queried = false;
    }

    public Document getDocument() {
        retrieveResult();
        Document document = this.resultdoc;
        if (document == null) {
            if (this.resultlist == null || this.queried) {
                document = null;
            } else {
                try {
                    JDOMFactory factory2 = getFactory();
                    if (factory2 == null) {
                        factory2 = new DefaultJDOMFactory();
                    }
                    document = factory2.document((Element) null);
                    document.setContent((Collection<? extends Content>) this.resultlist);
                    this.resultdoc = document;
                    this.resultlist = null;
                } catch (RuntimeException unused) {
                    return null;
                }
            }
        }
        this.queried = true;
        return document;
    }

    public void setFactory(JDOMFactory jDOMFactory) {
        this.factory = jDOMFactory;
    }

    public JDOMFactory getFactory() {
        return this.factory;
    }

    private void retrieveResult() {
        if (this.resultlist == null && this.resultdoc == null) {
            setResult(((DocumentBuilder) getHandler()).getResult());
        }
    }

    private static class FragmentHandler extends SAXHandler {
        private Element dummyRoot;

        public FragmentHandler(JDOMFactory jDOMFactory) {
            super(jDOMFactory);
            Element element = new Element("root", (String) null, (String) null);
            this.dummyRoot = element;
            pushElement(element);
        }

        public List<Content> getResult() {
            try {
                flushCharacters();
            } catch (SAXException unused) {
            }
            return getDetachedContent(this.dummyRoot);
        }

        private List<Content> getDetachedContent(Element element) {
            List<Content> content = element.getContent();
            ArrayList arrayList = new ArrayList(content.size());
            while (content.size() != 0) {
                arrayList.add(content.remove(0));
            }
            return arrayList;
        }
    }

    private class DocumentBuilder extends XMLFilterImpl implements LexicalHandler {
        private FragmentHandler saxHandler = null;
        private boolean startDocumentReceived = false;

        public DocumentBuilder() {
        }

        public List<Content> getResult() {
            FragmentHandler fragmentHandler = this.saxHandler;
            if (fragmentHandler == null) {
                return null;
            }
            List<Content> result = fragmentHandler.getResult();
            this.saxHandler = null;
            this.startDocumentReceived = false;
            return result;
        }

        private void ensureInitialization() throws SAXException {
            if (!this.startDocumentReceived) {
                startDocument();
            }
        }

        public void startDocument() throws SAXException {
            this.startDocumentReceived = true;
            JDOMResult.this.setResult((List<Content>) null);
            FragmentHandler fragmentHandler = new FragmentHandler(JDOMResult.this.getFactory());
            this.saxHandler = fragmentHandler;
            super.setContentHandler(fragmentHandler);
            super.startDocument();
        }

        public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
            ensureInitialization();
            super.startElement(str, str2, str3, attributes);
        }

        public void startPrefixMapping(String str, String str2) throws SAXException {
            ensureInitialization();
            super.startPrefixMapping(str, str2);
        }

        public void characters(char[] cArr, int i, int i2) throws SAXException {
            ensureInitialization();
            super.characters(cArr, i, i2);
        }

        public void ignorableWhitespace(char[] cArr, int i, int i2) throws SAXException {
            ensureInitialization();
            super.ignorableWhitespace(cArr, i, i2);
        }

        public void processingInstruction(String str, String str2) throws SAXException {
            ensureInitialization();
            super.processingInstruction(str, str2);
        }

        public void skippedEntity(String str) throws SAXException {
            ensureInitialization();
            super.skippedEntity(str);
        }

        public void startDTD(String str, String str2, String str3) throws SAXException {
            ensureInitialization();
            this.saxHandler.startDTD(str, str2, str3);
        }

        public void endDTD() throws SAXException {
            this.saxHandler.endDTD();
        }

        public void startEntity(String str) throws SAXException {
            ensureInitialization();
            this.saxHandler.startEntity(str);
        }

        public void endEntity(String str) throws SAXException {
            this.saxHandler.endEntity(str);
        }

        public void startCDATA() throws SAXException {
            ensureInitialization();
            this.saxHandler.startCDATA();
        }

        public void endCDATA() throws SAXException {
            this.saxHandler.endCDATA();
        }

        public void comment(char[] cArr, int i, int i2) throws SAXException {
            ensureInitialization();
            this.saxHandler.comment(cArr, i, i2);
        }
    }
}
