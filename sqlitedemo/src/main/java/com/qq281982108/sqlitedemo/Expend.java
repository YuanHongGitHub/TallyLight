package com.qq281982108.sqlitedemo;

import org.litepal.crud.DataSupport;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-04-06 11:13
 * 类名：Expend
 * 修改备注：支出项
 */
public class Expend extends DataSupport {
    private int id;
    private String money;
    private String expendCategory;
    private String moneyCategory;
    private String remark;
    private String time;
    private int year;
    private int month;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    private String user;

    private String merchant;
    private boolean refund;

    public boolean isRefund() {
        return refund;
    }

    public void setRefund(boolean refund) {
        this.refund = refund;
    }

    public String getMoneyCategory() {
        return moneyCategory;
    }

    public void setMoneyCategory(String moneyCategory) {
        this.moneyCategory = moneyCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getExpendCategory() {
        return expendCategory;
    }

    public void setExpendCategory(String expendCategory) {
        this.expendCategory = expendCategory;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

}
