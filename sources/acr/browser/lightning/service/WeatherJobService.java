package acr.browser.lightning.service;

import acr.browser.lightning.domain.WeatherData;
import acr.browser.lightning.notifiction.WeatherNotification;
import acr.browser.lightning.preference.PreferenceManager;
import acr.browser.lightning.utils.StartPageLoader;
import android.os.AsyncTask;
import android.util.Log;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class WeatherJobService extends JobService {
    public boolean onStartJob(JobParameters jobParameters) {
        final PreferenceManager preferenceManager = new PreferenceManager(getApplicationContext());
        new AsyncTask() {
            /* access modifiers changed from: protected */
            public Object doInBackground(Object[] objArr) {
                C32261 r3 = new StartPageLoader.WeatherCallback() {
                    public void onWeatherResult(WeatherData weatherData) {
                        new WeatherNotification(WeatherJobService.this.getApplicationContext(), weatherData, preferenceManager).show();
                        if (weatherData != null) {
                            weatherData.setCecius(preferenceManager.getWeatherData().isCecius());
                        }
                        preferenceManager.setWeatherDataData(weatherData);
                    }
                };
                PreferenceManager preferenceManager = preferenceManager;
                StartPageLoader.requestWeather(preferenceManager, StartPageLoader.buildUrl(preferenceManager.getWeatherData().getLocation()), r3);
                return null;
            }
        }.execute(new Object[0]);
        Log.d("ProxyService", "onStart");
        return false;
    }

    public boolean onStopJob(JobParameters jobParameters) {
        Log.d("ProxyService", "onStop");
        return false;
    }
}
