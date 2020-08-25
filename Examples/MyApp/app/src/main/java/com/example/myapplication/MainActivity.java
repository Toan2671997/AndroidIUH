package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edit_A, edit_B, edit_C;
    Button btn_Add, btn_Show, btn_Save, btn_Delete;
    GridView grid_data;
    ObjectModel objUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit_A = findViewById(R.id.edtA);
        edit_B = findViewById(R.id.edtB);
        edit_C = findViewById(R.id.edtC);
        btn_Add = findViewById(R.id.btnAdd);
        btn_Show = findViewById(R.id.btnShow);
        btn_Save = findViewById(R.id.btnSave);
        btn_Delete = findViewById(R.id.btnDelete);
        grid_data = findViewById(R.id.grdData);
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectModel objectModel;
                try {
                    objectModel = new ObjectModel( -1, edit_A.getText().toString(), edit_B.getText().toString(), edit_C.getText().toString());
                }
                catch (Exception e) {
                    objectModel = new ObjectModel( -1, "error", "", "");
                }

                DatabaseHelper dataBaseHelper = new DatabaseHelper(MainActivity.this);
                boolean isSuccess = dataBaseHelper.addObj(objectModel);
                if (isSuccess) {
                    Toast.makeText(MainActivity.this, "Add Success", Toast.LENGTH_LONG).show();
                    edit_A.setText("");
                    edit_B.setText("");
                    edit_C.setText("");
//                    Intent intent = new Intent(AddPeopleActivity.this, MainActivity.class);
//                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Add Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper dataBaseHelper = new DatabaseHelper(MainActivity.this);
                ObjectModel objectModel = new ObjectModel(
                        objUpdate.getId(),
                        edit_A.getText().toString(),
                        edit_B.getText().toString(),
                        edit_C.getText().toString()
                );
                dataBaseHelper.updateObj(objectModel);
                edit_A.setText("");
                edit_B.setText("");
                edit_C.setText("");
                objUpdate = null;
            }
        });
        btn_Show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper dataBaseHelper = new DatabaseHelper(MainActivity.this);
                ArrayList<ObjectModel> arrAllData= (ArrayList<ObjectModel>) dataBaseHelper.getAll();
                ArrayList<String> lstGridView = new ArrayList<>();
                if(arrAllData.size() > 0){
                    for (ObjectModel n: arrAllData){
                        String str = n.getId() +" "+ n.getA()+" "+n.getB()+" "+n.getC();
                        lstGridView.add(str);
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, lstGridView);
                grid_data.setAdapter(adapter);
            }
        });
        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper dataBaseHelper = new DatabaseHelper(MainActivity.this);
                dataBaseHelper.deleteObj(objUpdate);
                getData();
                Toast.makeText(ContactFragment.this.getActivity(), "Detele success", Toast.LENGTH_LONG).show();
            }
        });

        grid_data.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DatabaseHelper dataBaseHelper = new DatabaseHelper(MainActivity.this);
                ArrayList<ObjectModel> arrAllData= (ArrayList<ObjectModel>) dataBaseHelper.getAll();
                ObjectModel obj = dataBaseHelper.getById(arrAllData.get(i));
                objUpdate = obj;
                edit_A.setText(obj.getA());
                edit_B.setText(obj.getB());
                edit_C.setText(obj.getC());
            }
        });

//        btn_Show.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DatabaseHelper dataBaseHelper = new DatabaseHelper(MainActivity.this);
//                List<ObjectModel> allData = dataBaseHelper.getAll();
//
//                ArrayAdapter arrayPeople = new ArrayAdapter<PeopleModel>(ContactFragment.this.getActivity(), R.layout.single_row, R.id.sr_tv_name, allPeople);
//                listView.setAdapter(arrayPeople);
//            }
//        });

    }
}