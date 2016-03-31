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

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-30 17:28
 * 类名：CalculatorPopupWindow
 * 修改备注：
 */
public class CalculatorPopupWindow extends PopupWindow implements View.OnClickListener {
    StringBuffer numZero, numResult, numFirst, numSecond;
    boolean isHadDot = false;
    boolean isDivided, isMultiplied, isSubtracted, isAdded, dotIsFirst, dotIsFirstCleared;
    int dotLength = 0;
    Double result = 0.0;
    private int mWidth;
    private int mHeight;
    private View mConvertView;
    private Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_dot, btn_back,
            btn_divide, btn_multiply, btn_subtract, btn_add,//除乘减加
            btn_clear, btn_result, btn_confirm, btn_plus_minus;//清空、结果、确认提交、正负
    private OnCalculatorListener mListener;

    public CalculatorPopupWindow(Context context) {
        calWidthAndHeight(context);

        mConvertView = LayoutInflater.from(context).inflate(R.layout.popwindow_layout, null);
        setContentView(mConvertView);
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
        initViews(mConvertView);
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
        numZero = new StringBuffer("0");
        numFirst = new StringBuffer();
        numSecond = new StringBuffer();
        numResult = new StringBuffer();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.calculator_0:
                if (numFirst.length() != 0) {
                    sbAppend(0);
                }
                break;
            case R.id.calculator_1:
                sbAppend(1);
                break;
            case R.id.calculator_2:
                sbAppend(2);
                break;
            case R.id.calculator_3:
                sbAppend(3);
                break;
            case R.id.calculator_4:
                sbAppend(4);
                break;
            case R.id.calculator_5:
                sbAppend(5);
                break;
            case R.id.calculator_6:
                sbAppend(6);
                break;
            case R.id.calculator_7:
                sbAppend(7);
                break;
            case R.id.calculator_8:
                sbAppend(8);
                break;
            case R.id.calculator_9:
                sbAppend(9);
                break;
            case R.id.calculator_dot:
                if (!isHadDot) {
                    dotLength++;
                    isHadDot = true;
                    if (numFirst.length() == 0) {
                        dotIsFirst = true;
                        sbAppend(0);
                        dotLength--;
                    }
                    mListener.onCalculated(numFirst.append("."));
                }
                break;
            case R.id.calculator_back://返回一位
                if (numFirst.length() > 1) {
                    if (dotLength > 2) {
                        dotLength--;
                        numFirst.deleteCharAt(numFirst.length() - 1);
                    } else if (dotLength == 2) {
                        isHadDot = false;
                        dotLength = 0;
                        if (dotIsFirst) {
                            numFirst.delete(numFirst.length() - 3, numFirst.length());
                            dotIsFirstCleared = true;
                        } else {
                            numFirst.delete(numFirst.length() - 2, numFirst.length());
                        }
                    } else {
                        numFirst.deleteCharAt(numFirst.length() - 1);
                    }
                    if (dotIsFirstCleared) {
                        mListener.onCalculated(numZero);
                        dotIsFirst = false;
                        dotIsFirstCleared = false;
                    } else {
                        mListener.onCalculated(numFirst);
                    }
                } else {
                    if (numFirst.length() != 0) {
                        numFirst.deleteCharAt(numFirst.length() - 1);
                    }
                    mListener.onCalculated(numZero);
                    Log.e("yh", "numZero.toString()" + numZero.toString());
                }
                Log.e("yh", "numFirst.toString()" + numFirst.toString());
                break;
            case R.id.calculator_clear://清除所有
                if (numFirst.length() != 0) {
                    mListener.onCalculated(numFirst.delete(0, numFirst.length()));
                }
                mListener.onCalculated(numZero);
                result = 0.0;
                dotLength = 0;
                isHadDot = false;
                dotIsFirst = false;
                dotIsFirstCleared = false;
                break;
            case R.id.calculator_add://加
                isAdded = true;
                isSubtracted = isDivided = isMultiplied = false;
                isHadDot = false;
                dotLength = 0;
                if (numFirst.length() != 0) {
                    if (result == 0.0) {
                        numResult = numFirst;
                        Log.e("yh", "" + numResult.length());
                        result = Double.valueOf(numResult.toString().trim());
                    } else {
                        numResult = numFirst;
                        result += Double.valueOf(numFirst.toString().trim());
                        result = (double) Math.round(result * 100) / 100;
                        mListener.onCalculated(result.toString());
                    }
                    numFirst.delete(0, numFirst.length());
                }
                break;
            case R.id.calculator_subtract://减
                isSubtracted = true;
                isAdded = isDivided = isMultiplied = false;
                isHadDot = false;
                dotLength = 0;
                if (numFirst.length() != 0) {
                    if (!numFirst.toString().equals("-")) {
                        if (result == 0.0) {
                            numResult = numFirst;
                            Log.e("yh", "" + numResult.length());
                            result = Double.valueOf(numResult.toString().trim());
                        } else {
                            numResult = numFirst;
                            result -= Double.valueOf(numFirst.toString().trim());
                            result = (double) Math.round(result * 100) / 100;
                            mListener.onCalculated(result.toString());
                        }
                        numFirst.delete(0, numFirst.length());
                    }
                } else if (result == 0.0) {
                    Log.e("yh", "--------" + numResult.length());
                    numFirst.append("-");
                }
                break;
            case R.id.calculator_multiply://乘
                isMultiplied = true;
                isAdded = isDivided = isSubtracted = false;
                isHadDot = false;
                dotLength = 0;
                if (numFirst.length() != 0) {
                    if (result == 0.0) {
                        numResult = numFirst;
                        Log.e("yh", "" + numResult.length());
                        result = Double.valueOf(numResult.toString().trim());
                    } else {
                        numResult = numFirst;
                        result = result * Double.valueOf(numFirst.toString().trim());
                        result = (double) Math.round(result * 100) / 100;
                        mListener.onCalculated(result.toString());
                    }
                    numFirst.delete(0, numFirst.length());
                }
                break;
            case R.id.calculator_divide://除
                isDivided = true;
                isAdded = isMultiplied = isSubtracted = false;
                isHadDot = false;
                dotLength = 0;
                if (numFirst.length() != 0) {
                    if (result == 0.0) {
                        numResult = numFirst;
                        Log.e("yh", "" + numResult.length());
                        result = Double.valueOf(numResult.toString().trim());
                    } else if (result != 0) {
                        numResult = numFirst;
                        result = result / Double.valueOf(numFirst.toString().trim());
                        result = (double) Math.round(result * 100) / 100;
                        mListener.onCalculated(result.toString());
                    }
                    numFirst.delete(0, numFirst.length());
                }
                break;
            case R.id.calculator_result://等于
                if (isAdded) {
                    if (numFirst.length() != 0) {
                        if (result == 0.0) {
                            numResult = numFirst;
                            Log.e("yh", "" + numResult.length());
                            result = Double.valueOf(numResult.toString().trim());
                        } else {
                            numResult = numFirst;
                            result += Double.valueOf(numFirst.toString().trim());
                            result = (double) Math.round(result * 100) / 100;
                            mListener.onCalculated(result.toString());
                        }
                        numFirst.delete(0, numFirst.length());
                    }
                }
                if (isSubtracted) {
                    if (numFirst.length() != 0) {
                        if (!numFirst.toString().equals("-")) {
                            if (result == 0.0) {
                                numResult = numFirst;
                                Log.e("yh", "" + numResult.length());
                                result = Double.valueOf(numResult.toString().trim());
                            } else {
                                numResult = numFirst;
                                result -= Double.valueOf(numFirst.toString().trim());
                                result = (double) Math.round(result * 100) / 100;
                                mListener.onCalculated(result.toString());
                            }
                            numFirst.delete(0, numFirst.length());
                        }
                    } else if (result == 0.0) {
                        Log.e("yh", "--------" + numResult.length());
                        numFirst.append("-");
                    }
                }
                if (isMultiplied) {
                    if (numFirst.length() != 0) {
                        if (result == 0.0) {
                            numResult = numFirst;
                            Log.e("yh", "" + numResult.length());
                            result = Double.valueOf(numResult.toString().trim());
                        } else {
                            numResult = numFirst;
                            result = result * Double.valueOf(numFirst.toString().trim());
                            result = (double) Math.round(result * 100) / 100;
                            mListener.onCalculated(result.toString());
                        }
                        numFirst.delete(0, numFirst.length());
                    }
                }
                if (isDivided) {
                    if (numFirst.length() != 0) {
                        if (result == 0.0) {
                            numResult = numFirst;
                            Log.e("yh", "" + numResult.length());
                            result = Double.valueOf(numResult.toString().trim());
                        } else if (result != 0) {
                            numResult = numFirst;
                            result = result / Double.valueOf(numFirst.toString().trim());
                            result = (double) Math.round(result * 100) / 100;
                            mListener.onCalculated(result.toString());
                        }
                        numFirst.delete(0, numFirst.length());
                    }
                }
                isAdded = isDivided = isMultiplied = isSubtracted = false;
                break;
            case R.id.calculator_confirm://确认提交
                if (!result.toString().contains("-")) {
                    dismiss();
                }
                break;
            case R.id.calculator_plus_minus://正负值
                break;
            default:
                break;
        }
    }

    private void sbAppend(int num) {
        if (isHadDot) {
            if (dotLength < 3) {
                dotLength++;
                mListener.onCalculated(numFirst.append(num));
            }
        } else {
            mListener.onCalculated(numFirst.append(num));
        }
    }

    public void setOnCalculatorListener(OnCalculatorListener listener) {
        mListener = listener;
    }

    public interface OnCalculatorListener {
        void onCalculated(StringBuffer stringBuffer);

        void onCalculated(String result);
    }
}
