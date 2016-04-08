package com.qq281982108.tallylight.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.qq281982108.tallylight.R;
import com.qq281982108.tallylight.model.Expend;
import com.qq281982108.tallylight.renyugang.PinnedHeaderExpandableListView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-25 15:35
 * 类名：RecordPageDetailFragment
 * 修改备注：
 */
public class RecordPageDetailFragment extends BaseFragment implements ExpandableListView.OnChildClickListener,
        ExpandableListView.OnGroupClickListener,
        PinnedHeaderExpandableListView.OnHeaderUpdateListener {
    private static final String TAG = "DetailFragment";

    private PinnedHeaderExpandableListView expandableListView;
    private List<Expend> groupList;
    private List<List<Expend>> childList;
    private MyexpandableListAdapter adapter;

    private List<Expend> timeList;
    //    private CommonAdapter mAdapter;
    private Receiver mReceiver = new Receiver();
    private IntentFilter mTimeFilter = new IntentFilter("android.basic.notify");
    private ProgressBar mDialog;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record_page_detail, container, false);
        expandableListView = (PinnedHeaderExpandableListView) view.findViewById(R.id.expandablelist);
        mDialog = (ProgressBar) view.findViewById(R.id.progressBar);
        timeList = DataSupport.select("time").order("time desc").find(Expend.class);
        initData();
        adapter = new MyexpandableListAdapter(getContext());
        expandableListView.setAdapter(adapter);

        // 展开所有group
        for (int i = 0, count = expandableListView.getCount(); i < count; i++) {
            expandableListView.expandGroup(i);
        }

        expandableListView.setOnHeaderUpdateListener(this);
        expandableListView.setOnChildClickListener(this);
        expandableListView.setOnGroupClickListener(this);

        return view;
    }

    void initData() {
        groupList = timeList;
//        groupList = new ArrayList<Expend>();
//        Expend group = null;
//        for (int i = 0; i < timeList.size(); i++) {
//            group = new Expend();
//            groupList.add(group);
//        }
        childList = new ArrayList<List<Expend>>();
        for (int i = 0; i < groupList.size(); i++) {
            ArrayList<Expend> childTemp;
            if (i == 0) {
                childTemp = new ArrayList<Expend>();
                for (int j = 0; j < 13; j++) {
                    Expend people = new Expend();
                    people.setMoney("money-" + j);
                    people.setUser("user-" + j);
                    people.setMerchant("merchant-" + j);

                    childTemp.add(people);
                }
            } else if (i == 1) {
                childTemp = new ArrayList<Expend>();
                for (int j = 0; j < 8; j++) {
                    Expend people = new Expend();
                    people.setMoney("money---" + j);
                    people.setUser("user---" + j);
                    people.setMerchant("merchant---" + j);

                    childTemp.add(people);
                }
            } else {
                childTemp = new ArrayList<Expend>();
                for (int j = 0; j < 23; j++) {
                    Expend people = new Expend();
                    people.setMoney("money-------" + j);
                    people.setUser("user-------" + j);
                    people.setMerchant("merchant-------" + j);

                    childTemp.add(people);
                }
            }
            childList.add(childTemp);
        }

    }

    @Override
    public boolean onGroupClick(final ExpandableListView parent, final View v,
                                int groupPosition, final long id) {

        return false;
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v,
                                int groupPosition, int childPosition, long id) {
        Toast.makeText(getContext(),
                childList.get(groupPosition).get(childPosition).getMoney(), 1)
                .show();

        return false;
    }

    @Override
    public View getPinnedHeader() {
        View headerView = getActivity().getLayoutInflater().inflate(R.layout.group, null);
        headerView.setLayoutParams(new AbsListView.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));

        return headerView;
    }

    @Override
    public void updatePinnedHeader(View headerView, int firstVisibleGroupPos) {
        if (adapter.getGroupCount() > 0) { // 防止列表在无数据的情况崩溃
            Expend firstVisibleGroup = (Expend) adapter.getGroup(firstVisibleGroupPos);
            TextView textView = (TextView) headerView.findViewById(R.id.group);
            textView.setText(firstVisibleGroup.getTime());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(mReceiver, mTimeFilter);
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().unregisterReceiver(mReceiver);
    }

    /**
     * 初始化变量，并读取第一页显示的数据
     */
    public void loadData() {
//        mAdapter = new CommonAdapter<Expend>(
//                getContext(), allExpend, R.layout.main_item) {
//            @Override
//            public void convert(ViewHolder helper, Expend item) {
//                helper.setText(R.id.id_tv_title, item.getTime() + item.getUser() + item.getExpendCategory() + item.getMoney());
//            }
//        };
//        mListView.setAdapter(mAdapter);
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

            groupHolder.textView.setText(((Expend) getGroup(groupPosition))
                    .getTime());
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

                childHolder.textName = (TextView) convertView
                        .findViewById(R.id.name);
                childHolder.textAge = (TextView) convertView
                        .findViewById(R.id.age);
                childHolder.textAddress = (TextView) convertView
                        .findViewById(R.id.address);
                childHolder.imageView = (ImageView) convertView
                        .findViewById(R.id.image);
                Button button = (Button) convertView
                        .findViewById(R.id.button1);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "clicked pos=", Toast.LENGTH_SHORT).show();
                    }
                });

                convertView.setTag(childHolder);
            } else {
                childHolder = (ChildHolder) convertView.getTag();
            }

            childHolder.textName.setText(((Expend) getChild(groupPosition,
                    childPosition)).getTime());
            childHolder.textAge.setText(String.valueOf(((Expend) getChild(
                    groupPosition, childPosition)).getMoney()));
            childHolder.textAddress.setText(((Expend) getChild(groupPosition,
                    childPosition)).getMerchant());

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
        TextView textName;
        TextView textAge;
        TextView textAddress;
        ImageView imageView;
    }

    private class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case "android.basic.notify":
//                    new MyAsyncTask().execute();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 清空数据并加载数据
     */
//    public void clearListAndReadList() {
//        if (allExpend != null) {
//            int size = allExpend.size();
//            if (size > 0) {
//                allExpend.removeAll(allExpend);
//            }
//        }
//        allExpend = DataSupport.order("time desc").find(Expend.class);
//    }

    private class MyAsyncTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            mDialog.setVisibility(View.VISIBLE);
            Log.e(TAG, Thread.currentThread().getName() + " onPreExecute ");
        }

        @Override
        protected Void doInBackground(Void... params) {
            // 模拟数据的加载,耗时的任务
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//            clearListAndReadList();
            Log.e(TAG, Thread.currentThread().getName() + " doInBackground ");
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.e(TAG, Thread.currentThread().getName() + " onProgressUpdate ");
        }

        @Override
        protected void onPostExecute(Void result) {
            // 进行数据加载完成后的UI操作
            loadData();
            mDialog.setVisibility(View.INVISIBLE);
            Log.e(TAG, Thread.currentThread().getName() + " onPostExecute ");
        }
    }
}
