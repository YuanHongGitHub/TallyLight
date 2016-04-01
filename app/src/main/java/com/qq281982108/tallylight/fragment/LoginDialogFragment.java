package com.qq281982108.tallylight.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qq281982108.tallylight.R;

import cn.bmob.v3.BmobUser;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-31 19:17
 * 类名：LoginDialogFragment
 * 修改备注：
 */
public class LoginDialogFragment extends DialogFragment {
    private BmobUser mBmobUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_fragment_login, null);

        return rootView;
    }
}
