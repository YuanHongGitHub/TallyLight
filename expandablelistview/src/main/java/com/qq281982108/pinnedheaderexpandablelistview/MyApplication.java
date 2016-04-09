package com.qq281982108.pinnedheaderexpandablelistview;

import org.litepal.LitePalApplication;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-04-06 11:34
 * 类名：MyApplication
 * 修改备注：
 */
public class MyApplication extends LitePalApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePalApplication.initialize(this);
    }
}
