package xmqian.myutils.simple

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import butterknife.ButterKnife
import butterknife.OnClick
import com.coszero.utilslibrary.utils.ToastUtils
import xmqian.myutils.R

/**
 * Toast工具类测试
 */
class ToastActivity : AppCompatActivity() {

    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_toast)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.textView2)
    fun showShortToast() {
        index++
        //        ToastUtils.showMsg(this,"显示短时间显示"+index +"次");
        /*需要注册全局Context*/
        ToastUtils.showMsg("显示长时间显示" + index + "次")
    }

    @OnClick(R.id.textView3)
    fun showLongToast() {
        index++
        //        ToastUtils.showLongMsg(this,"显示长时间显示"+index +"次");
        /*需要注册全局Context*/
        ToastUtils.showLongMsg("显示长时间显示" + index + "次")
    }

    override fun onDestroy() {
        super.onDestroy()
        ButterKnife.unbind(this)
    }
}
