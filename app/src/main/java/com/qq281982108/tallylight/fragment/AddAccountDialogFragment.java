package com.qq281982108.tallylight.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.qq281982108.tallylight.R;
import com.qq281982108.tallylight.adapter.ClassifyMainAdapter;
import com.qq281982108.tallylight.adapter.ClassifyMoreAdapter;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-28 19:47
 * 类名：TimeChoiceDialogFragment
 * 修改备注：
 */
public class AddAccountDialogFragment extends DialogFragment implements View.OnClickListener {
    ClassifyMainAdapter mainAdapter;
    ClassifyMoreAdapter moreAdapter;
    private ListView mainlist;
    private ListView morelist;
    private String[] ClassifyMain;
    private String[][] ClassifyMore;
    private int get_id;
    private int a = -1;
    private int main_postion = -1;
    private int list_id;
    private OnAccountAddedListener mListener;

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
        View rootView = inflater.inflate(R.layout.choice_add_account, null);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout(dm.widthPixels, dm.heightPixels / 2);
    }

    public void setOnAccountAddedListener(OnAccountAddedListener listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }

    public interface OnAccountAddedListener {
        void onAdded(String s);
    }

}
