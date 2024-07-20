package org.htmlcleaner;

public class HtmlCleanerException extends RuntimeException {
    public HtmlCleanerException() {
        this("HtmlCleaner expression occureed!");
    }

    public HtmlCleanerException(Throwable th) {
        super(th);
    }

    public HtmlCleanerException(String str) {
        super(str);
    }

    public HtmlCleanerException(String str, Throwable th) {
        super(str, th);
    }
}
