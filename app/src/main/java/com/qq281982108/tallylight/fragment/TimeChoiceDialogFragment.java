package com.qq281982108.tallylight.fragment;

import android.app.DialogFragment;
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
    private int[] time;
    private int selectYear;
    private int selectMonth;
    private int selectDay;
    private int selectHour;
    private int selectMinute;
    private WheelView yearWheel;
    private WheelView monthWheel;
    private WheelView dayWheel;
    private WheelView leftWheel;
    private WheelView rightWheel;
    private Button mButton;
    private OnTimeSelectedListener mListener;

    public static TimeChoiceDialogFragment newInstance(int[] time) {
        TimeChoiceDialogFragment timeChoiceDialogFragment = new TimeChoiceDialogFragment();
        Bundle args = new Bundle();
        args.putIntArray("time", time);
        timeChoiceDialogFragment.setArguments(args);
        return timeChoiceDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //如果setCancelable()中参数为true，若点击dialog覆盖不到的activity的空白或者按返回键，
        //则进行cancel，状态检测依次onCancel()和onDismiss()。如参数为false，则按空白处或返回键无反应。缺省为true
        setCancelable(true);
        //可以设置dialog的显示风格
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        time = getArguments().getIntArray("time");
        selectYear = time[0];
        selectMonth = time[1];
        selectDay = time[2];
        selectHour = time[3];
        selectMinute = time[4];
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

        yearWheel.setCurrentItem(time[0] - WheelStyle.minYear);
        monthWheel.setCurrentItem(time[1] - 1);
        dayWheel.setCurrentItem(time[2] - 1);
        leftWheel.setCurrentItem(time[3]);
        rightWheel.setCurrentItem(time[4]);

        yearWheel.setOnSelectListener(new WheelView.onSelectListener() {
            @Override
            public void onSelect(int index, String text) {
                if (index == time[0] - WheelStyle.minYear) return;
                selectYear = index + WheelStyle.minYear;
                dayWheel.setWheelItemList(WheelStyle.createDayString(selectYear, selectMonth));
            }
        });


        monthWheel.setOnSelectListener(new WheelView.onSelectListener() {
            @Override
            public void onSelect(int index, String text) {
                if (index == (time[1] - 1)) return;
                selectMonth = index + 1;
                dayWheel.setWheelItemList(WheelStyle.createDayString(selectYear, selectMonth));
            }
        });

        dayWheel.setOnSelectListener(new WheelView.onSelectListener() {
            @Override
            public void onSelect(int index, String text) {
                if (index == (time[2] - 1)) return;
                selectDay = index + 1;
            }
        });
        leftWheel.setOnSelectListener(new WheelView.onSelectListener() {
            @Override
            public void onSelect(int index, String text) {
                if (index == time[3]) return;
                selectHour = index;
            }
        });
        rightWheel.setOnSelectListener(new WheelView.onSelectListener() {
            @Override
            public void onSelect(int index, String text) {
                if (index == time[4]) return;
                selectMinute = index;
            }
        });
        return rootView;
    }

    private void send() {
        Log.e("yh", "choice:" + selectYear + selectMonth + selectDay + selectHour + selectMinute);
        time[0] = selectYear;
        time[1] = selectMonth;
        time[2] = selectDay;
        time[3] = selectHour;
        time[4] = selectMinute;
        mListener.onSelect(getTag(), time);
        dismiss();
    }

    public void setOnTimeSelectedListener(OnTimeSelectedListener listener) {
        mListener = listener;
    }

    public interface OnTimeSelectedListener {
        void onSelect(String tag, int[] ints);
    }
}
