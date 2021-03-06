package com.coszero.uilibrary.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;


import com.coszero.uilibrary.R;
import com.coszero.utilslibrary.utils.DensityUtil;

import java.lang.ref.WeakReference;

/**
 * 用于倒计时的 TextView
 * 使用方法 startCountDown(int sec)
 * Created by xuyougen on 2018/4/12.
 */

public class CountDownTextView extends android.support.v7.widget.AppCompatTextView {

    private RectF mRectF;
    private Paint mPaint;
    private CountDownHandler mCountDownHandler;

    private boolean mIsStroke = false;
    //正常描边色
    private int mNormalStrokeColor;
    //禁用描边色
    private int mDisabledStrokeColor = Color.parseColor("#BBBBBB");
    //描边的宽度
    private int mStrokeWidth = 2;

    private String mDefaultString;
    private String mSecString = "秒";

    public CountDownTextView(Context context) {
        this(context, null);
    }

    public CountDownTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountDownTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.CountDownTextView);
        /*这里从集合里取出相对应的属性值,第二参数是如果使用者没用配置该属性时所用的默认值*/
        mIsStroke = typeArray.getBoolean(R.styleable.CountDownTextView_is_Stroke, false);
        mStrokeWidth = typeArray.getInteger(R.styleable.CountDownTextView_stroke_Width, 2);
        mNormalStrokeColor = typeArray.getColor(R.styleable.CountDownTextView_enable_Color, getResources().getColor(R.color.ui_color_orange));
        mDisabledStrokeColor = typeArray.getColor(R.styleable.CountDownTextView_disable_Color, Color.parseColor("#BBBBBB"));
        init(context);
    }

    private void init(Context context) {
        setPadding(DensityUtil.dip2px(context, 15), DensityUtil.dip2px(context, 5), DensityUtil.dip2px(context, 15), DensityUtil.dip2px(context, 5));
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeWidth);
        mCountDownHandler = new CountDownHandler(this);
        mDefaultString = getText().toString();
        mNormalStrokeColor = getCurrentTextColor();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRectF = new RectF(mStrokeWidth, mStrokeWidth, w - mStrokeWidth, h - mStrokeWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.isEnabled()) {
            mPaint.setColor(mNormalStrokeColor);
            this.setTextColor(mNormalStrokeColor);
        } else {
            mPaint.setColor(mDisabledStrokeColor);
            this.setTextColor(mDisabledStrokeColor);
        }
        if (mIsStroke) {
            canvas.drawRoundRect(mRectF, getHeight() / 2, getHeight() / 2, mPaint);
        }
    }

    public void startCountDown(int time) {
        startCountDown(time, mSecString);
    }

    /**开始倒计时
     * @param time
     * @param unitString 计时单位
     */
    public void startCountDown(int time, String unitString) {
        if (!mCountDownHandler.isCountDownFinished()) return;
        mCountDownHandler.startCountDown(time, mDefaultString, unitString);
    }

    public void setNormalStrokeColor(@ColorInt int normalStrokeColor) {
        mNormalStrokeColor = normalStrokeColor;
        invalidate();
    }

    public void setDisabledStrokeColor(@ColorInt int disabledStrokeColor) {
        mDisabledStrokeColor = disabledStrokeColor;
        invalidate();
    }

    public void setNormalStrokeColorRes(@ColorRes int colorRes) {
        mNormalStrokeColor = ContextCompat.getColor(getContext(), colorRes);
        invalidate();
    }

    public void setDisabledStrokeColorRes(@ColorRes int colorRes) {
        mDisabledStrokeColor = ContextCompat.getColor(getContext(), colorRes);
        invalidate();
    }

    private static class CountDownHandler extends Handler {
        private int mCountDownTime;
        private WeakReference<CountDownTextView> mWeakReference;
        private static final int WHAT_COUNT_DOWN_TIME = 0x23;

        private String mSecStr;
        private String mResetStr;

        CountDownHandler(CountDownTextView countDownTextView) {
            mWeakReference = new WeakReference<CountDownTextView>(countDownTextView);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (!(mCountDownTime <= 0)) {
                mCountDownTime--;
                if (mWeakReference.get() != null) {
                    mWeakReference.get().setText(formatNumStr(mCountDownTime) + mSecStr);
                    this.sendEmptyMessageDelayed(WHAT_COUNT_DOWN_TIME, 1000);
                } else {
                    this.removeCallbacksAndMessages(null);
                }
            } else {
                if (mWeakReference.get() != null) {
                    mWeakReference.get().setText(mResetStr);
                    mWeakReference.get().setEnabled(true);
                }
                this.removeCallbacksAndMessages(null);
            }
        }

        @SuppressLint("SetTextI18n")
        void startCountDown(int countDownTime, String resetStr, String secStr) {
            mSecStr = secStr;
            mResetStr = resetStr;
            mCountDownTime = countDownTime;
            if (mCountDownTime <= 0) return;
            if (mWeakReference.get() != null) {
                mWeakReference.get().setText(formatNumStr(mCountDownTime) + mSecStr);
                mWeakReference.get().setEnabled(false);
            }
            this.sendEmptyMessageDelayed(WHAT_COUNT_DOWN_TIME, 1000);
        }

        boolean isCountDownFinished() {
            return mCountDownTime <= 0;
        }

        private String formatNumStr(int num) {
            return num < 10 ? "0" + num : String.valueOf(num);
        }
    }
}
