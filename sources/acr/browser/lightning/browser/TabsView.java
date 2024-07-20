package acr.browser.lightning.browser;

public interface TabsView {
    void tabAdded();

    void tabChanged(int i);

    void tabRemoved(int i);

    void tabsInitialized();
}
