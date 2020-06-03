package com.mo.zhou.timer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.mo.zhou.commom.base.BaseActivity;
import com.mo.zhou.timer.note.NotesFragment;
import com.mo.zhou.timer.widget.TopBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.vp_main)
    ViewPager vpMain;


    //private String[] mTitles = {"便签", "待办"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
    }

    private void initView(){
        mFragments.add(new NotesFragment());
        mFragments.add(new NotesFragment());
        vpMain.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        vpMain.setCurrentItem(0);

//        if (mTabEntities.size() == 0) {
//            for (int i = 0; i < mTitles.length; i++) {
//                final int finalI = i;
//                mTabEntities.add(new CustomTabEntity() {
//                    @Override
//                    public String getTabTitle() {
//                        return mTitles[finalI];
//                    }
//
//                    @Override
//                    public int getTabSelectedIcon() {
//                        return 0;
//                    }
//
//                    @Override
//                    public int getTabUnselectedIcon() {
//                        return 0;
//                    }
//                });
//            }
//        }
//
//        tlMain.setTabData(mTabEntities);

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

        topBar.setOnSelectedListener(new TopBar.OnSelectedListener() {
            @Override
            public void onLeft() {
                vpMain.setCurrentItem(0);
            }

            @Override
            public void onRight() {
                vpMain.setCurrentItem(1);
            }
        });


//        tlMain.setOnTabSelectListener(new OnTabSelectListener() {
//            @Override
//            public void onTabSelect(int position) {
//                vpMain.setCurrentItem(position);
//            }
//
//            @Override
//            public void onTabReselect(int position) {
//
//            }
//        });

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
