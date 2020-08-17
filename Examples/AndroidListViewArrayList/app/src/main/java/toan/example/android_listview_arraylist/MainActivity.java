package toan.example.android_listview_arraylist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public class NhanVien {
        private String id;
        private String name;
        private boolean gender;
        public String getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public void setId(String id) {
            this.id = id;
        }
        public void setName(String name) {
            this.name = name;
        }
        public boolean isGender() {
            return gender;
        }
        public void setGender(boolean gender) {
            this.gender = gender;
        }

        public NhanVien() {

        }

        @Override
        public String toString() {
            return this.id + " - " + this.name;
        }
    }

    public class MyArrayAdapter extends ArrayAdapter<NhanVien> {
        Activity context = null;
        ArrayList<NhanVien> myArray = null;
        int layoutId;

        public MyArrayAdapter(Activity context, int textViewResourceId, ArrayList<NhanVien> objects) {
            super(context, textViewResourceId, objects);
            this.context = context;
            this.layoutId = textViewResourceId;
            this.myArray = objects;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layoutId, null);

            if(myArray.size() > 0 && position >=0) {
                final TextView txtDisplay = (TextView)convertView.findViewById(R.id.tv_item);
                final NhanVien nv = myArray.get(position);
                txtDisplay.setText(nv.toString());
                final ImageView imgitem = (ImageView)convertView.findViewById(R.id.img_item);
                if (nv.isGender()) {
                    imgitem.setImageResource(R.drawable.girl);
                } else {
                    imgitem.setImageResource(R.drawable.boy);
                }
            }
            return convertView;
        }
    }

    ArrayList<NhanVien> arrNhanVien = null;
    MyArrayAdapter adapter = null;
    ListView lvwNhanVien = null;
    Button btnNhap;
    ImageButton btnRemoveAll;
    EditText txtMa, txtTen;
    RadioGroup genderGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNhap = (Button)findViewById(R.id.btn_nhap);
        btnRemoveAll = (ImageButton)findViewById(R.id.btn_thoat);
        txtMa = (EditText)findViewById(R.id.editManv);
        txtTen = (EditText)findViewById(R.id.editTennv);
        genderGroup = (RadioGroup)findViewById(R.id.rdg_gioitinh);
        lvwNhanVien = (ListView)findViewById(R.id.lsv_danhsach);
        arrNhanVien = new ArrayList<NhanVien>();
        adapter = new MyArrayAdapter(this, R.layout.my_item_layout, arrNhanVien);
        lvwNhanVien.setAdapter(adapter);

        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyNhap();
            }
        });

        btnRemoveAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyXoa();
            }
        });
    }

    public void xuLyNhap() {
        String ma = txtMa.getText() + "";
        String ten = txtTen.getText() + "";
        boolean gioitinh = false;
        if (genderGroup.getCheckedRadioButtonId() == R.id.radioButton_female) {
            gioitinh = true;
        }
        NhanVien nv = new NhanVien();
        nv.setId(ma);
        nv.setName(ten);
        nv.setGender(gioitinh);

        arrNhanVien.add(nv);

        adapter.notifyDataSetChanged();

        txtMa.setText("");
        txtTen.setText("");
        txtMa.requestFocus();
    }

    public void xuLyXoa() {
        for (int i = lvwNhanVien.getChildCount() - 1; i >= 0; i--)
        {
            View v = lvwNhanVien.getChildAt(i);
            CheckBox chk = (CheckBox)v.findViewById(R.id.chk_item);
            if (chk.isChecked()) {
                arrNhanVien.remove(i);
            }
        }
        adapter.notifyDataSetChanged();
    }
}