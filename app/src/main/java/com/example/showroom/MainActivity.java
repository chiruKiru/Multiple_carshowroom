package com.example.showroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_main);

        Name = (EditText) findViewById(R.id.name);
        Password = (EditText) findViewById(R.id.password);
        Info = (TextView)  findViewById(R.id.info);
        Login = (Button) findViewById(R.id.login);

        Info.setText("No of attempts remaining is: 5");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(),Password.getText().toString());
            }
        });
    }
    private void validate(String UserName ,String UserPassword)
    {
        if ((UserName.equals("Admin")) && (UserPassword.equals("1234"))) {
            Intent intent = new Intent(MainActivity.this,admin2.class);
            startActivity(intent);
        }
        else {
            counter--;

            Info.setText("No of attempts remaining is "+ toString().valueOf(counter));
            if(counter == 0){
                Login.setEnabled(false);
            }
        }
    }
}