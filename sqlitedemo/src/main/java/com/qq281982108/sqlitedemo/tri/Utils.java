package com.qq281982108.sqlitedemo.tri;

import android.content.Context;
import android.util.TypedValue;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-04-08 16:08
 * 类名：Utils
 * 修改备注：
 */
public class Utils {

    public static int dp2px(Context context, int dp) {
        return Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics()));
    }

}
