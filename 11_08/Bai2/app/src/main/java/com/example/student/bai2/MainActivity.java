package com.example.student.bai2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnDN = (Button) findViewById(R.id.btnDangNhap);
        Button btnThoat = (Button) findViewById(R.id.btnThoat);

        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Thong bao");
                CheckBox chkBox = (CheckBox) findViewById(R.id.checkBox);
                if (chkBox.isChecked()) {
                    builder.setMessage("Chao mung, Tai khoan ban da duoc luu");
                } else {
                    builder.setMessage("Chao mung");
                }
                builder.setPositiveButton("Chuyen sang Activity 2", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        funcDangNhap();
                        dialogInterface.dismiss();
//                        finish();
                    }
                });
                builder.setNegativeButton("Quay lai", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Ban co muon thoat khong");
                builder.setTitle("Thong bao");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void funcDangNhap() {
        EditText editTK = (EditText) findViewById(R.id.edtTaiKhoan);
        EditText editMK = (EditText) findViewById(R.id.edtMatKhau);
        Intent intent2 = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent2);
    }
}
