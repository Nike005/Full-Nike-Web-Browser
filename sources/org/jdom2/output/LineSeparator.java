package org.jdom2.output;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.JDOMConstants;
import org.jdom2.internal.SystemProperty;
import org.jsoup.nodes.DocumentType;

public enum LineSeparator {
    CRNL("\r\n"),
    NL(StringUtils.f3949LF),
    CR(StringUtils.f3948CR),
    DOS("\r\n"),
    UNIX(StringUtils.f3949LF),
    SYSTEM(SystemProperty.get("line.separator", "\r\n")),
    NONE((String) null),
    DEFAULT(getDefaultLineSeparator());
    
    private final String value;

    private static String getDefaultLineSeparator() {
        String str = SystemProperty.get(JDOMConstants.JDOM2_PROPERTY_LINE_SEPARATOR, "DEFAULT");
        if ("DEFAULT".equals(str)) {
            return "\r\n";
        }
        if (DocumentType.SYSTEM_KEY.equals(str)) {
            return System.getProperty("line.separator");
        }
        if ("CRNL".equals(str)) {
            return "\r\n";
        }
        if ("NL".equals(str)) {
            return StringUtils.f3949LF;
        }
        if ("CR".equals(str)) {
            return StringUtils.f3948CR;
        }
        if ("DOS".equals(str)) {
            return "\r\n";
        }
        if ("UNIX".equals(str)) {
            return StringUtils.f3949LF;
        }
        if ("NONE".equals(str)) {
            return null;
        }
        return str;
    }

    private LineSeparator(String str) {
        this.value = str;
    }

    public String value() {
        return this.value;
    }
}
