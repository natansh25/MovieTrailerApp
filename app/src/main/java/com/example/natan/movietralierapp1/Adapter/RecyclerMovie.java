
package com.example.natan.movietralierapp1.Adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.natan.movietralierapp1.MainActivity;
import com.example.natan.movietralierapp1.picasso.RoundedTransformation;
import com.squareup.picasso.Picasso;

import com.example.natan.movietralierapp1.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by natan on 11/20/2017.
 */

public class RecyclerMovie extends RecyclerView.Adapter<RecyclerMovie.MyViewHolder> {


    private List<Movie> mMovieList;
    //Implementing on click listner
    final private ListItemClickListener mOnClickListener;

    //Interface

    public interface ListItemClickListener {

        void onListItemClick(Movie movie,ImageView imageView);
    }


    public RecyclerMovie(MainActivity mainActivity, List<Movie> movieList, ListItemClickListener listener) {
        mMovieList = movieList;
        mOnClickListener = listener;
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

        Picasso.get().load("https://image.tmdb.org/t/p/w500" + movie.getImage()).transform(new RoundedTransformation(14, 0)).into(holder.img_movie);
        holder.bind(mMovieList.get(position), mOnClickListener);
        ViewCompat.setTransitionName(holder.img_movie, movie.getTitle());


    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView)
        ImageView img_movie;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void bind(final Movie movie, final ListItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onListItemClick(movie,img_movie);
                }
            });
        }


    }
}
