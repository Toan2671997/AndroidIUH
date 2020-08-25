package toan.example.myapplication.ui.contact;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.List;

import toan.example.myapplication.DataBaseHelper;
import toan.example.myapplication.MainActivity;
import toan.example.myapplication.PeopleModel;
import toan.example.myapplication.R;
import toan.example.myapplication.ui.AddPeopleActivity;

public class ContactFragment extends Fragment {

    ImageView btnAdd, btnSearch;
    ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Set View
        View root = inflater.inflate(R.layout.fragment_contact, container, false);
        btnAdd = root.findViewById(R.id.imgAdd);
        btnSearch = root.findViewById(R.id.imgSearch);
        listView = root.findViewById(R.id.lstPeople);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_author = new Intent(ContactFragment.this.getActivity(), AddPeopleActivity.class);
                startActivity(intent_author);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(ContactFragment.this.getActivity());
                PeopleModel peopleModel = (PeopleModel) adapterView.getItemAtPosition(i);
                dataBaseHelper.deletePeople(peopleModel);
                getData();
                Toast.makeText(ContactFragment.this.getActivity(), "Detele success", Toast.LENGTH_LONG).show();
            }
        });



//        btnSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        getData();
        return root;
    }

    public void getData () {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(ContactFragment.this.getActivity());
        List<PeopleModel> allPeople = dataBaseHelper.getAll();

        ArrayAdapter arrayPeople = new ArrayAdapter<PeopleModel>(ContactFragment.this.getActivity(), R.layout.single_row, R.id.sr_tv_name, allPeople);
        listView.setAdapter(arrayPeople);
    }
}