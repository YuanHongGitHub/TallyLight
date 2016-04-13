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
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qq281982108.tallylight.R;
import com.qq281982108.tallylight.adapter.MyExpandableListAdapter;
import com.qq281982108.tallylight.db.DbOperations;
import com.qq281982108.tallylight.model.Spending;
import com.qq281982108.tallylight.renyugang.PinnedHeaderExpandableListView;

import java.util.List;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-25 15:35
 * 类名：RecordPageDetailFragment
 * 修改备注：
 */
public class RecordPageDetailFragment extends BaseFragment {
    private static final String TAG = "DetailFragment";
    private DbOperations mDbOperations = new DbOperations();
    private PinnedHeaderExpandableListView expandableListView;
    private List<String> groupList;
    private List<List<Spending>> childList;
    private MyExpandableListAdapter adapter;
    private Receiver mReceiver = new Receiver();
    private IntentFilter mTimeFilter = new IntentFilter("android.basic.notify");
    private ProgressBar mDialog;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record_page_detail, container, false);
        expandableListView = (PinnedHeaderExpandableListView) view.findViewById(R.id.expandablelist);
        mDialog = (ProgressBar) view.findViewById(R.id.progressBar);
        new MyAsyncTask().execute();
        return view;
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

    private class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case "android.basic.notify":
                    new MyAsyncTask().execute();
                    break;
                default:
                    break;
            }
        }
    }

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
            groupList = mDbOperations.initGroupList();
            childList = mDbOperations.initChildList();
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
            mDialog.setVisibility(View.INVISIBLE);
            adapter = new MyExpandableListAdapter(getContext(), groupList, childList);
            expandableListView.setAdapter(adapter);
            expandableListView.setVisibility(View.VISIBLE);
            if (groupList.size() == 0) {
                expandableListView.setVisibility(View.INVISIBLE);
            } else expandableListView.expandGroup(0);

            Log.e(TAG, Thread.currentThread().getName() + " onPostExecute ");

            expandableListView.setOnHeaderUpdateListener(new PinnedHeaderExpandableListView.OnHeaderUpdateListener() {
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
                        TextView textView = (TextView) headerView.findViewById(R.id.group);
                        textView.setText((String) (adapter.getGroup(firstVisibleGroupPos)));
                    }
                }
            });
            expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                    return false;
                }
            });
            expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                    return false;
                }
            });
        }
    }
}
