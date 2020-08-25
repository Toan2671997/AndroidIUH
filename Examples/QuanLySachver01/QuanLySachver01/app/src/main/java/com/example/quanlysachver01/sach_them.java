package com.example.quanlysachver01;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
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
import android.widget.Toast;

import com.example.quanlysachver01.ui.home.HomeFragment;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

public class sach_them extends AppCompatActivity {
    EditText ten,tacgia,ngayphathanh;
    Spinner loaisach;
    ArrayList<String> lstloaisach;
    sach newSach ;
    String maloai;
    Map<String,?> maploaisach;
    Button btnThem,btn_chup,btn_chon;
    //Database database;
    ImageView iv_anh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach_them);
        //database = new Database(this,"sach.sql",null,1);
        btnThem = findViewById(R.id.btnThemSach);
        btn_chup= findViewById(R.id.btn_chup);
        btn_chon = findViewById(R.id.btn_chon);
        iv_anh = findViewById(R.id.imgsach);
        lstloaisach = new ArrayList<String>();

        ten = findViewById(R.id.tensach);

        tacgia = findViewById(R.id.tacgia);
        ngayphathanh = findViewById(R.id.ngayphathanh);
        loaisach = findViewById(R.id.loaisach);
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
                DatePickerDialog pid = new DatePickerDialog(sach_them.this,callBack,2012,11,30);

                pid.show();
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themSach();
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
    }
    public void themSach(){
       // int id = Integer.parseInt(null);
        newSach = new sach(0, ten.getText().toString(),maloai , tacgia.getText().toString(), ngayphathanh.getText().toString(),getByteArrayFromImageView(iv_anh));
//                Id INTEGER PRIMARY KEY AUTOINCREMENT,hinh BLOB, tensach VARCHAR(200),tacgia VARCHAR(200),loaisach VARCHAR(200), ngayphathanh VARCHAR(200))"

        //HomeFragment.database.queryDate("INSERT INTO Sach VALUES(null,"+getByteArrayFromImageView(iv_anh)+","+ ten.getText().toString()+","+ tacgia.getText().toString()+","+maloai+","+ngayphathanh.getText().toString()+")" );
        //database.queryDate("INSERT INTO CongViec VALUES(null,'Lam bai tap 1')" );
        //HomeFragment.database.queryDate("INSERT INTO Sach VALUES(null,'"+newSach.getHinh()+"','"+ newSach.getTen()+"','"+ newSach.getTacgia()+"','"+newSach.getLoaisach()+"','"+"'nam'"+")" );
        HomeFragment.database.addStudent(newSach);
        Intent itHome = new Intent(sach_them.this, MainActivity.class);
        startActivity(itHome);

    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                try {
                    Uri imageUri = data.getData();
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    iv_anh.setImageBitmap(bitmap);
                } catch (FileNotFoundException  e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 0) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                iv_anh.setImageBitmap(bitmap);
            }
        }
    }

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
};
