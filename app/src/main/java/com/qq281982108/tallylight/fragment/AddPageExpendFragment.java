package com.qq281982108.tallylight.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.qq281982108.tallylight.R;
import com.qq281982108.tallylight.util.CalculatorUtil;
import com.qq281982108.tallylight.util.TimeUtils;
import com.qq281982108.tallylight.view.CalculatorPopupWindow;

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
        CategoryChoiceDialogFragment.OnCategorySelectedListener,
        CalculatorUtil.CalculatorUtilListener {
    public int[] time = new int[5];
    boolean hasPause = false;
    private TextView mMoneyTV, mSelectTimeTV, mSelectMemberTV, mSelectCategoryTV;
    private CalculatorPopupWindow mCalculatorPopupWindow;
    private String textMember = "自己", textCategory = "早餐", textMoney = "0.0";
    private int popHeight;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {

        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_add_page_expend, container, false);
        mMoneyTV = (TextView) view.findViewById(R.id.expend_tv_spending_amount);

        mSelectTimeTV = (TextView) view.findViewById(R.id.tv_add_fragment_time_time);
        mSelectTimeTV.setText(TimeUtils.getTime(TimeUtils.getCurrentTimeInLong()));
        mSelectMemberTV = (TextView) view.findViewById(R.id.tv_add_fragment_member);
        mSelectCategoryTV = (TextView) view.findViewById(R.id.expend_tv_spending_category);

        mMoneyTV.setOnClickListener(this);
        view.findViewById(R.id.ll_add_fragment_time).setOnClickListener(this);
        view.findViewById(R.id.ll_add_fragment_member).setOnClickListener(this);
        view.findViewById(R.id.expend_rl_spending_category).setOnClickListener(this);

        SharedPreferences getData = getContext().getSharedPreferences("getData", Context.MODE_PRIVATE);
        if (getData != null && hasPause) {
            Log.e("yh", "onCreateView");
            mSelectMemberTV.setText(getData.getString("member", textMember));
            mSelectCategoryTV.setText(getData.getString("category", textCategory));
            mMoneyTV.setText(getData.getString("money", textMoney));
            int year = getData.getInt("time0", time[0]);
            int getMonth = getData.getInt("time1", time[1]);
            int getDay = getData.getInt("time2", time[2]);
            int getHour = getData.getInt("time3", time[3]);
            int getMinute = getData.getInt("time4", time[4]);
            String month = getMonth > 9 ? "-" + getMonth : "-0" + getMonth;
            String day = getDay > 9 ? "-" + getDay : "-0" + getDay;
            String hour = getHour > 9 ? " " + getHour : "0" + getHour;
            String minute = getMinute > 9 ? ":" + getMinute : ":0" + getMinute;
            mSelectTimeTV.setText(year + month + day + " " + hour + minute);
        } else {
//            Log.e("yh", "onCreateView");
            time[0] = TimeUtils.getYear();
            time[1] = TimeUtils.getMonth();
            time[2] = TimeUtils.getMonthDay();
            time[3] = TimeUtils.getHour();
            time[4] = TimeUtils.getMinute();
        }
        return view;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.e("yh", "onCreate");
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);

        popHeight = (int) (outMetrics.heightPixels * 0.5);
    }


    @Override
    public void onPause() {
        super.onPause();
        hasPause = true;
        SharedPreferences mSharedPreferences = getContext().getSharedPreferences("getData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("member", textMember);
        editor.putString("category", textCategory);
        editor.putString("money", textMoney);
        editor.putInt("time0", time[0]);
        editor.putInt("time1", time[1]);
        editor.putInt("time2", time[2]);
        editor.putInt("time3", time[3]);
        editor.putInt("time4", time[4]);
        editor.apply();
        Log.e("yh", "onPause");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.expend_tv_spending_amount:
                if (mCalculatorPopupWindow == null) {
                    mCalculatorPopupWindow = new CalculatorPopupWindow(getContext());
                    mCalculatorPopupWindow.setHeight(popHeight);
                }
                CalculatorUtil.setCalculatorUtilListener(this);
//                mCalculatorPopupWindow.setOnCalculatorListener(this);
                mCalculatorPopupWindow.showAtLocation(getView(), Gravity.CENTER, 0, popHeight / 2);
                break;
            case R.id.ll_add_fragment_time:
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
        time = ints;
        String month = time[1] > 9 ? "-" + time[1] : "-0" + time[1];
        String day = time[2] > 9 ? "-" + time[2] : "-0" + time[2];
        String hour = time[3] > 9 ? " " + time[3] : "0" + time[3];
        String minute = time[4] > 9 ? ":" + time[4] : ":0" + time[4];
        mSelectTimeTV.setText(time[0] + month + day + " " + hour + minute);
    }

    @Override
    public void onSelect(String tag, String s) {
        mSelectMemberTV.setText(s);
        textMember = s;
    }

    @Override
    public void onSelect(String s) {
        mSelectCategoryTV.setText(s);
        textCategory = s;
    }

    @Override
    public void onCalculated(StringBuffer stringBuffer) {
        mMoneyTV.setText(stringBuffer.toString());
        Log.e("yh", "mMoneyTV" + stringBuffer.toString());
        textMoney = stringBuffer.toString();
    }

    @Override
    public void onCalculated(String result) {
        mMoneyTV.setText(result);
        textMoney = result;
        Log.e("yh", "mMoneyTV" + result);
    }
}
