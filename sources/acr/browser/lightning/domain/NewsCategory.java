package acr.browser.lightning.domain;

import java.io.Serializable;
import java.util.List;

public class NewsCategory implements Serializable {
    private String name;
    private List<News> newsList;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public List<News> getNewsList() {
        return this.newsList;
    }

    public void setNewsList(List<News> list) {
        this.newsList = list;
    }
}
