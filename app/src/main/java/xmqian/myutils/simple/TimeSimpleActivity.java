package xmqian.myutils.simple;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.Time;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import regpang.utilslibrary.utils.TimeUtils;
import xmqian.myutils.ActivityBase;
import xmqian.myutils.R;

/**
 * @author xmqian
 * @date 2018/5/29
 * @desc
 */
public class TimeSimpleActivity extends ActivityBase {
    @Bind(R.id.lay_content)
    LinearLayout layContent;
    private Date date;

    @Override
    protected void setWantShowContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_time_simple);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        //以默认格式获取系统时间
        addInfo("默认格式系统时间：" + TimeUtils.getCurrentTime());
        //以需要的格式获取
        addInfo("自定义格式获取：" + TimeUtils.getCurrentTime(TimeUtils.DF_YYYY_MM_DD_HH_MM_SS));
        //获取未经处理的时间格式
        addInfo("未经处理的时间：" + TimeUtils.getLongTime());
        //处理long类型的时间
        addInfo("处理long类型的时间：" + TimeUtils.formatDateTime(TimeUtils.getLongTime()));
        //处理long类型的时间获取自定义格式
        addInfo("处理long类型的时间获取自定义格式：" + TimeUtils.formatDateTime(TimeUtils.getLongTime(), TimeUtils.DF_YYYY_MM_DD));
        //根据日期算出星期几
        addInfo("计算今天星期几：" + TimeUtils.getWeekName(TimeUtils.getYear() + "年" + TimeUtils.getMonth() + "月" + TimeUtils.getDay() + "日", 0));
        //加一小时后的日期
        addInfo("固定时间[2018-01-01 03:18:33]加一小时后的日期：" + TimeUtils.getFormatDate(TimeUtils.addDateTime(TimeUtils.parseDate("2018-01-01 03:18:33"), 1), TimeUtils.DF_YYYY_MM_DD_HH_MM_SS));

    }

    public void addInfo(String infoValue) {
        TextView textView = new TextView(this);
        textView.setText(String.valueOf(infoValue));
        layContent.addView(textView);
    }
}
