package org.htmlcleaner;

public class XPatherException extends Exception {
    public XPatherException() {
        this("Error in evaluating XPath expression!");
    }

    public XPatherException(Throwable th) {
        super(th);
    }

    public XPatherException(String str) {
        super(str);
    }

    public XPatherException(String str, Throwable th) {
        super(str, th);
    }
}
