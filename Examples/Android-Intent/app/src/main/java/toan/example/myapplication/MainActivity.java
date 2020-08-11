package toan.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.btnSend);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });
    }

    public void openActivity2() {
        EditText editFN = (EditText) findViewById(R.id.edtFirstName);
        EditText editLN = (EditText) findViewById(R.id.edtLastName);
        EditText editAge = (EditText) findViewById(R.id.edtAge);
        Intent intent2 = new Intent(MainActivity.this, MainActivity2.class);
        intent2.putExtra("Age", editAge.getText().toString());
        intent2.putExtra("FirstName", editFN.getText().toString());
        intent2.putExtra("LastName", editLN.getText().toString());
        startActivity(intent2);
    }
}