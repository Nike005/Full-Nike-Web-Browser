package info.guardianproject.netcipher.proxy;

import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import org.apache.commons.lang3.StringUtils;

public class TorServiceUtils {
    public static final String CHMOD_EXE_VALUE = "700";
    public static final String SHELL_CMD_CHMOD = "chmod";
    public static final String SHELL_CMD_KILL = "kill -9";
    public static final String SHELL_CMD_PIDOF = "pidof";
    public static final String SHELL_CMD_PS = "ps";
    public static final String SHELL_CMD_RM = "rm";
    private static final String TAG = "TorUtils";

    public static boolean isRootPossible() {
        StringBuilder sb = new StringBuilder();
        try {
            if (new File("/system/app/Superuser.apk").exists() || new File("/system/app/superuser.apk").exists()) {
                return true;
            }
            if (!new File("/system/bin/su").exists()) {
                if (doShellCommand(new String[]{"which su"}, sb, false, true) == 0) {
                    Log.d(TAG, "root exists, but not sure about permissions");
                    return true;
                }
                Log.e(TAG, "Could not acquire root permissions");
                return false;
            } else if (doShellCommand(new String[]{"su"}, sb, false, true) != 0) {
                return false;
            } else {
                return true;
            }
        } catch (IOException e) {
            Log.e(TAG, "Error checking for root access", e);
        } catch (Exception e2) {
            Log.e(TAG, "Error checking for root access", e2);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:7|6|8|9|15) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003d, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003e, code lost:
        android.util.Log.e(TAG, "Unable to get proc id for command: " + java.net.URLEncoder.encode(r4), r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        return r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0038 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int findProcessId(android.content.Context r4) {
        /*
            java.io.File r4 = r4.getFilesDir()
            java.io.File r4 = r4.getParentFile()
            java.io.File r4 = r4.getParentFile()
            java.lang.String r4 = r4.getAbsolutePath()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r4)
            java.lang.String r4 = "/"
            r0.append(r4)
            java.lang.String r4 = "org.torproject.android"
            r0.append(r4)
            java.lang.String r4 = "/app_bin/tor"
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            r0 = -1
            int r1 = findProcessIdWithPidOf(r4)     // Catch:{ Exception -> 0x0038 }
            if (r1 != r0) goto L_0x0059
            int r1 = findProcessIdWithPS(r4)     // Catch:{ Exception -> 0x0037 }
            goto L_0x0059
        L_0x0037:
            r0 = r1
        L_0x0038:
            int r1 = findProcessIdWithPS(r4)     // Catch:{ Exception -> 0x003d }
            goto L_0x0059
        L_0x003d:
            r1 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Unable to get proc id for command: "
            r2.append(r3)
            java.lang.String r4 = java.net.URLEncoder.encode(r4)
            r2.append(r4)
            java.lang.String r4 = r2.toString()
            java.lang.String r2 = "TorUtils"
            android.util.Log.e(r2, r4, r1)
            r1 = r0
        L_0x0059:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: info.guardianproject.netcipher.proxy.TorServiceUtils.findProcessId(android.content.Context):int");
    }

    public static int findProcessIdWithPidOf(String str) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(new String[]{SHELL_CMD_PIDOF, new File(str).getName()}).getInputStream()));
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return -1;
            }
            try {
                return Integer.parseInt(readLine.trim());
            } catch (NumberFormatException e) {
                Log.e("TorServiceUtils", "unable to parse process pid: " + readLine, e);
            }
        }
    }

    public static int findProcessIdWithPS(String str) throws Exception {
        String readLine;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(SHELL_CMD_PS).getInputStream()));
        do {
            readLine = bufferedReader.readLine();
            if (readLine == null) {
                return -1;
            }
        } while (readLine.indexOf(' ' + str) == -1);
        StringTokenizer stringTokenizer = new StringTokenizer(readLine, StringUtils.SPACE);
        stringTokenizer.nextToken();
        return Integer.parseInt(stringTokenizer.nextToken().trim());
    }

    public static int doShellCommand(String[] strArr, StringBuilder sb, boolean z, boolean z2) throws Exception {
        Process process;
        if (z) {
            process = Runtime.getRuntime().exec("su");
        } else {
            process = Runtime.getRuntime().exec("sh");
        }
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(process.getOutputStream());
        for (String write : strArr) {
            outputStreamWriter.write(write);
            outputStreamWriter.write(StringUtils.f3949LF);
        }
        outputStreamWriter.flush();
        outputStreamWriter.write("exit\n");
        outputStreamWriter.flush();
        if (!z2) {
            return -1;
        }
        char[] cArr = new char[10];
        InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
        while (true) {
            int read = inputStreamReader.read(cArr);
            if (read == -1) {
                break;
            } else if (sb != null) {
                sb.append(cArr, 0, read);
            }
        }
        InputStreamReader inputStreamReader2 = new InputStreamReader(process.getErrorStream());
        while (true) {
            int read2 = inputStreamReader2.read(cArr);
            if (read2 == -1) {
                return process.waitFor();
            }
            if (sb != null) {
                sb.append(cArr, 0, read2);
            }
        }
    }
}
