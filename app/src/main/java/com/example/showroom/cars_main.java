package com.example.showroom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class cars_main extends AppCompatActivity {

    DBHelper myDb;

    EditText Car_id,Model;
    Spinner Type,Company;
    Button Insert ,Update,Delete,View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_main);

        myDb = new DBHelper(this);

        Car_id = (EditText) findViewById(R.id.car_id);
        Model = (EditText) findViewById(R.id.model);
        Type = (Spinner) findViewById(R.id.type);
        Company = (Spinner) findViewById(R.id.company);
        Insert  = (Button) findViewById(R.id.insert);
        View = (Button) findViewById(R.id.view);
        Update = (Button)findViewById(R.id.update);
        Delete = (Button) findViewById(R.id.delete);


        AddData();
        viewData();
        UpdateData();
        DeleteData();
    }

    void AddData() {
        Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                String check = Car_id.getText().toString();
                if (check.isEmpty()) {
                    Toast.makeText(cars_main.this, "Please Enter the showroom_ID", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isInserted = myDb.InsertCar(Integer.parseInt(Car_id.getText().toString()),
                            Model.getText().toString(),
                            Company.getSelectedItem().toString(),
                            Type.getSelectedItem().toString());

                    if (isInserted == true) {
                        Toast.makeText(cars_main.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(cars_main.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    void viewData() {
        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                    Cursor res = myDb.getCar();
                    if (res.getCount() == 0) {
                        ShowMessage("Error", "No Data Found");
                        return;
                    }

                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("CAR_ID   :    " + res.getString(0) + "\n");
                        buffer.append("Model    :    " + res.getString(1) + "\n");
                        buffer.append("Company   :    " + res.getString(2) + "\n");
                        buffer.append("Type     :   " + res.getString(3) + "\n\n");
                    }
                    ShowMessage("Data", buffer.toString());
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
                String check = Car_id.getText().toString();
                if (check.isEmpty()) {
                    Toast.makeText(cars_main.this, "Please Enter the showroom_ID", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isUpdated = myDb.UpdateCar(Integer.parseInt(Car_id.getText().toString()),
                            Model.getText().toString(),
                            Company.getSelectedItem().toString(),
                            Type.getSelectedItem().toString());

                    if (isUpdated == true) {
                        Toast.makeText(cars_main.this, "Data Updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(cars_main.this, "Data not Updated", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void DeleteData() {
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                String check = Car_id.getText().toString();
                if (check.isEmpty()) {
                    Toast.makeText(cars_main.this, "Please Enter the showroom_ID", Toast.LENGTH_SHORT).show();
                } else {
                    Integer DeletedRows = myDb.deleteCar(Integer.parseInt(Car_id.getText().toString()));

                    if (DeletedRows > 0) {
                        Toast.makeText(cars_main.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(cars_main.this, "Not Found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
