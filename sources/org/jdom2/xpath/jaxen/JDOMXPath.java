package org.jdom2.xpath.jaxen;

import java.util.ArrayList;
import java.util.List;
import org.jaxen.BaseXPath;
import org.jaxen.JaxenException;
import org.jaxen.SimpleVariableContext;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.xpath.XPath;

@Deprecated
public class JDOMXPath extends XPath {
    private static final long serialVersionUID = 200;
    private final JDOMNavigator navigator = new JDOMNavigator();
    private transient org.jaxen.XPath xPath;

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

    public JDOMXPath(String str) throws JDOMException {
        setXPath(str);
    }

    public List<?> selectNodes(Object obj) throws JDOMException {
        try {
            this.navigator.setContext(obj);
            List<Object> unWrap = unWrap(this.xPath.selectNodes(obj));
            this.navigator.reset();
            return unWrap;
        } catch (JaxenException e) {
            throw new JDOMException("XPath error while evaluating \"" + this.xPath.toString() + "\": " + e.getMessage(), e);
        } catch (Throwable th) {
            this.navigator.reset();
            throw th;
        }
    }

    public Object selectSingleNode(Object obj) throws JDOMException {
        try {
            this.navigator.setContext(obj);
            Object unWrapNS = unWrapNS(this.xPath.selectSingleNode(obj));
            this.navigator.reset();
            return unWrapNS;
        } catch (JaxenException e) {
            throw new JDOMException("XPath error while evaluating \"" + this.xPath.toString() + "\": " + e.getMessage(), e);
        } catch (Throwable th) {
            this.navigator.reset();
            throw th;
        }
    }

    public String valueOf(Object obj) throws JDOMException {
        try {
            this.navigator.setContext(obj);
            String stringValueOf = this.xPath.stringValueOf(obj);
            this.navigator.reset();
            return stringValueOf;
        } catch (JaxenException e) {
            throw new JDOMException("XPath error while evaluating \"" + this.xPath.toString() + "\": " + e.getMessage(), e);
        } catch (Throwable th) {
            this.navigator.reset();
            throw th;
        }
    }

    public Number numberValueOf(Object obj) throws JDOMException {
        try {
            this.navigator.setContext(obj);
            Number numberValueOf = this.xPath.numberValueOf(obj);
            this.navigator.reset();
            return numberValueOf;
        } catch (JaxenException e) {
            throw new JDOMException("XPath error while evaluating \"" + this.xPath.toString() + "\": " + e.getMessage(), e);
        } catch (Throwable th) {
            this.navigator.reset();
            throw th;
        }
    }

    public void setVariable(String str, Object obj) throws IllegalArgumentException {
        SimpleVariableContext variableContext = this.xPath.getVariableContext();
        if (variableContext instanceof SimpleVariableContext) {
            variableContext.setVariableValue((String) null, str, obj);
        }
    }

    public void addNamespace(Namespace namespace) {
        this.navigator.includeNamespace(namespace);
    }

    public String getXPath() {
        return this.xPath.toString();
    }

    private void setXPath(String str) throws JDOMException {
        try {
            BaseXPath baseXPath = new BaseXPath(str, this.navigator);
            this.xPath = baseXPath;
            baseXPath.setNamespaceContext(this.navigator);
        } catch (Exception e) {
            throw new JDOMException("Invalid XPath expression: \"" + str + "\"", e);
        }
    }

    public String toString() {
        return String.format("[XPath: %s]", new Object[]{this.xPath.toString()});
    }
}
