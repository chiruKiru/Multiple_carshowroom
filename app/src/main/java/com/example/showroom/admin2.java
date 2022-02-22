package com.example.showroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class admin2 extends AppCompatActivity {

    private Button Cars;
    private Button Specification;
    private Button Testdrive;
    private Button Employee;
    private Button Booking;
    private Button Rating;
    private Button Showroom;
    private Button Pricing,Images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin2);

        Cars = (Button) findViewById(R.id.cars);
        Specification = (Button) findViewById(R.id.specification);
        Testdrive = (Button) findViewById(R.id.testdrive);
        Employee = (Button) findViewById(R.id.employee);
        Booking = (Button) findViewById(R.id.booking);
        Rating = (Button) findViewById(R.id.rating);
        Showroom = (Button) findViewById(R.id.showroom);
        Pricing = (Button) findViewById(R.id.pricing);
        Images = (Button) findViewById(R.id.images);

        Cars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin2.this,cars_main.class);
                startActivity(intent);
            }
        });

        Specification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin2.this,admin8_specification.class);
                startActivity(intent);
            }
        });

        Testdrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin2.this,admin4_testdrive.class);
                startActivity(intent);
            }
        });

        Employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin2.this,admin5_employee.class);
                startActivity(intent);
            }
        });

        Booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin2.this,admin6_booking.class);
                startActivity(intent);
            }
        });

        Rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin2.this,admin7_rating.class);
                startActivity(intent);
            }
        });

        Showroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin2.this,showroom.class);
                startActivity(intent);
            }
        });

        Pricing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin2.this,pricing.class);
                startActivity(intent);
            }
        });

        Images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin2.this,Images.class);
                startActivity(intent);
            }
        });
    }

}