package xmqian.myutils;

import android.app.Application;

import coszero.utilslibrary.utils.ToastUtils;

/**
 * @author xmqian
 * @date 2018/5/25
 * @desc
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtils.init(this);
    }
}
