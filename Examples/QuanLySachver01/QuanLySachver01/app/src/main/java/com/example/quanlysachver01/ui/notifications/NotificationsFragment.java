package com.example.quanlysachver01.ui.notifications;

import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.quanlysachver01.R;
import com.example.quanlysachver01.loaisach_danhsach_loaisach;

import java.util.ArrayList;
import java.util.Map;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    static ListView lv;
    ArrayAdapter adapter = null;
    View root;
    static FragmentActivity activity;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
         root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        lv = root.findViewById(R.id.listloaisach);
        xemDulieu();

        return root;
    }
    public  void xemDulieu(){
        ArrayList<String> lstloaisach = new ArrayList<String>();
        SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("loaisach",0);
        Map<String,?>maploaisach =  pref.getAll();
        for(Map.Entry<String,?> entry : maploaisach.entrySet()){
            //Log.d("map values",entry.getKey() + ": " + entry.getValue().toString());
            lstloaisach.add(entry.getValue().toString());
        }
        loaisach_danhsach_loaisach adapter = new loaisach_danhsach_loaisach(getActivity(),R.layout.loaisach_danhsach,lstloaisach);
        lv.setAdapter(adapter);

    }
    public static void updateListLoaiSach(){

        ArrayList<String> lstloaisach = new ArrayList<String>();
        SharedPreferences pref = activity.getApplicationContext().getSharedPreferences("loaisach",0);
        Map<String,?>maploaisach =  pref.getAll();
        for(Map.Entry<String,?> entry : maploaisach.entrySet()){
            //Log.d("map values",entry.getKey() + ": " + entry.getValue().toString());
            lstloaisach.add(entry.getValue().toString());
        }
        loaisach_danhsach_loaisach adapter = new loaisach_danhsach_loaisach(activity,R.layout.loaisach_danhsach,lstloaisach);
        lv.setAdapter(adapter);
    }
}