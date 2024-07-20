package org.htmlcleaner;

import com.appnext.banners.BannerAdRequest;

public enum BelongsTo {
    HEAD_AND_BODY(BannerAdRequest.TYPE_ALL),
    HEAD("head"),
    BODY("body");
    
    private final String dbCode;

    private BelongsTo(String str) {
        this.dbCode = str;
    }

    public String getDbCode() {
        return this.dbCode;
    }

    public static BelongsTo toValue(Object obj) {
        if (obj instanceof BelongsTo) {
            return (BelongsTo) obj;
        }
        if (obj != null) {
            String trim = obj.toString().trim();
            for (BelongsTo belongsTo : values()) {
                if (belongsTo.getDbCode().equalsIgnoreCase(trim) || belongsTo.name().equalsIgnoreCase(trim)) {
                    return belongsTo;
                }
            }
        }
        return null;
    }
}
