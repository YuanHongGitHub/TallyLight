package com.qq281982108.tallylight.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
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
public class AddPageExpendFragment extends BaseFragment implements View.OnClickListener {
    public static int SELECT_YEAR = TimeUtils.getYear();
    public static int SELECT_MONTH = TimeUtils.getMonth();
    public static int SELECT_DAY = TimeUtils.getMonthDay();
    public static int SELECT_HOUR = TimeUtils.getHour();
    public static int SELECT_MINUTE = TimeUtils.getMinute();

    private TextView mSelectTimeTV, mSelectMemberTV, mSelectCategoryTV;
    private Receiver mReceiver = new Receiver();
    private IntentFilter mTimeFilter = new IntentFilter("android.basic.msg");
    private IntentFilter mMemberFilter = new IntentFilter("android.basic.member");
    private IntentFilter mCategoryFilter = new IntentFilter("android.basic.category");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_add_page_expend, container, false);
        mSelectTimeTV = (TextView) view.findViewById(R.id.tv_add_fragment_time_time);
        mSelectTimeTV.setText(TimeUtils.getTime(TimeUtils.getCurrentTimeInLong()));
        mSelectMemberTV = (TextView) view.findViewById(R.id.tv_add_fragment_member);
        mSelectCategoryTV = (TextView) view.findViewById(R.id.expend_tv_spending_category);

        view.findViewById(R.id.ll_add_fragment_time).setOnClickListener(this);
        view.findViewById(R.id.ll_add_fragment_member).setOnClickListener(this);
        view.findViewById(R.id.expend_rl_spending_category).setOnClickListener(this);
        Log.e("yh", "INIT:" + SELECT_YEAR + SELECT_MONTH + SELECT_DAY + SELECT_HOUR + SELECT_MINUTE);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_add_fragment_time:
                TimeChoiceDialogFragment timeChoiceDialogFragment = new TimeChoiceDialogFragment();
                timeChoiceDialogFragment.show(getActivity().getFragmentManager(), "time");
                break;
            case R.id.ll_add_fragment_member:
                MemberChoiceDialogFragment memberChoiceDialogFragment = new MemberChoiceDialogFragment();
                memberChoiceDialogFragment.show(getActivity().getFragmentManager(), "member");
            case R.id.expend_rl_spending_category:
                CategoryChoiceDialogFragment categoryChoiceDialogFragment = new CategoryChoiceDialogFragment();
                categoryChoiceDialogFragment.show(getActivity().getFragmentManager(), "category");

            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(mReceiver, mTimeFilter);
        getActivity().registerReceiver(mReceiver, mMemberFilter);
        getActivity().registerReceiver(mReceiver, mCategoryFilter);
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().unregisterReceiver(mReceiver);
    }

    private class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case "android.basic.msg":
                    mSelectTimeTV.setText(SELECT_YEAR + "-" + SELECT_MONTH + "-" + SELECT_DAY + "-" + " " + SELECT_HOUR + ":" + SELECT_MINUTE);
                    break;
                case "android.basic.member":
                    mSelectMemberTV.setText(intent.getExtras().getString("member"));
                    break;
                case "android.basic.category":
                    mSelectCategoryTV.setText(intent.getExtras().getString("category"));
                    break;
                default:
                    break;
            }
        }
    }
}
