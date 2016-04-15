package com.qq281982108.tallylight.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qq281982108.tallylight.R;
import com.qq281982108.tallylight.model.Spending;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-04-11 9:56
 * 类名：MyExpandableListAdapter
 * 修改备注：
 */
public class MyExpandableListAdapter extends BaseExpandableListAdapter {
    protected final int groupLayoutId = R.layout.group;
    protected final int childLayoutId = R.layout.child;
    protected LayoutInflater inflater;
    protected Context context;
    protected List<String> groupList;
    protected List<List<Spending>> childList;

    private List<String> dayList;

    public MyExpandableListAdapter(Context context, List<String> groupList, List<List<Spending>> childList) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.groupList = groupList;
        this.childList = childList;
    }

    // 返回父列表个数
    @Override
    public int getGroupCount() {
//        Log.e("yh", "groupList.size()" + groupList.size());
        return groupList.size();
    }

    // 返回子列表个数
    @Override
    public int getChildrenCount(int groupPosition) {
//        Log.e("yh", "childList.get(groupPosition).size()" + childList.get(groupPosition).size());
        return 1;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {

        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        GroupHolder groupHolder = null;
        if (convertView == null) {
            groupHolder = new GroupHolder();
            convertView = inflater.inflate(groupLayoutId, null);
            groupHolder.monthSummary = (RelativeLayout) convertView.findViewById(R.id.month_summary);
            groupHolder.textView = (TextView) convertView
                    .findViewById(R.id.group);
            groupHolder.imageView = (ImageView) convertView
                    .findViewById(R.id.group_image);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        groupHolder.textView.setText(((String) getGroup(groupPosition)));
        if (isExpanded) {
            groupHolder.monthSummary.setVisibility(View.VISIBLE);
            groupHolder.imageView.setImageResource(R.drawable.expanded);
        } else {
            groupHolder.monthSummary.setVisibility(View.GONE);
            groupHolder.imageView.setImageResource(R.drawable.collapse);
        }
        groupHolder.monthSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "HAHA", Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    @Override
    public Object getGroup(int groupPosition) {

        return groupList.get(groupPosition);
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder = null;
        if (convertView == null) {
            childHolder = new ChildHolder();
            convertView = inflater.inflate(childLayoutId, null);

            childHolder.childListView = (ListView) convertView.findViewById(R.id.child_list_view);
            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }


        dayList = new ArrayList<String>();
        List<String> yearMonthList0 = new ArrayList<String>();
//        for (Spending aYearMonthList : get) {
//            yearMonthList0.add(aYearMonthList.getRecorderYearMonth());
//        }
        HashSet<String> hashSet = new HashSet<String>(yearMonthList0);
        dayList.addAll(hashSet);
//        childHolder.childListView.setAdapter(new ListViewAdapter(context, childList.get(groupPosition), childPosition));
        String[] strs = new String[]{
                "first", "second", "third", "fourth", "fifth"
        };
        childHolder.childListView.setAdapter(new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1, strs));
//        if (childPosition>0){
//            String dayPos = ((Spending) getChild(groupPosition,
//                    childPosition)).getRecorderTime().substring(8, 10);
//            String dayPos_ = ((Spending) getChild(groupPosition,
//                    childPosition-1)).getRecorderTime().substring(8, 10);
//            Log.e("yh", "day:" + dayPos + "day-1:" + dayPos_);
//            Log.e("yh", "groupPosition:" + groupPosition + "childPosition:" + childPosition);
//            if (dayPos.equals(dayPos_)){
//                childHolder.day.setVisibility(View.INVISIBLE);
//                childHolder.daySummary.setVisibility(View.GONE);
//            }
//        }else {
//            childHolder.day.setVisibility(View.VISIBLE);
//            childHolder.daySummary.setVisibility(View.VISIBLE);
//        }
//
//        childHolder.day.setText(((Spending) getChild(groupPosition,
//                childPosition)).getRecorderTime().substring(8, 10));
//        childHolder.category.setText(((Spending) getChild(groupPosition,
//                childPosition)).getSpendingCategory());
//        childHolder.dayTime.setText(((Spending) getChild(groupPosition,
//                childPosition)).getRecorderTime().substring(11));
//        childHolder.money.setText(((Spending) getChild(groupPosition,
//                childPosition)).getMoney());
        return convertView;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupHolder {
        RelativeLayout monthSummary;
        TextView textView;
        ImageView imageView;
    }

    class ChildHolder {
        ListView childListView;
    }
}