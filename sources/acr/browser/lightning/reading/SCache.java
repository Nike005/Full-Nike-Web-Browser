package acr.browser.lightning.reading;

public interface SCache {
    JResult get(String str);

    int getSize();

    void put(String str, JResult jResult);
}
