package org.jdom2.xpath.jaxen;

import org.jdom2.Element;
import org.jdom2.Namespace;

final class NamespaceContainer {
    private final Element emt;

    /* renamed from: ns */
    private final Namespace f3965ns;

    public NamespaceContainer(Namespace namespace, Element element) {
        this.f3965ns = namespace;
        this.emt = element;
    }

    public Namespace getNamespace() {
        return this.f3965ns;
    }

    public Element getParentElement() {
        return this.emt;
    }

    public String toString() {
        return this.f3965ns.getPrefix() + "=" + this.f3965ns.getURI();
    }
}
