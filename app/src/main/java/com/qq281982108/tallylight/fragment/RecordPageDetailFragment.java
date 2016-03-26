package com.qq281982108.tallylight.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.qq281982108.tallylight.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-25 15:35
 * 类名：RecordPageDetailFragment
 * 修改备注：
 */
public class RecordPageDetailFragment extends BaseFragment {
    private List<String> GroupData;//定义组数据
    private List<List<String>> ChildrenData;//定义组中的子数据

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record_page_detail, container, false);
        LoadListDate();
        initView(view);
        return view;
    }

    private void LoadListDate() {
        GroupData = new ArrayList<String>();
        GroupData.add("国家");
        GroupData.add("人物");
        GroupData.add("武器");

        ChildrenData = new ArrayList<List<String>>();
        List<String> Child1 = new ArrayList<String>();
        Child1.add("蜀国");
        Child1.add("魏国");
        Child1.add("吴国");
        ChildrenData.add(Child1);
        List<String> Child2 = new ArrayList<String>();
        Child2.add("关羽");
        Child2.add("张飞");
        Child2.add("典韦");
        Child2.add("吕布");
        Child2.add("曹操");
        Child2.add("甘宁");
        Child2.add("郭嘉");
        Child2.add("周瑜");
        ChildrenData.add(Child2);
        List<String> Child3 = new ArrayList<String>();
        Child3.add("青龙偃月刀");
        Child3.add("丈八蛇矛枪");
        Child3.add("青钢剑");
        Child3.add("麒麟弓");
        Child3.add("银月枪");
        ChildrenData.add(Child3);
    }

    private void initView(View view) {
        ExpandableListView myExpandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
        myExpandableListView.setAdapter(new ExpandableAdapter());
    }

    private class ExpandableAdapter extends BaseExpandableListAdapter {
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return ChildrenData.get(groupPosition).get(childPosition);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            TextView myText = null;
            if (convertView != null) {
                myText = (TextView) convertView;
                myText.setText(ChildrenData.get(groupPosition).get(childPosition));
            } else {
                myText = createView(ChildrenData.get(groupPosition).get(childPosition));
            }
            return myText;
        }

        private TextView createView(String content) {
            AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT, 80);
            TextView myText = new TextView(getActivity());
            myText.setLayoutParams(layoutParams);
            myText.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            myText.setPadding(80, 0, 0, 0);
            myText.setText(content);
            return myText;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return ChildrenData.get(groupPosition).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return GroupData.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return GroupData.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return 0;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            TextView myText = null;
            if (convertView != null) {
                myText = (TextView) convertView;
                myText.setText(GroupData.get(groupPosition));
            } else {
                myText = createView(GroupData.get(groupPosition));
            }
            return myText;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }
}