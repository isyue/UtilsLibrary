package regpang.utilslibrary.utils;

import android.app.Application;
import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

/**
 * Toast工具类
 */
public class ToastUtils {

    private static Context mContext;

    private static Toast toast;

    public static void init(Application context) {
        if (mContext == null) {
            mContext = context;
        }
    }

    /**
     * 短时间显示吐司，并且重复点击上一层吐司消失
     *  需要先在App中调用init()
     * @param msg
     */
    public static void showMsg(String msg) {
        if (null == toast) {
            toast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
        } else {
            toast.cancel();
            toast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    /**
     * 短时间显示吐司，并且重复点击上一层吐司消失
     *
     * @param context
     * @param msg
     */
    public static void showMsg(Context context, String msg) {
        mContext = context;
        showMsg(msg);
    }

    /**
     * 显示长吐司，并重复点击消失上一层吐司
     *需要先在App中调用init()
     * @param msg
     */
    public static void showLongMsg(String msg) {
        if (null == toast) {
            toast = Toast.makeText(mContext, msg, Toast.LENGTH_LONG);
        } else {
            toast.cancel();
            toast = Toast.makeText(mContext, msg, Toast.LENGTH_LONG);
        }
        toast.show();
    }

    /**
     * 显示长吐司，并重复点击消失上一层吐司
     *
     * @param msg
     */
    public static void showLongMsg(Context context, String msg) {
        mContext = context;
        showLongMsg(msg);
    }

    public static void showAsyncMsg(String msg) {
        Looper.prepare();
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        Looper.loop();
    }
}
