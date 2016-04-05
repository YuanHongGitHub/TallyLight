package com.qq281982108.tallylight.util;

import android.util.Log;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-04-01 10:05
 * 类名：CalculatorUtil
 * 修改备注：
 */
public class CalculatorUtil {
    private static CalculatorUtilListener mListener;
    public Double result = 0.0;
    public StringBuffer numFirst = new StringBuffer();
    boolean isHadDot, isDivided, isMultiplied, isSubtracted, isAdded, dotIsFirst, dotIsFirstCleared;
    int dotLength = 0;
    StringBuffer numZero = new StringBuffer("0");
    StringBuffer numResult = new StringBuffer();

    public static void setCalculatorUtilListener(CalculatorUtilListener listener) {
        mListener = listener;
    }

    public void add() {
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
    }

    public void subtract() {
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
    }

    public void multiply() {
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
    }

    public void divide() {
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
    }

    public void result() {
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
        isHadDot = isAdded = isDivided = isMultiplied = isSubtracted = false;
    }

    public void confirm() {
        if (!result.toString().contains("-")) {
            isHadDot = isAdded = isDivided = isMultiplied = isSubtracted = false;
            numFirst.delete(0, numFirst.length());
            result = 0.0;
//            dismiss();
        }
    }

    public void dot() {
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
    }

    public void sbAppend(int num) {
        if (isHadDot) {
            if (dotLength < 3) {
                dotLength++;
                mListener.onCalculated(numFirst.append(num));
            }
        } else {
            mListener.onCalculated(numFirst.append(num));
        }
    }

    public void back() {
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
    }

    public void clear() {
        if (numFirst.length() != 0) {
            mListener.onCalculated(numFirst.delete(0, numFirst.length()));
        }
        mListener.onCalculated(numZero);
        result = 0.0;
        dotLength = 0;
        isHadDot = false;
        dotIsFirst = false;
        dotIsFirstCleared = false;
    }

    public void plus_minus() {
        if (result < 0) {
            result = result * -1;
            mListener.onCalculated(result.toString());
        }

    }

    public interface CalculatorUtilListener {
        void onCalculated(StringBuffer stringBuffer);

        void onCalculated(String result);
    }
}
