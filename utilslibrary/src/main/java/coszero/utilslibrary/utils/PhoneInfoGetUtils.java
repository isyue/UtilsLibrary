package coszero.utilslibrary.utils;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @author xmqian
 * @date 2018/5/28
 * @desc 获取手机的信息，包括状态信息
 */
public class PhoneInfoGetUtils {

    /**
     * 获取是否存在NavigationBar，是否有虚拟按键
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
     * 返回当前屏幕是否为竖屏。
     *
     * @param context
     * @return 当且仅当当前屏幕为竖屏时返回true, 否则返回false。
     */
    public static boolean isScreenOriatationPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    /**
     * @param context
     * @return 获取设备唯一标识符
     */
    public static String getUniqueId(Context context) {
        return md5(getDeviceId(context));
    }

    /**
     * </span></p>  * deviceID的组成为：渠道标志+识别符来源标志+hash后的终端识别符
     * <p>
     * 渠道标志为：
     * 1，andriod（a）
     * <p>
     * 识别符来源标志：
     * 1， wifi mac地址（wifi）；
     * 2， IMEI（imei）；
     * 3， 序列号（sn）；
     * 4， id：随机码。若前面的都取不到时，则随机生成一个随机码，需要缓存。
     *
     * @param context
     * @return
     */
    private static String getDeviceId(Context context) {
        StringBuilder deviceId = new StringBuilder();
        // 渠道标志
        try {
            //wifi mac地址
            String wifiMac = getWifiMac(context);
            if (!TextUtils.isEmpty(wifiMac)) {
                deviceId.append("wifi");
                deviceId.append(wifiMac);
                Log.e("getDeviceId : ", deviceId.toString());
                return deviceId.toString();
            }
            //IMEI（imei）
            String imei = getDeviceIMEI(context);
            if (!TextUtils.isEmpty(imei)) {
                deviceId.append("imei");
                deviceId.append(imei);
                Log.e("getDeviceId : ", deviceId.toString());
                return deviceId.toString();
            }
            //序列号（sn）
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String sn = tm.getSimSerialNumber();
            if (!TextUtils.isEmpty(sn)) {
                deviceId.append("sn");
                deviceId.append(sn);
                Log.e("getDeviceId : ", deviceId.toString());
                return deviceId.toString();
            }
            String androidId = Settings.Secure.getString(context.getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
            if (!TextUtils.isEmpty(sn)) {
                deviceId.append("androidId");
                deviceId.append(androidId);
                Log.e("getDeviceId : ", deviceId.toString());
                return deviceId.toString();
            }
            //如果上面都没有， 则生成一个id：随机码
            String uuid = getUUID(context);
            if (!TextUtils.isEmpty(uuid)) {
                deviceId.append("uuid");
                deviceId.append(uuid);
                Log.e("getDeviceId : ", deviceId.toString());
                return deviceId.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            deviceId.append("id").append(getUUID(context));
        }
        Log.e("getDeviceId : ", deviceId.toString());
        return deviceId.toString();
    }

    /**
     * 获取手机的IMEI
     *
     * @param context
     * @return
     */
    public static String getDeviceIMEI(Context context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            Log.e("getDeviceIMEI", "权限不足");
            return "";
        }
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = tm.getDeviceId();
        return imei;
    }

    /**
     * 获取wifi的mac地址
     *
     * @param context
     * @return
     */
    public static String getWifiMac(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }


    /**
     * 得到全局唯一UUID
     *
     * @param context
     * @return
     */
    private static String getUUID(Context context) {
        String uuid = "";
        SharedPreferences mShare = context.getSharedPreferences("uuid", 0);
        if (mShare != null) {
            uuid = mShare.getString("uuid", "");
        }
        if (TextUtils.isEmpty(uuid)) {
            uuid = UUID.randomUUID().toString();
            mShare.edit().putString("uuid", uuid).commit();
        }
        Log.e("tag", "getUUID : " + uuid);
        return uuid;
    }


    /**
     * @param context
     * @return 获得设备SN号(产品序列号)
     */
    public static String getSN(Context context) {
        return android.os.Build.SERIAL;
    }

    /**
     * @param context
     * @return 获得SIM卡SN号(产品序列号)
     */
    public static String getSimSN(Context context) {
        String id = "";
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            Log.e("getSimSn", "no permission");
            return "";
        }
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        id = tm.getSimSerialNumber();
        return id;
    }

    /**
     * 获取本地mac地址
     *
     * @param context
     * @return
     */
    public static String getLocalMacAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();
        return info.getMacAddress();
    }

    /**
     * md5加密
     *
     * @param string
     * @return
     */
    private static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(
                    string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }


}
