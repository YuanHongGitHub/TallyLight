package com.qq281982108.tallylight.fragment;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-25 12:48
 * 类名：BaseFragment
 * 修改备注：
 */
public class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";
    /**
     * 是否拦截返回键
     *
     * @return false不拦截；true 拦截
     */
    public boolean onBackPressed() {
        return false;
    }

    public void toast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        Log.d(TAG, msg);
    }
}
