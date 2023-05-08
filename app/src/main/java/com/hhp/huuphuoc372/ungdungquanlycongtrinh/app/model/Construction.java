package com.hhp.huuphuoc372.ungdungquanlycongtrinh.app.model;

import java.util.Date;

public class Construction {
    private String maCT,tenCT,diaChi,trangThai;
    private Date ngayKhoiCong;
    private int soLuongNS;

    public Construction(String maCT, String tenCT, String diaChi, String trangThai, Date ngayKhoiCong, int soLuongNS) {
        this.maCT = maCT;
        this.tenCT = tenCT;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
        this.ngayKhoiCong = ngayKhoiCong;
        this.soLuongNS = soLuongNS;
    }

    public Construction() {
    }

    public String getMaCT() {
        return maCT;
    }

    public void setMaCT(String maCT) {
        this.maCT = maCT;
    }

    public String getTenCT() {
        return tenCT;
    }

    public void setTenCT(String tenCT) {
        this.tenCT = tenCT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgayKhoiCong() {
        return ngayKhoiCong;
    }

    public void setNgayKhoiCong(Date ngayKhoiCong) {
        this.ngayKhoiCong = ngayKhoiCong;
    }


    public int getSoLuongNS() {
        return soLuongNS;
    }

    public void setSoLuongNS(int soLuongNS) {
        this.soLuongNS = soLuongNS;
    }

    @Override
    public String toString() {
        return "Construction{" +
                "maCT='" + maCT + '\'' +
                ", tenCT='" + tenCT + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", trangThai='" + trangThai + '\'' +
                ", ngayKhoiCong=" + ngayKhoiCong +
                ", soLuongNS=" + soLuongNS +
                '}';
    }
}