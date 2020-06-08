package com.mo.zhou.timer.note;

import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mo.zhou.commom.base.BaseMvpActivity;
import com.mo.zhou.commom.utils.Helper;
import com.mo.zhou.timer.R;
import com.mo.zhou.timer.widget.EditBottomBar;
import com.mo.zhou.timer.widget.MoreResourceEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditNoteActivity extends BaseMvpActivity<EditNotePresenter, EditNoteModel> implements EditNoteContract.View {


    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.et_content)
    MoreResourceEditText etContent;
    @BindView(R.id.sl_content)
    ScrollView slContent;
    @BindView(R.id.edit_bar)
    EditBottomBar editBar;

    private int editTextHeight;

    @Override
    protected int getLayoutResID() {
        return R.layout.edit_note_activity;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        etContent.setEnabled(true);
        etContent.setFocusable(true);//可以通过键盘得到焦点
        etContent.setFocusableInTouchMode(true);//可以通过触摸得到焦点
        initEvent();
    }

    private void initEvent() {
//        SoftKeyBoardListener.setListener(this, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
//            @Override
//            public void keyBoardShow(int height) {
//                editTextHeight = height;
//                RelativeLayout.LayoutParams layoutParams1 = (RelativeLayout.LayoutParams) slContent.getLayoutParams();
//                layoutParams1.height = height + Helper.dip2px(50);
//                slContent.setLayoutParams(layoutParams1);
//                Helper.showToast(height + "");
//
//            }
//
//            @Override
//            public void keyBoardHide(int height) {
//                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) slContent.getLayoutParams();
//                layoutParams.height = Helper.getScreenHeight();
//                slContent.setLayoutParams(layoutParams);
//
//            }
//        });


        etContent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                //触摸的是EditText并且当前EditText可以滚动则将事件交给EditText处理；否则将事件交由其父类处理
                if ((view.getId() == R.id.et_content && canVerticalScroll(etContent))) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);//告诉父view，我的事件自己处理
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        view.getParent().requestDisallowInterceptTouchEvent(false);//告诉父view，你可以处理了
                    }
                }
                return false;
            }
        });

        slContent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //通知父控件请勿拦截本控件touch事件
                v.getParent().requestDisallowInterceptTouchEvent(true);
                switch (event.getAction()){
                    case MotionEvent.ACTION_UP:
                        //点击整个页面都会让内容框获得焦点，且弹出软键盘
                        etContent.setFocusable(true);
                        etContent.setFocusableInTouchMode(true);
                        etContent.requestFocus();
                        //AddFlagActivity.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                        Helper.showKeyboard(etContent);
                        break;
                }
                return false;
            }
        });

        editBar.setEdirBarClickListener(new EditBottomBar.OnEditBarClickListener() {
            @Override
            public void onClick(int index) {
                switch (index) {
                    case EditBottomBar.INDEX_PIC:
                        break;
                    case EditBottomBar.INDEX_CHECK:
                        break;
                    case EditBottomBar.INDEX_THEME:
                        break;
                    case EditBottomBar.INDEX_STYLE:
                        break;
                    case EditBottomBar.INDEX_AUDIO:
                        break;
                }
            }
        });
    }

    /**
     * EditText竖直方向是否可以滚动
     *
     * @param editText 需要判断的EditText
     * @return true：可以滚动   false：不可以滚动
     */
    private boolean canVerticalScroll(EditText editText) {
        //滚动的距离
        int scrollY = editText.getScrollY();
        //控件内容的总高度
        int scrollRange = editText.getLayout().getHeight();
        //控件内容总高度与实际显示高度的差值
        int scrollDifference = scrollRange - editTextHeight;

        if (scrollDifference == 0) {
            return false;
        }
        return (scrollY > 0) || (scrollY < scrollDifference - 1);
    }


    @OnClick({})
    void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
