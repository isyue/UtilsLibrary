package regpang.utilslibrary.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class AppUtils {

    /**
     * 获取应用版本
     *
     * @param context
     * @return
     */
    public static int getCurrentVersion(Context context) {
        int version = Integer.MAX_VALUE;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            version = pi.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return version;
    }
}
