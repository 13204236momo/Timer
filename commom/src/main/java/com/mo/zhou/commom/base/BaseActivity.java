package com.mo.zhou.commom.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.mo.zhou.commom.R;
import com.mo.zhou.commom.density.Density;
import com.mo.zhou.commom.utils.StatusBarUtil;

public class BaseActivity extends AppCompatActivity {

    protected View contentView;
    private LinearLayout llContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar();
        setContentView(R.layout.activity_base);
        Density.setDensity(getApplication(), this);

        initUI();
    }

    private void initUI(){
        llContent = findBy(R.id.ll_content);
    }


    /**
     * 加入页面内容布局
     *
     * @param layoutId
     */
    protected void contentView(int layoutId) {
        contentView = getLayoutInflater().inflate(layoutId, null);
        if (llContent.getChildCount() > 0) {
            llContent.removeAllViews();
        }
        if (contentView != null) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT);
            llContent.addView(contentView, params);
        }
    }

    private void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isUseFullScreenMode()) {
                StatusBarUtil.transparencyBar(this);
            } else {
                StatusBarUtil.setStatusBarColor(this, getStatusBarColor());
            }

            if (isUseBlackFontWithStatusBar()) {
                StatusBarUtil.setLightStatusBar(this, true, isUseFullScreenMode());
            }
        }
    }

    /**
     * 是否设置成透明状态栏，即就是全屏模式
     */
    protected boolean isUseFullScreenMode() {
        return false;
    }

    /**
     * 更改状态栏颜色，只有非全屏模式下有效
     */
    protected int getStatusBarColor() {
        return R.color.state_bar_bg_color;
    }

    /**
     * 是否改变状态栏文字颜色为黑色，默认为黑色
     */
    protected boolean isUseBlackFontWithStatusBar() {
        return true;
    }

    public void jumpActivity(Class<?> clazz) {
        startActivity(new Intent(this, clazz));
    }


    public <T extends View> T findBy(int resId){
        return ViewUtils.findViewById(this,resId);
    }

    public static final class ViewUtils {

        public static  <T extends View> T findViewById(Activity activity, int resId){
            return (T) activity.findViewById(resId);
        }

        public static <T extends View> T findViewById(View view, int resId){
            return (T) view.findViewById(resId);
        }
    }
}
