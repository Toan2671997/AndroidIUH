package toan.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import toan.example.myapplication.DataBaseHelper;
import toan.example.myapplication.MainActivity;
import toan.example.myapplication.PeopleModel;
import toan.example.myapplication.R;
import toan.example.myapplication.ui.contact.ContactFragment;

public class AddPeopleActivity extends AppCompatActivity {

    Button btn_Add, btn_Cancel;
    EditText edName, edPhone, edEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_people);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edName = findViewById(R.id.edtName);
        edPhone = findViewById(R.id.edtSdt);
        edEmail = findViewById(R.id.edtEmail);
        btn_Add = findViewById(R.id.btnAdd);
        btn_Cancel = findViewById(R.id.btnCancel);
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PeopleModel peopleModel;
                try {
                    peopleModel = new PeopleModel( -1, edName.getText().toString(), edPhone.getText().toString(), edEmail.getText().toString(), "None", false);
                }
                catch (Exception e) {
                    peopleModel = new PeopleModel( -1, "Error", "", "", "None", false);;
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(AddPeopleActivity.this);
                boolean isSuccess = dataBaseHelper.addPeople(peopleModel);
                if (isSuccess) {
                    Toast.makeText(AddPeopleActivity.this, "Add Success", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AddPeopleActivity.this, MainActivity.class);
                    startActivity(intent);
//                    setResult(RESULT_OK, intent);
//                    finish();
                } else {
                    Toast.makeText(AddPeopleActivity.this, "Add Failed", Toast.LENGTH_LONG).show();
                }

            }
        });
        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}