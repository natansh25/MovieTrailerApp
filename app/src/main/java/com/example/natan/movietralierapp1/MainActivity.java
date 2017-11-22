package com.example.natan.movietralierapp1;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.natan.movietralierapp1.Adapter.Movie;
import com.example.natan.movietralierapp1.Adapter.RecyclerMovie;
import com.example.natan.movietralierapp1.Network.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    TextView txt1, txtjson;
    private RecyclerMovie mRecyclerMovie;
    private RecyclerView mrecyclerView;
    private ProgressBar mProgressBar;
    private Toast mToast;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            //txt1=findViewById(R.id.txt);
            //txtjson=findViewById(R.id.json);

            mrecyclerView = findViewById(R.id.recyclerView);
            mProgressBar=findViewById(R.id.progress_bar);

            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this, 2);


            mrecyclerView.setLayoutManager(mLayoutManager);
            mrecyclerView.setItemAnimator(new DefaultItemAnimator());


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
            /*
            URL searchUrl=urls[0];
            String MovieDbResults=null;
            try{
                MovieDbResults=NetworkUtils.getReponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }


            return MovieDbResults;
            */

            List<Movie> result = NetworkUtils.fetchMovieData(urls[0]);
            //perfect
            Log.i("tag12", String.valueOf(urls[0]));

            return result;
        }


        @Override
        protected void onPostExecute(List<Movie> movies) {


            mProgressBar.setVisibility(View.INVISIBLE);
            mRecyclerMovie = new RecyclerMovie(MainActivity.this, movies, new RecyclerMovie.ListItemClickListener() {
                @Override
                public void onListItemClick(int clickedItemIndex) {
                    Toast.makeText(MainActivity.this, String.valueOf(clickedItemIndex), Toast.LENGTH_SHORT).show();
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
            case R.id.higest_rated:
                Toast.makeText(this, "Highest Rated", Toast.LENGTH_SHORT).show();
                URL sortUrl = NetworkUtils.buildURl("vote_count.desc");
                Log.i("sort", String.valueOf(sortUrl));
                //Log.i("url", sortUrl.toString());
                // txt1.setText(sortUrl.toString());

                new MovieDbQUeryTask().execute(sortUrl);


                break;

            case R.id.most_popular:
                //Toast.makeText(this, "most popular", Toast.LENGTH_SHORT).show();
                URL sortUrl2 = NetworkUtils.buildURl("popularity.desc");
                //txt1.setText(sortUrl2.toString());
                new MovieDbQUeryTask().execute(sortUrl2);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
