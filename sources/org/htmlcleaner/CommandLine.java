package org.htmlcleaner;

import acr.browser.lightning.constant.Constants;
import com.appnext.base.p082b.C4899d;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.TreeMap;
import java.util.logging.Logger;
import org.htmlcleaner.audit.HtmlModificationListenerLogger;

public class CommandLine {
    private static final String OMITXMLDECL = "omitxmldecl";
    private static final String className;
    private static final Logger logger;

    private static String getArgValue(String[] strArr, String str, String str2) {
        for (String str3 : strArr) {
            int indexOf = str3.indexOf(61);
            if (indexOf >= 0) {
                String trim = str3.substring(0, indexOf).trim();
                String trim2 = str3.substring(indexOf + 1).trim();
                if (trim.toLowerCase().startsWith(str.toLowerCase())) {
                    return trim2;
                }
            }
        }
        return str2;
    }

    private static boolean toBoolean(String str) {
        return str != null && (C4899d.f4618fe.equalsIgnoreCase(str) || CleanerProperties.BOOL_ATT_TRUE.equalsIgnoreCase(str) || "yes".equalsIgnoreCase(str));
    }

    static {
        String name = CommandLine.class.getName();
        className = name;
        logger = Logger.getLogger(name);
    }

    public static void main(String[] strArr) throws IOException, XPatherException {
        String str;
        String str2;
        HtmlCleaner htmlCleaner;
        TagNode tagNode;
        OutputStream outputStream;
        String str3;
        String str4;
        String[] strArr2 = strArr;
        String argValue = getArgValue(strArr2, "src", "");
        if ("".equals(argValue)) {
            System.err.println("Usage: java -jar htmlcleanerXX.jar src=<url | file> [incharset=<charset>] [dest=<file>] [outcharset=<charset>] [taginfofile=<file>] [options...]");
            System.err.println("");
            System.err.println("where options include:");
            System.err.println("    outputtype=simple* | compact | browser-compact | pretty");
            System.err.println("    advancedxmlescape=true* | false");
            System.err.println("    usecdata=true* | false");
            System.err.println("    specialentities=true* | false");
            System.err.println("    unicodechars=true* | false");
            System.err.println("    omitunknowntags=true | false*");
            System.err.println("    treatunknowntagsascontent=true | false*");
            System.err.println("    omitdeprtags=true | false*");
            System.err.println("    treatdeprtagsascontent=true | false*");
            System.err.println("    omitcomments=true | false*");
            System.err.println("    omitxmldecl=true* | false");
            System.err.println("    omitdoctypedecl=true* | false");
            System.err.println("    omithtmlenvelope=true | false*");
            System.err.println("    useemptyelementtags=true* | false");
            System.err.println("    allowmultiwordattributes=true* | false");
            System.err.println("    allowhtmlinsideattributes=true | false*");
            System.err.println("    ignoreqe=true | false*");
            System.err.println("    namespacesaware=true* | false");
            System.err.println("    hyphenreplacement=<string value> [=]");
            System.err.println("    prunetags=<string value> []");
            System.err.println("    booleanatts=self* | empty | true");
            System.err.println("    nodebyxpath=<xpath expression>");
            System.err.println("    t:<sourcetagX>[=<desttag>[,<preserveatts>]]");
            System.err.println("    t:<sourcetagX>.<destattrY>[=<template>]");
            System.exit(1);
        }
        String argValue2 = getArgValue(strArr2, "incharset", "");
        String str5 = "UTF-8";
        if ("".equals(argValue2)) {
            argValue2 = str5;
        }
        String argValue3 = getArgValue(strArr2, "outcharset", "");
        if (!"".equals(argValue3)) {
            str5 = argValue3;
        }
        String argValue4 = getArgValue(strArr2, "dest", "");
        String argValue5 = getArgValue(strArr2, "outputtype", "");
        String argValue6 = getArgValue(strArr2, "advancedxmlescape", "");
        String argValue7 = getArgValue(strArr2, "usecdata", "");
        String argValue8 = getArgValue(strArr2, "specialentities", "");
        String argValue9 = getArgValue(strArr2, "unicodechars", "");
        String argValue10 = getArgValue(strArr2, "omitunknowntags", "");
        String argValue11 = getArgValue(strArr2, "treatunknowntagsascontent", "");
        String argValue12 = getArgValue(strArr2, "omitdeprtags", "");
        String argValue13 = getArgValue(strArr2, "treatdeprtagsascontent", "");
        String argValue14 = getArgValue(strArr2, "omitcomments", "");
        String str6 = str5;
        String argValue15 = getArgValue(strArr2, OMITXMLDECL, "");
        String str7 = argValue5;
        String argValue16 = getArgValue(strArr2, "omitdoctypedecl", "");
        String str8 = argValue4;
        String argValue17 = getArgValue(strArr2, "omithtmlenvelope", "");
        String str9 = argValue2;
        String argValue18 = getArgValue(strArr2, "useemptyelementtags", "");
        String str10 = argValue;
        String argValue19 = getArgValue(strArr2, "allowmultiwordattributes", "");
        String argValue20 = getArgValue(strArr2, "allowhtmlinsideattributes", "");
        String argValue21 = getArgValue(strArr2, "ignoreqe", "");
        String argValue22 = getArgValue(strArr2, "namespacesaware", "");
        String argValue23 = getArgValue(strArr2, "hyphenreplacement", "");
        String argValue24 = getArgValue(strArr2, "prunetags", "");
        String argValue25 = getArgValue(strArr2, "booleanatts", "");
        String argValue26 = getArgValue(strArr2, "nodebyxpath", "");
        String argValue27 = getArgValue(strArr2, "taginfofile", "");
        if (!"".equals(argValue27)) {
            str2 = argValue18;
            str = argValue17;
            htmlCleaner = new HtmlCleaner((ITagInfoProvider) new ConfigFileTagProvider(new File(argValue27)));
        } else {
            str2 = argValue18;
            str = argValue17;
            htmlCleaner = new HtmlCleaner();
        }
        CleanerProperties properties = htmlCleaner.getProperties();
        properties.addHtmlModificationListener(new HtmlModificationListenerLogger(logger));
        if (!"".equals(argValue10)) {
            properties.setOmitUnknownTags(toBoolean(argValue10));
        }
        if (!"".equals(argValue11)) {
            properties.setTreatUnknownTagsAsContent(toBoolean(argValue11));
        }
        if (!"".equals(argValue12)) {
            properties.setOmitDeprecatedTags(toBoolean(argValue12));
        }
        if (!"".equals(argValue13)) {
            properties.setTreatDeprecatedTagsAsContent(toBoolean(argValue13));
        }
        if (!"".equals(argValue6)) {
            properties.setAdvancedXmlEscape(toBoolean(argValue6));
        }
        if (!"".equals(argValue7)) {
            properties.setUseCdataForScriptAndStyle(toBoolean(argValue7));
        }
        if (!"".equals(argValue8)) {
            properties.setTranslateSpecialEntities(toBoolean(argValue8));
        }
        if (!"".equals(argValue9)) {
            properties.setRecognizeUnicodeChars(toBoolean(argValue9));
        }
        if (!"".equals(argValue14)) {
            properties.setOmitComments(toBoolean(argValue14));
        }
        if (!"".equals(argValue15)) {
            properties.setOmitXmlDeclaration(toBoolean(argValue15));
        }
        if (!"".equals(argValue16)) {
            properties.setOmitDoctypeDeclaration(toBoolean(argValue16));
        }
        String str11 = str;
        if (!"".equals(str11)) {
            properties.setOmitHtmlEnvelope(toBoolean(str11));
        }
        String str12 = str2;
        if (!"".equals(str12)) {
            properties.setUseEmptyElementTags(toBoolean(str12));
        }
        String str13 = argValue19;
        if (!"".equals(str13)) {
            properties.setAllowMultiWordAttributes(toBoolean(str13));
        }
        String str14 = argValue20;
        if (!"".equals(str14)) {
            properties.setAllowHtmlInsideAttributes(toBoolean(str14));
        }
        String str15 = argValue21;
        if (!"".equals(str15)) {
            properties.setIgnoreQuestAndExclam(toBoolean(str15));
        }
        String str16 = argValue22;
        if (!"".equals(str16)) {
            properties.setNamespacesAware(toBoolean(str16));
        }
        String str17 = argValue23;
        if (!"".equals(str17)) {
            properties.setHyphenReplacementInComment(str17);
        }
        String str18 = argValue24;
        if (!"".equals(str18)) {
            properties.setPruneTags(str18);
        }
        String str19 = argValue25;
        if (!"".equals(str19)) {
            properties.setBooleanAttributeValues(str19);
        }
        TreeMap treeMap = new TreeMap();
        int i = 0;
        for (String str20 : strArr) {
            if (str20.startsWith("t:") && str20.length() > 2) {
                String substring = str20.substring(2);
                int indexOf = substring.indexOf(61);
                if (indexOf <= 0) {
                    str3 = substring;
                } else {
                    str3 = substring.substring(0, indexOf);
                }
                if (indexOf <= 0) {
                    str4 = null;
                } else {
                    str4 = substring.substring(indexOf + 1);
                }
                treeMap.put(str3, str4);
            }
        }
        htmlCleaner.initCleanerTransformations(treeMap);
        long currentTimeMillis = System.currentTimeMillis();
        String lowerCase = str10.toLowerCase();
        if (lowerCase.startsWith(Constants.HTTP) || lowerCase.startsWith(Constants.HTTPS)) {
            tagNode = htmlCleaner.clean(new URL(lowerCase), str9);
        } else {
            tagNode = htmlCleaner.clean(new File(lowerCase), str9);
        }
        String str21 = argValue26;
        if (!"".equals(str21)) {
            Object[] evaluateXPath = tagNode.evaluateXPath(str21);
            while (true) {
                if (i >= evaluateXPath.length) {
                    break;
                } else if (evaluateXPath[i] instanceof TagNode) {
                    tagNode = (TagNode) evaluateXPath[i];
                    System.out.println("Node successfully found by XPath.");
                    break;
                } else {
                    i++;
                }
            }
            if (i == evaluateXPath.length) {
                System.out.println("Node not found by XPath expression - whole html tree is going to be serialized!");
            }
        }
        if (str8 == null || "".equals(str8.trim())) {
            outputStream = System.out;
        } else {
            outputStream = new FileOutputStream(str8);
        }
        String str22 = str7;
        if ("compact".equals(str22)) {
            new CompactXmlSerializer(properties).writeXmlToStream(tagNode, outputStream, str6);
        } else {
            String str23 = str6;
            if ("browser-compact".equals(str22)) {
                new BrowserCompactXmlSerializer(properties).writeXmlToStream(tagNode, outputStream, str23);
            } else if ("pretty".equals(str22)) {
                new PrettyXmlSerializer(properties).writeXmlToStream(tagNode, outputStream, str23);
            } else {
                new SimpleXmlSerializer(properties).writeXmlToStream(tagNode, outputStream, str23);
            }
        }
        System.out.println("Finished successfully in " + (System.currentTimeMillis() - currentTimeMillis) + "ms.");
    }
}
