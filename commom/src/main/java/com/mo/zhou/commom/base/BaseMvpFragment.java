package com.mo.zhou.commom.base;


import android.os.Bundle;

import android.view.View;

import androidx.annotation.Nullable;

import com.mo.zhou.commom.base.mvp.BaseModel;
import com.mo.zhou.commom.base.mvp.BasePresenter;
import com.mo.zhou.commom.base.mvp.BaseView;
import com.mo.zhou.commom.base.mvp.TUtil;


/**
 * Created by DN on 2018/03/12.
 */

public abstract class BaseMvpFragment<T extends BasePresenter, E extends BaseModel> extends BaseFragment {
    public T mPresenter;
    public E mModel;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (this instanceof BaseView) mPresenter.setVM(this, mModel);
    }

}