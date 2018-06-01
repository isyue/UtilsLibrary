package regpang.utilslibrary.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xmqian
 * @date 2018/5/29
 * @desc 字符串操作类
 */
public class StringUtils {
    /**
     * 格式化金钱
     *
     * @param summoney
     * @return
     */
    public static String getFormatMoney(String summoney) {
        DecimalFormat df = new DecimalFormat("###.00");
        return df.format(Double.valueOf(summoney));
    }

    /**
     * 验证18位身份证
     *
     * @param input The input.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isIDCard(final CharSequence input) {
        String regExp = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9Xx])$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(input);
        return m.matches();
    }

    /**
     * 是否是标准的手机号码
     *
     * @param str
     * @return
     */
    public static boolean isPhone(String str) {
        String regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 判断给定字符串是否空白串。
     * 空白串是指由空格、制表符、回车符、换行符组成的字符串
     * 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是不是一个合法的电子邮件地址
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        Pattern emailer = Pattern
                .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        if (email == null || email.trim().length() == 0)
            return false;
        return emailer.matcher(email).matches();
    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * 字符串转布尔值
     *
     * @param b
     * @return 转换异常返回 false
     */
    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception e) {
        }
        return false;
    }


    /**
     * 将一个InputStream流转换成字符串
     *
     * @param is
     * @return
     */
    public static String toConvertString(InputStream is) {
        StringBuffer res = new StringBuffer();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader read = new BufferedReader(isr);
        try {
            String line;
            line = read.readLine();
            while (line != null) {
                res.append(line);
                line = read.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != isr) {
                    isr.close();
                    isr.close();
                }
                if (null != read) {
                    read.close();
                    read = null;
                }
                if (null != is) {
                    is.close();
                    is = null;
                }
            } catch (IOException e) {
            }
        }
        return res.toString();
    }

    /**
     * 截取字符串
     *
     * @param data 源数据
     * @param charAt 截取的字符
     * @return
     */
    public static String[] splitString(String data, String charAt) {
        if (data != null && data.contains(charAt)) {
            return data.split(charAt);
        }
        return null;
    }

    /**
     * 判断字符串是不是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {

        if (str.startsWith("0")) {
            return false;
        }

        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * null转为长度为0的字符串
     *
     * @param s 待转字符串
     * @return s为null转为长度为0字符串，否则不改变
     */
    public static String null2Length0(String s) {
        return s == null ? "" : s;
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
     * 把数组转换成字符拼接的字符串
     * 默认使用“,”进行拼接
     *
     * @param ss 数组
     * @return
     */
    public static String arrayToString(String[] ss) {
        return arrayToString(ss, ",");
    }

    /**
     * 把数组转换成字符拼接的字符串
     * 默认使用“,”进行拼接
     *
     * @param ss 数组
     * @param splitChar 传入分割的字符
     * @return
     */
    public static String arrayToString(String[] ss, String splitChar) {
        StringBuffer s = new StringBuffer("");
        if (null != ss) {
            for (int i = 0; i < ss.length - 1; i++) {
                s
//                        .append("'")
                        .append(ss[i])
//                        .append("'")
                        .append(splitChar);
            }
            if (ss.length > 0) {
                s.append("'").append(ss[ss.length - 1]).append("'");
            }
        }
        return s.toString();
    }
}
