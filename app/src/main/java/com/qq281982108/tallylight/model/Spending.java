package com.qq281982108.tallylight.model;

import org.litepal.crud.DataSupport;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-04-09 9:57
 * 类名：Spending
 * 修改备注：
 */
public class Spending extends DataSupport {
    private int id;
    private String money;
    private String spendingCategory;
    private String account;
    private String spendingRemark;
    private String recorderTime;
    private String recorderYearMonth;
    private String user;
    private String seller;
    private boolean refund;

    public String getRecorderYearMonth() {
        return recorderYearMonth;
    }

    public void setRecorderYearMonth(String recorderYearMonth) {
        this.recorderYearMonth = recorderYearMonth;
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

    public String getSpendingCategory() {
        return spendingCategory;
    }

    public void setSpendingCategory(String spendingCategory) {
        this.spendingCategory = spendingCategory;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getSpendingRemark() {
        return spendingRemark;
    }

    public void setSpendingRemark(String spendingRemark) {
        this.spendingRemark = spendingRemark;
    }

    public String getRecorderTime() {
        return recorderTime;
    }

    public void setRecorderTime(String recorderTime) {
        this.recorderTime = recorderTime;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public boolean isRefund() {
        return refund;
    }

    public void setRefund(boolean refund) {
        this.refund = refund;
    }
}
