package com.qq281982108.tallylight.renyugang;

import android.content.Context;
import android.util.TypedValue;

public class Utils {

    public static int dp2px(Context context, int dp) {
        return Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics()));
    }

}
