package xmqian.myutils

import android.app.Application

import com.coszero.utilslibrary.utils.ToastUtils

/**
 * @author xmqian
 * @date 2018/5/25
 * @desc
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ToastUtils.init(this)
    }
}
