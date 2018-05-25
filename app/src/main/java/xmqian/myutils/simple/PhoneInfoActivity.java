package xmqian.myutils.simple;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import regpang.utilslibrary.utils.PhoneScreenInfoUtils;
import xmqian.myutils.ActivityBase;
import xmqian.myutils.R;

/**
 * @author xmqian
 * @date 2018/5/25
 * @desc 手机信息展示
 */
public class PhoneInfoActivity extends ActivityBase {
    @Bind(R.id.lay_content)
    LinearLayout layCOnten;

    @Override
    protected void setWantShowContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_phone_info);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        //屏幕密度
        addInfo("屏幕密度：" + String.valueOf(PhoneScreenInfoUtils.getDensity(this)));
        //屏幕高度
        addInfo("屏幕高度：" + String.valueOf(PhoneScreenInfoUtils.getScreenHeight(this)));
        //屏幕宽度
        addInfo("屏幕宽度：" + String.valueOf(PhoneScreenInfoUtils.getScreenWidth(this)));
        //状态栏高度
        addInfo("状态栏高度：" + String.valueOf(PhoneScreenInfoUtils.getStatusBarHeight(this)));
        //获取虚拟键高度，没有获取为0
        addInfo("虚拟键高度：" + String.valueOf(PhoneScreenInfoUtils.getNavigationBarHeight(this)));
    }

    public void addInfo(String infoValue) {
        TextView textView = new TextView(this);
        textView.setText(String.valueOf(infoValue));
        layCOnten.addView(textView);
    }
}
