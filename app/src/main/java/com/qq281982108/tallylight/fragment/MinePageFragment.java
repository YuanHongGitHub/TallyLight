package com.qq281982108.tallylight.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.qq281982108.tallylight.R;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-25 12:50
 * 类名：RecordPageFragment
 * 修改备注：
 */
public class MinePageFragment extends BaseFragment implements View.OnClickListener {
    LoginDialogFragment mLoginDialogFragment = new LoginDialogFragment();
    private RelativeLayout mLoginStatusRL;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_mine_page, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mLoginStatusRL = (RelativeLayout) view.findViewById(R.id.rl_mine_page_login_status);
        mLoginStatusRL.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_mine_page_login_status:
                //TODO
                mLoginDialogFragment.show(getActivity().getFragmentManager(), "login");
                break;
            default:
                break;
        }
    }
}
