package com.example.showroom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class admin8_specification extends AppCompatActivity {

    DBHelper myDb;

    EditText Car_id,Model,Mileage,Engine_cc;
    CheckBox red,black,gray,blue,maroon,brown;
    Spinner Fuel_type,Trans,Seater;
    Button Insert ,Update,Delete,View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin8_specification);
        myDb = new DBHelper(this);

        Car_id = (EditText)  findViewById(R.id.car_id);
        Model = (EditText) findViewById(R.id.model);
        Mileage = (EditText)  findViewById(R.id.mileage);
        Engine_cc = (EditText)  findViewById(R.id.Engine);
        red = (CheckBox) findViewById(R.id.color1);
        black = (CheckBox) findViewById(R.id.color2);
        gray = (CheckBox) findViewById(R.id.color3);
        blue = (CheckBox) findViewById(R.id.color4);
        maroon = (CheckBox) findViewById(R.id.color5);
        brown = (CheckBox) findViewById(R.id.color6);
        Fuel_type = (Spinner) findViewById(R.id.fueltype);
        Trans = (Spinner) findViewById(R.id.transmissiontype);
        Seater = (Spinner) findViewById(R.id.seater);
        Insert = (Button) findViewById(R.id.insert);
        Delete = (Button) findViewById(R.id.delete);
        Update = (Button) findViewById(R.id.update);
        View = (Button) findViewById(R.id.view);


        InsertData();
        ViewAll();
        UpdateData();
        DeleteData();
    }
    public void InsertData() {
        Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                String check = Car_id.getText().toString();
                if (check.isEmpty()) {
                    Toast.makeText(admin8_specification.this, "Please Enter the CAR_ID", Toast.LENGTH_SHORT).show();
                } else {
                    StringBuffer color = new StringBuffer();
                    if (red.isChecked())
                        color.append("Red, ");
                    if (black.isChecked())
                        color.append("Black, ");
                    if (gray.isChecked())
                        color.append("Silver, ");
                    if (blue.isChecked())
                        color.append("White, ");
                    if (maroon.isChecked())
                        color.append("maroon, ");
                    if (brown.isChecked())
                        color.append("Brown, ");

                    boolean isInserted = myDb.InsertSpec(Integer.parseInt(Car_id.getText().toString()),
                            Model.getText().toString(),
                            Mileage.getText().toString(),
                            Engine_cc.getText().toString(),
                            Fuel_type.getSelectedItem().toString(),
                            Trans.getSelectedItem().toString(),
                            Seater.getSelectedItem().toString(),
                            color.toString()
                    );
                    if (isInserted) {
                        Toast.makeText(admin8_specification.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(admin8_specification.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void ViewAll() {
        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Cursor res = myDb.getSpec();
                if(res.getCount() == 0){
                    ShowMessage("Error","No Data Found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("CAR_ID   :    " +res.getString(0)+"\n");
                    buffer.append("Model    :   " +res.getString(1)+"\n");
                    buffer.append("Mileage     :     " +res.getString(2)+"\n");
                    buffer.append("Engine_cc    :    " +res.getString(3)+"\n");
                    buffer.append("Colors Avail     :   " +res.getString(4)+"\n");
                    buffer.append("Fuel Type    :    " +res.getString(5)+"\n");
                    buffer.append("Transmission     :   " +res.getString(6)+"\n");
                    buffer.append("Seater   :    " +res.getString(7)+"\n\n");
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
                String check = Car_id.getText().toString();
                if (check.isEmpty()) {
                    Toast.makeText(admin8_specification.this, "Please Enter the CAR_ID", Toast.LENGTH_SHORT).show();
                } else {
                    StringBuffer color = new StringBuffer();
                    if (red.isChecked())
                        color.append("Red, ");
                    if (black.isChecked())
                        color.append("Black, ");
                    if (gray.isChecked())
                        color.append("Silver, ");
                    if (blue.isChecked())
                        color.append("White, ");
                    if (maroon.isChecked())
                        color.append("maroon, ");
                    if (brown.isChecked())
                        color.append("Brown, ");

                    boolean isUpdate = myDb.Updatespec(Integer.parseInt(Car_id.getText().toString()),
                            Model.getText().toString(),
                            Mileage.getText().toString(),
                            Engine_cc.getText().toString(),
                            Fuel_type.getSelectedItem().toString(),
                            Trans.getSelectedItem().toString(),
                            Seater.getSelectedItem().toString(),
                            color.toString()
                    );
                    if (isUpdate) {
                        Toast.makeText(admin8_specification.this, "Data Updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(admin8_specification.this, "Data Not Found", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(admin8_specification.this, "Please Enter the CAR_ID", Toast.LENGTH_SHORT).show();
                } else {
                    Integer DeletedRows = myDb.deletespec(Integer.parseInt(Car_id.getText().toString()));
                    if (DeletedRows > 0) {
                        Toast.makeText(admin8_specification.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(admin8_specification.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}