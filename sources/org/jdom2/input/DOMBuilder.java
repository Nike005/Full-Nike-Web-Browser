package org.jdom2.input;

import java.util.HashMap;
import java.util.Iterator;
import org.jdom2.CDATA;
import org.jdom2.Comment;
import org.jdom2.DefaultJDOMFactory;
import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.EntityRef;
import org.jdom2.JDOMFactory;
import org.jdom2.Namespace;
import org.jdom2.ProcessingInstruction;
import org.jdom2.Text;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.DocumentType;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMBuilder {
    private JDOMFactory factory = new DefaultJDOMFactory();

    public void setFactory(JDOMFactory jDOMFactory) {
        this.factory = jDOMFactory;
    }

    public JDOMFactory getFactory() {
        return this.factory;
    }

    public Document build(org.w3c.dom.Document document) {
        Document document2 = this.factory.document((Element) null);
        buildTree(document, document2, (Element) null, true);
        return document2;
    }

    public Element build(org.w3c.dom.Element element) {
        Document document = this.factory.document((Element) null);
        buildTree(element, document, (Element) null, true);
        return document.getRootElement();
    }

    public CDATA build(CDATASection cDATASection) {
        return this.factory.cdata(cDATASection.getNodeValue());
    }

    public Text build(org.w3c.dom.Text text) {
        return this.factory.text(text.getNodeValue());
    }

    public Comment build(org.w3c.dom.Comment comment) {
        return this.factory.comment(comment.getNodeValue());
    }

    public ProcessingInstruction build(org.w3c.dom.ProcessingInstruction processingInstruction) {
        return this.factory.processingInstruction(processingInstruction.getTarget(), processingInstruction.getData());
    }

    public EntityRef build(EntityReference entityReference) {
        return this.factory.entityRef(entityReference.getNodeName());
    }

    public DocType build(DocumentType documentType) {
        String publicId = documentType.getPublicId();
        String systemId = documentType.getSystemId();
        String internalSubset = documentType.getInternalSubset();
        DocType docType = this.factory.docType(documentType.getName());
        docType.setPublicID(publicId);
        docType.setSystemID(systemId);
        docType.setInternalSubset(internalSubset);
        return docType;
    }

    private void buildTree(Node node, Document document, Element element, boolean z) {
        String str;
        Namespace namespace;
        String str2;
        Namespace namespace2;
        Namespace namespace3;
        String str3;
        Document document2 = document;
        Element element2 = element;
        switch (node.getNodeType()) {
            case 1:
                String nodeName = node.getNodeName();
                int i = 58;
                int indexOf = nodeName.indexOf(58);
                if (indexOf >= 0) {
                    str = nodeName.substring(0, indexOf);
                    nodeName = nodeName.substring(indexOf + 1);
                } else {
                    str = "";
                }
                String namespaceURI = node.getNamespaceURI();
                if (namespaceURI == null) {
                    namespace = element2 == null ? Namespace.NO_NAMESPACE : element2.getNamespace(str);
                } else {
                    namespace = Namespace.getNamespace(str, namespaceURI);
                }
                Element element3 = this.factory.element(nodeName, namespace);
                if (z) {
                    this.factory.setRoot(document2, element3);
                } else {
                    this.factory.addContent(element2, element3);
                }
                NamedNodeMap attributes = node.getAttributes();
                int length = attributes.getLength();
                for (int i2 = 0; i2 < length; i2++) {
                    Attr attr = (Attr) attributes.item(i2);
                    String name = attr.getName();
                    if (name.startsWith("xmlns")) {
                        int indexOf2 = name.indexOf(58);
                        if (indexOf2 >= 0) {
                            str3 = name.substring(indexOf2 + 1);
                        } else {
                            str3 = "";
                        }
                        Namespace namespace4 = Namespace.getNamespace(str3, attr.getValue());
                        if (str.equals(str3)) {
                            element3.setNamespace(namespace4);
                        } else {
                            this.factory.addNamespaceDeclaration(element3, namespace4);
                        }
                    }
                }
                int i3 = 0;
                while (i3 < length) {
                    Attr attr2 = (Attr) attributes.item(i3);
                    String name2 = attr2.getName();
                    if (!name2.startsWith("xmlns")) {
                        int indexOf3 = name2.indexOf(i);
                        if (indexOf3 >= 0) {
                            str2 = name2.substring(0, indexOf3);
                            name2 = name2.substring(indexOf3 + 1);
                        } else {
                            str2 = "";
                        }
                        String value = attr2.getValue();
                        String namespaceURI2 = attr2.getNamespaceURI();
                        if (namespaceURI2 == null || "".equals(namespaceURI2)) {
                            namespace2 = Namespace.NO_NAMESPACE;
                        } else if (str2.length() > 0) {
                            namespace2 = Namespace.getNamespace(str2, namespaceURI2);
                        } else {
                            HashMap hashMap = new HashMap();
                            Iterator<Namespace> it = element3.getNamespacesInScope().iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    Namespace next = it.next();
                                    if (next.getPrefix().length() <= 0 || !next.getURI().equals(namespaceURI2)) {
                                        hashMap.put(next.getPrefix(), next);
                                    } else {
                                        namespace3 = next;
                                    }
                                } else {
                                    namespace3 = null;
                                }
                            }
                            if (namespace3 == null) {
                                String str4 = "attns" + 0;
                                int i4 = 0;
                                while (hashMap.containsKey(str4)) {
                                    int i5 = i4 + 1;
                                    i4 = i5;
                                    str4 = "attns" + i5;
                                }
                                namespace2 = Namespace.getNamespace(str4, namespaceURI2);
                            } else {
                                namespace2 = namespace3;
                            }
                        }
                        this.factory.setAttribute(element3, this.factory.attribute(name2, value, namespace2));
                    }
                    i3++;
                    i = 58;
                }
                NodeList childNodes = node.getChildNodes();
                if (childNodes != null) {
                    int length2 = childNodes.getLength();
                    for (int i6 = 0; i6 < length2; i6++) {
                        Node item = childNodes.item(i6);
                        if (item != null) {
                            buildTree(item, document2, element3, false);
                        }
                    }
                    return;
                }
                return;
            case 3:
                this.factory.addContent(element2, build((org.w3c.dom.Text) node));
                return;
            case 4:
                this.factory.addContent(element2, build((CDATASection) node));
                return;
            case 5:
                this.factory.addContent(element2, build((EntityReference) node));
                return;
            case 7:
                if (z) {
                    this.factory.addContent(document2, build((org.w3c.dom.ProcessingInstruction) node));
                    return;
                } else {
                    this.factory.addContent(element2, build((org.w3c.dom.ProcessingInstruction) node));
                    return;
                }
            case 8:
                if (z) {
                    this.factory.addContent(document2, build((org.w3c.dom.Comment) node));
                    return;
                } else {
                    this.factory.addContent(element2, build((org.w3c.dom.Comment) node));
                    return;
                }
            case 9:
                NodeList childNodes2 = node.getChildNodes();
                int length3 = childNodes2.getLength();
                for (int i7 = 0; i7 < length3; i7++) {
                    buildTree(childNodes2.item(i7), document2, element2, true);
                }
                return;
            case 10:
                this.factory.addContent(document2, build((DocumentType) node));
                return;
            default:
                return;
        }
    }
}
