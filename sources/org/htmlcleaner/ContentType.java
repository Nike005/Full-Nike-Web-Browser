package org.htmlcleaner;

import com.appnext.banners.BannerAdRequest;

public enum ContentType {
    all(BannerAdRequest.TYPE_ALL),
    none("none"),
    text("text");
    
    private final String dbCode;

    private ContentType(String str) {
        this.dbCode = str;
    }

    public String getDbCode() {
        return this.dbCode;
    }

    public static ContentType toValue(Object obj) {
        if (obj instanceof ContentType) {
            return (ContentType) obj;
        }
        if (obj != null) {
            String trim = obj.toString().trim();
            for (ContentType contentType : values()) {
                if (contentType.getDbCode().equalsIgnoreCase(trim) || contentType.name().equalsIgnoreCase(trim)) {
                    return contentType;
                }
            }
        }
        return null;
    }
}
