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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.qq281982108.tallylight.R;
import com.qq281982108.tallylight.adapter.CommonAdapter;
import com.qq281982108.tallylight.model.Expend;
import com.qq281982108.tallylight.util.ViewHolder;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-25 15:35
 * 类名：RecordPageDetailFragment
 * 修改备注：
 */
public class RecordPageDetailFragment00 extends BaseFragment {
    private static final String TAG = "DetailFragment";
    private ListView mListView;
    private List<Expend> allExpend;
    private CommonAdapter mAdapter;
    private Receiver mReceiver = new Receiver();
    private IntentFilter mTimeFilter = new IntentFilter("android.basic.notify");
    private ProgressBar mDialog;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record_page_detail00, container, false);
        mListView = (ListView) view.findViewById(R.id.main_listView);
        mDialog = (ProgressBar) view.findViewById(R.id.progressBar);
        allExpend = DataSupport.order("time desc").find(Expend.class);
        if (mAdapter == null) {
            mAdapter = new CommonAdapter<Expend>(
                    getContext(), allExpend, R.layout.main_item) {
                @Override
                public void convert(ViewHolder helper, Expend item) {
                    helper.setText(R.id.id_tv_title, item.getTime() + item.getUser() + item.getExpendCategory() + item.getMoney());
                }
            };
        }
        //设置适配器
        mListView.setAdapter(mAdapter);

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                final Expend expend = (Expend) listView.getItemAtPosition(position);
                final int expendId = expend.getId();
                ItemLongClickDialogFragment itemLongClickDialogFragment = ItemLongClickDialogFragment.newInstance(expendId);
                itemLongClickDialogFragment.setOnDetailItemDeleteClickListener(new ItemLongClickDialogFragment.OnDetailItemDeleteClickListener() {
                    @Override
                    public void delete() {
                        DataSupport.delete(Expend.class, expendId);
                        new MyAsyncTask().execute();
                    }
                });
                itemLongClickDialogFragment.show(getActivity().getFragmentManager(), "item");
                return true;
            }
        });

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

    /**
     * 清空数据并加载数据
     */
    public void clearListAndReadList() {
        if (allExpend != null) {
            int size = allExpend.size();
            if (size > 0) {
                allExpend.removeAll(allExpend);
            }
        }
        allExpend = DataSupport.order("time desc").find(Expend.class);
    }

    /**
     * 初始化变量，并读取第一页显示的数据
     */
    public void loadData() {
        mAdapter = new CommonAdapter<Expend>(
                getContext(), allExpend, R.layout.main_item) {
            @Override
            public void convert(ViewHolder helper, Expend item) {
                helper.setText(R.id.id_tv_title, item.getTime() + item.getUser() + item.getExpendCategory() + item.getMoney());
            }
        };
        mListView.setAdapter(mAdapter);
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
            clearListAndReadList();
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
