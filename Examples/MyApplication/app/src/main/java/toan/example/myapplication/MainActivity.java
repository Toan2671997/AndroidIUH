package toan.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.List;

import toan.example.myapplication.ui.contact.ContactFragment;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lstPeople);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_contact, R.id.navigation_favorite, R.id.navigation_group, R.id.navigation_list_trash)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        
//        getData();
    }

//    public void getData () {
//        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
//        List<PeopleModel> allPeople = dataBaseHelper.getAll();
//
//        ArrayAdapter arrayPeople = new ArrayAdapter<PeopleModel>(MainActivity.this, android.R.layout.simple_list_item_1, allPeople);
//        listView.setAdapter(arrayPeople);
//    }

}