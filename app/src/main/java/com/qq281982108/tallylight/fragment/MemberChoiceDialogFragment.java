package com.qq281982108.tallylight.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qq281982108.tallylight.R;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-28 19:47
 * 类名：TimeChoiceDialogFragment
 * 修改备注：
 */
public class MemberChoiceDialogFragment extends DialogFragment implements View.OnClickListener {

    private TextView myself, spouse, children, parents, friends, family;
    private EditText et_other;
    private Button btn_other;

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
        //getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View rootView = inflater.inflate(R.layout.dialog_fragment_member_choice, null);

        myself = (TextView) rootView.findViewById(R.id.myself);
        spouse = (TextView) rootView.findViewById(R.id.spouse);
        children = (TextView) rootView.findViewById(R.id.children);
        parents = (TextView) rootView.findViewById(R.id.parents);
        friends = (TextView) rootView.findViewById(R.id.friends);
        family = (TextView) rootView.findViewById(R.id.family);

        et_other = (EditText) rootView.findViewById(R.id.et_other);
        btn_other = (Button) rootView.findViewById(R.id.btn_other);

        myself.setOnClickListener(this);
        spouse.setOnClickListener(this);
        children.setOnClickListener(this);
        parents.setOnClickListener(this);
        friends.setOnClickListener(this);
        family.setOnClickListener(this);
        btn_other.setOnClickListener(this);
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
            case R.id.myself:
                Log.e("yh", "" + myself.getText());
                send((String) myself.getText());
                break;
            case R.id.spouse:
                send((String) spouse.getText());
                break;
            case R.id.children:
                send((String) children.getText());
                break;
            case R.id.parents:
                send((String) parents.getText());
                break;
            case R.id.friends:
                send((String) friends.getText());
                break;
            case R.id.family:
                send((String) family.getText());
                break;
            case R.id.btn_other:
                send(et_other.getText().toString());
                break;
            default:
                break;
        }
    }

    private void send(String s) {
        Intent intent = new Intent().setAction("android.basic.member");
        intent.putExtra("member", s);
        getActivity().sendBroadcast(intent);
        dismiss();
    }
}
