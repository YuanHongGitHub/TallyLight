//package com.qq281982108.sqlitedemo;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.ListView;
//
//import com.qq281982108.sqlitedemo.expand.PinnedHeaderExpandableAdapter;
//import com.qq281982108.sqlitedemo.expand.PinnedHeaderExpandableListView;
//
//import org.litepal.crud.DataSupport;
//
//import java.util.List;
//
//public class MainActivity0 extends AppCompatActivity {
//
//    private ListView list;
//    private PinnedHeaderExpandableListView mExpandableListView;
//
//    private PinnedHeaderExpandableListView explistview;
//
//    private int expandFlag = -1;//控制列表的展开
//    private PinnedHeaderExpandableAdapter adapter;
//    private CommonAdapter<Expend> adapter0;
//    private List<Expend> expends = DataSupport.findAll(Expend.class);
//    private List<Expend> years = DataSupport.select("year").order("year desc").find(Expend.class);
//    private List<Expend> month = DataSupport.select("month").order("month desc").find(Expend.class);
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main0);
////        list = (ListView) findViewById(R.id.list);
//        mExpandableListView = (PinnedHeaderExpandableListView)findViewById(R.id.explistview);
//        explistview = (PinnedHeaderExpandableListView)findViewById(R.id.explistview);
//        initData();
//        Expend expend = new Expend();
//        expend.setYear(1999);
//        expend.setMonth(1);
//        expend.setMoney("123");
//        expend.setExpendCategory("晚餐");
//        expend.setMoneyCategory("现金");
//        expend.setRemark(null);
//        expend.setTime("00000000");
//        expend.setUser("自己");
//        expend.setMerchant(null);
//        expend.setRefund(false);
//        expend.save();
//
//
////        list.setAdapter(adapter0 = new CommonAdapter<Expend>(
////                getApplicationContext(), expends, R.layout.main_item) {
////            @Override
////            public void convert(ViewHolder c, Expend expend) {
////                TextView view = c.getView(R.id.id_tv_title);
////                view.setText(
////                        expend.getMoney()
////                                + expend.getExpendCategory()
////                                + expend.getMoneyCategory()
////                                + expend.getTime()
////                                + expend.getUser());
////            }
////
////        });
//    }
//    private String[][] childrenData;
//    private String[] groupData;
//
//    //    private String[][] childrenData = new String[10][10];
////    private String[] groupData = new String[10];
//    private void initData() {
////        for(int i=0;i<10;i++){
////            groupData[i] = "分组"+i;
////        }
////
////        for(int i=0;i<10;i++){
////            for(int j=0;j<10;j++){
////                childrenData[i][j] = "好友"+i+"-"+j;
////            }
////        }
////        //设置悬浮头部VIEW
////        explistview.setHeaderView(getLayoutInflater().inflate(R.layout.group_head,
////                explistview, false));
////        adapter = new PinnedHeaderExpandableAdapter(childrenData, groupData, getApplicationContext(),explistview);
////        explistview.setAdapter(adapter);
//
//        if (years.size()!=0&&month.size()!=0){
//            groupData = new String[years.size()];
//            childrenData = new String[years.size()][month.size()];
//            for(int i=0;i<years.size();i++){
//                groupData[i] = "年份"+i;
//            }
//
//            for(int i=0;i<years.size();i++){
//                for(int j=0;j<month.size();j++){
//                    childrenData[i][j] = "年份"+i+"-月份"+j;
//                }
//            }
//            //设置悬浮头部VIEW
//            mExpandableListView.setHeaderView(getLayoutInflater().inflate(R.layout.group_head,
//                    mExpandableListView, false));
//            adapter = new PinnedHeaderExpandableAdapter(childrenData, groupData, getApplicationContext(),mExpandableListView);
//            mExpandableListView.setAdapter(adapter);
//        }
//    }
//
////    class GroupClickListener implements ExpandableListView.OnGroupClickListener {
////        @Override
////        public boolean onGroupClick(ExpandableListView parent, View v,
////                                    int groupPosition, long id) {
////            if (expandFlag == -1) {
////                // 展开被选的group
////                explistview.expandGroup(groupPosition);
////                // 设置被选中的group置于顶端
////                explistview.setSelectedGroup(groupPosition);
////                expandFlag = groupPosition;
////            } else if (expandFlag == groupPosition) {
////                explistview.collapseGroup(expandFlag);
////                expandFlag = -1;
////            } else {
////                explistview.collapseGroup(expandFlag);
////                // 展开被选的group
////                explistview.expandGroup(groupPosition);
////                // 设置被选中的group置于顶端
////                explistview.setSelectedGroup(groupPosition);
////                expandFlag = groupPosition;
////            }
////            return true;
////        }
////    }
//
////    class GroupClickListener implements ExpandableListView.OnGroupClickListener {
////        @Override
////        public boolean onGroupClick(ExpandableListView parent, View v,
////                                    int groupPosition, long id) {
////            if (expandFlag == -1) {
////                // 展开被选的group
////                mExpandableListView.expandGroup(groupPosition);
////                // 设置被选中的group置于顶端
////                mExpandableListView.setSelectedGroup(groupPosition);
////                expandFlag = groupPosition;
////            } else if (expandFlag == groupPosition) {
////                mExpandableListView.collapseGroup(expandFlag);
////                expandFlag = -1;
////            } else {
////                mExpandableListView.collapseGroup(expandFlag);
////                // 展开被选的group
////                mExpandableListView.expandGroup(groupPosition);
////                // 设置被选中的group置于顶端
////                mExpandableListView.setSelectedGroup(groupPosition);
////                expandFlag = groupPosition;
////            }
////            return true;
////        }
////    }
//
//    int i = 0;
//    public void submit(View view) {
//        i = i+2;
//        Expend expend = new Expend();
//        if (i%2 == 0){
//            expend.setMoney(i + "");
//            if (i%4==0){
//                expend.setExpendCategory("早餐");
//            } else expend.setExpendCategory("晚餐");
//            if (i%10 == 0){
//                expend.setMoneyCategory("支付宝");
//                expend.setYear(2012);
//                expend.setMonth(12);
//            }else {
//                expend.setMoneyCategory("现金");
//                expend.setYear(2015);
//                expend.setMonth(10);
//            }
//            expend.setRemark(null);
//            expend.setTime("2016-" + i);
//            if (i%6 == 0) {
//                expend.setUser("其他");
//            }else expend.setUser("自己");
//        }
//        expend.save();
//
//        expends.add(expend);
////        adapter0.notifyDataSetChanged();
//
//    }
//}
