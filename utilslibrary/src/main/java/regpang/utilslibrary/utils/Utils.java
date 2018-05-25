package regpang.utilslibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author xmqian
 * @date 2018/5/25
 * @desc 一些杂项的方法
 */
public class Utils {
    // 隐藏软件盘
    public static void hiddeSoftInput(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        if (activity.getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (activity.getCurrentFocus() != null)
                inputMethodManager.hideSoftInputFromWindow(activity
                                .getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }

    }

    /**
     * 格式化手机号，中间4-7位号码隐藏
     *
     * @param phoneNum
     * @return
     */
    public static String getPhoneFormat(String phoneNum) {
        return phoneNum.substring(0, 3).intern() + "****" + phoneNum.substring(7, 11).intern();
    }

    /**
     * 密码是否是字母和数字的组合
     *
     * @param pwd
     * @return
     */
    public static boolean isPwd(String pwd) {
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
        return pwd.matches(regex);
    }

    /**
     * 以文件名的格式获取当前时间
     *
     * @return "yyyyMMdd_HHmmss"
     */
    public static String getFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random random = new Random();
        int num = random.nextInt() * 100;
        return dateFormat.format(date) + num + ".";
    }

    /**
     * 检测Sdcard是否存在
     *
     * @return
     */
    public static boolean isExitsSdcard() {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
    }

    /**
     * 获取是否存在NavigationBar，是否有虚拟按钮
     */
    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {

        }
        return hasNavigationBar;

    }
}
