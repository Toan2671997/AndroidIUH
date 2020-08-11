package com.example.bt03thtuan02_02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText nam;
    Button tranform;
    TextView chinaYear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nam = (EditText)findViewById(R.id.edit_text_year);
        tranform = (Button)findViewById(R.id.btn_tranform);
        chinaYear = (TextView)findViewById(R.id.resultNameChinaYear) ;
        tranform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,MainActivity2.class);
                String str = nam.getText().toString();
                i.putExtra("year",str);
                startActivityForResult(i,999);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 999 && resultCode==RESULT_OK){
            String str = data.getStringExtra("confirm");
            chinaYear.setText(str);
        }
    }


}