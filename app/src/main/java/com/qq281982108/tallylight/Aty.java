package com.qq281982108.tallylight;

import android.content.Context;
import android.content.Intent;

import com.qq281982108.tallylight.activity.BaseActivity;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-23 15:23
 * 类名：Aty
 * 修改备注：
 */
public class Aty extends BaseActivity {

    public static void actionStart(Context context, String data1, String data2) {
        Intent intent = new Intent(context, Aty.class);
        intent.putExtra("param1", data1);
        intent.putExtra("param2", data2);
        context.startActivity(intent);
    }
}
