package com.zhujohnle.mobidevos.framework.db.table;

import android.database.Cursor;

import com.zhujohnle.mobidevos.exception.DbException;
import com.zhujohnle.mobidevos.framework.db.annotation.Finder;
import com.zhujohnle.mobidevos.framework.db.sqlite.ColumnDbType;
import com.zhujohnle.mobidevos.framework.db.sqlite.FinderLazyLoader;
import com.zhujohnle.mobidevos.utils.LogUtils;

import java.lang.reflect.Field;
import java.util.List;

public class TableFinder extends TableColumn {

    private final String valueColumnName;
    private final String targetColumnName;

    /* package */ TableFinder(Class<?> entityType, Field field) {
        super(entityType, field);

        Finder finder =
                field.getAnnotation(Finder.class);
        this.valueColumnName = finder.valueColumn();
        this.targetColumnName = finder.targetColumn();
    }

    public Class<?> getTargetEntityType() {
        return ColumnUtils.getFinderTargetEntityType(this);
    }

    public String getTargetColumnName() {
        return targetColumnName;
    }

    @Override
    public void setValue2Entity(Object entity, Cursor cursor, int index) {
        Object value = null;
        Class<?> columnType = columnField.getType();
        Object finderValue = TableUtils.getColumnOrId(entity.getClass(), this.valueColumnName).getColumnValue(entity);
        if (columnType.equals(FinderLazyLoader.class)) {
            value = new FinderLazyLoader(this, finderValue);
        } else if (columnType.equals(List.class)) {
            try {
                value = new FinderLazyLoader(this, finderValue).getAllFromDb();
            } catch (DbException e) {
                LogUtils.e(e.getMessage(), e);
            }
        } else {
            try {
                value = new FinderLazyLoader(this, finderValue).getFirstFromDb();
            } catch (DbException e) {
                LogUtils.e(e.getMessage(), e);
            }
        }

        if (setMethod != null) {
            try {
                setMethod.invoke(entity, value);
            } catch (Throwable e) {
                LogUtils.e(e.getMessage(), e);
            }
        } else {
            try {
                this.columnField.setAccessible(true);
                this.columnField.set(entity, value);
            } catch (Throwable e) {
                LogUtils.e(e.getMessage(), e);
            }
        }
    }

    @Override
    public Object getColumnValue(Object entity) {
        return null;
    }

    @Override
    public Object getDefaultValue() {
        return null;
    }

    @Override
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.TEXT;
    }
}
