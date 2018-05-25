package regpang.utilslibrary.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import regpang.utilslibrary.R;
import regpang.utilslibrary.utils.DialogUtils;

/**
 * Created by zhangfeng on 2016/4/28.
 * Activity界面的父类
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected boolean isFrist = true;//判断数据是否第一次加载
    private String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWantShowContentView(savedInstanceState);
        //setNavigationIcon需要放在 setSupportActionBar之后才会生效。
        //检查网络
        initView();
        initListener();
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
}
