package com.onesignal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.SystemClock;
import com.onesignal.OneSignal;
import com.onesignal.OneSignalDbContract;
import com.onesignal.outcomes.OSOutcomeTableProvider;
import java.util.ArrayList;

class OneSignalDbHelper extends SQLiteOpenHelper implements OneSignalDb {
    private static final String COMMA_SEP = ",";
    private static final String DATABASE_NAME = "OneSignal.db";
    static final int DATABASE_VERSION = 8;
    private static final int DB_OPEN_RETRY_BACKOFF = 400;
    private static final int DB_OPEN_RETRY_MAX = 5;
    private static final String FLOAT_TYPE = " FLOAT";
    private static final String INTEGER_PRIMARY_KEY_TYPE = " INTEGER PRIMARY KEY";
    private static final String INT_TYPE = " INTEGER";
    protected static final String SQL_CREATE_ENTRIES = "CREATE TABLE notification (_id INTEGER PRIMARY KEY,notification_id TEXT,android_notification_id INTEGER,group_id TEXT,collapse_id TEXT,is_summary INTEGER DEFAULT 0,opened INTEGER DEFAULT 0,dismissed INTEGER DEFAULT 0,title TEXT,message TEXT,full_data TEXT,created_time TIMESTAMP DEFAULT (strftime('%s', 'now')),expire_time TIMESTAMP);";
    private static final String SQL_CREATE_IN_APP_MESSAGE_ENTRIES = "CREATE TABLE in_app_message (_id INTEGER PRIMARY KEY,display_quantity INTEGER,last_display INTEGER,message_id TEXT,displayed_in_session INTEGER,click_ids TEXT);";
    protected static final String[] SQL_INDEX_ENTRIES = {OneSignalDbContract.NotificationTable.INDEX_CREATE_NOTIFICATION_ID, OneSignalDbContract.NotificationTable.INDEX_CREATE_ANDROID_NOTIFICATION_ID, OneSignalDbContract.NotificationTable.INDEX_CREATE_GROUP_ID, OneSignalDbContract.NotificationTable.INDEX_CREATE_COLLAPSE_ID, OneSignalDbContract.NotificationTable.INDEX_CREATE_CREATED_TIME, OneSignalDbContract.NotificationTable.INDEX_CREATE_EXPIRE_TIME};
    private static final String TEXT_TYPE = " TEXT";
    private static final String TIMESTAMP_TYPE = " TIMESTAMP";
    private static OSOutcomeTableProvider outcomeTableProvider = new OSOutcomeTableProvider();
    private static OneSignalDbHelper sInstance;

    private static int getDbVersion() {
        return 8;
    }

    /* access modifiers changed from: package-private */
    public void setOutcomeTableProvider(OSOutcomeTableProvider oSOutcomeTableProvider) {
        outcomeTableProvider = oSOutcomeTableProvider;
    }

    OneSignalDbHelper(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, getDbVersion());
    }

    public static synchronized OneSignalDbHelper getInstance(Context context) {
        OneSignalDbHelper oneSignalDbHelper;
        synchronized (OneSignalDbHelper.class) {
            if (sInstance == null) {
                sInstance = new OneSignalDbHelper(context.getApplicationContext());
            }
            oneSignalDbHelper = sInstance;
        }
        return oneSignalDbHelper;
    }

    /* access modifiers changed from: package-private */
    public synchronized SQLiteDatabase getSQLiteDatabase() {
        try {
        } catch (SQLiteCantOpenDatabaseException | SQLiteDatabaseLockedException e) {
            throw e;
        }
        return getWritableDatabase();
    }

    public synchronized SQLiteDatabase getSQLiteDatabaseWithRetries() {
        int i = 0;
        while (true) {
            try {
            } catch (SQLiteCantOpenDatabaseException | SQLiteDatabaseLockedException e) {
                i++;
                if (i < 5) {
                    SystemClock.sleep((long) (i * DB_OPEN_RETRY_BACKOFF));
                } else {
                    throw e;
                }
            }
        }
        return getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
        sQLiteDatabase.execSQL(OSOutcomeTableProvider.SQL_CREATE_OUTCOME_ENTRIES_V3);
        sQLiteDatabase.execSQL(OSOutcomeTableProvider.SQL_CREATE_UNIQUE_OUTCOME_ENTRIES_V2);
        sQLiteDatabase.execSQL(SQL_CREATE_IN_APP_MESSAGE_ENTRIES);
        for (String execSQL : SQL_INDEX_ENTRIES) {
            sQLiteDatabase.execSQL(execSQL);
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
        OneSignal.Log(log_level, "OneSignal Database onUpgrade from: " + i + " to: " + i2);
        try {
            internalOnUpgrade(sQLiteDatabase, i);
        } catch (SQLiteException e) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error in upgrade, migration may have already run! Skipping!", e);
        }
    }

    private synchronized void internalOnUpgrade(SQLiteDatabase sQLiteDatabase, int i) {
        if (i < 2) {
            try {
                upgradeToV2(sQLiteDatabase);
            } catch (Throwable th) {
                throw th;
            }
        }
        if (i < 3) {
            upgradeToV3(sQLiteDatabase);
        }
        if (i < 4) {
            upgradeToV4(sQLiteDatabase);
        }
        if (i < 5) {
            upgradeToV5(sQLiteDatabase);
        }
        if (i == 5) {
            upgradeFromV5ToV6(sQLiteDatabase);
        }
        if (i < 7) {
            upgradeToV7(sQLiteDatabase);
        }
        if (i < 8) {
            upgradeToV8(sQLiteDatabase);
        }
    }

    private static void upgradeToV2(SQLiteDatabase sQLiteDatabase) {
        safeExecSQL(sQLiteDatabase, "ALTER TABLE notification ADD COLUMN collapse_id TEXT;");
        safeExecSQL(sQLiteDatabase, OneSignalDbContract.NotificationTable.INDEX_CREATE_GROUP_ID);
    }

    private static void upgradeToV3(SQLiteDatabase sQLiteDatabase) {
        safeExecSQL(sQLiteDatabase, "ALTER TABLE notification ADD COLUMN expire_time TIMESTAMP;");
        safeExecSQL(sQLiteDatabase, "UPDATE notification SET expire_time = created_time + 259200;");
        safeExecSQL(sQLiteDatabase, OneSignalDbContract.NotificationTable.INDEX_CREATE_EXPIRE_TIME);
    }

    private static void upgradeToV4(SQLiteDatabase sQLiteDatabase) {
        safeExecSQL(sQLiteDatabase, OSOutcomeTableProvider.SQL_CREATE_OUTCOME_ENTRIES_V1);
    }

    private static void upgradeToV5(SQLiteDatabase sQLiteDatabase) {
        safeExecSQL(sQLiteDatabase, OSOutcomeTableProvider.SQL_CREATE_UNIQUE_OUTCOME_ENTRIES_V1);
        upgradeFromV5ToV6(sQLiteDatabase);
    }

    private static void upgradeFromV5ToV6(SQLiteDatabase sQLiteDatabase) {
        outcomeTableProvider.upgradeOutcomeTableRevision1To2(sQLiteDatabase);
    }

    private static void upgradeToV7(SQLiteDatabase sQLiteDatabase) {
        safeExecSQL(sQLiteDatabase, SQL_CREATE_IN_APP_MESSAGE_ENTRIES);
    }

    private synchronized void upgradeToV8(SQLiteDatabase sQLiteDatabase) {
        outcomeTableProvider.upgradeOutcomeTableRevision2To3(sQLiteDatabase);
        outcomeTableProvider.upgradeCacheOutcomeTableRevision1To2(sQLiteDatabase);
    }

    private static void safeExecSQL(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            sQLiteDatabase.execSQL(str);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: finally extract failed */
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "SDK version rolled back! Clearing OneSignal.db as it could be in an unexpected state.");
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", (String[]) null);
        try {
            ArrayList<String> arrayList = new ArrayList<>(rawQuery.getCount());
            while (rawQuery.moveToNext()) {
                arrayList.add(rawQuery.getString(0));
            }
            for (String str : arrayList) {
                if (!str.startsWith("sqlite_")) {
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
                }
            }
            rawQuery.close();
            onCreate(sQLiteDatabase);
        } catch (Throwable th) {
            rawQuery.close();
            throw th;
        }
    }

    static StringBuilder recentUninteractedWithNotificationsWhere() {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        StringBuilder sb = new StringBuilder("created_time > " + (currentTimeMillis - 604800) + " AND " + OneSignalDbContract.NotificationTable.COLUMN_NAME_DISMISSED + " = 0 AND " + OneSignalDbContract.NotificationTable.COLUMN_NAME_OPENED + " = 0 AND " + OneSignalDbContract.NotificationTable.COLUMN_NAME_IS_SUMMARY + " = 0");
        if (OneSignalPrefs.getBool(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_OS_RESTORE_TTL_FILTER, true)) {
            sb.append(" AND expire_time > " + currentTimeMillis);
        }
        return sb;
    }

    static void cleanOutcomeDatabaseTable(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.delete(OSOutcomeTableProvider.OUTCOME_EVENT_TABLE, (String) null, (String[]) null);
    }
}
