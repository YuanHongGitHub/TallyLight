package com.qq281982108.pinnedheaderexpandablelistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-04-15 13:40
 * 类名：MyAdapter
 * 修改备注：
 */
class MyAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater mInflater;
    private String[] mStrings;
    private String[][] mStrings2;

    public MyAdapter(Context context, String[] strings, String[][] strings2) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        mStrings = strings;
        mStrings2 = strings2;
    }

    @Override
    public int getCount() {
        // How many items are in the data set represented by this Adapter.(在此适配器中所代表的数据集中的条目数)
        return mStrings.length;
    }

    @Override
    public Object getItem(int position) {
        // Get the data item associated with the specified position in the data set.(获取数据集中与指定索引对应的数据项)
        return null;
    }

    @Override
    public long getItemId(int position) {
        // Get the row id associated with the specified position in the list.(取在列表中与指定索引对应的行id)
        return 0;
    }

    //然后重写getView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item0, null);
            holder.mTextView = (TextView) convertView.findViewById(R.id.text);
            holder.mListView = (ListView) convertView.findViewById(R.id.list_view1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTextView.setText(mStrings[position]);
        holder.mListView.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, mStrings2[position]));
        holder.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return convertView;
    }

    //在外面先定义，ViewHolder静态类
    static class ViewHolder {
        TextView mTextView;
        ListView mListView;
    }
}