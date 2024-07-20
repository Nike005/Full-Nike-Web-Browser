package acr.browser.lightning.utils;

import acr.browser.lightning.utils.StartPageLoader;
import android.app.Activity;
import android.location.Location;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import java.util.ArrayList;

public class LocationService implements OnSuccessListener<Location> {
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static LocationService instance;
    Activity activity;
    public Location location;
    private StartPageLoader.LocationHandler locationHandler;
    private FusedLocationProviderClient mFusedLocationClient;

    public static LocationService getLocationManager(Activity activity2) {
        if (instance == null) {
            instance = new LocationService(activity2);
        }
        return instance;
    }

    private LocationService(Activity activity2) {
        this.activity = activity2;
        initLocationService(activity2);
    }

    private void requestRuntimePermissions(Activity activity2) {
        ArrayList arrayList = new ArrayList();
        if (ContextCompat.checkSelfPermission(activity2, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            arrayList.add("android.permission.ACCESS_FINE_LOCATION");
        }
        if (ContextCompat.checkSelfPermission(activity2, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
            arrayList.add("android.permission.ACCESS_COARSE_LOCATION");
        }
        if (arrayList.size() > 0) {
            ActivityCompat.requestPermissions(activity2, (String[]) arrayList.toArray(new String[arrayList.size()]), 1);
        }
    }

    private boolean checkPermitions(Activity activity2) {
        return Build.VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(activity2, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(activity2, "android.permission.ACCESS_COARSE_LOCATION") == 0;
    }

    private void initLocationService(Activity activity2) {
        if (!checkPermitions(this.activity)) {
            requestRuntimePermissions(this.activity);
            return;
        }
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity2);
        this.mFusedLocationClient = fusedLocationProviderClient;
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(activity2, this);
    }

    public void getLocation(StartPageLoader.LocationHandler locationHandler2) {
        this.locationHandler = locationHandler2;
        if (!checkPermitions(this.activity)) {
            requestRuntimePermissions(this.activity);
            return;
        }
        Location location2 = this.location;
        if (location2 == null) {
            FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.activity);
            this.mFusedLocationClient = fusedLocationProviderClient;
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this.activity, this);
            return;
        }
        locationHandler2.onResult(location2);
    }

    public void onSuccess(Location location2) {
        this.location = location2;
        this.locationHandler.onResult(location2);
    }
}
