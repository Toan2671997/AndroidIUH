package toan.example.android_interalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText hoten, email;
    Button submit;
    String[] infoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = (Button) findViewById(R.id.btnSubmit);
        hoten = (EditText) findViewById(R.id.editHoTen);
        email = (EditText) findViewById(R.id.editEmail);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileOutput();
            }
        });
    }

    public void openFileOutput() {
        String filename = "Info.txt";

        String data = "Tên" + hoten.getText() + "\n" + "Email:" + email.getText();
        infoList.add(data);
        FileOutputStream fileOut;
        try {
            fileOut = openFileOutput(filename, Context.MODE_PRIVATE);
            fileOut.write(data.getBytes());
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

        SharedPreferences settings = getSharedPreferences("users", 0);

        SharedPreferences.Editor editorUser = settings.edit();
        for (int i = 0; i < users.size(); i++)
            editorUser.putString("user" + i, users.get(i));

        editorUser.commit();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("Info", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("NewDanhBa", data);
        editor.commit();

    }
}