package com.example.student.onthithuchanh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class themnhanvien extends AppCompatActivity {
    EditText ten,cmnd,diachi;
    Button btnThem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themnhanvien);
        ten= findViewById(R.id.editTen);
        cmnd= findViewById(R.id.editCmnd);
        diachi= findViewById(R.id.editDiachi);
        btnThem = findViewById(R.id.btnThem);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NhanVien nv = new NhanVien(0,ten.getText().toString(),Integer.parseInt(cmnd.getText().toString()),diachi.getText().toString());
                MainActivity.database.insertNhanVien(nv);
                Intent intentDanhsach = new Intent(themnhanvien.this, MainActivity.class);
                startActivity(intentDanhsach);
            }
        });
    }
}
