package com.mo.zhou.commom.base;

import android.os.Bundle;

import com.mo.zhou.commom.base.mvp.BaseModel;
import com.mo.zhou.commom.base.mvp.BasePresenter;
import com.mo.zhou.commom.base.mvp.BaseView;
import com.mo.zhou.commom.base.mvp.TUtil;


public abstract class BaseMvpActivity<T extends BasePresenter, E extends BaseModel> extends BaseActivity {
    public T mPresenter;
    public E mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getSupportActionBar().hide();
        init();
    }

    protected void init() {
        contentView(getLayoutResID());
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (this instanceof BaseView) mPresenter.setVM(this, mModel);
        initView();

    }

    /**
     * 获得Layout文件id
     *
     * @return
     */
    protected abstract int getLayoutResID();


    protected abstract void initView();

}

