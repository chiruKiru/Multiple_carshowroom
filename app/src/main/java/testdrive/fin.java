package testdrive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.showroom.*;

public class fin extends AppCompatActivity {

    TextView A;
    ImageView b;
    Button Back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin);


        A = (TextView) findViewById(R.id.a);
        Back = (Button) findViewById(R.id.back);
        Bundle bundle;
        bundle = getIntent().getExtras();
        String a = bundle.getString("T");

        A.setText(a.toString());
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fin.this,First_Page.class);
                startActivity(intent);
            }
        });
    }
}