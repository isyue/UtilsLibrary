package com.coszero.utilslibrary.app;

import android.app.Activity;
import android.content.Context;

import java.util.Stack;

import com.coszero.utilslibrary.utils.LogX;

/**
 * @author jiyuren
 * @ClassName: net.oschina.app.AppManager
 * @Description: Activity管理类：用于管理Activity和退出程序
 * @date 2014-11-2 上午11:27:55
 */
public class AppManager {

    private static Stack<Activity> activityStack;
    private static volatile AppManager instance;

    private AppManager() {
    }

    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            synchronized (AppManager.class) {
                if (null == instance) {
                    instance = new AppManager();
                }
            }
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            synchronized (AppManager.class) {
                if (null == activityStack) {
                    activityStack = new Stack<>();
                    activityStack.add(activity);
                }
            }
        } else {
            activityStack.add(activity);
        }
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            LogX.d("finishActivity = %s", activity.getClass().getSimpleName());
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 移除任务桟
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (null != activity && activityStack.contains(activity)) {
            LogX.i("removeActivity = %s", activity.getClass().getSimpleName());
            activityStack.remove(activity);
        }
    }


    /**
     * 结束指定类
     */
    public void finishActivity(Class<?> cls) {
        Activity removeAct = null;
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                removeAct = activity;
            }
        }
        if (null != removeAct) {
            LogX.d("finishActivity = %s", removeAct.getClass().getSimpleName());
            finishActivity(removeAct);
        }
    }

    /**
     * 获取指定类名的activity对象
     *
     * @param clazz
     * @return
     */
    public Activity getActivity(Class<?> clazz) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(clazz)) {
                LogX.d("activity name = %s", activity.getClass().getSimpleName());
                return activity;
            }
        }
        return null;
    }

    /**
     * 结束所有加入列表内的Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 获取任务栈中的所有acticity列表
     * 只有添加了 addActivity()方法的类才能在改列表中
     *
     * @return
     */
    public Stack<Activity> getActivityLs() {
        return activityStack;
    }

    /**
     * 退出应用程序
     *
     * @param context
     * @param code -1 为非正常退出
     */
    public void exitApp(Context context, int code) {
        try {
            finishAllActivity();
//            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//            activityMgr.killBackgroundProcesses(context.getPackageName());
//            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(code);
            // System.exit(-1);//非正常关闭
        } catch (Exception e) {
        }
    }
}