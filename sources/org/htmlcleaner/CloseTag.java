package org.htmlcleaner;

public enum CloseTag {
    required(false, true),
    optional(true, true),
    forbidden(true, false);
    
    private final boolean endTagPermitted;
    private final boolean minimizedTagPermitted;

    private CloseTag(boolean z, boolean z2) {
        this.minimizedTagPermitted = z;
        this.endTagPermitted = z2;
    }

    public boolean isMinimizedTagPermitted() {
        return this.minimizedTagPermitted;
    }

    public boolean isEndTagPermitted() {
        return this.endTagPermitted;
    }
}
