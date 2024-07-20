package org.htmlcleaner;

import java.util.regex.Pattern;

public class AttributeTransformationPatternImpl implements AttributeTransformation {
    private final Pattern attNamePattern;
    private final Pattern attValuePattern;
    private final String template;

    public AttributeTransformationPatternImpl(Pattern pattern, Pattern pattern2, String str) {
        this.attNamePattern = pattern;
        this.attValuePattern = pattern2;
        this.template = str;
    }

    public AttributeTransformationPatternImpl(String str, String str2, String str3) {
        Pattern pattern;
        Pattern pattern2 = null;
        if (str == null) {
            pattern = null;
        } else {
            pattern = Pattern.compile(str);
        }
        this.attNamePattern = pattern;
        this.attValuePattern = str2 != null ? Pattern.compile(str2) : pattern2;
        this.template = str3;
    }

    public boolean satisfy(String str, String str2) {
        Pattern pattern = this.attNamePattern;
        if (pattern != null && !pattern.matcher(str).find()) {
            return false;
        }
        Pattern pattern2 = this.attValuePattern;
        return pattern2 == null || pattern2.matcher(str2).find();
    }

    public String getTemplate() {
        return this.template;
    }
}
