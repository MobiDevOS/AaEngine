/*
 * Copyright (c) 2013. wyouflf (wyouflf@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zhujohnle.mobidevos.framework.db.table;

import android.database.Cursor;

import com.zhujohnle.mobidevos.exception.DbException;
import com.zhujohnle.mobidevos.framework.core.log.FLog;
import com.zhujohnle.mobidevos.framework.db.converter.ColumnConverter;
import com.zhujohnle.mobidevos.framework.db.converter.ColumnConverterFactory;
import com.zhujohnle.mobidevos.framework.db.sqlite.ColumnDbType;
import com.zhujohnle.mobidevos.framework.db.sqlite.ForeignLazyLoader;

import java.lang.reflect.Field;
import java.util.List;

public class TableForeign extends TableColumn {

    private final String foreignColumnName;
    private final ColumnConverter foreignColumnConverter;

    /* package */ TableForeign(Class<?> entityType, Field field) {
        super(entityType, field);

        foreignColumnName = ColumnUtils.getForeignColumnNameByField(field);
        Class<?> foreignColumnType =
                TableUtils.getColumnOrId(getForeignEntityType(), foreignColumnName).columnField.getType();
        foreignColumnConverter = ColumnConverterFactory.getColumnConverter(foreignColumnType);
    }

    public String getForeignColumnName() {
        return foreignColumnName;
    }

    public Class<?> getForeignEntityType() {
        return ColumnUtils.getForeignEntityType(this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setValue2Entity(Object entity, Cursor cursor, int index) {
        Object fieldValue = foreignColumnConverter.getFieldValue(cursor, index);
        if (fieldValue == null) return;

        Object value = null;
        Class<?> columnType = columnField.getType();
        if (columnType.equals(ForeignLazyLoader.class)) {
            value = new ForeignLazyLoader(this, fieldValue);
        } else if (columnType.equals(List.class)) {
            try {
                value = new ForeignLazyLoader(this, fieldValue).getAllFromDb();
            } catch (DbException e) {
                FLog.e(e.getMessage(), e);
            }
        } else {
            try {
                value = new ForeignLazyLoader(this, fieldValue).getFirstFromDb();
            } catch (DbException e) {
                FLog.e(e.getMessage(), e);
            }
        }

        if (setMethod != null) {
            try {
                setMethod.invoke(entity, value);
            } catch (Throwable e) {
                FLog.e(e.getMessage(), e);
            }
        } else {
            try {
                this.columnField.setAccessible(true);
                this.columnField.set(entity, value);
            } catch (Throwable e) {
                FLog.e(e.getMessage(), e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object getColumnValue(Object entity) {
        Object fieldValue = getFieldValue(entity);
        Object columnValue = null;

        if (fieldValue != null) {
            Class<?> columnType = columnField.getType();
            if (columnType.equals(ForeignLazyLoader.class)) {
                columnValue = ((ForeignLazyLoader) fieldValue).getColumnValue();
            } else if (columnType.equals(List.class)) {
                try {
                    List<?> foreignEntities = (List<?>) fieldValue;
                    if (foreignEntities.size() > 0) {

                        Class<?> foreignEntityType = ColumnUtils.getForeignEntityType(this);
                        TableColumn column = TableUtils.getColumnOrId(foreignEntityType, foreignColumnName);
                        columnValue = column.getColumnValue(foreignEntities.get(0));

                        // 仅自动关联外键
                        Table table = this.getTable();
                        if (table != null && column instanceof TableId) {
                            for (Object foreignObj : foreignEntities) {
                                Object idValue = column.getColumnValue(foreignObj);
                                if (idValue == null) {
                                    table.db.saveOrUpdate(foreignObj);
                                }
                            }
                        }

                        columnValue = column.getColumnValue(foreignEntities.get(0));
                    }
                } catch (Throwable e) {
                    FLog.e(e.getMessage(), e);
                }
            } else {
                try {
                    TableColumn column = TableUtils.getColumnOrId(columnType, foreignColumnName);
                    columnValue = column.getColumnValue(fieldValue);

                    Table table = this.getTable();
                    if (table != null && columnValue == null && column instanceof TableId) {
                        table.db.saveOrUpdate(fieldValue);
                    }

                    columnValue = column.getColumnValue(fieldValue);
                } catch (Throwable e) {
                    FLog.e(e.getMessage(), e);
                }
            }
        }

        return columnValue;
    }

    @Override
    public ColumnDbType getColumnDbType() {
        return foreignColumnConverter.getColumnDbType();
    }

    /**
     * It always return null.
     *
     * @return null
     */
    @Override
    public Object getDefaultValue() {
        return null;
    }
}