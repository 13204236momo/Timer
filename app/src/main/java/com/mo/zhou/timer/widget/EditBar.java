package com.mo.zhou.timer.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.mo.zhou.commom.utils.Helper;
import com.mo.zhou.timer.R;

public class EditBar extends LinearLayout {

    public static final int INDEX_PIC = 0;
    public static final int INDEX_CHECK = 1;
    public static final int INDEX_THEME = 2;
    public static final int INDEX_STYLE = 3;
    public static final int INDEX_AUDIO = 4;


    private ImageView ivPic, ivCheck, ivTheme, ivStyle, ivAudio;
    private OnEditBarClickListener listener;

    public EditBar(Context context) {
        this(context, null);
    }

    public EditBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EditBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.edit_bar, this);
        ivAudio = findViewById(R.id.iv_audio);
        ivPic = findViewById(R.id.iv_pic);
        ivCheck = findViewById(R.id.iv_check);
        ivTheme = findViewById(R.id.iv_theme);
        ivStyle = findViewById(R.id.iv_t_style);

        initEvent();
    }

    private void initEvent() {

        ivPic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(INDEX_PIC);
                }
            }
        });
        ivCheck.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(INDEX_CHECK);
                }
            }
        });
        ivTheme.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(INDEX_THEME);
                }
            }
        });
        ivStyle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(INDEX_STYLE);
                }
            }
        });
        ivAudio.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(INDEX_AUDIO);
                }
            }
        });
    }


    public interface OnEditBarClickListener {
        void onClick(int index);
    }

    public void setEdirBarClickListener(OnEditBarClickListener listener) {
        if (listener != null) {
            this.listener = listener;
        }
    }


}
