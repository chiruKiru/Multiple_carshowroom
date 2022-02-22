package testdrive;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.showroom.*;

import java.util.ArrayList;
import java.util.Calendar;

import kotlin.jvm.functions.FunctionN;

public class test_booking extends AppCompatActivity {

    DBHelper myDb;
    EditText Date, Fname, Lname, DL, PH;
    Spinner Timmings, Showroom;
    Button OK;
    DatePickerDialog.OnDateSetListener setListener;
    Bitmap ap,scale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_booking);
        myDb = new DBHelper(this);

        String hi;
        ArrayList<String > Data = new ArrayList<String >(100);
        ArrayList<String > v = new ArrayList<String >(100);

        Bundle bundle;
        bundle = getIntent().getExtras();
        hi = bundle.getString("de");

        Date = (EditText) findViewById(R.id.Date);
        Fname = (EditText) findViewById(R.id.fname);
        Lname = (EditText) findViewById(R.id.lname);
        DL = (EditText) findViewById(R.id.DLNO);
        PH = (EditText) findViewById(R.id.phno);
        Timmings = (Spinner) findViewById(R.id.Timmings);
        Showroom = (Spinner) findViewById(R.id.Showroom);
        OK = (Button) findViewById(R.id.ok1);
        ap = BitmapFactory.decodeResource(getResources(),R.drawable.img);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(calendar.YEAR);
        final int month = calendar.get(calendar.MONTH);
        final int day = calendar.get(calendar.DATE);

        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(test_booking.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth, setListener, year, month, day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayofmonth) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                Date.setText(date);

            }
        };
        int a = 0;
        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a,b;
                StringBuffer buffer = new StringBuffer();
                Cursor res = myDb.Book_test(Showroom.getSelectedItem().toString(), hi);
                while (res.moveToNext()) {
                    Data.add(res.getString(0));
                    Data.add(res.getString(1));
                    Data.add(res.getString(2));
                    Data.add(res.getString(3));
                    Data.add(res.getString(4));
                }
                a = Data.get(0);
                b = Data.get(1);
               boolean isUpdated = myDb.InsertTest_Drive(Fname.getText().toString(), Lname.getText().toString(),
                        Date.getText().toString(), DL.getText().toString(), PH.getText().toString(), Showroom.getSelectedItem().toString(),
                        Timmings.getSelectedItem().toString(), a, Data.get(2), Data.get(3), Data.get(4));

                Cursor r = myDb.Final(a,b);
                while (r.moveToNext()) {
                    buffer.append("Name      :      " +Fname.getText().toString()+"  " +Lname.getText().toString() +"\n\n");
                    buffer.append("Drivers Licence No       :    " +DL.getText().toString()+ "\n\n");
                    buffer.append("Date     :    " +Date.getText().toString()+"\n\n");
                    buffer.append("Appointed Employee       :    " + r.getString(0) +" " +r.getString(1)+"\n\n");
                    buffer.append("Timings      :     " + Timmings.getSelectedItem().toString()+"\n\n");
                    buffer.append("Address   :   " +r.getString(2)+"\n\n");
                    buffer.append("Booked location : " +Showroom.getSelectedItem().toString()+"\n\n");
                    buffer.append("Pincode :- " +r.getString(3)+"\n\n");
                    buffer.append("Phno :- " +r.getString(4)+"\n");
                    buffer.append("BOOKED SUCCESSFULL BRING DOCUMENTS");
                }

                Intent intent = new Intent(test_booking.this,fin.class);
                intent.putExtra("T",buffer.toString());
                startActivity(intent);
            }
        });
    }
    public void ShowMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(test_booking.this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
