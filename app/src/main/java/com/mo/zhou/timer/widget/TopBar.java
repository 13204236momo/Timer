package com.mo.zhou.timer.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mo.zhou.timer.R;

public class TopBar extends LinearLayout {

    public static final int STATE_LEFT = 0;
    public static final int STATE_RIGHT = 1;
    public static final int STATE_SHOW = 2;
    public static final int STATE_HIDE = 3;

    private TextView tvLeft;
    private TextView tvRight;
    private ImageView ivSpread;
    private OnClickedListener listener;

    private boolean isSelectedLeft = true;
    private boolean isShowPopWindow = false;
    private Context mContext;

    public TopBar(Context context) {
        this(context, null);
    }

    public TopBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init(context);
        initEvent();
    }

    private void init(Context context) {
        View.inflate(context, R.layout.top_bar, this);
        tvLeft = findViewById(R.id.tv_left);
        tvRight = findViewById(R.id.tv_right);
        ivSpread = findViewById(R.id.iv_spread);
    }

    private void initEvent() {
        tvLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {

                    tvLeft.setTextColor(getResources().getColor(R.color.main_top_bar_text_selected));
                    tvRight.setTextColor(getResources().getColor(R.color.main_top_bar_text_unSelected));
                    ivSpread.setVisibility(VISIBLE);

                    if (isSelectedLeft) {
                        if (isShowPopWindow) {
                            listener.onClick(STATE_HIDE);
                            isShowPopWindow = false;
                            hideAnimation();
                        } else {
                            isShowPopWindow = true;
                            listener.onClick(STATE_SHOW);
                            showAnimation();
                        }
                    } else {
                        isSelectedLeft = true;
                        listener.onClick(STATE_LEFT);
                    }
                }
            }
        });

        tvRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    if (isShowPopWindow) {
                        isShowPopWindow = false;
                        listener.onClick(STATE_HIDE);
                        hideAnimation();
                    } else {
                        listener.onClick(STATE_RIGHT);
                        tvRight.setTextColor(getResources().getColor(R.color.main_top_bar_text_selected));
                        tvLeft.setTextColor(getResources().getColor(R.color.main_top_bar_text_unSelected));
                        ivSpread.clearAnimation();
                        ivSpread.setVisibility(INVISIBLE);
                        isSelectedLeft = false;
                    }
                }
            }
        });
    }

    private void showAnimation(){
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.rotate_anim_1);
        ivSpread.startAnimation(animation);
    }

    private void hideAnimation(){
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.rotate_anim_2);
        ivSpread.startAnimation(animation);
    }


    public interface OnClickedListener {
        void onClick(int state);
    }

    public void setOnSelectedListener(OnClickedListener listener) {
        this.listener = listener;
    }

}
