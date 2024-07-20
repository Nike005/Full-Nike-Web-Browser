package org.jdom2;

import java.io.InvalidObjectException;
import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class Namespace implements Serializable {
    public static final Namespace NO_NAMESPACE = new Namespace("", "");
    public static final Namespace XML_NAMESPACE = new Namespace(JDOMConstants.NS_PREFIX_XML, JDOMConstants.NS_URI_XML);
    private static final ConcurrentMap<String, ConcurrentMap<String, Namespace>> namespacemap = new ConcurrentHashMap(512, 0.75f, 64);
    private static final long serialVersionUID = 200;
    private final transient String prefix;
    private final transient String uri;

    static {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put(NO_NAMESPACE.getPrefix(), NO_NAMESPACE);
        namespacemap.put(NO_NAMESPACE.getURI(), concurrentHashMap);
        ConcurrentHashMap concurrentHashMap2 = new ConcurrentHashMap();
        concurrentHashMap2.put(XML_NAMESPACE.getPrefix(), XML_NAMESPACE);
        namespacemap.put(XML_NAMESPACE.getURI(), concurrentHashMap2);
    }

    public static Namespace getNamespace(String str, String str2) {
        String str3;
        if (str2 != null) {
            ConcurrentMap concurrentMap = (ConcurrentMap) namespacemap.get(str2);
            if (concurrentMap == null) {
                String checkNamespaceURI = Verifier.checkNamespaceURI(str2);
                if (checkNamespaceURI == null) {
                    concurrentMap = new ConcurrentHashMap();
                    ConcurrentMap putIfAbsent = namespacemap.putIfAbsent(str2, concurrentMap);
                    if (putIfAbsent != null) {
                        concurrentMap = putIfAbsent;
                    }
                } else {
                    throw new IllegalNameException(str2, "Namespace URI", checkNamespaceURI);
                }
            }
            if (str == null) {
                str3 = "";
            } else {
                str3 = str;
            }
            Namespace namespace = (Namespace) concurrentMap.get(str3);
            if (namespace != null) {
                return namespace;
            }
            if ("".equals(str2)) {
                throw new IllegalNameException("", "namespace", "Namespace URIs must be non-null and non-empty Strings");
            } else if (!JDOMConstants.NS_URI_XML.equals(str2)) {
                if (str == null) {
                    str = "";
                }
                String checkNamespacePrefix = Verifier.checkNamespacePrefix(str);
                if (checkNamespacePrefix == null) {
                    Namespace namespace2 = new Namespace(str, str2);
                    Namespace namespace3 = (Namespace) concurrentMap.putIfAbsent(str, namespace2);
                    return namespace3 != null ? namespace3 : namespace2;
                }
                throw new IllegalNameException(str, "Namespace prefix", checkNamespacePrefix);
            } else {
                throw new IllegalNameException(str2, "Namespace URI", "The http://www.w3.org/XML/1998/namespace must be bound to only the 'xml' prefix.");
            }
        } else if (str == null || "".equals(str)) {
            return NO_NAMESPACE;
        } else {
            throw new IllegalNameException("", "namespace", "Namespace URIs must be non-null and non-empty Strings");
        }
    }

    public static Namespace getNamespace(String str) {
        return getNamespace("", str);
    }

    private Namespace(String str, String str2) {
        this.prefix = str;
        this.uri = str2;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getURI() {
        return this.uri;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Namespace) {
            return this.uri.equals(((Namespace) obj).uri);
        }
        return false;
    }

    public String toString() {
        return "[Namespace: prefix \"" + this.prefix + "\" is mapped to URI \"" + this.uri + "\"]";
    }

    public int hashCode() {
        return this.uri.hashCode();
    }

    private static final class NamespaceSerializationProxy implements Serializable {
        private static final long serialVersionUID = 200;
        private final String pprefix;
        private final String puri;

        public NamespaceSerializationProxy(String str, String str2) {
            this.pprefix = str;
            this.puri = str2;
        }

        private Object readResolve() {
            return Namespace.getNamespace(this.pprefix, this.puri);
        }
    }

    private Object writeReplace() {
        return new NamespaceSerializationProxy(this.prefix, this.uri);
    }

    private Object readResolve() throws InvalidObjectException {
        throw new InvalidObjectException("Namespace is serialized through a proxy");
    }
}
