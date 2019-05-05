package com.coszero.utilslibrary.utils;

import android.app.Activity;
import android.content.Context;

import java.util.Stack;

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
        try {
            Activity activity = activityStack.lastElement();
            return activity;
        } catch (Exception o) {
            return null;
        }
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
        try {
            if (activityStack != null) {
                if (null != activity && activityStack.contains(activity)) {
                    LogX.i("removeActivity = %s", activity.getClass().getSimpleName());
                    activityStack.remove(activity);
                }
            }
        } catch (Exception o) {
            LogX.d("####移除Activity失败***");
        }
    }


    /**
     * 结束指定类名的Activity
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
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    public Stack<Activity> getActivityLs() {
        return activityStack;
    }

    /**
     * 退出应用程序
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