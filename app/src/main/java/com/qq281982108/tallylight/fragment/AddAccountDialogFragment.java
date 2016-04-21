package com.qq281982108.tallylight.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qq281982108.tallylight.R;
import com.qq281982108.tallylight.model.Account;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-28 19:47
 * 类名：TimeChoiceDialogFragment
 * 修改备注：
 */
public class AddAccountDialogFragment extends DialogFragment implements View.OnClickListener {

    AddCrashAccountDialogFragment addCrashAccountDialogFragment;
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

        rootView.findViewById(R.id.add_crash_account).setOnClickListener(this);
        rootView.findViewById(R.id.add_credit_card_account).setOnClickListener(this);
        rootView.findViewById(R.id.add_deposit_card_account).setOnClickListener(this);
        rootView.findViewById(R.id.add_investment_account).setOnClickListener(this);
        rootView.findViewById(R.id.add_value_card_account).setOnClickListener(this);
        rootView.findViewById(R.id.add_online_account).setOnClickListener(this);
        rootView.findViewById(R.id.add_virtual_account).setOnClickListener(this);
        rootView.findViewById(R.id.add_rights_or_debtor_account).setOnClickListener(this);

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
            case R.id.add_crash_account:
                //  TODO
                initDialog(AccountChoiceDialogFragment.ACCOUNT_CATEGORY[0]);
                break;
            case R.id.add_credit_card_account:
                //  TODO
                initDialog(AccountChoiceDialogFragment.ACCOUNT_CATEGORY[1]);
                break;
            case R.id.add_deposit_card_account:
                //  TODO
                initDialog(AccountChoiceDialogFragment.ACCOUNT_CATEGORY[2]);
                break;
            case R.id.add_investment_account:
                //  TODO
                initDialog(AccountChoiceDialogFragment.ACCOUNT_CATEGORY[3]);
                break;
            case R.id.add_value_card_account:
                //  TODO
                initDialog(AccountChoiceDialogFragment.ACCOUNT_CATEGORY[4]);
                break;
            case R.id.add_online_account:
                //  TODO
                initDialog(AccountChoiceDialogFragment.ACCOUNT_CATEGORY[5]);
                break;
            case R.id.add_virtual_account:
                //  TODO
                initDialog(AccountChoiceDialogFragment.ACCOUNT_CATEGORY[6]);
                break;
            case R.id.add_rights_or_debtor_account:
                //  TODO
                initDialog(AccountChoiceDialogFragment.ACCOUNT_CATEGORY[7]);
                break;
            default:
                break;
        }
    }

    public void initDialog(final String s) {
        addCrashAccountDialogFragment = AddCrashAccountDialogFragment.newInstance(s);
        addCrashAccountDialogFragment.setOnAccountAddFinishedListener(new AddCrashAccountDialogFragment.OnAccountAddFinishedListener() {
            @Override
            public void onAddFinished(String accountName, String accountMoney, String accountRemarks) {
                saveAndSend(s, accountName, accountMoney, accountRemarks);
            }
        });
        addCrashAccountDialogFragment.show(getActivity().getFragmentManager(), "addAccountDetail");
    }

    public void saveAndSend(String accountCategory, String accountName, String accountMoney, String accountRemarks) {
        Account account = new Account();
        account.setAccountCategory(accountCategory);
        account.setAccountName(accountName);
        account.setMoney(accountMoney);
        account.setRemarks(accountRemarks);
        Log.e("yh", "accountName:" + accountName + "--accountMoney:" + accountMoney + "--accountRemarks:" + accountRemarks);
        account.save();
        mListener.onAdded(accountName);
        dismiss();
    }

    public interface OnAccountAddedListener {
        void onAdded(String s);
    }

}
