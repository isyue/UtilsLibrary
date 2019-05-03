package xmqian.myutils.simple;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coszero.utilslibrary.utils.DensityUtil;
import xmqian.myutils.ActivityBase;
import xmqian.myutils.R;

/**
 * @author xmqian
 * @date 2018/5/25
 * @desc 测试密度转换
 */
public class DensitySimpleActivity extends ActivityBase {
    @Bind(R.id.edit_input)
    TextInputEditText inputEditText;
    @Bind(R.id.tv_show_value)
    TextView tvShowValue;

    @Override
    protected void setWantShowContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_density_simple);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {

    }

    public float getInputValue() {
        if (TextUtils.isEmpty(inputEditText.getText().toString())) {
            return 0;
        } else {
            return Float.valueOf(inputEditText.getText().toString());
        }
    }

    public void setChangeValue(int value) {
        tvShowValue.setText("转换后的值为：" + String.valueOf(value));
    }


    @OnClick(R.id.tv_dp2px)
    public void dp2px() {
        setChangeValue(DensityUtil.dip2px(this, getInputValue()));
    }

    @OnClick(R.id.tv_sp2px)
    public void sp2px() {
        setChangeValue(DensityUtil.sp2px(this, getInputValue()));
    }

    @OnClick(R.id.tv_px2dp)
    public void px2dp() {
        setChangeValue(DensityUtil.px2dip(this, getInputValue()));
    }

    @OnClick(R.id.tv_px2sp)
    public void px2sp() {
        setChangeValue(DensityUtil.px2sp(this, getInputValue()));
    }
}
