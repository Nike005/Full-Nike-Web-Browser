package org.htmlcleaner;

public enum Display {
    block(true, false),
    inline(false, true),
    any(true, false),
    none(true, false);
    
    private boolean afterTagLineBreakNeeded;
    private boolean leadingAndEndWhitespacesAllowed;

    private Display(boolean z, boolean z2) {
        this.afterTagLineBreakNeeded = z;
        this.leadingAndEndWhitespacesAllowed = z2;
    }

    public boolean isAfterTagLineBreakNeeded() {
        return this.afterTagLineBreakNeeded;
    }

    public boolean isLeadingAndEndWhitespacesAllowed() {
        return this.leadingAndEndWhitespacesAllowed;
    }
}
