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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.qq281982108.tallylight.R;
import com.qq281982108.tallylight.adapter.AccountListViewAdapter;
import com.qq281982108.tallylight.adapter.CategoryAdapter;
import com.qq281982108.tallylight.db.DbOperations;
import com.qq281982108.tallylight.model.Account;

import java.util.List;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-28 19:47
 * 类名：TimeChoiceDialogFragment
 * 修改备注：
 */
public class AccountChoiceDialogFragment extends DialogFragment {

    public static final String[] ACCOUNT_CATEGORY = {"现金", "信用卡", "储蓄卡", "投资账户", "储值卡", "网络账户", "虚拟账户", "债权/债务人"};
    private OnAccountSelectedListener mListener;
    private ListView mListView;
    private List[] accountList = new List[8];
    private DbOperations mDbOperations = new DbOperations();
    private LayoutInflater mInflater;
    private CategoryAdapter mCategoryAdapter = new CategoryAdapter() {
        @Override
        protected View getTitleView(String title, int index, View convertView, ViewGroup parent) {
            TextView titleView;

            if (convertView == null) {
                titleView = (TextView) mInflater.inflate(R.layout.title, null);
            } else {
                titleView = (TextView) convertView;
            }
            titleView.setText(title);
            return titleView;
        }
    };
    private int[] accountISize = new int[8];

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
        this.mInflater = inflater;
        //getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View rootView = inflater.inflate(R.layout.dialog_fragment_account_choice, null);
        mListView = (ListView) rootView.findViewById(R.id.account_choice_lv);
        rootView.findViewById(R.id.add_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddAccountDialogFragment addAccountDialogFragment = new AddAccountDialogFragment();
                addAccountDialogFragment.setOnAccountAddedListener(new AddAccountDialogFragment.OnAccountAddedListener() {
                    @Override
                    public void onAdded(String s) {
                        send(s);
                    }
                });
                addAccountDialogFragment.show(getActivity().getFragmentManager(), "addAccount");
            }
        });

        for (int i = 0; i < ACCOUNT_CATEGORY.length; i++) {
            accountList[i] = mDbOperations.initAccountList(ACCOUNT_CATEGORY[i]);
            if (accountList[i].size() != 0) {
                mCategoryAdapter.addCategory(ACCOUNT_CATEGORY[i],
                        new AccountListViewAdapter(getActivity(), accountList[i]));
                accountISize[i] = accountList[i].size() + 1;
                Log.e("yy", "accountISize" + accountISize[i] + "i" + i);
            }
        }
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListView.setSelection(position);
                //FIXME 暂时先这么处理
                int size0 = accountISize[0];
                int size1 = accountISize[0] + accountISize[1];
                int size2 = accountISize[0] + accountISize[1] + accountISize[2];
                int size3 = accountISize[0] + accountISize[1] + accountISize[2] + accountISize[3];
                int size4 = accountISize[0] + accountISize[1] + accountISize[2] + accountISize[3] + accountISize[4];
                int size5 = accountISize[0] + accountISize[1] + accountISize[2] + accountISize[3] + accountISize[4] + accountISize[5];
                int size6 = accountISize[0] + accountISize[1] + accountISize[2] + accountISize[3] + accountISize[4] + accountISize[5] + accountISize[6];
                int size7 = accountISize[0] + accountISize[1] + accountISize[2] + accountISize[3] + accountISize[4] + accountISize[5] + accountISize[6] + accountISize[7];
                Log.e("yy", "position" + position);
                Log.e("yy", "accountISize[0]" + accountISize[0]);
                Log.e("yy", "accountISize[0+1]" + (accountISize[0] + accountISize[1]));
                if (position > 0 && position < size0) {//现金
                    Account account =
                            (Account) accountList[0].get(position - 1);
                    send(account.getAccountName());
                } else if (position > size0 && position < size1) {//信用卡
                    Account account =
                            (Account) accountList[1].get(position - size0 - 1);
                    send(account.getAccountName());
                } else if (position > size1 && position < size2) {//储蓄卡
                    Account account =
                            (Account) accountList[2].get(position - size1 - 1);
                    send(account.getAccountName());
                } else if (position > size2 && position < size3) {//投资账户
                    Account account =
                            (Account) accountList[3].get(position - size2 - 1);
                    send(account.getAccountName());
                } else if (position > size3 && position < size4) {//储值卡
                    Account account =
                            (Account) accountList[4].get(position - size3 - 1);
                    send(account.getAccountName());
                } else if (position > size4 && position < size5) {//网络账户
                    Account account =
                            (Account) accountList[5].get(position - size4 - 1);
                    send(account.getAccountName());
                } else if (position > size5 && position < size6) {//虚拟账户
                    Account account =
                            (Account) accountList[6].get(position - size5 - 1);
                    send(account.getAccountName());
                } else if (position > size6 && position < size7) {//债权/债务人
                    Account account =
                            (Account) accountList[7].get(position - size6 - 1);
                    send(account.getAccountName());
                }
            }
        });
        mListView.setAdapter(mCategoryAdapter);

        return rootView;
    }
    private void send(String s) {
        if (s == null) return;
        mListener.onAccountSelect(s);
        dismiss();
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
        getDialog().getWindow().setLayout(dm.widthPixels, dm.heightPixels / 2);
    }

    public void setOnAccountSelectedListener(OnAccountSelectedListener listener) {
        mListener = listener;
    }

    public interface OnAccountSelectedListener {
        void onAccountSelect(String s);
    }
}
