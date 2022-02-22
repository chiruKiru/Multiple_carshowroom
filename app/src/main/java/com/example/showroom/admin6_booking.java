package com.example.showroom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class admin6_booking extends AppCompatActivity {

    Button View,Delete;
    EditText Booking_no;
    DBHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin6_booking);

        myDb = new DBHelper(this);

        View = (Button) findViewById(R.id.viewall);
        Delete = (Button) findViewById(R.id.delete);
        Booking_no = (EditText) findViewById(R.id.booking_no) ;

        viewData();
        DeleteData();
        
    }
    void viewData() {
        View.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Cursor res = myDb.getbook();
                if(res.getCount() == 0){
                    ShowMessage("Error","No Data Found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("Appointment_no   :   " +res.getString(0)+"\n");
                    buffer.append("Name     :    " +res.getString(1) + " "+res.getString(2)+"\n");
                    buffer.append("Date     :    " +res.getString(3)+"\n");
                    buffer.append("DL    :   " +res.getString(4)+"\n");
                    buffer.append("Phno     :   " +res.getString(5)+"\n");
                    buffer.append("Showroom name    :   " +res.getString(6)+"\n");
                    buffer.append("Timming  :    " +res.getString(7)+"\n");
                    buffer.append("Model    :   " +res.getString(8)+"\n");
                    buffer.append("Company  :    " +res.getString(9)+"\n");
                    buffer.append("Empp Assigned    :   " +res.getString(10)+"\n");
                    buffer.append("Showroom_ID   :   " +res.getString(11)+"\n");
                }
                ShowMessage("Data",buffer.toString());
            }
        });
    }

    public void ShowMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    public void DeleteData() {
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                String check = Booking_no.getText().toString();
                if (check.isEmpty()) {
                    Toast.makeText(admin6_booking.this, "Please Enter the app_no", Toast.LENGTH_SHORT).show();
                } else {
                    Integer DeletedRows = myDb.delbook(Integer.parseInt(Booking_no.getText().toString()));
                    if (DeletedRows > 0) {
                        Toast.makeText(admin6_booking.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(admin6_booking.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}