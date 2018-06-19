package com.example.natan.movietralierapp1;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.natan.movietralierapp1.Adapter.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.release)
    TextView txt_Release;
    @BindView(R.id.rating)
    TextView txt_Rating;
    @BindView(R.id.title)
    TextView txt_Title;
    @BindView(R.id.image_poster)
    ImageView img_Poster;
    @BindView(R.id.plot)
    TextView txt_Plot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);


        getActionBar().setDisplayHomeAsUpEnabled(true);


        Movie movie = getIntent().getParcelableExtra("data");

        txt_Title.setText(movie.getTitle());
        txt_Plot.setText(movie.getOverview());
        txt_Rating.setText(movie.getVoteAverage() + "/10");
        txt_Release.setText(movie.getReleaseDate());
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + movie.getImage()).into(img_Poster);
        Log.d("xxx", "https://image.tmdb.org/t/p/w500" + movie.getBackImage());

    }
}
