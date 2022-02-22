package com.example.showroom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class showroom extends AppCompatActivity {

    DBHelper myDb;

    EditText Showroom_no,Location,Address,Pincode,PHno;
    Spinner Company;
    Button Insert,Delete,Update,View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDb = new DBHelper(this);
        setContentView(R.layout.activity_showroom);


        Showroom_no = (EditText) findViewById(R.id.showroom_no);
        Company = (Spinner) findViewById(R.id.company);
        Location = (EditText) findViewById(R.id.location);
        Address = (EditText) findViewById(R.id.address);
        Pincode = (EditText) findViewById(R.id.pincode);
        PHno = (EditText) findViewById(R.id.Phno);
        Insert = (Button) findViewById(R.id.insert);
        View = (Button) findViewById(R.id.view);
        Update = (Button) findViewById(R.id.update);
        Delete = (Button) findViewById(R.id.delete);

        InsertData();
        ViewAll();
        UpdateData();
        DeleteData();
    }
    public void InsertData() {
        Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                String check = Showroom_no.getText().toString();
                if (check.isEmpty()) {
                    Toast.makeText(showroom.this, "Please Enter the CAR_ID", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isInserted = myDb.InsertShow(Integer.parseInt(Showroom_no.getText().toString()),
                            Company.getSelectedItem().toString(),
                            Location.getText().toString(),
                            Address.getText().toString(),
                            Pincode.getText().toString(),
                            PHno.getText().toString()
                    );
                    if (isInserted) {
                        Toast.makeText(showroom.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(showroom.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void ViewAll() {
        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Cursor res = myDb.getShow();
                if(res.getCount() == 0){
                    ShowMessage("Error","No Data Found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("Showroom_id   :   " +res.getString(0)+"\n");
                    buffer.append("Company   :   " +res.getString(1)+"\n");
                    buffer.append("Location     :    " +res.getString(2)+"\n");
                    buffer.append("Address   :    " +res.getString(3)+"\n");
                    buffer.append("Pincode      :   " +res.getString(4)+"\n");
                    buffer.append("Phno     :   " +res.getString(5)+"\n\n");
                }
                ShowMessage("Specification",buffer.toString());
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

    public void UpdateData() {
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                String check = Showroom_no.getText().toString();
                if (check.isEmpty()) {
                    Toast.makeText(showroom.this, "Please Enter the CAR_ID", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isUpdate = myDb.UpdateShow(Integer.parseInt(Showroom_no.getText().toString()),
                            Company.getSelectedItem().toString(),
                            Location.getText().toString(),
                            Address.getText().toString(),
                            Pincode.getText().toString(),
                            PHno.getText().toString()
                    );
                    if (isUpdate) {
                        Toast.makeText(showroom.this, "Data Updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(showroom.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void DeleteData() {
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                String check = Showroom_no.getText().toString();
                if (check.isEmpty()) {
                    Toast.makeText(showroom.this, "Please Enter the Showroom_ID", Toast.LENGTH_SHORT).show();
                } else {
                    Integer DeletedRows = myDb.deleteShow(Integer.parseInt(Showroom_no.getText().toString()));
                    if (DeletedRows > 0) {
                        Toast.makeText(showroom.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(showroom.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}