package com.example.student.onthithuchanh;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class suanhanvien extends AppCompatActivity {
    EditText ten,cmnd,diachi;
    Button btnLuu,btnXoa;
    NhanVien nv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suanhanvien);
        ten= findViewById(R.id.suaeditTen);
        cmnd= findViewById(R.id.suaeditCmnd);
        diachi= findViewById(R.id.suaeditDiachi);
        btnLuu = findViewById(R.id.suabtnThem);
        btnXoa = findViewById(R.id.suabtnXoa);
        int idNV = getIntent().getIntExtra("id",-1);
         nv = MainActivity.database.getNhanVienWithId(idNV);
        ten.setText(nv.getTen());
        cmnd.setText(String.valueOf(nv.getCmnd()));
        diachi.setText(nv.getDiachi());
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NhanVien nvUpdate = new NhanVien(nv.getId(),ten.getText().toString(),Integer.parseInt(cmnd.getText().toString()),diachi.getText().toString());
                MainActivity.database.updateBook(nvUpdate);
                Intent intentDanhsach = new Intent(suanhanvien.this, MainActivity.class);
                startActivity(intentDanhsach);
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.database.deleteNhanVien(nv.getId());
                Intent intentDanhsach = new Intent(suanhanvien.this, MainActivity.class);
                //Toast.makeText(this,"Xoa thanh cong",Toast.LENGTH_LONG).show();
                startActivity(intentDanhsach);
            }
        });
    }
}
