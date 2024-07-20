package org.htmlcleaner;

public class SpecialEntity {
    private final String escapedXmlString;
    private boolean htmlSpecialEntity;
    private final String htmlString;
    private final int intCode;
    private final String key;

    public SpecialEntity(String str, int i, String str2, boolean z) {
        this.key = str;
        this.intCode = i;
        String str3 = "&" + str + ";";
        if (str2 != null) {
            this.htmlString = str2;
        } else {
            this.htmlString = str3;
        }
        if (z) {
            this.escapedXmlString = String.valueOf((char) this.intCode);
        } else {
            this.escapedXmlString = str3;
        }
        this.htmlSpecialEntity = z;
    }

    public String getKey() {
        return this.key;
    }

    public int intValue() {
        return this.intCode;
    }

    public String getHtmlString() {
        return this.htmlString;
    }

    public String getEscapedXmlString() {
        return this.escapedXmlString;
    }

    public String getEscaped(boolean z) {
        return z ? getHtmlString() : getEscapedXmlString();
    }

    public boolean isHtmlSpecialEntity() {
        return this.htmlSpecialEntity;
    }

    public char charValue() {
        return (char) intValue();
    }

    public String getDecimalNCR() {
        return "&#" + this.intCode + ";";
    }

    public String getHexNCR() {
        return "&#x" + Integer.toHexString(this.intCode) + ";";
    }

    public String getEscapedValue() {
        return "&" + this.key + ";";
    }
}
