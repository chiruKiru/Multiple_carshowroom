package testdrive;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.example.showroom.*;

import java.util.ArrayList;

public class testdrive4 extends AppCompatActivity {
    DBHelper myDb;
    ArrayList<String> Details = new ArrayList<>(100);
    ArrayList<String> car_id = new ArrayList<>(100);
    ArrayList<String> Images = new ArrayList<>(100);
    ArrayList<ImageView> Im = new ArrayList<>(100);
    ArrayList<String> Data = new ArrayList<>(100);
    ArrayList<String> Rating = new ArrayList<>(100);

    public Integer count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testdrive4);
        myDb = new DBHelper(this);

        Bundle bundle = null;
        bundle = getIntent().getExtras();
        Details = bundle.getStringArrayList("T1");
        car_id = bundle.getStringArrayList("T2");
        Images = bundle.getStringArrayList("T3");

        int n = Details.size();
        LinearLayout Main = (LinearLayout) findViewById(R.id.hi);
        ImageView[] imgs = new ImageView[100];
        for (int i = 0; i < n; i++) {
            imgs[i] = new ImageView(this);
            TextView text = new TextView(this);
            Picasso.get()
                    .load(Images.get(i))
                    .placeholder(R.drawable.ic_baseline_broken_image_24)
                    .into(imgs[i]);
            imgs[i].setId(i);
            text.setText(Details.get(i));
            Main.addView(imgs[i], 1000, 600);
            Main.addView(text);
            imgs[i].setTag(String.valueOf(i));
            Im.add(imgs[i]);
            imgs[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("Clicked",""+view.getTag());
                    String clicked = view.getTag().toString();
                    Cursor res = myDb.get_detail_car(car_id.get(Integer.parseInt(clicked)).toString());
                    StringBuffer buffer = new StringBuffer();
                    String a;
                    a = car_id.get(Integer.parseInt(clicked)).toString();
                    while (res.moveToNext()) {
                        buffer.append("Model   : " + res.getString(0) + "\n");
                        buffer.append("COMPANY : " + res.getString(1) + "\n");
                        Data.add(buffer.toString());
                        buffer.delete(0, buffer.length());
                        buffer.append("Details of car");
                        buffer.append("\n\n");
                        buffer.append("TYPE                                 :         "+ res.getString(2) + "\n");
                        buffer.append("MILEAGE                          :         " + res.getString(4) + "\n");
                        buffer.append("ENGINE CC                      :         " + res.getString(5) + "\n");
                        buffer.append("FUEL                                 :          " + res.getString(6) + "\n");
                        buffer.append("TRANSMISSION TYPE  :         " + res.getString(7) + "\n");
                        buffer.append("SEATER                             :         " + res.getString(8) + "\n");
                        buffer.append("COLOR                               :        " + res.getString(9) + "\n\n");
                        buffer.append("Price                                  :        " + res.getString(10) + "\n\n");
                        Data.add(buffer.toString());
                        Data.add(res.getString(3));
                        buffer.delete(0, buffer.length());
                    }
                    Cursor r = myDb.rating(a);
                    StringBuffer rating = new StringBuffer();
                    while (r.moveToNext()) {
                        rating.append("Ratings of car  ");
                        rating.append("\n\n");
                        rating.append("Comfort                   :       " + r.getString(0) + "\n");
                        rating.append("Performance          :       " + r.getString(1) + "\n");
                        rating.append("Fuel_Economy       :       " + r.getString(2) + "\n");
                        rating.append("Value_for_Money  :       " + r.getString(3) + "\n");
                        rating.append("Exterior                    :       " + r.getString(4) + "\n");
                        Rating.add(rating.toString());
                        rating.delete(0, rating.length());
                    }
                    //ShowMessage("",Data.toString());
                    Intent intent = new Intent(testdrive4.this,details.class);
                    intent.putStringArrayListExtra("data",Data);
                    intent.putStringArrayListExtra("r",Rating);
                    intent.putExtra("t",a);
                    startActivity(intent);
                    Rating.clear();
                    Data.clear();
                }
            });
        }
    }
    public void ShowMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
