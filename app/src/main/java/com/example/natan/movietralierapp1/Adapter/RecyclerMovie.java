package com.example.natan.movietralierapp1.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.natan.movietralierapp1.MainActivity;
import com.squareup.picasso.Picasso;

import com.example.natan.movietralierapp1.R;

import java.util.List;

/**
 * Created by natan on 11/20/2017.
 */

public class RecyclerMovie extends RecyclerView.Adapter<RecyclerMovie.MyViewHolder> {


    private List<Movie> mMovieList;


    public RecyclerMovie(MainActivity mainActivity, List<Movie> movieList) {
        mMovieList = movieList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Movie movie = mMovieList.get(position);
        Context context=holder.img_movie.getContext();
        Picasso.with(context).load("https://image.tmdb.org/t/p/w500" + movie.getImage()).into(holder.img_movie);







    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView img_movie;


        public MyViewHolder(View itemView) {
            super(itemView);
            img_movie=itemView.findViewById(R.id.imageView);

        }
    }
}
