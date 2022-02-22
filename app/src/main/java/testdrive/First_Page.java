package testdrive;
import com.example.showroom.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.showroom.R;

import java.util.ArrayList;

public class First_Page extends AppCompatActivity {

    Button Admin,Test,Booking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        Admin = (Button) findViewById(R.id.admin);
        Test = (Button) findViewById(R.id.test);
        Booking = (Button) findViewById(R.id.car_booking);


        Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(First_Page.this,MainActivity.class);
                startActivity(intent);
            }
        });
        Test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(First_Page.this,home2.class);
                startActivity(intent);
            }
        });

        Booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(First_Page.this,booking.book_1.class);
                startActivity(intent);
            }
        });

    }
}