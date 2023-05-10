package com.hhp.huuphuoc372.ungdungquanlycongtrinh.app.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "QuanLyCongTrinhDb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE CongTrinh(MaCT integer primary key autoincrement, TenCT text, DiaChi text, TrangThai text, NgayKhoiCong date, SoLuongNS integer )";
        db.execSQL(sql);
        sql = "CREATE TABLE Account (User text primary key, Password text)";
        db.execSQL(sql);
        sql = "INSERT INTO Account VALUES('huuphuoc372', '12345')";
        db.execSQL(sql);
//        sql = "INSERT INTO CongTrinh(TenCT, DiaChi, TrangThai, NgayKhoiCong, SoLuongNS) VALUES('Kho hàng', 'HoChiMinh', 'Dang tien hanh', '30/04/2023', 10)";
//        db.execSQL(sql); sql = "INSERT INTO CongTrinh(TenCT, DiaChi, TrangThai, NgayKhoiCong, SoLuongNS) VALUES('Nhà ở', 'HoChiMinh', 'Dang tien hanh', '30/04/2023', 10)";
//        db.execSQL(sql); sql = "INSERT INTO CongTrinh(TenCT, DiaChi, TrangThai, NgayKhoiCong, SoLuongNS) VALUES('Hồ bơi', 'HoChiMinh', 'Dang tien hanh', '20/04/2023', 13)";
//        db.execSQL(sql); sql = "INSERT INTO CongTrinh(TenCT, DiaChi, TrangThai, NgayKhoiCong, SoLuongNS) VALUES('Công viên', 'HoChiMinh', 'Dang tien hanh', '30/05/2023', 15)";
//        db.execSQL(sql); sql = "INSERT INTO CongTrinh(TenCT, DiaChi, TrangThai, NgayKhoiCong, SoLuongNS) VALUES('Chuồng heo', 'HoChiMinh', 'Dang tien hanh', '1/04/2023', 11)";
//        db.execSQL(sql); sql = "INSERT INTO CongTrinh(TenCT, DiaChi, TrangThai, NgayKhoiCong, SoLuongNS) VALUES('Cầu khỉ', 'HoChiMinh', 'Dang tien hanh', '25/06/2023', 9)";
//        db.execSQL(sql); sql = "INSERT INTO CongTrinh(TenCT, DiaChi, TrangThai, NgayKhoiCong, SoLuongNS) VALUES('Resort', 'HoChiMinh', 'Dang tien hanh', '30/04/2023', 7)";
//        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS CongTrinh");
        onCreate(db);

    }
}
