package com.mo.zhou.timer.note;

import android.os.Bundle;
import android.view.View;

import com.mo.zhou.commom.base.BaseMvpFragment;
import com.mo.zhou.timer.R;

import java.util.List;

public class NotesFragment extends BaseMvpFragment<NotesPresenter,NotesModel> implements NotesContract.View {
    @Override
    protected int getLayoutResID() {
        return R.layout.notes_fragment;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    public void showNotes(List<String> notes) {

    }

    @Override
    public void showMsg(String msg) {

    }
}
