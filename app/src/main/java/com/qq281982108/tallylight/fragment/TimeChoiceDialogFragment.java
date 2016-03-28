package com.qq281982108.tallylight.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qq281982108.tallylight.R;
import com.qq281982108.tallylight.view.WheelStyle;
import com.qq281982108.tallylight.view.WheelView;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-28 19:47
 * 类名：TimeChoiceDialogFragment
 * 修改备注：
 */
public class TimeChoiceDialogFragment extends DialogFragment {
    int selectYear;
    int selectMonth;
    private WheelView yearWheel;
    private WheelView monthWheel;
    private WheelView dayWheel;
    private WheelView leftWheel;
    private WheelView rightWheel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //如果setCancelable()中参数为true，若点击dialog覆盖不到的activity的空白或者按返回键，
        //则进行cancel，状态检测依次onCancel()和onDismiss()。如参数为false，则按空白处或返回键无反应。缺省为true
        setCancelable(true);
        //可以设置dialog的显示风格
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View rootView = inflater.inflate(R.layout.activity_time_choice, null);
        yearWheel = (WheelView) rootView.findViewById(R.id.select_date_wheel_year_wheel);
        monthWheel = (WheelView) rootView.findViewById(R.id.select_date_month_wheel);
        dayWheel = (WheelView) rootView.findViewById(R.id.select_date_day_wheel);
        leftWheel = (WheelView) rootView.findViewById(R.id.select_time_wheel_left);
        rightWheel = (WheelView) rootView.findViewById(R.id.select_time_wheel_right);
        leftWheel.setWheelStyle(WheelStyle.STYLE_HOUR);
        rightWheel.setWheelStyle(WheelStyle.STYLE_MINUTE);
        yearWheel.setWheelStyle(WheelStyle.STYLE_YEAR);
        leftWheel.setCurrentItem(12);
        rightWheel.setCurrentItem(12);
        yearWheel.setOnSelectListener(new WheelView.onSelectListener() {
            @Override
            public void onSelect(int index, String text) {
                selectYear = index + WheelStyle.minYear;
                dayWheel.setWheelItemList(WheelStyle.createDayString(selectYear, selectMonth));
            }
        });

        monthWheel.setWheelStyle(WheelStyle.STYLE_MONTH);
        monthWheel.setOnSelectListener(new WheelView.onSelectListener() {
            @Override
            public void onSelect(int index, String text) {
                selectMonth = index + 1;
                dayWheel.setWheelItemList(WheelStyle.createDayString(selectYear, selectMonth));
            }
        });
        return rootView;
    }
}
