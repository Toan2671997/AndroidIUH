package com.example.student.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> myList;
    ArrayAdapter<String> adapter;
    ListView lst;
    Button button;
    TextView tv, tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.editText);
        myList = new ArrayList<String>();
        lst = (ListView)findViewById(R.id.danhsach);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myList);
        lst.setAdapter(adapter);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myList.add(tv.getText() + "");
                adapter.notifyDataSetChanged();
            }
        });

        // Xu ly event Listview
        tvResult = (TextView)findViewById(R.id.txtKetqua);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String msg = "position: " + i;
                msg += "; value : " + adapterView.getItemAtPosition(i).toString();
                tvResult.setText(msg);
            }
        });



    }
}
