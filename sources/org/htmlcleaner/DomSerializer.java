package org.htmlcleaner;

import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DomSerializer {
    protected boolean escapeXml;
    protected CleanerProperties props;

    public DomSerializer(CleanerProperties cleanerProperties, boolean z) {
        this.escapeXml = true;
        this.props = cleanerProperties;
        this.escapeXml = z;
    }

    public DomSerializer(CleanerProperties cleanerProperties) {
        this(cleanerProperties, true);
    }

    public Document createDOM(TagNode tagNode) throws ParserConfigurationException {
        Document document;
        DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        DOMImplementation dOMImplementation = newDocumentBuilder.getDOMImplementation();
        if (tagNode.getDocType() != null) {
            String part1 = tagNode.getDocType().getPart1();
            document = dOMImplementation.createDocument(tagNode.getNamespaceURIOnPath(""), part1, dOMImplementation.createDocumentType(part1, tagNode.getDocType().getPublicId(), tagNode.getDocType().getSystemId()));
        } else {
            document = newDocumentBuilder.newDocument();
            document.appendChild(document.createElement(tagNode.getName()));
        }
        createSubnodes(document, document.getDocumentElement(), tagNode.getAllChildren());
        return document;
    }

    /* access modifiers changed from: protected */
    public boolean isScriptOrStyle(Element element) {
        String nodeName = element.getNodeName();
        return "script".equalsIgnoreCase(nodeName) || "style".equalsIgnoreCase(nodeName);
    }

    /* access modifiers changed from: protected */
    public boolean dontEscape(Element element) {
        return this.props.isUseCdataForScriptAndStyle() && isScriptOrStyle(element) && !element.hasChildNodes();
    }

    private void createSubnodes(Document document, Element element, List list) {
        if (list != null) {
            for (Object next : list) {
                if (next instanceof CommentNode) {
                    element.appendChild(document.createComment(((CommentNode) next).getContent()));
                } else if (next instanceof ContentNode) {
                    String content = ((ContentNode) next).getContent();
                    boolean dontEscape = dontEscape(element);
                    if (this.escapeXml && !dontEscape) {
                        content = C2401Utils.escapeXml(content, this.props, true);
                    }
                    element.appendChild(dontEscape ? document.createCDATASection(content) : document.createTextNode(content));
                } else if (next instanceof TagNode) {
                    TagNode tagNode = (TagNode) next;
                    Element createElement = document.createElement(tagNode.getName());
                    for (Map.Entry next2 : tagNode.getAttributes().entrySet()) {
                        String str = (String) next2.getKey();
                        String str2 = (String) next2.getValue();
                        if (this.escapeXml) {
                            str2 = C2401Utils.escapeXml(str2, this.props, true);
                        }
                        createElement.setAttribute(str, str2);
                    }
                    createSubnodes(document, createElement, tagNode.getAllChildren());
                    element.appendChild(createElement);
                } else if (next instanceof List) {
                    createSubnodes(document, element, (List) next);
                }
            }
        }
    }
}
