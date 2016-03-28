package com.qq281982108.tallylight.util;

import android.content.Context;
import android.widget.Toast;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-28 19:15
 * 类名：ToastUtils
 * 修改备注：
 */
public class ToastUtils {

    public static void ToastMessage(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
