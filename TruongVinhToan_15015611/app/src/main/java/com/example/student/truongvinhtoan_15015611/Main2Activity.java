package com.example.student.truongvinhtoan_15015611;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {
    Button btnXacNhan;
    EditText editHoTen, editDiaChi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnXacNhan = (Button)findViewById(R.id.button);
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                editHoTen = (EditText)findViewById(R.id.editTextHoTen);
                editDiaChi = (EditText)findViewById(R.id.editTextDiaChi);
                intent1.putExtra("HoTen", editHoTen.getText().toString());
                intent1.putExtra("DiaChi", editDiaChi.getText().toString());
                setResult(RESULT_OK, intent1);
                finish();
            }
        });
    }
}
