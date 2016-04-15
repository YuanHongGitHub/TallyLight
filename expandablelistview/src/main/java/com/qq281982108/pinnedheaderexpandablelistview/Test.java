package com.qq281982108.pinnedheaderexpandablelistview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-04-15 13:35
 * 类名：Test
 * 修改备注：
 */
public class Test extends Activity {
    String[] strs = new String[]{
            "first", "second", "third", "fourth", "fifth"
    };
    String[][] strs2 = new String[][]{{"1"}, {"2"}, {"3"}, {"4"}, {"5"}
    };
    private ListView mListView, mListView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        mListView = (ListView) findViewById(R.id.list_view0);
        mListView1 = (ListView) findViewById(R.id.list_view1);
        mListView.setAdapter(new MyAdapter(this, strs, strs2));

    }
}
