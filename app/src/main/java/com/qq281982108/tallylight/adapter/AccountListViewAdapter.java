package com.qq281982108.tallylight.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qq281982108.tallylight.R;
import com.qq281982108.tallylight.model.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-04-13 15:29
 * 类名：AccountListViewAdapter
 * 修改备注：
 */
public class AccountListViewAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<Account> accountList = new ArrayList<>();

    public AccountListViewAdapter(Context context, List<Account> accountList) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.accountList = accountList;
    }

    @Override
    public int getCount() {
        return accountList.size();

    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup arg2) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = mLayoutInflater.inflate(R.layout.account_item, null);
            holder.accountName = (TextView) view.findViewById(R.id.account_name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.accountName.setText(accountList.get(position).getAccountName());
        return view;
    }

    public class ViewHolder {
        public TextView accountName;
    }
}