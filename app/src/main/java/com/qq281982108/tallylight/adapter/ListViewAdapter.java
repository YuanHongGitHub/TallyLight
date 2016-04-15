package com.qq281982108.tallylight.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qq281982108.tallylight.R;
import com.qq281982108.tallylight.model.Spending;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-04-13 15:29
 * 类名：ListViewAdapter
 * 修改备注：
 */
public class ListViewAdapter extends BaseAdapter {
    //    private List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
    private int childPosition;
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<Spending> childListViewData = new ArrayList<>();
    private List<String> mList;

    public ListViewAdapter(Context context, List<Spending> childListViewData, int childPosition) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.childListViewData = childListViewData;
        this.childPosition = childPosition;
        Log.e("yh", "childPosition:"+childPosition);
    }

    @Override
    public int getCount() {
        Log.e("yh", " childListViewData.size():"+childListViewData.size());
        return childListViewData.size();

    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        Log.e("yh", "position:"+position);
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup arg2) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = mLayoutInflater.inflate(R.layout.child_listview, null);
            holder.day = (TextView) view.findViewById(R.id.day);
            holder.category = (TextView) view.findViewById(R.id.category);
            holder.dayTime = (TextView) view.findViewById(R.id.day_time);
            holder.money = (TextView) view.findViewById(R.id.money);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        String day = null;
        mList = new ArrayList<>();
        for (int i = 0; i < childListViewData.size(); i++) {
            day = (childListViewData.get(i)).getRecorderTime().substring(8, 10);
            mList.add(day);
        }
        holder.day.setText((childListViewData.get(position)).getRecorderTime().substring(8, 10));
        holder.category.setText((childListViewData.get(position)).getSpendingCategory());
        holder.dayTime.setText((childListViewData.get(position)).getRecorderTime().substring(11));
        holder.money.setText((childListViewData.get(position)).getMoney());
        return view;
    }

    public class ViewHolder {
        public TextView day, category, dayTime, money;
    }
}