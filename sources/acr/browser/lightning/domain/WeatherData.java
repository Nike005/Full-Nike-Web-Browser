package acr.browser.lightning.domain;

public class WeatherData {
    private int code;
    private boolean isCecius;
    private long lastUpdateTime;
    private String location;
    private int temp;
    private String text;

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String str) {
        this.location = str;
    }

    public int getTemp() {
        return this.temp;
    }

    public void setTemp(int i) {
        this.temp = i;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public long getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public void setLastUpdateTime(long j) {
        this.lastUpdateTime = j;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public boolean isCecius() {
        return this.isCecius;
    }

    public void setCecius(boolean z) {
        this.isCecius = z;
    }

    public String toString() {
        return "WeatherData{location='" + this.location + '\'' + ", temp=" + this.temp + ", text='" + this.text + '\'' + '}';
    }
}
