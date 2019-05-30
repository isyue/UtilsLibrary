package xmqian.myutils

import android.os.Bundle

import butterknife.ButterKnife
import com.coszero.utilslibrary.base.BaseActivity

/**
 * @author xmqian
 * @date 2018/5/25
 * @desc
 */
abstract class ActivityBase : BaseActivity() {
    abstract override fun setWantShowContentView(savedInstanceState: Bundle)
    abstract override fun initView()

    override fun onDestroy() {
        super.onDestroy()
        ButterKnife.unbind(this)
    }
}
