package com.example.bt03thtuan02_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    Button confirm;
    TextView chinaYear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        confirm = (Button)findViewById(R.id.confirm);
        chinaYear = (TextView)findViewById(R.id.namechinayear);
        Integer st = Integer.parseInt(getIntent().getStringExtra("year"));
        String can ="";
        String chi ="";
        switch (st % 10){
            case 0:
                can = "Canh";
                break;
            case 1:
                can = "Tân";
                break;
            case 2:
                can = "Nhâm";
                break;
            case 3:
                can = "Quý";
                break;
            case 4:
                can = "Giáp";
                break;
            case 5:
                can = "Ất";
                break;
            case 6:
                can = "Bính";
                break;
            case 7:
                can = "Đinh";
                break;
            case 8:
                can = "Mậu";
                break;
            case 9:
                can = "Kỷ";
                break;

        }
        switch (st % 12){
            case 0:
                chi = "Thân";
                break;
            case 1:
                chi = "Dậu";
                break;
            case 2:
                chi = "Tuất";
                break;
            case 3:
                chi = "Hợi";
                break;
            case 4:
                chi = "Tý";
                break;
            case 5:
                chi = "Sửu";
                break;
            case 6:
                chi = "Dần";
                break;
            case 7:
                chi = "Mẹo";
                break;
            case 8:
                chi = "Thân";
                break;
            case 9:
                chi = "Tỵ";
                break;
            case 10:
                chi = "Ngọ";
                break;
            case 11:
                chi = "Mùi";
                break;

        }
        final String result = can+" "+chi;
        chinaYear.setText(result);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("confirm",result.toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}