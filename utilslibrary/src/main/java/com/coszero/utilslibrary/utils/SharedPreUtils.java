package com.coszero.utilslibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Map;
import java.util.Map.Entry;

/**
 * @author xmqian
 * @date 2018/12/29 10:14
 * @desc SharedPreferences存储App的配置数据
 */
public class SharedPreUtils {

    private static String FILE_NAME = "config";
    private static SharedPreferences sharedPreferences;

    /**
     * 存储字符串
     *
     * @param context 上下文
     * @param key 键名称
     * @param value 值内容
     */
    public static void saveStringData(Context context, String key, String value) {
        checkNull(context);

        sharedPreferences.edit().putString(key, value).commit();
    }

    public static void saveBoolean(Context context, String key, boolean value) {
        checkNull(context);
        sharedPreferences.edit().putBoolean(key, value).commit();
    }

    public static Boolean getBooleanData(Context context, String key, Boolean defValue) {
        checkNull(context);
        return sharedPreferences.getBoolean(key, defValue);
    }

    public static void saveInteger(Context context, String key, int value) {
        checkNull(context);
        sharedPreferences.edit().putInt(key, value).commit();
    }

    public static int getIntegerData(Context context, String key, int defValue) {
        checkNull(context);
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
        checkNull(context);
        return sharedPreferences.getString(key, defValue);
    }

    /**
     * 在logcat里面打印所有文件中的内容
     *
     * @param context 上下文
     */
    public static void showAllStringData(Context context) {
        checkNull(context);
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
        checkNull(context);
        sharedPreferences.edit().remove(key).commit();
    }

    /**
     * 快速保存数据,先保存在内存，再写入磁盘
     *
     * @param context
     * @param key
     * @param object
     */
    public static void put(Context context, String key, Object object) {
        checkNull(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        editor.apply();
    }

    /**
     * 获取数据
     *
     * @param context
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object get(Context context, String key, Object defaultObject) {
        checkNull(context);
        //根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
        if (defaultObject instanceof String) {
            return sharedPreferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defaultObject);
        }
        return null;
    }

    /**
     * 清除所有数据
     *
     * @param context
     */
    public static void clear(Context context) {
        checkNull(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean contains(Context context, String key) {
        checkNull(context);
        return sharedPreferences.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @param context
     * @return
     */
    public static Map<String, ?> getAll(Context context) {
        checkNull(context);
        return sharedPreferences.getAll();
    }

    private static void checkNull(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
            Log.w("SharedPreUtils", "### sharedPreferences is null");
        }
    }
}
