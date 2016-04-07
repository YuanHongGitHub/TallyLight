package com.qq281982108.sqlitedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Expend expend = new Expend();
    int i = 0;
    private ListView list;
    private CommonAdapter<Expend> adapter;
    private List<Expend> expends = DataSupport.findAll(Expend.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.list);

        expend.setMoney("123");
        expend.setExpendCategory("晚餐");
        expend.setMoneyCategory("现金");
        expend.setRemark(null);
        expend.setTime("00000000");
        expend.setUser("自己");
        expend.setMerchant(null);
        expend.setRefund(false);
        expend.save();
//        data.add(new String(
//                   expend.getMoney()
//                +expend.getExpendCategory()
//                +expend.getMoneyCategory()
//                +expend.getRemark()
//                +expend.getTime()
//                +expend.getUser()));
//        for (i = 0; i < 5; i++) {
//            data.add(new String("" + (i + 1)));
//        }
        list.setAdapter(adapter = new CommonAdapter<Expend>(
                getApplicationContext(), expends, R.layout.main_item) {
            @Override
            public void convert(ViewHolder c, Expend expend) {
                TextView view = c.getView(R.id.id_tv_title);
                view.setText(
                        expend.getMoney()
                                + expend.getExpendCategory()
                                + expend.getMoneyCategory()
                                + expend.getTime()
                                + expend.getUser());
            }

        });
    }

    public void submit(View view) {
//        Expend expend = new Expend();
        expend.setMoney("123");
        expend.setExpendCategory("晚餐");
        expend.setMoneyCategory("现金");
        expend.setRemark(null);
        expend.setTime("00000000");
        expend.setUser("自己");
        expend.save();
        expends.add(expend);
        adapter.notifyDataSetChanged();
    }
}
