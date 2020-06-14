package com.mo.zhou.timer.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.opengl.Visibility;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.mo.zhou.timer.R;

public class EditTopBar extends ConstraintLayout {

    public static final int INDEX_BACK = 0;
    public static final int INDEX_FORWARD = 1;
    public static final int INDEX_BACKWARD = 2;
    public static final int INDEX_SAVE = 3;
    public static final int INDEX_SHARE = 4;
    public static final int INDEX_THEME = 5;



    private ImageView ivBack,ivForward,ivBackward,ivSave,ivShare,ivTheme,ivSet;

    public EditTopBar(Context context) {
        this(context, null);
    }

    public EditTopBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EditTopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View.inflate(context, R.layout.edit_top_bar, this);
        ivBack = findViewById(R.id.iv_back);
        ivForward = findViewById(R.id.iv_forward);
        ivBackward = findViewById(R.id.iv_backward);
        ivSave = findViewById(R.id.iv_save);
        ivShare = findViewById(R.id.iv_share);
        ivTheme = findViewById(R.id.iv_theme);
        ivSet = findViewById(R.id.iv_set);

        initEvent();

    }

    private void initEvent(){
        ivBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onClick(0);
                }
            }
        });

        ivForward.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onClick(1);
                }
            }
        });

        ivBackward.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onClick(2);
                }
            }
        });

        ivSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onClick(3);
                }
            }
        });

        ivShare.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onClick(4);
                }
            }
        });

        ivTheme.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onClick(5);
                }
            }
        });
    }


     private OnEditTopBarClickedListener listener;
      public interface OnEditTopBarClickedListener{
        void onClick(int state);
      }

      public void setOnEditTopBarClickedListener(OnEditTopBarClickedListener listener){
          if (listener !=null){
              this.listener = listener;
          }

      }

      public void showEdit(){
          ivForward.setVisibility(VISIBLE);
          ivBackward.setVisibility(VISIBLE);
          //ivSave.setVisibility(VISIBLE);

          ivShare.setVisibility(GONE);
          ivTheme.setVisibility(GONE);
          ivSet.setVisibility(GONE);
      }

      public void showSet(){
          ivForward.setVisibility(GONE);
          ivBackward.setVisibility(GONE);
          ivSave.setVisibility(GONE);

          ivShare.setVisibility(VISIBLE);
          ivTheme.setVisibility(VISIBLE);
          ivSet.setVisibility(VISIBLE);
      }

      public void setForwardTint(int color){
          ivForward.setColorFilter(getContext().getResources().getColor(color));
      }

    public void setBackwardTint(int color){
        ivBackward.setColorFilter(getContext().getResources().getColor(color));
    }

    public void setSaveVisible(int state){
        ivSave.setVisibility(state);
    }
}
