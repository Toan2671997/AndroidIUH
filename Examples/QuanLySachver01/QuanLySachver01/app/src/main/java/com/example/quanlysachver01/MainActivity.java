package com.example.quanlysachver01;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("loaisach",0);
        SharedPreferences.Editor editor = pref.edit();
        String data = "Văn học nước ngoài";
        editor.putString("vanhoanuocngoai",data);

        String data1 = "Văn học trong nước";
        editor.putString("vanhoctrongnuoc",data1);

        String data2 = "Tôn giáo";
        editor.putString("tongiao",data2);

        String data3 = "Tâm lý";
        editor.putString("tamly",data3);
        editor.commit();

    }

}