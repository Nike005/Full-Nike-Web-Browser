package com.appsgeyser.sdk.configuration;

import android.content.Context;
import android.text.TextUtils;
import com.appsgeyser.sdk.GuidGenerator;
import org.json.JSONException;
import org.json.JSONObject;

public final class Configuration {
    private static final String DEFAULT_APPGUID = "";
    private static final String DEFAULT_APPID = "";
    private static final String DEFAULT_TEMPLATE_VERSION = "";
    private static final String KEY_APPLICATION_ID = "ApplicationId";
    private static final String KEY_APP_GUID = "AppGuid";
    private static final String KEY_APP_NAME = "AppName";
    private static final String KEY_REGISTERED = "Registered";
    private static final String KEY_TEMPLATE_VERSION = "TemplateVersion";
    private static final String METRICA_APPSGEYSER_SDK_VERSION_NAME = "appsgeyserSdkVersion";
    private static final String METRICA_JSON_LOAD = "metricaJsonLoad";
    private static final String METRICA_TEMPLATE_VERSION_NAME = "templateVersion";
    private static Configuration instance;
    private String appGuid = "";
    private String appName = "";
    private String applicationId = "";
    private String publisherName = "";
    private boolean registered = false;
    private PreferencesCoder settingsCoder;

    private Configuration() {
    }

    public static Configuration getInstance(Context context) {
        if (instance == null) {
            instance = new Configuration();
        }
        instance.setSettingsCoder(new PreferencesCoder(context));
        return instance;
    }

    public PreferencesCoder getSettingsCoder() {
        return this.settingsCoder;
    }

    private void setSettingsCoder(PreferencesCoder preferencesCoder) {
        this.settingsCoder = preferencesCoder;
    }

    public void loadConfiguration() {
        this.publisherName = "";
        this.applicationId = this.settingsCoder.getPrefString(KEY_APPLICATION_ID, "");
        this.appGuid = this.settingsCoder.getPrefString(KEY_APP_GUID, "");
        this.registered = this.settingsCoder.getPrefBoolean(KEY_REGISTERED, false);
    }

    public boolean isRegistered() {
        return this.registered;
    }

    public String getAppGuid() {
        if (TextUtils.isEmpty(this.appGuid)) {
            String generateNewGuid = GuidGenerator.generateNewGuid();
            this.appGuid = generateNewGuid;
            PreferencesCoder preferencesCoder = this.settingsCoder;
            if (preferencesCoder != null) {
                preferencesCoder.savePrefString(KEY_APP_GUID, generateNewGuid);
            }
        }
        return this.appGuid;
    }

    public String getPublisherName() {
        return this.publisherName;
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public void setApplicationId(String str) {
        this.applicationId = str;
        PreferencesCoder preferencesCoder = this.settingsCoder;
        if (preferencesCoder != null) {
            preferencesCoder.savePrefString(KEY_APPLICATION_ID, str);
        }
    }

    public void setTemplateVersion(String str) {
        this.settingsCoder.savePrefString(KEY_TEMPLATE_VERSION, str);
    }

    public String getTemplateVersion() {
        return this.settingsCoder.getPrefString(KEY_TEMPLATE_VERSION, "");
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String str) {
        this.appName = str;
        PreferencesCoder preferencesCoder = this.settingsCoder;
        if (preferencesCoder != null) {
            preferencesCoder.savePrefString(KEY_APP_NAME, str);
        }
    }

    public void clearApplicationSettings() {
        this.applicationId = "";
        this.appGuid = "";
        this.settingsCoder.savePrefString(KEY_APPLICATION_ID, "");
        this.settingsCoder.savePrefString(KEY_APP_GUID, "");
    }

    public void registerNew() {
        this.registered = true;
        this.settingsCoder.savePrefBoolean(KEY_REGISTERED, true);
    }

    public void setMetricaOnStartEvent(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            jSONObject.put(METRICA_TEMPLATE_VERSION_NAME, str2);
            jSONObject.put(METRICA_APPSGEYSER_SDK_VERSION_NAME, Constants.PLATFORM_VERSION);
            this.settingsCoder.savePrefString(METRICA_JSON_LOAD, jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getMetricaOnStartEvent() {
        return this.settingsCoder.getPrefString(METRICA_JSON_LOAD, (String) null);
    }
}
