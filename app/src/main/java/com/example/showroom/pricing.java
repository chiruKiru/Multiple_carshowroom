package com.example.showroom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class pricing extends AppCompatActivity {
    DBHelper myDb;

    EditText Car_id,Showroom_price,Tax,Other_tax;
    Button Insert,Update,View,Delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDb = new DBHelper(this);

        setContentView(R.layout.activity_pricing);

        Car_id = (EditText) findViewById(R.id.car_id);
        Showroom_price = (EditText) findViewById(R.id.ex_showroom_price);
        Tax = (EditText) findViewById(R.id.roadtax);
        Other_tax = (EditText) findViewById((R.id.othertax));
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
        Insert.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                String check = Car_id.getText().toString();
                if (check.isEmpty()) {
                    Toast.makeText(pricing.this, "Please Enter the CAR_ID", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isInserted = myDb.InsertPrice(Integer.parseInt(Car_id.getText().toString()),
                            Integer.parseInt(Showroom_price.getText().toString()),
                            Integer.parseInt(Tax.getText().toString()),
                            Integer.parseInt(Other_tax.getText().toString())
                    );
                    if (isInserted) {
                        Toast.makeText(pricing.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(pricing.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void ViewAll() {
        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Cursor res = myDb.getPrice();
                if(res.getCount() == 0){
                    ShowMessage("Error","No Data Found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("Car_id      :     " +res.getString(0)+"\n");
                    buffer.append("Showroom Price   :   " +res.getString(1)+"\n");
                    buffer.append("Tax      :   " +res.getString(2)+"\n");
                    buffer.append("Other Tax     :     " +res.getString(3)+"\n");
                    buffer.append("Total    :    " +res.getString(4)+"\n\n");
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
                    Toast.makeText(pricing.this, "Please Enter the CAR_ID", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isUpdate = myDb.UpdatePrice(Integer.parseInt(Car_id.getText().toString()),
                            Integer.parseInt(Showroom_price.getText().toString()),
                            Integer.parseInt(Tax.getText().toString()),
                            Integer.parseInt(Other_tax.getText().toString())
                    );
                    if (isUpdate) {
                        Toast.makeText(pricing.this, "Data Updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(pricing.this, "Data Not Found", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(pricing.this, "Please Enter the Showroom_ID", Toast.LENGTH_SHORT).show();
                } else {
                    Integer DeletedRows = myDb.deletePrice(Integer.parseInt(Car_id.getText().toString()));
                    if (DeletedRows > 0) {
                        Toast.makeText(pricing.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(pricing.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
