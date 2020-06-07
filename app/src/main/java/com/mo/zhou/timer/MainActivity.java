package com.mo.zhou.timer;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.mo.zhou.commom.base.BaseActivity;
import com.mo.zhou.commom.utils.Helper;
import com.mo.zhou.timer.event.AnimationEvent;
import com.mo.zhou.timer.note.NotesFragment;
import com.mo.zhou.timer.widget.TopBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.vp_main)
    ViewPager vpMain;

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mFragments.add(new NotesFragment());
        mFragments.add(new NotesFragment());
        vpMain.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        vpMain.setCurrentItem(0);

        initEvent();

    }

    private void initEvent() {
        vpMain.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //tlMain.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        topBar.setOnSelectedListener(new TopBar.OnClickedListener() {
            @Override
            public void onClick(int state) {
                switch (state) {
                    case TopBar.STATE_LEFT:
                        vpMain.setCurrentItem(0);
                        break;
                    case TopBar.STATE_RIGHT:
                        vpMain.setCurrentItem(1);
                        break;
                    case TopBar.STATE_SHOW:
                        //Toast.makeText(MainActivity.this, "show", Toast.LENGTH_SHORT).show();
                        Helper.showToast("show");
                        break;
                    case TopBar.STATE_HIDE:
                        //Toast.makeText(MainActivity.this, "hide",Toast.LENGTH_LONG).show();
                        Helper.showToast("hide");
                        break;
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void animation(AnimationEvent data) {
        switch (data.state) {
            case AnimationEvent.STATE_SHOW:
                showAnimation();
                break;
            case AnimationEvent.STATE_HIDE:
                hideAnimation();
                break;
        }
    }

    private void showAnimation(){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(topBar.getHeight(), 0).setDuration(200);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) vpMain.getLayoutParams();
                layoutParams.setMargins(0,(int) animation.getAnimatedValue(),0,0);
            }
        });

        valueAnimator.start();
    }

    private void hideAnimation(){

        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,topBar.getHeight()).setDuration(200);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) vpMain.getLayoutParams();
                layoutParams.setMargins(0,(int) animation.getAnimatedValue(),0,0);
            }
        });

        valueAnimator.start();
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

}
