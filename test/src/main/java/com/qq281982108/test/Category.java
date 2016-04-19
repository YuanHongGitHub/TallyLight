package com.qq281982108.test;

import android.widget.Adapter;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-04-19 17:04
 * 类名：Category
 * 修改备注：
 */
public class Category {
    private String mTitle;

    private Adapter mAdapter;

    public Category(String title, Adapter adapter) {
        mTitle = title;
        mAdapter = adapter;
    }

    public void setTile(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public Adapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(Adapter adapter) {
        mAdapter = adapter;
    }

}
