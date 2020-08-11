package toan.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent1 = getIntent();

        TextView txtFN = (TextView) findViewById(R.id.txtFirstName);
        TextView txtLN = (TextView) findViewById(R.id.txtLastName);
        TextView txtAge = (TextView) findViewById(R.id.txtAge);

        txtFN.setText(intent1.getStringExtra("FirstName"));
        txtLN.setText(intent1.getStringExtra("LastName"));
        Integer age = Integer.parseInt(intent1.getStringExtra("Age"));
        txtAge.setText(age.toString());


    }
}