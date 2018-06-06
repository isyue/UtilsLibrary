package regpang.utilslibrary.utils;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/08/02
 *     @link(https://github.com/Blankj/AndroidUtilCode/edit/master/utilcode/src/main/java/com/blankj/utilcode/util/AppUtils.java)
 *     desc  : App工具类，获取app信息，和操作app
 * </pre>
 */
public final class AppUtils {
    /**
     * 获取当前本地apk的版本
     *
     * @param mContext
     * @return
     */
        public static int getVersionCode(Context mContext) {
        int versionCode = 0;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = mContext.getPackageManager().
                    getPackageInfo(mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取版本号名称
     *
     * @param context 上下文
     * @return
     */
    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }
}
