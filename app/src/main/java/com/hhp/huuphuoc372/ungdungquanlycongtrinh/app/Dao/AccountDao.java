package com.hhp.huuphuoc372.ungdungquanlycongtrinh.app.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hhp.huuphuoc372.ungdungquanlycongtrinh.app.Database.DbHelper;
import com.hhp.huuphuoc372.ungdungquanlycongtrinh.app.model.Account;

public class AccountDao {
    DbHelper dbHelper;

    public AccountDao(Context context) {
        dbHelper = new DbHelper(context);
    }
    public Account getAccountByUser(String user){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM Account WHERE User ="+"'"+user+"'";
        Cursor cs = db.rawQuery(sql, null);
        if(cs.getCount()!=0) {
            cs.moveToFirst();
            Account account = new Account();
            account.setUser(cs.getString(0));
            account.setPassword(cs.getString(1));
            return account;
        }
        else{
            return null;
        }
    }
}
