package com.appsgeyser.sdk.p087ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.appsgeyser.sdk.C5051R;
import com.appsgeyser.sdk.configuration.Configuration;
import com.appsgeyser.sdk.configuration.Constants;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.server.implementation.AppsgeyserServerClient;

/* renamed from: com.appsgeyser.sdk.ui.AboutDialogActivity */
public class AboutDialogActivity extends Activity {
    private static final String APPSGEYSER_DESCRIPTION_CUSTOM = "custom";
    private static final String APPSGEYSER_DESCRIPTION_LOGO = "appsgeyser_logo";
    private static final String APPSGEYSER_DESCRIPTION_TEXT = "appsgeyser_text";
    private static final String CONFIG_PHP_KEY = "config_php_key";
    private TextView appName;
    private TextView appVersion;
    private ImageView appsgeyserDescriptionLogoImageView;
    private ImageView closeScreenImageView;
    private Configuration config;
    private ConfigPhp configPhp;
    private String description;
    private TextView descriptionTextView;
    private String descriptionType;
    private ImageView logoImageView;
    private Button privacy;
    private TextView templateVersion;
    private Button tos;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C5051R.layout.appsgeysersdk_about_dialog);
        if (bundle != null) {
            this.configPhp = (ConfigPhp) bundle.getParcelable(CONFIG_PHP_KEY);
        } else {
            Intent intent = getIntent();
            if (intent != null) {
                this.configPhp = (ConfigPhp) intent.getParcelableExtra(CONFIG_PHP_KEY);
            }
        }
        ConfigPhp configPhp2 = this.configPhp;
        if (configPhp2 != null) {
            this.descriptionType = configPhp2.getAboutScreenDescriptionType();
            this.description = this.configPhp.getAboutScreenDescription();
        }
        init();
    }

    private void init() {
        try {
            Configuration instance = Configuration.getInstance(this);
            this.config = instance;
            String applicationId = instance.getApplicationId();
            this.logoImageView = (ImageView) findViewById(C5051R.C5054id.logo);
            this.appsgeyserDescriptionLogoImageView = (ImageView) findViewById(C5051R.C5054id.appsgeysersdk_about_appsgeyser_logo);
            this.closeScreenImageView = (ImageView) findViewById(C5051R.C5054id.close_about_screen);
            this.descriptionTextView = (TextView) findViewById(C5051R.C5054id.description);
            this.appName = (TextView) findViewById(C5051R.C5054id.about_app_name);
            this.appVersion = (TextView) findViewById(C5051R.C5054id.app_version);
            this.templateVersion = (TextView) findViewById(C5051R.C5054id.template_version);
            this.tos = (Button) findViewById(C5051R.C5054id.app_tos);
            this.privacy = (Button) findViewById(C5051R.C5054id.app_privacy);
            try {
                PackageManager packageManager = getPackageManager();
                this.appVersion.setText("Version " + getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
                this.appName.setText((String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(getPackageName(), 128)));
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            final String str = "http://www.appsgeyser.com?pn=" + getPackageName();
            this.logoImageView.setImageDrawable(getPackageManager().getApplicationIcon(getPackageName()));
            this.logoImageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                    intent.setFlags(268435456);
                    AboutDialogActivity.this.startActivity(intent);
                    AppsgeyserServerClient.getInstance().sendAboutDialogVisitSite(AboutDialogActivity.this.getApplicationContext());
                }
            });
            this.closeScreenImageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    AboutDialogActivity.this.finish();
                }
            });
            this.templateVersion.setText(getString(C5051R.string.appsgeysersdk_build, new Object[]{Constants.PLATFORM_VERSION}));
            String string = getResources().getString(C5051R.string.appsgeysersdk_description_with_publish_name);
            if (this.config.getPublisherName().length() == 0) {
                string = getResources().getString(C5051R.string.appsgeysersdk_description_without_publish_name);
            }
            if (this.descriptionType != null) {
                String str2 = this.descriptionType;
                char c = 65535;
                int hashCode = str2.hashCode();
                if (hashCode != -1349088399) {
                    if (hashCode != 1502878931) {
                        if (hashCode == 1503108181) {
                            if (str2.equals(APPSGEYSER_DESCRIPTION_TEXT)) {
                                c = 0;
                            }
                        }
                    } else if (str2.equals(APPSGEYSER_DESCRIPTION_LOGO)) {
                        c = 1;
                    }
                } else if (str2.equals("custom")) {
                    c = 2;
                }
                if (c == 0) {
                    this.appsgeyserDescriptionLogoImageView.setVisibility(8);
                    this.descriptionTextView.setVisibility(0);
                } else if (c == 1) {
                    this.appsgeyserDescriptionLogoImageView.setVisibility(0);
                    this.descriptionTextView.setVisibility(8);
                } else if (c != 2) {
                    this.appsgeyserDescriptionLogoImageView.setVisibility(8);
                    this.descriptionTextView.setVisibility(0);
                } else {
                    string = this.description;
                    this.appsgeyserDescriptionLogoImageView.setVisibility(8);
                    this.descriptionTextView.setVisibility(0);
                }
            }
            this.descriptionTextView.setText(Html.fromHtml(string.replace("PUB_NAME", this.config.getPublisherName()).replace("APPSGEYSER_URL", str).replace("APP_VERSION", "").replace("APP_ID", applicationId)));
            this.descriptionTextView.setLinksClickable(true);
            this.descriptionTextView.setMovementMethod(LinkMovementMethod.getInstance());
            this.tos.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    Intent data = intent.setData(Uri.parse("http://www.appsgeyser.com/tos/?pn=" + AboutDialogActivity.this.getPackageName()));
                    data.setFlags(268435456);
                    AboutDialogActivity.this.startActivity(data);
                }
            });
            this.privacy.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    Intent data = intent.setData(Uri.parse("http://www.appsgeyser.com/privacy/app/?pn=" + AboutDialogActivity.this.getPackageName()));
                    data.setFlags(268435456);
                    AboutDialogActivity.this.startActivity(data);
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelable(CONFIG_PHP_KEY, this.configPhp);
        super.onSaveInstanceState(bundle);
    }
}
