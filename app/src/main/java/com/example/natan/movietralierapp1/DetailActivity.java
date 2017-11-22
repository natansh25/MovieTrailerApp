package com.example.natan.movietralierapp1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.natan.movietralierapp1.Adapter.Movie;
import com.squareup.picasso.Picasso;

public class DetailActivity extends Activity {

    TextView t,v,r,f;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        t=findViewById(R.id.release);
        v=findViewById(R.id.vote);
        r=findViewById(R.id.title);
        img=findViewById(R.id.imageView);
        f=findViewById(R.id.date);


        Movie movie=(Movie) getIntent().getParcelableExtra("data");
        t.setText(movie.getTitle());
        v.setText(movie.getVoteAverage());
        r.setText(movie.getReleaseDate());
        Picasso.with(img.getContext()).load(movie.getImage()).into(img);

    }
}
