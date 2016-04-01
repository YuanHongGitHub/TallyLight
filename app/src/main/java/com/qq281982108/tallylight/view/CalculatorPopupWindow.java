package com.qq281982108.tallylight.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.qq281982108.tallylight.R;
import com.qq281982108.tallylight.util.CalculatorUtil;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-30 17:28
 * 类名：CalculatorPopupWindow
 * 修改备注：
 */
public class CalculatorPopupWindow extends PopupWindow implements View.OnClickListener {
    CalculatorUtil mCalculatorUtil = new CalculatorUtil();
    private int mWidth;
    private int mHeight;
    private Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_dot, btn_back,
            btn_divide, btn_multiply, btn_subtract, btn_add,//除乘减加
            btn_clear, btn_result, btn_confirm, btn_plus_minus;//清空、结果、确认提交、正负
    public CalculatorPopupWindow(Context context) {
        calWidthAndHeight(context);
        View convertView = LayoutInflater.from(context).inflate(R.layout.popwindow_layout, null);
        setContentView(convertView);
        setWidth(mWidth);
        setHeight(mHeight);
        setOutsideTouchable(false);
        setBackgroundDrawable(new BitmapDrawable());

        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    dismiss();
                    return true;
                }
                return false;
            }
        });
        initViews(convertView);
        initEvent();
    }

    /**
     * 计算高度和宽度
     *
     * @param context
     */
    private void calWidthAndHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);

        mWidth = outMetrics.widthPixels;
        mHeight = (int) (outMetrics.heightPixels * 0.5);
        Log.e("yh", "mWidth" + mWidth + "--mHeight" + mHeight);
    }

    private void initViews(View view) {
        btn_0 = (Button) view.findViewById(R.id.calculator_0);
        btn_1 = (Button) view.findViewById(R.id.calculator_1);
        btn_2 = (Button) view.findViewById(R.id.calculator_2);
        btn_3 = (Button) view.findViewById(R.id.calculator_3);
        btn_4 = (Button) view.findViewById(R.id.calculator_4);
        btn_5 = (Button) view.findViewById(R.id.calculator_5);
        btn_6 = (Button) view.findViewById(R.id.calculator_6);
        btn_7 = (Button) view.findViewById(R.id.calculator_7);
        btn_8 = (Button) view.findViewById(R.id.calculator_8);
        btn_9 = (Button) view.findViewById(R.id.calculator_9);
        btn_divide = (Button) view.findViewById(R.id.calculator_divide);
        btn_multiply = (Button) view.findViewById(R.id.calculator_multiply);
        btn_subtract = (Button) view.findViewById(R.id.calculator_subtract);
        btn_add = (Button) view.findViewById(R.id.calculator_add);
        btn_back = (Button) view.findViewById(R.id.calculator_back);
        btn_clear = (Button) view.findViewById(R.id.calculator_clear);
        btn_result = (Button) view.findViewById(R.id.calculator_result);
        btn_dot = (Button) view.findViewById(R.id.calculator_dot);
        btn_confirm = (Button) view.findViewById(R.id.calculator_confirm);
        btn_plus_minus = (Button) view.findViewById(R.id.calculator_plus_minus);
    }

    private void initEvent() {
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_subtract.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_result.setOnClickListener(this);
        btn_dot.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);
        btn_plus_minus.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.calculator_0:
                if (mCalculatorUtil.numFirst.length() != 0) {
                    mCalculatorUtil.sbAppend(0);
                }
                break;
            case R.id.calculator_1:
                mCalculatorUtil.sbAppend(1);
                break;
            case R.id.calculator_2:
                mCalculatorUtil.sbAppend(2);
                break;
            case R.id.calculator_3:
                mCalculatorUtil.sbAppend(3);
                break;
            case R.id.calculator_4:
                mCalculatorUtil.sbAppend(4);
                break;
            case R.id.calculator_5:
                mCalculatorUtil.sbAppend(5);
                break;
            case R.id.calculator_6:
                mCalculatorUtil.sbAppend(6);
                break;
            case R.id.calculator_7:
                mCalculatorUtil.sbAppend(7);
                break;
            case R.id.calculator_8:
                mCalculatorUtil.sbAppend(8);
                break;
            case R.id.calculator_9:
                mCalculatorUtil.sbAppend(9);
                break;
            case R.id.calculator_dot:
                mCalculatorUtil.dot();
                break;
            case R.id.calculator_back://返回一位
                mCalculatorUtil.back();
                break;
            case R.id.calculator_clear://清除所有
                mCalculatorUtil.clear();
                break;
            case R.id.calculator_add://加
                mCalculatorUtil.add();
                break;
            case R.id.calculator_subtract://减
                mCalculatorUtil.subtract();
                break;
            case R.id.calculator_multiply://乘
                mCalculatorUtil.multiply();
                break;
            case R.id.calculator_divide://除
                mCalculatorUtil.divide();
                break;
            case R.id.calculator_result://等于
                mCalculatorUtil.result();
                break;
            case R.id.calculator_confirm://确认提交
                if (!mCalculatorUtil.result.toString().contains("-")) {
                    dismiss();
                }
                break;
            case R.id.calculator_plus_minus://正负值
                break;
            default:
                break;
        }
    }

}
