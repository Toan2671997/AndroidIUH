package com.example.quanlysachver01;

public class sach {
    private int id;
    private String ten;
    private String loaisach;
    private String tacgia;
    private String ngayphathanh;
    private byte[] hinh;


    public sach(int id, String ten, String loaisach, String tacgia, String ngayphathanh, byte[] hinh) {
        this.id = id;
        this.ten = ten;
        this.loaisach = loaisach;
        this.tacgia = tacgia;
        this.ngayphathanh = ngayphathanh;
        this.hinh = hinh;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }

    public String getNgayphathanh() {
        return ngayphathanh;
    }

    public void setNgayphathanh(String ngayphathanh) {
        this.ngayphathanh = ngayphathanh;
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

    public String getLoaisach() {
        return loaisach;
    }

    public void setLoaisach(String loaisach) {
        this.loaisach = loaisach;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    @Override
    public String toString() {
        return "sach{" +
                "id='" + id + '\'' +
                ", ten='" + ten + '\'' +
                ", loaisach='" + loaisach + '\'' +
                ", tacgia='" + tacgia + '\'' +
                ", ngayphathanh='" + ngayphathanh + '\'' +
                '}';
    }
}
