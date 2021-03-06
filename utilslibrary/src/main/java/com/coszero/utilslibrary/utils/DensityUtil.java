package com.coszero.utilslibrary.utils;

import android.content.Context;

/**
 * @author xmqian
 * @date 2018/5/25
 * @desc Android密度单位转换
 */
public class DensityUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
        /*系统转换方式*/
//        float scale = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
//        return (int) scale;
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 sp(像素) 的单位 转成为 px
     *
     * @param paramContext
     * @param paramFloat
     * @return
     */
    public static int sp2px(Context paramContext, float paramFloat) {
        float f = paramContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (paramFloat * f + 0.5F);
        /*系统转换方式*/
//        float scale = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, paramFloat, paramContext.getResources().getDisplayMetrics());
//        return (int) scale;
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 sp
     *
     * @param paramContext
     * @param paramFloat
     * @return
     */
    public static int px2sp(Context paramContext, float paramFloat) {
        float f = paramContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (paramFloat / f + 0.5F);
    }
}
