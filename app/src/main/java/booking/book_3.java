package booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.showroom.DBHelper;
import com.example.showroom.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

import testdrive.details;
import testdrive.test_booking;

public class book_3 extends AppCompatActivity {

    DBHelper myDb;
    TextView First ,Details,Rating;
    YouTubePlayerView Video;
    Button Book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book3);

        First = (TextView) findViewById(R.id.welcome);
        Details = (TextView) findViewById(R.id.Details);
        Video = (YouTubePlayerView) findViewById(R.id.youtube_player_view);
        Book = (Button) findViewById(R.id.next);
        Rating = (TextView) findViewById(R.id.Rating);


        String ID;
        String h;
        String car_id;
        myDb = new DBHelper(this);

        ArrayList<String > data = new ArrayList<String >(100);
        ArrayList<String > rating = new ArrayList<String >(100);
        Bundle bundle;
        bundle = getIntent().getExtras();
        data = bundle.getStringArrayList("data");
        car_id = bundle.getString("t");
        rating = bundle.getStringArrayList("r");

        ID = data.get(2);
        First.setText(data.get(0));
        Details.setText(data.get(1));
        Rating.setText(rating.get(0));

        Video.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                String videoId = ID;
                youTubePlayer.cueVideo(ID,0);
            }
        });

        Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(book_3.this, booking.book_4.class);
                intent.putExtra("de",car_id);
                startActivity(intent);
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
}