package com.mo.zhou.timer.entity;

public class EditHistoryEntity {

    /**
     * 增加
     */
    public static final int TYPE_ADD = 0;
    /**
     * 删去
     */
    public static final int TYPE_DELETE = 1;

    /**
     * 当前记录位置
     */
    private int currentIndex;

    /**
     * 增删的内容
     */
    private CharSequence content;

    /**
     * 光标的位置，增删发生的位置
     */
    private int charIndex;

    /**
     * 增删类型
     */
    private int type;

    public EditHistoryEntity(int currentIndex, CharSequence content, int charIndex, int type) {
        this.currentIndex = currentIndex;
        this.content = content;
        this.charIndex = charIndex;
        this.type = type;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public CharSequence getContent() {
        return content;
    }

    public void setContent(CharSequence content) {
        this.content = content;
    }

    public int getCharIndex() {
        return charIndex;
    }

    public void setCharIndex(int charIndex) {
        this.charIndex = charIndex;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
