package org.htmlcleaner;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public abstract class XmlSerializer extends Serializer {
    public static final String BEGIN_CDATA = "<![CDATA[";
    public static final String END_CDATA = "]]>";
    public static final String SAFE_BEGIN_CDATA = "/*<![CDATA[*/";
    public static final String SAFE_END_CDATA = "/*]]>*/";
    public static final String XMLNS_NAMESPACE = "xmlns";
    private boolean creatingHtmlDom;

    protected XmlSerializer(CleanerProperties cleanerProperties) {
        super(cleanerProperties);
    }

    public void setCreatingHtmlDom(boolean z) {
        this.creatingHtmlDom = z;
    }

    public boolean isCreatingHtmlDom() {
        return this.creatingHtmlDom;
    }

    @Deprecated
    public void writeXmlToStream(TagNode tagNode, OutputStream outputStream, String str) throws IOException {
        super.writeToStream(tagNode, outputStream, str);
    }

    @Deprecated
    public void writeXmlToStream(TagNode tagNode, OutputStream outputStream) throws IOException {
        super.writeToStream(tagNode, outputStream);
    }

    @Deprecated
    public void writeXmlToFile(TagNode tagNode, String str, String str2) throws IOException {
        super.writeToFile(tagNode, str, str2);
    }

    @Deprecated
    public void writeXmlToFile(TagNode tagNode, String str) throws IOException {
        super.writeToFile(tagNode, str);
    }

    @Deprecated
    public String getXmlAsString(TagNode tagNode, String str) {
        return super.getAsString(tagNode, str);
    }

    @Deprecated
    public String getXmlAsString(TagNode tagNode) {
        return super.getAsString(tagNode);
    }

    @Deprecated
    public void writeXml(TagNode tagNode, Writer writer, String str) throws IOException {
        super.write(tagNode, writer, str);
    }

    /* access modifiers changed from: protected */
    public String escapeXml(String str) {
        return C2401Utils.escapeXml(str, this.props, isCreatingHtmlDom());
    }

    /* access modifiers changed from: protected */
    public boolean dontEscape(TagNode tagNode) {
        return this.props.isUseCdataForScriptAndStyle() && isScriptOrStyle(tagNode);
    }

    /* access modifiers changed from: protected */
    public boolean isMinimizedTagSyntax(TagNode tagNode) {
        TagInfo tagInfo = this.props.getTagInfoProvider().getTagInfo(tagNode.getName());
        return tagNode.isEmpty() && (tagInfo == null || tagInfo.isMinimizedTagPermitted()) && (this.props.isUseEmptyElementTags() || (tagInfo != null && tagInfo.isEmptyTag()));
    }

    /* access modifiers changed from: protected */
    public void serializeOpenTag(TagNode tagNode, Writer writer) throws IOException {
        serializeOpenTag(tagNode, writer, true);
    }

    /* access modifiers changed from: protected */
    public void serializeOpenTag(TagNode tagNode, Writer writer, boolean z) throws IOException {
        if (!isForbiddenTag(tagNode)) {
            String name = tagNode.getName();
            Map<String, String> attributes = tagNode.getAttributes();
            if (this.props.isAddNewlineToHeadAndBody() && isHeadOrBody(name)) {
                writer.write(StringUtils.f3949LF);
            }
            writer.write("<" + name);
            for (Map.Entry next : attributes.entrySet()) {
                serializeAttribute(tagNode, writer, (String) next.getKey(), (String) next.getValue());
            }
            if (isMinimizedTagSyntax(tagNode)) {
                writer.write(" />");
                if (z) {
                    writer.write(StringUtils.f3949LF);
                }
            } else if (dontEscape(tagNode)) {
                writer.write(">");
                if (!tagNode.getText().toString().startsWith(SAFE_BEGIN_CDATA)) {
                    writer.write(SAFE_BEGIN_CDATA);
                }
            } else {
                writer.write(">");
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean isForbiddenTag(TagNode tagNode) {
        return tagNode.getName() == null;
    }

    /* access modifiers changed from: protected */
    public boolean isHeadOrBody(String str) {
        return "head".equalsIgnoreCase(str) || "body".equalsIgnoreCase(str);
    }

    /* access modifiers changed from: protected */
    public void serializeAttribute(TagNode tagNode, Writer writer, String str, String str2) throws IOException {
        if (!isForbiddenAttribute(tagNode, str, str2)) {
            writer.write(StringUtils.SPACE + str + "=\"" + escapeXml(str2) + "\"");
        }
    }

    /* access modifiers changed from: protected */
    public boolean isForbiddenAttribute(TagNode tagNode, String str, String str2) {
        return !this.props.isNamespacesAware() && ("xmlns".equals(str) || str.startsWith("xmlns:"));
    }

    /* access modifiers changed from: protected */
    public void serializeEndTag(TagNode tagNode, Writer writer) throws IOException {
        serializeEndTag(tagNode, writer, true);
    }

    /* access modifiers changed from: protected */
    public void serializeEndTag(TagNode tagNode, Writer writer, boolean z) throws IOException {
        if (!isForbiddenTag(tagNode)) {
            String name = tagNode.getName();
            if (dontEscape(tagNode) && !tagNode.getText().toString().trim().endsWith(SAFE_END_CDATA)) {
                writer.write(SAFE_END_CDATA);
            }
            writer.write("</" + name + ">");
            if (z) {
                writer.write(StringUtils.f3949LF);
            }
        }
    }
}
