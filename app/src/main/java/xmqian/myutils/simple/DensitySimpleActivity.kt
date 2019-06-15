package xmqian.myutils.simple

import android.text.TextUtils
import butterknife.ButterKnife
import butterknife.OnClick
import com.coszero.utilslibrary.utils.DensityUtil
import kotlinx.android.synthetic.main.activity_density_simple.*
import xmqian.myutils.ActivityBase
import xmqian.myutils.R

/**
 * @author xmqian
 * @date 2018/5/25
 * @desc 测试密度转换
 */
class DensitySimpleActivity : ActivityBase() {
    override fun getLayoutId(): Int {
        return R.layout.activity_density_simple
    }


    private val inputValue: Float
        get() = if (TextUtils.isEmpty(edit_input!!.text!!.toString())) {
            0f
        } else {
            java.lang.Float.valueOf(edit_input!!.text!!.toString())
        }

    override fun initView() {
        ButterKnife.bind(this)
        dd_text.setPadding(0, 0, DensityUtil.dip2px(this, 16f), 0)
    }

    private fun setChangeValue(value: Int) {
        tv_show_value!!.text = "转换后的值为：$value"
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
