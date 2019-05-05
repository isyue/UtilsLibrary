package xmqian.myutils.simple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import com.coszero.utilslibrary.utils.ToastUtils;
import xmqian.myutils.R;

/**
 * Toast工具类测试
 */
public class ToastActivity extends AppCompatActivity {

    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.textView2)
    public void showShortToast(){
        index++;
//        ToastUtils.showMsg(this,"显示短时间显示"+index +"次");
        /*需要注册全局Context*/
        ToastUtils.showMsg("显示长时间显示"+index +"次");
    }

    @OnClick(R.id.textView3)
    public void showLongToast(){
        index++;
//        ToastUtils.showLongMsg(this,"显示长时间显示"+index +"次");
        /*需要注册全局Context*/
        ToastUtils.showLongMsg("显示长时间显示"+index +"次");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
