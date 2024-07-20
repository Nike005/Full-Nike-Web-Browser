package org.jdom2.xpath.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jdom2.Namespace;
import org.jdom2.Verifier;
import org.jdom2.filter.Filter;
import org.jdom2.xpath.XPathDiagnostic;
import org.jdom2.xpath.XPathExpression;

public abstract class AbstractXPathCompiled<T> implements XPathExpression<T> {
    private static final NamespaceComparator NSSORT = new NamespaceComparator();
    private final Filter<T> xfilter;
    private final Map<String, Namespace> xnamespaces = new HashMap();
    private final String xquery;
    private Map<String, Map<String, Object>> xvariables = new HashMap();

    /* access modifiers changed from: protected */
    public abstract List<?> evaluateRawAll(Object obj);

    /* access modifiers changed from: protected */
    public abstract Object evaluateRawFirst(Object obj);

    private static final class NamespaceComparator implements Comparator<Namespace> {
        private NamespaceComparator() {
        }

        public int compare(Namespace namespace, Namespace namespace2) {
            return namespace.getPrefix().compareTo(namespace2.getPrefix());
        }
    }

    public AbstractXPathCompiled(String str, Filter<T> filter, Map<String, Object> map, Namespace[] namespaceArr) {
        String str2;
        String str3;
        if (str == null) {
            throw new NullPointerException("Null query");
        } else if (filter != null) {
            this.xnamespaces.put(Namespace.NO_NAMESPACE.getPrefix(), Namespace.NO_NAMESPACE);
            if (namespaceArr != null) {
                int length = namespaceArr.length;
                int i = 0;
                while (i < length) {
                    Namespace namespace = namespaceArr[i];
                    if (namespace != null) {
                        Namespace put = this.xnamespaces.put(namespace.getPrefix(), namespace);
                        if (put == null || put == namespace) {
                            i++;
                        } else if (put == Namespace.NO_NAMESPACE) {
                            throw new IllegalArgumentException("The default (no prefix) Namespace URI for XPath queries is always '' and it cannot be redefined to '" + namespace.getURI() + "'.");
                        } else {
                            throw new IllegalArgumentException("A Namespace with the prefix '" + namespace.getPrefix() + "' has already been declared.");
                        }
                    } else {
                        throw new NullPointerException("Null namespace");
                    }
                }
            }
            if (map != null) {
                for (Map.Entry next : map.entrySet()) {
                    String str4 = (String) next.getKey();
                    if (str4 != null) {
                        int indexOf = str4.indexOf(58);
                        if (indexOf < 0) {
                            str2 = "";
                        } else {
                            str2 = str4.substring(0, indexOf);
                        }
                        if (indexOf < 0) {
                            str3 = str4;
                        } else {
                            str3 = str4.substring(indexOf + 1);
                        }
                        String checkNamespacePrefix = Verifier.checkNamespacePrefix(str2);
                        if (checkNamespacePrefix == null) {
                            String checkXMLName = Verifier.checkXMLName(str3);
                            if (checkXMLName == null) {
                                Namespace namespace2 = this.xnamespaces.get(str2);
                                if (namespace2 != null) {
                                    Map map2 = this.xvariables.get(namespace2.getURI());
                                    if (map2 == null) {
                                        map2 = new HashMap();
                                        this.xvariables.put(namespace2.getURI(), map2);
                                    }
                                    if (map2.put(str3, next.getValue()) != null) {
                                        throw new IllegalArgumentException("Variable with name " + ((String) next.getKey()) + "' has already been defined.");
                                    }
                                } else {
                                    throw new IllegalArgumentException("Prefix '" + str2 + "' for variable " + str4 + " has not been assigned a Namespace.");
                                }
                            } else {
                                throw new IllegalArgumentException("Variable name '" + str3 + "' for variable " + str4 + " is illegal: " + checkXMLName);
                            }
                        } else {
                            throw new IllegalArgumentException("Prefix '" + str2 + "' for variable " + str4 + " is illegal: " + checkNamespacePrefix);
                        }
                    } else {
                        throw new NullPointerException("Variable with a null name");
                    }
                }
            }
            this.xquery = str;
            this.xfilter = filter;
        } else {
            throw new NullPointerException("Null filter");
        }
    }

    public XPathExpression<T> clone() {
        try {
            AbstractXPathCompiled abstractXPathCompiled = (AbstractXPathCompiled) super.clone();
            HashMap hashMap = new HashMap();
            for (Map.Entry next : this.xvariables.entrySet()) {
                HashMap hashMap2 = new HashMap();
                for (Map.Entry entry : ((Map) next.getValue()).entrySet()) {
                    hashMap2.put(entry.getKey(), entry.getValue());
                }
                hashMap.put(next.getKey(), hashMap2);
            }
            abstractXPathCompiled.xvariables = hashMap;
            return abstractXPathCompiled;
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Should never be getting a CloneNotSupportedException!", e);
        }
    }

    public final String getExpression() {
        return this.xquery;
    }

    public final Namespace getNamespace(String str) {
        Namespace namespace = this.xnamespaces.get(str);
        if (namespace != null) {
            return namespace;
        }
        throw new IllegalArgumentException("Namespace with prefix '" + str + "' has not been declared.");
    }

    public Namespace[] getNamespaces() {
        Namespace[] namespaceArr = (Namespace[]) this.xnamespaces.values().toArray(new Namespace[this.xnamespaces.size()]);
        Arrays.sort(namespaceArr, NSSORT);
        return namespaceArr;
    }

    public final Object getVariable(String str, Namespace namespace) {
        Map map = this.xvariables.get(namespace == null ? "" : namespace.getURI());
        if (map != null) {
            Object obj = map.get(str);
            if (obj != null) {
                return obj;
            }
            if (map.containsKey(str)) {
                return null;
            }
            throw new IllegalArgumentException("Variable with name '" + str + "' in namespace '" + namespace.getURI() + "' has not been declared.");
        }
        throw new IllegalArgumentException("Variable with name '" + str + "' in namespace '" + namespace.getURI() + "' has not been declared.");
    }

    public Object getVariable(String str) {
        if (str != null) {
            int indexOf = str.indexOf(58);
            if (indexOf >= 0) {
                return getVariable(str.substring(indexOf + 1), getNamespace(str.substring(0, indexOf)));
            }
            return getVariable(str, Namespace.NO_NAMESPACE);
        }
        throw new NullPointerException("Cannot get variable value for null qname");
    }

    public Object setVariable(String str, Namespace namespace, Object obj) {
        Object variable = getVariable(str, namespace);
        this.xvariables.get(namespace.getURI()).put(str, obj);
        return variable;
    }

    public Object setVariable(String str, Object obj) {
        if (str != null) {
            int indexOf = str.indexOf(58);
            if (indexOf >= 0) {
                return setVariable(str.substring(indexOf + 1), getNamespace(str.substring(0, indexOf)), obj);
            }
            return setVariable(str, Namespace.NO_NAMESPACE, obj);
        }
        throw new NullPointerException("Cannot get variable value for null qname");
    }

    public final Filter<T> getFilter() {
        return this.xfilter;
    }

    public List<T> evaluate(Object obj) {
        return this.xfilter.filter(evaluateRawAll(obj));
    }

    public T evaluateFirst(Object obj) {
        Object evaluateRawFirst = evaluateRawFirst(obj);
        if (evaluateRawFirst == null) {
            return null;
        }
        return this.xfilter.filter(evaluateRawFirst);
    }

    public XPathDiagnostic<T> diagnose(Object obj, boolean z) {
        return new XPathDiagnosticImpl(obj, this, z ? Collections.singletonList(evaluateRawFirst(obj)) : evaluateRawAll(obj), z);
    }

    public String toString() {
        int size = this.xnamespaces.size();
        int i = 0;
        for (Map<String, Object> size2 : this.xvariables.values()) {
            i += size2.size();
        }
        return String.format("[XPathExpression: %d namespaces and %d variables for query %s]", new Object[]{Integer.valueOf(size), Integer.valueOf(i), getExpression()});
    }
}
