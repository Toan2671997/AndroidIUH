package toan.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

//public class MainActivity extends AppCompatActivity {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//}

public class MainActivity extends ListActivity {

//    String[] presidents = {
//            "Toan",
//            "Thang",
//            "Thien",
//            "Nam",
//            "TUng",
//    };
    String[] presidents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, presidents));

        setContentView(R.layout.activity_main);
        ListView listView = getListView();
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setTextFilterEnabled(true);
        presidents = getResources().getStringArray(R.array.presidents_array);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, presidents));
    }

//    @Override
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//        Toast.makeText(this, "You selected " + presidents [position], Toast.LENGTH_SHORT).show();
//    }


//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        Toast.makeText(this, "You selected name " + presidents[position], Toast.LENGTH_SHORT).show();
//    }

    public void onClick(View view) {
        ListView listView = getListView();

        String itemsSelected = "Selected Items: \n";
        for (int i=0; i<listView.getCount(); i++) {
            itemsSelected += listView.getItemAtPosition(i) + "\n";
        }
        Toast.makeText(this, itemsSelected, Toast.LENGTH_SHORT).show();
    }
}