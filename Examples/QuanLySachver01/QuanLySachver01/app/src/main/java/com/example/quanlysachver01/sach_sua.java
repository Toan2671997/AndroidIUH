package com.example.quanlysachver01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.quanlysachver01.ui.home.HomeFragment;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Map;

public class sach_sua extends AppCompatActivity {
    EditText ten,tacgia,ngayphathanh;
    Spinner loaisach;
    ArrayList<String> lstloaisach;
    sach newSach ;
    String maloai;
    Map<String,?> maploaisach;
    Button btnLuu,btn_chup,btn_chon;
    //Database database;
    ImageView iv_anh;
    Integer idSach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach_sua);
        btnLuu= findViewById(R.id.suabtnLuuSach);
        btn_chup= findViewById(R.id.suabtn_chup);
        btn_chon = findViewById(R.id.suabtn_chon);
        iv_anh = findViewById(R.id.suaimgsach);
        lstloaisach = new ArrayList<String>();

        ten = findViewById(R.id.suatensach);

        tacgia = findViewById(R.id.suatacgia);
        ngayphathanh = findViewById(R.id.suangayphathanh);
        loaisach = findViewById(R.id.sualoaisach);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("loaisach",0);
        maploaisach =  pref.getAll();
        for(Map.Entry<String,?> entry : maploaisach.entrySet()){
            //Log.d("map values",entry.getKey() + ": " + entry.getValue().toString());
            lstloaisach.add(entry.getValue().toString());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,lstloaisach);
        loaisach.setAdapter(adapter);
        loaisach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                for(Map.Entry<String,?> entry : maploaisach.entrySet()){
                    //Log.d("map values",entry.getKey() + ": " + entry.getValue().toString());
                    if(entry.getValue().equals(lstloaisach.get(i))){
                        maloai = entry.getKey();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
        ngayphathanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener callBack = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        ngayphathanh.setText((day + 1)+"/"+(month+1)+"/"+year);
                    }
                };
                DatePickerDialog pid = new DatePickerDialog(sach_sua.this,callBack,2012,11,30);

                pid.show();
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                suaSach();
            }
        });
        btn_chon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePhoto();
            }
        });
        btn_chup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });
        idSach = getIntent().getIntExtra("id",-1);
        String tenSach = getIntent().getStringExtra("ten");
        String tacgiaSach = getIntent().getStringExtra("tacgia");
        String maloaiSach = getIntent().getStringExtra("maloai");
        String ngayphathanhSach = getIntent().getStringExtra("ngayphathanh");
        byte[] hinh = getIntent().getByteArrayExtra("hinh");
        Bitmap bmHinhDaiDien = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
        iv_anh.setImageBitmap(bmHinhDaiDien);
        ten.setText(tenSach);
        tacgia.setText(tacgiaSach);
        ngayphathanh.setText(ngayphathanhSach);

    }
    public void suaSach(){

        newSach = new sach(idSach, ten.getText().toString(),maloai , tacgia.getText().toString(), ngayphathanh.getText().toString(),getByteArrayFromImageView(iv_anh));


        HomeFragment.database.updateStudent(newSach);
        Intent itHome = new Intent(sach_sua.this, MainActivity.class);
        startActivity(itHome);

    };
    private byte[] getByteArrayFromImageView(ImageView imgv){
        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    private void takePicture(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }
    private void choosePhoto(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }
}