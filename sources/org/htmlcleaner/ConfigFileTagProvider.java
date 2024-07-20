package org.htmlcleaner;

import com.mopub.common.Constants;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ConfigFileTagProvider extends HashMap implements ITagInfoProvider {
    static SAXParserFactory parserFactory;
    /* access modifiers changed from: private */
    public boolean generateCode = false;

    static {
        SAXParserFactory newInstance = SAXParserFactory.newInstance();
        parserFactory = newInstance;
        newInstance.setValidating(false);
        parserFactory.setNamespaceAware(false);
    }

    private ConfigFileTagProvider() {
    }

    public ConfigFileTagProvider(InputSource inputSource) {
        try {
            new ConfigParser(this).parse(inputSource);
        } catch (Exception e) {
            throw new HtmlCleanerException("Error parsing tag configuration file!", e);
        }
    }

    public ConfigFileTagProvider(File file) {
        try {
            new ConfigParser(this).parse(new InputSource(new FileReader(file)));
        } catch (Exception e) {
            throw new HtmlCleanerException("Error parsing tag configuration file!", e);
        }
    }

    public ConfigFileTagProvider(URL url) {
        try {
            Object content = url.getContent();
            if (content instanceof InputStream) {
                new ConfigParser(this).parse(new InputSource(new InputStreamReader((InputStream) content)));
            }
        } catch (Exception e) {
            throw new HtmlCleanerException("Error parsing tag configuration file!", e);
        }
    }

    public TagInfo getTagInfo(String str) {
        return (TagInfo) get(str);
    }

    public static void main(String[] strArr) throws IOException, SAXException, ParserConfigurationException {
        ConfigFileTagProvider configFileTagProvider = new ConfigFileTagProvider();
        configFileTagProvider.generateCode = true;
        File file = new File((strArr == null || strArr.length <= 0) ? "default.xml" : strArr[0]);
        configFileTagProvider.getClass();
        ConfigParser configParser = new ConfigParser(configFileTagProvider);
        PrintStream printStream = System.out;
        printStream.println("package " + "org.htmlcleaner" + ";");
        System.out.println("import java.util.HashMap;");
        PrintStream printStream2 = System.out;
        printStream2.println("public class " + "CustomTagProvider" + " extends HashMap implements ITagInfoProvider {");
        System.out.println("private ConcurrentMap<String, TagInfo> tagInfoMap = new ConcurrentHashMap<String, TagInfo>();");
        System.out.println("// singleton instance, used if no other TagInfoProvider is specified");
        PrintStream printStream3 = System.out;
        printStream3.println("public final static " + "CustomTagProvider" + " INSTANCE= new " + "CustomTagProvider" + "();");
        PrintStream printStream4 = System.out;
        StringBuilder sb = new StringBuilder();
        sb.append("public ");
        sb.append("CustomTagProvider");
        sb.append("() {");
        printStream4.println(sb.toString());
        System.out.println("TagInfo tagInfo;");
        configParser.parse(new InputSource(new FileReader(file)));
        System.out.println("}");
        System.out.println("}");
    }

    private class ConfigParser extends DefaultHandler {
        private String dependencyName = null;
        private TagInfo tagInfo = null;
        private Map tagInfoMap;

        ConfigParser(Map map) {
            this.tagInfoMap = map;
        }

        public void parse(InputSource inputSource) throws ParserConfigurationException, SAXException, IOException {
            ConfigFileTagProvider.parserFactory.newSAXParser().parse(inputSource, this);
        }

        public void characters(char[] cArr, int i, int i2) throws SAXException {
            if (this.tagInfo != null) {
                String trim = new String(cArr, i, i2).trim();
                if ("fatal-tags".equals(this.dependencyName)) {
                    this.tagInfo.defineFatalTags(trim);
                    if (ConfigFileTagProvider.this.generateCode) {
                        PrintStream printStream = System.out;
                        printStream.println("tagInfo.defineFatalTags(\"" + trim + "\");");
                    }
                } else if ("req-enclosing-tags".equals(this.dependencyName)) {
                    this.tagInfo.defineRequiredEnclosingTags(trim);
                    if (ConfigFileTagProvider.this.generateCode) {
                        PrintStream printStream2 = System.out;
                        printStream2.println("tagInfo.defineRequiredEnclosingTags(\"" + trim + "\");");
                    }
                } else if ("forbidden-tags".equals(this.dependencyName)) {
                    this.tagInfo.defineForbiddenTags(trim);
                    if (ConfigFileTagProvider.this.generateCode) {
                        PrintStream printStream3 = System.out;
                        printStream3.println("tagInfo.defineForbiddenTags(\"" + trim + "\");");
                    }
                } else if ("allowed-children-tags".equals(this.dependencyName)) {
                    this.tagInfo.defineAllowedChildrenTags(trim);
                    if (ConfigFileTagProvider.this.generateCode) {
                        PrintStream printStream4 = System.out;
                        printStream4.println("tagInfo.defineAllowedChildrenTags(\"" + trim + "\");");
                    }
                } else if ("higher-level-tags".equals(this.dependencyName)) {
                    this.tagInfo.defineHigherLevelTags(trim);
                    if (ConfigFileTagProvider.this.generateCode) {
                        PrintStream printStream5 = System.out;
                        printStream5.println("tagInfo.defineHigherLevelTags(\"" + trim + "\");");
                    }
                } else if ("close-before-copy-inside-tags".equals(this.dependencyName)) {
                    this.tagInfo.defineCloseBeforeCopyInsideTags(trim);
                    if (ConfigFileTagProvider.this.generateCode) {
                        PrintStream printStream6 = System.out;
                        printStream6.println("tagInfo.defineCloseBeforeCopyInsideTags(\"" + trim + "\");");
                    }
                } else if ("close-inside-copy-after-tags".equals(this.dependencyName)) {
                    this.tagInfo.defineCloseInsideCopyAfterTags(trim);
                    if (ConfigFileTagProvider.this.generateCode) {
                        PrintStream printStream7 = System.out;
                        printStream7.println("tagInfo.defineCloseInsideCopyAfterTags(\"" + trim + "\");");
                    }
                } else if ("close-before-tags".equals(this.dependencyName)) {
                    this.tagInfo.defineCloseBeforeTags(trim);
                    if (ConfigFileTagProvider.this.generateCode) {
                        PrintStream printStream8 = System.out;
                        printStream8.println("tagInfo.defineCloseBeforeTags(\"" + trim + "\");");
                    }
                }
            }
        }

        public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
            String str4 = str3;
            Attributes attributes2 = attributes;
            if ("tag".equals(str4)) {
                String value = attributes2.getValue("name");
                String value2 = attributes2.getValue(Constants.VAST_TRACKER_CONTENT);
                String value3 = attributes2.getValue("section");
                String value4 = attributes2.getValue("deprecated");
                String value5 = attributes2.getValue("unique");
                String value6 = attributes2.getValue("ignore-permitted");
                ContentType value7 = ContentType.toValue(value2);
                BelongsTo value8 = BelongsTo.toValue(value3);
                boolean z = true;
                boolean z2 = value4 != null && CleanerProperties.BOOL_ATT_TRUE.equals(value4);
                boolean z3 = value5 != null && CleanerProperties.BOOL_ATT_TRUE.equals(value5);
                boolean z4 = value6 != null && CleanerProperties.BOOL_ATT_TRUE.equals(value6);
                CloseTag closeTag = CloseTag.required;
                Display display = Display.any;
                String str5 = value6;
                String str6 = CleanerProperties.BOOL_ATT_TRUE;
                String str7 = value5;
                TagInfo tagInfo2 = r2;
                TagInfo tagInfo3 = new TagInfo(value, value7, value8, z2, z3, z4, closeTag, display);
                this.tagInfo = tagInfo2;
                if (ConfigFileTagProvider.this.generateCode) {
                    String replaceAll = "tagInfo = new TagInfo(\"#1\", #2, #3, #4, #5, #6);".replaceAll("#1", value);
                    String replaceAll2 = replaceAll.replaceAll("#2", ContentType.class.getCanonicalName() + "." + value7.name());
                    String replaceAll3 = replaceAll2.replaceAll("#3", BelongsTo.class.getCanonicalName() + "." + value8.name()).replaceAll("#4", Boolean.toString(value4 != null && str6.equals(value4))).replaceAll("#5", Boolean.toString(str7 != null && str6.equals(str7)));
                    if (str5 == null || !str6.equals(str5)) {
                        z = false;
                    }
                    System.out.println(replaceAll3.replaceAll("#6", Boolean.toString(z)));
                }
            } else if (!"tags".equals(str4)) {
                this.dependencyName = str4;
            }
        }

        public void endElement(String str, String str2, String str3) throws SAXException {
            if ("tag".equals(str3)) {
                TagInfo tagInfo2 = this.tagInfo;
                if (tagInfo2 != null) {
                    this.tagInfoMap.put(tagInfo2.getName(), this.tagInfo);
                    if (ConfigFileTagProvider.this.generateCode) {
                        PrintStream printStream = System.out;
                        printStream.println("this.put(\"" + this.tagInfo.getName() + "\", tagInfo);\n");
                    }
                }
                this.tagInfo = null;
            } else if (!"tags".equals(str3)) {
                this.dependencyName = null;
            }
        }
    }
}
