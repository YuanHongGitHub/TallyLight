package com.qq281982108.tallylight.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qq281982108.tallylight.R;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-25 12:50
 * 类名：RecordPageFragment
 * 修改备注：报表
 */
public class StatementPageFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_statement_page, container, false);
        return view;
    }
}
