package com.example.lukelin.udacitypopularmovies.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lukelin.udacitypopularmovies.R;
import com.example.lukelin.udacitypopularmovies.pojos.Movie;

import java.util.List;

/**
 * Created by lukelin on 2016-09-16.
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private Context context;
    private List<Movie> mValues;
    private MovieListFragment.OnItemSelectedListener listener;
    private int currentPosition = 0;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final ImageView mImageView;
        public final TextView mName;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = (ImageView) view.findViewById(R.id.list_item_image);
            mName = (TextView) view.findViewById(R.id.list_item_title);
        }

    }

    public MovieListAdapter(Context context, List<Movie> items, MovieListFragment.OnItemSelectedListener listener) {
        this.context = context;
        mValues = items;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(currentPosition == position) holder.mView.setBackgroundResource(R.color.cardview_dark_background);
        holder.mName.setText(mValues.get(position).getTitle());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentPosition(position);
                if(listener != null) listener.onItemSelected(mValues.get(position));
            }
        });
        Glide.with(holder.mImageView.getContext())
                .load(mValues.get(position).getPoster_path())
                .fitCenter()
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int position) {
        int temp = currentPosition;
        currentPosition = position;
        notifyItemChanged(temp);
        notifyItemChanged(currentPosition);
    }
}