package org.htmlcleaner;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public abstract class Serializer {
    protected CleanerProperties props;

    /* access modifiers changed from: protected */
    public abstract void serialize(TagNode tagNode, Writer writer) throws IOException;

    private class HeadlessTagNode extends TagNode {
        private HeadlessTagNode(TagNode tagNode) {
            super("");
            Map<String, String> namespaceDeclarations;
            getAttributes().putAll(tagNode.getAttributes());
            addChildren(tagNode.getAllChildren());
            setDocType(tagNode.getDocType());
            Map<String, String> namespaceDeclarations2 = getNamespaceDeclarations();
            if (namespaceDeclarations2 != null && (namespaceDeclarations = tagNode.getNamespaceDeclarations()) != null) {
                namespaceDeclarations2.putAll(namespaceDeclarations);
            }
        }
    }

    protected Serializer(CleanerProperties cleanerProperties) {
        this.props = cleanerProperties;
    }

    public void writeToStream(TagNode tagNode, OutputStream outputStream, String str, boolean z) throws IOException {
        write(tagNode, new OutputStreamWriter(outputStream, str), str, z);
    }

    public void writeToStream(TagNode tagNode, OutputStream outputStream, String str) throws IOException {
        writeToStream(tagNode, outputStream, str, false);
    }

    public void writeToStream(TagNode tagNode, OutputStream outputStream, boolean z) throws IOException {
        writeToStream(tagNode, outputStream, this.props.getCharset(), z);
    }

    public void writeToStream(TagNode tagNode, OutputStream outputStream) throws IOException {
        writeToStream(tagNode, outputStream, false);
    }

    public void writeToFile(TagNode tagNode, String str, String str2, boolean z) throws IOException {
        writeToStream(tagNode, new FileOutputStream(str), str2, z);
    }

    public void writeToFile(TagNode tagNode, String str, String str2) throws IOException {
        writeToFile(tagNode, str, str2, false);
    }

    public void writeToFile(TagNode tagNode, String str, boolean z) throws IOException {
        writeToFile(tagNode, str, this.props.getCharset(), z);
    }

    public void writeToFile(TagNode tagNode, String str) throws IOException {
        writeToFile(tagNode, str, false);
    }

    public String getAsString(TagNode tagNode, String str, boolean z) {
        StringWriter stringWriter = new StringWriter();
        try {
            write(tagNode, stringWriter, str, z);
            return stringWriter.getBuffer().toString();
        } catch (IOException e) {
            throw new HtmlCleanerException((Throwable) e);
        }
    }

    public String getAsString(TagNode tagNode, String str) {
        return getAsString(tagNode, str, false);
    }

    public String getAsString(TagNode tagNode, boolean z) {
        return getAsString(tagNode, this.props.getCharset(), z);
    }

    public String getAsString(TagNode tagNode) {
        return getAsString(tagNode, false);
    }

    public String getAsString(String str) {
        return getAsString(new HtmlCleaner(this.props).clean(str), this.props.getCharset());
    }

    public void write(TagNode tagNode, Writer writer, String str) throws IOException {
        write(tagNode, writer, str, false);
    }

    public void write(TagNode tagNode, Writer writer, String str, boolean z) throws IOException {
        DoctypeToken docType;
        if (z) {
            tagNode = new HeadlessTagNode(tagNode);
        }
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        if (!this.props.isOmitXmlDeclaration()) {
            String str2 = "<?xml version=\"1.0\"";
            if (str != null) {
                str2 = str2 + " encoding=\"" + str + "\"";
            }
            bufferedWriter.write((str2 + "?>") + StringUtils.f3949LF);
        }
        if (!this.props.isOmitDoctypeDeclaration() && (docType = tagNode.getDocType()) != null) {
            docType.serialize(this, bufferedWriter);
        }
        serialize(tagNode, bufferedWriter);
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    /* access modifiers changed from: protected */
    public boolean isScriptOrStyle(TagNode tagNode) {
        String name = tagNode.getName();
        return "script".equalsIgnoreCase(name) || "style".equalsIgnoreCase(name);
    }
}
