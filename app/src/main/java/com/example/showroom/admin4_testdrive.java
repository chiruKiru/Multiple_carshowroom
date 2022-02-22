package com.example.showroom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class admin4_testdrive extends AppCompatActivity {

    Button View,Del;
    EditText app;
    DBHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin4_testdrive);
        myDb = new DBHelper(this);


        View = (Button) findViewById(R.id.view);
        Del = (Button) findViewById(R.id.delete);
        app = (EditText) findViewById(R.id.appointment) ;


        viewData();
        DeleteData();
    }
    void viewData() {
        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Cursor res = myDb.getApp();
                if(res.getCount() == 0){
                    ShowMessage("Error","No Data Found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("Cust_ID      : " +res.getString(0)+"\n");
                    buffer.append("Name     :    " +res.getString(1) +"  "+res.getString(2)+"\n");
                    buffer.append("Date     :   " +res.getString(3)+"\n");
                    buffer.append("DL_NO    :    " +res.getString(4)+"\n");
                    buffer.append("Ph_no    :    " +res.getString(5)+"\n");
                    buffer.append("Showroom  :   " +res.getString(6)+"\n");
                    buffer.append("Paymment  :   " +res.getString(7)+"\n");
                    buffer.append("Model    :   " +res.getString(8)+"\n");
                    buffer.append("Company  :    " +res.getString(9)+"\n\n");
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
        Del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                String check = app.getText().toString();
                if (check.isEmpty()) {
                    Toast.makeText(admin4_testdrive.this, "Please Enter the app_no", Toast.LENGTH_SHORT).show();
                } else {
                    Integer DeletedRows = myDb.delv(Integer.parseInt(app.getText().toString()));
                    if (DeletedRows > 0) {
                        Toast.makeText(admin4_testdrive.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(admin4_testdrive.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}