package org.htmlcleaner;

import java.util.HashMap;
import java.util.Map;

public class CleanerTransformations {
    private TagTransformation globalTransformations = new TagTransformation();
    private Map mappings = new HashMap();

    public CleanerTransformations() {
    }

    public CleanerTransformations(Map map) {
        updateTagTransformations(map);
    }

    public void addTransformation(TagTransformation tagTransformation) {
        if (tagTransformation != null) {
            this.mappings.put(tagTransformation.getSourceTag(), tagTransformation);
        }
    }

    public void addGlobalTransformation(AttributeTransformation attributeTransformation) {
        this.globalTransformations.addAttributePatternTransformation(attributeTransformation);
    }

    public boolean hasTransformationForTag(String str) {
        return str != null && this.mappings.containsKey(str.toLowerCase());
    }

    public TagTransformation getTransformation(String str) {
        if (str != null) {
            return (TagTransformation) this.mappings.get(str.toLowerCase());
        }
        return null;
    }

    public void updateTagTransformations(String str, String str2) {
        boolean z = false;
        boolean z2 = true;
        if (str.indexOf(46) <= 0) {
            String str3 = null;
            if (str2 != null) {
                String[] strArr = C2401Utils.tokenize(str2, ",;");
                if (strArr.length > 0) {
                    str3 = strArr[0];
                }
                if (strArr.length > 1) {
                    if (CleanerProperties.BOOL_ATT_TRUE.equalsIgnoreCase(strArr[1]) || "yes".equalsIgnoreCase(strArr[1]) || "1".equals(strArr[1])) {
                        z = true;
                    }
                    z2 = z;
                }
            }
            addTransformation(new TagTransformation(str, str3, z2));
            return;
        }
        String[] strArr2 = C2401Utils.tokenize(str, ".");
        TagTransformation transformation = getTransformation(strArr2[0]);
        if (transformation != null) {
            transformation.addAttributeTransformation(strArr2[1], str2);
        }
    }

    public void updateTagTransformations(Map map) {
        for (Map.Entry entry : map.entrySet()) {
            updateTagTransformations((String) entry.getKey(), (String) entry.getValue());
        }
    }

    public Map<String, String> transformAttributes(String str, Map<String, String> map) {
        TagTransformation transformation = getTransformation(str);
        if (transformation != null) {
            map = transformation.applyTagTransformations(map);
        }
        return this.globalTransformations.applyTagTransformations(map);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = getTransformation(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getTagName(java.lang.String r2) {
        /*
            r1 = this;
            boolean r0 = r1.hasTransformationForTag(r2)
            if (r0 == 0) goto L_0x0010
            org.htmlcleaner.TagTransformation r0 = r1.getTransformation(r2)
            if (r0 == 0) goto L_0x0010
            java.lang.String r2 = r0.getDestTag()
        L_0x0010:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.htmlcleaner.CleanerTransformations.getTagName(java.lang.String):java.lang.String");
    }

    public void clear() {
        this.mappings.clear();
    }
}
