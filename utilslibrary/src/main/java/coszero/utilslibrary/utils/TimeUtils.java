package coszero.utilslibrary.utils;


import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * @author xmqian
 * @date 2018/5/29
 * @desc 获取系统时间，和格式化时间格式
 */
public class TimeUtils {
    private final static long minute = 60 * 1000;// 1分钟
    private final static long hour = 60 * minute;// 1小时
    private final static long day = 24 * hour;// 1天
    private final static long month = 31 * day;// 月
    private final static long year = 12 * month;// 年

    /*时间格式可根据需要进行更改*/
    /**
     * 日期格式：yyyy-MM-dd HH:mm:ss
     **/
    public static final String DF_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式：yyyy-MM-dd HH:mm
     **/
    public static final String DF_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    /**
     * 日期格式：yyyy-MM-dd
     **/
    public static final String DF_YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 日期格式：HH:mm:ss
     **/
    public static final String DF_HH_MM_SS = "HH:mm:ss";

    /**
     * 日期格式：HH:mm
     **/
    public static final String DF_HH_MM = "HH:mm";


    private static Calendar mCalendar;
    private static SimpleDateFormat format;

    /**
     * 以默认格式获取当前的系统时间
     *
     * @return 返回月-日 时：分 格式的时间
     */
    public static String getCurrentTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA)
                .format(new Date());
    }

    /**
     * 获取系统当前日期
     *
     * @return date
     */
    public static Date getCurrentDate() {
        return new Date();
    }


    /**
     * 以你所需要的格式获取当前的系统时间
     *
     * @param rgx 传入你需要的时间格式
     * @return 返回你所需要格式的时间字符串
     */
    public static String getCurrentTime(String rgx) {
        return new SimpleDateFormat(rgx, Locale.CHINA)
                .format(new Date());
    }

    /**
     * 判断两日期是否同一天
     *
     * @param str1
     * @param str2
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static boolean isToday(String str1, String str2) {
        Date day1 = null, day2 = null;
        day1 = parseDate(str1);
        day2 = parseDate(str2);
        SimpleDateFormat sdf = new SimpleDateFormat(DF_YYYY_MM_DD);
        String ds1 = sdf.format(day1);
        String ds2 = sdf.format(day2);
        if (ds1.equals(ds2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 传入时间 算出星期几
     *
     * @param str 2014年1月3日
     * @param days 1:2014年1月4日 类推
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getWeekName(String str, int days) {
        String dateStr = "";
        try {
            DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
            Date date = df.parse(str);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            Date d = dateFormat.parse(dateFormat.format(date));
            c.setTime(d);
            c.add(Calendar.DAY_OF_MONTH, days);
            switch (c.get(Calendar.DAY_OF_WEEK) - 1) {
                case 0:
                    dateStr = "周日";
                    break;
                case 1:
                    dateStr = "周一";
                    break;
                case 2:
                    dateStr = "周二";
                    break;
                case 3:
                    dateStr = "周三";
                    break;
                case 4:
                    dateStr = "周四";
                    break;
                case 5:
                    dateStr = "周五";
                    break;
                case 6:
                    dateStr = "周六";
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    /**
     * 将日期字符串转成日期
     *
     * @param strDate 字符串日期，需要"-"分割
     * @return java.util.date日期类型
     */
    @SuppressLint("SimpleDateFormat")
    public static Date parseDate(String strDate) {
        DateFormat dateFormat = new SimpleDateFormat(DF_YYYY_MM_DD_HH_MM_SS);
        Date returnDate = null;
        try {
            returnDate = dateFormat.parse(strDate);
        } catch (ParseException e) {

        }
        return returnDate;

    }

    /**
     * 获取未经处理的时间格式
     *
     * @return 返回long类型的时间格式
     */
    public static long getLongTime() {
        Date date = new Date();
        return date.getTime();
    }

    /**
     * 将long类型日期以yyyy-MM-dd HH:mm:ss格式化
     *
     * @param dateL 传入以Date获取的long类型值
     * @return 返回已默认格式转化的时间字符串
     */
    @SuppressLint("SimpleDateFormat")
    public static String formatDateTime(long dateL) {
        SimpleDateFormat sdf = new SimpleDateFormat(DF_YYYY_MM_DD_HH_MM_SS);
        Date date = new Date(dateL);
        return sdf.format(date);
    }

    /**
     * 传入一个String转化为long
     *
     * @param param 传入的时间,格式为"-"
     * @return 返回一个long型
     * @throws ParseException
     */
    @SuppressLint("SimpleDateFormat")
    public static Long stringParserLong(String param) throws ParseException {
        format = new SimpleDateFormat(DF_YYYY_MM_DD_HH_MM_SS);
        return format.parse(param).getTime();
    }

    /**
     * 将long类型日期格式化为你想要的格式
     *
     * @param dateL 传入以Date获取的long类型值
     * @param rgx 传入你想转化的格式 yyyy-MM-dd HH:mm:ss
     * @return 返回已默认格式转化的时间字符串
     */
    @SuppressLint("SimpleDateFormat")
    public static String formatDateTime(long dateL, String rgx) {
        SimpleDateFormat sdf = new SimpleDateFormat(rgx);
        Date date = new Date(dateL);
        return sdf.format(date);
    }

    /**
     * 获取日期格式 年/月/日
     *
     * @return 从日历类中获取年/月/日
     */
    private static String getYMD(Calendar mCalendar, String splitChar) {
        int mYear = mCalendar.get(Calendar.YEAR);
        int mMonth = mCalendar.get(Calendar.MONTH) + 1;
        int mDay = mCalendar.get(Calendar.DATE);
        return mYear + splitChar + mMonth + splitChar + mDay;
    }

    /**
     * 格式化Date类型的日期
     *
     * @param date date 如果是改造的，格式要和获取的格式一致
     * @param rgx 需要获取的格式
     * @return 从日历类中获取年/月/日
     */
    public static String getFormatDate(Date date, String rgx) {
        SimpleDateFormat sdf = new SimpleDateFormat(rgx);
        return sdf.format(date);
    }

    /**
     * 对日期进行增加操作
     *
     * @param target 需要进行运算的日期
     * @param hour 小时
     * @return
     */
    public static Date addDateTime(Date target, double hour) {
        if (null == target || hour < 0) {
            return target;
        }

        return new Date(target.getTime() + (long) (hour * 60 * 60 * 1000));
    }

    /**
     * 对日期进行相减操作
     *
     * @param target 需要进行运算的日期
     * @param hour 小时
     * @return
     */
    public static Date subDateTime(Date target, double hour) {
        if (null == target || hour < 0) {
            return target;
        }

        return new Date(target.getTime() - (long) (hour * 60 * 60 * 1000));
    }

    /**
     * 获取时间格式 时：分
     *
     * @param mCalendar
     * @return 从日历类中获取当前时间时：分
     */
    public static String getTime(Calendar mCalendar) {
        int mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        int mMinute = mCalendar.get(Calendar.MINUTE);
        return mHour + ":" + mMinute;
    }

    /**
     * 获取当前日期月
     *
     * @return 返回当前月份
     */
    public static int getMonth() {
        mCalendar = Calendar.getInstance();
        int mMonth = mCalendar.get(Calendar.MONTH) + 1;
        return mMonth;
    }

    /**
     * 获取当前日期日
     *
     * @return
     */
    public static int getDay() {
        mCalendar = Calendar.getInstance();
        int mDay = mCalendar.get(Calendar.DATE);
        return mDay;
    }

    /**
     * 获取当前日期年
     *
     * @return 返回当前年
     */
    public static int getYear() {
        mCalendar = Calendar.getInstance();
        int year = mCalendar.get(Calendar.YEAR);
        return year;
    }

    /**
     * 转换年月日的分隔符“-”、“/”
     *
     * @param changeTime 需要转换的时间
     * @return 返回转换分隔符后的日期 “-”转“/”或者“/”转“-”
     * @throws ParseException
     */
    public static String changeSplitCharYMD(String changeTime) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        String formatStr;
        String changeSplitChar;
        if (changeTime.contains("-")) {
            formatStr = "yy-MM-dd";
            changeSplitChar = "/";
        } else if (changeTime.contains("/")) {
            formatStr = "yy/MM/dd";
            changeSplitChar = "-";
        } else {
            System.out.println("时间格式不对");
            return changeTime;
        }
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        Date date = format.parse(changeTime);
        calendar.setTime(date);
        return getYMD(calendar, changeSplitChar);
    }

    /**
     * 验证日期是否比当前日期早
     *
     * @param startTime 当前系统时间
     * @param finishTime 需要比较的时间
     * @return 返回当前时间是否比finishTime早，早：true;晚false
     */
    public static boolean compareData(String startTime, String finishTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DF_YYYY_MM_DD_HH_MM_SS);
        boolean isLast = false;
        try {
            Date startDate = dateFormat.parse(startTime);
            Date finishDate = dateFormat.parse(finishTime);
            isLast = startDate.before(finishDate);
        } catch (ParseException e) {
            System.out.println("比较失败，原因：" + e.getMessage());
        }
        return isLast;
    }

    /**
     * 用当前时间给图片命名
     *
     * @return 返回yyyyMMdd_HHmmss格式的字符串
     */
    public static String getFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random random = new Random();
        int num = random.nextInt() * 100;
        return dateFormat.format(date) + num + ".";
    }

    /**
     * 以友好的方式显示时间
     *
     * @param sdate 传入的时间
     * @return
     */
    public static String friendlyTime(String sdate) {
        Date time = null;
        time = parseDate(sdate);
        if (time == null) {
            return "Unknown";
        }
        long diff = new Date().getTime() - time.getTime();
        long r = 0;

        if (diff > year) {
            r = (diff / year);
            return r + "年前";
        }
        if (diff > month) {
            r = (diff / month);
            return r + "个月前";
        }
        if (diff > day) {
            r = (diff / day);
            if (r == 1) {
                return "昨天";
            }
            if (r == 2) {
                return "前天";
            }
            return r + "天前";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "个小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }

}
