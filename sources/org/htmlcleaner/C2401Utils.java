package org.htmlcleaner;

import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: org.htmlcleaner.Utils */
public class C2401Utils {
    private static final Pattern ASCII_CHAR = Pattern.compile("\\p{Print}");
    public static Pattern DECIMAL = Pattern.compile("^([\\p{Digit}]+)(;?)");
    public static Pattern HEX_RELAXED = Pattern.compile("^0*([x|X][\\p{XDigit}]+)(;?)");
    public static Pattern HEX_STRICT = Pattern.compile("^([x|X][\\p{XDigit}]+)(;?)");
    private static String ampNcr;

    public static boolean isIdentifierHelperChar(char c) {
        return ':' == c || '.' == c || '-' == c || '_' == c;
    }

    static boolean isValidXmlChar(char c) {
        return (c >= ' ' && c <= 55295) || c == 9 || c == 10 || c == 13 || (c >= 57344 && c <= 65533) || (c >= 0 && c <= 65535);
    }

    @Deprecated
    static CharSequence readUrl(URL url, String str) throws IOException {
        int read;
        StringBuilder sb = new StringBuilder(1024);
        InputStream openStream = url.openStream();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(openStream, str);
            char[] cArr = new char[1024];
            do {
                read = inputStreamReader.read(cArr);
                if (read >= 0) {
                    sb.append(cArr, 0, read);
                    continue;
                }
            } while (read > 0);
            return sb;
        } finally {
            openStream.close();
        }
    }

    public static String escapeXml(String str, CleanerProperties cleanerProperties, boolean z) {
        return escapeXml(str, cleanerProperties.isAdvancedXmlEscape(), cleanerProperties.isRecognizeUnicodeChars(), cleanerProperties.isTranslateSpecialEntities(), z, cleanerProperties.isTransResCharsToNCR(), cleanerProperties.isTransSpecialEntitiesToNCR());
    }

    public static String escapeXml(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        SpecialEntity specialEntity;
        int i;
        if (str == null) {
            return null;
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        int i2 = 0;
        while (i2 < length) {
            char charAt = str.charAt(i2);
            if (charAt != '&') {
                SpecialEntity specialEntityByUnicode = SpecialEntities.INSTANCE.getSpecialEntityByUnicode(charAt);
                if (specialEntityByUnicode != null) {
                    sb.append(z5 ? specialEntityByUnicode.getDecimalNCR() : specialEntityByUnicode.getEscaped(z4));
                } else {
                    sb.append(charAt);
                }
            } else if ((z || z2) && i2 < length - 1 && str.charAt(i2 + 1) == '#') {
                i2 = convertToUnicode(str, z4, z2, z6, sb, i2 + 2);
            } else {
                String str2 = "&amp;";
                if ((z3 || z) && (specialEntity = SpecialEntities.INSTANCE.getSpecialEntity(str.substring(i2, Math.min(10, length - i2) + i2))) != null) {
                    if (z3 && specialEntity.isHtmlSpecialEntity()) {
                        if (z2) {
                            sb.append((char) specialEntity.intValue());
                        } else {
                            sb.append(specialEntity.getDecimalNCR());
                        }
                        i = specialEntity.getKey().length();
                    } else if (z) {
                        sb.append(z5 ? specialEntity.getDecimalNCR() : specialEntity.getEscaped(z4));
                        i = specialEntity.getKey().length();
                    } else {
                        if (z5) {
                            str2 = getAmpNcr();
                        }
                        sb.append(str2);
                    }
                    i2 += i + 1;
                } else {
                    if (z5) {
                        str2 = getAmpNcr();
                    }
                    sb.append(str2);
                }
            }
            i2++;
        }
        return sb.toString();
    }

    private static String getAmpNcr() {
        if (ampNcr == null) {
            ampNcr = SpecialEntities.INSTANCE.getSpecialEntityByUnicode(38).getDecimalNCR();
        }
        return ampNcr;
    }

    private static int convertToUnicode(String str, boolean z, boolean z2, boolean z3, StringBuilder sb, int i) {
        StringBuilder sb2 = new StringBuilder();
        int extractCharCode = extractCharCode(str, i, true, sb2);
        if (sb2.length() > 0) {
            try {
                boolean equals = sb2.substring(0, 1).equals(AvidJSONUtil.KEY_X);
                char parseInt = (char) (equals ? Integer.parseInt(sb2.substring(1), 16) : Integer.parseInt(sb2.toString()));
                SpecialEntity specialEntityByUnicode = SpecialEntities.INSTANCE.getSpecialEntityByUnicode(parseInt);
                if (parseInt == 0) {
                    sb.append("&amp;");
                } else if (specialEntityByUnicode != null && (!specialEntityByUnicode.isHtmlSpecialEntity() || !z2)) {
                    sb.append(z ? specialEntityByUnicode.getHtmlString() : z3 ? equals ? specialEntityByUnicode.getHexNCR() : specialEntityByUnicode.getDecimalNCR() : specialEntityByUnicode.getEscapedXmlString());
                } else if (z2) {
                    sb.append(String.valueOf(parseInt));
                } else {
                    if (ASCII_CHAR.matcher(new String(new char[]{parseInt})).find()) {
                        sb.append(String.valueOf(parseInt));
                    } else {
                        sb.append("&#");
                        sb.append(sb2);
                        sb.append(";");
                    }
                }
            } catch (NumberFormatException unused) {
                sb.append("&amp;#");
                sb.append(sb2);
                sb.append(";");
            }
        } else {
            sb.append("&amp;");
        }
        return extractCharCode;
    }

    private static int extractCharCode(String str, int i, boolean z, StringBuilder sb) {
        Matcher matcher;
        CharSequence subSequence = str.subSequence(i, Math.min(str.length(), i + 15));
        if (z) {
            matcher = HEX_RELAXED.matcher(subSequence);
        } else {
            matcher = HEX_STRICT.matcher(subSequence);
        }
        if (!matcher.find()) {
            matcher = DECIMAL.matcher(subSequence);
            if (!matcher.find()) {
                return i;
            }
        }
        int end = i + (matcher.end() - 1);
        sb.append(matcher.group(1));
        return end;
    }

    public static boolean isValidXmlIdentifier(String str) {
        int length;
        if (str == null || (length = str.length()) == 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if ((i == 0 && !Character.isUnicodeIdentifierStart(charAt)) || (!Character.isUnicodeIdentifierStart(charAt) && !Character.isDigit(charAt) && !isIdentifierHelperChar(charAt))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmptyString(Object obj) {
        if (obj == null || escapeXml(obj.toString(), true, false, false, false, false, false).replace(160, ' ').trim().length() == 0) {
            return true;
        }
        return false;
    }

    public static String[] tokenize(String str, String str2) {
        int i = 0;
        if (str == null) {
            return new String[0];
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str, str2);
        String[] strArr = new String[stringTokenizer.countTokens()];
        while (stringTokenizer.hasMoreTokens()) {
            strArr[i] = stringTokenizer.nextToken();
            i++;
        }
        return strArr;
    }

    public static String getXmlNSPrefix(String str) {
        int indexOf = str.indexOf(58);
        if (indexOf > 0) {
            return str.substring(0, indexOf);
        }
        return null;
    }

    public static String getXmlName(String str) {
        int indexOf = str.indexOf(58);
        return (indexOf <= 0 || indexOf >= str.length() + -1) ? str : str.substring(indexOf + 1);
    }

    static boolean isValidInt(String str, int i) {
        try {
            Integer.parseInt(str, i);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }
}
