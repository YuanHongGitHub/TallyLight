package com.qq281982108.tallylight.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qq281982108.tallylight.R;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-28 19:47
 * 类名：TimeChoiceDialogFragment
 * 修改备注：
 */
public class ItemLongClickDialogFragment extends DialogFragment implements View.OnClickListener {

    private TextView change, delete;

    private int expendId;
    private OnDetailItemDeleteClickListener mListener;

    public static ItemLongClickDialogFragment newInstance(int position) {
        ItemLongClickDialogFragment itemLongClickDialogFragment = new ItemLongClickDialogFragment();
        Bundle args = new Bundle();
        args.putInt("expendId", position);
        itemLongClickDialogFragment.setArguments(args);
        return itemLongClickDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //如果setCancelable()中参数为true，若点击dialog覆盖不到的activity的空白或者按返回键，
        //则进行cancel，状态检测依次onCancel()和onDismiss()。如参数为false，则按空白处或返回键无反应。缺省为true
        setCancelable(true);
        //可以设置dialog的显示风格
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        expendId = getArguments().getInt("expendId");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View rootView = inflater.inflate(R.layout.dialog_fragment_item_long_click, null);

        change = (TextView) rootView.findViewById(R.id.change);
        delete = (TextView) rootView.findViewById(R.id.delete);

        change.setOnClickListener(this);
        delete.setOnClickListener(this);
        return rootView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change:
                Log.e("yh", "expendId" + expendId);
                break;
            case R.id.delete:
                send();
                Log.e("yh", "expendId" + expendId);
                break;
            default:
                break;
        }
    }

    private void send() {
        mListener.delete();
        dismiss();
    }

    public void setOnDetailItemDeleteClickListener(OnDetailItemDeleteClickListener listener) {
        mListener = listener;
    }

    public interface OnDetailItemDeleteClickListener {
        void delete();
    }
}
