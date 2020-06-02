package com.mo.zhou.commom.base.recyclerAdapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

/**
 * Created by DN on 2018/03/12.
 */

public abstract class MyBaseMultiItemQuickAdapter<T extends MultiItemEntity, K extends BaseViewHolder> extends BaseMultiItemQuickAdapter<T, K> {
    public MyBaseMultiItemQuickAdapter(List<T> data) {
        super(data);
    }
}
