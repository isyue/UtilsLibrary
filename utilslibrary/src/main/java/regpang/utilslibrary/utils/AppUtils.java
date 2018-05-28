package regpang.utilslibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.SyncStateContract;

import java.io.File;

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

    /** 引导安装包
     * @param context
     * @param file
     * @return
     */
    public static Intent promptInstall(Context context, File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
//        intent.setDataAndType(getUri(file), Constants.VAPA);
        return intent;
    }
}
