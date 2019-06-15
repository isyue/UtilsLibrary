package xmqian.myutils.simple

import android.widget.TextView
import com.coszero.utilslibrary.utils.TimeUtils
import kotlinx.android.synthetic.main.activity_time_simple.*
import xmqian.myutils.ActivityBase
import xmqian.myutils.R
import java.util.*

/**
 * @author xmqian
 * @date 2018/5/29
 * @desc
 */
class TimeSimpleActivity : ActivityBase() {
    override fun getLayoutId(): Int {
        return R.layout.activity_time_simple
    }

    private val date: Date? = null

    override fun initView() {
        //以默认格式获取系统时间
        addInfo("默认格式系统时间：" + TimeUtils.getCurrentTime())
        //以需要的格式获取
        addInfo("自定义格式获取：" + TimeUtils.getCurrentTime(TimeUtils.DF_YYYY_MM_DD_HH_MM_SS))
        //获取未经处理的时间格式
        addInfo("未经处理的时间：" + TimeUtils.getLongTime())
        //处理long类型的时间
        addInfo("处理long类型的时间：" + TimeUtils.formatDateTime(TimeUtils.getLongTime()))
        //处理long类型的时间获取自定义格式
        addInfo("处理long类型的时间获取自定义格式：" + TimeUtils.formatDateTime(TimeUtils.getLongTime(), TimeUtils.DF_YYYY_MM_DD))
        //根据日期算出星期几
        addInfo("计算今天星期几：" + TimeUtils.getWeekName(TimeUtils.getYear().toString() + "年" + TimeUtils.getMonth() + "月" + TimeUtils.getDay() + "日", 0))
        //加一小时后的日期
        addInfo("固定时间[2018-01-01 03:18:33]加一小时后的日期：" + TimeUtils.getFormatDate(TimeUtils.addDateTime(TimeUtils.parseDate("2018-01-01 03:18:33"), 1.0), TimeUtils.DF_YYYY_MM_DD_HH_MM_SS))

    }

    private fun addInfo(infoValue: String) {
        val textView = TextView(this)
        textView.text = infoValue
        lay_content!!.addView(textView)
    }
}
