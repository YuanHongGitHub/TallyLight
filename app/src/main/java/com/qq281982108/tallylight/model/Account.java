package com.qq281982108.tallylight.model;

import org.litepal.crud.DataSupport;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-04-19 14:47
 * 类名：Account
 * 修改备注：
 */
public class Account extends DataSupport {
    private int id;
    private String accountCategory;
    private String accountName;
    private String money;
    private String remarks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountCategory() {
        return accountCategory;
    }

    public void setAccountCategory(String accountCategory) {
        this.accountCategory = accountCategory;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
