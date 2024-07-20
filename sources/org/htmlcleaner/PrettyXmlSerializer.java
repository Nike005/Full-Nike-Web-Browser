package org.htmlcleaner;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.commons.lang3.StringUtils;

public class PrettyXmlSerializer extends XmlSerializer {
    private static final String DEFAULT_INDENTATION_STRING = "\t";
    private String indentString;
    private List<String> indents;

    public PrettyXmlSerializer(CleanerProperties cleanerProperties) {
        this(cleanerProperties, DEFAULT_INDENTATION_STRING);
    }

    public PrettyXmlSerializer(CleanerProperties cleanerProperties, String str) {
        super(cleanerProperties);
        this.indentString = DEFAULT_INDENTATION_STRING;
        this.indents = new ArrayList();
        this.indentString = str;
    }

    /* access modifiers changed from: protected */
    public void serialize(TagNode tagNode, Writer writer) throws IOException {
        serializePrettyXml(tagNode, writer, 0);
    }

    private synchronized String getIndent(int i) {
        String str;
        String str2;
        int size = this.indents.size();
        if (size <= i) {
            if (size == 0) {
                str = null;
            } else {
                str = this.indents.get(size - 1);
            }
            while (size <= i) {
                if (str == null) {
                    str2 = "";
                } else {
                    str2 = str + this.indentString;
                }
                this.indents.add(str);
                size++;
            }
        }
        return this.indents.get(i);
    }

    private String getIndentedText(String str, int i) {
        String indent = getIndent(i);
        StringBuilder sb = new StringBuilder(str.length());
        StringTokenizer stringTokenizer = new StringTokenizer(str, "\n\r");
        while (stringTokenizer.hasMoreTokens()) {
            String trim = stringTokenizer.nextToken().trim();
            if (!"".equals(trim)) {
                sb.append(indent);
                sb.append(trim);
                sb.append(StringUtils.f3949LF);
            }
        }
        return sb.toString();
    }

    private String getSingleLineOfChildren(List list) {
        StringBuilder sb = new StringBuilder();
        Iterator it = list.iterator();
        boolean z = true;
        while (it.hasNext()) {
            Object next = it.next();
            if (!(next instanceof ContentNode)) {
                return null;
            }
            String obj = next.toString();
            if (z) {
                obj = ltrim(obj);
            }
            if (!it.hasNext()) {
                obj = rtrim(obj);
            }
            if (obj.indexOf(StringUtils.f3949LF) >= 0 || obj.indexOf(StringUtils.f3948CR) >= 0) {
                return null;
            }
            sb.append(obj);
            z = false;
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public void serializePrettyXml(TagNode tagNode, Writer writer, int i) throws IOException {
        String str;
        List allChildren = tagNode.getAllChildren();
        boolean isEmptyString = C2401Utils.isEmptyString(tagNode.getName());
        if (isEmptyString) {
            str = "";
        } else {
            str = getIndent(i);
        }
        writer.write(str);
        serializeOpenTag(tagNode, writer, true);
        if (!isMinimizedTagSyntax(tagNode)) {
            String singleLineOfChildren = getSingleLineOfChildren(allChildren);
            boolean dontEscape = dontEscape(tagNode);
            if (singleLineOfChildren == null) {
                if (!isEmptyString) {
                    writer.write(StringUtils.f3949LF);
                }
                for (Object next : allChildren) {
                    if (next instanceof TagNode) {
                        serializePrettyXml((TagNode) next, writer, isEmptyString ? i : i + 1);
                    } else if (next instanceof ContentNode) {
                        String obj = next.toString();
                        writer.write(getIndentedText(dontEscape ? obj.replaceAll(XmlSerializer.END_CDATA, "]]&gt;") : escapeXml(obj), isEmptyString ? i : i + 1));
                    } else if (next instanceof CommentNode) {
                        writer.write(getIndentedText(((CommentNode) next).getCommentedContent(), isEmptyString ? i : i + 1));
                    }
                }
            } else if (!dontEscape(tagNode)) {
                writer.write(escapeXml(singleLineOfChildren));
            } else {
                writer.write(singleLineOfChildren.replaceAll(XmlSerializer.END_CDATA, "]]&gt;"));
            }
            if (singleLineOfChildren == null) {
                writer.write(str);
            }
            serializeEndTag(tagNode, writer, true);
        }
    }

    private String ltrim(String str) {
        if (str == null) {
            return null;
        }
        int i = 0;
        int length = str.length();
        while (i < length && Character.isWhitespace(str.charAt(i))) {
            i++;
        }
        if (i >= length) {
            return "";
        }
        return str.substring(i);
    }

    private String rtrim(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        while (length > 0 && Character.isWhitespace(str.charAt(length - 1))) {
            length--;
        }
        if (length <= 0) {
            return "";
        }
        return str.substring(0, length);
    }
}
