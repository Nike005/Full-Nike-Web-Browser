package org.htmlcleaner;

import java.util.List;
import java.util.Map;
import org.jdom2.Content;
import org.jdom2.DefaultJDOMFactory;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMConstants;
import org.jdom2.Namespace;

public class JDomSerializer {
    protected boolean escapeXml;
    private DefaultJDOMFactory factory;
    protected CleanerProperties props;

    public JDomSerializer(CleanerProperties cleanerProperties, boolean z) {
        this.escapeXml = true;
        this.props = cleanerProperties;
        this.escapeXml = z;
    }

    public JDomSerializer(CleanerProperties cleanerProperties) {
        this(cleanerProperties, true);
    }

    public Document createJDom(TagNode tagNode) {
        this.factory = new DefaultJDOMFactory();
        Element createElement = createElement(tagNode);
        Document document = this.factory.document(createElement);
        setAttributes(tagNode, createElement);
        createSubnodes(createElement, tagNode.getAllChildren());
        return document;
    }

    private Element createElement(TagNode tagNode) {
        Element element;
        String name = tagNode.getName();
        boolean isNamespacesAware = this.props.isNamespacesAware();
        String xmlNSPrefix = C2401Utils.getXmlNSPrefix(name);
        Map<String, String> namespaceDeclarations = tagNode.getNamespaceDeclarations();
        String str = null;
        if (xmlNSPrefix != null) {
            name = C2401Utils.getXmlName(name);
            if (isNamespacesAware) {
                if (namespaceDeclarations != null) {
                    str = namespaceDeclarations.get(xmlNSPrefix);
                }
                if (str == null) {
                    str = tagNode.getNamespaceURIOnPath(xmlNSPrefix);
                }
                if (str == null) {
                    str = xmlNSPrefix;
                }
            }
        } else if (isNamespacesAware) {
            if (namespaceDeclarations != null) {
                str = namespaceDeclarations.get("");
            }
            if (str == null) {
                str = tagNode.getNamespaceURIOnPath(xmlNSPrefix);
            }
        }
        if (!isNamespacesAware || str == null) {
            element = this.factory.element(name);
        } else {
            element = this.factory.element(name, xmlNSPrefix == null ? Namespace.getNamespace(str) : Namespace.getNamespace(xmlNSPrefix, str));
        }
        if (isNamespacesAware) {
            defineNamespaceDeclarations(tagNode, element);
        }
        return element;
    }

    private void defineNamespaceDeclarations(TagNode tagNode, Element element) {
        Map<String, String> namespaceDeclarations = tagNode.getNamespaceDeclarations();
        if (namespaceDeclarations != null) {
            for (Map.Entry next : namespaceDeclarations.entrySet()) {
                String str = (String) next.getKey();
                String str2 = (String) next.getValue();
                element.addNamespaceDeclaration((str == null || "".equals(str)) ? Namespace.getNamespace(str2) : Namespace.getNamespace(str, str2));
            }
        }
    }

    private void setAttributes(TagNode tagNode, Element element) {
        for (Map.Entry next : tagNode.getAttributes().entrySet()) {
            String str = (String) next.getKey();
            String str2 = (String) next.getValue();
            if (this.escapeXml) {
                str2 = C2401Utils.escapeXml(str2, this.props, true);
            }
            String xmlNSPrefix = C2401Utils.getXmlNSPrefix(str);
            Namespace namespace = null;
            if (xmlNSPrefix != null) {
                str = C2401Utils.getXmlName(str);
                if (this.props.isNamespacesAware()) {
                    String namespaceURIOnPath = tagNode.getNamespaceURIOnPath(xmlNSPrefix);
                    if (namespaceURIOnPath == null) {
                        namespaceURIOnPath = xmlNSPrefix;
                    }
                    if (!xmlNSPrefix.startsWith(JDOMConstants.NS_PREFIX_XML)) {
                        namespace = Namespace.getNamespace(xmlNSPrefix, namespaceURIOnPath);
                    }
                }
            }
            if (namespace == null) {
                element.setAttribute(str, str2);
            } else {
                element.setAttribute(str, str2, namespace);
            }
        }
    }

    private void createSubnodes(Element element, List list) {
        if (list != null) {
            for (Object next : list) {
                if (next instanceof CommentNode) {
                    element.addContent((Content) this.factory.comment(((CommentNode) next).getContent().toString()));
                } else if (next instanceof ContentNode) {
                    String name = element.getName();
                    String obj = next.toString();
                    boolean z = this.props.isUseCdataForScriptAndStyle() && ("script".equalsIgnoreCase(name) || "style".equalsIgnoreCase(name));
                    if (this.escapeXml && !z) {
                        obj = C2401Utils.escapeXml(obj, this.props, true);
                    }
                    element.addContent(z ? this.factory.cdata(obj) : this.factory.text(obj));
                } else if (next instanceof TagNode) {
                    TagNode tagNode = (TagNode) next;
                    Element createElement = createElement(tagNode);
                    setAttributes(tagNode, createElement);
                    createSubnodes(createElement, tagNode.getAllChildren());
                    element.addContent((Content) createElement);
                } else if (next instanceof List) {
                    createSubnodes(element, (List) next);
                }
            }
        }
    }
}
