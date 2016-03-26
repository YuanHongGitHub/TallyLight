package com.qq281982108.tallylight.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-25 15:35
 * 类名：RecordPageDetailFragment
 * 修改备注：
 */
public class RecordPageGatherFragment extends BaseFragment {
    public static final String BUNDLE_TITLE = "title";
    private String mTitle = "DefaultValue";

    public static RecordPageGatherFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        RecordPageGatherFragment fragment = new RecordPageGatherFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            mTitle = arguments.getString(BUNDLE_TITLE);
        }

        TextView tv = new TextView(getActivity());
        tv.setText(mTitle);
        tv.setGravity(Gravity.CENTER);

        return tv;
    }
}
