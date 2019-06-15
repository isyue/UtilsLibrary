package xmqian.myutils.simple

import com.coszero.utilslibrary.utils.KeyBoardUtils
import kotlinx.android.synthetic.main.activity_check_input.*
import xmqian.myutils.ActivityBase
import xmqian.myutils.R


/**
 * @author xmqian
 * @date 2018/10/17 19:20
 * @desc 输入检查
 */
class CheckInputActivity : ActivityBase() {
    override fun initView() {
        btn_input_text.setOnClickListener {
            if (KeyBoardUtils.isKeyBoardOpened(this)) {
                KeyBoardUtils.closeKeybord(edit_input, this)
            } else {
                KeyBoardUtils.openKeybord(edit_input, this)
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_check_input
    }
}
