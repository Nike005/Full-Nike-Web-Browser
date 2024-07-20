package org.htmlcleaner;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.text.Typography;
import org.apache.commons.lang3.StringUtils;

public abstract class HtmlSerializer extends Serializer {
    private static final Map<Character, String> RESERVED_XML_CHARS;

    static {
        HashMap hashMap = new HashMap();
        RESERVED_XML_CHARS = hashMap;
        hashMap.put(Character.valueOf(Typography.amp), "&amp;");
        RESERVED_XML_CHARS.put(Character.valueOf(Typography.less), "&lt;");
        RESERVED_XML_CHARS.put(Character.valueOf(Typography.greater), "&gt;");
        RESERVED_XML_CHARS.put(Character.valueOf(Typography.quote), "&quot;");
        RESERVED_XML_CHARS.put('\'', "&apos;");
    }

    @Deprecated
    private boolean isReservedXmlChar(char c) {
        return RESERVED_XML_CHARS.containsKey(Character.valueOf(c));
    }

    protected HtmlSerializer(CleanerProperties cleanerProperties) {
        super(cleanerProperties);
    }

    /* access modifiers changed from: protected */
    public boolean isMinimizedTagSyntax(TagNode tagNode) {
        TagInfo tagInfo = this.props.getTagInfoProvider().getTagInfo(tagNode.getName());
        return tagInfo != null && !tagNode.hasChildren() && tagInfo.isEmptyTag();
    }

    /* access modifiers changed from: protected */
    public boolean dontEscape(TagNode tagNode) {
        return isScriptOrStyle(tagNode);
    }

    /* access modifiers changed from: protected */
    public String escapeText(String str) {
        Object obj;
        boolean z;
        String str2;
        String substring;
        SpecialEntity specialEntity;
        String str3;
        String str4 = str;
        boolean isRecognizeUnicodeChars = this.props.isRecognizeUnicodeChars();
        boolean isTranslateSpecialEntities = this.props.isTranslateSpecialEntities();
        if (str4 == null) {
            return null;
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        int i = 0;
        while (i < length) {
            char charAt = str4.charAt(i);
            if (charAt == '&') {
                String str5 = "&#38;";
                char c = ';';
                int i2 = 2;
                if (i >= length - 2 || str4.charAt(i + 1) != '#') {
                    String substring2 = str4.substring(i, Math.min(SpecialEntities.INSTANCE.getMaxEntityLength() + 2, length - i) + i);
                    int indexOf = substring2.indexOf(59);
                    if (indexOf <= 0 || (specialEntity = SpecialEntities.INSTANCE.getSpecialEntity(substring)) == null) {
                        String substring3 = str4.substring(i);
                        Iterator<Map.Entry<Character, String>> it = RESERVED_XML_CHARS.entrySet().iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                z = false;
                                break;
                            }
                            Map.Entry next = it.next();
                            String str6 = (String) next.getValue();
                            if (substring3.startsWith(str6)) {
                                if (this.props.isTransResCharsToNCR()) {
                                    str2 = "&#" + ((Character) next.getKey()).charValue() + ";";
                                } else {
                                    str2 = str6;
                                }
                                sb.append(str2);
                                i += str6.length() - 1;
                                z = true;
                            }
                        }
                        if (!z) {
                            if (!this.props.isTransResCharsToNCR()) {
                                str5 = "&";
                            }
                            sb.append(str5);
                        }
                    } else {
                        if (isTranslateSpecialEntities) {
                            sb.append(isRecognizeUnicodeChars ? Character.valueOf(specialEntity.charValue()) : specialEntity.getDecimalNCR());
                        } else {
                            sb.append(specialEntity.getEscapedValue());
                        }
                        i += (substring = substring2.substring(1, indexOf)).length() + 1;
                    }
                } else {
                    boolean z2 = Character.toLowerCase(str4.charAt(i + 2)) == 'x';
                    if (z2) {
                        i2 = 3;
                    }
                    int i3 = i2 + i;
                    int i4 = z2 ? 16 : 10;
                    String str7 = "";
                    while (true) {
                        if (i3 < length) {
                            char charAt2 = str4.charAt(i3);
                            if (charAt2 != c) {
                                if (!C2401Utils.isValidInt(str7 + charAt2, i4)) {
                                    i3--;
                                    break;
                                }
                                str7 = str7 + charAt2;
                                i3++;
                                c = ';';
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    if (C2401Utils.isValidInt(str7, i4)) {
                        char parseInt = (char) Integer.parseInt(str7, i4);
                        if (C2401Utils.isValidXmlChar(parseInt)) {
                            if (!isReservedXmlChar(parseInt)) {
                                if (isRecognizeUnicodeChars) {
                                    str3 = String.valueOf(parseInt);
                                } else {
                                    str3 = "&#" + str7 + ";";
                                }
                                sb.append(str3);
                            } else {
                                sb.append("&#" + str7 + ";");
                            }
                        }
                        i = i3;
                    } else {
                        if (!this.props.isTransResCharsToNCR()) {
                            str5 = "&";
                        }
                        sb.append(str5);
                    }
                }
            } else if (isReservedXmlChar(charAt)) {
                if (this.props.isTransResCharsToNCR()) {
                    obj = "&#" + charAt + ";";
                } else {
                    obj = Character.valueOf(charAt);
                }
                sb.append(obj);
            } else {
                sb.append(charAt);
            }
            i++;
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public void serializeOpenTag(TagNode tagNode, Writer writer, boolean z) throws IOException {
        Map<String, String> namespaceDeclarations;
        String name = tagNode.getName();
        if (!C2401Utils.isEmptyString(name)) {
            boolean isNamespacesAware = this.props.isNamespacesAware();
            if (!isNamespacesAware && C2401Utils.getXmlNSPrefix(name) != null) {
                name = C2401Utils.getXmlName(name);
            }
            writer.write("<" + name);
            for (Map.Entry next : tagNode.getAttributes().entrySet()) {
                String str = (String) next.getKey();
                if (!isNamespacesAware && C2401Utils.getXmlNSPrefix(str) != null) {
                    str = C2401Utils.getXmlName(str);
                }
                writer.write(StringUtils.SPACE + str + "=\"" + escapeText((String) next.getValue()) + "\"");
            }
            if (isNamespacesAware && (namespaceDeclarations = tagNode.getNamespaceDeclarations()) != null) {
                for (Map.Entry next2 : namespaceDeclarations.entrySet()) {
                    String str2 = (String) next2.getKey();
                    String str3 = "xmlns";
                    if (str2.length() > 0) {
                        str3 = str3 + ":" + str2;
                    }
                    writer.write(StringUtils.SPACE + str3 + "=\"" + escapeText((String) next2.getValue()) + "\"");
                }
            }
            if (isMinimizedTagSyntax(tagNode)) {
                writer.write(" />");
                if (z) {
                    writer.write(StringUtils.f3949LF);
                    return;
                }
                return;
            }
            writer.write(">");
        }
    }

    /* access modifiers changed from: protected */
    public void serializeEndTag(TagNode tagNode, Writer writer, boolean z) throws IOException {
        String name = tagNode.getName();
        if (!C2401Utils.isEmptyString(name)) {
            if (C2401Utils.getXmlNSPrefix(name) != null && !this.props.isNamespacesAware()) {
                name = C2401Utils.getXmlName(name);
            }
            writer.write("</" + name + ">");
            if (z) {
                writer.write(StringUtils.f3949LF);
            }
        }
    }
}
