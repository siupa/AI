package com.aisim.dal.sqllite;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteStatement;

/**
 * ai
 * Created by Krzysztof on 9/19/2015.
 */
public interface SQLiteDaoMapper<T> {
    T fromDb(SQLiteStatement st) throws SQLiteException;
    SQLiteStatement toDb(SQLiteConnection conn, T object) throws SQLiteException;
}
