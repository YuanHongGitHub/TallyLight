package com.qq281982108.tallylight.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qq281982108.flyrefresh.FlyRefreshLayout;
import com.qq281982108.tallylight.ItemData;
import com.qq281982108.tallylight.R;
import com.qq281982108.tallylight.SampleItemAnimator;
import com.qq281982108.tallylight.model.Constant;
import com.qq281982108.tallylight.model.Tally;
import com.qq281982108.tallylight.view.PullToZoomListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-24 15:52
 * 类名：TallyMainActivity
 * 修改备注：
 */
public class TallyMainActivity extends BaseActivity implements View.OnClickListener, FlyRefreshLayout.OnPullRefreshListener {
    BmobUser mUser = new BmobUser();
    Tally mTally = new Tally();
    PullToZoomListView listView;
    private String[] adapterData;

    private FlyRefreshLayout mFlylayout;
    private RecyclerView mListView;

    private ItemAdapter mAdapter;

    private ArrayList<ItemData> mDataSet = new ArrayList<>();
    private Handler mHandler = new Handler();
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tally_main);
        Bmob.initialize(this, Constant.BmobAppID);

        listView = (PullToZoomListView) findViewById(R.id.listview);

        adapterData = new String[]{"Activity", "Service", "Content Provider", "Intent", "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient"};

        listView.setAdapter(new ArrayAdapter<String>(TallyMainActivity.this,
                android.R.layout.simple_list_item_1, adapterData));
        listView.getHeaderView().setImageResource(R.drawable.book_travel_bk);
        listView.getHeaderView().setScaleType(ImageView.ScaleType.CENTER_CROP);
        findViewById(R.id.zhuce).setOnClickListener(this);
        findViewById(R.id.tianjia).setOnClickListener(this);
        findViewById(R.id.huoqu).setOnClickListener(this);
        findViewById(R.id.xiugai).setOnClickListener(this);
        findViewById(R.id.shanchu).setOnClickListener(this);

        initDataSet();

        mFlylayout = (FlyRefreshLayout) findViewById(R.id.fly_layout);

        mFlylayout.setOnPullRefreshListener(this);

        mListView = (RecyclerView) findViewById(R.id.list);

        mLayoutManager = new LinearLayoutManager(this);
        mListView.setLayoutManager(mLayoutManager);
        mAdapter = new ItemAdapter(this);

        mListView.setAdapter(mAdapter);

        mListView.setItemAnimator(new SampleItemAnimator());

        View actionButton = mFlylayout.getHeaderActionButton();
        if (actionButton != null) {
            actionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFlylayout.startRefresh();
                }
            });
        }
    }

    private void initDataSet() {
        mDataSet.add(new ItemData(Color.parseColor("#76A9FC"), R.mipmap.ic_assessment_white_24dp, "Meeting Minutes", new Date(2014 - 1900, 2, 9)));
        mDataSet.add(new ItemData(Color.GRAY, R.mipmap.ic_folder_white_24dp, "Favorites Photos", new Date(2014 - 1900, 1, 3)));
        mDataSet.add(new ItemData(Color.GRAY, R.mipmap.ic_folder_white_24dp, "Photos", new Date(2014 - 1900, 0, 9)));
    }

    @Override
    public void onRefresh(FlyRefreshLayout view) {
        View child = mListView.getChildAt(0);
        if (child != null) {
            bounceAnimateView(child.findViewById(R.id.icon));
        }

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mFlylayout.onRefreshFinish();
            }
        }, 2000);
    }

    private void bounceAnimateView(View view) {
        if (view == null) {
            return;
        }

        Animator swing = ObjectAnimator.ofFloat(view, "rotationX", 0, 30, -20, 0);
        swing.setDuration(400);
        swing.setInterpolator(new AccelerateInterpolator());
        swing.start();
    }

    @Override
    public void onRefreshAnimationEnd(FlyRefreshLayout view) {
        addItemData();
    }

    private void addItemData() {
        ItemData itemData = new ItemData(Color.parseColor("#FFC970"), R.mipmap.ic_smartphone_white_24dp, "Magic Cube Show", new Date());
        mDataSet.add(0, itemData);
        mAdapter.notifyItemInserted(0);
        mLayoutManager.scrollToPosition(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zhuce:
                mUser.setUsername("hah");
                mUser.setPassword("123");
                mUser.signUp(this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        toast("注册成功");
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        toast("注册失败：" + s);
                    }
                });
                break;
            case R.id.tianjia:
                if (mTally.getObjectId() != null) mTally = new Tally();
                mTally.setCategory("支出");
                mTally.setMoney(12);
                mTally.setTime(System.currentTimeMillis());
                mTally.save(this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        toast("添加数据成功，返回objectId为：" + mTally.getObjectId());
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        toast("添加失败：" + s);
                    }
                });
                break;
            case R.id.huoqu:
                BmobQuery<Tally> bmobQuery = new BmobQuery<Tally>();
                bmobQuery.getObject(this, mTally.getObjectId(), new GetListener<Tally>() {
                    @Override
                    public void onSuccess(Tally tally) {
                        toast("查询成功" + tally.getCategory() + tally.getMoney() + tally.getTime());
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        toast("查询失败：" + s);
                    }
                });
                break;
            case R.id.xiugai:
                mTally.setCategory("收入");
                mTally.setMoney(20);
                mTally.setTime(System.currentTimeMillis());
                mTally.update(this, mTally.getObjectId(), new UpdateListener() {
                    @Override
                    public void onSuccess() {
                        toast("更新成功：" + mTally.getUpdatedAt());
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        toast("更新失败：" + s);
                    }
                });
                break;
            case R.id.shanchu:
                mTally.setObjectId(mTally.getObjectId());
                mTally.delete(this, new DeleteListener() {
                    @Override
                    public void onSuccess() {
                        toast("删除成功");
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        toast("删除失败：" + s);
                    }
                });
                break;
            default:
                break;
        }
    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView title;
        TextView subTitle;

        public ItemViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            title = (TextView) itemView.findViewById(R.id.title);
            subTitle = (TextView) itemView.findViewById(R.id.subtitle);
        }

    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

        private LayoutInflater mInflater;
        private DateFormat dateFormat;

        public ItemAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
            dateFormat = SimpleDateFormat.getDateInstance(DateFormat.DEFAULT, Locale.ENGLISH);
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = mInflater.inflate(R.layout.view_list_item, viewGroup, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
            final ItemData data = mDataSet.get(i);
            ShapeDrawable drawable = new ShapeDrawable(new OvalShape());
            drawable.getPaint().setColor(data.color);
            itemViewHolder.icon.setBackgroundDrawable(drawable);
            itemViewHolder.icon.setImageResource(data.icon);
            itemViewHolder.title.setText(data.title);
            itemViewHolder.subTitle.setText(dateFormat.format(data.time));
        }

        @Override
        public int getItemCount() {
            return mDataSet.size();
        }
    }
}
