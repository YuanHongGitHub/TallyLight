package com.qq281982108.tallylight.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qq281982108.tallylight.R;
import com.qq281982108.tallylight.util.TimeUtils;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-26 10:28
 * 类名：AddPageExpendFragment
 * 修改备注：
 */
public class AddPageExpendFragment extends BaseFragment
        implements View.OnClickListener,
        TimeChoiceDialogFragment.OnTimeSelectedListener,
        MemberChoiceDialogFragment.OnMemberSelectedListener,
        CategoryChoiceDialogFragment.OnCategorySelectedListener {
    public int[] time = new int[5];
    private TextView mMoneyTV, mSelectTimeTV, mSelectMemberTV, mSelectCategoryTV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_add_page_expend, container, false);
        mMoneyTV = (TextView) view.findViewById(R.id.expend_tv_spending_amount);
        mSelectTimeTV = (TextView) view.findViewById(R.id.tv_add_fragment_time_time);
        mSelectTimeTV.setText(TimeUtils.getTime(TimeUtils.getCurrentTimeInLong()));
        mSelectMemberTV = (TextView) view.findViewById(R.id.tv_add_fragment_member);
        mSelectCategoryTV = (TextView) view.findViewById(R.id.expend_tv_spending_category);

        view.findViewById(R.id.ll_add_fragment_time).setOnClickListener(this);
        view.findViewById(R.id.ll_add_fragment_member).setOnClickListener(this);
        view.findViewById(R.id.expend_rl_spending_category).setOnClickListener(this);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.expend_tv_spending_amount:

                break;
            case R.id.ll_add_fragment_time:
                time[0] = TimeUtils.getYear();
                time[1] = TimeUtils.getMonth();
                time[2] = TimeUtils.getMonthDay();
                time[3] = TimeUtils.getHour();
                time[4] = TimeUtils.getMinute();
                TimeChoiceDialogFragment timeChoiceDialogFragment = TimeChoiceDialogFragment.newInstance(time);
                timeChoiceDialogFragment.setOnTimeSelectedListener(this);
                timeChoiceDialogFragment.show(getActivity().getFragmentManager(), "time");
                break;
            case R.id.ll_add_fragment_member:
                MemberChoiceDialogFragment memberChoiceDialogFragment = new MemberChoiceDialogFragment();
                memberChoiceDialogFragment.setOnMemberSelectedListener(this);
                memberChoiceDialogFragment.show(getActivity().getFragmentManager(), "member");
                break;
            case R.id.expend_rl_spending_category:
                CategoryChoiceDialogFragment categoryChoiceDialogFragment = new CategoryChoiceDialogFragment();
                categoryChoiceDialogFragment.setOnCategorySelectedListener(this);
                categoryChoiceDialogFragment.show(getActivity().getFragmentManager(), "category");
                break;
            default:
                break;
        }
    }

    @Override
    public void onSelect(String tag, int[] ints) {
        switch (tag) {
            case "time":
                time[0] = ints[0];
                time[1] = ints[1];
                time[2] = ints[2];
                time[3] = ints[3];
                time[4] = ints[4];
                String month = time[1] > 9 ? "-" + time[1] : "-0" + time[1];
                String day = time[2] > 9 ? "-" + time[2] : "-0" + time[2];
                String hour = time[3] > 9 ? " " + time[3] : "0" + time[3];
                String minute = time[4] > 9 ? ":" + time[4] : ":0" + time[4];
                mSelectTimeTV.setText(time[0] + month + day + " " + hour + minute);
                break;
            default:
                mSelectTimeTV.setText("00");
                break;
        }

    }

    @Override
    public void onSelect(String tag, String s) {
        mSelectMemberTV.setText(s);
    }

    @Override
    public void onSelect(String s) {
        mSelectCategoryTV.setText(s);
    }

}
