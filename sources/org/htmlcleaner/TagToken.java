package org.htmlcleaner;

public abstract class TagToken extends BaseTokenImpl {
    protected String name;

    /* access modifiers changed from: package-private */
    public abstract void addAttribute(String str, String str2);

    public TagToken() {
    }

    public TagToken(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }
}
