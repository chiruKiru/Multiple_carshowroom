package com.example.showroom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Images extends AppCompatActivity {

    DBHelper myDb;

    EditText Images,Videos,Car_id;
    Button Insert,Update,Delete,View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDb = new DBHelper(this);
        setContentView(R.layout.activity_images);

        Car_id = (EditText) findViewById(R.id.car_id);
        Images = (EditText) findViewById(R.id.images);
        Videos = (EditText) findViewById(R.id.videos);
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
        Insert.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                String check = Car_id.getText().toString();
                if (check.isEmpty()) {
                    Toast.makeText(Images.this, "Please Enter the showroom_ID", Toast.LENGTH_SHORT).show();
                } else{
                    boolean isUpdated = myDb.insertImg(Integer.parseInt(Car_id.getText().toString()),
                            Images.getText().toString(),
                            Videos.getText().toString()
                    );

                    if (isUpdated) {
                        Toast.makeText(Images.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(Images.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void ViewAll() {
        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Cursor res = myDb.getImg();
                if(res.getCount() == 0){
                    ShowMessage("ERROR","No DATA Found");
                    return;
                }
                StringBuffer stringBuffer = new StringBuffer();
                while (res.moveToNext()) {
                    stringBuffer.append("CAR_ID     :    " + res.getString(0) + "\n");
                    stringBuffer.append("Image Link     :   " + res.getString(1) + "\n");
                    stringBuffer.append("Video LInk     :   " + res.getString(2) + "\n\n");
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
                    Toast.makeText(Images.this, "Please Enter the showroom_ID", Toast.LENGTH_SHORT).show();
                } else{
                boolean isUpdated = myDb.updateImg(Integer.parseInt(Car_id.getText().toString()),
                        Images.getText().toString(),
                        Videos.getText().toString()
                );
                if(isUpdated){
                    Toast.makeText(Images.this, "Data Updated", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(Images.this, "Data Not Updated", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(Images.this, "Please Enter the showroom_ID", Toast.LENGTH_SHORT).show();
                } else {
                    Integer res = myDb.deleteImg(Integer.parseInt(Car_id.getText().toString()));

                    if (res > 0) {
                        Toast.makeText(Images.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(Images.this, "Data not Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
