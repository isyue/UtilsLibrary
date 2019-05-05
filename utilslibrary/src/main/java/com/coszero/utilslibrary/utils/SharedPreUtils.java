package com.coszero.utilslibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;
import java.util.Map.Entry;

/**
 * @author xmqian
 * @date 2018/12/29 10:14
 * @desc SharedPreferences存储App的配置数据
 */
public class SharedPreUtils {

    private static String CONFIG = "config";
    private static SharedPreferences sharedPreferences;

    /**
     * 存储字符串
     *
     * @param context 上下文
     * @param key 键名称
     * @param value 值内容
     */
    public static void saveStringData(Context context, String key, String value) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }

        sharedPreferences.edit().putString(key, value).commit();
    }

    public static void saveBoolean(Context context, String key, boolean value) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        sharedPreferences.edit().putBoolean(key, value).commit();
    }

    public static Boolean getBooleanData(Context context, String key, Boolean defValue) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        return sharedPreferences.getBoolean(key, defValue);
    }

    public static void saveInteger(Context context, String key, int value) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        sharedPreferences.edit().putInt(key, value).commit();
    }

    public static int getIntegerData(Context context, String key, int defValue) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        return sharedPreferences.getInt(key, defValue);
    }

    /**
     * 取得存储的字符串
     *
     * @param context 上下文
     * @param key 键名称
     * @param defValue 默认值
     * @return 返回存储的值，没有则返回默认值
     */
    public static String getStringData(Context context, String key, String defValue) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        return sharedPreferences.getString(key, defValue);
    }

    /**
     * 在logcat里面打印所有文件中的内容
     *
     * @param context 上下文
     */
    public static void showAllStringData(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        Map<String, String> all = (Map<String, String>) sharedPreferences.getAll();
        for (Entry<String, String> entry : all.entrySet()) {
            System.out.println(entry.getKey() + ":::" + entry.getValue());
        }
    }

    /**
     * 删除文件中指定的键值数据
     *
     * @param context 上下文
     * @param key 你要删除的键名称
     */
    public static void deleteStringData(Context context, String key) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        sharedPreferences.edit().remove(key).commit();
    }

}
