package com.example.quanlysachver01;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quanlysachver01.ui.notifications.NotificationsFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class loaisach_danhsach_loaisach extends ArrayAdapter<String> {
    Activity context = null;
    ArrayList<String> myArray = null;
    int layoutID;
    public loaisach_danhsach_loaisach(@NonNull Activity context, int resource, @NonNull ArrayList<String> objects) {
        super(context, resource, objects); this.context = context;
        this.myArray = objects;
        this.layoutID = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutID,null);
        if(myArray.size()>0 && position >= 0 ){
            TextView txtItem = convertView.findViewById(R.id.txtDisplay);
            txtItem.setText(myArray.get(position));
        }
        ImageButton btnXoaLoai = convertView.findViewById(R.id.btnDeleteLoaiSach);
        btnXoaLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref =context.getApplicationContext().getSharedPreferences("loaisach",0);
                SharedPreferences.Editor editor = pref.edit();
                Map<String,?>maploaisach =  pref.getAll();
                for(Map.Entry<String,?> entry : maploaisach.entrySet()){
                    //Log.d("map values",entry.getKey() + ": " + entry.getValue().toString());
                    if(entry.getValue() == myArray.get(position)){
                        editor.remove(entry.getKey());
                        editor.commit();
                        NotificationsFragment.updateListLoaiSach();
                    }
                }

            }
        });

        return convertView;
    }
}
