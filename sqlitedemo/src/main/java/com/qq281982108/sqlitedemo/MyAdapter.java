package com.qq281982108.sqlitedemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-04-07 11:25
 * 类名：MyAdapter
 * 修改备注：
 */
public abstract class MyAdapter<T> extends CommonAdapter<T> {
    public MyAdapter(Context context, List<T> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = ViewHolder.get(mContext, convertView, parent,
                R.layout.main_item, position);
        TextView mTitle = viewHolder.getView(R.id.id_tv_title);
        mTitle.setText((String) mDatas.get(position));
        return viewHolder.getConvertView();
    }

}
