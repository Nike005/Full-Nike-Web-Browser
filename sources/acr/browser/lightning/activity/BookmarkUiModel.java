package acr.browser.lightning.activity;

public class BookmarkUiModel {
    private String mCurrentFolder;

    public void setCurrentFolder(String str) {
        this.mCurrentFolder = str;
    }

    public boolean isRootFolder() {
        return this.mCurrentFolder == null;
    }

    public String getCurrentFolder() {
        return this.mCurrentFolder;
    }
}
