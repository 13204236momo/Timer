package com.mo.zhou.timer.note;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mo.zhou.commom.base.BaseMvpActivity;
import com.mo.zhou.commom.utils.Helper;
import com.mo.zhou.commom.utils.SoftKeyBoardListener;
import com.mo.zhou.timer.R;
import com.mo.zhou.timer.entity.EditHistoryEntity;
import com.mo.zhou.timer.widget.EditBottomBar;
import com.mo.zhou.timer.widget.EditTopBar;
import com.mo.zhou.timer.widget.MoreResourceEditText;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.top_bar_edit)
    EditTopBar editTopBar;

    private int editTextHeight;
    //编辑历史
    private List<EditHistoryEntity> editHistory = new ArrayList<>();

    private int currentIndex = -1;


    private TextWatcher textWatcher;
    String buffer;

    @Override
    protected int getLayoutResID() {
        return R.layout.edit_note_activity;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        initEvent();
        editHistory.add(new EditHistoryEntity(0, "", 0, EditHistoryEntity.TYPE_ADD));
    }

    private void initEvent() {
        SoftKeyBoardListener.setListener(this, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                editTopBar.showEdit();
                setColor();
            }

            @Override
            public void keyBoardHide(int height) {
                editTopBar.showSet();
                etContent.setFocusable(false);
                editHistory.clear();
                editHistory.add(new EditHistoryEntity(0, "", 0, EditHistoryEntity.TYPE_ADD));
                currentIndex = -1;
                setColor();
                editTopBar.setSaveVisible(View.GONE);
            }
        });


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


        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editHistory.add(new EditHistoryEntity(editHistory.size(), s.subSequence(start, start + count), start, EditHistoryEntity.TYPE_ADD));
                currentIndex = editHistory.size() - 1;
                setColor();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        etContent.addTextChangedListener(textWatcher);


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

        slContent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                etContent.setFocusableInTouchMode(true);//可以通过触摸得到焦点
                etContent.setEnabled(true);
                etContent.setFocusable(true);//可以通过键盘得到焦点
                Helper.showKeyboard(etContent);
                return false;
            }
        });

        editTopBar.setOnEditTopBarClickedListener(new EditTopBar.OnEditTopBarClickedListener() {
            @Override
            public void onClick(int state) {
                switch (state) {
                    case EditTopBar.INDEX_BACK:
                        finish();
                        break;
                    case EditTopBar.INDEX_BACKWARD:
                        if (currentIndex >= 1) {
                            etContent.removeTextChangedListener(textWatcher);
                            EditHistoryEntity entity = editHistory.get(currentIndex);
                            if (entity.getType() == EditHistoryEntity.TYPE_ADD) {
                                etContent.getText().delete(entity.getCharIndex(), entity.getCharIndex() + entity.getContent().length());
                                etContent.setSelection(entity.getCharIndex());
                            } else if (entity.getType() == EditHistoryEntity.TYPE_DELETE) {
                                etContent.getText().insert(entity.getCharIndex(), entity.getContent());
                                etContent.setSelection(entity.getCharIndex() + 1);
                            }
                            etContent.addTextChangedListener(textWatcher);
                            currentIndex--;

                        } else {

                        }
                        setColor();

                        break;
                    case EditTopBar.INDEX_FORWARD:
                        if (currentIndex < editHistory.size() - 1) {
                            currentIndex++;
                            etContent.removeTextChangedListener(textWatcher);
                            EditHistoryEntity entity = editHistory.get(currentIndex);
                            if (entity.getType() == EditHistoryEntity.TYPE_ADD) {
                                etContent.getText().insert(entity.getCharIndex(), entity.getContent());
                                etContent.setSelection(entity.getCharIndex() + entity.getContent().length());
                            } else if (entity.getType() == EditHistoryEntity.TYPE_DELETE) {
                                etContent.getText().delete(entity.getCharIndex(), entity.getCharIndex() + entity.getContent().length());
                                etContent.setSelection(entity.getCharIndex() + entity.getContent().length()-1);
                            }

                            etContent.addTextChangedListener(textWatcher);
                        } else {
                        }
                        setColor();

                        break;
                    case EditTopBar.INDEX_SAVE:
                        break;
                    case EditTopBar.INDEX_SHARE:
                        break;
                    case EditTopBar.INDEX_THEME:
                        break;
                    default:
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

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        etContent.removeTextChangedListener(textWatcher);
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DEL) {
            //光标位置
            int position = etContent.getSelectionStart();
            CharSequence content = etContent.getText();
            editHistory.add(new EditHistoryEntity(editHistory.size(), String.valueOf(content.charAt(position - 1)), position - 1, EditHistoryEntity.TYPE_DELETE));
            currentIndex = editHistory.size() - 1;
        } else if (event.getAction() == KeyEvent.ACTION_UP && event.getKeyCode() == KeyEvent.KEYCODE_DEL) {
            etContent.addTextChangedListener(textWatcher);
            setColor();
        }
        return super.dispatchKeyEvent(event);
    }


    private void setColor() {
        if (currentIndex == -1) {
            editTopBar.setForwardTint(R.color.default_gray);
            editTopBar.setBackwardTint(R.color.default_gray);
        } else if (currentIndex == 0) {
            editTopBar.setForwardTint(R.color.colorPrimary);
            editTopBar.setBackwardTint(R.color.default_gray);
        } else if (currentIndex == editHistory.size() - 1) {
            editTopBar.setForwardTint(R.color.default_gray);
            editTopBar.setBackwardTint(R.color.colorPrimary);
        } else {
            editTopBar.setForwardTint(R.color.colorPrimary);
            editTopBar.setBackwardTint(R.color.colorPrimary);
        }

        //Log.e("zhou",etContent.getText());
        if (etContent.getText().toString().equals("")) {
            editTopBar.setSaveVisible(View.GONE);
        } else {
            editTopBar.setSaveVisible(View.VISIBLE);
        }
    }
}
