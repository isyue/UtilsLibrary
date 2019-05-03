package coszero.utilslibrary.utils;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * 打印Log日志
 *
 * @link(com.orhanobut:logger:1.13) Created by zero on 2016/04/02.
 */
public class LogX {
    private static final String TAG = "AppLog";
    //控制日志开关,自动判断是否显示日志
//    private static boolean isOpen = BuildConfig.DEBUG;
    private static boolean isOpen = true;

    private static boolean isLogError = isOpen;
    private static boolean isLogFile = isOpen;
    private static boolean isLogTime = isOpen;
    private static boolean isLogPrint = isOpen;
    private static boolean isLogSystem = isOpen;


    /**
     * 控制日志开关
     *
     * @param isOpen true:开，false：关
     */
    public static void openLog(boolean isOpen) {
        isLogError = isOpen;
        isLogFile = isOpen;
        isLogTime = isOpen;
        isLogPrint = isOpen;
    }

    /**
     * 打印Error
     *
     * @param msg
     */
    public static void e(String msg) {
        e(TAG, msg);
    }

    public static void e(String tag, String msg) {
        if (isLogError) {
            android.util.Log.e(tag, msg);
        }
    }


    /**
     * 打印Warn
     *
     * @param msg
     */
    public static void w(String msg) {
        w(TAG, msg);
    }

    public static void w(String tag, String msg) {
        if (isLogError) {
            android.util.Log.w(tag, msg);
        }
    }

    /**
     * 打印debug
     *
     * @param msg
     */
    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void d(String tag, String msg) {
        if (isLogError) {
            android.util.Log.d(tag, msg);
        }
    }


    /**
     * 打印info
     *
     * @param msg
     */
    public static void i(String msg) {
        i(TAG, msg);
    }

    public static void i(String tag, String msg) {
        if (isLogError) {
            android.util.Log.i(tag, msg);
        }
    }

    /**
     * 打印系统日志
     *
     * @param msg
     */
    public static void println(String msg) {
        if (isLogSystem) {
            System.out.println(msg);
        }
    }

    /**
     * 打印时间日志
     *
     * @param tag
     * @param msg
     */
    public static void timeLog(String tag, String msg) {
        if (isLogTime) {
            android.util.Log.i(tag, msg + " : " + System.currentTimeMillis());
        }
    }

    /**
     * 打印日志，并存储为文件
     *
     * @param fileName
     * @param msg
     */
    public static void logFile(String fileName, String msg) {
        if (isLogFile) {
            d(fileName, msg);
            File fileDir = new File(Environment.getExternalStorageDirectory(),
                    "/mlogs/");
            File logFile = new File(fileDir, fileName);

            FileWriter fileOutputStream = null;
            try {
                if (!fileDir.exists()) {
                    if (!fileDir.mkdirs()) {
                        return;
                    }
                }
                if (!logFile.exists()) {
                    if (!logFile.createNewFile()) {
                        return;
                    }
                }
                fileOutputStream = new FileWriter(logFile, true);
                fileOutputStream.write(msg);
                fileOutputStream.flush();
            } catch (Exception e) {
            } finally {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
    }

    /**
     * 打印堆栈日志
     *
     * @param e
     */
    public static void printStackTrace(Exception e) {
        if (isLogPrint) {
            e.printStackTrace();
        }
    }

    /**
     * 是否为debug
     *
     * @param context
     * @return
     */
    public static boolean isApkDebugable(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {

        }
        return false;
    }
}
