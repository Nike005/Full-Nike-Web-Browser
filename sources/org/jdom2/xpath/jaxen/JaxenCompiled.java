package org.jdom2.xpath.jaxen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jaxen.BaseXPath;
import org.jaxen.JaxenException;
import org.jaxen.NamespaceContext;
import org.jaxen.UnresolvableException;
import org.jaxen.VariableContext;
import org.jaxen.XPath;
import org.jdom2.Namespace;
import org.jdom2.filter.Filter;
import org.jdom2.xpath.util.AbstractXPathCompiled;

class JaxenCompiled<T> extends AbstractXPathCompiled<T> implements NamespaceContext, VariableContext {
    private final JDOM2Navigator navigator = new JDOM2Navigator();
    private final XPath xPath;

    private static final Object unWrapNS(Object obj) {
        return obj instanceof NamespaceContainer ? ((NamespaceContainer) obj).getNamespace() : obj;
    }

    private static final List<Object> unWrap(List<?> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (Object unWrapNS : list) {
            arrayList.add(unWrapNS(unWrapNS));
        }
        return arrayList;
    }

    public JaxenCompiled(String str, Filter<T> filter, Map<String, Object> map, Namespace[] namespaceArr) {
        super(str, filter, map, namespaceArr);
        try {
            BaseXPath baseXPath = new BaseXPath(str, this.navigator);
            this.xPath = baseXPath;
            baseXPath.setNamespaceContext(this);
            this.xPath.setVariableContext(this);
        } catch (JaxenException e) {
            throw new IllegalArgumentException("Unable to compile '" + str + "'. See Cause.", e);
        }
    }

    public String translateNamespacePrefixToUri(String str) {
        return getNamespace(str).getURI();
    }

    public Object getVariableValue(String str, String str2, String str3) throws UnresolvableException {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        try {
            if ("".equals(str)) {
                str = getNamespace(str2).getURI();
            }
            return getVariable(str3, Namespace.getNamespace(str));
        } catch (IllegalArgumentException unused) {
            throw new UnresolvableException("Unable to resolve variable " + str3 + " in namespace '" + str + "' to a vaulue.");
        }
    }

    /* access modifiers changed from: protected */
    public List<?> evaluateRawAll(Object obj) {
        try {
            return unWrap(this.xPath.selectNodes(obj));
        } catch (JaxenException e) {
            throw new IllegalStateException("Unable to evaluate expression. See cause", e);
        }
    }

    /* access modifiers changed from: protected */
    public Object evaluateRawFirst(Object obj) {
        try {
            return unWrapNS(this.xPath.selectSingleNode(obj));
        } catch (JaxenException e) {
            throw new IllegalStateException("Unable to evaluate expression. See cause", e);
        }
    }
}
