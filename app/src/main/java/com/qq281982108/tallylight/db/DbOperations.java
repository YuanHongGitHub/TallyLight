package com.qq281982108.tallylight.db;

import android.util.Log;

import com.qq281982108.tallylight.model.Account;
import com.qq281982108.tallylight.model.Spending;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-04-12 16:27
 * 类名：DbOperations
 * 修改备注：
 */
public class DbOperations {
    private List<String> groupList;
    private List<List<Spending>> childList;
    private List<Account> mAccountList;
    private List<String> categoryList;

    public List<String> initGroupList() {
        groupList = new ArrayList<String>();
        List<Spending> yearMonthList = DataSupport.select("recorderYearMonth").find(Spending.class);
        List<String> yearMonthList0 = new ArrayList<String>();
        for (Spending aYearMonthList : yearMonthList) {
            yearMonthList0.add(aYearMonthList.getRecorderYearMonth());
        }
        HashSet<String> hashSet = new HashSet<String>(yearMonthList0);
        groupList.addAll(hashSet);
        Collections.sort(groupList, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return rhs.compareTo(lhs);
            }
        });
        return groupList;
    }

    public List<List<Spending>> initChildList() {
        childList = new ArrayList<List<Spending>>();
        for (int i = 0; i < groupList.size(); i++) {
            List<Spending> spendingI = DataSupport.where("recorderYearMonth = ?", groupList.get(i)).find(Spending.class);
            Log.e("yh", "spendingsize" + spendingI.size());
            ArrayList<Spending> childTemp = new ArrayList<Spending>();
            for (int j = 0; j < spendingI.size(); j++) {
                childTemp.add(spendingI.get(j));
                Log.e("yh", "getRecorderTime" + j + "-->" + spendingI.get(j).getRecorderTime());
            }
            Collections.sort(childTemp, new Comparator<Spending>() {
                @Override
                public int compare(Spending lhs, Spending rhs) {
                    return rhs.getRecorderTime().compareTo(lhs.getRecorderTime());
                }
            });
            childList.add(childTemp);
        }
        return childList;
    }

    public List<String> initAccountCategory() {
        categoryList = new ArrayList<String>();
        List<Account> accountCategoryList = DataSupport.select("accountCategory").find(Account.class);
        List<String> accountCategoryList0 = new ArrayList<String>();
        for (Account aYearMonthList : accountCategoryList) {
            accountCategoryList0.add(aYearMonthList.getAccountCategory());
        }
        HashSet<String> hashSet = new HashSet<String>(accountCategoryList0);
        categoryList.addAll(hashSet);
        Collections.sort(categoryList, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return rhs.compareTo(lhs);
            }
        });
        return categoryList;
    }

    public List<Account> initAccountList(String accountCategory) {
        mAccountList = new ArrayList<Account>();
        List<Account> accounts = DataSupport.where("accountCategory = ?", accountCategory).find(Account.class);
        if (accounts.size() == 0) {
            Account account = new Account();
            account.setAccountCategory("现金");
            account.setAccountName("现金");
            account.setMoney("0.00");
            account.save();
        }
        for (int i = 0; i < accounts.size(); i++) {
            mAccountList.add(accounts.get(i));
        }

        return mAccountList;
    }
}
