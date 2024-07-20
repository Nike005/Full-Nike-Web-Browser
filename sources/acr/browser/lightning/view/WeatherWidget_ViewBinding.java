package acr.browser.lightning.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import butterknife.Unbinder;
import butterknife.internal.C4621Utils;
import com.wnikebrow_13999769.R;

public class WeatherWidget_ViewBinding implements Unbinder {
    private WeatherWidget target;

    public WeatherWidget_ViewBinding(WeatherWidget weatherWidget, View view) {
        this.target = weatherWidget;
        weatherWidget.temperature = (TextView) C4621Utils.findRequiredViewAsType(view, R.id.temperature, "field 'temperature'", TextView.class);
        weatherWidget.weatherIcon = (ImageView) C4621Utils.findRequiredViewAsType(view, R.id.weather_icon, "field 'weatherIcon'", ImageView.class);
        weatherWidget.location = (TextView) C4621Utils.findRequiredViewAsType(view, R.id.location, "field 'location'", TextView.class);
        weatherWidget.weatherText = (TextView) C4621Utils.findRequiredViewAsType(view, R.id.weatherText, "field 'weatherText'", TextView.class);
        weatherWidget.weatherWidget = (CardView) C4621Utils.findRequiredViewAsType(view, R.id.weatherWidget, "field 'weatherWidget'", CardView.class);
        weatherWidget.celsiusButton = (TextView) C4621Utils.findRequiredViewAsType(view, R.id.celsiusButton, "field 'celsiusButton'", TextView.class);
        weatherWidget.temperaturePanel = (LinearLayout) C4621Utils.findRequiredViewAsType(view, R.id.temperaturePanel, "field 'temperaturePanel'", LinearLayout.class);
        weatherWidget.imagePanel = (ViewGroup) C4621Utils.findRequiredViewAsType(view, R.id.imagePanel, "field 'imagePanel'", ViewGroup.class);
    }

    public void unbind() {
        WeatherWidget weatherWidget = this.target;
        if (weatherWidget != null) {
            this.target = null;
            weatherWidget.temperature = null;
            weatherWidget.weatherIcon = null;
            weatherWidget.location = null;
            weatherWidget.weatherText = null;
            weatherWidget.weatherWidget = null;
            weatherWidget.celsiusButton = null;
            weatherWidget.temperaturePanel = null;
            weatherWidget.imagePanel = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
