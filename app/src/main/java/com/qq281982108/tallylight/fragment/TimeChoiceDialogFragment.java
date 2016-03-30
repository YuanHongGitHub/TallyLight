package com.qq281982108.tallylight.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
    private int selectYear = AddPageExpendFragment.SELECT_YEAR;
    private int selectMonth = AddPageExpendFragment.SELECT_MONTH;
    private int selectDay = AddPageExpendFragment.SELECT_DAY;
    private int selectHour = AddPageExpendFragment.SELECT_HOUR;
    private int selectMinute = AddPageExpendFragment.SELECT_MINUTE;
    private WheelView yearWheel;
    private WheelView monthWheel;
    private WheelView dayWheel;
    private WheelView leftWheel;
    private WheelView rightWheel;
    private Button mButton;

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
        View rootView = inflater.inflate(R.layout.dialog_fragment_time_choice, null);
        yearWheel = (WheelView) rootView.findViewById(R.id.select_date_wheel_year_wheel);
        monthWheel = (WheelView) rootView.findViewById(R.id.select_date_month_wheel);
        dayWheel = (WheelView) rootView.findViewById(R.id.select_date_day_wheel);
        leftWheel = (WheelView) rootView.findViewById(R.id.select_time_wheel_left);
        rightWheel = (WheelView) rootView.findViewById(R.id.select_time_wheel_right);

        mButton = (Button) rootView.findViewById(R.id.time_choice_dialog_btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });

        yearWheel.setWheelStyle(WheelStyle.STYLE_YEAR);
        monthWheel.setWheelStyle(WheelStyle.STYLE_MONTH);
        dayWheel.setWheelStyle(WheelStyle.STYLE_DAY);
        leftWheel.setWheelStyle(WheelStyle.STYLE_HOUR);
        rightWheel.setWheelStyle(WheelStyle.STYLE_MINUTE);

        yearWheel.setCurrentItem(AddPageExpendFragment.SELECT_YEAR - WheelStyle.minYear);
        monthWheel.setCurrentItem(AddPageExpendFragment.SELECT_MONTH - 1);
        dayWheel.setCurrentItem(AddPageExpendFragment.SELECT_DAY - 1);
        leftWheel.setCurrentItem(AddPageExpendFragment.SELECT_HOUR);
        rightWheel.setCurrentItem(AddPageExpendFragment.SELECT_MINUTE);

        yearWheel.setOnSelectListener(new WheelView.onSelectListener() {
            @Override
            public void onSelect(int index, String text) {
                if (index == AddPageExpendFragment.SELECT_YEAR - WheelStyle.minYear) return;
                selectYear = index + WheelStyle.minYear;
                dayWheel.setWheelItemList(WheelStyle.createDayString(selectYear, selectMonth));
            }
        });


        monthWheel.setOnSelectListener(new WheelView.onSelectListener() {
            @Override
            public void onSelect(int index, String text) {
                if (index == (AddPageExpendFragment.SELECT_MONTH - 1)) return;
                selectMonth = index + 1;
                dayWheel.setWheelItemList(WheelStyle.createDayString(selectYear, selectMonth));
            }
        });

        dayWheel.setOnSelectListener(new WheelView.onSelectListener() {
            @Override
            public void onSelect(int index, String text) {
                if (index == (AddPageExpendFragment.SELECT_DAY - 1)) return;
                selectDay = index + 1;
            }
        });
        leftWheel.setOnSelectListener(new WheelView.onSelectListener() {
            @Override
            public void onSelect(int index, String text) {
                if (index == AddPageExpendFragment.SELECT_HOUR) return;
                selectHour = index;
            }
        });
        rightWheel.setOnSelectListener(new WheelView.onSelectListener() {
            @Override
            public void onSelect(int index, String text) {
                if (index == AddPageExpendFragment.SELECT_MINUTE) return;
                selectMinute = index;
            }
        });
        return rootView;
    }

    private void send() {
        Log.e("yh", "choice:" + selectYear + selectMonth + selectDay + selectHour + selectMinute);
        AddPageExpendFragment.SELECT_YEAR = selectYear;
        AddPageExpendFragment.SELECT_MONTH = selectMonth;
        AddPageExpendFragment.SELECT_DAY = selectDay;
        AddPageExpendFragment.SELECT_HOUR = selectHour;
        AddPageExpendFragment.SELECT_MINUTE = selectMinute;
        Intent intent = new Intent().setAction("android.basic.msg");
        getActivity().sendBroadcast(intent);
        dismiss();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
//        OnTimeSelectListener mListener = (OnTimeSelectListener) getActivity();

    }
}
