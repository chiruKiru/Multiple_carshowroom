package com.example.showroom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class admin7_rating extends AppCompatActivity {

    DBHelper myDb;
    EditText Car_id,Model,Comfort,Performance,Fuel_economy,Value_for_Money,Exterior;
    Button Insert,Delete,View,Update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDb = new DBHelper(this);
        setContentView(R.layout.activity_admin7_rating);

        Car_id = (EditText) findViewById(R.id.car_id);
        Model = (EditText) findViewById(R.id.model);
        Comfort = (EditText) findViewById(R.id.comfort);
        Performance = (EditText) findViewById(R.id.performance);
        Fuel_economy = (EditText) findViewById(R.id.fueleconomy);
        Value_for_Money = (EditText) findViewById(R.id.valueformoney);
        Exterior = (EditText) findViewById(R.id.exterior);
        Insert = (Button) findViewById(R.id.insert);
        Delete = (Button) findViewById(R.id.delete);
        Update = (Button) findViewById(R.id.update);
        View = (Button) findViewById(R.id.view);

        AddData();
        ViewAll();
        UpdateData();
        DeleteData();
    }
    public void AddData() {
        Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                String check = Car_id.getText().toString();
                if (check.isEmpty()) {
                    Toast.makeText(admin7_rating.this, "Please Enter the showroom_ID", Toast.LENGTH_SHORT).show();
                } else{
                    boolean isUpdated = myDb.insertRating(Integer.parseInt(Car_id.getText().toString()),
                            Model.getText().toString(),
                            Comfort.getText().toString(),
                            Performance.getText().toString(),
                            Fuel_economy.getText().toString(),
                            Value_for_Money.getText().toString(),
                            Exterior.getText().toString()
                    );

                if (isUpdated) {
                    Toast.makeText(admin7_rating.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(admin7_rating.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
            }
        }
        });
    }

    public void ViewAll() {
        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Cursor res = myDb.getRating();
                if(res.getCount() == 0){
                    ShowMessage("ERROR","No DATA Found");
                    return;
                }
                StringBuffer stringBuffer = new StringBuffer();
                while (res.moveToNext()){
                    stringBuffer.append("CAR_ID     :    "+res.getString(0)+"\n");
                    stringBuffer.append("Model  :   "+res.getString(1)+"\n");
                    stringBuffer.append("Comfort     :   "+res.getString(2)+"\n");
                    stringBuffer.append("Performance    :    "+res.getString(3)+"\n");
                    stringBuffer.append("Fuel_Economy   :    "+res.getString(4)+"\n");
                    stringBuffer.append("Value_for_money     :   "+res.getString(5)+"\n");
                    stringBuffer.append("Exterior    :   "+res.getString(6)+"\n\n");
                }
                ShowMessage("Ratings",stringBuffer.toString());
            }
        });
    }

    public void ShowMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void UpdateData() {
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                String check = Car_id.getText().toString();
                if (check.isEmpty()) {
                    Toast.makeText(admin7_rating.this, "Please Enter the showroom_ID", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isUpdated = myDb.updateRating(Integer.parseInt(Car_id.getText().toString()),
                            Model.getText().toString(),
                            Comfort.getText().toString(),
                            Performance.getText().toString(),
                            Fuel_economy.getText().toString(),
                            Value_for_Money.getText().toString(),
                            Exterior.getText().toString());

                    if (isUpdated) {
                        Toast.makeText(admin7_rating.this, "Data Updated", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(admin7_rating.this, "Data Not Updated", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(admin7_rating.this, "Please Enter the showroom_ID", Toast.LENGTH_SHORT).show();
                } else {
                Integer res = myDb.deleteRating(Integer.parseInt(Car_id.getText().toString()));

                if(res > 0){
                    Toast.makeText(admin7_rating.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(admin7_rating.this, "Data not Found", Toast.LENGTH_SHORT).show();
            }
                }
        });
    }
}