package com.example.myapplication;

public class ObjectModel {
    private int id;
    private String a;
    private String b;
    private String c;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public ObjectModel(int id, String a, String b, String c) {
        this.id = id;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public ObjectModel() {
    }

    @Override
    public String toString() {
        return "ObjectModel{" +
                "id=" + id +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                '}';
    }
}
