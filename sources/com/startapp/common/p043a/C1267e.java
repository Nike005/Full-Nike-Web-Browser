package com.startapp.common.p043a;

import android.content.Context;
import com.startapp.common.C1303g;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.startapp.common.a.e */
/* compiled from: StartAppSDK */
public class C1267e {
    /* renamed from: a */
    public static <T> T m2057a(Context context, String str, Class<T> cls) {
        return m2058a(context, (String) null, str, cls);
    }

    /* renamed from: a */
    public static void m2061a(Context context, String str, Serializable serializable) {
        m2062a(context, (String) null, str, serializable);
    }

    /* renamed from: b */
    public static void m2070b(final Context context, final String str, final Serializable serializable) {
        C1303g.m2206a(C1303g.C1307a.DEFAULT, (Runnable) new Runnable() {
            public void run() {
                C1267e.m2062a(context, (String) null, str, serializable);
            }
        });
    }

    /* renamed from: a */
    public static void m2062a(Context context, String str, String str2, Serializable serializable) {
        if (str2 == null) {
            C1270g.m2078a("FileUtils", 3, "writeToDisk: fileName is null");
            return;
        }
        try {
            m2065a(m2068b(context, str), str2, serializable);
        } catch (Exception e) {
            C1270g.m2078a("FileUtils", 3, "Failed writing to disk: " + e.getLocalizedMessage());
        }
    }

    /* renamed from: b */
    public static void m2071b(Context context, String str, String str2, Serializable serializable) {
        if (str2 == null) {
            C1270g.m2078a("FileUtils", 3, "writeToDisk: fileName is null");
            return;
        }
        try {
            m2065a(m2072c(context, str), str2, serializable);
        } catch (Exception e) {
            C1270g.m2078a("FileUtils", 3, "Failed writing to disk: " + e.getLocalizedMessage());
        }
    }

    /* renamed from: a */
    public static <T> T m2058a(Context context, String str, String str2, Class<T> cls) {
        if (str2 == null) {
            C1270g.m2076a(3, "readFromDisk::fileName is null");
            return null;
        }
        try {
            return m2059a(m2068b(context, str), str2);
        } catch (Exception e) {
            C1270g.m2078a("FileUtils", 6, "Failed to read from disk: " + e.getLocalizedMessage());
            return null;
        } catch (Error e2) {
            C1270g.m2078a("FileUtils", 6, "Failed to read from disk: " + e2.getLocalizedMessage());
            return null;
        }
    }

    /* renamed from: b */
    public static <T> T m2066b(Context context, String str, String str2, Class<T> cls) {
        if (str2 == null) {
            C1270g.m2076a(3, "readFromDisk::fileName is null");
            return null;
        }
        try {
            return m2059a(m2072c(context, str), str2);
        } catch (Exception e) {
            C1270g.m2078a("FileUtils", 6, "Failed to read from disk: " + e.getLocalizedMessage());
            return null;
        } catch (Error e2) {
            C1270g.m2078a("FileUtils", 6, "Failed to read from disk: " + e2.getLocalizedMessage());
            return null;
        }
    }

    /* renamed from: b */
    public static <T> List<T> m2069b(Context context, String str, Class<T> cls) {
        ArrayList arrayList = new ArrayList();
        try {
            File file = new File(m2072c(context, str));
            if (file.exists()) {
                if (file.isDirectory()) {
                    String[] list = file.list();
                    if (list == null) {
                        C1270g.m2078a("FileUtils", 3, "Files directory is empty");
                        return null;
                    }
                    for (String file2 : list) {
                        File file3 = new File(file, file2);
                        C1270g.m2078a("FileUtils", 4, "Reading file: " + file3.getPath());
                        arrayList.add(m2067b(file3));
                    }
                    return arrayList;
                }
            }
            C1270g.m2078a("FileUtils", 3, "Files directory does not exist or not a directory: " + str);
            return null;
        } catch (Exception e) {
            C1270g.m2078a("FileUtils", 6, "Failed to read from disk: " + e.getLocalizedMessage());
        }
    }

    /* renamed from: a */
    public static void m2060a(Context context, String str) {
        if (str == null) {
            C1270g.m2076a(3, "deleteDirectory::dirPath == null");
            return;
        }
        m2063a(new File(m2068b(context, str)));
        m2063a(new File(m2072c(context, str)));
    }

    /* renamed from: b */
    private static String m2068b(Context context, String str) {
        String str2;
        StringBuilder sb = new StringBuilder();
        sb.append(context.getFilesDir().toString());
        if (str != null) {
            str2 = File.separator + str;
        } else {
            str2 = "";
        }
        sb.append(str2);
        return sb.toString();
    }

    /* renamed from: c */
    private static String m2072c(Context context, String str) {
        String str2;
        StringBuilder sb = new StringBuilder();
        sb.append(context.getCacheDir().toString());
        if (str != null) {
            str2 = File.separator + str;
        } else {
            str2 = "";
        }
        sb.append(str2);
        return sb.toString();
    }

    /* renamed from: a */
    private static void m2065a(String str, String str2, Serializable serializable) {
        File file = new File(str);
        if (file.exists() || file.mkdirs()) {
            File file2 = new File(file, str2);
            C1270g.m2078a("FileUtils", 4, "Writing file: " + file2.getPath());
            m2064a(serializable, file2);
            return;
        }
        C1270g.m2078a("FileUtils", 3, "Unable to create directories");
    }

    /* renamed from: a */
    private static <T> T m2059a(String str, String str2) {
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            C1270g.m2078a("FileUtils", 3, "Files directory does not exist or not a directory");
            return null;
        }
        File file2 = new File(file, str2);
        if (!file2.exists()) {
            return null;
        }
        C1270g.m2078a("FileUtils", 4, "Reading file: " + file2.getPath());
        return m2067b(file2);
    }

    /* renamed from: a */
    private static void m2063a(File file) {
        if (file.isDirectory()) {
            for (File a : file.listFiles()) {
                m2063a(a);
            }
        }
        file.delete();
    }

    /* renamed from: a */
    private static void m2064a(Serializable serializable, File file) {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(serializable);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    /* renamed from: b */
    private static <T> T m2067b(File file) {
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        T readObject = objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        return readObject;
    }
}
