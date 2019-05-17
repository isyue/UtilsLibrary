package com.coszero.utilslibrary.app;

import android.text.TextUtils;

import com.coszero.utilslibrary.utils.LogX;
import com.coszero.utilslibrary.utils.StringUtils;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc:App中的异常检查
 * <p>
 * Author xmqian
 * Email:xmqian93@163.com
 * Date: 2019/5/17
 */
public class AppCheckUtils {
    /**
     * 检查List是否为空
     */
    public static <V> boolean isEmptyLs(List<V> list) {
        return null == list || list.size() <= 0;
    }

    /**
     * 判断 json 对象是否为空对象
     * 对象里整型字段必须用 transient 修饰符
     *
     * @param obj
     * @return
     */
    public static boolean isJsonObjectEmpty(Object obj) {
        if (null == obj) {
            return true;
        }
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        try {
            json = obj.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogX.d("##检查JSON对象", "json = " + json);
        return TextUtils.equals(json, "{}");
    }

    /**
     * 列表安全检查，为空返回空列表
     *
     * @param list
     * @return
     */
    public static List checkList(List list) {
        if (isEmptyLs(list)) {
            return new ArrayList();
        }
        return list;
    }

    /**
     * 对象空安全检查
     *
     * @param object
     * @return 空为 false
     */
    public static boolean checkObject(Object object) {
        if (isJsonObjectEmpty(object)) {
            return false;
        }
        return true;
    }

    /**
     * 检查可能为int值得字符串类型
     *
     * @param id
     * @return 返回为0，方式转换int值时报错
     */
    public static String checkIntStr(String id) {
        if (StringUtils.isEmpty(id)) {
            return "0";
        }
        return id;
    }

    /**
     * 字符串空安全检查
     *
     * @param str
     * @return
     */
    public static String checkString(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        return str;
    }
}
