package toan.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Gallery gallery = (Gallery)findViewById(R.id.gallery1);
        gallery.setAdapter(new ImageAdapter(this));
        gallery.setSpacing(1);
        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)findViewById(R.id.textView1)).setText("Img at pos: "+i);
                int id = (Integer)gallery.getAdapter().getItem(i);
                ((ImageView)findViewById(R.id.imgView1)).setImageResource(id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                ((TextView)findViewById(R.id.textView1)).setText("No Img.");
            }
        });
    }
}

class ImageAdapter extends BaseAdapter {
    private Context context;
    private int[] array = {R.drawable.car1, R.drawable.car2, R.drawable.car3, R.drawable.car4, R.drawable.car5, R.drawable.car6};

    ImageAdapter(Context context) {
        super();
        this.context = context;
    }

    public int getCount() {
        return this.array.length;
    }

    public Object getItem(int var1) {
        return  this.array[var1];
    }

    public long getItemId(int var1) {
        return  var1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(array[i]);
        imageView.setScaleType(ImageView.ScaleType.FIT_END);
        imageView.setLayoutParams(new Gallery.LayoutParams(100,100));
        return view;
    }
}