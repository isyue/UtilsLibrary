package regpang.utilslibrary.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * 获取时间
 */
public class TimeUtils {


    private static Calendar mCalendar;

    /**
     * 获取月-日-时-分
     *
     * @return
     */
    public static String getCurrentTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA)
                .format(new Date());
    }

    /**
     * 获取中国时间
     *
     * @param rgx
     * @return
     */
    public static String getCurrentTime(String rgx) {
        return new SimpleDateFormat(rgx, Locale.CHINA)
                .format(new Date());
    }

    public static String formatTime(long time) {
        time /= 1000;
        long minute = time / 60;
        long second = time % 60;
        return String.format("%02d:%02d", minute, second);
    }

    /**
     * 获取时间格式 年/月、日
     *
     * @param mCalendar
     * @return
     */
    public static String getYMD(Calendar mCalendar) {
        int mYear = mCalendar.get(Calendar.YEAR);
        int mMonth = mCalendar.get(Calendar.MONTH) + 1;
        int mDay = mCalendar.get(Calendar.DATE);
        return mYear + "/" + mMonth + "/" + mDay;
    }

    /**
     * 获取时间月
     *
     * @return
     */
    public static int getMonth() {
        mCalendar = Calendar.getInstance();
        int mMonth = mCalendar.get(Calendar.MONTH) + 1;
        return mMonth;
    }

    /**
     * 获取时间年
     *
     * @return
     */
    public static int getYear() {
        mCalendar = Calendar.getInstance();
        int year = mCalendar.get(Calendar.YEAR);
        return year;
    }

    /**
     * 获取时间格式 时：分
     *
     * @param mCalendar
     * @return
     */
    public static String getTime(Calendar mCalendar) {

        int mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        int mMinute = mCalendar.get(Calendar.MINUTE);
        return mHour + ":" + mMinute;
    }

    /**
     * 获取时间格式 年-月-日
     *
     * @param updateTime
     * @return
     * @throws ParseException
     */
    public static boolean canApply(String updateTime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
        Date date = format.parse(updateTime);
        return date.after(new Date());
    }

    /**
     * 获取时间格式年-月-日
     *
     * @param updateTime
     * @return
     * @throws ParseException
     */
    public static String getYMD(String updateTime) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
        Date date = format.parse(updateTime);
        calendar.setTime(date);
        return getYMD(calendar);
    }

	/**
     * 判断当前两个日期的大小
     */
    public static boolean isLastCompareData(String startTime, String finishTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        boolean isLast = false;
        try {
            Date startDate = dateFormat.parse(startTime);
            Date finishDate = dateFormat.parse(finishTime);
            isLast = startDate.before(finishDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return isLast;
    }

	/**
     * 用当前时间给图片命名
     *
     * @return
     */
    public static String getFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random random = new Random();
        int num = random.nextInt() * 100;
        return dateFormat.format(date) + num + ".";
    }
}
