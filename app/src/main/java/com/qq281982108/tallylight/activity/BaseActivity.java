package com.qq281982108.tallylight.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.qq281982108.tallylight.util.ActivityCollector;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-23 14:55
 * 类名：BaseActivity
 * 修改备注：FragmentActivity的基类
 * 1.和ActivityCollector搭配使用，可随时关闭Activity
 */
public class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // hide the window title.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // hide the status bar and other OS-level chrome
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        Log.d("BaseActivity", getClass().getSimpleName());
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
