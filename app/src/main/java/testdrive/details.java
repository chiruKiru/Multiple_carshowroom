package testdrive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.showroom.*;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

public class details extends AppCompatActivity {
    DBHelper myDb;
    TextView First ,Details,Rating;
    YouTubePlayerView Video;
    Button Book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        First = (TextView) findViewById(R.id.welcome);
        Details = (TextView) findViewById(R.id.Details);
        Video = (YouTubePlayerView) findViewById(R.id.youtube_player_view);
        Book = (Button) findViewById(R.id.next);
        Rating = (TextView) findViewById(R.id.Rating) ;

        String ID;
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
        Details.setText(data.get(1).toString());
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
                Intent intent = new Intent(details.this,test_booking.class);
               intent.putExtra("de",car_id);
               startActivity(intent);
           }
       });
    }
}