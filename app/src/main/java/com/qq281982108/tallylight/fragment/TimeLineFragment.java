package com.qq281982108.tallylight.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.qq281982108.tallylight.R;
import com.qq281982108.tallylight.adapter.MyAdapter;
import com.qq281982108.tallylight.model.ChildEntity;
import com.qq281982108.tallylight.model.GroupEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-23 19:24
 * 类名：TimeLineFragment
 * 修改备注：
 */
public class TimeLineFragment extends Fragment {
    private ExpandableListView expandableListView;
    private List<GroupEntity> lists;
    private MyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        lists = initList();
        adapter = new MyAdapter(getActivity(), lists);
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
        expandableListView.setAdapter(adapter);
        expandableListView.setGroupIndicator(null); // 去掉默认带的箭头
        expandableListView.setSelection(0);// 设置默认选中项
        // 遍历所有group,将所有项设置成默认展开
        int groupCount = expandableListView.getCount();
        for (int i = 0; i < groupCount; i++) {
            expandableListView.expandGroup(i);
        }
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
    }

    private List<GroupEntity> initList() {

        List<GroupEntity> groupList;
        //测试数据
        String[] groupArray = new String[]{"9月1日", "9月2日", "9月3日"};
        String[][] childTimeArray = new String[][]{
                {"测试数据1", "测试数据2", "测试数据3"},
                {"测试数据4"}, {"测试数据5", "测试数据6"}};
        groupList = new ArrayList<GroupEntity>();
        for (int i = 0; i < groupArray.length; i++) {
            GroupEntity groupEntity = new GroupEntity(groupArray[i]);
            List<ChildEntity> childList = new ArrayList<ChildEntity>();
            for (int j = 0; j < childTimeArray[i].length; j++) {
                ChildEntity childStatusEntity = new ChildEntity(childTimeArray[i][j]);
                childList.add(childStatusEntity);
            }
            groupEntity.setChildEntities(childList);
            groupList.add(groupEntity);
        }
        return groupList;
    }
}
