package com.qq281982108.tallylight.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.qq281982108.tallylight.R;
import com.qq281982108.tallylight.db.DbOperations;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-28 19:47
 * 类名：TimeChoiceDialogFragment
 * 修改备注：
 */
public class AddCrashAccountDialogFragment extends DialogFragment {
    private TextView crashCategoryTxt;
    private EditText accountNameEt, accountMoneyEt, accountRemarksEt;
    private String accountCategoryName, accountName, accountMoney = "0.00", accountRemarks;
    private DbOperations mDbOperations = new DbOperations();
    private List<String> mAccountList = new ArrayList<>();
    private OnAccountAddFinishedListener mListener;

    public static AddCrashAccountDialogFragment newInstance(String category) {
        AddCrashAccountDialogFragment addCrashAccountDialogFragment = new AddCrashAccountDialogFragment();
        Bundle args = new Bundle();
        args.putString("accountCategory", category);
        addCrashAccountDialogFragment.setArguments(args);
        return addCrashAccountDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //如果setCancelable()中参数为true，若点击dialog覆盖不到的activity的空白或者按返回键，
        //则进行cancel，状态检测依次onCancel()和onDismiss()。如参数为false，则按空白处或返回键无反应。缺省为true
        setCancelable(true);
        //可以设置dialog的显示风格
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        accountCategoryName = getArguments().getString("accountCategory");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.choice_add_account_detail, null);
        crashCategoryTxt = (TextView) rootView.findViewById(R.id.crash_category_txt);
        accountNameEt = (EditText) rootView.findViewById(R.id.account_name_et);
        accountMoneyEt = (EditText) rootView.findViewById(R.id.account_money_et);
        accountRemarksEt = (EditText) rootView.findViewById(R.id.account_remarks_et);

        if (accountCategoryName.contains("账户")) {
            crashCategoryTxt.setText("新增" + accountCategoryName);
        } else crashCategoryTxt.setText("新增" + accountCategoryName + "账户");

        mAccountList = mDbOperations.loadAllAccountList();

        rootView.findViewById(R.id.save_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                accountName = accountNameEt.getText().toString();
                accountMoney = accountMoneyEt.getText().toString();
                accountRemarks = accountRemarksEt.getText().toString();
                if (mAccountList.contains(accountName)) {
                    Toast.makeText(getActivity(),
                            "该账户名已存在", Toast.LENGTH_SHORT)
                            .show();
                } else if (accountName == null) {
                    Toast.makeText(getActivity(),
                            "请输入账户名称和金额，金额默认为零", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    mListener.onAddFinished(accountName, accountMoney, accountRemarks);
                    Log.e("yh", "accountName:" + accountName + "--accountMoney:" + accountMoney + "--accountRemarks:" + accountRemarks);
                    dismiss();
                }

            }
        });

        accountRemarksEt.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int editStart;
            private int editEnd;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp = s;
            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = accountRemarksEt.getSelectionStart();
                editEnd = accountRemarksEt.getSelectionEnd();
                if (temp.length() > 100) {
                    Toast.makeText(getActivity(),
                            "你输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection = editStart;
                    accountRemarksEt.setText(s);
                    accountRemarksEt.setSelection(tempSelection);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout(dm.widthPixels, dm.heightPixels * 2 / 3);
    }

    public void setOnAccountAddFinishedListener(OnAccountAddFinishedListener listener) {
        mListener = listener;
    }

    public interface OnAccountAddFinishedListener {
        void onAddFinished(String accountName, String accountMoney, String accountRemarks);
    }
}
