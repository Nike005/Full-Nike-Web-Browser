package org.jdom2.output;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import org.jdom2.IllegalDataException;
import org.jdom2.Verifier;

public class Format implements Cloneable {
    private static final EscapeStrategy Bits7EscapeStrategy = new EscapeStrategy7Bits();
    private static final EscapeStrategy Bits8EscapeStrategy = new EscapeStrategy8Bits();
    private static final EscapeStrategy DefaultEscapeStrategy = new EscapeStrategy() {
        public boolean shouldEscape(char c) {
            return Verifier.isHighSurrogate(c);
        }
    };
    private static final String STANDARD_ENCODING = "UTF-8";
    private static final String STANDARD_INDENT = "  ";
    private static final String STANDARD_LINE_SEPARATOR = LineSeparator.DEFAULT.value();
    private static final EscapeStrategy UTFEscapeStrategy = new EscapeStrategyUTF();
    String encoding = "UTF-8";
    EscapeStrategy escapeStrategy = DefaultEscapeStrategy;
    boolean expandEmptyElements = false;
    boolean ignoreTrAXEscapingPIs = false;
    String indent = null;
    String lineSeparator = STANDARD_LINE_SEPARATOR;
    TextMode mode = TextMode.PRESERVE;
    boolean omitDeclaration = false;
    boolean omitEncoding = false;
    boolean specifiedAttributesOnly = false;

    public enum TextMode {
        PRESERVE,
        TRIM,
        NORMALIZE,
        TRIM_FULL_WHITE
    }

    private static final class EscapeStrategyUTF implements EscapeStrategy {
        private EscapeStrategyUTF() {
        }

        public final boolean shouldEscape(char c) {
            return Verifier.isHighSurrogate(c);
        }
    }

    private static final class EscapeStrategy8Bits implements EscapeStrategy {
        public boolean shouldEscape(char c) {
            return (c >>> 8) != 0;
        }

        private EscapeStrategy8Bits() {
        }
    }

    private static final class EscapeStrategy7Bits implements EscapeStrategy {
        public boolean shouldEscape(char c) {
            return (c >>> 7) != 0;
        }

        private EscapeStrategy7Bits() {
        }
    }

    private static final class DefaultCharsetEscapeStrategy implements EscapeStrategy {
        private final CharsetEncoder encoder;

        public DefaultCharsetEscapeStrategy(CharsetEncoder charsetEncoder) {
            this.encoder = charsetEncoder;
        }

        public boolean shouldEscape(char c) {
            if (Verifier.isHighSurrogate(c)) {
                return true;
            }
            return !this.encoder.canEncode(c);
        }
    }

    public static Format getRawFormat() {
        return new Format();
    }

    public static Format getPrettyFormat() {
        Format format = new Format();
        format.setIndent(STANDARD_INDENT);
        format.setTextMode(TextMode.TRIM);
        return format;
    }

    public static Format getCompactFormat() {
        Format format = new Format();
        format.setTextMode(TextMode.NORMALIZE);
        return format;
    }

    public static final String compact(String str) {
        int length = str.length() - 1;
        int i = 0;
        while (i <= length && Verifier.isXMLWhitespace(str.charAt(i))) {
            i++;
        }
        while (length > i && Verifier.isXMLWhitespace(str.charAt(length))) {
            length--;
        }
        if (i > length) {
            return "";
        }
        StringBuilder sb = new StringBuilder((length - i) + 1);
        boolean z = true;
        while (i <= length) {
            char charAt = str.charAt(i);
            if (!Verifier.isXMLWhitespace(charAt)) {
                sb.append(charAt);
                z = true;
            } else if (z) {
                sb.append(' ');
                z = false;
            }
            i++;
        }
        return sb.toString();
    }

    public static final String trimRight(String str) {
        int length = str.length() - 1;
        while (length >= 0 && Verifier.isXMLWhitespace(str.charAt(length))) {
            length--;
        }
        if (length < 0) {
            return "";
        }
        return str.substring(0, length + 1);
    }

    public static final String trimLeft(String str) {
        int length = str.length();
        int i = 0;
        while (i < length && Verifier.isXMLWhitespace(str.charAt(i))) {
            i++;
        }
        if (i >= length) {
            return "";
        }
        return str.substring(i);
    }

    public static final String trimBoth(String str) {
        int length = str.length() - 1;
        while (length > 0 && Verifier.isXMLWhitespace(str.charAt(length))) {
            length--;
        }
        int i = 0;
        while (i <= length && Verifier.isXMLWhitespace(str.charAt(i))) {
            i++;
        }
        if (i > length) {
            return "";
        }
        return str.substring(i, length + 1);
    }

    public static final String escapeAttribute(EscapeStrategy escapeStrategy2, String str) {
        EscapeStrategy escapeStrategy3 = escapeStrategy2;
        String str2 = str;
        int length = str.length();
        int i = 0;
        while (i < length && (r12 = str2.charAt(i)) != '<' && r12 != '>' && r12 != '&' && r12 != 13 && r12 != 10 && r12 != '\"' && r12 != 9 && !escapeStrategy3.shouldEscape(r12)) {
            i++;
        }
        if (i == length) {
            return str2;
        }
        StringBuilder sb = new StringBuilder(length + 5);
        sb.append(str2, 0, i);
        while (true) {
            char c = 0;
            while (i < length) {
                int i2 = i + 1;
                char charAt = str2.charAt(i);
                if (c <= 0) {
                    if (charAt == 9) {
                        sb.append("&#x9;");
                    } else if (charAt == 10) {
                        sb.append("&#xA;");
                    } else if (charAt == 13) {
                        sb.append("&#xD;");
                    } else if (charAt == '\"') {
                        sb.append("&quot;");
                    } else if (charAt == '&') {
                        sb.append("&amp;");
                    } else if (charAt == '<') {
                        sb.append("&lt;");
                    } else if (charAt == '>') {
                        sb.append("&gt;");
                    } else if (!escapeStrategy3.shouldEscape(charAt)) {
                        sb.append(charAt);
                    } else if (Verifier.isHighSurrogate(charAt)) {
                        c = charAt;
                    } else {
                        sb.append("&#x");
                        sb.append(Integer.toHexString(charAt));
                        sb.append(';');
                    }
                    i = i2;
                } else if (Verifier.isLowSurrogate(charAt)) {
                    int decodeSurrogatePair = Verifier.decodeSurrogatePair(c, charAt);
                    sb.append("&#x");
                    sb.append(Integer.toHexString(decodeSurrogatePair));
                    sb.append(';');
                    i = i2;
                } else {
                    throw new IllegalDataException("Could not decode surrogate pair 0x" + Integer.toHexString(c) + " / 0x" + Integer.toHexString(charAt));
                }
            }
            if (c <= 0) {
                return sb.toString();
            }
            throw new IllegalDataException("Surrogate pair 0x" + Integer.toHexString(c) + "truncated");
        }
    }

    public static final String escapeText(EscapeStrategy escapeStrategy2, String str, String str2) {
        EscapeStrategy escapeStrategy3 = escapeStrategy2;
        String str3 = str;
        String str4 = str2;
        int length = str2.length();
        int i = 0;
        while (i < length && (r11 = str4.charAt(i)) != '<' && r11 != '>' && r11 != '&' && r11 != 13 && r11 != 10 && !escapeStrategy3.shouldEscape(r11)) {
            i++;
        }
        if (i == length) {
            return str4;
        }
        StringBuilder sb = new StringBuilder();
        if (i > 0) {
            sb.append(str4, 0, i);
        }
        while (true) {
            char c = 0;
            while (i < length) {
                int i2 = i + 1;
                char charAt = str4.charAt(i);
                if (c <= 0) {
                    if (charAt != 10) {
                        if (charAt == 13) {
                            sb.append("&#xD;");
                        } else if (charAt == '&') {
                            sb.append("&amp;");
                        } else if (charAt == '<') {
                            sb.append("&lt;");
                        } else if (charAt == '>') {
                            sb.append("&gt;");
                        } else if (!escapeStrategy3.shouldEscape(charAt)) {
                            sb.append(charAt);
                        } else if (Verifier.isHighSurrogate(charAt)) {
                            c = charAt;
                        } else {
                            sb.append("&#x" + Integer.toHexString(charAt) + ";");
                        }
                    } else if (str3 != null) {
                        sb.append(str3);
                    } else {
                        sb.append(10);
                    }
                    i = i2;
                } else if (Verifier.isLowSurrogate(charAt)) {
                    int decodeSurrogatePair = Verifier.decodeSurrogatePair(c, charAt);
                    sb.append("&#x" + Integer.toHexString(decodeSurrogatePair) + ";");
                    i = i2;
                } else {
                    throw new IllegalDataException("Could not decode surrogate pair 0x" + Integer.toHexString(c) + " / 0x" + Integer.toHexString(charAt));
                }
            }
            if (c <= 0) {
                return sb.toString();
            }
            throw new IllegalDataException("Surrogate pair 0x" + Integer.toHexString(c) + "truncated");
        }
    }

    private static final EscapeStrategy chooseStrategy(String str) {
        if ("UTF-8".equalsIgnoreCase(str) || "UTF-16".equalsIgnoreCase(str)) {
            return UTFEscapeStrategy;
        }
        if (str.toUpperCase().startsWith("ISO-8859-") || "Latin1".equalsIgnoreCase(str)) {
            return Bits8EscapeStrategy;
        }
        if ("US-ASCII".equalsIgnoreCase(str) || "ASCII".equalsIgnoreCase(str)) {
            return Bits7EscapeStrategy;
        }
        try {
            return new DefaultCharsetEscapeStrategy(Charset.forName(str).newEncoder());
        } catch (Exception unused) {
            return DefaultEscapeStrategy;
        }
    }

    private Format() {
        setEncoding("UTF-8");
    }

    public Format setEscapeStrategy(EscapeStrategy escapeStrategy2) {
        this.escapeStrategy = escapeStrategy2;
        return this;
    }

    public EscapeStrategy getEscapeStrategy() {
        return this.escapeStrategy;
    }

    public Format setLineSeparator(String str) {
        if ("".equals(str)) {
            str = null;
        }
        this.lineSeparator = str;
        return this;
    }

    public Format setLineSeparator(LineSeparator lineSeparator2) {
        return setLineSeparator(lineSeparator2 == null ? STANDARD_LINE_SEPARATOR : lineSeparator2.value());
    }

    public String getLineSeparator() {
        return this.lineSeparator;
    }

    public Format setOmitEncoding(boolean z) {
        this.omitEncoding = z;
        return this;
    }

    public boolean getOmitEncoding() {
        return this.omitEncoding;
    }

    public Format setOmitDeclaration(boolean z) {
        this.omitDeclaration = z;
        return this;
    }

    public boolean getOmitDeclaration() {
        return this.omitDeclaration;
    }

    public Format setExpandEmptyElements(boolean z) {
        this.expandEmptyElements = z;
        return this;
    }

    public boolean getExpandEmptyElements() {
        return this.expandEmptyElements;
    }

    public void setIgnoreTrAXEscapingPIs(boolean z) {
        this.ignoreTrAXEscapingPIs = z;
    }

    public boolean getIgnoreTrAXEscapingPIs() {
        return this.ignoreTrAXEscapingPIs;
    }

    public Format setTextMode(TextMode textMode) {
        this.mode = textMode;
        return this;
    }

    public TextMode getTextMode() {
        return this.mode;
    }

    public Format setIndent(String str) {
        this.indent = str;
        return this;
    }

    public String getIndent() {
        return this.indent;
    }

    public Format setEncoding(String str) {
        this.encoding = str;
        this.escapeStrategy = chooseStrategy(str);
        return this;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public boolean isSpecifiedAttributesOnly() {
        return this.specifiedAttributesOnly;
    }

    public void setSpecifiedAttributesOnly(boolean z) {
        this.specifiedAttributesOnly = z;
    }

    public Format clone() {
        try {
            return (Format) super.clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }
}
