package com.tsy.tsy.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import cn.tsy.base.uitls.JCLoger;

/**
 * sqlite帮助类
 *
 * @author Jocerly
 */
public class DBSqliteHelper extends SQLiteOpenHelper {

    public final static String DB = "tsy";
    public final static int VERSION = 1;
    public final static String ID = "_id";

    private String table;

    public DBSqliteHelper(Context context, String table) {
        super(context, DB, null, VERSION);
        this.table = table;
    }

    public DBSqliteHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    /**
     * 数据库更新
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + ScanGoodsDao.TABLENAME);
//        db.execSQL("DROP TABLE IF EXISTS " + SearchDao.TABLENAME);
//        db.execSQL("DROP TABLE IF EXISTS " + ClassifyFirstDao.TABLENAME);
//        db.execSQL("DROP TABLE IF EXISTS " + ClassifyDao.TABLENAME);
        onCreate(db);
//        if (oldVersion == 1 && newVersion == 2) {
//            db.execSQL(ProvinceDao.SQL);
//            db.execSQL(CityDao.SQL);
//            db.execSQL(BankCardDao.SQL);
//        }
        if (oldVersion == 1 && newVersion == 2) {
        }
    }

    /**
     * 新增记录
     *
     * @param values
     */
    protected long doAdd(ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        long id = -1;
        try {
            id = db.insert(table, ID, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        JCLoger.debug(id + "-----" + values.toString());
        return id;
    }

    /**
     * 新增记录，集合
     *
     * @param valuesList
     */
    protected long doAdds(List<ContentValues> valuesList) {
        SQLiteDatabase db = getWritableDatabase();
        long id = -1;
        try {
            db.beginTransaction();
            for (ContentValues values : valuesList) {
                id = db.insert(table, ID, values);
                JCLoger.debug(id + "-----" + values.toString());
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return id;
    }

    /**
     * 修改操作
     *
     * @param values
     * @param whereClauses
     * @param whereArgs
     */
    protected void doUpdate(ContentValues values, String[] whereClauses, String[] whereArgs) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            String whereClause = null;
            if (whereClauses != null) {
                for (String str : whereClauses) {
                    if (whereClause == null) {
                        whereClause = str + " = ?";
                    } else {
                        whereClause += " AND " + str + " = ?";
                    }
                }
            }
            JCLoger.debug("doUpdate    " + db.update(table, values, whereClause, whereArgs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
    }

    /**
     * 删除记录
     *
     * @param whereClauses
     * @param whereArgs
     */
    public void doDelete(String[] whereClauses, String[] whereArgs) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            String whereClause = null;
            if (whereClauses != null) {
                for (String str : whereClauses) {
                    if (whereClause == null) {
                        whereClause = str + " = ?";
                    } else {
                        whereClause += " AND " + str + " = ?";
                    }
                }
            }
            db.delete(table, whereClause, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
    }

    /**
     * 删除记录
     *
     * @param sql
     */
    public void doDeleteBySql(String sql) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }

    /**
     * 得到所有的记录
     *
     * @param selections
     * @param selectionArgs
     * @return
     */
    protected ArrayList<ConcurrentHashMap<String, String>> doList(String[] selections, String[] selectionArgs) {
        return doList(selections, selectionArgs, false);
    }

    /**
     * 得到所有的记录
     *
     * @param selections
     * @param selectionArgs
     * @param isDown        是否降序
     * @return
     */
    protected ArrayList<ConcurrentHashMap<String, String>> doList(String[] selections, String[] selectionArgs, boolean isDown) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<ConcurrentHashMap<String, String>> list = new ArrayList<ConcurrentHashMap<String, String>>();
        try {
            String selection = null;
            if (selections != null) {
                for (String str : selections) {
                    if (selection == null) {
                        selection = str + " = ?";
                    } else {
                        selection += " AND " + str + " = ?";
                    }
                }
            }
            Cursor cursor = db.query(table, null, selection, selectionArgs, null, null, isDown ? ID + " desc" : null);
            while (cursor.moveToNext()) {
                int columnLength = cursor.getColumnCount();
                ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
                for (int i = 0; i < columnLength; i++) {
                    if (!TextUtils.isEmpty(cursor.getString(i))) {
                        map.put(cursor.getColumnName(i), cursor.getString(i));
                    }

                }
                list.add(map);
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
        return list;
    }

    /**
     * 得到所有的记录
     *
     * @param selections
     * @param selectionArgs
     * @return
     */
    protected ArrayList<ConcurrentHashMap<String, String>> doList(String[] selections, String[] selectionArgs, int pageIndex, int pageSize) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<ConcurrentHashMap<String, String>> list = new ArrayList<ConcurrentHashMap<String, String>>();
        try {
            String selection = null;
            if (selections != null) {
                for (String str : selections) {
                    if (selection == null) {
                        selection = str + " = ?";
                    } else {
                        selection += " AND " + str + " = ?";
                    }
                }
            }
            Cursor cursor = db.query(table, null, selection, selectionArgs, null, null, null, (pageIndex - 1) * pageSize + "," + pageIndex * pageSize);//"5,9",第6行开始,返回9行数据
            while (cursor.moveToNext()) {
                int columnLength = cursor.getColumnCount();
                ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
                for (int i = 0; i < columnLength; i++) {
                    if (!TextUtils.isEmpty(cursor.getString(i))) {
                        map.put(cursor.getColumnName(i), cursor.getString(i));
                    }

                }
                list.add(map);
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
        return list;
    }

    /**
     * sql语句查询，返回ArrayList<String>
     *
     * @param sql
     * @return
     */
    public ArrayList<String> doQueryBySql(String sql) {
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int columnLength = cursor.getColumnCount();
                for (int i = 0; i < columnLength; i++) {
                    list.add(cursor.getString(i));
                }
            }
            cursor.close();
        }
        db.close();
        return list;
    }

    /**
     * 获取数据总条数
     *
     * @param tableName
     * @return
     */
    public long getDBNum(String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteStatement statement = db.compileStatement("select count(*) from " + tableName);
        long count = statement.simpleQueryForLong();
        return count;
//		Cursor cursor = db.rawQuery("select count(*) as num from "+tableName, null);
//		return cursor.getColumnIndex("num");
    }

    /**
     * sql语句查询，返回ConcurrentHashMap<String,String>
     *
     * @param sql
     * @return
     */
    public ConcurrentHashMap<String, String> doQueryBySql3(String sql) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int columnLength = cursor.getColumnCount();
                for (int i = 0; i < columnLength; i++) {
                    map.put(cursor.getString(0), cursor.getString(1));
                }
            }
            cursor.close();
        }
        db.close();
        return map;
    }

    /**
     * sql语句查询,返回List<ConcurrentHashMap<String,String>>
     *
     * @param sql
     * @return
     */
    public ArrayList<ConcurrentHashMap<String, String>> doQueryBySql2(String sql) {
        ArrayList<ConcurrentHashMap<String, String>> list = new ArrayList<ConcurrentHashMap<String, String>>();
        SQLiteDatabase db = this.getReadableDatabase();
        JCLoger.debug("------sql:" + sql);
        Cursor cursor = db.rawQuery(sql, null);
        JCLoger.debug("-------cursor:" + cursor.getCount());
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int columnLength = cursor.getColumnCount();
                ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
                for (int i = 0; i < columnLength; i++) {
                    map.put(cursor.getColumnName(i), cursor.getString(i));
                }
                list.add(map);
            }
            cursor.close();
        }
        db.close();
        return list;
    }
}
