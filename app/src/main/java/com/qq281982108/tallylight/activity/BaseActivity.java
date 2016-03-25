package com.qq281982108.tallylight.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

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
    public static String TAG = "bmob";
    Toast mToast;

    public static void showLog(String msg) {
        Log.i("smile", msg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // hide the window title.
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // hide the status bar and other OS-level chrome
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        Log.d("BaseActivity", getClass().getSimpleName());
        ActivityCollector.addActivity(this);
    }

    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Log.d(TAG, msg);
    }

    public void showToast(String text) {
        if (!TextUtils.isEmpty(text)) {
            if (mToast == null) {
                mToast = Toast.makeText(getApplicationContext(), text,
                        Toast.LENGTH_SHORT);
            } else {
                mToast.setText(text);
            }
            mToast.show();
        }
    }

    public void showToast(int resId) {
        if (mToast == null) {
            mToast = Toast.makeText(getApplicationContext(), resId,
                    Toast.LENGTH_SHORT);
        } else {
            mToast.setText(resId);
        }
        mToast.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
