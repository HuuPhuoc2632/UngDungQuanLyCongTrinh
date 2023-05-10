package com.hhp.huuphuoc372.ungdungquanlycongtrinh.app.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hhp.huuphuoc372.ungdungquanlycongtrinh.app.Database.DbHelper;
import com.hhp.huuphuoc372.ungdungquanlycongtrinh.app.model.Construction;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ConstructionDao {
    DbHelper dbHelper;
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    private Date date;

    public ConstructionDao(Context context) {
        dbHelper = new DbHelper(context);
    }
    public List<Construction> getAllConstruction(){
        List<Construction> constructionList = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM CongTrinh", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            Construction construction = new Construction();
            construction.setMaCT(cs.getString(0));
            construction.setTenCT(cs.getString(1));
            construction.setDiaChi(cs.getString(2));
            construction.setTrangThai(cs.getString(3));
            System.out.println("ngay day: "+ cs.getString(4));
            try {
                construction.setNgayKhoiCong(format.parse(cs.getString(4)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            construction.setSoLuongNS(cs.getInt(5));
            constructionList.add(construction);
            cs.moveToNext();
        }
        cs.close();
        return constructionList;
    }
    public boolean insertConstruction(Construction construction){
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("TenCT", construction.getTenCT());
            values.put("DiaChi", construction.getDiaChi());
            values.put("NgayKhoiCong", format.format(construction.getNgayKhoiCong()));

            long kq = db.insert("CongTrinh", null, values);
            return kq>0;
    }
    public boolean deleteConstruction(String id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long kq = db.delete("CongTrinh", "MaCT = ?", new String[]{id});
        return kq>0;
    }

    public boolean updateConstruction(Construction construction){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenCT", construction.getTenCT());
        values.put("DiaChi", construction.getDiaChi());
        values.put("NgayKhoiCong", format.format(construction.getNgayKhoiCong()));
        values.put("SoLuongNS", construction.getSoLuongNS());
        values.put("TrangThai", construction.getTrangThai());
        long kq = db.update("CongTrinh", values, "MaCT = ?",new String[]{construction.getMaCT()});
        return kq>0;
    }

}
