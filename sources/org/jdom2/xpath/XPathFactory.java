package org.jdom2.xpath;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import org.jdom2.JDOMConstants;
import org.jdom2.Namespace;
import org.jdom2.filter.Filter;
import org.jdom2.filter.Filters;
import org.jdom2.internal.ReflectionConstructor;
import org.jdom2.internal.SystemProperty;
import org.jdom2.xpath.jaxen.JaxenXPathFactory;

public abstract class XPathFactory {
    private static final String DEFAULTFACTORY = SystemProperty.get(JDOMConstants.JDOM2_PROPERTY_XPATH_FACTORY, (String) null);
    private static final Namespace[] EMPTYNS = new Namespace[0];
    private static final AtomicReference<XPathFactory> defaultreference = new AtomicReference<>();

    public abstract <T> XPathExpression<T> compile(String str, Filter<T> filter, Map<String, Object> map, Namespace... namespaceArr);

    public static final XPathFactory instance() {
        XPathFactory xPathFactory = defaultreference.get();
        if (xPathFactory != null) {
            return xPathFactory;
        }
        String str = DEFAULTFACTORY;
        XPathFactory jaxenXPathFactory = str == null ? new JaxenXPathFactory() : newInstance(str);
        if (defaultreference.compareAndSet((Object) null, jaxenXPathFactory)) {
            return jaxenXPathFactory;
        }
        return defaultreference.get();
    }

    public static final XPathFactory newInstance(String str) {
        return (XPathFactory) ReflectionConstructor.construct(str, XPathFactory.class);
    }

    public <T> XPathExpression<T> compile(String str, Filter<T> filter, Map<String, Object> map, Collection<Namespace> collection) {
        return compile(str, filter, map, (Namespace[]) collection.toArray(EMPTYNS));
    }

    public <T> XPathExpression<T> compile(String str, Filter<T> filter) {
        return compile(str, filter, (Map<String, Object>) null, EMPTYNS);
    }

    public XPathExpression<Object> compile(String str) {
        return compile(str, Filters.fpassthrough(), (Map<String, Object>) null, EMPTYNS);
    }
}
