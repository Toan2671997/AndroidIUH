package com.example.student.onthithuchanh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static DatabaseHelper database;
    GridView gridNhanVien;
    ArrayList<NhanVien>arrNhanVien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = new DatabaseHelper(this);
        setContentView(R.layout.activity_main);
        gridNhanVien = findViewById(R.id.gridNhanVien);
        arrNhanVien = database.getAllNhanVien();
        ArrayList<String> lstGridView = new ArrayList<>();
        if(arrNhanVien.size() > 0){
            for (NhanVien n: arrNhanVien){
                String str = n.getId() +" "+ n.getTen()+" "+n.getCmnd()+" "+n.getDiachi();
                lstGridView.add(str);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lstGridView);
        gridNhanVien.setAdapter(adapter);
        gridNhanVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentSua = new Intent(MainActivity.this, suanhanvien.class);
                intentSua.putExtra("id",arrNhanVien.get(i).getId());
                startActivity(intentSua);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menumain, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.themnv:
                Intent intent_author = new Intent(MainActivity.this, themnhanvien.class);
                startActivity(intent_author);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
