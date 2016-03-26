package com.qq281982108.tallylight.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.qq281982108.tallylight.R;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-26 14:19
 * 类名：LoginActivity
 * 修改备注：
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mBackIV;

    public static void actionStart(Context context, String data1, String data2) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("param1", data1);
        intent.putExtra("param2", data2);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        mBackIV = (ImageView) findViewById(R.id.iv_activity_login_back);
        mBackIV.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_activity_login_back:
                LoginActivity.this.finish();
                break;
            default:
                break;
        }
    }
}
