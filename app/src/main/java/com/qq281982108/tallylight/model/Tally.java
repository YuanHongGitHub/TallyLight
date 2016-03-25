package com.qq281982108.tallylight.model;

import cn.bmob.v3.BmobObject;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-24 16:01
 * 类名：Tally
 * 修改备注：
 */
public class Tally extends BmobObject {
    private String mCategory;
    private long mMoney;
    private long mTime;

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    public long getMoney() {
        return mMoney;
    }

    public void setMoney(long money) {
        mMoney = money;
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }
}
