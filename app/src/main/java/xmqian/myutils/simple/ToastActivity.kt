package xmqian.myutils.simple

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import butterknife.BindString
import butterknife.ButterKnife
import butterknife.OnClick
import com.coszero.uilibrary.widget.CountDownTextView
import com.coszero.utilslibrary.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_fragment_toast.*
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
        ctv_get_code?.setOnClickListener { ctv_get_code?.startCountDown(59) }
    }

    @OnClick(R.id.textView2, R.id.textView3)
    fun showShortToast(view: View) {
        index++
        when (view.id) {
            R.id.textView2 -> {
                //        ToastUtils.showMsg(this,"显示短时间显示"+index +"次");
                /*需要注册全局Context*/
                ToastUtils.showMsg("显示长时间显示$index 次")
            }
            R.id.textView3 -> {
                //        ToastUtils.showLongMsg(this,"显示长时间显示"+index +"次");
                /*需要注册全局Context*/
                ToastUtils.showLongMsg("显示长时间显示" + index + "次")
            }
        }
    }
}
