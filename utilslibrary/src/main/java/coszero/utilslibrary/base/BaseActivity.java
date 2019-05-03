package coszero.utilslibrary.base;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangfeng on 2016/4/28.
 * Activity界面的父类
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected boolean isFrist = true;//判断数据是否第一次加载
    private String title;
    private static List<BaseActivity> caches = new ArrayList<>();
    public static Class<?> currentActivity;
    /**
     * 用来标记同一生命周期
     */
    private String tag = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWantShowContentView(savedInstanceState);
        //setNavigationIcon需要放在 setSupportActionBar之后才会生效。
        //检查网络
        initView();
        initListener();
        caches.add(this);
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
     * 初始化视图显示
     */
    protected abstract void initView();

    /**
     * 初始化监听
     */
    protected void initListener() {
    }


    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean onKey = true;
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                return keyBack();
            default:
                onKey = super.onKeyDown(keyCode, event);
                break;
        }
        return onKey;
    }

    public boolean keyBack() {
        finish();
        return true;
    }

    /**
     * 自动转控件类型
     *
     * @param paramInt
     * @param <V>
     * @return
     */
    protected <V> V findView(int paramInt) {
        return (V) this.findViewById(paramInt);
    }

    /**
     * 自动转控件类型
     *
     * @param paramView
     * @param paramInt
     * @param <V>
     * @return
     */
    protected <V> V findView(View paramView, int paramInt) {
        return (V) paramView.findViewById(paramInt);
    }

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

    public static void exit() {
        for (BaseActivity cache : caches) {
            cache.finish();
        }
        caches.clear();
        System.exit(0);
        System.out.println("#exit app");
    }
}
