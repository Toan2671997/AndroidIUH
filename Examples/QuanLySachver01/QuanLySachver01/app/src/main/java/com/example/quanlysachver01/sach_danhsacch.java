package com.example.quanlysachver01;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class sach_danhsacch extends ArrayAdapter<String> {
    Activity context = null;
    ArrayList<String> myArray = null;
    int layoutID;
    public sach_danhsacch(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
    }
}
