package com.qq281982108.tallylight.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;

import com.qq281982108.tallylight.R;
import com.qq281982108.tallylight.basepopup.BasePopupWindow;
import com.qq281982108.tallylight.util.ToastUtils;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-28 19:09
 * 类名：ScalePopup
 * 修改备注：
 */
public class ScalePopup extends BasePopupWindow implements View.OnClickListener {

    private View popupView;

    public ScalePopup(Activity context) {
        super(context);
        bindEvent();
    }

    private void bindEvent() {
        if (popupView != null) {
            popupView.findViewById(R.id.tx_1).setOnClickListener(this);
            popupView.findViewById(R.id.tx_2).setOnClickListener(this);
            popupView.findViewById(R.id.tx_3).setOnClickListener(this);
        }

    }

    @Override
    protected Animation getShowAnimation() {
        return getDefaultScaleAnimation();
    }

    @Override
    protected View getClickToDismissView() {
        return popupView.findViewById(R.id.click_to_dismiss);
    }

    @Override
    public View getPopupView() {
        popupView = LayoutInflater.from(mContext).inflate(R.layout.activity_time_choice, null);
        return popupView;
    }

    @Override
    public View getAnimaView() {
        return popupView.findViewById(R.id.popup_anima);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tx_1:
                ToastUtils.ToastMessage(mContext, "click tx_1");
                break;
            case R.id.tx_2:
                ToastUtils.ToastMessage(mContext, "click tx_2");
                break;
            case R.id.tx_3:
                ToastUtils.ToastMessage(mContext, "click tx_3");
                break;
            default:
                break;
        }

    }
}
