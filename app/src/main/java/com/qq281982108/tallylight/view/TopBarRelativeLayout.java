package com.qq281982108.tallylight.view;

import android.content.Context;
import android.content.Intent;
import android.text.format.Time;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qq281982108.tallylight.R;
import com.qq281982108.tallylight.activity.MyCalendarActivity;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-26 17:04
 * 类名：CalenderTextView
 * 修改备注：
 */
public class TopBarRelativeLayout extends RelativeLayout implements View.OnClickListener {

    private TextView mTextView;
    private ImageView mImageView;
    private RelativeLayout mRelativeLayout;

    public TopBarRelativeLayout(Context context) {
        this(context, null);
    }

    public TopBarRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.top_bar_calendar, this);
        mTextView = (TextView) findViewById(R.id.tv_calendar);
        mImageView = (ImageView) findViewById(R.id.top_bar_xiaoxi);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.rl_calendar);
        mImageView.setOnClickListener(this);
        mRelativeLayout.setOnClickListener(this);
        Time t = new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
        t.setToNow(); // 取得系统时间。
        mTextView.setText("" + t.monthDay);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_xiaoxi:
                //TODO
                Log.e("yh", "top_bar_xiaoxi");
                break;
            case R.id.rl_calendar:
                //TODO
                getContext().startActivity(new Intent(getContext(), MyCalendarActivity.class));
            default:
                break;
        }
    }
}
