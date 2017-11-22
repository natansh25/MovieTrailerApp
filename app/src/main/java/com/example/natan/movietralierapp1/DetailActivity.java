package com.example.natan.movietralierapp1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.natan.movietralierapp1.Adapter.Movie;
import com.squareup.picasso.Picasso;

public class DetailActivity extends Activity {

    TextView t, v, r, f;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        t = findViewById(R.id.release);
        v = findViewById(R.id.vote);
        r = findViewById(R.id.title);
        img = findViewById(R.id.imageView2);
        f = findViewById(R.id.date);


        getActionBar().setDisplayHomeAsUpEnabled(true);

        //setting back button


        Movie movie = (Movie) getIntent().getParcelableExtra("data");
        t.setText(movie.getTitle());
        v.setText(movie.getVoteAverage());
        r.setText(movie.getReleaseDate());
        Log.i("img", movie.getImage());
        Picasso.with(img.getContext()).load("https://image.tmdb.org/t/p/w500" + movie.getImage()).into(img);

    }
}
