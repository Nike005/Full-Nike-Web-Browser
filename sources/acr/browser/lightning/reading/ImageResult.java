package acr.browser.lightning.reading;

import org.jsoup.nodes.Element;

class ImageResult {
    private final String alt;
    public Element element;
    private final int height;
    private final boolean noFollow;
    private final String src;
    private final String title;
    public final Integer weight;
    private final int width;

    public ImageResult(String str, Integer num, String str2, int i, int i2, String str3, boolean z) {
        this.src = str;
        this.weight = num;
        this.title = str2;
        this.height = i;
        this.width = i2;
        this.alt = str3;
        this.noFollow = z;
    }
}
