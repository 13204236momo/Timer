package com.mo.zhou.timer.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Note {

    @Id(autoincrement = true)
    private Long id;

    /**
     * note内容
     */
    @NotNull
    private String text;

    /**
     * 创建时间（时间戳）
     */
    private Long create_time;

    /**
     * 主题Id
     */
    private int theme_id;

    /**
     * 所属分组
     */
    private String group;

    @Generated(hash = 1784522037)
    public Note(Long id, @NotNull String text, Long create_time, int theme_id,
            String group) {
        this.id = id;
        this.text = text;
        this.create_time = create_time;
        this.theme_id = theme_id;
        this.group = group;
    }

    @Generated(hash = 1272611929)
    public Note() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getCreate_time() {
        return this.create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

    public int getTheme_id() {
        return this.theme_id;
    }

    public void setTheme_id(int theme_id) {
        this.theme_id = theme_id;
    }

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
