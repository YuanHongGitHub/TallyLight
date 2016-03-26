package com.qq281982108.tallylight.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.qq281982108.tallylight.R;
import com.qq281982108.tallylight.fragment.AddPageFragment;
import com.qq281982108.tallylight.fragment.BaseFragment;
import com.qq281982108.tallylight.fragment.MinePageFragment;
import com.qq281982108.tallylight.fragment.RecordPageFragment;
import com.qq281982108.tallylight.fragment.StatementPageFragment;
import com.qq281982108.tallylight.fragment.WalletPageFragment;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-25 11:21
 * 类名：TallyLightActivity
 * 修改备注：
 */
public class TallyLightActivity extends BaseActivity implements View.OnClickListener {
    public static final int DETAIL_PAGE_INDEX = 0;
    public static final int STATEMENT_PAGE_INDEX = 1;
    public static final int ADD_PAGE_INDEX = 2;
    public static final int WALLET_PAGE_INDEX = 3;
    public static final int MINE_PAGE_INDEX = 4;
    private FragmentManager fm;
    private BaseFragment[] listFragments;
    private String[] tabArray = {"detail", "statement", "add", "wallet", "mine"};
    private int index = 0;
    private TextView tv_detailPage, tv_statementPage, tv_addPage, walletPage, minePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tally_light);
        init();
    }

    private void init() {
        // TODO Auto-generated method stub
        // 先初始化变量、控件并设置点击事件监听
        tv_detailPage = (TextView) findViewById(R.id.tv_record_page);
        tv_statementPage = (TextView) findViewById(R.id.tv_statement_page);
        tv_addPage = (TextView) findViewById(R.id.tv_add_page);
        walletPage = (TextView) findViewById(R.id.tv_wallet_page);
        minePage = (TextView) findViewById(R.id.tv_mine_page);

        tv_detailPage.setOnClickListener(this);
        tv_statementPage.setOnClickListener(this);
        tv_addPage.setOnClickListener(this);
        walletPage.setOnClickListener(this);
        minePage.setOnClickListener(this);

        // 将四个Fragment全部放入BaseFragment的数组中，后续就用listFragments索引 的形式操纵Fragment
        listFragments = new BaseFragment[tabArray.length];
        listFragments[DETAIL_PAGE_INDEX] = new RecordPageFragment();
        listFragments[STATEMENT_PAGE_INDEX] = new StatementPageFragment();
        listFragments[ADD_PAGE_INDEX] = new AddPageFragment();
        listFragments[WALLET_PAGE_INDEX] = new WalletPageFragment();
        listFragments[MINE_PAGE_INDEX] = new MinePageFragment();

        // 获取事务处理的FragmentTransaction，FragmentTransaction的相关api可以对fragment进行添加、移除、隐藏、显示等操作
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        // 初始化时先看listFragments[0](即首页HomePageFragment)是否已经被添加
        // 如果没有添加，则通过FragmentTransaction向activity提交一个添加HomePageFragment的事务
        // 如果已经添加过，则通过FragmentTransaction向activity提交隐藏之前fragment并显示HomePageFragment的请求
        // 从这里可以看出，FragmentTransaction可以提交一套事务让activity处理
        if (!listFragments[0].isAdded()) {
            ft.add(R.id.container_layout, listFragments[0]).commit();
        } else {
            ft.hide(listFragments[index]).show(listFragments[0]).commit();
        }
        setPageStyle(DETAIL_PAGE_INDEX);

    }

    // 该函数主要负责在切换activity时变换底部导航栏字体和图标的样式，以及变换title栏字体内容
    private void setPageStyle(int pageIndex) {
        int gray = getResources().getColor(R.color.colorGray);
        int blue = getResources().getColor(R.color.colorBlue);
        tv_detailPage.setTextColor(gray);
        tv_statementPage.setTextColor(gray);
        tv_addPage.setTextColor(gray);
        walletPage.setTextColor(gray);
        minePage.setTextColor(gray);

        tv_detailPage.setCompoundDrawablesWithIntrinsicBounds(0,
                R.drawable.shiguangzhou, 0, 0);
        tv_statementPage.setCompoundDrawablesWithIntrinsicBounds(0,
                R.drawable.baobiao, 0, 0);
        tv_addPage.setCompoundDrawablesWithIntrinsicBounds(0,
                R.drawable.qianbao, 0, 0);
        walletPage.setCompoundDrawablesWithIntrinsicBounds(0,
                R.drawable.qianbao, 0, 0);
        minePage.setCompoundDrawablesWithIntrinsicBounds(0,
                R.drawable.qianbao, 0, 0);
        switch (pageIndex) {
            case DETAIL_PAGE_INDEX:
                tv_detailPage.setTextColor(blue);
                tv_detailPage.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.drawable.shiguangzhou_lan, 0, 0);
                break;
            case STATEMENT_PAGE_INDEX:
                tv_statementPage.setTextColor(blue);
                tv_statementPage.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.drawable.baobiao_lan, 0, 0);
                break;
            case ADD_PAGE_INDEX:
                tv_addPage.setTextColor(blue);
                tv_addPage.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.drawable.qianbao_lan, 0, 0);
                break;
            case WALLET_PAGE_INDEX:
                walletPage.setTextColor(blue);
                walletPage.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.drawable.qianbao_lan, 0, 0);
                break;
            case MINE_PAGE_INDEX:
                minePage.setTextColor(blue);
                minePage.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.drawable.qianbao_lan, 0, 0);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.tv_record_page:
                showPageFragment(DETAIL_PAGE_INDEX);
                break;
            case R.id.tv_statement_page:
                showPageFragment(STATEMENT_PAGE_INDEX);
                break;

            case R.id.tv_add_page:
                showPageFragment(ADD_PAGE_INDEX);
                break;
            case R.id.tv_wallet_page:
                showPageFragment(WALLET_PAGE_INDEX);
                break;
            case R.id.tv_mine_page:
                showPageFragment(MINE_PAGE_INDEX);
                break;
            default:
                break;
        }
    }

    // 该函数根据不同的索引值，去显示相应的Fragment
    private void showPageFragment(int pageIndex) {
        setPageStyle(pageIndex);
        // 关于Fragment的事务处理就要使用FragmentTransaction
        FragmentTransaction ft = fm.beginTransaction();
        switch (pageIndex) {
            case DETAIL_PAGE_INDEX:
            case STATEMENT_PAGE_INDEX:
            case ADD_PAGE_INDEX:
            case WALLET_PAGE_INDEX:
            case MINE_PAGE_INDEX:
                // 如果即将打开的Fragment没有被添加过，则将上一个Fragment隐藏，并将即将要显示的Fragment添加
                // 如果即将打开的Fragment已经被添加过，则将上一个Fragment隐藏，并将即将要显示的Fragment显示
                int nextIndex = pageIndex;
                if (!listFragments[nextIndex].isAdded()) {
                    ft.hide(listFragments[index]).add(R.id.container_layout,
                            listFragments[nextIndex]);
                } else {
                    ft.hide(listFragments[index]).show(listFragments[nextIndex]);
                }
                index = pageIndex;
                break;
            default:
                break;
        }
        // 最后不要忘了将事务提交(提交给activity处理)
        ft.commit();
    }
}
