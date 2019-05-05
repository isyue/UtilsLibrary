package xmqian.myutils;

import android.os.Bundle;

import butterknife.ButterKnife;
import com.coszero.utilslibrary.base.BaseActivity;

/**
 * @author xmqian
 * @date 2018/5/25
 * @desc
 */
public abstract class ActivityBase extends BaseActivity {
    @Override
    protected abstract void setWantShowContentView(Bundle savedInstanceState);
    @Override
    protected abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
