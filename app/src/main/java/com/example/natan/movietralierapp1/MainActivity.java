package com.example.natan.movietralierapp1;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;


import com.example.natan.movietralierapp1.Adapter.Movie;
import com.example.natan.movietralierapp1.Adapter.RecyclerMovie;
import com.example.natan.movietralierapp1.Network.NetworkUtils;

import java.net.URL;
import java.util.List;

public class MainActivity extends Activity {

    private RecyclerMovie mRecyclerMovie;
    private RecyclerView mrecyclerView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mrecyclerView = findViewById(R.id.recyclerView);
        mProgressBar = findViewById(R.id.progress_bar);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this, 2);


        mrecyclerView.setLayoutManager(mLayoutManager);
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());
        build("popularity.desc");



    }


    //Creating inner class for Async Task

    public class MovieDbQUeryTask extends AsyncTask<URL, Void, List<Movie>> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }


        @Override
        protected List<Movie> doInBackground(URL... urls) {

            List<Movie> result = NetworkUtils.fetchMovieData(urls[0]);
            return result;
        }


        @Override
        protected void onPostExecute(List<Movie> movies) {


            mProgressBar.setVisibility(View.INVISIBLE);
            mRecyclerMovie = new RecyclerMovie(MainActivity.this, movies, new RecyclerMovie.ListItemClickListener() {
                @Override
                public void onListItemClick(Movie movie) {
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("data", movie);
                    startActivity(intent);

                }
            });

            mrecyclerView.setAdapter(mRecyclerMovie);
            mRecyclerMovie.notifyDataSetChanged();

        }
    }


    // For menu settings

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.highest_Rated:
                build("vote_count.desc");


                break;

            case R.id.most_popular:
                build("popularity.desc");
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private URL build(String sort) {
        URL final_Url = NetworkUtils.buildURl(sort);
        new MovieDbQUeryTask().execute(final_Url);
        return final_Url;
    }
}
