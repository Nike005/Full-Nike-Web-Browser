package org.htmlcleaner;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class TagTransformation {
    public static String VAR_END = "}";
    public static String VAR_START = "${";
    private List<AttributeTransformation> attributePatternTransformations;
    private Map<String, String> attributeTransformations;
    private String destTag;
    private boolean preserveSourceAttributes;
    private String sourceTag;

    public TagTransformation() {
        this.attributeTransformations = new LinkedHashMap();
        this.attributePatternTransformations = new ArrayList();
        this.preserveSourceAttributes = true;
    }

    public TagTransformation(String str, String str2, boolean z) {
        this.attributeTransformations = new LinkedHashMap();
        this.attributePatternTransformations = new ArrayList();
        this.sourceTag = str.toLowerCase();
        if (str2 == null) {
            this.destTag = null;
        } else {
            this.destTag = C2401Utils.isValidXmlIdentifier(str2) ? str2.toLowerCase() : str;
        }
        this.preserveSourceAttributes = z;
    }

    public TagTransformation(String str, String str2) {
        this(str, str2, true);
    }

    public TagTransformation(String str) {
        this(str, (String) null);
    }

    public void addAttributeTransformation(String str, String str2) {
        this.attributeTransformations.put(str.toLowerCase(), str2);
    }

    public void addAttributePatternTransformation(Pattern pattern, String str) {
        this.attributePatternTransformations.add(new AttributeTransformationPatternImpl(pattern, (Pattern) null, str));
    }

    public void addAttributePatternTransformation(Pattern pattern, Pattern pattern2, String str) {
        addAttributePatternTransformation(new AttributeTransformationPatternImpl(pattern, pattern2, str));
    }

    public void addAttributePatternTransformation(AttributeTransformation attributeTransformation) {
        if (this.attributePatternTransformations == null) {
            this.attributePatternTransformations = new ArrayList();
        }
        this.attributePatternTransformations.add(attributeTransformation);
    }

    public void addAttributeTransformation(String str) {
        addAttributeTransformation(str, (String) null);
    }

    /* access modifiers changed from: package-private */
    public boolean hasAttributeTransformations() {
        return (this.attributeTransformations == null && this.attributePatternTransformations == null) ? false : true;
    }

    /* access modifiers changed from: package-private */
    public String getSourceTag() {
        return this.sourceTag;
    }

    /* access modifiers changed from: package-private */
    public String getDestTag() {
        return this.destTag;
    }

    /* access modifiers changed from: package-private */
    public boolean isPreserveSourceAttributes() {
        return this.preserveSourceAttributes;
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> getAttributeTransformations() {
        return this.attributeTransformations;
    }

    public Map<String, String> applyTagTransformations(Map<String, String> map) {
        boolean isPreserveSourceAttributes = isPreserveSourceAttributes();
        boolean hasAttributeTransformations = hasAttributeTransformations();
        if (!hasAttributeTransformations && isPreserveSourceAttributes) {
            return map;
        }
        LinkedHashMap linkedHashMap = isPreserveSourceAttributes ? new LinkedHashMap(map) : new LinkedHashMap();
        if (hasAttributeTransformations) {
            for (Map.Entry next : getAttributeTransformations().entrySet()) {
                String str = (String) next.getKey();
                String str2 = (String) next.getValue();
                if (str2 == null) {
                    linkedHashMap.remove(str);
                } else {
                    linkedHashMap.put(str, evaluateTemplate(str2, map));
                }
            }
            for (AttributeTransformation next2 : this.attributePatternTransformations) {
                for (Map.Entry next3 : map.entrySet()) {
                    String str3 = (String) next3.getKey();
                    if (next2.satisfy(str3, (String) next3.getValue())) {
                        String template = next2.getTemplate();
                        if (template == null) {
                            linkedHashMap.remove(str3);
                        } else {
                            linkedHashMap.put(str3, evaluateTemplate(template, map));
                        }
                    }
                }
            }
        }
        return linkedHashMap;
    }

    public String evaluateTemplate(String str, Map<String, String> map) {
        if (str == null) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer();
        int indexOf = str.indexOf(VAR_START);
        int i = -1;
        while (indexOf >= 0 && indexOf < str.length()) {
            stringBuffer.append(str.substring(i + 1, indexOf));
            i = str.indexOf(VAR_END, indexOf);
            if (i > indexOf) {
                String str2 = "";
                String str3 = map != null ? map.get(str.substring(VAR_START.length() + indexOf, i).toLowerCase()) : str2;
                if (str3 != null) {
                    str2 = str3.toString();
                }
                stringBuffer.append(str2);
            }
            indexOf = str.indexOf(VAR_START, Math.max(VAR_END.length() + i, indexOf + 1));
        }
        stringBuffer.append(str.substring(i + 1));
        return stringBuffer.toString();
    }
}
