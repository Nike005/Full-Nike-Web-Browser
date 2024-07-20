package acr.browser.lightning.notifiction;

import acr.browser.lightning.activity.MainActivity;
import acr.browser.lightning.domain.WeatherData;
import acr.browser.lightning.preference.PreferenceManager;
import acr.browser.lightning.utils.StartPageLoader;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.onesignal.OneSignalDbContract;
import com.wnikebrow_13999769.R;

public class WeatherNotification {
    private String TAG = "WeatherNotification";
    private int WEATHER_NOTIFICATION_ID = 1022;

    /* renamed from: nm */
    private NotificationManager f4055nm = null;
    private Notification notice = null;

    public WeatherNotification(Context context, WeatherData weatherData, PreferenceManager preferenceManager) {
        double d;
        this.f4055nm = (NotificationManager) context.getSystemService(OneSignalDbContract.NotificationTable.TABLE_NAME);
        if (weatherData != null) {
            if (preferenceManager.getWeatherData().isCecius()) {
                double temp = (double) (weatherData.getTemp() - 32);
                Double.isNaN(temp);
                d = temp * 0.5555555555555556d;
            } else {
                d = (double) weatherData.getTemp();
            }
            int i = (int) d;
            DrawableCompat.setTint(DrawableCompat.wrap(context.getResources().getDrawable(StartPageLoader.getIconId("simple", weatherData.getCode()))), -1);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.weather_notification);
            remoteViews.setImageViewResource(R.id.weatherImage, StartPageLoader.getIconId("simple", weatherData.getCode()));
            remoteViews.setTextViewText(R.id.title, weatherData.getText());
            remoteViews.setTextViewText(R.id.text, weatherData.getLocation());
            remoteViews.setTextViewText(R.id.countText, String.valueOf(i) + "°");
            int i2 = R.drawable.notification_icon_default_white;
            if (i > 120) {
                i2 = R.drawable.notification_icon_max_white;
            } else if (i < -40) {
                i2 = R.drawable.notification_icon_min_white;
            } else if (i < 0) {
                i2 = context.getResources().getIdentifier("notification_icon__" + Math.abs(i) + "_white", "drawable", context.getPackageName());
            } else if (i >= 0) {
                i2 = context.getResources().getIdentifier("notification_icon_" + i + "_white", "drawable", context.getPackageName());
            }
            Notification build = new NotificationCompat.Builder(context).setContentTitle(weatherData.getText()).setContentTitle(weatherData.getText()).setSmallIcon(i2).setContent(remoteViews).setContentIntent(PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 134217728)).build();
            this.notice = build;
            build.flags |= 32;
            return;
        }
        Log.d(this.TAG, "No weather data");
    }

    public void show() {
        Notification notification = this.notice;
        if (notification != null) {
            this.f4055nm.notify(this.WEATHER_NOTIFICATION_ID, notification);
        }
    }

    public void remove() {
        if (this.notice != null) {
            this.f4055nm.cancel(this.WEATHER_NOTIFICATION_ID);
        }
    }
}
