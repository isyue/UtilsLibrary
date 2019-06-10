package com.coszero.utilslibrary.base;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.coszero.utilslibrary.app.AppManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangfeng on 2016/4/28.
 * Activity界面的父类
 */
public abstract class BaseActivity extends AppCompatActivity {
    public static Class<?> currentActivity;
    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        setWantShowContentView(savedInstanceState);
        //setNavigationIcon需要放在 setSupportActionBar之后才会生效。
    }

    @Override
    protected void onResume() {
        super.onResume();
        currentActivity = getClass();
    }

    /**
     * 关联布局文件，设置标题
     */
    protected abstract void setWantShowContentView(Bundle savedInstanceState);

    /**
     * 隐藏状态栏开关
     *
     * @param on
     */
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    /**
     * 退出app
     */
    public void exitApp() {
        AppManager.getAppManager().exitApp(getApplicationContext(), 0);
        System.out.println("#exit app");
    }

    @Override
    protected void onDestroy() {
        AppManager.getAppManager().removeActivity(this);
        super.onDestroy();
    }
}
