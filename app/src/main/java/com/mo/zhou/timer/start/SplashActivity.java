package com.mo.zhou.timer.start;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mo.zhou.commom.base.BaseActivity;
import com.mo.zhou.timer.MainActivity;
import com.mo.zhou.timer.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.tv_1)
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView(R.layout.ac_splash);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_1)
    void onClick(View view){
        switch (view.getId()){
            case R.id.tv_1:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }

}
