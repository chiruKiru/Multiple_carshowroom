package booking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.example.showroom.DBHelper;
import com.example.showroom.R;

import java.util.ArrayList;

import booking.book_1;
import testdrive.testdrive4;

public class book_1 extends AppCompatActivity {

    DBHelper myDb;

    Spinner Type, Company;
    Button ok;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book1);
        myDb = new DBHelper(this);

        ok = (Button) findViewById(R.id.ok);
        Type = (Spinner) findViewById(R.id.type3);
        Company = (Spinner) findViewById(R.id.company3);


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                ArrayList<String> Detail = new ArrayList<>(100);
                ArrayList<String> car_id = new ArrayList<>(100);
                ArrayList<String> Images = new ArrayList<>(100);
                Integer Car;
                String a = Type.getSelectedItem().toString().toLowerCase();
                String b = Company.getSelectedItem().toString().toLowerCase();
                if ((Company.getSelectedItem().toString().toLowerCase().equals("all")) && (Type.getSelectedItem().toString().toLowerCase().equals("all"))) {
                    Cursor res = myDb.get_detail();
                    if (res.getCount() == 0) {
                        ShowMessage("Error", "No Data Found");
                        return;
                    }
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("Model : " + res.getString(1) + "\n");
                        buffer.append("COMPANY : " + res.getString(2) + "\n");
                        buffer.append("TYPE : " + res.getString(3) + "\n");
                        buffer.append("PRICE : " + res.getString(4) + "\n\n");

                        Detail.add(buffer.toString());
                        car_id.add(res.getString(0));
                        Images.add(res.getString(5));
                        buffer.delete(0, buffer.length());
                    }
                    intent = new Intent(book_1.this,book_2.class);
                    intent.putExtra("T1", Detail);
                    intent.putExtra("T2", car_id);
                    intent.putExtra("T3", Images);
                    startActivity(intent);
                } else if (a.equals("all")) {
                    String pass = Company.getSelectedItem().toString();
                    Cursor res = myDb.get_detail1(pass);
                    if (res.getCount() == 0) {
                        ShowMessage("Error", "No Data Found");
                        return;
                    }
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("Model : " + res.getString(1) + "\n");
                        buffer.append("COMPANY : " + res.getString(2) + "\n");
                        buffer.append("TYPE : " + res.getString(3) + "\n");
                        buffer.append("PRICE : " + res.getString(4) + "\n\n");

                        Detail.add(buffer.toString());
                        car_id.add(res.getString(0));
                        Images.add(res.getString(5));
                        buffer.delete(0, buffer.length());
                    }
                    intent = new Intent(book_1.this,book_2.class);
                    intent.putExtra("T1", Detail);
                    intent.putExtra("T2", car_id);
                    intent.putExtra("T3", Images);
                    startActivity(intent);
                } else if (b.equals("all")) {
                    String pass = Type.getSelectedItem().toString();
                    Cursor res = myDb.get_detail2(pass);
                    if (res.getCount() == 0) {
                        ShowMessage("Error", "No Data Found");
                        return;
                    }
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("Model : " + res.getString(1) + "\n");
                        buffer.append("COMPANY : " + res.getString(2) + "\n");
                        buffer.append("TYPE : " + res.getString(3) + "\n");
                        buffer.append("PRICE : " + res.getString(4) + "\n\n");

                        Detail.add(buffer.toString());
                        car_id.add(res.getString(0));
                        Images.add(res.getString(5));
                        buffer.delete(0, buffer.length());
                    }
                    intent = new Intent(book_1.this,book_2.class);
                    intent.putExtra("T1", Detail);
                    intent.putExtra("T2", car_id);
                    intent.putExtra("T3", Images);
                    startActivity(intent);
                } else {
                    String pass = Type.getSelectedItem().toString();
                    String pass1 = Company.getSelectedItem().toString();
                    Cursor res = myDb.get_detail_main(pass, pass1);
                    if (res.getCount() == 0) {
                        ShowMessage("Error", "No Data Found");
                        return;
                    }
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("Model : " + res.getString(1) + "\n");
                        buffer.append("COMPANY : " + res.getString(2) + "\n");
                        buffer.append("TYPE : " + res.getString(3) + "\n");
                        buffer.append("PRICE : " + res.getString(4) + "\n\n");

                        Detail.add(buffer.toString());
                        car_id.add(res.getString(0));
                        Images.add(res.getString(5));
                        buffer.delete(0, buffer.length());
                    }
                    intent = new Intent(book_1.this,book_2.class);
                    intent.putExtra("T1", Detail);
                    intent.putExtra("T2", car_id);
                    intent.putExtra("T3", Images);
                    startActivity(intent);
                }
            }
        });
    }
    public void ShowMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}