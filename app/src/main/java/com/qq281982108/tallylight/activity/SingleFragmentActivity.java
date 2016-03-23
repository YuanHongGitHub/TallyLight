package com.qq281982108.tallylight.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.qq281982108.tallylight.R;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-23 14:59
 * 类名：SingleFragmentActivity
 * 修改备注：
 */
public abstract class SingleFragmentActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);

        if (fragment == null) {
            fragment = createFragment();
            manager.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }

    protected int getLayoutResId() {
        return R.layout.activity_fragment;
    }

    protected abstract Fragment createFragment();
}