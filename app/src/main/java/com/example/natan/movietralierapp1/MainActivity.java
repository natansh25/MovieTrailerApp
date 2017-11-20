package com.example.natan.movietralierapp1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.natan.movietralierapp1.Network.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView txt1,txtjson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1=findViewById(R.id.txt);
        txtjson=findViewById(R.id.json);
    }


    //Creating inner class for Async Task

    public class MovieDbQUeryTask extends AsyncTask<URL,Void,String>{



        @Override
        protected String doInBackground(URL... urls) {

            URL searchUrl=urls[0];
            String MovieDbResults=null;
            try{
                MovieDbResults=NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }


            return MovieDbResults;
        }


        @Override
        protected void onPostExecute(String s) {
            txtjson.setText(s.toString());
            Log.i("msg",s);
        }
    }




    // For menu settings

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.higest_rated:
                Toast.makeText(this, "Highest Rated", Toast.LENGTH_SHORT).show();
                URL sortUrl= NetworkUtils.buildURl("vote_count.desc");
                Log.i("url",sortUrl.toString());
                txt1.setText(sortUrl.toString());
                new MovieDbQUeryTask().execute(sortUrl);



                break;

            case R.id.most_popular:
                Toast.makeText(this, "most popular", Toast.LENGTH_SHORT).show();
                URL sortUrl2= NetworkUtils.buildURl("popularity.desc");
                txt1.setText(sortUrl2.toString());
                new MovieDbQUeryTask().execute(sortUrl2);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
