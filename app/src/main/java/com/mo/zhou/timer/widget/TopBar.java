package com.mo.zhou.timer.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.mo.zhou.timer.R;

public class TopBar extends LinearLayout {

    private TextView tvLeft;
    private TextView tvRight;
    private ImageView ivSpread;
    private OnSelectedListener listener;


    public TopBar(Context context) {
        this(context,null);
    }

    public TopBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        initEvent();
    }

    private void init(Context context){
        View.inflate(context, R.layout.top_bar, this);
        tvLeft = findViewById(R.id.tv_left);
        tvRight = findViewById(R.id.tv_right);
        ivSpread = findViewById(R.id.iv_spread);
    }

    private void initEvent(){
        tvLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onLeft();
                }
            }
        });

        tvRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onRight();
                }
            }
        });
    }



    public interface OnSelectedListener{
        void onLeft();
        void onRight();
    }

    public void setOnSelectedListener(OnSelectedListener listener){
        this.listener = listener;
    }

}
