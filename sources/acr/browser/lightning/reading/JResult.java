package acr.browser.lightning.reading;

import com.mopub.mobileads.VastIconXmlManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JResult implements Serializable {
    private String authorDescription;
    private String authorName;
    private String canonicalUrl;
    private Date date;
    private String description;
    private String faviconUrl;
    private String imageUrl;
    private List<ImageResult> images = null;
    private Collection<String> keywords;
    private String language;
    private final List<Map<String, String>> links = new ArrayList();
    private String originalUrl;
    private String rssUrl;
    private String sitename;
    private String text;
    private String title;
    private String type;
    private String url;
    private String videoUrl;

    public String getUrl() {
        String str = this.url;
        return str == null ? "" : str;
    }

    public JResult setUrl(String str) {
        this.url = str;
        return this;
    }

    public JResult setOriginalUrl(String str) {
        this.originalUrl = str;
        return this;
    }

    public String getOriginalUrl() {
        return this.originalUrl;
    }

    public JResult setCanonicalUrl(String str) {
        this.canonicalUrl = str;
        return this;
    }

    public String getCanonicalUrl() {
        return this.canonicalUrl;
    }

    public String getFaviconUrl() {
        String str = this.faviconUrl;
        return str == null ? "" : str;
    }

    public JResult setFaviconUrl(String str) {
        this.faviconUrl = str;
        return this;
    }

    public JResult setRssUrl(String str) {
        this.rssUrl = str;
        return this;
    }

    public String getRssUrl() {
        String str = this.rssUrl;
        return str == null ? "" : str;
    }

    public String getDescription() {
        String str = this.description;
        return str == null ? "" : str;
    }

    public JResult setDescription(String str) {
        this.description = str;
        return this;
    }

    public String getAuthorName() {
        String str = this.authorName;
        return str == null ? "" : str;
    }

    public JResult setAuthorName(String str) {
        this.authorName = str;
        return this;
    }

    public String getAuthorDescription() {
        String str = this.authorDescription;
        return str == null ? "" : str;
    }

    public JResult setAuthorDescription(String str) {
        this.authorDescription = str;
        return this;
    }

    public String getImageUrl() {
        String str = this.imageUrl;
        return str == null ? "" : str;
    }

    public JResult setImageUrl(String str) {
        this.imageUrl = str;
        return this;
    }

    public String getText() {
        String str = this.text;
        return str == null ? "" : str;
    }

    public JResult setText(String str) {
        this.text = str;
        return this;
    }

    public String getTitle() {
        String str = this.title;
        return str == null ? "" : str;
    }

    public JResult setTitle(String str) {
        this.title = str;
        return this;
    }

    public String getVideoUrl() {
        String str = this.videoUrl;
        return str == null ? "" : str;
    }

    public JResult setVideoUrl(String str) {
        this.videoUrl = str;
        return this;
    }

    public JResult setDate(Date date2) {
        this.date = date2;
        return this;
    }

    public Collection<String> getKeywords() {
        return this.keywords;
    }

    public void setKeywords(Collection<String> collection) {
        this.keywords = collection;
    }

    public Date getDate() {
        return this.date;
    }

    public List<ImageResult> getImages() {
        List<ImageResult> list = this.images;
        return list == null ? Collections.emptyList() : list;
    }

    public int getImagesCount() {
        List<ImageResult> list = this.images;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void setImages(List<ImageResult> list) {
        this.images = list;
    }

    public void addLink(String str, String str2, Integer num) {
        HashMap hashMap = new HashMap();
        hashMap.put("url", str);
        hashMap.put("text", str2);
        hashMap.put(VastIconXmlManager.OFFSET, String.valueOf(num));
        this.links.add(hashMap);
    }

    public List<Map<String, String>> getLinks() {
        List<Map<String, String>> list = this.links;
        return list == null ? Collections.emptyList() : list;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getSitename() {
        return this.sitename;
    }

    public void setSitename(String str) {
        this.sitename = str;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public String toString() {
        return "title:" + getTitle() + " imageUrl:" + getImageUrl() + " text:" + this.text;
    }
}
