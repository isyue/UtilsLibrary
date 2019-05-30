package xmqian.myutils.simple

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

import butterknife.Bind
import butterknife.ButterKnife
import com.coszero.utilslibrary.utils.PhoneInfoGetUtils
import com.coszero.utilslibrary.utils.PhoneNetStateUtils
import com.coszero.utilslibrary.utils.PhoneScreenInfoUtils
import xmqian.myutils.ActivityBase
import xmqian.myutils.R

/**
 * @author xmqian
 * @date 2018/5/25
 * @desc 手机信息展示
 */
class PhoneInfoActivity : ActivityBase() {
    @Bind(R.id.lay_content)
    internal var layCOnten: LinearLayout? = null
    private var tvNetState: TextView? = null

    override fun setWantShowContentView(savedInstanceState: Bundle) {
        setContentView(R.layout.activity_phone_info)
        ButterKnife.bind(this)
        tvNetState = findViewById<View>(R.id.tv_netState) as TextView?

        PhoneNetStateUtils.registerNetState(this) { netWorkState -> tvNetState!!.text = "当前网络状态：$netWorkState" }
    }

    override fun initView() {
        //屏幕密度
        addInfo("屏幕密度：" + PhoneScreenInfoUtils.getDensity(this).toString())
        //屏幕高度
        addInfo("屏幕高度：" + PhoneScreenInfoUtils.getScreenHeight(this).toString())
        //屏幕宽度
        addInfo("屏幕宽度：" + PhoneScreenInfoUtils.getScreenWidth(this).toString())
        //状态栏高度
        addInfo("状态栏高度：" + PhoneScreenInfoUtils.getStatusBarHeight(this).toString())
        //获取虚拟键高度，没有获取为0
        addInfo("虚拟键高度：" + PhoneScreenInfoUtils.getNavigationBarHeight(this).toString())
        //获取是否有SD卡
        addInfo("是否有SD卡：" + if (PhoneInfoGetUtils.isExitsSdcard()) "有" else "无")
        //获取屏幕状态
        addInfo("屏幕状态：" + if (PhoneInfoGetUtils.isScreenOriatationPortrait(this)) "竖屏" else "横屏")
        //获取手机SN号
        addInfo("手机SN号：" + PhoneInfoGetUtils.getSN(this).toString())
        //获取手机卡SN号
        addInfo("手机卡SN号:" + PhoneInfoGetUtils.getSimSN(this).toString())
        //是否有虚拟按键
        addInfo("是否拥有虚拟按键：" + if (PhoneInfoGetUtils.checkDeviceHasNavigationBar(this)) "有" else "无")
        //获取设备唯一标识符
        addInfo("设备唯一标识符：" + PhoneInfoGetUtils.getUniqueId(this).toString())
        //获取设备IMEI号
        addInfo("设备IMEI号：" + PhoneInfoGetUtils.getDeviceIMEI(this).toString())
        //获取Wifi Mac地址
        addInfo("Wifi Mac地址：" + PhoneInfoGetUtils.getWifiMac(this).toString())
        //获取本地Mac地址
        addInfo("本地Mac地址：" + PhoneInfoGetUtils.getLocalMacAddress(this).toString())
        //获取wifi信号强度
        addInfo("Wifi信号强度值：" + PhoneNetStateUtils.getWifiState(this).toString())
    }

    private fun addInfo(infoValue: String) {
        val textView = TextView(this)
        textView.text = infoValue
        layCOnten!!.addView(textView)
    }

    override fun onDestroy() {
        super.onDestroy()
        PhoneNetStateUtils.unregisterNetState(this)
    }
}
