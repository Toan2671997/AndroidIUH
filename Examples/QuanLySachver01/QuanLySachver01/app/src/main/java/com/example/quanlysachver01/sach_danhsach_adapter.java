package com.example.quanlysachver01;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quanlysachver01.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class sach_danhsach_adapter extends ArrayAdapter<sach> {
    Activity context;
    ArrayList<sach> myArray = null;
    int layoutID;


    public sach_danhsach_adapter(@NonNull Activity context, int resource, @NonNull ArrayList<sach> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutID=resource;
        this.myArray = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutID,null);
        if(myArray.size() > 0 && position >= 0){
            final sach s = myArray.get(position);
            TextView tensach = convertView.findViewById(R.id.tensach);
            tensach.setText(s.getTen());
            TextView tentacgia = convertView.findViewById(R.id.tentacgia);
            tentacgia.setText(s.getTacgia());
            SharedPreferences pref = context.getApplicationContext().getSharedPreferences("loaisach",0);
            Map<String,?> maploaisach;
            maploaisach =  pref.getAll();
            for(Map.Entry<String,?> entry : maploaisach.entrySet()){
                //Log.d("map values",entry.getKey() + ": " + entry.getValue().toString());
                if(entry.getKey().contains(s.getLoaisach())){
                    TextView tenloaisach = convertView.findViewById(R.id.loaisach);
                    tenloaisach.setText(entry.getValue().toString());
                }
                //lstloaisach.add(entry.getValue().toString());
            }
            ImageButton btnDelete = convertView.findViewById(R.id.btnDeleteSach);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder= new AlertDialog.Builder(context);
                    builder.setTitle("Xác Nhận Xóa");
                    builder.setMessage("Bạn có chắc muốn xóa quyển sách này ?");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            HomeFragment.database.queryDate("DELETE FROM sach WHERE Id='"+s.getId()+"'");
                            Toast.makeText(context,"Xoá thành công",Toast.LENGTH_LONG);
                            HomeFragment.updateListSach();
                        }
                    });
                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    AlertDialog dialog=builder.create();
                    dialog.show();

                }
            });
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent iSua = new Intent(context,sach_sua.class);
                    Integer idSach = s.getId();
                    iSua.putExtra("id",idSach);
                    iSua.putExtra("ten",s.getTen());
                    iSua.putExtra("tacgia",s.getTacgia());
                    iSua.putExtra("ngayphathanh",s.getNgayphathanh());
                    iSua.putExtra("maloai",s.getLoaisach());
                    iSua.putExtra("hinh",s.getHinh());
                    context.startActivityForResult(iSua,1234);
                }
            });
            TextView ngayphathanh = convertView.findViewById(R.id.ngayphahanh);
            ngayphathanh.setText(s.getNgayphathanh());
            ImageView imgView = convertView.findViewById(R.id.imgsach);
            Bitmap bmHinhDaiDien = BitmapFactory.decodeByteArray(s.getHinh(), 0, s.getHinh().length);
            imgView.setImageBitmap(bmHinhDaiDien);

        }
        return convertView;
    }
}
