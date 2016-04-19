package com.qq281982108.pinnedheaderexpandablelistview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.qq281982108.pinnedheaderexpandablelistview.model.Spending;
import com.qq281982108.pinnedheaderexpandablelistview.view.PinnedHeaderExpandableListView;
import com.qq281982108.pinnedheaderexpandablelistview.view.StickyLayout;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends Activity implements
        ExpandableListView.OnChildClickListener,
        ExpandableListView.OnGroupClickListener,
        PinnedHeaderExpandableListView.OnHeaderUpdateListener, StickyLayout.OnGiveUpTouchEventListener {

    private PinnedHeaderExpandableListView expandableListView;

    private EditText jine, zhichuleibie, zhanghu, beizhu, shijian, yonghu, shangjia;
    private CheckBox baoxiao;

    private ArrayList<String> groupList;
    private ArrayList<List<Spending>> childList;

    private MyexpandableListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        expandableListView = (PinnedHeaderExpandableListView) findViewById(R.id.expandablelist);
        jine = (EditText) findViewById(R.id.jine);
        zhichuleibie = (EditText) findViewById(R.id.zhichuleibie);
        zhanghu = (EditText) findViewById(R.id.zhanghu);
        beizhu = (EditText) findViewById(R.id.beizhu);
        shijian = (EditText) findViewById(R.id.shijian);
        yonghu = (EditText) findViewById(R.id.yonghu);
        shangjia = (EditText) findViewById(R.id.shangjia);
        baoxiao = (CheckBox) findViewById(R.id.baoxiao);
        initData();

        adapter = new MyexpandableListAdapter(this);
        expandableListView.setAdapter(adapter);

        // 展开group0

//        expandableListView.expandGroup(0);
        expandableListView.setOnHeaderUpdateListener(this);
        expandableListView.setOnChildClickListener(this);
        expandableListView.setOnGroupClickListener(this);

    }

    /***
     * InitData
     */
    void initData() {
        groupList = new ArrayList<String>();
        List<Spending> yearMonthList = DataSupport.select("recorderYearMonth").find(Spending.class);
        List<String> yearMonthList0 = new ArrayList<String>();
        for (Spending aYearMonthList : yearMonthList) {
            yearMonthList0.add(aYearMonthList.getRecorderYearMonth());
        }
        HashSet<String> hashSet = new HashSet<String>(yearMonthList0);
        groupList.addAll(hashSet);
        Collections.sort(groupList, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return lhs.compareTo(rhs);
            }
        });
        childList = new ArrayList<List<Spending>>();
        for (int i = 0; i < groupList.size(); i++) {
            List<Spending> spendingI = DataSupport.where("recorderYearMonth = ?", groupList.get(i)).find(Spending.class);
            Log.e("yh", "spendingsize" + spendingI.size());
            ArrayList<Spending> childTemp = new ArrayList<Spending>();
            for (int j = 0; j < spendingI.size(); j++) {
                childTemp.add(spendingI.get(j));
                Log.e("yh", "getRecorderTime" + j + "-->" + spendingI.get(j).getRecorderTime());
            }
            Collections.sort(childTemp, new Comparator<Spending>() {
                @Override
                public int compare(Spending lhs, Spending rhs) {
                    return rhs.getRecorderTime().compareTo(lhs.getRecorderTime());
                }
            });
            childList.add(childTemp);
        }
//        if (spendingYearMonth.size() == 0) {
//            expandableListView.setVisibility(View.INVISIBLE);
////            expandableListView.setOnHeaderUpdateListener(this);
////            expandableListView.setOnChildClickListener(this);
////            expandableListView.setOnGroupClickListener(this);
//        } else {
//            expandableListView.setVisibility(View.VISIBLE);
////            expandableListView.expandGroup(0);
//            Spending dataItem;
//            Map<String, List<Spending>> resultMap = new HashMap<String, List<Spending>>();
//            for (int i = 0; i < spendingYearMonth.size(); i++) {
//                dataItem = spendingYearMonth.get(i);
//                if (resultMap.containsKey(dataItem.getRecorderYearMonth())) {
//                    resultMap.get(dataItem.getRecorderYearMonth()).add(dataItem);
//                } else {
//                    List<Spending> list = new ArrayList<Spending>();
//                    list.add(dataItem);
//                    resultMap.put(dataItem.getRecorderYearMonth(), list);
//                }
//            }
//            Log.e("yh", " resultMap.size(): " + resultMap.size());
//            for (Spending spending : spendingYearMonth) {
//                Log.e("yh", "getRecorderYearMonth: " + spending.getRecorderYearMonth());
//                groupList.add(spending);
//            }
//        }

//        childList = new ArrayList<List<Spending>>();

//        groupList = new ArrayList<Spending>();
//        Spending group = null;
//        for (int i = 0; i < 3; i++) {
//            group = new Spending();
//            groupList.add(group);
//        }
//        childList = new ArrayList<List<Spending>>();
//        for (int i = 0; i < groupList.size(); i++) {
//            ArrayList<Spending> childTemp;
//            if (i == 0) {
//                childTemp = new ArrayList<Spending>();
//                for (int j = 0; j < 13; j++) {
//                    Spending people = new Spending();
//                    people.setMoney("money-" + j);
////                    people.setUser("user-" + j);
////                    people.setMerchant("merchant-" + j);
//
//                    childTemp.add(people);
//                }
//            } else if (i == 1) {
//                childTemp = new ArrayList<Spending>();
//                for (int j = 0; j < 8; j++) {
//                    Spending people = new Spending();
//                    people.setMoney("money---" + j);
////                    people.setUser("user---" + j);
////                    people.setMerchant("merchant---" + j);
//
//                    childTemp.add(people);
//                }
//            } else {
//                childTemp = new ArrayList<Spending>();
//                for (int j = 0; j < 23; j++) {
//                    Spending people = new Spending();
//                    people.setMoney("money-------" + j);
////                    people.setUser("user-------" + j);
////                    people.setMerchant("merchant-------" + j);
//
//                    childTemp.add(people);
//                }
//            }
//            childList.add(childTemp);
//        }


    }

    public void tijiao(View view) {
        String jineString = jine.getText().toString();
        String zhichuleibieString = zhichuleibie.getText().toString();
        String zhanghuString = zhanghu.getText().toString();
        String beizhuString = beizhu.getText().toString();
        String shijianString = shijian.getText().toString();
        String yonghuString = yonghu.getText().toString();
        String shangjiaString = shangjia.getText().toString();
        boolean baoxiaoBoolean = baoxiao.isChecked();

        Spending spending = new Spending();

        //2016-01-01 00:00
        spending.setRecorderYearMonth(shijianString.substring(0, 6));//201601
//        recorderTime.setMonthDay(shijianString.substring(6, 8));//01
//        recorderTime.setDayTime(shijianString.substring(8));//0000

        //餐饮-早餐
//        spendingCategory.setSpendingGroup(zhichuleibieString.substring(0, 2));
//        spendingCategory.setSpendingChild(zhanghuString.substring(2));


        spending.setRefund(baoxiaoBoolean);
        spending.setMoney(jineString);
        spending.setSeller(shangjiaString);

        spending.setAccount(zhanghuString);
        spending.setSpendingCategory(zhichuleibieString);
        spending.setRecorderTime(shijianString);
        spending.setSpendingRemark(beizhuString);
        spending.setUser(yonghuString);

        spending.save();
    }

    @Override
    public boolean onGroupClick(final ExpandableListView parent, final View v,
                                int groupPosition, final long id) {

        return false;
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v,
                                int groupPosition, int childPosition, long id) {
//        Toast.makeText(MainActivity.this,
//                childList.get(groupPosition).get(childPosition).getName(), 1)
//                .show();

        return false;
    }

    @Override
    public View getPinnedHeader() {
        View headerView = getLayoutInflater().inflate(R.layout.group, null);
        headerView.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        return headerView;
    }

    @Override
    public void updatePinnedHeader(View headerView, int firstVisibleGroupPos) {
        if (adapter.getGroupCount() > 0) { // 防止列表在无数据的情况崩溃
            String firstVisibleGroup = (String) adapter.getGroup(firstVisibleGroupPos);
            TextView textView = (TextView) headerView.findViewById(R.id.group);
            textView.setText(firstVisibleGroup);
        }
    }

    @Override
    public boolean giveUpTouchEvent(MotionEvent event) {
        if (expandableListView.getFirstVisiblePosition() == 0) {
            View view = expandableListView.getChildAt(0);
            if (view != null && view.getTop() >= 0) {
                return true;
            } else if (adapter.getGroupCount() == 0) { //保证列表没有数据的情况下，头部隐藏后还能继续下拉显示出来
                return true;
            }
        }
        return false;
    }

    /***
     * 数据源
     *
     * @author Administrator
     */
    class MyexpandableListAdapter extends BaseExpandableListAdapter {
        private Context context;
        private LayoutInflater inflater;

        public MyexpandableListAdapter(Context context) {
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        // 返回父列表个数
        @Override
        public int getGroupCount() {
            return groupList.size();
        }

        // 返回子列表个数
        @Override
        public int getChildrenCount(int groupPosition) {
            return childList.get(groupPosition).size();
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
                convertView = inflater.inflate(R.layout.group, null);
                groupHolder.textView = (TextView) convertView
                        .findViewById(R.id.group);
                groupHolder.imageView = (ImageView) convertView
                        .findViewById(R.id.image);
                convertView.setTag(groupHolder);
            } else {
                groupHolder = (GroupHolder) convertView.getTag();
            }

            groupHolder.textView.setText(((String) getGroup(groupPosition)));
            if (isExpanded)// ture is Expanded or false is not isExpanded
                groupHolder.imageView.setImageResource(R.drawable.expanded);
            else
                groupHolder.imageView.setImageResource(R.drawable.collapse);
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
                convertView = inflater.inflate(R.layout.child, null);

                childHolder.textShijian = (TextView) convertView
                        .findViewById(R.id.cshijian);
                childHolder.textleibie = (TextView) convertView
                        .findViewById(R.id.cleibie);
                childHolder.textjine = (TextView) convertView
                        .findViewById(R.id.cjine);
                convertView.setTag(childHolder);
            } else {
                childHolder = (ChildHolder) convertView.getTag();
            }

            if (childPosition > 0) {
                String dayPos = ((Spending) getChild(groupPosition,
                        childPosition)).getRecorderTime().substring(0, 8);
                String dayPos_ = ((Spending) getChild(groupPosition,
                        childPosition - 1)).getRecorderTime().substring(0, 8);
                Log.e("yh", "day:" + dayPos + "day-1:" + dayPos_);
//            Log.e("yh", "groupPosition:" + groupPosition + "childPosition:" + childPosition);
                if (((Spending) getChild(groupPosition,
                        childPosition)).getRecorderTime().substring(0, 8).equals(((Spending) getChild(groupPosition,
                        childPosition - 1)).getRecorderTime().substring(0, 8))) {
                    Log.e("yh", "groupPosition:" + groupPosition + "childPosition:" + childPosition);
                    childHolder.textShijian.setText("与上一个相同");
                }
            } else {
                childHolder.textShijian.setText(((Spending) getChild(groupPosition,
                        childPosition)).getRecorderTime().substring(0, 8));
            }
//            childHolder.textleibie.setText(String.valueOf(((Spending) getChild(
//                    groupPosition, childPosition)).getSpendingCategory()));
//            childHolder.textjine.setText(((Spending) getChild(groupPosition,
//                    childPosition)).getMoney());

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
    }

    class GroupHolder {
        TextView textView;
        ImageView imageView;
    }

    class ChildHolder {
        TextView textShijian;
        TextView textleibie;
        TextView textjine;
    }

}

