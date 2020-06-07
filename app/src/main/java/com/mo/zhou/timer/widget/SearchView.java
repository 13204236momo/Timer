package com.mo.zhou.timer.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.mo.zhou.commom.utils.Helper;
import com.mo.zhou.timer.R;
import com.mo.zhou.timer.event.AnimationEvent;

import org.greenrobot.eventbus.EventBus;

public class SearchView extends LinearLayout {


    private LinearLayout llContent;
    private LinearLayout llSearch;
    private EditText etSearch;
    private TextView tvCancel;
    private View shadow;

    //tvCancel是否显示
    private boolean isShow = false;
    private int llSearchDefaultWidth = 0;

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.search_view, this);
        llContent = findViewById(R.id.cl_search);
        llSearch = findViewById(R.id.ll_search);
        etSearch = findViewById(R.id.et_search);
        tvCancel = findViewById(R.id.tv_cancel);
        shadow = findViewById(R.id.shadow);

        etSearch.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!isShow)
                    showAnimation();
                llContent.setBackgroundColor(Color.WHITE);
                return false;
            }
        });
        tvCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShow)
                    hideAnimation();

                Helper.hideKeyboard(SearchView.this);
            }
        });

    }

    private void showAnimation() {
        isShow = true;
        llSearchDefaultWidth = llSearch.getWidth();
        ValueAnimator valueAnimator = ValueAnimator.ofInt(llSearch.getWidth(), llSearch.getWidth() - Helper.dip2px(70)).setDuration(200);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                llSearch.getLayoutParams().width = (int) animation.getAnimatedValue();
                llSearch.requestLayout();
            }

        });
        shadow.setVisibility(VISIBLE);
        valueAnimator.start();
        EventBus.getDefault().post(new AnimationEvent(AnimationEvent.STATE_SHOW));

    }

    private void hideAnimation() {

        isShow = false;
        ValueAnimator valueAnimator = ValueAnimator.ofInt(llSearch.getWidth(), llSearchDefaultWidth).setDuration(200);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                llSearch.getLayoutParams().width = (int) animation.getAnimatedValue();
                llSearch.requestLayout();
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                llContent.setBackgroundColor(Color.TRANSPARENT);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        shadow.setVisibility(GONE);
        valueAnimator.start();
        EventBus.getDefault().post(new AnimationEvent(AnimationEvent.STATE_HIDE));
    }

}
