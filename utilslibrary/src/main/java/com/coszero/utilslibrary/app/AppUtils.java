package com.coszero.utilslibrary.app;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.List;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/08/02
 *     @link(https://github.com/Blankj/AndroidUtilCode/edit/master/utilcode/src/main/java/com/blankj/utilcode/util/AppUtils.java)
 *     desc  : App工具类，获取app信息，和操作app-比如安装和自动卸载等
 * </pre>
 */
public final class AppUtils {
    private AppUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取应用包名
     *
     * @param context
     * @return 当前应用的包名
     */
    public static String getPackageName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取应用程序版本名称信息
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置textView图标
     *
     * @param drawable
     * @param textView
     */
    public static void setLeftIcon(Drawable drawable, TextView textView) {
        if (null != drawable) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }
        textView.setCompoundDrawables(drawable, null, null, null);
    }

    /**
     * 默认的密码字符串组合，用来将字节转换成 16 进制表示的字符
     */
    protected static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    protected static MessageDigest messagedigest = null;

    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String getFileMD5String(File file) throws IOException {
        InputStream fis;
        fis = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int numRead = 0;
        while ((numRead = fis.read(buffer)) > 0) {
            messagedigest.update(buffer, 0, numRead);
        }
        fis.close();
        return bufferToHex(messagedigest.digest());
    }

    public static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];// 取字节中高 4 位的数字转换
        // 为逻辑右移，将符号位一起右移,此处未发现两种符号有何不同
        char c1 = hexDigits[bt & 0xf];// 取字节中低 4 位的数字转换
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }
    //end


    public static String getChannelName(Context context) {
        if (context == null) {
            return null;
        }
        String channelName = "qutuo";
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                //注意此处为ApplicationInfo 而不是 ActivityInfo,因为友盟设置的meta-data是在application标签中，而不是某activity标签中，所以用ApplicationInfo
                ApplicationInfo applicationInfo = packageManager.
                        getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        channelName = String.valueOf(applicationInfo.metaData.get("UMENG_CHANNEL"));
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return channelName;
    }

    /**
     * 判断是否联网
     *
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = cm.getActiveNetworkInfo();
        if (network != null) {
            return network.isAvailable();
        }
        return false;

    }

    /**
     * 设置textView图标
     *
     * @param drawable
     * @param textView
     */
    public static void setRightIcon(Drawable drawable, TextView textView) {
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, null, drawable, null);
    }

    public static void setTopIcon(Drawable drawable, TextView textView) {
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, drawable, null, null);
    }

    /**
     * 隐藏view
     *
     * @param view
     */
    public static void hideView(View view) {
        if (null == view) {
            return;
        }
        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.GONE);
        }
    }

    /**
     * 显示view
     *
     * @param view
     */
    public static void showView(View view) {
        if (null == view) {
            return;
        }
        if (view.getVisibility() == View.GONE || view.getVisibility() == View.INVISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
    }

    public static void inVisibleView(View view) {
        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 改变字符串中间的颜色
     *
     * @param str
     * @param start
     * @param end
     * @param color
     */
    /**
     * 改变部分文字的颜色
     *
     * @param textView 控件
     * @param start 开始文字
     */
    public static void changeTextColor(Context context, TextView textView, int start, int end, int colorRes) {
        String s = textView.getText().toString();
        SpannableStringBuilder builder = new SpannableStringBuilder(s);
        ForegroundColorSpan redSpan = new ForegroundColorSpan(context.getResources().getColor(colorRes));
        builder.setSpan(redSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(builder);
    }

    public static int getColor(Context context, int colorId) {
        return context.getResources().getColor(colorId);
    }

    public static String getString(Context context, int strId) {
        return context.getResources().getString(strId);
    }

    ;

    /**
     * 拼接图片url，项目专用
     */
    public static String imageUrlFormat(List<String> urls) {
        if (urls == null || urls.size() == 0) {
            return "";
        }
        StringBuffer sqlSb = new StringBuffer();
        for (String url : urls) {
            sqlSb.append(url + ",");
        }
        // 去除最后的逗号
        String subSql = sqlSb.substring(0, sqlSb.length() - 1);
        return subSql.toString();
    }

    public static String double2Str(double num) {
        DecimalFormat format1 = new DecimalFormat("0.00");
        String couponMoney = format1.format(num);
        return couponMoney;
    }

    public static Drawable getDrawable(Context context, int rosId) {
        return context.getResources().getDrawable(rosId);
    }

    /**
     * 按要求格式化文字颜色
     * 输入框字数限制专用
     *
     * @param context
     * @param inputNum 输入的数量
     * @param maxLength 最大数量
     * @return
     */
    public static Spanned formatColorText(Context context, int inputNum, int maxLength) {
        return Html.fromHtml("<font color = '" + AppUtils.getColor(context, 0xff0000ff) + "'>" + inputNum + "</font>" + "<font color = '" +
                AppUtils.getColor(context, 0xff999999) + "'>" + "/" + maxLength + "</font>");
    }

    public static int strToInt(String tag) {
        if (TextUtils.isEmpty(tag)) {
            return 0;
        }
        try {
            return Integer.valueOf(tag);
        } catch (Exception e) {
            return 0;
        }
    }

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


}
