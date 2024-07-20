package acr.browser.lightning.domain;

public class GeoData {
    private String cityName;
    private String countryCode;
    private long lastUpdateTime;

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String str) {
        this.cityName = str;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public long getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public void setLastUpdateTime(long j) {
        this.lastUpdateTime = j;
    }
}
