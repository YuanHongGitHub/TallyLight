package com.qq281982108.tallylight.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.qq281982108.tallylight.R;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-26 10:28
 * 类名：AddPageExpendFragment
 * 修改备注：
 */
public class AddPageExpendFragment extends BaseFragment {

    private LinearLayout mLinearLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_add_page_expend, container, false);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.ll_add_fragment_time);
        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeChoiceDialogFragment scalePopUpWindow = new TimeChoiceDialogFragment();
                scalePopUpWindow.show(getActivity().getFragmentManager(), "test");
            }
        });
        return view;
    }
}
