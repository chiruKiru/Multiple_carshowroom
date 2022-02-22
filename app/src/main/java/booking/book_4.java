package booking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.showroom.DBHelper;
import com.example.showroom.R;

import java.util.ArrayList;
import java.util.Calendar;

import testdrive.fin;
import testdrive.test_booking;

public class book_4 extends AppCompatActivity {

    DBHelper myDb;
    EditText Date, Fname, Lname, DL, PH;
    Spinner Timmings, Showroom;
    Button OK;
    DatePickerDialog.OnDateSetListener setListener;
    Bitmap ap,scale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book4);

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
        Timmings = (Spinner) findViewById(R.id.payment);
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(book_4.this,
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
                    String a, b;
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
                    boolean isUpdated = myDb.InsertCar_booking(Fname.getText().toString(), Lname.getText().toString(),
                            Date.getText().toString(), DL.getText().toString(), PH.getText().toString(), Showroom.getSelectedItem().toString(),
                            Timmings.getSelectedItem().toString(), Data.get(2), Data.get(3), Data.get(1), Data.get(0));

                    Cursor r = myDb.Final(a, b);
                    while (r.moveToNext()) {
                        buffer.append("Name     :    " + Fname.getText().toString() + "  " + Lname.getText().toString() + "\n\n");
                        buffer.append("Drivers Licence No        :       " + DL.getText().toString() + "\n\n");
                        buffer.append("Date     :    " + Date.getText().toString() + "\n\n");
                        buffer.append("Appointed Employee       :   " + r.getString(0) + " " + r.getString(1) + "\n\n");
                        buffer.append("Payment      :   " + Timmings.getSelectedItem().toString() + "\n\n");
                        buffer.append("Address       :      " + r.getString(2) + "\n\n");
                        buffer.append("Booked location      :    " + Showroom.getSelectedItem().toString() + "\n\n");
                        buffer.append("Pincode      :    " + r.getString(3) + "\n\n");
                        buffer.append("Phno     :    " + r.getString(4) + "\n");
                        buffer.append("\nBOOKED SUCCESSFULL BRING DOCUMENTS");
                    }

                    Intent intent = new Intent(book_4.this, book_5.class);
                    intent.putExtra("T", buffer.toString());
                    startActivity(intent);
            }
        });
    }
    public void ShowMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(book_4.this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}