package com.qq281982108.tallylight.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qq281982108.tallylight.R;
import com.qq281982108.tallylight.view.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-25 12:50
 * 类名：RecordPageFragment
 * 修改备注：
 */
public class RecordPageFragment extends BaseFragment {
    RecordPageDetailFragment mDetailFragment = new RecordPageDetailFragment();
    RecordPageFlowDirectionFragment mLiuxiangFragment = new RecordPageFlowDirectionFragment();
    RecordPageGatherFragment mHuizongFragment = new RecordPageGatherFragment();
    private List<Fragment> mTabContents = new ArrayList<>();
    private FragmentPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private List<String> mDatas = Arrays.asList("明细", "流向", "汇总");
    private ViewPagerIndicator mIndicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_record_page, container, false);
        initView(view);
        initDatas();
        //设置Tab上的标题
        mIndicator.setTabItemTitles(mDatas);
        mViewPager.setAdapter(mAdapter);
        //设置关联的ViewPager
        mIndicator.setViewPager(mViewPager, 0);
        return view;
    }

    private void initView(View v) {
        mViewPager = (ViewPager) v.findViewById(R.id.id_vp);
        mIndicator = (ViewPagerIndicator) v.findViewById(R.id.id_indicator_record_page);
    }

    private void initDatas() {
        mTabContents.add(mDetailFragment);
        mTabContents.add(mLiuxiangFragment);
        mTabContents.add(mHuizongFragment);

        mAdapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return mTabContents.get(position);
            }

            @Override
            public int getCount() {
                return mTabContents.size();
            }


        };
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        Toast.makeText(getActivity(), "RecordPageFragment", Toast.LENGTH_SHORT).show();
    }
}
