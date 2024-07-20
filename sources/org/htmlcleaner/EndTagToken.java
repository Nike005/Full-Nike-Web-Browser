package org.htmlcleaner;

import java.io.Writer;

public class EndTagToken extends TagToken {
    /* access modifiers changed from: package-private */
    public void addAttribute(String str, String str2) {
    }

    public void serialize(Serializer serializer, Writer writer) {
    }

    public EndTagToken() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EndTagToken(String str) {
        super(str == null ? null : str.toLowerCase());
    }

    public String toString() {
        return "endtoken" + super.toString();
    }
}
