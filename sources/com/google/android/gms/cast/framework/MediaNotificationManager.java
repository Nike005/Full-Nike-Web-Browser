package com.google.android.gms.cast.framework;

public class MediaNotificationManager {
    private final SessionManager zzgu;

    public MediaNotificationManager(SessionManager sessionManager) {
        this.zzgu = sessionManager;
    }

    public void updateNotification() {
        CastSession currentCastSession = this.zzgu.getCurrentCastSession();
        if (currentCastSession != null) {
            currentCastSession.zzs().zzg(true);
        }
    }
}
