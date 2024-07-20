package acr.browser.lightning.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat;
import com.onesignal.OneSignalDbContract;
import com.wnikebrow_13999769.R;
import java.util.concurrent.TimeUnit;

public class WeatherNotificationService extends Service {

    /* renamed from: nm */
    NotificationManager f4057nm;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        this.f4057nm = (NotificationManager) getSystemService(OneSignalDbContract.NotificationTable.TABLE_NAME);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendNotif();
        return super.onStartCommand(intent, i, i2);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onLowMemory() {
        super.onLowMemory();
    }

    /* access modifiers changed from: package-private */
    public void sendNotif() {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.weather_notification);
        remoteViews.setImageViewResource(R.id.image, R.drawable.app_icon);
        remoteViews.setTextViewText(R.id.title, "Custom notification");
        remoteViews.setTextViewText(R.id.text, "This is a custom layout");
        this.f4057nm.notify(777, new NotificationCompat.Builder(getApplicationContext()).setContentTitle("Ясно").setSmallIcon(R.drawable.cast_ic_notification_small_icon).setContent(remoteViews).build());
    }
}
