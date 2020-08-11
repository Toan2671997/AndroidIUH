package com.example.student.truongvinhtoan_15015611;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2;
    TextView txtView;
    String resultSoThich = "Sở thích: ";
    String resultInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        txtView = (TextView) findViewById(R.id.textView);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this, Main2Activity.class);
                startActivityForResult(intent2, 999);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Chọn sở thích của bạn");
//                builder.setMessage("Hạy chọn sở thích của bạn dưới đây");

                // Add a checkbox list
                final boolean[] checked = new boolean[] {false};
                final String[] soThichList = {"Thể Thao", "Âm Nhạc", "Du Lịch", "Quẩy", "Bay Nhảy"};
                final boolean[] soThichItem = {false, false, false, false, false};
                builder.setMultiChoiceItems(soThichList, soThichItem, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        // The user checked or unchecked a box
                        soThichItem[which] = isChecked;
                    }
                });
                builder.setPositiveButton("Xong", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for (int j=0; j<soThichItem.length; j++) {
                            if (soThichItem[j]) {
                                resultSoThich += (soThichList[j] + " ").toString();
                            }
                        }
                        String resutFinal = resultInfo + "\n" + resultSoThich;
                        txtView.setText(resutFinal);
                        dialogInterface.dismiss();
//                        finish();
                    }
                });
                builder.setNegativeButton("Quay lại", new DialogInterface.OnClickListener() {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 999 && resultCode == RESULT_OK) {
            String hoten = data.getStringExtra("HoTen");
            String diachi = data.getStringExtra("DiaChi");
            String result = "Họ Tên: " + hoten + "\n" + "Địa chỉ: " + diachi;
            resultInfo = result.toString();
            txtView.setText(result.toString());
        }
    }
}
