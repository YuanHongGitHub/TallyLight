package com.qq281982108.tallylight.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
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
public class AddPageFragment extends BaseFragment {
    AddPageExpendFragment mAddPageExpendFragment = new AddPageExpendFragment();
    RecordPageFlowDirectionFragment mLiuxiangFragment = new RecordPageFlowDirectionFragment();
    RecordPageFlowDirectionFragment mLiuxiangFragment1 = new RecordPageFlowDirectionFragment();
    RecordPageGatherFragment mHuizongFragment = new RecordPageGatherFragment();
    private List<Fragment> mTabContents = new ArrayList<>();
    private FragmentPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private List<String> mDatas = Arrays.asList("支出", "收入", "转账", "借贷");
    private ViewPagerIndicator mIndicator;
    private RelativeLayout rl_calendar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_add_page, container, false);
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
        mViewPager = (ViewPager) v.findViewById(R.id.id_indicator_add_page_vp);
        mIndicator = (ViewPagerIndicator) v.findViewById(R.id.id_indicator_add_page);
        rl_calendar = (RelativeLayout) v.findViewById(R.id.rl_calendar);
    }

    private void initDatas() {
        mTabContents.add(mAddPageExpendFragment);
        mTabContents.add(mLiuxiangFragment);
        mTabContents.add(mLiuxiangFragment1);
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
        Toast.makeText(getActivity(), "AddPageFragment", Toast.LENGTH_SHORT).show();
    }
}
