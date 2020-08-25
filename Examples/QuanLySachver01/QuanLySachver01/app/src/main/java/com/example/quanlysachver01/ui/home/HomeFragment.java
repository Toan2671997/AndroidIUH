package com.example.quanlysachver01.ui.home;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.quanlysachver01.Database;
import com.example.quanlysachver01.R;
import com.example.quanlysachver01.sach;
import com.example.quanlysachver01.sach_danhsach_adapter;
import com.example.quanlysachver01.sach_them;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    public static Database database;
    private HomeViewModel homeViewModel;
    static ListView lstSach;
    static ArrayList<sach> arrSach = null;
    public static sach_danhsach_adapter adapter = null;
    static FragmentActivity activity;
     ListView lvNhanVien = null;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        activity = (FragmentActivity)inflater.getContext();
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        setHasOptionsMenu(true);
        lstSach = root.findViewById(R.id.listsach);
        arrSach = new ArrayList<sach>();
        database = new Database(getActivity(),"sach.sql",null,1);
        database.queryDate("CREATE TABLE IF NOT EXISTS Sach(Id INTEGER PRIMARY KEY AUTOINCREMENT,hinh BLOB, tensach VARCHAR(200),tacgia VARCHAR(200),loaisach VARCHAR(200), ngayphathanh VARCHAR(200))");
   // database.queryDate("INSERT INTO Sach VALUES(null,'thang','thang','thang','thang','thang')" );
        Cursor dataSach = database.getdata("SELECT * FROM Sach");


        while (dataSach.moveToNext()){

            int id = dataSach.getInt(0);
            byte [] hinh = dataSach.getBlob(1);
            String ten = dataSach.getString(2);
            String tacgia = dataSach.getString(3);
            String loaisach = dataSach.getString(4);
            String ngayphathanh = dataSach.getString(5);
            sach s = new sach(id, ten,  loaisach,  tacgia,  ngayphathanh, hinh);
            arrSach.add(s);
        }
        adapter = new sach_danhsach_adapter(getActivity(),R.layout.sach_danhsach,arrSach);
        lstSach.setAdapter(adapter);
        //tạo database

        //tao bảng

        return root;
    }

    @Override
    public void  onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        //MenuInflater inflater =  getSupportMenuInflater();
        inflater.inflate(R.menu.menubook, menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.themsach:
                Intent it = new Intent(getActivity(), sach_them.class);
                startActivityForResult(it,99);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public static void updateListSach(){
        arrSach = new ArrayList<sach>();
        Cursor dataSach = database.getdata("SELECT * FROM Sach");

        while (dataSach.moveToNext()){
            int id = dataSach.getInt(0);
            byte [] hinh = dataSach.getBlob(1);
            String ten = dataSach.getString(2);
            String tacgia = dataSach.getString(3);
            String loaisach = dataSach.getString(4);
            String ngayphathanh = dataSach.getString(5);
            sach s = new sach(id, ten,  loaisach,  tacgia,  ngayphathanh, hinh);
            arrSach.add(s);
        }
        adapter = new sach_danhsach_adapter(activity,R.layout.sach_danhsach,arrSach);
        lstSach.setAdapter(adapter);
    }
}
