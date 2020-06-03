package com.mo.zhou.timer.note;

import com.mo.zhou.commom.base.mvp.BaseModel;
import com.mo.zhou.commom.base.mvp.BasePresenter;
import com.mo.zhou.commom.base.mvp.BaseView;

import java.util.List;

public class NotesContract {

    interface View extends BaseView {
      void showNotes(List<String> notes);
      void showMsg(String msg);
    }

    interface Model extends BaseModel {

        List<String> getNotes();
    }

    abstract static class Presenter extends BasePresenter<Model, View> {

        @Override
        public void onStart() {

        }

        public abstract void showState(List<String> notes);
    }
}