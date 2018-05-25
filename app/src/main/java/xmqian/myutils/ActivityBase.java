package xmqian.myutils;

import android.os.Bundle;

import butterknife.ButterKnife;
import regpang.utilslibrary.base.BaseActivity;

/**
 * @author xmqian
 * @date 2018/5/25
 * @desc
 */
public class ActivityBase extends BaseActivity {
    @Override
    protected void setWantShowContentView(Bundle savedInstanceState) {
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
