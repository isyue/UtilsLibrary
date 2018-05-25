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
   protected TextView toolbarTitle;
    protected ImageButton ibtnRight;
    protected TextView tvRight;
    protected Toolbar toolbar;
    protected DialogUtils dialogUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWantShowContentView();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //setNavigationIcon需要放在 setSupportActionBar之后才会生效。
        //检查网络

        if (dialogUtils == null) {
            dialogUtils = new DialogUtils(this, getString(R.string.dialog_request_data));
        }
        findId();
        initView();
        initListener();
    }

    /**
     * 隐藏右边图标
     */
    protected void hideRightImgStatee() {
        ibtnRight.setVisibility(View.GONE);
    }

    /**
     * 显示右边图标
     *
     * @param imageResourceID
     */
    protected void showRightImgStatee(int imageResourceID) {
        ibtnRight.setVisibility(View.VISIBLE);
        ibtnRight.setImageResource(imageResourceID);
    }
   public void changeRightImg(int imageResourceID){
       showRightImgStatee(imageResourceID);
   }
    public void changeRightTv(String subtitle){

      showRightTextState(subtitle);
  }
    /**
     * 改变标题名称
     *
     * @param title
     */
    public void changeTitle(String title) {
        toolbarTitle.setText(title);

    }
public void showToobar(){
    showLeftImgState();
}
    /**
     * 隐藏右边文字
     *
     */
    protected void hideRightTextStatee() {
        tvRight.setVisibility(View.GONE);
    }

    /**
     * 显示右边文字
     *
     * @param subtitle
     */
    protected void showRightTextState(String subtitle) {
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(subtitle);

    }

    /**
     * 隐藏返回键
     */
    protected void hideLeftImgState() {
        toolbar.setNavigationIcon(null);
    }
   //显示返回键
    protected void showLeftImgState(){
    }
    /**
     * 关联布局文件，设置标题
     */
    protected abstract void setWantShowContentView();

    /**
     * 找id
     */
    protected abstract void findId();

    /**
     * 初始化视图显示
     */
    protected abstract void initView();

    /**
     * 初始化监听
     */
    protected void initListener() {
    }


    public void setToolbarTitle(String title) {
        this.title = title;
    }

    protected Toolbar getToolbar() {
        return toolbar;
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
