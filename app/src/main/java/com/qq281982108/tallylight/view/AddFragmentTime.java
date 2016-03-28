package com.qq281982108.tallylight.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qq281982108.tallylight.R;
import com.qq281982108.tallylight.util.TimeUtils;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-28 10:43
 * 类名：AddFragmentTime
 * 修改备注：
 */
public class AddFragmentTime extends LinearLayout {
    private TextView mTime;

    public AddFragmentTime(Context context) {
        this(context, null);
    }

    public AddFragmentTime(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.add_fragment_time, this);
        mTime = (TextView) findViewById(R.id.tv_add_fragment_time_time);
        mTime.setText(TimeUtils.getTime(TimeUtils.getCurrentTimeInLong()));
    }
}
