package com.qq281982108.tallylight.util;

import android.content.Context;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-28 13:17
 * 类名：SizeConvertUtil
 * 修改备注：尺寸转换工具
 */
public class SizeConvertUtil {


    public static int dpTopx(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int pxTodp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static int pxTosp(Context context, float px) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (px / fontScale + 0.5f);
    }

    public static int spTopx(Context context, float sp) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * fontScale + 0.5f);
    }
}
