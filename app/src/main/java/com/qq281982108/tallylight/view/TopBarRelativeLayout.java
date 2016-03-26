package com.qq281982108.tallylight.view;

import android.content.Context;
import android.text.format.Time;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qq281982108.tallylight.R;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-26 17:04
 * 类名：CalenderTextView
 * 修改备注：
 */
public class TopBarRelativeLayout extends RelativeLayout implements View.OnClickListener {

    private View mView;
    private TextView mTextView;
    private Context mContext;

    public TopBarRelativeLayout(Context context) {
        this(context, null);
    }

    public TopBarRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mView = LayoutInflater.from(mContext).inflate(R.layout.top_bar_calendar, null);
        mTextView = (TextView) mView.findViewById(R.id.tv_calendar);
        Time t = new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
        t.setToNow(); // 取得系统时间。
        mTextView.setText("" + t.monthDay);
    }

    @Override
    public void onClick(View v) {

    }
}
