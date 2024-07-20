package org.jdom2.xpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Attribute;
import org.jdom2.Comment;
import org.jdom2.Content;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.NamespaceAware;
import org.jdom2.Parent;
import org.jdom2.ProcessingInstruction;
import org.jdom2.Text;
import org.jdom2.filter.Filters;

public final class XPathHelper {
    private XPathHelper() {
    }

    private static StringBuilder getPositionPath(Object obj, List<?> list, String str, StringBuilder sb) {
        sb.append(str);
        if (list != null) {
            int i = 0;
            Iterator<?> it = list.iterator();
            while (it.hasNext()) {
                i++;
                if (it.next() == obj) {
                    break;
                }
            }
            if (i > 1 || it.hasNext()) {
                sb.append('[');
                sb.append(i);
                sb.append(']');
            }
        }
        return sb;
    }

    private static final StringBuilder getSingleStep(NamespaceAware namespaceAware, StringBuilder sb) {
        if (namespaceAware instanceof Content) {
            Content content = (Content) namespaceAware;
            Parent parent = content.getParent();
            List<Element> list = null;
            if (content instanceof Text) {
                if (parent != null) {
                    list = parent.getContent(Filters.text());
                }
                return getPositionPath(content, list, "text()", sb);
            } else if (content instanceof Comment) {
                if (parent != null) {
                    list = parent.getContent(Filters.comment());
                }
                return getPositionPath(content, list, "comment()", sb);
            } else if (content instanceof ProcessingInstruction) {
                if (parent != null) {
                    list = parent.getContent(Filters.processinginstruction());
                }
                return getPositionPath(content, list, "processing-instruction()", sb);
            } else {
                boolean z = content instanceof Element;
                if (z) {
                    Element element = (Element) content;
                    if (element.getNamespace() == Namespace.NO_NAMESPACE) {
                        String name = element.getName();
                        if (parent instanceof Element) {
                            list = ((Element) parent).getChildren(name);
                        }
                        return getPositionPath(content, list, name, sb);
                    }
                }
                if (z) {
                    Element element2 = (Element) content;
                    if (parent instanceof Element) {
                        list = ((Element) parent).getChildren(element2.getName(), element2.getNamespace());
                    }
                    return getPositionPath(content, list, "*[local-name() = '" + element2.getName() + "' and namespace-uri() = '" + element2.getNamespaceURI() + "']", sb);
                }
                return getPositionPath(content, parent == null ? Collections.singletonList(namespaceAware) : parent.getContent(), "node()", sb);
            }
        } else {
            if (namespaceAware instanceof Attribute) {
                Attribute attribute = (Attribute) namespaceAware;
                if (attribute.getNamespace() == Namespace.NO_NAMESPACE) {
                    sb.append("@");
                    sb.append(attribute.getName());
                } else {
                    sb.append("@*[local-name() = '");
                    sb.append(attribute.getName());
                    sb.append("' and namespace-uri() = '");
                    sb.append(attribute.getNamespaceURI());
                    sb.append("']");
                }
            }
            return sb;
        }
    }

    private static StringBuilder getRelativeElementPath(Element element, Parent parent, StringBuilder sb) {
        if (element == parent) {
            sb.append(".");
            return sb;
        }
        ArrayList arrayList = new ArrayList();
        while (parent != null && parent != element) {
            arrayList.add(parent);
            parent = parent.getParent();
        }
        int size = arrayList.size();
        if (parent != element) {
            int i = 0;
            Parent parent2 = element;
            while (parent2 != null) {
                size = locate(parent2, arrayList);
                if (size >= 0) {
                    break;
                }
                i++;
                parent2 = parent2.getParent();
            }
            if (parent2 != null) {
                while (true) {
                    i--;
                    if (i < 0) {
                        break;
                    }
                    sb.append("../");
                }
            } else {
                throw new IllegalArgumentException("The 'from' and 'to' Element have no common ancestor.");
            }
        }
        while (true) {
            size--;
            if (size >= 0) {
                getSingleStep((NamespaceAware) arrayList.get(size), sb);
                sb.append("/");
            } else {
                sb.setLength(sb.length() - 1);
                return sb;
            }
        }
    }

    private static int locate(Parent parent, List<Parent> list) {
        int size = list.size();
        do {
            size--;
            if (size < 0) {
                return -1;
            }
        } while (parent != list.get(size));
        return size;
    }

    public static String getRelativePath(Content content, Content content2) {
        if (content == null) {
            throw new NullPointerException("Cannot create a path from a null target");
        } else if (content2 != null) {
            StringBuilder sb = new StringBuilder();
            if (content == content2) {
                return ".";
            }
            Element parentElement = content instanceof Element ? (Element) content : content.getParentElement();
            if (content != parentElement) {
                sb.append("../");
            }
            if (content2 instanceof Element) {
                getRelativeElementPath(parentElement, (Element) content2, sb);
            } else {
                Parent parent = content2.getParent();
                if (parent != null) {
                    getRelativeElementPath(parentElement, parent, sb);
                    sb.append("/");
                    getSingleStep(content2, sb);
                } else {
                    throw new IllegalArgumentException("Cannot get a relative XPath to detached content.");
                }
            }
            return sb.toString();
        } else {
            throw new NullPointerException("Cannot create a path to a null target");
        }
    }

    public static String getRelativePath(Content content, Attribute attribute) {
        if (content == null) {
            throw new NullPointerException("Cannot create a path from a null Content");
        } else if (attribute != null) {
            Element parent = attribute.getParent();
            if (parent != null) {
                StringBuilder sb = new StringBuilder(getRelativePath(content, (Content) parent));
                sb.append("/");
                getSingleStep(attribute, sb);
                return sb.toString();
            }
            throw new IllegalArgumentException("Cannot create a path to detached Attribute");
        } else {
            throw new NullPointerException("Cannot create a path to a null Attribute");
        }
    }

    public static String getRelativePath(Attribute attribute, Attribute attribute2) {
        if (attribute == null) {
            throw new NullPointerException("Cannot create a path from a null 'from'");
        } else if (attribute2 == null) {
            throw new NullPointerException("Cannot create a path to a null target");
        } else if (attribute == attribute2) {
            return ".";
        } else {
            Element parent = attribute.getParent();
            if (parent != null) {
                return "../" + getRelativePath((Content) parent, attribute2);
            }
            throw new IllegalArgumentException("Cannot create a path from a detached attrbibute");
        }
    }

    public static String getRelativePath(Attribute attribute, Content content) {
        if (attribute == null) {
            throw new NullPointerException("Cannot create a path from a null 'from'");
        } else if (content != null) {
            Element parent = attribute.getParent();
            if (parent == null) {
                throw new IllegalArgumentException("Cannot create a path from a detached attrbibute");
            } else if (parent == content) {
                return "..";
            } else {
                return "../" + getRelativePath((Content) parent, content);
            }
        } else {
            throw new NullPointerException("Cannot create a path to a null target");
        }
    }

    public static String getAbsolutePath(Content content) {
        if (content != null) {
            StringBuilder sb = new StringBuilder();
            Element parentElement = content instanceof Element ? (Element) content : content.getParentElement();
            if (parentElement != null) {
                Element element = parentElement;
                while (element.getParentElement() != null) {
                    element = element.getParentElement();
                }
                sb.append("/");
                getSingleStep(element, sb);
                if (element != parentElement) {
                    sb.append("/");
                    getRelativeElementPath(element, parentElement, sb);
                }
                if (parentElement != content) {
                    sb.append("/");
                    getSingleStep(content, sb);
                }
                return sb.toString();
            } else if (content.getParent() != null) {
                sb.append("/");
                getSingleStep(content, sb);
                return sb.toString();
            } else {
                throw new IllegalArgumentException("Cannot create a path to detached target");
            }
        } else {
            throw new NullPointerException("Cannot create a path to a null target");
        }
    }

    public static String getAbsolutePath(Attribute attribute) {
        if (attribute != null) {
            Element parent = attribute.getParent();
            if (parent != null) {
                Element element = parent;
                while (element.getParentElement() != null) {
                    element = element.getParentElement();
                }
                StringBuilder sb = new StringBuilder();
                sb.append("/");
                getSingleStep(element, sb);
                if (parent != element) {
                    sb.append("/");
                    getRelativeElementPath(element, parent, sb);
                }
                sb.append("/");
                getSingleStep(attribute, sb);
                return sb.toString();
            }
            throw new IllegalArgumentException("Cannot create a path to detached target");
        }
        throw new NullPointerException("Cannot create a path to a null target");
    }
}
