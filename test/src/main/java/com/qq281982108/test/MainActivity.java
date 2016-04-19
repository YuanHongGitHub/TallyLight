package com.qq281982108.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String[] mContacts = {"马英才", "张三", "李四", "asd"};
    private String[] mMusic = {"素顔", "庐州月", "半城烟沙"};
    private String[] mEBook = {"拆掉思维里的墙", "淡定力", "人脉决定命脉"};
    private CategoryAdapter mCategoryAdapter = new CategoryAdapter() {
        @Override
        protected View getTitleView(String title, int index, View convertView, ViewGroup parent) {
            TextView titleView;

            if (convertView == null) {
                titleView = (TextView) getLayoutInflater().inflate(R.layout.title, null);
            } else {
                titleView = (TextView) convertView;
            }

            titleView.setText(title);

            return titleView;
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCategoryAdapter.addCategory("人名", new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mContacts));

        mCategoryAdapter.addCategory("音乐", new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mMusic));

        mCategoryAdapter.addCategory("书籍", new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mEBook));

        ListView categoryList = (ListView) findViewById(R.id.categoryList);

        categoryList.setAdapter(mCategoryAdapter);
    }

}
