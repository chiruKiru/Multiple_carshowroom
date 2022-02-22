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

public class admin5_employee extends AppCompatActivity {

    DBHelper myDb;
    EditText SSn,Fname,Lname;
    Spinner Company,gender,Showroom_no;
    Button Insert,Delete,Update,View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDb = new DBHelper(this);
        setContentView(R.layout.activity_admin5_employee);

        SSn = (EditText) findViewById(R.id.ssn);
        Fname = (EditText) findViewById(R.id.fname);
        Lname = (EditText) findViewById(R.id.lname);
        Company = (Spinner) findViewById(R.id.company);
        gender = (Spinner) findViewById(R.id.gender);
        Showroom_no = (Spinner) findViewById(R.id.showroom_no);
        Insert = (Button) findViewById(R.id.insert);
        View = (Button) findViewById(R.id.view);
        Update = (Button)findViewById(R.id.update);
        Delete = (Button) findViewById(R.id.delete);

        AddData();
        viewData();
        UpdateData();
        DeleteData();
}

    void AddData() {
        Insert.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {

                String check = SSn.getText().toString();
                if (check.isEmpty()) {
                    Toast.makeText(admin5_employee.this, "Please Enter the SSN", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isInserted = myDb.InsertEmp(Integer.parseInt(SSn.getText().toString()),
                            Fname.getText().toString(),
                            Lname.getText().toString(),
                            Company.getSelectedItem().toString(),
                            gender.getSelectedItem().toString(),
                            Integer.parseInt(Showroom_no.getSelectedItem().toString()));

                    if (isInserted == true) {
                        Toast.makeText(admin5_employee.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(admin5_employee.this, "Already Present", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    void viewData() {
        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Cursor res = myDb.getEmp();
                if(res.getCount() == 0){
                    ShowMessage("Error","No Data Found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("SSn  :   " +res.getString(0)+"\n");
                    buffer.append("F_name    :  " +res.getString(1)+"\n");
                    buffer.append("L_Name   :   " +res.getString(2)+"\n");
                    buffer.append("Company  :   " +res.getString(3)+"\n");
                    buffer.append("Gender    :   " +res.getString(4)+"\n");
                    buffer.append("Showroom_no   :   " +res.getString(5)+"\n\n");
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
    public void UpdateData() {
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                String check = SSn.getText().toString();
                if (check.isEmpty()) {
                    Toast.makeText(admin5_employee.this, "Please Enter the SSN", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isUpdated = myDb.UpdateEmp(Integer.parseInt(SSn.getText().toString()),
                            Fname.getText().toString(),
                            Lname.getText().toString(),
                            Company.getSelectedItem().toString(),
                            gender.getSelectedItem().toString(),
                            Integer.parseInt(Showroom_no.getSelectedItem().toString()));

                    if (isUpdated == true) {
                        Toast.makeText(admin5_employee.this, "Data Updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(admin5_employee.this, "Data not Updated", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public void DeleteData() {
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                String check = SSn.getText().toString();
                if (check.isEmpty()) {
                    Toast.makeText(admin5_employee.this, "Please Enter the SSN", Toast.LENGTH_SHORT).show();
                } else {
                    boolean DeletedRows = myDb.deleteEmp(Integer.parseInt(SSn.getText().toString()));

                    if (DeletedRows) {
                        Toast.makeText(admin5_employee.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(admin5_employee.this, "Not Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}