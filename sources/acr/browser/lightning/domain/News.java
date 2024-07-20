package acr.browser.lightning.domain;

import java.io.Serializable;

public class News implements Serializable {
    private String date;

    /* renamed from: id */
    private int f4052id;
    private String imageLink;
    private boolean isAds;
    private String link;
    private String source;
    private String text;
    private String title;

    public String getSource() {
        return this.source;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public int getId() {
        return this.f4052id;
    }

    public void setId(int i) {
        this.f4052id = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String str) {
        this.link = str;
    }

    public String getImageLink() {
        return this.imageLink;
    }

    public void setImageLink(String str) {
        this.imageLink = str;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public boolean isAds() {
        return this.isAds;
    }

    public void setAds(boolean z) {
        this.isAds = z;
    }
}
