package com.mo.zhou.timer.note;

import com.mo.zhou.commom.base.mvp.BaseModel;
import com.mo.zhou.commom.base.mvp.BasePresenter;
import com.mo.zhou.commom.base.mvp.BaseView;

import java.util.List;

public class EditNoteContract {

    interface View extends BaseView {
      
    }

    interface Model extends BaseModel {
        
    }

    abstract static class Presenter extends BasePresenter<Model, View> {

        @Override
        public void onStart() {

        }

        //public abstract void showState(List<String> notes);
        
    }
}