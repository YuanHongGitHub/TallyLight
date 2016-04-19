package com.qq281982108.tallylight.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.qq281982108.tallylight.CategoryAdapter;
import com.qq281982108.tallylight.R;
import com.qq281982108.tallylight.adapter.CommonAdapter;
import com.qq281982108.tallylight.db.DbOperations;
import com.qq281982108.tallylight.model.Account;
import com.qq281982108.tallylight.util.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-28 19:47
 * 类名：TimeChoiceDialogFragment
 * 修改备注：
 */
public class AccountChoiceDialogFragment extends DialogFragment {

    private static final String[] ACCOUNT_CATEGORY = {"现金", "信用卡", "储蓄卡", "投资账户", "储值卡", "网络账户", "虚拟账户", "债权/债务人"};
    private TextView myself, spouse, children, parents, friends, family;
    private EditText et_other;
    private Button btn_other;
    private OnAccountSelectedListener mListener;
    private ListView mListView;
    private List<Account> crashAccountList = new ArrayList<>();
    private List<Account> creditCardAccountList = new ArrayList<>();
    private List<Account> depositCardAccountList = new ArrayList<>();
    private List<Account> investmentAccountList = new ArrayList<>();
    private List<Account> valueCardAccountList = new ArrayList<>();
    private List<Account> onlineAccountList = new ArrayList<>();
    private List<Account> virtualAccountList = new ArrayList<>();
    private List<Account> rightsOrDebtorAccountList = new ArrayList<>();
    private List[] accountList = {crashAccountList, creditCardAccountList, depositCardAccountList, investmentAccountList,
            valueCardAccountList, onlineAccountList, virtualAccountList, rightsOrDebtorAccountList};
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
                mCategoryAdapter.addCategory(ACCOUNT_CATEGORY[i], new CommonAdapter(getActivity(), crashAccountList, R.layout.account_item) {
                    @Override
                    public void convert(ViewHolder helper, Object item) {
                        for (int j = 0; j < crashAccountList.size(); j++) {
                            helper.setText(R.id.account_name, "haha" + j);
                        }
                    }
                });
            }
        }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListView.setSelection(position);
                send(crashAccountList.get(0).getAccountName());
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
