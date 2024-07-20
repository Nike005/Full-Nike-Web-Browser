package acr.browser.lightning.utils;

import acr.browser.lightning.database.HistoryItem;
import acr.browser.lightning.domain.GeoData;
import acr.browser.lightning.domain.WeatherData;
import acr.browser.lightning.preference.PreferenceManager;
import android.app.Activity;
import android.location.Location;
import android.util.Log;
import com.google.android.gms.actions.SearchIntents;
import com.wnikebrow_13999769.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StartPageLoader {
    private static final long LOCATION_UPDATE_PERIOD = 7200000;
    private static final long WEATHER_UPDATE_PERIOD = 1200000;
    private static Map<Integer, Integer> weatherIconMap = new HashMap();
    private static Map<Integer, Integer> weatherIconMap2 = new HashMap();
    private static Map<Integer, Integer> weatherIconMap3 = new HashMap();

    public interface BoormarksHandler {
        void onResult(List<HistoryItem> list);
    }

    public interface GeoDataHandler {
        void onResult(GeoData geoData);
    }

    public interface LocationHandler {
        void onResult(Location location);
    }

    public interface SearchEngineHandler {
        void onResult(Integer num, String str);
    }

    public interface WeatherCallback {
        void onWeatherResult(WeatherData weatherData);
    }

    static {
        Map<Integer, Integer> map = weatherIconMap;
        Integer valueOf = Integer.valueOf(R.drawable.simple_weather_icon_30);
        map.put(0, valueOf);
        weatherIconMap.put(1, Integer.valueOf(R.drawable.simple_weather_icon_23));
        weatherIconMap.put(2, valueOf);
        Map<Integer, Integer> map2 = weatherIconMap;
        Integer valueOf2 = Integer.valueOf(R.drawable.simple_weather_icon_27);
        map2.put(3, valueOf2);
        weatherIconMap.put(4, valueOf2);
        Map<Integer, Integer> map3 = weatherIconMap;
        Integer valueOf3 = Integer.valueOf(R.drawable.simple_weather_icon_26);
        map3.put(5, valueOf3);
        weatherIconMap.put(6, valueOf3);
        weatherIconMap.put(7, valueOf3);
        Map<Integer, Integer> map4 = weatherIconMap;
        Integer valueOf4 = Integer.valueOf(R.drawable.simple_weather_icon_10);
        map4.put(8, valueOf4);
        weatherIconMap.put(9, valueOf4);
        weatherIconMap.put(10, Integer.valueOf(R.drawable.simple_weather_icon_21));
        weatherIconMap.put(11, Integer.valueOf(R.drawable.simple_weather_icon_23));
        weatherIconMap.put(12, Integer.valueOf(R.drawable.simple_weather_icon_23));
        Map<Integer, Integer> map5 = weatherIconMap;
        Integer valueOf5 = Integer.valueOf(R.drawable.simple_weather_icon_25);
        map5.put(13, valueOf5);
        weatherIconMap.put(14, valueOf5);
        weatherIconMap.put(15, valueOf5);
        weatherIconMap.put(16, valueOf5);
        weatherIconMap.put(17, Integer.valueOf(R.drawable.simple_weather_icon_28));
        weatherIconMap.put(18, Integer.valueOf(R.drawable.simple_weather_icon_28));
        weatherIconMap.put(19, valueOf4);
        weatherIconMap.put(20, valueOf4);
        weatherIconMap.put(21, valueOf4);
        weatherIconMap.put(22, valueOf4);
        weatherIconMap.put(23, valueOf);
        weatherIconMap.put(24, valueOf);
        Map<Integer, Integer> map6 = weatherIconMap;
        Integer valueOf6 = Integer.valueOf(R.drawable.simple_weather_icon_04);
        map6.put(25, valueOf6);
        weatherIconMap.put(26, valueOf6);
        weatherIconMap.put(27, Integer.valueOf(R.drawable.simple_weather_icon_07));
        weatherIconMap.put(28, Integer.valueOf(R.drawable.simple_weather_icon_03));
        weatherIconMap.put(29, Integer.valueOf(R.drawable.simple_weather_icon_07));
        weatherIconMap.put(30, Integer.valueOf(R.drawable.simple_weather_icon_03));
        weatherIconMap.put(31, Integer.valueOf(R.drawable.simple_weather_icon_02));
        weatherIconMap.put(32, Integer.valueOf(R.drawable.simple_weather_icon_01));
        weatherIconMap.put(33, Integer.valueOf(R.drawable.simple_weather_icon_02));
        weatherIconMap.put(34, Integer.valueOf(R.drawable.simple_weather_icon_01));
        weatherIconMap.put(35, valueOf3);
        weatherIconMap.put(36, Integer.valueOf(R.drawable.simple_weather_icon_01));
        weatherIconMap.put(37, valueOf2);
        weatherIconMap.put(38, valueOf2);
        weatherIconMap.put(39, valueOf2);
        weatherIconMap.put(40, Integer.valueOf(R.drawable.simple_weather_icon_21));
        weatherIconMap.put(41, valueOf5);
        weatherIconMap.put(42, valueOf5);
        weatherIconMap.put(43, valueOf5);
        weatherIconMap.put(44, valueOf6);
        weatherIconMap.put(45, valueOf2);
        weatherIconMap.put(46, valueOf5);
        weatherIconMap.put(47, valueOf2);
        weatherIconMap.put(3200, valueOf6);
        Map<Integer, Integer> map7 = weatherIconMap2;
        Integer valueOf7 = Integer.valueOf(R.drawable.weather_2_wind);
        map7.put(0, valueOf7);
        weatherIconMap2.put(1, Integer.valueOf(R.drawable.weather_2_big_rain));
        weatherIconMap2.put(2, valueOf7);
        Map<Integer, Integer> map8 = weatherIconMap2;
        Integer valueOf8 = Integer.valueOf(R.drawable.weather_2_thunder);
        map8.put(3, valueOf8);
        weatherIconMap2.put(4, valueOf8);
        weatherIconMap2.put(5, Integer.valueOf(R.drawable.weather_2_show_rain));
        weatherIconMap2.put(6, Integer.valueOf(R.drawable.weather_2_show_rain));
        weatherIconMap2.put(7, Integer.valueOf(R.drawable.weather_2_show_rain));
        Map<Integer, Integer> map9 = weatherIconMap2;
        Integer valueOf9 = Integer.valueOf(R.drawable.weather_2_fog);
        map9.put(8, valueOf9);
        weatherIconMap2.put(9, valueOf9);
        weatherIconMap2.put(10, Integer.valueOf(R.drawable.weather_2_rain));
        weatherIconMap2.put(11, Integer.valueOf(R.drawable.weather_2_big_rain));
        weatherIconMap2.put(12, Integer.valueOf(R.drawable.weather_2_big_rain));
        Map<Integer, Integer> map10 = weatherIconMap2;
        Integer valueOf10 = Integer.valueOf(R.drawable.weather_2_snow);
        map10.put(13, valueOf10);
        weatherIconMap2.put(14, valueOf10);
        weatherIconMap2.put(15, valueOf10);
        weatherIconMap2.put(16, valueOf10);
        weatherIconMap2.put(17, Integer.valueOf(R.drawable.weather_2_hail));
        weatherIconMap2.put(18, Integer.valueOf(R.drawable.weather_2_hail));
        weatherIconMap2.put(19, valueOf9);
        weatherIconMap2.put(20, valueOf9);
        weatherIconMap2.put(21, valueOf9);
        weatherIconMap2.put(22, valueOf9);
        weatherIconMap2.put(23, valueOf7);
        weatherIconMap2.put(24, valueOf7);
        weatherIconMap2.put(25, Integer.valueOf(R.drawable.weather_2_clouds));
        weatherIconMap2.put(26, Integer.valueOf(R.drawable.weather_2_clouds));
        weatherIconMap2.put(27, Integer.valueOf(R.drawable.weather_2_clouds_moon_night));
        weatherIconMap2.put(28, Integer.valueOf(R.drawable.weather_2_clouds_sun_day));
        weatherIconMap2.put(29, Integer.valueOf(R.drawable.weather_2_clouds_moon_night));
        weatherIconMap2.put(30, Integer.valueOf(R.drawable.weather_2_clouds_sun_day));
        weatherIconMap2.put(31, Integer.valueOf(R.drawable.weather_2_clear_night));
        weatherIconMap2.put(32, Integer.valueOf(R.drawable.weather_2_sun));
        weatherIconMap2.put(33, Integer.valueOf(R.drawable.weather_2_clear_night));
        weatherIconMap2.put(34, Integer.valueOf(R.drawable.weather_2_sun));
        weatherIconMap2.put(35, Integer.valueOf(R.drawable.weather_2_show_rain));
        weatherIconMap2.put(36, Integer.valueOf(R.drawable.weather_2_sun));
        weatherIconMap2.put(37, valueOf8);
        weatherIconMap2.put(38, valueOf8);
        weatherIconMap2.put(39, valueOf8);
        weatherIconMap2.put(40, Integer.valueOf(R.drawable.weather_2_rain));
        weatherIconMap2.put(41, valueOf10);
        weatherIconMap2.put(42, valueOf10);
        weatherIconMap2.put(43, valueOf10);
        weatherIconMap2.put(44, Integer.valueOf(R.drawable.weather_2_clouds));
        weatherIconMap2.put(45, valueOf8);
        weatherIconMap2.put(46, valueOf10);
        weatherIconMap2.put(47, valueOf8);
        weatherIconMap2.put(3200, Integer.valueOf(R.drawable.weather_2_clouds));
        weatherIconMap3.put(0, Integer.valueOf(R.drawable.wsymbol_0007_fog));
        Map<Integer, Integer> map11 = weatherIconMap3;
        Integer valueOf11 = Integer.valueOf(R.drawable.wsymbol_0018_cloudy_with_heavy_rain);
        map11.put(1, valueOf11);
        weatherIconMap3.put(2, Integer.valueOf(R.drawable.wsymbol_0007_fog));
        Map<Integer, Integer> map12 = weatherIconMap3;
        Integer valueOf12 = Integer.valueOf(R.drawable.wsymbol_0024_thunderstorms);
        map12.put(3, valueOf12);
        weatherIconMap3.put(4, valueOf12);
        weatherIconMap3.put(5, Integer.valueOf(R.drawable.wsymbol_0021_cloudy_with_sleet));
        weatherIconMap3.put(6, Integer.valueOf(R.drawable.wsymbol_0021_cloudy_with_sleet));
        weatherIconMap3.put(7, Integer.valueOf(R.drawable.wsymbol_0021_cloudy_with_sleet));
        Map<Integer, Integer> map13 = weatherIconMap3;
        Integer valueOf13 = Integer.valueOf(R.drawable.wsymbol_0006_mist);
        map13.put(8, valueOf13);
        weatherIconMap3.put(9, valueOf13);
        weatherIconMap3.put(10, Integer.valueOf(R.drawable.wsymbol_0017_cloudy_with_light_rain));
        weatherIconMap3.put(11, valueOf11);
        weatherIconMap3.put(12, valueOf11);
        Map<Integer, Integer> map14 = weatherIconMap3;
        Integer valueOf14 = Integer.valueOf(R.drawable.wsymbol_0020_cloudy_with_heavy_snow);
        map14.put(13, valueOf14);
        weatherIconMap3.put(14, valueOf14);
        weatherIconMap3.put(15, valueOf14);
        weatherIconMap3.put(16, valueOf14);
        weatherIconMap3.put(17, valueOf11);
        weatherIconMap3.put(18, valueOf11);
        weatherIconMap3.put(19, valueOf13);
        weatherIconMap3.put(20, valueOf13);
        weatherIconMap3.put(21, valueOf13);
        weatherIconMap3.put(22, valueOf13);
        weatherIconMap3.put(23, Integer.valueOf(R.drawable.wsymbol_0007_fog));
        weatherIconMap3.put(24, Integer.valueOf(R.drawable.wsymbol_0007_fog));
        weatherIconMap3.put(25, Integer.valueOf(R.drawable.wsymbol_0003_white_cloud));
        weatherIconMap3.put(26, Integer.valueOf(R.drawable.wsymbol_0003_white_cloud));
        weatherIconMap3.put(27, Integer.valueOf(R.drawable.wsymbol_0002_sunny_intervals));
        weatherIconMap3.put(28, Integer.valueOf(R.drawable.wsymbol_0002_sunny_intervals));
        weatherIconMap3.put(29, Integer.valueOf(R.drawable.wsymbol_0002_sunny_intervals));
        weatherIconMap3.put(30, Integer.valueOf(R.drawable.wsymbol_0002_sunny_intervals));
        Map<Integer, Integer> map15 = weatherIconMap3;
        Integer valueOf15 = Integer.valueOf(R.drawable.wsymbol_0001_sunny);
        map15.put(31, valueOf15);
        weatherIconMap3.put(32, valueOf15);
        weatherIconMap3.put(33, valueOf15);
        weatherIconMap3.put(34, valueOf15);
        weatherIconMap3.put(35, Integer.valueOf(R.drawable.wsymbol_0021_cloudy_with_sleet));
        weatherIconMap3.put(36, valueOf15);
        weatherIconMap3.put(37, valueOf12);
        weatherIconMap3.put(38, valueOf12);
        weatherIconMap3.put(39, valueOf12);
        weatherIconMap3.put(40, Integer.valueOf(R.drawable.wsymbol_0017_cloudy_with_light_rain));
        weatherIconMap3.put(41, valueOf14);
        weatherIconMap3.put(42, valueOf14);
        weatherIconMap3.put(43, valueOf14);
        weatherIconMap3.put(44, Integer.valueOf(R.drawable.wsymbol_0003_white_cloud));
        weatherIconMap3.put(45, valueOf12);
        weatherIconMap3.put(46, valueOf14);
        weatherIconMap3.put(47, valueOf12);
        weatherIconMap3.put(3200, Integer.valueOf(R.drawable.wsymbol_0003_white_cloud));
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x009d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getIconId(java.lang.String r4, int r5) {
        /*
            int r0 = r4.hashCode()
            r1 = -467747644(0xffffffffe41ebcc4, float:-1.171275E22)
            r2 = 2
            r3 = 1
            if (r0 == r1) goto L_0x002a
            r1 = 126390669(0x788918d, float:2.0548553E-34)
            if (r0 == r1) goto L_0x0020
            r1 = 1572114118(0x5db48ec6, float:1.62631926E18)
            if (r0 == r1) goto L_0x0016
            goto L_0x0034
        L_0x0016:
            java.lang.String r0 = "weatherSimple"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0034
            r4 = 0
            goto L_0x0035
        L_0x0020:
            java.lang.String r0 = "weatherFlat"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0034
            r4 = 2
            goto L_0x0035
        L_0x002a:
            java.lang.String r0 = "weatherDetailed"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0034
            r4 = 1
            goto L_0x0035
        L_0x0034:
            r4 = -1
        L_0x0035:
            r0 = 2131231488(0x7f080300, float:1.8079058E38)
            if (r4 == 0) goto L_0x009d
            if (r4 == r3) goto L_0x007c
            if (r4 == r2) goto L_0x005b
            java.util.Map<java.lang.Integer, java.lang.Integer> r4 = weatherIconMap
            java.lang.Integer r1 = java.lang.Integer.valueOf(r5)
            boolean r4 = r4.containsKey(r1)
            if (r4 == 0) goto L_0x005a
            java.util.Map<java.lang.Integer, java.lang.Integer> r4 = weatherIconMap
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.Object r4 = r4.get(r5)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r0 = r4.intValue()
        L_0x005a:
            return r0
        L_0x005b:
            java.util.Map<java.lang.Integer, java.lang.Integer> r4 = weatherIconMap3
            java.lang.Integer r0 = java.lang.Integer.valueOf(r5)
            boolean r4 = r4.containsKey(r0)
            if (r4 == 0) goto L_0x0078
            java.util.Map<java.lang.Integer, java.lang.Integer> r4 = weatherIconMap3
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.Object r4 = r4.get(r5)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            goto L_0x007b
        L_0x0078:
            r4 = 2131231524(0x7f080324, float:1.8079131E38)
        L_0x007b:
            return r4
        L_0x007c:
            java.util.Map<java.lang.Integer, java.lang.Integer> r4 = weatherIconMap2
            java.lang.Integer r0 = java.lang.Integer.valueOf(r5)
            boolean r4 = r4.containsKey(r0)
            if (r4 == 0) goto L_0x0099
            java.util.Map<java.lang.Integer, java.lang.Integer> r4 = weatherIconMap2
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.Object r4 = r4.get(r5)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            goto L_0x009c
        L_0x0099:
            r4 = 2131231509(0x7f080315, float:1.8079101E38)
        L_0x009c:
            return r4
        L_0x009d:
            java.util.Map<java.lang.Integer, java.lang.Integer> r4 = weatherIconMap
            java.lang.Integer r1 = java.lang.Integer.valueOf(r5)
            boolean r4 = r4.containsKey(r1)
            if (r4 == 0) goto L_0x00b9
            java.util.Map<java.lang.Integer, java.lang.Integer> r4 = weatherIconMap
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.Object r4 = r4.get(r5)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r0 = r4.intValue()
        L_0x00b9:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.utils.StartPageLoader.getIconId(java.lang.String, int):int");
    }

    public static void getWeather(Activity activity, final PreferenceManager preferenceManager, final WeatherCallback weatherCallback, boolean z) {
        WeatherData weatherData = preferenceManager.getWeatherData();
        if (!z && weatherData != null && !weatherData.getLocation().equals("") && new Date().getTime() - weatherData.getLastUpdateTime() <= WEATHER_UPDATE_PERIOD) {
            weatherCallback.onWeatherResult(weatherData);
        } else if (!preferenceManager.hasCity()) {
            LocationService.getLocationManager(activity).getLocation(new LocationHandler() {
                public void onResult(Location location) {
                    if (location == null) {
                        StartPageLoader.requestGeoData(preferenceManager, new GeoDataHandler() {
                            public void onResult(GeoData geoData) {
                                if (!geoData.getCityName().equals("")) {
                                    StartPageLoader.getWeather(preferenceManager, geoData.getCityName(), weatherCallback);
                                } else {
                                    StartPageLoader.getWeather(preferenceManager, preferenceManager.getCity(), weatherCallback);
                                }
                            }
                        });
                    } else {
                        StartPageLoader.getWeather(preferenceManager, location, weatherCallback);
                    }
                }
            });
        } else {
            getWeather(preferenceManager, preferenceManager.getCity(), weatherCallback);
        }
    }

    public static void getWeather(PreferenceManager preferenceManager, String str, WeatherCallback weatherCallback) {
        requestWeather(preferenceManager, buildUrl(str), weatherCallback);
    }

    public static void getWeather(PreferenceManager preferenceManager, Location location, WeatherCallback weatherCallback) {
        requestWeather(preferenceManager, buildUrl(location), weatherCallback);
    }

    public static void requestWeather(final PreferenceManager preferenceManager, String str, final WeatherCallback weatherCallback) {
        new OkHttpClient().newCall(new Request.Builder().url(str).build()).enqueue(new Callback() {
            public void onFailure(Call call, IOException iOException) {
                weatherCallback.onWeatherResult((WeatherData) null);
            }

            public void onResponse(Call call, Response response) throws IOException {
                WeatherData access$000 = StartPageLoader.parseWeatherData(response.body().string());
                response.body().close();
                weatherCallback.onWeatherResult(access$000);
                if (access$000 != null) {
                    access$000.setLastUpdateTime(new Date().getTime());
                    access$000.setCecius(preferenceManager.getWeatherData().isCecius());
                }
                preferenceManager.setWeatherDataData(access$000);
                response.close();
            }
        });
    }

    private static String buildUrl(Location location) {
        return HttpUrl.parse("https://query.yahooapis.com/v1/public/yql?format=json&rnd=20175017&diagnostics=true&q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text=%22(" + location.getLatitude() + "," + location.getLongitude() + ")%22)").newBuilder().build().toString();
    }

    public static String buildUrl(String str) {
        return HttpUrl.parse("https://query.yahooapis.com/v1/public/yql?format=json&rnd=20175017&diagnostics=true&q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text=%22" + str + "%22)").newBuilder().build().toString();
    }

    /* access modifiers changed from: private */
    public static WeatherData parseWeatherData(String str) {
        try {
            WeatherData weatherData = new WeatherData();
            JSONObject jSONObject = new JSONObject(str).getJSONObject(SearchIntents.EXTRA_QUERY).getJSONObject("results").getJSONObject("channel");
            weatherData.setLocation(jSONObject.getJSONObject("location").getString("city"));
            weatherData.setTemp(jSONObject.getJSONObject("item").getJSONObject("condition").getInt("temp"));
            weatherData.setText(jSONObject.getJSONObject("item").getJSONObject("condition").getString("text"));
            weatherData.setCode(jSONObject.getJSONObject("item").getJSONObject("condition").getInt("code"));
            Log.w("weather", weatherData.toString());
            return weatherData;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void requestGeoData(final PreferenceManager preferenceManager, final GeoDataHandler geoDataHandler) {
        OkHttpClient okHttpClient = new OkHttpClient();
        final GeoData geoData = preferenceManager.getGeoData();
        if (geoData.getCountryCode().equals("") || new Date().getTime() - geoData.getLastUpdateTime() > LOCATION_UPDATE_PERIOD) {
            okHttpClient.newCall(new Request.Builder().url("http://www.geoplugin.net/json.gp").build()).enqueue(new Callback() {
                public void onFailure(Call call, IOException iOException) {
                    geoDataHandler.onResult(geoData);
                }

                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        JSONObject jSONObject = new JSONObject(response.body().string());
                        response.body().close();
                        GeoData geoData = new GeoData();
                        geoData.setCityName(jSONObject.getString("geoplugin_city"));
                        geoData.setCountryCode(jSONObject.getString("geoplugin_countryCode").toLowerCase());
                        geoData.setLastUpdateTime(new Date().getTime());
                        if (!geoData.getCountryCode().equals("")) {
                            preferenceManager.setGeoData(geoData);
                            geoDataHandler.onResult(geoData);
                            return;
                        }
                        geoDataHandler.onResult(geoData);
                    } catch (JSONException unused) {
                        geoDataHandler.onResult(geoData);
                    }
                }
            });
        } else {
            geoDataHandler.onResult(geoData);
        }
    }

    public static void requestBookmarks(final BoormarksHandler boormarksHandler, String str) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        okHttpClient.newCall(builder.url("http://frame.appsgeyser.com/api/bookmarks/json.php?" + str).build()).enqueue(new Callback() {
            public void onFailure(Call call, IOException iOException) {
                boormarksHandler.onResult(new ArrayList());
            }

            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jSONObject = new JSONObject(response.body().string());
                    response.body().close();
                    JSONArray jSONArray = jSONObject.getJSONArray("browserLinks");
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        HistoryItem historyItem = new HistoryItem();
                        historyItem.setTitle(jSONArray.getJSONObject(i).getString("title"));
                        historyItem.setUrl(jSONArray.getJSONObject(i).getString("url"));
                        historyItem.setImageUrl(jSONArray.getJSONObject(i).getString("icon"));
                        historyItem.setShowOnMainScreen(true);
                        historyItem.setPosition(99);
                        arrayList.add(historyItem);
                    }
                    boormarksHandler.onResult(arrayList);
                } catch (JSONException e) {
                    e.printStackTrace();
                    boormarksHandler.onResult(new ArrayList());
                }
            }
        });
    }

    public static void requestSearchEngine(final SearchEngineHandler searchEngineHandler, String str) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        okHttpClient.newCall(builder.url("http://frame.appsgeyser.com/api/searchengine/json.php?" + str).build()).enqueue(new Callback() {
            public void onFailure(Call call, IOException iOException) {
                searchEngineHandler.onResult((Integer) null, (String) null);
            }

            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jSONObject = new JSONObject(response.body().string());
                    response.body().close();
                    searchEngineHandler.onResult(Integer.valueOf(jSONObject.getInt("searchId")), jSONObject.getString("searchUrl"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    searchEngineHandler.onResult((Integer) null, (String) null);
                }
            }
        });
    }
}
