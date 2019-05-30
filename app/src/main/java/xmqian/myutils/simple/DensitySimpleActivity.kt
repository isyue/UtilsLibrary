package xmqian.myutils.simple

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.text.TextUtils
import android.widget.TextView

import butterknife.Bind
import butterknife.ButterKnife
import butterknife.OnClick
import com.coszero.utilslibrary.utils.DensityUtil
import xmqian.myutils.ActivityBase
import xmqian.myutils.R

/**
 * @author xmqian
 * @date 2018/5/25
 * @desc 测试密度转换
 */
class DensitySimpleActivity : ActivityBase() {
    @Bind(R.id.edit_input)
    internal var inputEditText: TextInputEditText? = null
    @Bind(R.id.tv_show_value)
    internal var tvShowValue: TextView? = null

    val inputValue: Float
        get() = if (TextUtils.isEmpty(inputEditText!!.text!!.toString())) {
            0f
        } else {
            java.lang.Float.valueOf(inputEditText!!.text!!.toString())
        }

    override fun setWantShowContentView(savedInstanceState: Bundle) {
        setContentView(R.layout.activity_density_simple)
        ButterKnife.bind(this)
    }

    override fun initView() {

    }

    fun setChangeValue(value: Int) {
        tvShowValue!!.text = "转换后的值为：$value"
    }


    @OnClick(R.id.tv_dp2px)
    fun dp2px() {
        setChangeValue(DensityUtil.dip2px(this, inputValue))
    }

    @OnClick(R.id.tv_sp2px)
    fun sp2px() {
        setChangeValue(DensityUtil.sp2px(this, inputValue))
    }

    @OnClick(R.id.tv_px2dp)
    fun px2dp() {
        setChangeValue(DensityUtil.px2dip(this, inputValue))
    }

    @OnClick(R.id.tv_px2sp)
    fun px2sp() {
        setChangeValue(DensityUtil.px2sp(this, inputValue))
    }
}
