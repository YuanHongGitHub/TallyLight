package com.qq281982108.tallylight.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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
public class RecordPageDetailFragment extends BaseFragment {
    private ListView mListView;
    private List<Expend> allExpend = DataSupport.order("time desc").find(Expend.class);
    private CommonAdapter mAdapter;
    private Receiver mReceiver = new Receiver();
    private IntentFilter mTimeFilter = new IntentFilter("android.basic.notify");

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record_page_detail, container, false);
        mListView = (ListView) view.findViewById(R.id.main_listView);
//        Collections.sort(allExpend, new Comparator<Expend>() {
//            @Override
//            public int compare(Expend lhs, Expend rhs) {
//                Date date1 = TimeUtils.stringToDate(lhs.getTime());
//                Date date2 = TimeUtils.stringToDate(rhs.getTime());
//                if (date1.before(date2)){
//                    return 1;
//                }
//                return -1;
//            }
//        });
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
                        allExpend.remove(expend);
                        DataSupport.delete(Expend.class, expendId);
                        mAdapter.notifyDataSetChanged();
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

    private class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case "android.basic.notify":
                    Expend expend = DataSupport.findLast(Expend.class);
                    allExpend.add(expend);
                    mAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    }
}
