package com.mo.zhou.timer.note;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mo.zhou.commom.base.BaseMvpFragment;
import com.mo.zhou.timer.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotesFragment extends BaseMvpFragment<NotesPresenter,NotesModel> implements NotesContract.View {

    @BindView(R.id.tv_5)
    TextView textView;

    @Override
    protected int getLayoutResID() {
        return R.layout.notes_fragment;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this,view);
    }

    @Override
    public void showNotes(List<String> notes) {

    }

    @Override
    public void showMsg(String msg) {

    }

    @OnClick({R.id.tv_5})
    void onClick(View view){
        switch (view.getId()){
            case R.id.tv_5:
                startActivity(new Intent(getActivity(),EditNoteActivity.class));
                break;
        }
    }
}
