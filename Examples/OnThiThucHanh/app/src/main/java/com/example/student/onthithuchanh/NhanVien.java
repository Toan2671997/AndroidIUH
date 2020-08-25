package com.example.student.onthithuchanh;

public class NhanVien {
    private int id;
    private String ten;
    private int cmnd;
    private String diachi;


    public NhanVien(int id, String ten, int cmnd, String diachi) {
        this.id = id;
        this.ten = ten;
        this.cmnd = cmnd;
        this.diachi = diachi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getCmnd() {
        return cmnd;
    }

    public void setCmnd(int cmnd) {
        this.cmnd = cmnd;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
