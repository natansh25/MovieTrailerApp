package com.example.natan.movietralierapp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.natan.movietralierapp1.Network.NetworkUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1=findViewById(R.id.txt);
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
                txt1.setText(sortUrl.toString());



                break;

            case R.id.most_popular:
                Toast.makeText(this, "most popular", Toast.LENGTH_SHORT).show();
                URL sortUrl2= NetworkUtils.buildURl("popularity.desc");
                txt1.setText(sortUrl2.toString());
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
