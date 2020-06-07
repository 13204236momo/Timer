package com.mo.zhou.timer.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.mo.zhou.timer.greendao.entity.Note;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "NOTE".
*/
public class NoteDao extends AbstractDao<Note, Long> {

    public static final String TABLENAME = "NOTE";

    /**
     * Properties of entity Note.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Text = new Property(1, String.class, "text", false, "TEXT");
        public final static Property Create_time = new Property(2, Long.class, "create_time", false, "CREATE_TIME");
        public final static Property Theme_id = new Property(3, int.class, "theme_id", false, "THEME_ID");
        public final static Property Group = new Property(4, String.class, "group", false, "GROUP");
    }


    public NoteDao(DaoConfig config) {
        super(config);
    }
    
    public NoteDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NOTE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"TEXT\" TEXT NOT NULL ," + // 1: text
                "\"CREATE_TIME\" INTEGER," + // 2: create_time
                "\"THEME_ID\" INTEGER NOT NULL ," + // 3: theme_id
                "\"GROUP\" TEXT);"); // 4: group
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NOTE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Note entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getText());
 
        Long create_time = entity.getCreate_time();
        if (create_time != null) {
            stmt.bindLong(3, create_time);
        }
        stmt.bindLong(4, entity.getTheme_id());
 
        String group = entity.getGroup();
        if (group != null) {
            stmt.bindString(5, group);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Note entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getText());
 
        Long create_time = entity.getCreate_time();
        if (create_time != null) {
            stmt.bindLong(3, create_time);
        }
        stmt.bindLong(4, entity.getTheme_id());
 
        String group = entity.getGroup();
        if (group != null) {
            stmt.bindString(5, group);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Note readEntity(Cursor cursor, int offset) {
        Note entity = new Note( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // text
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // create_time
            cursor.getInt(offset + 3), // theme_id
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // group
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Note entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setText(cursor.getString(offset + 1));
        entity.setCreate_time(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setTheme_id(cursor.getInt(offset + 3));
        entity.setGroup(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Note entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Note entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Note entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
