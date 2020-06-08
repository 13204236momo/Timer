package com.mo.zhou.timer.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.mo.zhou.timer.R;

public class EditTopBar extends ConstraintLayout {
    public EditTopBar(Context context) {
        this(context,null);
    }

    public EditTopBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public EditTopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View.inflate(context, R.layout.edit_top_bar,this);
    }
}
